<%-- 
    Document   : foodDetail
    Created on : Jun 12, 2025, 8:23:05 AM
    Author     : Dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zxx">
    
<!-- Mirrored from templates.hibootstrap.com/hilo/default/shop-details.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:14:54 GMT -->
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
            <jsp:include page="../common/homePage/header.jsp"></jsp:include>

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
                                <li><a href="index.html">Home</a></li>
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
                <div class="row align-items-center">
                    <!--Image--> 
                    <div class="col-lg-6 col-md-12 product-detail-image">
                        <div class="product-detls-image">
                            <img src="${foodDetail.getImage_url()}" alt="Image">
                        </div>
                    </div>
                    
                    
                    <div class="col-lg-6 col-md-12">
                        <div class="product-desc">
                            <!--Name--> 
                            <h3>${foodDetail.getName()}</h3>
                            <!--Price--> 
                            <div class="price">
                                <span class="new-price" style="color:#F78600">
                                    Price : <fmt:formatNumber value="${foodDetail.getPrice()}" type="number" groupingUsed="true" maxFractionDigits="0"/> VNĐ
                                </span>     
                            </div>
                            
                            <!--Description--> 
                            <p>
                                ${foodDetail.getDescription()}
                            </p>
                            
                            <!--Quantity--> 
                            <div class="input-count-area">
                                <h3>Quantity</h3>
                                <div class="input-counter">
                                    <span class="minus-btn"><i class='bx bx-minus'></i></span>
                                    <input type="text" value="1">
                                    <span class="plus-btn"><i class='bx bx-plus'></i></span>
                                </div>
                            </div>
                            
                            <!--Order--> 
                            <div class="product-add-btn">
                                <button type="submit" class="default-btn btn-bg-three">
                                    <i class="fas fa-cart-plus"></i> Buy Now!
                                </button>
                                <button type="submit" class="default-btn btn-bg-three">
                                    <i class="fas fa-cart-plus"></i> Add To Cart
                                </button>
                            </div>
                            
                            <!--Socail media--> 
                            <div class="product-share">
                                <ul>
                                    <li>
                                        <span>Share:</span>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank">
                                            <i class='bx bxl-facebook' ></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank">
                                            <i class='bx bxl-linkedin'></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank">
                                            <i class='bx bxl-twitter'></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank">
                                            <i class='bx bxl-instagram'></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Product Details Area End -->
        
        <!-- Related Product Area -->
        <div class="related-products-area pb-70">
            <div class="container">
                <div class="section-title text-center">
                    <h2>Related Products</h2>
                </div>
                <div class="row pt-45">
                    <c:forEach items="${listRelated}" var="item">
                        <div class="col-lg-3 col-sm-6">
                            <div class="product-item">
                                <div class="product-img">
                                    <a href="shop-details.html">
                                        <img src="${pageContext.request.contextPath}/images/products/products-img1.png" alt="Product Images">
                                    </a>
                                    <div class="product-item-tag">
                                        <h3>New</h3>
                                    </div>
                                    <ul class="product-item-action">
                                        <li><a href="#"><i class='bx bx-repost'></i></a></li>
                                        <li><a href="wishlist.html"><i class='bx bx-heart'></i></a></li>
                                        <li><a href="cart.html"><i class='bx bx-cart'></i></a></li>
                                    </ul>
                                </div>

                                <div class="content">
                                    <h3><a href="shop-details.html">Organic Butter</a></h3>
                                    <div class="rating">
                                        <i class='bx bxs-star'></i>
                                        <i class='bx bxs-star'></i>
                                        <i class='bx bxs-star'></i>
                                        <i class='bx bxs-star'></i>
                                        <i class='bx bxs-star'></i>
                                    </div>
                                    <span>$12.0/Kg </span>
                                </div>
                            </div>   
                        </div>
                    </c:forEach>
                    
                </div>
            </div>
        </div>
        <!-- Related Product Area End -->

        <!-- Footer Area -->
        <jsp:include page="../common/homePage/footer.jsp"></jsp:include>
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
            .product-detail-image{
                height: 500px;
            }
            .product-detail-image .product-detls-image{
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
        </style>
    </body>

<!-- Mirrored from templates.hibootstrap.com/hilo/default/shop-details.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:14:59 GMT -->
</html>
