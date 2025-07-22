import com.su25.swp391.dal.implement.BlogDAO;
import com.su25.swp391.entity.Blog;
import com.su25.swp391.utils.TextUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller for blog functionality
 */
@WebServlet(name = "BlogController", urlPatterns = { "/blog" })
public class BlogController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Default action
        }

        switch (action) {
            case "detail":
                showBlogDetail(request, response);
                break;
            case "list":
            default:
                showBlogList(request, response);
                break;
        }
    }

    /**
     * Show blog list with pagination and filtering
     */
    private void showBlogList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTitle = request.getParameter("search");
        String status = "Inactive"; // Thay đổi từ "published" thành "Inactive" để khớp với database

        int page = 1;
        int pageSize = 5;

        try {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                if (page < 1) {
                    page = 1;
                }
            }
        } catch (NumberFormatException e) {
            page = 1;
        }

        BlogDAO blogDAO = new BlogDAO();
        List<Blog> blogs = blogDAO.filterBlogsWithPagination(status, searchTitle, page, pageSize);

        // Tạo preview text cho mỗi blog
        for (Blog blog : blogs) {
            String previewText = "";
            if (blog.getBrief_info() != null && !blog.getBrief_info().isEmpty()) {
                previewText = blog.getBrief_info();
            } else if (blog.getContent() != null && !blog.getContent().isEmpty()) {
                previewText = TextUtils.getPreviewText(blog.getContent(), 200);
            }
            blog.setBrief_info(previewText); // Sử dụng brief_info để store preview text
        }

        int totalBlogs = blogDAO.getTotalBlogCountWithFilter(status, searchTitle);
        int totalPages = (int) Math.ceil((double) totalBlogs / pageSize);

        request.setAttribute("blogs", blogs);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchTitle", searchTitle);

        // Lấy recent blogs để hiển thị trong sidebar
        List<Blog> recentBlogs = blogDAO.filterBlogsWithPagination("Inactive", null, 1, 5);
        if (recentBlogs.size() > 4) {
            recentBlogs = recentBlogs.subList(0, 4);
        }
        request.setAttribute("recentBlogs", recentBlogs);

        request.getRequestDispatcher("/view/nutritionist/blog/blog.jsp").forward(request, response);
    }

    /**
     * Show blog detail by ID
     */
    private void showBlogDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String blogIdStr = request.getParameter("id");

            if (blogIdStr == null || blogIdStr.isEmpty()) {
                setToastMessage(request, "Blog ID is required", "error");
                response.sendRedirect(request.getContextPath() + "/blog");
                return;
            }

            int blogId = Integer.parseInt(blogIdStr);

            BlogDAO blogDAO = new BlogDAO();
            Blog blog = blogDAO.findById(blogId);

            if (blog == null) {
                setToastMessage(request, "Blog not found", "error");
                response.sendRedirect(request.getContextPath() + "/blog");
                return;
            }

            if (!"Inactive".equals(blog.getStatus())) {
                setToastMessage(request, "Blog is not available", "error");
                response.sendRedirect(request.getContextPath() + "/blog");
                return;
            }

            List<Blog> recentBlogs = blogDAO.filterBlogsWithPagination("Inactive", null, 1, 5);
            if (recentBlogs != null) {
                recentBlogs.removeIf(b -> b.getId() == blog.getId());
                if (recentBlogs.size() > 4) {
                    recentBlogs = recentBlogs.subList(0, 4);
                }
            }

            request.setAttribute("blog", blog);
            request.setAttribute("recentBlogs", recentBlogs);

            request.getRequestDispatcher("/view/nutritionist/blog/blogdetail.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            setToastMessage(request, "Invalid blog ID", "error");
            response.sendRedirect(request.getContextPath() + "/blog");
        } catch (Exception e) {
            setToastMessage(request, "Error loading blog: " + e.getMessage(), "error");
            response.sendRedirect(request.getContextPath() + "/blog");
        }
    }

    /**
     * Set toast message in session
     */
    private void setToastMessage(HttpServletRequest request, String message, String type) {
        request.getSession().setAttribute("toastMessage", message);
        request.getSession().setAttribute("toastType", type);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Blog Controller";
    }
}
