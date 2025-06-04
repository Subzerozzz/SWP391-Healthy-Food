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
import jakarta.servlet.annotation.MultipartConfig;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
@MultipartConfig
@WebServlet(name = "ManageFoodController", urlPatterns = {"/manage-food"})
public class ManageFoodController extends HttpServlet {

    FoodDAO foodDao = new FoodDAO();
    FoodDraftDAO foodDraftDao = new FoodDraftDAO();
    RequestDAO requestDao = new RequestDAO();
    FoodCategoryDAO categoryDao = new FoodCategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }
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
            case "viewDetail":
                viewFoodDeatail(request,response);
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
        // lấy ra Food cần update
        Food foodNeedUpdate = foodDao.findById(foodId);
        HttpSession session = request.getSession();
        session.setAttribute("foodUpdate", foodNeedUpdate);
        // Chuyển về trang updateFood
        response.sendRedirect("view/nutritionist/menu/updateFood.jsp");
    }
    
    private void viewFoodDeatail(HttpServletRequest request, HttpServletResponse response) {
    }

    private void addFood(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Lấy các dữ liệu từ form
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Integer category_id = Integer.parseInt(request.getParameter("category"));
            String priceStr = request.getParameter("price");
            String type = "CREATE";
            // Lấy ngày tạo và ngày cập nhật
            Timestamp created_at = new Timestamp(System.currentTimeMillis());
            Timestamp updated_at = new Timestamp(System.currentTimeMillis());

            // Lấy ảnh từ form
            String fileName = null;
            Part filePart = request.getPart("filename");
            if (filePart != null && filePart.getSize() > 0) {
                System.out.println("File received: " + filePart.getSubmittedFileName() + ", size: " + filePart.getSize());

                // Lấy tên file gốc
                String originalFileName = filePart.getSubmittedFileName();
                // Tạo tên file duy nhất
                fileName = System.currentTimeMillis() + "_" + originalFileName;

                // Đường dẫn lưu file
                String uploadPath = request.getServletContext().getRealPath("/uploads/products/");
                System.out.println("Upload path: " + uploadPath);

                // Tạo thư mục nếu chưa tồn tại
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    boolean created = uploadDir.mkdirs();
                    System.out.println("Directory created: " + created);
                }

                // Lưu file
                String fullPath = uploadPath + File.separator + fileName;
                System.out.println("Saving file to: " + fullPath);
                filePart.write(fullPath);

                // Đường dẫn tương đối để lưu vào database
                fileName = "uploads/products/" + fileName;
            }

            Integer nutriId = 1;
            // Validate dữ liệu
            Map<String, String> errors = new HashMap<>();
            //Validate name
            if (name == null || name.trim().isEmpty()) {
                errors.put("name", "Food name is required");
            } else {
                Food existFood = foodDao.checkExistFoodName(name);
                if (existFood != null) {
                    errors.put("name", "Food name is already exists");
                }
            }
            //Validate price
            if (priceStr == null || priceStr.trim().isEmpty()) {
                errors.put("price", "Price is required");
            } else {
                try {
                    Double price = Double.parseDouble(priceStr);
                    if (price < 0) {
                        errors.put("price", "Price cannot be negative");
                    }
                } catch (Exception e) {
                    errors.put("price", "Price must be number");
                }
            }
            //Validate description
            if(description == null || description.trim().isEmpty()){
                errors.put("description", "Description is required");
            }
            //Validte image
            if(filePart == null || filePart.getSize() == 0){
                errors.put("filename", "Food image is required");
            }
            
            if(!errors.isEmpty()){
                request.setAttribute("errors", errors);
                request.setAttribute("formData", request.getParameterMap());
                showAddForm(request, response);
            }

            // Tạo 1 đối tượng FoodDraft và lưu vào DB
            FoodDraft newFoodDraft = FoodDraft.builder()
                    .name(name)
                    .description(description)
                    .price(Double.parseDouble(priceStr))
                    .image_url(fileName)
                    .category_id(category_id)
                    .created_at(created_at)
                    .updated_at(updated_at)
                    .type(type)
                    .nutri_id(nutriId)
                    .build();
            foodDraftDao.insert(newFoodDraft);
            // Tạo 1 đối tượng Request và lưu vào DB
            String result = "Pending";
            String statusRequest = "Not done";
            Integer foodDraftId = foodDraftDao.getBiggestId();

            Request newRequest = Request.builder()
                    .result(result)
                    .foodDraftId(foodDraftId)
                    .statusRequest(statusRequest)
                    .build();

            requestDao.insert(newRequest);

        } catch (Exception e) {
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
