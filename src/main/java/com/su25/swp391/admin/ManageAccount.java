/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.admin;

import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.entity.Account;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            case "edit":
                showEditForm(request, response);
                break;
            case "viewDetail":
                viewDetail(request, response);
                break;

            case "delete":
                deleteAccount(request, response);
                break;
            case "deactive":
                deactivateAccount(request, response);
                break;
            case "activate":
                activateAccount(request, response);
                break;
            case "list":
                listAccount(request, response);
                break;
            case "filter":
                handleFilter(request, response);
                break;
            case "search":
                searchAccount(request,response);
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
            case "edit":
                updateAccount(request, response);
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

    private void deactivateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountIdStr = request.getParameter("id");

        if (accountIdStr != null && !accountIdStr.isEmpty()) {
            int accountId = Integer.parseInt(accountIdStr);
            AccountDAO accountDao = new AccountDAO();
            boolean deactivated = accountDao.deactivateAccount(accountId);
            if (deactivated) {
                setToastMessage(request, "deactivate success", "Success");
            } else {
                setToastMessage(request, "deactivate fail", "Err");
            }
        } else {
            setToastMessage(request, "Invalid account Id", "Err");
        }
        response.sendRedirect(request.getContextPath() + "/manage-account");
        return;
    }

    private void activateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountIdStr = request.getParameter("id");

        if (accountIdStr != null && !accountIdStr.isEmpty()) {
            int accountId = Integer.parseInt(accountIdStr);
            AccountDAO accountDao = new AccountDAO();
            boolean activated = accountDao.activateAccount(accountId);
            if (activated) {
                setToastMessage(request, "Activate success", "Success");
            } else {
                setToastMessage(request, "Activate fail", "Err");
            }
        } else {
            setToastMessage(request, "Invalid account Id", "Err");
        }
        //chuyen huong ve trang list 
        response.sendRedirect(request.getContextPath() + "/manage-account");
        return;
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
                if (currentPage < 1) currentPage = 1;
            }
            if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
                pageSize = Integer.parseInt(pageSizeParam);
                if (pageSize < 5) pageSize = 5;
                if (pageSize > 50) pageSize = 50; // Giới hạn tối đa 50 bản ghi
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
            //lay thong tin tu request
            String full_name = request.getParameter("full_name");
            String user_name = request.getParameter("user_name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String role = request.getParameter("role");
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            //String dateStr = request.getParameter("birth_date");
            //Xu ly thong tin ve nhap date
            String dateStr = request.getParameter("birth_date");
            Date date = null;
            try {
                if (dateStr != null && !dateStr.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = sdf.parse(dateStr);
                    date = new java.sql.Date(utilDate.getTime());
                }
            } catch (ParseException e) {
                request.setAttribute("error", "Định dạng ngày không đúng. Vui lòng dùng yyyy-MM-dd.");
                request.getRequestDispatcher("/view/admin/add_account.jsp").forward(request, response);
                return;
            }
            String mobile = request.getParameter("mobile");
            String gender = request.getParameter("gender");
            //validate input data 
            Map<String, String> errors = validateAccountData(full_name, email, password,mobile, 0);
            if (!errors.isEmpty()) {
                //nếu có lỗi nhập thông tin loi
                request.getSession().setAttribute("errors", errors);
                //laay du lieu trc do nguoi dung nhap 
                request.getSession().setAttribute("formData", request.getParameterMap());
                //chuyen huong ve form them tai khoan
                request.getRequestDispatcher("/view/admin/add_account.jsp").forward(request, response);
                return;
            }
            //Tạo đối tượng account mới
            Account newAccount = Account.builder()
                    // .id(id)
                    .full_name(full_name)
                    .user_name(user_name)
                    .email(email)
                    .password(password)
                    .address(address)
                    .role(role)
                    .status(status)
                    .birth_date(date)
                    .mobile(mobile)
                    .gender(gender)
                    .build();
            AccountDAO accountDao = new AccountDAO();
            boolean isSuccses = accountDao.insert(newAccount) > 0;
            if (isSuccses) {
                request.getSession().setAttribute("totalMess", "Add new Account Success");
                request.getSession().setAttribute("totalType", "Success");
                listAccount(request, response);
//                response.sendRedirect("manage-account-dashbost");
//                return; // THÊM DÒNG NÀY
            } else {
                setToastMessage(request, "totalMess", "Fail to add Account");
                setToastMessage(request, "totalType", "Err");
                request.getRequestDispatcher("/view/admin/add_account.jsp").forward(request, response);
                return;
            }

        } catch (Exception e) {
            request.getSession().setAttribute("totalMess", "Fail to add Account");
            request.getSession().setAttribute("totalType", "Err" + e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("/view/admin/add_account.jsp").forward(request, response);
        }
    }

    private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int accountId = Integer.parseInt(request.getParameter("id"));
            String full_name = request.getParameter("full_name");
            String user_name = request.getParameter("user_name");
            String email = request.getParameter("email");
            String role = request.getParameter("role");
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String mobile = request.getParameter("mobile");
            String gender = request.getParameter("gender");
            //lay account tu database 
            AccountDAO accountDao = new AccountDAO();
            Account account = accountDao.findById(accountId);
            //cap nhap nhung thong tin co the thay doi
            account.setFull_name(full_name);
            account.setEmail(email);
            account.setUser_name(user_name);
            account.setRole(role);
            account.setStatus(status);
            account.setMobile(mobile);
            account.setGender(gender);
            //thuc hien update
            boolean isSuccess = accountDao.update(account);
            //xu ly ket qua 
            if (isSuccess) {
                request.getSession().setAttribute("totalMess", "update new Account Success");
                request.getSession().setAttribute("totalType", "Success");
                response.sendRedirect("manage-account");
                return;
            } else {
                setToastMessage(request, "totalMess", "Fail to update Account");
                setToastMessage(request, "totalType", "Err");
                request.setAttribute("error", "Update failed");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/edit_account.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.getSession().setAttribute("totalMess", "Fail to Update Account");
            request.getSession().setAttribute("totalType", "Err" + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/edit_account.jsp");
            dispatcher.forward(request, response);
            return;

        }

    }

   private Map<String, String> validateAccountData(String full_name, String email, String password,String mobile, int id) {
    Map<String, String> errors = new HashMap<>();
    AccountDAO accountdao = new AccountDAO();

    // Trim inputs
    full_name = full_name != null ? full_name.trim() : null;
    email = email != null ? email.trim() : null;
    mobile = mobile != null ? mobile.trim() : null;
    // Validate full_name
    if (full_name == null || full_name.isEmpty()) {
        errors.put("full_name", "Username is required");
    } else {
        if (full_name.length() < 3 || full_name.length() > 50) {
            errors.put("full_name", "Username must be between 3 and 50 characters");
        } else if (!Pattern.matches("^[a-zA-Z0-9_]+$", full_name)) {
            errors.put("full_name", "Username can only contain letters, numbers, and underscores");
        }
    }

    // Validate email
    if (email == null || email.isEmpty()) {
        errors.put("email", "Email is required");
    } else {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!Pattern.matches(emailRegex, email)) {
            errors.put("email", "Invalid email format");
        } else if (accountdao.isEmailExists(email, id)) {
            errors.put("email", "Email already exists");
        }
    }

    // Validate password (only if provided)
    if ((id == 0 && (password == null || password.isEmpty())) || (password != null && !password.isEmpty())) {
        if (password == null || password.length() < 8) {
            errors.put("password", "Password must be at least 8 characters");
        } else if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", password)) {
            errors.put("password", "Password must contain at least one uppercase letter, one lowercase letter, and one number");
        }
    }
    //validate mobile
      if (mobile == null || mobile.isEmpty()) {
        errors.put("mobile", "Mobile phone number is required");
    } else {
        // Ví dụ: mobile chỉ được gồm 10 đến 11 số, có thể bắt đầu bằng dấu + (cho mã quốc gia)
        String mobileRegex = "^\\+?\\d{10,11}$";
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

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountId = Integer.parseInt(request.getParameter("id"));
        Account account = new AccountDAO().findById(accountId);

        request.setAttribute("account", account);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/edit_account.jsp");
        dispatcher.forward(request, response);
    }

  private void handleFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        String statusParam = request.getParameter("status");
        Boolean status = (statusParam != null && !statusParam.isEmpty()) ? Boolean.parseBoolean(statusParam) : null;

        // Lấy tham số phân trang
        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");
        
        int currentPage = 1;
        int pageSize = 10;
        
        try {
            if (pageParam != null && !pageParam.isEmpty()) {
                currentPage = Integer.parseInt(pageParam);
                if (currentPage < 1) currentPage = 1;
            }
            if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
                pageSize = Integer.parseInt(pageSizeParam);
                if (pageSize < 5) pageSize = 5;
                if (pageSize > 50) pageSize = 50;
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
        request.setAttribute("status", statusParam);
        
        // Tính toán phạm vi hiển thị
        int startRecord = (currentPage - 1) * pageSize + 1;
        int endRecord = Math.min(startRecord + pageSize - 1, totalAccounts);
        request.setAttribute("startRecord", startRecord);
        request.setAttribute("endRecord", endRecord);
        
        request.getRequestDispatcher("/view/admin/list_account.jsp").forward(request, response);
    }
    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idStr = Integer.parseInt(request.getParameter("id"));
        AccountDAO accountdao = new AccountDAO();
        Account accont = accountdao.findById(idStr);
        if (accont != null) {
            Boolean deleteAccount = accountdao.delete(accont);
            if (deleteAccount) {
                setToastMessage(request, "totalMess", "Delete SuccsessFully");

                setToastMessage(request, "typeMess", "SuccsessFully");

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
