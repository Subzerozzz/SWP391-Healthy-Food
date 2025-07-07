<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<!--<![endif]-->
<!-- Mirrored from themesflat.co/html/remos/oder-detail.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->

<head>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>
  <style>
    .order-items-container {
      max-height: 300px;
      /* Chiều cao tối đa tương ứng với khoảng 4 item */
      overflow-y: auto;
      padding-right: 8px;
      /* Tránh thanh cuộn che nội dung */
      border: 1px solid #e0e0e0;
      border-radius: 8px;
    }

    /* Scrollbar - Chrome, Safari, Edge */
    .order-items-container::-webkit-scrollbar {
      width: 6px;
    }

    .order-items-container::-webkit-scrollbar-thumb {
      background-color: #ccc;
      border-radius: 4px;
    }

    .order-items-container::-webkit-scrollbar-thumb:hover {
      background-color: #999;
    }

    /* Scrollbar - Firefox */
    .order-items-container {
      scrollbar-width: thin;
      scrollbar-color: #ccc transparent;
    }
  </style>





  <!-- Basic Page Needs -->
  <meta charset="utf-8">
  <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
  <title>Remos eCommerce Admin Dashboard HTML Template</title>

  <meta name="author" content="themesflat.com">

  <!-- Mobile Specific Metas -->
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

  <!-- Theme Style -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min_1.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animation.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-select.min.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_1.css">



  <!-- Font -->
  <link rel="stylesheet" href="font/fonts.css">

  <!-- Icon -->
  <link rel="stylesheet" href="icon/style.css">

  <!-- Favicon and Touch Icons  -->
  <link rel="shortcut icon" href="images/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="images/favicon.png">

</head>

