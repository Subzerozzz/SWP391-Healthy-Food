/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.nutritionist;

import com.su25.swp391.dal.implement.FoodCategoryDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.FoodDraftDAO;
import com.su25.swp391.dal.implement.RequestDAO;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.FoodCategory;
import com.su25.swp391.entity.FoodDraft;
import com.su25.swp391.entity.Request;

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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ManageFood", urlPatterns = {"/manage-food"})
public class ManageFood extends HttpServlet {

    FoodDAO foodDao = new FoodDAO();
    FoodDraftDAO foodDraftDao = new FoodDraftDAO();
    RequestDAO requestDao = new RequestDAO();
    FoodCategoryDAO categoryDao = new FoodCategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "view":
                viewFood(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "update":
                showUpdateForm(request, response);
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

    private void viewFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("manager-dashboard").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<FoodCategory> listCategory = new ArrayList<>();
        listCategory = categoryDao.findAll();
        // lấy ra luu lên session
        HttpSession session = request.getSession();
        session.setAttribute("listCategory", listCategory);
        // chuyen huong nguoi dung
        response.sendRedirect("view/nutritionist/menu/addFood.jsp");
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Lấy ra id của food
        Integer foodId = Integer.parseInt(request.getParameter("id"));
        PrintWriter out = response.getWriter();
        out.println("id" + foodId);
        //lấy ra Food cần update
        Food foodNeedUpdate = foodDao.findById(foodId);
        HttpSession session = request.getSession();
        session.setAttribute("foodUpdate", foodNeedUpdate);
        //Chuyển về trang updateFood
        response.sendRedirect("view/nutritionist/menu/updateFood.jsp");
    }

    private void addFood(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
