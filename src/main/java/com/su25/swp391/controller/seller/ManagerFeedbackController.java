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
        // if action = null assign list
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
         try{
            // Get the seller account from session
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

            // Check if the seller is logged in
            if (account == null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
// get Parameter sort by id
            String sort = request.getParameter("sort");
            // Pagination
            //Get parameter by Food Name
            String selectFood = request.getParameter("selectFood");
            // Get filter parameters  by Rating
            String rating = request.getParameter("rating");
            // Get search by name, email
            String search = request.getParameter("search");

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
            }
            // Create a feedback list default
            List<Feedback> feedbacks;
            // Create a total feedback default
            int totalFeedback;
            // check value input of search
            if (search != null && !search.trim().isEmpty()) {
                // If there's a search term, use search with payment method and rating
                feedbacks = feedbackDAO.searchFeedback(search, rating, selectFood, sort, page, pageSize);
                totalFeedback = feedbackDAO.getTotalFeedbackResults(search, rating, selectFood);
            } else {
                // If no search, use filters
                feedbacks = feedbackDAO.findFeedbackWithFilters(rating, selectFood, sort, page, pageSize);
                // count feedback
                totalFeedback = feedbackDAO.getTotalFilteredFeedback(rating, selectFood);
            }
            // Number of page can have
            int totalPages = (int) Math.ceil((double) totalFeedback / pageSize);

            // get Account by user_id in table feedback
            HashMap<Integer, Account> AccountMap = new HashMap<>();
            for (Feedback feedback : feedbacks) {
                //Account by user_id in table feedback
                Account acc = accDAO.findById(feedback.getUser_id());
                AccountMap.put(feedback.getUser_id(), acc);
            }
            // get Food by food_id in table OrderItem
            HashMap<Integer, Food> FoodMap = new HashMap<>();
            for (Feedback feedback : feedbacks) {
                // Get OrderItem by order_item_id in table Feedback
                OrderItem item = itemDAO.findById(feedback.getOrder_item_id());
                Food food = foodDAO.findById(item.getFood_id());
                FoodMap.put(feedback.getOrder_item_id(), food);
            }
            // get list Food Name in table Food
            List<String> lFood = foodDAO.findFoodNameList();
            // Set attributes
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("rating", rating);
            request.setAttribute("search", search);
            request.setAttribute("sort", sort);
            request.setAttribute("selectFood", selectFood);
            request.setAttribute("feedbacks", feedbacks);
            request.setAttribute("AccountMap", AccountMap);
            request.setAttribute("FoodMap", FoodMap);
            request.setAttribute("lFood", lFood);
            // Redirect to feedback-list.jsp
            request.getRequestDispatcher("/view/seller/feedback-list.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle invalid order ID format or unexpected errors
            request.setAttribute("errorMessage", "Invalid order ID format");
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }
    /**
     * Get Detail of Feedback by id feedback
     * Get AccountMap by user_id in table feedback
     * Get FoodMap by food_id in table OrderItem , get OrderItem by order_item_in in table feedback
    **/
    private void viewDetailFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the seller account from session
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

            // Check if the seller is logged in
            if (account == null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
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
    private void hiddenFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the seller account from session
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

            // Check if the seller is logged in
            if (account == null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }

            // get feebbackId parameter and parse to int
            int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
            // Find feedback by feedbackID
            Feedback feedbackHidden = feedbackDAO.findById(feedbackId);
            // check feedback not exist and back to feedback list with notification error
            if (feedbackHidden == null) {
                session.setAttribute("isError", true);
                response.sendRedirect(request.getContextPath() + "/seller/manage-feedback");
                return;
            }
            // Have  feedback and update is_visible make hidden feedback
            boolean isUpdateSuccess = feedbackDAO.update(feedbackHidden);
            // Check update success give notification
            if (isUpdateSuccess) {
                session.setAttribute("isSuccess", true);
            } else {
                // Check update not success give notification
                session.setAttribute("isError", true);
            }
            // Redirect to list feedback
            response.sendRedirect(request.getContextPath() + "/seller/manage-feedback");
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
            // Get the seller account from session
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

            // Check if the seller is logged in
            if (account == null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
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
            // Get the seller account from session
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

            // Check if the seller is logged in
            if (account == null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
            
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

}
