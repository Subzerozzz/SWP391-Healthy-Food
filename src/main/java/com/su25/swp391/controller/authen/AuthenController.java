/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.authen;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.utils.MD5PasswordEncoderUtils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;

/**
 *
 * @author kieud
 */
@WebServlet(name = "AuthenController", urlPatterns = {"/login", "/register", "/home", "/OTP"})
public class AuthenController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String LOGIN_PAGE = "view/Authen/Login.jsp";
    private static final String REGISTER_PAGE = "view/Authen/Register.jsp";
    private static final String HOME_PAGE = "view/homePage/home.jsp";
    private static final String OTP_PAGE = "view/Authen/OTP.jsp";

    AccountDAO accountDAO = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/login":
                request.getRequestDispatcher("view/Authen/Login.jsp").forward(request, response);
                break;
            case "/register":
                request.getRequestDispatcher("view/Authen/Register.jsp").forward(request, response);
                break;
            case "/home":
                request.getRequestDispatcher("view/homePage/home.jsp").forward(request, response);
                break;
            case "/OTP":
                request.getRequestDispatcher("view/Authen/OTP.jsp").forward(request, response);
                break;
            default:
                break;
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/login":
                loginDoPost(request, response);
                break;
            case "/register":
                registerDoPost(request, response);
                break;
            case "/OTP":
                otpDoPost(request, response);
                break;
            default:
                break;
        }
    }

    private String registerDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        String url = null;
        HttpSession session = request.getSession();

       String user_name = request.getParameter("user_name");
       String email =request.getParameter("email");
       String password = request.getParameter("password");

        request.setAttribute("formdata", Map.of(
                "user_name", user_name != null ? user_name : "",
                "email", email != null ? email : "",
                "password", password != null ? password : ""));

        if (user_name == null || user_name.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            session.setAttribute("toastMessage", "All fields are required");
            session.setAttribute("toastType", "error");
            return REGISTER_PAGE;
        }

       
            if (validEmail(email) == true) {
                session.setAttribute("toastMessage", "Email correct format");
                session.setAttribute("toastType", "error");
            }
            else{
                session.setAttribute("toastMessage", "Email incorrect format");
                session.setAttribute("toastType", "error");
            }
            if (validPassword(password) == true) {
                session.setAttribute("toastMessage", "Password correct format");
                session.setAttribute("toastType", "error");
            }
            else{
                session.setAttribute("toastMessage", "Password incorrect format");
                session.setAttribute("toastType", "error");
            }
            if (validUsername(user_name) == true) {
                session.setAttribute("toastMessage", "Username correct format");
                session.setAttribute("toastType", "error");
            }
            else{
                session.setAttribute("toastMessage", "Username incorrect format");
                session.setAttribute("toastType", "error");
            }

        

        Account account = Account.builder()
                .email(email)
                .user_name(user_name)
                .password(password)
                .build();

        Account accountFoundByEmail = accountDAO.findByEmail(account);

        if (accountFoundByEmail != null) {
            if (accountFoundByEmail.getUser_name().equalsIgnoreCase(email)) {
                session.setAttribute("toastMessage", "Username already exists!");
                session.setAttribute("toastType", "error");
            } else {
                session.setAttribute("toastMessage", "Email already exists!");
                session.setAttribute("toastType", "error");
            }
            url = REGISTER_PAGE;
        } else {
            int accountId = accountDAO.insert(account);
            if (accountId > 0) {
                account.setId(accountId);
                session.setAttribute(GlobalConfig.SESSION_ACCOUNT, account);
                session.setAttribute("email", email);
                session.setMaxInactiveInterval(5 * 60);

//                // Gửi OTP
//                String otp = EmailUtils.sendOTPMail(email);
//                session.setAttribute("otp", otp);
//                session.setAttribute("otp_purpose", "activation"); // Thêm mục đích OTP
//
                url = OTP_PAGE;
            } else {
                session.setAttribute("toastMessage", "Failed to create account. Please try again.");
                session.setAttribute("toastType", "error");
                url = REGISTER_PAGE;
            }
        }
        return url;

    }

    private void loginDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        HttpSession session = request.getSession();

        // get về các thong tin người dufg nhập
        String usernameOrEmail = request.getParameter("username");
        String password = request.getParameter("password");
        // kiểm tra thông tin có tồn tại trong DB ko
        Account account = Account.builder()
                .user_name(usernameOrEmail)
                .email(usernameOrEmail)
                .password(password)
                .build();
        Account accFoundByUsernamePass = accountDAO.findByEmailOrUsernameAndPass(account);
        // true => trang home ( set account vao trong session )
        if (accFoundByUsernamePass != null) {
            // Kiểm tra status của account
            if (accFoundByUsernamePass.getStatus().equals("banned")) {
                session.setAttribute("toastMessage", "Your account is banned. Please contact admin to discuss.");
                session.setAttribute("toastType", "error");
                url = LOGIN_PAGE;
            } else {
                session.setAttribute(GlobalConfig.SESSION_ACCOUNT, accFoundByUsernamePass);
                url = HOME_PAGE;
            }

            // Lưu thông tin người dùng vào session
        } else {
            session.setAttribute("toastMessage", "Username or password incorrect!!");
            session.setAttribute("toastType", "error");
            url = LOGIN_PAGE;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }
    private void otpDoPost(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private boolean validEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") && !email.trim().isEmpty() && email != null;

    }

    private boolean validPassword(String password) {
        return password.matches("^[A-Za-z0-9+_.-]{8,32}$") && !password.trim().isEmpty() && password != null;
    }

    private boolean validUsername(String user_name) {
        return user_name.matches("^[a-zA-Z0-9]{3,20}$") && !user_name.trim().isEmpty() && user_name != null;
    }

    

}
