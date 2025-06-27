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
import java.util.List;
import java.util.Map;

/**
 *
 * @author kieud
 */
public class FeedbackDAO extends DBContext implements I_DAO<Feedback> {

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
            statement.setInt(2, t.getRating());
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
            statement.setInt(4, t.getRating());
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Feedback findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
