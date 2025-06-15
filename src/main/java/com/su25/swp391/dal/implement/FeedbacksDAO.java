/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Feedbacks;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class FeedbacksDAO extends DBContext implements I_DAO<Feedbacks> {
   
   // Get Feedbacks by Id Feedback
    @Override
    public Feedbacks findById(Integer id) {
        String sql = "SELECT f.*, a.full_name, a.user_name, fo.name , fo.image_url "
                + "FROM feedbacks f "
                + "JOIN account a "
                + "ON f.user_id = a.id "
                + "JOIN order_items oi "
                + "ON f.order_item_id = oi.order_item_id "
                + "JOIN Food fo "
                + "ON oi.food_id = fo.id "
                + "WHERE f.id = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Error getting feedback by ID: " + e.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }
    // Lấy tất cả feedback của một người dùng
    @Override
    public List<Feedbacks> findAll() {
    List<Feedbacks> feedbacks = new ArrayList<>();
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
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
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
    public Map<Integer, Feedbacks> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Feedbacks t) {
        String sql = "UPDATE swp391_healthy_food.feedbacks SET  is_visible = ? "
                + "WHERE id = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setBoolean(1, false);
            statement.setInt(2, t.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating feedback: " + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean delete(Feedbacks t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Feedbacks t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Feedbacks getFromResultSet(ResultSet resultSet) throws SQLException {
        return new Feedbacks()
                .builder()
                .id(resultSet.getInt(1))
                .userId(resultSet.getInt(1))
                .orderItemId(resultSet.getInt(1))
                .content(resultSet.getString("content"))
                .rating(this.resultSet.getInt("rating"))
                .isVisible(resultSet.getBoolean("is_visible"))
                .createdAt(resultSet.getTimestamp("created_at"))
                .updatedAt(resultSet.getTimestamp("updated_at"))
                .build();
     }

    

    public static void main(String[] args) {
        FeedbacksDAO f = new FeedbacksDAO();
        List<Feedbacks> l = f.findAll();
        System.out.println(l);
        Feedbacks f2 = f.findById(2);
        System.out.println(f2);
        f.update(f2);
    }

}
