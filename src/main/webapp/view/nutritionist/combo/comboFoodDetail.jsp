<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<!--<![endif]-->


<!-- Mirrored from themesflat.co/html/remos/oder-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-select.min_1.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_1.css">



    <!-- Font -->
    <link rel="stylesheet" href="font/fonts.css">

    <!-- Icon -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/icon/style.css">

    <!-- Favicon and Touch Icons  -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/images/favicon.png">

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
                 <jsp:include page="../../common/sidebar.jsp"></jsp:include>
                <!-- /section-menu-left -->
                <!-- section-content-right -->
                <div class="section-content-right">
                    <!-- header-dashboard -->
                     <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>
                    <!-- /header-dashboard -->
                    <!-- main-content -->
                    <div class="main-content">
                        <!-- main-content-wrap -->
                        <div class="main-content-inner">
                            <!-- main-content-wrap -->
                            <div class="main-content-wrap">
                                <div class="wg-box">
                                    <div class="flex items-center justify-between gap10 flex-wrap">
                                        <div class="wg-filter flex-grow">
                                        </div>
                                       
                                    </div>
                                    <div class="wg-table table-all-category">
                                        <ul class="table-title flex gap20 mb-14">
                                            <li>
                                                <div class="body-title">Food</div>
                                            </li>    
                                            <li>
                                                <div class="body-title">Food ID</div>
                                            </li>
                                            <li>
                                                <div class="body-title">Description</div>
                                            </li>
                                            <li>
                                                <div class="body-title">Price</div>
                                            </li>
                                            <li>
                                                <div class="body-title">Quantity</div>
                                            </li>
                                            <li>
                                                <div class="body-title">Calo</div>
                                            </li>
                                           
                                            <li>
                                                <div class="body-title">Action</div>
                                            </li>
                                        </ul>
                                        <ul class="flex flex-column">
                                            <c:forEach items = "${foodDetails}" var = "item">
                                                <c:set var = "food" value = "${item.food}"/>
                                                <li class="product-item gap14">
                                                <div class="image no-bg">
                                                    <img src="${food.image_url}" alt="">
                                                </div>
                                                <div class="flex items-center justify-between gap20 flex-grow">
                                                    <div class="name">
                                                        <a href="product-list.html" class="body-title-2">${food.name}</a>
                                                    </div>
                                                    <div class="body-text">${food.id}</div>
                                                    <div class="body-text">${food.description}</div>
                                                    <div class="body-text">${food.price}</div>
                                                    
                                                    <div class="body-text">${item.quantityInCombo}</div>
                                                    <div class="body-text">${food.calo}</div>
                                                    
                                                    <div class="list-icon-function">
                                                        <div class="item eye">
                                                            <i class="icon-eye"></i>
                                                        </div>
                                                        <div class="item edit">
                                                            <i class="icon-edit-3"></i>
                                                        </div>
                                                        <div class="item trash">
                                                            <i class="icon-trash-2"></i>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            </c:forEach>
                                            
                                            
                                        </ul>
                                    </div>
                                    <div class="divider"></div>
                                    <div class="flex items-center justify-between flex-wrap gap10">
<!--                                        <div class="text-tiny">Showing 10 entries</div>
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
                                        </ul>-->
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