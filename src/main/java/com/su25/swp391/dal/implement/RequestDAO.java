/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Request;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class RequestDAO extends DBContext implements I_DAO<Request> {

  @Override
  public List<Request> findAll() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public Map<Integer, Request> findAllMap() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public boolean update(Request t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public boolean delete(Request t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public int insert(Request t) {
    String sql = "INSERT INTO Request(type, foodDraftId, status)"
        + " VALUES(?, ?, ?);";
    try {
      statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, t.getType());
      statement.setInt(2, t.getFoodDraftId());
      statement.setString(3, t.getStatus());
      statement.executeUpdate();
      ResultSet resultSet = statement.getGeneratedKeys();

      if (resultSet.next()) {
        return resultSet.getInt(1);
      }
    } catch (SQLException ex) {
      Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println(ex);
    }
    return -1;
  }

  @Override
  public Request getFromResultSet(ResultSet resultSet) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public Request findById(Integer id) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
  

}
