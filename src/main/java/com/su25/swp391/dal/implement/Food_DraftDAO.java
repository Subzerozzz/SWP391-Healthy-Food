/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Food_Draft;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Food_DraftDAO extends DBContext implements I_DAO< Food_Draft> {

    @Override
    public List< Food_Draft> findAll() {
        String sql = "select * from  FoodDraft as f inner join Request as r on f.id = r.foodDraftId "
                + "where r.statusRequest = ?' ";
        List< Food_Draft> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, GlobalConfig.STATUS_REQUEST_NOT_DONE);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error happen in FoodDAO:" + e.getMessage());
        } finally {
            closeResources();
        }
        return list;
    }
    public List< Food_Draft> getFoodDraftByPage(int page, int pageSize) {
        String sql = "select * from  FoodDraft as f inner join Request as r on f.id = r.foodDraftId "
                + "where r.statusRequest = ? LIMIT ? OFFSET ?";
        int offset = (page - 1) * pageSize;
        List< Food_Draft> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, GlobalConfig.STATUS_REQUEST_NOT_DONE);
            statement.setInt(2, pageSize);
            statement.setInt(3, offset);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error happen in FoodDAO:" + e.getMessage());
        } finally {
            closeResources();
        }
        return list;
    }
    
    public int getTotalFoodDraftCount() {
        String sql = "select COUNT(*) from  FoodDraft as f inner join Request as r on f.id = r.foodDraftId "
                + "where r.statusRequest = ? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, GlobalConfig.STATUS_REQUEST_NOT_DONE);
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
     
    
    public List< Food_Draft> findAllByResult(String option) {
        String sql = "select * from  FoodDraft as f inner join Request as r on f.id = r.foodDraftId "
                + "where r.statusRequest = ? and f.type = ?";
        List< Food_Draft> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, GlobalConfig.STATUS_REQUEST_NOT_DONE);
            statement.setString(2, option);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error happen in FoodDAO:" + e.getMessage());
        } finally {
            closeResources();
        }
        return list;
    }
    
    public List<String> findAllType() {
        String sql = "select type from  FoodDraft  Group by type";
        List<String> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("type"));
            }
        } catch (Exception e) {
            System.out.println("Error happen in FoodDAO:" + e.getMessage());
        } finally {
            closeResources();
        }
        return list;
    }
    public List<Food_Draft> findAllByType(String type, int page, int pageSize) {
        String sql = "select * from  FoodDraft as f"
                + " inner join Request as r on f.id = r.foodDraftId "
                + "where r.statusRequest = ? and f.type = ? LIMIT ? OFFSET ?";
        int offset = (page - 1) * pageSize;
        List< Food_Draft> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, GlobalConfig.STATUS_REQUEST_NOT_DONE);
            statement.setString(2, type);
            statement.setInt(3, pageSize);
            statement.setInt(4, offset);
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
    public int getTotalFoodDCountBySelect(String option) {
        String sql = "select COUNT(*) from  FoodDraft as f inner join Request as r on f.id = r.foodDraftId "
                + "where r.statusRequest = ? and f.type = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, GlobalConfig.STATUS_REQUEST_NOT_DONE);
            statement.setString(2, option);
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
    
    // Find FoodDraft with filtering and pagination
    public List<Food_Draft> findFoodDraftWithFilter(String title,int page,int pageSize){
        List<Food_Draft> food_Ds = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM FoodDraft as f inner join Request as r on f.id = r.foodDraftId WHERE r.statusRequest = ? ");
        List<Object> params = new ArrayList<>();
        params.add(GlobalConfig.STATUS_REQUEST_NOT_DONE);
        // Add title filter if provided
        if (title != null && !title.trim().isEmpty()) {
            sqlBuilder.append(" AND name LIKE ?");
            params.add("%" + title + "%");
        }
        // Add ordering and pagination
        sqlBuilder.append(" ORDER BY created_at DESC LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add((page - 1) * pageSize);
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sqlBuilder.toString());
            // Set parameters
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                food_Ds.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return food_Ds;
    }

    public int countFoodDraftWithFilter(String title) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT COUNT(*) FROM FoodDraft as f inner join Request as r on f.id = r.foodDraftId WHERE r.statusRequest = ? ");
        List<Object> params = new ArrayList<>();
        params.add(GlobalConfig.STATUS_REQUEST_NOT_DONE);
        // Add title filter if provided
        if (title != null && !title.trim().isEmpty()) {
            sqlBuilder.append(" AND name LIKE ?");
            params.add("%" + title + "%");
        }
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sqlBuilder.toString());
            // Set parameters
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
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

    @Override
    public Map<Integer, Food_Draft> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Food_Draft t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public boolean delete(Food_Draft t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Food_Draft t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    @Override
    public Food_Draft getFromResultSet(ResultSet resultSet) throws SQLException {
        Food_Draft food = Food_Draft
                .builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .price(resultSet.getDouble("price"))
                .image_url(resultSet.getString("image_url"))
                .category_id(resultSet.getInt("category_id"))
                .created_at(resultSet.getTimestamp("created_at"))
                .updated_at(resultSet.getTimestamp("updated_at"))
                .food_id(resultSet.getInt("food_id"))
                .type(resultSet.getString("type"))
                .nutri_id(resultSet.getInt("nutri_id"))
                .calo(resultSet.getDouble("calo"))
                .build();

        return food;
    }
    

    @Override
    public Food_Draft findById(Integer id) {
        String sql = "select * from swp391_healthy_food.FoodDraft where id = ? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return null;
    }
 

    public static void main(String[] args) {
        Food_DraftDAO dao = new Food_DraftDAO();
        Food_Draft f = dao.findById(4);
//        dao.updateStatus(1);
        // System.out.println(f);
        
        System.out.println("--");
        
        System.out.println(dao.findById(3));
        System.out.println("IN: "+dao.countFoodDraftWithFilter("củ"));
        for (Food_Draft l : dao.findFoodDraftWithFilter("củ", 1, 5)) {
            System.out.println("list: "+l);
        }
    }
}
