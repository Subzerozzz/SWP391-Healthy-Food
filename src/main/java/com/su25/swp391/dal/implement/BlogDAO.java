package com.su25.swp391.dal.implement;

import com.su25.swp391.dal.DBContext;
import com.su25.swp391.dal.I_DAO;
import com.su25.swp391.entity.Blog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlogDAO extends DBContext implements I_DAO<Blog> {

    @Override
    public List<Blog> findAll() {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM blogs ";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                blogs.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all blogs: " + e.getMessage());
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
    public boolean update(Blog blog) {
        String sql = "UPDATE blogs SET title = ?, author = ?, brief_info = ?, content=?,created_date=?, thumbnailblogs=?  WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, blog.getTitle());
            statement.setString(2, blog.getAuthor());
            statement.setString(3, blog.getBrief_info());
            statement.setString(4, blog.getContent());
            statement.setDate(5, blog.getBirth_date());
            statement.setString(6, blog.getThumbnailblogs());
            statement.setInt(7, blog.getId());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating blog: " + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public boolean delete(Blog blog) {
        String sql = "DELETE FROM blogs WHERE id = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, blog.getId());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting blog: " + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    @Override
    public int insert(Blog blog) {
        String sql = "INSERT INTO blogs (title, author, brief_info, content, created_date, thumbnailblogs,status) VALUES (?, ?, ?, ?, ?, ?,?)";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, blog.getTitle());
            statement.setString(2, blog.getAuthor());
            statement.setString(3, blog.getBrief_info());
            statement.setString(4, blog.getContent());
            statement.setDate(5, blog.getBirth_date());
            statement.setString(6, blog.getThumbnailblogs());
            statement.setString(7, blog.getStatus());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating blog failed, no rows affected.");
            }

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Creating blog failed, no ID obtained.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding blog: " + e.getMessage());
            return -1;
        } finally {
            closeResources();
        }
    }

    @Override
    public Blog getFromResultSet(ResultSet rs) throws SQLException {
        Blog blog = new Blog();
        blog.setId(rs.getInt("id"));
        blog.setTitle(rs.getString("title"));
        blog.setAuthor(rs.getString("author"));
        blog.setBrief_info(rs.getString("brief_info"));
        blog.setContent(rs.getString("content"));
        blog.setThumbnailblogs(rs.getString("thumbnailblogs"));
        blog.setStatus(rs.getString("status"));
        blog.setBirth_date(rs.getDate("created_date"));
        return blog;
    }

    @Override
    public Blog findById(Integer id) {
        try {
            String sql = "SELECT * FROM blogs WHERE id = ?";
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

    public boolean isBlogTitleExists(String title) {
        String sql = "SELECT COUNT(*) FROM blogs WHERE title = ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, title);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error checking blog title: " + e.getMessage());
        } finally {
            closeResources();
        }
        return false;
    }

    public boolean isBlogTitleExists(String title, Integer id) {
        String sql = "SELECT COUNT(*) FROM blogs WHERE title = ? AND id != ?";

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setInt(2, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error checking blog title: " + e.getMessage());
        } finally {
            closeResources();
        }

        return false;
    }

    // dem so luong blog trong database
    public int getTotalBlog() {
        String sql = "SELECT COUNT(*) FROM blogs";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error checking blog title: " + e.getMessage());
        } finally {
            closeResources();
        }
        return 0;
    }
//    public static void main(String[] args) {
//        BlogDAO blogDao=new BlogDAO();
//        int count =blogDao.getTotalBlog();
//        System.out.println(count);
//    }

    public List<Blog> pagingBlog(int index) {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT * FROM blogs\n"
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
//    public static void main(String[] args) {
//        BlogDAO blog = new BlogDAO();
//        List<Blog> list = blog.pagingBlog(1);
//        for (Blog blog1 : list) {
//             System.out.println(blog1);
//        }
//    }
    public List<Blog> findBlogsWithFilter(String title, String status, int page, int pageSize) {
        List<Blog> blogs = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM blogs WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // Add title filter if provided
        if (title != null && !title.isEmpty()) {
            sqlBuilder.append(" AND title LIKE ?");
            params.add("%" + title + "%");
        }

        // Add status filter if provided
        if (status != null && !status.isEmpty()) {
            sqlBuilder.append(" AND status = ?");
            params.add(status);
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
                blogs.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Error finding blogs with filter: " + e.getMessage());
        } finally {
            closeResources();
        }
        return blogs;
    }
    public int countBlogsWithFilter(String title, String status) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT COUNT(*) FROM blogs WHERE 1=1");
        List<Object> params = new ArrayList<>();
        
        // Add title filter if provided
        if (title != null && !title.isEmpty()) {
            sqlBuilder.append(" AND title LIKE ?");
            params.add("%" + title + "%");
        }
        
        // Add status filter if provided
        if (status != null && !status.isEmpty()) {
            sqlBuilder.append(" AND status = ?");
            params.add(status);
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
        } catch (SQLException e) {
            System.out.println("Error counting blogs with filter: " + e.getMessage());
        } finally {
            closeResources();
        }
        return 0;
    }
    public List<Blog> searchBlogsByTitleorStatus(String keyword, int offset, int pageSize) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM blogs WHERE title LIKE ? OR author LIKE ? LIMIT ? OFFSET ?";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            String likeKeyword = "%" + keyword + "%";
            statement.setString(1, likeKeyword);
            statement.setString(2, likeKeyword);
            statement.setInt(3, pageSize);
            statement.setInt(4, offset);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                blogs.add(getFromResultSet(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Error searching blogs with pagination: " + e.getMessage());
        } finally {
            closeResources();
        }
        return blogs;
    }
   public int countBlogsBySearch(String keyword) {
    String sql = "SELECT COUNT(*) FROM blogs WHERE title LIKE ? OR author LIKE ?";
    try {
        connection = getConnection();
        statement = connection.prepareStatement(sql);
        String likeKeyword = "%" + keyword + "%";
        statement.setString(1, likeKeyword);
        statement.setString(2, likeKeyword);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
    } catch (Exception e) {
        System.out.println("Error counting blogs by search: " + e.getMessage());
    } finally {
        closeResources();
    }
    return 0;
}

}
