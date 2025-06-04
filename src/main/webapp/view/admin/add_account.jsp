<%-- 
    Document   : add_account
    Created on : May 29, 2025, 8:10:49 AM
    Author     : Hang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->
    <style>


    </style>

    <!-- Mirrored from themesflat.co/html/remos/add-new-user.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:55 GMT -->
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
                                                                        <jsp:include page = "../common/admin/section-menu-left.jsp"></jsp:include>
                                                                            <!-- /section-menu-left -->
                                                                            <!-- section-content-right -->
                                                                            <div class="section-content-right">
                                                                                <!-- header-dashboard -->
                                                                            <jsp:include page = "../common/admin/header-dashboard.jsp"></jsp:include>
                                                                                <!-- /header-dashboard -->
                                                                                <!-- main-content -->
                                                                                <div class="main-content">
                                                                                    <!-- main-content-wrap -->
                                                                                    <div class="main-content-inner">
                                                                                        <!-- main-content-wrap -->
                                                                                        <div class="main-content-wrap">
                                                                                            <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                                                                                <h3>Add New User</h3>
                                                                                                <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                                                                                    <li>
                                                                                                        <a href="index.html"><div class="text-tiny">Dashboard</div></a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <i class="icon-chevron-right"></i>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <a href="#"><div class="text-tiny">User</div></a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <i class="icon-chevron-right"></i>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="text-tiny">Add New User</div>
                                                                                                    </li>
                                                                                                </ul>
                                                                                            </div>
                                                                                            <!-- add-new-user -->
                                                                                            <form class="add_account" action="${pageContext.request.contextPath}/manage-account?action=add" method="POST">
                                                                                            <div class="wg-box">   

                                                                                                <div class="right flex-grow">
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">Full_Name</div>
                                                                                                        <input class="flex-grow" type="text" placeholder="Username" name="full_name" tabindex="0" value="" aria-required="true" required="">
                                                                                                    </fieldset>
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">User_Name</div>
                                                                                                        <input class="flex-grow" type="text" placeholder="Username" name="user_name" tabindex="0" value="" aria-required="true" required="">
                                                                                                    </fieldset>

                                                                                                    <fieldset class="email mb-24">
                                                                                                        <div class="body-title mb-10">Email</div>
                                                                                                        <input class="flex-grow" type="email" placeholder="Email" name="email" tabindex="0" value="" aria-required="true" required="">
                                                                                                    </fieldset>

                                                                                                    <fieldset class="password mb-24">
                                                                                                        <div class="body-title mb-10">Password</div>
                                                                                                        <input class="password-input" type="password" placeholder="Enter password" name="password" tabindex="0" value="" aria-required="true" required="">
                                                                                                            <span class="show-pass">
                                                                                                                <i class="icon-eye view"></i>
                                                                                                                <i class="icon-eye-off hide"></i>
                                                                                                            </span>

                                                                                                    </fieldset>
                                                                                                    <fieldset class="password">
                                                                                                        <div class="body-title mb-10">Confirm password</div>
                                                                                                        <input class="password-input" type="password" placeholder="Confirm password" name="password" tabindex="0" value="" aria-required="true" required="">
                                                                                                            <span class="show-pass">
                                                                                                                <i class="icon-eye view"></i>
                                                                                                                <i class="icon-eye-off hide"></i>
                                                                                                            </span>
                                                                                                    </fieldset>
                                                                                                    <!-- Address -->
                                                                                                    <fieldset class="address mb-24">
                                                                                                        <div class="body-title mb-10">Address</div>
                                                                                                        <input class="flex-grow" type="text" placeholder="Address" name="address">
                                                                                                    </fieldset>

                                                                                                    <!-- Role -->
                                                                                                    <fieldset class="role mb-24">
                                                                                                        <div class="body-title mb-10">Role</div>
                                                                                                        <select name="role" class="flex-grow" required>
                                                                                                            <option value="">-- Select Role --</option>
                                                                                                            <option value="admin">Admin</option>
                                                                                                            <option value="user">User</option>

                                                                                                        </select>
                                                                                                    </fieldset>

                                                                                                    <!-- Status -->
                                                                                                    <fieldset class="status mb-24">
                                                                                                        <div class="body-title mb-10">Status</div>
                                                                                                        <label><input type="radio" name="status" value="true" checked> Active</label>
                                                                                                        <label><input type="radio" name="status" value="false"> Deactive</label>
                                                                                                    </fieldset>

                                                                                                    <!-- Birth Date -->
                                                                                                    <fieldset class="birth-date mb-24">
                                                                                                        <div class="body-title mb-10">Birth Date</div>
                                                                                                        <input class="flex-grow" type="date" name="birth_date" required>
                                                                                                    </fieldset>
                                                                                                </div>
                                                                                                <!-- mobile -->
                                                                                                <fieldset class="birth-date mb-24">
                                                                                                    <div class="body-title mb-10">Mobile</div>
                                                                                                    <input class="flex-grow" type="text" name="mobile" required>
                                                                                                </fieldset>
                                                                                                <!-- Gender -->
                                                                                            <fieldset class="birth-date mb-24">
                                                                                                <div class="body-title mb-10">Gender</div>
                                                                                                <select name="gender" class="flex-grow" required>
                                                                                                    <option value="">-- Select gender --</option>
                                                                                                    <option value="male">Male</option>
                                                                                                    <option value="female">Female</option>
                                                                                                    <option value="other">Other</option>
                                                                                                </select>
                                                                                            </fieldset>
                                                                                            </div>
                                                                                            
                                                                                    </div>

                                                                                </div>


                                                                                <div class="bot">
                                                                                    <button class="tf-button w180" type="submit">Save</button>
                                                                                </div>

                                                                                </form>
                                                                                <!-- /add-new-user -->
                                                                            </div>
                                                                            <!-- /main-content-wrap -->
                                                                        </div>
                                                                        <!-- /main-content-wrap -->
                                                                        <!-- bottom-page -->
                                                                        <div class="bottom-page">
                                                                            <div class="body-text">Copyright © 2024 Remos. Design with</div>
                                                                            <i class="icon-heart"></i>
                                                                            <div class="body-text">by <a href="https://themeforest.net/user/themesflat/portfolio">Themesflat</a> All rights reserved.</div>
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


                                                        <!-- Mirrored from themesflat.co/html/remos/add-new-user.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:55 GMT -->
                                                        </html>