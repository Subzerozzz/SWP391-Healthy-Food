/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.su25.swp391.controller.seller;

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
@WebServlet(name="ManageDeliveryController", urlPatterns={"/manage-delivery"})
public class ManageDeliveryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get action from submit
        String action = request.getParameter("action");
        // If action == null
        if (action == null) {
            action = "list";
        }
        try {
            switch (action) {
                case "list":
                    listDelivery(request, response);
                    break;
//                case "view":
//                    viewOrderDetail(request, response);
//                    break;
//                case "viewUpdate":
//                    viewUpdate(request, response);
//                    break;
                default:
                    listDelivery(request, response);
                    break;
            }
        } catch (Exception ex) {
            // Notification error
            request.setAttribute("errorMessage", "Database error: " + ex.getMessage());
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Get action from submit
//        String action = request.getParameter("action");
//        if (action == null) {
//            action = "list";
//        }
//
//        try {
//            switch (action) {
//                case "updateStatus":
//                    updateOrderStatus(request, response);
//                    break;
//                default:
//                    listOrders(request, response);
//                    break;
//            }
//        } catch (Exception ex) {
//            // Notification error
//            request.setAttribute("errorMessage", "Database error: " + ex.getMessage());
//            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
//        }
//    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listDelivery(HttpServletRequest request, HttpServletResponse response) throws IOException {
      response.sendRedirect("manage-delivery-list");
    }

}
