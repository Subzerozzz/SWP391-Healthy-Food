/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.manage_menu;

import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.Food_DraftDAO;
import com.su25.swp391.dal.implement.RequestDAO;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.Food_Draft;
import com.su25.swp391.entity.Request;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/menu-category"})
public class RequestController extends HttpServlet {

    private Food_DraftDAO foodDraftDAO;
    private RequestDAO requestDAO;
    private FoodDAO foodDAO;
    @Override
    public void init() throws ServletException{
        foodDraftDAO = new Food_DraftDAO();
        requestDAO = new RequestDAO();
        foodDAO = new FoodDAO();
        
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get request from Nutritionist
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Assign action = list 
          }
        switch (action) {
            case "accept":
                addToFood(request, response);
                break;
            case "view":
                showViewDetail(request, response);
                break;
            case "deactivate":
                deactivateCategory(request, response);
                break;
            case "activate":
                activateCategory(request, response);
                break;
            case "reject":
                rejectFoodDraft(request,response);
                break;
            default:
               listFoodDraft(request,response);
                break;
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Default action
        }

        switch (action) {
            case "add":
                
                break;
            case "update":
                updateCategory(request, response);
                break;
            default:
                listCategories(request, response);
                break;
        }
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

    private void addToFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Food_Draft food = foodDraftDAO.findById(id);
        int ok1 = foodDAO.insertFoodfromFoodDraft(food);
        Boolean ok2 = requestDAO.updateRequestFoodDraftById(id);
        Boolean ok3 =foodDraftDAO.updateStatusAccept(id);
        String mess = "ok";
        if(ok1>0 && ok2 && ok3){
            mess = "Accpetion Successfully";
        }else{
            mess = "Accept Failure";
        }
        request.setAttribute("mess", mess);
        List<Food_Draft> listFoodDraft = foodDraftDAO.findAllFoodDrartByRequest();
        request.setAttribute("listFoodDraft", listFoodDraft);
        request.getRequestDispatcher("/view/nutritionist/dashboard.jsp").forward(request, response);
     }

    private void showViewDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Food_Draft foodDraft = foodDraftDAO.findById(id);
        request.setAttribute("foodD", foodDraft);
        request.getRequestDispatcher("/view/nutritionist/detail-food-draft.jsp").forward(request, response);
    }
//        response.sendRedirect(request.getContextPath() + "view/nutritionist/dashboard.jsp");
    

    private void deactivateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void activateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void listCategories(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    private void addCategory(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            // Lấy thông tin từ form
//            String name = request.getParameter("name");
//            if (!foodDAO.isCategoryNameExist(name, -1)) {
//                Food newCategory = Food
//                        .builder()
//                        .image_url(name)
//                        .build();
//                int newId = foodDAO.insert(newCategory);
//                if (newId > 0) {
//                    request.getSession().setAttribute("toastMessage", "Thêm danh mục thành công");
//                    request.getSession().setAttribute("toastType", "success");
//                }
//                response.sendRedirect(request.getContextPath() + "../view/nutritionist/addCategory.jsp");
//
//            } else {
//                request.getSession().setAttribute("error", "Name already");
//                response.sendRedirect(request.getContextPath() + "../view/nutritionist/dashboard.jsp");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private Map<String, String> ValidateCategoryData(int id, String name) {
     Map<String, String> error = new HashMap<>();
//        // Validate Name
//        if (name == null || name.trim().isEmpty()) {
//            error.put("name", "Category name is required");
//        } else if (name.length() > 100) {
//            error.put("name", "Category name must be less than 100 characteristic");
//        } else {
//            // Check name already existed
//            boolean nameExist = (name == null)
//                    ? foodDAO.isCategoryNameExist(name, -1)
//                    : foodDAO.isCategoryNameExist(name, id);
//
//        }
//        if (foodDAO.isCategoryNameExist(name, id)) {
//            error.put("name", "Name already Existed");
//        } else {
//            error.put("name", "Name is valid");
//        }
      return error;
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void listFoodDraft(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       List<Food_Draft> listFoodDraft = foodDraftDAO.findAllFoodDrartByRequest();
       request.setAttribute("listFoodDraft", listFoodDraft);
       request.getRequestDispatcher("/view/nutritionist/dashboard.jsp").forward(request, response);
    }

    private void rejectFoodDraft(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int id = Integer.parseInt(request.getParameter("id"));
       Boolean ok1 = foodDraftDAO.updateStatusReject(id);
       Boolean ok2 = requestDAO.updateRequestFoodDraftById(id);
       String mess = "";
       if(ok1&& ok2 ){
            mess = "Reject Successfully";
        }else{
            mess = "Reject Failure";
        }
        request.setAttribute("mess", mess);
        List<Food_Draft> listFoodDraft = foodDraftDAO.findAllFoodDrartByRequest();
        request.setAttribute("listFoodDraft", listFoodDraft);
        request.getRequestDispatcher("/view/nutritionist/dashboard.jsp").forward(request, response);
    
    }

}
