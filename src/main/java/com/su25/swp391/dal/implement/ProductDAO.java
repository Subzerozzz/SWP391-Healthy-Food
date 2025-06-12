//package com.su25.swp391.dal.implement;
//
//import com.su25.swp391.dal.DBContext;
//import com.su25.swp391.dal.I_DAO;
//import com.su25.swp391.entity.Product;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.HashMap;
//
//public class ProductDAO extends DBContext implements I_DAO<Product> {
//
//    @Override
//    public Map<Integer, Product> findAllMap() {
//        // Implement the logic to fetch all products and store them in a map
//        // where the key is the product ID and the value is the Product object.
//        Map<Integer, Product> productMap = new HashMap<>();
//        String sql = "SELECT * FROM products";
//        try {
//            connection = getConnection();
//            statement = connection.prepareStatement(sql);
//            resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                Product product = getFromResultSet(resultSet);
//                productMap.put(product.getProductId(), product);
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error finding all products: " + ex.getMessage());
//        } finally {
//            closeResources();
//        }
//        return productMap;
//    }
//
//    @Override
//    public boolean delete(Product product) {
//        throw new UnsupportedOperationException("Delete operation is not supported for products");
//    }
//
//// 
//
//    @Override
//    public List<Product> findAll() {
//        List<Product> products = new ArrayList<>();
//        String sql = "SELECT * FROM products";
//        try {
//            connection = getConnection();
//            statement = connection.prepareStatement(sql);
//            resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                products.add(getFromResultSet(resultSet));
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error finding all products: " + ex.getMessage());
//        } finally {
//            closeResources();
//        }
//        return products;
//    }
//
//    @Override
//    public boolean update(Product product) {
//        String sql = "UPDATE products SET name = ?, description = ?, price = ?, image = ?, category_id = ?, stock = ?, status = ?, seller_id = ? WHERE product_id = ?";
//        try {
//            connection = getConnection();
//            statement = connection.prepareStatement(sql);
//            statement.setString(1, product.getName());
//            statement.setString(2, product.getDescription());
//            statement.setBigDecimal(3, product.getPrice());
//            statement.setString(4, product.getImage());
//            statement.setInt(5, product.getCategoryId());
//            statement.setDouble(6, product.getStock());
//            statement.setBoolean(7, product.getStatus());
//	    statement.setInt(8, product.getSellerId());
//            statement.setInt(9, product.getProductId());
//            int affectedRows = statement.executeUpdate();
//            return affectedRows > 0;
//        } catch (SQLException ex) {
//            System.out.println("Error updating product: " + ex.getMessage());
//            return false;
//        } finally {
//            closeResources();
//        }
//    }
//
//    @Override
//    public int insert(Product product) {
//        String sql = "INSERT INTO products (name, description, price, image, category_id, stock, status, seller_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        try {
//            connection = getConnection();
//            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            statement.setString(1, pr   @Override
////    public Product getFromResultSet(ResultSet rs) throws SQLException {
////        Product product = new Product();
////        product.setProductId(rs.getInt("product_id"));
////        product.setName(rs.getString("name"));
////        product.setDescription(rs.getString("description"));
////        product.setPrice(rs.getBigDecimal("price"));
////        product.setImage(rs.getString("image"));
////        product.setCategoryId(rs.getInt("category_id"));
////        product.setStock(rs.getDouble("stock"));
////        product.setCreatedAt(rs.getTimestamp("created_at"));
////        product.setUpdatedAt(rs.getTimestamp("updated_at"));
////        product.setStatus(rs.getBoolean("status"));
////	product.setSellerId(rs.getInt("seller_id"));
////        return product;
////    }oduct.getName());
//            statement.setString(2, product.getDescription());
//            statement.setBigDecimal(3, product.getPrice());
//            statement.setString(4, product.getImage());
//            statement.setInt(5, product.getCategoryId());
//            statement.setDouble(6, product.getStock());
//            statement.setBoolean(7, product.getStatus());
//	    statement.setInt(8, product.getSellerId());
//            int affectedRows = statement.executeUpdate();
//            if (affectedRows == 0) {
//                throw new SQLException("Creating product failed, no rows affected.");
//            }
//            resultSet = statement.getGeneratedKeys();
//            if (resultSet.next()) {
//                return resultSet.getInt(1);
//            } else {
//                throw new SQLException("Creating product failed, no ID obtained.");
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error inserting product: " + ex.getMessage());
//            return -1;
//        } finally {
//            closeResources();
//        }
//    }
//
//    @Override
//    public Product findById(Integer id) {
//        String sql = "SELECT * FROM products WHERE product_id = ?";
//        try {
//            connection = getConnection();
//            statement = connection.prepareStatement(sql);
//            statement.setInt(1, id);
//            resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return getFromResultSet(resultSet);
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error finding product by ID: " + ex.getMessage());
//        } finally {
//            closeResources();
//        }
//        return null;
//    }
//    
//    public Product getProductById(int productId) {
//        String sql = "SELECT * FROM products WHERE product_id = ?";
//        try {
//            connection = getConnection();
//            statement = connection.prepareStatement(sql);
//            statement.setInt(1, productId);
//            resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return getFromResultSet(resultSet);
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error getting product by ID: " + ex.getMessage());
//        } finally {
//            closeResources();
//        }
//        return null;
//    }
//}
