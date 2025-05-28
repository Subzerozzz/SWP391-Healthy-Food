/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.manager;

import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.entity.Food;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ManageFood", urlPatterns = {"/manage-food"})
public class ManageFood extends HttpServlet {

    FoodDAO foodDao = new FoodDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "view":
                viewFood(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action")
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

    private List<Food> viewFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Food> listFood = foodDao.findAll();
        //luu vao Session
        HttpSession session = request.getSession();
        session.setAttribute("listFood", listFood);
        //chuyen huong ve trang dashboard manager
        request.getRequestDispatcher("manager-dashboard").forward(request, response);
        return listFood;
    }

}
