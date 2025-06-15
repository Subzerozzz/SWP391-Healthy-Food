package com.su25.swp391.dal.impl;

import com.su25.swp391.entity.Category;
import com.su25.swp391.entity.CategoryFood;
import com.su25.swp391.entity.Food;
import com.su25.swp391.dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class CategoryFoodDAO {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<CategoryFood> getAllCategoryFoods() {
        List<CategoryFood> list = new ArrayList<>();
        String query = "SELECT * FROM Category_product";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new CategoryFood(
                        rs.getInt("category_product_id"),
                        rs.getInt("category_id"),
                        rs.getInt("id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    public List<Category> getCategoriesByFoodId(int productId) {
        List<Category> categories = new ArrayList<>();
        try {
            String sql = "SELECT c.* FROM Categories c " +
                         "JOIN Category_product cp ON c.category_id = cp.category_id " +
                         "WHERE cp.id = ?";
            
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setStatus(rs.getByte("status"));
                
                // Sử dụng java.sql.Date thay vì java.util.Date
                java.sql.Timestamp createdTimestamp = rs.getTimestamp("created_at");
                java.sql.Timestamp updatedTimestamp = rs.getTimestamp("updated_at");
                
                if (createdTimestamp != null) {
                    category.setCreatedAt(new Timestamp(createdTimestamp.getTime()));
                }
                
                if (updatedTimestamp != null) {
                    category.setUpdatedAt(new Timestamp(updatedTimestamp.getTime()));
                }
                
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return categories;
    }

    public List<Food> getFoodsByCategoryId(int categoryId) {
        List<Food> list = new ArrayList<>();
        String query = "SELECT p.* FROM Food f " +
                "JOIN Category_product cp ON f.id = cp.product_id " +
                "WHERE cp.category_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Food product = Food.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .description(rs.getString("description"))
                        .price(rs.getBigDecimal("price"))
                        .image_url(rs.getString("image_url"))
                        .status(rs.getByte("status"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .updatedAt(rs.getTimestamp("updated_at"))
                        .build();
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    public void addCategoryToFood(int categoryId, int productId) {
        String query = "INSERT INTO Category_product (category_id, id) VALUES (?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void removeCategoryFromFood(int categoryId, int productId) {
        String query = "DELETE FROM Category_product WHERE category_id = ? AND id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void removeAllCategoriesFromFood(int productId) {
        String query = "DELETE FROM Category_product WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 