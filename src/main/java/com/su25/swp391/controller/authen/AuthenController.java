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
import com.su25.swp391.utils.MD5PasswordEncoderUtils;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author kieud
 */
@WebServlet(name = "AuthenController", urlPatterns = {"/home", "/changepassword",
    "/login", "/register", "/forgetpassword",
    "/otp", "/newpassword", "/logout", "/myaccount"})
public class AuthenController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String LOGIN_PAGE = "view/authen/login.jsp";
    private static final String REGISTER_PAGE = "view/authen/register.jsp";
    private static final String HOME_PAGE = "view/homePage/home.jsp";
    private static final String OTP_PAGE = "view/authen/otp.jsp";
    private static final String FORGETPASSWORD_PAGE = "view/authen/forgetPassword.jsp";
    private static final String NEWPASS_PAGE = "view/authen/newPassword.jsp";
    private static final String CHANGEPASSWORD_PAGE = "view/authen/changePassword.jsp";
    private static final String MYACCOUNT_PAGE = "view/authen/myaccount.jsp";

    AccountDAO accountDAO = new AccountDAO();
    EmailUtils emailotp = new EmailUtils();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/login":
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                break;
            case "/register":
                request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);
                break;
            case "/home":
                request.getRequestDispatcher(HOME_PAGE).forward(request, response);
                break;
            case "/otp":
                request.getRequestDispatcher(OTP_PAGE).forward(request, response);
                break;
            case "/forgetpassword":
                request.getRequestDispatcher(FORGETPASSWORD_PAGE).forward(request, response);
                break;
            case "/newpassword":
                request.getRequestDispatcher(NEWPASS_PAGE).forward(request, response);
                break;
            case "/changepassword":
                request.getRequestDispatcher(CHANGEPASSWORD_PAGE).forward(request, response);
                break;
            case "/myaccount":
                request.getRequestDispatcher(MYACCOUNT_PAGE).forward(request, response);
                break;
            case "/logout":
                logoutDoGet(request, response);
                break;
            default:
                break;
        }

    }

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
            case "/otp":
                otpDoPost(request, response);
                break;
            case "/forgetpassword":
                forgetpasswordDoPost(request, response);
                break;
            case "/newpassword":
                newpasswordDoPost(request, response);
                break;
            case "/changepassword":
                changepasswordDoPost(request, response);
                break;
            case "/myaccount":
                myaccountDoPost(request, response);
                break;
            case "/logout":
                logoutDoGet(request, response);
                break;
            default:
                break;
        }
    }

    private void registerDoPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = null;
        HttpSession session = request.getSession();

        // Lấy thông tin từ request
        String email = request.getParameter("email");
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");
        String cfpassword = request.getParameter("confirmpassword");

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
            session.setAttribute("toastMessage", "Email incorrect format!");
            session.setAttribute("toastType", "error");
            url = REGISTER_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        if (!validPassword(password)) {
            session.setAttribute("toastMessage", "Password incorrect format!");
            session.setAttribute("toastType", "error");
            url = REGISTER_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        if (!validUsername(user_name)) {
            session.setAttribute("toastMessage", "Username incorrect format!");
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
                .role("customer")
                .status("active")
                .build();

        // Kiểm tra xem email đã tồn tại trong database chưa
        Account accountFoundByEmail = accountDAO.findByEmail(account);
        Account accountFoundByUsername = accountDAO.findByUsername(account);

        // Nếu email đã tồn tại
        if (accountFoundByEmail != null || accountFoundByUsername != null) {
            // Nếu username trùng với email
            if (accountFoundByEmail.getUser_name().equalsIgnoreCase(user_name)) {
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
            session.setAttribute("email", email);
            session.setAttribute("user_name", user_name);
            session.setAttribute("password", password);
            session.setAttribute("otpType", "register");
            url = OTP_PAGE;

        }
        request.getRequestDispatcher(url).forward(request, response);

    }

    private void loginDoPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = null;
        HttpSession session = request.getSession();

        // get về các thong tin người dufg nhập
        String usernameOrEmail = request.getParameter("username");
        String password = request.getParameter("password");

        if (usernameOrEmail == null || usernameOrEmail.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            session.setAttribute("toastMessage", "All fields are required");
            session.setAttribute("toastType", "error");
            url = LOGIN_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        // kiểm tra thông tin có tồn tại trong DB ko
        Account account = Account.builder()
                .user_name(usernameOrEmail)
                .email(usernameOrEmail)
                .password(MD5PasswordEncoderUtils.encodeMD5(password))
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
                session.setAttribute("email", usernameOrEmail);
                session.setAttribute("password", password);
                session.setAttribute("user_name", usernameOrEmail);
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

    private void otpDoPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = null;
        //get ve OTP
        HttpSession session = request.getSession();
        String otp = (String) session.getAttribute("otp");
        String otpType = (String) session.getAttribute("otpType");
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
            if ("register".equals(otpType)) {
                accountFoundByEmail.setPassword(MD5PasswordEncoderUtils.encodeMD5(accountFoundByEmail.getPassword()));
                accountDAO.insert(accountFoundByEmail);
                url = HOME_PAGE;
            } else if ("forgot".equals(otpType)) {
                url = NEWPASS_PAGE;
            }
        } else {
            session.setAttribute("toastMessage", "OTP incorrect!");
            session.setAttribute("toastType", "error");
            url = OTP_PAGE;
        }

        request.getRequestDispatcher(url).forward(request, response);

    }

    private void forgetpasswordDoPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = null;
        HttpSession session = request.getSession();

        String email = request.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            session.setAttribute("toastMessage", "All fields are required");
            session.setAttribute("toastType", "error");
            url = FORGETPASSWORD_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        Account account = Account.builder()
                .email(email)
                .build();

        Account accFoundByUsernamePass = accountDAO.findByEmail(account);

        if (accFoundByUsernamePass != null) {
            // Kiểm tra status của account
            if (accFoundByUsernamePass.getStatus().equals("banned")) {
                session.setAttribute("toastMessage", "Your account is banned. Please contact admin to discuss.");
                session.setAttribute("toastType", "error");
                url = FORGETPASSWORD_PAGE;
            } else {
                String otp = EmailUtils.sendOTPMail(email);
                session.setAttribute("email", email);
                session.setAttribute("otp", otp);
                session.setAttribute("account", account);
                session.setAttribute("otpType", "forgot");
                url = OTP_PAGE;
            }

            // Lưu thông tin người dùng vào session
        } else {
            session.setAttribute("toastMessage", "Username or password incorrect!!");
            session.setAttribute("toastType", "error");
            url = FORGETPASSWORD_PAGE;
        }
        request.getRequestDispatcher(url).forward(request, response);

    }

    private void logoutDoGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // lấy session hiện tại, không tạo mới
        String url = null;

        if (session != null) {
            session.removeAttribute("account"); // xoá attribute "account"
            session.invalidate(); // huỷ toàn bộ session nếu cần
        }
        url = HOME_PAGE;
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

    private void newpasswordDoPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = null;
        HttpSession session = request.getSession();

        String new_password = request.getParameter("new_password");
        String confirm_password = request.getParameter("confirm_password");

        if (new_password == null || new_password.trim().isEmpty()
                || confirm_password == null || confirm_password.trim().isEmpty()) {
            session.setAttribute("toastMessage", "All fields are required");
            session.setAttribute("toastType", "error");
            url = NEWPASS_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        if (!validPassword(new_password)) {
            session.setAttribute("toastMessage", "password incorect!");
            session.setAttribute("toastType", "error");
            url = NEWPASS_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        if (!new_password.equals(confirm_password)) {
            session.setAttribute("toastMessage", "Confirm password incorect!");
            session.setAttribute("toastType", "error");
            url = REGISTER_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        Account account = Account.builder()
                .email((String) session.getAttribute("email"))
                .password(MD5PasswordEncoderUtils.encodeMD5(new_password))
                .build();

        Account accFoundByUsernamePass = accountDAO.findByEmail(account);

        if (accFoundByUsernamePass != null) {
            accountDAO.updatePasswordByEmail(account);
            session.setAttribute("password", new_password);
            session.setAttribute(GlobalConfig.SESSION_ACCOUNT, account);
            url = LOGIN_PAGE;
        } else {
            session.setAttribute("toastMessage", "Email incorect!");
            session.setAttribute("toastType", "error");
            response.sendRedirect(HOME_PAGE);
            return;
        }
        request.getRequestDispatcher(url).forward(request, response);

    }

    private void changepasswordDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        HttpSession session = request.getSession();

        String current_password = request.getParameter("current_password");
        String new_password = request.getParameter("new_password");
        String confirm_password = request.getParameter("confirm_password");
        String old_password = (String) session.getAttribute("password");

        if (current_password == null || current_password.trim().isEmpty()
                || new_password == null || new_password.trim().isEmpty()
                || confirm_password == null || confirm_password.trim().isEmpty()) {
            session.setAttribute("toastMessage", "All fields are required");
            session.setAttribute("toastType", "error");
            url = CHANGEPASSWORD_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        if (!current_password.equals(old_password)) {
            session.setAttribute("toastMessage", "Current Password incorect!");
            session.setAttribute("toastType", "error");
            url = CHANGEPASSWORD_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        if (!validPassword(new_password)) {
            session.setAttribute("toastMessage", "New Password incorect!");
            session.setAttribute("toastType", "error");
            url = CHANGEPASSWORD_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        if (!new_password.equals(confirm_password)) {
            session.setAttribute("toastMessage", "Confirm Password incorect!");
            session.setAttribute("toastType", "error");
            url = CHANGEPASSWORD_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        Account account = Account.builder()
                .email((String) session.getAttribute("email"))
                .password(MD5PasswordEncoderUtils.encodeMD5(new_password))
                .build();
        Account accFoundByUsernamePass = accountDAO.findByEmail(account);

        if (accFoundByUsernamePass != null) {
            accountDAO.updatePasswordByEmail(account);
            session.setAttribute(GlobalConfig.SESSION_ACCOUNT, account);
            url = HOME_PAGE;
        } else {
            session.setAttribute("toastMessage", "Email incorect!");
            session.setAttribute("toastType", "error");
            url = CHANGEPASSWORD_PAGE;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void myaccountDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        HttpSession session = request.getSession();

        String full_name = request.getParameter("full_name");
        String birth_date = request.getParameter("birth_date");
        String gender = request.getParameter("gender");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        String email = (String) session.getAttribute("email");

        Date date = null;
        if (birth_date != null && !birth_date.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = sdf.parse(birth_date);
                date = new java.sql.Date(utilDate.getTime());

                //tính tuổi
                LocalDate birtDate = date.toLocalDate();
                LocalDate now = LocalDate.now();
                int age = Period.between(birtDate, now).getYears();
                if (age < 18 || age > 80) {
                    session.setAttribute("toastMessage", "tuổi trong khoảng 18 đến 80 tuổi");
                    session.setAttribute("toastType", "error");
                    return;
                }

            } catch (ParseException e) {
                session.setAttribute("toastMessage", "Định dạng k đúng, vui lòng thử lại");
                session.setAttribute("toastType", "error");
                return;
            }
        }
        if (!isValidPhoneNumber(mobile)) {
            session.setAttribute("toastMessage", "không sử dụng kí tự đặc biệt");
            session.setAttribute("toastType", "error");
            url = MYACCOUNT_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        Account account = Account.builder()
                .email(email)
                .full_name(full_name)
                .gender(gender)
                .birth_date(date)
                .address(address)
                .mobile(mobile)
                .build();

        // Kiểm tra xem email đã tồn tại trong database chưa
        Account accountFoundByEmail = accountDAO.findByEmail(account);

        if (accountFoundByEmail != null) {
            accountDAO.update(account);
            Account newAccount = accountDAO.findByEmail(account);
            session.setAttribute(GlobalConfig.SESSION_ACCOUNT, newAccount);
            url = MYACCOUNT_PAGE;
        } else {
            session.setAttribute("toastMessage", "Email incorect!");
            session.setAttribute("toastType", "error");
            url = MYACCOUNT_PAGE;
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

    public boolean isValidPhoneNumber(String phone) {
        // Loại bỏ khoảng trắng, dấu gạch, ngoặc, v.v. nếu có
        phone = phone.replaceAll("[\\s\\-()]", "");

        // Regex cho số điện thoại Việt Nam (VD: 09xxxxxxxx, +849xxxxxxxx)
        String regex = "^(0[3|5|7|8|9][0-9]{8})|(\\+84[3|5|7|8|9][0-9]{8})$";

        return phone.matches(regex);
    }

}
