/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.nutritionist;

import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.FoodCategoryDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.FoodDraftDAO;
import com.su25.swp391.dal.implement.RequestDAO;
import com.su25.swp391.entity.Account;
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
@WebServlet(name = "ManageFoodController", urlPatterns = { "/manage-food" })
public class ManageFoodController extends HttpServlet {

  public static final int RECORD_PER_PAGE = 10;

  FoodDAO foodDao = new FoodDAO();
  FoodDraftDAO foodDraftDao = new FoodDraftDAO();
  RequestDAO requestDao = new RequestDAO();
  FoodCategoryDAO categoryDao = new FoodCategoryDAO();
  AccountDAO accountDao = new AccountDAO();

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
      case "filter":
        filterByCategory(request, response);
        break;
      case "search":
        searchByName(request, response);
        break;
      case "pagination":
        paginationPage(request, response);
        break;
      case "paginationFilter":
        paginationFilter(request, response);
        break;
      case "paginationSearch":
        paginationSearch(request, response);
        break;
      case "request":
        showRequestNotDone(request, response);
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

  private void showAddForm(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    List<FoodCategory> listCategory = new ArrayList<>();
    listCategory = categoryDao.findAll();
    request.setAttribute("listCategory", listCategory);
    // chuyen huong nguoi dung
    request.getRequestDispatcher("view/nutritionist/menu/addFood.jsp").forward(request, response);
  }

