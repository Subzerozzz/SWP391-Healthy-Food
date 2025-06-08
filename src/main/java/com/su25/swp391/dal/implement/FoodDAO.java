/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.Food_Draft;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
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
                map.put(food.getId(), food);
            }
        } catch (Exception e) {
            e.getMessage();
        }finally{
            closeResources();
        }
        return map;
      }

   
    public boolean updateFoodbyFoodDraft(Food_Draft t) {
        String sql = "UPDATE Food SET name = ? , description = ? , price = ? , image_url = ? ,"
                + " status = ? ,category_id = ? ,"
                + "created_at = ? , updated_at = ? , nutri_id = ? ,calo = ? Where id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getName());
            statement.setString(2, t.getDescription());
            statement.setDouble(3, t.getPrice());
            statement.setString(4, t.getImage_url());
            statement.setString(5, "active");
            statement.setInt(6, t.getCategory_id());
            statement.setTimestamp(7, t.getCreated_at());
            statement.setTimestamp(8, t.getUpdated_at());
            statement.setInt(9, t.getNutri_id());
            statement.setInt(10, t.getFood_id());
            statement.setDouble(11, t.getCalo());
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
            statement.setInt(1, t.getId());
            statement.executeUpdate();
            return statement.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }
    
     public boolean deleteById(Food_Draft t) {
       String sql = "DELETE from Food Where id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getFood_id());
            return statement.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }
     
    // INSERT FOOD_DRAFT INTO FOOD TABLE WHEN ACCEPT CREATE
    public int insertFoodfromFoodDraft(Food_Draft t) {
      String sql = "INSERT into Food(name,description,price,image_url,status,category_id,created_at,updated_at,nutri_id,calo)"
              + " values(?,?,?,?,?,?,?,?,?,?) ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, t.getName());
            statement.setString(2, t.getDescription());
            statement.setDouble(3,t.getPrice());
            statement.setString(4, t.getImage_url());
            statement.setString(5, "active");
            statement.setInt(6, t.getCategory_id());
            statement.setTimestamp(7,t.getCreated_at());
            statement.setTimestamp(8,t.getUpdated_at());
            statement.setInt(9,t.getNutri_id());
            statement.setDouble(10, t.getCalo());
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
        Food food = Food
                .builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .price(resultSet.getDouble("price"))
                .image_url(resultSet.getString("image_url"))
                .status(resultSet.getString("status"))
                .category_id(resultSet.getInt("category_id"))
                .created_at(resultSet.getTimestamp("created_at"))
                .updated_at(resultSet.getTimestamp("updated_at"))
                .nutri_id(resultSet.getInt("nutri_id"))
                .calo(resultSet.getDouble("calo"))
                .build();
                
          return food;
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

    @Override
    public int insert(Food t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public static void main(String[] args) {
        FoodDAO dao = new FoodDAO();
     //  Timestamp a = new Timestamp(02,02,2004,00,00,00);
      Timestamp timestampNow = new Timestamp(System.currentTimeMillis());

        Food_Draft food = Food_Draft
                         .builder()
                         .name("ĐÂY")
                         .description("Oke la")
                         .price(20.0)
                         .image_url("")
                         .category_id(1)
                         .created_at(timestampNow)
                         .updated_at(timestampNow)
                         .nutri_id(1)
                         .build();
           dao.insertFoodfromFoodDraft(food);
    }

    @Override
    public boolean update(Food t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     
}
