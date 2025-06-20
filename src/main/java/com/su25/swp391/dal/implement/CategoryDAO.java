
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hang
 */
public class CategoryDAO extends DBContext implements I_DAO<Category> {

    @Override
    public List<Category> findAll() {
        List<Category> category = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                category.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
        } finally {
            closeResources();
        }
        return category;
    }

    public int getTotalCategoryCount() {
        String sql = "SELECT COUNT(*) FROM category";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error counting category: " + e.getMessage());
        } finally {
            closeResources();
        }
        return 0;
    }

    public List<Category> findAllWithPagination(int page, int pageSize) {
        List<Category> category = new ArrayList<>();
        String sql = "SELECT * FROM category ORDER BY id LIMIT ?, ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (page - 1) * pageSize);
            statement.setInt(2, pageSize);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                category.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error finding category with pagination: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return category;
    }


    @Override
    public Map<Integer, Category> findAllMap() {
        Map<Integer, Category> categoryMap = new HashMap<>();
        String sql = "SELECT * FROM category";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = getFromResultSet(resultSet);
                categoryMap.put(category.getId(), category);
            }
        } catch (Exception e) {
        } finally {
            closeResources();
        }
        return categoryMap;
    }

    @Override
    public boolean update(Category t) {
        List<Category> updatecategory = new ArrayList<>();
        String sql = "UPDATE category "
                + "SET name_category = ?, description = ?, minBMI = ?, maxBMI = ?"
                + " WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getName_category());
            statement.setString(2, t.getDescription());
            statement.setDouble(3, t.getMinBMI());
            statement.setDouble(4, t.getMaxBMI());
            statement.setInt(5, t.getId());
            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Err update" + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean delete(Category t) {
        List<Category> deletecategory = new ArrayList<>();
        String sql = "DELETE FROM category WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getId());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Err delete" + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public int insert(Category t) {
        String sql = "INSERT INTO category "
                + " (name_category, description, maxBMI, minBMI) "
                + "VALUES (?, ?, ?, ?)";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, t.getName_category());
            statement.setString(2, t.getDescription());
            statement.setDouble(3, t.getMaxBMI());
            statement.setDouble(4, t.getMinBMI());

            int affectedRow = statement.executeUpdate();
            if (affectedRow > 0) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return resultSet.getInt(1); // Trả về ID vừa insert
                }
            }
        } catch (Exception e) {
            System.out.println("Err insert: " + e.getMessage());
        } finally {
            closeResources();
        }
        return -1;
    }


    @Override
    public Category getFromResultSet(ResultSet resultSet) throws SQLException {
        return Category.builder()
                .id(resultSet.getInt("id"))
                .name_category(resultSet.getString("name_category"))
                .description(resultSet.getString("description"))
                .maxBMI(resultSet.getDouble("maxBMI"))
                .minBMI(resultSet.getDouble("minBMI"))
                .build();
    }

    @Override
    public Category findById(Integer id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);

            }
        } catch (SQLException e) {
            System.out.println("Err findCategoryById" + e.getMessage());
        } finally {
            closeResources();

        }
        return null;
    }

    public List<Category> findCategorybyName(String keyword) {
        List<Category> category = new ArrayList<>();
        String sql = "SELECT * FROM category WHERE name_category LIKE ? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            String likeKeyword = "%" + keyword + "%";
            statement.setString(1, likeKeyword);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                category.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Error search category" + e.getMessage());
        } finally {
            closeResources();
        }
        return category;
    }

    public List<Category> filterCategoryByBMI(double min, double max) {
    List<Category> list = new ArrayList<>();
    String sql = "SELECT * FROM category WHERE minBMI >= ? AND maxBMI <= ?";
    try {
        connection = getConnection();
        statement = connection.prepareStatement(sql);
        statement.setDouble(1, min);
        statement.setDouble(2, max);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            list.add(getFromResultSet(resultSet));
        }
    } catch (SQLException e) {
        System.out.println("Error filtering category by BMI: " + e.getMessage());
    } finally {
        closeResources();
    }
    return list;
}   
    public static void main(String[] args) {
        new CategoryDAO().findAll().forEach(item -> {
            System.out.println(item);
        });
    }
}
