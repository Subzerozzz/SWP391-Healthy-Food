
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.FoodCategory;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Hang
 */
public class CategoryDAO extends DBContext implements I_DAO<FoodCategory> {

    @Override
    public List<FoodCategory> findAll() {
        List<FoodCategory> category = new ArrayList<>();
        String sql = "SELECT * FROM FoodCategory";
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
        String sql = "SELECT COUNT(*) FROM FoodCategory";
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

    public List<FoodCategory> findAllWithPagination(int page, int pageSize) {
        List<FoodCategory> category = new ArrayList<>();
        String sql = "SELECT * FROM FoodCategory ORDER BY id LIMIT ?, ?";

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
    public Map<Integer, FoodCategory> findAllMap() {
        Map<Integer, FoodCategory> categoryMap = new HashMap<>();
        String sql = "SELECT * FROM FoodCategory";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                FoodCategory category = getFromResultSet(resultSet);
                categoryMap.put(category.getId(), category);
            }
        } catch (Exception e) {
        } finally {
            closeResources();
        }
        return categoryMap;
    }

    @Override
    public boolean update(FoodCategory t) {
        List<FoodCategory> updatecategory = new ArrayList<>();
        String sql = "UPDATE FoodCategory "
                + "SET name = ?, description = ?, minBMI = ?, maxBMI = ?"
                + " WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getName());
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
public boolean delete(FoodCategory t) {
    String deleteFoodSql = "DELETE FROM Food WHERE category_id = ?";
    String deleteCategorySql = "DELETE FROM FoodCategory WHERE id = ?";

    try {
        connection = getConnection();
        connection.setAutoCommit(false); // Bắt đầu transaction

        // Xóa tất cả các món ăn thuộc category này
        PreparedStatement stmtFood = connection.prepareStatement(deleteFoodSql);
        stmtFood.setInt(1, t.getId());
        stmtFood.executeUpdate();

        // Sau đó xóa category
        PreparedStatement stmtCategory = connection.prepareStatement(deleteCategorySql);
        stmtCategory.setInt(1, t.getId());
        int rows = stmtCategory.executeUpdate();

        connection.commit(); // Hoàn tất nếu không có lỗi
        return rows > 0;
    } catch (Exception e) {
        System.out.println("❌ Lỗi khi xóa: " + e.getMessage());
        try {
            if (connection != null) connection.rollback();
        } catch (Exception ex) {
            System.out.println("❌ Rollback lỗi: " + ex.getMessage());
        }
    } finally {
        closeResources();
    }
    return false;
}


    @Override
    public int insert(FoodCategory t) {
        String sql = "INSERT INTO FoodCategory "
                + " (name, description, maxBMI, minBMI) "
                + "VALUES (?, ?, ?, ?)";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, t.getName());
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
    public FoodCategory getFromResultSet(ResultSet resultSet) throws SQLException {
        return FoodCategory.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .maxBMI(resultSet.getDouble("maxBMI"))
                .minBMI(resultSet.getDouble("minBMI"))
                .build();
    }

    @Override
    public FoodCategory findById(Integer id) {
        String sql = "SELECT * FROM FoodCategory WHERE id = ?";
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

    public List<FoodCategory> findCategorybyName(String keyword) {
        List<FoodCategory> category = new ArrayList<>();
        String sql = "SELECT * FROM FoodCategory WHERE name LIKE ? ";
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

    public List<FoodCategory> filterCategoryByBMI(double min, double max) {
    List<FoodCategory> list = new ArrayList<>();
    String sql = "SELECT * FROM FoodCategory WHERE minBMI >= ? AND maxBMI <= ?";
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
 
}
