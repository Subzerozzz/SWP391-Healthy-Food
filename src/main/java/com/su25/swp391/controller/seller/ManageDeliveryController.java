/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.su25.swp391.controller.seller;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.DeliveryDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Delivery;
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
import java.util.Map;

/**
 *
 * @author Admin
 */
@WebServlet(name="ManageDeliveryController", urlPatterns={"/seller/manage-delivery"})
public class ManageDeliveryController extends HttpServlet {
     
     private DeliveryDAO deliveryDAO;
     private OrderDAO orderDAO;
     private AccountDAO accDAO;
     private FoodDAO foodDAO;
     private OrderItemDAO itemDAO;
     @Override
    public void init() throws ServletException {
        deliveryDAO = new DeliveryDAO();
        accDAO = new AccountDAO();
        orderDAO = new OrderDAO();
        foodDAO = new FoodDAO();
        itemDAO = new OrderItemDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get action from submit
        String action = request.getParameter("action");
        // If action == null
        if (action == null) {
            action = "list";
        }
        try {
            switch (action) {
                case "list":
                    listDelivery(request, response);
                    break;
                case "view":
                    viewDelivery(request, response);
                    break;
                default:
                    listDelivery(request, response);
                    break;
            }
        } catch (Exception ex) {
            // Notification error
            request.setAttribute("errorMessage", "Database error: " + ex.getMessage());
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get action from submit
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
       try {
            switch (action) {
                default:
                    listDelivery(request, response);
                    break;
            }
        } catch (Exception ex) {
            // Notification error
            request.setAttribute("errorMessage", "Database error: " + ex.getMessage());
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void listDelivery(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sort = request.getParameter("sort");
        String status = request.getParameter("status");
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
        }
        // Get orders with filters
        List<Delivery> listDelivery;
        int totalDeliveries = 0;
        if (search != null && !search.trim().isEmpty()) {
            listDelivery = deliveryDAO.searchDelivery(search, sort, status, page, pageSize);
            totalDeliveries = deliveryDAO.getTotalDeliveryResults(search, status);
        } else {
            // If no search, use filters
            listDelivery = deliveryDAO.findDeliveryWithFilters(sort, status, page, pageSize);
            totalDeliveries = deliveryDAO.getTotalFilteredDelivery(status);
        }
        // Number of page can have
        int totalPages = (int) Math.ceil((double) totalDeliveries / pageSize);

        request.setAttribute("sort", sort);
        request.setAttribute("status", status);
        request.setAttribute("search", search);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("accDAO", accDAO);
        request.setAttribute("orderDAO", orderDAO);
        request.setAttribute("listDelivery", listDelivery);
        request.getRequestDispatcher("/view/seller/manage-delivery-list.jsp").forward(request, response);
    }


   

    private void viewDelivery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
            // Get the seller account from session
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

            // Check if the seller is logged in
            if (account == null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
            int id = Integer.parseInt(request.getParameter("id"));
            
            int shipper_id = Integer.parseInt(request.getParameter("shipper_id"));
            
            Delivery delivery = deliveryDAO.findById(id);
            
            Order order = orderDAO.findById(delivery.getOrder_id());
            
            Account accShipper = accDAO.findById(shipper_id);

              // If the order does not exist, forward to error page
            if (order == null) {
                request.setAttribute("errorMessage", "Order not found with ID: " + delivery.getOrder_id());
                request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
                return;
            }

            // Retrieve the account associated with the order
            Account acc = accDAO.findById(order.getAccount_id());

            // Retrieve the list of items in the order
            List<OrderItem> orderItems = itemDAO.getOrderItemsByOrderId(order.getId());

            // Map food details for each order item
            HashMap<Integer, Food> OrderItemMap = new HashMap<>();
            for (OrderItem orderItem : orderItems) {
                Food food = foodDAO.findById(orderItem.getFood_id());
                OrderItemMap.put(orderItem.getFood_id(), food);
            }

            // Set attributes for JSP rendering
            request.setAttribute("order", order);
            request.setAttribute("account", acc);
            request.setAttribute("OrderItems", orderItems);
            request.setAttribute("OrderItemMap", OrderItemMap);
            request.setAttribute("accShipper", accShipper);
            request.setAttribute("de", delivery);
            // Forward to the delivery detail JSP page
            request.getRequestDispatcher("/view/seller/delivery-detail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Handle invalid order ID format
            request.setAttribute("errorMessage", "Invalid order ID format");
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }

}
