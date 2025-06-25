/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Feedback;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.Order;
import com.su25.swp391.entity.OrderItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class FeedbackDAO extends DBContext implements I_DAO<Feedback> {
   
   // Get Feedback by Id Feedback
    @Override
    public Feedback findById(Integer id) {
        String sql = "SELECT * from Feedback WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Error getting feedback by ID: " + e.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }
    // Lấy tất cả feedback của một người dùng
    @Override
    public List<Feedback> findAll() {
    List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT f.*, a.full_name, a.user_name, fo.name , fo.image_url "
                + "FROM feedbacks f "
                + "JOIN account a "
                + "ON f.user_id = a.id "
                + "JOIN order_items oi "
                + "ON f.order_item_id = oi.order_item_id "
                + "JOIN Food fo "
                + "ON oi.food_id = fo.id "
                + "Where f.is_visible = 1 "
                + "ORDER BY f.created_at DESC";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                feedbacks.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Error getting feedbacks by user ID: " + e.getMessage());
        } finally {
            closeResources();
        }
        return feedbacks;
    
    }

    @Override
    public Map<Integer, Feedback> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Feedback t) {
        String sql = "UPDATE Feedback SET  is_visible = ? "
                + "WHERE id = ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setBoolean(1, false);
            statement.setInt(2, t.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating feedback: " + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean delete(Feedback t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Feedback t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Feedback getFromResultSet(ResultSet resultSet) throws SQLException {
        return new Feedback()
                .builder()
                .id(resultSet.getInt("id"))
                .user_id(resultSet.getInt("user_id"))
                .order_item_id(resultSet.getInt("order_item_id"))
                .content(resultSet.getString("content"))
                .rating(resultSet.getInt("rating"))
                .isVisible(resultSet.getBoolean("is_visible"))
                .createdAt(resultSet.getTimestamp("created_at"))
                .updatedAt(resultSet.getTimestamp("updated_at"))
                .build();
     }
     public List<Feedback> searchFeedback(String search, String rating,String foodName, int page, int pageSize) {
        List<Feedback> feedbacks = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT f.* "
                + "FROM Feedback f "
                + "JOIN OrderItem oi ON f.order_item_id = oi.id "
                + "JOIN Food fo ON oi.food_id = fo.id "
                + "JOIN Account a ON f.user_id = a.id "
                + "WHERE  (a.user_name LIKE ? OR a.email LIKE ? ) ");
        List<Object> params = new ArrayList<>();

        String searchPattern = "%" + search.trim() + "%";
        params.add(searchPattern);
        params.add(searchPattern);
//        params.add(search);
        if(rating != null && rating.contains("-1")){
            rating = null;
        }
        if (rating != null && !rating.isEmpty()) {
            sql.append("AND f.rating = ? ");
            params.add(rating);
        }
        if(foodName != null && foodName.contains("-1")){
        foodName = null;
        }
        if (foodName != null && !foodName.isEmpty()) {
        sql.append("AND fo.name LIKE ? ");
        params.add( foodName );
    }
        

        sql.append(" ORDER BY f.created_at DESC LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add((page - 1) * pageSize);

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                feedbacks.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("Error searching orders: " + ex.getMessage());
        } finally {
            closeResources();
        }
        return feedbacks;
    }

    public int getTotalFeedbackResults(String search, String rating,String foodName) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) "
                + "FROM Feedback f "
                + "JOIN OrderItem oi ON f.order_item_id = oi.id "
                + "JOIN Food fo ON oi.food_id = fo.id "
                + "JOIN Account a ON f.user_id = a.id "
                + "WHERE (a.user_name LIKE ? OR a.email LIKE ? OR CAST(o.order_id AS CHAR) = ?) ");
        List<Object> params = new ArrayList<>();

        String searchPattern = "%" + search.trim() + "%";
        params.add(searchPattern);
        params.add(searchPattern);
        params.add(search);
        if(rating != null && rating.contains("-1")){
            rating = null;
        }
        if (rating != null && !rating.isEmpty()) {
            sql.append("AND f.rating = ? ");
            params.add(rating);
        }
        // Lọc theo tên món ăn
        if(foodName != null && foodName.contains("-1")){
        foodName = null;
        }
        if (foodName != null && !foodName.isEmpty()) {
            sql.append("AND fo.name LIKE ? ");
            params.add(foodName);
        }
       try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
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
    

      public List<Feedback> findFeedbackWithFilters(String rating, String foodName, int page, int pageSize) {
        List<Feedback> feedbacks = new ArrayList<>();
         StringBuilder sql = new StringBuilder("SELECT f.* "
        + "FROM Feedback f "
        + "JOIN OrderItem oi ON f.order_item_id = oi.id "
        + "JOIN Food fo ON oi.food_id = fo.id "
        + "WHERE 1=1 ");

    List<Object> params = new ArrayList<>();
    if(foodName != null && foodName.contains("-1")){
        foodName = null;
    }
    if (foodName != null && !foodName.isEmpty()) {
        sql.append("AND fo.name LIKE ? ");
        params.add(foodName);
    }
    if(rating != null && rating.contains("-1")){
          rating = null;
       }
    if (rating != null && !rating.isEmpty()) {
        sql.append("AND f.rating = ? ");
        params.add(rating);
    }
        
        sql.append("ORDER BY created_at DESC LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add((page - 1) * pageSize);

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                feedbacks.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("Error finding filtered orders: " + ex.getMessage());
        } finally {

        }
        return feedbacks;
    }
      
      public int getTotalFilteredFeedback(String rating, String foodName) {
    StringBuilder sql = new StringBuilder("SELECT COUNT(*) "
        + "FROM Feedback f "
        + "JOIN OrderItem oi ON f.order_item_id = oi.id "
        + "JOIN Food fo ON oi.food_id = fo.id "
        + "WHERE 1 = 1 ");
    
    List<Object> params = new ArrayList<>();
    if(rating != null && rating.contains("-1")){
          rating = null;
       }
    // Lọc theo rating (rating)
    if (rating != null && !rating.equals("-1") && !rating.isEmpty()) {
        sql.append("AND f.rating = ? ");
        params.add(rating);
    }
    if(foodName != null && foodName.contains("-1")){
        foodName = null;
    }
    // Lọc theo tên món ăn
    if (foodName != null && !foodName.isEmpty()) {
        sql.append("AND fo.name LIKE ? ");
        params.add(foodName);
    }

    try {
        connection = getConnection();
        statement = connection.prepareStatement(sql.toString());

        for (int i = 0; i < params.size(); i++) {
            statement.setObject(i + 1, params.get(i));
        }

        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
    } catch (SQLException ex) {
        System.out.println("Error counting filtered feedback: " + ex.getMessage());
    } finally {
        closeResources();
    }
    return 0;
}


    public static void main(String[] args) {
        FeedbackDAO f = new FeedbackDAO();
//        List<Feedback> l = f.findAll();
//        System.out.println(l);
//        Feedback f2 = f.findById(2);
//        System.out.println(f2);
//        f.update(f2);
//       List<Feedback> feedbacks = f.findFeedbackWithFilters("", 1, 10);
//          AccountDAO acc = new AccountDAO();
//          OrderItemDAO itemD = new OrderItemDAO();
//          FoodDAO fo = new FoodDAO();
//       for (Feedback feedback : feedbacks) {
//              Account acc2 = acc.findById(feedback.getUserId());
//              feedback.setAccount(acc2);
//              OrderItem item = itemD.findById(feedback.getOrderItemId());
//              Food food = fo.findById(item.getFoodId());
//             feedback.setFood(food);
//         }
//       System.out.println(feedbacks);
//        List<Feedback> feedbacks = f.searchFeedback("Manh","2", 1, 2);
//        System.out.println(feedbacks);
//          Feedback fe = f.findById(1);
//          System.out.println(fe);
       AccountDAO accDAO = new AccountDAO();
       OrderItemDAO itemDAO = new OrderItemDAO();
        FoodDAO foodDAO = new FoodDAO();
      List<Feedback> feedbacks = f.findFeedbackWithFilters("1", "-1",1, 2);
//        HashMap<Integer,Account> AccountMap = new HashMap<>();
//         for (Feedback feedback : feedbacks) {
//              Account acc = accDAO.findById(feedback.getUser_id());
//              AccountMap.put(feedback.getUser_id(), acc);
//          }
//         HashMap<Integer,Food> FoodMap = new HashMap<>();
//         for (Feedback feedback : feedbacks) {
//             OrderItem item = itemDAO.findById(feedback.getOrder_item_id());
//             Food food = foodDAO.findById(item.getFood_id());
//             FoodMap.put(feedback.getOrder_item_id(), food);
//          }
//         System.out.println(feedbacks);
//         System.out.println(AccountMap);
//         System.out.println(FoodMap);
      //    List<Feedback> feedbacks = f.searchFeedback("", "1","Ga", 1, 10);
           HashMap<Integer,Account> AccountMap = new HashMap<>();
         for (Feedback feedback : feedbacks) {
              Account acc = accDAO.findById(feedback.getUser_id());
              AccountMap.put(feedback.getUser_id(), acc);
          }
         HashMap<Integer,Food> FoodMap = new HashMap<>();
         for (Feedback feedback : feedbacks) {
             OrderItem item = itemDAO.findById(feedback.getOrder_item_id());
             Food food = foodDAO.findById(item.getFood_id());
             FoodMap.put(feedback.getOrder_item_id(), food);
          }
          System.out.println(feedbacks);
           System.out.println(AccountMap);
        System.out.println(FoodMap);
   //     System.out.println(f.getTotalFilteredFeedback("", foodName));
//          System.out.println(feedbacks);
//          System.out.println(AccountMap);
//          System.out.println(FoodMap);
//                  
    }

}
