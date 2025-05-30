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
import java.sql.PreparedStatement;

public class AccountDAO extends DBContext implements I_DAO<Account> {

    @Override
    public List<Account> findAll() {
        List<Account> account = new ArrayList<>();
        try {
            String sql = "SELECT * FROM account";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                account.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return account;
    }

    @Override
    public Map<Integer, Account> findAllMap() {
        Map<Integer, Account> accountMap = new HashMap<>();
        try {
            String sql = "SELECT * FROM accounts";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Account account = getFromResultSet(resultSet);
                accountMap.put(account.getId(), account);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return accountMap;
    }

    @Override
    public boolean update(Account t) {
        try {
            String sql = "UPDATE account SET email = ?, password = ?, full_name = ?, "
                    + "user_name = ?, birth_date = ?, gender = ?, role = ?, address = ?, mobie = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getEmail());
            statement.setString(2, t.getPassword());
            statement.setString(3, t.getFull_name());
            statement.setString(4, t.getUser_name());
            statement.setDate(5, t.getBirth_date());
            statement.setString(6, t.getRole());
            statement.setString(7, t.getAddress());
            statement.setInt(8, t.getMobie());
            statement.setInt(9, t.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean delete(Account t) {
        try {
            String sql = "DELETE FROM account WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public int insert(Account t) {
        try {
            String sql = "INSERT INTO account (email, password, full_name, user_name, gender, birth_date, role, address, mobie ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, t.getEmail());
            statement.setString(2, t.getPassword());
            statement.setString(3, t.getFull_name());
            statement.setString(4, t.getUser_name());
//            statement.setBoolean(5, t.isGender());
            statement.setDate(5, t.getBirth_date());
            statement.setString(6, t.getRole());
            statement.setString(7, t.getAddress());
            statement.setInt(8,t.getMobie());
            statement.setString(9, t.getStatus());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);        //
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return -1;
    }

    @Override
    public Account getFromResultSet(ResultSet resultSet) throws SQLException {
        return Account.builder()
                .id(resultSet.getInt("id"))
                .email(resultSet.getString("email"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .full_name(resultSet.getString("full_name"))
                .user_name(resultSet.getString("user_name"))
                .gender(resultSet.getBoolean("gender"))
                .birth_date(resultSet.getDate("birth_date"))
                .role(resultSet.getString("role"))
                .address(resultSet.getString("address"))
                .mobie(resultSet.getInt("mobie"))
                .status(resultSet.getString("status"))
                .build();
    }

    @Override
    public Account findById(Integer id) {
        try {
            String sql = "SELECT * FROM account WHERE id = ?";
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
        new AccountDAO().findAll().forEach(item -> {
            System.out.println(item);
        });
    }
     public Account findByEmailOrUsernameAndPass(Account t) {
        String sql = "SELECT * FROM account WHERE (email = ? OR username = ?) AND password = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getEmail());
            statement.setString(2, t.getUser_name());
            statement.setString(3, t.getPassword());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }

}
