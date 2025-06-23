package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderDAO extends DBContext implements I_DAO<Order>{

    public List<Order> getOrderListByUserId(int userId) {
        List<Order> orderLists = new ArrayList<>();
        Set<Integer> seenOrderIds = new HashSet<>();

        String sql = "SELECT "
                + "    o.order_id, o.user_id, o.status, o.total, o.payment_method, "
                + "    o.created_at, o.updated_at, o.coupon_code, o.discount_amount, "
                + "    o.full_name, o.mobile, o.address, o.email, o.shipping_address "
                + "FROM `Order` o "
                + "WHERE o.user_id = ?;";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                if (!seenOrderIds.contains(orderId)) {
                    seenOrderIds.add(orderId);

                    Order order = new Order();
                    order.setOrder_id(orderId);
                    order.setUser_id(resultSet.getInt("user_id"));
                    order.setStatus(resultSet.getString("status"));
                    order.setTotal(resultSet.getBigDecimal("total"));
                    order.setPayment_method(resultSet.getString("payment_method"));
                    order.setCreated_at(resultSet.getTimestamp("created_at"));
                    order.setUpdate_at(resultSet.getTimestamp("updated_at"));
                    order.setCoupon_code(resultSet.getString("coupon_code"));
                    order.setDiscount_amount(resultSet.getBigDecimal("discount_amount"));
                    order.setFull_name(resultSet.getString("full_name"));
                    order.setMobile(resultSet.getString("mobile"));
                    order.setAddress(resultSet.getString("address"));
                    order.setEmail(resultSet.getString("email"));
                    order.setShipping_address(resultSet.getString("shipping_address"));

                    orderLists.add(order);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return orderLists;
    }

    public List<Order> searchOrderListByUserIdAndStatus(int userId, String status) {
        List<Order> orderLists = new ArrayList<>();
        String sql = "SELECT "
                + "o.order_id, "
                + "o.user_id, "
                + "o.status, "
                + "o.total, "
                + "o.payment_method, "
                + "o.created_at, "
                + "o.updated_at, "
                + "o.coupon_code, "
                + "o.discount_amount, "
                + "o.full_name, "
                + "o.mobile, "
                + "o.address, "
                + "o.email, "
                + "o.shipping_address "
                + "FROM `Order` o "
                + "WHERE o.user_id = ? AND o.status = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setString(2, status);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order ol = new Order();
                ol.setOrder_id(resultSet.getInt("order_id"));
                ol.setUser_id(resultSet.getInt("user_id"));
                ol.setStatus(resultSet.getString("status"));
                ol.setTotal(resultSet.getBigDecimal("total"));
                ol.setPayment_method(resultSet.getString("payment_method"));
                ol.setCreated_at(resultSet.getTimestamp("created_at"));
                ol.setUpdate_at(resultSet.getTimestamp("updated_at"));
                ol.setCoupon_code(resultSet.getString("coupon_code"));
                ol.setDiscount_amount(resultSet.getBigDecimal("discount_amount"));
                ol.setFull_name(resultSet.getString("full_name"));
                ol.setMobile(resultSet.getString("mobile"));
                ol.setAddress(resultSet.getString("address"));
                ol.setEmail(resultSet.getString("email"));
                ol.setShipping_address(resultSet.getString("shipping_address"));

                orderLists.add(ol);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return orderLists;
    }

    public Order getFromResultSet(ResultSet resultSet) throws SQLException {
        return Order.builder()
                .order_id(resultSet.getInt("order_id"))
                .user_id(resultSet.getInt("user_id"))
                .status(resultSet.getString("status"))
                .total(resultSet.getBigDecimal("total"))
                .payment_method(resultSet.getString("payment_method"))
                .created_at(resultSet.getTimestamp("created_at"))
                .update_at(resultSet.getTimestamp("updated_at"))
                .coupon_code(resultSet.getString("coupon_code"))
                .discount_amount(resultSet.getBigDecimal("discount_amount"))
                .full_name(resultSet.getString("full_name"))
                .mobile(resultSet.getString("mobile"))
                .address(resultSet.getString("address"))
                .email(resultSet.getString("email"))
                .shipping_address(resultSet.getString("shipping_address"))
                .build();
    }

    public int getTotalOrderCountByUserId(int userId) {
        String sql = "SELECT COUNT(*) FROM `Order` WHERE user_id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);  
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            
        }
        return 0;
    }
        

    public List<Order> findOrdersByUserIdWithPagination(int userId, int page, int pageSize) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM `Order` WHERE user_id = ? ORDER BY order_id DESC LIMIT ? OFFSET ?";
        try {   
              int offset =(page-1) * pageSize;
              connection = getConnection();
              statement = connection.prepareStatement(sql);
              statement.setInt(1,userId);
              statement.setInt(2,pageSize);
              statement.setInt(3, offset);
              resultSet =  statement.executeQuery();
              while(resultSet.next()){
                  list.add(getFromResultSet(resultSet));
              }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeResources();
        }
        return list;
    }

    public Order getOrderById(int orderId) {
        Order order = null;
        String sql = "SELECT * FROM `Order` WHERE order_id = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                order = new Order();
                order.setOrder_id(resultSet.getInt("order_id"));
                order.setUser_id(resultSet.getInt("user_id"));
                order.setStatus(resultSet.getString("status"));
                order.setTotal(resultSet.getBigDecimal("total"));
                order.setPayment_method(resultSet.getString("payment_method"));
                order.setCreated_at(resultSet.getTimestamp("created_at"));
                order.setUpdate_at(resultSet.getTimestamp("updated_at"));
                order.setCoupon_code(resultSet.getString("coupon_code"));
                order.setDiscount_amount(resultSet.getBigDecimal("discount_amount"));
                order.setFull_name(resultSet.getString("full_name"));
                order.setMobile(resultSet.getString("mobile"));
                order.setAddress(resultSet.getString("address"));
                order.setEmail(resultSet.getString("email"));
                order.setShipping_address(resultSet.getString("shipping_address"));
            }
        } catch (Exception e) {
            System.out.println("Error finding order by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return order;
    }

    public boolean updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE `Order` SET status = ? WHERE order_id = ?";
        boolean success = false;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, orderId);

            int rows = statement.executeUpdate();
            success = (rows > 0);
        } catch (Exception e) {
            System.out.println("Error updating order status: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return success;
    }
    public List<Order> all(){
        String sql = "Select * from `Order` where user_id = 1 ";
        List<Order> l = new ArrayList<>();
        try {
            connection = getConnection();
            statement= connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                l.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return l;
    }
    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        
        int userId = 1; // thay bằng user_id thực tế có trong bảng Order
        int totalOrders = dao.getTotalOrderCountByUserId(userId);

        System.out.println("Tổng số đơn hàng của user_id = " + userId + " là: " + totalOrders);
        List<Order> li = new ArrayList<>();
        li = dao.findOrdersByUserIdWithPagination(1, 1, 10);
        System.out.println(li);
        List<Order> li2 = dao.all();
        System.out.println(li2);
        System.out.println(dao.getTotalOrderCountByUserId(1));
    }

    @Override
    public List<Order> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<Integer, Order> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Order findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
