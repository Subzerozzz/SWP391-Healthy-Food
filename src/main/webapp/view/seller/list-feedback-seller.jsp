<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font/fonts.css">
    <!-- Icon -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/icon/style.css">

    <!-- Favicon and Touch Icons  -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/images/favicon.png">
    <style>
        /* General Body and Filter Styling */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8fafc;
        }

        .filter-search-wrapper {
            margin: 20px auto;
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
        }

        .form-search label {
            font-weight: 600;
            color: #374151;
            font-size: 14px;
        }

        .form-search select {
            padding: 10px 16px;
            border: 1px solid #d1d5db;
            border-radius: 8px;
            background: white;
            font-size: 14px;
            min-width: 140px;
        }

        .filter-button {
            background: linear-gradient(135deg, #6366f1 0%, #8b5cf6 100%);
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 8px;
            font-weight: 600;
            cursor: pointer;
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
            overflow: hidden;
            min-width: 300px;
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
            background: #6366f1;
            color: white;
            padding: 12px 16px;
            cursor: pointer;
        }

        /* Table Styles */
        .table-wrapper {
            overflow-x: auto;
            width: 100%;
        }

        .wg-table.table-product-list {
            width: 100%;
            background: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            border-collapse: collapse;
        }

        .table-header, .table-row {
            display: flex;
            align-items: stretch;
            border-bottom: 1px solid #e9ecef;
            min-width: 1200px;
        }

        .table-header {
            background: #f8f9fa;
            font-weight: 600;
            color: #495057;
        }

        .table-row:hover {
            background-color: #f8f9fa;
        }

        .table-header .col, .table-row .col {
            padding: 16px 12px;
            display: flex;
            align-items: center;
            word-wrap: break-word;
            overflow-wrap: break-word;
        }

        /* Column Widths */
        .col-id { flex: 0 0 80px; max-width: 80px; }
        .col-food { flex: 0 0 200px; max-width: 200px; }
        .col-rating { flex: 0 0 100px; max-width: 100px; justify-content: center; }
        .col-content { flex: 1; min-width: 250px; }
        .col-customer { flex: 0 0 200px; max-width: 200px; }
        .col-status { flex: 0 0 120px; max-width: 120px; justify-content: center; }
        .col-date { flex: 0 0 180px; max-width: 180px; }
        .col-action { flex: 0 0 120px; max-width: 120px; justify-content: center; gap: 15px; }

        /* Action Buttons */
        .actions a, .actions button {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            width: 35px;
            height: 35px;
            border-radius: 50%;
            background-color: #f1f1f1;
            transition: all 0.2s ease;
            text-decoration: none;
            border: none;
            cursor: pointer;
            padding: 0;
        }
        .actions a:hover, .actions button:hover {
            background-color: #e0e0e0;
            transform: scale(1.1);
        }
        .actions i {
            font-size: 18px;
        }
        .icon-eye { color: #007bff; }
        
        .actions form {
            display: inline-flex;
        }

        /* Toggle Switch CSS */
        .switch {
            position: relative;
            display: inline-block;
            width: 50px;
            height: 28px;
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
            transition: .4s;
        }
        .slider:before {
            position: absolute;
            content: "";
            height: 20px;
            width: 20px;
            left: 4px;
            bottom: 4px;
            background-color: white;
            transition: .4s;
        }
        input:checked + .slider {
            background-color: #28a745;
        }
        input:focus + .slider {
            box-shadow: 0 0 1px #28a745;
        }
        input:checked + .slider:before {
            transform: translateX(22px);
        }
        .slider.round {
            border-radius: 34px;
        }
        .slider.round:before {
            border-radius: 50%;
        }
        .status-form {
            margin: 0;
        }
        /* Status */

        .status-label {
            padding: 4px 12px;
            border-radius: 20px;
            display: inline-block;
            font-weight: bold;
            font-size: 12px;
            text-transform: uppercase;
            background-color: white;
            border: 2px solid;
        }
        /* Trạng thái Active - màu xanh */
        .status-active {
            color: #008000;
            border-color: #008000;
        }

        /* Trạng thái Inactive - màu đỏ */
        .status-inactive {
            color: #cc0000;
            border-color: #cc0000;
        }
    </style>
</head>
<body class="body">
    <div id="wrapper">
        <div id="page" class="">
            <div class="layout-wrap">
                <div id="preload" class="preload-container">
                    <div class="preloading"><span></span></div>
                </div>
                <jsp:include page="../common/sidebar.jsp"></jsp:include>
                <div class="section-content-right">
                    <jsp:include page="../common/headerDashboard.jsp"></jsp:include>
                    <div class="main-content">
                        <div class="main-content-inner">
                            <div class="main-content-wrap">
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                    <h3>Feedback List</h3>
                                </div>
                                <div class="filter-search-wrapper">
                                    <div class="filter-search-container">
                                        <div class="left-controls">
                                            <form class="form-search" action="${pageContext.request.contextPath}/seller/feedback" method="GET">
                                                <input type="hidden" name="action" value="filter" />
                                                <input type="hidden" name="search" value="${search}" />
                                                <label for="sort" style="width: 200px">Sort by:</label>
                                                <select name="sort" id="sort" class="form-control">
                                                    <option value="" ${empty sort ? 'selected' : ''}>Default</option>
                                                    <option value="ASC" ${sort == 'ASC' ? 'selected' : ''}>Ascending</option>
                                                    <option value="DESC" ${sort == 'DESC' ? 'selected' : ''}>Descending</option>
                                                </select>
                                                <label for="rating">Rating:</label>
                                                <select name="rating" id="rating" class="form-control">
                                                    <option value="" ${empty rating ? 'selected' : ''}>All Ratings</option>
                                                    <c:forEach var="i" begin="1" end="5">
                                                        <option value="${i}" ${rating == i ? 'selected' : ''}>${i} Star(⭐)</option>
                                                    </c:forEach>
                                                </select>
                                                <button type="submit" class="filter-button" style="width: 200px">Filter</button>
                                            </form>
                                        </div>
                                        <form class="search-box" action="${pageContext.request.contextPath}/seller/feedback" method="get">
                                            <input type="hidden" name="action" value="search">
                                            <input type="hidden" name="sort" value="${sort}" />
                                            <input type="hidden" name="rating" value="${rating}" />
                                            <div class="search-container">
                                                <input type="text" name="search" placeholder="Search by customer name, email..." value="${search}">
                                                <button type="submit"><i class="icon-search"></i></button>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <div class="table-wrapper">
                                    <div class="wg-table table-product-list">
                                        <div class="table-header">
                                            <div class="col col-id">Id</div>
                                            <div class="col col-food">Food</div>
                                            <div class="col col-rating">Rating</div>
                                            <div class="col col-content">Content</div>
                                            <div class="col col-customer">Customer</div>
                                            <div class="col col-status">Status</div>
                                            <div class="col col-date">Created At</div>
                                            <div class="col col-action">Action</div>
                                        </div>
                                        <c:forEach items="${feedbacks}" var="feedback">
                                            <div class="table-row">
                                                <div class="col col-id">${feedback.id}</div>
                                                <div class="col col-food">
                                                    <c:choose>
                                                        <c:when test="${not empty foodNameMap[feedback.order_item_id]}">
                                                            <a href="${pageContext.request.contextPath}/seller/feedback?action=food&food_id=${foodIdMap[feedback.order_item_id]}">${foodNameMap[feedback.order_item_id]}</a>
                                                        </c:when>
                                                        <c:otherwise>N/A</c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="col col-rating">
                                                    <c:forEach begin="1" end="${feedback.rating}">
                                                        <i class="fa-solid fa-star text-warning"> </i>
                                                    </c:forEach>
                                                </div>
                                                <div class="col col-content">
                                                    <c:choose>
                                                        <c:when test="${fn:length(feedback.content) > 100}">
                                                            ${fn:substring(feedback.content, 0, 100)}...
                                                        </c:when>
                                                        <c:otherwise>
                                                            ${feedback.content}
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="col col-customer">
                                                    <c:choose>
                                                        <c:when test="${not empty accountMap[feedback.user_id]}">
                                                            <a href="${pageContext.request.contextPath}/seller/feedback?action=account&account_id=${feedback.user_id}">${accountMap[feedback.user_id].full_name}</a>
                                                        </c:when>
                                                        <c:otherwise>N/A</c:otherwise>
                                                    </c:choose>
                                                </div>
                                            <div class="col col-status">
                                         
                                             <c:choose>
                                                 <c:when test="${feedback.visible}">
                                                     <span class="status-label status-active">ACTIVE</span>
                                                 </c:when>
                                                 <c:otherwise>
                                                     <span class="status-label status-inactive">INACTIVE</span>
                                                 </c:otherwise>
                                             </c:choose>
                                         
                                     </div>
                                                <div class="col col-date">${feedback.createdAt}</div>
                                                <div class="col col-action actions">
                                                    <a href="${pageContext.request.contextPath}/seller/feedback?action=view&feedbackId=${feedback.id}"><i class="icon-eye"></i></a>
                                                
                                                    <form action="${pageContext.request.contextPath}/seller/feedback" method="post" class="status-form">
                                                        <input type="hidden" name="action" value="update"/>
                                                        <input type="hidden" name="feedbackId" value="${feedback.id}"/>
                                                        <label class="switch">
                                                            <input type="checkbox" <c:if test="${feedback.visible}">checked</c:if> onchange="this.form.submit()">
                                                            <span class="slider round"></span>
                                                        </label>
                                                    </form>
                                                    
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>

                                <div class="divider"></div>
                                       <div class="flex items-center justify-between flex-wrap gap10">
                                        <div class="text-tiny">Showing ${feedbacks.size()} of ${totalPage * 10} entries</div>
                                        <ul class="wg-pagination">
                                            <c:set var="queryString" value="" />
                                            <c:if test="${not empty param.action}">
                                                <c:set var="queryString" value="${queryString}&action=${param.action}" />
                                            </c:if>
                                            <c:if test="${not empty search}">
                                                <c:set var="queryString" value="${queryString}&search=${search}" />
                                            </c:if>
                                            <c:if test="${not empty rating}">
                                                <c:set var="queryString" value="${queryString}&rating=${rating}" />
                                            </c:if>
                                            <c:if test="${not empty sort}">
                                                <c:set var="queryString" value="${queryString}&sort=${sort}" />
                                            </c:if>

                                            <!-- Nút Previous -->
                                            <li class="${currentPage == 1 ? 'disabled' : ''}">
                                                <a href="${pageContext.request.contextPath}/seller/feedback?index=${currentPage - 1}${queryString}">
                                                    <i class="icon-chevron-left"></i>
                                                </a>
                                            </li>

                                            <!-- Trang đầu -->
                                            <li class="${currentPage == 1 ? 'active' : ''}">
                                                <a href="${pageContext.request.contextPath}/seller/feedback?index=1${queryString}">1</a>
                                            </li>

                                            <!-- Dấu ... trước -->
                                            <c:if test="${currentPage > 3}">
                                                <li><span>...</span></li>
                                                </c:if>

                                            <!-- Các trang giữa -->
                                            <c:forEach var="i" begin="${currentPage - 1}" end="${currentPage + 1}">
                                                <c:if test="${i > 1 && i < totalPage}">
                                                    <li class="${currentPage == i ? 'active' : ''}">
                                                        <a href="${pageContext.request.contextPath}/seller/feedback?index=${i}${queryString}">${i}</a>
                                                    </li>
                                                </c:if>
                                            </c:forEach>

                                            <!-- Dấu ... sau -->
                                            <c:if test="${currentPage < totalPage - 2}">
                                                <li><span>...</span></li>
                                                </c:if>

                                            <!-- Trang cuối -->
                                            <c:if test="${totalPage > 1}">
                                                <li class="${currentPage == totalPage ? 'active' : ''}">
                                                    <a href="${pageContext.request.contextPath}/seller/feedback?index=${totalPage}${queryString}">${totalPage}</a>
                                                </li>
                                            </c:if>

                                            <!-- Nút Next -->
                                            <li class="${currentPage == totalPage ? 'disabled' : ''}">
                                                <a href="${pageContext.request.contextPath}/seller/feedback?index=${currentPage + 1}${queryString}">
                                                    <i class="icon-chevron-right"></i>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>    
                            </div>
                        </div>
                    </div>
                    <div class="footer-wrap">
                        <jsp:include page="../common/footer.jsp"></jsp:include>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Javascript -->
    <script src="${pageContext.request.contextPath}/js/jquery.min_1.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/zoom.js"></script>
    <script src="${pageContext.request.contextPath}/js/switcher.js"></script>
    <script src="${pageContext.request.contextPath}/js/theme-settings.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    
    <c:if test="${isSuccess == true}">
        <script>
          document.addEventListener("DOMContentLoaded", function () {
            iziToast.success({
                title: "Success",
                message: "Operation completed successfully!",
                position: 'topRight'
            });
          });
        </script>
        <% session.removeAttribute("isSuccess"); %>
    </c:if>
    <c:if test="${isError == true}">
        <script>
          document.addEventListener("DOMContentLoaded", function () {
            iziToast.error({
                title: "Error",
                message: "An error occurred. Please try again.",
                position: 'topRight'
            });
          });
        </script>
        <% session.removeAttribute("isError"); %>
    </c:if>
</body>
</html>
