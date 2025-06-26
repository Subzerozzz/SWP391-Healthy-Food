/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.su25.swp391.controller.manager;

import com.su25.swp391.dal.implement.CouponDAO;
import com.su25.swp391.entity.Coupon;
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
 * @author Predator
 */
@WebServlet(name="CouponServlet", urlPatterns={"/Coupon"})
public class CouponServlet extends HttpServlet {
    private CouponDAO couponDAO;
    public void init() {
        couponDAO = new CouponDAO();
    }
    private static final int COUPONS_PER_PAGE = 9;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            String searchCode = request.getParameter("search");
            int page = 1;
            String pageStr = request.getParameter("page");
            if (pageStr != null && !pageStr.isEmpty()) {
                page = Integer.parseInt(pageStr);
            }

            CouponDAO couponDAO = new CouponDAO();
            List<Coupon> allCoupons;
            
            // Get coupons based on search criteria
            if (searchCode != null && !searchCode.trim().isEmpty()) {
                allCoupons = couponDAO.searchCoupons(searchCode.trim());
            } else {
                allCoupons = couponDAO.getAllActiveCoupons();
            }

            // Calculate pagination
            int totalCoupons = allCoupons.size();
            int totalPages = (int) Math.ceil((double) totalCoupons / COUPONS_PER_PAGE);
            
            // Ensure page is within valid range
            if (page < 1 || page > totalPages) {
                page = 1;
            } 
            
            // Get coupons for current page
            int startIndex = (page - 1) * COUPONS_PER_PAGE;
            int endIndex = Math.min(startIndex + COUPONS_PER_PAGE, totalCoupons);
            List<Coupon> coupons = allCoupons.subList(startIndex, endIndex);

            // Set attributes for JSP
            request.setAttribute("coupons", coupons);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("searchCode", searchCode);

            // Forward to coupon.jsp
            request.getRequestDispatcher("/view/manager/coupon.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles coupon listing and searching";
    }
}
