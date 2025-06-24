/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Combo;
import com.su25.swp391.entity.ComboFoodDetails;
import com.su25.swp391.entity.Food;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hang
 */
public class ComboDAO extends DBContext implements I_DAO<Combo> {

    @Override
    public List<Combo> findAll() {
        List<Combo> combos = new ArrayList<>();
        String sql = "SELECT * FROM Combo";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                combos.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Err finding all combos" + e.getMessage());
        } finally {
            closeResources();
        }

        return combos;
    }

    //lay tông sô combo
    public int getTotalComboCount() {
        String sql = "SELECT COUNT(*) FROM Combo";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error counting Combo: " + e.getMessage());
        } finally {
            closeResources();
        }
        return 0;
    }

    public List<Combo> findAllWithPagination(int page, int pageSize) {
        List<Combo> combo = new ArrayList<>();
        String sql = "SELECT * FROM Combo ORDER BY comboId LIMIT ?, ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (page - 1) * pageSize);
            statement.setInt(2, pageSize);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                combo.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error finding combo with pagination: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return combo;
    }

    public static void main(String[] args) {
        ComboDAO comboDAO = new ComboDAO(); // Giả sử bạn có class này
        int page = 1;
        int pageSize = 5;

        List<Combo> combos = comboDAO.findAllWithPagination(page, pageSize);

        if (combos.isEmpty()) {
            System.out.println("No combos found on page " + page);
        } else {
            System.out.println("Combos on page " + page + ":");
            for (Combo combo : combos) {
                System.out.println(combo);
            }
        }
    }

    @Override
    public Map<Integer, Combo> findAllMap() {
        Map<Integer, Combo> comboMap = new HashMap<>();
        String sql = "SELECT * FROM Combo";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Combo combo = getFromResultSet(resultSet);
                comboMap.put(combo.getComboId(), combo);
            }
        } catch (SQLException e) {
            System.out.println("Err finding all combos" + e.getMessage());
        } finally {
            closeResources();
        }

        return comboMap;
    }

    @Override
    public boolean update(Combo combo) {
        String sql = "UPDATE Combo\n"
                + "SET \n"
                + "    name = ?,\n"
                + "    description = ?,\n"
                + "    originalPrice = ?,\n"
                + "    discountPrice = ?,\n"
                + "    status = ?\n"
                + "WHERE ComboId = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, combo.getName());
            statement.setString(2, combo.getDescription());
            statement.setDouble(3, combo.getOriginalPrice());
            statement.setDouble(4, combo.getDiscountPrice());
            statement.setString(5, combo.getStatus());
            statement.setInt(6, combo.getComboId());

            int affectecdRows = statement.executeUpdate();
            return affectecdRows > 0;
        } catch (Exception e) {
            System.out.println("Erroe updating combo" + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public boolean delete(Combo t) {
        String sql = "DELETE From Combo WHERE comboId = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getComboId());
            int affectecdRows = statement.executeUpdate();
            return affectecdRows > 0;
        } catch (Exception e) {
            System.out.println("Error delete combo" + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public int insert(Combo t) {
        String sql = "INSERT INTO Combo (ComboId, name, description, originalPrice, discountPrice, status)\n"
                + "VALUES\n" + "(?,?,?,?,?,?)";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getComboId());
            statement.setString(2, t.getName());
            statement.setString(3, t.getDescription());
            statement.setDouble(4, t.getOriginalPrice());
            statement.setDouble(5, t.getDiscountPrice());
            statement.setString(6, t.getStatus());
            int affectedRow = statement.executeUpdate();
            if (affectedRow == 0) {
                throw new SQLException("Creating combo fail ,no rows affected");

            }
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Creating combo fail ,no rows affected");
            }
        } catch (Exception e) {
            System.out.println("Error insert fail" + e.getMessage());
            return -1;
        } finally {
            closeResources();
        }
    }

    @Override
    public Combo getFromResultSet(ResultSet resultSet) throws SQLException {
        Combo combo = new Combo();
        combo.setComboId(resultSet.getInt("ComboId"));
        combo.setName(resultSet.getString("name"));
        combo.setDescription(resultSet.getString("description"));
        combo.setOriginalPrice(resultSet.getDouble("originalPrice"));
        combo.setDiscountPrice(resultSet.getDouble("discountPrice"));
        combo.setStatus(resultSet.getString("status"));
        return combo;
    }

    public Food getFromResultSetFood(ResultSet resultSet) throws SQLException {
        Food food = new Food();
        food.setId(resultSet.getInt("id"));
        food.setName(resultSet.getString("name"));
        food.setDescription(resultSet.getString("description"));
        food.setPrice(resultSet.getDouble("price"));
        food.setImage_url(resultSet.getString("image_url"));
        food.setCalo(resultSet.getDouble("calo"));
        return food;
    }

    @Override
    public Combo findById(Integer id) {
        String sql = "Select *from Combo Where ComboId = ? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (Exception e) {
            System.out.println("Error findById fail" + e.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }
    //get food detai trong combo 

   public List<ComboFoodDetails> getComboFoodDetails(int comboId) {
    List<ComboFoodDetails> foodDetails = new ArrayList<>();
    String sql = "SELECT \n" +
            "    f.id AS food_id, \n" +
            "    f.name AS food_name, \n" +
            "    f.description, \n" +
            "    f.price, \n" +
            "    f.calo, \n" +
            "    f.image_url, \n" +
            "    cf.quanlityInCombo AS quantity_in_combo\n" +
            "FROM ComboFood cf\n" +
            "JOIN Food f ON cf.FoodId = f.id\n" +
            "WHERE cf.ComboId = ?";

    try {
        connection = getConnection();
        statement = connection.prepareStatement(sql);
        statement.setInt(1, comboId);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Food food = new Food();
            food.setId(resultSet.getInt("food_id")); // dùng alias đúng
            food.setName(resultSet.getString("food_name"));
            food.setDescription(resultSet.getString("description"));
            food.setPrice(resultSet.getDouble("price"));
            food.setImage_url(resultSet.getString("image_url"));
            food.setCalo(resultSet.getDouble("calo"));
            //lay so luong quanlityFood 
            int quantity = resultSet.getInt("quantity_in_combo");
            //khởi tạo một đối tượng
            ComboFoodDetails detail = new ComboFoodDetails();
            detail.setFood(food);
            detail.setQuantityInCombo(quantity);
            foodDetails.add(detail);
        }
    } catch (Exception e) {
        System.out.println("Error loading combo food details: " + e.getMessage());
        e.printStackTrace();
    } finally {
        closeResources();
    }

    return foodDetails;
}

}
