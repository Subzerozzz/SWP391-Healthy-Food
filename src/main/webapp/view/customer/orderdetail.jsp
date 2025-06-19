<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->


    <!-- Mirrored from themesflat.co/html/remos/oder-detail.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
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
                                                                            <jsp:include page = "/view/common/homePage/sidebar.jsp"></jsp:include>
                                                                            <!-- /section-menu-left -->
                                                                            <!-- section-content-right -->
                                                                            <div class="section-content-right">
                                                                                <!-- header-dashboard -->
                                                                                <jsp:include page = "/view/common/homePage/headerDashboardUser.jsp"></jsp:include>

                                                                                <!-- /header-dashboard -->
                                                                                <!-- main-content -->
                                                                                <div class="main-content">
                                                                                    <!-- main-content-wrap -->
                                                                                    <div class="main-content-inner">
                                                                                        <!-- main-content-wrap -->
                                                                                        <div class="main-content-wrap">
                                                                                            <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                                                                            <c:if test="${empty orderItem}">
                                                                                                    <p style="color:red;">Không có đơn hàng nào.</p>
                                                                                                </c:if>
                                                                                            <c:forEach var="order" items="${orderItem}">
                                                                                                
                                                                                                <h3>Order ${order.orderId} </h3>
                                                                                                <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                                                                                    <li>
                                                                                                        <a href="index.html"><div class="text-tiny">Dashboard</div></a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <i class="icon-chevron-right"></i>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <a href="#"><div class="text-tiny">Order</div></a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <i class="icon-chevron-right"></i>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <a href="#"><div class="text-tiny">Order detail</div></a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <i class="icon-chevron-right"></i>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="text-tiny">Order</div>
                                                                                                    </li>
                                                                                                </ul>
                                                                                                </c:forEach>
                                                                                            </div>
                                                                                        
                                                                                        <!-- order-detail -->
                                                                                        <div class="wg-order-detail">
                                                                                            <div class="left flex-grow">
                                                                                                <div class="wg-box mb-20">
                                                                                                    <div class="wg-table table-order-detail">
                                                                                                        <ul class="table-title flex items-center justify-between gap20 mb-24">
                                                                                                            <li>
                                                                                                                <div class="body-title">All item</div>
                                                                                                            </li>    
                                                                                                            <li>
                                                                                                                <div class="dropdown default">
                                                                                                                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                                                                                        <span class="body-title-2 flex items-center gap8">Sort<i class="h6 icon-chevron-down"></i></span>
                                                                                                                    </button>
                                                                                                                    <ul class="dropdown-menu">
                                                                                                                        <li>  
                                                                                                                            <a href="javascript:void(0);">Name</a>
                                                                                                                        </li>
                                                                                                                        <li>  
                                                                                                                            <a href="javascript:void(0);">Quantity</a>
                                                                                                                        </li>
                                                                                                                        <li>  
                                                                                                                            <a href="javascript:void(0);">Price</a>
                                                                                                                        </li>
                                                                                                                    </ul>
                                                                                                                </div>
                                                                                                            </li>
                                                                                                        </ul>
                                                                                                        <ul class="flex flex-column">
                                                                                                            <li class="product-item gap14">
                                                                                                                <div class="image no-bg">
                                                                                                                    <img src="images/products/41.png" alt="">
                                                                                                                </div>
                                                                                                                <div class="flex items-center justify-between gap40 flex-grow">
                                                                                                                    <div class="name">
                                                                                                                        <div class="text-tiny mb-1">Product name</div>
                                                                                                                        <a href="product-list.html" class="body-title-2">Kristin Watson</a>
                                                                                                                    </div>
                                                                                                                    <div class="name">
                                                                                                                        <div class="text-tiny mb-1">Quantity</div>
                                                                                                                        <div class="body-title-2">1</div>
                                                                                                                    </div>
                                                                                                                    <div class="name">
                                                                                                                        <div class="text-tiny mb-1">Price</div>
                                                                                                                        <div class="body-title-2">$50.47</div>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </li>
                                                                                                            <li class="product-item gap14">
                                                                                                                <div class="image no-bg">
                                                                                                                    <img src="images/products/44.png" alt="">
                                                                                                                </div>
                                                                                                                <div class="flex items-center justify-between gap40 flex-grow">
                                                                                                                    <div class="name">
                                                                                                                        <div class="text-tiny mb-1">Product name</div>
                                                                                                                        <a href="product-list.html" class="body-title-2">Kristin Watson</a>
                                                                                                                    </div>
                                                                                                                    <div class="name">
                                                                                                                        <div class="text-tiny mb-1">Quantity</div>
                                                                                                                        <div class="body-title-2">1</div>
                                                                                                                    </div>
                                                                                                                    <div class="name">
                                                                                                                        <div class="text-tiny mb-1">Price</div>
                                                                                                                        <div class="body-title-2">$50.47</div>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </li>
                                                                                                            <li class="product-item gap14">
                                                                                                                <div class="image no-bg">
                                                                                                                    <img src="images/products/43.png" alt="">
                                                                                                                </div>
                                                                                                                <div class="flex items-center justify-between gap40 flex-grow">
                                                                                                                    <div class="name">
                                                                                                                        <div class="text-tiny mb-1">Product name</div>
                                                                                                                        <a href="product-list.html" class="body-title-2">Kristin Watson</a>
                                                                                                                    </div>
                                                                                                                    <div class="name">
                                                                                                                        <div class="text-tiny mb-1">Quantity</div>
                                                                                                                        <div class="body-title-2">1</div>
                                                                                                                    </div>
                                                                                                                    <div class="name">
                                                                                                                        <div class="text-tiny mb-1">Price</div>
                                                                                                                        <div class="body-title-2">$50.47</div>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </li>
                                                                                                        </ul>
                                                                                                    </div>
                                                                                                </div>
                                                                                                <div class="wg-box">
                                                                                                    <div class="wg-table table-cart-totals">
                                                                                                        <ul class="table-title flex mb-24">
                                                                                                            <li>
                                                                                                                <div class="body-title">Cart Totals</div>
                                                                                                            </li>    
                                                                                                            <li>
                                                                                                                <div class="body-title">Price</div>
                                                                                                            </li>    
                                                                                                        </ul>
                                                                                                        <ul class="flex flex-column gap14">
                                                                                                            <li class="cart-totals-item">
                                                                                                                <span class="body-text">Subtotal:</span>
                                                                                                                <span class="body-title-2">$70.13</span>
                                                                                                            </li>
                                                                                                            <li class="divider"></li>
                                                                                                            <li class="cart-totals-item">
                                                                                                                <span class="body-text">Shipping:</span>
                                                                                                                <span class="body-title-2">$10.00</span>
                                                                                                            </li>
                                                                                                            <li class="divider"></li>
                                                                                                            <li class="cart-totals-item">
                                                                                                                <span class="body-text">Tax (GST):</span>
                                                                                                                <span class="body-title-2">$5.00</span>
                                                                                                            </li>
                                                                                                            <li class="divider"></li>
                                                                                                            <li class="cart-totals-item">
                                                                                                                <span class="body-title">Total price:</span>
                                                                                                                <span class="body-title tf-color-1">$90.58</span>
                                                                                                            </li>

                                                                                                        </ul>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="right">
                                                                                                <div class="wg-box mb-20 gap10">
                                                                                                    <div class="body-title">Summary</div>
                                                                                                    <div class="summary-item">
                                                                                                        <div class="body-text">Order ID</div>
                                                                                                        <div class="body-title-2">#192847</div>
                                                                                                    </div>
                                                                                                    <div class="summary-item">
                                                                                                        <div class="body-text">Date</div>
                                                                                                        <div class="body-title-2">20 Nov 2023</div>
                                                                                                    </div>
                                                                                                    <div class="summary-item">
                                                                                                        <div class="body-text">Total</div>
                                                                                                        <div class="body-title-2 tf-color-1">$948.5</div>
                                                                                                    </div>
                                                                                                </div>
                                                                                                <div class="wg-box mb-20 gap10">
                                                                                                    <div class="body-title">Shipping Address</div>
                                                                                                    <div class="body-text">3517 W. Gray St. Utica, Pennsylvania 57867</div>
                                                                                                </div>
                                                                                                <div class="wg-box mb-20 gap10">
                                                                                                    <div class="body-title">Payment Method</div>
                                                                                                    <div class="body-text">Pay on Delivery (Cash/Card). Cash on delivery (COD) available. Card/Net banking acceptance subject to device availability.</div>
                                                                                                </div>
                                                                                                <div class="wg-box gap10">
                                                                                                    <div class="body-title">Expected Date Of Delivery</div>
                                                                                                    <div class="body-title-2 tf-color-2">20 Nov 2023</div>
                                                                                                    <a class="tf-button style-1 w-full" href="oder-tracking.html"><i class="icon-truck"></i>Track order</a>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <!-- /order-detail -->
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


                                                        <!-- Mirrored from themesflat.co/html/remos/oder-detail.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
                                                        </html>