/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.nutritionist;

import com.su25.swp391.dal.implement.ComboDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.entity.Combo;
import com.su25.swp391.entity.ComboFoodDetails;
import com.su25.swp391.entity.Food;
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
@WebServlet(name = "ManagerCombo", urlPatterns = {"/managerCombo"})
public class ManagerCombo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";//default action
        }

        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "viewComboFoodDetail":
                viewComboFoodDetail(request, response);
                break;
            case "inactive":
                deactiveCombo(request, response);
                break;
            case "active":
                active(request, response);
                break;
            case "list":
            default:
                listCombo(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "add":
                addCombo(request, response);
                break;
            case "update":
                updateCombo(request, response);
                break;
            default:
                listCombo(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void active(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comboIdStr = request.getParameter("comboId");
        //kieemr tra xem id co null hay ko
        if (comboIdStr != null && !comboIdStr.isEmpty()) {
            int comboId = Integer.parseInt(comboIdStr);
            ComboDAO comboDao = new ComboDAO();
            boolean isActive = comboDao.activatCombo(comboId);
            if (isActive) {
                //lấy lại côm sau khi kich hoạt
                Combo combo = comboDao.findById(comboId);
                if (combo != null) {
                    request.getSession().setAttribute("toastMessage", "Combo đã được khôi phục");
                     request.getSession().setAttribute("toastType", "success");
                } else {
                    request.getSession().setAttribute("toastMessage", "Không tìm thấy combo để thể kích hoạt");
                    request.getSession().setAttribute("toastType", "error");

                }

            } else {
                request.getSession().setAttribute("toastMessage", "Kích hoạt combo thất bại. Vui lòng thử lại.");
                request.getSession().setAttribute("toastType", "error");
                
            }
        }
        response.sendRedirect(request.getContextPath() + "/managerCombo");
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void deactiveCombo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comboIdStr = request.getParameter("comboId");
        //kieemr tra xem id co null hay ko
        if (comboIdStr != null && !comboIdStr.isEmpty()) {
            int comboId = Integer.parseInt(comboIdStr);
            ComboDAO comboDao = new ComboDAO();
            boolean isDeactive = comboDao.deactivateAccount(comboId);
            if (isDeactive) {
                //lấy lại côm sau khi kich hoạt
                Combo combo = comboDao.findById(comboId);
                if (combo != null) {
                    request.getSession().setAttribute("toastMessage", "Combo đã Deactive");
                    request.getSession().setAttribute("toastType", "success");
                } else {
                    request.getSession().setAttribute("toastMessage", "Không tìm thấy combo ");
                    request.getSession().setAttribute("toastType", "error");

                }

            } else {
                request.getSession().setAttribute("toastMessage", "Deactive combo thất bại. Vui lòng thử lại.");
                request.getSession().setAttribute("toastType", "error");
            }
        }
        response.sendRedirect(request.getContextPath() + "/managerCombo");
    }

    private void listCombo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //lay thông tin khi người dùng tìm kiếm
        String serchFilter = request.getParameter("serch");
        String statusFilter = request.getParameter("status");

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
        ComboDAO comboDao = new ComboDAO();
        //lay ra danh sach combo 
        List<Combo> combo = comboDao.findAllWithPagination(currentPage, pageSize);
        //tinh phan trang
        int totalCombo = comboDao.getTotalComboCount();
        int totalPages = (int) Math.ceil((double) totalCombo / pageSize);
        //set cac thuoc tinhs
        request.setAttribute("listCombo", combo);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalCombo", totalCombo);
        request.setAttribute("totalPages", totalPages);

        //tinh toan pham vi the hien
        int startRecord = (currentPage - 1) * pageSize + 1;
        int endRecord = Math.min(startRecord + pageSize - 1, totalCombo);
        request.setAttribute("startRecord", startRecord);
        request.setAttribute("endRecord", endRecord);
        request.getRequestDispatcher("/view/nutritionist/combo/listCombo.jsp").forward(request, response);

    }

    private void addCombo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FoodDAO foodDao = new FoodDAO();
        List<Food> listFood = foodDao.findAll();
        request.setAttribute("listFood", listFood);
request.getRequestDispatcher("/view/nutritionist/combo/addCombo.jsp").forward(request, response);
    }

    private void updateCombo(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void viewComboFoodDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComboDAO combodao = new ComboDAO();
        //lay id của combo
        int comboId = Integer.parseInt(request.getParameter("id"));
        //lấy thông tin combo
        Combo combo = combodao.findById(comboId);
        //lấy danh sách trong mon ăn trong combo
        List<ComboFoodDetails> foodDetails = combodao.getComboFoodDetails(comboId);

        request.setAttribute("combo", combo);
        request.setAttribute("foodDetails", foodDetails);
        request.getRequestDispatcher("/view/nutritionist/combo/comboFoodDetail.jsp").forward(request, response);

    }

}
