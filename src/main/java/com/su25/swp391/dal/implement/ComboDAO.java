/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Combo;
import com.su25.swp391.entity.ComboFood;
import com.su25.swp391.entity.ComboFoodDetails;
import com.su25.swp391.entity.Food;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hang
 */
public class ComboDAO extends DBContext implements I_DAO<Combo> {

    private static Object builder() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

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
                + "    comboName = ?,\n"
                + "    description = ?,\n"
                + "    originalPrice = ?,\n"
                + "    discountPrice = ?,\n"
                + "    status = ?\n"
                + "WHERE ComboId = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, combo.getComboName());
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
    String sql = "INSERT INTO Combo (comboName, description, originalPrice, discountPrice, status) VALUES (?, ?, ?, ?, ?)";

    try {
        connection = getConnection();
        statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, t.getComboName());
        statement.setString(2, t.getDescription());
        statement.setDouble(3, t.getOriginalPrice());
        statement.setDouble(4, t.getDiscountPrice());
        statement.setString(5, t.getStatus());

        int affectedRow = statement.executeUpdate();
        if (affectedRow == 0) {
            throw new SQLException("Creating combo failed, no rows affected.");
        }

        resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1); // comboId được sinh ra
        } else {
            throw new SQLException("Creating combo failed, no ID obtained.");
        }
    } catch (Exception e) {
        System.out.println("Error insert fail: " + e.getMessage());
        return -1;
    } finally {
        closeResources();
    }
}
public class TestComboInsert {
    public static void main(String[] args) {
        List<Integer> foodIds = Arrays.asList(1, 2, 4);
        List<Integer> quantities = Arrays.asList(1, 2, 1);

        try {
            // Tính originalPrice từ các món ăn
            FoodDAO foodDAO = new FoodDAO();
            double originalPrice = 0;
            for (int i = 0; i < foodIds.size(); i++) {
                double price = foodDAO.getPriceById(foodIds.get(i)); // Hàm bạn cần tự tạo
                originalPrice += price * quantities.get(i);
            }

            double discountPrice = originalPrice * 0.8; // giảm 20%

            Combo combo = Combo.builder()
                    .comboName("Combo thử nghiệm")
                    .description("Gồm 3 món ăn phổ biến")
                    .originalPrice(originalPrice)
                    .discountPrice(discountPrice)
                    .status("active")
                    .build();

            ComboDAO comboDAO = new ComboDAO();
            int comboId = comboDAO.insert(combo);

            if (comboId > 0) {
                System.out.println("✅ Combo inserted with ID: " + comboId);

                ComboFoodDAO comboFoodDAO = new ComboFoodDAO();
                for (int i = 0; i < foodIds.size(); i++) {
                    ComboFood cf = ComboFood.builder()
                            .comboId(comboId)
                            .foodId(foodIds.get(i))
                            .quantityInCombo(quantities.get(i))
                            .build();

                    comboFoodDAO.insert(cf);
                }

                System.out.println("✅ ComboFood inserted.");
            } else {
                System.out.println("❌ Insert combo failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    @Override
    public Combo getFromResultSet(ResultSet resultSet) throws SQLException {
        Combo combo = new Combo();
        combo.setComboId(resultSet.getInt("comboId"));
        combo.setComboName(resultSet.getString("comboName"));
        combo.setDescription(resultSet.getString("description"));
        combo.setOriginalPrice(resultSet.getDouble("originalPrice"));
        combo.setDiscountPrice(resultSet.getDouble("discountPrice"));
        combo.setStatus(resultSet.getString("status"));
        return combo;
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
            "    cf.quantityInCombo AS quantity_in_combo\n" +
            "FROM ComboFood cf\n" +
            "JOIN Food f ON cf.foodId = f.id\n" +
            "WHERE cf.comboId = ?";

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
        public boolean activatCombo(int comboId) {
        String sql = "UPDATE Combo SET status = 'active' WHERE comboId=?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, comboId);
            int affectedRow = statement.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            System.out.println("Error activating combo:" + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }
        
public boolean deactivateAccount(int comboId) {
        String sql = "UPDATE Combo SET status = 'inactive' WHERE comboId=?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, comboId);
            int affectedRow = statement.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            System.out.println("Error inactive combo:" + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }
}
