/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.FoodDraft;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class FoodDraftDAO extends DBContext implements I_DAO<FoodDraft> {

  @Override
  public List<FoodDraft> findAll() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public Map<Integer, FoodDraft> findAllMap() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public boolean update(FoodDraft t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public boolean delete(FoodDraft t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public int insert(FoodDraft t) {
    String sql = "INSERT INTO Food_draft (name, description, price, image_url, category_id, created_at, updated_at,food_id,type,nutri_id)"
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
      // gan giá tri vao
      statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, t.getName());
      statement.setString(2, t.getDescription());
      statement.setDouble(3, t.getPrice());
      statement.setString(4, t.getImage_url());
      statement.setInt(5, t.getCategory_id());
      statement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
      statement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
      statement.setInt(8, t.getFood_id());
      statement.setString(9, t.getType());
      statement.setInt(10, t.getNutri_id());

      // thuc thi cau lenh
      statement.executeUpdate();
      resultSet = statement.getGeneratedKeys();
      if (resultSet.next()) {
        System.out.println("Insert thành công");
        return resultSet.getInt(1);
      }

    } catch (SQLException ex) {
      Logger.getLogger(FoodDraftDAO.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println(ex);
    }
    return -1;
  }

  @Override
  public FoodDraft getFromResultSet(ResultSet resultSet) throws SQLException {
    FoodDraft product = new FoodDraft();
    product.setId(resultSet.getInt("id"));
    product.setName(resultSet.getString("name"));
    product.setDescription(resultSet.getString("description"));
    product.setPrice(resultSet.getDouble("price"));
    product.setImage_url(resultSet.getString("image_url"));
    product.setCategory_id(resultSet.getInt("category_id"));
    product.setCreated_at(resultSet.getTimestamp("created_at"));
    product.setUpdated_at(resultSet.getTimestamp("updated_at"));
    product.setFood_id(resultSet.getInt("food_id"));
    product.setType(resultSet.getString("type"));
    product.setNutri_id(resultSet.getInt("nutri_id"));
    return product;
  }

  @Override
  public FoodDraft findById(Integer id) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  public Integer getBiggestId() {
    String sql = "SELECT MAX(id) AS id"
        + " FROM Food_draft;";
    try {
      statement = connection.prepareStatement(sql);
      resultSet = statement.executeQuery(sql);

      if (resultSet.next()) {
        return resultSet.getInt(1);
      }
    } catch (Exception e) {
      System.out.println("Loi day" + e);
    }
    return -1;
  }

  public static void main(String[] args) {
    System.out.println(new FoodDraftDAO().getBiggestId());
  }

}
