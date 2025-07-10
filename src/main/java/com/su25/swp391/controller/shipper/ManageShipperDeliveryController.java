

package com.su25.swp391.controller.shipper;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.DeliveryDAO;
import com.su25.swp391.dal.implement.FeedbackDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.OrderApprovalDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Delivery;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.Order;
import com.su25.swp391.entity.OrderItem;
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
@WebServlet(name="ManageShipperDeliveryController", urlPatterns={"/shipper/manage-delivery"})
public class ManageShipperDeliveryController extends HttpServlet {
     
     private DeliveryDAO deliveryDAO;
     private OrderDAO orderDAO;
     private AccountDAO accDAO;
     private FoodDAO foodDAO;
     private OrderItemDAO itemDAO;
     @Override
    public void init() throws ServletException {
        deliveryDAO = new DeliveryDAO();
        accDAO = new AccountDAO();
        orderDAO = new OrderDAO();
        foodDAO = new FoodDAO();
        itemDAO = new OrderItemDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get action from submit
        String action = request.getParameter("action");
        // If action == null
        if (action == null) {
            action = "list";
        }
        try {
            switch (action) {
                case "list":
                    listDelivery(request, response);
                    break;
                case "view":
                    viewDelivery(request, response);
                    break;
                case "shipper":
                    viewShipper(request, response);
                    break;
                case "add":
                    selectShipper(request, response);
                    break;
                default:
                    listDelivery(request, response);
                    break;
            }
        } catch (Exception ex) {
            // Notification error
            request.setAttribute("errorMessage", "Database error: " + ex.getMessage());
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get action from submit
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "add":
                    selectShipper(request, response);
                    break;
                default:
                    listDelivery(request, response);
                    break;
            }
        } catch (Exception ex) {
            // Notification error
            request.setAttribute("errorMessage", "Database error: " + ex.getMessage());
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listDelivery(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
     String sort = request.getParameter("sort");
String status = request.getParameter("status");
String search = request.getParameter("search");

// Lấy thông tin shipper đăng nhập từ session
//Account currentShipper = (Account) request.getSession().getAttribute("account");
//int currentShipperId = currentShipper.getId();
  int currentShipperId = 48;
// Phân trang
int page = 1;
int pageSize = 1;
try {
    if (request.getParameter("page") != null) {
        page = Integer.parseInt(request.getParameter("page"));
        if (page < 1) {
            page = 1;
        }
    }
} catch (NumberFormatException e) {
    // giữ page = 1
}

// Lấy danh sách đơn hàng theo shipper
List<Delivery> listDelivery;
int totalDeliveries = 0;

if (search != null && !search.trim().isEmpty()) {
    // Nếu có tìm kiếm => vẫn dùng searchDelivery và getTotalDeliveryResults như cũ (có JOIN với Account)
    listDelivery = deliveryDAO.searchDeliveryShipper(search, sort, status, page, pageSize,currentShipperId);
    totalDeliveries = deliveryDAO.getTotalDeliveryResultsShipper(search, status,currentShipperId);
    
    // Nếu muốn search riêng của shipper thì phải sửa cả searchDelivery(...) => chưa nên sửa nếu chưa cần
} else {
    // Dùng method mới cho shipper
    listDelivery = deliveryDAO.findDeliveryByShipper(currentShipperId, sort, status, page, pageSize);
    totalDeliveries = deliveryDAO.getTotalFilteredDeliveryByShipper(currentShipperId, status);
}

// Tính tổng số trang
int totalPages = (int) Math.ceil((double) totalDeliveries / pageSize);

// Set attribute cho JSP
request.setAttribute("sort", sort);
request.setAttribute("status", status);
request.setAttribute("search", search);
request.setAttribute("currentPage", page);
request.setAttribute("totalPages", totalPages);
request.setAttribute("accDAO", accDAO);
request.setAttribute("orderDAO", orderDAO);
request.setAttribute("listDelivery", listDelivery);

// Điều hướng tới JSP
request.getRequestDispatcher("/view/shipper/order-list.jsp").forward(request, response);
    }

    private void viewShipper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int id = Integer.parseInt(request.getParameter("id"));
       String sort = request.getParameter("sort");
       String search = request.getParameter("search");
       // Pagination
        int page = 1;
        int pageSize = 2;
         try {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                if (page < 1) {
                    page = 1;
                }
            }
        } catch (NumberFormatException e) {
            // Keep default value
        }
       List<Account> accountList = accDAO.searchShippers(search, sort, page, pageSize);
        int totalShipper = accDAO.getTotalShipperResults(search);
       
