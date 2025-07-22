<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                                     width: 3px;         /* Tăng kích thước nút */
                                     height: 2px;
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
                                     gap: 20px;
                                 }
                                 .col.actions a:hover {
                                     background-color: #e0e0e0;
                                     transform: scale(1.1);
                                 }

                                 .col.actions i {
                                     font-size: 15px; /* Tăng kích thước icon */
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
                                     padding: 10px 90px;
  ;
                                     background-color: white;
                                     max-width: 600px;
                                     width: 100%;
                                     margin-top: 40px
                                     bor
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
                                 * {
                                     margin: 0;
                                     padding: 0;
                                     box-sizing: border-box;
                                 }

                                 body {
                                     font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                                     background-color: #f8fafc;
                                     padding: 40px 20px;
                                 }

                                 /* Container chính cho filter và search */
                                 .filter-search-wrapper {
                                     background: white;
                                     padding: 24px;
                                     border-radius: 16px;
                                     box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
                                     border: 1px solid #e2e8f0;
                                 }

                                 .filter-search-container {
                                     display: flex;
                                     align-items: center;
                                     justify-content: space-between;
                                     gap: 24px;
                                     flex-wrap: wrap;
                                 }

                                 .left-controls {
                                     display: flex;
                                     align-items: center;
                                     gap: 20px;
                                     flex-wrap: wrap;
                                 }

                                 /* Form Filter Styling */
                                 .form-search {
                                     display: flex;
                                     align-items: center;
                                     gap: 12px;
                                     background: #f8fafc;
                                     padding: 10px;
                                     border-radius: 12px;
                                     border: 1px solid #e2e8f0;
                                     transition: all 0.3s ease;
                                 }

                                 .form-search:hover {
                                     border-color: #c7d2fe;
                                     box-shadow: 0 2px 8px rgba(99, 102, 241, 0.1);
                                 }

                                 .form-search label {
                                     font-weight: 600;
                                     color: #374151;
                                     font-size: 14px;
                                     white-space: nowrap;
                                     margin-right: 4px;
                                 }

                                 .form-search select {
                                     padding: 10px 16px;
                                     border: 1px solid #d1d5db;
                                     border-radius: 8px;
                                     background: white;
                                     font-size: 14px;
                                     color: #374151;
                                     min-width: 140px;
                                     cursor: pointer;
                                     transition: all 0.2s ease;
                                 }

                                 .form-search select:focus {
                                     outline: none;
                                     border-color: #6366f1;
                                     box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.1);
                                 }

                                 .form-search select:hover {
                                     border-color: #9ca3af;
                                 }

                                 .filter-button {
                                     background: #15803D;
                                     color: white;
                                     border: none;
                                     padding: 10px 20px;
                                     border-radius: 8px;
                                     font-weight: 600;
                                     font-size: 14px;
                                     cursor: pointer;
                                     transition: all 0.3s ease;
                                     box-shadow: 0 2px 8px rgba(99, 102, 241, 0.2);
                                     min-width: 80px;
                                 }

                                 .filter-button:hover {
                                     transform: translateY(-2px);
                                     box-shadow: 0 4px 16px rgba(99, 102, 241, 0.3);
                                 }

                                 .filter-button:active {
                                     transform: translateY(0);
                                 }

                                 /* Search Box Styling */
                                 .search-box {
                                     display: flex;
                                     align-items: center;
                                 }
                                 .search-container {
                                     display: flex;
                                     align-items: center;
                                     background: white;
                                     border: 1px solid #e2e8f0;
                                     border-radius: 12px;
                                     padding: 0;
                                     min-width: 300px;
                                     overflow: hidden;
                                     box-shadow: 0 2px 4px rgba(0, 0, 0, 0.04);
                                 }

                                 .search-container input[type="text"] {
                                     border: none;
                                     outline: none;
                                     padding: 12px 16px;
                                     flex: 1;
                                     font-size: 16px;
                                 }

                                 .search-container button {
                                     border: none;
                                     padding: 12px 16px;
                                     cursor: pointer;
                                     transition: background 0.3s ease;
                                     display: flex;
                                     align-items: center;
                                     justify-content: center;
                                 }

                                 .search-container button:hover {
                                     background: #2b6cb0; /* màu hover */
                                 }
                                 /* Icon font (basic implementation) */
                                 .icon-search::before {
                                     content: "🔍";
                                     font-style: normal;
                                 }

                                 /* Responsive Design */
                                 @media (max-width: 768px) {
                                     .filter-search-container {
                                         flex-direction: column;
                                         align-items: stretch;
                                         gap: 16px;
                                     }

                                     .left-controls {
                                         flex-direction: column;
                                         align-items: stretch;
                                         gap: 16px;
                                     }

                                     .search-container {
                                         min-width: auto;
                                         width: 100%;
                                     }

                                     .form-search {
                                         justify-content: space-between;
                                     }
                                 }

                                 @media (max-width: 480px) {
                                     .filter-search-wrapper {
                                         padding: 16px;
                                         margin: 0 10px;
                                     }

                                     .form-search {
                                         flex-direction: column;
                                         align-items: stretch;
                                         gap: 12px;
                                     }

                                     .form-search select {
                                         min-width: auto;
                                     }
                                 }

                                 /* Animation cho loading state */
                                 .loading {
                                     opacity: 0.7;
                                     pointer-events: none;
                                 }

                                 .loading .filter-button {
                                     background: #9ca3af;
                                 }

                                 /* Focus states for accessibility */
                                 .filter-button:focus,
                                 .form-search select:focus,
                                 .search-container button:focus {
                                     outline: 2px solid #6366f1;
                                     outline-offset: 2px;
                                 }
                                 .wg-table.table-product-list {
                                     width: 100%;
                                     background: #fff;
                                     border-radius: 8px;
                                     overflow: hidden;
                                     box-shadow: 0 2px 8px rgba(0,0,0,0.1);
                                 }

                                 .table-header {
                                     display: flex;
                                     align-items: center;
                                     background: #f8f9fa;
                                     padding: 16px 20px;
                                     border-bottom: 2px solid #e9ecef;
                                     font-weight: 600;
                                     color: #495057;
                                 }

                                 .table-row {
                                     display: flex;
                                     align-items: center;
                                     padding: 16px 20px;
                                     border-bottom: 1px solid #e9ecef;
                                     transition: background-color 0.2s ease;
                                 }

                                 .table-row:hover {
                                     background-color: #f8f9fa;
                                 }

                                 .table-row:last-child {
                                     border-bottom: none;
                                 }

                                 /* Định nghĩa độ rộng cố định cho từng cột */
                                 .table-header .col:nth-child(1),
                                 .table-row .col:nth-child(1) {
                                     flex: 0 0 25%; /* Blog title */
                                     padding-right: 15px;
                                 }

                                 .table-header .col:nth-child(2),
                                 .table-row .col:nth-child(2) {
                                     flex: 0 0 10%; /* Blog ID */
                                     padding-right: 15px;
                                     text-align: center;
                                 }

                                 .table-header .col:nth-child(3),
                                 .table-row .col:nth-child(3) {
                                     flex: 0 0 15%; /* Author */
                                     padding-right: 15px;
                                 }

                                 .table-header .col:nth-child(4),
                                 .table-row .col:nth-child(4) {
                                     flex: 0 0 12%; /* Date */
                                     padding-right: 15px;
                                 }

                                 .table-header .col:nth-child(5),
                                 .table-row .col:nth-child(5) {
                                     flex: 0 0 12%; /* Status */
                                     padding-right: 15px;
                                 }

                                 .table-header .col:nth-child(6),
                                 .table-row .col:nth-child(6) {
                                     flex: 0 0 15%; /* Actions */
                                     text-align: center;
                                 }

                                 /* Loại bỏ margin-left inline styles */
                                 .table-header .col,
                                 .table-row .col {
                                     margin-left: 0 !important;
                                 }

                                 /* Style cho blog title link */
                                .body-title-2 {
                                    color: #000000; /* Màu đen */
                                    text-decoration: none;
                                    font-weight: 500;
                                    transition: color 0.2s ease;
                                }

                                .body-title-2:hover {
                                    color: #000000; /* Màu đen khi hover */
                                    text-decoration: underline;
                                }

                                 /* Style cho status badge */
                                 .table-row .col span {
                                     background-color: #E74C3C !important;
                                     color: white;
                                     padding: 6px 16px;
                                     border-radius: 20px;
                                     display: inline-block;
                                     font-weight: bold;
                                     font-size: 12px;
                                     text-transform: uppercase;
                                 }
                                 /* Responsive design */
                                 @media (max-width: 768px) {
                                     .table-header .col:nth-child(1),
                                     .table-row .col:nth-child(1) {
                                         flex: 0 0 30%;
                                     }

                                     .table-header .col:nth-child(2),
                                     .table-row .col:nth-child(2) {
                                         flex: 0 0 8%;
                                     }

                                     .table-header .col:nth-child(3),
                                     .table-row .col:nth-child(3) {
                                         flex: 0 0 15%;
                                     }

                                     .table-header .col:nth-child(4),
                                     .table-row .col:nth-child(4) {
                                         flex: 0 0 12%;
                                     }

                                     .table-header .col:nth-child(5),
                                     .table-row .col:nth-child(5) {
                                         flex: 0 0 12%;
                                     }

                                     .table-header .col:nth-child(6),
                                     .table-row .col:nth-child(6) {
                                         flex: 0 0 15%;
                                     }
                                 }
        </style>    
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
                                <div class="flex items-center flex-wrap justify-between gap20 mb-10">
                                    <h3>Blog List</h3>
                                </div>
                                <!-- product-list -->
                                    <div class="filter-search-wrapper">
                                        <div class="filter-search-container">
                                            <div class="left-controls">
                                                <!-- Form Filter -->
                                                <form class="form-search" action="${pageContext.request.contextPath}/manage-blog" method="GET">
                                                <input type="hidden" name="action" value="filter" />
                                                <label for="status">Status:</label>
                                                <select name="status" id="status" class="form-control">
                                                    <option value="">All Status</option>
                                                    <option value="Active">Active</option>
                                                    <option value="Inactive">Inactive</option>
                                                </select>
                                                <button type="submit" class="filter-button">Filter</button>
                                            </form>
                                          <!-- Search -->
                                          <form class="search-box" action="${pageContext.request.contextPath}/manage-blog" method="get">
                                              <input type="hidden" name="action" value="search">
                                                  <div class="search-container">
                                                      <input type="text" name="search" placeholder="Search here...">
                                                          <button type="submit">
                                                              <i class="icon-search"></i>
                                                          </button>
                                                  </div>
                                          </form>
                                        </div>  
                                        <a class="tf-button style-1 w208" href="${pageContext.request.contextPath}/manage-blog?action=add&id=${blog.id}"><i class="icon-plus"></i>Add new</a>
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
                                     <div class="col body-title-2">${blog.id}</div>
                                     <div class="col body-title-2">${blog.author}</div>
                                     <div class="col body-title-2">${blog.created_Date}</div> 
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
                                        <!-- # Phân Trang -->
                                        <ul class="wg-pagination">
                                            <c:set var="queryString" value="&search=${param.search}&action=${param.action}&status=${param.status}" />

                                            <!-- Nút Trang Trước (về trang 1) -->
                                            <li class="${currentPage == 1 ? 'disabled' : ''}">
                                                <c:choose>
                                                    <c:when test="${currentPage > 1}">
                                                        <a href="${pageContext.request.contextPath}/manage-blog?index=1${queryString}">
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

                                            <!-- Hiển thị số trang (giữ nguyên phần này nếu bạn muốn hiển thị số) -->
                                            <c:choose>
                                                <%-- Nếu còn nhiều hơn 3 trang phía sau --%>
                                                <c:when test="${currentPage <= totalPage - 2}">
                                                    <c:if test="${currentPage > 1}">
                                                        <li>
                                                            <a href="${pageContext.request.contextPath}/manage-blog?index=${currentPage - 1}${queryString}">${currentPage - 1}</a>
                                                        </li>
                                                    </c:if>
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
                                                <%-- Nếu đang ở gần cuối thì chỉ hiển thị 3 trang cuối --%>
                                                <c:otherwise>
                                                    <c:set var="startPage" value="${totalPage - 2 <= 0 ? 1 : totalPage - 2}" />
                                                    <c:forEach begin="${startPage}" end="${totalPage}" var="i">
                                                        <li class="${currentPage == i ? 'active' : ''}">
                                                            <a href="${pageContext.request.contextPath}/manage-blog?index=${i}${queryString}">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                            <!-- Nút Trang Sau (về trang cuối cùng) -->
                                            <li class="${currentPage == totalPage ? 'disabled' : ''}">
                                                <c:choose>
                                                    <c:when test="${currentPage < totalPage}">
                                                        <a href="${pageContext.request.contextPath}/manage-blog?index=${totalPage}${queryString}">
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
                                <!-- /product-list -->
                            </div>
                            <!-- /main-content-wrap -->
                        </div>
                        <!-- /main-content-wrap -->


                    </div>
                             
                    <!-- /main-content -->
                </div>
                                              
                <!-- /section-content-right -->
            </div>
             <!-- bottom-page -->
                        <jsp:include page="../../common/footer.jsp"></jsp:include>
                    <!-- /bottom-page -->    
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
                 message: "Yêu cầu xóa blog của bạn đã thành công",
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
                 message: "Yêu cầu tạo blog của bạn đã thành công",
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
                 message: "Yêu cầu chỉnh sửa blog của bạn đã thành công",
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