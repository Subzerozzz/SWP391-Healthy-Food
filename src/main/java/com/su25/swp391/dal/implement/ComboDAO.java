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

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
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

    /**
     *
     * @param page
     * @param pageSize
     * @return
     */
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

    /**
     *
     * @return
     */
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
                + "WHERE comboId = ?";
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
            System.out.println("Error updating combo" + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    /**
     *
     */
    public class ComboDAOTest {

        /**
         *
         * @param args
         */
        public static void main(String[] args) {
            ComboDAO dao = new ComboDAO();

            // Giả lập combo có ID = 1 trong DB
            Combo combo = new Combo();
            combo.setComboId(1); // comboId phải tồn tại trong DB
            combo.setComboName("Combo Test Update");
            combo.setDescription("Updated description");
            combo.setOriginalPrice(50000.0);
            combo.setDiscountPrice(45000.0);
            combo.setStatus("active"); // phải đúng ENUM trong DB

            boolean result = dao.update(combo);
            if (result) {
                System.out.println("✅ Update successful");
            } else {
                System.out.println("❌ Update failed");
            }
        }
    }

    /**
     *
     * @param t
     * @return
     */
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

    /**
     *
     * @param t
     * @return
     */
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

    /**
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
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

    /**
     *
     * @param id
     * @return
     */
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

    /**
     *
     * @param comboId
     * @return
     */
    public List<ComboFoodDetails> getComboFoodDetails(int comboId) {
        List<ComboFoodDetails> foodDetails = new ArrayList<>();
        String sql = "SELECT \n"
                + "    f.id AS food_id, \n"
                + "    f.name AS food_name, \n"
                + "    f.description, \n"
                + "    f.price, \n"
                + "    f.calo, \n"
                + "    f.image_url, \n"
                + "    cf.quantityInCombo AS quantity_in_combo\n"
                + "FROM ComboFood cf\n"
                + "JOIN Food f ON cf.foodId = f.id\n"
                + "WHERE cf.comboId = ?";

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

    /**
     *
     * @param comboId
     * @return
     */
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

    public List<Combo> searchCombos(String searchTerm, int page, int pageSize) {
        List<Combo> combos = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Combo WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // Thêm điều kiện tìm kiếm
        if (searchTerm != null && !searchTerm.isEmpty()) {
            sql.append(" AND (comboName LIKE ? OR description LIKE ?)");
            params.add("%" + searchTerm + "%");
            params.add("%" + searchTerm + "%");
        }

        // Thêm phân trang
        sql.append(" ORDER BY comboId DESC LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add((page - 1) * pageSize);

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            // Thiết lập tham số
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                combos.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("Error searching combos: " + ex.getMessage());
        } finally {
            closeResources();
        }

        return combos;
    }

    public static void main(String[] args) {
        ComboDAO dao = new ComboDAO();
        List<Combo> result = dao.searchCombos("combo", 1, 10);
        for (Combo c : result) {
            System.out.println(c.getComboId() + " - " + c.getComboName());
        }
    }

    /**
     * Đếm tổng số combo thỏa mãn điều kiện tìm kiếm
     *
     * @param searchTerm Từ khóa tìm kiếm (tên, mô tả)
     * @param status Trạng thái combo (active/inactive)
     * @return Tổng số combo thỏa mãn điều kiện
     */
    public int countSearchResults(String searchTerm) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Combo WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // Thêm điều kiện tìm kiếm
        if (searchTerm != null && !searchTerm.isEmpty()) {
            sql.append(" AND (comboName LIKE ? OR description LIKE ?)");
            params.add("%" + searchTerm + "%");
            params.add("%" + searchTerm + "%");
        }

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            // Thiết lập tham số
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error counting search results: " + ex.getMessage());
        } finally {
            closeResources();
        }

        return 0;
    }
    //lay ra nhung combo co trnag thai active
        public List<Combo> findAllComboActive() {
        String sql = "select * from Combo  where status = 'active'";
        List<Combo> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error happen in ComboDAO:" + e.getMessage());
        } finally {
            closeResources();
        }
        return list;

    }

    public List<Combo> filterComboPaging(String status, int pageIndex, int pageSize) {
        List<Combo> combos = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Combo WHERE 1=1 ");

        if (status != null) {
            sql.append(" AND status = ?");
        }

        sql.append(" ORDER BY comboId");
        sql.append(" LIMIT ? OFFSET ?");

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
            int paramIndex = 1;

            // Thêm tham số lọc
            if (status != null) {
                statement.setString(paramIndex++, status);
            }

            // Thêm tham số phân trang
            statement.setInt(paramIndex++, pageSize); // LIMIT
            statement.setInt(paramIndex++, (pageIndex - 1) * pageSize); // OFFSET

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                combos.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error filtering and paging combos: " + e.getMessage());
        } finally {
            closeResources();
        }

        return combos;
    }

    public int countByStatus(String status) {
        int count = 0;
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Combo WHERE 1=1");

        // Thêm điều kiện lọc nếu có
        if (status != null && !status.isEmpty()) {
            sql.append(" AND status = ?");
        }

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            // Thiết lập tham số nếu có
            if (status != null && !status.isEmpty()) {
                statement.setString(1, status);
            }

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error counting accounts by status: " + e.getMessage());
        } finally {
            closeResources();
        }

        return count;
    }

}
