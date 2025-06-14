/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.su25.swp391.controller.seller;

import com.su25.swp391.dal.implement.OrderApprovalDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name="ManagerFeedbackController", urlPatterns={"/manage-feedback"})
public class ManagerFeedbackController extends HttpServlet {
    private OrderDAO orderDAO;
    private OrderApprovalDAO approvalDAO;
    private OrderItemDAO itemDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAO();
        approvalDAO = new OrderApprovalDAO();
        itemDAO = new OrderItemDAO();
    }
    
  

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         // Get action from submit
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }
        
        try {
            switch (action) {
//                case "list":
//                    listFeedbacks(request, response);
//                case "select-product":
//                    showProductSelectionList(request, response, loggedInUser);
//                    break;
//                case "write-review":
//                    showReviewForm(request, response, loggedInUser);
//                    break;
//                case "edit":
//                    showEditFeedbackForm(request, response, loggedInUser);
//                    break;
//                case "delete":
//                    deleteFeedback(request, response, loggedInUser);
//                    break;
                default:
                    listFeedbacks(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
//            session.setAttribute("errorMessage", "Đã xảy ra lỗi: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/feedbackControl");
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       // Get action from submit
        String action = request.getParameter("action");
        try {
            switch (action) {
//                case "submit":
//                    submitFeedback(request, response, loggedInUser);
//                    break;
//                case "update":
//                    updateFeedback(request, response, loggedInUser);
//                    break;
                default:
                    listFeedbacks(request, response);
            }
        } catch (Exception ex) {
//            session.setAttribute("errorMessage", "Đã xảy ra lỗi: " + ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/feedbackControl");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listFeedbacks(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
