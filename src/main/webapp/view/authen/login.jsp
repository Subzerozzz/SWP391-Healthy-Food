<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

    <!-- Mirrored from templates.hibootstrap.com/hilo/default/log-in.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:14:24 GMT -->
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
    <style>
        .btn-google {
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #ffffff;
            color: #444;
            border: 1px solid #ddd;
            padding: 12px 20px;
            border-radius: 50px;
            font-size: 16px;
            font-weight: 500;
            transition: all 0.3s ease;
            text-decoration: none;
            margin-top: 20px;
        }

        .btn-google:hover {
            background-color: #f7f7f7;
            border-color: #bbb;
            text-decoration: none;
        }

        .btn-google i {
            margin-right: 10px;
            font-size: 20px;
            color: #db4437; /* Google red */
        }

        .btn-google span {
            display: inline-block;
            color: #555;
        }
    </style>

    <body>
        <!--commnent to check--> 
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
                                <h2> Log In</h2>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                                <li> Log In</li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-lg-7 col-md-8">
                        <div class="inner-img">
                            <img src="${pageContext.request.contextPath}/images/inner-banner/inner-banner4.png" alt="Images">
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
                            <h2>Log In</h2>
                            <form action="login" method="post">
                                <div class="row">
                                    <div class="col-lg-12 ">
                                        <div class="form-group">
                                            <input style="border-radius:30px" type="text" class="form-control" name="username" placeholder="Username or Email">

                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <div class="form-group">
                                            <input style="border-radius:30px" class="form-control" type="password" name="password"  placeholder="Password">
                                        </div>
                                    </div>
                                    <p style="color:red">${toastMessage}</p>
                                    <div class="col-lg-12 form-condition">
                                        <div class="agree-label">
                                            <div>
                                                <a style="color: #F78600" href="${pageContext.request.contextPath}/register">Register</a>
                                                <a style="color: #F78600" class="forget" href="${pageContext.request.contextPath}/forgetpassword">Forgot My Password?</a>
                                            </div>


                                        </div>
                                    </div>

                                    <div class="col-lg-12 ">
                                        <button style="border-radius:30px" type="submit" class="default-btn btn-bg-three">
                                            Log In Now
                                        </button>


                                        <div class="text-tiny mb-16 text-center">Or continue with social account</div>
                                         <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:9999/SWP391-Healthy-Food/LoginGoogleHandler&response_type=code&client_id=603741791751-77ab6u1qf89i36uqfudjqc08agjs9obm.apps.googleusercontent.com&approval_prompt=force" class="btn-google">
                                            <i class="fa-brands fa-google"></i>
                                            <span>Sign in with Google</span>
                                        </a>
                                    </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- User Area End   -->

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

    <!-- Mirrored from templates.hibootstrap.com/hilo/default/log-in.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:14:24 GMT -->
</html>