/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.customer;

import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.FeedbackDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Feedback;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.Order;
import com.su25.swp391.entity.OrderItem;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author kieud
 */
@WebServlet(name = "FeedbackManage", urlPatterns = {"/feedback", "/feedbackdetail", "/createfeedback", "/remove"})
public class FeedbackManage extends HttpServlet {

    private final AccountDAO accountDAO = new AccountDAO();
    private final OrderDAO orderListDAO = new OrderDAO();
    private final OrderItemDAO orderDetailDAO = new OrderItemDAO();
    private final FoodDAO foodDAO = new FoodDAO();
    private final FeedbackDAO feedbackDAO = new FeedbackDAO();

    private final Order order = new Order();

    private static final String ORDER_LIST = "view/customer/orderlist.jsp";
    private static final String ORDER_DETAILS = "view/customer/orderdetail.jsp";
    private static final String HOME_PAGE = "view/homePage/home.jsp";
    private static final String FEEDBACK_PAGE = "view/customer/feedbacklist.jsp";
    private static final String CREATE_PAGE = "view/customer/createfeedback.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/feedback":
                showFeedback(request, response);
                break;
            case "/createfeedback":
                request.getRequestDispatcher(CREATE_PAGE).forward(request, response);
                break;
            case "/remove":
                removeFeedback(request, response);
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
            case "/createfeedback":
                createfeedback(request, response);
                break;
            default:
                break;
        }
    }

    private void showFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String search = request.getParameter("rating");
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
        // phân trang
        try {
            // khi page == null. Gán giá trị mặc định là 1
            if (pageParam != null && !pageParam.isEmpty()) {
                currentPage = Integer.parseInt(pageParam);
                if (currentPage < 1) {
                    currentPage = 1;
                }
            }
            // page size đã mặc định cho web chỉ xuất hiện 10 sản phẩm
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

        Account acc = Account.builder().email(email).user_name(username).build();

        Account findAccountbyEmail = accountDAO.findByEmail(acc);
        Account findAccountbyUsername = accountDAO.findByUsername(acc);

        if (findAccountbyEmail != null || findAccountbyUsername != null) {
            int userId = (findAccountbyEmail != null) ? findAccountbyEmail.getId() : findAccountbyUsername.getId();
            List<Feedback> feedbacklist = feedbackDAO.feedbackByUserIdAndRatingWithPagination(userId, search, currentPage, pageSize);

            // Duyệt qua toàn bộ orderItemList, lấy food_id từ mỗi OrderItem, và cho vào một Set<Integer> để loại bỏ trùng lặp
            Set<Integer> orderItemIds = feedbacklist.stream()
                    .map(Feedback::getOrder_item_id)
                    .collect(Collectors.toSet());

            //Tạo một Map<Integer, Food> tên là foodMap để ánh xạ foodId → Food.
            Map<Integer, Food> foodMap = new HashMap<>();
            // Duyệt qua từng orderItemId trong Set, gọi orderDetailDAO.findById(orderItemId) để lấy đối tượng OrderItem từ CSDL.
            for (Integer orderItemId : orderItemIds) {
                OrderItem orderItem = orderDetailDAO.findById(orderItemId);
                // nếu orderItem tồn tại thì tìm nó trong bảng food
                if (orderItem != null) {
                    Food food = foodDAO.findById(orderItem.getFood_id());
                    // nếu tìm thấy trong bẳng food Gộp lại thành một Map<food_id, Food> để dễ tra cứu trong JSP
                    if (food != null) {
                        foodMap.put(orderItemId, food);
                    }
                }
            }

            request.setAttribute("foodMap", foodMap);

            int totalFeedback = feedbackDAO.getTotalFeedbackCountByUserIdAndRating(userId, search);
            // lấy tổng số lượng order chia cho kích thức trang (10) để biết tổng số trang
            int totalPages = (int) Math.ceil((double) totalFeedback / pageSize);

            // Thiết lập các thuộc tính cho JSP
            request.setAttribute("feedbacklist", feedbacklist);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("totalFeedback", totalFeedback);

            // Gán vào session để sử dụng khi quay lại
            session.setAttribute("currentPage", currentPage);
            session.setAttribute("pageSize", pageSize);

            // Tính toán phạm vi hiển thị
            int startRecord = (currentPage - 1) * pageSize + 1;
            int endRecord = Math.min(startRecord + pageSize - 1, totalFeedback);
            request.setAttribute("startRecord", startRecord);
            request.setAttribute("endRecord", endRecord);

            request.getRequestDispatcher(FEEDBACK_PAGE).forward(request, response);
            return;
        } else {
            response.sendRedirect(HOME_PAGE);
        }
    }

    private void createfeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("email");
        String username = (String) session.getAttribute("user_name");
        String source = request.getParameter("source"); // dùng để thực hiệc việc back lại trang feedback list
        String ratingraw = request.getParameter("rating");
        String content = request.getParameter("feedbackText");
        String orderitem = request.getParameter("order_item_id");
        String orderIdParam = (String) session.getAttribute("orderIdStr");
        int orderItemId = Integer.parseInt(orderitem);
        int orderID = Integer.parseInt(orderIdParam);
        int rating = Integer.parseInt(ratingraw);

        if (email == null && username == null) {
            response.sendRedirect(HOME_PAGE);
            return;
        }

        Account acc = Account.builder().email(email).user_name(username).build();
        Account findByEmail = accountDAO.findByEmail(acc);
        Account findByUsername = accountDAO.findByUsername(acc);

        if (findByEmail != null || findByUsername != null) {
            int userId = (findByEmail != null) ? findByEmail.getId() : findByUsername.getId();
            Feedback feedback = Feedback.builder()
                    .user_id(userId)
                    .order_item_id(orderItemId)
                    .content(content)
                    .rating(rating)
                    .isVisible(true)
                    .build();
            Feedback checkfeedback = feedbackDAO.findByUserIdAndOrderItemId(userId, orderItemId);
            boolean isFeedbackExists = (checkfeedback != null);
            if (checkfeedback == null) {
                feedbackDAO.insert(feedback);
                if ("orderdetail".equals(source) && orderIdParam != null && !orderIdParam.isEmpty()) {
                    response.sendRedirect("orderdetail?order_id=" + orderIdParam);
                } else if ("feedback".equals(source)) {
                    response.sendRedirect("feedback");
                } else {
                    response.sendRedirect("order");
                }
            } else {
                request.setAttribute("isFeedbackExists", isFeedbackExists);
                request.setAttribute("order_item_id", orderItemId);
                feedbackDAO.updateByUserIdAndOrderItemId(userId, orderItemId, content, rating);
                if ("orderdetail".equals(source) && orderIdParam != null && !orderIdParam.isEmpty()) {
                    response.sendRedirect("orderdetail?order_id=" + orderIdParam);
                } else if ("feedback".equals(source)) {
                    response.sendRedirect("feedback");
                } else {
                    response.sendRedirect("order");
                }
            }
        } else {
            response.sendRedirect(HOME_PAGE);
        }
    }

    private void removeFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String idfeedbackraw = request.getParameter("id");
        int idfeedback = Integer.parseInt(idfeedbackraw);

        String email = (String) session.getAttribute("email");
        String username = (String) session.getAttribute("user_name");

        if (email == null && username == null) {
            response.sendRedirect(HOME_PAGE);
        }

        Account account = Account.builder().email(email).user_name(username).build();

        Account accFindEmail = accountDAO.findByEmail(account);
        Account accFindUsername = accountDAO.findByUsername(account);

        if (accFindEmail != null || accFindUsername != null) {
            int userID = (accFindEmail != null) ? accFindEmail.getId() : accFindUsername.getId();
            Feedback feedback = Feedback.builder().id(idfeedback).user_id(userID).isVisible(false).build();
            feedbackDAO.update(feedback);
            response.sendRedirect("feedback"); // tái sử dụng logic của url /feedback
        } else {
            response.sendRedirect(HOME_PAGE);
        }
    }

}
