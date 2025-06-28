/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author kieud
 */
public class FeedbackDAO extends DBContext implements I_DAO<Feedback> {

    public int getTotalFeedbackCountByUserIdAndRating(int userId, String rating) {
        boolean filterByRating = rating != null
                && !rating.trim().isEmpty()
                && !rating.equalsIgnoreCase("all");

        String sql = "SELECT COUNT(*) FROM Feedback WHERE user_id = ?";
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

    public List<Feedback> feedbackByUserIdAndRatingWithPagination(int userId, String rating, int page, int pageSize) {
        List<Feedback> list = new ArrayList<>();
        boolean filterByRating = rating != null
                && !rating.trim().isEmpty()
                && !rating.equalsIgnoreCase("all");

        String sql = "SELECT * FROM Feedback WHERE user_id = ?";
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

    @Override
    public List<Feedback> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<Integer, Feedback> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Feedback t) {
        String sql = "UPDATE Feedback SET content = ?, rating = ?, is_visible = ? "
                + "WHERE id = ?"; // assuming 'id' is the primary key column name

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, t.getContent());
            statement.setString(2, t.getRating());
            statement.setBoolean(3, t.is_visible());
            statement.setInt(4, t.getId()); // assuming 'id' maps to t.getId()

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating t: " + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean delete(Feedback t) {
        String sql = "DELETE FROM Feedback WHERE id = ?"; // hoặc "t_id" nếu DB bạn dùng tên đó

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, t.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting t: " + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

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
            statement.setString(4, t.getRating());
            statement.setBoolean(5, t.is_visible());

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

    @Override
    public Feedback getFromResultSet(ResultSet resultSet) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(resultSet.getInt("id")); // Giả định tên cột trong DB là id
        feedback.setUser_id(resultSet.getInt("user_id"));
        feedback.setOrder_item_id(resultSet.getInt("order_item_id"));
        feedback.setContent(resultSet.getString("content"));
        feedback.setRating(resultSet.getString("rating"));
        feedback.set_visible(resultSet.getBoolean("is_visible"));
        feedback.setCreated_at(resultSet.getTimestamp("created_at"));
        feedback.setUpdate_at(resultSet.getTimestamp("updated_at"));
        return feedback;
    }

    @Override
    public Feedback findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
