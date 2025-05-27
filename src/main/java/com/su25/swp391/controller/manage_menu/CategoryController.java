/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.manage_menu;

import com.su25.swp391.dal.implement.Menu_CategoryDAO;
import com.su25.swp391.entity.Menu_Category;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/manage-menu/menu-category"})
public class CategoryController extends HttpServlet {

    private Menu_CategoryDAO menuCategoryDAO;

    @Override
    public void init() throws ServletException {
        menuCategoryDAO = new Menu_CategoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get request from Nutritionist
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Assign action = list 
        }
        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "deactivate":
                deactivateCategory(request, response);
                break;
            case "activate":
                activateCategory(request, response);
                break;
            case "list":
            default:
                listCategories(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Default action
        }

        switch (action) {
            case "add":
                addCategory(request, response);
                break;
            case "update":
                updateCategory(request, response);
                break;
            default:
                listCategories(request, response);
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

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String menuCategoryIdStr = request.getParameter("id");
        if (menuCategoryIdStr != null && !menuCategoryIdStr.isEmpty()) {
            int categoryId = Integer.parseInt(menuCategoryIdStr);
            Menu_Category menuCategory = menuCategoryDAO.findById(categoryId);
            if (menuCategory != null) {
                // Get parentCategory
                List<Menu_Category> parentCategory = menuCategoryDAO.fillAllParentCategories();
                request.setAttribute("parentCategory", parentCategory);
                request.setAttribute("menuCategory", menuCategory);
                request.getRequestDispatcher("/view/nutritionist/menu-category-edit.jsp").forward(request, response);

            }

        }
        response.sendRedirect(request.getContextPath() + "view/nutritionist/dashboard.jsp");
    }

    private void deactivateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void activateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void listCategories(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Lấy thông tin từ form
            String name = request.getParameter("name");
            if (!menuCategoryDAO.isCategoryNameExist(name, -1)) {
                Menu_Category newCategory = Menu_Category
                        .builder()
                        .name(name)
                        .build();
                int newId = menuCategoryDAO.insert(newCategory);
                if (newId > 0) {
                    request.getSession().setAttribute("toastMessage", "Thêm danh mục thành công");
                    request.getSession().setAttribute("toastType", "success");
                }
                response.sendRedirect(request.getContextPath() + "../view/nutritionist/addCategory.jsp");

            } else {
                request.getSession().setAttribute("error", "Name already");
                response.sendRedirect(request.getContextPath() + "../view/nutritionist/dashboard.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> ValidateCategoryData(int id, String name) {
        Map<String, String> error = new HashMap<>();
        // Validate Name
        if (name == null || name.trim().isEmpty()) {
            error.put("name", "Category name is required");
        } else if (name.length() > 100) {
            error.put("name", "Category name must be less than 100 characteristic");
        } else {
            // Check name already existed
            boolean nameExist = (name == null)
                    ? menuCategoryDAO.isCategoryNameExist(name, -1)
                    : menuCategoryDAO.isCategoryNameExist(name, id);

        }
        if (menuCategoryDAO.isCategoryNameExist(name, id)) {
            error.put("name", "Name already Existed");
        } else {
            error.put("name", "Name is valid");
        }
        return error;
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
