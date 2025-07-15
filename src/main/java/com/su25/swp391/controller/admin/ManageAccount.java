/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.admin;

import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.utils.EmailUtils;
import static com.su25.swp391.utils.MD5PasswordEncoderUtils.encodeMD5;
import jakarta.mail.MessagingException;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Hang
 */
@WebServlet(name = "ManageAccount_Controller", urlPatterns = {"/manage-account"})
public class ManageAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";//
        }
        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "viewDetail":
                viewDetail(request, response);
                break;

            case "delete":
                deleteAccount(request, response);
                break;
            case "deactive": {
                try {
                    deactivateAccount(request, response);
                } catch (MessagingException ex) {
                    Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case "activate": {
                try {
                    activateAccount(request, response);
                } catch (MessagingException ex) {
                    Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case "list":
                listAccount(request, response);
                break;
            case "filter":
                handleFilter(request, response);
                break;
            case "search":
                searchAccount(request, response);
                break;
            default:
                listAccount(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; //default action   
        }
        switch (action) {
            case "add":
                addAccount(request, response);
                break;
            default:
                listAccount(request, response);
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void setToastMessage(HttpServletRequest request, String message, String type) {
        request.getSession().setAttribute("toastMessage", message);
        request.getSession().setAttribute("typeToast", type);
    }

    private void searchAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchKeyword = request.getParameter("search");
        // Kiểm tra nếu từ khóa null hoặc chỉ toàn khoảng trắng thì quay lại danh sách
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/manage-account?action=list");
            return;
        }
        searchKeyword = searchKeyword.trim(); // Xóa khoảng trắng 2 đầu
        AccountDAO dao = new AccountDAO();
        List<Account> result = dao.searchAccountsByNameOrEmail(searchKeyword);

        request.setAttribute("listAccount", result);
        request.setAttribute("search", searchKeyword); // Hiển thị lại giá trị đã nhập trong ô input
        request.getRequestDispatcher("/view/admin/list_account.jsp").forward(request, response);
    }

    private void deactivateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException {
        String accountIdStr = request.getParameter("id");

        if (accountIdStr != null && !accountIdStr.isEmpty()) {
            int accountId = Integer.parseInt(accountIdStr);
            AccountDAO accountDao = new AccountDAO();

            // Lấy thông tin tài khoản để gửi email
            Account account = accountDao.findById(accountId); // Bạn cần tạo hàm này nếu chưa có

            boolean deactivated = accountDao.deactivateAccount(accountId);
            if (deactivated) {
                // Gửi email thông báo
                String subject = "Tai khoan da bi vo hieu hoa";
                String content = "<h3>Xin chào " + account.getFull_name() + ",</h3>"
                        + "<p>Tài khoản của bạn trên hệ thống đã bị vô hiệu hóa.</p>"
                        + "<p>Nếu bạn nghĩ đây là nhầm lẫn, vui lòng liên hệ qua hotline.</p>"
                        + "<br><p>Trân trọng!</p>";

                boolean sent = EmailUtils.sendMail(account.getEmail(), subject, content);
//            System.out.println("Email deactivate sent: " + sent);

                request.getSession().setAttribute("toastMessage", "Tài khoản đã bị vô hiệu hóa!"
                        + (sent ? " Email đã được gửi!" : " Gửi email thất bại!"));
                request.getSession().setAttribute("toastType", "error");
            } else {
                request.getSession().setAttribute("toastMessage", "Không thể vô hiệu hóa tài khoản!");
                request.getSession().setAttribute("toastType", "error");
            }
        } else {
            request.getSession().setAttribute("toastMessage", "ID tài khoản không hợp lệ!");
            request.getSession().setAttribute("toastType", "error");
        }

        response.sendRedirect(request.getContextPath() + "/manage-account");
    }

    private void activateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException {
        String accountIdStr = request.getParameter("id");

        if (accountIdStr != null && !accountIdStr.isEmpty()) {
            int accountId = Integer.parseInt(accountIdStr);
            AccountDAO accountDao = new AccountDAO();
            boolean activated = accountDao.activateAccount(accountId);

            if (activated) {
                // Lấy lại tài khoản sau khi kích hoạt
                Account account = accountDao.findById(accountId);
                if (account != null) {
                    // Gửi email thông báo
                    String subject = "tai Khoan cua ban da duoc kich hoat";
                    String content = "<h3>Xin chào " + account.getFull_name() + ",</h3>"
                            + "<p>Tài khoản của bạn đã được <strong>kích hoạt</strong> thành công.</p>"
                            + "<p>Bạn có thể đăng nhập với tên người dùng: <strong>" + account.getUser_name() + "</strong>.</p>"
                            + "<p>Hãy truy cập hệ thống và thay đổi mật khẩu nếu cần.</p>"
                            + "<br><p>Trân trọng,</p><p>Đội ngũ quản trị</p>";

                    boolean emailSent = EmailUtils.sendMail(account.getEmail(), subject, content);
                    String emailStatus = emailSent ? " Email đã được gửi." : " Gửi email thất bại.";

                    request.getSession().setAttribute("toastMessage", "Tài khoản đã được kích hoạt!" + emailStatus);
                } else {
                    request.getSession().setAttribute("toastMessage", "Kích hoạt thành công nhưng không thể lấy thông tin tài khoản để gửi email.");
                }
                request.getSession().setAttribute("toastType", "success");
            } else {
                request.getSession().setAttribute("toastMessage", "Không thể kích hoạt tài khoản!");
                request.getSession().setAttribute("toastType", "error");
            }
        } else {
            request.getSession().setAttribute("toastMessage", "ID tài khoản không hợp lệ!");
            request.getSession().setAttribute("toastType", "error");
        }

        response.sendRedirect(request.getContextPath() + "/manage-account");
    }

    private void listAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy tham số phân trang
        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");

        int currentPage = 1;
        int pageSize = 10; // Mặc định 10 bản ghi mỗi trang

        try {
            if (pageParam != null && !pageParam.isEmpty()) {
                currentPage = Integer.parseInt(pageParam);
                if (currentPage < 1) {
                    currentPage = 1;
                }
            }
            if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
                pageSize = Integer.parseInt(pageSizeParam);
                if (pageSize < 5) {
                    pageSize = 5;
                }
                if (pageSize > 50) {
                    pageSize = 50; // Giới hạn tối đa 50 bản ghi
                }
            }
        } catch (NumberFormatException e) {
            currentPage = 1;
            pageSize = 10;
        }

        AccountDAO accountDAO = new AccountDAO();

        // Lấy danh sách tài khoản với phân trang
        List<Account> listAccount = accountDAO.findAllWithPagination(currentPage, pageSize);

        // Tính toán thông tin phân trang
        int totalAccounts = accountDAO.getTotalAccountCount();
        int totalPages = (int) Math.ceil((double) totalAccounts / pageSize);

        // Thiết lập các thuộc tính cho JSP
        request.setAttribute("listAccount", listAccount);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalAccounts", totalAccounts);

        // Tính toán phạm vi hiển thị
        int startRecord = (currentPage - 1) * pageSize + 1;
        int endRecord = Math.min(startRecord + pageSize - 1, totalAccounts);
        request.setAttribute("startRecord", startRecord);
        request.setAttribute("endRecord", endRecord);

        request.getRequestDispatcher("view/admin/list_account.jsp").forward(request, response);
    }

    private void addAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy thông tin từ request
            String full_name = request.getParameter("full_name");
            String user_name = request.getParameter("user_name");
            String email = request.getParameter("emails");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String role = request.getParameter("role");
            String status = request.getParameter("status");
            String dateStr = request.getParameter("birth_date");
            String mobile = request.getParameter("mobile");
            String gender = request.getParameter("gender");
            //khởi tạo map chung để chứa tất cả các lỗi
            Map<String, String> errors = new HashMap<>();
            errors.putAll(validateAccountData(full_name, email, password, mobile));

            if (user_name != null && email != null && user_name.equalsIgnoreCase(email)) {
                errors.put("user_name", "Tên đăng nhập không được trùng với email");
            }
            //validate address
            if (address == null || address.trim().isEmpty()) {
                errors.put("address", "Address is required");
            } else if (!address.equals(address.trim())) {
                errors.put("address", "Address must not start or end with a space");
            } else if (address.length() < 3 || address.length() > 100) {
                errors.put("address", "Address must be between 3 and 100 characters");
            } else if (!Pattern.matches("^[\\p{L}\\p{N}_ ,.-]+$", address)) {
                errors.put("address", "Address can only contain letters (with accents), numbers, commas, dots, hyphens, and spaces");
            }
            // Xử lý ngày sinh
            Date date = null;
            if (dateStr != null && !dateStr.isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = sdf.parse(dateStr);
                    date = new java.sql.Date(utilDate.getTime());

                    //tính tuổi
                    LocalDate birtDate = date.toLocalDate();
                    LocalDate now = LocalDate.now();
                    int age = Period.between(birtDate, now).getYears();
                    if (age < 18 || age > 80) {
                        errors.put("birth_date", "Tuổi phải nằm trong khoảng từ 18 đến 80");
                    }

                } catch (ParseException e) {
                    errors.put("birth_date", "Định dạng này không đúng .Vui lòng dùng đúng định dạng ");
                }
            }

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("hasValidateErr", true);
                // Gán lại các giá trị đã nhập vào request để hiện lại trên form
                request.setAttribute("full_name", full_name);
                request.setAttribute("emails", email);
                request.setAttribute("address", address);
                request.setAttribute("role", role);
                request.setAttribute("status", status);
                request.setAttribute("birth_date", dateStr);
                request.setAttribute("mobile", mobile);
                request.setAttribute("gender", gender);
                request.getRequestDispatcher("/view/admin/add_account.jsp").forward(request, response);
                return;
            }

            // Tạo đối tượng Account mới
            Account newAccount = Account.builder()
                    .full_name(full_name)
                    .user_name(user_name)
                    .email(email)
                    .password(encodeMD5(password))
                    .address(address)
                    .role(role)
                    .status(status)
                    .birth_date(date)
                    .mobile(mobile)
                    .gender(gender)
                    .build();

            AccountDAO accountDao = new AccountDAO();
            boolean isSuccess = accountDao.insert(newAccount) > 0;

            if (isSuccess) {
                // Gửi mail chứa thông tin tài khoản
                String emailResult = EmailUtils.sendAccountMail(email, user_name, password);
                request.getSession().setAttribute("toastMessage", "Thêm tài khoản thành công! " + emailResult);
                request.getSession().setAttribute("toastType", "success");
                // Redirect về trang quản lý tài khoản
                response.sendRedirect(request.getContextPath() + "/manage-account");
                return;
            } else {
                request.getSession().setAttribute("toastMessage", "Thêm tài khoản thất bại!");
                request.getSession().setAttribute("toastType", "error");
                request.getRequestDispatcher("/view/admin/add_account.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.getSession().setAttribute("totalMessage", "Fail to add Account");
            request.getSession().setAttribute("toastType ", "error" + e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("/view/admin/add_account.jsp").forward(request, response);
        }
    }

    private Map<String, String> validateAccountData(String full_name, String email, String password, String mobile) {
        Map<String, String> errors = new HashMap<>();
        AccountDAO accountdao = new AccountDAO();

        // Trim inputs
        full_name = full_name != null ? full_name.trim() : null;
        email = email != null ? email.trim() : null;
        mobile = mobile != null ? mobile.trim() : null;
        // Validate full_name
        if (full_name == null || full_name.trim().isEmpty()) {
            errors.put("full_name", "Full_name is required");
        } else if (!full_name.equals(full_name.trim())) {
            errors.put("full_name", "Full_name must not start or end with a space");
        } else if (full_name.length() < 3 || full_name.length() > 30) {
            errors.put("full_name", "Full_name must be between 3 and 50 characters");
        } else if (!Pattern.matches("^[\\p{L}\\s]+$", full_name)) {
            errors.put("full_name", "Full_name can only contain letters, and spaces");
        }

        // Validate email
        if (email == null || email.isEmpty()) {
            errors.put("email", "Email is required");
        } else {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            if (!Pattern.matches(emailRegex, email)) {
                errors.put("email", "Invalid email format");
            }
            if (accountdao.isEmailExists(email)) {
                System.out.println("Lỗi email");
                errors.put("email", "Email already exists");
            }
        }

        // Validate password
        if (password == null || password.length() < 8) {
            errors.put("password", "Password must be at least 8 characters");
        } else if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", password)) {
            errors.put("password", "Password must contain at least one uppercase, one lowercase, and one number");
        }
        //validate mobile
        if (mobile == null || mobile.isEmpty()) {
            errors.put("mobile", "Mobile phone number is required");
        } else {
            // Ví dụ: mobile chỉ được gồm 10 đến 11 số, có thể bắt đầu bằng dấu + (cho mã quốc gia)
            String mobileRegex = "^(0|\\+84)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-9]|9[0-9])\\d{7}$";
            if (!Pattern.matches(mobileRegex, mobile)) {
                errors.put("mobile", "Mobile phone number must be 10-11 digits and can start with +");
            }
        }
        return errors;
    }

    private String getParameter(HttpServletRequest request, String fullname) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/add_account.jsp");
        dispatcher.forward(request, response);
    }

    private void handleFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        if (status != null && status.isEmpty()) {
            status = null; // Gán null nếu là chuỗi rỗng
        }
        // Lấy tham số phân trang
        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");

        int currentPage = 1;
        int pageSize = 10;

        try {
            if (pageParam != null && !pageParam.isEmpty()) {
                currentPage = Integer.parseInt(pageParam);
                if (currentPage < 1) {
                    currentPage = 1;
                }
            }
            if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
                pageSize = Integer.parseInt(pageSizeParam);
                if (pageSize < 5) {
                    pageSize = 5;
                }
                if (pageSize > 50) {
                    pageSize = 50;
                }
            }
        } catch (NumberFormatException e) {
            currentPage = 1;
            pageSize = 10;
        }

        AccountDAO accountDAO = new AccountDAO();

        // Lấy danh sách đã lọc với phân trang
        List<Account> filteredAccounts = accountDAO.filterAccountsWithPagination(role, status, currentPage, pageSize);

        // Tính toán thông tin phân trang cho bộ lọc
        int totalAccounts = accountDAO.getTotalAccountCountWithFilter(role, status);
        int totalPages = (int) Math.ceil((double) totalAccounts / pageSize);

        // Thiết lập các thuộc tính cho JSP
        request.setAttribute("listAccount", filteredAccounts);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalAccounts", totalAccounts);
        request.setAttribute("role", role);
        request.setAttribute("status", status);

        // Tính toán phạm vi hiển thị
        int startRecord = (currentPage - 1) * pageSize + 1;
        int endRecord = Math.min(startRecord + pageSize - 1, totalAccounts);
        request.setAttribute("startRecord", startRecord);
        request.setAttribute("endRecord", endRecord);

        request.getRequestDispatcher("/view/admin/list_account.jsp").forward(request, response);
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idStr = Integer.parseInt(request.getParameter("id"));
        AccountDAO accountdao = new AccountDAO();
        Account accont = accountdao.findById(idStr);
        if (accont != null) {
            Boolean deleteAccount = accountdao.delete(accont);
            if (deleteAccount) {
                request.getSession().setAttribute("isDelete", true);
                response.sendRedirect(request.getContextPath() + "/manage-account");
                return;

            } else {
                setToastMessage(request, "totalMess", "Fail to add Account");
                setToastMessage(request, "totalType", "Err");
            }
        } else {
            setToastMessage(request, "totalMess", "Fail to add Account");
            setToastMessage(request, "totalType", "Err");
        }
        response.sendRedirect(request.getContextPath() + "/manage-account");
    }

    private void viewDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int accountId = Integer.parseInt(request.getParameter("id"));

            AccountDAO accountDao = new AccountDAO();
            Account account = accountDao.findById(accountId);

            if (account != null) {
                request.setAttribute("account", account);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/viewDetail.jsp");
                dispatcher.forward(request, response);
            } else {
                request.getSession().setAttribute("totalMess", "Không tìm thấy tài khoản.");
                request.getSession().setAttribute("totalType", "Err");
                response.sendRedirect(request.getContextPath() + "/manage-account");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("totalMess", "Có lỗi xảy ra khi xem chi tiết.");
            request.getSession().setAttribute("totalType", "Err");
            response.sendRedirect(request.getContextPath() + "/manage-account");
        }
    }

}
