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
import java.io.File;
import java.util.List;

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
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String briefinfo = request.getParameter("briefinfo");
        String context = request.getParameter("context");
        String date = request.getParameter("date");
        String filename = request.getParameter("filename"); // For file upload, use getPart instead

        Blog newBlog = new Blog();
        newBlog.setTitle(title);
        newBlog.setAuthor(author);
        newBlog.setBriefInfo(briefinfo);
        newBlog.setDescription(description);
        newBlog.setDate(date);
        newBlog.setImage(filename); // Adjust if you handle file upload via Part

        try {
            blogDAO.insert(newBlog);
            response.sendRedirect("manage-blog");
        } catch (Exception e) {
            request.setAttribute("error", "Failed to create blog: " + e.getMessage());
            request.getRequestDispatcher("/view/nutritionist/blog/addBlog.jsp").forward(request, response);
        }
    }

    private void blogUpdateDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String briefinfo = request.getParameter("briefinfo");
        String description = request.getParameter("description");
        String date = request.getParameter("date");
        String filename = request.getParameter("filename");

        Blog blog = new Blog();
        blog.setId(id);
        blog.setTitle(title);
        blog.setAuthor(author);
        blog.setBriefInfo(briefinfo);
        blog.setDescription(description);
        blog.setDate(date);
        blog.setImage(filename);

        try {
            blogDAO.update(blog);
            response.sendRedirect("manage-blog");
        } catch (Exception e) {
            request.setAttribute("error", "Failed to update blog: " + e.getMessage());
            request.getRequestDispatcher("/view/nutritionist/blog/editBlog.jsp").forward(request, response);
        }
    }

    private void listBlogDoGet(HttpServletRequest request, HttpServletResponse response) {
       RequestDispatcher dispatcher = request.getRequestDispatcher("/view/nutritionist/blog/listBlog.jsp");
       dispatcher.forward(request, response);
    }

    private void showAddDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       RequestDispatcher dispatcher = request.getRequestDispatcher("/view/nutritionist/blog/addBlog.jsp");
       dispatcher.forward(request, response);
    }

    private void showEditDoGet(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
