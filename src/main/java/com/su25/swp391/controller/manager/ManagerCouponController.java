/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.manager;

import com.su25.swp391.dal.implement.CouponDAO;
import com.su25.swp391.entity.Coupon;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Predator
 */
@WebServlet(name = "ManagerCouponController", urlPatterns = {"/ManagerCoupon"})
public class ManagerCouponController extends HttpServlet {

    private CouponDAO couponDAO;

    public void init() {
        couponDAO = new CouponDAO();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listCouponDoGet(request, response);
                break;
            case "add":
                showAddDoGet(request, response);
                break;
            case "edit":
                showEditDoGet(request, response);
                break;
            case "view":
                showDetailDoGet(request, response);
                break;
            case "delete":
                deleteCouponDoGet(request, response);
                break;
            case "search":
                searchByNameDoGet(request, response);
                break;
            case "filter":
                handleFilter(request, response);
                break;
            default:
                listCouponDoGet(request, response);
                break;
        }
    }

    private void showAddDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/manager/addCoupon.jsp");
        dispatcher.forward(request, response);
    }

    private void listCouponDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int currentPage = Integer.parseInt(indexPage);
        int totalCoupons = couponDAO.getTotalCoupon();
        int totalPage = totalCoupons / 10;
        if (totalCoupons % 10 != 0) {
            totalPage++;
        }
        List<Coupon> coupon = couponDAO.pagingCoupon(currentPage);
        request.setAttribute("coupons", coupon);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/manager/listCoupon.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Coupon coupon = couponDAO.findById(id);
        if (coupon != null) {
            request.setAttribute("coupon", coupon);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/manager/editCoupon.jsp");
            dispatcher.forward(request, response);
        } else {
            request.getSession().setAttribute("toastMessage", "Coupon not found");
            request.getSession().setAttribute("toastType", "error");
            response.sendRedirect(request.getContextPath() + "/view/manager/editCoupon.jsp");
        }
    }

    private void showDetailDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Coupon coupon = couponDAO.findById(id);
        request.setAttribute("coupon", coupon);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/manager/viewdetailCoupon.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteCouponDoGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Coupon coupon = couponDAO.findById(id);
        if (coupon != null) {
            boolean delete = couponDAO.delete(coupon);
            if (delete) {
                HttpSession session = request.getSession();
                request.getSession().setAttribute("toastMessage", "Coupon delete succesfull");
                request.getSession().setAttribute("toastType", "success");
                session.setAttribute("isDelete", true);
            } else {
                request.getSession().setAttribute("toastMessage", "Fail to Coupon ");
                request.getSession().setAttribute("toastType", "error");
            }
        } else {
            request.getSession().setAttribute("toastMessage", "Coupon not found");
            request.getSession().setAttribute("toastType", "error");
        }

        response.sendRedirect(request.getContextPath() + "/ManagerCoupon");
    }

    private void searchByNameDoGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String searchKeyword = request.getParameter("search");
        String indexStr = request.getParameter("index");
        int currentPage = 1;
        int pageSize = 10; // số bản ghi mỗi trang, bạn có thể chỉnh lại

        if (indexStr != null) {
            try {
                currentPage = Integer.parseInt(indexStr);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/ManagerCoupon?action=list");
            return;
        }
        searchKeyword = searchKeyword.trim();
        int offset = (currentPage - 1) * pageSize;
        // Lấy danh sách coupon theo từ khóa và phân trang
        List<Coupon> listCoupon = couponDAO.searchCouponsByCodeorDescription(searchKeyword, offset, pageSize);
        // Lấy tổng số coupon phù hợp để tính tổng trang
        int totalCoupons = couponDAO.countCouponsBySearch(searchKeyword);
        int totalPage = (int) Math.ceil((double) totalCoupons / pageSize);
        request.setAttribute("coupons", listCoupon);
        request.setAttribute("search", searchKeyword);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.getRequestDispatcher("/view/manager/listCoupon.jsp").forward(request, response);
    }

    private void handleFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String discountType = request.getParameter("discounttype"); // lấy đúng tham số discounttype (trùng với JSP)
    
    // Lấy tham số phân trang
    String pageParam = request.getParameter("index");
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

    // Gọi DAO lọc và đếm tổng bản ghi
    List<Coupon> filteredCoupon = couponDAO.filterCouponWithPagination(discountType, currentPage, pageSize);
    int totalCoupons = couponDAO.getTotalCouponCountWithFilter(discountType);
    int totalPages = (int) Math.ceil((double) totalCoupons / pageSize);

    // Đưa dữ liệu vào request để JSP hiển thị
    request.setAttribute("coupons", filteredCoupon);
    request.setAttribute("currentPage", currentPage);
    request.setAttribute("totalPage", totalPages);
    request.setAttribute("pageSize", pageSize);
    request.setAttribute("totalCoupons", totalCoupons);
    request.setAttribute("discounttype", discountType); // giữ giá trị filter cho JSP

    // Tính chỉ số bản ghi hiển thị (ví dụ: 11-20)
    int startRecord = (currentPage - 1) * pageSize + 1;
    int endRecord = Math.min(startRecord + pageSize - 1, totalCoupons);
    request.setAttribute("startRecord", startRecord);
    request.setAttribute("endRecord", endRecord);

    // Forward tới trang JSP hiển thị danh sách coupons
    request.getRequestDispatcher("/view/manager/listCoupon.jsp").forward(request, response);
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
                couponCreateDoPost(request, response);
                break;
            case "edit":
                couponUpdateDoPost(request, response);
                break;
        }
    }

    private void couponCreateDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String code = request.getParameter("code");
            String description = request.getParameter("description");
            String discounType = request.getParameter("discountype");
            BigDecimal discountValue = new BigDecimal(request.getParameter("discountvalue"));
            BigDecimal minPurchase = new BigDecimal(request.getParameter("minpurchase"));
            BigDecimal maxDiscount = new BigDecimal(request.getParameter("maxdiscount"));
            String dateStr1 = request.getParameter("date1");
            String dateStr2 = request.getParameter("date2");
            String usageLimitStr = request.getParameter("usageLimit");
            Integer usageLimit = (usageLimitStr != null && !usageLimitStr.isEmpty())
                    ? Integer.parseInt(usageLimitStr) : null;
            String percustomerlimitStr=request.getParameter("percuslimit");
            Integer percustomerlimit=(percustomerlimitStr!=null && !percustomerlimitStr.isEmpty())
                    ? Integer.parseInt(percustomerlimitStr) : null;
            boolean isActive = request.getParameter("isActive") != null;
            Date date1 = null;
            try {
                if (dateStr1 != null && !dateStr1.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = sdf.parse(dateStr1);
                    date1 = new java.sql.Date(utilDate.getTime());
                } else {
                    date1 = new java.sql.Date(System.currentTimeMillis());
                }
            } catch (ParseException e) {
                request.setAttribute("error", "Invalid date format");
                request.getRequestDispatcher("/view/manager/addCoupon.jsp").forward(request, response);
                return;
            }
            Date date2 = null;
            try {
                if (dateStr2 != null && !dateStr2.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = sdf.parse(dateStr2);
                    date2 = new java.sql.Date(utilDate.getTime());
                } else {
                    date2 = new java.sql.Date(System.currentTimeMillis());
                }
            } catch (ParseException e) {
                request.setAttribute("error", "Invalid date format");
                request.getRequestDispatcher("/view/manager/addCoupon.jsp").forward(request, response);
                return;
            }
            Map<String, String> errors = validateCouponData(code, description, discounType, discountValue, minPurchase, maxDiscount,usageLimit,percustomerlimit,0 );
            if (!errors.isEmpty()) {
                request.getSession().setAttribute("errors", errors);
                request.getSession().setAttribute("formData", request.getParameterMap());
                request.getRequestDispatcher("/view/manager/addCoupon.jsp").forward(request, response);
                return;
            }
            Coupon newCoupon = Coupon.builder()
                    .code(code)
                    .description(description)
                    .discountType(discounType)
                    .discountValue(discountValue)
                    .minPurchase(minPurchase)
                    .maxDiscount(maxDiscount)
                    .active(isActive)
                    .usageLimit(usageLimit)
                    .perCustomerLimit(percustomerlimit)
                    .startDate(date1)
                    .endDate(date2)
                    .build();
            CouponDAO couponDao = new CouponDAO();
            boolean isSuccses = couponDao.insert(newCoupon) > 0;
            if (isSuccses) {
                HttpSession session = request.getSession();
                request.getSession().setAttribute("totalMess", "Add new Coupon Success");
                request.getSession().setAttribute("totalType", "Success");
                session.setAttribute("isAdd", true);
                response.sendRedirect("ManagerCoupon?action=list");
                return;
            } else {
                request.getSession().setAttribute("totalMess", "Fail to add Coupon");
                request.getSession().setAttribute("totalType", "Err");
                request.getRequestDispatcher("/view/manager/addCoupon.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.getSession().setAttribute("totalMess", "Fail to add Coupon: " + e.getMessage());
            request.getSession().setAttribute("totalType", "Err");
            e.printStackTrace();
            request.getRequestDispatcher("/view/manager/addCoupon.jsp").forward(request, response);
        }
    }

    private void couponUpdateDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String code = request.getParameter("code");
            String description = request.getParameter("description");
            String discounType = request.getParameter("discountype");
            BigDecimal discountValue = new BigDecimal(request.getParameter("discountvalue"));
            BigDecimal minPurchase = new BigDecimal(request.getParameter("minpurchase"));
            BigDecimal maxDiscount = new BigDecimal(request.getParameter("maxdiscount"));
            String usageLimitStr = request.getParameter("usageLimit");
            Integer usageLimit = (usageLimitStr != null && !usageLimitStr.isEmpty())
                    ? Integer.parseInt(usageLimitStr) : null;
            String percustomerlimitStr=request.getParameter("percuslimit");
            Integer percustomerlimit=(percustomerlimitStr!=null && !percustomerlimitStr.isEmpty())
                    ? Integer.parseInt(percustomerlimitStr) : null;
            boolean isActive = request.getParameter("isActive") != null;
            String dateStr1 = request.getParameter("date1");
            String dateStr2 = request.getParameter("date2");
            Date date1 = null;
            try {
                if (dateStr1 != null && !dateStr1.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = sdf.parse(dateStr1);
                    date1 = new java.sql.Date(utilDate.getTime());
                } else {
                    date1 = new java.sql.Date(System.currentTimeMillis());
                }
            } catch (ParseException e) {
                request.setAttribute("error", "Invalid date format");
                request.getRequestDispatcher("/view/manager/editCoupon.jsp").forward(request, response);
                return;
            }
            Date date2 = null;
            try {
                if (dateStr2 != null && !dateStr2.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = sdf.parse(dateStr2);
                    date2 = new java.sql.Date(utilDate.getTime());
                } else {
                    date2 = new java.sql.Date(System.currentTimeMillis());
                }
            } catch (ParseException e) {
                request.setAttribute("error", "Invalid date format");
                request.getRequestDispatcher("/view/manager/editCoupon.jsp").forward(request, response);
                return;
            }
            CouponDAO couponDao = new CouponDAO();
            Coupon Coupon = couponDao.findById(id);
            Coupon.setCode(code);
            Coupon.setDescription(description);
            Coupon.setDiscountType(discounType);
            Coupon.setDiscountValue(discountValue);
            Coupon.setMinPurchase(minPurchase);
            Coupon.setMaxDiscount(maxDiscount);
            Coupon.setActive(isActive);
            Coupon.setUsageLimit(usageLimit);
            Coupon.setPerCustomerLimit(percustomerlimit);
            Coupon.setStartDate(date1);
            Coupon.setEndDate(date2);
            boolean isSuccess = couponDao.update(Coupon);
            if (isSuccess) {
                HttpSession session = request.getSession();
                request.getSession().setAttribute("totalMess", "update new Blog Success");
                request.getSession().setAttribute("totalType", "Success");
                session.setAttribute("isUpdate", true);
                response.sendRedirect("ManagerCoupon");
                return;
            } else {
                request.getSession().setAttribute("totalMess", "Fail to update Account");
                request.getSession().setAttribute("totalType", "Err");
                request.setAttribute("error", "Update failed");
                response.sendRedirect("/view/manager/editCoupon.jsp");
                return;
            }
        } catch (Exception e) {
            request.getSession().setAttribute("totalMess", "Fail to Update Account");
            request.getSession().setAttribute("totalType", "Err" + e.getMessage());
            response.sendRedirect("ManagerCoupon");
            return;
        }
    }

    private Map<String, String> validateCouponData(String code, String description, String discountType,
            BigDecimal discountValue, BigDecimal minPurchase, BigDecimal maxDiscount,
            Integer usageLimit, Integer perCustomerLimit, Integer id) {

        Map<String, String> errors = new HashMap<>();

        // Validate code
        if (code == null || code.trim().isEmpty()) {
            errors.put("code", "Coupon code is required");
        } else if (code.length() > 50) {
            errors.put("code", "Coupon code must be less than 50 characters");
        } else {
            boolean codeExists = id == null
                    ? couponDAO.isCouponCodeExists(code)
                    : couponDAO.isCouponCodeExists(code, id);
            if (codeExists) {
                errors.put("code", "Coupon code already exists");
            }
        }

        // Validate description
        if (description == null || description.trim().isEmpty()) {
            errors.put("description", "Description is required");
        } else if (description.length() > 255) {
            errors.put("description", "Description must be less than 255 characters");
        }

        // Validate discount type
        if (discountType == null || (!discountType.equals("percentage") && !discountType.equals("fixed"))) {
            errors.put("discountType", "Discount type must be either 'percentage' or 'fixed'");
        }

        // Validate discount value
        if (discountValue == null) {
            errors.put("discountValue", "Discount value is required");
        } else if (discountValue.compareTo(BigDecimal.ZERO) < 0) {
            errors.put("discountValue", "Discount value must be greater than or equal to 0");
        }

        // Validate min purchase
        if (minPurchase == null) {
            errors.put("minPurchase", "Minimum purchase amount is required");
        } else if (minPurchase.compareTo(BigDecimal.ZERO) < 0) {
            errors.put("minPurchase", "Minimum purchase must be greater than or equal to 0");
        }

        // Validate max discount
        if (maxDiscount == null) {
            errors.put("maxDiscount", "Maximum discount value is required");
        } else if (maxDiscount.compareTo(BigDecimal.ZERO) < 0) {
            errors.put("maxDiscount", "Maximum discount must be greater than or equal to 0");
        }

        // Validate usage limit
        if (usageLimit != null && usageLimit < 0) {
            errors.put("usageLimit", "Usage limit must be greater than or equal to 0");
        }

        // Validate per-customer limit
        if (perCustomerLimit != null && perCustomerLimit < 0) {
            errors.put("perCustomerLimit", "Per-customer limit must be greater than or equal to 0");
        }

        // Optional: Validate isActive
        // (Usually not needed, but add if you need some logic like must be true for specific conditions)
        return errors;
    }
}
