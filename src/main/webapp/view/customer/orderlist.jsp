<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <head>
        <style>
            .wg-table.table-all-category ul.table-title li {
                width: 120px !important;
            }
            .wg-table.table-all-category ul.table-title li:first-child {
                width: 100px;
                flex-shrink: 0;
            }
            ul.table-title {
                display: flex;
                padding: 0;
                margin: 0;
                list-style: none;
            }

            .wg-table.table-all-category .product-item > .flex > div:first-child {
                width: 25vh !important;
                flex-shrink: 0;
            }


            .block-available.pending {
                background-color: #e0e0e0;
                color: #555;
            }

            .block-available.accepted {
                background-color: #d4f6dd;
                color: #2e7d32;
            }

            .block-available.cancelled {
                background-color: #fdecea;
                color: #c62828;
            }

            .block-available.complete {
                background-color: #fff8e1;
                color: #f9a825;
            }

        </style>
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
                                                                                                                <select name="status" required>
                                                                                                                    <option value="">-- Filter by Status --</option>
                                                                                                                    <option value="pending">Pending</option>
                                                                                                                    <option value="accepted">Accepted</option>
                                                                                                                    <option value="cancelled">Cancelled</option>
                                                                                                                    <option value="completed">Completed</option>
                                                                                                                </select>                                                                                                            </fieldset>
                                                                                                            <div class="button-submit">
                                                                                                                <button class="" type="submit"><i class="icon-search"></i></button>
                                                                                                            </div>
                                                                                                        </form>
                                                                                                    </div>
                                                                                                </div>
                                                                                                <div class="wg-table table-all-category">
                                                                                                    <ul class="table-title">
                                                                                                        <li>
                                                                                                            <div class="body-title">Order ID</div>
                                                                                                        </li>    
                                                                                                        <li>
                                                                                                            <div class="body-title">Ship Address</div>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <div class="body-title">Total</div>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <div class="body-title">Create At</div>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <div class="body-title">Payment Method</div>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <div class="body-title">Status</div>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <div class="body-title">Active</div>
                                                                                                        </li>
                                                                                                    </ul>
                                                                                                    <ul class="flex flex-column">

                                                                                                    <c:forEach var="order" items="${orderList}">
                                                                                                        <li class="product-item gap14">
                                                                                                            <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                                <div class="body-text">#${order.order_id} </div>
                                                                                                                <div class="body-text">${order.shipping_address} </div>
                                                                                                                <div class="body-text">
                                                                                                                    <fmt:formatNumber value="${order.total}" type="number" maxFractionDigits="0" groupingUsed="true"/> VND
                                                                                                                </div>
                                                                                                                <div class="body-text">${order.created_at} </div>
                                                                                                                <div class="body-text"><a>${order.payment_method}</a></div>
                                                                                                                <div>
                                                                                                                    <div class="block-available ${order.status}"><a>${order.status}</a></div>
                                                                                                                </div>
                                                                                                                <div class="list-icon-function">
                                                                                                                    <a href="${pageContext.request.contextPath}/orderdetail?order_id=${order.order_id}" class="item eye">
                                                                                                                        <i class="icon-eye"></i>
                                                                                                                    </a>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </li>
                                                                                                    </c:forEach>
                                                                                                </ul>
                                                                                            </div>
                                                                                            <div class="flex items-center justify-between flex-wrap gap10">
                                                                                                <div class="divider"></div>
                                                                                                <div class="pagination">

                                                                                                    <ul class="wg-pagination">

                                                                                                        <li>
                                                                                                            <a href="${pageContext.request.contextPath}/orderlist?page=1"><i class="icon-chevron-left"></i></a>
                                                                                                        </li>
                                                                                                        <c:choose>
                                                                                                            <c:when test="${currentPage <= totalPages - 2}">
                                                                                                                <c:if test="${currentPage > 1}">
                                                                                                                    <li class="">
                                                                                                                        <a href="${pageContext.request.contextPath}/orderlist?page=${currentPage - 1}">${currentPage - 1}</a>
                                                                                                                    </li>
                                                                                                                </c:if>
                                                                                                                <li class="active">
                                                                                                                    <a href="${pageContext.request.contextPath}/orderlist?page=${currentPage}">${currentPage}</a>
                                                                                                                </li>

                                                                                                                <li class="">
                                                                                                                    <a href="${pageContext.request.contextPath}/orderlist?page=${currentPage + 1}">${currentPage + 1}</a>
                                                                                                                </li>

                                                                                                                <c:if test="${currentPage < totalPages - 2}">
                                                                                                                    <li>
                                                                                                                        <span>...</span>
                                                                                                                    </li>
                                                                                                                </c:if>


                                                                                                                <li class="">
                                                                                                                    <a href="${pageContext.request.contextPath}/orderlist?page=${totalPages}">${totalPages}</a>
                                                                                                                </li>
                                                                                                            </c:when>

                                                                                                            <c:otherwise>
                                                                                                                <c:forEach begin="${totalPages-2 <= 0 ? 1 : totalPages - 2}" end="${totalPages}" var="i">
                                                                                                                    <li class="${currentPage == i ? 'active' : ''}">
                                                                                                                        <a href="${pageContext.request.contextPath}/orderlist?page=${i}">${i}</a>
                                                                                                                    </li>
                                                                                                                </c:forEach>
                                                                                                            </c:otherwise>
                                                                                                        </c:choose>

                                                                                                        <li>
                                                                                                            <a href="${pageContext.request.contextPath}/orderlist?page=${totalPages}"><i class="icon-chevron-right"></i></a>
                                                                                                        </li>
                                                                                                    </ul>

                                                                                                </div>
                                                                                            </div>
                                                                                            <!-- /order-list -->
                                                                                        </div>
                                                                                        <!-- /main-content-wrap -->
                                                                                    </div>
                                                                                    <!-- /main-content-wrap -->

                                                                                </div>
                                                                                <!-- /main-content -->
                                                                                <!-- bottom-page -->
                                                                                <jsp:include page = "/view/common/nutritionist/footer.jsp"></jsp:include>
                                                                                <!-- /bottom-page -->
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