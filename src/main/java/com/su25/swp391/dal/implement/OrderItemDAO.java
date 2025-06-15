package com.su25.swp391.dal.implement;

import com.su25.swp391.entity.OrderItem;
import com.su25.swp391.dal.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO extends DBContext {

    // Lấy danh sách items của một đơn hàng
    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        List<OrderItem> items = new ArrayList<>();

        String sql = "SELECT oi.*, p.name as food_name, p.image as food_image "
                + "FROM order_items oi "
                + "JOIN foods p ON oi.food_id = p.food_id "
                + "WHERE oi.order_id = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderItem item = new OrderItem();
                item.setOrderItemId(resultSet.getInt("order_item_id"));
                item.setOrderId(resultSet.getInt("order_id"));
                item.setFoodId(resultSet.getInt("food_id"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getBigDecimal("price"));
                item.setCreatedAt(resultSet.getTimestamp("created_at"));
                item.setUpdatedAt(resultSet.getTimestamp("updated_at"));

                items.add(item);
            }
        } finally {
            closeResources();
        }

        return items;
    }

    /**
     * Find all order items for a specific order
     */
    public List<OrderItem> findByOrderId(int orderId) {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT oi.*, f.name as food_name, f.image_url as food_image, f.description " +
             "FROM Order_Items oi " +
             "JOIN Food f ON oi.food_id = f.id " +
             "WHERE oi.order_id = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderItem item = new OrderItem();
                item.setOrderItemId(resultSet.getInt("order_item_id"));
                item.setOrderId(resultSet.getInt("order_id"));
                item.setFoodId(resultSet.getInt("food_id"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getBigDecimal("price"));
                item.setCreatedAt(resultSet.getTimestamp("created_at"));
                item.setUpdatedAt(resultSet.getTimestamp("updated_at"));

                items.add(item);
            }
        } catch (SQLException ex) {
            System.out.println("Error finding order items by order ID: " + ex.getMessage());
        } finally {
            closeResources();
        }

        return items;
    }
    public List<OrderItem> findByFoodID(int foodID) {
        List<OrderItem> items = new ArrayList<>();
         String sql = "SELECT oi.*, f.name AS food_name, f.description AS food_description, " +
                 "f.price AS food_price, f.image_url, f.status AS food_status " +
                 "FROM Order_Items oi " +
                 "JOIN Food f ON oi.food_id = f.id " +
                 "WHERE f.id = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, foodID);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderItem item = new OrderItem();
                item.setOrderItemId(resultSet.getInt("order_item_id"));
                item.setOrderId(resultSet.getInt("order_id"));
                item.setFoodId(resultSet.getInt("food_id"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getBigDecimal("price"));
                item.setCreatedAt(resultSet.getTimestamp("created_at"));
                item.setUpdatedAt(resultSet.getTimestamp("updated_at"));

                items.add(item);
            }
        } catch (SQLException ex) {
            System.out.println("Error finding order items by order ID: " + ex.getMessage());
        } finally {
            closeResources();
        }

        return items;
    }

    /**
     * Insert a new order item
     */
    public boolean insert(OrderItem item) {
        String sql = "INSERT INTO order_items (order_id, food_id, quantity, price, created_at, updated_at) "
                + "VALUES (?, ?, ?, ?, NOW(), NOW())";
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, item.getOrderId());
            statement.setInt(2, item.getFoodId());
            statement.setInt(3, item.getQuantity());
            statement.setBigDecimal(4, item.getPrice());
            
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error inserting order item: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    /**
     * Get order item by ID
     * @param orderItemId ID of the order item to get
     * @return OrderItem object if found, null otherwise
     */
    public OrderItem getOrderItemById(int orderItemId) {
        String sql = "SELECT oi.*, p.name as food_name, p.image as food_image "
                + "FROM order_items oi "
                + "JOIN foods p ON oi.food_id = p.food_id "
                + "WHERE oi.order_item_id = ?";
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderItemId);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                OrderItem item = new OrderItem();
                item.setOrderItemId(resultSet.getInt("order_item_id"));
                item.setOrderId(resultSet.getInt("order_id"));
                item.setFoodId(resultSet.getInt("food_id"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getBigDecimal("price"));
                item.setCreatedAt(resultSet.getTimestamp("created_at"));
                item.setUpdatedAt(resultSet.getTimestamp("updated_at"));

                return item;
            }
        } catch (SQLException ex) {
            System.out.println("Error getting order item by ID: " + ex.getMessage());
        } finally {
            closeResources();
        }
        
        return null;
    }

//     public static void main(String[] args) {
//        OrderItemDAO dao = new OrderItemDAO();
//
//        int testOrderId = 1; // Thay bằng order_id thật có trong DB
//
//        List<OrderItem> items = dao.findByOrderId(testOrderId);
//
//        if (items.isEmpty()) {
//            System.out.println("No items found for order ID " + testOrderId);
//        } else {
//            System.out.println("Items for order ID " + testOrderId + ":");
//            for (OrderItem item : items) {
//                System.out.println("Item ID: " + item.getOrderItemId());
//                System.out.println("Food Name: " + item.getFoodName());
//                System.out.println("Quantity: " + item.getQuantity());
//                System.out.println("Price: " + item.getPrice());
//                System.out.println("Created At: " + item.getCreatedAt());
//                System.out.println("Image: " + item.getFoodImage());
//                System.out.println("---------------------------");
//            }
//        }
//    }
}