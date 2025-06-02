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
            case "deactive":
                deactivateAccount(request, response);
                break;
            case "activate":
                activateAccount(request, response);
                break;
            case "list":
                listAccount(request, response);
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
        response.sendRedirect("/manage-account-dashbost");
        return;
    }

    private void listAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("manage-account-dashbost").forward(request, response);
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
            String dateStr = request.getParameter("birth_data");
            //Xu ly thong tin ve nhap date
            Date date = null;
            try {
                if (dateStr != null && dateStr.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    date = (Date) sdf.parse(dateStr);
                }
            } catch (ParseException e) {
                request.setAttribute("error", "Đinh dang sai form ");
                request.getRequestDispatcher("/view/admin/add_account.jsp").forward(request, response);
                return;
            }
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
                    .use_name(user_name)
                    .email(email)
                    .password(password)
                    .address(address)
                    .role(role)
                    .status(status)
                    .birth_data(date)
                    .build();
            AccountDAO accountDao = new AccountDAO();
            boolean isSuccses = accountDao.insert(newAccount) > 0;
            if (isSuccses) {
                request.getSession().setAttribute("totalMess", "Add new Account Success");
                request.getSession().setAttribute("totalType", "Success");
                listAccount(request, response);
                return; // THÊM DÒNG NÀY
            } else {
                setToastMessage(request, "totalMess", "Fail to add Account");
                setToastMessage(request, "totalType", "Err");
                request.getRequestDispatcher("/view/admin/add_account.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.getSession().setAttribute("totalMess", "Fail to add Account");
            request.getSession().setAttribute("totalType", "Err" + e.getMessage());
            request.getRequestDispatcher("/view/admin/add_account.jsp").forward(request, response);
        }
//        response.sendRedirect("manage-account-dashbost");

    }

    private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int accountId = Integer.parseInt(request.getParameter("id"));
            String full_name = request.getParameter("full_name");
            String email = request.getParameter("email");
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            //lay account tu database 
            AccountDAO accountDao = new AccountDAO();
            Account account = accountDao.findById(accountId);
            //cap nhap nhung thong tin co the thay doi
            account.setFull_name(full_name);
            account.setEmail(email);
            account.setStatus(status);
            //thuc hien update
            boolean isSuccess = accountDao.update(account);
            //xu ly ket qua 
            if (isSuccess) {
                request.getSession().setAttribute("totalMess", "update new Account Success");
                request.getSession().setAttribute("totalType", "Success");
                response.sendRedirect("manage-account");
                return; // THÊM DÒNG NÀY
            } else {
                 setToastMessage(request, "totalMess", "Fail to update Account");
                setToastMessage(request, "totalType", "Err");
                request.setAttribute("error", "Update failed");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/edit_account.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            request.getSession().setAttribute("totalMess", "Fail to Update Account");
            request.getSession().setAttribute("totalType", "Err" + e.getMessage());
        }

        response.sendRedirect("manage-account-dashbost");
        return;
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/edit_account.jsp");
        dispatcher.forward(request, response);
    }

}
