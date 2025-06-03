package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Blog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlogDAO extends DBContext implements I_DAO<Blog> {

    @Override
    public List<Blog> findAll() {
        List<Blog> blogs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM blogs";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                blogs.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return blogs;
    }

    @Override
    public Map<Integer, Blog> findAllMap() {
        Map<Integer, Blog> blogMap = new HashMap<>();
        try {
            String sql = "SELECT * FROM blogs";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Blog blog = getFromResultSet(resultSet);
                blogMap.put(blog.getId(), blog);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return blogMap;
    }

    @Override
    public boolean update(Blog t) {
        try {
            String sql = "UPDATE blogs SET title = ?, author = ?, brief_info = ?, content = ?, thumbnailblogs = ?, status = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getTitle());
            statement.setString(2, t.getAuthor());
            statement.setString(3, t.getBrief_info());
            statement.setString(4, t.getContext());
            statement.setString(5, t.getThumbnailblogs());
            statement.setString(6, t.getStatus());
            statement.setInt(7, t.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    @Override
    public boolean delete(Blog t) {
        try {
            String sql = "DELETE FROM blogs WHERE id = ?";
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
    public int insert(Blog t) {
        try {
            String sql = "INSERT INTO blogs (title, author, brief_info, content, thumbnailblogs, status) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, t.getTitle());
            statement.setString(2, t.getAuthor());
            statement.setString(3, t.getBrief_info());
            statement.setString(4, t.getContext());
            statement.setString(5, t.getThumbnailblogs());
            statement.setString(6, t.getStatus());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return -1;
    }

    @Override
    public Blog getFromResultSet(ResultSet resultSet) throws SQLException {
        return Blog.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .author(resultSet.getString("author"))
                .brief_info(resultSet.getString("brief_info"))
                .context(resultSet.getString("content"))
                .thumbnailblogs(resultSet.getString("thumbnailblogs"))
                .status(resultSet.getString("status"))
                .build();
    }

    @Override
    public Blog findById(Integer id) {
        try {
            String sql = "SELECT * FROM blogs WHERE id = ?";
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

    public List<Blog> findBlogsWithFilter(String searchTitle, String statusFilter, int page, int pageSize) {
        List<Blog> blogs = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM blogs WHERE 1=1");
            List<Object> params = new ArrayList<>();
            if (searchTitle != null && !searchTitle.trim().isEmpty()) {
                sql.append(" AND title LIKE ?");
                params.add("%" + searchTitle + "%");
            }
            if (statusFilter != null && !statusFilter.trim().isEmpty()) {
                sql.append(" AND status = ?");
                params.add(statusFilter);
            }
            sql.append(" ORDER BY id DESC LIMIT ? OFFSET ?");
            params.add(pageSize);
            params.add((page - 1) * pageSize);
            statement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                blogs.add(getFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return blogs;
    }

    public int countBlogsWithFilter(String searchTitle, String statusFilter) {
        int count = 0;
        try {
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM blogs WHERE 1=1");
            List<Object> params = new ArrayList<>();
            if (searchTitle != null && !searchTitle.trim().isEmpty()) {
                sql.append(" AND title LIKE ?");
                params.add("%" + searchTitle + "%");
            }
            if (statusFilter != null && !statusFilter.trim().isEmpty()) {
                sql.append(" AND status = ?");
                params.add(statusFilter);
            }
            statement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources();
        }
        return count;
    } 
    
}
