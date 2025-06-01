/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.su25.swp391.controller.manage_menu;

import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.Food_DraftDAO;
import com.su25.swp391.dal.implement.RequestDAO;
import com.su25.swp391.entity.Food_Draft;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name="HandleFoodController", urlPatterns={"/request-create-food"})
public class RequestCreateFoodController extends HttpServlet {
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

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listFoodDraft(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       List<Food_Draft> listFoodDraft = foodDraftDAO.findAllFoodByCreateRequest();
       request.setAttribute("listFoodDraft", listFoodDraft);
       request.getRequestDispatcher("/view/nutritionist/handle-create-food.jsp").forward(request, response);
    }

    private void addToFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Food_Draft food = foodDraftDAO.findByIdCreateFood(id);
        int count = foodDAO.insertFoodfromFoodDraft(food);
        Boolean done = requestDAO.updateRequestFoodDraftById(id);
        Boolean approve =foodDraftDAO.updateStatusAccept(id);
        String mess = "";
        if(count>0 && done && approve){
            mess = "Accpetion Successfully";
        }else{
            mess = "Accept Failure";
        }
        request.setAttribute("mess", mess);
        List<Food_Draft> listFoodDraft = foodDraftDAO.findAllFoodByCreateRequest();
        request.setAttribute("listFoodDraft", listFoodDraft);
        request.getRequestDispatcher("/view/nutritionist/handle-create-food.jsp").forward(request, response);
     }

    private void showViewDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Food_Draft foodDraft = foodDraftDAO.findById(id);
        request.setAttribute("foodD", foodDraft);
        request.getRequestDispatcher("/view/nutritionist/detail-food-draft.jsp").forward(request, response);
 
    }

    private void deactivateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void activateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void rejectFoodDraft(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Boolean ok1 = foodDraftDAO.updateStatusReject(id);
        Boolean ok2 = requestDAO.updateRequestFoodDraftById(id);
        String mess = "";
        if (ok1 && ok2) {
            mess = "Reject Successfully";
        } else {
            mess = "Reject Failure";
        }
        request.setAttribute("mess", mess);
        List<Food_Draft> listFoodDraft = foodDraftDAO.findAllFoodByCreateRequest();
        request.setAttribute("listFoodDraft", listFoodDraft);
        request.getRequestDispatcher("/view/nutritionist/handle-create-food.jsp").forward(request, response);
    
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void listCategories(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
