/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.ComboFood;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hang
 */
public class ComboFoodDAO extends DBContext implements I_DAO<ComboFood>{

   

    @Override
    public List<ComboFood> findAll() {
          List<ComboFood> comboFood = new ArrayList<>();
        String sql = "SELECT * FROM ComboFood";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comboFood.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("Error finding all combo food: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return comboFood;
    }

    @Override
    public Map<Integer, ComboFood> findAllMap() {
         Map<Integer, ComboFood> comboMap = new HashMap<>();
        String sql = "SELECT * FROM ComboFood";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ComboFood combo = getFromResultSet(resultSet);
                comboMap.put(combo.getComboId(), combo);
            }
        } catch (SQLException e) {
            System.out.println("Err finding all combos" + e.getMessage());
        } finally {
            closeResources();
        }

        return comboMap;
    }

    @Override
    public boolean update(ComboFood t) {
        String sql = "UPDATE ComboFood SET comboId = ?, foodId = ?, quantityInCombo = ? WHERE id = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,t.getComboId());
            statement.setInt(2, t.getFoodId());
            statement.setInt(3, t.getQuantityInCombo());
            statement.setInt(4, t.getId());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error updating combo food: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }
    @Override
    public boolean delete(ComboFood t) {
         String sql = "DELETE FROM ComboFood WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getId());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error deleting combo food: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

     
    public boolean deleteByComboId(Integer comboId) {
         String sql = "DELETE FROM ComboFood WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, comboId);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error deleting combo food: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }
    @Override
    public int insert(ComboFood t) {
    String sql = "INSERT INTO ComboFood (comboId, foodId, quantityInCombo) VALUES (?, ?, ?)";
    int result = 0;

    try {
        connection = getConnection();
        statement = connection.prepareStatement(sql);
        statement.setInt(1, t.getComboId());
        statement.setInt(2, t.getFoodId());
        statement.setInt(3, t.getQuantityInCombo());

        result = statement.executeUpdate(); // Trả về số dòng bị ảnh hưởng

    } catch (SQLException ex) {
        System.out.println("❌ Error inserting combo-food: " + ex.getMessage());
        ex.printStackTrace();
        result = -1;
    } finally {
        closeResources();
    }

    return result;
}
//    public int insert(ComboFood t) {
//        String sql = "INSERT INTO ComboFood (comboId, foodId, quantityInCombo) VALUES (?, ?, ?)";
//
//        try {
//            connection = getConnection();
//            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            statement.setInt(1, t.getComboId());
//            statement.setInt(2, t.getFoodId());
//            statement.setInt(3, t.getQuantityInCombo());
//
//            int affectedRows = statement.executeUpdate();
//
//            if (affectedRows == 0) {
//                throw new SQLException("Creating combo product failed, no rows affected.");
//            }
//            resultSet = statement.getGeneratedKeys();
//            if (resultSet.next()) {
//                return resultSet.getInt(1);
//            } else {
//                throw new SQLException("Creating combo product failed, no ID obtained.");
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error inserting combo product: " + ex.getMessage());
//            return -1;
//        } finally {
//            closeResources();
//        }
//    }

    @Override
    public ComboFood getFromResultSet(ResultSet rs) throws SQLException {
         ComboFood comboFood = new ComboFood();
        comboFood.setId(rs.getInt("id"));
        comboFood.setComboId(rs.getInt("comboId"));
        comboFood.setFoodId(rs.getInt("foodId"));
        comboFood.setQuantityInCombo(rs.getInt("quantityInCombo"));
        return comboFood;
    }
    @Override
    public ComboFood findById(Integer id) {
         List<ComboFood> comboFood = new ArrayList<>();
        String sql = "SELECT * FROM ComboFood WHERE comboId = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comboFood.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("Error finding combo products by combo ID: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }
    
    
}
