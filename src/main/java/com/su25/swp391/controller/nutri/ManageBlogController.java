package com.su25.swp391.controller.nutri;

import com.su25.swp391.dal.implement.BlogDAO;
import com.su25.swp391.entity.Blog;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "ManageBlogController", urlPatterns = {"/manage-blog"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class ManageBlogController extends HttpServlet {

    private final String UPLOAD_DIRECTORY = "uploads";

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
            case "search":
                searchByNameDoGet(request, response);
                break;
            case "filter":
                handleFilter(request, response);
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
            String content = request.getParameter("content");
            String dateStr = request.getParameter("date");
            String status = request.getParameter("status");
            Date date = null;
            try {
                if (dateStr != null && !dateStr.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = sdf.parse(dateStr);
                    date = new java.sql.Date(utilDate.getTime());
                } else {
                    date = new java.sql.Date(System.currentTimeMillis());
                }
            } catch (ParseException e) {
                request.setAttribute("error", "Invalid date format");
                request.getRequestDispatcher("/view/nutritionist/blog/addBlog.jsp").forward(request, response);
                return;
            }

            Map<String, String> errors = validateBlogData(title, content, 0);
            if (!errors.isEmpty()) {
                request.getSession().setAttribute("errors", errors);
                request.getSession().setAttribute("formData", request.getParameterMap());
                request.getRequestDispatcher("/view/nutritionist/blog/addBlog.jsp").forward(request, response);
                return;
            }

            String fileName = null;
            try {
                Part filePart = request.getPart("filename");
                if (filePart != null && filePart.getSize() > 0) {
                    String originalFileName = filePart.getSubmittedFileName();
                    String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    fileName = UUID.randomUUID().toString() + fileExtension;

                    String applicationPath = request.getServletContext().getRealPath("");
                    String uploadPath = applicationPath + File.separator + UPLOAD_DIRECTORY;

                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    InputStream inputStream = filePart.getInputStream();
                    String filePath = uploadPath + File.separator + fileName;
                    FileOutputStream outputStream = new FileOutputStream(filePath);

                    int read;
                    byte[] bytes = new byte[1024];
                    while ((read = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                    outputStream.close();
                }
            } catch (Exception e) {
                request.getSession().setAttribute("totalMess", "Error uploading image: " + e.getMessage());
                request.getSession().setAttribute("totalType", "Err");
                request.getRequestDispatcher("/view/nutritionist/blog/addBlog.jsp").forward(request, response);
                return;
            }
            Blog newBlog = Blog.builder()
                    .title(title)
                    .author("HarryPorter")
                    .brief_info(briefinfo)
                    .content(content)
                    .birth_date(date)
                    .thumbnailblogs(fileName != null ? UPLOAD_DIRECTORY + "/" + fileName : null)
                    .status("Inactive")
                    .build();

            BlogDAO BlogDao = new BlogDAO();
            boolean isSuccses = BlogDao.insert(newBlog) > 0;
            if (isSuccses) {
                HttpSession session = request.getSession();
                request.getSession().setAttribute("totalMess", "Add new Blog Success");
                request.getSession().setAttribute("totalType", "Success");
                session.setAttribute("isAdd", true);
                listBlogDoGet(request, response);
                return;
            } else {
                request.getSession().setAttribute("totalMess", "Fail to add Blog");
                request.getSession().setAttribute("totalType", "Err");
                request.getRequestDispatcher("/view/nutritionist/blog/addBlog.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.getSession().setAttribute("totalMess", "Fail to add Account: " + e.getMessage());
            request.getSession().setAttribute("totalType", "Err");
            e.printStackTrace();
            request.getRequestDispatcher("/view/nutritionist/blog/addBlog.jsp").forward(request, response);
        }
    }

    private void blogUpdateDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String briefinfo = request.getParameter("briefinfo");
            String description = request.getParameter("content");
            String dateStr = request.getParameter("date");
            //Xu ly thong tin ve nhap date
            Date date = null;
            try {
                if (dateStr != null && !dateStr.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = sdf.parse(dateStr);
                    date = new java.sql.Date(utilDate.getTime()); // ép sang java.sql.Date
                } else {
                    date = new java.sql.Date(System.currentTimeMillis());
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
            blog.setContent(description);
            blog.setBirth_date(date);
            boolean isSuccess = blogDao.update(blog);
            if (isSuccess) {
                HttpSession session = request.getSession();
                request.getSession().setAttribute("totalMess", "update new Blog Success");
                request.getSession().setAttribute("totalType", "Success");
                session.setAttribute("isUpdate", true);
                response.sendRedirect("manage-blog");
                return;
            } else {
                request.getSession().setAttribute("totalMess", "Fail to update Account");
                request.getSession().setAttribute("totalType", "Err");
                request.setAttribute("error", "Update failed");
                response.sendRedirect("/view/nutritionist/blog/editBlog.jsp");
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

        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int currentPage = Integer.parseInt(indexPage);

        int totalBlogs = blogDAO.getTotalBlog();
        int totalPage = totalBlogs / 10;
        if (totalBlogs % 10 != 0) {
            totalPage++;
        }

        List<Blog> list = blogDAO.pagingBlog(currentPage);

        request.setAttribute("blogs", list);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/nutritionist/blog/listBlog.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/nutritionist/blog/addBlog.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        BlogDAO blogDao = new BlogDAO();
        Blog blog = blogDao.findById(id);
        if (blog != null) {
            request.setAttribute("blog", blog);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/nutritionist/blog/editBlog.jsp");
            dispatcher.forward(request, response);
        } else {
            request.getSession().setAttribute("toastMessage", "Blog not found");
            request.getSession().setAttribute("toastType", "error");
            response.sendRedirect(request.getContextPath() + "/view/nutritionist/blog/editBlog.jsp");
        }

    }

    private void showDetailDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Blog blog = blogDAO.findById(id);
        request.setAttribute("blog", blog);
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
                HttpSession session = request.getSession();
                request.getSession().setAttribute("toastMessage", "Blog delete succesfull");
                request.getSession().setAttribute("toastType", "success");
                session.setAttribute("isDelete", true);
            } else {
                request.getSession().setAttribute("toastMessage", "Fail to delete blog");
                request.getSession().setAttribute("toastType", "error");
            }
        } else {
            request.getSession().setAttribute("toatMessage", "Blog not found");
            request.getSession().setAttribute("toastType", "error");
        }

        response.sendRedirect(request.getContextPath() + "/manage-blog");

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
 private void searchByNameDoGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchKeyword = request.getParameter("search");
        String indexStr = request.getParameter("index");
        int currentPage = 1;
        int pageSize = 10; // số bản ghi mỗi trang, bạn có thể chỉnh lại

        if (indexStr != null) {
            try {
                currentPage = Integer.parseInt(indexStr);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/manage-blog?action=list");
            return;
        }
        searchKeyword = searchKeyword.trim();
        int offset = (currentPage - 1) * pageSize;
        // Lấy danh sách blog theo từ khóa và phân trang
        List<Blog> listBlog = blogDAO.searchBlogsByTitleorStatus(searchKeyword, offset, pageSize);
        // Lấy tổng số blog phù hợp để tính tổng trang
        int totalBlogs = blogDAO.countBlogsBySearch(searchKeyword);
        int totalPage = (int) Math.ceil((double) totalBlogs / pageSize);
        request.setAttribute("blogs", listBlog);
        request.setAttribute("search", searchKeyword);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.getRequestDispatcher("/view/nutritionist/blog/listBlog.jsp").forward(request, response);
    }

    private void handleFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String statusParam = request.getParameter("status");
        Boolean status = (statusParam != null && !statusParam.isEmpty()) ? Boolean.parseBoolean(statusParam) : null;

        // Lấy tham số phân trang
        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");

        int currentPage = 1;
        int pageSize = 10;

        try {
            if (pageParam != null && !pageParam.isEmpty()) {
                currentPage = Integer.parseInt(pageParam);
                if (currentPage < 1) {
                    currentPage = 1;
                }
            }
            if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
                pageSize = Integer.parseInt(pageSizeParam);
                if (pageSize < 5) {
                    pageSize = 5;
                }
                if (pageSize > 50) {
                    pageSize = 50;
                }
            }
        } catch (NumberFormatException e) {
            currentPage = 1;
            pageSize = 10;
        }

        // Lấy danh sách đã lọc với phân trang
        List<Blog> filteredBlog = blogDAO.findBlogsWithFilter(title, title, pageSize, pageSize);

        // Tính toán thông tin phân trang cho bộ lọc
        int totalBlogs = blogDAO.getTotalBlog();
        int totalPages = (int) Math.ceil((double) totalBlogs / pageSize);

        // Thiết lập các thuộc tính cho JSP
        request.setAttribute("listBlogDoGet", filteredBlog);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalBlogs", totalBlogs);
        request.setAttribute("title", title);
        request.setAttribute("status", statusParam);

        // Tính toán phạm vi hiển thị
        int startRecord = (currentPage - 1) * pageSize + 1;
        int endRecord = Math.min(startRecord + pageSize - 1, totalBlogs);
        request.setAttribute("startRecord", startRecord);
        request.setAttribute("endRecord", endRecord);

        request.getRequestDispatcher("/view/nutritionist/blog/listBlog.jsp").forward(request, response);
    }
}


