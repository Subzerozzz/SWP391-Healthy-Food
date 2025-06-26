<%-- 
    Document   : viewCoupon
    Created on : Jun 15, 2025
    Author     : Predator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<head>
    <!-- Basic Page Needs -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <!-- Theme Style -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min_1.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animation.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-select.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_1.css">

    <!-- Font -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font/fonts.css">
    <!-- Icon -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/icon/style.css">

    <!-- Favicon and Touch Icons  -->
    <link rel="shortcut icon" href="images/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="images/favicon.png">

    <style>
        .coupon-card {
            border: 2px dashed #ffcc99;
            padding: 20px;
            margin-bottom: 30px;
            border-radius: 8px;
            background-color: #fff;
            transition: all 0.3s ease;
        }

        .coupon-card:hover {
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            transform: translateY(-2px);
        }

        .coupon-header {
            border-bottom: 1px solid #eee;
            padding-bottom: 15px;
            margin-bottom: 15px;
        }

        .coupon-code {
            font-size: 24px;
            font-weight: bold;
            color: #80b435;
            letter-spacing: 2px;

            word-wrap: break-word;   /* Cho phép xuống dòng nếu từ dài quá */
            word-break: break-all;   /* Bắt buộc xuống dòng nếu quá dài */
            white-space: normal;     /* Cho phép xuống dòng (mặc định là nowrap) */
        }

        .coupon-description {
            color: #666;
            margin: 10px 0;
            min-height: 60px;
            font-size: 15px; /* 👈 tăng kích thước chữ lên một chút */
        }

        .coupon-details {
            display: flex;
            justify-content: space-between;
            margin-top: 15px;
            font-size: 14px;
            color: #888;
        }

        .coupon-value {
            font-size: 20px;
            color: #e83e8c;
            font-weight: bold;
        }

        .coupon-dates {
            font-size: 12px;
            color: #999;
            margin-top: 10px;
        }

        .coupon-usage {
            background-color: #f8f9fa;
            padding: 8px;
            border-radius: 4px;
            margin-top: 10px;
            font-size: 13px;
        }

        .coupon-search {
            margin-bottom: 30px;
        }

        .coupon-search .form-control {
            border-radius: 4px 0 0 4px;
            height: 45px;
        }

        .coupon-search .btn {
            border-radius: 0 4px 4px 0;
            background-color: #80b435;
            color: white;
            border-color: #80b435;
        }

        .coupon-search .btn:hover {
            background-color: #6a9a2d;
            border-color: #6a9a2d;
        }

        .pagination {
            margin-top: 30px;
            justify-content: center;
        }

        .pagination .page-link {
            color: #2275FC;
        }

        .pagination .page-item.active .page-link {
            background-color: #2275FC;
            border-color: #2275FC;
            color: white;
        }

        .remaining-time {
            margin-top: 5px;
            font-size: 12px;
            color: #666;
        }

        .remaining-time.urgent span {
            color: #dc3545;
            font-weight: bold;
        }
             /* Search Box Styling */
               .search-box {
                  display: flex;
                  align-items: center;
                  transform: translateY(-19px);
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
        .pagination {
                   display: flex;
                   justify-content: flex-end; /* 👈 Lệch phải */
                   gap: 8px;
                   list-style: none;
                   padding-left: 0;
                   margin-top: 30px;
          }

          .page-item {
                     display: flex;
                     align-items: center;
           }
           .page-link {
                       width: 36px;
                       height: 36px;
                       line-height: 36px;
                       border-radius: 50%;
                       text-align: center;
                       text-decoration: none;
                       font-size: 16px;
                       color: #333;
                       border: 1px solid #e0e0e0;
                       background-color: white;
                       transition: all 0.2s ease;
                       display: inline-block;
                       padding: 0;
            }
            .page-link:hover {
                        background-color: #f0f0f0;
                        color: #000;
            }
            .page-item.active .page-link {
                        background-color: #1a73e8;
                        color: white;
                        border: none;
            }
            .page-item.disabled .page-link {
                        pointer-events: none;
                        opacity: 0.4;
                        background-color: white;
                        border: 1px solid #e0e0e0;
            }                   
            footer-wrap {
                        background: #fff;
                        padding: 16px;
                        text-align: center;
                        box-shadow: 0px 4px 24px 2px rgba(20, 25, 38, 0.05);
                        font-family: Arial, sans-serif;
                        font-size: 14px;
                        color: #333;
            }
            .footer-wrap .bottom-page {
                        display: inline-flex;
                        align-items: center;
                        gap: 6px;
                        flex-wrap: wrap;
                        justify-content: center;
                        padding-left: 700px;
            }

            .footer-wrap .bottom-page .body-text {
                         margin: 0;
            }

           .footer-wrap .bottom-page i.icon-heart {
                         color: #ff6a00;
                         font-style: normal; /* hoặc dùng font-awesome nếu có */
            }
            .footer-wrap .bottom-page a {
                          color: #007bff;
                          text-decoration: none;
            }
            .footer-wrap .bottom-page a:hover {
                text-decoration: underline;
            }

    </style>

    <title>View Coupon </title>
</head>

<body class="body">
<div id="wrapper">
    <div id="page" class="">
        <div class="layout-wrap">
            <!-- preload -->
            <div id="preload" class="preload-container">
                <div class="preloading">
                    <span></span>
                </div>
            </div>
            <!-- /preload -->

            <!-- section-menu-left -->
            <jsp:include page="../common/manager/sidebar.jsp"></jsp:include>
            <!-- /section-menu-left -->

            <!-- section-content-right -->
            <div class="section-content-right">
                <!-- header-dashboard -->
                <jsp:include page="../common/manager/headerDashboard.jsp"></jsp:include> 
                <!-- /header-dashboard -->
                <!-- main-content -->
                <div class="main-content">
                    <div class="main-content-inner">
                        <div class="main-content-wrap">
                            <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                <h3>Coupon</h3>
                            </div>

                            <div class="coupon-area white-bg pt-0 pb-0 mb-70">
                                <div class="container">
                                    <div class="row">
                                        <div class="col-12">
                                            <!-- Search form -->
                                            <form class="search-box" action="${pageContext.request.contextPath}/Coupon" method="get">
                                            <div class="search-container">
                                                <input type="text" name="search" placeholder="Search by code or description..." value="${searchCode}">
                                                    <button type="submit">
                                                        <i class="icon-search"></i>
                                                    </button>
                                            </div>
                                        </form>     
                                            <c:if test="${empty coupons}">
                                                <div class="alert alert-info">No coupons available at the moment.</div>
                                            </c:if>
                                            <div class="row">
                                                <c:forEach var="coupon" items="${coupons}">
                                                    <div class="col-md-6 col-lg-4">
                                                        <div class="coupon-card">
                                                            <div class="coupon-header">
                                                                <div class="coupon-code">${coupon.code}</div>
                                                            </div>
                                                            <div class="coupon-description">${coupon.description}</div>
                                                            <div class="coupon-value">
                                                                <c:choose>
                                                                    <c:when test="${coupon.discountType == 'percentage'}">                                                        
                                                                        ${coupon.discountValue}% OFF
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        $<fmt:formatNumber value="${coupon.discountValue}" pattern="#,##0.00"/> OFF
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                            <div class="coupon-details">
                                                                <div>Min Purchase: $<fmt:formatNumber value="${coupon.minPurchase}" pattern="#,##0.00"/></div>
                                                                <c:if test="${not empty coupon.maxDiscount}">
                                                                    <div>Max Discount: $<fmt:formatNumber value="${coupon.maxDiscount}" pattern="#,##0.00"/></div>
                                                                </c:if>
                                                            </div>
                                                            <div class="coupon-dates">
                                                                Valid from: <fmt:formatDate value="${coupon.startDate}" pattern="MMM dd, yyyy"/> -
                                                                <fmt:formatDate value="${coupon.endDate}" pattern="MMM dd, yyyy"/>

                                                                <c:set var="now" value="<%= new java.util.Date() %>"/>
                                                                <c:set var="remainingDays" value="${((coupon.endDate.time - now.time) / (1000*60*60*24))}"/>
                                                                <div class="remaining-time ${remainingDays <= 7 ? 'urgent' : ''}">
                                                                    <c:choose>
                                                                        <c:when test="${remainingDays > 1}">
                                                                            <span>Expires in ${Math.round(remainingDays)} days</span>
                                                                        </c:when>
                                                                        <c:when test="${remainingDays > 0}">
                                                                            <span>Expires in less than a day</span>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <span>Expired</span>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                            </div>
                                                            <div class="coupon-usage">
                                                                <c:if test="${not empty coupon.usageLimit}">
                                                                    Used ${coupon.usageCount} times out of ${coupon.usageLimit}
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div> <!-- .col-12 -->
                                    </div> <!-- .row -->
                                </div> <!-- .container -->
                            </div> <!-- .coupon-area -->
                            <!-- Pagination -->
                                <c:if test="${totalPages > 1}">
                                    <ul class="pagination justify-content-end mt-4">
                                        <%-- Tạo queryString giữ lại tham số searchCode nếu có --%>
                                        <c:set var="queryString" value="${not empty searchCode ? '&search=' + searchCode : ''}" />

                                        <!-- Nút về trang đầu -->
                                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                            <c:choose>
                                                <c:when test="${currentPage > 1}">
                                                    <a class="page-link" href="${pageContext.request.contextPath}/Coupon?page=1${queryString}">&laquo;</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a class="page-link" href="javascript:void(0);">&laquo;</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </li>

                                        <!-- Hiển thị các trang xung quanh và phân trang thông minh -->
                                        <c:choose>
                                            <c:when test="${currentPage <= totalPages - 2}">
                                                <c:if test="${currentPage > 1}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="${pageContext.request.contextPath}/Coupon?page=${currentPage - 1}${queryString}">${currentPage - 1}</a>
                                                    </li>
                                                </c:if>
                                                <li class="page-item active">
                                                    <a class="page-link" href="${pageContext.request.contextPath}/Coupon?page=${currentPage}${queryString}">${currentPage}</a>
                                                </li>
                                                <li class="page-item">
                                                    <a class="page-link" href="${pageContext.request.contextPath}/Coupon?page=${currentPage + 1}${queryString}">${currentPage + 1}</a>
                                                </li>
                                                <li class="page-item disabled"><span class="page-link">...</span></li>
                                                <li class="page-item">
                                                    <a class="page-link" href="${pageContext.request.contextPath}/Coupon?page=${totalPages}${queryString}">${totalPages}</a>
                                                </li>
                                            </c:when>

                                            <c:otherwise>
                                                <c:set var="startPage" value="${totalPages - 2 <= 0 ? 1 : totalPages - 2}" />
                                                <c:forEach var="i" begin="${startPage}" end="${totalPages}">
                                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                        <a class="page-link" href="${pageContext.request.contextPath}/Coupon?page=${i}${queryString}">${i}</a>
                                                    </li>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>

                                        <!-- Nút về trang cuối -->
                                        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                            <c:choose>
                                                <c:when test="${currentPage < totalPages}">
                                                    <a class="page-link" href="${pageContext.request.contextPath}/Coupon?page=${totalPages}${queryString}">&raquo;</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a class="page-link" href="javascript:void(0);">&raquo;</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </li>
                                    </ul>
                                </c:if>
                        </div> <!-- .main-content-wrap -->
                    </div> <!-- .main-content-inner -->
                </div> <!-- .main-content -->

                <div class="footer-wrap">
                    <jsp:include page="../common/manager/footer.jsp"></jsp:include>
                </div>
            </div> <!-- .section-content-right -->

        </div> <!-- .layout-wrap -->
    </div> <!-- #page -->
</div> <!-- #wrapper -->

<!-- Javascript -->
<script src="${pageContext.request.contextPath}/js/jquery.min_1.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/js/zoom.js"></script>
<script src="${pageContext.request.contextPath}/js/switcher.js"></script>
<script src="${pageContext.request.contextPath}/js/theme-settings.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
