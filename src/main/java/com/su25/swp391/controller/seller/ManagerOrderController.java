
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
import com.su25.swp391.utils.EmailUtils;
import jakarta.mail.MessagingException;

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
    // Declare properties for DAO 
    private OrderDAO orderDAO;
    private OrderApprovalDAO approvalDAO;
    private OrderItemDAO itemDAO;
    private AccountDAO accDAO = new AccountDAO();
    private CouponDAO couponDAO;
    private FoodDAO foodDAO;
    // Method in Servlet Container call only 1 time in lifecycle
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
        // If action == null
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
                case "viewUpdate":
                    viewUpdate(request, response);
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

    // View Orders List
    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        // Get the seller account from session
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

        // Check if the seller is logged in
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        // Get sort parameter
        String sort = request.getParameter("sort");
        // Get filter parameters by Status
        String status = request.getParameter("status");
        // Filter by paymentMethod
        String paymentMethod = request.getParameter("paymentMethod");
        // Filter by payment Status : Paid or Unpaid
        String paymentStatusParam = request.getParameter("paymentStatus");
        int paymentStatus = -1;
        if(paymentStatusParam != null && !paymentStatusParam.isEmpty()){
            paymentStatus = Integer.parseInt(paymentStatusParam);
        }else{
            paymentStatus = -1;
        }
        // Get search by name, id, email
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
        List<Order> orders;
        int totalOrders;

        if (search != null && !search.trim().isEmpty()) {
            // If there's a search term, use search with payment method and status
            orders = orderDAO.searchOrders(sort, search, status, paymentMethod, page, pageSize);
            // count order
            totalOrders = orderDAO.getTotalSearchResults(search, status, paymentMethod);
        } else {
            // If no search, use filters
            orders = orderDAO.findOrdersWithFilters(sort, status, paymentMethod,paymentStatus , page, pageSize);
            // count order
            totalOrders = orderDAO.getTotalFilteredOrders(status, paymentMethod, paymentStatus);
        }
        // Number of page can have
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);
        // AccountMap get key with account_id and value is Account find by account_id
        HashMap<Integer, Account> AccountMap = new HashMap<>();
        for (Order order : orders) {
            // Find Account by id
            Account acc = accDAO.findById(order.getAccount_id());
            //  key with account_id and value is Account find by account_id
            AccountMap.put(order.getAccount_id(), acc);
        }
        // Set attributes
        request.setAttribute("orders", orders);
        request.setAttribute("AccountMap", AccountMap);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("sort", sort);
        request.setAttribute("status", status);
        request.setAttribute("paymentMethod",paymentMethod);
        request.setAttribute("search", search);
        request.setAttribute("paymentStatus", paymentStatus);
        // Forword to the order list page
        request.getRequestDispatcher("/view/seller/order-list.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Handle invalid order ID format
            request.setAttribute("errorMessage", "Invalid order ID format");
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }

    /**
     * Handles updating the status of a specific order.
     *
     * - Validates input parameters. - Updates the order status in the database.
     * - Records the seller's approval note. - Sends an email notification to
     * guest customers (if applicable). - Sets success or error messages for
     * user feedback.
     *
     * @param request The HttpServletRequest containing client request data.
     * @param response The HttpServletResponse used to redirect or respond.
     */
    private void updateOrderStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Get the orderId from request parameter
            int orderId = Integer.parseInt(request.getParameter("orderId"));

            // Get the new status from request
            String newStatus = request.getParameter("newStatus");

            // Get seller's note from request
            String note = request.getParameter("note");

            // Validate that the new status is not empty
            if (newStatus == null || newStatus.trim().isEmpty()) {
                request.getSession().setAttribute("errorMessage", "Status cannot be empty");
                response.sendRedirect(request.getContextPath() + "/seller/manage-order?action=view&id=" + orderId);
                return;
            }

            // Get the seller account from session
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

            // Check if the seller is logged in
            if (acc == null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }

            // Retrieve the order from the database
            Order order = orderDAO.findById(orderId);
            if (order == null) {
                session.setAttribute("errorMessage", "Order not found");
                response.sendRedirect(request.getContextPath() + "/seller/manage-order");
                return;
            }

            // Store the old status for logging
            String oldStatus = order.getStatus();
            System.out.println("DEBUG: update order #" + orderId + " from '" + oldStatus + "' to '" + newStatus + "'");

            // Update the order status
            boolean update = orderDAO.updateOrderStatus(orderId, newStatus, acc.getId(), note);

            if (update) {
                // If the order was just accepted, show success message
                if ("accepted".equals(newStatus) && !"accepted".equals(oldStatus)) {
                    String statusText = newStatus;
                    switch (newStatus) {
                        case "accepted":
                            statusText = "accepted";
                            break;
                        case "cancelled":
                            statusText = "cancelled";
                            break;
                        case "abc":
                            statusText= newStatus;
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

            // If this is a guest order (account_id = 0), send an email notification
            if (order.getAccount_id() == 0) {
                String customerEmail = order.getEmail();
                String customerName = order.getFull_name();
                String subject = "Order Status Update";
                String content = "<h3>Hello " + customerName + ",</h3>"
                        + "<p>Your order has been <strong>" + newStatus + "</strong>.</p>"
                        + "<p>Thank you for shopping at Healthy Food Store!</p>"
                        + "<br><em>Best regards,</em><br>Customer Support Team";
                try {
                    EmailUtils.sendMail(customerEmail, subject, content);
                } catch (MessagingException ex) {
                    ex.printStackTrace(); // Consider replacing with logger
                }
            }

            // Redirect to the order update detail page
            response.sendRedirect(request.getContextPath() + "/seller/manage-order?action=viewUpdate&id=" + orderId);
        } catch (Exception e) {
            // Handle unexpected exceptions
            e.printStackTrace(); // Consider replacing with logger
        }
    }

    /**
     * Handles displaying the detailed information of a specific order.
     * This method: - Retrieves the order by its ID from the request parameter.
     * - Loads the related account and order items. - Maps food details for each
     * order item. - Forwards the data to the order detail JSP page for
     * rendering. - Handles invalid or missing order cases.
     * @param request The HttpServletRequest containing client request data
     * @param response The HttpServletResponse for sending the response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws SQLException if a database access error occurs
     */
    private void viewOrderDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            // Get the seller account from session
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

            // Check if the seller is logged in
            if (account == null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
            // Get orderId from the "id" request parameter
            int orderId = Integer.parseInt(request.getParameter("id"));

            // Retrieve the order by its ID
            Order order = orderDAO.findById(orderId);

            // If the order does not exist, forward to error page
            if (order == null) {
                request.setAttribute("errorMessage", "Order not found with ID: " + orderId);
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

            // Forward to the order detail JSP page
            request.getRequestDispatcher("/view/seller/order-detail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Handle invalid order ID format
            request.setAttribute("errorMessage", "Invalid order ID format");
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }
    /**
     * Handles displaying the update status page for a specific order.
     *
     * This method: - Retrieves the order based on the ID provided in the
     * request. - Loads related approval records and their associated accounts.
     * - Passes the data to the JSP page for rendering the update interface. -
     * Handles cases where the order ID is invalid or the order is not found.
     *
     * @param request The HttpServletRequest object containing client request
     * data.
     * @param response The HttpServletResponse object for sending the response
     * to the client.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the seller account from session
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

            // Check if the seller is logged in
            if (account == null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
            // Get orderId from the "id" request parameter
            int orderId = Integer.parseInt(request.getParameter("id"));

            // Retrieve the order by its ID
            Order order = orderDAO.findById(orderId);

            // If the order does not exist, forward to error page
            if (order == null) {
                request.setAttribute("errorMessage", "Order not found with ID: " + orderId);
                request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
                return;
            }

            // Retrieve approval records related to this order
            List<OrderApproval> approvals = approvalDAO.getOrderApprovalsByOrderId(orderId);

            // Map each approver's account information
            HashMap<Integer, Account> OrderApprovalMap = new HashMap<>();
            for (OrderApproval orderApproval : approvals) {
                Account accApproval = accDAO.findById(orderApproval.getApproved_by());
                OrderApprovalMap.put(orderApproval.getApproved_by(), accApproval);
            }

            // Set attributes for JSP rendering
            request.setAttribute("order", order);
            request.setAttribute("OrderApprovalMap", OrderApprovalMap);
            request.setAttribute("approvals", approvals);

            // Forward to the update order status JSP page
            request.getRequestDispatcher("/view/seller/update-order-status.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle invalid order ID format or unexpected errors
            request.setAttribute("errorMessage", "Invalid order ID format");
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }

}
