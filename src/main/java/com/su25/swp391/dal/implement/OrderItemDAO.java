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
import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.OrderItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            connection = getConnection();
            String sql = "SELECT * FROM OrderItem";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                orderItems.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return orderItems;
    }

    @Override
    public Map<Integer, OrderItem> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(OrderItem orderItem) {
        try {
            connection = getConnection();
            String sql = "UPDATE OrderItem SET order_id = ?, food_id = ?, quantity = ?, "
                    + "price = ?, updated_at = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, orderItem.getOrder_id());
            statement.setInt(2, orderItem.getFood_id());
            statement.setInt(3, orderItem.getQuantity());
            statement.setDouble(4, orderItem.getPrice());
            statement.setTimestamp(5, orderItem.getUpdated_at());
            statement.setInt(6, orderItem.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean delete(OrderItem orderItem) {
        try {
            connection = getConnection();
            String sql = "DELETE FROM OrderItem WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderItem.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public int insert(OrderItem orderItem) {
        try {
            connection = getConnection();
            String sql = "INSERT INTO OrderItem (order_id, food_id, quantity, price, created_at, updated_at) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setInt(1, orderItem.getOrder_id());
            statement.setInt(2, orderItem.getFood_id());
            statement.setInt(3, orderItem.getQuantity());
            statement.setDouble(4, orderItem.getPrice());
            statement.setTimestamp(5, orderItem.getCreated_at());
            statement.setTimestamp(6, orderItem.getUpdated_at());

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeResources();
        }
        return -1;
    }

    @Override
    public OrderItem getFromResultSet(ResultSet rs) throws SQLException {
        OrderItem item = new OrderItem();
        item.setId(rs.getInt("id"));
        item.setOrder_id(rs.getInt("order_id"));
        item.setFood_id(rs.getInt("food_id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setPrice(rs.getDouble("price"));
        item.setCreated_at(rs.getTimestamp("created_at"));
        item.setUpdated_at(rs.getTimestamp("updated_at"));
        return item;
    }

    public List<OrderItem> findAllOrderItemByOrderID(Integer id) {
        List<OrderItem> list = new ArrayList<>();
        String sql = "Select * From OrderItem WHERE order_id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources();
        }
        return list;
    }

    @Override
    public OrderItem findById(Integer id) {
        try {
            connection = getConnection();
            String sql = "SELECT * FROM OrderItem WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return null;
    }
    
    public List<OrderItem> findOrderItemsByOrderId(int order_id) {  //getOrderDetailByOrderId
        List<OrderItem> orderDetails = new ArrayList<>();
        String sql = "SELECT "
                + "oi.id, "
                + "oi.order_id, "
                + "oi.food_id, "
                + "oi.quantity, "
                + "oi.price, "
                + "oi.created_at, "
                + "oi.updated_at "
                + "FROM OrderItem oi "
                + "WHERE oi.order_id = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, order_id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderItem od = getFromResultSet(resultSet);
                orderDetails.add(od);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return orderDetails;
    }

    public static void main(String[] args) {
        for (OrderItem o : new OrderItemDAO().findAllOrderItemByOrderID(41)) {
            System.out.println(o);
        }
    }

}
