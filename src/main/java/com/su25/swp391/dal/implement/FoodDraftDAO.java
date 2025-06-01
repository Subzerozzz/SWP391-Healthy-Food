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
    String sql = "INSERT INTO Food_draft (name, description, price, image_url, status, category_id, created_at, updated_at)"
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try {
      // gan giá tri vao
      statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, t.getName());
      statement.setString(2, t.getDescription());
      statement.setDouble(3, t.getPrice());
      statement.setString(4, t.getImage_url());
      statement.setString(5, t.getStatus());
      statement.setInt(6, t.getCategory_id());
      statement.setTimestamp(7, t.getCreated_at());
      statement.setTimestamp(8, t.getUpdated_at());

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


  public Integer getBiggestId() {
      String sql = "SELECT MAX(id) AS id"
                    +" FROM Food_draft;";
      try {
        statement = connection.prepareStatement(sql);
        resultSet = statement.executeQuery(sql);

        if(resultSet.next()){
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
