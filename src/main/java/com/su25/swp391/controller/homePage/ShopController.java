/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.homePage;

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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ShopController", urlPatterns = {"/shop"})
public class ShopController extends HttpServlet {

    public static final int FOOD_PER_PAGE = 9;
    FoodDAO foodDao = new FoodDAO();
    FoodCategoryDAO foodCategoryDao = new FoodCategoryDAO();
    


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        switch (action) {
            case "shopDetail":
                shopDetail(request, response);
                break;
            default:
                filterFood(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void shopDetail(HttpServletRequest request, HttpServletResponse response) {

        try {
            // Lấy ra id
            Integer id = Integer.parseInt(request.getParameter("id"));
            // Lấy ra thông tin món ăn
            Food food = foodDao.findById(id);
            // Lấy ra các food trong category này
            List<Food> listFoodCategory = foodDao.findAll();
            // Lấy ra 4 món ăn đầu tiên liên quan
            List<Food> listRelated = new ArrayList<>();
            int count = 0;
            for (Food a : listFoodCategory) {
                if (count < 4) {
                    listRelated.add(a);
                } else {
                    break;
                }
                count++;
            }
            // trả về foodDetail
            request.setAttribute("foodDetail", food);
            request.setAttribute("listRelated", listRelated);
            request.getRequestDispatcher("view/homePage/shopDetail.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void filterFood(HttpServletRequest request, HttpServletResponse response) {
        try {
            String foodName = request.getParameter("foodName") == null ? null : request.getParameter("foodName");
            String minPriceStr = request.getParameter("selectedMin");
            String maxPriceStr = request.getParameter("selectedMax");
            Integer category = request.getParameter("category") == null ? 0 : Integer.parseInt(request.getParameter("category"));
            String sortParam = request.getParameter("sort");
            String minCaloStr = request.getParameter("selectedMinCalo");
            String maxCaloStr = request.getParameter("selectedMaxCalo");
            
            //Xử lý sort
            if(sortParam == null || sortParam == ""){
                sortParam = "default";
            }

            //Lấy currentPage
            Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

            //Xử lý minPrice và maxPrice
            Double minPrice = null;
            Double maxPrice = null;
            
            //minPrice và maxPrice trong hệ thống
            Double minPriceDefault = 0.0;
            Double maxPriceDefault = foodDao.findMaxPrice() + 5;

            if (minPriceStr != null && !minPriceStr.isEmpty()) {
                try {
                    minPrice = Double.parseDouble(minPriceStr);
                } catch (Exception e) {
                    System.out.println("Error parse min price: " + e.getMessage());
                }
            } else {
                minPrice = minPriceDefault;
            }

            if (maxPriceStr != null && !maxPriceStr.isEmpty()) {
                try {
                    maxPrice = Double.parseDouble(maxPriceStr);
                } catch (Exception e) {
                    System.out.println("Error parse max price: " + e.getMessage());
                }
            } else {
                maxPrice = maxPriceDefault;
            }
            
            //Xử lý minCalo và maxCalo
            
            Double minCalo = null;
            Double maxCalo = null;
            
            //minCalo và maxCalo trong hệ thống 
            Double minCaloDefault = 0.0;
            Double maxCaloDefault = foodDao.findMaxCalo();
            
             if (minCaloStr != null && !minCaloStr.isEmpty()) {
                try {
                    minCalo = Double.parseDouble(minCaloStr);
                } catch (Exception e) {
                    System.out.println("Error parse min calo: " + e.getMessage());
                }
            } else {
                minCalo = minCaloDefault;
            }

            if (maxCaloStr != null && !maxCaloStr.isEmpty()) {
                try {
                    maxCalo = Double.parseDouble(maxCaloStr);
                } catch (Exception e) {
                    System.out.println("Error parse max calo: " + e.getMessage());
                }
            } else {
                maxCalo = maxCaloDefault;
            }
            

            //Lọc sản phẩm theo các tiêu chí + 9 ban ghi dau
            List<Food> listFood = foodDao.getFoodWithFitlers(foodName, minPrice, maxPrice, category, currentPage, sortParam, minCalo, maxCalo , FOOD_PER_PAGE);
            //Lay ra danh sach category
            List<FoodCategory> listFoodCategory = foodCategoryDao.findAll();

            //Tính toán totalPage
            List<Food> listFood1 = foodDao.getFoodWithFitlers(foodName, minPrice, maxPrice, category, null, sortParam, minCalo, maxCalo , FOOD_PER_PAGE);
            Integer totalOfRecord = listFood1.size();
            Integer totalPage = totalOfRecord % FOOD_PER_PAGE == 0 ? totalOfRecord / FOOD_PER_PAGE : totalOfRecord / FOOD_PER_PAGE + 1;

            //set cac data
            request.setAttribute("listFood", listFood);
            request.setAttribute("listFoodCategory", listFoodCategory);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("totalOfRecord", totalOfRecord);
            request.setAttribute("minPriceDefault", minPriceDefault);
            request.setAttribute("maxPriceDefault", maxPriceDefault);
            request.setAttribute("minCaloDefault", minCaloDefault);
            request.setAttribute("maxCaloDefault", maxCaloDefault);
            

            //cac data cu
            request.setAttribute("foodName", foodName);
            request.setAttribute("minPrice", minPrice);
            request.setAttribute("maxPrice", maxPrice);
            request.setAttribute("minCalo", minCalo);
            request.setAttribute("maxCalo", maxCalo);
            request.setAttribute("category", category);
            request.setAttribute("sort", sortParam);
            //Sau khi lọc các sản phẩm trả ra JSP dữ liệu + các dữ liệu cũ
            request.getRequestDispatcher("view/homePage/shop.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
