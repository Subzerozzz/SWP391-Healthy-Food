/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.manager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.su25.swp391.dal.implement.ProductDAO;
import com.su25.swp391.entity.Product;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ManageProduct", urlPatterns = { "/manage-product" })
public class ManageProduct extends HttpServlet {
    ProductDAO productDao = new ProductDAO();


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Tương tác với productDAO để lấy ra danh sách các product
    List<Product> productList = productDao.findAll();
    //Tạo ra session 
    HttpSession session = request.getSession();
    session.setAttribute("products", productList);
    request.getRequestDispatcher("manage-product.jsp").forward(request, response);
    // Sau đó chuyển hướng sang trang lại sang trang dashboard quản lý product
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  }
}