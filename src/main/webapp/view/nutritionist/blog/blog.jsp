<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@page contentType="text/html" pageEncoding="UTF-8" %>
                <!DOCTYPE html>
                <html lang="zxx">

                <!-- Mirrored from templates.hibootstrap.com/hilo/default/blog-2.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:22 GMT -->

                <head>
                    <!-- Required Meta Tags -->
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">

                    <!--=== Link of CSS Files ===-->
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/flaticon.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/boxicons.min.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.theme.default.min.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nice-select.min.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/meanmenu.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme-dark.css">
                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog-custom.css">

                    <!--=== Title & Favicon ===-->
                    <title>Blog - Healthy Food</title>
                    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
                </head>

                <body>
                    <!-- Pre Loader -->
                    <div class="preloader">
                        <div class="d-table">
                            <div class="d-table-cell">
                                <img src="${pageContext.request.contextPath}/images/preloder-img.png" alt="Images">
                                <h2>Hilo</h2>
                            </div>
                        </div>
                    </div>
                    <!-- End Pre Loader -->

                    <!-- Start Navbar Area -->
                    <jsp:include page="../../common/homePage/headerUser.jsp"></jsp:include>
                    <!-- End Navbar Area -->

                    <!-- Inner Banner Area -->
                    <div class="inner-banner-area">
                        <div class="container">
                            <div class="row align-items-center">
                                <div class="col-lg-5 col-md-4">
                                    <div class="inner-content">
                                        <h2>Blog</h2>
                                        <ul>
                                            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                                            <li>Blog</li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="col-lg-7 col-md-8">
                                    <div class="inner-img">
                                        <img src="${pageContext.request.contextPath}/images/inner-banner/inner-banner4.png"
                                            alt="Images">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Inner Banner Area End -->

                    <!-- Blog Area -->
                    <div class="blog-area white-bg pt-0 pb-0 mb-70">
                        <div class="container">
                            <div class="row">
                                <!-- Blog Post Start -->
                                <div class="col-lg-9">
                                    <div class="blog_area">
                                        <!-- Search form -->
                                        <div class="blog-search mb-50">
                                            <form action="blog" method="get">
                                                <div class="input-group">
                                                    <input type="text" class="form-control" name="search"
                                                        value="${searchTitle}" placeholder="Search blogs...">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-outline-secondary"
                                                            type="submit">Search</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                        <c:if test="${empty blogs}">
                                            <div class="alert alert-info">
                                                <h4>No blogs found</h4>
                                                <p>There are currently no blogs available to display.</p>
                                                <c:if test="${searchTitle != null && !empty searchTitle}">
                                                    <p>Try adjusting your search terms or <a href="blog">view all
                                                            blogs</a>.</p>
                                                </c:if>
                                            </div>
                                        </c:if>

                                        <c:forEach var="blog" items="${blogs}">
                                            <article class="blog_single">
                                                <c:if
                                                    test="${blog.thumbnailblogs != null && !empty blog.thumbnailblogs}">
                                                    <div class="post-thumbnail">
                                                        <img src="${pageContext.request.contextPath}/${blog.thumbnailblogs}"
                                                            alt="${blog.title}" class="img-fluid"
                                                            style="width: 100%; height: 300px; object-fit: cover;">
                                                    </div>
                                                </c:if>
                                                <header class="entry-header">
                                                    <h2 class="entry-title">
                                                        <a href="blog?action=detail&id=${blog.id}">${blog.title}</a>
                                                    </h2>
                                                    <span class="post-author">
                                                        <span class="post-by">Posted by: </span> ${blog.author != null ?
                                                        blog.author : 'Admin'}
                                                    </span>
                                                    <span class="post-separator">|</span>
                                                    <span class="blog-post-date">
                                                        <i class="fas fa-calendar-alt"></i>
                                                        <fmt:formatDate value="${blog.created_Date}"
                                                            pattern="MMMM dd, yyyy" />
                                                    </span>
                                                </header>

                                                <div class="postinfo-wrapper">
                                                    <div class="post-info">
                                                        <div class="entry-summary">
                                                            <c:choose>
                                                                <c:when
                                                                    test="${blog.brief_info != null && !empty blog.brief_info}">
                                                                    <p>
                                                                        <c:out value="${blog.brief_info}" />
                                                                    </p>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <p>No preview available.</p>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <a href="blog?action=detail&id=${blog.id}"
                                                                class="form-button">Read More</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </article>
                                        </c:forEach>

                                        <!-- Pagination -->
                                        <c:if test="${totalPages > 1}">
                                            <div class="row">
                                                <div class="col-12">
                                                    <div class="pagination-wrapper">
                                                        <ul class="pagination">
                                                            <c:if test="${currentPage > 1}">
                                                                <li class="page-item">
                                                                    <a class="page-link"
                                                                        href="blog?page=${currentPage - 1}${searchTitle != null ? '&search='.concat(searchTitle) : ''}">
                                                                        <i class="fa fa-angle-left"></i> Previous
                                                                    </a>
                                                                </li>
                                                            </c:if>

                                                            <c:forEach begin="1" end="${totalPages}" var="i">
                                                                <li
                                                                    class="page-item ${i == currentPage ? 'active' : ''}">
                                                                    <a class="page-link"
                                                                        href="blog?page=${i}${searchTitle != null ? '&search='.concat(searchTitle) : ''}">${i}</a>
                                                                </li>
                                                            </c:forEach>

                                                            <c:if test="${currentPage < totalPages}">
                                                                <li class="page-item">
                                                                    <a class="page-link"
                                                                        href="blog?page=${currentPage + 1}${searchTitle != null ? '&search='.concat(searchTitle) : ''}">
                                                                        Next <i class="fa fa-angle-right"></i>
                                                                    </a>
                                                                </li>
                                                            </c:if>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                                <!-- Blog Post End -->

                                <!-- Blog Sidebar Start -->
                                <div class="col-lg-3">
                                    <div class="blog_sidebar">
                                        <div class="product-filter mb-35">
                                            <h5>Recent Posts</h5>
                                            <div class="blog_Archives__sidbar">
                                                <ul>
                                                    <c:forEach var="recentBlog" items="${recentBlogs}" end="4">
                                                        <li>
                                                            <a
                                                                href="blog?action=detail&id=${recentBlog.id}">${recentBlog.title}</a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="product-filter mb-35">
                                            <div class="sidebar-banner single-banner">
                                                <div class="banner-img">
                                                    <a href="#"><img src="img/banner/shop-sidebar.jpg" alt=""></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Blog Sidebar End -->
                            </div>
                        </div>
                    </div>

                    <!-- Blog Area End -->

                    <!-- Footer Area -->
                    <jsp:include page="../../common/homePage/footerUser.jsp"></jsp:include>
                    <!-- Footer Area End -->

                    <!--=== Link of JS Files ===-->
                    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/jquery.nice-select.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/meanmenu.js"></script>
                    <script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/jquery.ajaxchimp.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/form-validator.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/contact-form-script.js"></script>
                    <script src="${pageContext.request.contextPath}/js/mixitup.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/custom.js"></script>
                </body>

                <!-- Mirrored from templates.hibootstrap.com/hilo/default/blog-2.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:25 GMT -->

                </html>