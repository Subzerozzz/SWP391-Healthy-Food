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
                viewDetail(request,response);
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
        response.sendRedirect("manage-account-dashbost");
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
        response.sendRedirect("manage-account-dashbost");
        return;
    }

    private void listAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AccountDAO account = new AccountDAO();
        List<Account> listAccount = account.findAll();

        request.setAttribute("listAccount", listAccount);

        
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
            String dateStr = request.getParameter("birth_date");
            //Xu ly thong tin ve nhap date
            Date date = null;
            try {
                if (dateStr != null && dateStr.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = sdf.parse(dateStr);
                    date = new java.sql.Date(utilDate.getTime()); // ép sang java.sql.Date
                }
            } catch (ParseException e) {
                request.setAttribute("error", "Đinh dang sai form ");
                request.getRequestDispatcher("/view/admin/add_account.jsp").forward(request, response);
                return;
            }
            String mobile = request.getParameter("mobile");
            String gender = request.getParameter("gender");
            //validate input data 
            Map<String, String> errors = validateAccountData(full_name, email, password, 0);
            if (!errors.isEmpty()) {
                //nếu có lỗi nhập thông tin loi
                request.getSession().setAttribute("Err", errors);
                //laay du lieu trc do nguoi dung nhap 
                request.getSession().setAttribute("formData", request.getParameterMap());
                //chuyen huong ve form them tai khoan
                request.getRequestDispatcher("/view/admin/add_account.jsp").forward(request, response);

            }
            //Tạo đối tượng account mới
            Account newAccount = Account.builder()
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

    private Map<String, String> validateAccountData(String full_name, String email, String password, int id) {
        Map<String, String> errors = new HashMap<>();
        AccountDAO accountdao = new AccountDAO();

        // Validate full_name
        if (full_name != null && !full_name.isEmpty()) {
            if (full_name.length() < 3 || full_name.length() > 50) {
                errors.put("full_name", "Username must be between 3 and 50 characters");
            } else if (!Pattern.matches("^[a-zA-Z0-9_]+$", full_name)) {
                errors.put("full_name", "Username can only contain letters, numbers, and underscores");
            }
        }

        // Validate email
        if (email != null && !email.isEmpty()) {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            if (!Pattern.matches(emailRegex, email)) {
                errors.put("email", "Invalid email format");
            } else if (accountdao.isEmailExists(email, id)) {
                errors.put("email", "Email already exists");
            }
        }

        // Validate password
        if (password != null && !password.isEmpty()) {
            if (password.length() < 8) {
                errors.put("password", "Password must be at least 8 characters");
            } else if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", password)) {
                errors.put("password", "Password must contain at least one uppercase letter, one lowercase letter, and one number");
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

        AccountDAO accountDAO = new AccountDAO();
        List<Account> filteredAccounts = accountDAO.filterAccounts(role, status);

        request.setAttribute("listAccount", filteredAccounts);
        request.setAttribute("role", role);
        request.setAttribute("status", statusParam);
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
        response.sendRedirect(request.getContextPath()+"/manage-account");
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
