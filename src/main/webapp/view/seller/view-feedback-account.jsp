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
        /* Container box */
        .wg-box {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 6px 24px rgba(0, 0, 0, 0.08);
            max-width: 700px;
            margin: 30px auto;
            font-family: 'Segoe UI', 'Roboto', sans-serif;
        }

        /* Title */
        .wg-box h5 {
            font-size: 26px;
            font-weight: 700;
            color: #2c3e50;
            margin-bottom: 30px;
            border-bottom: 2px solid #e0e0e0;
            padding-bottom: 10px;
        }

        /* Each detail item */
        fieldset.name {
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 1px solid #f0f0f0;
        }

        .body-title {
            font-size: 16px;
            font-weight: 500;
            color: #34495e;
            display: flex;
            justify-content: space-between;
        }

        .body-title span {
            font-weight: 600;
            color: #000000;
        }

        /* Back button */
        .bot {
            text-align: center;
            margin-top: 30px;
        }

        .bot button {
            background-color: #007BFF;
            border: none;
            color: #ffffff;
            padding: 12px 28px;
            font-size: 16px;
            border-radius: 10px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        .bot button:hover {
            background-color: #0056b3;
            transform: scale(1.02);
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
                                                                        <jsp:include page = "../common/sidebar.jsp"></jsp:include>
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
                                                                                              
                                                                                                <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                                                                                    <li>
                                                                                                        <a href="index.html"><div class="text-tiny">Dashboard</div></a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <i class="icon-chevron-right"></i>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <a href="${pageContext.request.contextPath}/seller/manage-feedback"><div class="text-tiny">List Feedback</div></a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <i class="icon-chevron-right"></i>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="text-tiny">Account Detail:${account.id} </div>
                                                                                                </li>
                                                                                            </ul>
                                                                                        </div>
                                                                                      
                                                                                            <div class="wg-box">
                                                                                                <div class="left">
                                                                                                    <h5 class="mb-4">Account Detail</h5>

                                                                                                </div>
                                                                                                <div class="right flex-grow">
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">
                                                                                                            <label>Id_User:</label>
                                                                                                            <span>${account.id}</span>
                                                                                                        </div>

                                                                                                    </fieldset>


                                                                                                    <!-- Full Name -->
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">
                                                                                                            <label>  Full Name:</label>     
                                                                                                            <span>${account.full_name}</span>     
                                                                                                        </div>

                                                                                                    </fieldset>

                                                                                                    <!-- User Name -->
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">
                                                                                                            <label>User Name:</label> <span> ${account.user_name}</span></div>

                                                                                                    </fieldset>

                                                                                                    <!-- Email -->
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">
                                                                                                            <label>Email:</label> 
                                                                                                            <span>${account.email}</span>
                                                                                                            
                                                                                                        </div>

                                                                                                    </fieldset>

                                                                                                    

                                                                                                    </fieldset>
                                                                                                    <!-- Address -->
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">
                                                                                                            <label>Adress:</label> 
                                                                                                            <span>${account.address}</span>
                                                                                                           

                                                                                                    </fieldset>
                                                                                                                   <!--Phone                                  -->
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">
                                                                                                            <label>Phone:</label> 
                                                                                                            <span>${account.mobile}</span>
                                                                 
                                                                                                    </fieldset>
                                                                                                                  <!--Birth_date                                  -->
                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">
                                                                                                            <label>Birth_date :</label> 
                                                                                                            <span>${account.birth_date}</span>
                                                                 
                                                                                                    </fieldset>

                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">Gender:<span>${account.gender == 'male' ? 'Male' : (account.gender == 'female' ? 'Female' : 'Other')}</span></div>

                                                                                                    </fieldset>

                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">Role:<span>${account.role == 'admin' ? 'Admin' : 'User'}</span></div>

                                                                                                    </fieldset>

                                                                                                    <fieldset class="name mb-24">
                                                                                                        <div class="body-title mb-10">Status: <span>${account.status == 'active'? 'Active' : 'Deactive'}</span></div>

                                                                                                    </fieldset>

                                                                                                </div>

                                                                                            </div>


                                                                                            <div class="bot">
                                                                                                <button type="button" onclick="window.location.href = '${pageContext.request.contextPath}/seller/feedback'">Back</button>
                                                                                            </div>

                                                                                    </div>
                                                                                    <!-- /main-content-wrap -->
                                                                                </div>
                                                                                <!-- /main-content-wrap -->
                                                                                <!-- bottom-page -->
                                                                                    <jsp:include page="../common/footer.jsp"></jsp:include>
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