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

/**
 *
 * @author Dell
 */
public class FoodDAO extends DBContext implements I_DAO<Food> {

  @Override
  public List<Food> findAll() {
    // Chuẩn bị 1 mảng để chứa dữ liệu
    List<Food> list = new ArrayList<>();
    // Chuẩn bị câu lệnh SQL
    String sql = "SELECT * FROM Food";
    try {
      // Chuẩn bị đối tượng statement
      PreparedStatement statement = connection.prepareStatement(sql);
      // Thực thi câu lệnh SQL trả về đối tượng resultSet
      ResultSet resultSet = statement.executeQuery();
      // Duyệt qua từng bản ghi trong resultSet
      while (resultSet.next()) {
        // Lấy dữ liệu từ resultSet gán vào đối tượng produc
        list.add(getFromResultSet(resultSet));
      }
    } catch (SQLException e) {
      System.out.println("Lỗi truy vấn");
    }
    return list;
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
    Food product = new Food();
    product.setId(resultSet.getInt("id"));
    product.setName(resultSet.getString("name"));
    product.setDescription(resultSet.getString("description"));
    product.setPrice(resultSet.getDouble("price"));
    product.setImage_url(resultSet.getString("image_url"));
    product.setStatus(resultSet.getString("status"));
    product.setCreated_at(resultSet.getTimestamp("created_at"));
    product.setUpdated_at(resultSet.getTimestamp("updated_at"));
    product.setCategory_id(resultSet.getInt("category_id"));
    return product;
  }

  @Override
  public Food findById(Integer id) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
    // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
  
    public static void main(String[] args) {
        for(Food a : new FoodDAO().findAll()){
            System.out.println(a.toString());
        }
    }

}
