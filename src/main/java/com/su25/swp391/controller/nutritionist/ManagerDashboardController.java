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
  private static final int RECORD_PER_PAGE = 10;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Food> listFood1 = foodDao.findAll();
    List<FoodCategory> listCategory = foodCategoryDao.findAll();
    // pagination
    // lấy ra tổng số bản ghi
    Integer totalOfRecord = listFood1.size();
    // tính ra tổng số page
    Integer totalPage = totalOfRecord % RECORD_PER_PAGE == 0 ? totalOfRecord / RECORD_PER_PAGE
        : totalOfRecord / RECORD_PER_PAGE + 1;
    // lấy ra số bản ghi theo từng page
    List<Food> listFood = foodDao.findRecordByPage(RECORD_PER_PAGE,1);
    request.setAttribute("listFood", listFood);
    request.setAttribute("listCategory", listCategory);
    request.setAttribute("totalPage", totalPage);
    request.setAttribute("currentPage", 1);
    request.getRequestDispatcher("view/nutritionist/menu/dashboard.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }


}
