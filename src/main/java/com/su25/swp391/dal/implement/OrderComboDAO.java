
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.OrderCombo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hang
 */
public class OrderComboDAO extends DBContext implements I_DAO<OrderCombo> {

    @Override
    public List<OrderCombo> findAll() {
        List<OrderCombo> orderCombos = new ArrayList<>();
        String sql = "SELECT * FROM OrderCombo";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderCombos.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("Error finding all order combos: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return orderCombos;

    }

    @Override
    public Map<Integer, OrderCombo> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(OrderCombo orderCombo) {
        String sql = "UPDATE OrderCombo SET order_id = ?,  comboId = ?, comboName = ?, "
                + "discountPrice = ?, quantity = ?, totalPrice = ? user_id = ?"
                + " status=?  WHERE orderComboId = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderCombo.getComboId());
            statement.setString(2, orderCombo.getComboName());
            statement.setDouble(3, orderCombo.getDiscountPrice());
            statement.setInt(4, orderCombo.getQuantity());
            statement.setDouble(5, orderCombo.getTotalPrice());
            statement.setInt(6,orderCombo.getUser_id());
            statement.setString(7, orderCombo.getStatus());
            statement.setInt(8, orderCombo.getOrderComboId());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error updating order combo: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public boolean delete(OrderCombo orderCombo) {
        String sql = "DELETE FROM orderCombo WHERE orderComboId = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderCombo.getOrderComboId());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error deleting order combo: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public int insert(OrderCombo orderCombo) {
        String sql = "INSERT INTO OrderCombo ( comboId, comboName, discountPrice, "
                + "quantity, totalPrice,payment_status,user_id,status) VALUES ( ?, ?, ?, ?, ?,?,?,?)";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, orderCombo.getComboId());
            statement.setString(2, orderCombo.getComboName());
            statement.setDouble(3, orderCombo.getDiscountPrice());
            statement.setInt(4, orderCombo.getQuantity());
            statement.setDouble(5, orderCombo.getTotalPrice());
            statement.setInt(6, orderCombo.getPayment_status());
            statement.setInt(7, orderCombo.getUser_id());
            statement.setString(8, orderCombo.getStatus());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order combo failed, no rows affected.");
            }

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Creating order combo failed, no ID obtained.");
            }
        } catch (SQLException ex) {
            System.out.println("Error inserting order combo: " + ex.getMessage());
            return -1;
        } finally {
            closeResources();
        }
    }

    @Override
    public OrderCombo getFromResultSet(ResultSet rs) throws SQLException {
        OrderCombo orderCombo = new OrderCombo();

        orderCombo.setOrderComboId(rs.getInt("orderComboId"));
        orderCombo.setComboId(rs.getInt("comboId"));
        orderCombo.setComboName(rs.getString("comboName"));
        orderCombo.setDiscountPrice(rs.getDouble("discountPrice"));
        orderCombo.setQuantity(rs.getInt("quantity"));
        orderCombo.setTotalPrice(rs.getDouble("totalPrice"));
        orderCombo.setPayment_status(rs.getInt("payment_status"));
        orderCombo.setUser_id(rs.getInt("user_id"));
        orderCombo.setStatus(rs.getString("status"));
      
        return orderCombo;
    }

    @Override
    public OrderCombo findById(Integer id) {
        String sql = "SELECT * FROM OrderCombo WHERE orderComboId = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            System.out.println("Error finding order combo by ID: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }

    public void updatePaymentStatus(int orderComboId, int status) {
        String sql = "UPDATE OrderCombo SET payment_status = ? WHERE orderComboId = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, status);
            statement.setInt(2, orderComboId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error updating payment status: " + ex.getMessage());
        } finally {
            closeResources();
        }
    }
    
    // Mạnh
    public List<OrderCombo> findOrderCombosByUserIdAndStatusWithPagination(int userId, String status, int page, int pageSize) {
    List<OrderCombo> list = new ArrayList<>();
    boolean filterByStatus = status != null && !status.trim().isEmpty() && !status.equalsIgnoreCase("all");

    String sql = "SELECT * FROM OrderCombo WHERE user_id = ?";
    if (filterByStatus) {
        sql += " AND status = ?";
    }
    sql += " ORDER BY orderComboId DESC LIMIT ? OFFSET ?";

    try {
        int offset = (page - 1) * pageSize;
        connection = getConnection();
        statement = connection.prepareStatement(sql);

        statement.setInt(1, userId);
        int paramIndex = 2;

        if (filterByStatus) {
           statement.setString(paramIndex++, status);
        }

        statement.setInt(paramIndex++, pageSize);
        statement.setInt(paramIndex, offset);

        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            list.add(getFromResultSet(resultSet));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        closeResources();
    }

    return list;
}
    // Mạnh
    public int getTotalOrderComboCountByUserIdAndStatus(int userId, String status) {
    boolean filterByStatus = status != null && !status.trim().isEmpty() && !status.equalsIgnoreCase("all");
    String sql = "SELECT COUNT(*) FROM OrderCombo WHERE user_id = ?";
    if (filterByStatus) {
        sql += " AND status = ?";
    }

    try {
        connection = getConnection();
        statement = connection.prepareStatement(sql);

        statement.setInt(1, userId);
        if (filterByStatus) {
             statement.setString(2, status);
        }

        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        closeResources();
    }

    return 0;
}
   //Mạnh
    public boolean updateOrderComboStatus(int orderComboId, String status) {
    String sql = "UPDATE OrderCombo SET status = ? WHERE orderComboId = ?";
    boolean success = false;

    try {
        connection = getConnection();
        statement = connection.prepareStatement(sql);
        statement.setString(1, status); 
        statement.setInt(2, orderComboId);

        int rows = statement.executeUpdate();
        success = (rows > 0);
    } catch (Exception e) {
        System.out.println("Error updating orderCombo status: " + e.getMessage());
        e.printStackTrace();
    } finally {
        closeResources();
    }

    return success;
}

}