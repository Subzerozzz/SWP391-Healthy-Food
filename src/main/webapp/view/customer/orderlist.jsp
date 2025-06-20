<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <head>
        <!-- Basic Page Needs -->
        <meta charset="utf-8">
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
                                                                                                <h3>Order List</h3>
                                                                                            </div>
                                                                                            <!-- order-list -->
                                                                                            <div class="wg-box">
                                                                                                <div class="flex items-center justify-between gap10 flex-wrap">
                                                                                                    <div class="wg-filter flex-grow">
                                                                                                        <form class="form-search" action="search" method="post">
                                                                                                            <fieldset class="name">
                                                                                                                <input type="text" placeholder="Search here..." class="" name="name" tabindex="2" value="" aria-required="true" required="">
                                                                                                            </fieldset>
                                                                                                            <div class="button-submit">
                                                                                                                <button class="" type="submit"><i class="icon-search"></i></button>
                                                                                                            </div>
                                                                                                        </form>
                                                                                                    </div>
                                                                                                    <!--                                                                                                    <a class="tf-button style-1 w208" href="oder-detail.html"><i class="icon-file-text"></i>Export all order</a>-->
                                                                                                </div>
                                                                                                <div class="wg-table table-all-category">
                                                                                                    <ul class="table-title flex gap20 mb-14">
                                                                                                        <li>
                                                                                                            <div class="body-title">Product</div>
                                                                                                        </li>    
                                                                                                        <li>
                                                                                                            <div class="body-title">Order ID</div>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <div class="body-title">Price</div>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <div class="body-title">Quantity</div>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <div class="body-title">Payment Method</div>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <div class="body-title">Status</div>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <div class="body-title">Action</div>
                                                                                                        </li>
                                                                                                    </ul>
                                                                                                    <ul class="flex flex-column">

                                                                                                    <c:forEach var="order" items="${orderList}">
                                                                                                        <li class="product-item gap14">
                                                                                                            <div class="image no-bg">
                                                                                                                <img src="images/products/51.png" >
                                                                                                            </div>
                                                                                                            <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                                <div class="name">
                                                                                                                    <a href="product-list.html" class="body-title-2">${order.foodName}</a>
                                                                                                                </div>
                                                                                                                <div class="body-text">#${order.orderId} </div>
                                                                                                                <div class="body-text">${order.foodPrice}VND</div>
                                                                                                                <div class="body-text">${order.quantity}</div>
                                                                                                                <div class="body-text">${order.paymentMethod}</div>
                                                                                                                <div>
                                                                                                                    <div class="block-available">${order.status}</div>
                                                                                                                </div>
                                                                                                                <div class="list-icon-function">
                                                                                                                    <a href="${pageContext.request.contextPath}/orderdetail?orderId=${order.orderId}" class="item eye">
                                                                                                                        <i class="icon-eye"></i>
                                                                                                                    </a>
<!--                                                                                                                    <button class="item edit" onclick="editProduct(${order.orderId})">
                                                                                                                        <i class="icon-edit-3"></i>
                                                                                                                    </button>-->
                                                                                                                    <button class="item trash" onclick="deleteProduct(${order.orderItemId})">
                                                                                                                        <i class="icon-trash-2"></i>
                                                                                                                    </button>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </li>
                                                                                                    </c:forEach>
                                                                                                </ul>
                                                                                            </div>
                                                                                            <div class="divider"></div>
                                                                                            <div class="flex items-center justify-between flex-wrap gap10">
                                                                                                <div class="text-tiny">Showing 10 entries</div>
                                                                                                <ul class="wg-pagination">
                                                                                                    <li>
                                                                                                        <a href="#"><i class="icon-chevron-left"></i></a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <a href="#">1</a>
                                                                                                    </li>
                                                                                                    <li class="active">
                                                                                                        <a href="#">2</a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <a href="#">3</a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <a href="#"><i class="icon-chevron-right"></i></a>
                                                                                                    </li>
                                                                                                </ul>
                                                                                            </div>
                                                                                        </div>
                                                                                        <!-- /order-list -->
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
                                                            <script>
                                                                function deleteProduct(orderItemId) {
                                                                    fetch('hide-order', {
                                                                        method: 'POST',
                                                                        headers: {
                                                                            'Content-Type': 'application/x-www-form-urlencoded'
                                                                        },
                                                                        body: 'orderItemId=' + orderItemId
                                                                    }).then(response => {
                                                                        if (response.ok) {
                                                                            const element = document.getElementById("order-" + orderItemId);
                                                                            if (element) {
                                                                                element.remove(); // Ẩn khỏi UI
                                                                            }
                                                                        } else {
                                                                            console.error("Failed to hide order");
                                                                        }
                                                                    }).catch(error => {
                                                                        console.error("Error:", error);
                                                                    });
                                                                }
                                                            </script>

                                                            <script src="${pageContext.request.contextPath}/js/jquery.min_1.js"></script>
                                                            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
                                                            <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
                                                            <script src="${pageContext.request.contextPath}/js/zoom.js"></script>
                                                            <script src="${pageContext.request.contextPath}/js/switcher.js"></script>
                                                            <script src="${pageContext.request.contextPath}/js/theme-settings.js"></script>
                                                            <script src="${pageContext.request.contextPath}/js/main.js"></script>
                                                        </body>


                                                        <!-- Mirrored from themesflat.co/html/remos/oder-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
                                                        </html>