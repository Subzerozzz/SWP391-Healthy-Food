/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.FoodDraft;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.sql.Timestamp;
import org.apache.poi.util.LocaleID;

public class FoodDAO extends DBContext implements I_DAO<Food> {

    @Override
    public List<Food> findAll() {
        String sql = "select * from Food ";
        List<Food> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error happen in FoodDAO:" + e.getMessage());
        } finally {
            closeResources();
        }
        return list;

    }

    @Override
    public Food getFromResultSet(ResultSet resultSet) throws SQLException {
        return Food
                .builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .price(resultSet.getDouble("price"))
                .image_url(resultSet.getString("image_url"))
                .status(resultSet.getString("status"))
                .category_id(resultSet.getInt("category_id"))
                .created_at(resultSet.getTimestamp("created_at"))
                .updated_at(resultSet.getTimestamp("updated_at"))
                .nutri_id(resultSet.getInt("nutri_id"))
                .calo(resultSet.getDouble("calo"))
                .build();
        
    }

    public Food checkExistFoodName(String name) {
        Food food = null;
        String sql = "Select * From Food WHERE name = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            // set giá trị vào name
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                food = getFromResultSet(resultSet);
            }
        } catch (Exception e) {
        } finally {
            closeResources();
        }
        return food;
    }

    public List<Food> findByCategoryId(Integer categoryId) {
        List<Food> foodList = new ArrayList<>();
        String sql = "SELECT * FROM Food WHERE category_id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                foodList.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeResources();
        }
        return foodList;
    }

    public List<Food> getFoodByName(String foodName) {
        List<Food> listFood = null;
        String sql = "SELECT * FROM Food"
                + " WHERE name LIKE ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + foodName + "%");
            resultSet = statement.executeQuery();
            listFood = new ArrayList<>();
            while (resultSet.next()) {
                listFood.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeResources();
        }
        return listFood;
    }

    public List<Food> findRecordByPage(int i) {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM Food\n"
                + "ORDER BY id\n"
                + "LIMIT 10 OFFSET ?;";
        // Tính số bản ghi cần bỏ qua
        Integer recordOffset = (i - 1) * 10;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, recordOffset);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeResources();
        }
        return list;
    }

    public List<Food> findRecordByPageForCategory(Integer categoryID, int i) {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM Food\n"
                + "WHERE category_id = ?\n"
                + "ORDER BY id\n"
                + "LIMIT 10 OFFSET ?";
        // Tính số bản ghi cần bỏ qua
        Integer recordOffset = (i - 1) * 10;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryID);
            statement.setInt(2, recordOffset);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeResources();
        }
        return list;
    }

    public List<Food> getRecordByPageForSearch(String foodName, int i) {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM Food\n"
                + "WHERE name LIKE ?\n"
                + "ORDER BY id\n"
                + "LIMIT 10 OFFSET ?";
        // Tính số bản ghi cần bỏ qua
        Integer recordOffset = (i - 1) * 10;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + foodName + "%");
            statement.setInt(2, recordOffset);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeResources();
        }
        return list;
    }

    @Override
    public Map<Integer, Food> findAllMap() {
        String sql = "Select * from Food";
        Map<Integer, Food> map = new HashMap<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Food food = getFromResultSet(resultSet);
                map.put(food.getId(), food);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            closeResources();
        }
        return map;
    }

    @Override
    public int insert(Food t) {
        String sql = "INSERT into Food(name,description,price,image_url,status,category_id,created_at,updated_at,nutri_id,calo)"
                + " values(?,?,?,?,?,?,?,?,?,?) ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, t.getName());
            statement.setString(2, t.getDescription());
            statement.setDouble(3, t.getPrice());
            statement.setString(4, t.getImage_url());
            statement.setString(5, "active");
            statement.setInt(6, t.getCategory_id());
            statement.setTimestamp(7, t.getCreated_at());
            statement.setTimestamp(8, t.getUpdated_at());
            statement.setInt(9, t.getNutri_id());
            statement.setDouble(10, t.getCalo());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return -1;

    }

    @Override
    public boolean update(Food t) {
        String sql = "UPDATE Food SET name = ? , description = ? , price = ? , image_url = ? ,"
                + " status = ? ,category_id = ? ,"
                + "created_at = ? , updated_at = ? , nutri_id = ? ,calo = ? Where id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getName());
            statement.setString(2, t.getDescription());
            statement.setDouble(3, t.getPrice());
            statement.setString(4, t.getImage_url());
            statement.setString(5, "active");
            statement.setInt(6, t.getCategory_id());
            statement.setTimestamp(7, t.getCreated_at());
            statement.setTimestamp(8, t.getUpdated_at());
            statement.setInt(9, t.getNutri_id());
            statement.setDouble(10, t.getCalo());
            statement.setInt(11, t.getId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return false;
    }

    @Override
    public boolean delete(Food t) {
        String sql = "DELETE from Food Where id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getId());
            statement.executeUpdate();
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }
    // Get Food by FoodDraft

    public Food getFromResultFood_Draft(FoodDraft f) {
        return Food
                .builder()
                .id(f.getFood_id())
                .name(f.getName())
                .description(f.getDescription())
                .price(f.getPrice())
                .image_url(f.getImage_url())
                .category_id(f.getCategory_id())
                .created_at(f.getCreated_at())
                .updated_at(f.getUpdated_at())
                .nutri_id(f.getNutri_id())
                .calo(f.getCalo())
                .build();

    }

    @Override
    public Food findById(Integer id) {
        String sql = "Select * from Food WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return null;
    }

    public List<Food> filterChanning(Integer categoryId, String foodName, Integer limit, Integer currentPage) {
        List<Food> list = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT f.* FROM Food f WHERE 1=1");

        // Lọc theo categoryId nếu có
        if (categoryId != 0) {
            sqlBuilder.append(" AND f.category_id = ?");
        }

        // Lọc theo foodName nếu khác rỗng
        if (foodName != null && !foodName.trim().isEmpty()) {
            sqlBuilder.append(" AND f.name LIKE ?");
        }

        // set limit dựa vào currentPage
        if (currentPage != null) {
            sqlBuilder.append(" LIMIT ? OFFSET ?");
        }

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sqlBuilder.toString());
            int paramIndex = 1;
            if (categoryId != 0) {
                statement.setInt(paramIndex++, categoryId);
            }
            if (foodName != null && !foodName.trim().isEmpty()) {
                statement.setString(paramIndex++, "%" + foodName + "%");
            }
            if (currentPage != null) {
                statement.setInt(paramIndex++, limit);
                statement.setInt(paramIndex++, (currentPage - 1) * limit);
            }
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
    
    public List<String> findFoodNameList(){
        String sql = "Select DISTINCT name from Food";
        List<String> lFood = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                lFood.add(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return lFood;
    } 
    
    public List<Food> getFoodByFoodIdFromOrderItems(int foodId) {
    List<Food> foodList = new ArrayList<>();
    String sql = "SELECT f.* FROM Food f "
               + "JOIN OrderItem oi ON f.id = oi.food_id "
               + "WHERE oi.food_id = ?";

    try {
        connection = getConnection();
        statement = connection.prepareStatement(sql);
        statement.setInt(1, foodId);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Food food = new Food();
            food.setId(resultSet.getInt("id"));
            food.setName(resultSet.getString("name"));
            food.setDescription(resultSet.getString("description"));
            food.setPrice(resultSet.getDouble("price"));
            food.setImage_url(resultSet.getString("image_url"));
            food.setStatus(resultSet.getString("status"));
            food.setCategory_id(resultSet.getInt("category_id"));
            food.setCreated_at(resultSet.getTimestamp("created_at"));
            food.setUpdated_at(resultSet.getTimestamp("updated_at"));
            food.setNutri_id(resultSet.getInt("nutri_id"));
            food.setCalo(resultSet.getDouble("calo"));

            foodList.add(food);
        }

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        closeResources();
    }

    return foodList;
  }
     public static void main(String[] args) {
        FoodDAO foodDAO = new FoodDAO();

        int testFoodId = 1; // 📝 Thay bằng food_id thực tế có trong bảng Order_Items

        List<Food> foods = foodDAO.getFoodByFoodIdFromOrderItems(testFoodId);

        if (foods == null || foods.isEmpty()) {
            System.out.println("❌ No food found for food_id = " + testFoodId);
        } else {
            System.out.println("✅ Found " + foods.size() + " food(s):");
            for (Food food : foods) {
                System.out.println("ID: " + food.getId());
                System.out.println("Name: " + food.getName());
                System.out.println("Price: " + food.getPrice());
                System.out.println("Image: " + food.getImage_url());
                System.out.println("Calo: " + food.getCalo());
                System.out.println("------");
            }
        }
    }


}
