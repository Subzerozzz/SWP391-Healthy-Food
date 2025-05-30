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
import jakarta.servlet.http.Part;
import java.io.File;
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
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addFood(request, response);
                break;
            case "delete":
                deleteFood(request, response);
                break;
            case "update":
                updateFood(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private List<Food> viewFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Food> listFood = foodDao.findAll();
        // luu vao Session
        HttpSession session = request.getSession();
        session.setAttribute("listFood", listFood);
        // chuyen huong ve trang dashboard manager
        request.getRequestDispatcher("manager-dashboard").forward(request, response);
        return listFood;
    }

    private void addFood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //get name
            String name = request.getParameter("name");
            //get category
            Integer category = Integer.parseInt(request.getParameter("category"));
            //get price
            Double price = Double.parseDouble(request.getParameter("price"));
            //get status
            String status = request.getParameter("status");
            //get description
            String description = request.getParameter("description");
            //get image
            String image = request.getParameter("image") == null ? "" : request.getParameter("image");
            //get created
            String created_at = request.getParameter("created_at");
            
            Food newFood = Food.builder()
                    .name(name)
                    .category_id(category)
                    .price(price)
                    .status(status)
                    .description(description)
                    .created_at(created_at)
                    .build();
            
            foodDao.insert(newFood);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteFood(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void updateFood(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
