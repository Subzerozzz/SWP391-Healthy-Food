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
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Combo;
import com.su25.swp391.entity.ComboFood;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.Order;
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
@WebServlet(name = "BuyComboController", urlPatterns = {"/buy-combo", "/process-vnpay"})
public class BuyComboController extends HttpServlet {

    ComboDAO combodao = new ComboDAO();
    ComboFoodDAO combofoodDao = new ComboFoodDAO();
    FoodDAO fooddao = new FoodDAO();
    OrderComboDAO ordercombodao = new OrderComboDAO();
    OrderComboFoodDAO ordercomboFoodDao = new OrderComboFoodDAO();
    OrderDAO orderDao = new OrderDAO();
    private static final String COMBO_SERVLET_URL = "comboController";

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("wholesale".equals(action)) {
            handleWholesalePayment(req, resp); // 
        } else {
            // fallback nếu không có action
            resp.sendRedirect(req.getContextPath() + "/comboController");
        }
    }

    private void handleWholesalePayment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String amountParam = req.getParameter("amount");
            if (amountParam == null || amountParam.isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/comboController");
                return;
            }

            long amount = (long) (Double.parseDouble(amountParam) * 100); // VNPAY cần nhân 100
            String vnp_Url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html"; // hoặc gọi VNPayConfig.buildUrl(...)
            // build thêm tham số vào URL...
            String redirectUrl = vnp_Url + "?vnp_Amount=" + amount + "&..."; // build đúng tham số bạn cần

            resp.sendRedirect(redirectUrl); // 👈 chuyển người dùng tới VNPay
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/comboController");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    private void processBuyCombo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

        if (account == null) {
            String message = URLEncoder.encode("Vui lòng đăng nhập để mua hàng", "UTF-8");
            request.getRequestDispatcher("/view/authen/login.jsp").forward(request, response);
            return;
        }
        if (!"customer".equalsIgnoreCase(account.getRole())) {
            setToastMessage(request, "Chỉ Có Customer mới được mua hàng", "error");
            request.getRequestDispatcher("/view/authen/login.jsp").forward(request, response);
            return;
        }
        int user_id = account.getId();
        
        String comboIdStr = request.getParameter("comboId");
        String quantityStr = request.getParameter("quantity");

        int comboId = 0;
        int quantity = 0;
        try {
            comboId = Integer.parseInt(comboIdStr);
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                throw new NumberFormatException("Số lượng phải > 0");
            }
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
        order.setUser_id(user_id);

        int orderComboId = ordercombodao.insert(order);
        if (orderComboId == -1) {
            setToastMessage(request, "Không thể tạo đơn hàng", "error");
            response.sendRedirect(request.getContextPath() + "/comboController");
            return;
        }

        // Lưu vào session để thanh toán
        session.setAttribute("combo", combo);
        session.setAttribute("user_id", user_id);
        session.setAttribute("quantity", quantity);
        session.setAttribute("comboFoods", comboFoods);
        session.setAttribute("comboOrderId", orderComboId);
        session.setAttribute("comboAmount", totalPrice);

        // Chuyển sang trang tạo thanh toán
        long amount = Math.round(combo.getDiscountPrice() * quantity * 1);

        response.sendRedirect(request.getContextPath() + "/ajaxServlet?amount=" + amount
                + "&orderId=" + orderComboId + "&combo_payment=true");

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

    /**
     * Xử lý thông tin khi VNPAY trả về
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void processVnpay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //get combo info
        HttpSession session = request.getSession();
        try {
            Combo combo = (Combo) session.getAttribute("combo");
            int quantity = (int) session.getAttribute("quantity");
            List<ComboFood> comboFoods = (List<ComboFood>) session.getAttribute("comboFoods");
            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

            //get VNPAY info
            String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
            String vnp_TransactionStatus = request.getParameter("vnp_TransactionStatus");

            //kiểm tra trạng thái thanh toán
            if ("00".equals(vnp_ResponseCode) && "00".equals(vnp_TransactionStatus)) {
                int orderComboIdAfterInsert = (int) session.getAttribute("comboOrderId");

                // Cập nhật trạng thái đã thanh toán
                ordercombodao.updatePaymentStatus(orderComboIdAfterInsert, 1);

                //STEP3 : insert order combo product
                for (ComboFood cp : comboFoods) {
                    Food food = fooddao.findById(cp.getFoodId());
                    OrderComboFood orderComboFood = OrderComboFood
                            .builder()
                            .orderComboId(orderComboIdAfterInsert)
                            .foodId(food.getId())
                            .foodName(food.getName())
                            .foodPrice(food.getPrice())
                            .quantityInCombo(cp.getQuantityInCombo())
                            .totalQuantity(cp.getQuantityInCombo() * quantity)
                            .build();

                    ordercomboFoodDao.insert(orderComboFood);

                }
                //Xóa session
                session.removeAttribute("combo");
                session.removeAttribute("quantity");
                session.removeAttribute("comboProducts");

                setToastMessage(request, "Order Successful !!", "success");
                response.sendRedirect(COMBO_SERVLET_URL);
            } else {
                // Thanh toán không thành công hoặc lỗi
                // Xử lý lỗi hoặc chuyển hướng đến trang thông báo lỗi
                setToastMessage(request, "Order failed. Something Wrong!!", "error");
                response.sendRedirect(COMBO_SERVLET_URL);
            }

        } catch (Exception e) {
            setToastMessage(request, "Order failed. Something Wrong!! " + e.getMessage(), "error");
            response.sendRedirect(COMBO_SERVLET_URL);
        }

    }
}
