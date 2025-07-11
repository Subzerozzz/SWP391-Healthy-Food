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
                    case "Pending":
                        listPending(request,response);
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
            case "option":
                switch (select) {
                    case "CREATE":
                        listTypeOfCreate(request, response);
                        break;
                    case "UPDATE":
                        listTypeOfUpdate(request, response);
                        break;
                    case "DELETE":
                        listTypeOfDelete(request, response);
                        break;
                        case "Pending":
                        listPending(request,response);
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
            case "search":
                showFoodDraftList(request, response);
                break;
            default:
                // listTypeOfRequest(request,response);  
                listTypeOfUpdate(request, response);
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
        // Get list Food_Draft by Id
        FoodDraft foodDraft = foodDraftDAO.findById(id);
        // Set Attribut to page detail-food-draft.jsp
        request.setAttribute("foodD", foodDraft);
        // Come to page detail-food-draft.jsp
        request.getRequestDispatcher("/view/manager/detail-food-draft.jsp").forward(request, response);
    }

    // VIEW LIST ALL OF FOOD_DRAFT
    private void listTypeOfRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        // Get list Food_Draft by filter page and page-size
        List<FoodDraft> listF = foodDraftDAO.getFoodDraftByPage(page, pageSize);
        // Get total pages can have conditional where r.statusRequest = 'Not done'
        int totalFoodDraft = foodDraftDAO.getTotalFoodDraftCount();
        // Split how many page can have in a brower
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        // Set Attribut to page dashboard.jsp
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("currentPage", page);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("type", listType);
        // come to dashboard.jsp
        request.getRequestDispatcher("/view/manager/dashboard.jsp").forward(request, response);
    }

    // VIEW LIST ALL OF FOOD_DRAFT BY TYPE OF CREATE
    private void listTypeOfCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Default page
        int page = 1;
        // Size in each page can have
        int pageSize = GlobalConfig.SIZE_PAGE;
        String select = request.getParameter("select");
        try {
            page = Integer.parseInt(request.getParameter("page"));
            // check number of page
            if (page < 1) {
                page = 1;
            }
        } catch (Exception e) {
            page = 1;
        }
        // Get list Food_Draft by filter select and page and page-size
        List<FoodDraft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        // Get total pages can have conditional where r.statusRequest = 'Not done'
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        // Split how many page can have in a brower
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        // Set Attribut to page dashboard.jsp
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("currentPage", page);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("type", listType);
        // Come to dashboard.jsp
        request.getRequestDispatcher("/view/manager/dashboard.jsp").forward(request, response);
    }

    // VIEW LIST ALL OF FOOD_DRAFT BY TYPE OF UPDATE
    private void listTypeOfUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Default page
        int page = 1;
        // Size in each page can have
        int pageSize = GlobalConfig.SIZE_PAGE;
        String select = request.getParameter("select");
        try {
            page = Integer.parseInt(request.getParameter("page"));
            // check number of page
            if (page < 1) {
                page = 1;
            }
        } catch (Exception e) {
            page = 1;
        }
        // Get list Food_Draft by filter page and page-size
        List<FoodDraft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        // Get total pages can have conditional where r.statusRequest = 'Not done'
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        // Split how many page can have in a brower
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        // Set Attribut to page dashboard.jsp
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("currentPage", page);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("type", listType);
        // Come to dashboard.jsp
        request.getRequestDispatcher("/view/manager/dashboard.jsp").forward(request, response);
    }

    // VIEW LIST ALL OF FOOD_DRAFT BY TYPE OF DELETE
    private void listTypeOfDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Default page
        int page = 1;
        // Size in each page can have
        int pageSize = GlobalConfig.SIZE_PAGE;
        String select = request.getParameter("select");
        try {
            page = Integer.parseInt(request.getParameter("page"));
            // check number of page
            if (page < 1) {
                page = 1;
            }
        } catch (Exception e) {
            page = 1;
        }
        // Get list Food_Draft by filter page and page-size
        List<FoodDraft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        // Get total pages can have conditional where r.statusRequest = 'Not done'
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        // Split how many page can have in a brower
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        // Set Attribut to page dashboard.jsp
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("currentPage", page);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("type", listType);
        // Come to dashboard.jsp
        request.getRequestDispatcher("/view/manager/dashboard.jsp").forward(request, response);
    }

    // INSERT A FOOD_DRAFT TO TABLE FOOD
    private void addToFood(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get Id Food_Draft by parameter
        int id = Integer.parseInt(request.getParameter("id"));
        // get select add by parameter
        String select = request.getParameter("select");
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

        // Get total pages can have conditional where r.statusRequest = 'Not done'
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        // Split how many page can have in a brower
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        // Check if already handle don't do it again
        if (requestDAO.checkReload(id)) {
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
                HttpSession session = request.getSession();
                session.setAttribute("isSuccess", true);
            }
        }
        // Set Attribut to page dashboard.jsp
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("currentPage", page);
        List<FoodDraft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("type", listType);
        // Come to page dashboard.jsp
        request.getRequestDispatcher("/view/manager/dashboard.jsp").forward(request, response);
    }

    // UPDATE A FOOD_DRAFT TO TABLE FOOD FROM A FOOD_DRAFT GET BY ID_FOOD
    private void updateToFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get Id Food_Draft by parameter
        int id = Integer.parseInt(request.getParameter("id"));
        // get select add by parameter
        String select = request.getParameter("select");
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
        // Get total pages can have conditional where r.statusRequest = 'Not done'
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        // Split how many page can have in a brower
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        // Check if already handle don't do it again
        if (requestDAO.checkReload(id)) {
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
                HttpSession session = request.getSession();
                session.setAttribute("isSuccess", true);
            }
        }
        // Set Attribut to page dashboard.jsp
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("currentPage", page);
        List<FoodDraft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("type", listType);
        // Come to page dashboard.jsp
        request.getRequestDispatcher("/view/manager/dashboard.jsp").forward(request, response);
    }

    // DELETE A FOOD TO TABLE FOOD BY ID_FOOD IN TABLE FOOOD_DRAFT
    private void deleteToFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get Id Food_Draft by parameter
        int id = Integer.parseInt(request.getParameter("id"));
        // get select add by parameter
        String select = request.getParameter("select");
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
        // Get total pages can have conditional where r.statusRequest = 'Not done'
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        // Split how many page can have in a brower
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        // Check if already handle don't do it again
        if (requestDAO.checkReload(id)) {
            // Get foodDraft By Id
            FoodDraft food_D = foodDraftDAO.findById(id);
            // After get foodDraft insert it into Food
            Food food = foodDAO.getFromResultFood_Draft(food_D);
            Boolean checkDeleteFoodDone = foodDAO.delete(food);
            // Check after update Result
            Boolean checkUpdateResultDone = requestDAO.updateResult(GlobalConfig.STATUS_RESULT_ACCEPT, id);
            Request req = requestDAO.findRequestByFoodDraftId(id);
            LogRequest logR = logReqDAO.getFromResultFoodDAndRequest(req, food_D);
            logReqDAO.insert(logR);
            requestDAO.delete(req);
            HttpSession session = request.getSession();
            session.setAttribute("isSuccess", true);

        }
        // Set Attribut to page dashboard.jsp
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("currentPage", page);
        List<FoodDraft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("type", listType);
        // Come to page dashboard.jsp
        request.getRequestDispatcher("/view/manager/dashboard.jsp").forward(request, response);
    }

    // REJECT A FOOD_DRAFT BY TYPE CREATE OF FOOD
    private void rejectByCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get Id Food_Draft by parameter
        int id = Integer.parseInt(request.getParameter("id"));
        // get select add by parameter
        String select = request.getParameter("select");
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
        // Get total pages can have conditional where r.statusRequest = 'Not done'
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        // Split how many page can have in a brower
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        // Check if already handle don't do it again
        if (requestDAO.checkReload(id)) {
            // Check update Request status to Reject
            Boolean checkUpdateResult = requestDAO.updateResult(GlobalConfig.STATUS_RESULT_REJECT, id);
            // if check update Request True
            if (checkUpdateResult) {
                Request req = requestDAO.findRequestByFoodDraftId(id);
                FoodDraft food_D = foodDraftDAO.findById(id);
                LogRequest logR = logReqDAO.getFromResultFoodDAndRequest(req, food_D);
                logReqDAO.insert(logR);
                requestDAO.delete(req);
                HttpSession session = request.getSession();
                session.setAttribute("isSuccess", true);
            }
        }
        // Set Attribut to page dashboard.jsp
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("currentPage", page);
        List<FoodDraft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("type", listType);
        // Come to page dashboard.jsp
        request.getRequestDispatcher("/view/manager/dashboard.jsp").forward(request, response);
    }

    // REJECT A FOOD_DRAFT BY TYPE UPDATE OF FOOD
    private void rejectByUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get Id FoodDraft by parameter
        int id = Integer.parseInt(request.getParameter("id"));
        // get select add by parameter
        String select = request.getParameter("select");
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
        // Get total pages can have conditional where r.statusRequest = 'Not done'
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        // Split how many page can have in a brower
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        // Check if already handle don't do it again
        if (requestDAO.checkReload(id)) {
            // Check update Request status to Reject
            Boolean checkUpdateResult = requestDAO.updateResult(GlobalConfig.STATUS_RESULT_REJECT, id);
            // if check update Request True
            if (checkUpdateResult) {
                Request req = requestDAO.findRequestByFoodDraftId(id);
                FoodDraft food_D = foodDraftDAO.findById(id);
                LogRequest logR = logReqDAO.getFromResultFoodDAndRequest(req, food_D);
                logReqDAO.insert(logR);
                requestDAO.delete(req);
                HttpSession session = request.getSession();
                session.setAttribute("isSuccess", true);
            }
        }
        // Set Attribut to page dashboard.jsp
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("currentPage", page);
        List<FoodDraft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("type", listType);
        // Come to page dashboard.jsp
        request.getRequestDispatcher("/view/manager/dashboard.jsp").forward(request, response);
    }
    // REJECT A FOOD_DRAFT BY TYPE OF DELETE FOOD

    private void rejectByDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get Id Food_Draft by parameter
        int id = Integer.parseInt(request.getParameter("id"));
        // get select add by parameter
        String select = request.getParameter("select");
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
        // Get total pages can have conditional where r.statusRequest = 'Not done'
        int totalFoodDraft = foodDraftDAO.getTotalFoodDCountBySelect(select);
        // Split how many page can have in a brower
        int totalPages = (int) Math.ceil((double) totalFoodDraft / pageSize);
        // Check if already handle don't do it again
        if (requestDAO.checkReload(id)) {
            // Check if already handle don't do it again
            Boolean checkUpdateResult = requestDAO.updateResult(GlobalConfig.STATUS_RESULT_REJECT, id);
            // if check update Request True
            if (checkUpdateResult) {
                Request req = requestDAO.findRequestByFoodDraftId(id);
                FoodDraft food_D = foodDraftDAO.findById(id);
                LogRequest logR = logReqDAO.getFromResultFoodDAndRequest(req, food_D);
                logReqDAO.insert(logR);
                requestDAO.delete(req);
                HttpSession session = request.getSession();
                session.setAttribute("isSuccess", true);
            }
        }
        // Set Attribut to page dashboard.jsp
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("select", select);
        request.setAttribute("currentPage", page);
        List<FoodDraft> listF = foodDraftDAO.findAllByType(select, page, pageSize);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("listFoodDraft", listF);
        request.setAttribute("type", listType);
        // Come to page dashboard.jsp
        request.getRequestDispatcher("/view/manager/dashboard.jsp").forward(request, response);
    }

    private void showFoodDraftList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters for filtering 
        String searchTitle = request.getParameter("name");
        // Pagination parameters
        int page = 1;
        int pageSize = GlobalConfig.SIZE_PAGE; // Number of blogs per page
        try {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                if (page < 1) {
                    page = 1;
                }
            }
        } catch (NumberFormatException e) {
            page = 1;
        }
        // Get FoodDraft from database
        List<FoodDraft> food_D = foodDraftDAO.findFoodDraftWithFilter(searchTitle, page, pageSize);
        int totalFood_D = foodDraftDAO.countFoodDraftWithFilter(searchTitle);
        // Calculate total pages
        int totalPages = (int) Math.ceil((double) totalFood_D / pageSize);
        // Set Attribut to page dashboard.jsp
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("listFoodDraft", food_D);
        request.setAttribute("currentPage", page);
        List<String> listType = foodDraftDAO.findAllType();
        request.setAttribute("type", listType);
        // come to dashboard.jsp
        request.getRequestDispatcher("/view/manager/dashboard.jsp").forward(request, response);
    }

    private void listPending(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          List<FoodDraft> listF = null;
          request.setAttribute("listFoodDraft", listF);
          request.getRequestDispatcher("/view/manager/dashboard.jsp").forward(request, response);
    }

}
