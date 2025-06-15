/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.ordercustommer;

import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;
import com.su25.swp391.entity.Account;
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
import java.util.List;

/**
 *
 * @author kieud
 */
@WebServlet(name = "OrderManage", urlPatterns = {"/orderlist", "/orderdetail"})
public class OrderManage extends HttpServlet {

    AccountDAO accountDAO = new AccountDAO();
    OrderDAO orderDAO = new OrderDAO();
    OrderItemDAO orderitemDAO = new OrderItemDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/orderlist":
                HttpSession session = request.getSession();

                String email = (String) session.getAttribute("email");
                if (email == null) {
                    response.sendRedirect("view/homePage/home.jsp"); // hoặc trang lỗi
                    return;
                }

                Account acc = Account.builder().email(email).build();
                Account fullAcc = accountDAO.findByEmail(acc);

                if (fullAcc != null) {
                    int userID = fullAcc.getId();
                    List<Order> orderList = orderDAO.findOrdersByUserId(userID, null, null, 1, 10);
                    
                    request.setAttribute("orderList", orderList);
                }

                request.getRequestDispatcher("view/customer/orderList.jsp").forward(request, response);
                break;
            case "/orderdetail":
                try {
                // Lấy orderId từ URL
                String orderIdStr = request.getParameter("orderId");
                if (orderIdStr == null || orderIdStr.isEmpty()) {
                    response.sendRedirect("view/homePage/home.jsp"); // hoặc set message lỗi
                    return;
                }

                int orderId = Integer.parseInt(orderIdStr);

                // Lấy chi tiết đơn hàng từ DAO
                List<OrderItem> orderItem = orderitemDAO.findByOrderId(orderId);

                if (orderItem == null) {
                    request.setAttribute("message", "Order not found!");
                    request.getRequestDispatcher("/view/customer/orderDetail.jsp").forward(request, response);
                    return;
                }

                // Đẩy dữ liệu sang JSP
                request.setAttribute("orderItem", orderItem);
                request.getRequestDispatcher("/view/customer/orderDetail.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "An error occurred while retrieving the order detail.");
                request.getRequestDispatcher("/view/customer/orderDetail.jsp").forward(request, response);
            }
            break;
        }
    }
    

    /* 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String path = request.getServletPath();
//        switch (path) {
//            case "/orderlist":
//                orderlistDoPost(request, response);
//                break;
//        }
//    }
//    private void orderlistDoPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        int userID;
//        Account account = Account.builder()
//                .email((String) session.getAttribute("email"))
//                .build();
//        Account accFoundByUsernamePass = accountDAO.findByEmail(account);
//        if (accFoundByUsernamePass != null) {
//            userID = accFoundByUsernamePass.getId();
//            session.setAttribute("userID", userID);
//            int page = 1;
//            int pageSize = 10;
//            List<Order> orderList = orderDAO.findOrdersByUserId(userID, null, null, page, pageSize);
//            request.setAttribute("orderList", orderList);
//            request.getRequestDispatcher("/view/managerOrderCustommer/orderList.jsp").forward(request, response);
//        } else {
//            session.setAttribute("toastMessage", "The incident has happened");
//            session.setAttribute("toastType", "error");
//        }
//    }
}
