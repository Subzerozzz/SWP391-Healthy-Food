/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Food_draft;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class FoodDraftDAO extends DBContext implements I_DAO<Food_draft> {

  @Override
  public List<Food_draft> findAll() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public Map<Integer, Food_draft> findAllMap() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public boolean update(Food_draft t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public boolean delete(Food_draft t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
    public int insert(Food_draft t) {
        String sql = "INSERT INTO food_draft (name, description, price, image_url, status, category_id, created_at, updated_at)"
                      +"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql, preparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, t.getName());
            preparedStatement.setString(2, t.getDescription());
            preparedStatement.setDouble(3, t.getPrice());
            preparedStatement.setString(4, t.getImage_url());
            preparedStatement.setString(5, t.getStatus());  
        }
    }

  @Override
  public Food_draft getFromResultSet(ResultSet resultSet) throws SQLException {
    Food_draft product = new Food_draft();
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
  public Food_draft findById(Integer id) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

}
