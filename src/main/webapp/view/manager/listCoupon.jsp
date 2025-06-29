<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                                 .footer-wrap {
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
                                     padding-left: 190px;
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
                                 .search-container {
                                     display: flex;
                                     align-items: center;
                                     border: 1px solid #e2e8f0; /* viền nhạt */
                                     border-radius: 12px; /* bo góc */
                                     padding: 10px 90px;
                                     background-color: white;
                                     max-width: 600px;
                                     width: 100%;
                                     margin-top: 40px;
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
                                     
                                     margin: 0 auto;
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
                                     padding: 2px 21px;
                                     border-radius: 12px;
                                     border: 1px solid #e2e8f0;
                                     transition: all 0.3s ease;
                                     margin-top: 3px;
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

                                 /* ===== FIXED COUPON TABLE STYLES ===== */
                                 .wg-table.table-product-list {
                                     width: 100%;
                                     background: #fff;
                                     border-radius: 8px;
                                     overflow: hidden;
                                     box-shadow: 0 2px 8px rgba(0,0,0,0.1);
                                     overflow-x: auto;
                                 }

                                 .table-header {
                                     display: flex;
                                     align-items: center;
                                     background: #f8f9fa;
                                     padding: 16px 10px;
                                     border-bottom: 2px solid #e9ecef;
                                     font-weight: 600;
                                     color: #495057;
                                     min-width: 1200px;
                                 }

                                 .table-row {
                                     display: flex;
                                     align-items: center;
                                     padding: 16px 10px;
                                     border-bottom: 1px solid #e9ecef;
                                     transition: background-color 0.2s ease;
                                     min-width: 1200px;
                                 }

                                 .table-row:hover {
                                     background-color: #f8f9fa;
                                 }

                                 .table-row:last-child {
                                     border-bottom: none;
                                 }

                                 /* Fixed column widths for perfect alignment */
                                 .table-header .col,
                                 .table-row .col {
                                     padding: 0 8px;
                                     text-align: left;
                                     display: flex;
                                     align-items: center;        
                                     justify-content: flex-start; 
                                     word-wrap: break-word;
                                     overflow-wrap: break-word;
}

                                 /* Code Column */
                                 .table-header .col:nth-child(1),
                                 .table-row .col:nth-child(1) {
                                     flex: 0 0 130px;
                                     max-width: 130px;
                                 }

                                 /* Coupon ID Column */
                                 .table-header .col:nth-child(2),
                                 .table-row .col:nth-child(2) {
                                     flex: 0 0 100px;
                                     max-width: 100px;
                                     justify-content: center;
                                     text-align: center;
                                 }

                                 /* Description Column */
                                 .table-header .col:nth-child(3),
                                 .table-row .col:nth-child(3) {
                                     flex: 0 0 200px;
                                     max-width: 200px;
                                 }

                                 /* Discount Type Column */
                                 .table-header .col:nth-child(4),
                                 .table-row .col:nth-child(4) {
                                     flex: 0 0 120px;
                                     max-width: 120px;
                                 }

                                 /* Discount Value Column */
                                 .table-header .col:nth-child(5),
                                 .table-row .col:nth-child(5) {
                                     flex: 0 0 120px;
                                     max-width: 120px;
                                 }

                                 /* Min Purchase Column */
                                 .table-header .col:nth-child(6),
                                 .table-row .col:nth-child(6) {
                                     flex: 0 0 120px;
                                     max-width: 120px;
                                 }

                                 /* Start Date Column */
                                 .table-header .col:nth-child(7),
                                 .table-row .col:nth-child(7) {
                                     flex: 0 0 110px;
                                     max-width: 110px;
                                 }

                                 /* End Date Column */
                                 .table-header .col:nth-child(8),
                                 .table-row .col:nth-child(8) {
                                     flex: 0 0 110px;
                                     max-width: 110px;
                                 }

                                 /* Actions Column */
                                 .table-header .col:nth-child(9),
                                 .table-row .col:nth-child(9) {
                                     flex: 0 0 150px;
                                     max-width: 150px;
                                     justify-content: center;
                                     gap: 20px;
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
                                 /* Style cho các link và text trong bảng */
                                .body-title-2 {
                                    color: #000000;
                                    text-decoration: none;
                                    font-weight: 700;
                                    transition: color 0.2s ease;
                                    font-size: 14px;
                                }
                                .body-title_3 {
                                    font-size: 14px;
                                    font-weight: normal !important;
                                    font-family: Arial, sans-serif;
                                    color: #000000;
                                    margin: 0; /* Xóa margin gây lệch */
                                    line-height: 1.5;
}
                                .body-title-2:hover {
                                    color: #000000;
                                    
                                }
                                .discount-type {
                                    padding: 4px 10px;
                                    border-radius: 12px;
                                    font-size: 14px;
                                    display: inline-block;
                                    font-weight: normal;
                                }

                                /* Chỉ style riêng */
                                .discount-fixed {
                                    background-color: #ffe5e5 !important;
                                    color: #cc0000 !important;
                                    border: 1px solid #cc0000 !important;
                                }

                                .discount-percentage {
                                    background-color: #e5f7e5 !important;
                                    color: #007700 !important;
                                    border: 1px solid #007700 !important;
                                }

                                 /* Action buttons styling */
                                 .col.actions {
                                     display: flex;
                                     justify-content: center;
                                     align-items: center;
                                     gap: 20px;
                                 }

                                 .col.actions a {
                                     display: inline-flex;
                                     align-items: center;
                                     justify-content: center;
                                     width: 3px;
                                     height: 2px;
                                     border-radius: 50%;
                                     background-color: #f1f1f1;
                                     transition: background-color 0.3s, transform 0.2s;
                                     text-decoration: none;
                                 }

                                 .col.actions a:hover {
                                     background-color: #e0e0e0;
                                     transform: scale(1.1);
                                 }

                                 .col.actions i {
                                     font-size: 16px;
                                 }

                                 /* Icon colors */
                                 .icon-eye {
                                     color: #007bff;
                                 }
                                 .icon-edit-3 {
                                     color: #28a745;
                                 }
                                 .icon-trash-2 {
                                     color: #dc3545;
                                 }

                                 /* Status badge styling */
                                 .table-row .col span:not(.status-label) {
                                     background-color: #f8f9fa !important;
                                     color: white;
                                     padding: 4px 12px;
                                     border-radius: 12px;
                                     display: inline-block;
                                     font-weight: bold;
                                     font-size: 11px;
                                     text-transform: uppercase;
                                 }

                                 @media (max-width: 1200px) {
                                     .wg-table.table-product-list {
                                         overflow-x: auto; /* Tốt hơn dùng scroll */
                                         -webkit-overflow-scrolling: touch; /* Mượt mà trên iOS */
                                     }

                                     .table-header,
                                     .table-row {
                                         display: flex;
                                         align-items: center;
                                         min-height: 56px;
                                         padding: 16px 10px;
                                         border-bottom: 1px solid #e9ecef;
                                         min-width: 1600px; 
                                     }

                                     .table-header .col,
                                     .table-row .col {
                                         padding: 8px;
                                         flex-shrink: 0; /* Không bị co nhỏ */
                                         box-sizing: border-box;
                                     }

                                     /* Optional: giữ màu nền cho header dễ nhìn */
                                     .table-header {
                                         background-color: #f8f9fa;
                                         font-weight: 600;
                                         color: #495057;
                                     }
                                 }
                                 @media (max-width: 768px) {
                                     .wg-table.table-product-list {
                                         overflow-x: auto;
                                     }

                                     .table-header,
                                     .table-row {
                                         display: flex;
                                         align-items: center;
                                         min-height: 56px;
                                         padding: 16px 10px;
                                         border-bottom: 1px solid #e9ecef;
                                         min-width: 1730px; 
                                     }

                                     .table-header .col:nth-child(1),
                                     .table-row .col:nth-child(1) {
                                         flex: 0 0 130px;
                                         max-width: 130px;
                                     }

                                     .table-header .col:nth-child(2),
                                     .table-row .col:nth-child(2) {
                                         flex: 0 0 100px;
                                         max-width: 100px;
                                         text-align: center;
                                     }

                                     .table-header .col:nth-child(3),
                                     .table-row .col:nth-child(3) {
                                         flex: 0 0 200px;
                                         max-width: 200px;
                                     }

                                     .table-header .col:nth-child(4),
                                     .table-row .col:nth-child(4) {
                                         flex: 0 0 120px;
                                         max-width: 120px;
                                     }

                                     .table-header .col:nth-child(5),
                                     .table-row .col:nth-child(5) {
                                         flex: 0 0 120px;
                                         max-width: 120px;
                                     }

                                     .table-header .col:nth-child(6),
                                     .table-row .col:nth-child(6) {
                                         flex: 0 0 120px;
                                         max-width: 120px;
                                     }

                                     .table-header .col:nth-child(7),
                                     .table-row .col:nth-child(7) {
                                         flex: 0 0 110px;
                                         max-width: 110px;
                                     }

                                     .table-header .col:nth-child(8),
                                     .table-row .col:nth-child(8) {
                                         flex: 0 0 110px;
                                         max-width: 110px;
                                     }

                                     .table-header .col:nth-child(9),
                                     .table-row .col:nth-child(9) {
                                         flex: 0 0 150px;
                                         max-width: 150px;
                                         text-align: center;
                                     }

                                     .table-header .col:nth-child(10),
                                     .table-row .col:nth-child(10) {
                                         flex: 0 0 150px;
                                         max-width: 150px;
                                         text-align: center;
                                     }

                                     .table-header .col:nth-child(11),
                                     .table-row .col:nth-child(11) {
                                         flex: 0 0 120px;
                                         max-width: 120px;
                                         text-align: center;
                                     }
                                     /* Wrapper cuộn ngang khi tràn */
                                     .table-wrapper {
                                         overflow-x: auto;
                                         width: 100%;
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
                                    <h3>Coupon List</h3>
                                </div>
                                <!-- product-list -->
                                    <div class="filter-search-wrapper">
                                        <div class="filter-search-container">
                                            <div class="left-controls">
                                                <!-- Form Filter -->
                                                <form class="form-search" action="${pageContext.request.contextPath}/ManagerCoupon" method="GET">
                                                <input type="hidden" name="action" value="filter" />
                                                <label for="discounttype">Discount Type:</label>
                                                <select name="discounttype" id="discounttype" class="form-control">
                                                    <option value="" ${param.discounttype == null || param.discounttype == '' ? 'selected' : ''}>All Types</option>
                                                    <option value="fixed" ${param.discounttype == 'fixed' ? 'selected' : ''}>Fixed</option>
                                                    <option value="percentage" ${param.discounttype == 'percentage' ? 'selected' : ''}>Percentage</option>
                                                </select>

                                                <button type="submit" class="filter-button">Filter</button>
                                            </form>
                                            <!-- Search -->
                                          <form class="search-box" action="${pageContext.request.contextPath}/ManagerCoupon" method="get">
                                              <input type="hidden" name="action" value="search">
                                                  <div class="search-container">
                                                      <input type="text" name="search" placeholder="Search by code or description..." value="${search}">
                                                          <button type="submit">
                                                              <i class="icon-search"></i>
                                                          </button>
                                                  </div>
                                          </form>                       
                                        </div>  
                                        <a class="tf-button style-1 w208" href="${pageContext.request.contextPath}/ManagerCoupon?action=add&id=${coupon.id}"><i class="icon-plus"></i>Add new</a>
                                    </div>
                         <div class="wg-table table-product-list">
                             <!-- Header -->
                             <div class="table-header" style="display: flex; justify-content: center; min-width: 1730px !important;">
                                 <div class="col" >Code</div>
                                 <div class="col">Coupon ID</div>
                                 <div class="col">Description</div>
                                 <div class="col">Discount_Type</div> 
                                 <div class="col">Discount_Value(%)</div>
                                 <div class="col">Min_Purchase(VNĐ)</div>
                                 <div class="col">Status</div>
                                 <div class="col">Per_Customer_Limit</div>
                                 <div class="col">Start_date</div>
                                 <div class="col" style="text-align: center !important;">End_date</div>
                                 <div class="col" style="text-align: center !important;padding-left:60px">Action</div>
                             </div>
                             <!-- Data Rows -->
                             <c:forEach items="${coupons}" var="coupon">
                                 <div class="table-row" style="display: flex; justify-content: center; min-width: 1730px !important;">
                                     <div class="col">
                                         <div class="body-title-2">${coupon.code} </div>
                                     </div>
                                     <div class="col body-title_3">${coupon.id}</div>
                                     <div class="col body-title_3">${coupon.description}</div>
                                     <c:choose>
                                         <c:when test="${coupon.discount_type == 'fixed'}">
                                             <div class="col">
                                                 <span class="discount-type discount-fixed">${coupon.discount_type}</span>
                                             </div>
                                         </c:when>
                                         <c:when test="${coupon.discount_type == 'percentage'}">
                                             <div class="col">
                                                 <span class="discount-type discount-percentage">${coupon.discount_type}</span>
                                             </div>
                                         </c:when>
                                         <c:otherwise>
                                             <div class="col">
                                                 <span class="discount-type">${coupon.discount_type}</span>
                                             </div>
                                         </c:otherwise>
                                     </c:choose>
                                     <div class="col body-title_3">${coupon.discount_value}</div>
                                     <div class="col body-title_3">${coupon.min_purchase}</div>
                                     <div class="col body-title_3">
                                         <div class="col" >
                                             <c:choose>
                                                 <c:when test="${coupon.is_active==1}">
                                                     <span class="status-label status-active">ACTIVE</span>
                                                 </c:when>
                                                 <c:otherwise>
                                                     <span class="status-label status-inactive">INACTIVE</span>
                                                 </c:otherwise>
                                             </c:choose>
                                         </div>
                                     </div>
                                     <div class="col body-title_3">${coupon.per_customer_limit}</div>
                                     <div class="col body-title_3">${coupon.start_date}</div>
                                     <div class="col body-title_3">${coupon.end_date}</div>
                                     <div class="col actions body-title_3">
                                         <a href="${pageContext.request.contextPath}/ManagerCoupon?action=view&id=${coupon.id}"><i class="icon-eye"></i></a>
                                         <a href="${pageContext.request.contextPath}/ManagerCoupon?action=edit&id=${coupon.id}"> <i class="icon-edit-3"></i></a>
                                         <a href="${pageContext.request.contextPath}/ManagerCoupon?action=delete&id=${coupon.id}"><i class="icon-trash-2"></i></a>
                                     </div>
                                 </div>
                             </c:forEach>
                         </div>
                                    <div class="divider"></div>
                                    <div class="flex items-center justify-between flex-wrap gap10">
                                        <div class="text-tiny">Showing ${coupons.size()} entries</div>
                                        <!-- # Phân Trang -->

                                        <ul class="wg-pagination">
                                            <%-- Tạo queryString với các tham số lọc --%>
                                            <c:set var="queryString" value="&search=${search}&action=filter&discounttype=${discounttype}" />

                                            <!-- Nút Trang Trước (về trang 1) -->
                                            <li class="${currentPage == 1 ? 'disabled' : ''}">
                                                <c:choose>
                                                    <c:when test="${currentPage > 1}">
                                                        <a href="${pageContext.request.contextPath}/ManagerCoupon?index=1${queryString}">
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

                                            <!-- Phân trang logic hiển thị số trang -->
                                            <c:choose>
                                                <c:when test="${currentPage <= totalPage - 2}">
                                                    <c:if test="${currentPage > 1}">
                                                        <li>
                                                            <a href="${pageContext.request.contextPath}/ManagerCoupon?index=${currentPage - 1}${queryString}">${currentPage - 1}</a>
                                                        </li>
                                                    </c:if>
                                                    <li class="active">
                                                        <a href="${pageContext.request.contextPath}/ManagerCoupon?index=${currentPage}${queryString}">${currentPage}</a>
                                                    </li>
                                                    <li>
                                                        <a href="${pageContext.request.contextPath}/ManagerCoupon?index=${currentPage + 1}${queryString}">${currentPage + 1}</a>
                                                    </li>
                                                    <li><span>...</span></li>
                                                    <li>
                                                        <a href="${pageContext.request.contextPath}/ManagerCoupon?index=${totalPage}${queryString}">${totalPage}</a>
                                                    </li>
                                                </c:when>

                                                <c:otherwise>
                                                    <c:set var="startPage" value="${totalPage - 2 <= 0 ? 1 : totalPage - 2}" />
                                                    <c:forEach begin="${startPage}" end="${totalPage}" var="i">
                                                        <li class="${currentPage == i ? 'active' : ''}">
                                                            <a href="${pageContext.request.contextPath}/ManagerCoupon?index=${i}${queryString}">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>

                                            <!-- Nút Trang Sau (về trang cuối) -->
                                            <li class="${currentPage == totalPage ? 'disabled' : ''}">
                                                <c:choose>
                                                    <c:when test="${currentPage < totalPage}">
                                                        <a href="${pageContext.request.contextPath}/ManagerCoupon?index=${totalPage}${queryString}">
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
                    </div>
                      <!-- bottom-page -->
                      <div class="footer-wrap">
                          <jsp:include page="../common/footer.jsp"></jsp:include>
                      </div>
                        <!-- /bottom-page -->                           
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
                 message: "Xóa coupon của bạn đã thành công",
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
                 message: "Yêu cầu tạo coupon của bạn đã thành công",
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
                 message: "Yêu cầu chỉnh sửa coupon của bạn đã thành công",
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