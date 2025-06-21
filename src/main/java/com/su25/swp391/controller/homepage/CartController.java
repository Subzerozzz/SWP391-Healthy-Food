/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.homepage;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.CartDAO;
import com.su25.swp391.dal.implement.CartItemDAO;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Cart;
import com.su25.swp391.entity.CartItem;
import com.su25.swp391.entity.Food;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    CartDAO cartDao = new CartDAO();
    CartItemDAO cartItemDao = new CartItemDAO();
    FoodDAO foodDao = new FoodDAO();

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
            //Lấy ra cartId theo accountId
            Cart cart = cartDao.findCartByAccountId(account.getId());
            //Lấy ra tất cả cartItem theo cartId
            List<CartItem> listCartItem = cartItemDao.findAllCartItemByCartId(cart.getId());
            Map<Integer, Food> foodMap = new HashMap<>();
            for (CartItem item : listCartItem) {
                Food food = foodDao.findById(item.getFood_id());
                foodMap.put(item.getFood_id(), food);
            }
            //Trả listCartItem ra JSP
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
            //Trả listCartItem ra JSP
            request.setAttribute("listCartItem", listCartItem);
            request.setAttribute("foodMap", foodMap);
            request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
        }
    }

    private void showCheckout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
            Integer subTotal = Integer.parseInt(request.getParameter("subTotal"));
            Integer totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
            List<CartItem> listCartItem = new ArrayList<>();

            if (account != null) {
                //Lấy ra cartId theo accountId
                Cart cart = cartDao.findCartByAccountId(account.getId());
                //Lấy ra tất cả cartItem theo cartId
                listCartItem = cartItemDao.findAllCartItemByCartId(cart.getId());
            } else {
                listCartItem = (List<CartItem>) session.getAttribute("cart");
                if (listCartItem == null) {
                    listCartItem = new ArrayList<>();
                }
            }
            //cart rong thi tra ve JSP va thong bao
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

                //Tạo cartItem
                CartItem newCartItem = CartItem.builder()
                        .cart_id(cartId)
                        .food_id(id)
                        .quantity(quantity)
                        .created_at(created_at)
                        .updated_at(updated_at)
                        .build();

                //Check xem newcartItem đó đã tồn tại trong cartID này chưa
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
                //Check xem trong listCartItem đã tồn tại sản phẩm này chưa
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

            //Chuyển về trang shopDetail và báo 
            request.setAttribute("listRelated", listRelated);
            request.setAttribute("foodDetail", food);
            request.setAttribute("addSuccess", true);
            request.getRequestDispatcher("view/homePage/shopDetail.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void updateItemToCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
        //Lay ra list foodId tuong ung voi so quantity moi
        String[] quantityList = request.getParameterValues("quantity");
        String[] foodIdList = request.getParameterValues("foodId");

        //Convert
        List<Integer> listID = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();//value1 la id, value 2 la quantity

        for (int i = 0; i < foodIdList.length; i++) {
            Integer foodId = Integer.parseInt(foodIdList[i]);
            Integer quantity = Integer.parseInt(quantityList[i]);
            listID.add(foodId);
            map.put(foodId, quantity);
        }

        List<CartItem> listCartItem = new ArrayList<>();

        if (account != null) {
            //Lay ra cartID theo accID
            Cart cart = cartDao.findCartByAccountId(account.getId());
            //Lấy ra tất cả cartItem theo cartId
            listCartItem = cartItemDao.findAllCartItemByCartId(cart.getId());

            //Cap nhat lai quantiy cho nhung foodId nay trong listCartItem
            for (Integer id : listID) {
                for (int i = 0; i < listCartItem.size(); i++) {
                    if (listCartItem.get(i).getFood_id() == id) {
                        listCartItem.get(i).setQuantity(map.get(id));
                    }
                }
            }
            //chuan bi foodMap cho JSP
            Map<Integer, Food> foodMap = new HashMap<>();
            for (CartItem item : listCartItem) {
                Food food = foodDao.findById(item.getFood_id());
                foodMap.put(item.getFood_id(), food);
            }
            //Co the cap nhat lai ca coupon neu co

            //Trả listCartItem ra JSP
            request.setAttribute("listCartItem", listCartItem);
            request.setAttribute("foodMap", foodMap);
            request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
        } else {
            listCartItem = (List<CartItem>) session.getAttribute("cart");

            //Cap nhat lai quantiy cho nhung foodId nay trong listCartItem
            for (Integer id : listID) {
                for (int i = 0; i < listCartItem.size(); i++) {
                    if (listCartItem.get(i).getFood_id() == id) {
                        listCartItem.get(i).setQuantity(map.get(id));
                    }
                }
            }
            //chuan bi foodMap cho JSP
            Map<Integer, Food> foodMap = new HashMap<>();
            for (CartItem item : listCartItem) {
                Food food = foodDao.findById(item.getFood_id());
                foodMap.put(item.getFood_id(), food);
            }

            //Trả listCartItem ra JSP
            request.setAttribute("listCartItem", listCartItem);
            request.setAttribute("foodMap", foodMap);
            request.getRequestDispatcher("view/homePage/cart.jsp").forward(request, response);
        }

    }

    private void deleteItemToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay ban ghi can xoa
        Integer deleteID = Integer.parseInt(request.getParameter("deleteId"));
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

        if (account != null) {
            //Lay ra cartID theo accID
            Cart cart = cartDao.findCartByAccountId(account.getId());
            Integer cartId = cart.getId();
            //Lấy ra tất cả cartItem theo cartId
            List<CartItem> listCartItem = cartItemDao.findAllCartItemByCartId(cartId);
            //Tim cartItemId theo deleteID do
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

}
