<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zxx">

    <!-- Mirrored from templates.hibootstrap.com/hilo/default/index-3.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:14:45 GMT -->
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
                    <h2>GreenBite</h2>
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

            <!-- Banner Area Two -->
            <div class="banner-area-two">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-lg-5">
                            <div class="banner-content-2">
                                <span><b>Get 35% Discount,</b> Every Product </span>
                                <h2>Quality Is the First Not Quantity</h2>
                                <p>Lorem ipsum dolor sit amet, consetetur sadipscins elitr, seddiams nonumy eirmod.</p>
                                <a href="#" class="default-btn btn-bg-three border-radius-5">Shop Now</a>
                            </div>
                        </div>

                        <div class="col-lg-7">
                            <div class="banner-img-2">
                                <img src="${pageContext.request.contextPath}/images/home-three.png" alt="Banner Images">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Banner Area Two End -->

        <!-- Brand Area -->
        <div class="brand-area pt-100">
            <div class="container">
                <div class="brand-slider owl-carousel owl-theme">
                    <div class="brand-item">
                        <img src="${pageContext.request.contextPath}/images/brand-logo/brand-logo1.png" alt="Images">
                    </div>
                    <div class="brand-item">
                        <img src="${pageContext.request.contextPath}/images/brand-logo/brand-logo2.png" alt="Images">
                    </div>
                    <div class="brand-item">
                        <img src="${pageContext.request.contextPath}/images/brand-logo/brand-logo3.png" alt="Images">
                    </div>
                    <div class="brand-item">
                        <img src="${pageContext.request.contextPath}/images/brand-logo/brand-logo4.png" alt="Images">
                    </div>
                    <div class="brand-item">
                        <img src="${pageContext.request.contextPath}/images/brand-logo/brand-logo5.png" alt="Images">
                    </div>
                    <div class="brand-item">
                        <img src="${pageContext.request.contextPath}/images/brand-logo/brand-logo2.png" alt="Images">
                    </div>
                </div>
            </div>
        </div>
        <!-- Brand Area End -->

        <!-- Offer Style Area -->
        <div class="offer-style-area pt-100 pb-70">
            <div class="container">
                <div class="row">
                    <div class="col-lg-7">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="offer-style-item">
                                    <div class="line"></div>
                                    <span>Get 35% Discount</span>
                                    <h3>Natural Fresh Fruits</h3>
                                    <img src="${pageContext.request.contextPath}/images/offer-img/offer-style-1.png" alt="Images">
                                </div>
                            </div>

                            <div class="col-lg-5">
                                <div class="offer-style-item-2">
                                    <div class="line"></div>
                                    <h3>Fruits Collection</h3>
                                    <img src="${pageContext.request.contextPath}/images/offer-img/offer-style-2.png" alt="Images">
                                </div>
                            </div>

                            <div class="col-lg-7">
                                <div class="offer-style-item-3">
                                    <img src="${pageContext.request.contextPath}/images/offer-img/offer-style-3.png" alt="Images">
                                    <div class="line"></div>
                                    <h3>Vintage Capsicum</h3>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-5">
                        <div class="offer-style-item-4">
                            <div class="line"></div>
                            <h3>Fruits Collection</h3>
                            <img src="${pageContext.request.contextPath}/images/offer-img/offer-style-4.png" alt="Images">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Offer Style Area End -->
        <!-- Choose Area Two End -->
        <div class="choose-area-three pt-100 pb-70">
            <div class="container">
                <div class="section-title text-center">
                    <h2>Reason of Choice to Us</h2>
                </div>

                <div class="row pt-45 align-items-center">
                    <div class="col-lg-7">
                        <div class="choose-img">
                            <img src="${pageContext.request.contextPath}/images/choose-img.jpg" alt="Images">
                        </div>
                    </div>

                    <div class="col-lg-5">
                        <div class="row">
                            <div class="col-lg-12 col-sm-6">
                                <div class="choose-item">
                                    <i class="flaticon-24-hours"></i>
                                    <h3>24/7 Online Support</h3>
                                    <p>Lorem ipsum dolor sit amet, co nsetetur sadipscing elitr, sed di am nonumy eirmod thoad sid dwsd.</p>
                                </div>
                            </div>

                            <div class="col-lg-12 col-sm-6">
                                <div class="choose-item">
                                    <i class="flaticon-leaf"></i>
                                    <h3>100% Pure Foods</h3>
                                    <p>Lorem ipsum dolor sit amet, co nsetetur sadipscing elitr, sed di am nonumy eirmod thoad sid dwsd.</p>
                                </div>
                            </div>

                            <div class="col-lg-12 col-sm-6 offset-lg-0 offset-sm-3">
                                <div class="choose-item">
                                    <i class="flaticon-service"></i>
                                    <h3>Home Delivery</h3>
                                    <p>Lorem ipsum dolor sit amet, co nsetetur sadipscing elitr, sed di am nonumy eirmod thoad sid dwsd.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Choose Area Two End -->

        <!-- Newsletter Area Section -->
        <div class="newsletter-area-section-3 newsletter-area-section-bg pt-100 pb-70">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-6">
                        <div class="section-title">
                            <span>Get 35% Discount, <b> Subscribe Now</b></span>
                            <h2>Subscribe to Our Newsletters</h2>
                            <p>
                                Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam 
                                nonumy eirmod tempor invidunt ut labore et dolore magna aliquy 
                                erat, sed diam voluptua. At vero eos et.
                            </p>
                        </div>

                        <div class="newsletter-area-two newsletter-area-three">
                            <form class="newsletter-form" data-toggle="validator" method="POST">
                                <input type="email" class="form-control" placeholder="Enter your email" name="EMAIL" required autocomplete="off">
                                <button class="subscribe-btn" type="submit">
                                    Subscribe
                                </button>
                                <div id="validator-newsletter" class="form-result"></div>
                            </form>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="newsletter-img">
                            <img src="${pageContext.request.contextPath}/images/newsletter-img.jpg" alt="Images">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Newsletter Area Section End -->

        <!-- Blog Area -->
        <section class="blog-area pt-100 pb-70">
            <div class="container">
                <div class="section-title text-center">
                    <h2>Our Latest Blogs</h2>
                </div>

                <div class="row pt-45">
                    <div class="col-lg-4 col-md-6">
                        <div class="blog-card blog-color-2">
                            <a href="blog-details.html">
                                <img src="${pageContext.request.contextPath}/images/blog/blog-img1.jpg" alt="Images">
                            </a>
                            <div class="content">
                                <span><i class='bx bx-time-five'></i> Nov 01, 2024</span>
                                <h3><a href="blog-details.html">We Always Ready to Give You Best Delivery Support </a></h3>
                                <p>
                                    Lorem ipsum dolor sit amet, consetetur exit sadipscing elitr, sed diam.
                                </p>
                                <a href="#" class="read-btn">Read More</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-4 col-md-6">
                        <div class="blog-card blog-color-2">
                            <a href="blog-details.html">
                                <img src="${pageContext.request.contextPath}/images/blog/blog-img2.jpg" alt="Images">
                            </a>
                            <div class="content">
                                <span><i class='bx bx-time-five'></i> Nov 05, 2024</span>
                                <h3><a href="blog-details.html">Click  and Make the Payment in the Most Easiest Way</a></h3>
                                <p>
                                    Lorem ipsum dolor sit amet, consetetur exit sadipscing elitr, sed diam.
                                </p>
                                <a href="#" class="read-btn">Read More</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-4 col-md-6 offset-lg-0 offset-md-3">
                        <div class="blog-card blog-color-2">
                            <a href="blog-details.html">
                                <img src="${pageContext.request.contextPath}/images/blog/blog-img3.jpg" alt="Images">
                            </a>
                            <div class="content">
                                <span><i class='bx bx-time-five'></i> Nov 07, 2024</span>
                                <h3><a href="blog-details.html">Global Ecommerce System Marked as a History </a></h3>
                                <p>
                                    Lorem ipsum dolor sit amet, consetetur exit sadipscing elitr, sed diam.
                                </p>
                                <a href="#" class="read-btn">Read More</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Blog Area End -->

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

    <!-- Mirrored from templates.hibootstrap.com/hilo/default/index-3.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:14:50 GMT -->
</html>