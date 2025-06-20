package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Account;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;

public class AccountDAO extends DBContext implements I_DAO<Account> {

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

    public List<Account> findAccountByRole(String nutri) {
        String sql = "SELECT * FROM Account WHERE role = ?";
        List<Account> list = new java.util.ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, nutri);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Account account = getFromResultSet(resultSet);
                list.add(account);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeResources();
        }
        return list;
    }

    @Override
    public List<Account> findAll() {
        List<Account> account = new ArrayList<>();
        try {
            connection = getConnection();
            String sql = "SELECT * FROM Account";
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
            connection = getConnection();
            String sql = "SELECT * FROM Account";
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

    public boolean updatePasswordByEmail(Account t) {
        try {
            connection = getConnection();
            String sql = "UPDATE Account SET password = ? WHERE email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getPassword());
            statement.setString(2, t.getEmail());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean update(Account t) {
        try {
            connection = getConnection();
            String sql = "UPDATE Account SET full_name = ?, "
                    + " birth_date = ?, gender = ?, role = ?, address = ?, mobile = ?, status= ? WHERE email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getFull_name());
            statement.setObject(2, t.getBirth_date());
            statement.setObject(3, t.getGender());
            statement.setObject(4, t.getRole());
            statement.setObject(5, t.getAddress());
            statement.setObject(6, t.getMobile());
            statement.setObject(7, t.getStatus());
            statement.setObject(8, t.getEmail());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    public boolean updateAdmin(Account t) {
        List<Account> updateAccount = new ArrayList<>();
        String sql = "UPDATE Account SET  full_name = ?, status = ?, mobile=? ,gender=?,address=? WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getFull_name());
            //statement.setString(2, t.getEmail());
            //statement.setString(3, t.getUser_name());
            //statement.setString(4, t.getRole());
            statement.setString(2, t.getStatus());
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
        String sql = "DELETE FROM Account WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getId());
            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Err delete" + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    public List<Account> pagingAccount(int index) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM Account\n"
                + "WHERE role != 'admin'\n"
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
        String sql = "select count(*) from Account + WHERE role != 'admin'";
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

    public List<Account> filterAccountsPaging(String role, String status, int pageIndex, int pageSize) {
        List<Account> accounts = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Account WHERE 1=1 ");

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
                statement.setString(paramIndex++, status);
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
        String sql = "INSERT INTO Account (full_name, user_name, email, password, address, role, status, birth_date,mobile,gender) VALUES"
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
            statement.setString(7, t.getStatus());
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

    public Account findByEmail(Account t) {
        String sql = "SELECT * FROM Account WHERE email = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getEmail());
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

    @Override
    public Account findById(Integer id) {
        try {
            connection = getConnection();
            String sql = "SELECT * FROM Account WHERE id = ?";
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

    public Account findByEmailOrUsernameAndPass(Account t) {
        String sql = "SELECT * FROM Account WHERE (email = ? OR user_name = ?) AND password = ?";
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

    // Phương thức phân trang cho tất cả tài khoản
    public List<Account> findAllWithPagination(int page, int pageSize) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Account ORDER BY id LIMIT ?, ?";

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
        String sql = "SELECT COUNT(*) FROM Account";
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
        String sql = "SELECT * FROM Account WHERE full_name LIKE ? OR email LIKE ? OR address LIKE ?";
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

    public boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM Account WHERE email = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int count = resultSet.getInt(1); // lấy cột đầu tiên (COUNT(*))
                return count > 0;
            }
        } catch (Exception e) {
            System.out.println("Is EmailExist" + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }
    // Phương thức phân trang với bộ lọc

    public List<Account> filterAccountsWithPagination(String role, String status, int page, int pageSize) {
        List<Account> accounts = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Account WHERE 1=1");

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
                statement.setString(paramIndex++, status);
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
    public int getTotalAccountCountWithFilter(String role, String status) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Account WHERE 1=1");

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
                statement.setString(paramIndex++, status);
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

      public boolean activateAccount(int accountId) {
        String sql = "UPDATE Account SET Status = 'active' WHERE id=?";
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
        String sql = "UPDATE Account SET Status = 'banned' WHERE id=?";
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

    public static void main(String[] args) {
        System.out.println(new AccountDAO().isEmailExists("nguyenduyntn112004@gmail.com"));
    }
}
