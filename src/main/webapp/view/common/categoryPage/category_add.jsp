<%-- 
    Document   : category_add
    Created on : Jun 12, 2025, 12:04:52 AM
    Author     : Hang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->
  

    <!-- Mirrored from themesflat.co/html/remos/new-category.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
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
                                        <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font/fonts.css">
                                            <!-- Icon -->
                                            <link rel="stylesheet" href="${pageContext.request.contextPath}/icon/style.css">

                                                <!-- Favicon and Touch Icons  -->
                                                <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon_1.png">
                                                    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/images/favicon_1.png">


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
                                                                        <jsp:include page = "section-menu-left.jsp"></jsp:include>
                                                                            <!-- /section-menu-left -->
                                                                            <!-- section-content-right -->
                                                                            <div class="section-content-right">
                                                                                <!-- header-dashboard -->
                                                                            <jsp:include page = "header.jsp"></jsp:include>
                                                                                <!-- /header-dashboard -->
                                                                                <!-- main-content -->
                                                                                <div class="main-content">
                                                                                    <!-- main-content-wrap -->
                                                                                    <div class="main-content-inner">
                                                                                        <!-- main-content-wrap -->
                                                                                        <div class="main-content-wrap">
                                                                                            <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                                                                                <h3>Category infomation</h3>
                                                                                                <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                                                                                    <li>
                                                                                                        <a href="index.html"><div class="text-tiny">Dashboard</div></a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <i class="icon-chevron-right"></i>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <a href="#"><div class="text-tiny">Category</div></a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <i class="icon-chevron-right"></i>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="text-tiny">New category</div>
                                                                                                    </li>
                                                                                                </ul>
                                                                                            </div>
                                                                                            <!-- new-category -->
                                                                                            <div class="wg-box">
                                                                                                <form class="form-new-product form-style-1" method="POST" action="${pageContext.request.contextPath}/manageCategory?action=add">
<!--                                                                                                    <fieldset class="idcategory">
                                                                                                        <div class="body-title">ID: <span class="tf-color-1">*</span></div>
                                                                                                        <input class="flex-grow" type="text" placeholder="Category name" name="text" tabindex="0" value="" aria-required="true" required="">
                                                                                                    </fieldset>-->
                                                                                                    <fieldset class="name_category">
                                                                                                        <div class="body-title">Product name: <span class="tf-color-1">*</span></div>
                                                                                                        <input class="flex-grow" type="text" placeholder="Category name" name="text" tabindex="0" value="" aria-required="true" required="">
                                                                                                    </fieldset>
                                                                                                    <fieldset>
                                                                                                        <div class="description body-title">Description: <span class="tf-color-1">*</span></div>
                                                                                                        <input class="flex-grow" type="text" placeholder="Description" name="text" tabindex="0" value="${description != null ? decription : ''}" aria-required="true" required="">
                                                                                                    </fieldset>
                                                                                                    <fieldset class="minBMI">
                                                                                                        <div class="body-title">MinBMI: </div>
                                                                                                        <input class="flex-grow" type="text" placeholder="minBMI" name="text" tabindex="0" value="${minBMI != null ? minBMI : ''}" aria-required="true" required="">
                                                                                                    </fieldset>
                                                                                                    <fieldset class="maxBMI">
                                                                                                        <div class="body-title">MaxBMI :</div>
                                                                                                        <input class="flex-grow" type="text" placeholder="maxBMI" name="text" tabindex="0" value="${maxBMI != null ? maxBMI : ''}" aria-required="true" required="">
                                                                                                    </fieldset>
                                                                                                    <div class="bot">
                                                                                                        <div></div>
                                                                                                        <button class="tf-button w208" type="submit">Save</button>
                                                                                                    </div>
                                                                                                </form>
                                                                                            </div>
                                                                                            <!-- /new-category -->
                                                                                        </div>
                                                                                        <!-- /main-content-wrap -->
                                                                                    </div>
                                                                                    <!-- /main-content-wrap -->
                                                                                    <!-- bottom-page -->
                                                                                    
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


                                                        <!-- Mirrored from themesflat.co/html/remos/new-category.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
                                                        </html>