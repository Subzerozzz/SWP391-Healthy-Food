<%-- 
    Document   : viewCouponDetail
    Created on : June 14, 2025
    Author     : Developer
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<!--<![endif]-->

<style>
    .coupon-detail-container {
        padding: 40px 20px;
        margin: 20px 0;
        position: relative;
    }

    .coupon-ticket {
        background: #fff;
        position: relative;
        max-width: 800px;
        margin: 0 auto;
        box-shadow: 0 20px 40px rgba(0, 0, 0, 0.15);
        border: 3px solid #333;
        /* Tạo hiệu ứng răng cưa */
        background-image: 
            radial-gradient(circle at 15px 15px, transparent 8px, #fff 8px),
            radial-gradient(circle at 15px calc(100% - 15px), transparent 8px, #fff 8px),
            radial-gradient(circle at calc(100% - 15px) 15px, transparent 8px, #fff 8px),
            radial-gradient(circle at calc(100% - 15px) calc(100% - 15px), transparent 8px, #fff 8px);
        background-size: 30px 30px;
        background-position: 0 0, 0 100%, 100% 0, 100% 100%;
        background-repeat: repeat-x, repeat-x, repeat-x, repeat-x;
    }

    .coupon-ticket::before {
        content: '';
        position: absolute;
        top: -3px;
        left: -3px;
        right: -3px;
        bottom: -3px;
        background: 
            /* Top edge */
            radial-gradient(circle at 12px, transparent 6px, #333 6px, #333 12px, transparent 12px),
            /* Bottom edge */
            radial-gradient(circle at 12px, transparent 6px, #333 6px, #333 12px, transparent 12px),
            /* Left edge */
            radial-gradient(circle at 12px, transparent 6px, #333 6px, #333 12px, transparent 12px),
            /* Right edge */
            radial-gradient(circle at 12px, transparent 6px, #333 6px, #333 12px, transparent 12px);
        background-size: 24px 100%, 24px 100%, 100% 24px, 100% 24px;
        background-position: top, bottom, left, right;
        background-repeat: repeat-x, repeat-x, repeat-y, repeat-y;
        z-index: -1;
    }

    .coupon-inner {
        padding: 30px;
        border: 2px dashed #e74c3c;
        margin: 15px;
        border-radius: 8px;
        background: linear-gradient(135deg, #fef9e7 0%, #fff3cd 100%);
        position: relative;
    }

    .coupon-header {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 25px;
        padding-bottom: 20px;
        border-bottom: 2px dashed #e74c3c;
        position: relative;
    }

    .coupon-header::after {
        content: '✂';
        position: absolute;
        right: -15px;
        top: 50%;
        transform: translateY(-50%);
        font-size: 20px;
        color: #e74c3c;
    }

    .coupon-code {
        font-size: 36px;
        font-weight: 900;
        color: #e74c3c;
        text-transform: uppercase;
        letter-spacing: 3px;
        text-shadow: 2px 2px 4px rgba(0,0,0,0.1);
        font-family: 'Courier New', monospace;
    }

    .coupon-status {
        padding: 10px 20px;
        border-radius: 25px;
        font-weight: 700;
        font-size: 14px;
        text-transform: uppercase;
        letter-spacing: 1px;
        border: 2px solid;
        background: #fff;
    }

    .status-active {
        color: #27ae60;
        border-color: #27ae60;
        box-shadow: 0 0 10px rgba(39, 174, 96, 0.3);
    }

    .status-inactive {
        color: #e74c3c;
        border-color: #e74c3c;
        box-shadow: 0 0 10px rgba(231, 76, 60, 0.3);
    }

    .coupon-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
        gap: 20px;
        margin-bottom: 25px;
    }

    .info-card {
        background: #fff;
        border-radius: 8px;
        padding: 20px;
        border: 2px solid #f8f9fa;
        transition: all 0.3s ease;
        position: relative;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    }

    .info-card::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background: linear-gradient(135deg, rgba(231, 76, 60, 0.02) 0%, rgba(231, 76, 60, 0.05) 100%);
        opacity: 0;
        transition: opacity 0.3s ease;
        border-radius: 6px;
    }

    .info-card:hover {
        transform: translateY(-3px);
        box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
        border-color: #e74c3c;
    }

    .info-card:hover::before {
        opacity: 1;
    }

    .info-label {
        font-size: 14px;
        font-weight: 600;
        color: #64748b;
        text-transform: uppercase;
        letter-spacing: 1px;
        margin-bottom: 8px;
        position: relative;
        z-index: 2;
    }

    .info-value {
        font-size: 18px;
        font-weight: 700;
        color: #1e293b;
        position: relative;
        z-index: 2;
    }

    .discount-highlight {
        background: linear-gradient(135deg, #fff5f5, #fef2f2);
        border: 2px solid #e74c3c !important;
        position: relative;
    }

    .discount-highlight::after {
        content: '💰';
        position: absolute;
        top: 10px;
        right: 15px;
        font-size: 20px;
    }

    .discount-highlight .info-value {
        color: #e74c3c;
        font-size: 24px;
        font-weight: 900;
    }

    .date-range-card {
        background: linear-gradient(135deg, #f0f9ff, #e0f2fe);
        border: 2px solid #3b82f6 !important;
        position: relative;
    }

    .date-range-card::after {
        content: '📅';
        position: absolute;
        top: 10px;
        right: 15px;
        font-size: 20px;
    }

    .date-range-card .info-value {
        color: #3b82f6;
        font-weight: 700;
    }

    .usage-stats {
        display: flex;
        align-items: center;
        gap: 15px;
        margin-top: 10px;
    }

    .usage-bar {
        flex: 1;
        height: 8px;
        background: rgba(102, 126, 234, 0.2);
        border-radius: 4px;
        overflow: hidden;
    }

    .usage-progress {
        height: 100%;
        background: linear-gradient(90deg, #667eea, #764ba2);
        border-radius: 4px;
        transition: width 0.5s ease;
    }

    .usage-text {
        font-size: 14px;
        font-weight: 600;
        color: #64748b;
        white-space: nowrap;
    }

    .description-card {
        background: linear-gradient(135deg, #fffbeb, #fef3c7);
        border-radius: 8px;
        padding: 20px;
        margin: 20px 0;
        border: 2px dashed #f59e0b;
        position: relative;
    }

    .description-card::before {
        content: '📝';
        position: absolute;
        top: 15px;
        right: 20px;
        font-size: 20px;
    }

    .description-card .info-label {
        color: #92400e;
        font-weight: 700;
    }

    .description-card .info-value {
        color: #92400e;
        font-weight: 600;
    }

    .action-buttons {
        display: flex;
        gap: 15px;
        justify-content: center;
        margin-top: 30px;
    }

    .btn-modern {
        padding: 12px 30px;
        border-radius: 25px;
        font-weight: 600;
        text-decoration: none;
        transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
        border: none;
        cursor: pointer;
        text-transform: uppercase;
        letter-spacing: 1px;
        font-size: 14px;
    }

    .btn-back {
        background: #6c757d;
        color: white;
        border: 2px solid #6c757d;
    }

    .btn-back:hover {
        background: #5a6268;
        border-color: #5a6268;
        transform: translateY(-2px);
        box-shadow: 0 8px 25px rgba(0, 0, 0, 0.2);
        color: white;
    }

    .btn-edit {
        background: #e74c3c;
        color: white;
        border: 2px solid #e74c3c;
    }

    .btn-edit:hover {
        background: #c0392b;
        border-color: #c0392b;
        transform: translateY(-2px);
        box-shadow: 0 8px 25px rgba(231, 76, 60, 0.3);
        color: white;
    }

    /* Responsive Design */
    @media (max-width: 768px) {
        .coupon-detail-container {
            margin: 10px 0;
            padding: 20px;
        }
        
        .coupon-header {
            flex-direction: column;
            gap: 15px;
            text-align: center;
        }
        
        .coupon-code {
            font-size: 24px;
        }
        
        .coupon-grid {
            grid-template-columns: 1fr;
            gap: 15px;
        }
        
        .action-buttons {
            flex-direction: column;
            align-items: center;
        }
        
        .btn-modern {
            width: 200px;
        }
    }

    /* Animation for page load */
    .fade-in {
        animation: fadeInUp 0.6s ease-out;
    }

    @keyframes fadeInUp {
        from {
            opacity: 0;
            transform: translateY(30px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
</style>

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font/fonts.css">

    <!-- Icon -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/icon/style.css">

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
                <jsp:include page="../common/manager/sidebar.jsp"></jsp:include>
                <!-- /section-menu-left -->
                <!-- section-content-right -->
                <div class="section-content-right">
                    <!-- header-dashboard -->
                    <jsp:include page="../common/manager/header.jsp"></jsp:include> 
                    <!-- /header-dashboard -->
                    <!-- main-content -->
                    <div class="main-content">
                        <!-- main-content-wrap -->
                        <div class="main-content-inner">
                            <!-- main-content-wrap -->
                            <div class="main-content-wrap">
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                    <h3>Coupon Details</h3>
                                    <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                        <li>
                                            <a href="index"><div class="text-tiny">Dashboard</div></a>
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
                                            <div class="text-tiny">Coupon Detail</div>
                                        </li>
                                    </ul>
                                </div>

                                <!-- Coupon Detail Container -->
                                <div class="coupon-detail-container fade-in">
                                    <div class="coupon-ticket">
                                        <div class="coupon-inner">
                                        <!-- Coupon Header -->
                                        <div class="coupon-header">
                                            <div class="coupon-code">${coupon.code}</div>
                                        </div>
                                        <!-- Description Card -->
                                        <div class="description-card">
                                            <div class="info-label">Description</div>
                                            <div class="info-value">${coupon.description}</div>
                                        </div>

                                        <!-- Coupon Details Grid -->
                                        <div class="coupon-grid">
                                            <!-- Discount Information -->
                                            <div class="info-card discount-highlight">
                                                <div class="info-label">Discount Type</div>
                                                <div class="info-value">
                                                    <c:choose>
                                                        <c:when test="${coupon.discountType == 'percentage'}">
                                                            ${coupon.discountValue}% OFF
                                                        </c:when>
                                                        <c:otherwise>
                                                            $${coupon.discountValue} OFF
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>

                                            <!-- Minimum Purchase -->
                                            <div class="info-card">
                                                <div class="info-label">Minimum Purchase</div>
                                                <div class="info-value">
                                                    <c:choose>
                                                        <c:when test="${coupon.minPurchase != null}">
                                                            $${coupon.minPurchase}
                                                        </c:when>
                                                        <c:otherwise>
                                                            No minimum
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>

                                            <!-- Maximum Discount -->
                                            <div class="info-card">
                                                <div class="info-label">Maximum Discount</div>
                                                <div class="info-value">
                                                    <c:choose>
                                                        <c:when test="${coupon.maxDiscount != null}">
                                                            $${coupon.maxDiscount}
                                                        </c:when>
                                                        <c:otherwise>
                                                            No limit
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>

                                            <!-- Date Range -->
                                            <div class="info-card date-range-card">
                                                <div class="info-label">Valid Period</div>
                                                <div class="info-value">
                                                    ${coupon.startDate} - ${coupon.endDate}
                                                </div>
                                            </div>

                                            <!-- Usage Statistics -->
                                            <div class="info-card">
                                                <div class="info-label">Usage Statistics</div>
                                                <div class="info-value">
                                                    ${coupon.usageCount} / 
                                                    <c:choose>
                                                        <c:when test="${coupon.usageLimit != null}">
                                                            ${coupon.usageLimit}
                                                        </c:when>
                                                        <c:otherwise>
                                                            Unlimited
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <c:if test="${coupon.usageLimit != null}">
                                                    <div class="usage-stats">
                                                        <div class="usage-bar">
                                                            <div class="usage-progress" style="width: ${(coupon.usageCount * 100) / coupon.usageLimit}%"></div>
                                                        </div>
                                                        <div class="usage-text">
                                                            ${Math.round((coupon.usageCount * 100) / coupon.usageLimit)}% used
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </div>

                                            <!-- Created Date -->
                                            <div class="info-card">
                                                <div class="info-label">Created Date</div>
                                                <div class="info-value">${coupon.createdAt}</div>
                                            </div>

                                            <!-- Last Updated -->
                                            <div class="info-card">
                                                <div class="info-label">Last Updated</div>
                                                <div class="info-value">${coupon.updatedAt}</div>
                                            </div>
                                        </div>

                                        <!-- Action Buttons -->
                                        <div class="action-buttons">
                                            <a href="${pageContext.request.contextPath}/ManagerCoupon"" class="btn-modern btn-back">
                                                Back to List
                                            </a>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /Coupon Detail Container -->
                            </div>
                            <!-- /main-content-wrap -->
                        </div>
                        <!-- /main-content-wrap -->
                        <!-- bottom-page -->
                        <div class="bottom-page">
                            
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
</body>

</html>