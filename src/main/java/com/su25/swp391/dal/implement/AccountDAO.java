/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class AccountDAO extends DBContext implements I_DAO<Account> {

  @Override
  public List<Account> findAll() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public Map<Integer, Account> findAllMap() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public boolean update(Account t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public boolean delete(Account t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public int insert(Account t) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public Account getFromResultSet(ResultSet resultSet) throws SQLException {
    Account account = new Account();
    account.setId(resultSet.getInt("id"));
    account.setEmail(resultSet.getString("email"));
    account.setPassword(resultSet.getString("password"));
    account.setFull_name(resultSet.getString("full_name"));
    account.setUser_name(resultSet.getString("user_name"));
    account.setBirth_date(resultSet.getDate("birth_date"));
    account.setGender(resultSet.getString("gender"));
    account.setAddress(resultSet.getString("address"));
    account.setMobile(resultSet.getString("mobile"));
    account.setRole(resultSet.getString("role"));
    account.setStatus(resultSet.getString("status"));
    return account;
  }

  @Override
  public Account findById(Integer id) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  public List<Account> findAccountByRole(String nutri) {
    String sql = "SELECT * FROM Account WHERE role = ?";
    List<Account> list = new java.util.ArrayList<>();
    try {
      statement = connection.prepareStatement(sql);
      statement.setString(1, nutri);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Account account = getFromResultSet(resultSet);
        list.add(account);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return list;
  }

  public static void main(String[] args) {
    for (Account a : new AccountDAO().findAccountByRole("nutri")) {
      System.out.println(a);
    }
  }

}
