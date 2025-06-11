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
        String sql = "SELECT * FROM Swp301_pr.category";
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
        String sql = "SELECT COUNT(*) FROM Swp301_pr.category";
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
        String sql = "SELECT * FROM Swp301_pr.category ORDER BY idcategory LIMIT ?, ?";

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
      public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
        
        int page = 1;
        int pageSize = 10;

        System.out.println("Testing findAllWithPagination with page = " + page + " and pageSize = " + pageSize);
        
        List<Category> categories = dao.findAllWithPagination(page, pageSize);
        
        if (categories == null || categories.isEmpty()) {
            System.out.println("No categories found.");
        } else {
            for (Category c : categories) {
                System.out.println("ID: " + c.getIdcategory() + ", Name: " + c.getName_category() + ",Description:" + c.getDescription() +",MinMBI:" + c.getMinBMI()+",MaxMBI:" + c.getMaxBMI());
            }
        }
    }
    @Override
    public Map<Integer, Category> findAllMap() {
        Map<Integer, Category> categoryMap = new HashMap<>();
        String sql = "SELECT * FROM Swp301_pr.category";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = getFromResultSet(resultSet);
                categoryMap.put(category.getIdcategory(), category);
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
        String sql = "UPDATE Swp301_pr.category SET  name_category = ? WHERE idcategory = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getName_category());
            statement.setInt(2, t.getIdcategory());
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
        String sql = "DELETE FROM Swp301_pr.category WHERE idcategory = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getIdcategory());
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
        List<Category> insertcategory = new ArrayList<>();
        String sql = "INSERT INTO Swp301_pr.category (name_category) VALUES"
                + "( ?)";
        try {
             connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // BẮT BUỘC PHẢI CÓ ĐOẠN NÀY
            statement.setString(1, t.getName_category());
            //Lấy khóa chính được tạo tự động sau khi insert
            int affectedRow = statement.executeUpdate();
            if (affectedRow > 0) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return resultSet.getInt(1); // Trả về ID vừa insert
                }
            }
        } catch (Exception e) {
            System.out.println("Err inset" +e.getMessage());
        } finally {
            closeResources();
        }
        return -1;
    }

    @Override
    public Category getFromResultSet(ResultSet resultSet) throws SQLException {
return Category.builder()
        .idcategory(resultSet.getInt("idcategory"))
        .name_category(resultSet.getString("name_category"))
        .description(resultSet.getString("description"))
        .maxBMI(resultSet.getInt("maxBMI"))
        .minBMI(resultSet.getInt("minBMI"))
        .build();
    }

    @Override
    public Category findById(Integer id) {
        List<Category> findCategorybyId = new ArrayList<>();
        String sql = "SELECT * FROM Swp301_pr.account WHERE idcategory = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return getFromResultSet(resultSet);
                
            }
        } catch (Exception e) {
            System.out.println("Err findCategoryById"+ e.getMessage());
        } finally {
            closeResources();
                  
        }
        return null;
    }
public  List<Category> searchCategorybyName(String keyword){
    List<Category> category = new ArrayList<>();
    String sql = "SELECT * FROM Swp301_pr.account WHERE name_category LIKE ? ";
    try {
        connection = getConnection();
        statement = connection.prepareStatement(sql);
        String likeKeyword = "%" +keyword + "%";
        statement.setString(1, likeKeyword);
        resultSet = statement.executeQuery();
        while (resultSet.next()){
            category.add(getFromResultSet(resultSet));
        }
    } catch (Exception e) {
        System.out.println("Error search category"+e.getMessage());
    } finally {
        closeResources();
    }
    return category;
}
}
