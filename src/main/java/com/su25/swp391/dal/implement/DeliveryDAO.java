package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Delivery;
import com.su25.swp391.entity.Feedback;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeliveryDAO extends DBContext implements I_DAO<Delivery> {

    @Override
    public List<Delivery> findAll() {
        String sql = "Select * from Delivery";
        List<Delivery> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
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

    public List<Delivery> findDeliveryWithFilters(String sort, String status, int page, int pageSize) {
        List<Delivery> list = new ArrayList<>();
        // Build the SQL query dynamically with optional filters
        StringBuilder sql = new StringBuilder(
                "SELECT * from Delivery where 1 = 1 ");
        List<Object> params = new ArrayList<>();
        // Add status filter if provided
        if (status != null && !status.isEmpty()) {
            sql.append("AND status = ? ");
            params.add(status);
        }
        // Append sorting and pagination
        if (sort != null && !sort.isEmpty()) {
            sql.append(" ORDER BY id " + sort + " LIMIT ? OFFSET ?");
        } else {
            sql.append(" ORDER BY assigned_at DESC LIMIT ? OFFSET ?");
        }
        // Add pagination parameters
        params.add(pageSize);
        params.add((page - 1) * pageSize);
        try {
            // Open connection and prepare statement
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
            // Set parameters in PreparedStatement
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
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

    public int getTotalFilteredDelivery(String status) {
        // Build the base SQL query with necessary joins
        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) from Delivery where 1 = 1 ");
        // Store query parameters in a list for later use
        List<Object> params = new ArrayList<>();

        // Add filter for food name, if provided
        if (status != null && !status.isEmpty()) {
            sql.append("AND status = ? ");
            params.add(status);
        }

        try {
            // Open a connection and prepare the SQL statement
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            // Bind all parameters to the prepared statement
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            // Execute the query and process the result set
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1); // Return the value of COUNT(*)
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Clean up database resources (connection, statement, resultSet, etc.)
            closeResources();
        }

        // Return the list of matched feedback
        return 0;
    }

    public List<Delivery> searchDelivery(String search, String sort, String status, int page, int pageSize) {
        // Create a list to hold the feedback results
        List<Delivery> list = new ArrayList<>();

        // Build the base SQL query with necessary joins
        StringBuilder sql = new StringBuilder("SELECT d.* "
                + "FROM Delivery d "
                + "JOIN Account a ON d.shipper_id = a.id "
                + "WHERE (a.user_name LIKE ? OR a.email LIKE ?) ");

        // Store query parameters in a list for later use
        List<Object> params = new ArrayList<>();

        // Prepare search pattern for user_name or email (wildcard matching)
        String searchPattern = "%" + search.trim() + "%";
        params.add(searchPattern);
        params.add(searchPattern);

        // Add filter for food name, if provided
        if (status != null && !status.isEmpty()) {
            sql.append("AND d.status = ? ");
            params.add(status);
        }

        // Append sorting and pagination
        if (sort != null && !sort.isEmpty()) {
            sql.append(" ORDER BY d.id " + sort + " LIMIT ? OFFSET ?");
        } else {
            sql.append(" ORDER BY d.assigned_at DESC LIMIT ? OFFSET ?");
        }
        // Add pagination parameters
        params.add(pageSize);
        params.add((page - 1) * pageSize);

        try {
            // Open a connection and prepare the SQL statement
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            // Bind all parameters to the prepared statement
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            // Execute the query and process the result set
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Clean up database resources (connection, statement, resultSet, etc.)
            closeResources();
        }

        // Return the list of matched feedback
        return list;
    }

    public int getTotalDeliveryResults(String search, String status) {
        // Build the base SQL query with necessary joins
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) "
                + "FROM Delivery d "
                + "JOIN Account a ON d.shipper_id = a.id "
                + "WHERE (a.user_name LIKE ? OR a.email LIKE ?) ");

        // Store query parameters in a list for later use
        List<Object> params = new ArrayList<>();

        // Prepare search pattern for user_name or email (wildcard matching)
        String searchPattern = "%" + search.trim() + "%";
        params.add(searchPattern);
        params.add(searchPattern);

        // Add filter for food name, if provided
        if (status != null && !status.isEmpty()) {
            sql.append("AND d.status = ? ");
            params.add(status);
        }

        try {
            // Open a connection and prepare the SQL statement
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            // Bind all parameters to the prepared statement
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            // Execute the query and process the result set
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1); // Return the value of COUNT(*)
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Clean up database resources (connection, statement, resultSet, etc.)
            closeResources();
        }

        // Return the list of matched feedback
        return 0;
    }

    public int getNumberDeliveryOfShipperPending(int shipper_id) {
        String sql = "SELECT COUNT(*) FROM Delivery WHERE shipper_id = ? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, shipper_id);
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

    @Override
    public Map<Integer, Delivery> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Delivery t) {
        String sql = "UPDATE Delivery SET status = ?, delivered_at = NOW(), note = ? WHERE id = ? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getStatus());
            if (t.getStatus().equalsIgnoreCase("success") || t.getStatus().equalsIgnoreCase("reject")) {
                statement.setString(2, t.getNote());
            } else {
                statement.setString(2, "");
            }
            statement.setInt(3, t.getId());
            int row = statement.executeUpdate();
            return (row > 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean delete(Delivery t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Delivery t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Delivery getFromResultSet(ResultSet resultSet) throws SQLException {
        return Delivery
                .builder()
                .id(resultSet.getInt("id"))
                .order_id(resultSet.getInt("order_id"))
                .order_combo_id(resultSet.getInt("order_combo_id"))
                .shipper_id(resultSet.getInt("shipper_id"))
                .status(resultSet.getString("status"))
                .assigned_at(resultSet.getTimestamp("assigned_at"))
                .delivered_at(resultSet.getTimestamp("delivered_at"))
                .note(resultSet.getString("note"))
                .build();

    }

    @Override
    public Delivery findById(Integer id) {
        String sql = "Select * from Delivery where id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return null;
    }

    public List<Delivery> findDeliveryByShipper(int shipperId, String sort, String status, int page, int pageSize) {
        List<Delivery> list = new ArrayList<>();

        // Base query
        StringBuilder sql = new StringBuilder("SELECT * FROM Delivery WHERE shipper_id = ? ");
        List<Object> params = new ArrayList<>();
        params.add(shipperId);

        // Chỉ lọc status nếu thật sự có truyền vào
        if (status != null && !status.isEmpty() && !status.equalsIgnoreCase("all")) {
            sql.append("AND status = ? ");
            params.add(status);
        }

        // Sort và phân trang
        if (sort != null && !sort.isEmpty()) {
            sql.append("ORDER BY id ").append(sort).append(" LIMIT ? OFFSET ?");
        } else {
            sql.append("ORDER BY assigned_at DESC LIMIT ? OFFSET ?");
        }

        params.add(pageSize);
        params.add((page - 1) * pageSize);

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

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

    public int getTotalFilteredDeliveryByShipper(int shipperId, String status) {
        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) FROM Delivery WHERE shipper_id = ? ");
        List<Object> params = new ArrayList<>();
        params.add(shipperId);

        // Chỉ lọc status nếu khác null, không rỗng và không phải "all"
        if (status != null && !status.isEmpty() && !status.equalsIgnoreCase("all")) {
            sql.append("AND status = ? ");
            params.add(status);
        }

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }

        return 0;
    }

    public List<Delivery> searchDeliveryShipper(String search, String sort, String status, int page, int pageSize, int shipperId) {
        List<Delivery> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT d.* "
                + "FROM Delivery d "
                + "JOIN `Order` o ON d.order_id = o.id "
                + "LEFT JOIN Account c ON o.account_id = c.id "
                + // Customer (có thể null nếu guest)
                "WHERE d.shipper_id = ? "
                + "AND ( "
                + "(o.account_id IS NOT NULL AND (c.full_name LIKE ? OR c.email LIKE ?)) "
                + "OR (o.account_id IS NULL AND (o.full_name LIKE ? OR o.email LIKE ?)) "
                + ") "
        );

        List<Object> params = new ArrayList<>();
        params.add(shipperId);

        String searchPattern = "%" + search.trim() + "%";
        // Cho customer
        params.add(searchPattern);
        params.add(searchPattern);
        // Cho guest
        params.add(searchPattern);
        params.add(searchPattern);

        if (status != null && !status.isEmpty() && !status.equalsIgnoreCase("all")) {
            sql.append("AND d.status = ? ");
            params.add(status);
        }

        sql.append(sort != null && !sort.isEmpty()
                ? "ORDER BY d.id " + sort + " LIMIT ? OFFSET ?"
                : "ORDER BY d.assigned_at DESC LIMIT ? OFFSET ?"
        );

        params.add(pageSize);
        params.add((page - 1) * pageSize);

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }

        return list;
    }

    public int getTotalDeliveryResultsShipper(String search, String status, int shipperId) {
        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) "
                + "FROM Delivery d "
                + "JOIN `Order` o ON d.order_id = o.id "
                + "LEFT JOIN Account c ON o.account_id = c.id "
                + "WHERE d.shipper_id = ? "
                + "AND ( "
                + "(o.account_id IS NOT NULL AND (c.full_name LIKE ? OR c.email LIKE ?)) "
                + "OR (o.account_id IS NULL AND (o.full_name LIKE ? OR o.email LIKE ?)) "
                + ") "
        );

        List<Object> params = new ArrayList<>();
        params.add(shipperId);

        String searchPattern = "%" + search.trim() + "%";
        params.add(searchPattern); // customer
        params.add(searchPattern);
        params.add(searchPattern); // guest
        params.add(searchPattern);

        if (status != null && !status.isEmpty() && !status.equalsIgnoreCase("all")) {
            sql.append("AND d.status = ? ");
            params.add(status);
        }

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }

        return 0;
    }

    public boolean insertDelivery(int order_id, int shipper_id) {
        String sql = "Insert INTO Delivery(order_id,shipper_id,status,assigned_at) VALUES(?,?,'pending',NOW()) ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, order_id);
            statement.setInt(2, shipper_id);
            int row = statement.executeUpdate();
            return (row > 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    public boolean insertDeliveryCombo(int order_combo_id, int shipper_id) {
        String sql = "Insert INTO Delivery(order_combo_id,shipper_id,status,assigned_at) VALUES(?,?,'pending',NOW()) ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, order_combo_id);
            statement.setInt(2, shipper_id);
            int row = statement.executeUpdate();
            return (row > 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

  public Delivery findDeliveryByOrderId(int orderId) {
    Delivery delivery = null;
    String sql = "SELECT * FROM Delivery WHERE order_id = ?";

    try {
        connection = getConnection();
        statement = connection.prepareStatement(sql);
        statement.setInt(1, orderId);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            delivery = getFromResultSet(resultSet);
        }
    } catch (SQLException e) {
        System.out.println("Error in findDeliveryByOrderId: " + e.getMessage());
    } finally {
        closeResources(); // đóng connection, statement, resultSet
    }

    return delivery;
}


}
