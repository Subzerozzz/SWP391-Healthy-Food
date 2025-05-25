/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Menu;
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
public class MenuDAO extends DBContext implements I_DAO<Menu>{

    @Override
    public List<Menu> findAll() {
        String sql = "select * from Menu";
        List<Menu> list = new ArrayList<>();
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
    public Map<Integer, Menu> findAllMap() {
        String sql = "Select * from Menu";
        Map<Integer, Menu> map = new HashMap<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Menu menu = getFromResultSet(resultSet);
                map.put(menu.getIdCategory(), menu);
            }
        } catch (Exception e) {
            e.getMessage();
        }finally{
            closeResources();
        }
        return map;
      }

    @Override
    public boolean update(Menu t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Menu t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Menu t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Menu getFromResultSet(ResultSet resultSet) throws SQLException {
        Menu menu = Menu
                 .builder()
                .idCategory(resultSet.getInt("idC"))
                .nameCategory(resultSet.getString("nameC"))
                .build();
          return menu;
   }

    @Override
    public Menu findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
