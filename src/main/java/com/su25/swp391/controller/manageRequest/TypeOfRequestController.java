/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.manageRequest;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.FoodDraftDAO;
import com.su25.swp391.dal.implement.LogRequestDAO;
import com.su25.swp391.dal.implement.RequestDAO;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.FoodDraft;
import com.su25.swp391.entity.LogRequest;
import com.su25.swp391.entity.Request;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "TypeOfRequestController", urlPatterns = {"/type-of-request"})
public class TypeOfRequestController extends HttpServlet {

    private FoodDraftDAO foodDraftDAO;
    private RequestDAO requestDAO;
    private FoodDAO foodDAO;
    private LogRequestDAO logReqDAO;

    @Override
    public void init() throws ServletException {
        foodDraftDAO = new FoodDraftDAO();
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
                switch (select) {
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
                        listTypeOfRequest(request, response);
                        break;
                }
                break;
            case "view":
                showViewDetail(request, response);
                break;
            case "reject":
                switch (select) {
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
                        listTypeOfRequest(request, response);
                        break;
                }
                break;
            default:
                listTypeOfRequest(request, response);
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
            default:
                listTypeOfRequest(request, response);
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    // SHOW DETAIL ABOUT A FOOD_DRAFT YOU CHOICE
    private void showViewDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get Id Food_Draft by parameter
        int id = Integer.parseInt(request.getParameter("id"));
        // get select add by parameter
        String select = request.getParameter("select");
        // get search add by parameter
        String search = request.getParameter("search");
        // get sort by id by parameter
        String sort = request.getParameter("sort");
        // Get list Food_Draft by Id
        FoodDraft foodDraft = foodDraftDAO.findById(id);
        // Set Attribut to page detail-food-draft.jsp
        request.setAttribute("foodD", foodDraft);
        request.setAttribute("search", search);
        request.setAttribute("select", select);
        // Come to page detail-food-draft.jsp
        request.getRequestDispatcher("/view/manager/detail-food-draft.jsp").forward(request, response);
    }

