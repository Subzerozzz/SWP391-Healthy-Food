/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.su25.swp391.controller.manage_menu;

import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.Food_DraftDAO;
import com.su25.swp391.dal.implement.RequestDAO;
import com.su25.swp391.entity.Food_Draft;
import com.su25.swp391.entity.Request;
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
@WebServlet(name="TypeOfRequestController", urlPatterns={"/type-of-request"})
public class TypeOfRequestController extends HttpServlet {
   
    private Food_DraftDAO foodDraftDAO;
    private RequestDAO requestDAO;
    private FoodDAO foodDAO;
    @Override
    public void init() throws ServletException{
        foodDraftDAO = new Food_DraftDAO();
        requestDAO = new RequestDAO();
        foodDAO = new FoodDAO();
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       // Get request from Nutritionist
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Assign action = list 
        }
        switch (action) {
            case "accept":
                addToFood(request, response);
                break;
            case "view":
                showViewDetail(request, response);
                break;
            case "deactivate":
                deactivateCategory(request, response);
                break;
            case "activate":
                activateCategory(request, response);
                break;
            case "reject":
                rejectFoodDraft(request,response);
                break;
            default:
               listTypeOfRequest(request,response);
                break;
            }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Default action
        }

        switch (action) {
            case "add":
                
                break;
            case "update":
                updateCategory(request, response);
                break;
            default:
                listCategories(request, response);
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void listCategories(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void listTypeOfRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          List<Food_Draft> list = foodDraftDAO.findAll();
          List<String> listType = foodDraftDAO.findAllType();
          request.setAttribute("listFoodDraft", list);
          request.setAttribute("type", listType);
          request.getRequestDispatcher("/view/nutritionist/dashboard.jsp").forward(request, response);
    }

    private void rejectFoodDraft(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void activateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void deactivateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void showViewDetail(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void addToFood(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
