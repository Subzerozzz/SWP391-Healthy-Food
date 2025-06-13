/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Coupon;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Predator
 */
public class CouponDAO extends DBContext implements I_DAO<Coupon> {

    @Override
    public List<Coupon> findAll() {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM coupons";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                coupons.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all coupons: " + e.getMessage());
        } finally {
            closeResources();
        }
        return coupons;
    }

    @Override
    public Map<Integer, Coupon> findAllMap() {
        Map<Integer, Coupon> couponMap = new HashMap<>();
        try {
            String sql = "SELECT * FROM coupons";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Coupon coupon = getFromResultSet(resultSet);
                couponMap.put(coupon.getCouponId(), coupon);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
            return couponMap;
        }
    }

    @Override
    public boolean update(Coupon coupon) {
        String sql = "UPDATE coupons SET code = ?, description = ?, discount_type = ?, discount_value = ?, min_purchase=?, max_discount=?,start_date=?,end_date=?,usage_limit=?,usage_count=?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, coupon.getCode());
            statement.setString(2, coupon.getDescription());
            statement.setString(3, coupon.getDiscountType());
            statement.setBigDecimal(4, coupon.getDiscountValue());
            statement.setBigDecimal(5, coupon.getMinPurchase());
            statement.setBigDecimal(6, coupon.getMaxDiscount());
            statement.setDate(7, new java.sql.Date(coupon.getStartDate().getTime()));
            statement.setDate(8, new java.sql.Date(coupon.getEndDate().getTime()));
            statement.setInt(9, coupon.getUsageLimit());
            statement.setInt(10, coupon.getUsageLimit());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating coupon: " + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public boolean delete(Coupon coupon) {
        String sql = "DELETE from coupons WHERE coupon_id=? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, coupon.getCouponId());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting coupon: " + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public int insert(Coupon coupon) {
        String sql = "INSERT INTO coupons (code,description,discount_type,discount_value,min_purchase,max_discount"
                + "start_date, end_date, usage_limit, is_active)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, coupon.getCode());
            statement.setString(2, coupon.getDescription());
            statement.setString(3, coupon.getDiscountType());
            statement.setBigDecimal(4, coupon.getMinPurchase());
            statement.setBigDecimal(5, coupon.getMaxDiscount());
            statement.setDate(6, new java.sql.Date(coupon.getStartDate().getTime()));
            statement.setDate(7, new java.sql.Date(coupon.getEndDate().getTime()));
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating coupon failed, no rows affected.");
            }
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Creating coupon failed, no ID obtained.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding coupon: " + e.getMessage());
            return -1;
        } finally {
            closeResources();
        }
    }

    @Override
    public Coupon getFromResultSet(ResultSet rs) throws SQLException {
        Coupon coupon = new Coupon();
        coupon.setCouponId(rs.getInt("coupon_id"));
        coupon.setCode(rs.getString("code"));
        coupon.setDescription(rs.getString("description"));
        coupon.setDiscountType(rs.getString("discount_type"));
        coupon.setDiscountValue(rs.getBigDecimal("discount_value"));
        coupon.setMinPurchase(rs.getBigDecimal("min_purchase"));
        coupon.setMaxDiscount(rs.getBigDecimal("max_discount"));
        coupon.setStartDate(rs.getDate("start_date"));
        coupon.setEndDate(rs.getDate("end_date"));
        coupon.setUsageLimit(rs.getInt("usage_limit"));
        coupon.setUsageCount(rs.getInt("usage_count"));
        coupon.setActive(rs.getBoolean("is_active"));
        coupon.setCreatedAt(rs.getDate("created_at"));
        coupon.setUpdatedAt(rs.getDate("updated_at"));
        return coupon;
    }

    @Override
    public Coupon findById(Integer id) {
        try {
            String sql = "SELECT * FROM coupons WHERE coupon_id = ?";
            connection = getConnection();
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

    public int getTotalCoupon() {
        String sql = "SELECT COUNT(*) FROM coupons";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error checking code: " + e.getMessage());
        } finally {
            closeResources();
        }
        return 0;
    }

    public List<Coupon> pagingCoupon(int index) {
        List<Coupon> coupon = new ArrayList<>();
        String sql = "SELECT * FROM coupons\n"
                + "ORDER BY coupon_id\n"
                + "LIMIT 10 OFFSET ?;";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (index - 1) * 10);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                coupon.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace(); // BẮT BUỘC CÓ
        } finally {
            closeResources();
        }
        return coupon;
    }

    public int countCouponsBySearch(String keyword) {
        String sql = "SELECT COUNT(*) FROM blogs WHERE code LIKE ? OR description LIKE ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            String likeKeyword = "%" + keyword + "%";
            statement.setString(1, likeKeyword);
            statement.setString(2, likeKeyword);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error counting coupon by search: " + e.getMessage());
        } finally {
            closeResources();
        }
        return 0;
    }

    public List<Coupon> searchCouponsByCodeorDescription(String keyword, int offset, int pageSize) {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM blogs WHERE code LIKE ? OR description LIKE ? LIMIT ? OFFSET ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            String likeKeyword = "%" + keyword + "%";
            statement.setString(1, likeKeyword);
            statement.setString(2, likeKeyword);
            statement.setInt(3, pageSize);
            statement.setInt(4, offset);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                coupons.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error searching coupon with pagination: " + e.getMessage());
        } finally {
            closeResources();
        }
        return coupons;
    }

    public int getTotalCouponCountWithFilter(String discounttype, String search) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM coupons WHERE 1=1");

        // Thêm điều kiện search nếu có
        if (search != null && !search.trim().isEmpty()) {
            sql.append(" AND (title LIKE ? OR content LIKE ?)");
        }

        // Thêm điều kiện status nếu có
        if (discounttype != null && !discounttype.trim().isEmpty()) {
            sql.append(" AND status = ?");
        }

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
            int paramIndex = 1;

            // Set search parameters
            if (search != null && !search.trim().isEmpty()) {
                statement.setString(paramIndex++, "%" + search + "%");
                statement.setString(paramIndex++, "%" + search + "%");
            }

            // Set status parameter
            if (discounttype != null && !discounttype.trim().isEmpty()) {
                statement.setString(paramIndex++, discounttype);
            }

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error counting filtered coupons: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return 0;
    }

    public List<Coupon> filterCouponWithPagination(String discounttype, String search, int page, int pageSize) {
        List<Coupon> coupons = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM blogs WHERE 1=1");

        // Thêm điều kiện search nếu có
        if (search != null && !search.trim().isEmpty()) {
            sql.append(" AND (title LIKE ? OR content LIKE ?)");
        }

        // Thêm điều kiện status nếu có
        if (discounttype != null && !discounttype.trim().isEmpty()) {
            sql.append(" AND status = ?");
        }

        sql.append(" ORDER BY id DESC LIMIT ?, ?"); // Thêm DESC để hiển thị blog mới nhất trước

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
            int paramIndex = 1;

            // Set search parameters
            if (search != null && !search.trim().isEmpty()) {
                statement.setString(paramIndex++, "%" + search + "%");
                statement.setString(paramIndex++, "%" + search + "%");
            }

            // Set status parameter
            if (discounttype != null && !discounttype.trim().isEmpty()) {
                statement.setString(paramIndex++, discounttype);
            }

            // Set pagination parameters
            statement.setInt(paramIndex++, (page - 1) * pageSize);
            statement.setInt(paramIndex++, pageSize);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                coupons.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error filtering coupons with pagination: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return coupons;
    }
}
