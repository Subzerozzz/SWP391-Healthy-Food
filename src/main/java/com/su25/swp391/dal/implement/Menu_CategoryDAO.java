/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Menu_Category;
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
public class Menu_CategoryDAO extends DBContext implements I_DAO<Menu_Category>{
    
    @Override
    public List<Menu_Category> findAll() {
        String sql = "select * from Menu_Category";
        List<Menu_Category> list = new ArrayList<>();
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
    public Map<Integer, Menu_Category> findAllMap() {
        String sql = "Select * from Menu";
        Map<Integer, Menu_Category> map = new HashMap<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Menu_Category menu = getFromResultSet(resultSet);
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
    public boolean update(Menu_Category t) {
        String sql = "UPDATE Menu_Category SET name = ? Where id = ?";
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
    public boolean delete(Menu_Category t) {
       String sql = "DELETE from Menu_Category Where id = ?";
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
    public int insert(Menu_Category t) {
      String sql = "INSERT into Menu_Category(name) values(?) ";
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
    public Menu_Category getFromResultSet(ResultSet resultSet) throws SQLException {
        Menu_Category menu = Menu_Category
                 .builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
          return menu;
   }

    @Override
    public Menu_Category findById(Integer id) {
       String sql = "Select * from Menu_Category WHERE id = ?";
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
        Menu_CategoryDAO dao = new Menu_CategoryDAO();
        for (Menu_Category i : dao.findAll()) {
            System.out.println(i);
        }
    }
     
    public boolean isCategoryNameExist( String name,int id){
        String sql =" Select COUNT(*) from Category WHERE id != ? and name = ?";
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
    
    public List<Menu_Category> fillAllParentCategories() {
       return null;
    }
    
}
