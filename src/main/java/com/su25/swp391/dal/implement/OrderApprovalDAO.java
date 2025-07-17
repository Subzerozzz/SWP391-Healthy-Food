///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.su25.swp391.dal.implement;
//
//import com.su25.swp391.config.GlobalConfig;
//import com.su25.swp391.dal.DBContext;
//import com.su25.swp391.dal.I_DAO;
//import com.su25.swp391.entity.Account;
//import com.su25.swp391.entity.OrderApproval;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class OrderApprovalDAO extends DBContext implements I_DAO<OrderApproval>{
//
//    // Thêm approval mới (sử dụng connection được truyền vào để hỗ trợ transaction)
//    public boolean addOrderApproval(int orderId, int adminId, String statusBefore, String statusAfter, String note) {
//        String sql = "INSERT INTO order_approvals (order_id, approved_by, approved_at, status_before, status_after, note) "
//                + "VALUES (?, ?, NOW(), ?, ?, ?)";
//
//        try {
//            statement = connection.prepareStatement(sql);
//            statement.setInt(1, orderId);
//            statement.setInt(2, adminId);
//            statement.setString(3, statusBefore);
//            statement.setString(4, statusAfter);
//            statement.setString(5, note);
//
//            return statement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources();
//        }
//        return false;
//    }
//
//    // Lấy lịch sử approval của một đơn hàng
//    public List<OrderApproval> getOrderApprovalsByOrderId(int orderId) {
//        List<OrderApproval> approvals = new ArrayList<>();
//
//        String sql = "SELECT * "
//                + "FROM OrderApproval "
//                + "WHERE order_id = ? "
//                + "ORDER BY approved_at DESC";
//
//        try {
//            connection = getConnection();
//            statement = connection.prepareStatement(sql);
//            statement.setInt(1, orderId);
//            resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                approvals.add(getFromResultSet(resultSet));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources();
//        }
//
//        return approvals;
//    }
//    
//   
//    @Override
//    public List<OrderApproval> findAll() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public Map<Integer, OrderApproval> findAllMap() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public boolean update(OrderApproval t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public boolean delete(OrderApproval t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public int insert(OrderApproval t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public OrderApproval getFromResultSet(ResultSet resultSet) throws SQLException {
//      return OrderApproval
//              .builder()
//              .id(resultSet.getInt("id"))
//              .order_id(resultSet.getInt("order_id"))
//              .approved_by(resultSet.getInt("approved_by"))
//              .approved_at(resultSet.getTimestamp("approved_at"))
//              .statusBefore(resultSet.getString("status_before"))
//              .statusAfter(resultSet.getString("status_after"))
//              .note(resultSet.getString("note"))
//              .build();
//    }
//
//    @Override
//    public OrderApproval findById(Integer id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//     public static void main(String[] args) {
//        OrderApprovalDAO d = new OrderApprovalDAO();
//        AccountDAO acc = new AccountDAO();
//        List<OrderApproval> l = d.getOrderApprovalsByOrderId(62);
//         for (OrderApproval oA : l) {
//             Account a = acc.findById(oA.getApproved_by());
//             
//         }
//        System.out.println(l);
//    }
//
//}
