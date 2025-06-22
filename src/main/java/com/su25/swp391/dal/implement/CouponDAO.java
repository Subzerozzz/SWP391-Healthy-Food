/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Coupon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class CouponDAO extends DBContext implements I_DAO<Coupon> {

    @Override
    public List<Coupon> findAll() {
        List<Coupon> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Coupon";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CouponDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return list;
    }

    @Override
    public Map<Integer, Coupon> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public Coupon getFromResultSet(ResultSet rs) throws SQLException {
        return Coupon.builder()
                .id(rs.getInt("id"))
                .code(rs.getString("code"))
                .description(rs.getString("description"))
                .discount_type(rs.getString("discount_type"))
                .discount_value(rs.getDouble("discount_value"))
                .min_purchase(rs.getDouble("min_purchase"))
                .max_discount(rs.getDouble("max_discount"))
                .start_date(rs.getDate("start_date"))
                .end_date(rs.getDate("end_date"))
                .usage_limit(rs.getInt("usage_limit"))
                .usage_count(rs.getInt("usage_count"))
                .is_active(rs.getInt("is_active"))
                .created_at(rs.getDate("created_at"))
                .updated_at(rs.getDate("updated_at"))
                .per_customer_limit(rs.getInt("per_customer_limit"))
                .build();

    }

    @Override
    public Coupon findById(Integer id) {
        try {
            String sql = "SELECT * FROM Coupon WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CouponDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return null;
    }

    public Coupon findCouponByCouponCode(String couponCode) {
        String sql = "Select * from Coupon Where code = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, couponCode);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new CouponDAO().findCouponByCouponCode("COUPON1001"));
    }

}
