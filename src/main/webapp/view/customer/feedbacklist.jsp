<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                                                            <jsp:include page = "/view/common/homePage/headerDashboardUser.jsp"></jsp:include>                                                                            <!-- /header-dashboard -->
                                                                                <!-- main-content -->
                                                                                <div class="main-content">
                                                                                    <!-- main-content-wrap -->
                                                                                    <div class="main-content-inner">
                                                                                        <!-- main-content-wrap -->
                                                                                        <div class="main-content-wrap">
                                                                                            <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                                                                                <h3>Feedback List</h3>
                                                                                            </div>
                                                                                            <!-- order-list -->
                                                                                            <div class="wg-box">
                                                                                                <div class="flex items-center justify-between gap10 flex-wrap">
                                                                                                    <div class="wg-filter flex-grow">
                                                                                                        <form class="form-search">
                                                                                                            <fieldset class="name">
                                                                                                                <select name="rating" required>
                                                                                                                    <option value="all" ${param.status == 'all' ? 'selected' : ''}>-- All Rating --</option>
                                                                                                                <option value="5"  ${param.status == '5' ? 'selected' : ''}>5</option>
                                                                                                                <option value="4"  ${param.status == '4' ? 'selected' : ''}>4</option>
                                                                                                                <option value="3"  ${param.status == '3' ? 'selected' : ''}>3</option>
                                                                                                                <option value="2"  ${param.status == '2' ? 'selected' : ''}>2</option>
                                                                                                                <option value="1"  ${param.status == '1' ? 'selected' : ''}>1</option>
                                                                                                            </select>                                                                                                         </fieldset>
                                                                                                        <div class="button-submit">
                                                                                                            <button class="" type="submit"><i class="icon-search"></i></button>
                                                                                                        </div>
                                                                                                    </form>
                                                                                                </div>
                                                                                                <a class="tf-button style-1 w208" href="oder-detail.html"><i class="icon-file-text"></i>Export all order</a>
                                                                                            </div>
                                                                                            <div class="wg-table table-all-category">
                                                                                                <ul class="table-title flex gap20 mb-14">
                                                                                                        
                                                                                                    <li>
                                                                                                        <div class="body-title">Order ID</div>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="body-title">Updated At</div>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="body-title">Content</div>
                                                                                                    </li>
                                                                                                    
                                                                                                    <li>
                                                                                                        <div class="body-title">Action</div>
                                                                                                    </li>
                                                                                                </ul>
                                                                                                <ul class="flex flex-column">
                                                                                                    <li class="product-item gap14">
                                                                                                        <div class="image no-bg">
                                                                                                            <img src="images/products/51.png" alt="">
                                                                                                        </div>
                                                                                                        <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                            <div class="name">
                                                                                                                <a href="product-list.html" class="body-title-2">Kristin Watson</a>
                                                                                                            </div>
                                                                                                            <div class="body-text">#7712309</div>
                                                                                                            <div class="body-text">$1,452.500</div>
                                                                                                            <div class="body-text">1,638</div>
                                                                                                            <div class="body-text">20</div>
                                                                                                            <div>
                                                                                                                <div class="block-available">Success</div>
                                                                                                            </div>
                                                                                                            <div>
                                                                                                                <div class="block-tracking">Tracking</div>
                                                                                                            </div>
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
                                                                                                    <li class="product-item gap14">
                                                                                                        <div class="image no-bg">
                                                                                                            <img src="images/products/52.png" alt="">
                                                                                                        </div>
                                                                                                        <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                            <div class="name">
                                                                                                                <a href="product-list.html" class="body-title-2">Kristin Watson</a>
                                                                                                            </div>
                                                                                                            <div class="body-text">#7712309</div>
                                                                                                            <div class="body-text">$1,452.500</div>
                                                                                                            <div class="body-text">1,638</div>
                                                                                                            <div class="body-text">20</div>
                                                                                                            <div>
                                                                                                                <div class="block-pending">Pending</div>
                                                                                                            </div>
                                                                                                            <div>
                                                                                                                <div class="block-tracking">Tracking</div>
                                                                                                            </div>
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
                                                                                                    <li class="product-item gap14">
                                                                                                        <div class="image no-bg">
                                                                                                            <img src="images/products/53.png" alt="">
                                                                                                        </div>
                                                                                                        <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                            <div class="name">
                                                                                                                <a href="product-list.html" class="body-title-2">Kristin Watson</a>
                                                                                                            </div>
                                                                                                            <div class="body-text">#7712309</div>
                                                                                                            <div class="body-text">$1,452.500</div>
                                                                                                            <div class="body-text">1,638</div>
                                                                                                            <div class="body-text">20</div>
                                                                                                            <div>
                                                                                                                <div class="block-available">Success</div>
                                                                                                            </div>
                                                                                                            <div>
                                                                                                                <div class="block-tracking">Tracking</div>
                                                                                                            </div>
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
                                                                                                    <li class="product-item gap14">
                                                                                                        <div class="image no-bg">
                                                                                                            <img src="images/products/54.png" alt="">
                                                                                                        </div>
                                                                                                        <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                            <div class="name">
                                                                                                                <a href="product-list.html" class="body-title-2">Kristin Watson</a>
                                                                                                            </div>
                                                                                                            <div class="body-text">#7712309</div>
                                                                                                            <div class="body-text">$1,452.500</div>
                                                                                                            <div class="body-text">1,638</div>
                                                                                                            <div class="body-text">20</div>
                                                                                                            <div>
                                                                                                                <div class="block-available">Success</div>
                                                                                                            </div>
                                                                                                            <div>
                                                                                                                <div class="block-tracking">Tracking</div>
                                                                                                            </div>
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
                                                                                                    <li class="product-item gap14">
                                                                                                        <div class="image no-bg">
                                                                                                            <img src="images/products/55.png" alt="">
                                                                                                        </div>
                                                                                                        <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                            <div class="name">
                                                                                                                <a href="product-list.html" class="body-title-2">Kristin Watson</a>
                                                                                                            </div>
                                                                                                            <div class="body-text">#7712309</div>
                                                                                                            <div class="body-text">$1,452.500</div>
                                                                                                            <div class="body-text">1,638</div>
                                                                                                            <div class="body-text">20</div>
                                                                                                            <div>
                                                                                                                <div class="block-not-available">Cancel</div>
                                                                                                            </div>
                                                                                                            <div>
                                                                                                                <div class="block-tracking">Tracking</div>
                                                                                                            </div>
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
                                                                                                    <li class="product-item gap14">
                                                                                                        <div class="image no-bg">
                                                                                                            <img src="images/products/56.png" alt="">
                                                                                                        </div>
                                                                                                        <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                            <div class="name">
                                                                                                                <a href="product-list.html" class="body-title-2">Kristin Watson</a>
                                                                                                            </div>
                                                                                                            <div class="body-text">#7712309</div>
                                                                                                            <div class="body-text">$1,452.500</div>
                                                                                                            <div class="body-text">1,638</div>
                                                                                                            <div class="body-text">20</div>
                                                                                                            <div>
                                                                                                                <div class="block-not-available">Cancel</div>
                                                                                                            </div>
                                                                                                            <div>
                                                                                                                <div class="block-tracking">Tracking</div>
                                                                                                            </div>
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
                                                                                                    <li class="product-item gap14">
                                                                                                        <div class="image no-bg">
                                                                                                            <img src="images/products/57.png" alt="">
                                                                                                        </div>
                                                                                                        <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                            <div class="name">
                                                                                                                <a href="product-list.html" class="body-title-2">Kristin Watson</a>
                                                                                                            </div>
                                                                                                            <div class="body-text">#7712309</div>
                                                                                                            <div class="body-text">$1,452.500</div>
                                                                                                            <div class="body-text">1,638</div>
                                                                                                            <div class="body-text">20</div>
                                                                                                            <div>
                                                                                                                <div class="block-available">Success</div>
                                                                                                            </div>
                                                                                                            <div>
                                                                                                                <div class="block-tracking">Tracking</div>
                                                                                                            </div>
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
                                                                                                    <li class="product-item gap14">
                                                                                                        <div class="image no-bg">
                                                                                                            <img src="images/products/58.png" alt="">
                                                                                                        </div>
                                                                                                        <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                            <div class="name">
                                                                                                                <a href="product-list.html" class="body-title-2">Kristin Watson</a>
                                                                                                            </div>
                                                                                                            <div class="body-text">#7712309</div>
                                                                                                            <div class="body-text">$1,452.500</div>
                                                                                                            <div class="body-text">1,638</div>
                                                                                                            <div class="body-text">20</div>
                                                                                                            <div>
                                                                                                                <div class="block-available">Success</div>
                                                                                                            </div>
                                                                                                            <div>
                                                                                                                <div class="block-tracking">Tracking</div>
                                                                                                            </div>
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
                                                                                                    <li class="product-item gap14">
                                                                                                        <div class="image no-bg">
                                                                                                            <img src="images/products/59.png" alt="">
                                                                                                        </div>
                                                                                                        <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                            <div class="name">
                                                                                                                <a href="product-list.html" class="body-title-2">Kristin Watson</a>
                                                                                                            </div>
                                                                                                            <div class="body-text">#7712309</div>
                                                                                                            <div class="body-text">$1,452.500</div>
                                                                                                            <div class="body-text">1,638</div>
                                                                                                            <div class="body-text">20</div>
                                                                                                            <div>
                                                                                                                <div class="block-available">Success</div>
                                                                                                            </div>
                                                                                                            <div>
                                                                                                                <div class="block-tracking">Tracking</div>
                                                                                                            </div>
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
                                                                                                    <li class="product-item gap14">
                                                                                                        <div class="image no-bg">
                                                                                                            <img src="images/products/60.png" alt="">
                                                                                                        </div>
                                                                                                        <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                            <div class="name">
                                                                                                                <a href="product-list.html" class="body-title-2">Kristin Watson</a>
                                                                                                            </div>
                                                                                                            <div class="body-text">#7712309</div>
                                                                                                            <div class="body-text">$1,452.500</div>
                                                                                                            <div class="body-text">1,638</div>
                                                                                                            <div class="body-text">20</div>
                                                                                                            <div>
                                                                                                                <div class="block-available">Success</div>
                                                                                                            </div>
                                                                                                            <div>
                                                                                                                <div class="block-tracking">Tracking</div>
                                                                                                            </div>
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
                                                                                <jsp:include page = "/view/common/nutritionist/footer.jsp"></jsp:include>
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
