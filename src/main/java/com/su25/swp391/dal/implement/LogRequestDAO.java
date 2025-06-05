/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.mysql.cj.xdevapi.Result;
import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Food_Draft;
import com.su25.swp391.entity.LogRequest;
import com.su25.swp391.entity.Request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class LogRequestDAO extends DBContext implements I_DAO<LogRequest>{

    @Override
    public List<LogRequest> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<Integer, LogRequest> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(LogRequest t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   

    @Override
    public boolean delete(LogRequest t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(LogRequest t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public int insertToLogRequest(Request t, Food_Draft f) {
      String sql = "Insert into swp391_healthy_food.LogRequest(result,foodDraftId,statusRequest,nutri_id) values (?,?,?,?)";
        try {
            connection = getConnection();
            statement=connection.prepareStatement(sql);
            statement.setString(1, t.getResult());
            statement.setInt(2, t.getFoodDraftId());
            statement.setString(3, t.getStatusRequest());
            statement.setInt(4, f.getNutri_id());
            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return -1;
    }

    @Override
    public LogRequest getFromResultSet(ResultSet resultSet) throws SQLException {
          return LogRequest
                  .builder()
                  .result(resultSet.getString("result"))
                  .foodDraftId(resultSet.getInt("foodDraftId"))
                  .statusRequest(resultSet.getString("statusRequest"))
                  .nutri_id(resultSet.getInt("nutri_id"))
                  .build();
    }

    @Override
    public LogRequest findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
