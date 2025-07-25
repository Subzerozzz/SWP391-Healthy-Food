/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Coupon;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
            connection = getConnection();
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
        Map<Integer, Coupon> couponMap = new HashMap<>();
        try {
            String sql = "SELECT * FROM Coupon";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Coupon coupon = getFromResultSet(resultSet);
                couponMap.put(coupon.getId(), coupon);
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
        try {
            connection = getConnection();
            String sql = "UPDATE Coupon SET code = ?, description = ?, discount_type = ?, "
                    + "discount_value = ?, min_purchase = ?, max_discount = ?, "
                    + "start_date = ?, end_date = ?, usage_limit = ?,usage_count = ?, is_active = ?, "
                    + "updated_at = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, coupon.getCode());
            statement.setString(2, coupon.getDescription());
            statement.setString(3, coupon.getDiscount_type());
            statement.setDouble(4, coupon.getDiscount_value());
            statement.setDouble(5, coupon.getMin_purchase());
            statement.setDouble(6, coupon.getMax_discount());
            statement.setDate(7, coupon.getStart_date());
            statement.setDate(8, coupon.getEnd_date());
            statement.setInt(9, coupon.getUsage_limit());
            statement.setInt(10, coupon.getUsage_count());
            statement.setInt(11, coupon.getIs_active());
            statement.setDate(12, coupon.getUpdated_at());
            statement.setInt(13, coupon.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CouponDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean delete(Coupon coupon) {
        String sql = "DELETE from Coupon WHERE id=? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, coupon.getId());
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
        String sql = "INSERT INTO Coupon (code,description,discount_type,discount_value,min_purchase,max_discount,"
                + "start_date, end_date,is_active,usage_limit,per_customer_limit)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, coupon.getCode());
            statement.setString(2, coupon.getDescription());
            statement.setString(3, coupon.getDiscount_type());
            statement.setDouble(4, coupon.getDiscount_value());
            statement.setDouble(5, coupon.getMin_purchase());
            statement.setDouble(6, coupon.getMax_discount());
            statement.setDate(7, new java.sql.Date(coupon.getStart_date().getTime()));
            statement.setDate(8, new java.sql.Date(coupon.getEnd_date().getTime()));
            statement.setInt(9, coupon.getIs_active());
            // Xử lý null an toàn
            statement.setObject(10, coupon.getUsage_limit(), java.sql.Types.INTEGER);
            statement.setObject(11, coupon.getPer_customer_limit(), java.sql.Types.INTEGER);

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
            connection = getConnection();
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

    public boolean isCouponCodeExists(String code, Integer id) {
        String sql = "SELECT COUNT(*) FROM Coupon WHERE code = ? AND id != ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, code);
            statement.setInt(2, id);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Trả về true nếu code đã tồn tại ở bản ghi khác
            }
        } catch (Exception e) {
            System.out.println("Error checking coupon code existence by id: " + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    public List<Coupon> getAllActiveCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM Coupon WHERE is_active = 1 ORDER BY start_date DESC";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
//            statement.setTimestamp(1, currentTime); // Check end_date
//            statement.setTimestamp(2, currentTime); // Check start_date

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                coupons.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return coupons;
    }

    public List<Coupon> searchCoupons(String keyword) {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM Coupon WHERE (code LIKE ? OR description LIKE ?) "
                + "AND is_active = 1";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

//            Timestamp currentTime = new Timestamp(new java.util.Date().getTime());
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
//            statement.setTimestamp(3, currentTime); // Check end_date
//            statement.setTimestamp(4, currentTime); // Check start_date
            
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                coupons.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coupons;
    }

    public List<Coupon> searchCouponsByCodeorDescription(String keyword, int offset, int pageSize) {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM Coupon WHERE code LIKE ? OR description LIKE ? LIMIT ? OFFSET ?";
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

    public int countCouponsBySearch(String keyword) {
        String sql = "SELECT COUNT(*) FROM Coupon WHERE code LIKE ? OR description LIKE ?";
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

    public boolean isCouponCodeExists(String code) {
        String sql = "SELECT COUNT(*) FROM Coupon WHERE code = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, code);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (Exception e) {
            System.out.println("Error counting coupon by search: " + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    public List<Coupon> filterCouponWithPagination(String discounttype, int page, int pageSize) {
        List<Coupon> coupons = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Coupon WHERE 1=1");

        if (discounttype != null && !discounttype.trim().isEmpty()) {
            sql.append(" AND LOWER(discount_type) = LOWER(?)");
        }

        // Nối trực tiếp LIMIT và OFFSET vào câu SQL (tránh lỗi không hỗ trợ ? với LIMIT)
        sql.append(" ORDER BY id ASC LIMIT ").append((page - 1) * pageSize).append(", ").append(pageSize);

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            if (discounttype != null && !discounttype.trim().isEmpty()) {
                statement.setString(1, discounttype.toLowerCase());
            }

            System.out.println("SQL Filter Query: " + statement.toString());

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

    public int getTotalCouponCountWithFilter(String discounttype) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Coupon WHERE 1=1");

        if (discounttype != null && !discounttype.trim().isEmpty()) {
            sql.append(" AND LOWER(discount_type) = LOWER(?)");
        }
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            if (discounttype != null && !discounttype.trim().isEmpty()) {
                statement.setString(1, discounttype.toLowerCase());
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

    public int getTotalCoupon() {
        String sql = "SELECT COUNT(*) FROM Coupon";
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
        String sql = "SELECT * FROM Coupon\n"
                + "ORDER BY id\n"
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

    public static void main(String[] args) {
        // Create an instance of the CouponDAO
        CouponDAO couponDAO = new CouponDAO();

        // Call the method to get active coupons
        List<Coupon> activeCoupons = couponDAO.searchCoupons("searchCoupons");

        // Check if any active coupons were returned
        if (activeCoupons.isEmpty()) {
            System.out.println("No active coupons found.");
        } else {
            System.out.println("Active Coupons:");
            for (Coupon coupon : activeCoupons) {
                // Assuming Coupon class has a toString() method that prints the coupon details
                System.out.println(coupon);
            }
        }
    }
}
