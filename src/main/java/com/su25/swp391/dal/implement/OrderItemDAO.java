/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

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

/**
 *
 * @author Dell
 */
public class OrderItemDAO extends DBContext implements I_DAO<OrderItem> {

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
    public OrderItem getFromResultSet(ResultSet resultSet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

}
