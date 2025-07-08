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
        
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/izitoast@1.4.0/dist/css/iziToast.min.css">

        <!--=== Title & Favicon ===-->
        <title>Hilo - Organic Food eCommerce Shop HTML Template</title>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
    </head>
    <style>
        .pagination li.disabled a {
            pointer-events: none;
            opacity: 0.5;
            cursor: not-allowed;
        }

        /* Combo card styling */
        .combo-product {
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            margin-bottom: 30px;
            overflow: hidden;
        }

        .combo-product:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }

        .combo-product .product-img {
            width: 100%;
            height: 280px;
            position: relative;
            overflow: hidden;
            padding: 20px;
            background: #fff;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .combo-product .product-img img {
            max-width: 100%;
            max-height: 100%;
            object-fit: contain;
            transition: transform 0.5s ease;
        }

        .combo-product:hover .product-img img {
            transform: scale(1.08);
        }

        .combo-product .product-content {
            padding: 15px;
            text-align: center;
            background: #fff;
        }

        .combo-product .product-content h2 {
            font-size: 16px;
            margin-bottom: 10px;
            font-weight: 500;
            height: 40px;
            overflow: hidden;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
        }

        .combo-product .product-content h2 a {
            color: #333;
            text-decoration: none;
        }

        .combo-product .product-price {
            margin: 10px 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .combo-product .original-price {
            font-size: 16px;
            color: #999;
            text-decoration: line-through;
            margin-bottom: 5px;
        }

        .combo-product .discount-price {
            font-size: 18px;
            color: #80b435;
            font-weight: 600;
        }

        .combo-product .savings {
            font-size: 14px;
            color: #e74c3c;
            font-weight: 500;
            margin-top: 5px;
        }

        .combo-product .add-to-cart-btn {
            display: inline-block;
            padding: 8px 20px;
            background-color: #80b435;
            color: #fff;
            border-radius: 4px;
            font-weight: 500;
            transition: all 0.3s ease;
            border: none;
            cursor: pointer;
            margin-top: 10px;
            text-decoration: none;
        }

        .combo-product .add-to-cart-btn:hover {
            background-color: #6a9c2a;
        }

        .combo-product .view-details-btn {
            display: inline-block;
            padding: 8px 20px;
            background-color: #f8f9fa;
            color: #333;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-weight: 500;
            transition: all 0.3s ease;
            cursor: pointer;
            margin-top: 10px;
            margin-right: 5px;
            text-decoration: none;
        }

        .combo-product .view-details-btn:hover {
            background-color: #e9ecef;
        }

        .combo-badge {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: #e74c3c;
            color: white;
            padding: 5px 10px;
            border-radius: 3px;
            font-size: 12px;
            font-weight: bold;
            z-index: 1;
        }

        .page-title {
            text-align: center;
            margin-bottom: 30px;
        }

        .page-title h1 {
            font-size: 32px;
            color: #333;
            margin-bottom: 10px;
        }

        .page-title p {
            font-size: 16px;
            color: #666;
        }
    </style>

    <style>
        /* Remove old combo styles and add new coupon-like styles */
        .combo-product {
            border: 2px dashed #f78600;
            padding: 20px;
            margin-bottom: 30px;
            border-radius: 8px;
            background-color: #fff;
            transition: all 0.3s ease;
        }

        .combo-product:hover {
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            transform: translateY(-2px);
        }

        .combo-header {
            border-bottom: 1px solid #eee;
            padding-bottom: 15px;
            margin-bottom: 15px;
            text-align: center;
        }

        .combo-price {
            display: flex;
            justify-content: space-between;
            margin-top: 15px;
            font-size: 14px;
            color: #888;
        }

        .combo-badge {
            background-color: #e74c3c;
            color: white;
            padding: 5px 10px;
            border-radius: 3px;
            font-size: 12px;
            font-weight: bold;
        }

        /* Update product image styling */
        .product-img {
            width: 100%;
            height: 200px;
            position: relative;
            overflow: hidden;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 15px;
        }

        /* Modify buttons to match coupon style */
        .add-to-cart-btn {
            background-color: #f78600 !important;
            border: none !important;
            width: 100%;
            text-align: center;
        }

        .view-details-btn {
            background-color: #f8f9fa !important;
            border: 1px solid #ddd !important;
            color: #333 !important;
            width: 100%;
            margin-top: 10px !important;
        }
    </style>

    <style>
        /* Add these pagination styles */
        .pagination-area {
            display: flex;
            justify-content: center;
            margin-top: 40px;
        }

        .pagination-content {
            display: flex;
            list-style: none;
            padding: 0;
            margin: 0;
            gap: 5px;
        }

        .pagination-content li {
            display: inline-block;
        }

        .pagination-content li a {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 40px;
            height: 40px;
            border: 1px solid #ddd;
            color: #666;
            border-radius: 4px;
            text-decoration: none;
            transition: all 0.3s ease;
        }

        .pagination-content li.active a {
            background-color: #80b435;
            border-color: #80b435;
            color: #fff;
        }

        .pagination-content li a:hover:not(.active) {
            background-color: #f8f9fa;
            border-color: #80b435;
            color: #80b435;
        }

        .pagination-content li a i {
            font-size: 18px;
        }
    </style>

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

        <!-- Product Area -->
        <!--Shop Area Start-->
        <div class="shop-area mb-70">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="page-title">
                            <h1>Combo Sản Phẩm</h1>
                            <p>Tiết kiệm hơn với các combo sản phẩm đặc biệt của chúng tôi</p>
                        </div>

                        <!-- Add search form similar to coupons -->
                        <div class="coupon-search mb-5">
                            <form action="${pageContext.request.contextPath}/comboController" method="get">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="search" value="${searchTerm}" placeholder="Tìm kiếm combo...">
                                    <div class="input-group-append">
                                        <button class="btn btn-outline-secondary" type="submit">Tìm kiếm</button>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div class="row">
                            <c:forEach items="${combos}" var="combo">
                                <div class="col-md-6 col-lg-4">
                                    <div class="combo-product">
                                        <div class="combo-header">
                                            <span class="combo-badge">Tiết kiệm <fmt:formatNumber value="${combo.originalPrice - combo.discountPrice}"/>đ</span>
                                            <h2 style="margin-top: 15px;">
                                                <a href="${pageContext.request.contextPath}/comboController?action=details&id=${combo.comboId}">${combo.comboName}</a>
                                            </h2>
                                        </div>

                                        <div class="product-img">
                                            <img src="" alt="" class="product-image">
                                        </div>

                                        <div class="combo-price">
                                            <div>
                                                <del><fmt:formatNumber value="${combo.originalPrice}"/>đ</del>
                                                <div class="text-success" style="font-size: 1.2em; font-weight: bold;">
                                                    <fmt:formatNumber value="${combo.discountPrice}"/>đ
                                                </div>
                                            </div>
                                            <div class="product-action">
                                                <a href="${pageContext.request.contextPath}/comboController?action=details&id=${combo.comboId}" class="view-details-btn">Xem chi tiết</a>
                                                <a href="${pageContext.request.contextPath}/cart?action=add&comboId=${combo.comboId}&quantity=1" class="add-to-cart-btn">Thêm vào giỏ</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                            <c:if test="${empty combos}">
                                <div class="col-12 text-center">
                                    <p>Không có combo nào được tìm thấy.</p>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <!-- Replace the pagination HTML structure -->
                    <c:if test="${totalPages > 1}">
                        <div class="col-12">
                            <div class="pagination-area">
                                <ul class="pagination-content">
                                    <c:if test="${currentPage > 1}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/comboController?page=${currentPage - 1}${searchQueryString}">
                                                <i class="fa fa-angle-left"></i>
                                            </a>
                                        </li>
                                    </c:if>

                                    <c:forEach begin="${startPage}" end="${endPage}" var="i">
                                        <li class="${currentPage == i ? 'active' : ''}">
                                            <a href="${pageContext.request.contextPath}/comboController?page=${i}${searchQueryString}">${i}</a>
                                        </li>
                                    </c:forEach>

                                    <c:if test="${currentPage < totalPages}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/comboController?page=${currentPage + 1}${searchQueryString}">
                                                <i class="fa fa-angle-right"></i>
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <!--Shop Area End-->
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
        <script src="https://cdn.jsdelivr.net/npm/izitoast@1.4.0/dist/js/iziToast.min.js"></script>
        <c:if test="${not empty sessionScope.toastMessage}">
            <script>
                                                                    iziToast.${sessionScope.toastType}({
                                                                        title: '${sessionScope.toastType == "success" ? "Thành công" : "That bai"}',
                                                                        message: '${sessionScope.toastMessage}',
                                                                        position: 'topRight',
                                                                        timeout: 3000,
           color: '${sessionScope.toastType == "success" ? "green" : "red"}'
              });
            </script>
            <c:remove var="toastMessage" scope="session"/>
            <c:remove var="toastType" scope="session"/>
        </c:if>
        <style>
            .product-img {
                height: 250px;
                overflow: hidden;
                display: flex;
                justify-content: center;
                align-items: center;
                border-radius: 15px !important;
            }

            .product-img a {
                height: 100%;
                width: auto;
            }

            .product-img img {
                height: 100%;
                width: 100%;
                object-fit: cover;
                padding: 0px !important;
            }
            /*formSearch*/
            .formSearchByName{
                display:flex;
                margin-bottom: 25px

            }
            .formSearchByName .formSearch {
                border:1px solid #EAEDF2;
                flex: 1;
                outline:none;
                padding-left: 10px;
                border-top-left-radius: 10px;
                border-bottom-left-radius: 10px;
            }
            .formSearchByName button {
                border: none;
                color:white;
                font-size: 25px;
                text-align: center;
                background-color: #F78600;
                padding: 5px 16px;
                border-bottom-right-radius: 10px;
                border-top-right-radius: 10px;
            }
            /*End form search*/

            /*category*/
            .product-side-bar-widget {
                font-family: 'Segoe UI', sans-serif;
                color: #333;
            }

            .product-side-bar-widget .title {
                font-size: 20px;
                font-weight: 600;
                margin-bottom: 20px;
                color: #111;
                display: inline-block;
                padding-bottom: 4px;
            }

            .categoryList ul {
                list-style: none;
                padding: 0;
                margin: 0;
                border-radius: 10px;
                background-color: #fff;
                box-shadow: 0 1px 4px rgba(0, 0, 0, 0.06);
                overflow: hidden;
            }

            .categoryList li {
                display: flex;
                align-items: center;
                padding: 14px 16px;
                border-bottom: 1px solid #f0f0f0;
                font-size: 15px;
                transition: background 0.3s, color 0.3s;
            }

            .categoryList li:last-child {
                border-bottom: none;
            }

            .categoryList input[type="radio"] {
                margin-right: 10px;
                accent-color: black;
                transform: scale(1.1);
                cursor: pointer;
            }

            .categoryList label {
                cursor: pointer;
                flex: 1;
                color:black;
                user-select: none;
                font-weight: 600;
            }

            .categoryList li:hover {
                background-color: #fefce8;
            }

            .categoryList li.active label {
                font-weight: 600;
                color: black;
            }

            .categoryList li.active {
                background-color: #F78600;
            }


        </style>

        <script>
            //Xử lý price range 
            $(function () {
            <%
                    Double min_price = (Double) request.getAttribute("minPrice"); // ví dụ 50000
                    Double max_price = (Double) request.getAttribute("maxPrice"); // ví dụ 200000

                    Double min_price_default = (Double) request.getAttribute("minPriceDefault"); // ví dụ 0
                    Double max_price_default = (Double) request.getAttribute("maxPriceDefault"); // ví dụ 500000
%>

                // Đây là khoảng người dùng đã lọc
                const min = <%= min_price%>;
                const max = <%= max_price%>;

                // Đây là giới hạn full cho thanh trượt
                const minPrice = <%= min_price_default%>;
                const maxPrice = <%= max_price_default%>;

                $("#range-slider").slider({
                    range: true,
                    min: minPrice,
                    max: maxPrice,
                    values: [min, max],
                    slide: function (event, ui) {
                        const minForm = ui.values[0].toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                        const maxForm = ui.values[1].toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                        $("#price-amount").val(minForm + " - " + maxForm);

                        $("#selected-min").val(ui.values[0]);
                        $("#selected-max").val(ui.values[1]);
                    }
                });

                const initMin = $("#range-slider").slider("values", 0);
                const initMax = $("#range-slider").slider("values", 1);
                const initMinForm = initMin.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                const initMaxForm = initMax.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                $("#price-amount").val(initMinForm + " - " + initMaxForm);
                $("#selected-min").val(initMin);
                $("#selected-max").val(initMax);
            });

            //Xử lý goToPage 
            const goToPage = (page) => {
                // Lấy form
                const formSearch = document.querySelector("#formSearch");

                // Tạo URLSearchParams
                const params = new URLSearchParams();

                // Lấy foodName nếu có
                const foodNameInput = formSearch.querySelector('input[name="foodName"]');
                const foodName = foodNameInput ? foodNameInput.value.trim() : "";
                if (foodName !== "") {
                    params.append("foodName", foodName);
                }

                // Lấy min/max price nếu có
                const minInput = formSearch.querySelector('input[name="selectedMin"]');
                const maxInput = formSearch.querySelector('input[name="selectedMax"]');
                const minVal = minInput ? minInput.value.trim() : "";
                const maxVal = maxInput ? maxInput.value.trim() : "";
                if (minVal !== "") {
                    params.append("selectedMin", minVal);
                }
                if (maxVal !== "") {
                    params.append("selectedMax", maxVal);
                }

                //Lấy min/max calo

                const minInputCalo = formSearch.querySelector('input[name="selectedMinCalo"]');
                const maxInputCalo = formSearch.querySelector('input[name="selectedMaxCalo"]');
                const minValCalo = minInputCalo ? minInputCalo.value.trim() : "";
                const maxValCalo = maxInputCalo ? maxInputCalo.value.trim() : "";
                if (minValCalo !== "") {
                    params.append("selectedMinCalo", minValCalo);
                }
                if (maxValCalo !== "") {
                    params.append("selectedMaxCalo", maxValCalo);
                }

                // Lấy category nếu có radio được chọn
                const categoryInput = formSearch.querySelector('input[name="category"]:checked');
                const categoryVal = categoryInput ? categoryInput.value : "";
                if (categoryVal !== "") {
                    params.append("category", categoryVal);
                }

                // Thêm page
                params.append("page", page);

                // Gửi form bằng cách chuyển trang với query string đã build
                const baseUrl = formSearch.getAttribute("action");
                const fullUrl = baseUrl + "?" + params.toString();
                console.log(baseUrl);
                window.location.href = fullUrl;
            };

            //Xử lý submitSort
            const submitSort = (e) => {
                const sortType = e.value;
                console.log(sortType);

                // Lấy form
                const formSearch = document.querySelector("#formSearch");

                // Tạo URLSearchParams
                const params = new URLSearchParams();

                // Lấy foodName nếu có
                const foodNameInput = formSearch.querySelector('input[name="foodName"]');
                const foodName = foodNameInput ? foodNameInput.value.trim() : "";
                if (foodName !== "") {
                    params.append("foodName", foodName);
                }

                // Lấy min/max price nếu có
                const minInput = formSearch.querySelector('input[name="selectedMin"]');
                const maxInput = formSearch.querySelector('input[name="selectedMax"]');
                const minVal = minInput ? minInput.value.trim() : "";
                const maxVal = maxInput ? maxInput.value.trim() : "";
                if (minVal !== "") {
                    params.append("selectedMin", minVal);
                }
                if (maxVal !== "") {
                    params.append("selectedMax", maxVal);
                }

                //Lấy min/max calo

                const minInputCalo = formSearch.querySelector('input[name="selectedMinCalo"]');
                const maxInputCalo = formSearch.querySelector('input[name="selectedMaxCalo"]');
                const minValCalo = minInputCalo ? minInputCalo.value.trim() : "";
                const maxValCalo = maxInputCalo ? maxInputCalo.value.trim() : "";
                if (minValCalo !== "") {
                    params.append("selectedMinCalo", minValCalo);
                }
                if (maxValCalo !== "") {
                    params.append("selectedMaxCalo", maxValCalo);
                }

                // Lấy category nếu có radio được chọn
                const categoryInput = formSearch.querySelector('input[name="category"]:checked');
                const categoryVal = categoryInput ? categoryInput.value : "";
                if (categoryVal !== "") {
                    params.append("category", categoryVal);
                }
                //Add sort type
                params.append("sort", sortType);

                //Lấy fullForm + gửi
                const baseUrl = formSearch.getAttribute("action");
                const fullUrl = baseUrl + "?" + params.toString();
                console.log(baseUrl);
                window.location.href = fullUrl;

            }

            //Xử lý calo range
            $(function () {
            <%
                    Double min_calo = (Double) request.getAttribute("minCalo"); // ví dụ 50000
                    Double max_calo = (Double) request.getAttribute("maxCalo"); // ví dụ 200000

                    Double min_calo_default = (Double) request.getAttribute("minCaloDefault"); // ví dụ 0
                    Double max_calo_default = (Double) request.getAttribute("maxCaloDefault"); // ví dụ 500000
%>

                // Đây là khoảng người dùng đã lọc
                const min = <%= min_calo%>;
                const max = <%= max_calo%>;

                // Đây là giới hạn full cho thanh trượt
                const minCalo = <%= min_calo_default%>;
                const maxCalo = <%= max_calo_default%>;

                $("#calo-slider").slider({
                    range: true,
                    min: minCalo,
                    max: maxCalo,
                    values: [min, max],
                    slide: function (event, ui) {
                        const minForm = ui.values[0];
                        const maxForm = ui.values[1];
                        $("#calo-amount").val(minForm + " - " + maxForm);

                        $("#selected-min-calo").val(ui.values[0]);
                        $("#selected-max-calo").val(ui.values[1]);
                    }
                });

                const initMin = $("#calo-slider").slider("values", 0);
                const initMax = $("#calo-slider").slider("values", 1);
                $("#calo-amount").val(initMin + " - " + initMax);
                $("#selected-min-calo").val(initMin);
                $("#selected-max-calo").val(initMax);
            });
        </script>
    </body>

    <!-- Mirrored from templates.hibootstrap.com/hilo/default/shop-left-sidebar.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:18 GMT -->

</html>