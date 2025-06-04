/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.FoodCategory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class FoodCategoryDAO extends DBContext implements I_DAO<FoodCategory> {

  @Override
  public List<FoodCategory> findAll() {
    // Chuẩn bị 1 mảng để chứa dữ liệu
    List<FoodCategory> list = new ArrayList<>();
    // Chuẩn bị câu lệnh SQL
    String sql = "SELECT * FROM FoodCategory";
    try {
      // Chuẩn bị đối tượng statement
      statement = connection.prepareStatement(sql);
      // Thực thi câu lệnh SQL trả về đối tượng resultSet
      resultSet = statement.executeQuery();
      // Duyệt qua từng bản ghi trong resultSet
      while (resultSet.next()) {
        // Lấy dữ liệu từ resultSet gán vào đối tượng produc
        list.add(getFromResultSet(resultSet));
      }
    } catch (SQLException e) {
      System.out.println("Lỗi truy vấn");
      System.out.println(e);
    }
    return list;
  }

  @Override
  public Map<Integer, FoodCategory> findAllMap() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public boolean update(FoodCategory t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public boolean delete(FoodCategory t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public int insert(FoodCategory t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public FoodCategory getFromResultSet(ResultSet resultSet) throws SQLException {
    FoodCategory product = new FoodCategory();
    product.setId(resultSet.getInt("id"));
    product.setName(resultSet.getString("name"));
    product.setDescription(resultSet.getString("description"));
    product.setMinBMI(resultSet.getDouble("minBMI"));
    product.setMaxBMI(resultSet.getDouble("maxBMI"));
    return product;
  }

  @Override
  public FoodCategory findById(Integer id) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
  
    public static void main(String[] args) {
        for(FoodCategory a : new FoodCategoryDAO().findAll()){
            System.out.println(a.toString());
        }
    }

}
