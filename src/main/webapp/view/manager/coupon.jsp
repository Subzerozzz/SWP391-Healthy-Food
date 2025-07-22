<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="zxx">
    
<!-- Mirrored from templates.hibootstrap.com/hilo/default/shop-left-sidebar.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:18 GMT -->
<head>
        <!-- Required Meta Tags -->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--=== Link of CSS Files ===--> 
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/boxicons.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nice-select.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/meanmenu.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme-dark.css">

        <!--=== Title & Favicon ===-->
        <title>Hilo - Organic Food eCommerce Shop HTML Template</title>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
        <style>
            .coupon-card {
                border: 2px dashed #ffcc99;
                padding: 20px;
                margin-bottom: 30px;
                border-radius: 8px;
                background-color: #fff;
                transition: all 0.3s ease;
                overflow-wrap: break-word;   /* Ngắt từ dài */
                word-wrap: break-word;
                word-break: break-word;
                max-width: 100%;
                box-sizing: border-box;
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
                word-wrap: break-word;
                word-break: break-word;
                white-space: normal;
            }

            .coupon-description {
                color: #666;
                margin: 10px 0;
                min-height: 60px;
                font-size: 15px;
                overflow-wrap: break-word;
                word-break: break-word;
                white-space: normal;
                max-height: 150px;         /* Nếu mô tả quá dài, sẽ scroll */
                overflow-y: auto;
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
                overflow-wrap: break-word;
                word-break: break-word;
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
             justify-content: center !important; /* ép căn giữa */
             align-items: center;
             gap: 8px;
             list-style: none;
             padding-left: 0;
             margin-top: 30px;
             width: 100%;  /* chiếm toàn bộ để căn giữa */
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
             background-color: #ffa50033; /* Cam nhạt khi hover */
             color: #000;
         }

         .page-item.active .page-link {
             background-color: orange; /* Nút màu cam */
             border-color: orange;
             color: white;
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
            .inner-banner-area {
                background-color: #F2F1F7;
                overflow: hidden;
                position: relative;
                z-index: 1;
                transform: translateY(-50px) !important;
}
    </style>

    </head>
    <body>
        <!-- Pre Loader -->
        <div class="preloader">
            <div class="d-table">
                <div class="d-table-cell">
                    <img src="${pageContext.request.contextPath}/images/preloder-img.png" alt="Images">
                    <h2>Hilo</h2>
                </div>
            </div>
        </div>
        <!-- End Pre Loader -->

        <!-- Start Navbar Area -->
        <jsp:include page="../common/homePage/headerUser.jsp"></jsp:include>    
        <!-- End Navbar Area -->
        <!-- Inner Banner Area -->
         <!-- Inner Banner Area -->
        <div class="inner-banner-area">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-5 col-md-5">
                        <div class="inner-content">
                            <h2>Coupon</h2>
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                                <li>Coupon</li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-lg-7 col-md-7">
                        <div class="inner-img">
                            <img src="${pageContext.request.contextPath}/images/inner-banner/inner-banner6.png" alt="Images">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Inner Banner Area End -->
        <!-- main-content -->
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
                                                                    <c:when test="${coupon.discount_type == 'percentage'}">                                                        
                                                                        ${coupon.discount_value}% OFF
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        $<fmt:formatNumber value="${coupon.discount_value}" pattern="#,##0.00"/> OFF
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                            <div class="coupon-details">
                                                                <div>Min Purchase: $<fmt:formatNumber value="${coupon.min_purchase}" pattern="#,##0.00"/></div>
                                                                <c:if test="${not empty coupon.max_discount}">
                                                                    <div>Max Discount: $<fmt:formatNumber value="${coupon.max_discount}" pattern="#,##0.00"/></div>
                                                                </c:if>
                                                            </div>
                                                            <div class="coupon-dates">
                                                                Valid from: <fmt:formatDate value="${coupon.start_date}" pattern="MMM dd, yyyy"/> -
                                                                <fmt:formatDate value="${coupon.end_date}" pattern="MMM dd, yyyy"/>

                                                                <c:set var="now" value="<%= new java.util.Date() %>"/>
                                                                <c:set var="remainingDays" value="${((coupon.end_date.time - now.time) / (1000*60*60*24))}"/>
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
                                                                <c:if test="${not empty coupon.usage_limit}">
                                                                    Used ${coupon.usage_count} times out of ${coupon.usage_limit}
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
        <!-- Product Area End -->

        <!-- Footer Area -->
        <jsp:include page="../common/homePage/footerUser.jsp"></jsp:include>
        <!-- Footer Area End -->

        <!--=== Link of JS Files ===-->
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.nice-select.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/meanmenu.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.ajaxchimp.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/form-validator.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/contact-form-script.js"></script>
        <script src="${pageContext.request.contextPath}/js/mixitup.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/custom.js"></script>
    </body>

<!-- Mirrored from templates.hibootstrap.com/hilo/default/shop-left-sidebar.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:18 GMT -->
</html>