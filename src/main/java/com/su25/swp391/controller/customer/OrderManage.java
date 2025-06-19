package com.su25.swp391.controller.ordercustommer;

import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.OrderListDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.OrderList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "OrderManage", urlPatterns = {"/orderlist", "/orderdetail"})
public class OrderManage extends HttpServlet {

    private final AccountDAO accountDAO = new AccountDAO();
    private final OrderListDAO orderListDAO = new OrderListDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/orderlist":
                showOrderList(request, response);
                break;
//            case "/orderdetail":
//                showOrderDetail(request, response);
//                break;
        }
    }

    private void showOrderList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        if (email == null) {
            response.sendRedirect("view/homePage/home.jsp");
            return;
        }

        Account acc = Account.builder().email(email).build();
        Account fullAcc = accountDAO.findByEmail(acc);

        if (fullAcc != null) {
            int userId = fullAcc.getId();
            List<OrderList> orderList = orderListDAO.getOrderListByUserId(userId);
            request.setAttribute("orderList", orderList);
        }

        request.getRequestDispatcher("view/customer/orderlist.jsp").forward(request, response);
    }

//    private void showOrderDetail(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String orderIdStr = request.getParameter("orderId");
//
//        if (orderIdStr == null || orderIdStr.isEmpty()) {
//            response.sendRedirect("view/homePage/home.jsp");
//            return;
//        }
//
//        try {
//            int orderId = Integer.parseInt(orderIdStr);
//
//            // Lấy tất cả đơn hàng từ session người dùng
//            HttpSession session = request.getSession();
//            String email = (String) session.getAttribute("email");
//            Account acc = Account.builder().email(email).build();
//            Account fullAcc = accountDAO.findByEmail(acc);
//
//            if (fullAcc != null) {
//                List<OrderList> allOrders = orderListDAO.getOrderListByUserId(fullAcc.getId());
//
//                // Lọc ra đơn hàng có id tương ứng
//                List<OrderList> orderDetails = allOrders.stream()
//                        .filter(o -> o.getOrderId()) == orderId)
//                        .collect(Collectors.toList());
//
//                if (orderDetails.isEmpty()) {
//                    request.setAttribute("message", "Order not found!");
//                } else {
//                    request.setAttribute("orderItem", orderDetails); // dùng trong JSP
//                }
//
//                request.getRequestDispatcher("/view/customer/orderdetail.jsp").forward(request, response);
//            }
//
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            request.setAttribute("message", "Invalid order ID.");
//            request.getRequestDispatcher("/view/customer/orderdetail.jsp").forward(request, response);
//        }
//    }
}
