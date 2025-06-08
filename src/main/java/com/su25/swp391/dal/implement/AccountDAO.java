/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Account;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String sql = "UPDATE Swp301_pr.account SET  full_name = ?, status = ?,mobile=?,gender=?,address=? WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getFull_name());
           
            statement.setBoolean(2, t.getStatus());
            statement.setString(3, t.getMobile());
            statement.setString(4, t.getGender());
            statement.setString(5, t.getAddress());
            statement.setInt(6, t.getId());
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
            connection = getConnection();
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

    public List<Account> pagingAccount(int index) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM Swp301_pr.account\n"
                + "ORDER BY id\n"
                + "LIMIT 10 OFFSET ?;";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (index - 1) * 10);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                list.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace(); // BẮT BUỘC CÓ
        } finally {
            closeResources();
        }
        return list;
    }
    //dem so luong account trong db
    public int getTotalAccount() {
        String sql = "select count(*) from Swp301_pr.account";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            closeResources();
        }
        return 0;
    }
    public List<Account> filterAccountsPaging(String role, Boolean status, int pageIndex, int pageSize) {
    List<Account> accounts = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM Swp301_pr.account WHERE 1=1");

    if (role != null && !role.isEmpty()) {
        sql.append(" AND role = ?");
    }
    if (status != null) {
        sql.append(" AND status = ?");
    }

    sql.append(" ORDER BY id");
    sql.append(" LIMIT ? OFFSET ?");

    try {
        connection = getConnection();
        statement = connection.prepareStatement(sql.toString());
        int paramIndex = 1;

        // Thêm tham số lọc
        if (role != null && !role.isEmpty()) {
            statement.setString(paramIndex++, role);
        }
        if (status != null) {
            statement.setBoolean(paramIndex++, status);
        }

        // Thêm tham số phân trang
        statement.setInt(paramIndex++, pageSize); // LIMIT
        statement.setInt(paramIndex++, (pageIndex - 1) * pageSize); // OFFSET

        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            accounts.add(getFromResultSet(resultSet));
        }
    } catch (Exception e) {
        System.out.println("Error filtering and paging accounts: " + e.getMessage());
    } finally {
        closeResources();
    }

    return accounts;
}
    


    @Override
    public int insert(Account t) {
        List<Account> insertAccount = new ArrayList<>();
        String sql = "INSERT INTO Swp301_pr.account (full_name, user_name, email, password, address, role, status, brith_date,mobile,gender) VALUES"
                + "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // BẮT BUỘC PHẢI CÓ ĐOẠN NÀY
            statement.setString(1, t.getFull_name());
            statement.setString(2, t.getUser_name());
            statement.setString(3, t.getEmail());
            statement.setString(4, t.getPassword());
            statement.setString(5, t.getAddress());
            statement.setString(6, t.getRole());
            statement.setBoolean(7, t.getStatus());
            statement.setDate(8, t.getBirth_date());
            statement.setString(9, t.getMobile());
            statement.setString(10, t.getGender());

            //Lấy khóa chính được tạo tự động sau khi insert
            int affectedRow = statement.executeUpdate();
            if (affectedRow > 0) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return resultSet.getInt(1); // Trả về ID vừa insert
                }
            }
        } catch (Exception e) {
            System.out.println("Err Insert" + e.getMessage());
        } finally {
            closeResources();
        }
        return -1;
    }

    public class Main {

        public static void main(String[] args) {
            // Tạo đối tượng Account với dữ liệu test
            Account testAccount = new Account();
            testAccount.setFull_name("Nguyễn Văn A");
            testAccount.setUser_name("nguyenvana");
            testAccount.setEmail("vana@example.com");
            testAccount.setPassword("123456");
            testAccount.setAddress("Hà Nội");
            testAccount.setRole("user");
            testAccount.setStatus(true);
            testAccount.setBirth_date(Date.valueOf("2000-01-01")); // java.sql.Date
            testAccount.setMobile("0987654321");
            testAccount.setGender("Male");

            // Tạo DAO để gọi phương thức insert
            AccountDAO dao = new AccountDAO();
            int generatedId = dao.insert(testAccount);

            // In kết quả
            if (generatedId != -1) {
                System.out.println("Insert thành công! ID mới: " + generatedId);
            } else {
                System.out.println("Insert thất bại.");
            }
        }
    }

    @Override
    public Account getFromResultSet(ResultSet resultSet) throws SQLException {
        return Account.builder()
                .id(resultSet.getInt("id"))
                .full_name(resultSet.getString("full_name"))
                .user_name(resultSet.getString("user_name"))
                .address(resultSet.getString("address"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password")) // Thêm password
                .role(resultSet.getString("role"))
                .status(resultSet.getBoolean("status")) // Thêm status
                .birth_date(resultSet.getDate("brith_date")) // Thêm birth_date (chú ý tên cột trong DB là "brith_date")
                .mobile(resultSet.getString("mobile"))
                .gender(resultSet.getString("gender"))
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
     // Phương thức phân trang cho tất cả tài khoản
    public List<Account> findAllWithPagination(int page, int pageSize) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Swp301_pr.account ORDER BY id LIMIT ?, ?";
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (page - 1) * pageSize);
            statement.setInt(2, pageSize);
            
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accounts.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error finding accounts with pagination: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }
        
        return accounts;
    }
