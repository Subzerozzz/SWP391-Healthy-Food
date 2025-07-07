
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Delivery;
import com.su25.swp391.entity.Feedback;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DeliveryDAO extends DBContext implements I_DAO<Delivery>{

    @Override
    public List<Delivery> findAll() {
       String sql = "Select * from Delivery";
        List<Delivery> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }
    
    public List<Delivery> findDeliveryWithFilters(String sort,String status){
        List<Delivery> list = new ArrayList<>();
        // Build the SQL query dynamically with optional filters
        StringBuilder sql = new StringBuilder(
        "SELECT * from Delivery where 1 = 1 ");
        List<Object> params = new ArrayList<>();
        // Add status filter if provided
        if (status != null && !status.isEmpty()) {
            sql.append("AND status = ? ");
            params.add(status);
        }
        // Append sorting and pagination
        if(sort != null && !sort.isEmpty()){
            sql.append(" ORDER BY order_id "+sort+" ");
        }else{
            sql.append(" ORDER BY assigned_at DESC ");
        }
       
        try {
            // Open connection and prepare statement
            connection = getConnection();
            statement = connection.prepareCall(sql.toString());
             // Set parameters in PreparedStatement
           for(int i = 0;i < params.size();i++){
                statement.setObject(i+1, params.get(i));
            }
           resultSet =statement.executeQuery();
           while(resultSet.next()){
               list.add(getFromResultSet(resultSet));
           }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }
    
    public List<Delivery> searchDelivery(String search, String sort, String status) {
        // Create a list to hold the feedback results
        List<Delivery> list = new ArrayList<>();

        // Build the base SQL query with necessary joins
        StringBuilder sql = new StringBuilder("SELECT d.* "
                + "FROM Delivery d "
                + "JOIN Account a ON d.shipper_id = a.id "
                + "WHERE (a.user_name LIKE ? OR a.email LIKE ?) ");

        // Store query parameters in a list for later use
        List<Object> params = new ArrayList<>();

        // Prepare search pattern for user_name or email (wildcard matching)
        String searchPattern = "%" + search.trim() + "%";
        params.add(searchPattern);
        params.add(searchPattern);


        // Add filter for food name, if provided
        if (status!= null && !status.isEmpty()) {
            sql.append("AND d.status = ? ");
            params.add(status);
        }

        // Append sorting and pagination
        if(sort != null && !sort.isEmpty()){
            sql.append(" ORDER BY d.order_id "+sort+" ");
        }else{
            sql.append(" ORDER BY d.assigned_at DESC ");
        }

        

        try {
            // Open a connection and prepare the SQL statement
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());

            // Bind all parameters to the prepared statement
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            // Execute the query and process the result set
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Clean up database resources (connection, statement, resultSet, etc.)
            closeResources();
        }

        // Return the list of matched feedback
        return list;
    }
    
    @Override
    public Map<Integer, Delivery> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Delivery t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Delivery t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Delivery t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Delivery getFromResultSet(ResultSet resultSet) throws SQLException {
     return Delivery
             .builder()
             .id(resultSet.getInt("id"))
             .order_id(resultSet.getInt("order_id"))
             .shipper_id(resultSet.getInt("shipper_id"))
             .status(resultSet.getString("status"))
             .assigned_at(resultSet.getTimestamp("assigned_at"))
             .delivered_at(resultSet.getTimestamp("delivered_at"))
             .rejectReason(resultSet.getString("rejectReason"))
             .build();
    
    }

    @Override
    public Delivery findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public static void main(String[] args) {
        for (Delivery d : new DeliveryDAO().searchDelivery("hung", "asc", "")) {
            System.out.println(d);
        }
    }
    
}
