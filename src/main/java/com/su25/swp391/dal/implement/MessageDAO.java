/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.LogRequest;
import com.su25.swp391.entity.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Predator
 */
public class MessageDAO extends DBContext implements I_DAO<Message> {

    @Override
    public List<Message> findAll() {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM Messages ORDER BY timestamp ASC";
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Message message = getFromResultSet(resultSet);
                messages.add(message);
            }
        } catch (SQLException e) {
            System.err.println("Error finding all messages: " + e.getMessage());
        } finally {
            closeResources();
        }
        
        return messages;
    }

    @Override
    public Map<Integer, Message> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Message t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Message t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Message t) {
        String sql = "INSERT INTO Messages (sender, receiver, message) VALUES (?, ?, ?)";
        int result = -1;
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, t.getSender());
            statement.setString(2, t.getReceiver());
            statement.setString(3, t.getMessage());
            
            int affectedRows = statement.executeUpdate();
            
            if (affectedRows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    result = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error inserting message: " + e.getMessage());
        } finally {
            closeResources();
        }
        
        return result;
    }

    @Override
    public Message getFromResultSet(ResultSet resultSet) throws SQLException {
        Message message = new Message();
        message.setId(resultSet.getInt("id"));
        message.setSender(resultSet.getString("sender"));
        message.setReceiver(resultSet.getString("receiver"));
        message.setMessage(resultSet.getString("message"));
        // Handle datetime timestamp
        if (resultSet.getTimestamp("timestamp") != null) {
            message.setTimestamp(resultSet.getTimestamp("timestamp").toString());
        }
        return message;
    }

    @Override
    public Message findById(Integer id) {
        String sql = "SELECT * FROM Messages WHERE id = ?";
        Message message = null;
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                message = getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Error finding message by id: " + e.getMessage());
        } finally {
            closeResources();
        }
        
        return message;
    }
    
    // Custom methods for chat functionality
    public boolean saveMessage(String sender, String receiver, String msg) {
        String sql = "INSERT INTO Messages (sender, receiver, message) VALUES (?, ?, ?)";
        boolean success = false;
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, sender);
            statement.setString(2, receiver);
            statement.setString(3, msg);
            
            int affectedRows = statement.executeUpdate();
            success = affectedRows > 0;
            
            System.out.println("Message saved: " + sender + " -> " + receiver + ": " + msg);
            
        } catch (SQLException e) {
            System.err.println("Error saving message: " + e.getMessage());
        } finally {
            closeResources();
        }
        
        return success;
    }

    public List<Message> getMessages(String user1, String user2) {
        String sql = "SELECT * FROM Messages WHERE (sender=? AND receiver=?) OR (sender=? AND receiver=?) ORDER BY timestamp ASC";
        List<Message> list = new ArrayList<>();
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, user1);
            statement.setString(2, user2);
            statement.setString(3, user2);
            statement.setString(4, user1);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Message msg = getFromResultSet(resultSet);
                list.add(msg);
            }
            
            System.out.println("Retrieved " + list.size() + " messages between " + user1 + " and " + user2);
            
        } catch (SQLException e) {
            System.err.println("Error retrieving messages: " + e.getMessage());
        } finally {
            closeResources();
        }
        
        return list;
    }
}

