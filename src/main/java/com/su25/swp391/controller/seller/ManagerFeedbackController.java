/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.seller;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.FeedbacksDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.OrderApprovalDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Feedbacks;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.Order;
import com.su25.swp391.entity.OrderItem;
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
 * @author Admin
 */
@WebServlet(name = "ManagerFeedbackController", urlPatterns = {"/seller/manage-feedback"})
public class ManagerFeedbackController extends HttpServlet {

    private OrderDAO orderDAO;
    private OrderApprovalDAO approvalDAO;
    private OrderItemDAO itemDAO;
    private FeedbacksDAO feedbackDAO;
    private AccountDAO accDAO;
    private FoodDAO foodDAO;
    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAO();
        approvalDAO = new OrderApprovalDAO();
        itemDAO = new OrderItemDAO();
        feedbackDAO = new FeedbacksDAO();
        accDAO = new AccountDAO();
        foodDAO = new FoodDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get action from submit
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }
        try {
            switch (action) {
                case "list":
                    listFeedbacks(request, response);
                    break;

                case "view":
                    viewDetailFeedback(request, response);
                    break;
                default:
                    listFeedbacks(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
//            session.setAttribute("errorMessage", "Đã xảy ra lỗi: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/feedbackControl");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get action from submit
        String action = request.getParameter("action");
        try {
            switch (action) {

                case "update":
                    hiddenFeedback(request, response);
                    break;
                default:
                    listFeedbacks(request, response);
            }
        } catch (Exception ex) {
//            session.setAttribute("errorMessage", "Đã xảy ra lỗi: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/feedbackControl");
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

     private void listFeedbacks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         // Get filter parameters by Status
        String status = request.getParameter("status");
        // Get search by name, id, email
        String search = request.getParameter("search");
        // Pagination
        int page = 1;
        int pageSize = GlobalConfig.SIZE_PAGE;
        try {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                if (page < 1) {
                    page = 1;
                }
            }
        } catch (NumberFormatException e) {
            // Keep default value
        }// Get orders with filters
        List<Feedbacks> feedbacks;
        int totalOrders;

        if (search != null && !search.trim().isEmpty()) {
            // If there's a search term, use search with payment method and status
            feedbacks = feedbackDAO.searchFeedback(search, status, page, pageSize);
            totalOrders = feedbackDAO.getTotalFeedbackResults(search, status);
        } else {
            // If no search, use filters
            feedbacks=feedbackDAO.findFeedbackWithFilters(status, page, pageSize);
            // count order
            totalOrders = feedbackDAO.getTotalFilteredFeedback(status);
        }
        // Number of page can have
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);
        
        // get account
         for (Feedbacks feedback : feedbacks) {
              Account acc = accDAO.findById(feedback.getUserId());
              feedback.setAccount(acc);
              OrderItem item = itemDAO.findById(feedback.getOrderItemId());
              Food food = foodDAO.findById(item.getFoodId());
              feedback.setFood(food);
         }
         
        
        // Set attributes
        request.setAttribute("orders", feedbacks);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("status", status);
        request.setAttribute("search", search);
        // Forword to the order list page
        request.getRequestDispatcher("/view/seller/order-list.jsp").forward(request, response);
        
         List<Feedbacks> list = feedbackDAO.findAll();
        request.setAttribute("feedbacks", list);
        request.getRequestDispatcher("/view/seller/feedback-list.jsp").forward(request, response);
    }

    private void viewDetailFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        Feedbacks feedback = feedbackDAO.findById(feedbackId);
        request.setAttribute("feedback", feedback);
        request.getRequestDispatcher("/view/seller/feedback-detail.jsp").forward(request, response);
    }

    private void hiddenFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        Feedbacks feedback = feedbackDAO.findById(feedbackId);
        feedbackDAO.update(feedback);
        request.getRequestDispatcher("/view/seller/feedback-list.jsp").forward(request, response);
    }

}
