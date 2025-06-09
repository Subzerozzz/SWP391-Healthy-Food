<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<!--<![endif]-->


<!-- Mirrored from themesflat.co/html/remos/product-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:35 GMT -->
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

    <!-- Link IzisToast -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>

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
                <jsp:include page="../../common/nutritionist/sidebar.jsp"></jsp:include>
                <!-- /section-menu-left -->
                <!-- section-content-right -->
                <div class="section-content-right">
                    <!-- header-dashboard -->
                <jsp:include page="../../common/nutritionist/headerdashboard.jsp"></jsp:include>    
                    <!-- /header-dashboard -->
                    <!-- main-content -->
                    <div class="main-content">
                        <!-- main-content-wrap -->
                        <div class="main-content-inner">
                            <!-- main-content-wrap -->
                            <div class="main-content-wrap">
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                    <h3>Blog List</h3>
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
                                            <div class="text-tiny">Blog List</div>
                                        </li>
                                    </ul>
                                </div>
                                <!-- product-list -->
                                <div class="wg-box">
                                    <div class="title-box">
                                        <i class="icon-coffee"></i>
                                        <div class="body-text">Tip search by Blog ID: Each blog is provided with a unique ID, which you can rely on to find the exact product you need.</div>
                                    </div>
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
                                            <form class="search-box" action="${pageContext.request.contextPath}/manage-blog" method="get">
                                            <div class="search-container">
                                                <input type="text" name="search" placeholder="Search here..." value="${param.search}">
                                                    <input type="hidden" name="action" value="search">
                                                        <button type="submit"><i class="icon-search"></i></button>
                                                        </div>
                                            </form>
                           <style>
                               .blog-table-container {
                                   font-family: 'Segoe UI', sans-serif;
                                   width: 100%;
                                   border-radius: 8px;
                                   overflow: hidden;
                                   box-shadow: 0 2px 8px rgba(0,0,0,0.1);
                                   background: #fff;
                               }

                               .table-header, .table-row {
                                   display: flex;
                                   padding: 14px 20px;
                                   border-bottom: 1px solid #eee;
                                   align-items: center;
                               }

                               .table-header {
                                   font-weight: 600;
                                   background-color: #f8f9fa;
                                   color: #333;
                                   text-transform: uppercase;
                               }

                               .col {
                                   flex: 1;
                                   min-width: 150px;
                                   word-break: break-word;
                               }

                               .blog-title {
                                   font-weight: 500;
                                   color: #212529;
                                   text-decoration: none;
                               }

                               .blog-title:hover {
                                   text-decoration: underline;
                               }

                               .status {
                                   padding: 6px 16px;
                                   border-radius: 20px;
                                   font-weight: bold;
                                   display: inline-block;
                                   font-size: 13px;
                               }

                               .status.inactive {
                                   background-color: #e74c3c;
                                   color: white;
                               }
                               .col.actions a {
                                   display: inline-flex;
                                   align-items: center;
                                   justify-content: center;
                                   width: 30px;         /* Tăng kích thước nút */
                                   height: 20px;
                                   border-radius: 50%;
                                   background-color: #f1f1f1;
                                   transition: background-color 0.3s, transform 0.2s;
                                   text-decoration: none;
                               }
                               td.actions {
                                   text-align: center;
                                   vertical-align: middle; /* canh giữa theo chiều dọc nếu cần */
                               }

                               /* Flexbox canh giữa nội dung bên trong */
                               .col.actions {
                                   display: inline-flex;
                                   justify-content: center;
                                   align-items: center;
                                   gap: 30px;
                               }
                               .col.actions a:hover {
                                   background-color: #e0e0e0;
                                   transform: scale(1.1);
                               }

                               .col.actions i {
                                   font-size: 25px; /* Tăng kích thước icon */
                               }

                               /* Icon màu sắc tương ứng */
                               .icon-eye {
                                   color: #007bff; /* Xanh biển */
                               }
                               .icon-edit-3 {
                                   color: #28a745; /* Xanh lá */
                               }
                               .icon-trash-2 {
                                   color: #dc3545; /* Đỏ */
                               }
                               .search-container {
                                   display: flex;
                                   align-items: center;
                                   border: 1px solid #e2e8f0; /* viền nhạt */
                                   border-radius: 12px; /* bo góc */
                                   padding: 8px 12px;
                                   background-color: white;
                                   max-width: 600px;
                                   width: 100%;
                               }

                               .search-container input[type="text"] {
                                   border: none;
                                   outline: none;
                                   flex: 1;
                                   font-size: 16px;
                                   color: #333;
                               }

                               .search-container input[type="text"]::placeholder {
                                   color: #a0aec0; /* xám nhạt */
                               }

                               .search-container button {
                                   background: none;
                                   border: none;
                                   cursor: pointer;
                                   padding: 0;
                               }

                               .search-container i.icon-search {
                                   font-size: 18px;
                                   color: #333;
                               }
                           </style>    
                                        </div>  
                                        <a class="tf-button style-1 w208" href="${pageContext.request.contextPath}/manage-blog?action=add&id=${blog.id}"><i class="icon-plus"></i>Add new</a>
                                    </div>
                       

                         <div class="wg-table table-product-list">
                             <!-- Header -->
                             <div class="table-header">
                                 <div class="col">Blog</div>
                                 <div class="col" style="margin-left: 70px">Blog ID</div>
                                 <div class="col" style="margin-left: 120px">Author</div>
                                 <div class="col"style="margin-left: 100px">Date</div> 
                                 <div class="col"style="margin-left: 100px">Status</div>
                                 <div class="col" style="margin-left: 130px;">Action</div>
                             </div>

                             <!-- Data Rows -->
                             <c:forEach items="${blogs}" var="blog">
                                 <div class="table-row">
                                     <div class="col">
                                         <a href="list.jsp" class="body-title-2">${blog.title}</a>
                                     </div>
                                     <div class="col">${blog.id}</div>
                                     <div class="col">${blog.author}</div>
                                     <div class="col">${blog.birth_date}</div> 
                                     <div class="col">
                                         <span style="background-color: #E74C3C;; color: white; padding: 6px 16px; border-radius: 20px; display: inline-block; font-weight: bold;">
                                             ${blog.status}
                                         </span>

                                     </div>
                                     <div class="col actions ">
                                         <a href="${pageContext.request.contextPath}/manage-blog?action=view&id=${blog.id}"><i class="icon-eye"></i></a>
                                         <a href="${pageContext.request.contextPath}/manage-blog?action=edit&id=${blog.id}"> <i class="icon-edit-3"></i></a>
                                         <a href="${pageContext.request.contextPath}/manage-blog?action=delete&id=${blog.id}"><i class="icon-trash-2"></i></a>
                                     </div>
                                 </div>
                             </c:forEach>
                         </div>
                                    <div class="divider"></div>
                                    <div class="flex items-center justify-between flex-wrap gap10">
                                        <div class="text-tiny">Showing ${blogs.size()} entries</div>
                                        
                                        <ul class="wg-pagination">
                                            <c:set var="queryString" value="&search=${param.search}&action=${param.action}" />
                                            <!-- Nút Trang Trước -->
                                            <li class="${currentPage == 1 ? 'disabled' : ''}">
                                                <c:choose>
                                                    <c:when test="${currentPage > 1}">
                                                        <a href="${pageContext.request.contextPath}/manage-blog?index=${currentPage - 1}${queryString}">
                                                            <i class="icon-chevron-left"></i>
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="javascript:void(0);">
                                                            <i class="icon-chevron-left"></i>
                                                        </a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </li>

                                            <!-- Số trang -->
                                            <c:choose>
                                                <c:when test="${currentPage < totalPage - 2}">
                                                    <li class="active">
                                                        <a href="${pageContext.request.contextPath}/manage-blog?index=${currentPage}${queryString}">${currentPage}</a>
                                                    </li>
                                                    <li>
                                                        <a href="${pageContext.request.contextPath}/manage-blog?index=${currentPage + 1}${queryString}">${currentPage + 1}</a>
                                                    </li>
                                                    <li><span>...</span></li>
                                                    <li>
                                                        <a href="${pageContext.request.contextPath}/manage-blog?index=${totalPage}${queryString}">${totalPage}</a>
                                                    </li>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set var="startPage" value="${totalPage > 2 ? totalPage - 2 : 1}" />
                                                    <c:forEach begin="${startPage}" end="${totalPage}" var="i">
                                                        <li class="${currentPage == i ? 'active' : ''}">
                                                            <a href="${pageContext.request.contextPath}/manage-blog?index=${i}${queryString}">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>

                                            <!-- Nút Trang Sau -->
                                            <li class="${currentPage == totalPage ? 'disabled' : ''}">
                                                <c:choose>
                                                    <c:when test="${currentPage < totalPage}">
                                                        <a href="${pageContext.request.contextPath}/manage-blog?index=${currentPage + 1}${queryString}">
                                                            <i class="icon-chevron-right"></i>
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="javascript:void(0);">
                                                            <i class="icon-chevron-right"></i>
                                                        </a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <!-- /product-list -->
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
    <!--Thông báo xóa--> 
     <c:if test="${isDelete == true}">
         <script>
           document.addEventListener("DOMContentLoaded", function () {
             iziToast.error({
                 title: "Thông báo",
                 message: "Yêu cầu xóa blog của bạn đã được gửi đi",
                 position: 'topRight',
                 timeout: 5000,
                 backgroundColor:"#d4edda"
                 });
           });
         </script>
         <!--Xóa đi biến isDelete sau khi đã thông báo--> 
         <%
             session.removeAttribute("isDelete");
         %>
     </c:if>

     <!--Thông báo add-->
     <c:if test="${isAdd == true}">
         <script>
           document.addEventListener("DOMContentLoaded", function () {
             iziToast.error({
                 title: "Thông báo",
                 message: "Yêu cầu tạo blog của bạn đã được gửi đi",
                 position: 'topRight',
                 timeout: 5000,
                 backgroundColor:"#d4edda"
                 });
           });
         </script>
         <!--Xóa đi biến isAdd sau khi đã thông báo--> 
         <%
             session.removeAttribute("isAdd");
         %>
     </c:if>

     <!--Thông báo update--> 

     <c:if test="${isUpdate == true}">
         <script>
           document.addEventListener("DOMContentLoaded", function () {
             iziToast.error({
                 title: "Thông báo",
                 message: "Yêu cầu chỉnh sửa blog của bạn đã được gửi đi",
                 position: 'topRight',
                 timeout: 5000,
                 backgroundColor:"#d4edda"
                 });
           });
         </script>
         <!--Xóa đi biến isAdd sau khi đã thông báo--> 
         <%
             session.removeAttribute("isUpdate");
         %>
     </c:if>
</body>
<!-- Mirrored from themesflat.co/html/remos/product-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:40 GMT -->
</html>