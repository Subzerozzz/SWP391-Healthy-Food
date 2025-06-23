package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.OrderItem;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kieud
 */
public class OrderItemDAO extends DBContext implements I_DAO<OrderItem>{

    public List<OrderItem> getOrderDetailByOrderId(int order_id) {
        List<OrderItem> orderDetails = new ArrayList<>();
        String sql = "SELECT "
                + "oi.order_item_id, "
                + "oi.order_id, "
                + "oi.food_id, "
                + "oi.quantity, "
                + "oi.price, "
                + "oi.created_at, "
                + "oi.updated_at "
                + "FROM OrderItem oi "
                + "WHERE oi.order_id = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, order_id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderItem od = new OrderItem();
                od.setOrder_item_id(resultSet.getInt("order_item_id"));
                od.setOrder_id(resultSet.getInt("order_id"));
                od.setFood_id(resultSet.getInt("food_id"));
                od.setQuantity(resultSet.getInt("quantity"));
                od.setPrice(resultSet.getBigDecimal("price"));
                od.setCreated_at(resultSet.getTimestamp("created_at"));
                od.setUpdated_at(resultSet.getTimestamp("updated_at"));
                orderDetails.add(od);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return orderDetails;
    }

    public BigDecimal calculateSubtotal(List<OrderItem> orderDetails) {
        BigDecimal subtotal = BigDecimal.ZERO;

        for (OrderItem od : orderDetails) {
            BigDecimal itemTotal = od.getPrice()
                    .multiply(BigDecimal.valueOf(od.getQuantity()));
            subtotal = subtotal.add(itemTotal); // cộng dồn từng item
        }

        return subtotal;
    }

    @Override
    public List<OrderItem> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<Integer, OrderItem> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(OrderItem t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(OrderItem t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(OrderItem t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public OrderItem getFromResultSet(ResultSet resultSet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public OrderItem findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
 

}
