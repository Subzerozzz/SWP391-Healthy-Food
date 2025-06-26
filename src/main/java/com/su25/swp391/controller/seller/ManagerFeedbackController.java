/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.seller;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.FeedbackDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.OrderApprovalDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Feedback;
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
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
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
    private FeedbackDAO feedbackDAO;
    private AccountDAO accDAO;
    private FoodDAO foodDAO;
    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAO();
        approvalDAO = new OrderApprovalDAO();
        itemDAO = new OrderItemDAO();
        feedbackDAO = new FeedbackDAO();
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
                case "update":
                    hiddenFeedback(request, response);
                    break;
                case "account":
                    detailAccount(request, response);
                    break;
                case "food":
                    detailFood(request, response);
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
//            response.sendRedirect(request.getContextPath() + "/feedbackControl");
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
         //Get parameter by Food Name
        String selectFood = request.getParameter("selectFood");
          // Get filter parameters  by Rating
        String rating = request.getParameter("rating");
        // Get search by name, email
        String search = request.getParameter("search");
       
        // Pagination
        int page = 1;
        int pageSize = 10;
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
        List<Feedback> feedbacks;
        int totalFeedback;

        if (search != null && !search.trim().isEmpty()) {
            // If there's a search term, use search with payment method and rating
            feedbacks = feedbackDAO.searchFeedback(search, rating,selectFood, page, pageSize);
            totalFeedback = feedbackDAO.getTotalFeedbackResults(search, rating,selectFood);
        } else {
            // If no search, use filters
            feedbacks = feedbackDAO.findFeedbackWithFilters(rating,selectFood, page, pageSize);
            // count order
            totalFeedback = feedbackDAO.getTotalFilteredFeedback(rating,selectFood);
        }
        // Number of page can have
        int totalPages = (int) Math.ceil((double) totalFeedback / pageSize);

        // get Account
        HashMap<Integer, Account> AccountMap = new HashMap<>();
        for (Feedback feedback : feedbacks) {
            Account acc = accDAO.findById(feedback.getUser_id());
            AccountMap.put(feedback.getUser_id(), acc);
        }
        // get Food
        HashMap<Integer, Food> FoodMap = new HashMap<>();
        for (Feedback feedback : feedbacks) {
            OrderItem item = itemDAO.findById(feedback.getOrder_item_id());
            Food food = foodDAO.findById(item.getFood_id());
            FoodMap.put(feedback.getOrder_item_id(), food);
        }
        List<String> lFood = foodDAO.findFoodNameList();
        // Set attributes
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("rating", rating);
        request.setAttribute("search", search);
        request.setAttribute("selectFood",selectFood);
        request.setAttribute("feedbacks", feedbacks);
        request.setAttribute("AccountMap", AccountMap);
        request.setAttribute("FoodMap", FoodMap);
        request.setAttribute("lFood",lFood);
        PrintWriter o = response.getWriter();
        o.print(feedbacks);
        o.print(AccountMap);
        o.print(FoodMap);
        o.print(lFood);
        o.print(rating);
        request.getRequestDispatcher("/view/seller/feedback-list.jsp").forward(request, response);
    }

    private void viewDetailFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        Feedback feedback = feedbackDAO.findById(feedbackId);
        // get account
        HashMap<Integer, Account> AccountMap = new HashMap<>();
        Account acc = accDAO.findById(feedback.getUser_id());
        AccountMap.put(feedback.getUser_id(), acc);

        HashMap<Integer, Food> FoodMap = new HashMap<>();

        OrderItem item = itemDAO.findById(feedback.getOrder_item_id());
        Food food = foodDAO.findById(item.getFood_id());
        FoodMap.put(feedback.getOrder_item_id(), food);

        request.setAttribute("feedback", feedback);
        request.setAttribute("AccountMap", AccountMap);
        request.setAttribute("FoodMap", FoodMap);
        PrintWriter o = response.getWriter();
        o.print(feedback);
        o.print(AccountMap);
        o.print(FoodMap);
        request.getRequestDispatcher("/view/seller/feedback-detail.jsp").forward(request, response);
    }

    private void hiddenFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        Feedback feedbackHidden = feedbackDAO.findById(feedbackId); 
        if(feedbackHidden == null){
          session.setAttribute("isError", true);
          response.sendRedirect(request.getContextPath()+"/seller/manage-feedback");
          return;
        }
        boolean isUpdateSuccess = feedbackDAO.update(feedbackHidden);
        if(isUpdateSuccess){
           session.setAttribute("isSuccess", true);
        }else{
           session.setAttribute("isError", true); 
        }
        response.sendRedirect(request.getContextPath()+"/seller/manage-feedback");
    }

    private void detailAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int account_id = Integer.parseInt(request.getParameter("account_id"));
        Account acc = accDAO.findById(account_id);
        request.setAttribute("account",acc);
        request.getRequestDispatcher("/view/seller/view-feedback-account.jsp").forward(request, response);
        
    }

    private void detailFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         int food_id = Integer.parseInt(request.getParameter("food_id"));
         Food food = foodDAO.findById(food_id);
         Account nutri = accDAO.findById(food.getNutri_id());
         request.setAttribute("foodD",food);
         request.setAttribute("nutri", nutri);
         request.setAttribute("accDAO", accDAO);
         PrintWriter o = response.getWriter();
         o.print(nutri);
         request.getRequestDispatcher("/view/seller/view-feedback-food.jsp").forward(request, response);
    }

}
