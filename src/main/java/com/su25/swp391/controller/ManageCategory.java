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
import java.util.List;

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
            case "search":
                searchCate(request, response);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/common/categoryPage/category_add.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForn(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        request.setAttribute("totalCategory", totalCategory);

        //tinh toan pham vi the hien
        int startRecord = (currentPage - 1) * pageSize + 1;
        int endRecord = Math.min(startRecord + pageSize - 1, totalCategory);
        request.setAttribute("startRecord", startRecord);
        request.setAttribute("endStart", endRecord);
        request.getRequestDispatcher("view/common/categoryPage/category_list.jsp").forward(request, response);
        
    }
    
    private void searchCate(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void addCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void updateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
