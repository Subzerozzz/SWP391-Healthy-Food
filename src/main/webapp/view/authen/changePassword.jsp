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
<script src="https://cdn.jsdelivr.net/npm/izitoast/dist/js/iziToast.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/izitoast/dist/css/iziToast.min.css">

    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
        <!--<![endif]-->
        <style>
            /* Container fieldset */
            fieldset.name {
                border: none;
                padding: 0;
                margin-bottom: 24px;
            }

            /* Label title */
            .body-title {
                font-weight: 600;
                font-size: 15px;
                margin-bottom: 6px;
            }

            /* Select box */
            select.form-control {
                width: 100%;
                padding: 10px 14px;
                border: 1px solid #ccc;
                border-radius: 6px;
                background-color: #fff;
                font-size: 14px;
                font-family: inherit;
                outline: none;
                transition: border-color 0.2s ease;
            }

            select.form-control:focus {
                border-color: #1e80ff;
                box-shadow: 0 0 0 2px rgba(30, 128, 255, 0.2);
                background-color: #fefefe;
            }
            .readonly-input[readonly] {
                background-color: #e9ecef;     /* Bootstrap-like xám */
                color: #495057;
                border: none;
                border-radius: 10px;
                padding: 12px 16px;
                font-size: 16px;
                width: 100%;
            }
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
                                                                            <jsp:include page="../common/sidebar.jsp"></jsp:include>
                                                                                <!-- /section-menu-left -->
                                                                                <!-- section-content-right -->
                                                                                <div class="section-content-right">
                                                                                    <!-- header-dashboard -->
                                                                                <jsp:include page = "../common/headerDashboard.jsp"></jsp:include>
                                                                                    <!-- /header-dashboard -->
                                                                                    <!-- main-content -->
                                                                                    <div class="main-content">
                                                                                        <!-- main-content-wrap -->
                                                                                        <div class="main-content-inner">
                                                                                            <!-- main-content-wrap -->
                                                                                            <div class="main-content-wrap">
                                                                                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                                                                                    <h3>Change Password</h3>
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
                                                                                                            <div class="text-tiny">Edit Password</div>
                                                                                                        </li>
                                                                                                    </ul>
                                                                                                </div>
                                                                                                <!-- add-new-user -->
                                                                                                <form class="add_account" action="changepassword" method="Post">
                                                                                                    <div class="wg-box">
                                                                                                        <div class="left">
                                                                                                            <h5 class="mb-4">Password</h5>
                                                                                                            <div class="body-text">Fill in the information below to edit password</div>
                                                                                                        </div>

                                                                                                        <div class="right flex-grow">


                                                                                                            <fieldset class="name mb-24">
                                                                                                                <div class="body-title mb-10">Current Password</div>
                                                                                                                <input type="password" class="form-control" name="current_password">
                                                                                                                    
                                                                                                            </fieldset>


                                                                                                            <fieldset class="name mb-24">
                                                                                                                <div class="body-title mb-10">New Password</div>
                                                                                                                <input type="password" class="form-control" name="new_password" >
                                                                                                            </fieldset>


                                                                                                            <!-- Mobile -->
                                                                                                            <fieldset class="name mb-24">
                                                                                                                <div class="body-title mb-10">Confirm Password</div>
                                                                                                                <input class="form-control" type="password" name="confirm_password">
                                                                                                                <c:if test="${not empty errors.mobile}">
                                                                                                                    <div class="text-danger">${errors.mobile}</div>
                                                                                                                </c:if>
                                                                                                        </fieldset>
                                                                                                    </div>
                                                                                                </div>
                                                                                                <p style="color:red">${toastMessage}</p>
                                                                                                <div class="bot">
                                                                                                    <button class="tf-button w180" type="submit">Change Now</button>
                                                                                                </div>
                                                                                            </form>

                                                                                        </div>
                                                                                        <!-- /add-new-user -->
                                                                                    </div>
                                                                                    <!-- /main-content-wrap -->
                                                                                    <!-- bottom-page -->
                                                                                        <jsp:include page="../common/footer.jsp"></jsp:include>
                                                                                    <!-- /bottom-page -->
                                                                                </div>
                                                                                <!-- /main-content-wrap -->
                                                                                
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

                                                                <c:if test="${toastType == 'error'}">
                                                                    <script>
                                                                        document.addEventListener("DOMContentLoaded", function () {
                                                                            iziToast.error({
                                                                                title: "Thông báo",
                                                                                message: '${sessionScope.toastMessage}',
                                                                                position: 'topRight',
                                                                                timeout: 5000,
                                                                                backgroundColor: "#E53E31"
                                                                            });
                                                                        });
                                                                    </script>
                                                                    <!--Xóa đi biến isDelete sau khi đã thông báo--> 
                                                                    <%
                                                                        session.removeAttribute("toastType");
                                                                        session.removeAttribute("toastMessage");
                                                                    %>
                                                                </c:if>
                                                                    

                                                                <!-- Mirrored from themesflat.co/html/remos/add-new-user.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:55 GMT -->
                                                                </html>