/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.su25.swp391.entity.FoodDraft;
import java.sql.PreparedStatement;

/**
 *
 * @author Dell
 */
public class FoodDraftDAO extends DBContext implements I_DAO<FoodDraft> {

    @Override
    public int insert(FoodDraft t) {
        String sql = "INSERT INTO FoodDraft (name, description, price, image_url, category_id, created_at, updated_at,type,nutri_id,food_id,calo)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            // gan giá tri vao
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, t.getName());
            statement.setString(2, t.getDescription());
            statement.setDouble(3, t.getPrice());
            statement.setString(4, t.getImage_url());
            statement.setInt(5, t.getCategory_id());
            statement.setTimestamp(6, t.getCreated_at());
            statement.setTimestamp(7, t.getUpdated_at());
            statement.setString(8, t.getType());
            statement.setInt(9, t.getNutri_id());

            if (t.getFood_id() != null) {
                statement.setInt(10, t.getFood_id());
            } else {
                System.out.println("hhhh");
                statement.setNull(10, java.sql.Types.INTEGER);
            }
            statement.setDouble(11, t.getCalo());
            // thuc thi cau lenh
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                System.out.println("Insert thành công");
                return resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDraftDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }finally{
            closeResources();
        }
        return -1;
    }

    @Override
    public FoodDraft getFromResultSet(ResultSet resultSet) throws SQLException {
        FoodDraft product = new FoodDraft();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getDouble("price"));
        product.setImage_url(resultSet.getString("image_url"));
        product.setCategory_id(resultSet.getInt("category_id"));
        product.setCreated_at(resultSet.getTimestamp("created_at"));
        product.setUpdated_at(resultSet.getTimestamp("updated_at"));
        product.setFood_id(resultSet.getInt("food_id"));
        product.setType(resultSet.getString("type"));
        product.setNutri_id(resultSet.getInt("nutri_id"));
        product.setCalo(resultSet.getDouble("calo"));
        return product;
    }

    public Integer getBiggestId() {
        String sql = "SELECT * FROM FoodDraft ORDER BY id DESC LIMIT 1";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            closeResources();
        }
        return -1;
    }

//  Trí 
    @Override
    public List< FoodDraft> findAll() {
        String sql = "select * from  FoodDraft as f inner join Request as r on f.id = r.foodDraftId "
                + "where r.statusRequest = ?' ";
        List< FoodDraft> list = new ArrayList<>();
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
   // new find List FoodDraft by seach or status
    public List<FoodDraft> findFoodDraftBySearchFilter(String search, String status,String sort, int page,int pageSize){
        List<FoodDraft> list = new ArrayList<>();
        try {
            List<Object> params = new ArrayList<>();
            StringBuilder sql = new StringBuilder("Select * from FoodDraft as f inner join Request as r on f.id = r.foodDraftId where r.statusRequest = 'Not done' ");
            if(status != null && !status.trim().isEmpty()){
                sql.append(" and f.type = ? ");
                params.add(status);
            }
            if(search != null && !search.trim().isEmpty()){
            sql.append(" and f.name like ? ");
            params.add("%"+search.trim()+"%");
            }
            // Append sorting and pagination
            if (sort != null && !sort.isEmpty()) {
                sql.append(" ORDER BY f.id " + sort + " LIMIT ? OFFSET ?");
            } else {
                sql.append(" ORDER BY f.name DESC LIMIT ? OFFSET ?");
            }
           
            params.add(pageSize);
            params.add((page-1)*pageSize);
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
               for (int i = 0; i < params.size(); i++) {
                statement.setObject(i+1, params.get(i));
            }
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
    public int getTotalNumberFoodDraftBySearchFilter(String search, String status) {
    int total = 0;
    try {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM FoodDraft AS f ");
        sql.append("INNER JOIN Request AS r ON f.id = r.foodDraftId where r.statusRequest = 'Not done' ");

        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND f.type = ? ");
            params.add(status);
        }

        if (search != null && !search.trim().isEmpty()) {
            sql.append(" AND f.name LIKE ? ");
            params.add("%" + search.trim() + "%");
        }

        connection = getConnection();
        statement = connection.prepareStatement(sql.toString());

        for (int i = 0; i < params.size(); i++) {
            statement.setObject(i + 1, params.get(i));
        }

        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            total = resultSet.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        closeResources();
    }
    return total;
} 
    
    //
    public List< FoodDraft> getFoodDraftByPage(int page, int pageSize) {
        String sql = "select * from  FoodDraft as f inner join Request as r on f.id = r.foodDraftId "
                + "where r.statusRequest = ? LIMIT ? OFFSET ?";
        int offset = (page - 1) * pageSize;
        List< FoodDraft> list = new ArrayList<>();
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

    public List< FoodDraft> findAllByResult(String option) {
        String sql = "select * from  FoodDraft as f inner join Request as r on f.id = r.foodDraftId "
                + "where r.statusRequest = ? and f.type = ?";
        List< FoodDraft> list = new ArrayList<>();
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

    public List<FoodDraft> findAllByType(String type, int page, int pageSize) {
        String sql = "select * from  FoodDraft as f"
                + " inner join Request as r on f.id = r.foodDraftId "
                + "where r.statusRequest = ? and f.type = ? LIMIT ? OFFSET ?";
        int offset = (page - 1) * pageSize;
        List< FoodDraft> list = new ArrayList<>();
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
    public List<FoodDraft> findFoodDraftWithFilter(String title, int page, int pageSize) {
        List<FoodDraft> food_Ds = new ArrayList<>();
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
    public Map<Integer, FoodDraft> findAllMap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(FoodDraft t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(FoodDraft t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public FoodDraft findById(Integer id) {
        String sql = "select * from FoodDraft where id = ? ";
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

//    public static void main(String[] args) {
//        FoodDraftDAO dao = new FoodDraftDAO();
//        FoodDraft f = dao.findById(4);
////        dao.updateStatus(1);
//        // System.out.println(f);
//
//        System.out.println("--");
//
//        System.out.println(dao.findById(3));
//        System.out.println("IN: " + dao.countFoodDraftWithFilter("củ"));
//        for (FoodDraft l : dao.findFoodDraftWithFilter("củ", 1, 5)) {
//            System.out.println("list: " + l);
//        }
//        List<FoodDraft> l = dao.getFoodDraftByPage(1, 10);
//        System.out.println(l);
//    }
    public static void main(String[] args) {
        FoodDraftDAO dao = new FoodDraftDAO();
        for (FoodDraft object : dao.findFoodDraftBySearchFilter("", "","DESC", 1, 10)) {
            System.out.println(object);
        }
    }
}
