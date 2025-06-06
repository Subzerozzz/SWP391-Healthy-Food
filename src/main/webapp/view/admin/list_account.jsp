
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
<style>
/* Gợi ý: Bắt đầu CSS cải tiến */

.table-all-user ul.table-title li,
.table-all-user .user-item > .flex > div {
    flex: 1;
    min-width: 120px;
    word-break: break-word;
}

.table-all-user .user-item {
    border-bottom: 1px solid #e0e0e0;
    padding: 10px 0;
}

.user-item .name a {
    font-weight: 600;
    font-size: 16px;
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
    color: #28a745; /* xanh lá đẹp, như trong Bootstrap */
}
.fa-circle-check:hover {
    color: #007BFF; /* Màu xanh biển khi hover */
}
.list-icon-function .item.eye i {
    color: #FFC107; /* màu vàng */
}

.list-icon-function .item.edit i {
    color: #FF3B3B; /* đỏ tươi */
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
                                                                        <jsp:include page = "../common/admin/section-menu-left.jsp"></jsp:include>
                                                                            <!-- /section-menu-left -->
                                                                            <!-- section-content-right -->
                                                                            <div class="section-content-right">
                                                                                <!-- header-dashboard -->
                                                                            <jsp:include page = "../common/admin/header-dashboard.jsp"></jsp:include>
                                                                                <!-- /header-dashboard -->
                                                                                <!-- main-content -->
                                                                                <div class="main-content">
                                                                                    <!-- main-content-wrap -->
                                                                                    <div class="main-content-inner">
                                                                                        <!-- main-content-wrap -->
                                                                                        <div class="main-content-wrap">
                                                                                            <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                                                                                <h3>All User</h3>
                                                                                                <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                                                                                    <li>
                                                                                                        <a href="index.html"><div class="text-tiny">Dashboard</div></a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <i class="icon-chevron-right"></i>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <a href="#"><div class="text-tiny">User</div></a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <i class="icon-chevron-right"></i>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="text-tiny">All User</div>
                                                                                                    </li>
                                                                                                </ul>
                                                                                            </div>
                                                                                            <!-- all-user -->
                                                                                            <div class="wg-box">
                                                                                                <div class="flex items-center justify-between gap10 flex-wrap">
                                                                                                    <div class="wg-filter flex-grow">
                                                                                                        <form action="${pageContext.request.contextPath}/manage-account" method="GET" class="form-search">
                                                                                                        <input type="hidden" name="action" value="filter" />

                                                                                                        <select name="role" class="form-control">
                                                                                                            <option value="">All Roles</option>
                                                                                                            <option value="admin" ${param.role == 'admin' ? 'selected' : ''}>Admin</option>
                                                                                                            <option value="user" ${param.role == 'user' ? 'selected' : ''}>User</option>
                                                                                                        </select>

                                                                                                        <select name="status" class="form-control">
                                                                                                            <option value="">All Status</option>
                                                                                                            <option value="true" ${param.status == 'true' ? 'selected' : ''}>Active</option>
                                                                                                            <option value="false" ${param.status == 'false' ? 'selected' : ''}>Deactive</option>
                                                                                                        </select>

                                                                                                        <button type="submit" class="tf-button">Lọc</button>
                                                                                                    </form>
                                                                                                </div>
                                                                                                <a class="tf-button style-1 w208" href="${pageContext.request.contextPath}/manage-account?action=add"><i class="icon-plus"></i>Add new</a>
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
                                                                                                    </li>

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
                                                                                                        <li class="user-item gap14">
<!--                                                                                                            <div class="image">
                                                                                                                <img src="${pageContext.request.contextPath}/images/avatar/user-6.png" alt="">
                                                                                                            </div>-->
                                                                                                            <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                                <div class="name">
                                                                                                                    <a href="#" class="body-title-2">${account.user_name}</a>
                                                                                                                    <div class="text-tiny mt-3">${account.full_name}</div>
                                                                                                                </div>
                                                                                                                <div class="body-text">${account.address}</div>
                                                                                                                <div class="body-text">${account.email}</div>
                                                                                                                <div class="body-text">${account.mobile}</div>
                                                                                                                <div class="body-text">${account.gender}</div>
                                                                                                                <div class="body-text">${account.role}</div>
                                                                                                                <div class="body-text">
                                                                                                                    <c:choose>
                                                                                                                        <c:when test="${account.status == false}">
                                                                                                                            <span class="status-badge deactive">Deactive</span>
                                                                                                                        </c:when>
                                                                                                                        <c:when test="${account.status == true}">
                                                                                                                            <span class="status-badge active">Active</span>
                                                                                                                        </c:when>
                                                                                                                    </c:choose>

                                                                                                                </div>
                                                                                                                <div class="list-icon-function">
                                                                                                                    <div class="item eye">
                                                                                                                      <a href="${pageContext.request.contextPath}/manage-account?action=viewDetail&id=${account.id}">  <i class="icon-eye"></i>
                                                                                                                    </div>

                                                                                                                    <div class="item edit">

                                                                                                                        <a href="${pageContext.request.contextPath}/manage-account?action=edit&id=${account.id}"><i class="icon-edit-3"></i></a>
                                                                                                                    </div>
                                                                                                                    <div class="item trash">
                                                                                                                        <a href="${pageContext.request.contextPath}/manage-account?action=${account.status ?'deactive' :'activate'}&id=${account.id}"><i class="fa-solid fa-circle-check"></i></a>
                                                                                                                    </div>
                                                                                                                    <div class="item trash">
                                                                                                                        <a href="${pageContext.request.contextPath}/manage-account?action=delete&id=${account.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa không?');" >
                                                                                                                            <i class="icon-trash-2"></i>
                                                                                                                        </a>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </li>
                                                                                                    </c:forEach>
                                                                                                </ul>
                                                                                            </div>
                                                                                            <div class="divider"></div>
                                                                                            <div class="flex items-center justify-between flex-wrap gap10">
                                                                                                <div class="text-tiny">Showing 10 entries</div>
                                                                                                <ul class="wg-pagination">
                                                                                                    <li>
                                                                                                        <a href="#"><i class="icon-chevron-left"></i></a>
                                                                                                    </li>
                                                                                                    <c:forEach begin="1" end="${endP}" var="i">
                                                                                                        <li class = "${i == index ? 'active' : ''}">
                                                                                                            <a href="${pageContext.request.contextPath}/manage-account?index=${i}">${i}</a>
                                                                                                        </li>
                                                                                                    </c:forEach>
<!--                                                                                                    <li>
                                                                                                        <a href="#">1</a>
                                                                                                    </li>
                                                                                                    <li class="active">
                                                                                                        <a href="#">2</a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <a href="#">3</a>
                                                                                                    </li>-->
                                                                                                    <li>
                                                                                                        <a href="#"><i class="icon-chevron-right"></i></a>
                                                                                                    </li>
                                                                                                </ul>
                                                                                            </div>
                                                                                        </div>
                                                                                        <!-- /all-user -->
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

                                                        </body>


                                                        <!-- Mirrored from themesflat.co/html/remos/all-user.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:55 GMT -->
                                                        </html>
