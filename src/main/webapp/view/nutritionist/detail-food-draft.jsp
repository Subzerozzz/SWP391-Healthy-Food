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
                            <div class="main-content-wrap">
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                    <h3>Food Draft Detail</h3>
                                    <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                        <li>
                                            <a href="index.html"><div class="text-tiny">Dashboard</div></a>
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
                                        <li>
                                            <div class="text-tiny">Product Detail</div>
                                        </li>
                                    </ul>
                                </div>
                                <!-- Product Detail -->
                                <div class="wg-box">
                                    <div class="tf-main-product section-image-zoom flex">
                                        <div class="tf-product-media-wrap">
                                            <div class="thumbs-slider">
                                               <div class="item">
                                                    <a href="${foodD.image_url}" target="_blank" data-pswp-width="1000px" data-pswp-height="1000px">
                                                        <img  data-zoom="images/products/product-detail-2.png" src="${foodD.image_url}" alt="">
                                                    </a>
                                               </div>
                                               </div>
                                        </div>
                                        <div class="tf-product-info-wrap relative flex-grow">
                                            <div class="tf-zoom-main"></div>
                                            <div class="tf-product-info-list other-image-zoom">
                                                <div class="tf-product-info-title">
                                                    <h3>${foodD.name}<br>Nutritionist:  ${foodD.nameNutri}</h3>
                                                    <div class="price body-title">$${foodD.price}</div>
                                                </div>
                                                <div>
                                                    <h3>Type Request: ${foodD.typeOfRequest}</h3>
                                                    <h3>Description: ${foodD.description}</h3>
                                                    <h3>Create At: ${foodD.create_At}</h3>
                                                </div>
                                               
                                               <br/>
                                                </div>
                                                <div style="display: flex;justify-content: center;gap: 100px">
                                                  <div>
                                                   <form action="action">
                                                           <button type="submit" class="btn-blue" style="background-color: greenyellow">Accept</button>
                                                    </form>
                                                  
                                               </div>  
                                                <div>
                                                   <form action="action">
                                                           <button type="submit" class="btn-blue" style="background-color: red">Reject</button>
                                                   </form>     
                                                </div>   
                                                </div>
                                           
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /Product Detail -->
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


