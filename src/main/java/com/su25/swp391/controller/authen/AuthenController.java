/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.authen;

import com.oracle.wls.shaded.org.apache.regexp.RE;
import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.utils.EmailUtils;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
    EmailUtils emailotp = new EmailUtils();

        /**
     * Xử lý các yêu cầu GET từ client.
     *
     * @param request  HttpServletRequest chứa thông tin yêu cầu từ client.
     * @param response HttpServletResponse để gửi phản hồi về client.
     * @throws ServletException Nếu có lỗi liên quan đến servlet.
     * @throws IOException      Nếu có lỗi liên quan đến I/O.
     */
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
    /**
     * Xử lý các yêu cầu POST từ client.
     *
     * @param request  HttpServletRequest chứa thông tin yêu cầu từ client.
     * @param response HttpServletResponse để gửi phản hồi về client.
     * @throws ServletException Nếu có lỗi liên quan đến servlet.
     * @throws IOException      Nếu có lỗi liên quan đến I/O.
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

    /**
     * Xử lý yêu cầu đăng ký tài khoản từ client.
     *
     * @param request  HttpServletRequest chứa thông tin yêu cầu từ client.
     * @param response HttpServletResponse để gửi phản hồi về client.
     * @throws ServletException Nếu có lỗi liên quan đến servlet.
     * @throws IOException      Nếu có lỗi liên quan đến I/O.
     */
    private void registerDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        HttpSession session = request.getSession();

        // Lấy thông tin từ request
        String email = request.getParameter("email");
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");
//        String full_name = request.getParameter("full_name");
//        String mobile = request.getParameter("mobile");
        String cfpassword = request.getParameter("confirmpassword");
//        String birth_date = request.getParameter("birth_date");
//        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        

        // Kiểm tra các trường bắt buộc
        if (user_name == null || user_name.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()
                || cfpassword == null || cfpassword.trim().isEmpty()) {
            session.setAttribute("toastMessage", "All fields are required");
            session.setAttribute("toastType", "error");
            url = REGISTER_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        if (!validEmail(email)) {
            session.setAttribute("toastMessage", "Email incorrect format");
            session.setAttribute("toastType", "error");
            url = REGISTER_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        if (!validPassword(password)) {
            session.setAttribute("toastMessage", "Password incorrect format");
            session.setAttribute("toastType", "error");
            url = REGISTER_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        if (!validUsername(user_name)) {
            session.setAttribute("toastMessage", "Username incorrect format");
            session.setAttribute("toastType", "error");
            url = REGISTER_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
      
        
        // Tạo đối tượng Account từ thông tin đã lấy
        Account account = Account.builder()
                .email(email)
                .user_name(user_name)
                .password(password)
                .address(address)
                .build();

        // Kiểm tra xem email đã tồn tại trong database chưa
        Account accountFoundByEmail = accountDAO.findByEmail(account);

        // Nếu email đã tồn tại
        if (accountFoundByEmail != null) {
            // Nếu username trùng với email
            if (accountFoundByEmail.getUser_name().equalsIgnoreCase(email)) {
                session.setAttribute("toastMessage", "Username already exists!");
                session.setAttribute("toastType", "error");
            } else {
                // Nếu email đã tồn tại
                session.setAttribute("toastMessage", "Email already exists!");
                session.setAttribute("toastType", "error");
            }
            url = REGISTER_PAGE;
        } else {
            // Nếu email chưa tồn tại
            String otp = EmailUtils.sendOTPMail(email);
            session.setAttribute("otp", otp);
            session.setAttribute("account", account);
            url = OTP_PAGE;
            
        }
        request.getRequestDispatcher(url).forward(request, response);

    }

    /**
     * Xử lý yêu cầu đăng nhập từ client.
     *
     * @param request  HttpServletRequest chứa thông tin yêu cầu từ client.
     * @param response HttpServletResponse để gửi phản hồi về client.
     * @throws ServletException Nếu có lỗi liên quan đến servlet.
     * @throws IOException      Nếu có lỗi liên quan đến I/O.
     */
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

    /**
     * Xử lý yêu cầu OTP từ client.
     *
     * @param request  HttpServletRequest chứa thông tin yêu cầu từ client.
     * @param response HttpServletResponse để gửi phản hồi về client.
     * @throws IOException 
     * @throws ServletException 
     */
    private void otpDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        //get ve OTP
        HttpSession session = request.getSession();
        String otp = (String) session.getAttribute("otp");
        Account accountFoundByEmail = (Account) session.getAttribute("account");
        
        // get ve OTP tu form
        String otp1 = request.getParameter("otp1");
        String otp2 = request.getParameter("otp2");
        String otp3 = request.getParameter("otp3");
        String otp4 = request.getParameter("otp4");
        String otp5 = request.getParameter("otp5");
        String otp6 = request.getParameter("otp6");
        String otpForm = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;
        // so sanh OTP
        if (otp.equals(otpForm)) {
            accountDAO.insert(accountFoundByEmail);
            session.setAttribute("toastMessage", "Register success!");
            session.setAttribute("toastType", "success");
            url = HOME_PAGE;
        } else {
            session.setAttribute("toastMessage", "OTP incorrect!");
            session.setAttribute("toastType", "error");
            url = OTP_PAGE;
        }
        request.getRequestDispatcher(url).forward(request, response);

    }

    /**
     * Kiểm tra định dạng email.
     *
     * @param email Email cần kiểm tra.
     * @return true nếu email hợp lệ, false nếu không.
     */
    private boolean validEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    /**
     * Kiểm tra định dạng mật khẩu.
     *
     * @param password Mật khẩu cần kiểm tra.
     * @return true nếu mật khẩu hợp lệ, false nếu không.
     */
    private boolean validPassword(String password) {
        if (password.length() >= 8 && password.length() <= 16) {
            return password.matches("^[A-Za-z0-9+_.-]+$");
        }
        return false;
    }

    /**
     * Kiểm tra định dạng tên người dùng.
     *
     * @param user_name Tên người dùng cần kiểm tra.
     * @return true nếu tên người dùng hợp lệ, false nếu không.
     */
    private boolean validUsername(String user_name) {
        if (user_name.length() > 3 && user_name.length() < 32) {
            return user_name.matches("^[A-Za-z0-9+_.-]+$");
        }
        return false;
    }

    /**
     * Kiểm tra định dạng tên đầy đủ.
     *
     * @param full_name Tên đầy đủ cần kiểm tra.
     * @return true nếu tên đầy đủ hợp lệ, false nếu không.
     */
    private boolean validFullname(String full_name) {
        if (full_name.length() > 3 && full_name.length() < 32) {
            return full_name.matches("^[A-Za-z0-9+_.-]+$");
        }
        return false;
    }
    /**
     * Kiểm tra định dạng số điện thoại.
     *
     * @param mobile Số điện thoại cần kiểm tra.
     * @return true nếu số điện thoại hợp lệ, false nếu không.
     */
    private boolean validMobile(String mobile) {
        if (mobile.length() > 9 && mobile.length() <= 11) {
            return mobile.matches("^[A-Za-z0-9+_.-]+$");
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
