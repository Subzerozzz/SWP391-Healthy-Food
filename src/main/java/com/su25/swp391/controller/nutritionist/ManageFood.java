/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.nutritionist;

import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.FoodDraftDAO;
import com.su25.swp391.dal.implement.RequestDAO;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.Food_draft;
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
@WebServlet(name = "ManageFood", urlPatterns = { "/manage-food" })
public class ManageFood extends HttpServlet {

  FoodDAO foodDao = new FoodDAO();
  FoodDraftDAO foodDraftDao = new FoodDraftDAO();
  RequestDAO requestDao = new RequestDAO();

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

  private void viewFood(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.getRequestDispatcher("manager-dashboard").forward(request, response);
  }

  private void addFood(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      //LẤY CÁC DỮ LIỆU
      // get name
      String name = request.getParameter("name");
      // get category
      Integer category = Integer.parseInt(request.getParameter("category"));
      // get price
      Double price = Double.parseDouble(request.getParameter("price"));
      // get status
      String status = "initial";
      // get description
      String description = request.getParameter("description");
      // get image
      String image = request.getParameter("image") == null ? "" : request.getParameter("image");
      // get created
      // Thiết lập thời gian tạo và cập nhật
      Timestamp now = new Timestamp(System.currentTimeMillis());
      Timestamp created_at = new Timestamp(now.getTime());
      Timestamp updated_at = new Timestamp(now.getTime());

      // 1. THÊM FOOD VÀO DRAFT TRƯỚC
      Food_draft newFood = Food_draft.builder()
          .name(name)
          .category_id(category)
          .price(price)
          .status(status)
          .description(description)
          .image_url(image)
          .created_at(created_at)
          .updated_at(updated_at)
          .build();

      foodDraftDao.insert(newFood);

      // 2.SAU ĐÓ THÊM VÀO 1 REQUEST ĐỂ GỬI LÊN MANAGER
      //set type cho request
      String type = "CREATE FOOD";
      //lấy ra bản id của Food_draft mới nhất gắn vào bản request
      Integer foodDraftId = foodDraftDao.getBiggestId();
      //set trang thai mac dinh cho request moi
      String statusRequest = "NOT DONE";
      
      Request newRequest = Request.builder()
              .type(type)
              .foodDraftId(foodDraftId)
              .status(statusRequest)
              .build();
      
      requestDao.insert(newRequest);
      
      //3.CHUYỂN VỀ TRANG HOME VÀ THÔNG BÁO YÊU CẦU THÀNH CÔNG GỬI ĐI
      HttpSession session = request.getSession();
      session.setAttribute("isCreated", true);
      response.sendRedirect("manager-dashboard");

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e);
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
