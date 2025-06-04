package com.su25.swp391.controller.nutri;

import com.su25.swp391.dal.implement.BlogDAO;
import com.su25.swp391.entity.Blog;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import static jakarta.ws.rs.core.Response.status;
import java.io.File;
import java.sql.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import static junit.runner.Version.id;

@WebServlet(name = "ManageBlogController", urlPatterns = {"/manage-blog"})
public class ManageBlogController extends HttpServlet {

    private BlogDAO blogDAO;

    public void init() {
        blogDAO = new BlogDAO();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listBlogDoGet(request, response);
                break;
            case "add":
                showAddDoGet(request, response);
                break;
            case "edit":
                showEditDoGet(request, response);
                break;
            case "view":
                showDetailDoGet(request, response);
                break;
            case "delete":
                deleteBlogDoGet(request, response);
                break;
            default:
                listBlogDoGet(request, response);
                break;
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "add":
                blogCreateDoPost(request, response);
                break;
            case "edit":
                blogUpdateDoPost(request, response);
                break;
        }
    }

    private void blogCreateDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String briefinfo = request.getParameter("briefinfo");
            String context = request.getParameter("context");
            String dateStr = request.getParameter("birth_date");
            //Xu ly thong tin ve nhap date
            Date date = null;
            try {
                if (dateStr != null && dateStr.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = sdf.parse(dateStr);
                    date = new java.sql.Date(utilDate.getTime()); // ép sang java.sql.Date
                }
            } catch (ParseException e) {
                request.setAttribute("error", "Đinh dang sai form ");
                request.getRequestDispatcher("/view/nutritionist/blog/addBlog.jsp").forward(request, response);
                return;
            }
            // Validate form data
            Map<String, String> errors = validateBlogData(title, context, 0);
            if (!errors.isEmpty()) {
                // Store errors and form data in session for redisplay
                request.getSession().setAttribute("errors", errors);
                Map<String, String[]> formData = new HashMap<>();
                if (!errors.isEmpty()) {
                    //nếu có lỗi nhập thông tin loi
                    request.getSession().setAttribute("Err", errors);
                    //laay du lieu trc do nguoi dung nhap 
                    request.getSession().setAttribute("formData", request.getParameterMap());
                    //chuyen huong ve form them tai khoan
                    request.getRequestDispatcher("/view/nutritionist/blog/addBlog.jsp").forward(request, response);
                }
            }
            //Tạo đối tượng blog mới
            Blog newBlog = Blog.builder()
                    .title(title)
                    .author(author)
                    .brief_info(briefinfo)
                    .context(context)
                    .birth_date(date)
                    .build();
            BlogDAO BlogDao = new BlogDAO();
            boolean isSuccses = BlogDao.insert(newBlog) > 0;
            if (isSuccses) {
                request.getSession().setAttribute("totalMess", "Add new Blog Success");
                request.getSession().setAttribute("totalType", "Success");
                listBlogDoGet(request, response);
            } else {
                request.getSession().setAttribute("totalMess", "Fail to add Blog");
                request.getSession().setAttribute("totalType", "Err");
                request.getRequestDispatcher("/view/nutritionist/blog/addBlog.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.getSession().setAttribute("totalMess", "Fail to add Account");
            request.getSession().setAttribute("totalType", "Err" + e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("/view/nutritionist/blog/addBlog.jsp").forward(request, response);
        }
    }

    private void blogUpdateDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String briefinfo = request.getParameter("brief_info");
            String description = request.getParameter("context");
            String dateStr = request.getParameter("birth_date");
            //Xu ly thong tin ve nhap date
            Date date = null;
            try {
                if (dateStr != null && dateStr.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = sdf.parse(dateStr);
                    date = new java.sql.Date(utilDate.getTime()); // ép sang java.sql.Date
                }
            } catch (ParseException e) {
                request.setAttribute("error", "Đinh dang sai form ");
                request.getRequestDispatcher("/view/nutritionist/blog/editBlog.jsp").forward(request, response);
                return;
            }
            BlogDAO blogDao = new BlogDAO();
            Blog blog = blogDao.findById(id);
            blog.setTitle(title);
            blog.setAuthor(author);
            blog.setBrief_info(briefinfo);
            blog.setContext(description);
            blog.setBirth_date(date);
            boolean isSuccess = blogDao.update(blog);
            if (isSuccess) {
                request.getSession().setAttribute("totalMess", "update new Blog Success");
                request.getSession().setAttribute("totalType", "Success");
                response.sendRedirect("manage-blog");
                return;
            } else {
                request.getSession().setAttribute("totalMess", "Fail to update Account");
                request.getSession().setAttribute("totalType", "Err");
                request.setAttribute("error", "Update failed");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/nutritionist/blog/editBlog.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.getSession().setAttribute("totalMess", "Fail to Update Account");
            request.getSession().setAttribute("totalType", "Err" + e.getMessage());
            response.sendRedirect("manage-blog");
            return;
        }
    }

    private void listBlogDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Blog> list = blogDAO.findAll();
        request.setAttribute("blogs", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/nutritionist/blog/listBlog.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/nutritionist/blog/addBlog.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/nutritionist/blog/editBlog.jsp");
        dispatcher.forward(request, response);
    }

    private void showDetailDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Blog list = blogDAO.findById(id);
        request.setAttribute("blog", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/nutritionist/blog/viewdetailBlog.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteBlogDoGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        BlogDAO blogDao = new BlogDAO();
        Blog blog = blogDao.findById(id);
        if (blog != null) {
            boolean delete = blogDao.delete(blog);
            if (delete) {
                request.getSession().setAttribute("toastMessage", "Blog delete succesfull");
                request.getSession().setAttribute("toastType", "success");
            } else {
                request.getSession().setAttribute("toastMessage", "Fail to delete blog");
                request.getSession().setAttribute("toastType", "error");
            }
        } else {
            request.getSession().setAttribute("toatMessage", "Blog not found");
            request.getSession().setAttribute("toastType", "error");
        }
        response.sendRedirect(request.getContextPath() + "view/nutritionist/blog/manage-blog");

    }

    private Map<String, String> validateBlogData(String title, String context, Integer id) {
        Map<String, String> errors = new HashMap<>();
        BlogDAO blogDAO = new BlogDAO();

        // Validate title
        if (title == null || title.trim().isEmpty()) {
            errors.put("title", "Blog title is required");
        } else if (title.length() > 150) {
            errors.put("title", "Blog title must be less than 150 characters");
        } else {
            // Check if title already exists
            boolean titleExists = id == null
                    ? blogDAO.isBlogTitleExists(title)
                    : blogDAO.isBlogTitleExists(title, id);

            if (titleExists) {
                errors.put("title", "Blog title already exists");
            }
        }

        // Validate content
        if (context == null || context.trim().isEmpty()) {
            errors.put("content", "Blog content is required");
        }
        return errors;
    }
}