      // Number of page can have
        int totalPages = (int) Math.ceil((double) totalShipper / pageSize);
        
       Delivery de = deliveryDAO.findById(id);
       request.setAttribute("sort", sort);
      request.setAttribute("search", search);
      request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
       request.setAttribute("id", id);
       request.setAttribute("shipperList", accountList);
       request.setAttribute("de", de);
       
        Map<Integer, Integer> deliveringCountMap = new HashMap<>();
        for (Account shipper : accountList) {
            int deliveringCount = deliveryDAO.getNumberDeliveryOfShipper(shipper.getId());
            deliveringCountMap.put(shipper.getId(), deliveringCount);
        }
        request.setAttribute("deliveringCountMap", deliveringCountMap);
       request.setAttribute("deliveryDAO", deliveryDAO);
       request.getRequestDispatcher("/view/seller/select-shipper.jsp").forward(request, response);
    }

    private void selectShipper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            int id = Integer.parseInt(request.getParameter("id"));
            int shipper_id = Integer.parseInt(request.getParameter("shipper_id"));
            Delivery delivery = deliveryDAO.findById(id);
            delivery.setShipper_id(shipper_id);
            Boolean checkUpdateShipperSuccess = deliveryDAO.update(delivery);
            if(checkUpdateShipperSuccess){
               session.setAttribute("isSuccess", true);
            }else{
               session.setAttribute("errorMessage", true); 
            }
            response.sendRedirect(request.getContextPath() + "/seller/manage-delivery");
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    private void viewDelivery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
       try {
      int id = Integer.parseInt(request.getParameter("id"));
      int shipper_id = Integer.parseInt(request.getParameter("shipper_id"));
      Delivery delivery = deliveryDAO.findById(id);
      Order order = orderDAO.findById(delivery.getOrder_id());
      Account accShipper = accDAO.findById(shipper_id);
           
//            // Get the seller account from session
//            HttpSession session = request.getSession();
//            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
//
//            // Check if the seller is logged in
//            if (account == null) {
//                response.sendRedirect(request.getContextPath() + "/home");
//                return;
//            }
             // If the order does not exist, forward to error page
            if (order == null) {
                request.setAttribute("errorMessage", "Order not found with ID: " + delivery.getOrder_id());
                 request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
                return;
            }

            // Retrieve the account associated with the order
            Account acc = accDAO.findById(order.getAccount_id());

            // Retrieve the list of items in the order
            List<OrderItem> orderItems = itemDAO.getOrderItemsByOrderId(order.getId());

            // Map food details for each order item
            HashMap<Integer, Food> OrderItemMap = new HashMap<>();
            for (OrderItem orderItem : orderItems) {
                Food food = foodDAO.findById(orderItem.getFood_id());
                OrderItemMap.put(orderItem.getFood_id(), food);
            }

            // Set attributes for JSP rendering
            request.setAttribute("order", order);
            request.setAttribute("account", acc);
            request.setAttribute("OrderItems", orderItems);
            request.setAttribute("OrderItemMap", OrderItemMap);
            request.setAttribute("accShipper", accShipper);
            request.setAttribute("de", delivery);
            // Forward to the delivery detail JSP page
            request.getRequestDispatcher("/view/shipper/delivery-detail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Handle invalid order ID format
            request.setAttribute("errorMessage", "Invalid order ID format");
            request.getRequestDispatcher("/view/error/error.jsp").forward(request, response);
        }
    }

}
