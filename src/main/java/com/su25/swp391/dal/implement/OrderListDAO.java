package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.entity.OrderList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderListDAO extends DBContext {

    public List<OrderList> getOrderListByUserId(int userId) {
        List<OrderList> orderLists = new ArrayList<>();
        String sql = "SELECT "
                + "f.image_url, "
                + "f.name AS food_name, "
                + "o.order_id, "
                + "f.price AS food_price, "
                + "oi.quantity, "
                + "o.payment_method, "
                + "o.status, "
                + "oi.order_item_id "
                + "FROM Order_Items oi "
                + "JOIN Orders o ON oi.order_id = o.order_id "
                + "JOIN Food f ON oi.food_id = f.id "
                + "WHERE o.user_id = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderList ol = new OrderList();
                ol.setOrderId(resultSet.getInt("order_id"));
                ol.setFoodName(resultSet.getString("food_name"));
                ol.setImageUrl(resultSet.getString("image_url"));
                ol.setFoodPrice(resultSet.getDouble("food_price"));
                ol.setQuantity(resultSet.getInt("quantity"));
                ol.setPaymentMethod(resultSet.getString("payment_method"));
                ol.setStatus(resultSet.getString("status"));
                ol.setOrderItemId(resultSet.getInt("order_item_id"));
                orderLists.add(ol);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return orderLists;
    }

    public List<OrderList> searchOrderListByUserIdAndKeyword(int userId, String keyword) {
        List<OrderList> orderLists = new ArrayList<>();
        String sql = "SELECT "
                + "f.image_url, "
                + "f.name AS food_name, "
                + "o.order_id, "
                + "f.price AS food_price, "
                + "oi.quantity, "
                + "o.payment_method, "
                + "o.status, "
                + "oi.order_item_id "
                + "FROM Order_Items oi "
                + "JOIN Orders o ON oi.order_id = o.order_id "
                + "JOIN Food f ON oi.food_id = f.id "
                + "WHERE o.user_id = ? AND f.name LIKE ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setString(2, "%" + keyword + "%"); // LIKE '%keyword%'
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderList ol = new OrderList();
                ol.setOrderId(resultSet.getInt("order_id"));
                ol.setFoodName(resultSet.getString("food_name"));
                ol.setImageUrl(resultSet.getString("image_url"));
                ol.setFoodPrice(resultSet.getDouble("food_price"));
                ol.setQuantity(resultSet.getInt("quantity"));
                ol.setPaymentMethod(resultSet.getString("payment_method"));
                ol.setStatus(resultSet.getString("status"));
                ol.setOrderItemId(resultSet.getInt("order_item_id"));
                orderLists.add(ol);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return orderLists;
    }
    public static void main(String[] args) {
        OrderListDAO dao = new OrderListDAO();

        int userId = 1; // giả sử user ID = 1
        List<OrderList> orders = dao.getOrderListByUserId(userId);

        for (OrderList order : orders) {
            System.out.println(order);
        }

        if (orders.isEmpty()) {
            System.out.println("No orders found for user ID: " + userId);
        }
    }

}
