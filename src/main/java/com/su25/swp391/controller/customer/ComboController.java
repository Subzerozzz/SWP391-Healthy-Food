/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.customer;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.ComboDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.entity.Combo;
import com.su25.swp391.entity.ComboFoodDetails;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hang
 */
@WebServlet(name = "ComboController", urlPatterns = {"/comboController"})
public class ComboController extends HttpServlet {

    private final String COMBO_LIST_PAGE = "/view/homePage/combo_list.jsp";
    private final String COMBO_DETAILS_PAGE = "/view/homePage/combo_detail.jsp";

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
            out.println("<title>Servlet ComboController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComboController at " + request.getContextPath() + "</h1>");
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
        try {
            // Lấy action từ request
            String action = request.getParameter("action");

            // Kiểm tra trạng thái đăng nhập và lưu vào request attribute
            HttpSession session = request.getSession();
            boolean isLoggedIn = session.getAttribute(GlobalConfig.SESSION_ACCOUNT) != null;
            request.setAttribute("isLoggedIn", isLoggedIn);

            // Nếu action là "details", xử lý hiển thị chi tiết combo
            if ("details".equals(action)) {
                showComboDetails(request, response);
                return;
            }

            // Mặc định hiển thị danh sách combo
            showComboList(request, response);

        } catch (Exception e) {
            System.out.println("Error in ComboController: " + e.getMessage());
            e.printStackTrace();
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
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void showComboDetails(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
// Lấy ID combo từ request
        String comboIdStr = request.getParameter("id");
        if (comboIdStr == null || comboIdStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/comboComtroller");
            return;
        }
        
        try {
            int comboId = Integer.parseInt(comboIdStr);
            ComboDAO comboDAO = new ComboDAO();
            FoodDAO foodDAO = new FoodDAO();
            
            // Lấy thông tin combo
            Combo combo = comboDAO.findById(comboId);
            if (combo == null || !"active".equals(combo.getStatus())) {
                response.sendRedirect(request.getContextPath() + "/comboController");
                return;
            }
            
            // Lấy danh sách sản phẩm trong combo
            List<ComboFoodDetails> foodDetails = comboDAO.getComboFoodDetails(comboId);
            
            // Đặt thuộc tính vào request
            request.setAttribute("combo", combo);
            request.setAttribute("foodDetails", foodDetails);
            
            // Forward đến trang chi tiết combo
            request.getRequestDispatcher(COMBO_DETAILS_PAGE).forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/comboController");
        }    }

    private void showComboList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComboDAO comboDAO = new ComboDAO();

        // Get search parameter
        String searchTerm = request.getParameter("search");

        // Xử lý phân trang
        int pageSize = 9; // Số combo trên mỗi trang
        int currentPage = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null && !pageStr.isEmpty()) {
            try {
                currentPage = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                currentPage = 1; // Reset về trang 1 nếu số trang không hợp lệ
            }
        }

        List<Combo> combos;
        int totalCombos;

        // If search term exists, use search functionality
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            // Get total count for pagination
            totalCombos = comboDAO.countSearchResults(searchTerm);

            // Get combos for current page
            combos = comboDAO.searchCombos(searchTerm, currentPage, pageSize);
        } else {
            // Get all active combos if no search term
            List<Combo> allCombos = comboDAO.findAllComboActive();
            totalCombos = allCombos.size();

            // Calculate pagination
            int fromIndex = (currentPage - 1) * pageSize;
            int toIndex = Math.min(fromIndex + pageSize, totalCombos);

            combos = fromIndex < totalCombos ? allCombos.subList(fromIndex, toIndex) : new ArrayList<>();
        }

        // Tính tổng số trang
        int totalPages = (int) Math.ceil((double) totalCombos / pageSize);

        // Validate currentPage
        if (currentPage < 1) {
            currentPage = 1;
        } else if (currentPage > totalPages) {
            currentPage = totalPages > 0 ? totalPages : 1;
        }

        // Tính toán phân trang
        int maxVisiblePages = 5;
        int halfVisible = (maxVisiblePages - 1) / 2;

        // Tính startPage và endPage
        int startPage;
        int endPage;

        if (totalPages <= maxVisiblePages) {
            startPage = 1;
            endPage = totalPages;
        } else {
            startPage = Math.max(1, currentPage - halfVisible);
            endPage = Math.min(currentPage + halfVisible, totalPages);

            // Điều chỉnh nếu khoảng trang hiển thị không đủ
            if (endPage - startPage < maxVisiblePages - 1) {
                startPage = Math.max(1, endPage - maxVisiblePages + 1);
            }
        }

        // Create search query string for pagination links
        String searchQueryString = "";
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            searchQueryString = "&search=" + searchTerm;
        }

        // Đặt các thuộc tính vào request
        request.setAttribute("combos", combos);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("searchQueryString", searchQueryString);
        request.setAttribute("searchTerm", searchTerm);

        // Forward đến trang danh sách combo
        request.getRequestDispatcher(COMBO_LIST_PAGE).forward(request, response);
    }

}
