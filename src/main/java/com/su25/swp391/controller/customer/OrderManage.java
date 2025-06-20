package com.su25.swp391.controller.customer;

import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.OrderDetailDAO;
import com.su25.swp391.dal.implement.OrderListDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.OrderDetails;
import com.su25.swp391.entity.OrderList;
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

@WebServlet(name = "OrderManage", urlPatterns = {"/orderlist", "/orderdetail", "/search", "/hide-order"})
public class OrderManage extends HttpServlet {

    private final AccountDAO accountDAO = new AccountDAO();
    private final OrderListDAO orderListDAO = new OrderListDAO();
    private final OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
    
    private static final String ORDER_LIST = "view/customer/orderlist.jsp";
    private static final String ORDER_DETAILS = "view/customer/orderlist.jsp";
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
           case "/hide-order":
                hideorderDoPost(request, response);
                break;
             default:
                break;
        }
    }

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
        Account accountFoundByEmail= accountDAO.findByEmail(acc);
        Account accountFoundByUsername = accountDAO.findByUsername(acc);

        if (accountFoundByEmail != null ) {
            int userId = accountFoundByEmail.getId();
            List<OrderList> orderList = orderListDAO.getOrderListByUserId(userId);
            request.setAttribute("orderList", orderList);
        }else if(accountFoundByUsername != null) {
            int userId = accountFoundByUsername.getId();
            List<OrderList> orderList = orderListDAO.getOrderListByUserId(userId);
            request.setAttribute("orderList", orderList);
        }
                

        request.getRequestDispatcher(ORDER_LIST).forward(request, response);
    }

    private void showOrderDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderIdStr = request.getParameter("orderId");

        if (orderIdStr == null || orderIdStr.isEmpty()) {
            response.sendRedirect(HOME_PAGE);
            return;
        }

        int orderId = Integer.parseInt(orderIdStr);
        List<OrderDetails> orderDetails = orderDetailDAO.getOrderDetailByOrderId(orderId);

        if (orderDetails != null && !orderDetails.isEmpty()) {
            request.setAttribute("orderDetails", orderDetails);
            BigDecimal totalprice = orderDetailDAO.calculateSubtotal(orderDetails);
            request.setAttribute("totalprice", totalprice);
            request.getRequestDispatcher(ORDER_DETAILS).forward(request, response);
        } else {
            response.sendRedirect(ORDER_LIST);
        }
    }

    private void searchDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String search = request.getParameter("name");
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
            List<OrderList> orderList = orderListDAO.searchOrderListByUserIdAndKeyword(userId, search);
            request.setAttribute("orderList", orderList);
        }else if(accountFoundByUsername != null){
            int userId = accountFoundByUsername.getId();
            List<OrderList> orderList = orderListDAO.searchOrderListByUserIdAndKeyword(userId, search);
            request.setAttribute("orderList", orderList);
        }
                
        request.getRequestDispatcher(ORDER_LIST).forward(request, response);
    }

    private void hideorderDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         int orderItemId = Integer.parseInt(request.getParameter("orderItemId"));
        HttpSession session = request.getSession();

        List<Integer> hiddenOrderIds = (List<Integer>) session.getAttribute("hiddenOrderIds");
        if (hiddenOrderIds == null) {
            hiddenOrderIds = new ArrayList<>();
        }

        if (!hiddenOrderIds.contains(orderItemId)) {
            hiddenOrderIds.add(orderItemId);
        }

        session.setAttribute("hiddenOrderIds", hiddenOrderIds);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
