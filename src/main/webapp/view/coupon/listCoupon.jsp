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
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8fafc;
        }

        /* Filter and Search Section */
        .filter-search-wrapper {
            max-width: 1400px;
            margin: 0 auto 24px;
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

        .form-search {
            display: flex;
            align-items: center;
            gap: 12px;
            background: #f8fafc;
            padding: 16px 20px;
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

        .filter-button {
            background: linear-gradient(135deg, #6366f1 0%, #8b5cf6 100%);
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 8px;
            font-weight: 600;
            font-size: 14px;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0 2px 8px rgba(99, 102, 241, 0.2);
        }

        .filter-button:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 16px rgba(99, 102, 241, 0.3);
        }

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
            background: #3b82f6;
            color: white;
        }

        .search-container button:hover {
            background: #2b6cb0;
        }

        .icon-search::before {
            content: "🔍";
            font-style: normal;
        }

        /* Coupon Table Styles */
        .wg-table.table-coupon-list {
            width: 100%;
            max-width: 1400px;
            margin: 0 auto;
            background: #fff;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
            border: 1px solid #e2e8f0;
        }

        .table-header {
            display: grid;
            grid-template-columns: 120px 80px 180px 100px 120px 120px 100px 100px 120px 100px 100px 100px 120px;
            align-items: center;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            padding: 16px 20px;
            color: white;
            font-weight: 600;
            font-size: 13px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .table-row {
            display: grid;
            grid-template-columns: 120px 80px 180px 100px 120px 120px 100px 100px 120px 100px 100px 100px 120px;
            align-items: center;
            padding: 16px 20px;
            border-bottom: 1px solid #e9ecef;
            transition: all 0.2s ease;
            font-size: 14px;
        }

        .table-row:hover {
            background-color: #f8f9fa;
            transform: translateY(-1px);
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .table-row:last-child {
            border-bottom: none;
        }

        .col {
            padding: 0 8px;
            word-break: break-word;
        }

        /* Coupon Code Styling */
        .coupon-code {
            font-weight: 700;
            color: #1a365d;
            font-family: 'Courier New', monospace;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 6px 12px;
            border-radius: 8px;
            font-size: 12px;
            text-transform: uppercase;
            letter-spacing: 1px;
            display: inline-block;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        /* Status Badge */
        .status {
            padding: 6px 16px;
            border-radius: 20px;
            font-weight: 600;
            display: inline-block;
            font-size: 11px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .status.active {
            background: linear-gradient(135deg, #10b981 0%, #059669 100%);
            color: white;
            box-shadow: 0 2px 4px rgba(16, 185, 129, 0.3);
        }

        .status.inactive {
            background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
            color: white;
            box-shadow: 0 2px 4px rgba(239, 68, 68, 0.3);
        }

        /* Discount Value */
        .discount-value {
            font-weight: 700;
            color: #dc2626;
            font-size: 15px;
        }

        /* Currency Display */
        .currency {
            color: #059669;
            font-weight: 600;
            font-size: 14px;
        }

        /* Usage Progress */
        .usage-info {
            font-size: 12px;
            color: #6b7280;
            margin-bottom: 4px;
            text-align: center;
        }

        .usage-bar {
            width: 60px;
            height: 6px;
            background: #e5e7eb;
            border-radius: 3px;
            overflow: hidden;
            margin: 0 auto;
        }

        .usage-fill {
            height: 100%;
            background: linear-gradient(90deg, #10b981, #059669);
            transition: width 0.3s ease;
            border-radius: 3px;
        }

        /* Date Styling */
        .date-text {
            color: #4b5563;
            font-size: 13px;
            font-weight: 500;
        }

        /* Actions */
        .actions {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 8px;
        }

        .actions a {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            width: 32px;
            height: 32px;
            border-radius: 8px;
            transition: all 0.2s ease;
            text-decoration: none;
        }

        .actions a:hover {
            transform: scale(1.1);
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
        }

        .actions i {
            font-size: 16px;
        }

        .icon-eye {
            color: #3b82f6;
            background: rgba(59, 130, 246, 0.1);
        }

        .icon-eye:hover {
            background: rgba(59, 130, 246, 0.2);
        }

        .icon-edit-3 {
            color: #10b981;
            background: rgba(16, 185, 129, 0.1);
        }

        .icon-edit-3:hover {
            background: rgba(16, 185, 129, 0.2);
        }

        .icon-trash-2 {
            color: #ef4444;
            background: rgba(239, 68, 68, 0.1);
        }

        .icon-trash-2:hover {
            background: rgba(239, 68, 68, 0.2);
        }

        /* Responsive Design */
        @media (max-width: 1200px) {
            .table-header,
            .table-row {
                grid-template-columns: 100px 60px 150px 80px 100px 100px 80px 80px 100px 90px 90px 90px 100px;
                font-size: 12px;
            }
        }

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

            .table-header,
            .table-row {
                grid-template-columns: 120px 80px 180px 100px 120px 120px 100px 100px 120px 100px 100px 100px 120px;
            }

@media (max-width: 768px) {
    /* Hide some columns on mobile */
    .table-header .col:nth-child(10),
    .table-header .col:nth-child(11),
    .table-header .col:nth-child(12),
    .table-row .col:nth-child(10),
    .table-row .col:nth-child(11),
    .table-row .col:nth-child(12) {
        display: none;
    }
}

        /* Body title styling */
        .body-title-2 {
            color: #374151;
            font-weight: 500;
            transition: color 0.2s ease;
        }

        .body-title-2:hover {
            color: #1f2937;
        }

        /* Max discount styling */
        .max-discount {
            color: #7c3aed;
            font-weight: 600;
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
                <jsp:include page="../common/coupon/sidebar.jsp"></jsp:include>
                <!-- /section-menu-left -->
                <!-- section-content-right -->
                <div class="section-content-right">
                    <!-- header-dashboard -->
                <jsp:include page="../common/coupon/header.jsp"></jsp:include>    
                    <!-- /header-dashboard -->
                    <!-- main-content -->
                    <div class="main-content">
                        <!-- main-content-wrap -->
                        <div class="main-content-inner">
                            <!-- main-content-wrap -->
                            <div class="main-content-wrap">
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                    <h3>Coupon Management</h3>
                                </div>
                                <!-- coupon-list -->
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
                                                <div class="search-container">
                                                    <input type="text" name="search" placeholder="Search coupons...">
                                                    <button type="submit">
                                                        <i class="icon-search"></i>
                                                    </button>
                                                </div>
                                            </form>
                                        </div>  
                                        <a class="tf-button style-1 w208" href="${pageContext.request.contextPath}/manage-blog?action=add&id=${blog.id}"><i class="icon-plus"></i>Add new</a>
                                    </div>
                                </div>

                                <div class="wg-table table-coupon-list">
                                    <!-- Header -->
                                    <div class="table-header">
                                        <div class="col">Code</div>
                                        <div class="col">ID</div>
                                        <div class="col">Description</div>
                                        <div class="col">Discount</div> 
                                        <div class="col">Min Purchase</div>
                                        <div class="col">Max Discount</div>
                                        <div class="col">Start Date</div>
                                        <div class="col">End Date</div>
                                        <div class="col">Usage</div>
                                        <div class="col">Status</div>
                                        <div class="col">Created</div>
                                        <div class="col">Updated</div>
                                        <div class="col">Actions</div>
                                    </div>

                                    <!-- Data Rows -->
                                    <c:forEach items="${blogs}" var="blog">
                                        <div class="table-row">
                                            <div class="col">
                                                <span class="coupon-code">${blog.code}</span>
                                            </div>
                                            <div class="col body-title-2">${blog.couponID}</div>
                                            <div class="col body-title-2" title="${blog.description}">
                                                ${blog.description.length() > 30 ? blog.description.substring(0, 30).concat('...') : blog.description}
                                            </div>
                                            <div class="col">
                                                <span class="discount-value">${blog.discountValue}%</span>
                                            </div>
                                            <div class="col">
                                                <span class="currency">$${blog.minPurchase}</span>
                                            </div>
                                            <div class="col">
                                                <span class="max-discount">$${blog.maxDiscount}</span>
                                            </div>
                                            <div class="col">
                                                <span class="date-text">${blog.startDate}</span>
                                            </div>
                                            <div class="col">
                                                <span class="date-text">${blog.endDate}</span>
                                            </div>
                                            <div class="col">
                                                <div class="usage-info">${blog.usageCount}/${blog.usageLimit}</div>
                                                <div class="usage-bar">
                                                    <div class="usage-fill" style="width: ${blog.usageLimit > 0 ? (blog.usageCount * 100 / blog.usageLimit) : 0}%"></div>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <span class="status ${blog.isActive ? 'active' : 'inactive'}">
                                                    ${blog.isActive ? 'Active' : 'Inactive'}
                                                </span>
                                            </div>
                                            <div class="col">
                                                <span class="date-text">${blog.createdAt}</span>
                                            </div>
                                            <div class="col">
                                                <span class="date-text">${blog.updatedAt}</span>
                                            </div>
                                            <div class="col actions">
                                                <a href="${pageContext.request.contextPath}/manage-blog?action=view&id=${blog.id}" class="icon-eye"><i class="icon-eye"></i></a>
                                                <a href="${pageContext.request.contextPath}/manage-blog?action=edit&id=${blog.id}" class="icon-edit-3"> <i class="icon-edit-3"></i></a>
                                                <a href="${pageContext.request.contextPath}/manage-blog?action=delete&id=${blog.id}" class="icon-trash-2"><i class="icon-trash-2"></i></a>
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