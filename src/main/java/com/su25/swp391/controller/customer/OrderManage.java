package com.su25.swp391.controller.customer;

import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.OrderItem;
import com.su25.swp391.entity.Order;
import com.su25.swp391.entity.Food;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "OrderManage", urlPatterns = {"/orderlist", "/orderdetail", "/search", "/cancel-order"})
public class OrderManage extends HttpServlet {

    private final AccountDAO accountDAO = new AccountDAO();
    private final OrderDAO orderListDAO = new OrderDAO();
    private final OrderItemDAO orderDetailDAO = new OrderItemDAO();
    private final FoodDAO foodDAO = new FoodDAO();

    private final Order order = new Order();

    private static final String ORDER_LIST = "view/customer/orderlist.jsp";
    private static final String ORDER_DETAILS = "view/customer/orderdetail.jsp";
    private static final String HOME_PAGE = "view/homePage/home.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/orderdetail":
                showOrderDetail(request, response);
                break;
            case "/orderlist":
                orderPage(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/search":
                searchDoPost(request, response);
                break;
            case "/cancel-order":
                cancerOrderDoPost(request, response);
                break;
            default:
                break;
        }
    }

    private void showOrderDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String orderIdStr = request.getParameter("order_id");

        if (orderIdStr == null || orderIdStr.isEmpty()) {
            response.sendRedirect(HOME_PAGE);
            return;
        }

        try {
            int orderId = Integer.parseInt(orderIdStr);
            List<OrderItem> orderDetails = orderDetailDAO.getOrderDetailByOrderId(orderId);
            Order order = orderListDAO.getOrderById(orderId);

            if (orderDetails != null && !orderDetails.isEmpty() && order != null) {
                List<OrderViewModel> viewModels = new ArrayList<>();

                for (OrderItem item : orderDetails) {
                    List<Food> foods = foodDAO.getFoodByFoodIdFromOrderItems(item.getFood_id());
                    if (foods != null && !foods.isEmpty()) {
                        Food food = foods.get(0);
                        viewModels.add(new OrderViewModel(food, order, item));
                    }
                }
                BigDecimal totalprice = orderDetailDAO.calculateSubtotal(orderDetails);
                request.setAttribute("totalprice", totalprice);
                request.setAttribute("orderViews", viewModels);
                request.setAttribute("order", order);

                request.getRequestDispatcher(ORDER_DETAILS).forward(request, response);
            } else {
                response.sendRedirect(ORDER_LIST);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(HOME_PAGE);
        }
    }

    private void searchDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String search = request.getParameter("status");
        String email = (String) session.getAttribute("email");
        String username = (String) session.getAttribute("user_name");

        if (email == null) {
            response.sendRedirect(HOME_PAGE);
            return;
        }

        Account acc = Account.builder().email(email).build();
        Account accountFoundByEmail = accountDAO.findByEmail(acc);
        Account accountFoundByUsername = accountDAO.findByUsername(acc);

        if (accountFoundByEmail != null) {
            int userId = accountFoundByEmail.getId();
            List<Order> orderList = orderListDAO.searchOrderListByUserIdAndStatus(userId, search);
            request.setAttribute("orderList", orderList);
        } else if (accountFoundByUsername != null) {
            int userId = accountFoundByUsername.getId();
            List<Order> orderList = orderListDAO.searchOrderListByUserIdAndStatus(userId, search);
            request.setAttribute("orderList", orderList);
        }

        request.getRequestDispatcher(ORDER_LIST).forward(request, response);
    }

    private void cancerOrderDoPost(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        Order order = orderListDAO.getOrderById(orderId);
        if (!"pending".equalsIgnoreCase(order.getStatus())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
            return;
        }

        boolean updated = orderListDAO.updateOrderStatus(orderId, "cancelled");
        if (updated) {
            response.setStatus(HttpServletResponse.SC_OK); // 200
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
        }
    }

    private void orderPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String username = (String) session.getAttribute("user_name");
        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");
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
            List<Order> orderList = orderListDAO.findOrdersByUserIdWithPagination(userId, currentPage, pageSize);
            int totalOrder = orderListDAO.getTotalOrderCountByUserId(userId);
            int totalPages = (int) Math.ceil((double) totalOrder / pageSize);

            // Thiết lập các thuộc tính cho JSP
            request.setAttribute("orderList", orderList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("totalOrder", totalOrder);

            // Gán vào session để sử dụng khi quay lại
            session.setAttribute("currentPage", currentPage);
            session.setAttribute("pageSize", pageSize);

            // Tính toán phạm vi hiển thị
            int startRecord = (currentPage - 1) * pageSize + 1;
            int endRecord = Math.min(startRecord + pageSize - 1, totalOrder);
            request.setAttribute("startRecord", startRecord);
            request.setAttribute("endRecord", endRecord);

            request.getRequestDispatcher(ORDER_LIST).forward(request, response);
        } else {
            response.sendRedirect(HOME_PAGE);
        }

    }

}
