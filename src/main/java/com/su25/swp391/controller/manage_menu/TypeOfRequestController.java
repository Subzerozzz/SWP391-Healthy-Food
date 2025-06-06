/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.su25.swp391.controller.manage_menu;

import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.Food_DraftDAO;
import com.su25.swp391.dal.implement.LogRequestDAO;
import com.su25.swp391.dal.implement.RequestDAO;
import com.su25.swp391.entity.Food_Draft;
import com.su25.swp391.entity.Request;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name="TypeOfRequestController", urlPatterns={"/type-of-request"})
public class TypeOfRequestController extends HttpServlet {
   
    private Food_DraftDAO foodDraftDAO;
    private RequestDAO requestDAO;
    private FoodDAO foodDAO;
    private LogRequestDAO logReqDAO;
    @Override
    public void init() throws ServletException{
        foodDraftDAO = new Food_DraftDAO();
        requestDAO = new RequestDAO();
        foodDAO = new FoodDAO();
        logReqDAO = new LogRequestDAO();
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       // Get request from Nutritionist
        String action = request.getParameter("action");
        String select = request.getParameter("select");
        if (action == null) {
            action = "list"; // Assign action = list 
        }
        
        switch (action) {
            case "accept":
                 switch(select){
                    case "CREATE":
                        addToFood(request, response);
                        break;
                    case "UPDATE":
                        updateToFood(request, response);
                        break;
                    case "DELETE":
                        deleteToFood(request, response);
                        break;
                    default:
                        listTypeOfRequest(request,response);
                        break;
                }
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
                 switch(select){
                    case "CREATE":
                        rejectByCreate(request, response);
                        break;
                    case "UPDATE":
                        rejectByUpdate(request, response);
                        break;
                    case "DELETE":
                        rejectByDelete(request, response);
                        break;
                    default:
                        listTypeOfRequest(request,response);
                        break;
                }
                break;
            case "option":
                switch(select){
                    case "CREATE":
                        listTypeOfCreate(request, response);
                        break;
                    case "UPDATE":
                        listTypeOfUpdate(request, response);
                        break;
                    case "DELETE":
                        listTypeOfDelete(request, response);
                        break;
                    default:
                        listTypeOfRequest(request,response);
                        break;
                }
                break;
            default:
                  listTypeOfRequest(request,response);
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

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void listCategories(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

     private void rejectFoodDraft(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void activateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void deactivateCategory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
   // SHOW DETAIL ABOUT A FOOD_DRAFT YOU CHOICE
    private void showViewDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Food_Draft foodDraft = foodDraftDAO.findById(id);
        request.setAttribute("foodD", foodDraft);
        
        request.getRequestDispatcher("/view/nutritionist/detail-food-draft.jsp").forward(request, response);
    }    
     
    // VIEW LIST ALL OF FOOD_DRAFT
    private void listTypeOfRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          int page = 1;
        int pageSize =2 ;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            if(page < 1){
                page  = 1;
            }
        } catch (Exception e) {
            page =1;
        }
          List<Food_Draft> listF = foodDraftDAO.getFoodDraftByPage(page, pageSize);
          
             int totalFoodDraft = foodDraftDAO.getTotalFoodDraftCount();
             int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
             request.setAttribute("totalPages", totalPages);
        
         
          request.setAttribute("listFoodDraft", listF);
          request.setAttribute("currentPage", page);
          
       //   List<Food_Draft> list = foodDraftDAO.findAll();
          List<String> listType = foodDraftDAO.findAllType();
      //    request.setAttribute("listFoodDraft", list);
          request.setAttribute("type", listType);
          request.getRequestDispatcher("/view/nutritionist/dashboard.jsp").forward(request, response);
    }
    
    // VIEW LIST ALL OF FOOD_DRAFT BY TYPE OF CREATE
     private void listTypeOfCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int pageSize =2 ;
        String select = request.getParameter("select");
        try {
            page = Integer.parseInt(request.getParameter("page"));
            if(page < 1){
                page  = 1;
            }
        } catch (Exception e) {
            page =1;
        }
          List<Food_Draft> listF = foodDraftDAO.findAllByType(select,page, pageSize);
          
             int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
             int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
             request.setAttribute("totalPages", totalPages);
        
          request.setAttribute("select", select);
          request.setAttribute("listFoodDraft", listF);
          request.setAttribute("currentPage", page);
        
      //  List<Food_Draft> list = foodDraftDAO.findAllByType(select,page,pageSize);
        List<String> listType = foodDraftDAO.findAllType();
       // request.setAttribute("listFoodDraft", list);
        request.setAttribute("type", listType);
        request.getRequestDispatcher("/view/nutritionist/dashboard.jsp").forward(request, response);
    }
    
