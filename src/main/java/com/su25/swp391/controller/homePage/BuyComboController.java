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
import java.util.List;

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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    private void processBuyCombo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

        //kiem tra dang nhap
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/authen?action=login&message=Vui lòng đăng nhập để mua hàng");
            return;
        }
        String comboIdStr = request.getParameter("comboId");
        String quantityStr = request.getParameter("quantity");
        String errorMessage = null;

        int comboId = 0;
        int quantity = 0;
        try {
            comboId = Integer.parseInt(comboIdStr);
            quantity = Integer.parseInt(quantityStr);
            if (quantity < 0) {
                throw new NumberFormatException("Số lượng phải lớn hơn 0");
            }
        } catch (NumberFormatException e) {
            errorMessage = "ID combo hoặc số lượng không hợp lệ";
            System.out.println("Hàm processBuyCombo" + errorMessage);
            setToastMessage(request, "Order failed. Something Wrong!!", "error");
            response.sendRedirect(request.getContextPath() + "/comboController");
            return;
        }
        // Lấy thông tin combo

        Combo combo = combodao.findById(comboId);
        if (combo == null) {
            errorMessage = "Không tìm thấy combo.";
            System.out.println("Hàm processBuyCombo: " + errorMessage);
            setToastMessage(request, "Order failed. Something Wrong!!", "error");
            response.sendRedirect(request.getContextPath() + "/comboController");
            return;
        }
        //Lấy danh sách sản phẩm trong combo 

        List<ComboFood> comboFoods = combofoodDao.findByIdList(comboId);
        for (ComboFood cp : comboFoods) {
            //lay thong tin san pham
            Food food = fooddao.findById(cp.getFoodId());
            //kiem tra xem cos tim thay mon an 
            if (food == null) {
                errorMessage = "Lỗi: Không tìm thấy món ăn có ID " + cp.getFoodId() + "trong combo.";
                System.out.println("Hàm processBuyCombo: " + errorMessage);
                setToastMessage(request, "Order failed. Something wrong!", "error");
                response.sendRedirect(request.getContextPath() + "/comboController");
                return;
            }

            // Lưu thông tin combo vào session
            session.setAttribute("combo", combo);
            session.setAttribute("quantity", quantity);
            session.setAttribute("comboFoods", comboFoods);

            // Chuyển tới trang thanh toán (ví dụ VNPAY)
            String url = request.getContextPath() + "/ajaxServlet?amount=" + combo.getDiscountPrice() + "&action=wholesale";
            response.sendRedirect(url);
        }

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

    private void processVnpay(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion = request.getSession();
        try {
            Combo combo = (Combo) sesion.getAttribute("combo");
            int quantity = (int) sesion.getAttribute("quantity");
            List<ComboFood> comboFoods = (List<ComboFood>) sesion.getAttribute("comboFoods");
            Account account = (Account) sesion.getAttribute(GlobalConfig.SESSION_ACCOUNT);
        } catch (Exception e) {
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
