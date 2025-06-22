/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Coupon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class CouponDAO extends DBContext implements I_DAO<Coupon>{

    @Override
    public List<Coupon> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<Integer, Coupon> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Coupon t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Coupon t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Coupon t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Coupon getFromResultSet(ResultSet resultSet) throws SQLException {
      return Coupon
             .builder()
             .id(resultSet.getInt("id"))
             .code(resultSet.getString("code"))
             .discount_value(resultSet.getDouble("discount_value"))
             .build();
    }

    @Override
    public Coupon findById(Integer id) {
      String sql = "Select * from Coupon where id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet= statement.executeQuery();
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
