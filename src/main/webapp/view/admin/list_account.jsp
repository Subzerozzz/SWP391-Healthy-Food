<%-- 
    Document   : all-acc.jsp
    Created on : May 27, 2025, 10:00:36 AM
    Author     : Hang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" />
        <!--link izitoatMess-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
            <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>


            <style>
                /* Gợi ý: Bắt đầu CSS cải tiến */

                .table-all-user ul.table-title li,
                .table-all-user .user-item>.flex>div {
                    flex: 1;
                    min-width: 120px;
                    word-break: break-word;
                }

                .table-all-user .user-item {
                    border-bottom: 1px solid #e0e0e0;
                    padding: 10px 0;
                }

                .user-item .name a {
                    font-weight: 700;
                    font-size: 12px;
                    color: #333;
                }

                .user-item .name .text-tiny {
                    font-size: 13px;
                    color: #888;
                }

                .tf-button {
                    padding: 6px 20px;
                    background-color: #007bff;
                    color: white;
                    border-radius: 6px;
                    border: none;
                }

                .wg-pagination li a {
                    padding: 6px 10px;
                    border: 1px solid #ccc;
                    border-radius: 4px;
                }

                .wg-pagination li.active a {
                    background-color: #007bff;
                    color: white;
                    border-color: #007bff;
                }

                .status-badge.active {
                    background-color: #28a745;
                    color: white;
                    padding: 4px 8px;
                    border-radius: 4px;
                    font-size: 12px;
                }

                .status-badge.deactive {
                    background-color: #dc3545;
                    color: white;
                    padding: 4px 8px;
                    border-radius: 4px;
                    font-size: 12px;
                }

                .wg-filter {
                    display: flex;
                    align-items: center;
                    justify-content: space-between;
                    flex-wrap: wrap;
                    gap: 12px;
                }

                .form-search {
                    display: flex;
                    align-items: center;
                    gap: 12px;
                    flex-wrap: wrap;
                }

                /* Select box style */
                .form-search select.form-control {
                    padding: 8px 12px;
                    border: 1px solid #ccc;
                    border-radius: 6px;
                    min-width: 140px;
                    font-size: 14px;
                    outline: none;
                    transition: border-color 0.3s ease;
                }

                .form-search select.form-control:focus {
                    border-color: #007bff;
                }

                /* Button style */
                .form-search .tf-button {
                    padding: 10px 20px;
                    background-color: #1e80ff;
                    color: #fff;
                    font-weight: bold;
                    border: none;
                    border-radius: 10px;
                    cursor: pointer;
                    transition: background-color 0.2s ease;
                }

                .form-search .tf-button:hover {
                    background-color: #1066d3;
                }

                .pagination li a {
                    color: #333;
                    padding: 8px 12px;
                    text-decoration: none;
                    display: inline-block;
                    border-radius: 4px;
                    transition: background-color 0.3s;
                }

                /* Hover các trang không active */
                .pagination li:not(.active) a:hover {
                    background-color: #ddd;
                }

                /* Trang active giữ màu xanh cố định */
                .pagination li.active a {
                    background-color: #007bff;
                    color: white;
                    font-weight: bold;
                }

                /* Khi hover vào trang active, vẫn giữ màu xanh */
                .pagination li.active a:hover {
                    background-color: #007bff;
                    color: white;
                    font-weight: bold;
                }

                .fa-circle-check {
                    color: #28a745;
                    /* xanh lá đẹp, như trong Bootstrap */
                }

                .fa-circle-check:hover {
                    color: #007BFF;
                    /* Màu xanh biển khi hover */
                }

                .list-icon-function .item.eye i {
                    color: #FFC107;
                    margin-right: 10px;
                    /* màu vàng */
                }

                .list-icon-function .item.edit i {
                    color: #FF3B3B;
                    margin-right: 10px
                    /* đỏ tươi */
                }

                .wg-pagination {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    list-style: none;
                    padding: 0;
                    margin: 20px 0;
                    gap: 10px;
                }

                .wg-pagination li a {
                    display: inline-block;
                    width: 36px;
                    height: 36px;
                    line-height: 36px;
                    text-align: center;
                    border-radius: 50%;
                    border: 1px solid #ccc;
                    color: #000;
                    text-decoration: none;
                    font-weight: bold;
                    transition: all 0.3s ease;
                }

                /* Trang đang chọn */
                .wg-pagination li.active a {
                    background-color: #1e74fd;
                    color: white;
                    border-color: #1e74fd;
                }

                /* Hover các trang khác */
                .wg-pagination li a:hover {
                    background-color: #eee;
                    border-color: #aaa;
                }

                .switch {
                    position: relative;
                    display: inline-block;
                    width: 50px;
                    height: 26px;
                }

                .switch input {
                    opacity: 0;
                    width: 0;
                    height: 0;
                }

                .slider {
                    position: absolute;
                    cursor: pointer;
                    top: 0;
                    left: 0;
                    right: 0;
                    bottom: 0;
                    background-color: #ccc;
                    transition: 0.4s;
                    border-radius: 26px;
                    display: flex;
                    align-items: center;
                    justify-content: flex-start;
                    padding: 0 5px;
                    font-size: 12px;
                    font-weight: bold;
                    color: white;
                }

                /* Nút tròn trắng */
                .slider:before {
                    content: "";
                    position: absolute;
                    height: 20px;
                    width: 20px;
                    background-color: white;
                    border-radius: 50%;
                    transition: 0.4s;
                    left: 3px;
                    top: 3px;
                }

                /* Khi là ON */
                input:checked+.slider {
                    background-color: #4CAF50;
                    justify-content: flex-end;
                }

                /* Nút tròn chạy qua phải */
                input:checked+.slider:before {
                    transform: translateX(24px);
                }

                /* Chữ OFF/ON */
                .slider::after {

                    z-index: 1;
                }

                .search-box {
                    display: flex;
                    align-items: center;
                    width: 100%;
                    max-width: 400px;
                    background-color: #fff;
                    border: 1px solid #e0e0e0;
                    border-radius: 10px;
                    padding: 3px 12px;
                    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
                }

                .search-box input[type="text"] {
                    flex: 1;
                    border: none;
                    outline: none;
                    font-size: 14px;
                    padding: 8px;
                    color: #333;
                    background-color: transparent;
                }

                .search-box input[type="text"]::placeholder {
                    color: #aaa;
                }

                .search-box button {
                    border: none;
                    background: none;
                    cursor: pointer;
                    padding: 4px;
                    color: #333;
                    font-size: 16px;
                    transition: color 0.2s ease;
                }

                .search-box button:hover {
                    color: #007bff;
                }

                .filter-button {
                    background-color: #2275fc;
                    border: 1px solid #ccc;
                    padding: 8px 12px;
                    border-radius: 10px;
                    cursor: pointer;
                    color: #333;
                    transition: all 0.3s ease;
                }

                .filter-button i {
                    font-size: 16px;
                }

                .filter-button:hover {
                    background-color: #f0f0f0;
                    border-color: #999;
                }

                .filter-container {
                    display: flex;
                    align-items: center;
                    justify-content: space-between;
                    gap: 20px;
                    padding: 20px;
                    flex-wrap: wrap;
                    /* Để responsive khi thu nhỏ màn hình */
                }

                .wrapper-flex {
                    display: flex;
                    align-items: center;
                    gap: 20px;
                    flex-wrap: wrap;
                }

                .filter-group {
                    display: flex;
                    align-items: center;
                    gap: 10px;
                }

                .form-search select,
                .filter-button {
                    height: 36px;
                }

                .search-box {
                    display: flex;
                    align-items: center;
                    gap: 5px;
                    width: 250px;
                }

                .search-box input[type="text"] {
                    flex: 1;
                    height: 36px;
                    padding: 0 10px;
                }

                .search-box button {
                    height: 36px;
                }

                /* Tăng chiều rộng ô tìm kiếm */
                .search-box {
                    display: flex;
                    align-items: center;
                    gap: 5px;
                    width: 300px;
                    /* ← tăng tùy ý: 300px, 350px,... */
                }

                /* Input trong ô tìm kiếm chiếm đủ không gian */
                .search-box input[type="text"] {
                    flex: 1;
                    height: 36px;
                    padding: 0 10px;
                }

                /* Nút tìm kiếm giữ kích cỡ đều */
                .search-box button {
                    height: 36px;
                }

                /* Container ngoài căn trái - phải */
                .wg-box>.flex {
                    justify-content: space-between;
                    /* đẩy Add new sang phải */
                    align-items: center;
                    flex-wrap: wrap;
                    gap: 15px;
                }

                /* Nhóm lọc + tìm kiếm canh trái */
                .wrapper-flex {
                    display: flex;
                    align-items: center;
                    flex-wrap: wrap;
                    gap: 15px;
                }

                /* Nhóm bộ lọc ngang hàng */
                .form-search {
                    display: flex;
                    align-items: center;
                    gap: 10px;
                }

                /* Container chính chứa toàn bộ filter/search/add */
                .wg-box>.flex {
                    display: flex;
                    align-items: center;
                    justify-content: space-between;
                    flex-wrap: wrap;
                }

                /* Nhóm filter và search nằm bên trái */
                .wrapper-flex {
                    display: flex;
                    align-items: center;
                    gap: 12px;
                    flex-wrap: wrap;
                }

                /* Nhóm filter */
                .filter-group {
                    display: flex;
                    align-items: center;
                    gap: 8px;
                }

                /* Dropdown nhỏ lại */
                .filter-group select.form-control {
                    width: 120px;
                    height: 36px;
                    font-size: 14px;
                    padding: 4px 8px;
                }

                /* Nút lọc giữ gọn gàng */
                .filter-button {
                    height: 36px;
                    padding: 0 12px;
                    background-color: #2563eb;
                    color: #fff;
                    border: none;
                    border-radius: 6px;
                    cursor: pointer;
                    font-size: 14px;
                }

                .search-box {
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    margin: 0 auto;
                    /* căn giữa trong khối cha */
                }

                /* Nút "Add new" đẩy sát phải */
                .tf-button.style-1.w208 {
                    margin-left: auto;
                    height: 36px;
                    padding: 0 16px;
                    border: 1px solid #2563eb;
                    color: #2563eb;
                    border-radius: 8px;
                    font-weight: 600;
                    display: flex;
                    align-items: center;
                    gap: 6px;
                    text-decoration: none;
                }

                .add-btn {
                    margin-left: auto;
                    display: block;
                }
            </style>



            <!-- Mirrored from themesflat.co/html/remos/all-user.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->

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
                                                                                <!-- section-menu-left -->
                                                                                <jsp:include page="../common/sidebar.jsp"></jsp:include>
                                                                                    <!-- /section-menu-left -->
                                                                                    <!-- section-content-right -->
                                                                                    <div class="section-content-right">
                                                                                        <!-- header-dashboard -->
                                                                                    <jsp:include page="../common/headerDashboard.jsp"></jsp:include>
                                                                                        <!-- /header-dashboard -->
                                                                                        <!-- main-content -->
                                                                                        <div class="main-content">
                                                                                            <!-- main-content-wrap -->
                                                                                            <div class="main-content-inner">
                                                                                                <!-- main-content-wrap -->
                                                                                                <div class="main-content-wrap">
                                                                                                    <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                                                                                        <h3>All User</h3>
                                                                                                    </div>
                                                                                                    <!-- all-user -->
                                                                                                    <div class="wg-box">
                                                                                                        <div class="flex items-center justify-between gap10 flex-wrap">

                                                                                                            <div class="wrapper-flex">
                                                                                                                <!-- Form lọc (nằm ngang) -->
                                                                                                                <form class="form-search" action="${pageContext.request.contextPath}/manage-account" method="GET">
                                                                                                                <input type="hidden" name="action" value="filter" />

                                                                                                                <div class="filter-group">
                                                                                                                    <select name="role" class="form-control">
                                                                                                                        <option value="">All Roles</option>
                                                                                                                        <option value="manager" ${param.role=='manager' ? 'selected' : '' }>Manager</option>
                                                                                                                        <option value="customer" ${param.role=='customer' ? 'selected' : '' }>Customer</option>
                                                                                                                        <option value="shipper" ${param.role=='shipper' ? 'selected' : '' }>Shipper</option>
                                                                                                                        <option value="nutri" ${param.role=='nutri' ? 'selected' : '' }>Nutritionist</option>
                                                                                                                        <option value="saler" ${param.role=='saler' ? 'selected' : '' }>Saler</option>
                                                                                                                        
                                                                                                                    </select>

                                                                                                                    <select name="status" class="form-control">
                                                                                                                        <option value="">All Status</option>
                                                                                                                        <option value="active" ${param.status == 'active' ? 'selected' : ''}>Active</option>
                                                                                                                        <option value="banned" ${param.status == 'banned' ? 'selected' : ''}>Banned</option>
                                                                                                                    </select>

                                                                                                                    <button type="submit" class="filter-button">Filter</button>

                                                                                                            </form>

                                                                                                            <!-- Tìm kiếm -->
                                                                                                            <form class="search-box" method="get" action="${pageContext.request.contextPath}/manage-account">
                                                                                                                <input type="text" name="search" placeholder="Search here..." value="${param.search}">
                                                                                                                    <input type="hidden" name="action" value="search">
                                                                                                                        <button type="submit"><i class="fa fa-search"></i></button>
                                                                                                                        </form>
                                                                                                                        </div>

                                                                                                                        <!-- Add new -->

                                                                                                                        <a style="margin-left: 250px" class="tf-button style-1 w208 add-btn"
                                                                                                                           href="${pageContext.request.contextPath}/manage-account?action=add">
                                                                                                                            <i class="icon-plus"></i>Add new
                                                                                                                        </a>
                                                                                                                        </div>
                                                                                                                        </div>
                                                                                                                        <div class="wg-table table-all-user">
                                                                                                                            <ul class="table-title flex gap20 mb-14">
                                                                                                                                <li>
                                                                                                                                    <div class="body-title">User</div>
                                                                                                                                </li>

                                                                                                                                <li>
                                                                                                                                    <div class="body-title">Address</div>
                                                                                                                                </li>
                                                                                                                                <li>
                                                                                                                                    <div class="body-title">Email</div>
                                                                                                                                </li>

                                                                                                                                <li>
                                                                                                                                    <div class="body-title">Mobile</div>

                                                                                                                                    <li>
                                                                                                                                        <div class="body-title">Gender</div>
                                                                                                                                    </li>
                                                                                                                                    <li>
                                                                                                                                        <div class="body-title">Role</div>
                                                                                                                                    </li>

                                                                                                                                    <li>
                                                                                                                                        <div class="body-title">Status</div>
                                                                                                                                    </li>
                                                                                                                                    <li>
                                                                                                                                        <div class="body-title">Action</div>
                                                                                                                                    </li>
                                                                                                                            </ul>
                                                                                                                            <ul class="flex flex-column">
                                                                                                                                <c:forEach items="${listAccount}" var="account">
                                                                                                                                    <c:if test="${account.getRole() != 'admin'}">
                                                                                                                                        <li class="user-item gap14">

                                                                                                                                        <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                                                            <div class="name">
                                                                                                                                                <a href="#" class="body-title-2">${account.user_name == null ? 'null' : account.user_name}</a>
                                                                                                                                                <div class="text-tiny mt-3">${account.full_name == null ? 'null' : account.full_name}</div>
                                                                                                                                            </div>
                                                                                                                                            <div class="body-text">${account.address == null ? 'null' : account.address}</div>
                                                                                                                                            <div class="body-text">${account.email == null ? 'null' : account.email}</div>
                                                                                                                                            <div class="body-text">${account.mobile == null ? 'null' : account.mobile}</div>
                                                                                                                                            <div class="body-text">${account.gender == null ? 'null' : account.gender}</div>
                                                                                                                                            <div class="body-text">${account.role == null ? 'null' : account.role}</div>
                                                                                                                                            <div class="body-text">
                                                                                                                                                <c:choose>
                                                                                                                                                    <c:when test="${account.status == 'active'}">
                                                                                                                                                        <span class="status-badge active">Active</span>
                                                                                                                                                    </c:when>
                                                                                                                                                    <c:when test="${account.status == 'banned'}">
                                                                                                                                                        <span class="status-badge deactive">Banned</span>
                                                                                                                                                    </c:when>
                                                                                                                                                </c:choose>

                                                                                                                                            </div>
                                                                                                                                            <div class="list-icon-function">
                                                                                                                                                <div class="item eye">
                                                                                                                                                    <a
                                                                                                                                                        href="${pageContext.request.contextPath}/manage-account?action=viewDetail&id=${account.id}">
                                                                                                                                                        <i class="icon-eye"></i>
                                                                                                                                                </div>

                                                                                                                                                <div class="item edit" style="margin-right: 10px !important">

                                                                                                                                                    <a href="${pageContext.request.contextPath}/manage-account?action=edit&id=${account.id}"><i
                                                                                                                                                            class="icon-edit-3"></i></a>
                                                                                                                                                </div>
                                                                                                                                                <label class="switch"
                                                                                                                                                       title="${account.status eq 'active' ? 'Deactivate' : 'Activate'}">
                                                                                                                                                    <input type="checkbox"
                                                                                                                                                           onchange="location.href = '${pageContext.request.contextPath}/manage-account?action=${account.status eq 'active' ? 'deactive' : 'activate'}&id=${account.id}'"
                                                                                                                                                           <c:if test='${account.status eq "active"}'>checked</c:if>>
                                                                                                                                                               <span class="slider"></span>
                                                                                                                                                    </label>

                                                                                                                                                </div>
                                                                                                                                            </div>
                                                                                                                                        </li>
                                                                                                                                    </c:if>
                                                                                                                                    
                                                                                                                                </c:forEach>
                                                                                                                            </ul>
                                                                                                                        </div>
                                                                                                                        <div class="divider"></div>
                                                                                                                        <div class="flex items-center justify-between flex-wrap gap10">
                                                                                                                            <div class="text-tiny">
                                                                                                                                Showing ${startRecord} to ${endRecord} of ${totalAccounts} entries
                                                                                                                            </div>
                                                                                                                            <!--phân trang--> 
                                                                                                                            <c:if test="${totalPages > 1}">
                                                                                                                                <ul class="wg-pagination">
                                                                                                                                    <!-- Nút Previous -->
                                                                                                                                    <li>
                                                                                                                                        <a href="?action=${param.action}&role=${param.role}&status=${param.status}&page=1&pageSize=${pageSize}">
                                                                                                                                            <i class="icon-chevron-left"></i>
                                                                                                                                        </a>
                                                                                                                                    </li>

                                                                                                                                    <c:choose>
                                                                                                                                        <c:when test="${currentPage <= totalPages - 2}">
                                                                                                                                            <c:if test="${currentPage > 1}">
                                                                                                                                                <li>
                                                                                                                                                    <a href="?action=${param.action}&role=${param.role}&status=${param.status}&page=${currentPage - 1}&pageSize=${pageSize}">
                                                                                                                                                        ${currentPage - 1}
                                                                                                                                                    </a>
                                                                                                                                                </li>
                                                                                                                                            </c:if>

                                                                                                                                            <li class="active">
                                                                                                                                                <a href="?action=${param.action}&role=${param.role}&status=${param.status}&page=${currentPage}&pageSize=${pageSize}">
                                                                                                                                                    ${currentPage}
                                                                                                                                                </a>
                                                                                                                                            </li>

                                                                                                                                            <li>
                                                                                                                                                <a href="?action=${param.action}&role=${param.role}&status=${param.status}&page=${currentPage + 1}&pageSize=${pageSize}">
                                                                                                                                                    ${currentPage + 1}
                                                                                                                                                </a>
                                                                                                                                            </li>

                                                                                                                                            <c:if test="${currentPage < totalPages - 2}">
                                                                                                                                                <li><span>...</span></li>
                                                                                                                                                </c:if>

                                                                                                                                            <li>
                                                                                                                                                <a href="?action=${param.action}&role=${param.role}&status=${param.status}&page=${totalPages}&pageSize=${pageSize}">
                                                                                                                                                    ${totalPages}
                                                                                                                                                </a>
                                                                                                                                            </li>
                                                                                                                                        </c:when>

                                                                                                                                        <c:otherwise>
                                                                                                                                            <c:forEach begin="${totalPages - 2 <= 0 ? 1 : totalPages - 2}" end="${totalPages}" var="i">
                                                                                                                                                <li class="${currentPage == i ? 'active' : ''}">
                                                                                                                                                    <a href="?action=${param.action}&role=${param.role}&status=${param.status}&page=${i}&pageSize=${pageSize}">
                                                                                                                                                        ${i}
                                                                                                                                                    </a>
                                                                                                                                                </li>
                                                                                                                                            </c:forEach>
                                                                                                                                        </c:otherwise>
                                                                                                                                    </c:choose>

                                                                                                                                    <!-- Nút Next -->
                                                                                                                                    <li>
                                                                                                                                        <a href="?action=${param.action}&role=${param.role}&status=${param.status}&page=${totalPages}&pageSize=${pageSize}">
                                                                                                                                            <i class="icon-chevron-right"></i>
                                                                                                                                        </a>
                                                                                                                                    </li>
                                                                                                                                </ul>
                                                                                                                            </c:if>

                                                                                                                        </div>

                                                                                                                        </div>
                                                                                                                        <!-- /all-user -->
                                                                                                                        </div>
                                                                                                                        <!-- /main-content-wrap -->
                                                                                                                        </div>
                                                                                                                        <!-- /main-content-wrap -->
                                                                                                                        
                                                                                                                        
                                                                                                                        <!-- bottom-page -->
                                                                                                                            <jsp:include page="../common/footer.jsp"></jsp:include>
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
                                                                                                                        <%-- Toast khi cập nhật thành công --%>
                                                                                                                        <c:if test="${sessionScope.isUpdate == true}">
                                                                                                                            <script>
                                                                                                                                document.addEventListener("DOMContentLoaded", function () {
                                                                                                                                iziToast.success({
                                                                                                                                title: "Thông báo",
                                                                                                                                        message: "Cập nhật tài khoản thành công!",
                                                                                                                                        position: 'topRight',
                                                                                                                                        timeout: 5000,
                                                                                                                                        backgroundColor: "#d4edda"
                                                                                                                                });
                                                                                                                                });
                                                                                                                            </script>
                                                                                                                            <% session.removeAttribute("isUpdate"); %>
                                                                                                                        </c:if>
                                                                                                                        <!--hiển thị ban hay active-->
                                                                                                                        <c:if test="${not empty sessionScope.toastMessage}">
                                                                                                                            <script>
                                                                                                                                iziToast.${sessionScope.toastType}({
                                                                                                                                title: '${sessionScope.toastType == "success" ? "Thành công" : "Thành Công"}',
                                                                                                                                        message: '${sessionScope.toastMessage}',
                                                                                                                                        position: 'topRight',
                                                                                                                                        timeout: 3000,
                                                                                                                                        color: '${sessionScope.toastType == "success" ? "green" : "red"}'
                                                                                                                                });
                                                                                                                            </script>
                                                                                                                            <c:remove var="toastMessage" scope="session"/>
                                                                                                                            <c:remove var="toastType" scope="session"/>
                                                                                                                        </c:if>
                                                                                                                        <!--hien thị add tahnfh công-->
                                                                                                                        <c:if test="${not empty sessionScope.toastMessage}">
                                                                                                                            <script>
                                                                                                                                document.addEventListener("DOMContentLoaded", function () {
                                                                                                                                iziToast.${sessionScope.toastType}({
                                                                                                                                title: "Thông báo",
                                                                                                                                        message: "${sessionScope.toastMessage}",
                                                                                                                                        position: 'topRight',
                                                                                                                                        timeout: 5000
                                                                                                                                });
                                                                                                                                });
                                                                                                                            </script>
                                                                                                                            <c:remove var="toastMessage" scope="session"/>
                                                                                                                            <c:remove var="toastType" scope="session"/>
                                                                                                                        </c:if>

                                                                                                                        <c:if test="${not empty sessionScope.toastMessage}">
                                                                                                                            <script>
                                                                                                                                document.addEventListener("DOMContentLoaded", function () {
                                                                                                                                iziToast.$ {
                                                                                                                                sessionScope.toastType
                                                                                                                                }({
                                                                                                                                title: "Thông báo",
                                                                                                                                        message: "${sessionScope.toastMessage}",
                                                                                                                                        position: 'topRight',
                                                                                                                                        timeout: 5000
                                                                                                                                });
                                                                                                                                });
                                                                                                                            </script>
                                                                                                                            <% session.removeAttribute("toastMessage");
                                                                                                                                session.removeAttribute("toastType"); %>
                                                                                                                        </c:if>
                                                                                                                        <c:if test="${not empty sessionScope.toastMessage}">
                                                                                                                            <script>
                                                                                                                                document.addEventListener("DOMContentLoaded", function () {
                                                                                                                                iziToast.$ {
                                                                                                                                sessionScope.toastType
                                                                                                                                }({
                                                                                                                                title: "Thông báo",
                                                                                                                                        message: "${sessionScope.toastMessage}",
                                                                                                                                        position: 'topRight',
                                                                                                                                        timeout: 5000
                                                                                                                                });
                                                                                                                                });
                                                                                                                            </script>
                                                                                                                            <% session.removeAttribute("toastMessage");
                                                                                                                                session.removeAttribute("toastType");%>
                                                                                                                        </c:if>

                                                                                                                        <!-- Mirrored from themesflat.co/html/remos/all-user.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:55 GMT -->

                                                                                                                        </html>