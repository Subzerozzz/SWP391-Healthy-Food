/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.seller;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.CouponDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.FoodDraftDAO;
import com.su25.swp391.dal.implement.LogRequestDAO;
import com.su25.swp391.dal.implement.OrderApprovalDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;

import com.su25.swp391.dal.implement.RequestDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Coupon;
import com.su25.swp391.entity.Food;

import com.su25.swp391.entity.Order;
import com.su25.swp391.entity.OrderApproval;
import com.su25.swp391.entity.OrderItem;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author FPTSHOP
 */
@WebServlet(name = "ManagerOrderController", urlPatterns = { "/seller/manage-order" })
public class ManagerOrderController extends HttpServlet {

    private OrderDAO orderDAO;
    private OrderApprovalDAO approvalDAO;
    private OrderItemDAO itemDAO;
    private AccountDAO accDAO = new AccountDAO();
    private CouponDAO couponDAO;
    private FoodDAO foodDAO;
    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAO();
        approvalDAO = new OrderApprovalDAO();
        itemDAO = new OrderItemDAO();
        accDAO = new AccountDAO();
        couponDAO = new CouponDAO();
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
                case "updateStatus":
                    updateOrderStatus(request, response);
                    break;
                default:
                    listOrders(request, response);
                    break;
            }
        } catch (Exception ex) {
            // Notification error
            request.setAttribute("errorMessage", "Database error: " + ex.getMessage());
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }

    // View All Lists Orders
    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get filter parameters by Status
        String status = request.getParameter("status");
        // Filter by paymentMethod
        String paymentMethod = request.getParameter("paymentMethod");
        // Get search by name, id, email
        String search = request.getParameter("search");
        // Pagination
        int page = 1;
        int pageSize = 2;
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
        List<Order> orders;
        int totalOrders;

        if (search != null && !search.trim().isEmpty()) {
            // If there's a search term, use search with payment method and status
            orders = orderDAO.searchOrders(search, status, paymentMethod, page, pageSize);
            totalOrders = orderDAO.getTotalSearchResults(search, status, paymentMethod);
        } else {
            // If no search, use filters
            orders = orderDAO.findOrdersWithFilters(status, paymentMethod, page, pageSize);
            // count order
            totalOrders = orderDAO.getTotalFilteredOrders(status, paymentMethod);
        }
        // Number of page can have
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);
        HashMap<Integer, Account> AccountMap = new HashMap<>();
        for (Order order : orders) {
            Account acc = accDAO.findById(order.getUser_id());
            AccountMap.put(order.getUser_id(), acc);
        }
        // Set attributes
        request.setAttribute("orders", orders);
        request.setAttribute("AccountMap", AccountMap);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("status", status);
        request.setAttribute("search", search);
        // Forword to the order list page

        request.getRequestDispatcher("/view/seller/order-list.jsp").forward(request, response);

    }

    // Update status
    private void updateOrderStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Get orderId by update
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            // Get new Status
            String newStatus = request.getParameter("newStatus");
            // Get note of seller for order
            String note = request.getParameter("note");

            // Validate input status
            if (newStatus == null || newStatus.trim().isEmpty()) {
                request.getSession().setAttribute("errorMessage", "Status cannot be empty");
                response.sendRedirect(request.getContextPath() + "/seller/manage-order?action=view&id=" + orderId);
                return;
            }
            // Get seller ID from Session
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

            if (acc == null) {
                // response.sendRedirect(request.getContextPath() + "/authen");
                // return;
            }

            Order order = orderDAO.findById(orderId);
            if (order == null) {
                session.setAttribute("errorMessage", "Order not found");
                response.sendRedirect(request.getContextPath() + "/seller/manage-order");
                return;
            }

            // Get current status for message
            String oldStatus = order.getStatus();

            // Log gỡ rối
            System.out.println("DEBUG: update order #" + orderId + " from '" + oldStatus + "' to '" + newStatus + "'");

            boolean update = orderDAO.updateOrderStatus(orderId, newStatus, 21, note);

            if (update) {
                // If order is being accepted
                if ("accepted".equals(newStatus) && !"accepted".equals(oldStatus)) {
                    String statusText = "";
                    switch (newStatus) {
                        case "accepted":
                            statusText = "accepted";
                            break;
                        case "completed":
                            statusText = "completed";
                            break;
                        case "cancelled":
                            statusText = "cancelled";
                            break;
                        default:
                            statusText = newStatus;
                    }
                    session.setAttribute("successMessage",
                            "Order #" + orderId + " has been " + statusText + " successfully.");
                }

            } else {
                session.setAttribute("errorMessage", "Failed to update order status. Please try again.");

            }
            // Redirect to the order detail page
            response.sendRedirect(request.getContextPath() + "/seller/manage-order?action=view&id=" + orderId);
        } catch (Exception e) {
        }
    }

    // View Order Detail
    private void viewOrderDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        try {
            // Get orderId by order parameter
            int orderId = Integer.parseInt(request.getParameter("id"));
            // get order findById of orderId
            Order order = orderDAO.findById(orderId);
            Account acc = accDAO.findById(order.getUser_id());

            List<OrderItem> orderItems = itemDAO.getOrderItemsByOrderId(order.getId());
            HashMap<Integer, Food> OrderItemMap = new HashMap<>();
            for (OrderItem orderItem : orderItems) {
                Food food = foodDAO.findById(orderItem.getFood_id());
                OrderItemMap.put(orderItem.getFood_id(), food);
            }

            // get list approvals of seller
            List<OrderApproval> approvals = approvalDAO.getOrderApprovalsByOrderId(orderId);
            HashMap<Integer, Account> OrderApprovalMap = new HashMap<>();
            for (OrderApproval orderApproval : approvals) {
                Account accApproval = accDAO.findById(orderApproval.getApproved_by());
                OrderApprovalMap.put(orderApproval.getApproved_by(), accApproval);
            }
            // check existing order
            if (order == null) {
                request.setAttribute("errorMessage", "Order not found with ID: " + orderId);
                request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
                return;
            }
            request.setAttribute("order", order);
            request.setAttribute("account", acc);
            request.setAttribute("OrderItems", orderItems);
            request.setAttribute("OrderItemMap", OrderItemMap);
            request.setAttribute("OrderApprovalMap", OrderApprovalMap);
            request.setAttribute("approvals", approvals);
            //     Forward to the order detail page
            // PrintWriter out = response.getWriter();
            // out.print(order);

            request.getRequestDispatcher("/view/seller/order-detail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid order ID format");
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }

    }

}
