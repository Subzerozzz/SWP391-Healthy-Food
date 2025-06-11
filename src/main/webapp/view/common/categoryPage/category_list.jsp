<%-- 
    Document   : category_list
    Created on : Jun 11, 2025, 9:36:10 AM
    Author     : Hang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->


    <!-- Mirrored from themesflat.co/html/remos/category-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:47 GMT -->
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
                                                                        <jsp:include page = "section-menu-left.jsp"></jsp:include>
                                                                            <!-- /section-menu-left -->
                                                                            <!-- section-content-right -->
                                                                            <div class="section-content-right">
                                                                                <!-- header-dashboard -->
                                                                            <jsp:include page = "header.jsp"></jsp:include>
                                                                                <!-- /header-dashboard -->
                                                                                <!-- main-content -->
                                                                                <div class="main-content">
                                                                                    <!-- main-content-wrap -->
                                                                                    <div class="main-content-inner">
                                                                                        <!-- main-content-wrap -->

                                                                                        <!-- all-category -->
                                                                                        <div class="wg-box">
                                                                                            <div class="flex items-center justify-between gap10 flex-wrap">
                                                                                                <div class="wg-filter flex-grow">
                                                                                                    <div class="show">
                                                                                                        <div class="text-tiny">Showing</div>
                                                                                                        <div class="select">
                                                                                                            <select class="">
                                                                                                                <option>10</option>
                                                                                                                <option>20</option>
                                                                                                                <option>30</option>
                                                                                                            </select>
                                                                                                        </div>
                                                                                                        <div class="text-tiny">entries</div>
                                                                                                    </div>
                                                                                                    <form class="form-search">
                                                                                                        <fieldset class="name">
                                                                                                            <input type="text" placeholder="Search here..." class="" name="name" tabindex="2" value="" aria-required="true" required="">
                                                                                                        </fieldset>
                                                                                                        <div class="button-submit">
                                                                                                            <button class="" type="submit"><i class="icon-search"></i></button>
                                                                                                        </div>
                                                                                                    </form>
                                                                                                </div>
                                                                                                <a class="tf-button style-1 w208" href="new-category.html"><i class="icon-plus"></i>Add new</a>
                                                                                            </div>
                                                                                            <form class ="action" method="get" action="${pageContext.request.contextPath}/manageCategory">
                                                                                            <div class="wg-table table-all-category">
                                                                                                <ul class="table-title flex gap20 mb-14">
                                                                                                    <li>
                                                                                                        <div class="body-title">ID</div>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="body-title">Category</div>
                                                                                                    </li>  
                                                                                                    <li>
                                                                                                        <div class="body-title">MinBMI</div>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="body-title">MaxBMI</div>
                                                                                                    </li>

                                                                                                    <li>
                                                                                                        <div class="body-title">Description</div>
                                                                                                    </li>

                                                                                                    <li>
                                                                                                        <div class="body-title">Action</div>
                                                                                                    </li>
                                                                                                </ul>
                                                                                                <ul class="flex flex-column">
                                                                                                    <c:forEach items ="${listcategory}" var ="cate"> 
                                                                                                        <li class="product-item gap14">
                                                                                                         
                                                                                                            <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                                <div class="body-title">
                                                                                                                    <a href="product-list.html" class="body-title-2">${cate.idcategory}</a>
                                                                                                                </div>
                                                                                                                <div class="body-title-2">
                                                                                                                    <a href="product-list.html" class="body-title-2">${cate.name_category}</a>
                                                                                                                </div>
                                                                                                                <div class="body-title-2">${cate.minBMI}</div>
                                                                                                                <div class="body-title-2">
                                                                                                                    ${cate.maxBMI}
                                                                                                                </div>
                                                                                                                <div class ="body-title-2">
                                                                                                                    ${cate.description}
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
                                                                                                    </c:forEach>


                                                                                                </ul>
                                                                                            </div>
                                                                                        </form>

                                                                                        <div class="divider"></div>
                                                                                        <div class="flex items-center justify-between flex-wrap gap10">
                                                                                            <div class="text-tiny">
                                                                                                Showing ${startRecord} to ${endRecord} of ${totalCategory} entries
                                                                                            </div>

                                                                                            <c:if test="${totalPages > 1}">
                                                                                                <ul class="wg-pagination">
                                                                                                    <!-- Nút Previous -->
                                                                                                    <c:if test="${currentPage > 1}">
                                                                                                        <li>
                                                                                                            <a href="?action=${param.action}&role=${param.role}&status=${param.status}&page=${currentPage - 1}&pageSize=${pageSize}">
                                                                                                                <i class="icon-chevron-left"></i>
                                                                                                            </a>
                                                                                                        </li>
                                                                                                    </c:if>

                                                                                                    <!-- Hiển thị các trang -->
                                                                                                    <c:choose>
                                                                                                        <c:when test="${totalPages <= 7}">
                                                                                                            <!-- Hiển thị tất cả trang nếu <= 7 trang -->
                                                                                                            <c:forEach var="i" begin="1" end="${totalPages}">
                                                                                                                <li <c:if test="${i == currentPage}">class="active"</c:if>>
                                                                                                                    <a href="?action=${param.action}&role=${param.role}&status=${param.status}&page=${i}&pageSize=${pageSize}">${i}</a>
                                                                                                                </li>
                                                                                                            </c:forEach>
                                                                                                        </c:when>
                                                                                                        <c:otherwise>
                                                                                                            <!-- Hiển thị phân trang thông minh -->
                                                                                                            <c:if test="${currentPage > 1}">
                                                                                                                <li>
                                                                                                                    <a href="?action=${param.action}&role=${param.role}&status=${param.status}&page=1&pageSize=${pageSize}">1</a>
                                                                                                                </li>
                                                                                                            </c:if>

                                                                                                            <c:if test="${currentPage > 3}">
                                                                                                                <li><span>...</span></li>
                                                                                                                </c:if>

                                                                                                            <c:forEach var="i" begin="${currentPage > 2 ? currentPage - 1 : 1}" 
                                                                                                                       end="${currentPage < totalPages - 1 ? currentPage + 1 : totalPages}">
                                                                                                                <li <c:if test="${i == currentPage}">class="active"</c:if>>
                                                                                                                    <a href="?action=${param.action}&role=${param.role}&status=${param.status}&page=${i}&pageSize=${pageSize}">${i}</a>
                                                                                                                </li>
                                                                                                            </c:forEach>

                                                                                                            <c:if test="${currentPage < totalPages - 2}">
                                                                                                                <li><span>...</span></li>
                                                                                                                </c:if>

                                                                                                            <c:if test="${currentPage < totalPages}">
                                                                                                                <li>
                                                                                                                    <a href="?action=${param.action}&role=${param.role}&status=${param.status}&page=${totalPages}&pageSize=${pageSize}">${totalPages}</a>
                                                                                                                </li>
                                                                                                            </c:if>
                                                                                                        </c:otherwise>
                                                                                                    </c:choose>

                                                                                                    <!-- Nút Next -->
                                                                                                    <c:if test="${currentPage < totalPages}">
                                                                                                        <li>
                                                                                                            <a href="?action=${param.action}&role=${param.role}&status=${param.status}&page=${currentPage + 1}&pageSize=${pageSize}">
                                                                                                                <i class="icon-chevron-right"></i>
                                                                                                            </a>
                                                                                                        </li>
                                                                                                    </c:if>
                                                                                                </ul>
                                                                                            </c:if>
                                                                                        </div>

                                                                                    </div>
                                                                                    <!-- /all-category -->
                                                                                </div>
                                                                                <!-- /main-content-wrap -->
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


                                                        </body>


                                                        <!-- Mirrored from themesflat.co/html/remos/category-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
                                                        </html>
