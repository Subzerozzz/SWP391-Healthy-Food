/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.homePage;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.ComboDAO;
import com.su25.swp391.dal.implement.ComboFoodDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.OrderComboDAO;
import com.su25.swp391.dal.implement.OrderComboFoodDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Combo;
import com.su25.swp391.entity.ComboFood;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.OrderCombo;
import com.su25.swp391.entity.OrderComboFood;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author Hang
 */
@WebServlet(name = "BuyComboController", urlPatterns = {"/buy-combo", "/process-vnpay","/vnpay-return-combo"})
public class BuyComboController extends HttpServlet {

    ComboDAO combodao = new ComboDAO();
    ComboFoodDAO combofoodDao = new ComboFoodDAO();
    FoodDAO fooddao = new FoodDAO();
    OrderComboDAO ordercombodao = new OrderComboDAO();
    OrderComboFoodDAO ordercomboFoodDao = new OrderComboFoodDAO();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuyComboController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyComboController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get path
        String path = request.getServletPath();
        switch (path) {
            case "/buy-combo":
                processBuyCombo(request, response);
                break;
            case "/process-vnpay":
                processVnpay(request, response);
                break;
            case "/vnpay-return-combo":
                handleVNPayReturnForCombo(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/home");

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String path = request.getServletPath();
        switch (path) {
            case "/buy-combo":
                processBuyCombo(request, response);
                break;
            case "/process-vnpay":
                processVnpay(request, response);
                break;
            case "/vnpay-return-combo":
                handleVNPayReturnForCombo(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/home");

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   private void processBuyCombo(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session = request.getSession();
    Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

    if (account == null) {
        response.sendRedirect(request.getContextPath() + "/authen?action=login&message=Vui lòng đăng nhập để mua hàng");
        return;
    }

    String comboIdStr = request.getParameter("comboId");
    String quantityStr = request.getParameter("quantity");

    int comboId = 0;
    int quantity = 0;
    try {
        comboId = Integer.parseInt(comboIdStr);
        quantity = Integer.parseInt(quantityStr);
        if (quantity <= 0) throw new NumberFormatException("Số lượng phải > 0");
    } catch (NumberFormatException e) {
        
        setToastMessage(request, "Không thể tạo đơn hàng", "error");
        response.sendRedirect(request.getContextPath() + "/comboController");
        return;
    }

    Combo combo = combodao.findById(comboId);
    if (combo == null) {
        setToastMessage(request, "Không tìm thấy combo.", "error");
        response.sendRedirect(request.getContextPath() + "/comboController");
        return;
    }

    List<ComboFood> comboFoods = combofoodDao.findByIdList(comboId);

    double totalPrice = combo.getDiscountPrice() * quantity;

    OrderCombo order = new OrderCombo();
    order.setComboId(combo.getComboId());
    order.setComboName(combo.getComboName());
    order.setDiscountPrice(combo.getDiscountPrice());
    order.setQuantity(quantity);
    order.setTotalPrice(totalPrice);
    order.setPayment_status(0);

    int orderId = ordercombodao.insert(order);
    if (orderId == -1) {
        setToastMessage(request, "Không thể tạo đơn hàng", "error");
        response.sendRedirect(request.getContextPath() + "/comboController");
        return;
    }

    // Lưu vào session để thanh toán
    session.setAttribute("combo", combo);
    session.setAttribute("quantity", quantity);
    session.setAttribute("comboFoods", comboFoods);
    session.setAttribute("comboOrderId", orderId);
    session.setAttribute("comboAmount", totalPrice);

    // Chuyển sang servlet tạo thanh toán
    response.sendRedirect(request.getContextPath() + "/process-vnpay");
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void setToastMessage(HttpServletRequest request, String message, String type) {
        HttpSession session = request.getSession();
        session.setAttribute("toastMessage", message);
        session.setAttribute("toastType", type);
    }

    private void processVnpay(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session = request.getSession();
    try {
        Combo combo = (Combo) session.getAttribute("combo");
        int quantity = (int) session.getAttribute("quantity");
        List<ComboFood> comboFoods = (List<ComboFood>) session.getAttribute("comboFoods");
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
        int orderId = (int) session.getAttribute("comboOrderId");
        double amount = (double) session.getAttribute("comboAmount");

        if (combo == null || quantity <= 0 || orderId <= 0) {
            setToastMessage(request, "Thiếu dữ liệu đơn hàng", "error");
            response.sendRedirect(request.getContextPath() + "/comboController");
            return;
        }

        // VNPay config
        long amountVND = (long) (amount * 100); // chuyển sang đơn vị VNPay
        String vnp_TxnRef = String.valueOf(orderId);
        String vnp_OrderInfo = "Thanh toán combo #" + orderId;
        String orderType = "other";
        String vnp_Returnurl = VNPayConfig.vnp_ReturnUrl;
        String vnp_IpAddr = request.getRemoteAddr();

        // Thời gian tạo đơn
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cal.getTime());
        cal.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cal.getTime());

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", VNPayConfig.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amountVND));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_ReturnUrl", vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_BankCode", "NCB");

        // Sắp xếp & ký dữ liệu
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (String name : fieldNames) {
            String value = vnp_Params.get(name);
            if (hashData.length() > 0) {
                hashData.append('&');
                query.append('&');
            }
            hashData.append(name).append('=').append(value);
            query.append(URLEncoder.encode(name, StandardCharsets.US_ASCII)).append('=')
                    .append(URLEncoder.encode(value, StandardCharsets.US_ASCII));
        }

        String secureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        query.append("&vnp_SecureHash=").append(secureHash);

        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + query;
        response.sendRedirect(paymentUrl);

    } catch (Exception e) {
        e.printStackTrace();
        setToastMessage(request, "Có lỗi xảy ra khi xử lý thanh toán!", "error");
        response.sendRedirect(request.getContextPath() + "/comboController");
    }
}


    private void handleVNPayReturnForCombo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String responseCode = request.getParameter("vnp_ResponseCode");
        String transactionStatus = request.getParameter("vnp_TransactionStatus");

        if ("00".equals(responseCode) && "00".equals(transactionStatus)) {
            handleComboOrderSuccess(request, response);
        }  else {
            handleComboOrderFailed(request, response);
        }
    }

    private void handleComboOrderSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("vnp_TxnRef"));

        OrderCombo ordercombo = ordercombodao.findById(orderId);
        if (ordercombo != null) {
            ordercombo.setPayment_status(1);
            ordercombodao.update(ordercombo);
        }
        setToastMessage(request, "Thanh toán thành công!", "success");
        response.sendRedirect(request.getContextPath() + "/comboController");
    }


    private void handleComboOrderFailed(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("vnp_TxnRef"));
        OrderCombo order = ordercombodao.findById(orderId);
        if (order != null) {
            order.setPayment_status(0);
            ordercombodao.update(order);
        }
        setToastMessage(request, "Thanh toán thất bại.", "error");
        response.sendRedirect(request.getContextPath() + "/comboController");
    }
}