  private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    // Lấy ra id của food
    Integer idUpdate = Integer.parseInt(request.getParameter("id"));
    // lấy ra Food cần update
    Food foodNeedUpdate = foodDao.findById(idUpdate);
    // lấy ra danh sách category
    List<FoodCategory> listCategory = new ArrayList<>();
    listCategory = categoryDao.findAll();
    // set các giá trị để gửi lên updateFood.jsp
    request.setAttribute("listCategory", listCategory);
    request.setAttribute("foodUpdate", foodNeedUpdate);
    request.setAttribute("idUpdate", idUpdate);
    // Chuyển về trang updateFood
    request.getRequestDispatcher("view/nutritionist/menu/updateFood.jsp").forward(request, response);
  }

  private void addFood(HttpServletRequest request, HttpServletResponse response) {
    try {
      // Lấy các dữ liệu từ form
      String name = request.getParameter("name");
      String description = request.getParameter("description");
      Integer category_id = Integer.parseInt(request.getParameter("category"));
      String priceStr = request.getParameter("price");
      String type = "CREATE";
      String caloStr = request.getParameter("calo");
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

        // Tạo thư mục nếu chưa tồn tại
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
          boolean created = uploadDir.mkdirs();
        }

        // Lưu file
        String fullPath = uploadPath + File.separator + fileName;
        System.out.println("Saving file to: " + fullPath);
        filePart.write(fullPath);

        // Đường dẫn tương đối để lưu vào database
        fileName = "uploads/products/" + fileName;
      }

      // Fix tạm nutriId sau này dựa vào tài khoản lấy được trên session về để lấy ra
      // id
      Integer nutriId = 24;
      // Validate dữ liệu
      Map<String, String> errors = new HashMap<>();
      // Validate name
      if (name == null || name.trim().isEmpty()) {
        errors.put("name", "Chưa điền tên món ăn!");
      } else {
        Food existFood = foodDao.checkExistFoodName(name);
        if (existFood != null) {
          errors.put("name", "Tên món ăn đã tồn tại trong hệ thống!");
        }
      }
      // Validate price
      if (priceStr == null || priceStr.trim().isEmpty()) {
        errors.put("price", "Chưa điền giá tiền!");
      } else {
        try {
          Double price = Double.parseDouble(priceStr);
          if (price < 0) {
            errors.put("price", "Giá tiền không thể có giá trị âm");
          }
        } catch (Exception e) {
          errors.put("price", "Giá tiền phải có định dạng là số");
        }
      }
      // Validate description
      if (description == null || description.trim().isEmpty()) {
        errors.put("description", "Chưa điền mô tả món ăn!");
      }
      // Validte image
      if (filePart == null || filePart.getSize() == 0) {
        errors.put("filename", "Chưa tải lên ảnh cho món ăn!");
      }
      // Vaidate calo
      if (caloStr == null || caloStr.trim().isEmpty()) {
        errors.put("calo", "Chưa điền calo cho món ăn!");
      } else {
        try {
          Double calo = Double.parseDouble(caloStr);
          if (calo < 0) {
            errors.put("calo", "Calo không thể có giá trị âm!");
          }
        } catch (Exception e) {
          errors.put("calo", "Calo phải có định dạng là số!");
        }

      }

      if (!errors.isEmpty()) {
        for (Map.Entry<String, String> entry : errors.entrySet()) {
          System.out.println("Error: " + entry.getKey() + " - " + entry.getValue());
        }
        request.setAttribute("errors", errors);
        request.setAttribute("formData", request.getParameterMap());
        showAddForm(request, response);
        return;
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
          .calo(Double.parseDouble(caloStr))
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

      // chuyển hướng về trang dashboard
      // Gửi về 1 tín hiệu là yêu cầu thêm đã được gửi đi
      HttpSession session = request.getSession();
      session.setAttribute("isAdd", true);
      response.sendRedirect("manage-food?action=request");

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void deleteFood(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    Integer id = Integer.parseInt(request.getParameter("id"));
    // Lấy ra đối tượng Food
    Food foodDelete = foodDao.findById(id);
    // Tạo 1 đối tượng foodDraft
    FoodDraft foodDraft = FoodDraft.builder()
        .name(foodDelete.getName())
        .description(foodDelete.getDescription())
        .price(foodDelete.getPrice())
        .image_url(foodDelete.getImage_url())
        .category_id(foodDelete.getCategory_id())
        .created_at(foodDelete.getCreated_at())
        .updated_at(foodDelete.getUpdated_at())
        .type("DELETE")
        .food_id(id)
        .nutri_id(foodDelete.getNutri_id())
        .calo(foodDelete.getCalo())
        .build();
    // Lưu vào DB
    foodDraftDao.insert(foodDraft);
    // Tạo 1 đối tượng request
    String result = "Pending";
    String statusRequest = "Not done";
    Integer foodDraftId = foodDraftDao.getBiggestId();

    Request newRequest = Request.builder()
        .result(result)
        .foodDraftId(foodDraftId)
        .statusRequest(statusRequest)
        .build();

    requestDao.insert(newRequest);
    // chuyển hướng về trang dashboard
    HttpSession session = request.getSession();
    session.setAttribute("isDelete", true);
    response.sendRedirect("manage-food?action=request");
  }

  private void updateFood(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    try {
      // Lấy ra id cần update
      String id = request.getParameter("id");
      // Lấy ra các giá trị mới được gửi lên
      String name = request.getParameter("name");
      String description = request.getParameter("description");
      Integer category_id = Integer.parseInt(request.getParameter("category"));
      String priceStr = request.getParameter("price");
      String type = "UPDATE";
      String caloStr = request.getParameter("calo");
      // Lấy ra create_at
      Food oldFood = foodDao.findById(Integer.parseInt(id));
      Timestamp created_at = oldFood.getCreated_at();
      // Lấy ra nutri_id
      Integer nutriId = oldFood.getNutri_id();
      // Lấy ra update_at
      Timestamp updated_at = new Timestamp(System.currentTimeMillis());
      // Lấy ra image
      String fileName = null;
      Part filePart = request.getPart("fileName");
      String oldImage = request.getParameter("oldImage");
      if (filePart != null && filePart.getSize() > 0) {
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
      } else {
        fileName = oldImage;
      }
      // Validate dữ liệu
      Map<String, String> errors = new HashMap<>();
      // Validate name
      if (name == null || name.trim().isEmpty()) {
        errors.put("name", "Food name is required");
      }
      // Validate price
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
      // Validate description
      if (description == null || description.trim().isEmpty()) {
        errors.put("description", "Description is required");
      }

      // Vaidate calo
      if (caloStr == null || caloStr.trim().isEmpty()) {
        errors.put("calo", "Chưa điền calo cho món ăn!");
      } else {
        try {
          Double calo = Double.parseDouble(caloStr);
          if (calo < 0) {
            errors.put("calo", "Calo không thể có giá trị âm!");
          }
        } catch (Exception e) {
          errors.put("calo", "Calo phải có định dạng là số!");
        }

      }

      if (!errors.isEmpty()) {
        for (Map.Entry<String, String> entry : errors.entrySet()) {
          System.out.println("Error: " + entry.getKey() + " - " + entry.getValue());
        }
        request.setAttribute("errors", errors);
        request.setAttribute("formData", request.getParameterMap());
        showUpdateForm(request, response);
        return;
      }

      // Tạo 1 bản foodDraft với type là Update
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
          .food_id(Integer.parseInt(id))
          .calo(Double.parseDouble(caloStr))
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
      // chuyển hướng về trang dashboard và thông báo gửi về update đã được gửi đi
      HttpSession session = request.getSession();
      session.setAttribute("isUpdate", true);
      response.sendRedirect("manage-food?action=request");

    } catch (Exception e) {
      System.out.println(e);
    }

  }

  private void filterByCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      // lấy categoryID
      Integer categoryID = Integer.parseInt(request.getParameter("id"));
      // lấy số lượng page
      List<Food> listFood1 = foodDao.findByCategoryId(categoryID);
      Integer totalOfRecord = listFood1.size();
      Integer totalPage = totalOfRecord % RECORD_PER_PAGE == 0 ? totalOfRecord / RECORD_PER_PAGE
          : totalOfRecord / RECORD_PER_PAGE + 1;
      // lấy ra 10 bản ghi đầu tiên
      List<Food> listFood = foodDao.findRecordByPageForCategory(categoryID, 1,RECORD_PER_PAGE);
      // lấy ra listCategory
      List<FoodCategory> listCategory = categoryDao.findAll();
      // set giá trị
      request.setAttribute("listCategory", listCategory);
      request.setAttribute("totalPage", totalPage);
      request.setAttribute("listFood", listFood);
      request.setAttribute("categoryID", categoryID);
      request.setAttribute("currentPage", 1);
      request.getRequestDispatcher("view/nutritionist/menu/filterFood.jsp").forward(request, response);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void searchByName(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // lấy name
    String foodName = request.getParameter("name");
    // lấy totalPage
    List<Food> listFood1 = foodDao.getFoodByName(foodName);
    Integer totalOfRecord = listFood1.size();
    Integer totalPage = totalOfRecord % RECORD_PER_PAGE == 0 ? totalOfRecord / RECORD_PER_PAGE
        : totalOfRecord / RECORD_PER_PAGE + 1;
    // Lấy ra 10 bản ghi đầu tiên
    List<Food> listFood = foodDao.getRecordByPageForSearch(foodName, 1);
    // lấy ra listCategory
    List<FoodCategory> listCategory = categoryDao.findAll();
    // set gia tri vao request
    request.setAttribute("totalPage", totalPage);
    request.setAttribute("listFood", listFood);
    request.setAttribute("listCategory", listCategory);
    request.setAttribute("foodName", foodName);
    request.setAttribute("currentPage", 1);
    request.getRequestDispatcher("view/nutritionist/menu/searchFood.jsp").forward(request, response);

  }

  private void paginationPage(HttpServletRequest request, HttpServletResponse response) {
    String currentPageStr = request.getParameter("page");
    Integer currentPage = null;
    List<Food> listFood1 = foodDao.findAll();
    Integer totalOfRecord = listFood1.size();
    Integer totalPage = totalOfRecord % RECORD_PER_PAGE == 0 ? totalOfRecord / RECORD_PER_PAGE
        : totalOfRecord / RECORD_PER_PAGE + 1;
    try {
      currentPage = Integer.parseInt(currentPageStr);
      if (currentPage <= 0) {
        currentPage = 1;
      }

      List<Food> listFood = foodDao.findRecordByPage(RECORD_PER_PAGE,currentPage);
      List<FoodCategory> listCategory = categoryDao.findAll();
      request.setAttribute("totalPage", totalPage);
      request.setAttribute("listFood", listFood);
      request.setAttribute("currentPage", currentPage);
      request.setAttribute("listCategory", listCategory);
      request.getRequestDispatcher("view/nutritionist/menu/dashboard.jsp").forward(request, response);
    } catch (Exception e) {
      currentPage = 1;
    }
  }

  private void paginationFilter(HttpServletRequest request, HttpServletResponse response) {
    Integer categoryID = Integer.parseInt(request.getParameter("category"));
    String currentPageStr = request.getParameter("page");
    Integer currentPage = null;
    List<Food> listFood1 = foodDao.findByCategoryId(categoryID);
    Integer totalOfRecord = listFood1.size();
    Integer totalPage = totalOfRecord % RECORD_PER_PAGE == 0 ? totalOfRecord / RECORD_PER_PAGE
        : totalOfRecord / RECORD_PER_PAGE + 1;
    try {
      currentPage = Integer.parseInt(currentPageStr);
      if (currentPage < 0) {
        currentPage = 1;
      }

      List<Food> listFood = foodDao.findRecordByPageForCategory(categoryID, currentPage,RECORD_PER_PAGE);
      List<FoodCategory> listCategory = categoryDao.findAll();
      request.setAttribute("totalPage", totalPage);
      request.setAttribute("listFood", listFood);
      request.setAttribute("currentPage", currentPage);
      request.setAttribute("listCategory", listCategory);
      request.setAttribute("categoryID", categoryID);
      request.getRequestDispatcher("view/nutritionist/menu/filterFood.jsp").forward(request, response);
    } catch (Exception e) {
      currentPage = 1;
    }
  }

  private void paginationSearch(HttpServletRequest request, HttpServletResponse response) {
    String foodName = request.getParameter("name");
    String currentPageStr = request.getParameter("page");
    Integer currentPage = null;
    List<Food> listFood1 = foodDao.getFoodByName(foodName);
    Integer totalOfRecord = listFood1.size();
    Integer totalPage = totalOfRecord % RECORD_PER_PAGE == 0 ? totalOfRecord / RECORD_PER_PAGE
        : totalOfRecord / RECORD_PER_PAGE + 1;
    try {
      currentPage = Integer.parseInt(currentPageStr);
      if (currentPage < 0) {
        currentPage = 1;
      }

      List<Food> listFood = foodDao.getRecordByPageForSearch(foodName, currentPage);
      List<FoodCategory> listCategory = categoryDao.findAll();
      request.setAttribute("totalPage", totalPage);
      request.setAttribute("listFood", listFood);
      request.setAttribute("currentPage", currentPage);
      request.setAttribute("listCategory", listCategory);
      request.setAttribute("foodName", foodName);
      request.getRequestDispatcher("view/nutritionist/menu/searchFood.jsp").forward(request, response);
    } catch (Exception e) {
      currentPage = 1;
    }
  }

  private void showRequestNotDone(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Integer currentPage = request.getParameter("page") == null ? 1
        : Integer.parseInt(request.getParameter("page"));

    // Tính toán tổng số Page
    List<Request> listRequest1 = requestDao.findAll();
    // lấy ra tổng số bản ghi
    Integer totalOfRecord = listRequest1.size();
    // tính ra tổng số page
    Integer totalPage = totalOfRecord % RECORD_PER_PAGE == 0 ? totalOfRecord / RECORD_PER_PAGE
        : totalOfRecord / RECORD_PER_PAGE + 1;
    // Lấy ra 10 request not done cuối cùng để đảm bảo mới nhất
    List<Request> listRequestNotDone = requestDao.getRequestByStatusForPage("Not done", currentPage);
    // Dựa vào foodDraftId trong list not done để lấy ra list đó trong foodDraft
    List<FoodDraft> listFoodDraft = new ArrayList<>();
    for (Request a : listRequestNotDone) {
      listFoodDraft.add(foodDraftDao.findById(a.getFoodDraftId()));
    }
    // Lấy ra thông tin của các nutri
    List<Account> listNutri = accountDao.findAccountByRole("nutri");
    // Lấy ra listCatgory
    List<FoodCategory> listCategory = categoryDao.findAll();
    // Trả cái listFoodDraft về giao diện
    request.setAttribute("listFoodDraft", listFoodDraft);
    request.setAttribute("listRequestNotDone", listRequestNotDone);
    request.setAttribute("listNutri", listNutri);
    request.setAttribute("listCategory", listCategory);
    // set totalPage và currentPage
    request.setAttribute("totalPage", totalPage);
    request.setAttribute("currentPage", currentPage);
    request.getRequestDispatcher("view/nutritionist/menu/requestFood.jsp").forward(request, response);

  }

}
