package com.su25.swp391.controller.customer;

import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.OrderItem;
import com.su25.swp391.entity.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        //lay tham so order id khi nguoi dung chon 
        String orderIdStr = request.getParameter("order_id");

        if (orderIdStr == null || orderIdStr.isEmpty()) {
            response.sendRedirect(HOME_PAGE);
            return;
        }

        try {
            //parse sang int
            int orderId = Integer.parseInt(orderIdStr);

            //get ra list cac OI dua tren order id
            List<OrderItem> orderItemList = orderDetailDAO.findOrderItemsByOrderId(orderId);

            request.setAttribute("orderItemList", orderItemList);
            request.setAttribute("foodDAO", foodDAO);

            // Tạo Map<Integer, Food> từ danh sách food_id có trong orderItemList
            Set<Integer> foodIds = orderItemList.stream()
                    .map(OrderItem::getFood_id)
                    .collect(Collectors.toSet());

            Map<Integer, Food> foodMap = new HashMap<>();
            for (Integer foodId : foodIds) {
                Food food = foodDAO.findById(foodId);
                if (food != null) {
                    foodMap.put(foodId, food);
                }
            }

            //  Gọi hàm tính subtotal
            BigDecimal subtotal = calculateSubtotal(orderItemList, foodMap);
            request.setAttribute("subtotal", subtotal); // đưa xuống JSP
            

            Order order = orderListDAO.getOrderById(orderId);

            if (order != null) {
                request.setAttribute("order", order);
                request.getRequestDispatcher(ORDER_DETAILS).forward(request, response);
            } else {
             //   response.sendRedirect(ORDER_LIST);
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

    public BigDecimal calculateSubtotal(List<OrderItem> orderDetails, Map<Integer, Food> foodMap) {
        BigDecimal subtotal = BigDecimal.ZERO;

        for (OrderItem od : orderDetails) {
            Food food = foodMap.get(od.getFood_id());
            if (food != null && food.getPrice() != null) {
                BigDecimal foodPrice = BigDecimal.valueOf(food.getPrice()); // convert từ Double
                BigDecimal itemTotal = foodPrice.multiply(BigDecimal.valueOf(od.getQuantity()));
                subtotal = subtotal.add(itemTotal);
            }
        }

        return subtotal;
    }

}
