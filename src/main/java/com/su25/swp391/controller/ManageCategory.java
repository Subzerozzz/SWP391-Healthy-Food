
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller;

import com.su25.swp391.dal.implement.CategoryDAO;
import com.su25.swp391.entity.Category;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author Hang
 */
@WebServlet(name = "ManageCategory", urlPatterns = {"/manageCategory"})
public class ManageCategory extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listCate";
        }
        switch (action) {
            case "addCate":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForn(request, response);
                break;
            case "delete":
                deleteCategory(request, response);
                break;
            case "listCate":
                listCategory(request, response);
                break;
            case "viewDetail":
                viewDetail(request, response);
                break;
            case "search":
                searchCate(request, response);
                break;
            case "filter":
                filter(request, response);
                break;
            default:
                listCategory(request, response);
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
        String action = request.getParameter("action");
        if (action == null) {
            action = "listCate";
        }
        switch (action) {
            case "add":
                addCategory(request, response);
                break;
            case "edit":
                updateCategory(request, response);
                break;
            default:
                listCategory(request, response);
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

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/common/categoryPage/category_add.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCate = Integer.parseInt(request.getParameter("idcategory"));
        CategoryDAO cateDao = new CategoryDAO();
        Category category = cateDao.findById(idCate);

        request.setAttribute("cate", category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/common/categoryPage/category_edit.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idStr = Integer.parseInt(request.getParameter("idcategory"));
        CategoryDAO categoryDao = new CategoryDAO();
        Category cate = categoryDao.findById(idStr);
        if (cate != null) {
            Boolean deletCate = categoryDao.delete(cate);
            if (deletCate) {
                request.getSession().setAttribute("isDelete", true);
                response.sendRedirect(request.getContextPath() + "/manageCategory");
                return;
            } else {
                request.getSession().setAttribute("ToastMess", "Not Delete");
                request.getSession().setAttribute("toastType", "Err");
                return;
            }

        } else {
            request.getSession().setAttribute("ToastMess", "Not Delete");
            request.getSession().setAttribute("toastType", "Err");
        }
        response.sendRedirect(request.getContextPath() + "/manageCategory");
    }

    private void listCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //lấy tham số phan trang
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
        CategoryDAO cateDao = new CategoryDAO();

        //lay ra danh sach cate
        List<Category> listcategory = cateDao.findAllWithPagination(currentPage, pageSize);
        //tinh toan phan trang 
        int totalCategory = cateDao.getTotalCategoryCount();
        int totalPages = (int) Math.ceil((double) totalCategory / pageSize);
        //thiet lap cac thuoc tinh cho jsp
        request.setAttribute("listcategory", listcategory);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalCategory", totalCategory);

        //tinh toan pham vi the hien
        int startRecord = (currentPage - 1) * pageSize + 1;
        int endRecord = Math.min(startRecord + pageSize - 1, totalCategory);
        request.setAttribute("startRecord", startRecord);
        request.setAttribute("endRecord", endRecord);
        request.getRequestDispatcher("/view/common/categoryPage/category_list.jsp").forward(request, response);

    }

    private void searchCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchKey = request.getParameter("search");
        //kiem tra null tra ve danh sach list
        if (searchKey == null || searchKey.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/manageCategory");
            return;
        }
        searchKey = searchKey.trim();//xoa khoang trang 2 dau
        CategoryDAO cateDao = new CategoryDAO();
        List<Category> listCateSearch = cateDao.searchCategorybyName(searchKey);
        request.setAttribute("listcategory", listCateSearch);
        request.setAttribute("search", searchKey);
        request.getRequestDispatcher("/view/common/categoryPage/category_list.jsp").forward(request, response);
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name_category = request.getParameter("name_category");
            String description = request.getParameter("description");
            String minBMI_raw = request.getParameter("minBMI");
            String maxBMI_raw = request.getParameter("maxBMI");
            Map<String, String> errors = new HashMap();
            Double minBMI = null, maxBMI = null;

            try {
                if (minBMI_raw != null && !minBMI_raw.trim().isEmpty()) {
                    minBMI = Double.parseDouble(minBMI_raw.trim());
                    if (minBMI < 10 || minBMI > 50) {
                        errors.put("minBMI", "Min BMI phải nằm trong khoảng từ 10 đến 50");
                    }
                } else {
                    errors.put("minBMI", "Min BMI không được để trống");
                }

                if (maxBMI_raw != null && !maxBMI_raw.trim().isEmpty()) {
                    maxBMI = Double.parseDouble(maxBMI_raw.trim());
                    if (maxBMI < 10 || maxBMI > 50) {
                        errors.put("maxBMI", "Max BMI phải nằm trong khoảng từ 10 đến 50");
                    }
                } else {
                    errors.put("maxBMI", "Max BMI không được để trống");
                }

                // So sánh thứ tự chỉ khi không có lỗi định dạng
                if (minBMI != null && maxBMI != null && minBMI >= maxBMI) {
                    errors.put("BMIOrder", "Min BMI phải nhỏ hơn Max BMI");
                }

            } catch (NumberFormatException e) {
                errors.put("BMIFormat", "Chỉ số BMI phải là số hợp lệ");
            }
            if (name_category == null || name_category.trim().isEmpty()) {
                errors.put("name_category", "name_category is required");
            } else if (!name_category.equals(name_category.trim())) {
                errors.put("name_category", "name_category must not start or end with a space");
            } else if (name_category.length() < 3 || name_category.length() > 100) {
                errors.put("name_category", "name_category must be between 3 and 100 characters");
            } else if (!Pattern.matches("^[\\p{L}\\p{N}_ ,.-]+$", name_category)) {
                errors.put("name_category", "Name can only contain letters (with accents), numbers, commas, dots, hyphens, and spaces");
            }

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("hasValidateErr", true);
                //gan lai cac gia tri da nhap vao request de hien lai trong form
                request.setAttribute("name_category", name_category);
                request.setAttribute("minBMI", minBMI);
                request.setAttribute("maxBMI", maxBMI);
                request.setAttribute("description", description);

                request.getRequestDispatcher("/view/common/categoryPage/category_add.jsp").forward(request, response);
                return;
            }
            // tao doi tuong category moi
            Category category = Category.builder()
                    .name_category(name_category)
                    .minBMI(minBMI)
                    .maxBMI(maxBMI)
                    .description(description)
                    .build();
            CategoryDAO cateDao = new CategoryDAO();
            boolean isSuccess = cateDao.insert(category) > 0;
            if (isSuccess) {
                // Lưu message thành công vào session để hiển thị 1 lần
                request.getSession().setAttribute("toastMessage", "Thêm tài khoản thành công!");
                request.getSession().setAttribute("toastType", "success");
                // Redirect về trang quản lý tài khoản
                response.sendRedirect(request.getContextPath() + "/manageCategory");
                return;
            } else {
                request.getSession().setAttribute("toastMessage", "Thêm tài khoản thất bại!");
                request.getSession().setAttribute("toastType", "Fail");
                request.getRequestDispatcher("/view/common/categoryPage/category_add.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.getSession().setAttribute("totalMess", "Fail to add Account");
            request.getSession().setAttribute("totalType", "Err" + e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("/view/common/categoryPage/category_add.jsp").forward(request, response);
        }
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("idcategory"));
            String name_category = request.getParameter("name_category");
            String description = request.getParameter("description");
            String maxBMIStr = request.getParameter("maxBMI");
            String minBMIStr = request.getParameter("minBMI");
            CategoryDAO cateDao = new CategoryDAO();
            Category cate = cateDao.findById(id);
            //validate
            Map<String, String> errors = new HashMap<>();
            Double minBMI = null;
            Double maxBMI = null;
            //validate name
            if (name_category == null || name_category.trim().isEmpty()) {
                errors.put("name_category", "Name category is required");
            } else if (!name_category.equals(name_category.trim())) {
                errors.put("name_category", "Name category must not start or end with a space");
            } else if (name_category.length() < 3 || name_category.length() > 50) {
                errors.put("name_category", "Name must between 3 and 50 characters");
            } else if (!Pattern.matches("^[a-zA-Z0-9_ ]+$", name_category)) {
                errors.put("name_category", "Name can only contain letter,numbers,underscores,and space");
            }
            //validate description
            if (description != null && description.length() > 500) {
                errors.put("description", "Description must not exceed 500 characters.");
            }
            //validate max and min BMI
            if (minBMIStr == null || minBMIStr.trim().isEmpty()) {
                errors.put("minBMI", "minBMI is required.");
            } else {
                try {
                    minBMI = Double.parseDouble(minBMIStr.trim());
                    if (minBMI < 10 || minBMI > 50) {
                        errors.put("minBMI", "minBMI must be between 10 and 50.");
                    }
                } catch (NumberFormatException e) {
                    errors.put("minBMI", "minBMI must be a valid number.");
                }
            }

            // Validate maxBMI
            if (maxBMIStr == null || maxBMIStr.trim().isEmpty()) {
                errors.put("maxBMI", "maxBMI is required.");
            } else {
                try {
                    maxBMI = Double.parseDouble(maxBMIStr.trim());
                    if (maxBMI < 10 || maxBMI > 50) {
                        errors.put("maxBMI", "maxBMI must be between 10 and 50.");
                    }
                } catch (NumberFormatException e) {
                    errors.put("maxBMI", "maxBMI must be a valid number.");
                }
            }

            // Validate mối quan hệ giữa min và max
            if (minBMI != null && maxBMI != null && minBMI > maxBMI) {
                errors.put("BMI", "minBMI must be less than or equal to maxBMI.");
            }
            if (!errors.isEmpty()) {
                Map<String, String> formData = new HashMap<>();
                formData.put("name_category", name_category);
                formData.put("description", description);
                formData.put("minBMI", minBMIStr);
                formData.put("maxBMI", maxBMIStr);
                request.setAttribute("errors", errors);
                request.setAttribute("formData", formData);
                request.setAttribute("cate", cate);
                request.getRequestDispatcher("/view/common/categoryPage/category_edit.jsp").forward(request, response);
                return;
            }

            //cap nhap nhung truong có the thay doi
            cate.setDescription(description);
            cate.setName_category(name_category);
            cate.setMaxBMI(maxBMI);
            cate.setMinBMI(minBMI);

            //thu hien update
            Boolean isSuccess = cateDao.update(cate);
            if (isSuccess) {
                request.getSession().setAttribute("isUpdate", true);
                response.sendRedirect(request.getContextPath() + "/manageCategory?action=list");
                return;
            } else {
                request.getSession().setAttribute("toastMessage", "Edit tài khoản thất bại!");
                request.getSession().setAttribute("toastType", "Fail");
                request.setAttribute("error", "Update Fail");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/common/categoryPage/category_edit.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.getSession().setAttribute("toastMessage", "Edit tài khoản thất bại!");
            request.getSession().setAttribute("toastType", "Fail");
            request.setAttribute("error", "Update Fail");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/common/categoryPage/category_edit.jsp");
            dispatcher.forward(request, response);
            return;
        }
    }

    private void viewDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int idCategory = Integer.parseInt(request.getParameter("idcategory"));
            CategoryDAO cateDao = new CategoryDAO();
            Category cate = cateDao.findById(idCategory);
            if (cate != null) {
                request.setAttribute("category", cate);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/common/categoryPage/viewDetail.jsp");
                dispatcher.forward(request, response);
            } else {
                request.getSession().setAttribute("totalMess", "Không tìm thấy tài khoản.");
                request.getSession().setAttribute("totalType", "Err");
                response.sendRedirect(request.getContextPath() + "/manageCategory");

            }
        } catch (Exception e) {
            request.getSession().setAttribute("totalMess", "Không tìm thấy tài khoản.");
            request.getSession().setAttribute("totalType", "Err");
            response.sendRedirect(request.getContextPath() + "/manageCategory");

        }
    }

    private void filter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String range = request.getParameter("range");
        double min = 0, max = 60;
        switch (range) {
            case "1": //gầy
                min = 0;
                max = 18.4;
                break;
            case "2": // Bình thường
                min = 18.5;
                max = 24.9;
                break;
            case "3": // Thừa cân
                min = 25;
                max = 29.9;
                break;
            case "4": // Béo phì
                min = 30;
                max = 49.9;
                break;
            default:
                request.setAttribute("errors", List.of("Khoảng BMI không hợp lệ"));
                request.getRequestDispatcher("/view/common/categoryPage/category_list.jsp").forward(request, response);
                return;
        }
        CategoryDAO dao = new CategoryDAO();
        List<Category> filtered = dao.filterCategoryByBMI(min, max);
        request.setAttribute("listcategory", filtered);
        request.getRequestDispatcher("/view/common/categoryPage/category_list.jsp").forward(request, response);
    }
}


