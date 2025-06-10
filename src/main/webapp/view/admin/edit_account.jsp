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
                                                                                                <h3>Edit User</h3>
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
                                                                                                        <div class="text-tiny">Edit User</div>
                                                                                                    </li>
                                                                                                </ul>
                                                                                            </div>
                                                                                            <!-- add-new-user -->
                                                                                            <form class="add_account" action="${pageContext.request.contextPath}/manage-account?action=edit" method="Post">
                                                                                            <div class="wg-box">
                                                                                                <div class="left">
                                                                                                    <h5 class="mb-4">Account</h5>
                                                                                                    <div class="body-text">Fill in the information below to edit account</div>
                                                                                                </div>

                                                                                                <div class="right flex-grow">
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">Id_User</div>
                                                                                                        <input class="readonly-input" type="text" name="id" value="${param.id}" readonly>
                                                                                                    </fieldset>


                                                                                                    <!-- Full Name -->
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">Full Name</div>
                                                                                                        <input type="text" class="form-control" name="full_name" value="${account.full_name}" required>
                                                                                                    </fieldset>

                                                                                                    <!-- User Name -->
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">User Name</div>
                                                                                                        <input type="text" class="readonly-input" name="user_name" value="${account.user_name}" readonly>
                                                                                                    </fieldset>

                                                                                                    <!-- Email -->
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">Email</div>
                                                                                                        <input class="readonly-input" type="email" name="email" value="${account.email}" readonly>
                                                                                                    </fieldset>

                                                                                                    <!-- Mobile -->
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">Mobile</div>
<!--                                                                                                        <input class="form-control" type="text" name="mobile" value="${account.mobile}">-->
                                                                                                        <input class="form-control" type="text" name="mobile" value="${formData.mobile != null ? formData.mobile : account.mobile}" />
                                                                                                        <c:if test="${not empty errors.mobile}">
                                                                                                            <div class="text-danger">${errors.mobile}</div>
                                                                                                        </c:if>
                                                                                                    </fieldset>

                                                                                                    <!-- Gender -->
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">Gender</div>
                                                                                                        <select name="gender" class="form-control">
                                                                                                            <option value="male" ${account.gender == 'male' ? 'selected' : ''}>Male</option>
                                                                                                            <option value="female" ${account.gender == 'female' ? 'selected' : ''}>Female</option>
                                                                                                            <option value="other" ${account.gender == 'other' ? 'selected' : ''}>Other</option>
                                                                                                        </select>
                                                                                                    </fieldset>

                                                                                                    <!--Role-->                                                                                               
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">Role</div>
                                                                                                        <input type="text" class="form-control" name="role" value="${account.role == 'admin' ? 'Admin' : 'User'}" readonly>
                                                                                                    </fieldset>


                                                                                                    <!-- Status -->
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">Status</div>
                                                                                                        <select name="status" class="form-control">
                                                                                                            <option value="true" ${account.status ? 'selected' : ''}>Active</option>
                                                                                                            <option value="false" ${!account.status ? 'selected' : ''}>Deactive</option>
                                                                                                        </select>
                                                                                                    </fieldset>


                                                                                                </div>

                                                                                            </div>


                                                                                            <div class="bot">
                                                                                                <button class="tf-button w180" type="submit">Save</button>
                                                                                            </div>
                                                                                           
                                                                                    </div>

                                                                                    </form>
                                                                                    <!-- /add-new-user -->
                                                                                </div>
                                                                                <!-- /main-content-wrap -->
                                                                            </div>
                                                                            <!-- /main-content-wrap -->
                                                                            <!-- bottom-page -->
                                                                            ư
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