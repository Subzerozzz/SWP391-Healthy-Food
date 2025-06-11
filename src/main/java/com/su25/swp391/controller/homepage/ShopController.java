/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.homepage;

import com.su25.swp391.dal.implement.FoodCategoryDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.FoodCategory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ShopController", urlPatterns = {"/shop"})
public class ShopController extends HttpServlet {

    public static final int FOOD_PER_PAGE = 12;
    FoodDAO foodDao = new FoodDAO();
    FoodCategoryDAO foodCategoryDao = new FoodCategoryDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShopController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "viewAll" : request.getParameter("action");
        switch (action) {
            case "viewAll":
                showFoodList(request, response);
                break;
            case "foodByCategory":
                showFoodByCategory(request, response);
                break;
            case "search":
                searchFood(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void showFoodList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lấy ra totalPage
        List<Food> list1 = foodDao.findAll();
        Integer totalRecord = list1.size();
        Integer totalPage = totalRecord % FOOD_PER_PAGE == 0
                ? totalRecord / FOOD_PER_PAGE : totalRecord / FOOD_PER_PAGE + 1;
        //Lấy ra currentPage
        Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        //Lấy ra 12 bản ghi đầu tiên
        List<Food> listFood = foodDao.findRecordByPage(FOOD_PER_PAGE, currentPage);
        List<FoodCategory> listFoodCategory = foodCategoryDao.findAll();
        request.setAttribute("listFood", listFood);
        request.setAttribute("listFoodCategory", listFoodCategory);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        //Set 1 biến cho category là all
        request.setAttribute("category", 0);
        request.getRequestDispatcher("view/homePage/shop.jsp").forward(request, response);
    }

    private void showFoodByCategory(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer category = Integer.parseInt(request.getParameter("category"));
            //Lấy ra totalPage 
            List<Food> list1 = foodDao.findByCategoryId(category);
            Integer totalRecord = list1.size();
            Integer totalPage = totalRecord % FOOD_PER_PAGE == 0
                    ? totalRecord / FOOD_PER_PAGE : totalRecord / FOOD_PER_PAGE + 1;
            //Lấy ra currentPage
            Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            //Lấy ra 12 bản ghi đầu tiên
            List<Food> listFood = foodDao.findRecordByPageForCategory(category, currentPage, FOOD_PER_PAGE);
            List<FoodCategory> listFoodCategory = foodCategoryDao.findAll();
            request.setAttribute("listFood", listFood);
            request.setAttribute("listFoodCategory", listFoodCategory);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("category", category);
            request.getRequestDispatcher("view/homePage/shop.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void searchFood(HttpServletRequest request, HttpServletResponse response) {
        try {
            String foodName = request.getParameter("foodName");
            //Lấy ra totalPage 
            List<Food> list1 = foodDao.getFoodByName(foodName);
            Integer totalRecord = list1.size();
            Integer totalPage = totalRecord % FOOD_PER_PAGE == 0
                    ? totalRecord / FOOD_PER_PAGE : totalRecord / FOOD_PER_PAGE + 1;
            //Lấy ra currentPage
            Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            //Lấy ra 12 bản ghi đầu tiên theo tên
            List<Food> listFood = foodDao.getRecordByPageForSearch(foodName, currentPage, FOOD_PER_PAGE);
            List<FoodCategory> listFoodCategory = foodCategoryDao.findAll();
            request.setAttribute("listFood", listFood);
            request.setAttribute("listFoodCategory", listFoodCategory);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("foodName", foodName);
            request.setAttribute("isSearch", true);
            request.getRequestDispatcher("view/homePage/shop.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
