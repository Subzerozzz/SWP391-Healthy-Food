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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "OrderManage", urlPatterns = {"/orderlist", "/orderdetail", "/search", "/cancel-order", "/order-list"})
public class OrderManage extends HttpServlet {

    private final AccountDAO accountDAO = new AccountDAO();
    private final OrderDAO orderListDAO = new OrderDAO();
    private final OrderItemDAO orderDetailDAO = new OrderItemDAO();
    private final FoodDAO foodDAO = new FoodDAO();

    private static final String ORDER_LIST = "view/customer/orderlist.jsp";
    private static final String ORDER_DETAILS = "view/customer/orderdetail.jsp";
    private static final String HOME_PAGE = "view/homePage/home.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/orderlist":
                showOrderList(request, response);
                break;
            case "/orderdetail":
                showOrderDetail(request, response);
                break;
            case "/order-list":
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

//    private void showOrderList(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String email = (String) session.getAttribute("email");
//        String username = (String) session.getAttribute("user_name");
//
//        if (email == null) {
//            response.sendRedirect(HOME_PAGE);
//            return;
//        }
//
//        Account acc = Account.builder()
//                .email(email)
//                .user_name(username)
//                .build();
//        Account accountFoundByEmail = accountDAO.findByEmail(acc);
//        Account accountFoundByUsername = accountDAO.findByUsername(acc);
//
//        if (accountFoundByEmail != null) {
//            int userId = accountFoundByEmail.getId();
//            List<Order> orderList = orderListDAO.getOrderListByUserId(userId);
//            request.setAttribute("orderList", orderList);
//        } else if (accountFoundByUsername != null) {
//            int userId = accountFoundByUsername.getId();
//            List<Order> orderList = orderListDAO.getOrderListByUserId(userId);
//            request.setAttribute("orderList", orderList);
//        }
//
//        request.getRequestDispatcher(ORDER_LIST).forward(request, response);
//    }
    
     private void showOrderList(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    String email = (String) session.getAttribute("email");
    String username = (String) session.getAttribute("user_name");

    if (email == null) {
        response.sendRedirect(HOME_PAGE);
        return;
    }

    Account acc = Account.builder()
            .email(email)
            .user_name(username)
            .build();
    Account accountFoundByEmail = accountDAO.findByEmail(acc);
    Account accountFoundByUsername = accountDAO.findByUsername(acc);

    int userId = -1;
    if (accountFoundByEmail != null) {
        userId = accountFoundByEmail.getId();
        List<Order> orderList = orderListDAO.getOrderListByUserId(userId);
    } else if (accountFoundByUsername != null) {
        userId = accountFoundByUsername.getId();
        List<Order> orderList = orderListDAO.getOrderListByUserId(userId);
    }

    if (userId != -1) {
        // Get pagination parameters from request
        int page = 1;
        int pageSize = 10;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("pageSize") != null) {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }

        // Get paginated order list
        List<Order> orderList = orderListDAO.findAllWithPagination( page, pageSize);
        int totalOrders = orderListDAO.getTotalOrderCount();
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

        request.setAttribute("orderList", orderList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageSize", pageSize);
    }

    request.getRequestDispatcher(ORDER_LIST).forward(request, response);
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

    private void orderPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int currentPage = 1;
        int pageSize = 5;

        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
                if (currentPage <= 0) {
                    currentPage = 1;
                }
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }

        // Gọi DAO để lấy danh sách đơn theo trang
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orderList = orderDAO.findAllWithPagination(currentPage, pageSize);
        int totalOrders = orderDAO.getTotalOrderCount();
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

        // Gửi sang JSP
        request.setAttribute("orderList", orderList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher(ORDER_LIST).forward(request, response);
    }

}
