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
    String sql = "INSERT INTO FoodDraft (name, description, price, image_url, category_id, created_at, updated_at,type,nutri_id,food_id)"
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
      // gan giá tri vao
      statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, t.getName());
      statement.setString(2, t.getDescription());
      statement.setDouble(3, t.getPrice());
      statement.setString(4, t.getImage_url());
      statement.setInt(5, t.getCategory_id());
      statement.setTimestamp(6, t.getCreated_at());
      statement.setTimestamp(7, t.getUpdated_at());
      statement.setString(8, t.getType());
      statement.setInt(9, t.getNutri_id());

      if (t.getFood_id() != null) {
        statement.setInt(10, t.getFood_id());
      } else {
        System.out.println("hhhh");
        statement.setNull(10, java.sql.Types.INTEGER);
      }

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
    String sql = "SELECT * FROM FoodDraft WHERE id = ?";
    try {
      statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        FoodDraft foodDraft = getFromResultSet(resultSet);
        return foodDraft;
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  public Integer getBiggestId() {
    String sql = "SELECT * FROM FoodDraft ORDER BY id DESC LIMIT 1";
    try {
      statement = connection.prepareStatement(sql);

      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        return resultSet.getInt("id");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return -1;
  }

  public static void main(String[] args) {
    FoodDraftDAO dao = new FoodDraftDAO();
    FoodDraft foodDraft = new FoodDraft();
    foodDraft.setName("Test món ăn");
    foodDraft.setDescription("Món ăn này dùng để test");
    foodDraft.setPrice(99.99);
    foodDraft.setImage_url("uploads/products/test.jpg"); // Giả sử bạn đã upload ảnh và có đường dẫn
    foodDraft.setCategory_id(1); // Thay bằng ID thực trong DB
    foodDraft.setType("UPDATE"); // Hoặc "Main", "Drink" v.v.
    foodDraft.setNutri_id(1); // ID thực trong bảng Nutrition (nếu có)
    foodDraft.setCreated_at(new Timestamp(System.currentTimeMillis()));
    foodDraft.setUpdated_at(new Timestamp(System.currentTimeMillis()));
    foodDraft.setFood_id(2);

    int result = dao.insert(foodDraft);

    if (result > 0) {
      System.out.println("Thêm FoodDraft thành công với ID = " + result);
    } else {
      System.out.println("Thêm FoodDraft thất bại.");
    }
  }

}
