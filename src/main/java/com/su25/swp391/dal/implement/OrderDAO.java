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
import com.su25.swp391.entity.OrderItem;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class OrderDAO extends DBContext implements I_DAO<Order> {

    /*
     * Convert a ResultSet row into an Order object.
     * @param rs the ResultSet from a SQL query (likely includes JOIN with user table)
     * @return Order object populated with data from the current ResultSet row
     * @throws SQLException if there is an error reading from the ResultSet
     */
    @Override
    public Order getFromResultSet(ResultSet rs) throws SQLException {
       return Order
               .builder()
               .id(resultSet.getInt("id"))
               .user_id(resultSet.getInt("user_id"))
               .status(resultSet.getString("status"))
               .total(resultSet.getDouble("total"))
               .shipping_address(resultSet.getString("shipping_address"))
               .payment_method(resultSet.getString("payment_method"))
               .created_at(resultSet.getTimestamp("created_at"))
               .updated_at(resultSet.getTimestamp("updated_at"))
               .coupon_id(resultSet.getInt("coupon_id"))
               .acc(null)
               .coupon(null)
               .orderItems(null)
               .build();
         }
    /**
 * Retrieves a paginated list of orders based on optional status and payment method filters.
 *
 * @param status         Optional order status filter (e.g., "pending", "completed", etc.)
 * @param paymentMethod  Optional payment method filter (e.g., "Cash on Delivery", "Bank Transfer", etc.)
 * @param page           The current page number (starting from 1)
 * @param pageSize       The number of results to return per page
 * @return List of filtered and paginated Order objects
 */
    public List<Order> findOrdersWithFilters(String status, String paymentMethod, int page, int pageSize) {
        List<Order> orders = new ArrayList<>();
        // Build the SQL query dynamically with optional filters
        StringBuilder sql = new StringBuilder(
                "SELECT o.* "
                + "FROM orders o "
                + "JOIN Account a ON o.user_id = a.id "
                + "WHERE 1=1 ");
        List<Object> params = new ArrayList<>();
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
        // Append sorting and pagination
        sql.append("ORDER BY o.created_at DESC LIMIT ? OFFSET ?");
        params.add(pageSize);                // LIMIT
        params.add((page - 1) * pageSize);     // OFFSET

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
                orders.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("Error finding filtered orders: " + ex.getMessage());
        } finally {
            // Close ResultSet, Statement, Connection
            closeResources();
        }
        return orders;
    }
    /**
 * Counts the total number of orders that match optional filters for status and payment method.
 * This is typically used to support pagination (by calculating the total number of pages).
 *
 * @param status         Optional status filter (e.g., "pending", "completed", etc.)
 * @param paymentMethod  Optional payment method filter (e.g., "Cash on Delivery", "Bank Transfer", etc.)
 * @return Total number of matching orders, or 0 if none found or in case of error
 */
    public int getTotalFilteredOrders(String status, String paymentMethod) {
        // Build the SQL query with optional WHERE conditions
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) "
                + "FROM orders o "
                + "JOIN Account a "
                + "ON o.user_id = a.id "
                + "WHERE 1=1 ");
        List<Object> params = new ArrayList<>();
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
            System.out.println("Error counting filtered orders: " + ex.getMessage());
        } finally {
            // Close ResultSet, Statement, Connection
            closeResources();
        }
        return 0;
    }

    public Order findById(int orderId) {
        String sql = "SELECT o.*, a.user_name, a.email, a.mobie "
                + "FROM orders o "
                + "JOIN account a "
                + "ON o.user_id = a.id "
                + "WHERE o.order_id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            System.out.println("Error finding order by ID: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }
    /**
 * Updates the status of a specific order and logs the change in the order_approvals table.
 * This method uses a transaction to ensure consistency between the order update and the approval log.
 *
 * @param orderId    ID of the order to update
 * @param newStatus  New status to set (e.g., "accepted", "completed", etc.)
 * @param sellerId   ID of the seller who approves the status change
 * @param note       Optional note explaining the reason for status change
 * @return true if update and logging were successful, false otherwise
 */
    public boolean updateOrderStatus(int orderId, String newStatus, int sellerId, String note) {
        // Get current status
        String currentStatus = null;
        try {
            // Begin transaction
            connection = getConnection();
            connection.setAutoCommit(false);// Disable auto-commit for transaction control
            // Step 1: Fetch current status of the order
            String getStatusSql = "SELECT status FROM orders WHERE order_id = ?";
            statement = connection.prepareStatement(getStatusSql);
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                currentStatus = resultSet.getString("status");
                System.out.println("Current status: " + currentStatus);
            } else {
                // Order not found
                System.out.println("Order not found with ID: " + orderId);
                return false;
            }

            // Step 2: Update order status and timestamp
            String updateSql = "UPDATE orders SET status = ?, updated_at = NOW() WHERE order_id = ?";
            statement = connection.prepareStatement(updateSql);
            statement.setString(1, newStatus);
            statement.setInt(2, orderId);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Update order status rows affected: " + rowsAffected);

            if (rowsAffected > 0) {
                // Step 3: Log status change into order_approvals table
                String approvalSql = "INSERT INTO order_approvals (order_id, approved_by, status_before, status_after, note) "
                        + "VALUES (?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(approvalSql);
                statement.setInt(1, orderId);
                statement.setInt(2, sellerId);
                statement.setString(3, currentStatus);
                statement.setString(4, newStatus);
                statement.setString(5, note);

                int approvalRowsAffected = statement.executeUpdate();
                System.out.println("Insert approval rows affected: " + approvalRowsAffected);

                if (approvalRowsAffected > 0) {
                    // Commit transaction if all operations succeeded
                    connection.commit();
                    System.out.println("Transaction committed successfully");
                    return true;
                }
            }
            // Rollback if anything failed
            System.out.println("Rolling back transaction");
            connection.rollback();
            return false;

        } catch (SQLException ex) {
            // Rollback on exception
            try {
                if (connection != null) {
                    System.out.println("Exception occurred, rolling back: " + ex.getMessage());
                    connection.rollback();
                }
            } catch (SQLException e) {
                System.out.println("Error rolling back transaction: " + e.getMessage());
            }
            System.out.println("Error updating order status: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            // Ensure auto-commit is reset
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
            // Close connection, statement, and result set
            closeResources();
        }
    }
    /**
 * Searches for orders based on keyword, status, and payment method.
 * The search keyword is matched against username, email, or order ID (cast to string).
 * Supports pagination through LIMIT and OFFSET.
 *
 * @param search         The keyword to search (matches user name, email, or order ID)
 * @param status         Optional order status filter (e.g., "pending", "completed")
 * @param paymentMethod  Optional payment method filter (e.g., "COD", "PayPal")
 * @param page           Page number for pagination (1-based)
 * @param pageSize       Number of results per page
 * @return               A list of matching Order objects
 */
    public List<Order> searchOrders(String search, String status, String paymentMethod, int page, int pageSize) {
        List<Order> orders = new ArrayList<>();
        // Build the base SQL query to search by user name, email, or order ID
        StringBuilder sql = new StringBuilder("SELECT o.*"
                + "FROM orders o "
                + "JOIN Account a "
                + "ON o.user_id = a.id "
                + "WHERE (a.user_name LIKE ? OR a.email LIKE ? OR CAST(o.id AS CHAR) = ?) ");
        List<Object> params = new ArrayList<>();
        // Prepare the wildcard search pattern (for LIKE)
        String searchPattern = "%" + search.trim() + "%";
        params.add(searchPattern);// for user_name
        params.add(searchPattern);// for email
        params.add(search);       // for exact order ID (casted to string)
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
        // Add ordering and pagination
        sql.append("ORDER BY o.created_at DESC LIMIT ? OFFSET ?");
        params.add(pageSize);            // LIMIT
        params.add((page - 1) * pageSize); // OFFSET (start index)

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
            // Set all parameters into PreparedStatement
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            // Execute query and build list of orders
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("Error searching orders: " + ex.getMessage());
        } finally {
            // Close resultSet, statement, connection
            closeResources();
        }
        return orders;
    }
    /**
 * Returns the total number of orders that match a search keyword and optional filters.
 * This is used for pagination to know how many pages of results there are.
 *
 * @param search         The keyword to search (matches user name, email, or order ID)
 * @param status         Optional status filter (e.g., "pending", "completed")
 * @param paymentMethod  Optional payment method filter (e.g., "COD", "PayPal")
 * @return               The total number of matching orders
 */
    public int getTotalSearchResults(String search, String status, String paymentMethod) {
        // Build the SQL to count rows with conditions
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) "
                + "FROM orders o "
                + "JOIN Account a "
                + "ON o.user_id = a.id "
                + "WHERE (a.user_name LIKE ? OR a.email LIKE ? OR CAST(o.order_id AS CHAR) = ?) ");
        List<Object> params = new ArrayList<>();
        // Prepare search pattern with wildcard for LIKE
        String searchPattern = "%" + search.trim() + "%";
        params.add(searchPattern);// For user_name
        params.add(searchPattern);// For email
        params.add(search);        // For exact order ID
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
    public int insert(Order order) {
        String sql = "INSERT INTO orders (user_id, status, total, shipping_address, payment_method, coupon_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getUser_id());
            statement.setString(2, order.getStatus());
            statement.setDouble(3, order.getTotal());
            statement.setString(4, order.getShipping_address());
            statement.setString(5, order.getPayment_method());
            statement.setInt(6, order.getCoupon_id());
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
            return -1;
        } finally {
            closeResources();
        }
    }

    @Override
    public Order findById(Integer id) {
        String sql = "SELECT o.*, a.username, a.email, a.phone "
                + "FROM orders o "
                + "JOIN account a ON o.user_id = a.user_id "
                + "WHERE o.order_id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            System.out.println("Error finding order by ID: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }

    @Override
    public Map<Integer, Order> findAllMap() {
        return null;
    }

    @Override
    public boolean delete(Order order) {
        throw new UnsupportedOperationException("Delete operation is not supported for orders");
    }
    @Override
    public List<Order> findAll() {
     return null;
    }

    @Override
    public boolean update(Order order) {
        return true;
    }

    public static void main(String[] args) {
        AccountDAO aD = new AccountDAO();
        CouponDAO cD = new CouponDAO();
        OrderItemDAO otD = new OrderItemDAO();
        OrderDAO o = new OrderDAO();
//        List<Order> or = o.findOrdersWithFilters("", "", 1, 10);
        List<Order> or = o.searchOrders("phong", "", "", 1, 10);
        for (Order order : or) {
            Account acc = aD.findById(order.getUser_id());
            order.setAcc(acc);
            Coupon cp = cD.findById(order.getCoupon_id());
            order.setCoupon(cp);
            List<OrderItem> oT = otD.getOrderItemsByOrderId(order.getId());
            order.setOrderItems(oT);
        }
        System.out.println(or);
    }

}
