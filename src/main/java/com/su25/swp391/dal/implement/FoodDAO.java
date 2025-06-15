package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.dal.impl.CategoryFoodDAO;
import com.su25.swp391.entity.Category;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.CategoryFood;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Connection;

public class FoodDAO extends DBContext implements I_DAO<Food> {
    CategoryFoodDAO categoryfoodDAO = new CategoryFoodDAO();

    @Override
    public List<Food> findAll() {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM foods";

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                foods.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Error when finding all foods: " + e.getMessage());
        } finally {
            closeResources();
        }

        return foods;
    }

    @Override
    public boolean update(Food food) {
        String sql = "UPDATE foods SET name = ?, description = ?, "
                + "price = ?, stock = ?, image = ?, status = ?, updated_at = ? "
                + "WHERE id = ?";

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, food.getName());
            statement.setString(2, food.getDescription());
            statement.setBigDecimal(3, food.getPrice());
            statement.setString(5, food.getImage_url());
            statement.setByte(6, food.getStatus());
            statement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(8, food.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error when updating food: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public boolean delete(Food food) {
        String sql = "DELETE FROM foods WHERE id = ?";

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, food.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error when deleting food: " + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public int insert(Food food) {
        String sql = "INSERT INTO foods (name, description, price, stock, "
                + "image, status, created_at, updated_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println("Inserting food: " + food.getName());

            statement.setString(1, food.getName());
            statement.setString(2, food.getDescription());
            statement.setBigDecimal(3, food.getPrice());
            statement.setString(5, food.getImage_url());
            statement.setByte(6, food.getStatus());
            statement.setTimestamp(7, food.getCreatedAt());
            statement.setTimestamp(8, food.getUpdatedAt());

            int affectedRows = statement.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);

            if (affectedRows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int foodId = generatedKeys.getInt(1);
                    System.out.println("Generated food ID: " + foodId);
                    return foodId;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error when inserting food: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return 0;
    }

    @Override
    public Food getFromResultSet(ResultSet resultSet) throws SQLException {
        Food food = new Food();

        food.setId(resultSet.getInt("id"));
        food.setName(resultSet.getString("name"));
        food.setDescription(resultSet.getString("description"));
        food.setPrice(resultSet.getBigDecimal("price"));
        food.setImage_url(resultSet.getString("image"));
        food.setStatus(resultSet.getByte("status"));

        Timestamp createdAt = resultSet.getTimestamp("created_at");
        if (createdAt != null) {
            food.setCreatedAt((createdAt));
        }

        Timestamp updatedAt = resultSet.getTimestamp("updated_at");
        if (updatedAt != null) {
            food.setUpdatedAt(updatedAt);
        }

        return food;
    }

    /**
     * Find all active foods
     * 
     * @return List of active foods
     */
    public List<Food> findAllActive() {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM foods WHERE status = 1";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                foods.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("Error finding active foods: " + ex.getMessage());
        } finally {
            closeResources();
        }

        return foods;
    }


    /**
     * Tìm sản phẩm với các bộ lọc và phân trang
     */
    public List<Food> findFoodsWithFilter(String searchFilter, Byte statusByte, Integer categoryId, int page,
            int pageSize) {
        List<Food> foods = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder(
                "SELECT p.* FROM foods p JOIN category_food cp ON p.id = cp.id "
                + "JOIN categories c ON cp.category_id = c.category_id WHERE c.status = 1");
        List<Object> parameters = new ArrayList<>();

        // Thêm điều kiện tìm kiếm theo tên sản phẩm
        if (searchFilter != null && !searchFilter.isEmpty()) {
            sqlBuilder.append(" AND p.name LIKE ?");
            parameters.add("%" + searchFilter + "%");
        }

        // Thêm điều kiện lọc theo trạng thái
        if (statusByte != null) {
            sqlBuilder.append(" AND p.status = ?");
            parameters.add(statusByte);
        }

        // Thêm điều kiện lọc theo danh mục
        if (categoryId != null) {
            sqlBuilder.append(" AND cp.category_id = ?");
            parameters.add(categoryId);
        }

        // Thêm phân trang
        sqlBuilder.append(" ORDER BY p.id DESC LIMIT ? OFFSET ?");
        parameters.add(pageSize);
        parameters.add((page - 1) * pageSize);

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString());
            // Thiết lập các tham số
            for (int i = 0; i < parameters.size(); i++) {
                Object param = parameters.get(i);
                if (param instanceof String) {
                    statement.setString(i + 1, (String) param);
                } else if (param instanceof Integer) {
                    statement.setInt(i + 1, (Integer) param);
                } else if (param instanceof Byte) {
                    statement.setByte(i + 1, (Byte) param);
                }
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                foods.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Error when finding foods with filter: " + e.getMessage());
        } finally {
            closeResources();
        }

        return foods;
    }

    /**
     * Đếm tổng số sản phẩm theo bộ lọc
     */
    public int countFoodsWithFilter(String searchFilter, Byte statusByte, Integer categoryId) {
        StringBuilder sqlBuilder = new StringBuilder(
                "SELECT COUNT(*) FROM foods p JOIN category_food cp ON p.id = cp.id "
                + "JOIN categories c ON cp.category_id = c.category_id WHERE c.status = 1");
        List<Object> parameters = new ArrayList<>();

        // Thêm điều kiện tìm kiếm theo tên sản phẩm
        if (searchFilter != null && !searchFilter.isEmpty()) {
            sqlBuilder.append(" AND p.name LIKE ?");
            parameters.add("%" + searchFilter + "%");
        }

        // Thêm điều kiện lọc theo trạng thái
        if (statusByte != null) {
            sqlBuilder.append(" AND p.status = ?");
            parameters.add(statusByte);
        }

        // Thêm điều kiện lọc theo danh mục
        if (categoryId != null) {
            sqlBuilder.append(" AND cp.category_id = ?");
            parameters.add(categoryId);
        }

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString());
            // Thiết lập các tham số
            for (int i = 0; i < parameters.size(); i++) {
                Object param = parameters.get(i);
                if (param instanceof String) {
                    statement.setString(i + 1, (String) param);
                } else if (param instanceof Integer) {
                    statement.setInt(i + 1, (Integer) param);
                } else if (param instanceof Byte) {
                    statement.setByte(i + 1, (Byte) param);
                }
            }

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error when counting foods with filter: " + e.getMessage());
        } finally {
            closeResources();
        }

        return 0;
    }

    /**
     * Tìm sản phẩm với phân trang
     */
    public List<Food> findFoodsWithPagination(int page, int pageSize) {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT p.* FROM foods p "
                + "JOIN category_food cp ON p.id = cp.id "
                + "JOIN categories c ON cp.category_id = c.category_id "
                + "WHERE c.status = 1 ORDER BY p.id DESC LIMIT ? OFFSET ?";

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pageSize);
            statement.setInt(2, (page - 1) * pageSize);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                foods.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Error when finding foods with pagination: " + e.getMessage());
        } finally {
            closeResources();
        }

        return foods;
    }

    /**
     * Đếm tổng số sản phẩm
     */
    public int getTotalFoods() {
        String sql = "SELECT COUNT(*) FROM foods p "
                + "JOIN category_food cp ON p.id = cp.id "
                + "JOIN categories c ON cp.category_id = c.category_id "
                + "WHERE c.status = 1";

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error when getting total foods: " + e.getMessage());
        } finally {
            closeResources();
        }

        return 0;
    }

    /**
     * Tìm sản phẩm theo ID
     */
    public Food findById(int foodId) {
        String sql = "SELECT * FROM foods WHERE id = ?";

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, foodId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Error when finding food by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return null;
    }

    public boolean updateStatus(int foodId, byte status) {
        String sql = "UPDATE foods SET status = ?, updated_at = ? WHERE id = ?";

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setByte(1, status);
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setInt(3, foodId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error when updating food status: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }

    public double getMinPrice() {
        double minPrice = 0;
        try {
            connection = getConnection();
            String sql = "SELECT MIN(price) FROM foods WHERE status = 1";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                minPrice = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return minPrice;
    }

    public double getMaxPrice() {
        double maxPrice = 0;
        try {
            connection = getConnection();
            String sql = "SELECT MAX(price) FROM foods WHERE status = 1";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                maxPrice = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return maxPrice;
    }

    /**
     * Tìm kiếm sản phẩm với các bộ lọc
     *
     * @param searchKeyword Từ khóa tìm kiếm
     * @param selectedCategoryIds Danh sách ID danh mục được chọn
     * @param minPrice Giá tối thiểu
     * @param maxPrice Giá tối đa
     * @param sortParam Tham số sắp xếp
     * @param currentPage Trang hiện tại
     * @param pageSize Số sản phẩm trên mỗi trang
     * @return Danh sách sản phẩm thỏa mãn điều kiện
     */
    public List<Food> findFoodsWithFilters(
            String searchKeyword, List<Integer> categoryIds, Double minPrice, Double maxPrice,
            String sortType, int page, int pageSize) {

        List<Food> foods = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder();

        // Base query with JOIN to handle category filtering
        sqlBuilder.append("SELECT DISTINCT p.* FROM foods p ");

        // Only add the JOIN if we have category filters
        if (categoryIds != null && !categoryIds.isEmpty()) {
            sqlBuilder.append("JOIN category_food cp ON p.id = cp.id ");
        }

        sqlBuilder.append("WHERE p.status = 1 ");

        // Add search condition
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sqlBuilder.append("AND p.name LIKE ? ");
        }

        // Add category filter
        if (categoryIds != null && !categoryIds.isEmpty()) {
            sqlBuilder.append("AND cp.category_id IN (");
            for (int i = 0; i < categoryIds.size(); i++) {
                if (i > 0) {
                    sqlBuilder.append(",");
                }
                sqlBuilder.append("?");
            }
            sqlBuilder.append(") ");
        }

        // Add price range filter
        if (minPrice != null) {
            sqlBuilder.append("AND p.price >= ? ");
        }
        if (maxPrice != null) {
            sqlBuilder.append("AND p.price <= ? ");
        }

        // Add sorting
        if (sortType != null) {
            switch (sortType) {
                case "name_asc":
                    sqlBuilder.append("ORDER BY p.name ASC ");
                    break;
                case "name_desc":
                    sqlBuilder.append("ORDER BY p.name DESC ");
                    break;
                case "price_asc":
                    sqlBuilder.append("ORDER BY p.price ASC ");
                    break;
                case "price_desc":
                    sqlBuilder.append("ORDER BY p.price DESC ");
                    break;
                default:
                    sqlBuilder.append("ORDER BY p.id DESC ");
                    break;
            }
        } else {
            sqlBuilder.append("ORDER BY p.id DESC ");
        }

        // Add pagination
        sqlBuilder.append("LIMIT ? OFFSET ?");

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sqlBuilder.toString());

            int paramIndex = 1;

            // Set search parameter
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                statement.setString(paramIndex++, "%" + searchKeyword + "%");
            }

            // Set category parameters
            if (categoryIds != null && !categoryIds.isEmpty()) {
                for (Integer categoryId : categoryIds) {
                    statement.setInt(paramIndex++, categoryId);
                }
            }

            // Set price parameters
            if (minPrice != null) {
                statement.setDouble(paramIndex++, minPrice);
            }
            if (maxPrice != null) {
                statement.setDouble(paramIndex++, maxPrice);
            }

            // Set pagination parameters
            statement.setInt(paramIndex++, pageSize);
            statement.setInt(paramIndex++, (page - 1) * pageSize);

            resultSet = statement.executeQuery();

            // Process results
            while (resultSet.next()) {
                Food food = getFromResultSet(resultSet);

                // Load categories for this food
                CategoryFoodDAO categoryFoodDAO = new CategoryFoodDAO();
                List<Category> categories = categoryFoodDAO.getCategoriesByFoodId(food.getId());
                food.setCategories(categories);

                foods.add(food);
            }
        } catch (SQLException e) {
            System.out.println("Error finding foods with filters: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return foods;
    }

    /**
     * Đếm số lượng sản phẩm thỏa mãn điều kiện lọc
     *
     * @param searchKeyword Từ khóa tìm kiếm
     * @param selectedCategoryIds Danh sách ID danh mục được chọn
     * @param minPrice Giá tối thiểu
     * @param maxPrice Giá tối đa
     * @return Số lượng sản phẩm
     */
    public int countFoodsWithFilters(String searchKeyword, List<Integer> categoryIds, Double minPrice, Double maxPrice) {
        StringBuilder sqlBuilder = new StringBuilder();

        // Base query with JOIN to handle category filtering
        sqlBuilder.append("SELECT COUNT(DISTINCT p.id) FROM foods p ");

        // Only add the JOIN if we have category filters
        if (categoryIds != null && !categoryIds.isEmpty()) {
            sqlBuilder.append("JOIN category_food cp ON p.id = cp.id ");
        }

        sqlBuilder.append("WHERE p.status = 1 ");

        // Add search condition
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sqlBuilder.append("AND p.name LIKE ? ");
        }

        // Add category filter
        if (categoryIds != null && !categoryIds.isEmpty()) {
            sqlBuilder.append("AND cp.category_id IN (");
            for (int i = 0; i < categoryIds.size(); i++) {
                if (i > 0) {
                    sqlBuilder.append(",");
                }
                sqlBuilder.append("?");
            }
            sqlBuilder.append(") ");
        }

        // Add price range filter
        if (minPrice != null) {
            sqlBuilder.append("AND p.price >= ? ");
        }
        if (maxPrice != null) {
            sqlBuilder.append("AND p.price <= ? ");
        }

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sqlBuilder.toString());

            int paramIndex = 1;

            // Set search parameter
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                statement.setString(paramIndex++, "%" + searchKeyword + "%");
            }

            // Set category parameters
            if (categoryIds != null && !categoryIds.isEmpty()) {
                for (Integer categoryId : categoryIds) {
                    statement.setInt(paramIndex++, categoryId);
                }
            }

            // Set price parameters
            if (minPrice != null) {
                statement.setDouble(paramIndex++, minPrice);
            }
            if (maxPrice != null) {
                statement.setDouble(paramIndex++, maxPrice);
            }

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error counting foods with filters: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return 0;
    }

    /**
     * Tìm kiếm các sản phẩm có đánh giá cao nhất
     *
     * @param limit Số lượng sản phẩm cần lấy
     * @return Danh sách sản phẩm có đánh giá cao
     */
    public List<Food> findTopRatedFoods(int limit) {
        List<Food> foods = new ArrayList<>();

        try {
            connection = getConnection();

            // Thay vì sử dụng bảng feedbacks, chúng ta sẽ lấy các sản phẩm mới nhất
            String sql = "SELECT * FROM foods WHERE status = 1 ORDER BY created_at DESC LIMIT ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, limit);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Food food = getFromResultSet(resultSet);

                foods.add(food);
            }
        } catch (SQLException e) {
            System.out.println("Error finding top foods: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return foods;
    }

    /**
     * Tìm các sản phẩm liên quan (cùng danh mục) với sản phẩm hiện tại
     *
     * @param categoryId ID danh mục
     * @param currentFoodId ID sản phẩm hiện tại (để loại trừ)
     * @param limit Số lượng sản phẩm muốn lấy
     * @return Danh sách sản phẩm liên quan
     */
    public List<Food> findRelatedFoods(int categoryId, int currentFoodId, int limit) {
        List<Food> relatedFoods = new ArrayList<>();

        try {
            String sql = "SELECT DISTINCT p.* FROM foods p "
                    + "JOIN category_food cp ON p.id = cp.id "
                    + "WHERE cp.category_id = ? AND p.id != ? AND p.status = 1 "
                    + "ORDER BY RAND() LIMIT ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            statement.setInt(2, currentFoodId);
            statement.setInt(3, limit);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Food food = new Food();
                food.setId(rs.getInt("id"));
                food.setName(rs.getString("name"));
                food.setDescription(rs.getString("description"));
                food.setPrice(rs.getBigDecimal("price"));
                food.setImage_url(rs.getString("image"));
                food.setStatus(rs.getByte("status"));
                food.setCreatedAt(rs.getTimestamp("created_at"));
                food.setUpdatedAt(rs.getTimestamp("updated_at"));

                // Lấy danh sách categories cho food
                CategoryFoodDAO categoryFoodDAO = new CategoryFoodDAO();
                food.setCategories(categoryFoodDAO.getCategoriesByFoodId(food.getId()));

                relatedFoods.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relatedFoods;
    }

    /**
     * Lấy thông tin sản phẩm theo ID
     *
     * @param foodId ID của sản phẩm cần lấy
     * @return Đối tượng Food nếu tìm thấy, null nếu không tìm thấy
     */
    public Food getFoodById(int foodId) {
        Food food = null;

        try {
            connection = getConnection();
            String sql = "SELECT * FROM foods WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, foodId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                food = new Food();
                food.setId(resultSet.getInt("id"));
                food.setName(resultSet.getString("name"));
                food.setDescription(resultSet.getString("description"));
                food.setPrice(new java.math.BigDecimal(resultSet.getDouble("price")));
                food.setImage_url(resultSet.getString("image"));
                food.setStatus(resultSet.getByte("status"));
                food.setCreatedAt(resultSet.getTimestamp("created_at"));
                food.setUpdatedAt(resultSet.getTimestamp("updated_at"));

                // Lấy danh sách categories cho food
                CategoryFoodDAO categoryFoodDAO = new CategoryFoodDAO();
                food.setCategories(categoryFoodDAO.getCategoriesByFoodId(food.getId()));
            }
        } catch (SQLException e) {
            System.out.println("Error getting food by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return food;
    }

    /**
     * Tìm sản phẩm theo tên
     *
     * @param foodName Tên sản phẩm cần tìm
     * @return Đối tượng Food nếu tìm thấy, null nếu không tìm thấy
     */
    public Food findByName(String foodName) {
        Food food = null;

        try {
            connection = getConnection();
            String sql = "SELECT * FROM foods WHERE name = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, foodName);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                food = new Food();
                food.setId(resultSet.getInt("id"));
                food.setName(resultSet.getString("name"));
                food.setDescription(resultSet.getString("description"));
                food.setPrice(new java.math.BigDecimal(resultSet.getDouble("price")));
                food.setImage_url(resultSet.getString("image"));
                food.setStatus(resultSet.getByte("status"));
                food.setCreatedAt(resultSet.getTimestamp("created_at"));
                food.setUpdatedAt(resultSet.getTimestamp("updated_at"));

                // Lấy danh sách categories cho food
                CategoryFoodDAO categoryFoodDAO = new CategoryFoodDAO();
                food.setCategories(categoryFoodDAO.getCategoriesByFoodId(food.getId()));
            }
        } catch (SQLException e) {
            System.out.println("Error finding food by name: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return food;
    }
}