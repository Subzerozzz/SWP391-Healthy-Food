<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>

    <!-- Mirrored from themesflat.co/html/remos/category-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:47 GMT -->
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
        <!-- Basic Page Needs -->
        <!--link izitoatMess-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
            <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>
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
                                                                            <jsp:include page = "./section-menu-left.jsp"></jsp:include>
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
                                                                                            <div class="main-content-wrap">
                                                                                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                                                                                    <h3>All category</h3>
                                                                                                    <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                                                                                        <li>
                                                                                                            <a href="index.html"><div class="text-tiny">Dashboard</div></a>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <i class="icon-chevron-right"></i>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <a href="#"><div class="text-tiny">Category</div></a>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <i class="icon-chevron-right"></i>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <div class="text-tiny">All category</div>
                                                                                                        </li>
                                                                                                    </ul>
                                                                                                </div>
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
                                                                                                        <a class="tf-button style-1 w208" href="${pageContext.request.contextPath}/manageCategory?action=addCate"><i class="icon-plus"></i>Add new</a>
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
                                                                                                                                <a href="${pageContext.request.contextPath}/manageCategory?action=viewDetail&idcategory=${cate.idcategory}" ><i class="fa-regular fa-eye"></i></a>

                                                                                                                            </div>
                                                                                                                            <div class="item edit">
                                                                                                                                <a href="${pageContext.request.contextPath}/manageCategory?action=edit&idcategory=${cate.idcategory}"><i class="fa-solid fa-user-pen"></i></a>


                                                                                                                            </div>
                                                                                                                            <div class="item trash">
                                                                                                                                <a href="${pageContext.request.contextPath}/manageCategory?action=delete&idcategory=${cate.idcategory}"><i class="fa-solid fa-trash"></i></a>

                                                                                                                            </div>
                                                                                                                        </div>
                                                                                                                    </div>
                                                                                                                </li>
                                                                                                            </c:forEach>


                                                                                                        </ul>
                                                                                                    </div>
                                                                                                </form>
                                                                                                <!--                                                                                         
                                                                                                -->                                                                                                                                                                                    <div class="divider"></div>
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
                                                                                            <!-- /all-category 
                                                                                        </div>
                                                                                            <!-- /main-content-wrap -->
                                                                                        </div>
                                                                                        <!-- /main-content-wrap -->
                                                                                        <!-- bottom-page -->

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
                                                                    <!--thong bao xoa-->

                                                                    <c:if test="${sessionScope.isDelete == true}">
                                                                        <script>
                                                                            document.addEventListener("DOMContentLoaded", function () {
                                                                                iziToast.success({
                                                                                    title: "Thông báo",
                                                                                    message: "Tài khoản đã được xóa thành công!",
                                                                                    position: 'topRight',
                                                                                    timeout: 5000,
                                                                                    backgroundColor: "#d4edda"
                                                                                });
                                                                            });
                                                                        </script>
                                                                        <% session.removeAttribute("isDelete"); %>
                                                                    </c:if>
                                                                    <c:if test="${sessionScope.isUpdate == true}">
                                                                        <script>
                                                                            document.addEventListener("DOMContentLoaded", function () {
                                                                                iziToast.success({
                                                                                    title: "Thông báo",
                                                                                    message: "Tài khoản đã được update thành công!",
                                                                                    position: 'topRight',
                                                                                    timeout: 5000,
                                                                                    backgroundColor: "#d4edda"
                                                                                });
                                                                            });
                                                                        </script>
                                                                        <% session.removeAttribute("isUpdate"); %>
                                                                    </c:if>
                                                            </body>


                                                            <!-- Mirrored from themesflat.co/html/remos/category-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
                                                            </html>