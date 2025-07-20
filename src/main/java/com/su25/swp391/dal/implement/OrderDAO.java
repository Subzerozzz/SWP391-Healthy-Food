/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.entity.Order;
import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Coupon;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.OrderItem;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO extends DBContext implements I_DAO<Order> {

    /**
     * Retrieves a paginated list of Order based on optional status and payment
     * method filters.
     *
     * @param status Optional order status filter (e.g., "pending", "completed",
     * etc.)
     * @param paymentMethod Optional payment method filter (e.g., "Cash on
     * Delivery", "Bank Transfer", etc.)
     * @param paymentStatus get paid or unpaid
     * @param page The current page number (starting from 1)
     * @param pageSize The number of results to return per page
     * @param sort Sort desc or asc
     * @return List of filtered and paginated Order objects
     */
    public List<Order> findOrdersWithFilters(String sort, String status, String paymentMethod, int paymentStatus, int page, int pageSize) {
        List<Order> Order = new ArrayList<>();
        // Build the SQL query dynamically with optional filters
        StringBuilder sql = new StringBuilder(
                "SELECT * "
                + "FROM `Order` "
                + "WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        // Add status filter if provided
        if (status != null && !status.isEmpty()) {
            sql.append("AND status = ? ");
            params.add(status);
        }
        // Add payment method filter if provided
        if (paymentMethod != null && !paymentMethod.isEmpty()) {
            sql.append("AND payment_method = ? ");
            params.add(paymentMethod);
        }
        // Add payment status filter if provied
        if (paymentStatus >= 0 && paymentStatus <= 1) {
            sql.append("AND payment_status = ? ");
            params.add(paymentStatus);
        }
        // Append sorting and pagination
        if (sort != null && !sort.isEmpty()) {
            sql.append("ORDER BY id " + sort + " LIMIT ? OFFSET ? ");
        } else {
            sql.append("ORDER BY created_at DESC LIMIT ? OFFSET ?");
        }
        params.add(pageSize); // LIMIT
        params.add((page - 1) * pageSize); // OFFSET

        try {
            // Open connection and prepare statement
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
            // Set parameters in PreparedStatement
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            // Execute query and map results to Order objects
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close ResultSet, Statement, Connection
            closeResources();
        }
        return Order;
    }

    /**
     * Counts the total number of Order that match optional filters for status
     * and payment method. This is typically used to support pagination (by
     * calculating the total number of pages).
     *
     * @param status Optional status filter (e.g., "pending", "completed", etc.)
     * @param paymentMethod Optional payment method filter (e.g., "Cash on
     * Delivery", "Bank Transfer", etc.)
     * @return Total number of matching Order, or 0 if none found or in case of
     * error
     */
    public int getTotalFilteredOrders(String status, String paymentMethod, int paymentStatus) {
        // Build the SQL query with optional WHERE conditions
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) "
                + "FROM `Order`  "
                + "WHERE 1=1 ");
        List<Object> params = new ArrayList<>();
        // Add status filter if provided
        if (status != null && !status.isEmpty()) {
            sql.append("AND status = ? ");
            params.add(status);
        }
        // Add payment method filter if provided
        if (paymentMethod != null && !paymentMethod.isEmpty()) {
            sql.append("AND payment_method = ? ");
            params.add(paymentMethod);
        }
        // Add payment status filter if provied
        if (paymentStatus >= 0 && paymentStatus <= 1) {
            sql.append("AND payment_status = ? ");
            params.add(paymentStatus);
        }
        try {
            // Prepare and execute query
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
            // Set query parameters
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            // Extract result (single integer)
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close ResultSet, Statement, Connection
            closeResources();
        }
        return -1;
    }

    public List<Order> searchOrderListByUserIdAndStatus(int userId, String status) {
        List<Order> orderLists = new ArrayList<>();
        String sql = "SELECT "
                + "o.id, "
                + "o.account_id, "
                + "o.status, "
                + "o.total, "
                + "o.payment_method, "
                + "o.created_at, "
                + "o.updated_at, "
                + "o.coupon_code, "
                + "o.discount_amount, "
                + "o.full_name, "
                + "o.mobile, "
                + "o.address, "
                + "o.email, "
                + "o.shipping_address, "
                + "o.payment_status "
                + "FROM `Order` o "
                + "WHERE o.id = ? AND o.status = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setString(2, status);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getFromResultSet(resultSet); // dùng lại hàm đã viết
                orderLists.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return orderLists;
    }

    public int getTotalOrderCountByUserIdAndStatus(int userId, String status) {
        boolean filterByStatus = status != null && !status.trim().isEmpty() && !status.equalsIgnoreCase("all");
        String sql = "SELECT COUNT(*) FROM `Order` WHERE account_id = ?";
        if (filterByStatus) {
            sql += " AND status = ?";
        }

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, userId);
            if (filterByStatus) {
                statement.setString(2, status);
            }

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return 0;
    }

    /**
     * Updates the status of a specific order and logs the change in the
     * OrderApproval table. This method uses a transaction to ensure consistency
     * between the order update and the approval log.
     *
     * @param orderId ID of the order to update
     * @param newStatus New status to set (e.g., "accepted", "completed", etc.)
     * @param sellerId ID of the seller who approves the status change
     * @param note Optional note explaining the reason for status change
     * @return true if update and logging were successful, false otherwise
     */
    public boolean updateOrderStatus(int orderId, String newStatus) {
        String sql = "UPDATE `Order` SET status = ? , updated_at = NOW() where id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, newStatus);
            statement.setInt(2, orderId);
            int row = statement.executeUpdate();
            return (row > 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    public boolean updateOrderStatusComplete(int orderId, String newStatus) {
        String sql = "UPDATE `Order` SET status = ? , updated_at = NOW(),payment_status = 1 where id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, newStatus);
            statement.setInt(2, orderId);
            int row = statement.executeUpdate();
            return (row > 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    public List<Order> findOrdersByUserIdAndStatusWithPagination(int userId, String status, int page, int pageSize) {
        List<Order> list = new ArrayList<>();
        boolean filterByStatus = status != null && !status.trim().isEmpty() && !status.equalsIgnoreCase("all");

        String sql = "SELECT * FROM `Order` WHERE account_id = ?";
        if (filterByStatus) {
            sql += " AND status = ?";
        }
        sql += " ORDER BY id DESC LIMIT ? OFFSET ?";

        try {
            int offset = (page - 1) * pageSize;
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, userId);
            int paramIndex = 2;

            if (filterByStatus) {
                statement.setString(paramIndex++, status);
            }

            statement.setInt(paramIndex++, pageSize);
            statement.setInt(paramIndex, offset);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    /**
     * Searches for Order based on keyword, status, and payment method. The
     * search keyword is matched against username, email, or order ID (cast to
     * string). Supports pagination through LIMIT and OFFSET.
     *
     * @param search The keyword to search (matches user name, email, or order
     * ID)
     * @param status Optional order status filter (e.g., "pending", "completed")
     * @param paymentMethod Optional payment method filter (e.g., "COD",
     * "PayPal")
     * @param page Page number for pagination (1-based)
     * @param pageSize Number of results per page
     * @param sort Sort id by desc or asc
     * @return A list of matching Order objects
     */
    public List<Order> searchOrders(String sort, String search, String status, String paymentMethod, int page, int pageSize) {

        // Build the base SQL query to search by user name, email, or order ID
        List<Order> orders = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT o.* FROM `Order` o ")
                .append("LEFT JOIN Account a ON o.account_id = a.id ")
                .append("WHERE (") // << mở ngoặc block OR
                .append("COALESCE(a.user_name, '') LIKE ? ")
                .append("OR COALESCE(a.email, '') LIKE ? ")
                .append("OR COALESCE(o.full_name, '') LIKE ? ")
                .append("OR COALESCE(o.email, '') LIKE ? ")
                .append(") "); // << đóng ngoặc trước khi thêm AND

        List<Object> params = new ArrayList<>();
        // Prepare the wildcard search pattern (for LIKE)
        String searchPattern = "%" + search.trim() + "%";
        params.add(searchPattern); // user_name
        params.add(searchPattern); // account email
        params.add(searchPattern); // full_name
        params.add(searchPattern); // order email

        // Add status filter if provided
        if (status != null && !status.isEmpty()) {
            sql.append(" AND o.status = ? ");
            params.add(status);
        }
        // Add payment method filter if provided
        if (paymentMethod != null && !paymentMethod.isEmpty()) {
            sql.append(" AND o.payment_method = ? ");
            params.add(paymentMethod);
        }
        // Add ordering and pagination
        if (sort != null && !sort.isEmpty()) {
            sql.append("ORDER BY id " + sort + " LIMIT ? OFFSET ? ");
        } else {
            sql.append("ORDER BY created_at DESC LIMIT ? OFFSET ?");
        }
        params.add(pageSize); // LIMIT
        params.add((page - 1) * pageSize); // OFFSET (start index)
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
            // Set all parameters into PreparedStatement
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            // Execute query and build list of Order
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("Error searching Order: " + ex.getMessage());
        } finally {
            // Close resultSet, statement, connection
            closeResources();
        }
        return orders;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM `Order`";
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

    /**
     * Returns the total number of Order that match a search keyword and
     * optional filters. This is used for pagination to know how many pages of
     * results there are.
     *
     * @param search The keyword to search (matches user name, email, or order
     * ID)
     * @param status Optional status filter (e.g., "pending", "completed")
     * @param paymentMethod Optional payment method filter (e.g., "COD",
     * "PayPal")
     * @return The total number of matching Order
     */
    public int getTotalSearchResults(String search, String status, String paymentMethod) {
        // Build the SQL to count rows with conditions
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) "
                + "FROM `Order` o "
                + "LEFT JOIN Account a "
                + "ON account_id = a.id "
                + "WHERE (a.user_name LIKE ? OR a.email LIKE ? OR CAST(o.id AS CHAR) = ?"
                + " OR o.full_name LIKE ? OR o.email LIKE ? OR o.id = ?) ");
        List<Object> params = new ArrayList<>();
        // Prepare search pattern with wildcard for LIKE
        String searchPattern = "%" + search.trim() + "%";
        params.add(searchPattern);// For user_name
        params.add(searchPattern);// For email
        params.add(search); // For exact order ID
        params.add(searchPattern);// for full_name
        params.add(searchPattern);// for email
        params.add(search); // for exact order ID (casted to string)
        // Add status filter if provided
        if (status != null && !status.isEmpty()) {
            sql.append("AND o.status = ? ");
            params.add(status);
        }
        // Add payment method filter if provided
        if (paymentMethod != null && !paymentMethod.isEmpty()) {
            sql.append("AND o.payment_method = ? ");
            params.add(paymentMethod);
        }

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
            // Bind parameters to prepared statement
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            // Execute the count query
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);// Return the count
            }
        } catch (SQLException ex) {
            System.out.println("Error counting search results: " + ex.getMessage());
        } finally {
            closeResources();
        }
        // Fallback if error or no result
        return 0;
    }

    @Override
    public Map<Integer, Order> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Order order) {
        String sql = "UPDATE `Order` SET account_id=?, status=?, total=?, shipping_address=?, "
                + "payment_method=?, updated_at=?, coupon_code=?, full_name=?, mobile=?, email=?, "
                + "discount_amount=?, payment_status =? WHERE id=?";
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
            statement.setInt(12, order.getPayment_status());
            statement.setInt(13, order.getId());

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
        String sql = "DELETE FROM `Order` WHERE id = ?";
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
                + ",updated_at, coupon_code, full_name, mobile, email, discount_amount,payment_status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
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
            statement.setInt(13, order.getPayment_status());

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
        order.setPayment_status(rs.getInt("payment_status"));
        order.setDiscount_amount(rs.getDouble("discount_amount"));
        return order;
    }

    @Override
    public Order findById(Integer id) {
        Order order = null;
        String sql = "SELECT * FROM `Order` WHERE id = ?";
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

    public boolean updateOrderStatusCustomer(int orderId, String status) {
        String sql = "UPDATE `Order` SET status = ? WHERE id = ?";
        boolean success = false;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, orderId);

            int rows = statement.executeUpdate();
            success = (rows > 0);
        } catch (Exception e) {
            System.out.println("Error updating order status: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return success;
    }

    public List<Order> findAllByUserId(int userId) {
        String sql = "SELECT * FROM `Order` WHERE account_id = ?";
        List<Order> order = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId); // Gán giá trị userId vào ?
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return order;
    }

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        AccountDAO a = new AccountDAO();
        System.out.println(dao.findById(30));
        Account acc = a.findById(dao.findById(30).getAccount_id());
        System.out.println(acc);
    }

}