    // VIEW LIST ALL OF FOOD_DRAFT
    private void listTypeOfRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get select add by parameter
        String select = request.getParameter("select");
        // get search add by parameter
        String search = request.getParameter("search");
        // get sort by id by parameter
        String sort = request.getParameter("sort");
        // Default page 
        int page = 1;
        // Size in each page can have
        int pageSize = GlobalConfig.SIZE_PAGE;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            // check number of page
            if (page < 1) {
                page = 1;
            }
        } catch (Exception e) {
            page = 1;
        }
        // Get list Food_Draft by search, select page and page-size where r.statusRequest = 'Not done'
        List<FoodDraft> listF = foodDraftDAO.findFoodDraftBySearchFilter(search, select,sort, page, pageSize);
        // Get total pages can have conditional where r.statusRequest = 'Not done'
        int totalFoodDraft = foodDraftDAO.getTotalNumberFoodDraftBySearchFilter(search, select);
        // Split how many page can have in a brower
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        // Set Attribut to page dashboard.jsp
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("currentPage", page);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("type", listType);
        request.setAttribute("search", search);
        request.setAttribute("select", select);
        request.setAttribute("sort", sort);
        
        // come to dashboard.jsp
        request.getRequestDispatcher("/view/manager/dashboard.jsp").forward(request, response);
    }

 

    private void addToFood(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        // Get Id Food_Draft by parameter
        int id = Integer.parseInt(request.getParameter("id"));
        // get select add by parameter
        String select = request.getParameter("select");
        // get search add by parameter
        String search = request.getParameter("search");
        // get sort by id by parameter
        String sort = request.getParameter("sort");
        // Get foodDraft By Id
        FoodDraft food_D = foodDraftDAO.findById(id);
        // After get foodDraft insert it into Food
        Food food = foodDAO.getFromResultFood_Draft(food_D);
        foodDAO.insert(food);
        // Check after update Result in Request
        Boolean checkUpdateResultDone = requestDAO.updateResult(GlobalConfig.STATUS_RESULT_ACCEPT, id);
        // If Update True have a massage
        if (checkUpdateResultDone) {
            Request req = requestDAO.findRequestByFoodDraftId(id);
            LogRequest logR = logReqDAO.getFromResultFoodDAndRequest(req, food_D);
            logReqDAO.insert(logR);
            requestDAO.delete(req);
            session.setAttribute("isSuccess", "Approved and added to food successfully.");

        } else {
            session.setAttribute("isError", "Failed to update the request.");

        }
        response.sendRedirect(request.getContextPath() + "/type-of-request?action=list&select=" + select + "&search=" + search+"&sort"+sort);
    }

    // UPDATE A FOOD_DRAFT TO TABLE FOOD FROM A FOOD_DRAFT GET BY ID_FOOD
    private void updateToFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Get Id Food_Draft by parameter
        int id = Integer.parseInt(request.getParameter("id"));
        // get select add by parameter
        String select = request.getParameter("select");
        // get search add by parameter
        String search = request.getParameter("search");
        // get sort by id by parameter
        String sort = request.getParameter("sort");
        // Get foodDraft By Id
        FoodDraft food_D = foodDraftDAO.findById(id);
        // After get foodDraft insert it into Food
        Food food = foodDAO.getFromResultFood_Draft(food_D);
        Boolean checkUpdateFoodDone = foodDAO.update(food);
        // Check after update Result
        Boolean checkUpdateResultDone = requestDAO.updateResult(GlobalConfig.STATUS_RESULT_ACCEPT, id);
        // If Update True have a massage
        if (checkUpdateFoodDone && checkUpdateResultDone) {
            Request req = requestDAO.findRequestByFoodDraftId(id);
            LogRequest logR = logReqDAO.getFromResultFoodDAndRequest(req, food_D);
            logReqDAO.insert(logR);
            requestDAO.delete(req);
            session.setAttribute("isSuccess", "Update to food successfully.");
        } else {
            session.setAttribute("isError", "Failed to update the request.");
        }
        response.sendRedirect(request.getContextPath() + "/type-of-request?action=list&select=" + select + "&search=" + search+"&sort"+sort);

    }

    // DELETE A FOOD TO TABLE FOOD BY ID_FOOD IN TABLE FOOOD_DRAFT
    private void deleteToFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Get Id Food_Draft by parameter
        int id = Integer.parseInt(request.getParameter("id"));
        // get select add by parameter
        String select = request.getParameter("select");
        // get search add by parameter
        String search = request.getParameter("search");
        // get sort by id by parameter
        String sort = request.getParameter("sort");
        // Get foodDraft By Id
        FoodDraft food_D = foodDraftDAO.findById(id);
        // After get foodDraft insert it into Food
        Food food = foodDAO.getFromResultFood_Draft(food_D);
        Boolean checkDeleteFoodDone = foodDAO.delete(food);
        // Check after update Result
        Boolean checkUpdateResultDone = requestDAO.updateResult(GlobalConfig.STATUS_RESULT_ACCEPT, id);
        if (checkDeleteFoodDone && checkUpdateResultDone) {
            Request req = requestDAO.findRequestByFoodDraftId(id);
            LogRequest logR = logReqDAO.getFromResultFoodDAndRequest(req, food_D);
            logReqDAO.insert(logR);
            requestDAO.delete(req);
            session.setAttribute("isSuccess", "Delete to food successfully.");
        } else {
            session.setAttribute("isError", "Failed to delete the request.");
        }
        response.sendRedirect(request.getContextPath() + "/type-of-request?action=list&select=" + select + "&search=" + search+"&sort"+sort);
    }

    // REJECT A FOOD_DRAFT BY TYPE CREATE OF FOOD
    private void rejectByCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Get Id Food_Draft by parameter
        int id = Integer.parseInt(request.getParameter("id"));
        // get select add by parameter
        String select = request.getParameter("select");
        // get search add by parameter
        String search = request.getParameter("search");
        // get sort by id by parameter
        String sort = request.getParameter("sort");
        // Check update Request status to Reject
        Boolean checkUpdateResult = requestDAO.updateResult(GlobalConfig.STATUS_RESULT_REJECT, id);
        // if check update Request True
        if (checkUpdateResult) {
            Request req = requestDAO.findRequestByFoodDraftId(id);
            FoodDraft food_D = foodDraftDAO.findById(id);
            LogRequest logR = logReqDAO.getFromResultFoodDAndRequest(req, food_D);
            logReqDAO.insert(logR);
            requestDAO.delete(req);
            session.setAttribute("isSuccess", "Reject to food successfully.");
        } else {
            session.setAttribute("isError", "Failed to Reject the request.");
        }

        response.sendRedirect(request.getContextPath() + "/type-of-request?action=list&select=" + select + "&search=" + search+"&sort"+sort);
    }

    // REJECT A FOOD_DRAFT BY TYPE UPDATE OF FOOD
    private void rejectByUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Get Id FoodDraft by parameter
        int id = Integer.parseInt(request.getParameter("id"));
        // get select add by parameter
        String select = request.getParameter("select");
        // get search add by parameter
        String search = request.getParameter("search");
        // get sort by id by parameter
        String sort = request.getParameter("sort");
        // Check update Request status to Reject
        Boolean checkUpdateResult = requestDAO.updateResult(GlobalConfig.STATUS_RESULT_REJECT, id);
        // if check update Request True
        if (checkUpdateResult) {
            Request req = requestDAO.findRequestByFoodDraftId(id);
            FoodDraft food_D = foodDraftDAO.findById(id);
            LogRequest logR = logReqDAO.getFromResultFoodDAndRequest(req, food_D);
            logReqDAO.insert(logR);
            requestDAO.delete(req);
            session.setAttribute("isSuccess", "Reject to food successfully.");
        } else {
            session.setAttribute("isError", "Failed to Reject the request.");
        }
        response.sendRedirect(request.getContextPath() + "/type-of-request?action=list&select=" + select + "&search=" + search+"&sort"+sort);
    }
    // REJECT A FOOD_DRAFT BY TYPE OF DELETE FOOD

    private void rejectByDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Get Id Food_Draft by parameter
        int id = Integer.parseInt(request.getParameter("id"));
        // get select add by parameter
        String select = request.getParameter("select");
        // get search add by parameter
        String search = request.getParameter("search");
        // get sort by id by parameter
        String sort = request.getParameter("sort");
        // update status request to Reject
        Boolean checkUpdateResult = requestDAO.updateResult(GlobalConfig.STATUS_RESULT_REJECT, id);
        // if check update Request True
        if (checkUpdateResult) {
            Request req = requestDAO.findRequestByFoodDraftId(id);
            FoodDraft food_D = foodDraftDAO.findById(id);
            LogRequest logR = logReqDAO.getFromResultFoodDAndRequest(req, food_D);
            logReqDAO.insert(logR);
            requestDAO.delete(req);
            session.setAttribute("isSuccess", "Reject to food successfully.");
        } else {
            session.setAttribute("isError", "Failed to Reject the request.");
        }

        response.sendRedirect(request.getContextPath() + "/type-of-request?action=list&select=" + select + "&search=" + search+"&sort"+sort);
    }

   
}
