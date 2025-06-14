/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.coupon;

import com.su25.swp391.dal.implement.CouponDAO;
import com.su25.swp391.entity.Coupon;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Predator
 */
@WebServlet(name = "ManagerCouponController", urlPatterns = {"/ManagerCoupon"})
public class ManagerCouponController extends HttpServlet {
    private CouponDAO couponDAO;
    public void init() {
        couponDAO = new CouponDAO();
    }
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
            action = "list";
        }
        switch (action) {
            case "list":
                listCouponDoGet(request, response);
                break;
            case "add":
                showAddDoGet(request, response);
                break;
            case "edit":
                showEditDoGet(request, response);
                break;
            case "view":
                showDetailDoGet(request, response);
                break;
            case "delete":
                deleteCouponDoGet(request, response);
                break;
            case "search":
                searchByNameDoGet(request, response);
                break;
            case "filter":
                handleFilter(request, response);
                break;
            default:
                listCouponDoGet(request, response);
                break;
        }
    }

    private void showAddDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/coupon/addCoupon.jsp");
        dispatcher.forward(request, response);
    }
      private void listCouponDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int currentPage = Integer.parseInt(indexPage);
        int totalBlogs = couponDAO.getTotalCoupon();
        int totalPage = totalBlogs / 10;
        if (totalBlogs % 10 != 0) {
            totalPage++;
        }
        List<Coupon> coupon = couponDAO.pagingCoupon(currentPage);
        request.setAttribute("coupons", coupon);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/coupon/listCoupon.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Coupon coupon = couponDAO.findById(id);
        if (coupon != null) {
            request.setAttribute("coupon", coupon);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/coupon/editCoupon.jsp");
            dispatcher.forward(request, response);
        } else {
            request.getSession().setAttribute("toastMessage", "Coupon not found");
            request.getSession().setAttribute("toastType", "error");
            response.sendRedirect(request.getContextPath() + "/view/coupon/editCoupon.jsp");
        }
    }
    
    private void showDetailDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Coupon coupon = couponDAO.findById(id);
        request.setAttribute("coupon", coupon);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/coupon/viewdetailCoupon.jsp");
        dispatcher.forward(request, response);
    }
    private void deleteCouponDoGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Coupon coupon = couponDAO.findById(id);
        if (coupon != null) {
            boolean delete = couponDAO.delete(coupon);
            if (delete) {
                HttpSession session = request.getSession();
                request.getSession().setAttribute("toastMessage", "Coupon delete succesfull");
                request.getSession().setAttribute("toastType", "success");
                session.setAttribute("isDelete", true);
            } else {
                request.getSession().setAttribute("toastMessage", "Fail to Coupon ");
                request.getSession().setAttribute("toastType", "error");
            }
        } else {
            request.getSession().setAttribute("toastMessage", "Coupon not found");
            request.getSession().setAttribute("toastType", "error");
        }

        response.sendRedirect(request.getContextPath() + "/ManagerCoupon");
    }

    private void searchByNameDoGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String searchKeyword = request.getParameter("search");
        String indexStr = request.getParameter("index");
        int currentPage = 1;
        int pageSize = 10; // số bản ghi mỗi trang, bạn có thể chỉnh lại

        if (indexStr != null) {
            try {
                currentPage = Integer.parseInt(indexStr);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/ManagerCoupon?action=list");
            return;
        }
        searchKeyword = searchKeyword.trim();
        int offset = (currentPage - 1) * pageSize;
        // Lấy danh sách coupon theo từ khóa và phân trang
        List<Coupon> listCoupon = couponDAO.searchCouponsByCodeorDescription(searchKeyword, offset, pageSize);
        // Lấy tổng số coupon phù hợp để tính tổng trang
        int totalCoupons = couponDAO.countCouponsBySearch(searchKeyword);
        int totalPage = (int) Math.ceil((double) totalCoupons / pageSize);
        request.setAttribute("coupons", listCoupon);
        request.setAttribute("search", searchKeyword);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.getRequestDispatcher("/view/coupon/listCoupon.jsp").forward(request, response);
    }

    private void handleFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("status");
    String search = request.getParameter("search"); // Thêm search parameter
    
    // Lấy tham số phân trang - Sửa "page" thành "index" để phù hợp với phân trang
    String pageParam = request.getParameter("index"); // Đổi từ "page" thành "index"
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
    
    // Lấy danh sách đã lọc với phân trang
    List<Coupon> filteredCoupon = couponDAO.filterCouponWithPagination(status, search, currentPage, pageSize);
    
    // Tính toán tổng số blog với bộ lọc
    int totalBlogs = couponDAO.getTotalCouponCountWithFilter(status, search);
    int totalPages = (int) Math.ceil((double) totalBlogs / pageSize);
    
    // Thiết lập các thuộc tính cho JSP
    request.setAttribute("blogs", filteredCoupon);
    request.setAttribute("currentPage", currentPage);
    request.setAttribute("totalPage", totalPages); // Đổi từ "totalPages" thành "totalPage" để phù hợp với phân trang
    request.setAttribute("pageSize", pageSize);
    request.setAttribute("totalBlogs", totalBlogs);
    request.setAttribute("status", status);
    request.setAttribute("search", search); // Thêm search attribute
    
    // Tính toán phạm vi hiển thị bản ghi
    int startRecord = (currentPage - 1) * pageSize + 1;
    int endRecord = Math.min(startRecord + pageSize - 1, totalBlogs);
    request.setAttribute("startRecord", startRecord);
    request.setAttribute("endRecord", endRecord);
   
    request.getRequestDispatcher("/view/coupon/listCoupon.jsp").forward(request, response);
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
                couponCreateDoPost(request, response);
                break;
            case "edit":
                couponUpdateDoPost(request, response);
                break;
        }
    }

    private void couponCreateDoPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void couponUpdateDoPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
