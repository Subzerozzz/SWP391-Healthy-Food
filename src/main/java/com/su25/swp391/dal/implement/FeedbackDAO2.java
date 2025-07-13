package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.entity.Feedback;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO2 extends DBContext {

    // Get Feedback by Id Feedback
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

    // Update is_Visible for Feedback 
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

    // Create a Feedback object from the current row of a ResultSet
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
    /// test
    public int getTotalFeedback2() {
        String sql = "SELECT COUNT(*) FROM Feedback";
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
    public List<Feedback> pagingFeedback(int index) {
        List<Feedback> coupon = new ArrayList<>();
        String sql = "SELECT * FROM Feedback\n"
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
        FeedbackDAO2 f = new FeedbackDAO2();
        for (Feedback feedback : f.pagingFeedback(1)) {
            System.out.println(feedback);
        }
    }
            
}
