package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.entity.OrderDetails;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author kieud
 */
public class OrderDetailDAO extends DBContext {

    public List<OrderDetails> getOrderDetailByOrderId(int order_id) {
        List<OrderDetails> orderDetails = new ArrayList<>();
        String sql = "SELECT "
                + "                 f.image_url, "
                + "                 oi.order_item_id, "
                + "                 f.name AS food_name, "
                + "                 o.order_id, "
                + "                 f.id, "
                + "                 f.price AS food_price, "
                + "                 oi.quantity, "
                + "                 o.payment_method, "
                + "                 o.status,"
                + "                 o.shipping_address,"
                + "                 o.total,"
                + "                 o.created_at,"
                + "                 o.updated_at"
                + "                 FROM Order_Items oi "
                + "                 JOIN Orders o ON oi.order_id = o.order_id "
                + "                 JOIN Food f ON oi.food_id = f.id "
                + "                 WHERE o.order_id = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, order_id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderDetails od = new OrderDetails();
                od.setImage_url(resultSet.getString("image_url"));
                od.setFoodname(resultSet.getString("food_name"));
                od.setOrderItemId(resultSet.getInt("order_item_id"));
                od.setOrderId(resultSet.getInt("order_id"));
                od.setFoodId(resultSet.getInt("id"));
                od.setQuantity(resultSet.getInt("quantity"));
                od.setPrice(resultSet.getBigDecimal("food_price"));
                od.setCreatedAt(resultSet.getTimestamp("created_at"));
                od.setUpdatedAt(resultSet.getTimestamp("updated_at"));
                od.setShip_address(resultSet.getString("shipping_address"));
                od.setTotal(resultSet.getBigDecimal("total"));
                od.setPaymen_method(resultSet.getString("payment_method"));
                od.setStatus(resultSet.getString("status"));
                orderDetails.add(od);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return orderDetails;
    }

    public BigDecimal calculateSubtotal(List<OrderDetails> orderDetails) {
        BigDecimal subtotal = BigDecimal.ZERO;

        for (OrderDetails od : orderDetails) {
            BigDecimal itemTotal = od.getPrice()
                    .multiply(BigDecimal.valueOf(od.getQuantity()));
            subtotal = subtotal.add(itemTotal); // cộng dồn từng item
        }

        return subtotal;
    }


}
