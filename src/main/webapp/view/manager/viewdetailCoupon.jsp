<%-- 
    Document   : viewCoupon
    Created on : Jun 15, 2025
    Author     : Predator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title>View Coupon Details</title>
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
                    <jsp:include page="../common/manager/header.jsp"></jsp:include> 
                    <!-- /header-dashboard -->
                    
                    <!-- main-content -->
                    <div class="main-content">
                        <div class="main-content-inner">
                            <div class="main-content-wrap">
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                    <h3>Coupon Details</h3>
                                </div>
                                
                                <!-- coupon-details -->
                                <div style="background-color: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); margin-bottom: 20px;">
                                    
                                    <!-- Coupon Code -->
                                    <div style="margin-bottom: 25px; border-bottom: 1px solid #f0f0f0; padding-bottom: 15px;">
                                        <h4 style="font-weight: bold; color: #333; margin-bottom: 8px; font-size: 16px;">Coupon Code</h4>
                                        <p style="font-size: 18px; color: #007bff; font-weight: 600; margin: 0; background: #f8f9fa; padding: 10px; border-radius: 4px;">${coupon.code}</p>
                                    </div>
                                    
                                    <!-- Description -->
                                    <div style="margin-bottom: 25px; border-bottom: 1px solid #f0f0f0; padding-bottom: 15px;">
                                        <h4 style="font-weight: bold; color: #333; margin-bottom: 8px; font-size: 16px;">Description</h4>
                                        <p style="color: #666; margin: 0; line-height: 1.5;">${coupon.description}</p>
                                    </div>
                                    
                                    <!-- Discount Information Row -->
                                    <div style="display: flex; gap: 30px; margin-bottom: 25px; border-bottom: 1px solid #f0f0f0; padding-bottom: 15px;">
                                        <div style="flex: 1;">
                                            <h4 style="font-weight: bold; color: #333; margin-bottom: 8px; font-size: 16px;">Discount Type</h4>
                                            <p style="color: #666; margin: 0;">
                                                <c:choose>
                                                    <c:when test="${coupon.discountType == 'percentage'}">
                                                        <span style="background: #28a745; color: white; padding: 4px 12px; border-radius: 20px; font-size: 14px;">Percentage (%)</span>
                                                    </c:when>
                                                    <c:when test="${coupon.discountType == 'fixed'}">
                                                        <span style="background: #17a2b8; color: white; padding: 4px 12px; border-radius: 20px; font-size: 14px;">Fixed Amount</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span style="background: #6c757d; color: white; padding: 4px 12px; border-radius: 20px; font-size: 14px;">${coupon.discountType}</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                        
                                        <div style="flex: 1;">
                                            <h4 style="font-weight: bold; color: #333; margin-bottom: 8px; font-size: 16px;">Discount Value</h4>
                                            <p style="color: #dc3545; font-weight: 600; font-size: 18px; margin: 0;">
                                                <c:choose>
                                                    <c:when test="${coupon.discountType == 'percentage'}">${coupon.discountValue}%</c:when>
                                                    <c:otherwise>${coupon.discountValue}</c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                    </div>

                                    <!-- Purchase Limits Row -->
                                    <div style="display: flex; gap: 30px; margin-bottom: 25px; border-bottom: 1px solid #f0f0f0; padding-bottom: 15px;">
                                        <div style="flex: 1;">
                                            <h4 style="font-weight: bold; color: #333; margin-bottom: 8px; font-size: 16px;">Minimum Purchase</h4>
                                            <p style="color: #666; margin: 0;">
                                                <c:choose>
                                                    <c:when test="${coupon.minPurchase != null && coupon.minPurchase > 0}">
                                                        <span style="font-weight: 600;">${coupon.minPurchase}</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span style="color: #28a745; font-style: italic;">No minimum required</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                        
                                        <c:if test="${coupon.discountType == 'percentage'}">
                                            <div style="flex: 1;">
                                                <h4 style="font-weight: bold; color: #333; margin-bottom: 8px; font-size: 16px;">Maximum Discount</h4>
                                                <p style="color: #666; margin: 0;">
                                                    <c:choose>
                                                        <c:when test="${coupon.maxDiscount != null && coupon.maxDiscount > 0}">
                                                            <span style="font-weight: 600;">${coupon.maxDiscount}</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span style="color: #ffc107; font-style: italic;">No maximum limit</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </p>
                                            </div>
                                        </c:if>
                                    </div>

                                    <!-- Date Range Row -->
                                    <div style="display: flex; gap: 30px; margin-bottom: 25px; border-bottom: 1px solid #f0f0f0; padding-bottom: 15px;">
                                        <div style="flex: 1;">
                                            <h4 style="font-weight: bold; color: #333; margin-bottom: 8px; font-size: 16px;">Start Date</h4>
                                            <p style="color: #666; margin: 0; font-weight: 500;">${coupon.startDate}</p>
                                        </div>
                                        <div style="flex: 1;">
                                            <h4 style="font-weight: bold; color: #333; margin-bottom: 8px; font-size: 16px;">End Date</h4>
                                            <p style="color: #666; margin: 0; font-weight: 500;">${coupon.endDate}</p>
                                        </div>
                                    </div>  
                                    <!-- Back Button -->
                                    <div style="text-align: center; padding-top: 20px; border-top: 1px solid #f0f0f0;">
                                        <a href="${pageContext.request.contextPath}/ManagerCoupon" 
                                           style="display: inline-block; background: #007bff; color: white; padding: 12px 30px; text-decoration: none; border-radius: 5px; font-weight: 600; font-size: 16px; transition: background 0.3s;">
                                            ← Back to Coupon List
                                        </a>
                                    </div>
                                </div>
                                <!-- /coupon-details -->
                            </div>
                        </div>
                        
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