<%-- 
    Document   : addBlog
    Created on : May 30, 2025, 3:32:10 PM
    Author     : Predator
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<!--<![endif]-->

<style>
    /* Enhanced Modern Blog Detail Styles */
    .blog-detail-container {
        background: linear-gradient(135deg, #f8fafc 0%, #e2e8f0 100%);
        min-height: 100vh;
        padding: 2rem 0;
    }

    .blog-card {
        background: rgba(255, 255, 255, 0.98);
        backdrop-filter: blur(20px);
        border-radius: 24px;
        box-shadow: 
            0 25px 50px rgba(0, 0, 0, 0.08),
            0 10px 25px rgba(0, 0, 0, 0.05),
            inset 0 1px 0 rgba(255, 255, 255, 0.9);
        border: 1px solid rgba(226, 232, 240, 0.5);
        overflow: hidden;
        transition: all 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
        position: relative;
    }

    .blog-card::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        height: 4px;
        background: linear-gradient(90deg, #4299e1, #3182ce, #2b77cb, #2c5282);
        background-size: 400% 100%;
        animation: gradientShift 3s ease infinite;
    }

    @keyframes gradientShift {
        0%, 100% { background-position: 0% 50%; }
        50% { background-position: 100% 50%; }
    }

    .blog-header {
        background: linear-gradient(135deg, rgba(66, 153, 225, 0.05), rgba(43, 119, 203, 0.05));
        padding: 2rem;
        text-align: center;
        border-bottom: 1px solid rgba(226, 232, 240, 0.3);
    }

    .blog-title {
        font-size: 2.5rem;
        font-weight: 800;
        color: red; /* Dùng màu đỏ trực tiếp */
        margin-bottom: 1rem;
        line-height: 1.2;
        text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .blog-meta-wrapper {
        display: flex;
        justify-content: space-between;
        align-items: center;
        flex-wrap: wrap;
        margin-top: 1.5rem;
        padding: 0 2rem;
    }

    .meta-item {
        display: flex;
        align-items: center;
        gap: 0.5rem;
        background: rgba(255, 255, 255, 0.7);
        padding: 0.75rem 1.25rem;
        border-radius: 50px;
        font-weight: 600;
        color: #4a5568;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        transition: all 0.3s ease;
        font-size: 25px; /* 👈 Tăng cỡ chữ */
    }



    .meta-item:hover {
        transform: translateY(-2px);
        box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
        background: rgba(255, 255, 255, 0.9);
    }

    .meta-icon {
        width: 24px;
        height: 24px;
        background: linear-gradient(135deg, #4299e1, #2b77cb);
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        color: white;
        font-size: 14px;
    }

    .blog-content {
        padding: 2.5rem;
    }

    .content-layout {
        display: flex;
        gap: 3rem;
        align-items: flex-start;
    }

    .content-left {
        flex: 1;
        min-width: 0;
    }

    .content-right {
        flex: 0 0 45%;
        position: sticky;
        top: 2rem;
    }

    .content-section {
        margin-bottom: 2.5rem;
    }

    .section-title {
        font-size: 1.4rem;
        font-weight: 700;
        color: #2d3748;
        margin-bottom: 1rem;
        position: relative;
        padding-left: 1rem;
    }

    .section-title::before {
        content: '';
        position: absolute;
        left: 0;
        top: 50%;
        transform: translateY(-50%);
        width: 4px;
        height: 100%;
        background: linear-gradient(135deg, #4299e1, #2b77cb);
        border-radius: 2px;
    }

    .brief-info {
        background: linear-gradient(135deg, rgba(66, 153, 225, 0.03), rgba(43, 119, 203, 0.03));
        border: 1px solid rgba(66, 153, 225, 0.15);
        border-radius: 16px;
        padding: 1.5rem;
        font-size: 1.1rem;
        line-height: 1.6;
        color: #4a5568;
        position: relative;
        overflow: hidden;
    }

    .brief-info::before {
        content: '"';
        position: absolute;
        top: -10px;
        left: 10px;
        font-size: 4rem;
        color: rgba(66, 153, 225, 0.15);
        font-family: serif;
    }

    .description-content {
        font-size: 1.1rem;
        line-height: 1.8;
        color: #2d3748;
        background: rgba(255, 255, 255, 0.5);
        padding: 2rem;
        border-radius: 16px;
        border: 1px solid rgba(255, 255, 255, 0.3);
        box-shadow: inset 0 2px 10px rgba(0, 0, 0, 0.05);
    }

    .image-showcase {
        text-align: center;
        margin: 2.5rem 0;
    }

    /* Improved Image Frame Styles */
    .image-frame {
        position: relative;
        display: inline-block;
        width: 100%;
        max-width: 100%;
        border-radius: 16px;
        overflow: hidden;
        background: #ffffff;
        box-shadow: 
            0 8px 32px rgba(0, 0, 0, 0.12),
            0 4px 16px rgba(0, 0, 0, 0.08),
            0 2px 8px rgba(0, 0, 0, 0.04);
        border: 1px solid rgba(226, 232, 240, 0.8);
        transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
    }

    .image-frame::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background: linear-gradient(135deg,
            rgba(255, 255, 255, 0.1) 0%,
            rgba(255, 255, 255, 0.05) 50%,
            transparent 100%);
        z-index: 2;
        pointer-events: none;
        opacity: 0;
        transition: opacity 0.3s ease;
    }

    .image-frame img {
        width: 100%;
        height: auto;
        min-height: 280px;
        max-height: 400px;
        object-fit: cover;
        display: block;
        transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
        filter: brightness(0.98) contrast(1.02) saturate(1.05);
    }

    .image-frame:hover {
        transform: translateY(-4px);
        box-shadow: 
            0 16px 48px rgba(0, 0, 0, 0.15),
            0 8px 24px rgba(0, 0, 0, 0.1),
            0 4px 12px rgba(0, 0, 0, 0.06);
        border-color: rgba(66, 153, 225, 0.3);
    }

    .image-frame:hover::before {
        opacity: 1;
    }

    .image-frame:hover img {
        filter: brightness(1.02) contrast(1.08) saturate(1.1);
    }

    .back-button {
        display: inline-flex;
        align-items: center;
        gap: 0.75rem;
        background: linear-gradient(135deg, #4299e1, #2b77cb);
        color: white;
        padding: 1rem 2rem;
        border: none;
        border-radius: 50px;
        font-size: 1.1rem;
        font-weight: 600;
        text-decoration: none;
        transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
        box-shadow: 0 8px 25px rgba(66, 153, 225, 0.25);
        position: relative;
        overflow: hidden;
    }

    .back-button::before {
        content: '';
        position: absolute;
        top: 0;
        left: -100%;
        width: 100%;
        height: 100%;
        background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.2), transparent);
        transition: left 0.5s;
    }

    .back-button:hover {
        transform: translateY(-3px);
        box-shadow: 0 15px 35px rgba(66, 153, 225, 0.35);
        color: white;
        text-decoration: none;
    }

    .back-button:hover::before {
        left: 100%;
    }

    .back-button:active {
        transform: translateY(-1px);
    }

    /* Responsive Design */
    @media (max-width: 1024px) {
        .content-layout {
            flex-direction: column;
            gap: 2rem;
        }
        
        .content-right {
            flex: none;
            position: static;
        }
        
        .image-frame img {
            min-height: 250px;
            max-height: 350px;
        }
    }

    @media (max-width: 768px) {
        .blog-detail-container {
            padding: 1rem;
        }
        
        .blog-title {
            font-size: 2rem;
        }
        
        .blog-header, .blog-content {
            padding: 1.5rem;
        }
        
        .blog-meta {
            gap: 1rem;
        }
        
        .meta-item {
            padding: 0.5rem 1rem;
            font-size: 0.9rem;
        }
        
        .content-layout {
            gap: 1.5rem;
        }
        
        .image-frame {
            border-radius: 12px;
        }
        
        .image-frame img {
            min-height: 200px;
            max-height: 280px;
        }
    }

    @media (max-width: 480px) {
        .image-frame img {
            min-height: 180px;
            max-height: 240px;
        }
    }

    /* Animation for content reveal */
    .content-section {
        opacity: 0;
        transform: translateY(20px);
        animation: revealContent 0.6s ease forwards;
    }

    .content-section:nth-child(2) { animation-delay: 0.1s; }
    .content-section:nth-child(3) { animation-delay: 0.2s; }
    .content-section:nth-child(4) { animation-delay: 0.3s; }

    @keyframes revealContent {
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
</style>

<!-- Mirrored from themesflat.co/html/remos/add-product.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:33 GMT -->
<head>
    <!-- Basic Page Needs -->
    <meta charset="utf-8">
    <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
    <title>Remos eCommerce Admin Dashboard HTML Template</title>

    <meta name="author" content="themesflat.com">

    <!-- Mobile Specific Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <!-- Theme Style -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min_1.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animation.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-select.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_1.css">

    <!-- Font -->
    <link rel="stylesheet" href="font/fonts.css">

    <!-- Icon -->
    <link rel="stylesheet" href="icon/style.css">

    <!-- Favicon and Touch Icons  -->
    <link rel="shortcut icon" href="images/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="images/favicon.png">

</head>

<body class="body">

    <!-- #wrapper -->
    <div id="wrapper">
        <!-- #page -->
        <div id="page" class="">
            <!-- layout-wrap -->
           <div class="layout-wrap">
                <!-- preload -->
                <div id="preload" class="preload-container">
                    <div class="preloading">
                        <span></span>
                    </div>
                </div>
                <!-- /preload -->
                <!-- section-menu-left -->
                <jsp:include page="../../common/nutritionist/sidebar.jsp"></jsp:include>
                <!-- /section-menu-left -->
                <!-- section-content-right -->
                <div class="section-content-right">
                    <!-- header-dashboard -->
                    <jsp:include page="../../common/nutritionist/headerDashboard.jsp"></jsp:include> 
                    <!-- /header-dashboard -->
                    <!-- main-content -->
                    <div class="main-content blog-detail-container">
                        <!-- main-content-wrap -->
                        <div class="main-content-inner">
                            <!-- main-content-wrap -->
                            <div class="main-content-wrap">
                                <div class="blog-card">
                                    <!-- Blog Header -->
                                    <div class="blog-header">
                                        <h1 class="blog-title">${blog.title}</h1>
                                    <div class="blog-meta-wrapper">
                                        <div class="meta-item author">
                                            <div class="meta-icon">✍</div>
                                            <span>Author: ${blog.author}</span>
                                        </div>
                                        <div class="meta-item date">
                                            <div class="meta-icon">📅</div>
                                            <span>Date: ${blog.birth_date}</span>
                                        </div>
                                    </div>
                                    </div>

                                    <!-- Blog Content -->
                                    <div class="blog-content">
                                        <div class="content-layout">
                                            <!-- Left Column -->
                                            <div class="content-left">
                                                <div class="content-section">
                                                    <h3 class="section-title">Brief Information</h3>
                                                    <div class="brief-info">
                                                        ${blog.brief_info}
                                                    </div>
                                                </div>

                                                <div class="content-section">
                                                    <h3 class="section-title">Content</h3>
                                                    <div class="description-content">
                                                        ${blog.content}
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Right Column -->
                                            <div class="content-right">
                                                <div class="content-section">
                                                    <h3 class="section-title">Featured Image</h3>
                                                    <div class="image-showcase">
                                                        <div class="image-frame">
                                                            <img src="${blog.thumbnailblogs}" alt="Blog thumbnail">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="content-section" style="text-align: center; margin-top: 3rem;">
                                            <a href="${pageContext.request.contextPath}/manage-blog" class="back-button">
                                                ← Back to Blog Management
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /main-content-wrap -->
                        </div>
                        <!-- /main-content-wrap -->
                        <!-- bottom-page -->
                        <div class="bottom-page">
                            
                        </div>
                        <!-- /bottom-page -->
                    </div>
                    <!-- /main-content -->
                </div>
                <!-- /section-content-right -->
            </div>
            <!-- /layout-wrap -->
        </div>
        <!-- /#page -->
    </div>
    <!-- /#wrapper -->

    <!-- Javascript -->
    <script src="${pageContext.request.contextPath}/js/jquery.min_1.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/zoom.js"></script>
    <script src="${pageContext.request.contextPath}/js/switcher.js"></script>
    <script src="${pageContext.request.contextPath}/js/theme-settings.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>

</body>

<!-- Mirrored from themesflat.co/html/remos/add-product.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:35 GMT -->
</html>