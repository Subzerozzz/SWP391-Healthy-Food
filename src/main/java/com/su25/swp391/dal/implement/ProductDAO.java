package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;

public class ProductDAO extends DBContext implements I_DAO<Product> {

  @Override
  public List<Product> findAll() {
    // Chuẩn bị 1 mảng để chứa dữ liệu
    List<Product> list = new ArrayList<>();
    // Chuẩn bị câu lệnh SQL
    String sql = "SELECT * FROM Product";
    try {
      // Chuẩn bị đối tượng statement
      PreparedStatement statement = connection.prepareStatement(sql);
      // Thực thi câu lệnh SQL trả về đối tượng resultSet
      resultSet = statement.executeQuery();
      // Duyệt qua từng bản ghi trong resultSet
      while (resultSet.next()) {
        // Lấy dữ liệu từ resultSet gán vào đối tượng product
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getDouble("price"));
        product.setImage_url(resultSet.getString("image_url"));
        product.setStatus(resultSet.getString("status"));
        product.setCategory_id(resultSet.getInt("category_id"));
        product.setStock(resultSet.getInt("stock"));

        list.add(product);
      }
    } catch (SQLException e) {
      System.out.println("Lỗi truy vấn");
    }
    return list;
  }

  @Override
  public Map<Integer, Product> findAllMap() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public boolean update(Product t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public boolean delete(Product t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public int insert(Product t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public Product getFromResultSet(ResultSet resultSet) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public Product findById(Integer id) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  public static void main(String[] args) {
    ProductDAO productDao = new ProductDAO();
    for (Product a : productDao.findAll()) {
      System.out.println(a);
    }
  }

}
