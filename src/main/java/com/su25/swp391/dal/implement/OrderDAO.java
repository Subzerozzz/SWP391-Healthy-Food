/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class OrderDAO extends DBContext implements I_DAO<Order> {

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Order";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return orders;
    }

    @Override
    public Map<Integer, Order> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Order order) {
        String sql = "UPDATE Order SET account_id=?, status=?, total=?, shipping_address=?, "
                + "payment_method=?, updated_at=?, coupon_code=?, full_name=?, mobile=?, email=?, "
                + "discount_amount=? WHERE id=?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getAccount_id());
            statement.setString(2, order.getStatus());
            statement.setDouble(3, order.getTotal());
            statement.setString(4, order.getShipping_address());
            statement.setString(5, order.getPayment_method());
            statement.setTimestamp(6, order.getUpdated_at());
            statement.setString(7, order.getCoupon_code());
            statement.setString(8, order.getFull_name());
            statement.setString(9, order.getMobile());
            statement.setString(10, order.getEmail());
            statement.setDouble(11, order.getDiscount_amount());
            statement.setInt(12, order.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean delete(Order order) {
        String sql = "DELETE FROM Order WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public int insert(Order order) {
        String sql = "INSERT INTO `Order` (account_id, status, total, shipping_address, payment_method, created_at"
                + ",updated_at, coupon_code, full_name, mobile, email, discount_amount) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (order.getAccount_id() != null) {
                statement.setInt(1, order.getAccount_id());
            } else {
                statement.setNull(1, java.sql.Types.INTEGER);
            }
            statement.setString(2, order.getStatus());
            statement.setDouble(3, order.getTotal());
            statement.setString(4, order.getShipping_address());
            statement.setString(5, order.getPayment_method());
            statement.setTimestamp(6, order.getCreated_at());
            statement.setTimestamp(7, order.getUpdated_at());
            statement.setString(8, order.getCoupon_code());
            statement.setString(9, order.getFull_name());
            statement.setString(10, order.getMobile());
            statement.setString(11, order.getEmail());
            statement.setDouble(12, order.getDiscount_amount());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }
        } catch (SQLException ex) {
            System.out.println("Error inserting order: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return -1;
    }

    @Override
    public Order getFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setAccount_id(rs.getInt("account_id"));
        order.setStatus(rs.getString("status"));
        order.setTotal(rs.getDouble("total"));
        order.setShipping_address(rs.getString("shipping_address"));
        order.setPayment_method(rs.getString("payment_method"));
        order.setCreated_at(rs.getTimestamp("created_at"));
        order.setUpdated_at(rs.getTimestamp("updated_at"));
        order.setCoupon_code(rs.getString("coupon_code"));
        order.setFull_name(rs.getString("full_name"));
        order.setMobile(rs.getString("mobile"));
        order.setEmail(rs.getString("email"));
        order.setDiscount_amount(rs.getDouble("discount_amount"));
        return order;
    }

    @Override
    public Order findById(Integer id) {
        Order order = null;
        String sql = "SELECT * FROM Order WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = getFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return order;
    }

    public static void main(String[] args) {
        // Tạo một đối tượng Order mới
        Order order = new Order();
        order.setAccount_id(null); // Giả sử account_id = 1
        order.setStatus("Pending");
        order.setTotal(500000.0);
        order.setShipping_address("123 Lê Lợi, Hà Nội");
        order.setPayment_method("COD");
        order.setCreated_at(new Timestamp(System.currentTimeMillis()));
        order.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        order.setCoupon_code("aaa");
        order.setFull_name("Nguyễn Văn A");
        order.setMobile("0987654321");
        order.setEmail("nguyenvana@example.com");
        order.setDiscount_amount(50000.0);

        // Gọi DAO để insert
        OrderDAO orderDAO = new OrderDAO(); // Giả sử bạn đã có class này
        int orderId = orderDAO.insert(order);
        System.out.println(orderId);

    }

}
