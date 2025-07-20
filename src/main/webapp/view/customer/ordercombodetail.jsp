<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->
    <!-- Mirrored from themesflat.co/html/remos/oder-detail.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
                <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>
                <style>
                    .order-items-container {
                        max-height: 300px; /* Chiều cao tối đa tương ứng với khoảng 4 item */
                        overflow-y: auto;
                        padding-right: 8px; /* Tránh thanh cuộn che nội dung */
                        border: 1px solid #e0e0e0;
                        border-radius: 8px;
                    }

                    /* Scrollbar - Chrome, Safari, Edge */
                    .order-items-container::-webkit-scrollbar {
                        width: 6px;
                    }

                    .order-items-container::-webkit-scrollbar-thumb {
                        background-color: #ccc;
                        border-radius: 4px;
                    }

                    .order-items-container::-webkit-scrollbar-thumb:hover {
                        background-color: #999;
                    }

                    /* Scrollbar - Firefox */
                    .order-items-container {
                        scrollbar-width: thin;
                        scrollbar-color: #ccc transparent;
                    }
                </style>
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
                                                <link rel="stylesheet" href="font/fonts.css">

                                                    <!-- Icon -->
                                                    <link rel="stylesheet" href="icon/style.css">

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
                                                                                <jsp:include page = "/view/common/sidebar.jsp"></jsp:include>
                                                                                    <!-- /section-menu-left -->
                                                                                    <!-- section-content-right -->
                                                                                    <div class="section-content-right">
                                                                                        <!-- header-dashboard -->
                                                                                    <jsp:include page = "/view/common/headerDashboard.jsp"></jsp:include>
                                                                                        <!-- /header-dashboard -->
                                                                                        <!-- main-content -->
                                                                                        <div class="main-content">
                                                                                            <!-- main-content-wrap -->
                                                                                            <div class="main-content-inner">
                                                                                                <!-- main-content-wrap -->
                                                                                                <div class="main-content-wrap">
                                                                                                    <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                                                                                        <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                                                                                            <li>
                                                                                                                <a href="index.html"><div class="text-tiny">Dashboard</div></a>
                                                                                                            </li>
                                                                                                            <li>
                                                                                                                <i class="icon-chevron-right"></i>
                                                                                                            </li>
                                                                                                            <li>
                                                                                                                <a href="#"><div class="text-tiny">Order</div></a>
                                                                                                            </li>
                                                                                                            <li>
                                                                                                                <i class="icon-chevron-right"></i>
                                                                                                            </li>
                                                                                                            <li>
                                                                                                                <a href="#"><div class="text-tiny">Order detail</div></a>
                                                                                                            </li>
                                                                                                            <li>
                                                                                                                <i class="icon-chevron-right"></i>
                                                                                                            </li>
                                                                                                        </ul>
                                                                                                    </div>
                                                                                                    <!-- order-detail -->
                                                                                                    <div class="wg-order-detail">
                                                                                                        <div class="left flex-grow">
                                                                                                            <div class="wg-box mb-20">
                                                                                                                <div class="wg-table table-order-detail">
                                                                                                                    <ul class="table-title flex items-center justify-between gap20 mb-24">
                                                                                                                        <li>
                                                                                                                            <div class="body-title">All item</div>
                                                                                                                        </li>    
                                                                                                                    </ul>
                                                                                                                    <div class="order-items-container">
                                                                                                                        <ul class="flex flex-column">

                                                                                                                            <li class="product-item gap14">

                                                                                                                                <div class="flex items-center justify-between gap40 flex-grow">
                                                                                                                                    <!-- Tên món -->
                                                                                                                                    <div class="name">
                                                                                                                                        <div class="text-tiny mb-1">Combo Name</div>
                                                                                                                                        <a class="body-title-2">${orderCombo.comboName}</a>
                                                                                                                                </div>

                                                                                                                                <!-- Số lượng -->
                                                                                                                                <div class="name">
                                                                                                                                    <div class="text-tiny mb-1">Quantity</div>
                                                                                                                                    <div class="body-title-2">${orderCombo.quantity}</div>
                                                                                                                                </div>

                                                                                                                                <!-- Giá -->
                                                                                                                                <div class="name">
                                                                                                                                    <div class="text-tiny mb-1">Price</div>
                                                                                                                                    <div class="body-title-2">
                                                                                                                                        <fmt:formatNumber value="${combo.originalPrice}" type="number" groupingUsed="true" /> VND
                                                                                                                                    </div>
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                        </li>
                                                                                                                    </ul>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <div class="wg-box">
                                                                                                            <div class="wg-table table-cart-totals">
                                                                                                                <ul class="table-title flex mb-24">
                                                                                                                    <li>
                                                                                                                        <div class="body-title">Cart Totals</div>
                                                                                                                    </li>    
                                                                                                                    <li>
                                                                                                                        <div class="body-title">Price</div>
                                                                                                                    </li>    
                                                                                                                </ul>
                                                                                                                <ul class="flex flex-column gap14">
                                                                                                                    <li class="cart-totals-item">
                                                                                                                        <span class="body-text">Subtotal:</span>
                                                                                                                        <span class="body-title-2">
                                                                                                                            <fmt:formatNumber value="${subtotalcombo}" type="number" groupingUsed="true"/> VND
                                                                                                                        </span>
                                                                                                                    </li>
                                                                                                                    <li class="divider"></li>
                                                                                                                    <li class="cart-totals-item">
                                                                                                                        <span class="body-text">Discount Price</span>
                                                                                                                        <span class="body-title-2">
                                                                                                                            <fmt:formatNumber value="${discountPrice}" type="number" groupingUsed="true"/> VND
                                                                                                                        </span>
                                                                                                                    </li>
                                                                                                                    <li class="divider"></li>
                                                                                                                    <li class="cart-totals-item">
                                                                                                                        <span class="body-title">Total price</span>
                                                                                                                        <span class="body-title tf-color-1">
                                                                                                                            <fmt:formatNumber value="${orderCombo.totalPrice}" type="number" groupingUsed="true"/> VND
                                                                                                                        </span>
                                                                                                                    </li>
                                                                                                                </ul>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <div class="wg-box" style=" margin-top: 20px; ">
                                                                                                            <a class="tf-button style-1 w-full"
                                                                                                               onclick="cancelOrder(${orderCombo.orderComboId}, '${orderCombo.status}')">Cancel Order</a>
                                                                                                            <a class="tf-button style-1 w-full" href="${pageContext.request.contextPath}/listordercombo">Back to Order List</a>
                                                                                                        </div>

                                                                                                    </div>
                                                                                                </div>
                                                                                                <!-- /order-detail -->
                                                                                            </div>
                                                                                            <!-- /main-content-wrap -->
                                                                                        </div>
                                                                                        <!-- /main-content-wrap -->
                                                                                        <!-- bottom-page -->
                                                                                        <jsp:include page = "/view/common/footer.jsp"></jsp:include>
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
                                                                        <!-- JavaScript -->
                                                                        <script>
                                                                            function cancelOrder(orderComboId, status) {
                                                                                if (status.toLowerCase() !== 'pending') {
                                                                                    alert("Chỉ có thể hủy đơn hàng khi trạng thái là 'pending'");
                                                                                    return;
                                                                                }

                                                                                if (!confirm("Bạn có chắc muốn hủy đơn hàng này không?"))
                                                                                    return;

                                                                                fetch('cancel-order-combo', {
                                                                                    method: 'POST',
                                                                                    headers: {
                                                                                        'Content-Type': 'application/x-www-form-urlencoded'
                                                                                    },
                                                                                    body: 'orderComboId=' + orderComboId
                                                                                })
                                                                                        .then(response => {
                                                                                            if (response.ok) {
                                                                                                alert("Đơn hàng đã được hủy.");
                                                                                                location.reload(); // reload để cập nhật UI
                                                                                            } else {
                                                                                                alert("Không thể hủy đơn hàng. Đơn hàng có thể đã được xử lý.");
                                                                                            }
                                                                                        })
                                                                                        .catch(error => {
                                                                                            console.error("Lỗi:", error);
                                                                                            alert("Có lỗi xảy ra khi hủy đơn.");
                                                                                        });
                                                                            }

                                                                        </script>

                                                                        <script src="${pageContext.request.contextPath}/js/jquery.min_1.js"></script>
                                                                    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
                                                                    <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
                                                                    <script src="${pageContext.request.contextPath}/js/zoom.js"></script>
                                                                    <script src="${pageContext.request.contextPath}/js/switcher.js"></script>
                                                                    <script src="${pageContext.request.contextPath}/js/theme-settings.js"></script>
                                                                    <script src="${pageContext.request.contextPath}/js/main.js"></script>

                                                                </body>



                                                                </html>