package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Feedback;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
            e.printStackTrace();
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
                + "FROM Feedback f "
                + "JOIN Account a "
                + "ON f.user_id = a.id "
                + "JOIN OrderItem oi "
                + "ON f.order_item_id = oi.id"
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

    @Override
    public boolean delete(Feedback t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
//mạnh

    @Override
    public int insert(Feedback t) {
        String sql = "INSERT INTO Feedback (user_id, order_item_id, content, rating, is_visible) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, t.getUser_id());
            statement.setInt(2, t.getOrder_item_id());
            statement.setString(3, t.getContent());
            statement.setInt(4, t.getRating());
            statement.setBoolean(5, t.isVisible());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return resultSet.getInt(1); // return the auto-generated ID
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding Feedback: " + e.getMessage());
        } finally {
            closeResources();
        }
        return 0;
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
            params.add("%" + foodName + "%");
        }

        // Filter by rating if provided
        if (rating != null && !rating.isEmpty()) {
            sql.append("AND f.rating = ? ");
            params.add(rating);
        }

        // Apply sorting: by ID if specified, otherwise default to created_at DESC
        if (sort != null && !sort.isEmpty()) {
            sql.append("ORDER BY f.id ").append(sort).append(" LIMIT ? OFFSET ?");
        } else {
            sql.append("ORDER BY f.created_at DESC LIMIT ? OFFSET ?");
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
            ex.printStackTrace();
            // Handle any SQL exceptions that may occur
            System.out.println("Error finding filtered orders: " + ex.getMessage());
        } finally {
            closeResources();
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
            params.add("%" + foodName + "%");
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

    // Mạnh
    public int getTotalFeedbackCountByUserIdAndRating(int userId, String rating) {
        boolean filterByRating = rating != null
                && !rating.trim().isEmpty()
                && !rating.equalsIgnoreCase("all");

        String sql = "SELECT COUNT(*) FROM Feedback WHERE user_id = ? AND is_visible = 1";
        if (filterByRating) {
            sql += " AND rating = ?";
        }

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, userId);
            if (filterByRating) {
                statement.setInt(2, Integer.parseInt(rating));
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

    //Mạnh
    public List<Feedback> feedbackByUserIdAndRatingWithPagination(int userId, String rating, int page, int pageSize) {
        List<Feedback> list = new ArrayList<>();
        boolean filterByRating = rating != null
                && !rating.trim().isEmpty()
                && !rating.equalsIgnoreCase("all");

        String sql = "SELECT * FROM Feedback WHERE user_id = ? AND is_visible = 1";
        if (filterByRating) {
            sql += " AND rating = ?";
        }
        sql += " ORDER BY id DESC LIMIT ? OFFSET ?";

        try {
            int offset = (page - 1) * pageSize;
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, userId);
            int paramIndex = 2;

            if (filterByRating) {
                statement.setInt(paramIndex++, Integer.parseInt(rating));
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

    // mạnh
    public Feedback findByUserIdAndOrderItemId(int userId, int orderItemId) {
        Feedback feedback = null;
        String sql = "SELECT * FROM Feedback WHERE user_id = ? AND order_item_id = ? LIMIT 1";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, orderItemId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                feedback = getFromResultSet(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return feedback;
    }
// mạnh

    public boolean updateByUserIdAndOrderItemId(int userId, int orderItemId, String content, int rating) {
        String sql = "UPDATE Feedback SET content = ?, rating = ? WHERE user_id = ? AND order_item_id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, content);
            statement.setInt(2, rating);
            statement.setInt(3, userId);
            statement.setInt(4, orderItemId);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating feedback by user_id and order_item_id: " + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    // new tri
    // Update is_Visible for Feedback 
    @Override
    public boolean update(Feedback t) {
        // update new tri
        // Set command sql update is_visible in table Feedback by id
        String sql = "UPDATE Feedback SET  is_visible = ? "
                + "WHERE id = ?";
        try {
            // Get connection to the database
            connection = getConnection();
            // Prepare a parameterized SQL statement
            statement = connection.prepareStatement(sql);
            // update the first parameter is_víible
            statement.setBoolean(1, t.isVisible());
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

    public List<Feedback> getFeedbackByCriteria(String search, String rating, String sort, int page, int pageSize) {
        List<Feedback> feedbacks = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();

        // Base query: Use INNER JOIN for search, LEFT JOIN otherwise
        if (search != null && !search.trim().isEmpty()) {
            sql.append("SELECT f.* FROM Feedback f INNER JOIN Account a ON f.user_id = a.id");
        } else {
            sql.append("SELECT f.* FROM Feedback f LEFT JOIN Account a ON f.user_id = a.id");
        }

        // WHERE clauses
        boolean whereAdded = false;
        if (search != null && !search.trim().isEmpty()) {
            sql.append(" WHERE (a.full_name LIKE ? OR a.email LIKE ?)");
            params.add("%" + search.trim() + "%");
            params.add("%" + search.trim() + "%");
            whereAdded = true;
        }

        if (rating != null && !rating.isEmpty()) {
            if (whereAdded) {
                sql.append(" AND");
            } else {
                sql.append(" WHERE");
            }
            sql.append(" f.rating = ?");
            params.add(Integer.parseInt(rating));
        }

        // ORDER BY clause
        if (sort != null && !sort.isEmpty() && ("ASC".equalsIgnoreCase(sort) || "DESC".equalsIgnoreCase(sort))) {
            sql.append(" ORDER BY f.id ").append(sort);
        } else {
            sql.append(" ORDER BY f.created_at DESC");
        }

        // LIMIT and OFFSET for pagination
        sql.append(" LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add((page - 1) * pageSize);

        // Execute query
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                feedbacks.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("Error getting feedback by criteria: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return feedbacks;
    }

    public int countFeedbackByCriteria(String search, String rating) {
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();

        // Base query
        if (search != null && !search.trim().isEmpty()) {
            sql.append("SELECT COUNT(f.id) FROM Feedback f INNER JOIN Account a ON f.user_id = a.id");
        } else {
            sql.append("SELECT COUNT(f.id) FROM Feedback f");
        }

        // WHERE clauses
        boolean whereAdded = false;
        if (search != null && !search.trim().isEmpty()) {
            sql.append(" WHERE (a.full_name LIKE ? OR a.email LIKE ?)");
            params.add("%" + search.trim() + "%");
            params.add("%" + search.trim() + "%");
            whereAdded = true;
        }

        if (rating != null && !rating.isEmpty()) {
            if (whereAdded) {
                sql.append(" AND");
            } else {
                sql.append(" WHERE");
            }
            sql.append(" f.rating = ?");
            params.add(Integer.parseInt(rating));
        }

        // Execute query
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
            System.out.println("Error counting feedback by criteria: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return 0;
    }
    //Mạnh
public List<Feedback> findFeedbackByFoodIdAndRating(int foodId, String rating) {
    List<Feedback> feedbackList = new ArrayList<>();
    String sql = "SELECT f.* "
               + "FROM Feedback f "
               + "JOIN OrderItem oi ON f.order_item_id = oi.id "
               + "WHERE oi.food_id = ? AND f.is_visible = 1";

    // Chỉ thêm điều kiện lọc rating nếu rating có giá trị hợp lệ
    boolean hasRatingFilter = rating != null && !rating.trim().isEmpty() && !"all".equalsIgnoreCase(rating);
    if (hasRatingFilter) {
        sql += " AND f.rating = ?";
    }

    try {
        connection = getConnection();
        statement = connection.prepareStatement(sql);
        statement.setInt(1, foodId);

        if (hasRatingFilter) {
            statement.setInt(2, Integer.parseInt(rating.trim()));
        }

        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Feedback feedback = getFromResultSet(resultSet);
            feedbackList.add(feedback);
        }
    } catch (SQLException e) {
        System.out.println("Error fetching feedbacks by food_id and rating: " + e.getMessage());
    } finally {
        closeResources();
    }

    return feedbackList;
}

}
