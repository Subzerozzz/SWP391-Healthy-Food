/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.OrderItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderItemDAO extends DBContext implements I_DAO<OrderItem> {

    // Lấy danh sách items của một đơn hàng
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        List<OrderItem> items = new ArrayList<>();

        String sql = "SELECT * "
                + "FROM OrderItem  "
                + "WHERE order_id = ?";

        try {

            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                items.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return items;
    }

    /**
     * Insert a new order item
     * 
     * @param item
     * @return
     */
    // public boolean insert(OrderItem item) {
    // String sql = "INSERT INTO OrderItem (order_id, product_id, quantity, price,
    // created_at, updated_at) "
    // + "VALUES (?, ?, ?, ?, NOW(), NOW())";
    //
    // try {
    // connection = getConnection();
    // statement = connection.prepareStatement(sql);
    // statement.setInt(1, item.getOrderId());
    // statement.setInt(2, item.getFoodId());
    // statement.setInt(3, item.getQuantity());
    // statement.setBigDecimal(4, item.getPrice());
    //
    // int affectedRows = statement.executeUpdate();
    // return affectedRows > 0;
    // } catch (SQLException ex) {
    // System.out.println("Error inserting order item: " + ex.getMessage());
    // return false;
    // } finally {
    // closeResources();
    // }
    // }

    @Override
    public List<OrderItem> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<Integer, OrderItem> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(OrderItem t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(OrderItem t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public OrderItem getFromResultSet(ResultSet resultSet) throws SQLException {
        return OrderItem
                .builder()
                .id(resultSet.getInt("id"))
                .order_id(resultSet.getInt("order_id"))
                .food_id(resultSet.getInt("food_id"))
                .quantity(resultSet.getInt("quantity"))
                .price(resultSet.getDouble("price"))
                .created_at(resultSet.getTimestamp("created_at"))
                .updated_at(resultSet.getTimestamp("updated_at"))
                .build();
    }

    @Override
    public OrderItem findById(Integer id) {
        String sql = "SELECT * FROM OrderItem WHERE id = ?";
         try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            System.out.println("Error getting order item by ID: " + ex.getMessage());
        } finally {
            closeResources();
        }

        return null;
    }

    @Override
    public int insert(OrderItem t) {
        String sql = "INSERT INTO OrderItem (order_id, product_id, quantity, price, created_at, updated_at) "
                + "VALUES (?, ?, ?, ?, NOW(), NOW())";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getOrder_id());
            statement.setInt(2, t.getFood_id());
            statement.setInt(3, t.getQuantity());
            statement.setDouble(4, t.getPrice());

            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error inserting order item: " + ex.getMessage());
            return -1;
        } finally {
            closeResources();
        }
    }

    public static void main(String[] args) {
        OrderItemDAO o = new OrderItemDAO();
        List<OrderItem> l = o.getOrderItemsByOrderId(60);
        for (OrderItem orderItem : l) {
            System.out.println(orderItem);
        }

    }
}
