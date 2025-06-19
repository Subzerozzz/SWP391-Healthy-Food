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
        String sql = "SELECT " +
                "f.image_url, " +
                "f.name AS food_name, " +
                "o.order_id, " +
                "f.price AS food_price, " +
                "oi.quantity, " +
                "o.payment_method, " +
                "o.status " +
                "FROM Order_Items oi " +
                "JOIN Orders o ON oi.order_id = o.order_id " +
                "JOIN Food f ON oi.food_id = f.id " +
                "WHERE o.user_id = ?";

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
         int testUserId = 1;
         OrderListDAO dao = new OrderListDAO();
    List<OrderList> orders = dao.getOrderListByUserId(1);

    if (orders == null || orders.isEmpty()) {
        System.out.println("Không có đơn hàng nào cho user_id = " + testUserId);
    } else {
        System.out.println("Danh sách đơn hàng của user_id = " + testUserId + ":");
        for (OrderList order : orders) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Tên món: " + order.getFoodName());
            System.out.println("Giá: " + order.getFoodPrice());
            System.out.println("Số lượng: " + order.getQuantity());
            System.out.println("Thanh toán: " + order.getPaymentMethod());
            System.out.println("Trạng thái: " + order.getStatus());
            System.out.println("Ảnh: " + order.getImageUrl());
            System.out.println("------");
        }
    }
}
}