// Đếm tổng số tài khoản
    public int getTotalAccountCount() {
        String sql = "SELECT COUNT(*) FROM Swp301_pr.account";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error counting accounts: " + e.getMessage());
        } finally {
            closeResources();
        }
        return 0;
    }
    //tim kiem theo ten nguoi dung nhap vao
    public List<Account> searchAccountsByNameOrEmail(String keyword) {
    List<Account> accounts = new ArrayList<>();
    String sql = "SELECT * FROM Swp301_pr.account WHERE full_name LIKE ? OR email LIKE ? OR address LIKE ?";
    try {
        connection = getConnection();
        statement = connection.prepareStatement(sql);
        String likeKeyword = "%" + keyword + "%";
        statement.setString(1, likeKeyword);
        statement.setString(2, likeKeyword);
        statement.setString(3, likeKeyword);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            accounts.add(getFromResultSet(resultSet));
        }
    } catch (Exception e) {
        System.out.println("Error searching accounts: " + e.getMessage());
    } finally {
        closeResources();
    }
    return accounts;
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
// Phương thức phân trang với bộ lọc
    public List<Account> filterAccountsWithPagination(String role, Boolean status, int page, int pageSize) {
        List<Account> accounts = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Swp301_pr.account WHERE 1=1");

        if (role != null && !role.isEmpty()) {
            sql.append(" AND role = ?");
        }
        if (status != null) {
            sql.append(" AND status = ?");
        }
        
        sql.append(" ORDER BY id LIMIT ?, ?");

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
            int paramIndex = 1;

            if (role != null && !role.isEmpty()) {
                statement.setString(paramIndex++, role);
            }
            if (status != null) {
                statement.setBoolean(paramIndex++, status);
            }
            
            statement.setInt(paramIndex++, (page - 1) * pageSize);
            statement.setInt(paramIndex++, pageSize);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accounts.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error filtering accounts with pagination: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return accounts;
    }
    // Đếm tổng số tài khoản với bộ lọc
    public int getTotalAccountCountWithFilter(String role, Boolean status) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Swp301_pr.account WHERE 1=1");

        if (role != null && !role.isEmpty()) {
            sql.append(" AND role = ?");
        }
        if (status != null) {
            sql.append(" AND status = ?");
        }

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql.toString());
            int paramIndex = 1;

            if (role != null && !role.isEmpty()) {
                statement.setString(paramIndex++, role);
            }
            if (status != null) {
                statement.setBoolean(paramIndex++, status);
            }

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error counting filtered accounts: " + e.getMessage());
        } finally {
            closeResources();
        }
        return 0;
    }
}
