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

        <!-- Product Area -->
        <div class="product-area pt-100 pb-70">
            <div class="container">
                <form action="${pageContext.request.contextPath}/shop" id='formSearch'>
                    <!-- gán 1 ô input để luôn luôn có param sort trên request-->
                    <input type="hidden" name="sort" value="${sort}">
                    <div class="row">
                        <div class="col-lg-3">
                            <div class="product-side-bar">

                                <!--Search By Name-->
                                <div class="search-widget formSearchByName">
                                    <input name="foodName" type="search" class="formSearch" placeholder="Search by food name..." value="${foodName != null ? foodName : ''}">               
                                    <button type="submit">
                                        <i class="bx bx-search"></i>
                                    </button>
                                </div>

                                <!--Price Range-->
                                <div class="product-side-bar-widget">
                                    <h3 class="title">Prices Range</h3>
                                    <div class="price-range-bar" id="range-slider"></div>
                                    <div class="price-range-filter">
                                        <div class="price-range-filter-item d-flex align-items-center order-1 order-xl-2">
                                            <h4>Range:</h4>
                                            <input type="text" id="price-amount" readonly>
                                            <input type="hidden" name="selectedMin" id="selected-min">
                                            <input type="hidden" name="selectedMax" id="selected-max">
                                        </div>

                                        <div class="price-range-filter-item price-range-filter-button order-2 order-xl-1">
                                            <button class="btn btn-red btn-icon" onclick="document.querySelector('#formSearch').submit()">Filter</button>
                                        </div>
                                    </div>
                                </div>
                                
                                <!--Calo--> 
                                <div class="product-side-bar-widget">
                                    <h3 class="title">Calories range</h3>
                                    <div class="price-range-bar" id="calo-slider"></div>
                                    <div class="price-range-filter">
                                        <div class="price-range-filter-item d-flex align-items-center order-1 order-xl-2">
                                            <h4>Range:</h4>
                                            <input type="text" id="calo-amount" readonly>
                                            <input type="hidden" name="selectedMinCalo" id="selected-min-calo">
                                            <input type="hidden" name="selectedMaxCalo" id="selected-max-calo">
                                        </div>

                                        <div class="price-range-filter-item price-range-filter-button order-2 order-xl-1">
                                            <button class="btn btn-red btn-icon" onclick="document.querySelector('#formSearch').submit()">Filter</button>
                                        </div>
                                    </div>
                                </div>

                                <!--Food Categories-->
                                <div class="product-side-bar-widget">
                                    <h3 class="title">Product Categories</h3>
                                    <div class="categoryList">
                                        <ul>
                                            <li class="${category == 0 ? 'active': ''}">
                                                <input ${category == 0 ? 'checked': ''} type="radio" value='0' name='category' id='cate_0' onclick="document.querySelector('#formSearch').submit()">
                                                <label for='cate_0'>Tất cả sản phẩm</label>
                                            </li>
                                            <c:forEach items="${listFoodCategory}" var="item">
                                                <li class="${category == item.getId()?'active' : ''}">
                                                    <input ${category == item.getId()? 'checked' : ''} type="radio" value='${item.getId()}' name='category' id='cate_${item.getId()}' onclick="document.querySelector('#formSearch').submit()">
                                                    <label  for='cate_${item.getId()}'>${item.getName()}</label>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>


                            </div>
                        </div>
                        <div class="col-lg-9">
                            <!--Sort-->
                            <div class="product-topper">
                                <div class="row">
                                    <div class="col-lg-8 col-md-8">
                                        <div class="product-topper-title">
                                            <h3>Food List <span>( Showing 9 of ${totalOfRecord} result )</span> </h3>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4">
                                        <div class="product-category">
                                            <div class="form-group">
                                                <select class="form-control" onchange="submitSort(this)">
                                                    <option value="default" ${not empty sort and sort == 'default' ? 'selected' : ''}>Default sort</option>
                                                    <option value="price_asc" ${not empty sort and sort == 'price_asc' ? 'selected' : ''}>Price: Low to high</option>
                                                    <option value="price_desc" ${not empty sort and sort == 'price_desc' ? 'selected' : ''}>Price: High to low</option>
                                                    <option value="calo_asc" ${not empty sort and sort == 'calo_asc' ? 'selected' : ''}>Calories: Low to high</option>
                                                    <option value="calo_desc" ${not empty sort and sort == 'calo_desc' ? 'selected' : ''}>Calories: High to low</option>
                                                </select>
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!--Food list-->
                            <div class="row">
                                <c:forEach items="${listFood}" var="item">
                                    <div class="col-lg-4 col-sm-6">
                                        <div class="product-item">
                                            <div class="product-img">
                                                <a href="${pageContext.request.contextPath}/shop?action=shopDetail&id=${item.getId()}">
                                                    <img src="${item.getImage_url()}" alt="Product Images">
                                                </a>
                                                <ul class="product-item-action">
                                                    <li><a href="#"><i class='bx bx-repost'></i></a></li>
                                                    <li><a href="wishlist.html"><i class='bx bx-heart'></i></a></li>
                                                    <li><a href="cart.html"><i class='bx bx-cart'></i></a></li>
                                                </ul>
                                            </div>

                                            <div class="content">
                                                <h3><a href="${pageContext.request.contextPath}/shop?action=shopDetail&id=${item.getId()}">${item.getName()}</a></h3>
                                                <div>
                                                    Calo: ${item.getCalo()}
                                                </div>
                                                <span>
                                                    <fmt:formatNumber value="${item.getPrice()}" type="number" groupingUsed="true"
                                                                      maxFractionDigits="0" /> VNĐ
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>

                            <!--Pagination-->
                            <div class="col-lg-12 col-md-12 text-center">
                                <c:choose>
                                    <c:when test= "${totalPage > 0}">
                                        <div class="pagination-area">
                                            <a href="javascript:void(0)" onclick="goToPage(1)" class="prev page-numbers">
                                                <i class="bx bx-chevrons-left"></i>
                                            </a>

                                            <c:choose>
                                                <c:when test="${currentPage <= totalPage - 2}">
                                                    <c:if test="${currentPage > 1}">
                                                        <a href="javascript:void(0)" onclick="goToPage(${currentPage - 1})"
                                                           class="page-numbers">${currentPage - 1}</a>
                                                    </c:if>
                                                    <a href="javascript:void(0)" onclick="goToPage(${currentPage})"
                                                       class="page-numbers current">${currentPage}</a>

                                                    <a href="javascript:void(0)" onclick="goToPage(${currentPage + 1})"
                                                       class="page-numbers">${currentPage + 1}</a>

                                                    <c:if test="${currentPage < totalPage - 2}">
                                                        <a href="#" class="page-numbers">...</a>
                                                    </c:if>

                                                    <a href="javascript:void(0)" onclick="goToPage(${totalPage})"
                                                       class="page-numbers">${totalPage}</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <!--Cái này không thể kiểm tra từ currentPage vì nếu không thì nếu currentPage bằng 2 3 chẳng hạn thì nó mất trang 1--> 
                                                    <c:forEach begin="${totalPage-2 <= 0 ? 1 : totalPage - 2}" end="${totalPage}" var="i">
                                                        <a href="javascript:void(0)" onclick="goToPage(${i})"
                                                           class="page-numbers ${currentPage == i?'current' : ''}">${i}</a>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>

                                            <a href="javascript:void(0)" onclick="goToPage(${totalPage})" class="next page-numbers">
                                                <i class="bx bx-chevrons-right"></i>
                                            </a>

                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div style="font-size: 24px">Product not found</div>
                                    </c:otherwise>
                                </c:choose>
                                

                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
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
                const min = <%= min_price %>;
                const max = <%= max_price %>;

                // Đây là giới hạn full cho thanh trượt
                const minPrice = <%= min_price_default %>;
                const maxPrice = <%= max_price_default %>;

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
                const min = <%= min_calo %>;
                const max = <%= max_calo %>;

                // Đây là giới hạn full cho thanh trượt
                const minCalo = <%= min_calo_default %>;
                const maxCalo = <%= max_calo_default %>;

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