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
public class FeedbacksDAO extends DBContext implements I_DAO<Feedbacks>{
     // Lấy tất cả feedback của một người dùng
    public List<Feedbacks> getAllFeedbacks() {
        List<Feedbacks> feedbacks = new ArrayList<>();
        String sql = "SELECT f.*, a.full_name, a.user_name, fo.name , fo.image_url "
                + "FROM swp391_healthy_food.feedbacks f "
                + "JOIN swp391_healthy_food.account a ON f.user_id = a.id "
                + "JOIN swp391_healthy_food.order_items oi ON f.order_item_id = oi.order_item_id "
                + "JOIN swp391_healthy_food.Food fo ON oi.food_id = fo.id "
                + "ORDER BY f.created_at DESC";
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                feedbacks.add(mapFeedback(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Error getting feedbacks by user ID: " + e.getMessage());
        } finally {
            closeResources();
        }
        return feedbacks;
    }

    @Override
    public List<Feedbacks> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<Integer, Feedbacks> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Feedbacks t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Feedbacks findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // Phương thức hỗ trợ để map ResultSet thành đối tượng Feedbacks
    private Feedbacks mapFeedback(ResultSet rs) throws SQLException {
        Feedbacks feedback = new Feedbacks();
        feedback.setFeedbackId(rs.getInt("id"));
        feedback.setUserId(rs.getInt("user_id"));
        feedback.setOrderItemId(rs.getInt("order_item_id"));
        feedback.setContent(rs.getString("content"));
        feedback.setRating(rs.getInt("rating"));
        feedback.setIsVisible(rs.getBoolean("is_visible"));
        feedback.setCreatedAt(rs.getTimestamp("created_at"));
        feedback.setUpdatedAt(rs.getTimestamp("updated_at"));
        
        // Thông tin mở rộng
        String fullName = rs.getString("full_name");
       
        if (fullName != null && fullName != null) {
            feedback.setUserName(fullName );
        } else {
            feedback.setUserName(rs.getString("user_name"));
        }
        
        feedback.setProductName(rs.getString("name"));
        feedback.setProductImage(rs.getString("image_url"));
        
        return feedback;
    }
    public static void main(String[] args) {
        FeedbacksDAO f  = new FeedbacksDAO();
        List<Feedbacks> l = f.getAllFeedbacks();
        System.out.println(l);
    }
            
}
