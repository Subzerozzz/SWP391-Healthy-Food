/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.authen;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.utils.EmailUtils;
import com.su25.swp391.utils.GlobalUtils;
import com.su25.swp391.utils.MD5PasswordEncoderUtils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.Map;

/**
 *
 * @author kieud
 */
@WebServlet(name = "AuthenController", urlPatterns = {"/login", "/register", "/forgetpassword", "/home", "/OTP"})
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
    private static final String FORGETPASSWORD_PAGE = "view/Authen/ForgetPassword.jsp";

    AccountDAO accountDAO = new AccountDAO();
    EmailUtils emailotp = new EmailUtils();

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
            case "/forgetpassword":
                request.getRequestDispatcher("view/Authen/ForgetPassword.jsp").forward(request, response);
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
            case "/forgetpassword":
                forgetpasswordDoPost(request, response);
                break;
            default:
                break;
        }
    }

    private void registerDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        HttpSession session = request.getSession();

        // Lấy thông tin người dùng nhập
        String fullname = request.getParameter("full_name");
        String username = request.getParameter("user_name");
        String address = request.getParameter("address");
        //String birth_date = request.getParameter("birth_date");
        String genderParam = request.getParameter("gender");
        String email = request.getParameter("email");
        String mobie = request.getParameter("mobie");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Store form data in request to preserve it in case of validation failure
        request.setAttribute("formData", Map.of(
                "fullname", fullname != null ? fullname : "",
                "username", username != null ? username : "",
                "address", address != null ? address : "",
                // "birth_date", birth_date != null ? birth_date : "",
                "gender", genderParam != null ? genderParam : "",
                "email", email != null ? email : "",
                "mobie", mobie != null ? mobie : ""
        ));

        // Server-side validation
        if (fullname == null || fullname.trim().isEmpty()
                || username == null || username.trim().isEmpty()
                || address == null || address.trim().isEmpty()
                || //  birth_date == null || birth_date.trim().isEmpty() ||
                genderParam == null || genderParam.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || mobie == null || mobie.trim().isEmpty()
                || password == null || password.trim().isEmpty()
                || confirmPassword == null || confirmPassword.trim().isEmpty()) {

            session.setAttribute("toastMessage", "All fields are required");
            session.setAttribute("toastType", "error");
            url = REGISTER_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

       // boolean gender = Boolean.parseBoolean(genderParam);

        if (!validEmail(email)) {
            session.setAttribute("toastMessage", "Email incorect format");
            session.setAttribute("toastType", "error");
            url = REGISTER_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        if (!validFullname(fullname)) {
            session.setAttribute("toastMessage", "Your name incorect format");
            session.setAttribute("toastType", "error");
            url = REGISTER_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        if (!validMobile(mobie)) {
            session.setAttribute("toastMessage", "mobie incorect format");
            session.setAttribute("toastType", "error");
            url = REGISTER_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        if (!validPassword(password)) {
            session.setAttribute("toastMessage", "Password incorect format");
            session.setAttribute("toastType", "error");
            url = REGISTER_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        if (!validUsername(username)) {
            session.setAttribute("toastMessage", "Username incorect format");
            session.setAttribute("toastType", "error");
            url = REGISTER_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu và xác nhận mật khẩu có khớp không
        if (!password.equals(confirmPassword)) {
            session.setAttribute("toastMessage", "Password and confirm password do not match");
            session.setAttribute("toastType", "error");
            url = REGISTER_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        // Kiểm tra xem email đã tồn tại trong db chưa
        Account account = Account.builder()
                .full_name(fullname)
                .user_name(username)
                .address(address)
                //               .birth_date(birth_date)
                .mobie(mobie)
                .email(email)
                .password(MD5PasswordEncoderUtils.encodeMD5(password))
                .role(GlobalConfig.ROLE_USER)
                // .stat(false)
                .status("active")
                .build();
        Account accountFoundByEmail = accountDAO.findByEmail(account);

        if (accountFoundByEmail != null) {
            if (accountFoundByEmail.getUser_name().equalsIgnoreCase(username)) {
                session.setAttribute("toastMessage", "Username already exists!");
                session.setAttribute("toastType", "error");
            } else {
                session.setAttribute("toastMessage", "Email already exists!");
                session.setAttribute("toastType", "error");
            }
            url = REGISTER_PAGE;
        } else {
            // Lưu tài khoản vào database
            int accountId = accountDAO.insert(account);
            if (accountId > 0) {
                // Tạo session cho việc kích hoạt tài khoản sau này
                account.setId(accountId);
                session.setAttribute(GlobalConfig.SESSION_ACCOUNT, account);
                session.setAttribute("email", email);

                // Đặt thời gian hết hạn cho session (5 phút)
                session.setMaxInactiveInterval(5 * 60);

                // Gửi OTP
                String otp = EmailUtils.sendOTPMail(email);
                session.setAttribute("otp", otp);
                session.setAttribute("otp_purpose", "activation"); // Thêm mục đích OTP

                url = OTP_PAGE;

            } else {
                session.setAttribute("toastMessage", "Failed to create account. Please try again.");
                session.setAttribute("toastType", "error");
                url = REGISTER_PAGE;
            }
        }
        request.getRequestDispatcher(url).forward(request, response);

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

    private void forgetpasswordDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        HttpSession session = request.getSession();

        String usernameOrEmail = request.getParameter("username");
        Account account = Account.builder()
                .user_name(usernameOrEmail)
                .email(usernameOrEmail)
                .build();
        Account accFoundByUsernamePass = accountDAO.findByEmailOrUsernameAndPass(account);
        if (accFoundByUsernamePass != null) {
            if (accFoundByUsernamePass.getStatus().equals("banned")) {
                session.setAttribute("toastMessage", "Your account is banned. Please contact admin to discuss.");
                session.setAttribute("toastType", "error");
                url = FORGETPASSWORD_PAGE;
            } else {
                //otp
            }
        } else {
            session.setAttribute("toastMessage", "Your account is banned. Please contact admin to discuss.");
            session.setAttribute("toastType", "error");
            url = FORGETPASSWORD_PAGE;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }
    private void otpDoPost(HttpServletRequest request, HttpServletResponse response) {

    }

    private boolean validEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    private boolean validPassword(String password) {
        if (password.length() >= 8 && password.length() < 16) {
            return password.matches("^[A-Za-z0-9+_.-]+$");
        }
        return false;
    }

    private boolean validUsername(String user_name) {
        if (user_name.length() > 3 && user_name.length() < 32) {
            return user_name.matches("^[A-Za-z0-9+_.-]+$");
        }
        return false;
    }

    private boolean validFullname(String full_name) {
        if (full_name.length() > 3 && full_name.length() < 32) {
            return full_name.matches("^[\\p{L} .'-]+$");
        }
        return false;
    }

    private boolean validMobile(String mobie) {
        if (mobie.length() > 9 && mobie.length() <= 11) {
            return mobie.matches("^[A-Za-z0-9+_.-]+$");
        }
        return false;
    }
//    private boolean validFullname(String full_name) {
//        if (full_name.length() > 3 && full_name.length() < 32) {
//            return full_name.matches("^[A-Za-z0-9+_.-]+$");
//        }
//        return false;
//    } 
  

}
