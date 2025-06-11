/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Food;
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
public class FoodDAO extends DBContext implements I_DAO<Food> {

  @Override
  public List<Food> findAll() {
    String sqlString = "SELECT * FROM Food";
    List<Food> foodList = new ArrayList<>();
    try {
      statement = connection.prepareStatement(sqlString);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Food food = getFromResultSet(resultSet);
        foodList.add(food);
      }
    } catch (SQLException ex) {
      Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return foodList;
  }

  @Override
  public Map<Integer, Food> findAllMap() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
    // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public boolean update(Food t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
    // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public boolean delete(Food t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
    // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public int insert(Food t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
    // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public Food getFromResultSet(ResultSet resultSet) throws SQLException {
    Food food = new Food();
    food.setId(resultSet.getInt("id"));
    food.setName(resultSet.getString("name"));
    food.setDescription(resultSet.getString("description"));
    food.setPrice(resultSet.getDouble("price"));
    food.setImage_url(resultSet.getString("image_url"));
    food.setStatus(resultSet.getString("status"));
    food.setCategory_id(resultSet.getInt("category_id"));
    food.setCreated_at(resultSet.getTimestamp("created_at"));
    food.setUpdated_at(resultSet.getTimestamp("updated_at"));
    food.setNutri_id(Integer.parseInt(resultSet.getString("nutri_id")));
    food.setCalo(resultSet.getDouble("calo"));
    return food;
  }

  @Override
  public Food findById(Integer id) {
    String sqlString = "SELECT * FROM Food WHERE id = ?";
    Food food = null;
    try {
      statement = connection.prepareStatement(sqlString);
      statement.setInt(1, id);
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        food = getFromResultSet(resultSet);
      }
    } catch (SQLException ex) {
      Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return food;
  }

  public Food checkExistFoodName(String name) {
    Food food = null;
    String sql = "Select * From Food WHERE name = ?";
    try {
      statement = connection.prepareStatement(sql);
      // set giá trị vào name
      statement.setString(1, name);
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        food = getFromResultSet(resultSet);
      }
    } catch (Exception e) {
    }
    return food;
  }

  public List<Food> findByCategoryId(Integer categoryId) {
    List<Food> foodList = new ArrayList<>();
    String sql = "SELECT * FROM Food WHERE category_id = ?";
    try {
      statement = connection.prepareStatement(sql);
      statement.setInt(1, categoryId);
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        foodList.add(getFromResultSet(resultSet));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return foodList;
  }

  public List<Food> getFoodByName(String foodName) {
    List<Food> listFood = null;
    String sql = "SELECT * FROM Food"
        + " WHERE name LIKE ?";
    try {
      statement = connection.prepareStatement(sql);
      statement.setString(1, "%" + foodName + "%");
      resultSet = statement.executeQuery();
      listFood = new ArrayList<>();
      while (resultSet.next()) {
        listFood.add(getFromResultSet(resultSet));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return listFood;
  }

  public List<Food> findRecordByPage(int limit,int i) {
    List<Food> list = new ArrayList<>();
    String sql = "SELECT *\n"
        + "FROM Food\n"
        + "ORDER BY id\n"
        + "LIMIT ? OFFSET ?;";
    // Tính số bản ghi cần bỏ qua
    Integer recordOffset = (i - 1) * 10;
    try {
      statement = connection.prepareStatement(sql);
      statement.setInt(1, limit);
      statement.setInt(2, recordOffset);

      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        list.add(getFromResultSet(resultSet));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return list;
  }

  public List<Food> findRecordByPageForCategory(Integer categoryID, int i, Integer limit) {
    List<Food> list = new ArrayList<>();
    String sql = "SELECT *\n"
        + "FROM Food\n"
        + "WHERE category_id = ?\n"
        + "ORDER BY id\n"
        + "LIMIT ? OFFSET ?";
    // Tính số bản ghi cần bỏ qua
    Integer recordOffset = (i - 1) * 10;
    try {
      statement = connection.prepareStatement(sql);
      statement.setInt(1, categoryID);
      statement.setInt(2, limit);
      statement.setInt(3, recordOffset);

      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        list.add(getFromResultSet(resultSet));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return list;
  }

  public List<Food> getRecordByPageForSearch(String foodName, int i) {
    List<Food> list = new ArrayList<>();
    String sql = "SELECT *\n"
        + "FROM Food\n"
        + "WHERE name LIKE ?\n"
        + "ORDER BY id\n"
        + "LIMIT 10 OFFSET ?";
    // Tính số bản ghi cần bỏ qua
    Integer recordOffset = (i - 1) * 10;
    try {
      statement = connection.prepareStatement(sql);
      statement.setString(1, "%" + foodName + "%");
      statement.setInt(2, recordOffset);

      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        list.add(getFromResultSet(resultSet));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return list;
  }

  public static void main(String[] args) {
    for (Food a : new FoodDAO().getRecordByPageForSearch("gà", 1)) {
      System.out.println(a);
    }
  }

}
