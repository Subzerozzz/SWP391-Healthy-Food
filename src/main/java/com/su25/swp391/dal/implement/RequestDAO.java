/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Request;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class RequestDAO extends DBContext implements I_DAO<Request> {

    @Override
    public List<Request> findAll() {
        String sql = "Select * from Request ";
          List<Request> list = new ArrayList<>();
        try {
          
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }
       public List<Request> findAllRequest() {
        String sql = "select r.* from swp391_healthy_food.Request as r inner join swp391_healthy_food.Food_Draft as f \n"
                + "on r.foodDraftId = f.id ";
        List<Request> list = new ArrayList<>();
        try {

            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }
    

    @Override
    public Map<Integer, Request> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Request t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Request t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Request t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Request getFromResultSet(ResultSet resultSet) throws SQLException {
              Request request = Request
                      .builder()
                      .id(resultSet.getInt("id"))
                      
                      .foodDraftId(resultSet.getInt("foodDraftId"))
                      
                      .build();
       return request;
    }
     // Update status DONE when Accept or Reject a Food_Draft
    public boolean updateRequestFoodDraftById(int id) {
      String sql = "UPDATE Request SET status = 'DONE' where foodDraftId = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }
   
    
     
    @Override
    public Request findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
       public static void main(String[] args) {
        RequestDAO dao = new RequestDAO();
        dao.updateRequestFoodDraftById(3);
        List<Request> lits = dao.findAll();
           for (Request lit : lits) {
               System.out.println(lit);
           }
        
    }
       
}
