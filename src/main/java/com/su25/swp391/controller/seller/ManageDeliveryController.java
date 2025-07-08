/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.su25.swp391.controller.seller;

import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.DeliveryDAO;
import com.su25.swp391.dal.implement.FeedbackDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.OrderApprovalDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Delivery;
import com.su25.swp391.entity.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name="ManageDeliveryController", urlPatterns={"/seller/manage-delivery"})
public class ManageDeliveryController extends HttpServlet {
     
     private DeliveryDAO deliveryDAO;
     private OrderDAO orderDAO;
     private AccountDAO accDAO;
     @Override
    public void init() throws ServletException {
        deliveryDAO = new DeliveryDAO();
        accDAO = new AccountDAO();
        orderDAO = new OrderDAO();
    }
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
                case "shipper":
                    viewShipper(request, response);
                    break;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get action from submit
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "add":
                    selectShipper(request, response);
                    break;
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
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listDelivery(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      String sort = request.getParameter("sort");
      String status = request.getParameter("status");
      String search = request.getParameter("search");
      // Pagination
        int page = 1;
        int pageSize = 2;
         try {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                if (page < 1) {
                    page = 1;
                }
            }
        } catch (NumberFormatException e) {
            // Keep default value
        }
      // Get orders with filters
        List<Delivery> listDelivery;
        int totalDeliveries = 0;
      if (search != null && !search.trim().isEmpty()) {
          listDelivery = deliveryDAO.searchDelivery(search, sort, status,page,pageSize);
          totalDeliveries = deliveryDAO.getTotalDeliveryResults(search,status);
      } else {
            // If no search, use filters
            listDelivery = deliveryDAO.findDeliveryWithFilters(sort,status,page,pageSize);
            totalDeliveries = deliveryDAO.getTotalFilteredDelivery(status);
         }
      // Number of page can have
        int totalPages = (int) Math.ceil((double) totalDeliveries / pageSize);
     
      request.setAttribute("sort", sort);
      request.setAttribute("status", status);
      request.setAttribute("search", search);
      request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
      request.setAttribute("accDAO", accDAO);
      request.setAttribute("orderDAO", orderDAO);
      request.setAttribute("listDelivery", listDelivery);
      request.getRequestDispatcher("/view/seller/manage-delivery-list.jsp").forward(request, response);
    }

    private void viewShipper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       List<Account> accountList = accDAO.findAccountByRole("shipper");
       request.setAttribute("shipperList", accountList);
       request.setAttribute("deliveryDAO", deliveryDAO);
       request.getRequestDispatcher("/view/seller/select-shipper.jsp").forward(request, response);
    }

    private void selectShipper(HttpServletRequest request, HttpServletResponse response) {
       
    }

}
