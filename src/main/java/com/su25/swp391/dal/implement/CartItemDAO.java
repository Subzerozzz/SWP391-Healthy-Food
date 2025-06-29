/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.CartItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class CartItemDAO extends DBContext implements I_DAO<CartItem> {

    @Override
    public List<CartItem> findAll() {
        List<CartItem> list = new ArrayList<>();
        String sql = "SELECT * FROM CartItem";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeResources();
        }
        return list;
    }

    @Override
    public Map<Integer, CartItem> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(CartItem t) {
        int generatedId = -1;
        String sql = "INSERT INTO CartItem (cart_id, food_id, quantity, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, t.getCart_id());
            statement.setInt(2, t.getFood_id());
            statement.setInt(3, t.getQuantity());
            statement.setTimestamp(4, t.getCreated_at());
            statement.setTimestamp(5, t.getUpdated_at());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeResources();
        }
        return generatedId;
    }

    @Override
    public boolean update(CartItem t) {
        boolean success = false;
        String sql = "UPDATE CartItem SET cart_id = ?, food_id = ?, quantity = ?, created_at = ?, updated_at = ? WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getCart_id());
            statement.setInt(2, t.getFood_id());
            statement.setInt(3, t.getQuantity());
            statement.setTimestamp(4, t.getCreated_at());
            statement.setTimestamp(5, t.getUpdated_at());
            statement.setInt(6, t.getId());
            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeResources();
        }
        return success;
    }

    @Override
    public boolean delete(CartItem t) {
        boolean success = false;
        String sql = "DELETE FROM CartItem WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getId());
            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeResources();
        }
        return success;
    }

    @Override
    public CartItem getFromResultSet(ResultSet resultSet) throws SQLException {
        CartItem item = new CartItem();
        item.setId(resultSet.getInt("id"));
        item.setCart_id(resultSet.getInt("cart_id"));
        item.setFood_id(resultSet.getInt("food_id"));
        item.setQuantity(resultSet.getInt("quantity"));
        item.setCreated_at(resultSet.getTimestamp("created_at"));
        item.setUpdated_at(resultSet.getTimestamp("updated_at"));
        return item;
    }

    @Override
    public CartItem findById(Integer id) {
        CartItem item = null;
        String sql = "SELECT * FROM CartItem WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                item = getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeResources();
        }
        return item;
    }

    public List<CartItem> findAllCartItemByCartId(Integer id) {
        List<CartItem> list = new ArrayList<>();
        String sql = "Select * From CartItem WHERE cart_id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources();
        }
        return list;
    }
    public static void main(String[] args) {
        for(CartItem cartItem : new CartItemDAO().findAllCartItemByCartId(1)){
            System.out.println(cartItem.toString());
        }
    }

}
