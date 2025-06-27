
package com.su25.swp391.dal.implement;
import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Feedback;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.OrderItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FeedbackDAO extends DBContext implements I_DAO<Feedback> {
    // Get Feedback by Id Feedback
    @Override
    public Feedback findById(Integer id) {
        String sql = "SELECT * from Feedback WHERE id = ?";
        try {
            // Get connection to the database
            connection = getConnection();
            // Prepare a parameterized SQL statement
            statement = connection.prepareStatement(sql);
            // Set the first parameter (usually an ID value) in the SQL statement
            statement.setInt(1, id);
            // Execute the query and store the result in a ResultSet
            resultSet = statement.executeQuery();
            // If the query returns at least one row, map that row to a Java object and return it
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Error getting feedback by ID: " + e.getMessage());
        } finally {
            closeResources();
        }
        // If don't have feedback 
        return null;
    }
    // Lấy tất cả feedback của một người dùng
    @Override
    public List<Feedback> findAll() {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT f.*, a.full_name, a.user_name, fo.name , fo.image_url "
                + "FROM feedbacks f "
                + "JOIN account a "
                + "ON f.user_id = a.id "
                + "JOIN order_items oi "
                + "ON f.order_item_id = oi.order_item_id "
                + "JOIN Food fo "
                + "ON oi.food_id = fo.id "
                + "Where f.is_visible = 1 "
                + "ORDER BY f.created_at DESC";

        try {
            // Get connection to the database
            connection = getConnection();
            // Prepare a parameterized SQL statement
            statement = connection.prepareStatement(sql);
            // Execute the query and store the result in a ResultSet
            resultSet = statement.executeQuery();
            // Go to each resultSet get feedback object and add to feedback list
            while (resultSet.next()) {
                feedbacks.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Error getting feedbacks by user ID: " + e.getMessage());
        } finally {
            closeResources();
        }
        return feedbacks;
    }

    @Override
    public Map<Integer, Feedback> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Update is_Visible for Feedback 
    @Override
    public boolean update(Feedback t) {
        // Set command sql update is_visible in table Feedback by id
        String sql = "UPDATE Feedback SET  is_visible = ? "
                + "WHERE id = ?";
        try {
            // Get connection to the database
            connection = getConnection();
            // Prepare a parameterized SQL statement
            statement = connection.prepareStatement(sql);
            // update the first parameter is_víible
            statement.setBoolean(1, false);
            // Set the second parameter (usually an ID value) in the SQL statement
            statement.setInt(2, t.getId());
            // return true if have a row update
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating feedback: " + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean delete(Feedback t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Feedback t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Create a Feedback object from the current row of a ResultSet
  @Override
    public Feedback getFromResultSet(ResultSet resultSet) throws SQLException {
        return new Feedback()
                .builder() // Start building a new Feedback object
                .id(resultSet.getInt("id")) // Set feedback ID
                .user_id(resultSet.getInt("user_id")) // Set user ID who gave the feedback
                .order_item_id(resultSet.getInt("order_item_id")) // Set order item ID associated with the feedback
                .content(resultSet.getString("content")) // Set feedback content
                .rating(resultSet.getInt("rating")) // Set feedback rating (e.g., 1-5)
                .isVisible(resultSet.getBoolean("is_visible")) // Set visibility status
                .createdAt(resultSet.getTimestamp("created_at")) // Set timestamp for when feedback was created
                .updatedAt(resultSet.getTimestamp("updated_at")) // Set timestamp for last feedback update
                .build(); // Finish building and return the Feedback object
    }
    
    public List<Feedback> searchFeedback(String search, String rating, String foodName, String sort, int page, int pageSize) {
        // Create a list to hold the feedback results
        List<Feedback> feedbacks = new ArrayList<>();

        // Build the base SQL query with necessary joins
        StringBuilder sql = new StringBuilder("SELECT f.* "
                + "FROM Feedback f "
                + "JOIN OrderItem oi ON f.order_item_id = oi.id "
                + "JOIN Food fo ON oi.food_id = fo.id "
                + "JOIN Account a ON f.user_id = a.id "
                + "WHERE (a.user_name LIKE ? OR a.email LIKE ?) ");

        // Store query parameters in a list for later use
        List<Object> params = new ArrayList<>();

        // Prepare search pattern for user_name or email (wildcard matching)
        String searchPattern = "%" + search.trim() + "%";
        params.add(searchPattern);
        params.add(searchPattern);

        // Add filter for rating, if provided
        if (rating != null && !rating.isEmpty()) {
            sql.append("AND f.rating = ? ");
            params.add(rating);
        }

        // Add filter for food name, if provided
        if (foodName != null && !foodName.isEmpty()) {
            sql.append("AND fo.name LIKE ? ");
            params.add(foodName);
        }

        // Determine sorting logic: sort by ID if specified, otherwise default to created_at descending
        if (sort != null && !sort.isEmpty()) {
            sql.append("ORDER BY id ").append(sort).append(" LIMIT ? OFFSET ?");
        } else {
            sql.append("ORDER BY f.created_at DESC LIMIT ? OFFSET ?");
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
                feedbacks.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            // Log any SQL exceptions that occur during the process
            System.out.println("Error searching orders: " + ex.getMessage());
        } finally {
            // Clean up database resources (connection, statement, resultSet, etc.)
            closeResources();
        }

        // Return the list of matched feedback
        return feedbacks;
    }

    public int getTotalFeedbackResults(String search, String rating, String foodName) {
        // Build the base SQL query with JOINs to connect Feedback with related entities
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) "
                + "FROM Feedback f "
                + "JOIN OrderItem oi ON f.order_item_id = oi.id "
                + "JOIN Food fo ON oi.food_id = fo.id "
                + "JOIN Account a ON f.user_id = a.id "
                + "WHERE (a.user_name LIKE ? OR a.email LIKE ? OR CAST(o.order_id AS CHAR) = ?) ");

        // Prepare a list to hold all query parameters
        List<Object> params = new ArrayList<>();

        // Format and add the search keyword for username and email filters
        String searchPattern = "%" + search.trim() + "%";
        params.add(searchPattern); // For a.user_name LIKE ?
        params.add(searchPattern); // For a.email LIKE ?
        params.add(search);        // For exact match on order_id

        // Add rating filter if provided
        if (rating != null && !rating.isEmpty()) {
            sql.append("AND f.rating = ? ");
            params.add(rating);
        }

        // Add food name filter if provided
        if (foodName != null && !foodName.isEmpty()) {
            sql.append("AND fo.name LIKE ? ");
            params.add(foodName);
        }

        try {
            // Open database connection and prepare the statement
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            // Set each parameter in the PreparedStatement
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            // Execute the query and return the count if available
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1); // Return the value of COUNT(*)
            }
        } catch (SQLException ex) {
            // Log any errors during database access
            System.out.println("Error counting search results: " + ex.getMessage());
        } finally {
            // Ensure database resources are released properly
            closeResources();
        }

        // Fallback return if no result was found or an error occurred
        return 0;
    }

    public List<Feedback> findFeedbackWithFilters(String rating, String foodName, String sort, int page, int pageSize) {
        // List to hold the resulting feedback records
        List<Feedback> feedbacks = new ArrayList<>();

        // Build base SQL query joining necessary tables
        StringBuilder sql = new StringBuilder("SELECT f.* "
                + "FROM Feedback f "
                + "JOIN OrderItem oi ON f.order_item_id = oi.id "
                + "JOIN Food fo ON oi.food_id = fo.id "
                + "WHERE 1=1 "); // Dummy condition to simplify appending "AND" later

        // List to hold dynamic parameters for the query
        List<Object> params = new ArrayList<>();

        // Filter by food name if provided
        if (foodName != null && !foodName.isEmpty()) {
            sql.append("AND fo.name LIKE ? ");
            params.add(foodName);
        }

        // Filter by rating if provided
        if (rating != null && !rating.isEmpty()) {
            sql.append("AND f.rating = ? ");
            params.add(rating);
        }

        // Apply sorting: by ID if specified, otherwise default to created_at DESC
        if (sort != null && !sort.isEmpty()) {
            sql.append("ORDER BY id ").append(sort).append(" LIMIT ? OFFSET ?");
        } else {
            sql.append("ORDER BY created_at DESC LIMIT ? OFFSET ?");
        }

        // Add pagination parameters
        params.add(pageSize);
        params.add((page - 1) * pageSize);

        try {
            // Establish DB connection and prepare the SQL statement
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            // Bind parameters to the prepared statement
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            // Execute the query and populate the feedback list with results
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                feedbacks.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            // Handle any SQL exceptions that may occur
            System.out.println("Error finding filtered orders: " + ex.getMessage());
        } finally {
            // (Optional) Add resource cleanup here if you're not using try-with-resources
        }

        // Return the list of matched feedbacks
        return feedbacks;
    }
      
    public int getTotalFilteredFeedback(String rating, String foodName) {
        // Build the initial SQL query to count matching feedbacks
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) "
                + "FROM Feedback f "
                + "JOIN OrderItem oi ON f.order_item_id = oi.id "
                + "JOIN Food fo ON oi.food_id = fo.id "
                + "WHERE 1 = 1 "); // Placeholder condition to simplify dynamic WHERE clauses

        // List to hold dynamic SQL parameters
        List<Object> params = new ArrayList<>();

        // Apply rating filter if it exists
        if (rating != null && !rating.isEmpty()) {
            sql.append("AND f.rating = ? ");
            params.add(rating);
        }

        // Apply food name filter if it exists
        if (foodName != null && !foodName.isEmpty()) {
            sql.append("AND fo.name LIKE ? ");
            params.add(foodName);
        }

        try {
            // Establish DB connection and prepare statement
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            // Bind each parameter value to the prepared statement
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            // Execute the query and return the count if available
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1); // First column is COUNT(*)
            }
        } catch (SQLException ex) {
            // Log the exception for debugging purposes
            System.out.println("Error counting filtered feedback: " + ex.getMessage());
        } finally {
            // Always close DB resources to prevent leaks
            closeResources();
        }

        // Return 0 if no result found or an error occurred
        return 0;
    }


    public static void main(String[] args) {
        FeedbackDAO f = new FeedbackDAO();
        System.out.println("");               
    }

}
