package com.su25.swp391.controller.seller;

import com.su25.swp391.config.GlobalConfig;
import static com.su25.swp391.controller.customer.OrderManage.ORDER_LIST;
import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.ComboDAO;
import com.su25.swp391.dal.implement.DeliveryDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.OrderApprovalDAO;

import com.su25.swp391.dal.implement.OrderComboDAO;

import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Combo;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.Order;
import com.su25.swp391.entity.OrderApproval;
import com.su25.swp391.entity.OrderCombo;
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
import java.math.BigDecimal;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author FPTSHOP
 */
@WebServlet(name = "ManagerOrderController", urlPatterns = {"/manage-ordercombo"})
public class ManagerOrderComboController extends HttpServlet {

    // Declare properties for DAO 
    private final AccountDAO accountDAO = new AccountDAO();
    private final OrderDAO orderListDAO = new OrderDAO();
    private AccountDAO accDAO = new AccountDAO();
    private final FoodDAO foodDAO = new FoodDAO();
    private final OrderComboDAO ordercomboDAO = new OrderComboDAO();
    private final ComboDAO comboDAO = new ComboDAO();
    private OrderApprovalDAO approvalDAO = new OrderApprovalDAO();
    private DeliveryDAO deliveryDAO = new DeliveryDAO();

    private final OrderCombo order = new OrderCombo();
    public static final String ORDER_LIST = "view/seller/order_combo_list.jsp";
    private static final String ORDER_COMBO_DETAILS = "view/seller/order_combo_detail.jsp";
    private static final String HOME_PAGE = "view/homePage/home.jsp";
//    private static final String ORDER_COMBO = "view/customer/ordercombo.jsp";
//    private static final String ORDER_COMBO_DETAILS = "view/customer/ordercombodetail.jsp";

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
                    listOrdersCombo(request, response);
                    break;
                case "view":
                    viewOrderDetail(request, response);
                    break;
                case "viewUpdate":
                    viewUpdate(request, response);
                    break;
                default:
                    listOrdersCombo(request, response);
                    break;
            }
        } catch (Exception ex) {
            // Notification error
            request.setAttribute("errorMessage", "Database error: " + ex.getMessage());
//            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
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
                case "update":
                    updateOrderStatus(request, response);
                    break;
                default:
                    listOrdersCombo(request, response);
                    break;
            }
        } catch (Exception ex) {
            // Notification error
            request.setAttribute("errorMessage", "Database error: " + ex.getMessage());
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }

    // View Orders List
    private void listOrdersCombo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("email");
        String username = (String) session.getAttribute("user_name");

        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");

        String statusFilter = request.getParameter("status"); // status đơn hàng
        String paymentFilter = request.getParameter("payment_status"); // trạng thái thanh toán
        //lay ra all list khi filter all
        if ("all".equals(statusFilter)) {
            statusFilter = null;
        }
        if ("all".equals(paymentFilter)) {
            paymentFilter = null;
        }
        int currentPage = 1;
        int pageSize = 10;

        if (email == null && username == null) {
            response.sendRedirect(HOME_PAGE);
            return;
        }

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

        Account acc = Account.builder()
                .email(email)
                .user_name(username)
                .build();

        Account accountFoundByEmail = accountDAO.findByEmail(acc);
        Account accountFoundByUsername = accountDAO.findByUsername(acc);

        if (accountFoundByEmail != null || accountFoundByUsername != null) {
            int userId = (accountFoundByEmail != null) ? accountFoundByEmail.getId() : accountFoundByUsername.getId();

            List<OrderCombo> orderCombo = ordercomboDAO.findAllWithPaginationAndFilters(
                    currentPage, pageSize, statusFilter, paymentFilter);

            int totalOrder = ordercomboDAO.getOrderComboCountByFilters(statusFilter, paymentFilter);
            int totalPages = (int) Math.ceil((double) totalOrder / pageSize);

            request.setAttribute("orderCombo", orderCombo);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("totalOrder", totalOrder);

            request.setAttribute("statusFilter", statusFilter);
            request.setAttribute("paymentFilter", paymentFilter);

            session.setAttribute("currentPage", currentPage);
            session.setAttribute("pageSize", pageSize);

            int startRecord = (currentPage - 1) * pageSize + 1;
            int endRecord = Math.min(startRecord + pageSize - 1, totalOrder);
            request.setAttribute("startRecord", startRecord);
            request.setAttribute("endRecord", endRecord);

            request.getRequestDispatcher(ORDER_LIST).forward(request, response);
            return;
        } else {
            response.sendRedirect(HOME_PAGE);
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
            int orderComboId = Integer.parseInt(request.getParameter("orderComboId"));

            // Get the new status from request
            String newStatus = request.getParameter("newStatus");

            // Get seller's note from request (optional)
            String note = request.getParameter("note");
            if (note == null) {
                note = ""; // tránh null khi ghép vào nội dung email
            }
            // Validate that the new status is not empty
            if (newStatus == null || newStatus.trim().isEmpty()) {
                request.getSession().setAttribute("errorMessage", "Status cannot be empty");
                response.sendRedirect(request.getContextPath() + "/manage-ordercombo?action=viewUpdate&orderComboId=" + orderComboId);
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
            OrderCombo ordercombo = ordercomboDAO.findById(orderComboId);
            if (ordercombo == null) {
                session.setAttribute("errorMessage", "Order not found");
                response.sendRedirect(request.getContextPath() + "/manage-ordercombo");
                return;
            }

            // Update the order status
            boolean update = ordercomboDAO.updateOrderStatus(orderComboId, newStatus);
            System.out.println("Update order status? " + update);

            // Nếu trạng thái là accepted, cần lấy shipper
            if ("accepted".equalsIgnoreCase(newStatus)) {
                try {
                    Integer idShipper = Integer.parseInt(request.getParameter("idShipper"));
                    System.out.println("=> Insert delivery for shipper id: " + idShipper);
                    boolean checkInsertDelivery = deliveryDAO.insertDeliveryCombo(orderComboId, idShipper);
                    System.out.println("Insert result: " + checkInsertDelivery);
                } catch (NumberFormatException ex) {
                    session.setAttribute("errorMessage", "Please select a valid shipper.");
                    response.sendRedirect(request.getContextPath() + "/manage-ordercombo?action=viewUpdate&orderComboId=" + orderComboId);
                    return;
                }
            }

            if (update) {
                session.setAttribute("successMessage", "Success Order: " + orderComboId);
            } else {
                session.setAttribute("errorMessage", "Failed to update order status. Please try again.");
            }

            // Gửi email nếu là người dùng đăng nhập (user_id != null và != 0)
            Integer userId = ordercombo.getUser_id();
            if (userId != null && userId != 0) {
                Account buyer = accDAO.findById(userId);
                if (buyer != null) {
                    String guestEmail = buyer.getEmail();
                    String customerName = buyer.getFull_name() != null ? buyer.getFull_name() : "";
                    String subject = "Order Status Update";
                    String content = "<h3>Hello " + customerName + ",</h3>"
                            + "<p>Your order has been <strong>" + newStatus + "</strong>.</p>"
                            + (!note.isEmpty() ? "<p>Note: " + note + "</p>" : "")
                            + "<p>Thank you for shopping at Healthy Food Store!</p>"
                            + "<p>Xin quý khách xác nhận lại đã nhận được thông tin đơn hàng bị hủy"
                            + " và liên hệ lại cửa hàng nhận lại tiền hoàn!</p>"
                            + "<p>SDT:0378654625</p>"
                            + "<br><em>Best regards,</em><br>Customer Support Team";
                    try {
                        EmailUtils.sendMail(guestEmail, subject, content);
                    } catch (MessagingException ex) {
                        ex.printStackTrace(); // Consider replacing with logger
                    }
                }
            }

            // Redirect to the order update detail page
            response.sendRedirect(request.getContextPath() + "/manage-ordercombo?action=viewUpdate&orderComboId=" + orderComboId);

        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with logger
        }
    }

    /**
     * Handles displaying the detailed information of a specific order. This
     * method: - Retrieves the order by its ID from the request parameter. -
     * Loads the related account and order items. - Maps food details for each
     * order item. - Forwards the data to the order detail JSP page for
     * rendering. - Handles invalid or missing order cases.
     *
     * @param request The HttpServletRequest containing client request data
     * @param response The HttpServletResponse for sending the response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws SQLException if a database access error occurs
     */
    private void viewOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //lay tham so order id khi nguoi dung chon 
        String comboIdStr = request.getParameter("orderComboId");

        if (comboIdStr == null || comboIdStr.isEmpty()) {
            response.sendRedirect(HOME_PAGE);
            return;
        }
        int comboId = Integer.parseInt(comboIdStr);
        try {
            OrderCombo orderCombo = ordercomboDAO.findById(comboId);
            if (orderCombo != null) {
                int comboID = orderCombo.getComboId();
                Combo combo = comboDAO.findById(comboID);
                if (combo != null) {
                    double subtotalcombo = calculateOriginalTotalPrice(combo, orderCombo);
                    request.setAttribute("orderCombo", orderCombo);
                    request.setAttribute("combo", combo);
                    request.setAttribute("subtotalcombo", subtotalcombo);
                    request.getRequestDispatcher(ORDER_COMBO_DETAILS).forward(request, response);
                    return;
                }
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(HOME_PAGE);
        }
    }

    public double calculateOriginalTotalPrice(Combo combo, OrderCombo orderCombo) { // tính tiền combo
        if (combo == null || orderCombo == null) {
            return 0.0;
        }
        double price = combo.getOriginalPrice();
        int quantity = orderCombo.getQuantity();
        double discountPricer = combo.getDiscountPrice();

        return (price * quantity) - discountPricer;
    }

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
            int orderId = Integer.parseInt(request.getParameter("orderComboId"));

            // Retrieve the order by its ID
            OrderCombo order = ordercomboDAO.findById(orderId);

            // If the order does not exist, forward to error page
            if (order == null) {
                request.setAttribute("errorMessage", "Order not found with ID: " + orderId);
                request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
                return;
            }
            //List Shipper
            List<Account> accShipper = accDAO.findAccountByRole("shipper");

            // Set attributes for JSP rendering
            request.setAttribute("orderCombo", order);
            request.setAttribute("accShipper", accShipper);
            // Forward to the update order status JSP page
            request.getRequestDispatcher("/view/seller/update_status_orderCombo.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle invalid order ID format or unexpected errors
            e.printStackTrace(); // ⬅️ IN RA CONSOLE
            request.setAttribute("errorMessage", "Lỗi: " + e.getMessage());
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }

}
