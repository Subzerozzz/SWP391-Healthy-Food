/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.homePage;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.CartDAO;
import com.su25.swp391.dal.implement.CartItemDAO;
import com.su25.swp391.dal.implement.CouponDAO;
import com.su25.swp391.dal.implement.CouponUsageDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.dal.implement.OrderItemDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Cart;
import com.su25.swp391.entity.CartItem;
import com.su25.swp391.entity.Coupon;
import com.su25.swp391.entity.CouponUsage;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.Order;
import com.su25.swp391.entity.OrderItem;
import com.su25.swp391.utils.EmailUtils;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Dell
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    private static final Pattern REGEX_EMAIL = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern REGEX_PHONE = Pattern.compile("^(\\\\+84|0)[0-9]{9}$");

    CartDAO cartDao = new CartDAO();
    CartItemDAO cartItemDao = new CartItemDAO();
    FoodDAO foodDao = new FoodDAO();
    CouponDAO couponDao = new CouponDAO();
    CouponUsageDAO couponUsageDao = new CouponUsageDAO();
    OrderDAO orderDao = new OrderDAO();
    OrderItemDAO orderItemDao = new OrderItemDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "showCart":
                showCart(request, response);
                break;
            case "checkout":
                showCheckout(request, response);
                break;
            case "checkoutVNPay":
            {
                try {
                    handleVNPayReturn(request, response);
                } catch (MessagingException ex) {
                    Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "add":
                    addItemToCart(request, response);
                    break;
                case "update":
                    updateItemToCart(request, response);
                    break;
                case "delete":
                    deleteItemToCart(request, response);
                    break;
                case "checkout":
                    checkoutOrder(request, response);
                    break;
                case "coupon":
                    couponOrder(request, response);
                    break;
                default:
                    throw new AssertionError();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
        if (account != null) {
            // Lấy ra cartId theo accountId
            Cart cart = cartDao.findCartByAccountId(account.getId());
            // Lấy ra tất cả cartItem theo cartId
            List<CartItem> listCartItem = cartItemDao.findAllCartItemByCartId(cart.getId());
            Map<Integer, Food> foodMap = new HashMap<>();
            for (CartItem item : listCartItem) {
                Food food = foodDao.findById(item.getFood_id());
                foodMap.put(item.getFood_id(), food);
            }
            // Trả listCartItem ra JSP
            request.setAttribute("listCartItem", listCartItem);
            request.setAttribute("foodMap", foodMap);
            request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
        } else {
            List<CartItem> listCartItem = (List<CartItem>) session.getAttribute("cart");
            if (listCartItem == null) {
                listCartItem = new ArrayList<>();
            }
            Map<Integer, Food> foodMap = new HashMap<>();
            for (CartItem item : listCartItem) {
                Food food = foodDao.findById(item.getFood_id());
                foodMap.put(item.getFood_id(), food);
            }
            // Trả listCartItem ra JSP
            request.setAttribute("listCartItem", listCartItem);
            request.setAttribute("foodMap", foodMap);
            request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
        }
    }

    private void showCheckout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
            Integer subTotal = Integer.parseInt(request.getParameter("subTotal"));
            Integer totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
            String couponCode = request.getParameter("couponCode");
            Double discountAmount = request.getParameter("discountAmount") == "" ? 0 : Double.parseDouble(request.getParameter("discountAmount"));

            // Lay ra listCartItem
            List<CartItem> listCartItem = new ArrayList<>();

            if (account != null) {
                // Lấy ra cartId theo accountId
                Cart cart = cartDao.findCartByAccountId(account.getId());
                // Lấy ra tất cả cartItem theo cartId
                listCartItem = cartItemDao.findAllCartItemByCartId(cart.getId());
            } else {
                listCartItem = (List<CartItem>) session.getAttribute("cart");
                if (listCartItem == null) {
                    listCartItem = new ArrayList<>();
                }
            }
            // cart rong thi tra ve JSP va thong bao
            if (listCartItem.size() == 0) {
                request.setAttribute("isEmptyListCartItem", true);
                request.setAttribute("listCartItem", listCartItem);
                request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
                return;
            }
            Map<Integer, Food> foodMap = new HashMap<>();
            for (CartItem item : listCartItem) {
                Food food = foodDao.findById(item.getFood_id());
                foodMap.put(item.getFood_id(), food);
            }
            request.setAttribute("listCartItem", listCartItem);
            request.setAttribute("foodMap", foodMap);
            request.setAttribute("subTotal", subTotal);
            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("couponCode", couponCode);
            request.setAttribute("discountAmount", discountAmount);
            request.getRequestDispatcher("view/homePage/checkout.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void addItemToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Lấy food id và quantity
            Integer id = Integer.parseInt(request.getParameter("id"));
            Integer quantity = Integer.parseInt(request.getParameter("quantity"));
            // Lấy ra các thông tin của food
            Food food = foodDao.findById(id);
            // Lấy thời gian hiện tại
            Timestamp created_at = new Timestamp(System.currentTimeMillis());
            Timestamp updated_at = new Timestamp(System.currentTimeMillis());

            // Lấy về thông tin tài khoản trên session
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

            if (account != null) {
                // Lấy ra cartid theo tài khoản đó
                Cart cart = cartDao.findCartByAccountId(account.getId());
                Integer cartId = cart.getId();

                // Tạo cartItem
                CartItem newCartItem = CartItem.builder()
                        .cart_id(cartId)
                        .food_id(id)
                        .quantity(quantity)
                        .created_at(created_at)
                        .updated_at(updated_at)
                        .build();

                // Check xem newcartItem đó đã tồn tại trong cartID này chưa
                List<CartItem> listCartItemByCartID = cartItemDao.findAllCartItemByCartId(cartId);
                boolean check = true;
                for (CartItem cartItem : listCartItemByCartID) {
                    if (newCartItem.getFood_id() == cartItem.getFood_id()) {
                        check = false;
                        cartItem.setQuantity(cartItem.getQuantity() + quantity);
                        cartItemDao.update(cartItem);
                    }
                }

                if (check == true) {
                    cartItemDao.insert(newCartItem);
                }
            } else {
                List<CartItem> listCartItem = (List<CartItem>) session.getAttribute("cart");
                if (listCartItem == null) {
                    listCartItem = new ArrayList<>();
                }
                CartItem newCartItem = CartItem.builder()
                        .food_id(id)
                        .quantity(quantity)
                        .created_at(created_at)
                        .updated_at(updated_at)
                        .build();
                // Check xem trong listCartItem đã tồn tại sản phẩm này chưa
                boolean check = true;
                for (CartItem cartItem : listCartItem) {
                    if (cartItem.getFood_id() == newCartItem.getFood_id()) {
                        check = false;
                        cartItem.setQuantity(cartItem.getQuantity() + quantity);
                    }
                }
                if (check == true) {
                    listCartItem.add(newCartItem);
                }

                session.setAttribute("cart", listCartItem);
            }

            // Lấy ra các food trong category này
            List<Food> listFoodCategory = foodDao.findAll();
            // Lấy ra 4 món ăn đầu tiên liên quan
            List<Food> listRelated = new ArrayList<>();
            int count = 0;
            for (Food a : listFoodCategory) {
                if (count < 4) {
                    listRelated.add(a);
                } else {
                    break;
                }
                count++;
            }
            // Chuyển về trang shopDetail và báo
            request.setAttribute("listRelated", listRelated);
            request.setAttribute("foodDetail", food);
            request.setAttribute("addSuccess", true);
            request.getRequestDispatcher("view/homePage/shopDetail.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void updateItemToCart(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
        // Lay ra list foodId tuong ung voi so quantity moi
        String[] quantityList = request.getParameterValues("quantity");
        String[] foodIdList = request.getParameterValues("foodId");

        // Convert
        List<Integer> listID = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();// value1 la id, value 2 la quantity

        for (int i = 0; i < foodIdList.length; i++) {
            Integer foodId = Integer.parseInt(foodIdList[i]);
            Integer quantity = Integer.parseInt(quantityList[i]);
            listID.add(foodId);
            map.put(foodId, quantity);
        }

        List<CartItem> listCartItem = new ArrayList<>();

        if (account != null) {
            // Lay ra cartID theo accID
            Cart cart = cartDao.findCartByAccountId(account.getId());
            // Lấy ra tất cả cartItem theo cartId
            listCartItem = cartItemDao.findAllCartItemByCartId(cart.getId());

            // Cap nhat lai quantiy cho nhung foodId nay trong listCartItem
            for (Integer id : listID) {
                for (int i = 0; i < listCartItem.size(); i++) {
                    if (listCartItem.get(i).getFood_id() == id) {
                        listCartItem.get(i).setQuantity(map.get(id));
                    }
                }
            }
            // chuan bi foodMap cho JSP
            Map<Integer, Food> foodMap = new HashMap<>();
            for (CartItem item : listCartItem) {
                Food food = foodDao.findById(item.getFood_id());
                foodMap.put(item.getFood_id(), food);
            }
            // Co the cap nhat lai ca coupon neu co

            // Trả listCartItem ra JSP
            request.setAttribute("listCartItem", listCartItem);
            request.setAttribute("foodMap", foodMap);
            request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
        } else {
            listCartItem = (List<CartItem>) session.getAttribute("cart");

            // Cap nhat lai quantiy cho nhung foodId nay trong listCartItem
            for (Integer id : listID) {
                for (int i = 0; i < listCartItem.size(); i++) {
                    if (listCartItem.get(i).getFood_id() == id) {
                        listCartItem.get(i).setQuantity(map.get(id));
                    }
                }
            }
            // chuan bi foodMap cho JSP
            Map<Integer, Food> foodMap = new HashMap<>();
            for (CartItem item : listCartItem) {
                Food food = foodDao.findById(item.getFood_id());
                foodMap.put(item.getFood_id(), food);
            }

            // Trả listCartItem ra JSP
            request.setAttribute("listCartItem", listCartItem);
            request.setAttribute("foodMap", foodMap);
            request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
        }

    }

    private void deleteItemToCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lay ban ghi can xoa
        Integer deleteID = Integer.parseInt(request.getParameter("deleteId"));
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

        if (account != null) {
            // Lay ra cartID theo accID
            Cart cart = cartDao.findCartByAccountId(account.getId());
            Integer cartId = cart.getId();
            // Lấy ra tất cả cartItem theo cartId
            List<CartItem> listCartItem = cartItemDao.findAllCartItemByCartId(cartId);
            // Tim cartItemId theo deleteID do
            for (CartItem cartItem : listCartItem) {
                if (cartItem.getFood_id() == deleteID) {
                    cartItemDao.delete(cartItem);
                }
            }
        } else {
            List<CartItem> listCartItem = (List<CartItem>) session.getAttribute("cart");
            for (int i = 0; i < listCartItem.size(); i++) {
                if (listCartItem.get(i).getFood_id() == deleteID) {
                    listCartItem.remove(listCartItem.get(i));
                }
            }
            request.setAttribute("listCartItem", listCartItem);
        }
        showCart(request, response);
    }

    private void couponOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
            String couponCode = request.getParameter("coupon").trim();
            Integer subTotal = Integer.parseInt(request.getParameter("subTotal"));
            Integer totalPrice = Integer.parseInt(request.getParameter("totalPrice"));

            List<CartItem> listCartItem = new ArrayList<>();

            if (account != null) {
                // Lay ra cartID theo accID
                Cart cart = cartDao.findCartByAccountId(account.getId());
                Integer cartId = cart.getId();
                // Lấy ra tất cả cartItem theo cartId
                listCartItem = cartItemDao.findAllCartItemByCartId(cartId);
            } else {
                listCartItem = (List<CartItem>) session.getAttribute("cart");
                if (listCartItem == null) {
                    listCartItem = new ArrayList<>();
                }
            }
            // cart rong thi tra ve JSP va thong bao
            if (listCartItem.size() == 0) {
                request.setAttribute("isEmptyListCartItemForCoupon", true);
                request.setAttribute("listCartItem", listCartItem);
                request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
                return;
            }
            // chuan bi foodMap cho JSP
            Map<Integer, Food> foodMap = new HashMap<>();
            for (CartItem item : listCartItem) {
                Food food = foodDao.findById(item.getFood_id());
                foodMap.put(item.getFood_id(), food);
            }
            request.setAttribute("listCartItem", listCartItem);
            request.setAttribute("foodMap", foodMap);

            // Check coupon
            if (account == null) {
                request.setAttribute("accountNotFound", true);
                request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
                return;
            } else {
                Coupon coupon = couponDao.findCouponByCouponCode(couponCode);
                // check xem coupon ton tai khong
                if (coupon == null) {
                    request.setAttribute("couponNotFound", true);
                    request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
                    return;
                }
                // check xem coupon con active khogn
                if (coupon.getIs_active() == 0) {
                    request.setAttribute("couponNotActive", true);
                    request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
                    return;
                }
                // check xem coupon con HSD
                Date currentDate = new Date(System.currentTimeMillis());
                Date endDate = coupon.getEnd_date();
                if (currentDate.after(endDate)) {
                    request.setAttribute("couponNotActiveByDate", true);
                    request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
                    return;
                }
                // check xem coupon con so luong khong
                if (coupon.getUsage_count() >= coupon.getUsage_limit()) {
                    request.setAttribute("couponNotActiveByLimit", true);
                    request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
                    return;
                }
                //check xem subTotal có lon hon hoac bang min_purchase không
                if (subTotal <= coupon.getMin_purchase()) {
                    request.setAttribute("couponNotActiveByNotEnoughPrice", true);
                    request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
                    return;
                }
                // check xem nguoi dung da het so lan dung chua
                Integer countUsed = couponUsageDao.findByCouponIdAndAccountId(account.getId(), coupon.getId());
                if (countUsed >= coupon.getPer_customer_limit()) {
                    request.setAttribute("couponNotActiveByUsed", true);
                    request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
                    return;
                }

                //neu duoc di tiep
                String discountType = coupon.getDiscount_type();
                Double discountValue = null;
                if (discountType == "percentage") {
                    discountValue = (subTotal * coupon.getDiscount_value()) / 100;
                } else {
                    discountValue = coupon.getDiscount_value();
                }
                // tra ve JSP
                request.setAttribute("couponCode", couponCode);
                request.setAttribute("discountValue", discountValue);
                request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * lay ra cac du lieu can co trong 1 order Loi thi tra ve cart.jsp Khong thi
     * tiep tuc voi hai nhanh la cod hoac Vnpay
     */
    private void checkoutOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Map<String, String> errorMap = new HashMap<>();
            // Lay ra fullName
            String fullName = request.getParameter("fullName").trim();

            if (fullName == null || fullName.isEmpty()) {
                errorMap.put("fullName", "Tên không được để trống");
            }
            // Lay ra email
            String email = request.getParameter("email").trim();
            if (email == null || email.isEmpty()) {
                errorMap.put("email", "Email không được để trống");
            } else {
                if (!REGEX_EMAIL.matcher(email).matches()) {
                    errorMap.put("email", "Email sai định dạng");
                }
            }

            // Lay ra phoneNumber
            String phoneNumber = request.getParameter("phoneNumber").trim();
            if (phoneNumber == null || phoneNumber.isEmpty()) {
                errorMap.put("phoneNumber", "Số điện thoại không được để trống");
            }
            else{
                if(!REGEX_PHONE.matcher(phoneNumber).matches()){
                    errorMap.put("phoneNumber", "Số điện thoại sai định dạng");
                }
            }
            // Lay ra address
            String address = request.getParameter("address").trim();
            if (address == null || address.isEmpty()) {
                errorMap.put("address", "Địa chỉ không được để trống");
            }
            // Lay ra paymentMethod
            String paymentMethod = request.getParameter("paymentMethod");

            // Lat ra subTotal và totalPrice
            Double subTotal = Double.parseDouble(request.getParameter("subTotal"));
            Double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));

            // Lay ra coupon_code va discountAmount
            String couponCode = request.getParameter("couponCode");
            if (couponCode == null || couponCode.isEmpty()) {
                couponCode = null;
            }
            Double discountAmount = Double.parseDouble(request.getParameter("discountAmount"));

            // Lay ra created_at và updated_at
            Timestamp created_at = new Timestamp(System.currentTimeMillis());
            Timestamp updated_at = new Timestamp(System.currentTimeMillis());

            // Lay listCartItem
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
            List<CartItem> listCartItem = new ArrayList<>();

            if (account != null) {
                // Lay ra cartID theo accID
                Cart cart = cartDao.findCartByAccountId(account.getId());
                Integer cartId = cart.getId();
                // Lấy ra tất cả cartItem theo cartId
                listCartItem = cartItemDao.findAllCartItemByCartId(cartId);
            } else {
                listCartItem = (List<CartItem>) session.getAttribute("cart");
                if (listCartItem == null) {
                    listCartItem = new ArrayList<>();
                }
            }

            // chuan bi foodMap cho JSP
            Map<Integer, Food> foodMap = new HashMap<>();
            for (CartItem item : listCartItem) {
                Food food = foodDao.findById(item.getFood_id());
                foodMap.put(item.getFood_id(), food);
            }

            // Check
            if (!errorMap.isEmpty()) {
                request.setAttribute("listCartItem", listCartItem);
                request.setAttribute("foodMap", foodMap);
                request.setAttribute("errors", errorMap);
                request.setAttribute("subTotal", subTotal);
                request.setAttribute("totalPrice", totalPrice);
                request.setAttribute("discountAmount", discountAmount);
                request.setAttribute("paymentMethod", paymentMethod);
                request.setAttribute("formData", request.getParameterMap());
                request.getRequestDispatcher("view/homePage/checkout.jsp").forward(request, response);
                return;
            }

            switch (paymentMethod) {
                case "cod":
                    handleProcessCheckoutCOD(request, response);
                    break;
                case "vnpay":
                    handleProcessCheckoutVNPay(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            //Kiem tra neu co couponCode thi them
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void handleProcessCheckoutCOD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
        List<CartItem> listCartItem = new ArrayList<>();

        // Lay ra fullName
        String fullName = request.getParameter("fullName").trim();
        // Lay ra email
        String email = request.getParameter("email").trim();
        // Lay ra phoneNumber
        String phoneNumber = request.getParameter("phoneNumber").trim();
        // Lay ra address
        String address = request.getParameter("address").trim();
        // Lay ra paymentMethod
        String paymentMethod = request.getParameter("paymentMethod");

        // Lat ra subTotal và totalPrice
        Double subTotal = Double.parseDouble(request.getParameter("subTotal"));
        Double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));

        // Lay ra coupon_code va discountAmount
        String couponCode = request.getParameter("couponCode");
        if (couponCode == null || couponCode.isEmpty()) {
            couponCode = null;
        }
        Double discountAmount = Double.parseDouble(request.getParameter("discountAmount"));

        // Lay ra created_at và updated_at
        Timestamp created_at = new Timestamp(System.currentTimeMillis());
        Timestamp updated_at = new Timestamp(System.currentTimeMillis());

        Order newOrder = new Order();
        if (account != null) {
            // Lay ra cartID theo accID
            Cart cart = cartDao.findCartByAccountId(account.getId());
            Integer cartId = cart.getId();
            // Lấy ra tất cả cartItem theo cartId
            listCartItem = cartItemDao.findAllCartItemByCartId(cartId);
            newOrder = Order.builder()
                    .account_id(account.getId())
                    .status("pending")
                    .total(totalPrice)
                    .shipping_address(address)
                    .payment_method(GlobalConfig.PAYMENT_METHOD_COD)
                    .created_at(created_at)
                    .updated_at(updated_at)
                    .coupon_code(couponCode)
                    .discount_amount(discountAmount)
                    .email(email)
                    .full_name(fullName)
                    .mobile(phoneNumber)
                    .payment_status(0)
                    .build();
        } else {
            listCartItem = (List<CartItem>) session.getAttribute("cart");
            newOrder = Order.builder()
                    .account_id(null)
                    .status("pending")
                    .total(totalPrice)
                    .shipping_address(address)
                    .payment_method(GlobalConfig.PAYMENT_METHOD_COD)
                    .created_at(created_at)
                    .updated_at(updated_at)
                    .coupon_code(couponCode)
                    .discount_amount(discountAmount)
                    .email(email)
                    .full_name(fullName)
                    .mobile(phoneNumber)
                    .payment_status(0)
                    .build();
        }

        Integer orderId = orderDao.insert(newOrder);
        //Sau do tao cac orderItem cho order
        if (orderId > 0) {
            for (CartItem cartItem : listCartItem) {
                Integer foodId = cartItem.getFood_id();
                Integer quantity = cartItem.getQuantity();
                Double price = foodDao.findById(foodId).getPrice();
                Double priceTotal = price * quantity;

                OrderItem newOrderItem = OrderItem.builder()
                        .order_id(orderId)
                        .food_id(foodId)
                        .quantity(quantity)
                        .price(priceTotal)
                        .created_at(created_at)
                        .updated_at(updated_at)
                        .build();
                System.out.println(orderItemDao.insert(newOrderItem));
            }
        }
        //Đặt hàng xong thì xóa hết cartItem trong listCartItem
        if (account != null) {
            for (CartItem cartItem : listCartItem) {
                cartItemDao.delete(cartItem);
            }
            //Kiem tra xem co couponCode khong thi them 1 ban ghi vào couponUseage
            if(couponCode != null){
                Coupon coupon = couponDao.findCouponByCouponCode(couponCode);
                //Tao couponUseage
                CouponUsage couponUsage = CouponUsage.builder()
                        .coupon_id(coupon.getId())
                        .account_id(account.getId())
                        .order_id(orderId)
                        .used_at(updated_at)
                        .discount_amount(discountAmount)
                        .build();
                couponUsageDao.insert(couponUsage);
                        
            }
            //Sau do chuyen sang trang myOrder
            response.sendRedirect("orderlist");
        } else {
            List<CartItem> listCartItem1 = new ArrayList<>();
            session.setAttribute("cart", listCartItem1);
            //Lấy ra các OrderItem thông qua orderId
            List<OrderItem> listOrderItem = orderItemDao.findAllOrderItemByOrderID(orderId);
            //Goi ham sendOrderViaEmail
            boolean check = EmailUtils.sendOrderViaEmail(email, listOrderItem, orderId,true);
            //tra ve homePage voi notification success
            request.setAttribute("notificationForEmail", check);
            request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
        }

        //Kiem tra xem có coupon khong thi giam so luong
        if (couponCode != null) {
            Coupon coupon = couponDao.findCouponByCouponCode(couponCode.trim());
            coupon.setUsage_count(coupon.getUsage_count() + 1);
            Date currentDate = new Date(System.currentTimeMillis());
            coupon.setUpdated_at(currentDate);
            couponDao.update(coupon);
        }

    }

    private void handleProcessCheckoutVNPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
        // Lay ra fullName
        String fullName = request.getParameter("fullName").trim();
        // Lay ra email
        String email = request.getParameter("email").trim();
        // Lay ra phoneNumber
        String phoneNumber = request.getParameter("phoneNumber").trim();
        // Lay ra address
        String address = request.getParameter("address").trim();
        // Lay ra paymentMethod
        String paymentMethod = request.getParameter("paymentMethod");

        // Lat ra subTotal và totalPrice
        Double subTotal = Double.parseDouble(request.getParameter("subTotal"));
        Double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));

        // Lay ra coupon_code va discountAmount
        String couponCode = request.getParameter("couponCode");
        if (couponCode == null || couponCode.isEmpty()) {
            couponCode = null;
        }
        Double discountAmount = Double.parseDouble(request.getParameter("discountAmount"));

        // Lay ra created_at và updated_at
        Timestamp created_at = new Timestamp(System.currentTimeMillis());
        Timestamp updated_at = new Timestamp(System.currentTimeMillis());

        List<CartItem> listCartItem = new ArrayList<>();
        Order newOrder = new Order();
        if (account != null) {
            // Lay ra cartID theo accID
            Cart cart = cartDao.findCartByAccountId(account.getId());
            Integer cartId = cart.getId();
            // Lấy ra tất cả cartItem theo cartId
            listCartItem = cartItemDao.findAllCartItemByCartId(cartId);
            newOrder = Order.builder()
                    .account_id(account.getId())
                    .status("pending")
                    .total(totalPrice)
                    .shipping_address(address)
                    .payment_method(GlobalConfig.PAYMENT_METHOD_COD)
                    .created_at(created_at)
                    .updated_at(updated_at)
                    .coupon_code(couponCode)
                    .discount_amount(discountAmount)
                    .email(email)
                    .full_name(fullName)
                    .mobile(phoneNumber)
                    .payment_status(0)
                    .build();
        } else {
            listCartItem = (List<CartItem>) session.getAttribute("cart");
            newOrder = Order.builder()
                    .account_id(null)
                    .status("pending")
                    .total(totalPrice)
                    .shipping_address(address)
                    .payment_method(GlobalConfig.PAYMENT_METHOD_VNPAY)
                    .created_at(created_at)
                    .updated_at(updated_at)
                    .coupon_code(couponCode)
                    .discount_amount(discountAmount)
                    .email(email)
                    .full_name(fullName)
                    .mobile(phoneNumber)
                    .payment_status(0)
                    .build();
        }

        Integer orderId = orderDao.insert(newOrder);
        //Sau do tao cac orderItem cho order
        if (orderId > 0) {
            for (CartItem cartItem : listCartItem) {
                Integer foodId = cartItem.getFood_id();
                Integer quantity = cartItem.getQuantity();
                Double price = foodDao.findById(foodId).getPrice();
                Double priceTotal = price * quantity;

                OrderItem newOrderItem = OrderItem.builder()
                        .order_id(orderId)
                        .food_id(foodId)
                        .quantity(quantity)
                        .price(priceTotal)
                        .created_at(created_at)
                        .updated_at(updated_at)
                        .build();
                System.out.println(orderItemDao.insert(newOrderItem));
            }
        }

        //Đặt hàng xong thì xóa hết cartItem trong listCartItem
        if (account != null) {
            for (CartItem cartItem : listCartItem) {
                cartItemDao.delete(cartItem);
            }
        } else {
            List<CartItem> listCartItem1 = new ArrayList<>();
            session.setAttribute("cart", listCartItem1);
        }

        //Chuyen huong nguoi dung sang giao dien thanh toan tich hop VNPay
        response.sendRedirect(request.getContextPath() + "/ajaxServlet?amount=" + totalPrice + "&orderId=" + orderId);
    }

    private void handleVNPayReturn(HttpServletRequest request, HttpServletResponse response) throws IOException, 
            ServletException, MessagingException {
        String responseCode = request.getParameter("vnp_ResponseCode");
        String transactionStatus = request.getParameter("vnp_TransactionStatus");
        //kiem tra trang thai don hang thanh cong chua
        //thanh cong thi tao voi trang thai la da thanh toan
        //khong thi tao voi trang thai la chua thanh toan

        if ("00".equals(responseCode) && "00".equals(transactionStatus)) {
            handleOrderWithVNPaySuccess(request, response);
        } else if ("24".equals(responseCode) && "02".equals(transactionStatus)) {
            handleOrderWithVNPayCancel(request, response);
        } else {
            handleOrderWithVNPayFailed(request, response);
        }

    }

    private void handleOrderWithVNPaySuccess(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, ServletException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
        String orderIdStr = request.getParameter("vnp_TxnRef");
        Integer orderId = Integer.parseInt(orderIdStr);
        //Update don hang voi payment_status là 1
        Order order = orderDao.findById(orderId);
        order.setPayment_status(1);
        orderDao.update(order);

        if (account != null) {
            //Them vao couponUsage neu co couponCode
            if(order.getCoupon_code() != null){
                Coupon coupon = couponDao.findCouponByCouponCode(order.getCoupon_code());
                //Tao couponUseage
                CouponUsage couponUsage = CouponUsage.builder()
                        .coupon_id(coupon.getId())
                        .account_id(account.getId())
                        .order_id(orderId)
                        .used_at(order.getUpdated_at())
                        .discount_amount(order.getDiscount_amount())
                        .build();
                couponUsageDao.insert(couponUsage);
            }
            //Chuyen ve trang myOrder
            response.sendRedirect("orderlist");
            
        } else {
            //Lấy ra các OrderItem thông qua orderId
            List<OrderItem> listOrderItem = orderItemDao.findAllOrderItemByOrderID(orderId);
            //Goi ham sendOrderViaEmail
            boolean check = EmailUtils.sendOrderViaEmail(order.getEmail(), listOrderItem, orderId,true);
            //tra ve homePage voi notification success
            request.setAttribute("notificationForEmail", check);
            request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
        }
    }

    private void handleOrderWithVNPayCancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
        //Xóa đi đơn hàng kèm các OrderItem trong DB
        String orderIdStr = request.getParameter("vnp_TxnRef");
        Integer orderId = Integer.parseInt(orderIdStr);
        Order order = orderDao.findById(orderId);
        //Xoa listOrderItem truoc + tao lai cartItem dua tren OrdeITem
        List<OrderItem> listOrderItem = orderItemDao.findAllOrderItemByOrderID(orderId);

        if (account != null) {
            Integer cartId = cartDao.findCartByAccountId(account.getId()).getId();
            for (OrderItem orderItem : listOrderItem) {
                CartItem newCartItem = CartItem.builder()
                        .cart_id(cartId)
                        .food_id(orderItem.getFood_id())
                        .quantity(orderItem.getQuantity())
                        .created_at(orderItem.getCreated_at())
                        .updated_at(orderItem.getUpdated_at())
                        .build();
                cartItemDao.insert(newCartItem);
                orderItemDao.delete(orderItem);
            }
            orderDao.delete(order);

        } else {
            List<CartItem> listCartItem = new ArrayList<>();
            for (OrderItem orderItem : listOrderItem) {
                CartItem newCartItem = CartItem.builder()
                        .food_id(orderItem.getFood_id())
                        .quantity(orderItem.getQuantity())
                        .created_at(orderItem.getCreated_at())
                        .updated_at(orderItem.getUpdated_at())
                        .build();
                listCartItem.add(newCartItem);
                session.setAttribute("cart", listCartItem);
                orderItemDao.delete(orderItem);
            }
            orderDao.delete(order);
        }
        showCart(request, response);

    }

    private void handleOrderWithVNPayFailed(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, ServletException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
        String orderIdStr = request.getParameter("vnp_TxnRef");
        Integer orderId = Integer.parseInt(orderIdStr);
        //Update don hang voi payment_status là 0
        Order order = orderDao.findById(orderId);
        order.setPayment_status(0);
        orderDao.update(order);

        if (account != null) {
            //Them vao couponUsage neu co couponCode
            if(order.getCoupon_code() != null){
                Coupon coupon = couponDao.findCouponByCouponCode(order.getCoupon_code());
                //Tao couponUseage
                CouponUsage couponUsage = CouponUsage.builder()
                        .coupon_id(coupon.getId())
                        .account_id(account.getId())
                        .order_id(orderId)
                        .used_at(order.getUpdated_at())
                        .discount_amount(order.getDiscount_amount())
                        .build();
                couponUsageDao.insert(couponUsage);
            }
            response.sendRedirect("orderlist");
        } else {
            //Lấy ra các OrderItem thông qua orderId
            List<OrderItem> listOrderItem = orderItemDao.findAllOrderItemByOrderID(orderId);
            //Goi ham sendOrderViaEmail
            boolean check = EmailUtils.sendOrderViaEmail(order.getEmail(), listOrderItem, orderId,false);
            //tra ve homePage voi notification success
            request.setAttribute("notificationForEmail", check);
            request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
        }
    }

}
