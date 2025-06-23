/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Combo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hang
 */
public class ComboDAO extends DBContext implements I_DAO<Combo> {

    @Override
    public List<Combo> findAll() {
        List<Combo> combos = new ArrayList<>();
        String sql = "SELECT * FROM Combo";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                combos.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Err finding all combos" + e.getMessage());
        } finally {
            closeResources();
        }

        return combos;
    }

    @Override
    public Map<Integer, Combo> findAllMap() {
        Map<Integer, Combo> comboMap = new HashMap<>();
        String sql = "SELECT * FROM Combo";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Combo combo = getFromResultSet(resultSet);
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
    public boolean update(Combo combo) {
        String sql = "UPDATE Combo\n"
                + "SET \n"
                + "    name = ?,\n"
                + "    description = ?,\n"
                + "    originalPrice = ?,\n"
                + "    discountPrice = ?,\n"
                + "    status = ?\n"
                + "WHERE ComboId = ?";
        try {
             connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,combo.getName());
            statement.setString(2, combo.getDescription());
            statement.setDouble(3, combo.getOriginalPrice());
            statement.setDouble(4, combo.getDiscountPrice());
            statement.setString(5, combo.getStatus());
            statement.setInt(6,combo.getComboId());
            
            int affectecdRows = statement.executeUpdate();
            return affectecdRows>0;
        } catch (Exception e) {
            System.out.println("Erroe updating combo" + e.getMessage());
            return false;            
        } finally {
            closeResources();
        }
    }

    @Override
    public boolean delete(Combo t) {
      String sql = "DELETE From Combo WHERE comboId = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getComboId());
            int affectecdRows = statement.executeUpdate();
         return affectecdRows >0;
        } catch (Exception e) {
            System.out.println("Error delete combo" + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public int insert(Combo t) {
      String sql = "INSERT INTO Combo (ComboId, name, description, originalPrice, discountPrice, status)\n" +
"VALUES\n" + "(?,?,?,?,?,?)";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getComboId());
            statement.setString(2, t.getName());
            statement.setString(3, t.getDescription());
            statement.setDouble(4, t.getOriginalPrice());
            statement.setDouble(5, t.getDiscountPrice());
            statement.setString(6, t.getStatus());
            int affectedRow = statement.executeUpdate();
            if(affectedRow == 0){
                throw new SQLException("Creating combo fail ,no rows affected");
                
            }
           if(resultSet.next()){
               return resultSet.getInt(1);
           }else{
               throw  new SQLException("Creating combo fail ,no rows affected");
           }
        } catch (Exception e) {
              System.out.println("Error insert fail" + e.getMessage());
              return -1;
        } finally {
            closeResources();
        }
    }

    @Override
    public Combo getFromResultSet(ResultSet resultSet) throws SQLException {
        Combo combo = new Combo();
        combo.setComboId(resultSet.getInt("ComboId"));
        combo.setName(resultSet.getString("name"));
        combo.setDescription(resultSet.getString("description"));
        combo.setOriginalPrice(resultSet.getDouble("originalPrice"));
        combo.setDiscountPrice(resultSet.getDouble("discountPrice"));
        combo.setStatus(resultSet.getString("status"));
        return combo;
    }

    @Override
    public Combo findById(Integer id) {
String sql = "Select *from Combo Where ComboId = ? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return getFromResultSet(resultSet);
            }
        } catch (Exception e) {
            System.out.println("Error findById fail" + e.getMessage());
        } finally {
            closeResources();
        }
        return  null;
    }
    //Loc theo trangj thai

}
