<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
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
        <div class="navbar-area">
            <!-- Menu For Mobile Device -->
            <div class="mobile-nav">
                <a href="index.html" class="logo">
                    <img src="${pageContext.request.contextPath}/images/logos/logo-1.png" alt="Logo">
                </a>
            </div>

            <!-- Menu For Desktop Device -->
            <jsp:include page="../common/homePage/headerUser.jsp"></jsp:include>

                <div class="side-nav-responsive">
                    <div class="container">
                        <div class="dot-menu">
                            <div class="circle-inner">
                                <div class="circle circle-one"></div>
                                <div class="circle circle-two"></div>
                                <div class="circle circle-three"></div>
                            </div>
                        </div>

                        <div class="container-3">
                            <div class="side-nav-inner">
                                <div class="side-nav justify-content-center align-items-center">
                                    <div class="side-nav-item">
                                        <div class="language-on-list">
                                            <select class="language-list-item">
                                                <option>English</option>
                                                <option>العربيّة</option>
                                                <option>Deutsch</option>
                                                <option>Português</option>
                                                <option>简体中文</option>
                                            </select>
                                        </div>

                                        <div class="side-nav-cart">
                                            <a href="#"><i class='bx bx-cart'></i></a>
                                            <span>1</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Navbar Area -->

            <!-- Inner Banner Area -->
            <div class="inner-banner-area">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-lg-5 col-md-5">
                            <div class="inner-content">
                                <h2>Shop Left Sidebar</h2>
                                <ul>
                                    <li><a href="index.html">Home</a></li>

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

        <!--Combo Details Area Start-->
        <div class="combo-details-area mb-115">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="combo-details-container">
                            <div class="row">
                                <!-- Combo Image -->


                                <!-- Combo Info -->
                                <div class="col-md-7 mx-auto">
                                    <span class="combo-badge">Tiết kiệm <fmt:formatNumber value="${combo.originalPrice - combo.discountPrice}" type="currency" currencySymbol="" maxFractionDigits="0"/>đ</span>
                                    <h1 class="combo-title">${combo.comboName}</h1>
                                    <p class="combo-description">${combo.description}</p>

                                    <div class="combo-price-container">
                                        <div class="combo-original-price">Giá gốc: <fmt:formatNumber value="${combo.originalPrice}" type="currency" currencySymbol="" maxFractionDigits="0"/>đ</div>
                                        <div class="combo-discount-price">Giá ưu đãi: <fmt:formatNumber value="${combo.discountPrice}" type="currency" currencySymbol="" maxFractionDigits="0"/>đ</div>
                                        <div class="combo-savings">Tiết kiệm: <fmt:formatNumber value="${combo.originalPrice - combo.discountPrice}" type="currency" currencySymbol="" maxFractionDigits="0"/>đ</div>
                                    </div>

                                    <div class="combo-add-to-cart">
                                        <div class="combo-quantity">
                                            <span>Số lượng:</span>
                                            <button type="button" class="combo-quantity-btn decrease-quantity">-</button>
                                            <input type="number" class="combo-quantity-input" value="1" min="1" id="combo-quantity">
                                            <button type="button" class="combo-quantity-btn increase-quantity">+</button>
                                        </div>
                                        <button type="button" class="combo-add-to-cart-btn" onclick="buyComboNow(${combo.comboId})">Mua ngay</button>
                                        <button type="button" class="combo-add-to-cart-btn ms-2" style="background: blue " onclick="window.location.href = '${pageContext.request.contextPath}/comboController'">Back</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Combo Products -->
                        <div class="combo-details-container mt-4">
                            <h3 class="combo-products-title">Danh sách sản phẩm trong combo:</h3>
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>IMG</th>
                                            <th>Tên sản phẩm</th>
                                            <th>Giá</th>
                                            <th>Số lượng</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="item" items="${foodDetails}">
                                            <c:set var="food" value="${item.food}" />
                                            <tr>
                                                <td><img src="${pageContext.request.contextPath}/${food.image_url}" width="60" height="60"/>
                                                </td>

                                                <td>${food.name}</td>
                                                <td>
                                                    <fmt:formatNumber value="${food.price}" type="currency" currencySymbol="" maxFractionDigits="0"/>đ
                                                </td>
                                                <td>${item.quantityInCombo}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--Combo Details Area End-->

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

    <style>
        /* Enhanced styling for combo details */
        .combo-details-container {
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.08);
            padding: 30px;
            margin-bottom: 40px;
            border: 2px dashed #80b435;
        }

        .combo-image {
            width: 100%;
            height: 400px;
            overflow: hidden;
            border-radius: 8px;
            margin-bottom: 15px;
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #fff;
            padding: 20px;
        }

        .combo-image img {
            max-width: 100%;
            max-height: 100%;
            object-fit: contain;
        }

        .combo-title {
            font-size: 28px;
            font-weight: 600;
            color: #333;
            margin-bottom: 15px;
        }

        .combo-description {
            font-size: 16px;
            color: #666;
            margin-bottom: 20px;
            line-height: 1.6;
        }

        .combo-price-container {
            background: #f8f9fa;
            border-radius: 6px;
            padding: 15px;
            margin-bottom: 20px;
        }

        .combo-original-price {
            font-size: 18px;
            color: #999;
            text-decoration: line-through;
            margin-bottom: 5px;
        }

        .combo-discount-price {
            font-size: 24px;
            color: #80b435;
            font-weight: 700;
        }

        .combo-savings {
            font-size: 16px;
            color: #e74c3c;
            font-weight: 500;
            margin-top: 5px;
        }

        .combo-products-title {
            font-size: 20px;
            font-weight: 600;
            color: #333;
            margin: 30px 0 15px;
            padding-bottom: 10px;
            border-bottom: 1px solid #eee;
        }

        .combo-product-item {
            display: flex;
            align-items: center;
            padding: 15px;
            border: 1px solid #eee;
            border-radius: 6px;
            margin-bottom: 15px;
            transition: all 0.3s ease;
        }

        .combo-product-item:hover {
            box-shadow: 0 5px 15px rgba(0,0,0,0.05);
        }

        .combo-product-image {
            width: 80px;
            height: 80px;
            border-radius: 4px;
            overflow: hidden;
            margin-right: 15px;
        }

        .combo-product-image img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .combo-product-info {
            flex: 1;
        }

        .combo-product-name {
            font-size: 16px;
            font-weight: 500;
            color: #333;
            margin-bottom: 5px;
        }

        .combo-product-price {
            font-size: 14px;
            color: #666;
        }

        .combo-product-quantity {
            font-size: 14px;
            color: #80b435;
            font-weight: 500;
        }

        .combo-add-to-cart {
            display: flex;
            align-items: center;
            margin-top: 30px;
        }

        .combo-quantity {
            display: flex;
            align-items: center;
            margin-right: 15px;
        }

        .combo-quantity-input {
            width: 60px;
            height: 40px;
            text-align: center;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin: 0 10px;
        }

        .combo-quantity-btn {
            width: 30px;
            height: 30px;
            display: flex;
            align-items: center;
            justify-content: center;
            background: #f8f9fa;
            border: 1px solid #ddd;
            border-radius: 4px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .combo-quantity-btn:hover {
            background: #e9ecef;
        }

        .combo-add-to-cart-btn {
            padding: 10px 25px;
            background-color: #80b435;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .combo-add-to-cart-btn:hover {
            background-color: #6a9c2a;
        }

        .combo-badge {
            display: inline-block;
            padding: 5px 10px;
            background-color: #e74c3c;
            color: white;
            border-radius: 3px;
            font-size: 14px;
            font-weight: bold;
            margin-bottom: 15px;
        }
    </style>

    <script>
                                            document.addEventListener('DOMContentLoaded', function () {
                                                // Xử lý tăng giảm số lượng
                                                const quantityInput = document.getElementById('combo-quantity');
                                                const decreaseBtn = document.querySelector('.decrease-quantity');
                                                const increaseBtn = document.querySelector('.increase-quantity');

                                                decreaseBtn.addEventListener('click', function () {
                                                    let currentValue = parseInt(quantityInput.value);
                                                    if (currentValue > 1) {
                                                        quantityInput.value = currentValue - 1;
                                                    }
                                                });

                                                increaseBtn.addEventListener('click', function () {
                                                    let currentValue = parseInt(quantityInput.value);
                                                    quantityInput.value = currentValue + 1;
                                                });
                                            });
                                            // Hàm mua ngay combo
                                            function buyComboNow(comboId) {
                                                const quantity = document.getElementById('combo-quantity').value;
                                                const contextPath = '<%= request.getContextPath()%>'; // 
                                                window.location.href = contextPath + '/buy-combo?comboId=' + comboId + '&quantity=' + quantity;
                                            }
    </script>

</body>

<!-- Mirrored from templates.hibootstrap.com/hilo/default/shop-left-sidebar.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:18 GMT -->

</html>