/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Category;
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
public class CategoryDAO extends DBContext implements I_DAO<Category>{

    @Override
    public List<Category> findAll() {
        String sql = "select * from Menu";
        List<Category> list = new ArrayList<>();
        try {
           connection= getConnection();
           statement = connection.prepareStatement(sql);
           resultSet = statement.executeQuery();
           while(resultSet.next()){
               list.add(getFromResultSet(resultSet));
           }
        } catch (Exception e) {
            System.out.println("Error happen in MenuDAO:"+e.getMessage());
        }finally{
            closeResources();
        }
       return list;
        
     }

    @Override
    public Map<Integer, Category> findAllMap() {
        String sql = "Select * from Menu";
        Map<Integer, Category> map = new HashMap<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Category menu = getFromResultSet(resultSet);
                map.put(menu.getId(), menu);
            }
        } catch (Exception e) {
            e.getMessage();
        }finally{
            closeResources();
        }
        return map;
      }

    @Override
    public boolean update(Category t) {
        String sql = "UPDATE Category SET name = ? Where id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getId());
            statement.setString(2, t.getName());
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
    public boolean delete(Category t) {
       String sql = "DELETE from Category Where id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getId());
            statement.executeUpdate();
            return statement.executeUpdate()>0;
        } catch (Exception e) {
        } finally {
        }
        return false;
    }

    @Override
    public int insert(Category t) {
      String sql = "INSERT into Category(name) values(?) ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, t.getName());
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
    public Category getFromResultSet(ResultSet resultSet) throws SQLException {
        Category menu = Category
                 .builder()
                .id(resultSet.getInt("idC"))
                .name(resultSet.getString("nameC"))
                .build();
          return menu;
   }

    @Override
    public Category findById(Integer id) {
       String sql = "Select * from Category WHERE id = ?";
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
    
}