<body class="body">More actions

  <!-- #wrapper -->
  <div id="wrapper">
    <!-- #page -->
    <div id="page" class="">
      <!-- layout-wrap -->
      <div class="layout-wrap">
        <!-- preload -->
        <div id="preload" class="preload-container">
          <div class="preloading">
            <span></span>
          </div>
        </div>
      </div>
      <!-- /preload -->
      <!-- section-menu-left -->
      <jsp:include page="/view/common/sidebar.jsp"></jsp:include>
      <!-- /section-menu-left -->
      <!-- section-content-right -->
      <div class="section-content-right">
        <!-- header-dashboard -->
        <jsp:include page="/view/common/headerDashboard.jsp"></jsp:include>

        <!-- /header-dashboard -->
        <!-- main-content -->
        <div class="main-content">
          <!-- main-content-wrap -->
          <div class="main-content-inner">
            <!-- main-content-wrap -->
            <div class="main-content-wrap">
              <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                <c:forEach var="orderItem" items="${orderItemList}" varStatus="status">
                  <c:if test="${status.first}">
                    <h3>Order #${order.id}</h3>
                  </c:if>
                  <!-- Thông tin chi tiết -->
                </c:forEach>
                <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                  <li>
                    <a href="index.html">
                      <div class="text-tiny">Dashboard</div>
                    </a>
                  </li>
                  <li>
                    <i class="icon-chevron-right"></i>
                  </li>
                  <li>
                    <a href="#">
                      <div class="text-tiny">Order</div>
                    </a>
                  </li>
                  <li>
                    <i class="icon-chevron-right"></i>
                  </li>
                  <li>
                    <a href="#">
                      <div class="text-tiny">Order detail</div>
                    </a>
                  </li>
                  <li>
                    <i class="icon-chevron-right"></i>
                  </li>
                </ul>
              </div>
              <!-- order-detail -->
              <div class="wg-order-detail">
                <div class="left flex-grow">
                  <div class="wg-box mb-20">
                    <div class="wg-table table-order-detail">
                      <ul class="table-title flex items-center justify-between gap20 mb-24">
                        <li>
                          <div class="body-title">All item</div>
                        </li>

                      </ul>
                      <div class="order-items-container">
                        <ul class="flex flex-column">
                          <c:forEach var="orderItem" items="${orderItemList}" varStatus="status">
                            <li class="product-item gap14">
                              <div class="image no-bg">
                                <img src="${foodDAO.findById(orderItem.food_id).image_url}" alt="">
                              </div>
                              <div class="flex items-center justify-between gap40 flex-grow">
                                <!-- Tên món -->
                                <div class="name">
                                  <div class="text-tiny mb-1">Food name</div>
                                  <a href="product-list.html"
                                    class="body-title-2">${foodDAO.findById(orderItem.food_id).name}</a>
                                </div>

                                <!-- Số lượng -->
                                <div class="name">
                                  <div class="text-tiny mb-1">Quantity</div>
                                  <div class="body-title-2">${orderItem.quantity}</div>
                                </div>

                                <!-- Giá -->
                                <div class="name">
                                  <div class="text-tiny mb-1">Price</div>
                                  <div class="body-title-2">
                                    <fmt:formatNumber value="${foodDAO.findById(orderItem.food_id).price}" type="number"
                                      groupingUsed="true" /> VND
                                  </div>
                                </div>

                                <!-- Cột Feedback -->
                                <div class="name">
                                  <div class="text-tiny mb-1">Feedback</div>
                                  <c:if test="${order.payment_status == 1 && order.status == 'completed'}">
                                    <a href="createfeedback?order_item_id=${orderItem.id}&source=orderdetail&order_id=${order.id}"
                                      title="Write Feedback">
                                      <i class="fa fa-pencil-alt" style="font-size: 15px; color: #595959;"></i>
                                    </a>
                                  </c:if>
                                </div>
                              </div>
                            </li>
                          </c:forEach>
                        </ul>
                      </div>
                    </div>
                  </div>
                  <div class="wg-box">
                    <div class="wg-table table-cart-totals">
                      <ul class="table-title flex mb-24">
                        <li>
                          <div class="body-title">Cart Totals</div>
                        </li>
                        <li>
                          <div class="body-title">Price</div>
                        </li>
                      </ul>
                      <c:forEach var="orderItem" items="${orderItemList}" varStatus="status">
                        <c:if test="${status.first}">
                          <ul class="flex flex-column gap14">
                            <li class="cart-totals-item">
                              <span class="body-text">Subtotal:</span>
                              <span class="body-title-2">
                                <fmt:formatNumber value="${subtotal}" type="number" groupingUsed="true" /> VND
                              </span>
                            </li>
                            <li class="divider"></li>
                            <li class="cart-totals-item">
                              <span class="body-text">Coupon</span>
                              <span class="body-title-2">${order.coupon_code}</span>
                            </li>
                            <li class="divider"></li>
                            <li class="cart-totals-item">
                              <span class="body-text">Payment Status</span>
                              <span class="body-title-2">
                                <c:choose>
                                  <c:when test="${order.payment_status == 0}"><i style="color: red">Chưa thanh toán</i>
                                  </c:when>
                                  <c:when test="${order.payment_status == 1}"><i style="color: green">Đã thanh toán</i>
                                  </c:when>
                                  <c:otherwise>Không xác định</c:otherwise>
                                </c:choose>
                              </span>
                            </li>

                            <li class="divider"></li>
                            <li class="cart-totals-item">
                              <span class="body-title">Total price:</span>
                              <span class="body-title tf-color-1">
                                <fmt:formatNumber value="${order.total}" type="number" groupingUsed="true" /> VND
                              </span>
                            </li>
                          </ul>
                        </c:if>
                        <!-- Thông tin chi tiết -->
                      </c:forEach>
                    </div>
                  </div>
                </div>
                <c:forEach var="orderItem" items="${orderItemList}" varStatus="status">
                  <c:if test="${status.first}">
                    <div class="right">
                      <div class="wg-box mb-20 gap10">
                        <div class="body-title">Summary</div>
                        <div class="summary-item">
                          <div class="body-text">Order ID</div>
                          <div class="body-title-2">#${order.id}</div>
                        </div>
                        <div class="summary-item">
                          <div class="body-text">Create At</div>
                          <div class="body-title-2">
                            <fmt:formatDate value="${order.created_at}" pattern="dd-MM-yyyy HH:mm:ss" />
                          </div>
                        </div>
                        <div class="summary-item">
                          <div class="body-text">Total</div>
                          <div class="body-title-2 tf-color-1">
                            <fmt:formatNumber value="${order.total}" type="number" groupingUsed="true" /> VND
                          </div>
                        </div>
                      </div>
                      <div class="wg-box mb-20 gap10">
                        <div class="body-title">Shipping Address</div>
                        <div class="body-text">${order.shipping_address}</div>
                      </div>
                      <div class="wg-box mb-20 gap10">
                        <div class="body-title">Payment Method</div>
                        <div class="body-text">${order.payment_method} - <c:choose>
                            <c:when test="${order.payment_status == 0}">
                              <span class="">Chưa thanh toán</span>
                            </c:when>
                            <c:when test="${order.payment_status == 1}">
                              <span class="">Đã thanh toán</span>
                            </c:when>
                            <c:otherwise>
                              <span class="">Không xác định</span>
                            </c:otherwise>
                          </c:choose>
                        </div>
                      </div>
                      <div class="wg-box gap10">
                        <div class="body-title">Expected Date Of Delivery</div>
                        <c:choose>
                          <c:when test="${order.status == 'pending'}">
                            <div class="body-title-2 tf-color-2">Your order needs seller confirmation</div>
                          </c:when>
                          <c:when test="${order.status == 'accepted'}">
                            <div class="body-title-2 tf-color-2">Your order has been accepted by the seller, please
                              wait.</div>
                          </c:when>
                          <c:when test="${order.status == 'cancelled'}">
                            <div class="body-title-2 tf-color-2">Your order has been rejected by the seller.</div>
                          </c:when>
                          <c:when test="${order.status == 'completed'}">
                            <div class="body-title-2 tf-color-2">Your order is on its way to your address.</div>
                          </c:when>
                          <c:otherwise>
                            <div class="body-title-2 tf-color-2">Status unknown. Please contact support.</div>
                          </c:otherwise>
                        </c:choose>
                      </div>
                      <c:if test="${order.payment_method == 'VNPAY' and order.payment_status == 0}">
                        <a class="tf-button style-1 w-full" href="#" style="  margin-top: 20px">Thanh toán ngay</a>
                      </c:if>
                      <c:choose>
                        <c:when test="${order.status == 'completed' and order.payment_status == 1}">
                          <a style="margin-top: 20px; margin-bottom: 20px;" class="tf-button style-1 w-full"
                            href="feedback">Feedback Order</a>
                        </c:when>
                        <c:otherwise>
                          <a style=" margin-top: 20px;    margin-bottom: 20px;    " class="tf-button style-1 w-full"
                            onclick="cancelOrder(${order.id}, '${order.status}')">Cancel Order</a>
                        </c:otherwise>
                      </c:choose>
                    </div>
                    <c:if test="${order.payment_method == 'VNPAY' and order.payment_status == 0}">
                      <a class="tf-button style-1 w-full" href="#" style="  margin-top: 20px">Thanh toán ngay</a>
                    </c:if>
                    <c:choose>
                      <c:when test="${order.status == 'completed' and order.payment_status == 1}">
                        <a style="margin-top: 20px; margin-bottom: 20px;" class="tf-button style-1 w-full"
                          href="feedback?orderId=${order.id}">Feedback Order</a>
                      </c:when>
                      <c:otherwise>
                        <a style=" margin-top: 20px;    margin-bottom: 20px;    " class="tf-button style-1 w-full"
                          onclick="cancelOrder(${order.id}, '${order.status}')">Cancel Order</a>
                      </c:otherwise>
                    </c:choose>

                    <a class="tf-button style-1 w-full" href="${pageContext.request.contextPath}/orderlist">Back to
                      Order List</a>
              </div>
              </c:if>
              <!-- Thông tin chi tiết -->
              </c:forEach>
            </div>
            <!-- /order-detail -->
          </div>
          <!-- /main-content-wrap -->
        </div>
        <!-- /main-content-wrap -->
        <!-- bottom-page -->
        <jsp:include page="/view/common/footer.jsp"></jsp:include>
        <!-- /bottom-page -->
      </div>
      <!-- /main-content -->
    </div>
    <!-- /section-content-right -->
  </div>
  <!-- /layout-wrap -->
  </div>
  <!-- /#page -->
  </div>
  <!-- /#wrapper -->

  <!-- Javascript -->
  <script>
    function cancelOrder(orderId, status) {
      if (status.toLowerCase() !== 'pending') {
        alert("Chỉ có thể hủy đơn hàng khi trạng thái là 'pending'");
        return;
      }

      if (!confirm("Bạn có chắc muốn hủy đơn hàng này không?"))
        return;

      fetch('cancel-order', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          body: 'orderId=' + orderId
        })
        .then(response => {
          if (response.ok) {
            alert("Đơn hàng đã được hủy.");
            location.reload(); // reload để cập nhật UI
          } else {
            alert("Không thể hủy đơn hàng. Đơn hàng có thể đã được xử lý.");
          }
        })
        .catch(error => {
          console.error("Lỗi:", error);
          alert("Có lỗi xảy ra khi hủy đơn.");
        });
    }
  </script>
  <c:if test="${sessionScope.toastType == 'success'}">
    <script>
      document.addEventListener("DOMContentLoaded", function () {
        iziToast.success({
          title: "Thông báo",
          message: '<c:out value="${sessionScope.toastMessage}" />',
          position: 'topRight',
          timeout: 5000,
          backgroundColor: "#28a745"
        });
      });
    </script>
    <!--Xóa đi biến isDelete sau khi đã thông báo-->
    <%
                                                                            session.removeAttribute("toastType");
                                                                            session.removeAttribute("toastMessage");
                                                                        %>
  </c:if>

  <script src="${pageContext.request.contextPath}/js/jquery.min_1.js"></script>
  <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/zoom.js"></script>
  <script src="${pageContext.request.contextPath}/js/switcher.js"></script>
  <script src="${pageContext.request.contextPath}/js/theme-settings.js"></script>
  <script src="${pageContext.request.contextPath}/js/main.js"></script>

</body>



</html>