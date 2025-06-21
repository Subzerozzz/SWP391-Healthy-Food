<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="zxx">
    
<!-- Mirrored from templates.hibootstrap.com/hilo/default/checkout.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:09 GMT -->
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
        <style>
            .order-table {
                width: 100%;
                border-collapse: collapse;
                background-color: #fff;
                border-radius: 16px;
                box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
                font-family: 'Segoe UI', sans-serif;
                font-size: 16px; /* Cỡ chữ lớn hơn */
                overflow: hidden;
                margin-bottom: 10px
              }

              .order-table th,
              .order-table td {
                padding: 18px 25px; 
                text-align: left;
                border-bottom: none;
                font-size: 18px
              }

              .order-table thead th {
                font-weight: 600;
                background-color: #f9f9f9;
                color: #111;
                font-size: 20px
              }

              .order-table tbody tr {
                background-color: #fff;
                border-bottom: 2px solid #F5F5F5;
              }

              .order-table tfoot tr {
                background-color: #fff;
                font-weight: 600;
              }

              .order-table .order-total th,
              .order-table .order-total td {
                color: #ff6600;
                font-weight: bold;
                font-size: 20px;
              }

              /* Responsive tweak nếu cần */
              @media (max-width: 768px) {
                .order-table th,
                .order-table td {
                  padding: 14px 12px;
                  font-size: 15px;
                }
              }


        </style>
        
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
                            <h2>Checkout </h2>
                            <ul>
                                <li><a href="index.html">Home</a></li>
                                <li>Checkout</li>
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

        <!-- Checkout Area -->
        <div class="checkout-area pt-100 pb-70">
            <div class="container">
                <div class="row align-items-center">
                    <form id="contactForm" class="row">
                        <c:choose>
                            <c:when test="${sessionScope.account == null}">
                                <div class="col-lg-6">
                                    <div class="checkout-form">
                                        <div class="contact-form">
                                            <h2>Billing Information</h2>
                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label>First Name</label>
                                                            <input type="text"  class="form-control" placeholder=" First Name">
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label>Last Name</label>
                                                            <input type="text"  class="form-control" placeholder="Last Name">
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-12">
                                                        <div class="form-group">
                                                            <label>Email Address</label>
                                                            <input type="email" class="form-control"  placeholder="Email Address">
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-12">
                                                        <div class="form-group">
                                                            <label>Phone Number</label>
                                                            <input type="text" class="form-control" placeholder="Phone">
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-12">
                                                        <div class="form-group">
                                                            <label>Address</label>
                                                            <input type="text" class="form-control" placeholder="Address">
                                                            <span>Bạn có thể chỉnh sửa địa chị chi tiết nếu cần.</span>
                                                        </div>
                                                    </div>

                                                    <div class="payment-method-section">
                                                        <h4 class="mb-3">Payment Method</h4>

                                                        <!-- Cash on Delivery -->
                                                        <div class="payment-option">
                                                            <div class="payment-label">
                                                                <input type="radio" id="cash-on-delivery" name="paymentMethod" value="cod" checked="">
                                                                <div class="payment-icon">
                                                                    <iconify-icon icon="uil:money-bill" width="24" height="24" style="color: #80b82d;"></iconify-icon>
                                                                </div>
                                                                <label for="cash-on-delivery">Cash On Delivery</label>
                                                            </div>
                                                            <div class="payment-details">
                                                                Pay with cash upon delivery. Please have the exact amount ready for our delivery personnel.
                                                            </div>
                                                        </div>

                                                        <!-- VNPay -->
                                                        <div class="payment-option active">
                                                            <div class="payment-label">
                                                                <input type="radio" name="paymentMethod" id="bank-transfer" value="vnpay">
                                                                <div class="payment-icon">
                                                                    <iconify-icon icon="mdi:bank-outline" width="24" height="24" style="color: #80b82d;"></iconify-icon>
                                                                </div>
                                                                <label for="bank-transfer">VNPAY</label>
                                                            </div>
                                                            <div class="payment-details">
                                                                Make your payment directly into our bank account. Please use your Order ID as the payment reference. Your order won't be processed until the funds have cleared in our account.
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-12 col-md-12">
                                                        <button type="submit" class="default-btn btn-bg-three">
                                                            Send Message
                                                        </button>
                                                    </div>
                                                </div>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:set var="acc" value="${sessionScope.account}"/>
                                <div class="col-lg-6">
                                    <div class="checkout-form">
                                        <div class="contact-form">
                                            <h2>Billing Information</h2>
                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label>Full Name</label>
                                                            <input type="text"  class="form-control" placeholder=" First Name" value="${acc.getFull_name()}">
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label>User Name</label>
                                                            <input type="text"  class="form-control" placeholder="Last Name" value="${acc.getUser_name()}">
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-12">
                                                        <div class="form-group">
                                                            <label>Email Address</label>
                                                            <input type="email" class="form-control"  placeholder="Email Address" value="${acc.getEmail()}">
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-12">
                                                        <div class="form-group">
                                                            <label>Phone Number</label>
                                                            <input type="text" class="form-control" placeholder="Phone" value="${acc.getMobile()}">
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-12">
                                                        <div class="form-group">
                                                            <label>Address</label>
                                                            <input type="text" class="form-control" placeholder="Address" value="${acc.getAddress()}">
                                                            <span>Bạn có thể chỉnh sửa địa chị chi tiết nếu cần.</span>
                                                        </div>
                                                    </div>
                                                    
                                                    <!--payment--> 
                                                    <div class="payment-method-section">
                                                        <h4 class="mb-3">Payment Method</h4>

                                                        <!-- Cash on Delivery -->
                                                        <div class="payment-option">
                                                            <div class="payment-label">
                                                                <input type="radio" id="cash-on-delivery" name="paymentMethod" value="cod" checked="">
                                                                <div class="payment-icon">
                                                                    <iconify-icon icon="uil:money-bill" width="24" height="24" style="color: #80b82d;"></iconify-icon>
                                                                </div>
                                                                <label for="cash-on-delivery">Cash On Delivery</label>
                                                            </div>
                                                            <div class="payment-details">
                                                                Pay with cash upon delivery. Please have the exact amount ready for our delivery personnel.
                                                            </div>
                                                        </div>

                                                        <!-- VNPay -->
                                                        <div class="payment-option active">
                                                            <div class="payment-label">
                                                                <input type="radio" name="paymentMethod" id="bank-transfer" value="vnpay">
                                                                <div class="payment-icon">
                                                                    <iconify-icon icon="mdi:bank-outline" width="24" height="24" style="color: #80b82d;"></iconify-icon>
                                                                </div>
                                                                <label for="bank-transfer">VNPAY</label>
                                                            </div>
                                                            <div class="payment-details">
                                                                Make your payment directly into our bank account. Please use your Order ID as the payment reference. Your order won't be processed until the funds have cleared in our account.
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                        </div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        

                        <div class="col-lg-6">
                            <div class="checkout-form">
                                <div class="contact-form">
                                    <h2>Your order</h2>
                                    <table class="order-table">
                                        <thead>
                                            <tr>
                                                <th>Product</th>
                                                <th style="text-align: right;">Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listCartItem}" var="item">
                                                <c:set var="food" value="${foodMap[item.getFood_id()]}" />
                                                    <tr>
                                                        <td>${food.getName()} × ${item.getQuantity()}</td>
                                                        <td style="text-align: right;">
                                                            <fmt:formatNumber value="${food.getPrice() * item.getQuantity()}" type="number" groupingUsed="true"
                                                                maxFractionDigits="0" /> VNĐ</td>
                                                    </tr>
                                            </c:forEach>
                                            
                                            
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Subtotal</th>
                                                <td style="text-align: right;">
                                                    <fmt:formatNumber value="${subTotal}" type="number" groupingUsed="true"
                                                                maxFractionDigits="0" /> VNĐ</td>
                                            </tr>
                                            
                                            <tr>
                                                <th>Shipping</th>
                                                <td style="text-align: right;">Free</td>
                                            </tr>
                                            <tr class="order-total">
                                                <th>Total</th>
                                                <td style="text-align: right;">
                                                    <fmt:formatNumber value="${totalPrice}" type="number" groupingUsed="true"
                                                                maxFractionDigits="0" /> VNĐ
                                                </td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                    
                                    <div class="col-lg-12 col-md-12">
                                        <button type="submit" class="default-btn btn-bg-three">
                                            Place Order
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Checkout Area End -->

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

<!-- Mirrored from templates.hibootstrap.com/hilo/default/checkout.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:09 GMT -->
</html>
