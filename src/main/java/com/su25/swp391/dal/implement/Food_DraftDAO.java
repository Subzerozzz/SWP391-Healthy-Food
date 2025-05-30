/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Food_Draft;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Food_DraftDAO extends DBContext implements I_DAO< Food_Draft> {

    @Override
    public List< Food_Draft> findAll() {
        String sql = "select * from  Food_Draft f join f";
        List< Food_Draft> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
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

    public List<Food_Draft> findAllFoodDrartByRequest() {
        String sql = "select f.* from swp391_healthy_food.Request as r inner join swp391_healthy_food.Food_Darft as f \n"
                + "on r.foodDraftId = f.id Where f.status = 'pending'";
        List<Food_Draft> list = new ArrayList<>();
        try {

            connection = getConnection();
            statement = connection.prepareStatement(sql);
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
                .idNutri(resultSet.getInt("id_Nutri"))
                .nameNutri(resultSet.getString("nameNutri"))
                .description(resultSet.getString("description"))
                .price(resultSet.getDouble("price"))
                .image_url(resultSet.getString("image_url"))
                .status(resultSet.getString("status"))
                .create_At(resultSet.getDate("create_At"))
                .category_id(resultSet.getInt("category_id"))
                .stock(resultSet.getInt("stock"))
                .build();

        return food;
    }

    @Override
    public Food_Draft findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        Food_DraftDAO dao = new Food_DraftDAO();
        for (Food_Draft f : dao.findAllFoodDrartByRequest()) {
            System.out.println(f);
        }
    }

}
