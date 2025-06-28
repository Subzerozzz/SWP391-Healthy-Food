/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.nutritionist;

import com.su25.swp391.dal.implement.ComboDAO;
import com.su25.swp391.dal.implement.ComboFoodDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.entity.Combo;
import com.su25.swp391.entity.ComboFood;
import com.su25.swp391.entity.ComboFoodDetails;
import com.su25.swp391.entity.Food;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Hang
 */
@WebServlet(name = "ManagerCombo", urlPatterns = {"/managerCombo"})
public class ManagerCombo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";//default action
        }

        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "viewComboFoodDetail":
                viewComboFoodDetail(request, response);
                break;
            case "inactive":
                deactiveCombo(request, response);
                break;
            case "active":
                active(request, response);
                break;
            case "list":
            default:
                listCombo(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "add":
                addCombo(request, response);
                break;
            case "update":
                updateCombo(request, response);
                break;
            default:
                listCombo(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void active(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comboIdStr = request.getParameter("comboId");
        //kieemr tra xem id co null hay ko
        if (comboIdStr != null && !comboIdStr.isEmpty()) {
            int comboId = Integer.parseInt(comboIdStr);
            ComboDAO comboDao = new ComboDAO();
            boolean isActive = comboDao.activatCombo(comboId);
            if (isActive) {
                //lấy lại côm sau khi kich hoạt
                Combo combo = comboDao.findById(comboId);
                if (combo != null) {
                    request.getSession().setAttribute("toastMessage", "Combo đã được khôi phục");
                    request.getSession().setAttribute("toastType", "success");
                } else {
                    request.getSession().setAttribute("toastMessage", "Không tìm thấy combo để thể kích hoạt");
                    request.getSession().setAttribute("toastType", "error");

                }

            } else {
                request.getSession().setAttribute("toastMessage", "Kích hoạt combo thất bại. Vui lòng thử lại.");
                request.getSession().setAttribute("toastType", "error");

            }
        }
        response.sendRedirect(request.getContextPath() + "/managerCombo");
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //lấy danh sách all các food
        FoodDAO foodDao = new FoodDAO();
        List<Food> foods = foodDao.findAllFoodActive();
        //chuyển đổi food từ list<food> java sang json
        StringBuilder foodJson = new StringBuilder("[");
        for (int i = 0; i < foods.size(); i++) {
            //lay food theo i 
            Food food = foods.get(i);
            foodJson.append("{")
                    .append("\"id\":").append(food.getId()).append(",")
                    .append("\"name\":\"").append(food.getName()).append(",")
                    .append("\"price\":\"").append(food.getPrice()).append(",")
                    .append("\"calo\":").append(food.getCalo())
                    .append("}");
            if (i < foods.size() - 1) {
                foodJson.append(",");
            }
        }
        foodJson.append("]");
        request.setAttribute("foodJson", foodJson.toString());
        request.setAttribute("foods", foods);
        request.getRequestDispatcher("/view/nutritionist/combo/addCombo_1.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //lấy id combo cần edit
        String comboIdStr = request.getParameter("comboId");
        //kieem tra xem id co ton tại không
        if (comboIdStr != null && !comboIdStr.isEmpty()) {
            int comboId = Integer.parseInt(comboIdStr);
            //lay du lieu combo
            ComboDAO comboDao = new ComboDAO();
            Combo combo = comboDao.findById(comboId);

            if (combo != null) {
                //lay food trong combo
                ComboFoodDAO comboFoodDao = new ComboFoodDAO();
                List<ComboFood> comboFood = (List<ComboFood>) comboFoodDao.findById(comboId);
                //lay nhung food voiws trang thái active của nhưng cái đã chon trong combo
                FoodDAO foodDao = new FoodDAO();
                List<Food> allFoods = foodDao.findAllFoodActive();

                //chueyn đôi food thành json cho js
                StringBuilder foodsJson = new StringBuilder("[");
                for (int i = 0; i < allFoods.size(); i++) {
                    Food food = allFoods.get(i);
                    foodsJson.append("{")
                            .append("\"id\":").append(food.getId()).append(",")
                            .append("\"name\":\"").append(food.getName()).append(",")
                            .append("\"price\":\"").append(food.getPrice()).append(",")
                            .append("\"calo\":").append(food.getCalo())
                            .append("}");
                    if (i < allFoods.size() - 1) {
                        foodsJson.append(",");
                    }
                }
                foodsJson.append("]");

                //Convert selected products to JSON for JavaScript
                StringBuilder selectedFoodsJson = new StringBuilder("[");
                for (int i = 0; i < comboFood.size(); i++) {
                    ComboFood cbfood = comboFood.get(i);
                    //lay tung food  theo comboid
                    Food food = foodDao.findById(cbfood.getFoodId());
                    if (food != null) {
                        selectedFoodsJson.append("{")
                                .append("\"id\":").append(food.getId()).append(",")
                                .append("\"name\":\"").append(food.getName()).append(",")
                                .append("\"price\":\"").append(food.getPrice()).append(",")
                                .append("\"quantity\":").append(food.getCalo())
                                .append("}");
                        if( i <comboFood.size() -1){
                            selectedFoodsJson.append("]");
                        }
                    }
                    //set attributes for jsp 
                    request.setAttribute("combo", combo);
                    request.setAttribute("comboFood", comboFood);
                    request.setAttribute("allFoods", allFoods);
                    request.setAttribute("foodJson", foodsJson);
                    request.setAttribute("selectedFoodJson", selectedFoodsJson);
                    
                    request.getRequestDispatcher("/view/nutritionist/combo/editCombo.jsp");
                    return;
                }
               
            }
        }
         // If combo not found or ID not provided, redirect to list
                response.sendRedirect(request.getContextPath()+ "/view/nutritionist/combo/listCombo.jsp");
    }

    private void deactiveCombo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comboIdStr = request.getParameter("comboId");
        //kieemr tra xem id co null hay ko
        if (comboIdStr != null && !comboIdStr.isEmpty()) {
            int comboId = Integer.parseInt(comboIdStr);
            ComboDAO comboDao = new ComboDAO();
            boolean isDeactive = comboDao.deactivateAccount(comboId);
            if (isDeactive) {
                //lấy lại côm sau khi kich hoạt
                Combo combo = comboDao.findById(comboId);
                if (combo != null) {
                    request.getSession().setAttribute("toastMessage", "Combo đã Deactive");
                    request.getSession().setAttribute("toastType", "success");
                } else {
                    request.getSession().setAttribute("toastMessage", "Không tìm thấy combo ");
                    request.getSession().setAttribute("toastType", "error");

                }

            } else {
                request.getSession().setAttribute("toastMessage", "Deactive combo thất bại. Vui lòng thử lại.");
                request.getSession().setAttribute("toastType", "error");
            }
        }
        response.sendRedirect(request.getContextPath() + "/managerCombo");
    }

    private void listCombo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //lay thông tin khi người dùng tìm kiếm
        String serchFilter = request.getParameter("serch");
        String statusFilter = request.getParameter("status");

        //lấy tham số phan trang
        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");
        int currentPage = 1;
        int pageSize = 10;
        try {
            if (pageParam != null && !pageParam.isEmpty()) {
                currentPage = Integer.parseInt(pageParam);
                if (currentPage < 1) {
                    currentPage = 1;
                }
            }
            if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
                pageSize = Integer.parseInt(pageSizeParam);
                if (pageSize < 5) {
                    pageSize = 5;
                }
                if (pageSize > 50) {
                    pageSize = 50;
                }

            }
        } catch (NumberFormatException e) {
            currentPage = 1;
            pageSize = 10;
        }
        ComboDAO comboDao = new ComboDAO();
        //lay ra danh sach combo 
        List<Combo> combo = comboDao.findAllWithPagination(currentPage, pageSize);
        //tinh phan trang
        int totalCombo = comboDao.getTotalComboCount();
        int totalPages = (int) Math.ceil((double) totalCombo / pageSize);
        //set cac thuoc tinhs
        request.setAttribute("listCombo", combo);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalCombo", totalCombo);
        request.setAttribute("totalPages", totalPages);

        //tinh toan pham vi the hien
        int startRecord = (currentPage - 1) * pageSize + 1;
        int endRecord = Math.min(startRecord + pageSize - 1, totalCombo);
        request.setAttribute("startRecord", startRecord);
        request.setAttribute("endRecord", endRecord);
        request.getRequestDispatcher("/view/nutritionist/combo/listCombo.jsp").forward(request, response);

    }

    private void addCombo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("comboName");
            String description = request.getParameter("description");
            String status = request.getParameter("status");
            double discountPrice = Double.parseDouble(request.getParameter("discountPrice"));
            //lay id food
            String foodIdStr = request.getParameter("foodId");
            //lay so luong food tư database
            String quantitiesStr = request.getParameter("quantities");
            String[] foodIds = (foodIdStr != null && !foodIdStr.isEmpty()) ? foodIdStr.split(",") : null;
            String[] quantities = (quantitiesStr != null && !quantitiesStr.isEmpty()) ? quantitiesStr.split(",") : null;
            //validate các dữ liệu nhập vào
            Map<String, String> errors = validateComboData(name, discountPrice, quantities, foodIds, null);
            //kiêm tra lỗi nếu không co lỗi đi tiếp
            if (!errors.isEmpty()) {
                request.getSession().setAttribute("errors", errors);
                // Lưu lại dữ liệu form để hiển thị lại
                Map<String, String[]> formData = new HashMap<>();
                formData.put("comboName", new String[]{name});
                formData.put("description", new String[]{description});
                formData.put("status", new String[]{status});
                formData.put("discountPrice", new String[]{String.valueOf(discountPrice)});
                formData.put("foodId", new String[]{foodIdStr});
                formData.put("quantities", new String[]{quantitiesStr});
                //giữ lại dữ liệu sai đau bắt người dùng nhậ lại thôi
                request.getSession().setAttribute("formData", formData);

                response.sendRedirect(request.getContextPath() + "/managerCombo?action=add");
                return;
            }
            //tạo ra fooddao để láy dữ liệu trong dataFoodDao
            FoodDAO fooddao = new FoodDAO();
            //tính giá của các food
            Double originalPrice = calculateOriginalPrice(foodIds, quantities, fooddao);

            Combo newCombo = Combo.builder()
                    .comboName(name)
                    .description(description)
                    .originalPrice(originalPrice)
                    .discountPrice(discountPrice)
                    .status(status)
                    .build();

            ComboDAO comboDao = new ComboDAO();

            int comboId = comboDao.insert(newCombo);
            if (comboId > 0) {
                ComboFoodDAO combofoodDao = new ComboFoodDAO();
                for (int i = 0; i < foodIds.length; i++) {
                    int foodId = Integer.parseInt(foodIds[i]);
                    int quantity = Integer.parseInt(quantities[i]);
                    combofoodDao.insert(ComboFood.builder()
                            .comboId(comboId)
                            .foodId(foodId)
                            .quantityInCombo(quantity)
                            .build());
                }
                request.getSession().setAttribute("toastMessage", "Combo created successfully");
                request.getSession().setAttribute("toastType", "success");
            } else {

                request.getSession().setAttribute("toastMessage", "Combo creation failed");
                request.getSession().setAttribute("toastType", "error");
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi khi tạo combo:");
            e.printStackTrace();
            request.getSession().setAttribute("toastMessage", "Error: " + e.getMessage());
            request.getSession().setAttribute("toastType", "error");
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/managerCombo");
    }

    private void updateCombo(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void viewComboFoodDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComboDAO combodao = new ComboDAO();
        //lay id của combo
        int comboId = Integer.parseInt(request.getParameter("id"));
        //lấy thông tin combo
        Combo combo = combodao.findById(comboId);
        //lấy danh sách trong mon ăn trong combo
        List<ComboFoodDetails> foodDetails = combodao.getComboFoodDetails(comboId);

        request.setAttribute("combo", combo);
        request.setAttribute("foodDetails", foodDetails);
        request.getRequestDispatcher("/view/nutritionist/combo/comboFoodDetail.jsp").forward(request, response);

    }

    private Map<String, String> validateComboData(String name, Double discountPrice, String[] quantities, String[] foodId, Integer comboId) {
        Map<String, String> errors = new HashMap<>();

        //validate name
        if (name == null || name.trim().isEmpty()) {
            errors.put("name", "Tên combo không được để null");
        } else if (!name.matches("^[a-zA-Z0-9_ ]+$")) {
            errors.put("name", "combo không được chưa kí tự đặc biệt");
        }
        //validate food
        if (foodId == null || foodId.length == 0) {
            errors.put("foodId", "You must select at least one food for the combo");
        }
        //validate quantity
        if (quantities != null) {
            for (int i = 0; i < quantities.length; i++) {
                try {
                    int quantity = Integer.parseInt(quantities[i]);
                    if (quantity <= 0) {
                        errors.put("quantity_" + i, "Quantity must be greater than 0");
                    }
                } catch (NumberFormatException e) {
                    errors.put("quantity_" + i, "Invalid quantity");
                }
            }
        }
        // Validate discount price (must be less than original price)
        if (discountPrice != null && foodId != null && quantities != null) {
            FoodDAO foodDAO = new FoodDAO();
            double originalPrice = calculateOriginalPrice(foodId, quantities, foodDAO);

            if (discountPrice >= originalPrice) {
                errors.put("discount_price", "Discount price must be less than original price");
            }
        }

        // Add validation for duplicate products
        if (foodId != null) {
            Set<String> uniqueFood = new HashSet<>();
            for (int i = 0; i < foodId.length; i++) {
                if (!uniqueFood.add(foodId[i])) {
                    errors.put("duplicate_product", "Duplicate products are not allowed in a combo");
                    break;
                }
            }
        }

        return errors;
    }
//caculate the originalPrice combo base on select

    private Double calculateOriginalPrice(String[] foodIds, String[] quantities, FoodDAO foodDAO) {
        double originalPrice = 0;
        for (int i = 0; i < foodIds.length; i++) {
            int foodId = Integer.parseInt(foodIds[i]);
            int quantity = Integer.parseInt(quantities[i]);
            Food food = foodDAO.findById(foodId);
            if (food != null) {
                originalPrice += food.getPrice().doubleValue() * quantity;
            }
        }
        return originalPrice;
    }
}
