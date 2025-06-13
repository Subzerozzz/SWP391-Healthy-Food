/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.su25.swp391.controller.seller;


import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.Food_DraftDAO;
import com.su25.swp391.dal.implement.LogRequestDAO;
import com.su25.swp391.dal.implement.OrderApprovalDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;


import com.su25.swp391.dal.implement.RequestDAO;

import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Order;
import com.su25.swp391.entity.OrderApproval;
import com.su25.swp391.entity.OrderItem;
import com.su25.swp391.entity.Product;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author FPTSHOP
 */
@WebServlet(name = "ManagerOrderController", urlPatterns = {"/seller/manage-order"})
public class ManagerOrderController extends HttpServlet {
     
    private Food_DraftDAO foodDraftDAO;
    private RequestDAO requestDAO;
    private FoodDAO foodDAO;
    private LogRequestDAO logReqDAO;
    private OrderDAO orderDAO;
    private OrderApprovalDAO approvalDAO;
    private OrderItemDAO itemDAO;
    @Override
    public void init() throws ServletException{
        foodDraftDAO = new Food_DraftDAO();
        requestDAO = new RequestDAO();
        foodDAO = new FoodDAO();
        logReqDAO = new LogRequestDAO();
        orderDAO = new OrderDAO();
        approvalDAO = new OrderApprovalDAO();
        itemDAO = new OrderItemDAO();
    }
      
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        try {
            switch (action) {
                case "list":
                    listOrders(request, response);
                    break;
                case "view":
                    viewOrderDetail(request, response);
                    break;
               
                default:
                    listOrders(request, response);
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Database error: " + ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
         if (action == null) {
            action = "list";
        }
        
        try {
            switch (action) {
                case "updateStatus":
                    updateOrderStatus(request, response);
                    break;
                default:
                     listOrders(request, response);
                    break;
            }
        }catch(Exception ex){
            request.setAttribute("errorMessage", "Database error: " + ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// Get filter parameters
        String status = request.getParameter("status");
        String paymentMethod = request.getParameter("paymentMethod");
        String search = request.getParameter("search");
        int page = 1;
        int pageSize = 10;
        
        try {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                if (page < 1) page = 1;
            }
        } catch (NumberFormatException e) {
            // Keep default value
        }
        
        // Get orders with filters
        List<Order> orders;
        int totalOrders;
        
        if (search != null && !search.trim().isEmpty()) {
            // If there's a search term, use search with payment method
            orders = orderDAO.searchOrders(search, status, paymentMethod, page, pageSize);
            totalOrders = orderDAO.getTotalSearchResults(search, status, paymentMethod);
        } else {
            // If no search, use regular filters
            orders = orderDAO.findOrdersWithFilters(status, paymentMethod, page, pageSize);
            totalOrders = orderDAO.getTotalFilteredOrders(status, paymentMethod);
        }
        
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);
        
        // Set attributes
        request.setAttribute("orders", orders);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("status", status);
        request.setAttribute("paymentMethod", paymentMethod);
        request.setAttribute("search", search);
        
        // Forward to the order list page
        // Forword to the order list page
        request.getRequestDispatcher("/view/seller/order-list.jsp").forward(request, response);
    }

    private void updateOrderStatus(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Xem chi tiết đơn hàng
    private void viewOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         int orderId = Integer.parseInt(request.getParameter("orderId"));
        try {
           
            Order  order = orderDAO.findById(orderId);
            List<OrderApproval> approvals = approvalDAO.getOrderApprovalsByOrderId(orderId);
            List<OrderItem> orderItems = itemDAO.getOrderItemsByOrderId(orderId);
            
            if(order == null) {
                request.setAttribute("errorMessage", "Order not found with ID:" + orderId);
                request.getRequestDispatcher("WEB-INF/view/error/jsp").forward(request, response);
            }
            order.setOrderItems(orderItems);
            request.setAttribute("order", order);
            request.setAttribute("approvals", approvals);
            
            // forward to the order detail page
            request.getRequestDispatcher("/view/seller/order-list.jsp").forward(request, response);
            
            
        } catch (Exception e) {
             request.setAttribute("errorMessage", "Order not found with ID:" + orderId);
                request.getRequestDispatcher("WEB-INF/view/error/jsp").forward(request, response);
           }
    }

    }
