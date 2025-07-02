/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.OrderComboFood;
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
public class OrderComboFoodDAO extends DBContext implements I_DAO<OrderComboFood> {

    @Override
    public List<OrderComboFood> findAll() {
        List<OrderComboFood> orderComboProducts = new ArrayList<>();
        String sql = "SELECT * FROM OrderComboFood";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderComboProducts.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("Error finding all order combo foods: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return orderComboProducts;
    }

    @Override
    public Map<Integer, OrderComboFood> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(OrderComboFood orderComboFood) {
        String sql = "UPDATE OrderComboFood SET orderComboId = ?, foodId = ?, foodName = ?, "
                + "foodPrice = ?, quantityInCombo = ?, totalQuantity = ? WHERE orderComboFoodId = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderComboFood.getOrderComboId());
            statement.setInt(2, orderComboFood.getFoodId());
            statement.setString(3, orderComboFood.getFoodName());
            statement.setDouble(4, orderComboFood.getFoodPrice());
            statement.setInt(5, orderComboFood.getQuantityInCombo());
            statement.setInt(6, orderComboFood.getTotalQuantity());
            statement.setInt(7, orderComboFood.getOrderComboFoodId());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error updating order combo product: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public boolean delete(OrderComboFood orderComboFood) {
 String sql = "DELETE FROM OrderComboFood WHERE orderComboFoodId = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderComboFood.getOrderComboFoodId());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error deleting order combo product: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public int insert(OrderComboFood orderComboFood) {
 String sql = "INSERT INTO oOrderComboFood (orderComboId, foodId,foodName, foodPrice, "
                + "quantityInCombo, totalQuantity) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, orderComboFood.getOrderComboId());
            statement.setInt(2, orderComboFood.getFoodId());
            statement.setString(3, orderComboFood.getFoodName());
            statement.setDouble(4, orderComboFood.getFoodPrice());
            statement.setInt(5, orderComboFood.getQuantityInCombo());
            statement.setInt(6, orderComboFood.getTotalQuantity());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order combo product failed, no rows affected.");
            }

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Creating order combo product failed, no ID obtained.");
            }
        } catch (SQLException ex) {
            System.out.println("Error inserting order combo product: " + ex.getMessage());
            return -1;
        } finally {
            closeResources();
        }
    }

    @Override
    public OrderComboFood getFromResultSet(ResultSet rs) throws SQLException {
  OrderComboFood orderComboFood = new OrderComboFood();
        orderComboFood.setOrderComboFoodId(rs.getInt("orderComboFoodId"));
        orderComboFood.setOrderComboId(rs.getInt("orderComboId"));
        orderComboFood.setFoodId(rs.getInt("foodId"));
        orderComboFood.setFoodName(rs.getString("foodName"));
        orderComboFood.setFoodPrice(rs.getDouble("foodPrice"));
        orderComboFood.setQuantityInCombo(rs.getInt("quantityInCombo"));
        orderComboFood.setTotalQuantity(rs.getInt("totalQuantity"));
        return orderComboFood;   
    }

    @Override
    public OrderComboFood findById(Integer orderComboFoodId) {
String sql = "SELECT * FROM orderComboFood WHERE orderComboFoodId = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderComboFoodId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            System.out.println("Error finding order combo product by ID: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return null;    }

}
