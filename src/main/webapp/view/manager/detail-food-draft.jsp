<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->


    <!-- Mirrored from themesflat.co/html/remos/product-detail-1.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:40 GMT -->
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
                                    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/swiper-bundle.min.css">
                                        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_1.css">
                                            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail_food.css"/>


                                            <!--css-->

                                            <!-- Font -->
                                            <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/fonts.css">

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
                                                                            <jsp:include page="/view/common/menuDashBoard/section-menu-left.jsp"></jsp:include>
                                                                                <!-- /section-menu-left -->
                                                                                <!-- section-content-right -->
                                                                                <div class="section-content-right">
                                                                                    <!-- header-dashboard -->
                                                                                <jsp:include page="/view/common/menuDashBoard/header-dashboard.jsp"></jsp:include>
                                                                                    <!-- /header-dashboard -->
                                                                                    <!-- main-content -->
                                                                                    <div class="main-content">
                                                                                        <!-- main-content-wrap -->
                                                                                        <div class="main-content-inner">
                                                                                            <!-- main-content-wrap -->
                                                                                        
                                                                                            <!-- /Product Detail -->
                                                                                            <div class="product-detail">
                                                                                                <div class="product-image">
                                                                                                    <img src="https://mediawinwin.vn/upload/images/sanpham/bao-gia-chup-mon-an-dich-vu-chup-anh-do-an-chuyen-nghiep-5.JPG" alt="Black Fresh Berry">
                                                                                                </div>
                                                                                                <div class="product-info">
                                                                                                    <h2>${foodD.name}</h2>
                                                                                                <h3>Created at: ${foodD.created_at}</h3>
                                                                                                <div class="price">
                                                                                                    <span class="current">${foodD.price}</span>
                                                                                                    <!--                                            <span class="original">$330.00</span>-->
                                                                                                </div>
                                                                                                <p>Type: ${foodD.type}</p>
                                                                                                <p class="description">
                                                                                                   ${foodD.description}
                                                                                                </p>
                                                                                                <p class="description">
                                                                                                 Number of Calo: ${foodD.calo}
                                                                                                </p>
                                                                                                <p>Black Fresh Berry is a delicious and nutritious fruit, rich in antioxidants and flavor. Perfect for a healthy snack or to add freshness to your desserts and smoothies.</p>
                                                                                                <div style="display: flex;gap: 15px">
                                                                                                    <form action="type-of-request" method="get">
                                                                                                        <input type="hidden" name="select" value="${foodD.type}">
                                                                                                            <input type="hidden" name="action" value="accept">
                                                                                                                <input type="hidden" name="id" value="${foodD.id}">
                                                                                                                    <button type="submit"  style="background-color: #02b80b;color: white"
                                                                                                                            onclick="return confirm('Are you want to Accept ${foodD.type} this food')" >
                                                                                                                        Accept
                                                                                                                    </button>
                                                                                                                    </form>
                                                                                                                    <form action="type-of-request" method="get">
                                                                                                                        <input type="hidden" name="select" value="${foodD.type}">
                                                                                                                            <input type="hidden" name="action" value="reject">
                                                                                                                                <input type="hidden" name="id" value="${foodD.id}">
                                                                                                                                    <button type="submit"  style="background-color: #e60004;color: white"
                                                                                                                                            onclick="return confirm('Are you want to Reject ${foodD.type} this food')"        >
                                                                                                                                        Reject 
                                                                                                                                    </button>
                                                                                                                                    </form>     
                                                                                                                                    </div> 

                                                                                                                                    </div>
                                                                                                                                    </div>
                                                                                                                                    </div>
                                                                                                                                    <!-- /main-content-wrap -->
                                                                                                                                    </div>
                                                                                                                                    <!-- /main-content-wrap -->
                                                                                                                                    <!-- bottom-page -->
                                                                                                                                    <jsp:include page="/view/common/menuDashBoard/bottom-page.jsp"></jsp:include>
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
                                                                                                                                    <script src="${pageContext.request.contextPath}/js/swiper-bundle.min.js"></script>
                                                                                                                                    <script src="${pageContext.request.contextPath}/js/carousel.js"></script>
                                                                                                                                    <script src="${pageContext.request.contextPath}/js/main.js"></script>

                                                                                                                                    </body>


                                                                                                                                    <!-- Mirrored from themesflat.co/html/remos/product-detail-1.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:47 GMT -->
                                                                                                                                    </html>


