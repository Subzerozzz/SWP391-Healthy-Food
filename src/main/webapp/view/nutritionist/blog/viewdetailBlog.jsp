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
                    <jsp:include page="../../common/nutritionist/headerdashboard.jsp"></jsp:include> 
                    <!-- /header-dashboard -->
                    <!-- main-content -->
                    <div class="main-content">
                        <!-- main-content-wrap -->
                        <div class="main-content-inner">
                            <!-- main-content-wrap -->
                            <div class="main-content-wrap">
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                    <h3>Detail Blog</h3>
                                    <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                        <li>
                                            <a href="index"><div class="text-tiny">Dashboard</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <a href="#"><div class="text-tiny">Ecommerce</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                    </ul>
                                </div>
                                <!-- form-add-product -->
                                <form class="tf-section-2 form-add-product" >
                                    <div class="wg-box">
                                        <fieldset class="title">
                                            <div class="body-title mb-10">Blog Title: <span class="tf-color-1">*</span></div>
                                            <div class="col" style="color: red; font-size: 24px; font-weight: bold;">${blog.title}</div>
                                        </fieldset>
                                        <div class="gap22 cols">
                                            <fieldset class="author">
                                                <div class="body-title mb-10">Author:<span class="tf-color-1">*</span></div>
                                                <div class="col" style="color: black; font-size: 20px; font-weight: bold;">${blog.author}</div>
                                            </fieldset>
                                            <fieldset class="briefinfo">
                                               <div class="body-title mb-10">Brief_info: <span class="tf-color-1">*</span></div>
                                            <div class="col" style="color: black; font-size: 20px; font-weight: bold;">${blog.brief_info}</div>
                                            </fieldset>
                                        </div>
                                            <div class="body-title mb-10">
                                                Description: <span class="tf-color-1">*</span>
                                            </div>
                                            <div class="col" style="color: black; font-size: 20px; font-weight: bold; white-space: normal; overflow: visible; text-overflow: unset;">
                                                ${blog.content}
                                            </div>
                                            </fieldset>
                                    </div>
                                    <div class="wg-box">
                                            <div class="upload-image mb-16">
                                                <div class="item up-load">
                                                    <image src="${blog.thumbnailblogs}">
                                                </div>
                                            </div>
                                            <fieldset class="name">
                                                <div class="body-title mb-10">Blog date</div>
                                                <div class="select">
                                                    <div class="col" style="color: black; font-size: 20px; font-weight: bold;">${blog.birth_date}</div>
                                                </div>
                                            </fieldset>
                                    </div>
                                </form>
                                <!-- /form-add-product -->
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
