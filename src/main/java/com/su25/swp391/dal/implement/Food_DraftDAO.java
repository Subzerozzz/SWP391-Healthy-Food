/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Food_Draft;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Food_DraftDAO extends DBContext implements I_DAO< Food_Draft> {

    @Override
    public List< Food_Draft> findAll() {
        String sql = "select * from  Food_Draft ";
        List< Food_Draft> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error happen in FoodDAO:" + e.getMessage());
        } finally {
            closeResources();
        }
        return list;
    }
    public List<String> findAllType() {
        String sql = "select type from  Food_Draft Group by type";
        List<String> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("type"));
            }
        } catch (Exception e) {
            System.out.println("Error happen in FoodDAO:" + e.getMessage());
        } finally {
            closeResources();
        }
        return list;
    }
    public List<Food_Draft> findAllByType(String type) {
          String sql = "select * from  Food_Draft where type = ? ";
        List< Food_Draft> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, type);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error happen in FoodDAO:" + e.getMessage());
        } finally {
            closeResources();
        }
        return list;
    }

    public List<Food_Draft> findAllFoodDrartByRequest() {
        String sql = "select f.* from swp391_healthy_food.Request as r inner join swp391_healthy_food.Food_Draft as f \n"
                + "on r.foodDraftId = f.id Where f.status = 'pending'";
        List<Food_Draft> list = new ArrayList<>();
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
    // Find Type of Request is CREATE FOOD
     public List<Food_Draft> findAllFoodByCreateRequest() {
        String sql = "select f.* from swp391_healthy_food.Request as r inner join swp391_healthy_food.Food_Draft as f \n"
                + "on r.foodDraftId = f.id Where f.status = 'pending' and r.typeOfRequest = 'CREATE'";
        List<Food_Draft> list = new ArrayList<>();
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
    public Map<Integer, Food_Draft> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Food_Draft t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     // UPDATE STATUS APPROVED WHEN ACCEPT A FOOD_DRAFT
    public boolean updateStatusAccept(int id){
        String sql = " UPDATE Food_Draft SET status = 'Approved' where id = ? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            return statement.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }
   
      public boolean updateStatusReject(int id){
        String sql = " UPDATE Food_Draft SET status = 'Reject' where id = ? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            return statement.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean delete(Food_Draft t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Food_Draft t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    @Override
    public Food_Draft getFromResultSet(ResultSet resultSet) throws SQLException {
        Food_Draft food = Food_Draft
                .builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .price(resultSet.getDouble("price"))
                .image_url(resultSet.getString("image_url"))
                .category_id(resultSet.getInt("category_id"))
                .created_at(resultSet.getTimestamp("created_at"))
                .updated_at(resultSet.getTimestamp("updated_at"))
                .food_id(resultSet.getInt("food_id"))
                .type(resultSet.getString("type"))
                .nutri_id(resultSet.getInt("nutri_id"))
                .build();

        return food;
    }

    @Override
    public Food_Draft findById(Integer id) {
         String sql = "select * from swp391_healthy_food.Food_Draft where id = ? ";
          try {
      connection =getConnection();
      statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return getFromResultSet(resultSet);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      closeResources();
    }
    return null;
    }
    // Find Food_Draft By ID in CREATE FOOD
    public Food_Draft findByIdCreateFood(Integer id) {
         String sql = "select f.* from swp391_healthy_food.Request as r"
                 + " inner join swp391_healthy_food.Food_Draft as f " 
                       +"on r.foodDraftId = f.id  WHERE f.id = ? and  r.typeOfRequest = 'CREATE' ";
           try {
     
      connection =getConnection();
      statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return getFromResultSet(resultSet);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      closeResources();
    }
    return null;
    }

    public static void main(String[] args) {
        Food_DraftDAO dao = new Food_DraftDAO();
      Food_Draft f = dao.findById(4);
//        dao.updateStatus(1);
        // System.out.println(f);
        for (Food_Draft f2 : dao.findAllByType("Create")) {
            System.out.println(f2);
        }
        System.out.println("--");
        for (String string : dao.findAllType()) {
            System.out.println(string);
        }
        
    
   }
}
