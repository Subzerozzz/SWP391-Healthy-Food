package com.su25.swp391.controller.nutri;

import com.su25.swp391.dal.implement.BlogDAO;
import com.su25.swp391.entity.Blog;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;


 @WebServlet(name = "ManageBlogController", urlPatterns = {"/manage-blog"})
public class ManageBlogController {

    private BlogDAO blogDAO;

    public void init() {
        blogDAO = new BlogDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                blogListDoGet(request, response);
                break;
            case "create":
                blogCreateDoGet(request, response);
                break;
            case "delete":
                blogDeleteDoGet(request, response);
                break;
            case "update":
                blogUpdateDoGet(request, response);
                break;
        }
    }

    private void blogUpdateDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        BlogDAO blogDao = new BlogDAO();
        Blog blog = blogDao.findById(id);
        if (blog != null) {
            List<Blog> Blogs = blogDao.findAll();
            request.setAttribute("blog", blog);
            request.getRequestDispatcher("/view/nutrition/blog_edit").forward(request, response);

        }
        response.sendRedirect(request.getContextPath() + "view/nutritionist/dashboard.jsp");
    }

    private void blogDeleteDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        BlogDAO blogDao = new BlogDAO();
        Blog blog = blogDao.findById(id);
        if (blog != null) {
            boolean deleted = blogDao.delete(blog);
            if (deleted) {
                request.getSession().setAttribute("toastMessage", "Blog deleted successfully");
                request.getSession().setAttribute("toastType", "success");
            } else {
                request.getSession().setAttribute("toastMessage", "Failed to delete blog");
                request.getSession().setAttribute("toastType", "error");
            }
        } else {
            request.getSession().setAttribute("toastMessage", "Blog not found");
            request.getSession().setAttribute("toastType", "error");
        }

        // Redirect to list page
        response.sendRedirect(request.getContextPath() + "/view/nutrition/dashboard");
    }

    private void blogCreateDoGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String brief_info = request.getParameter("brief_info");
            String context = request.getParameter("context");
            String thumbnailblogs = request.getParameter("thumbnailblogs");
            //image
            Part part = request.getPart("image");
            if (part.getSubmittedFileName() == null || part.getSubmittedFileName().trim().isEmpty() || part == null) {

            } else {
                //duong dan luu anh
                String path = request.getServletContext().getRealPath("/image");
                File dir = new File(path);
                // Xem duong dan da ton tai chua
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File image = new File(dir, part.getSubmittedFileName());
                //ghi file vao trong duong dan
                part.write(image.getAbsolutePath());
                // Lay ra cai context cua project
                String pathOfFile = request.getContextPath() + "/image/" + image.getName();
            }
            BlogDAO blogDao = new BlogDAO();
            Blog blog = Blog.builder().id(id)
                    .title(title)
                    .author(author)
                    .brief_info(brief_info)
                    .context(context)
                    .thumbnailblogs(thumbnailblogs)
                    .build();
            blogDao.insert(blog);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void blogListDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BlogDAO blogDao = new BlogDAO();
        List<Blog> blog = blogDao.findAll();
        request.setAttribute("blogs", blog);
        request.getRequestDispatcher("manage-blog.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
//        if (action == null) {
//            action = "list";
//        }
//        switch (action) {
//            case "list":
//                blogListDoPost(request, response);
//                break;
//            case "create":
//                blogCreateDoPost(request, response);
//                break;
//            case "delete":
//                blogDeleteDoPost(request, response);
//                break;
//            case "update":
//                blogUpdateDoPost(request, response);
//                break;
//        }
    }
}
