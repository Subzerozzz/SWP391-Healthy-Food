/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.OrderCombo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hang
 */
public class OrderComboDAO extends DBContext implements I_DAO<OrderCombo> {

    @Override
    public List<OrderCombo> findAll() {
        List<OrderCombo> orderCombos = new ArrayList<>();
        String sql = "SELECT * FROM OrderCombo";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderCombos.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("Error finding all order combos: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return orderCombos;

    }

    @Override
    public Map<Integer, OrderCombo> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(OrderCombo orderCombo) {
        String sql = "UPDATE OrderCombo SET order_id = ?,  comboId = ?, comboName = ?, "
                + "discountPrice = ?, quantity = ?, totalPrice = ? ,user_id=? WHERE orderComboId = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderCombo.getComboId());
            statement.setString(2, orderCombo.getComboName());
            statement.setDouble(3, orderCombo.getDiscountPrice());
            statement.setInt(4, orderCombo.getQuantity());
            statement.setDouble(5, orderCombo.getTotalPrice());
            statement.setInt(6, orderCombo.getUser_id());
            statement.setInt(7, orderCombo.getOrderComboId());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error updating order combo: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public boolean delete(OrderCombo orderCombo) {
        String sql = "DELETE FROM orderCombo WHERE orderComboId = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderCombo.getOrderComboId());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error deleting order combo: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public int insert(OrderCombo orderCombo) {
        String sql = "INSERT INTO OrderCombo ( comboId, comboName, discountPrice, "
                + "quantity, totalPrice,payment_status,user_id) VALUES ( ?, ?, ?, ?, ?,?,?)";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, orderCombo.getComboId());
            statement.setString(2, orderCombo.getComboName());
            statement.setDouble(3, orderCombo.getDiscountPrice());
            statement.setInt(4, orderCombo.getQuantity());
            statement.setDouble(5, orderCombo.getTotalPrice());
            statement.setInt(6, orderCombo.getPayment_status());
            statement.setInt(7, orderCombo.getUser_id());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order combo failed, no rows affected.");
            }

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Creating order combo failed, no ID obtained.");
            }
        } catch (SQLException ex) {
            System.out.println("Error inserting order combo: " + ex.getMessage());
            return -1;
        } finally {
            closeResources();
        }
    }

    @Override
    public OrderCombo getFromResultSet(ResultSet rs) throws SQLException {
        OrderCombo orderCombo = new OrderCombo();

        orderCombo.setOrderComboId(rs.getInt("orderComboId"));
        orderCombo.setComboId(rs.getInt("comboId"));
        orderCombo.setComboName(rs.getString("comboName"));
        orderCombo.setDiscountPrice(rs.getDouble("discountPrice"));
        orderCombo.setQuantity(rs.getInt("quantity"));
        orderCombo.setTotalPrice(rs.getDouble("totalPrice"));
        orderCombo.setPayment_status(rs.getInt("payment_status"));
        orderCombo.setUser_id(rs.getInt("user_id"));
        return orderCombo;
    }

    @Override
    public OrderCombo findById(Integer id) {
        String sql = "SELECT * FROM OrderCombo WHERE orderComboId = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            System.out.println("Error finding order combo by ID: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }

    public void updatePaymentStatus(int orderComboId, int status) {
        String sql = "UPDATE OrderCombo SET payment_status = ? WHERE orderComboId = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, status);
            statement.setInt(2, orderComboId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error updating payment status: " + ex.getMessage());
        } finally {
            closeResources();
        }
    }
    
}
