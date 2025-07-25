<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

    <!-- Mirrored from templates.hibootstrap.com/hilo/default/forget-password.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:14 GMT -->
    <head>
        <!-- Required Meta Tags -->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--IzizToast-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>

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
        <jsp:include page = "/view/common/homePage/headerUser.jsp"></jsp:include>
            <!-- End Navbar Area -->

            <!-- Inner Banner Area -->
            <div class="inner-banner-area">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-lg-5 col-md-4">
                            <div class="inner-content">
                                <h2>Forget Password</h2>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                                <li>Forget Password</li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-lg-7 col-md-8">
                        <div class="inner-img">
                            <img src="${pageContext.request.contextPath}/images/inner-banner/inner-banner6.png" alt="Images">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Inner Banner Area End -->

        <!-- User Area -->
        <div class="user-area pt-100 pb-70">
            <div class="container">
                <div class="user-width">
                    <div class="user-form">
                        <div class="contact-form">
                            <h2>Forget Password</h2>
                            <form action="forgetpassword" method="post">
                                <div class="row">
                                    <div class="col-lg-12 ">
                                        <div class="form-group">
                                            <input style="border-radius:30px" type="email" class="form-control" name="email" placeholder="Email">
                                        </div>
                                    </div>
                                    <p style="color:red">${toastMessage}</p>

                                    <div class="col-lg-12 ">
                                        <button type="submit" class="default-btn btn-bg-three" style="border-radius:30px">
                                            Reset Now
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- User Area End -->

        <!-- Footer Area -->
        <footer class="footer-area footer-bg">
            <div class="container">
                <div class="footer-top pt-100 pb-70">
                    <div class="row">
                        <div class="col-lg-3 col-md-6">
                            <div class="footer-widget footer-widget-color-2">
                                <div class="footer-logo">
                                    <a href="index.html">
                                        <img src="${pageContext.request.contextPath}/images/logos/footer-logo.png" class="footer-logo1" alt="Images">
                                        <img src="${pageContext.request.contextPath}/images/logos/logo-1.png" class="footer-logo2" alt="Images">
                                    </a>
                                </div>
                                <p>
                                    We are one of the best & quality full in market. Let's join.
                                </p>
                                <ul class="footer-list-contact">
                                    <li>
                                        <i class='bx bx-home'></i>
                                        <a href="#">Virgil A Stanton, Virginia, USA</a>
                                    </li>
                                    <li>
                                        <i class='bx bx-phone-call' ></i>
                                        <a href="tel:11234567890">+1 (123) 456 7890</a>
                                    </li>
                                    <li>
                                        <i class='bx bx-envelope'></i>
                                        <a href="https://templates.hibootstrap.com/cdn-cgi/l/email-protection#0b636e6767644b6362676425686466"><span class="__cf_email__" data-cfemail="1b737e7777745b7372777435787476">[email&#160;protected]</span></a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6">
                            <div class="footer-widget footer-widget-color-2">
                                <h3>Services</h3>
                                <ul class="footer-list">
                                    <li>
                                        <a href="wordpress-hosting.html" target="_blank">
                                            My Account
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="tracking-order.html" target="_blank">
                                            Tracking Order 
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="customer-services.html" target="_blank">
                                            Customer Services  
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="compare.html" target="_blank">
                                            Compare    
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="wishlist.html" target="_blank">
                                            Wishlist      
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="privacy-policy.html" target="_blank">
                                            Privacy Policy      
                                        </a>
                                    </li> 
                                </ul>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6">
                            <div class="footer-widget footer-widget-color-2">
                                <h3>Information</h3>
                                <ul class="footer-list">
                                    <li>
                                        <a href="index.html" target="_blank">
                                            Home
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="about.html" target="_blank">
                                            About Us 
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="blog-details.html" target="_blank">
                                            Blog Details  
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="shop-details.html" target="_blank">
                                            Shop Details    
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="testimonials.html" target="_blank">
                                            Testimonials      
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="team.html" target="_blank">
                                            Team      
                                        </a>
                                    </li> 
                                </ul>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6">
                            <div class="footer-widget footer-widget-color-2">
                                <h3>Follow Us</h3>
                                <p>We are one of the best & quality full  in market. Let's join.</p>
                                <form class="footer-form-area">
                                    <input type="email" class="form-control" placeholder="Email">
                                    <button class="subscribe-btn" type="submit">
                                        <i class='bx bx-paper-plane'></i>
                                    </button>
                                </form>

                                <ul class="social-link">
                                    <li>
                                        <a href="https://www.facebook.com/" target="_blank">
                                            <i class='bx bxl-facebook'></i>
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="https://www.twitter.com/" target="_blank">
                                            <i class='bx bxl-twitter'></i>
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="https://www.instagram.com/" target="_blank">
                                            <i class='bx bxl-instagram'></i>
                                        </a>
                                    </li> 
                                    <li>
                                        <a href="https://www.youtube.com/" target="_blank">
                                            <i class='bx bxl-youtube'></i>
                                        </a>
                                    </li> 
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Footer Area -->
                    <jsp:include page="../common/homePage/footerUser.jsp"></jsp:include>
                <!-- Footer Area End -->
            </div>
        </footer>
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

        <!--Thông báo lỗi đăng nhập--> 

        <c:if test="${toastType == 'error'}">
            <script>
                                document.addEventListener("DOMContentLoaded", function () {
                                    iziToast.error({
                                        title: "Thông báo",
                                        message: '${sessionScope.toastMessage}',
                                        position: 'topRight',
                                        timeout: 5000,
                                        backgroundColor: "#E53E31"
                                    });
                                });
            </script>
            <!--Xóa đi biến isDelete sau khi đã thông báo--> 
            <%
                session.removeAttribute("toastType");
                session.removeAttribute("toastMessage");
            %>
        </c:if>
    </body>

    <!-- Mirrored from templates.hibootstrap.com/hilo/default/forget-password.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:14 GMT -->
</html>