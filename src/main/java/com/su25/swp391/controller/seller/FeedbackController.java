/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.seller;
import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.FeedbackDAO2;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.OrderApprovalDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Feedback;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.OrderItem;
import jakarta.servlet.RequestDispatcher;
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


@WebServlet(name = "FeedbackController", urlPatterns = {"/seller/feedback"})
public class FeedbackController extends HttpServlet {

    private OrderDAO orderDAO;
    private OrderApprovalDAO approvalDAO;
    private OrderItemDAO itemDAO;
    private FeedbackDAO2 feedbackDAO;
    private AccountDAO accDAO;
    private FoodDAO foodDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAO();
        approvalDAO = new OrderApprovalDAO();
        itemDAO = new OrderItemDAO();
        feedbackDAO = new FeedbackDAO2();
        accDAO = new AccountDAO();
        foodDAO = new FoodDAO();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "list";
        }
        try {
            switch (action) {
                case "list":
                case "search":
                case "filter":
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
        }
    }

   

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

     /**
     * Get a list Feedback by sort and filter without search if search == null and empty
     * Get total number feedback by sort and filter without search if search == null and empty
     * Get a list Feedback by sort and filter and search if search != null and !empty
     * Get  total number feedback by sort and filter and search if search != null and !empty
     * Pagination with default page = 1 and page-size = 10
     * Set list Feedback for feedback-list
     * Set attribute: selectFood, rating, search, sort 
     * Get AccountMap by user_id in table feedback
     * Get FoodMap by food_id in table OrderItem , get OrderItem by order_item_in in table feedback
     * Get list Food Name by table Food 
     */
    private void listFeedbacks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

        // Check if the seller is logged in
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String indexPage = request.getParameter("index");
        if (indexPage == null || indexPage.isEmpty()) {
            indexPage = "1";
        }
        int currentPage = Integer.parseInt(indexPage);
        int pageSize = 10;

        String search = request.getParameter("search");
        String rating = request.getParameter("rating");
        String sort = request.getParameter("sort");

        List<Feedback> feedbacks = feedbackDAO.getFeedbackByCriteria(search, rating, sort, currentPage, pageSize);
        int totalFeedbacks = feedbackDAO.countFeedbackByCriteria(search, rating);

        int totalPage = (int) Math.ceil((double) totalFeedbacks / pageSize);

        HashMap<Integer, Account> accountMap = new HashMap<>();
        HashMap<Integer, String> foodNameMap = new HashMap<>();
        HashMap<Integer, Integer> foodIdMap = new HashMap<>();
        for (Feedback feedback : feedbacks) {
            if (!accountMap.containsKey(feedback.getUser_id())) {
                Account acc = accDAO.findById(feedback.getUser_id());
                if (acc != null) {
                    accountMap.put(feedback.getUser_id(), acc);
                }
            }
            if (!foodNameMap.containsKey(feedback.getOrder_item_id())) {
                OrderItem item = itemDAO.findById(feedback.getOrder_item_id());
                if (item != null) {
                    Food food = foodDAO.findById(item.getFood_id());
                    if (food != null) {
                        foodNameMap.put(feedback.getOrder_item_id(), food.getName());
                        foodIdMap.put(feedback.getOrder_item_id(), food.getId());
                    }
                }
            }
        }

        request.setAttribute("feedbacks", feedbacks);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("search", search);
        request.setAttribute("rating", rating);
        request.setAttribute("sort", sort);
        request.setAttribute("accountMap", accountMap);
        request.setAttribute("foodNameMap", foodNameMap);
        request.setAttribute("foodIdMap", foodIdMap);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/feedback/list-feedback-seller.jsp");
        dispatcher.forward(request, response);
    }
    /**
     * Get Detail of Feedback by id feedback
     * Get AccountMap by user_id in table feedback
     * Get FoodMap by food_id in table OrderItem , get OrderItem by order_item_in in table feedback
    **/
    private void viewDetailFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
             // Get id feedback parameter
            int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
            // Find feedback by feedbackId
            Feedback feedback = feedbackDAO.findById(feedbackId);
            HashMap<Integer, Account> AccountMap = new HashMap<>();
            //Find Account by user_id in table feedback
            Account acc = accDAO.findById(feedback.getUser_id());
            AccountMap.put(feedback.getUser_id(), acc);
            // get Food by food_id in table OrderItem
            HashMap<Integer, Food> FoodMap = new HashMap<>();
            // Find OrderItem by order_item_id in table Feedback
            OrderItem item = itemDAO.findById(feedback.getOrder_item_id());
            // Find Food by food_id in table OrderItem
            Food food = foodDAO.findById(item.getFood_id());
            FoodMap.put(feedback.getOrder_item_id(), food);
            request.setAttribute("feedback", feedback);
            request.setAttribute("AccountMap", AccountMap);
            request.setAttribute("FoodMap", FoodMap);
            // Redirect to feedback-detail.jsp
            request.getRequestDispatcher("/view/seller/feedback-detail.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle invalid order ID format or unexpected errors
            request.setAttribute("errorMessage", "Invalid order ID format");
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }
    /**
     * get feebbackId parameter and parse to int
     * Find feedback by feedbackID
     * Update is_visible
    **/
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action") == null ? "list" : request.getParameter("action");
        try {
            switch (action) {
                case "update":
                    hiddenFeedback(request, response);
                    break;
                default:
                    listFeedbacks(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void hiddenFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            // get feebbackId parameter and parse to int
            int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
            // Find feedback by feedbackID
            Feedback feedbackHidden = feedbackDAO.findById(feedbackId);
            // check feedback not exist and back to feedback list with notification error
            if (feedbackHidden == null) {
                session.setAttribute("isError", true);
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/seller/feedback"));
                return;
            }
            // Have  feedback and update is_visible make hidden feedback
            feedbackHidden.setVisible(!feedbackHidden.isVisible());
            boolean isUpdateSuccess = feedbackDAO.update(feedbackHidden);
            // Check update success give notification
            if (isUpdateSuccess) {
                session.setAttribute("isSuccess", true);
            } else {
                // Check update not success give notification
                session.setAttribute("isError", true);
            }
            // Redirect to list feedback
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/seller/feedback"));
        } catch (Exception e) {
            // Handle invalid order ID format or unexpected errors
            request.setAttribute("errorMessage", "Invalid order ID format");
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }
    /**
     * get account_id parameter and parse to int
     * Find Account by account_id
     * set Attribute account 
    **/
    private void detailAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // get account_id parameter and parse to int
            int account_id = Integer.parseInt(request.getParameter("account_id"));
            // Find Account by account_id
            Account acc = accDAO.findById(account_id);
            request.setAttribute("account", acc);
            // Redirect to view-feedback-account.jsp
            request.getRequestDispatcher("/view/seller/view-feedback-account.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle invalid order ID format or unexpected errors
            request.setAttribute("errorMessage", "Invalid order ID format");
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }
    /**
     * get account_id parameter and parse to int
     * Find Account by account_id
     * set Attribute account 
    **/
    private void detailFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // get food_id parameter and parse to int
            int food_id = Integer.parseInt(request.getParameter("food_id"));
            // Find Food by food_id
            Food food = foodDAO.findById(food_id);
            // get Account with role nutri in table Food by nutri_id
            Account nutri = accDAO.findById(food.getNutri_id());
            request.setAttribute("foodD", food);
            request.setAttribute("nutri", nutri);
            request.setAttribute("accDAO", accDAO);
            // Redirect to view-feedback-food.jsp
            request.getRequestDispatcher("/view/seller/view-feedback-food.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle invalid order ID format or unexpected errors
            request.setAttribute("errorMessage", "Invalid order ID format");
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }
   // Change feedback
}
