///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package com.su25.swp391.controller.homePage;
//
//import com.su25.swp391.config.GlobalConfig;
//import com.su25.swp391.dal.implement.ComboDAO;
//import com.su25.swp391.dal.implement.ComboFoodDAO;
//import com.su25.swp391.dal.implement.FoodDAO;
//import com.su25.swp391.dal.implement.OrderComboDAO;
//import com.su25.swp391.dal.implement.OrderComboFoodDAO;
//import com.su25.swp391.dal.implement.OrderDAO;
//import com.su25.swp391.entity.Account;
//import com.su25.swp391.entity.Combo;
//import com.su25.swp391.entity.ComboFood;
//import com.su25.swp391.entity.Food;
//import com.su25.swp391.entity.Order;
//import com.su25.swp391.entity.OrderCombo;
//import com.su25.swp391.entity.OrderComboFood;
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.math.BigDecimal;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.TimeZone;
//
///**
// *
// * @author Hang
// */
//@WebServlet(name = "BuyComboController", urlPatterns = {"/buy-combo", "/process-vnpay",})
//public class BuyComboController extends HttpServlet {
//
//    ComboDAO combodao = new ComboDAO();
//    ComboFoodDAO combofoodDao = new ComboFoodDAO();
//    FoodDAO fooddao = new FoodDAO();
//    OrderComboDAO ordercombodao = new OrderComboDAO();
//    OrderComboFoodDAO ordercomboFoodDao = new OrderComboFoodDAO();
//    OrderDAO orderDao = new OrderDAO();
//    private static final String COMBO_SERVLET_URL = "comboController";
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet BuyComboController</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet BuyComboController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        //get path
//        String path = request.getServletPath();
//        switch (path) {
//            case "/buy-combo":
//                processBuyCombo(request, response);
//                break;
//            case "/process-vnpay":
//                processVnpay(request, response);
//                break;
////            case "/vnpay-return-combo":
////                handleVNPayReturnForCombo(request, response);
////                break;
//            default:
//                response.sendRedirect(request.getContextPath() + "/home");
//
//        }
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//     @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//
//        if ("wholesale".equals(action)) {
//            handleWholesalePayment(req, resp); // 
//        } else {
//            // fallback nếu không có action
//            resp.sendRedirect(req.getContextPath() + "/comboController");
//        }
//    }
//
//private void handleWholesalePayment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        try {
//            String amountParam = req.getParameter("amount");
//            if (amountParam == null || amountParam.isEmpty()) {
//                resp.sendRedirect(req.getContextPath() + "/comboController");
//                return;
//            }
//
//            long amount = (long) (Double.parseDouble(amountParam) * 100); // VNPAY cần nhân 100
//            String vnp_Url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html"; // hoặc gọi VNPayConfig.buildUrl(...)
//            // build thêm tham số vào URL...
//            String redirectUrl = vnp_Url + "?vnp_Amount=" + amount + "&..."; // build đúng tham số bạn cần
//
//            resp.sendRedirect(redirectUrl); // 👈 chuyển người dùng tới VNPay
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp.sendRedirect(req.getContextPath() + "/comboController");
//        }
//    }
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    private void processBuyCombo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        HttpSession session = request.getSession();
//        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
//
//        if (account == null) {
//            String message = URLEncoder.encode("Vui lòng đăng nhập để mua hàng", "UTF-8");
//            request.getRequestDispatcher("/view/authen/login.jsp").forward(request, response);
//            return;
//        }
//
//        String comboIdStr = request.getParameter("comboId");
//        String quantityStr = request.getParameter("quantity");
//
//        int comboId = 0;
//        int quantity = 0;
//        try {
//            comboId = Integer.parseInt(comboIdStr);
//            quantity = Integer.parseInt(quantityStr);
//            if (quantity <= 0) {
//                throw new NumberFormatException("Số lượng phải > 0");
//            }
//        } catch (NumberFormatException e) {
//
//            setToastMessage(request, "Không thể tạo đơn hàng", "error");
//            response.sendRedirect(request.getContextPath() + "/comboController");
//            return;
//        }
//
//        Combo combo = combodao.findById(comboId);
//        if (combo == null) {
//            setToastMessage(request, "Không tìm thấy combo.", "error");
//            response.sendRedirect(request.getContextPath() + "/comboController");
//            return;
//        }
//
//        List<ComboFood> comboFoods = combofoodDao.findByIdList(comboId);
//
//        double totalPrice = combo.getDiscountPrice() * quantity;
//
//        OrderCombo order = new OrderCombo();
//        order.setComboId(combo.getComboId());
//        order.setComboName(combo.getComboName());
//        order.setDiscountPrice(combo.getDiscountPrice());
//        order.setQuantity(quantity);
//        order.setTotalPrice(totalPrice);
//        order.setPayment_status(0);
//
//        int orderId = ordercombodao.insert(order);
//        if (orderId == -1) {
//            setToastMessage(request, "Không thể tạo đơn hàng", "error");
//            response.sendRedirect(request.getContextPath() + "/comboController");
//            return;
//        }
//
//        // Lưu vào session để thanh toán
//        session.setAttribute("combo", combo);
//        session.setAttribute("quantity", quantity);
//        session.setAttribute("comboFoods", comboFoods);
//        session.setAttribute("comboOrderId", orderId);
//        session.setAttribute("comboAmount", totalPrice);
//
//        // Chuyển sang trang tạo thanh toán
//        String url = request.getContextPath()
//                + "/ajaxServlet?amount=" + combo.getDiscountPrice()
//                + "&action=wholesale";
//        response.sendRedirect(url);
//
//    }
//
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//    private void setToastMessage(HttpServletRequest request, String message, String type) {
//        HttpSession session = request.getSession();
//        session.setAttribute("toastMessage", message);
//        session.setAttribute("toastType", type);
//    }
//
//    /**
//     * Xử lý thông tin khi VNPAY trả về
//     *
//     * @param request
//     * @param response
//     * @throws IOException
//     */
//    private void processVnpay(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        //get combo info
//        HttpSession session = request.getSession();
//        try {
//            Combo combo = (Combo) session.getAttribute("combo");
//            int quantity = (int) session.getAttribute("quantity");
//            List<ComboFood> comboFoods = (List<ComboFood>) session.getAttribute("comboFood");
//            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
//
//            //get VNPAY info
//            String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
//            String vnp_TransactionStatus = request.getParameter("vnp_TransactionStatus");
//
//            //kiểm tra trạng thái thanh toán
//            if ("00".equals(vnp_ResponseCode) && "00".equals(vnp_TransactionStatus)) {
//                // Thanh toán thành công
//                //STEP 1:  insert order
//                Order order = new Order();
//                order.setUserId(account.getId());
//                order.setStatus(GlobalConfig.ORDER_STATUS_PENDING);
//                order.setShippingAddress(account.getAddress());
//                order.setTotal(BigDecimal.valueOf(combo.getDiscountPrice()));
//                order.setPaymentMethod(GlobalConfig.PAYMENT_METHOD_VNPAY);
//
//                int orderIdAfterInsrt = orderDao.insert(order);
//
//                //STEP2: insert order combo
//                OrderCombo orderCombo = OrderCombo
//                        .builder()
//                        .order_id(orderIdAfterInsrt)
//                        .comboId(combo.getComboId())
//                        .comboName(combo.getComboName())
//                        .discountPrice(Double.valueOf(combo.getDiscountPrice()))
//                        .quantity(quantity)
//                        .totalPrice(Double.valueOf(combo.getOriginalPrice()))
//                        .build();
//
//                int orderComboIdAfterInsert = ordercombodao.insert(orderCombo);
//
//                //STEP3 : insert order combo product
//                for (ComboFood cp : comboFoods) {
//                    Food food = fooddao.findById(cp.getFoodId());
//                    OrderComboFood orderComboFood = OrderComboFood
//                            .builder()
//                            .orderComboId(orderComboIdAfterInsert)
//                            .foodId(food.getId())
//                            .foodName(food.getName())
//                            .foodPrice(food.getPrice())
//                            .quantityInCombo(cp.getQuantityInCombo())
//                            .totalQuantity(cp.getQuantityInCombo() * quantity)
//                            .build();
//
//                    ordercomboFoodDao.insert(orderComboFood);
//
//                }
//                //Xóa session
//                session.removeAttribute("combo");
//                session.removeAttribute("quantity");
//                session.removeAttribute("comboProducts");
//
//                setToastMessage(request, "Order Successful !!", "success");
//                response.sendRedirect(COMBO_SERVLET_URL);
//            } else {
//                // Thanh toán không thành công hoặc lỗi
//                // Xử lý lỗi hoặc chuyển hướng đến trang thông báo lỗi
//                setToastMessage(request, "Order failed. Something Wrong!!", "error");
//                response.sendRedirect(COMBO_SERVLET_URL);
//            }
//
//        } catch (Exception e) {
//            setToastMessage(request, "Order failed. Something Wrong!! " + e.getMessage(), "error");
//            response.sendRedirect(COMBO_SERVLET_URL);
//        }
//
//    }
//
////    private void handleVNPayReturnForCombo(HttpServletRequest request, HttpServletResponse response) throws IOException {
////        String responseCode = request.getParameter("vnp_ResponseCode");
////        String transactionStatus = request.getParameter("vnp_TransactionStatus");
////
////        if ("00".equals(responseCode) && "00".equals(transactionStatus)) {
////            handleComboOrderSuccess(request, response);
////        } else {
////            handleComboOrderFailed(request, response);
////        }
////    }
////
////    private void handleComboOrderSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
////        int orderId = Integer.parseInt(request.getParameter("vnp_TxnRef"));
////
////        OrderCombo ordercombo = ordercombodao.findById(orderId);
////        if (ordercombo != null) {
////            ordercombo.setPayment_status(1);
////            ordercombodao.update(ordercombo);
////        }
////        setToastMessage(request, "Thanh toán thành công!", "success");
////        response.sendRedirect(request.getContextPath() + "/comboController");
////    }
////
////    private void handleComboOrderFailed(HttpServletRequest request, HttpServletResponse response) throws IOException {
////        int orderId = Integer.parseInt(request.getParameter("vnp_TxnRef"));
////        OrderCombo order = ordercombodao.findById(orderId);
////        if (order != null) {
////            order.setPayment_status(0);
////            ordercombodao.update(order);
////        }
////        setToastMessage(request, "Thanh toán thất bại.", "error");
////        response.sendRedirect(request.getContextPath() + "/comboController");
////    }
//}
