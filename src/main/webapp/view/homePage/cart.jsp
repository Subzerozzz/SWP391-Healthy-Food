<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <!DOCTYPE html>
      <html lang="zxx">

      <!-- Mirrored from templates.hibootstrap.com/hilo/default/cart.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:08 GMT -->

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
              <div class="col-lg-5 col-md-4">
                <div class="inner-content">
                  <h2>Cart</h2>
                  <ul>
                    <li><a href="index.html">Home</a></li>
                    <li>Cart</li>
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

        <!-- Start Cart Area -->
        <section class="cart-wraps-area ptb-100">
          <div class="container">
            <div class="row">
              <div class="col-lg-12 col-md-12">
                  <c:choose>
                    <c:when test="${listCartItem.size() == 0}">
                      <div>Chưa có sản phẩm nào trong giỏ hàng !</div>
                      <div>Vui lòng thêm món ăn vào giỏ hàng.</div>
                    </c:when>
                    <c:otherwise>
                        <form id="cartForm" action="${pageContext.request.contextPath}/cart" method="POST">
                            <div class="cart-wraps">
                                <div class="cart-table table-responsive">
                                  <table class="table table-bordered">
                                    <thead>
                                      <tr>
                                        <th scope="col">Product</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Unit Price</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Total</th>
                                      </tr>
                                    </thead>

                                    <tbody>
                                      <c:forEach items="${listCartItem}" var="item">

                                        <c:set var="food" value="${foodMap[item.getFood_id()]}" />
                                        <tr>
                                          <td class="product-thumbnail">
                                            <a href="#">
                                              <img src="${food.getImage_url()}" alt="Image">
                                            </a>
                                          </td>

                                          <td class="product-name">
                                            <a href="#">${food.getName()}</a>
                                          </td>

                                          <td class="product-price">
                                            <span class="unit-amount">
                                              <fmt:formatNumber value="${food.getPrice()}" type="number" groupingUsed="true"
                                                maxFractionDigits="0" /> VNĐ
                                            </span>
                                          </td>

                                          <td class="product-quantity">
                                            <div class="input-counter">
                                              <span class="minus-btn">
                                                <i class='bx bx-minus'></i>
                                              </span>
                                              <input name="quantity" type="text" value="${item.getQuantity()}" min="1">
                                              
                                              <span class="plus-btn">
                                                <i class='bx bx-plus'></i>
                                              </span>
                                            </div>
                                          </td>
                                          
                                          <input type="hidden" name="foodId" value="${item.getFood_id()}"/>

                                          <td class="product-subtotal">
                                            <span class="subtotal-amount">
                                              <fmt:formatNumber value="${food.getPrice() * item.getQuantity()}" type="number"
                                                groupingUsed="true" maxFractionDigits="0" /> VNĐ
                                            </span>

                                            <a href="#" onclick="submitDelete(${item.getFood_id()})" class="remove">
                                              <i class='bx bx-trash'></i>
                                            </a>
                                          </td>
                                        </tr>
                                      </c:forEach>
                                    </tbody>
                                  </table>
                                </div>

                                <div class="cart-buttons">
                                  <div class="row align-items-center">
                                    <div class="col-lg-7 col-sm-7 col-md-7">
                                      <div class="continue-shopping-box">
                                        <a href="${pageContext.request.contextPath}/shop" class="default-btn btn-bg-three">
                                          Continue Shopping
                                        </a>
                                      </div>
                                    </div>

                                    <div class="col-lg-5 col-sm-5 col-md-5 text-end">
                                        <a href="#" onclick="submitUpdate()" 
                                            class="default-btn btn-bg-three">
                                            Update Cart
                                        </a>
                                    </div>
                                  </div>
                                </div>
                              </div>
                        </form>
                      
                    </c:otherwise>
                  </c:choose>

                      
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="cart-calc">
                        <div class="cart-wraps-form">
                          <h3>Calculate Shipping</h3>
                          <div class="row">
                            <div class="col-lg-6">
                              <div class="form-group">
                                <select>
                                  <option value="">Credit Card Type</option>
                                  <option value="">Another option</option>
                                  <option value="">A option</option>
                                </select>
                              </div>
                            </div>
                            <div class="form-group col-lg-6">
                              <input type="text" class="form-control" placeholder="Credit Card Number">
                            </div>
                            <div class="form-group col-12">
                              <input type="text" class="form-control" placeholder="Card Verification Number">
                            </div>
                          </div>
                          <div class="form-group">
                            <input type="text" class="form-control" placeholder="Coupon Code">
                          </div>
                          <a href="#" class="default-btn btn-bg-three">
                            Apply Coupon
                          </a>
                        </div>
                      </div>
                    </div>

                    <div class="col-lg-6">
                      <div class="cart-totals">
                        <h3>Cart Totals</h3>
                        <ul>
                          <li>Subtotal <span>$150.00</span></li>
                          <li>Shipping <span>$30.00</span></li>
                          <li>Coupon <span>$20.00</span></li>
                          <li>Total <span><b>$160.00</b></span></li>
                        </ul>
                        <a href="#" class="default-btn btn-bg-three">
                          Proceed To Checkout
                        </a>
                      </div>
                    </div>
                  </div>
              </div>
            </div>
          </div>
        </section>
        <!-- End Cart Area -->

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
        
        <script>
            const submitUpdate = () => {
                const form = document.getElementById("cartForm");
                const input = document.createElement("input");
                input.type="hidden";
                input.name="action";
                input.value="update";
                form.appendChild(input);
                form.submit();
            }
            
            const submitDelete = (foodId) => {
                const form = document.getElementById("cartForm");
                const inputAction = document.createElement("input");
                inputAction.type="hidden";
                inputAction.name="action";
                inputAction.value="delete";
                const input = document.createElement("input");
                input.type="hidden";
                input.name="deleteId";
                input.value=foodId;
                form.appendChild(inputAction)
                form.appendChild(input);
                form.submit();
            }
        </script>

      </body>

      <!-- Mirrored from templates.hibootstrap.com/hilo/default/cart.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:09 GMT -->

      </html>