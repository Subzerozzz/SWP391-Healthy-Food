/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.nutritionist;

import com.su25.swp391.dal.implement.FoodCategoryDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.FoodCategory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ManagerDashboardController", urlPatterns = { "/manager-dashboard" })
public class ManagerDashboardController extends HttpServlet {

  FoodDAO foodDao = new FoodDAO();
  FoodCategoryDAO foodCategoryDao = new FoodCategoryDAO();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Food> listFood = foodDao.findAll();
    List<FoodCategory> listCategory = foodCategoryDao.findAll();
    // luu vao Session
    HttpSession session = request.getSession();
    session.setAttribute("listFood", listFood);
    session.setAttribute("listCategory", listCategory);
    response.sendRedirect("view/nutritionist/menu/dashboard.jsp");
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request  servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException      if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

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

}
