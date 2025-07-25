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
                                        <tr name="tr-food">
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
                                                <input readonly name="quantity" type="text" value="${item.getQuantity()}" min="1">

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
                      <form action="${pageContext.request.contextPath}/cart" method="GET" id="formCheckout" class="row">  
                          <!--Coupon--> 
                            <div class="col-lg-6">
                              <div class="cart-calc">
                                <div class="cart-wraps-form">
                                  <h3>Coupon</h3>
                                  <div class="form-group">
                                    <input name="couponCode" type="text" class="form-control" placeholder="Coupon Code" value="${not empty couponCode? couponCode : ''}">
                                  </div>
                                  <a href="#" onclick="submitCoupon()" class="default-btn btn-bg-three">
                                    Apply Coupon
                                  </a>
                                </div>
                              </div>
                            </div>
                              <!--Total-->
                            <div class="col-lg-6">
                              <div class="cart-totals">
                                <h3>Cart Totals</h3>
                                <ul>
                                  <li class="subTotal">Subtotal<span></span></li>
                                  <li class="coupon">Coupon
                                      <span>
                                          <c:if test="${not empty discountValue}">
                                              <fmt:formatNumber value="${discountValue}" type="number" groupingUsed="true" maxFractionDigits="0" /> VNĐ
                                          </c:if>
                                          <c:if test="${empty discountValue}"> 
                                              0           
                                          </c:if>
                                      </span>
                                  </li>
                                  <li class="totalPrice">Total <span><b></b></span></li>
                                </ul>
                                <input type="hidden" name="action" value="checkout">
                                <input type="hidden" name="subTotal" value="">
                                <input type="hidden" name="totalPrice" value="">
                                <input type="hidden" name="discountAmount" value="${not empty discountValue? discountValue : ''}">
                                <a href="#" onclick="submitCheckout()" class="default-btn btn-bg-three">
                                   Proceed To Checkout
                                </a>
                              </div>
                            </div>
                    </form>
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
            
            const submitCheckout = () => {
                const formCheckout = document.getElementById("formCheckout");
                formCheckout.submit();
            }
            
            const submitCoupon = () => {
                const formCoupon = document.createElement('form');
                formCoupon.method = "POST";
                formCoupon.action = "${pageContext.request.contextPath}/cart"
                
                //Lấy couponCode,subTotal và totalPrice
                const coupon = document.querySelector("input[name='couponCode']").value
                const subTotal = document.querySelector("input[name='subTotal']").value
                const totalPrice = document.querySelector("input[name='totalPrice']").value
                
                console.log("Coupon" + coupon)
                console.log("Subtotal" + subTotal)
                console.log("TotalPrice" + totalPrice)
                
                //input an cho action
                const inputAction = document.createElement("input");
                inputAction.type="hidden";
                inputAction.name="action";
                inputAction.value="coupon";
                //input an cho couponCode
                const inputCoupon = document.createElement("input");
                inputCoupon.type="hidden";
                inputCoupon.name="coupon";
                inputCoupon.value=coupon;
                //input an subTotal
                const inputSubTotal = document.createElement("input");
                inputSubTotal.type="hidden";
                inputSubTotal.name="subTotal";
                inputSubTotal.value=subTotal;
                //input an totalPrice
                const inputTotalPrice = document.createElement("input");
                inputTotalPrice.type="hidden";
                inputTotalPrice.name="totalPrice";
                inputTotalPrice.value=totalPrice;
                
                formCoupon.appendChild(inputAction);
                formCoupon.appendChild(inputCoupon);
                formCoupon.appendChild(inputSubTotal);
                formCoupon.appendChild(inputTotalPrice);
                
                document.body.appendChild(formCoupon)
                formCoupon.submit();
                
            }
        </script>
        
        <script>
            document.addEventListener("DOMContentLoaded" , () => {
                const listRow = document.querySelectorAll("tr[name='tr-food']")
                let subTotal = 0;
                listRow.forEach(row => {
                    const totalRow = row.querySelector(".subtotal-amount")
                    const priceText = totalRow.textContent.trim()
                    const priceNumber = parseInt(priceText.replace(/[^\d]/g, ""));
                    subTotal += priceNumber;
                })
                //Lay ra the Subtotal
                const subTotalSpan = document.querySelector(".subTotal").querySelector("span")
                subTotalSpan.textContent = subTotal.toLocaleString("vi-VN") + " VNĐ"
                //Lay ra the coupon
                let coupon = document.querySelector(".coupon").querySelector("span").textContent
                const couponNumber =parseInt(coupon.replace(/[^\d]/g, ""));
                
                //Tinh toan total = subTotal - coupon
                const totalPrice = subTotal - couponNumber;
                console.log(totalPrice)
                const totalPriceSpan = document.querySelector(".totalPrice").querySelector("span b");
                totalPriceSpan.textContent = totalPrice.toLocaleString("vi-VN") + " VNĐ"
                
                console.log(couponNumber)
                console.log(subTotal)
                console.log(totalPrice)
                
                //Lay ra input subTotal va totalPrice
                const inputSubTotal = document.querySelector("input[name='subTotal']")
                const inputTotalPrice = document.querySelector("input[name='totalPrice']")
                inputSubTotal.value = subTotal
                inputTotalPrice.value = totalPrice
            })
        </script>
        
        
        
        <!--Thong bao cart rong cho checkout-->
         <c:if test="${isEmptyListCartItem == true}">
            <script>
              document.addEventListener("DOMContentLoaded", function () {
                iziToast.error({
                    title: "Thông báo",
                    message: "Vui lòng thêm sản phẩm để thanh toán !",
                    position: 'topRight',
                    timeout: 5000,
                    backgroundColor:"#d4edda"
                    });
              });
            </script>
          </c:if>
            
         <!--Thong bao coupon KHĐ do cart rong-->
         <c:if test="${isEmptyListCartItemForCoupon == true}">
            <script>
              document.addEventListener("DOMContentLoaded", function () {
                iziToast.error({
                    title: "Thông báo",
                    message: "Vui lòng thêm sản phẩm để áp dụng mã giảm giá !",
                    position: 'topRight',
                    timeout: 5000,
                    backgroundColor:"#E23F33"
                    });
              });
            </script>
          </c:if>
            
        <!--Thong bao coupon KHĐ do account khong ton tai--> 
        <c:if test="${accountNotFound == true}">
            <script>
              document.addEventListener("DOMContentLoaded", function () {
                iziToast.error({
                    title: "Thông báo",
                    message: "Vui lòng đăng ký để có thể sử dụng mã giảm giá!",
                    position: 'topRight',
                    timeout: 5000,
                    backgroundColor:"#E23F33"
                    });
              });
            </script>
          </c:if>
        
        <!--Thong bao coupon KHĐ do coupon khong ton tai-->
        <c:if test="${couponNotFound == true}">
            <script>
              document.addEventListener("DOMContentLoaded", function () {
                iziToast.error({
                    title: "Thông báo",
                    message: "Sai mã giảm giá!",
                    position: 'topRight',
                    timeout: 5000,
                    backgroundColor:"#E23F33"
                    });
              });
            </script>
          </c:if>
            
        <!--Thong bao coupon KHĐ do active-->
        <c:if test="${couponNotActive == true}">
          <script>
            document.addEventListener("DOMContentLoaded", function () {
              iziToast.error({
                  title: "Thông báo",
                  message: "Mã giảm giá đã hết hiệu lực !",
                  position: 'topRight',
                  timeout: 5000,
                  backgroundColor:"#E23F33"
                  });
            });
          </script>
        </c:if>

        <!--Thong bao coupon KHĐ do het han-->
        <c:if test="${couponNotActiveByDate == true}">
         <script>
           document.addEventListener("DOMContentLoaded", function () {
             iziToast.error({
                 title: "Thông báo",
                 message: "Mã giảm giá đã hết thời hạn !",
                 position: 'topRight',
                 timeout: 5000,
                 backgroundColor:"#E23F33"
                 });
           });
         </script>
       </c:if>

        <!--Thong bao coupon KHĐ do het so luong-->
        <c:if test="${couponNotActiveByLimit == true}">
        <script>
          document.addEventListener("DOMContentLoaded", function () {
            iziToast.error({
                title: "Thông báo",
                message: "Số lượng mã giảm giá đã hết. Vui lòng dùng mã khác!",
                position: 'topRight',
                timeout: 5000,
                backgroundColor:"#E23F33"
                });
          });
        </script>
      </c:if>

        <!--Thong bao coupon KHĐ do het so lan su dung-->
        <c:if test="${couponNotActiveByUsed == true}">
        <script>
          document.addEventListener("DOMContentLoaded", function () {
            iziToast.error({
                title: "Thông báo",
                message: "Bạn đã hết số lần sử dụng mã này. Vui lòng dùng mã khác!",
                position: 'topRight',
                timeout: 5000,
                backgroundColor:"#E23F33"
                });
          });
        </script>
      </c:if>
        
        <!--Thong bao coupon KHĐ do subTotal không đu-->
        <c:if test="${couponNotActiveByNotEnoughPrice == true}">
        <script>
          document.addEventListener("DOMContentLoaded", function () {
            iziToast.error({
                title: "Thông báo",
                message: "Tổng giá tiền của đơn hàng chưa đủ để áp dụng mã này!",
                position: 'topRight',
                timeout: 5000,
                backgroundColor:"#E23F33"
                });
          });
        </script>
        </c:if>
        
        <!--Thong bao don hang da duoc gui qua email-->
        <c:if test="${notificationForEmail == true}">
        <script>
          document.addEventListener("DOMContentLoaded", function () {
            iziToast.error({
                title: "Thông báo",
                message: "Đơn hàng đã được gửi qua email. Vui lòng kiểm tra!",
                position: 'topRight',
                timeout: 5000,
                backgroundColor:"#d4edda"
                });
          });
        </script>
      </c:if>
        
        
            
      </body>

      <!-- Mirrored from templates.hibootstrap.com/hilo/default/cart.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:09 GMT -->

      </html>