/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class CartDAO extends DBContext implements I_DAO<Cart> {

    /**
     * Tạo giỏ hàng mới cho người dùng
     *
     * @param userId ID của người dùng
     * @return cart_id của giỏ hàng mới tạo, 0 nếu có lỗi
     */
    public int createCart(int userId) {
        String sql = "INSERT INTO Cart (account_id, created_at, updated_at) VALUES (?, NOW(), NOW())";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userId);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error when creating cart: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return 0;
    }

    /**
     * Kiểm tra xem người dùng có giỏ hàng hay không
     *
     * @param userId ID của người dùng
     * @return true nếu người dùng có giỏ hàng, false nếu không
     */
    public boolean hasCart(int userId) {
        String sql = "SELECT COUNT(*) FROM Cart WHERE account_id = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error when checking if user has cart: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return false;
    }

    @Override
    public Map<Integer, Cart> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cart> findAll() {
        List<Cart> list = new ArrayList<>();
        String sql = "SELECT * FROM Cart";
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
    public Cart findById(Integer id) {
        Cart cart = null;
        String sql = "SELECT * FROM Cart WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cart = getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeResources();
        }
        return cart;
    }

    @Override
    public boolean update(Cart t) {
        boolean success = false;
        String sql = "UPDATE Cart SET account_id = ?, created_at = ?, updated_at = ? WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getAccount_id());
            statement.setTimestamp(2, t.getCreated_at());
            statement.setTimestamp(3, t.getUpdated_at());
            statement.setInt(4, t.getId());
            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeResources();
        }
        return success;
    }

    @Override
    public boolean delete(Cart t) {
        boolean success = false;
        String sql = "DELETE FROM Cart WHERE id = ?";
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
    public int insert(Cart t) {
        int generatedId = -1;
        String sql = "INSERT INTO Cart (account_id, created_at, updated_at) VALUES (?, ?, ?)";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, t.getAccount_id());
            statement.setTimestamp(2, t.getCreated_at());
            statement.setTimestamp(3, t.getUpdated_at());
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
    public Cart getFromResultSet(ResultSet resultSet) throws SQLException {
        Cart cart = new Cart();
        cart.setId(resultSet.getInt("id"));
        cart.setAccount_id(resultSet.getInt("account_id"));
        cart.setCreated_at(resultSet.getTimestamp("created_at"));
        cart.setUpdated_at(resultSet.getTimestamp("updated_at"));
        return cart;
    }

    public Cart findCartByAccountId(Integer id) {
        String sql = "SELECT * FROM Cart where account_id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new CartDAO().findCartByAccountId(52));
    }

}
