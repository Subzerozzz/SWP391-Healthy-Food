<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <!DOCTYPE html>
            <html lang="zxx">

            <!-- Mirrored from templates.hibootstrap.com/hilo/default/blog-details.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:25 GMT -->

            <head>
                <!-- Required Meta Tags -->
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <c:if test="${blog != null && blog.brief_info != null}">
                    <meta name="description" content="${fn:substring(blog.brief_info, 0, 160)}">
                </c:if>

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
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

                <!--=== Title & Favicon ===-->
                <title>
                    <c:choose>
                        <c:when test="${blog != null && blog.title != null}">
                            ${blog.title} - Healthy Food Blog
                        </c:when>
                        <c:otherwise>
                            Blog Detail - Healthy Food
                        </c:otherwise>
                    </c:choose>
                </title>
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
                                    <h2>Blog Details</h2>
                                    <ul>
                                        <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                                        <li><a href="${pageContext.request.contextPath}/blog">Blog</a></li>
                                        <li>
                                            <c:choose>
                                                <c:when test="${fn:length(blog.title) > 30}">
                                                    ${fn:substring(blog.title, 0, 30)}...
                                                </c:when>
                                                <c:otherwise>
                                                    ${blog.title}
                                                </c:otherwise>
                                            </c:choose>
                                        </li>
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

                <!-- Blog Details Area -->
                <div class="blog-area white-bg pt-0 pb-0 mb-70">
                    <div class="container">
                        <div class="row">
                            <!-- Blog Post Start -->
                            <div class="col-lg-9">
                                <div class="blog_area">
                                    <article class="blog_single blog-details">
                                        <c:if test="${blog.thumbnailblogs != null && !empty blog.thumbnailblogs}">
                                            <div class="post-thumbnail mb-4">
                                                <img src="${pageContext.request.contextPath}/${blog.thumbnailblogs}"
                                                    alt="${blog.title}" class="img-fluid"
                                                    style="width: 100%; height: 400px; object-fit: cover;">
                                            </div>
                                        </c:if>
                                        <header class="entry-header">
                                            <h1 class="entry-title">${blog.title}</h1>
                                            <span class="post-author">
                                                <span class="post-by"> Posted by: </span> ${blog.author != null ?
                                                blog.author : 'Admin'}
                                            </span>
                                            <span class="post-separator">|</span>
                                            <span class="blog-post-date">
                                                <i class="fas fa-calendar-alt"></i>
                                                <fmt:formatDate value="${blog.created_Date}" pattern="MMMM dd, yyyy" />
                                            </span>
                                        </header>
                                        <div class="postinfo-wrapper">
                                            <div class="post-info">
                                                <div class="entry-summary blog-post-description">
                                                    <c:out value="${blog.content}" escapeXml="false" />
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Social Sharing -->
                                        <div class="social-share mt-4 mb-4">
                                            <h5>Share this post:</h5>
                                            <div class="social-buttons">
                                                <a href="https://www.facebook.com/sharer/sharer.php?u=${pageContext.request.requestURL}?${pageContext.request.queryString}"
                                                    target="_blank" class="btn btn-primary btn-sm">
                                                    <i class="fab fa-facebook-f"></i> Facebook
                                                </a>
                                                <a href="https://twitter.com/intent/tweet?url=${pageContext.request.requestURL}?${pageContext.request.queryString}&text=${blog.title}"
                                                    target="_blank" class="btn btn-info btn-sm">
                                                    <i class="fab fa-twitter"></i> Twitter
                                                </a>
                                                <a href="https://www.linkedin.com/sharing/share-offsite/?url=${pageContext.request.requestURL}?${pageContext.request.queryString}"
                                                    target="_blank" class="btn btn-secondary btn-sm">
                                                    <i class="fab fa-linkedin-in"></i> LinkedIn
                                                </a>
                                            </div>
                                        </div>
                                    </article>

                                    <!-- Navigation -->
                                    <div class="blog-post-navigation mt-5">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="prev-post">
                                                    <a href="${pageContext.request.contextPath}/blog"
                                                        class="btn btn-outline-primary">
                                                        <i class="fa fa-angle-left mr-2"></i> Back to Blog List
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="col-md-6 text-right">
                                                <div class="next-post">
                                                    <c:if test="${recentBlogs != null && !empty recentBlogs}">
                                                        <a href="blog?action=detail&id=${recentBlogs[0].id}"
                                                            class="btn btn-outline-success">
                                                            Next Post <i class="fa fa-angle-right ml-2"></i>
                                                        </a>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Related Posts -->
                                    <c:if test="${recentBlogs != null && fn:length(recentBlogs) > 1}">
                                        <div class="related-posts mt-5">
                                            <h4>You might also like:</h4>
                                            <div class="row">
                                                <c:forEach var="relatedBlog" items="${recentBlogs}" begin="0" end="1">
                                                    <div class="col-md-6 mb-3">
                                                        <div class="card">
                                                            <c:if
                                                                test="${relatedBlog.thumbnailblogs != null && !empty relatedBlog.thumbnailblogs}">
                                                                <img src="${pageContext.request.contextPath}/${relatedBlog.thumbnailblogs}"
                                                                    class="card-img-top" alt="${relatedBlog.title}"
                                                                    style="height: 150px; object-fit: cover;">
                                                            </c:if>
                                                            <div class="card-body">
                                                                <h6 class="card-title">
                                                                    <a
                                                                        href="blog?action=detail&id=${relatedBlog.id}">${relatedBlog.title}</a>
                                                                </h6>
                                                                <small class="text-muted">
                                                                    <fmt:formatDate value="${relatedBlog.created_Date}"
                                                                        pattern="MMM dd, yyyy" />
                                                                </small>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
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
                                        <h5>Search</h5>
                                        <div class="search__sidbar">
                                            <form action="blog" method="get">
                                                <input id="search_input" name="search" placeholder="Search..."
                                                    class="input_text" type="text">
                                                <button id="blogsearchsubmit" type="submit" class="button">
                                                    <i class="fa fa-search"></i>
                                                </button>
                                            </form>
                                        </div>
                                    </div>

                                    <div class="product-filter mb-35">
                                        <h5>Recent Posts</h5>
                                        <div class="blog_Archives__sidbar">
                                            <ul>
                                                <c:forEach var="recentBlog" items="${recentBlogs}" end="4">
                                                    <li>
                                                        <a href="blog?action=detail&id=${recentBlog.id}"
                                                            class="${recentBlog.id == blog.id ? 'active' : ''}">
                                                            ${recentBlog.title}
                                                        </a>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Blog Sidebar End -->
                        </div>
                    </div>
                </div>
                <!-- Blog Details Area End -->
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

            <!-- Mirrored from templates.hibootstrap.com/hilo/default/blog-details.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:28 GMT -->

            </html>