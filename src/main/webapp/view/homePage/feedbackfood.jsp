<%-- Document : foodDetail Created on : Jun 12, 2025, 8:23:05 AM Author : Dell --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="zxx">

    <!-- Mirrored from templates.hibootstrap.com/hilo/default/shop-details.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:14:54 GMT -->

    <head>
        <!-- Required Meta Tags -->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            .rating-stars {
                display: inline-flex;
                align-items: center;
            }

            .rating-stars .fa-star {
                color: #ccc;
                /* màu mặc định là xám */
                font-size: 20px;
                margin-right: 2px;
            }

            .rating-stars .fas.fa-star {
                color: #f5c518;
                /* màu vàng cho sao đầy */
            }
        </style>

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

        <!--IzizToast-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>

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
                        <div class="col-lg-5 col-md-4">
                            <div class="inner-content">
                                <h2>Shop Details</h2>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                                <li>Shop Details</li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-lg-7 col-md-8">
                        <div class="inner-img">
                            <img src="${pageContext.request.contextPath}/images/inner-banner/inner-banner7.png" alt="Images">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Inner Banner Area End -->

        <!-- Product Details Area -->
        <div class="product-details-area pt-100 pb-70">
            <div class="container">
                <div class="row align-items-start">
                    <!--Image-->
                    <div class="col-lg-6 col-md-12 product-detail-image">
                        <div class="product-detls-image">
                            <img src="${foodDetail.getImage_url()}" alt="Image">
                        </div>
                    </div>
                    <!--feedback-->

                    <div class="col-lg-6 col-md-12" >
                        <h3><strong>Feedback Food</strong></h3>
                        <div class="feedback-section">
                            <div class="flex items-center justify-between flex-wrap" style="display: flex; align-items: center; justify-content: space-between; gap: 20px;">

                                <!-- Form filter rating -->
                                <div class="flex items-center justify-between gap10 flex-wrap">
                                    <div class="wg-filter flex-grow">
                                        <form action="feedbackfood" method="get">
                                            <input type="hidden" name="id" value="${feedbackFood}" />
                                            <div style="display: flex; align-items: center; gap: 10px;">
                                                <select name="rating" required>
                                                    <option value="all" ${param.rating=='all' ? 'selected' : '' }>-- All Rating --</option>
                                                    <option value="5" ${param.rating=='5' ? 'selected' : '' }>5</option>
                                                    <option value="4" ${param.rating=='4' ? 'selected' : '' }>4</option>
                                                    <option value="3" ${param.rating=='3' ? 'selected' : '' }>3</option>
                                                    <option value="2" ${param.rating=='2' ? 'selected' : '' }>2</option>
                                                    <option value="1" ${param.rating=='1' ? 'selected' : '' }>1</option>
                                                </select>
                                                <button type="submit" style="background: none; border: none; cursor: pointer;">
                                                    <i class="fas fa-search"></i>
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>


                                <!-- Average rating display -->
                                <div class="average-rating" style="white-space: nowrap;">
                                    <span>Average rating: 
                                        <fmt:formatNumber value="${averageRating}" type="number" maxFractionDigits="1" groupingUsed="false" />
                                        <i class="fas fa-star" style="color: gold;"></i>
                                    </span>
                                </div>
                            </div>

                            <div style="max-height: 400px; overflow-y: auto; border: 1px solid #ddd; padding: 10px;">
                                <c:forEach var="fb" items="${findFeedbackbyFoodId}">
                                    <div class="feedback-item" style="border: 1px solid #ccc; padding: 15px; margin-bottom: 15px;">
                                        <h3>
                                            <div class="rating-stars">
                                                <c:forEach begin="1" end="5" var="i">
                                                    <c:choose>
                                                        <c:when test="${i <= fb.rating}">
                                                            <i class="fas fa-star" style="color: gold;"></i>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <i class="far fa-star" style="color: gold;"></i>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </div>
                                        </h3>
                                        <p>${fb.content}</p>
                                        <small>-- ${accountDAO.findById(fb.user_id).user_name} --</small>
                                    </div>
                                </c:forEach>
                            </div>

                        </div>
                    </div>
                    <div class="d-flex justify-content-end">
                        <a class="tf-button style-1" href="${pageContext.request.contextPath}/shop?action=shopDetail&id=${feedbackFood}" style="color: black;">Back</a>
                    </div>                    <!--/feedback-->
                </div>
            </div>
        </div>
        <!-- Product Details Area End -->


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
            .product-detail-image {
                height: 500px;
            }

            .product-detail-image .product-detls-image {
                height: 100%;
                width: auto;
                overflow: hidden;
                border-radius: 10px;
            }

            .product-detail-image .product-detls-image img {
                height: 100%;
                width: 100%;
                object-fit: cover;
            }

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
                width: 100%;
                display: block;
            }

            .product-img img {
                height: 100%;
                width: 100%;
                object-fit: cover;
                padding: 0px !important;
                display: block;
            }
        </style>




    </body>

    <!-- Mirrored from templates.hibootstrap.com/hilo/default/shop-details.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:14:59 GMT -->

</html>