     // VIEW LIST ALL OF FOOD_DRAFT BY TYPE OF UPDATE
    private void listTypeOfUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int pageSize =2 ;
        String select = request.getParameter("select");
        try {
            page = Integer.parseInt(request.getParameter("page"));
            if(page < 1){
                page  = 1;
            }
        } catch (Exception e) {
            page =1;
        }
          List<Food_Draft> listF = foodDraftDAO.findAllByType(select,page, pageSize);
          
             int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
             int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
             request.setAttribute("totalPages", totalPages);
        
          request.setAttribute("select", select);
          request.setAttribute("listFoodDraft", listF);
          request.setAttribute("currentPage", page);
        
      //  List<Food_Draft> list = foodDraftDAO.findAllByType(select,page,pageSize);
        List<String> listType = foodDraftDAO.findAllType();
       // request.setAttribute("listFoodDraft", list);
        request.setAttribute("type", listType);
        request.getRequestDispatcher("/view/nutritionist/dashboard.jsp").forward(request, response);
    }
    
    // VIEW LIST ALL OF FOOD_DRAFT BY TYPE OF DELETE
    private void listTypeOfDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int pageSize =2 ;
        String select = request.getParameter("select");
        try {
            page = Integer.parseInt(request.getParameter("page"));
            if(page < 1){
                page  = 1;
            }
        } catch (Exception e) {
            page =1;
        }
          List<Food_Draft> listF = foodDraftDAO.findAllByType(select,page, pageSize);
          
             int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
             int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
             request.setAttribute("totalPages", totalPages);
        
          request.setAttribute("select", select);
          request.setAttribute("listFoodDraft", listF);
          request.setAttribute("currentPage", page);
        
      //  List<Food_Draft> list = foodDraftDAO.findAllByType(select,page,pageSize);
        List<String> listType = foodDraftDAO.findAllType();
       // request.setAttribute("listFoodDraft", list);
        request.setAttribute("type", listType);
        request.getRequestDispatcher("/view/nutritionist/dashboard.jsp").forward(request, response);
    }
    
    // INSERT A FOOD_DRAFT TO TABLE FOOD
    private void addToFood(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String select = request.getParameter("select");
        int page = 1;
        int pageSize = 2;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        } catch (Exception e) {
            page = 1;
        }
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        Food_Draft foodDraft = foodDraftDAO.findById(id);
        foodDAO.insertFoodfromFoodDraft(foodDraft);
        Boolean check = requestDAO.updateResult("Accept", id);
        if (check) {
            request.setAttribute("mess", "Accept Create successful");
            Request req = requestDAO.findById(id);
            logReqDAO.insertToLogRequest(req, foodDraft);
            requestDAO.delete(req);
        } else {
            request.setAttribute("mess", "Accept Create not successful");
        }
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("currentPage", page);
        List<Food_Draft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("type", listType);
        request.getRequestDispatcher("/view/nutritionist/dashboard.jsp").forward(request, response);
    }
    
    // UPDATE A FOOD_DRAFT TO TABLE FOOD FROM A FOOD_DRAFT GET BY ID_FOOD
    private void updateToFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String select = request.getParameter("select");
        int page = 1;
        int pageSize = 2;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        } catch (Exception e) {
            page = 1;
        }
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        Food_Draft foodD = foodDraftDAO.findById(id);
        Boolean check1 = foodDAO.updateFoodbyFoodDraft(foodD);
        Boolean check2 = requestDAO.updateResult("Accept", id);
        if (check1 && check2) {
            request.setAttribute("mess", "Accept Update successful");
            Request req = requestDAO.findById(id);
            logReqDAO.insertToLogRequest(req, foodD);
            requestDAO.delete(req);
        } else {
            request.setAttribute("mess", "Accept Update not successful");
        }
        
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("currentPage", page);
        List<Food_Draft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("type", listType);
        request.getRequestDispatcher("/view/nutritionist/dashboard.jsp").forward(request, response);
    }
    
    // DELETE A FOOD TO TABLE FOOD BY ID_FOOD IN TABLE FOOOD_DRAFT
    private void deleteToFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String select = request.getParameter("select");
        int page = 1;
        int pageSize = 2;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        } catch (Exception e) {
            page = 1;
        }
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
         Food_Draft food_D = foodDraftDAO.findById(id);
        Boolean check1 = foodDAO.deleteById(food_D);
        Boolean check2 = requestDAO.updateResult("Accept", id);
        if(check1 && check2){
            request.setAttribute("mess", "Accept Delete successful");
            Request req = requestDAO.findById(id);
            logReqDAO.insertToLogRequest(req, food_D);
            requestDAO.delete(req);
        }else{
            request.setAttribute("mess", "Accept Delete not successful");
        }
       
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("currentPage", page);
        List<Food_Draft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("type", listType);
        request.getRequestDispatcher("/view/nutritionist/dashboard.jsp").forward(request, response);
    }

    // REJECT A FOOD_DRAFT BY TYPE CREATE OF FOOD
    private void rejectByCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String select = request.getParameter("select");
        int page = 1;
        int pageSize = 2;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        } catch (Exception e) {
            page = 1;
        }
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        
        Boolean check = requestDAO.updateResult("Reject", id);
        if (check) {
            request.setAttribute("mess", "Reject Create successful");
            Request req = requestDAO.findById(id);
            Food_Draft food_D = foodDraftDAO.findById(id);
            logReqDAO.insertToLogRequest(req, food_D);
            requestDAO.delete(req);
        } else {
            request.setAttribute("mess", "Reject Create not successful");
        }
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("currentPage", page);
        List<Food_Draft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("type", listType);
        request.getRequestDispatcher("/view/nutritionist/dashboard.jsp").forward(request, response);
    }
    // REJECT A FOOD_DRAFT BY TYPE UPDATE OF FOOD
    private void rejectByUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         int id = Integer.parseInt(request.getParameter("id"));
        String select = request.getParameter("select");
        int page = 1;
        int pageSize = 2;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        } catch (Exception e) {
            page = 1;
        }
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        
        Boolean check = requestDAO.updateResult("Reject", id);
        if (check) {
            request.setAttribute("mess", "Reject Update successful");
            Request req = requestDAO.findById(id);
            Food_Draft food_D = foodDraftDAO.findById(id);
            logReqDAO.insertToLogRequest(req, food_D);
            requestDAO.delete(req);
        } else {
            request.setAttribute("mess", "Reject Update not successful");
        }
       request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("currentPage", page);
        List<Food_Draft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("type", listType);
        request.getRequestDispatcher("/view/nutritionist/dashboard.jsp").forward(request, response);
    }
   // REJECT A FOOD_DRAFT BY TYPE OF DELETE FOOD
    private void rejectByDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String select = request.getParameter("select");
        int page = 1;
        int pageSize = 2;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        } catch (Exception e) {
            page = 1;
        }
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        
       
        Boolean check = requestDAO.updateResult("Reject", id);
        if (check) {
            request.setAttribute("mess", "Reject Delete successful");
            Request req = requestDAO.findById(id);
            Food_Draft food_D = foodDraftDAO.findById(id);
            logReqDAO.insertToLogRequest(req, food_D);
            requestDAO.delete(req);
        } else {
            request.setAttribute("mess", "Reject Delete not successful");
        }
         request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("currentPage", page);
        List<Food_Draft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("type", listType);
        request.getRequestDispatcher("/view/nutritionist/dashboard.jsp").forward(request, response);
    }

    
}
