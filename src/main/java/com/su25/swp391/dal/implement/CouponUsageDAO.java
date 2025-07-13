/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.CouponUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class CouponUsageDAO extends DBContext implements I_DAO<CouponUsage> {

    @Override
    public List<CouponUsage> findAll() {
        List<CouponUsage> list = new ArrayList<>();
        String sql = "SELECT * FROM CouponUsage";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources();
        }
        return list;
    }

    @Override
    public Map<Integer, CouponUsage> findAllMap() {
        Map<Integer, CouponUsage> map = new HashMap<>();
        String sql = "SELECT * FROM CouponUsage";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CouponUsage couponUsage = getFromResultSet(resultSet);
                map.put(couponUsage.getId(), couponUsage);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources();
        }
        return map;
    }

    @Override
    public boolean update(CouponUsage t) {
        String sql = "UPDATE CouponUsage SET coupon_id = ?, account_id = ?, order_id = ?, used_at = ?, discount_amount = ? WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getCoupon_id());
            statement.setInt(2, t.getAccount_id());
            statement.setInt(3, t.getOrder_id());
            statement.setTimestamp(4, t.getUsed_at());
            statement.setDouble(5, t.getDiscount_amount());
            statement.setInt(6, t.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean delete(CouponUsage t) {
        String sql = "DELETE FROM CouponUsage WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public int insert(CouponUsage t) {
        String sql = "Insert into CouponUsage(coupon_id, account_id, order_id, used_at, discount_amount)"
                + "values(?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getCoupon_id());
            statement.setInt(2, t.getAccount_id());
            statement.setInt(3, t.getOrder_id());
            statement.setTimestamp(4, t.getUsed_at());
            statement.setDouble(5, t.getDiscount_amount());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources();
        }
        return -1;
    }

    @Override
    public CouponUsage getFromResultSet(ResultSet resultSet) throws SQLException {
        CouponUsage couponUsage = new CouponUsage();
        couponUsage.setId(resultSet.getInt("id"));
        couponUsage.setCoupon_id(resultSet.getInt("coupon_id"));
        couponUsage.setAccount_id(resultSet.getInt("account_id"));
        couponUsage.setOrder_id(resultSet.getInt("order_id"));
        couponUsage.setUsed_at(resultSet.getTimestamp("used_at"));
        couponUsage.setDiscount_amount(resultSet.getDouble("discount_amount"));
        return couponUsage;
    }

    @Override
    public CouponUsage findById(Integer id) {
        String sql = "SELECT * FROM CouponUsage WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }

    // Tim so luong couponId da duoc su dung boi accountId
    public Integer findByCouponIdAndAccountId(Integer account_id, Integer coupon_id) {
        String sql = "SELECT COUNT(*) FROM CouponUsage WHERE account_id = ? AND coupon_id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, account_id);
            statement.setInt(2, coupon_id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources();
        }
        return 0;
    }

}
