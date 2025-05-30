/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Food;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class FoodDAO extends DBContext implements I_DAO<Food>{
    
    @Override
    public List<Food> findAll() {
        String sql = "select * from Food ";
        List<Food> list = new ArrayList<>();
        try {
           connection= getConnection();
           statement = connection.prepareStatement(sql);
           resultSet = statement.executeQuery();
           while(resultSet.next()){
               list.add(getFromResultSet(resultSet));
           }
        } catch (Exception e) {
            System.out.println("Error happen in FoodDAO:"+e.getMessage());
        }finally{
            closeResources();
        }
       return list;
        
     }
     public List<Food> findAllStatusPending() {
        String sql = "select * from Food where status ='pending' ";
        List<Food> list = new ArrayList<>();
        try {
           connection= getConnection();
           statement = connection.prepareStatement(sql);
           resultSet = statement.executeQuery();
           while(resultSet.next()){
               list.add(getFromResultSet(resultSet));
           }
        } catch (Exception e) {
            System.out.println("Error happen in FoodDAO:"+e.getMessage());
        }finally{
            closeResources();
        }
       return list;
        
     }

    @Override
    public Map<Integer, Food> findAllMap() {
        String sql = "Select * from Food";
        Map<Integer, Food> map = new HashMap<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Food food = getFromResultSet(resultSet);
                map.put(food.getIdFood(), food);
            }
        } catch (Exception e) {
            e.getMessage();
        }finally{
            closeResources();
        }
        return map;
      }

    @Override
    public boolean update(Food t) {
        String sql = "UPDATE Food SET status = ? Where id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getStatus());
            statement.setInt(2, t.getIdFood());
            statement.executeUpdate();
            return statement.executeUpdate()>0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeResources();
        }
            
        return false;
    }

    @Override
    public boolean delete(Food t) {
       String sql = "DELETE from Food Where id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getIdFood());
            statement.executeUpdate();
            return statement.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public int insert(Food t) {
      String sql = "INSERT into Food(name) values(?) ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, t.getNameFood());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
       return -1;
    }

    @Override
    public Food getFromResultSet(ResultSet resultSet) throws SQLException {
        Food menu = Food
                 .builder()
                .idFood(resultSet.getInt("idFood"))
                .nameFood(resultSet.getString("nameFood"))
                .idNutri(resultSet.getInt("idNutri"))
                .nameNutri(resultSet.getString("nameNutri"))
                .img(resultSet.getString("img"))
                .price(resultSet.getDouble("price"))
                .description(resultSet.getString("desciption"))
                .status(resultSet.getString("status"))
                .build();
          return menu;
   }

    @Override
    public Food findById(Integer id) {
       String sql = "Select * from Food WHERE id = ?";
        try {
            connection= getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return getFromResultSet(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return null;
    }
    
    public static void main(String[] args) {
        FoodDAO dao = new FoodDAO();
        for (Food i : dao.findAll()) {
            System.out.println(i);
        }
    }
     
    public boolean isCategoryNameExist( String name,int id){
        String sql =" Select COUNT(*) from Food WHERE id != ? and name = ?";
        try {
            connection= getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, name);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1)>0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeResources();
        }
            return false;
    }
    
    public List<Food> fillAllParentCategories() {
       return null;
    }
    
}
