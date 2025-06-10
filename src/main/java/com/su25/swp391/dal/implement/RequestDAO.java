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
import java.util.ArrayList;
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
    String sql = "SELECT * FROM Request";
    List<Request> list = new ArrayList<>();
    try {
      statement = connection.prepareStatement(sql);
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        list.add(getFromResultSet(resultSet));
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return list;
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
    String sql = "INSERT INTO Request(result, foodDraftId, statusRequest)"
        + " VALUES(?, ?, ?);";
    try {
      statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, t.getResult());
      statement.setInt(2, t.getFoodDraftId());
      statement.setString(3, t.getStatusRequest());
      statement.executeUpdate();
      resultSet = statement.getGeneratedKeys();

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
    Request request = new Request();
    request.setId(resultSet.getInt("id"));
    request.setResult(resultSet.getString("result"));
    request.setFoodDraftId(resultSet.getInt("foodDraftId"));
    request.setStatusRequest(resultSet.getString("statusRequest"));
    return request;
  }

  @Override
  public Request findById(Integer id) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
    // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  public List<Request> getRequestByStatus(String status) {
    List<Request> list = new ArrayList<>();
    String sql = "SELECT * FROM Request WHERE statusRequest = ?";
    try {
      statement = connection.prepareStatement(sql);
      statement.setString(1, status);
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Request request = getFromResultSet(resultSet);
        list.add(request);
      }
    } catch (Exception e) {
      System.out.println("Lỗi");
      System.out.println(e);
    }
    return list;
  }

  public List<Request> getRequestByStatusForPage(String status, Integer page) {
    List<Request> list = new ArrayList<>();
    String sql = "SELECT *\n"
        + "FROM Request\n"
        + "ORDER BY id DESC\n"
        + "LIMIT 10 OFFSET ?;";
    // Tính số bản ghi cần bỏ qua
    Integer recordOffset = (page - 1) * 10;
    try {
      statement = connection.prepareStatement(sql);
      statement.setInt(1, recordOffset);

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
    for (Request a : new RequestDAO().getRequestByStatusForPage("Not done", 2)) {
      System.out.println(a);
    }
  }

}
