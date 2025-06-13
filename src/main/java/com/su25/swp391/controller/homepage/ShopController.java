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
import java.util.ArrayList;
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
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        switch (action) {
//            case "viewAll":
//                showFoodList(request, response);
//                break;
//            case "foodByCategory":
//                showFoodByCategory(request, response);
//                break;
//            case "search":
//                searchFood(request, response);
//                break;
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
        processRequest(request, response);
    }

    private void showFoodList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy ra totalPage
        List<Food> list1 = foodDao.findAll();
        Integer totalRecord = list1.size();
        Integer totalPage = totalRecord % FOOD_PER_PAGE == 0
                ? totalRecord / FOOD_PER_PAGE
                : totalRecord / FOOD_PER_PAGE + 1;
        // Lấy ra currentPage
        Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        // Lấy ra min-max price
        Double maxPrice = foodDao.getMaxPrice();
        Double minPrice = foodDao.getMinPrice();
        // Lấy ra 12 bản ghi đầu tiên
        List<Food> listFood = foodDao.findRecordByPage(FOOD_PER_PAGE, currentPage);
        List<FoodCategory> listFoodCategory = foodCategoryDao.findAll();
        request.setAttribute("listFood", listFood);
        request.setAttribute("listFoodCategory", listFoodCategory);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        // Set 1 biến cho category là all
        request.setAttribute("category", 0);
        //set min max price về giao diện
        request.setAttribute("maxPrice", maxPrice);
        request.setAttribute("minPrice", minPrice);
        request.getRequestDispatcher("view/homePage/shop.jsp").forward(request, response);
    }

    private void showFoodByCategory(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer category = Integer.parseInt(request.getParameter("category"));
            // Lấy ra totalPage
            List<Food> list1 = foodDao.findByCategoryId(category);
            Integer totalRecord = list1.size();
            Integer totalPage = totalRecord % FOOD_PER_PAGE == 0
                    ? totalRecord / FOOD_PER_PAGE
                    : totalRecord / FOOD_PER_PAGE + 1;
            // Lấy ra currentPage
            Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            //Xử lý min max
            // Lấy ra 12 bản ghi đầu tiên
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
            // Lấy ra totalPage
            List<Food> list1 = foodDao.getFoodByName(foodName);
            Integer totalRecord = list1.size();
            Integer totalPage = totalRecord % FOOD_PER_PAGE == 0
                    ? totalRecord / FOOD_PER_PAGE
                    : totalRecord / FOOD_PER_PAGE + 1;
            // Lấy ra currentPage
            Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            // Lấy ra 12 bản ghi đầu tiên theo tên
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

    private void shopDetail(HttpServletRequest request, HttpServletResponse response) {

        try {
            // Lấy ra id
            Integer id = Integer.parseInt(request.getParameter("id"));
            // Lấy ra thông tin món ăn
            Food food = foodDao.findById(id);
            // Có thể kiểm tra food bằng null nếu cần

            // Lấy ra categoryID
            Integer categoryId = food.getCategory_id();
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
            
            //Lấy currentPage
            Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
             
            //Xử lý minPrice và maxPrice
            Double minPrice = null;
            Double maxPrice = null;
            
            if(minPriceStr != null && !minPriceStr.isEmpty()){
                try {
                    minPrice = Double.parseDouble(minPriceStr);
                } catch (Exception e) {
                    System.out.println("Error parse min price: " + e.getMessage());
                } 
            }
            else{
                minPrice = 0.0;
            }
            
            if(maxPriceStr != null && !maxPriceStr.isEmpty()){
                try {
                    maxPrice = Double.parseDouble(maxPriceStr);
                } catch (Exception e) {
                    System.out.println("Error parse min price: " + e.getMessage());
                } 
            }
            else{
                maxPrice = foodDao.getMaxPrice() + 5;
            }
            
            //Lọc sản phẩm theo các tiêu chí + 12 ban ghi dau
            List<Food> listFood = foodDao.getFoodWithFitlers(foodName, minPrice, maxPrice, category, currentPage, FOOD_PER_PAGE);
            //Lay ra danh sach category
            List<FoodCategory> listFoodCategory = foodCategoryDao.findAll();

            //Tính toán totalPage
            List<Food> listFood1 = foodDao.getFoodWithFitlers(foodName, minPrice, maxPrice, category, null, FOOD_PER_PAGE);
            Integer totalOfRecord = listFood1.size();
            Integer totalPage = totalOfRecord % FOOD_PER_PAGE == 0 ? totalOfRecord/FOOD_PER_PAGE : totalOfRecord/FOOD_PER_PAGE + 1;
            
            //set cac gia tri moi
            request.setAttribute("listFood", listFood);
            request.setAttribute("listFoodCategory", listFoodCategory);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPage", totalPage);
            
            
            //set lai data cu
            request.setAttribute("foodName", foodName);
            request.setAttribute("minPrice", minPrice);
            request.setAttribute("maxPrice", maxPrice - 1);
            request.setAttribute("category", category);
            //Sau khi lọc các sản phẩm trả ra JSP dữ liệu + các dữ liệu cũ
            request.getRequestDispatcher("view/homePage/shop.jsp").forward(request, response);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
