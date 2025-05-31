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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class AccountDAO extends DBContext implements I_DAO<Account> {

    @Override
    public List<Account> findAll() {
        List<Account> account = new ArrayList<>();
        String sql = "SELECT * FROM Swp301_pr.account";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                account.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Err findAll" + e.getMessage());
        } finally {
            closeResources();
        }
        return account;
    }

    @Override
    public Map<Integer, Account> findAllMap() {
        Map<Integer, Account> accountMap = new HashMap<>();
        String spl = "SELECT * FROM Swp301_pr.account";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(spl);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Account account = getFromResultSet(resultSet);
                accountMap.put(account.getId(), account);
            }
        } catch (Exception e) {
            System.out.println("Err findAllMap" + e.getMessage());
        } finally {
            closeResources();
        }
        return accountMap;

    }

    public boolean activateAccount(int accountId) {
        String sql = "UPDATE Swp301_pr.account SET Status = true WHERE id=?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);
            int affectedRow = statement.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            System.out.println("Error activating account:" + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    public boolean deactivateAccount(int accountId) {
        String sql = "UPDATE Swp301_pr.account SET Status = false WHERE id=?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);
            int affectedRow = statement.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            System.out.println("Error activating account:" + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public boolean update(Account t) {
        List<Account> updateAccount = new ArrayList<>();
        String sql = "UPDATE Swp301_pr.account SET  full_name = ?, email = ?, status = ?, WHERE id = ?";
        try {

            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getFull_name());
            statement.setString(2, t.getEmail());
            statement.setBoolean(3, t.getStatus());
            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Err update" + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean delete(Account t) {
        List<Account> deleteAccount = new ArrayList<>();
        String sql = "DELETE FROM Swp301_pr.account WHERE id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getId());
            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Err update" + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public int insert(Account t) {
        List<Account> insertAccount = new ArrayList<>();
        String sql = "INSERT INTO Swp301_pr.account ( full_name, user_name, email, password, address, role, status, brith_date) VALUES\n"
                + "( ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getFull_name());
            statement.setString(2, t.getUse_name());
            statement.setString(3, t.getEmail());
            statement.setString(4, t.getPassword());
            statement.setString(5, t.getAddress());
            statement.setString(6, t.getRole());

            statement.setDate(8, t.getBirth_data());

            resultSet = statement.getGeneratedKeys();//Lấy khóa chính được tạo tự động sau khi insert
            if (resultSet.next()) {

                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Err Insert" + e.getMessage());
        } finally {
            closeResources();
        }
        return -1;
    }

    @Override
    public Account getFromResultSet(ResultSet resultSet) throws SQLException {
        return Account.builder()
                .id(resultSet.getInt("id"))
                .full_name(resultSet.getString("full_name"))
                .use_name(resultSet.getString("user_name"))
                .address(resultSet.getString("address"))
                .email(resultSet.getString("email"))
                .build();
    }

    @Override
    public Account findById(Integer id) {
        List<Account> findbyid = new ArrayList<>();
        String sql = "SELECT * FROM Swp301_pr.account WHERE id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (Exception e) {
            System.out.println("Err findbyid" + e.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }

    public boolean isEmailExists(String email, Integer id) {
        String sql = "SELECT COUNT (*) FROM Swp301_pr.account WHERE email = ?";
        if (id != null) {
            //kiem tra xem co tai khoan nao trung id ma dung tk nay ko
            sql += "AND id !=?";
        }
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
        } catch (Exception e) {
            System.out.println("Is EmailExist" + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

}
