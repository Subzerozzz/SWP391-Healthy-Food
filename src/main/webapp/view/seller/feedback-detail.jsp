<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
  <!--<![endif]-->

  <!-- Mirrored from themesflat.co/html/remos/oder-detail.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
  <head>
    <!-- Basic Page Needs -->
    <meta charset="utf-8" />
    <!--[if IE
      ]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"
    /><![endif]-->
    <title>Remos eCommerce Admin Dashboard HTML Template</title>

    <meta name="author" content="themesflat.com" />

    <!-- Mobile Specific Metas -->
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1"
    />

    <!-- Theme Style -->
    <link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/css/animate.min_1.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/css/animation.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/css/bootstrap.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/css/bootstrap-select.min.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/css/style_1.css"
    />

    <!--fontawsome-->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
    />

    <!-- Font -->
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/font/fonts.css"
    />

    <!-- Icon -->
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/icon/style.css"
    />

    <!-- Favicon and Touch Icons  -->
    <link
      rel="shortcut icon"
      href="${pageContext.request.contextPath}/images/favicon_1.png"
    />
    <link
      rel="apple-touch-icon-precomposed"
      href="${pageContext.request.contextPath}/images/favicon_1.png"
    />
    <style>
      .box-logo {
        height: 100px;
        overflow: hidden;
      }
      .logo {
        max-width: 100%;
        height: 100%;
        display: block;
      }

   .summary-item {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 8px;
}

.body-text {
  color: black;
  font-weight: 600;
  min-width: 120px; /* Giúp các label thẳng hàng */
}

.body-title-2 {
  color: black;
  flex: 1; /* Chiếm phần còn lại */
  word-break: break-word;
}
.feedback-card {
  display: flex;
  height: 75vh; /* Chiều cao phù hợp màn hình */
  max-height: 80vh;
  background: #ffffff;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  overflow: hidden;
  padding: 20px;
  gap: 24px;
  box-sizing: border-box;
}

/* Bên trái: ảnh */
.feedback-left {
  flex: 0 0 35%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.feedback-image {
  width: 100%;
  height: auto;
  max-height: 100%;
  border-radius: 12px;
  object-fit: cover;
  border: 1px solid #ccc;
}

/* Bên phải: nội dung */
.feedback-right {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

/* Grid nội dung chia đều */
.feedback-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px 32px;
}

.feedback-item {
  display: flex;
  flex-direction: column;
}

.feedback-label {
  font-weight: 600;
  color: #444;
  margin-bottom: 4px;
  font-size:16px;
}

.feedback-value,
.feedback-text {
  font-size: 16px;
  color: #222;
  line-height: 1.5;
}

/* Nếu content dài, chiếm cả 2 cột */
.feedback-full {
  grid-column: span 2;
}
    </style>
  </head>

  <body class="body">
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
          <!-- /preload -->
          <!-- section-menu-left -->
         <jsp:include page = "../common/sidebar.jsp"></jsp:include>
          <!-- /section-menu-left -->
          <!-- section-content-right -->
          <div class="section-content-right">
            <!-- header-dashboard -->
           <jsp:include page = "../common/headerDashboard.jsp"></jsp:include>
            <!-- /header-dashboard -->
            <!-- main-content -->
            <div class="main-content">
              <!-- main-content-wrap -->
              <div class="main-content-inner">
                <!-- main-content-wrap -->
                <div class="main-content-wrap">
                  <!-- order-detail -->
                  <div class="main-content-wrap">
                    <div class="flex items-center flex-wrap justify-between gap20 mb-27"s >
                        <c:set var="account" value="${AccountMap[feedback.user_id]}"/>
                     <c:set var="food" value="${FoodMap[feedback.order_item_id]}"/>
                      <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                       
                          <li>
                          
                            <a href="${pageContext.request.contextPath}/seller/manage-feedback">
                            <div class="text-tiny">Feedback</div>
                            </a>
                         
                        </li>
                        <li>
                          <i class="icon-chevron-right"></i>
                        </li>
                        <li>
                          <a href="#"
                            ><div class="text-tiny">Feedback detail</div></a
                          >
                        </li>
                        <li>
                          <i class="icon-chevron-right"></i>
                        </li>
                        <li>
                          <div class="text-tiny">Feedback ${feedback.id}</div>
                        </li>
                      </ul>
                    </div>
                    <!-- order-detail -->
                    <div class="wg-order-detail">
                        <div class="left flex-grow" style="width:50%">

                        <div class="feedback-card">
  <div class="feedback-left">
    <img
      class="feedback-image"
      src="https://foodphoto.vn/wp-content/uploads/2023/09/chup-hinh-thuc-pham-2-550x550.jpeg"
      alt="Food Image"
    />
  </div>

  <div class="feedback-right">
    <div class="feedback-grid">
      <div class="feedback-item">
        <div class="feedback-label">Food Name:  ${food.name}</div>
       
      </div>

      <div class="feedback-item">
        <div class="feedback-label">Customer: ${account.user_name}</div>
      
      </div>

      <div class="feedback-item">
          <div class="feedback-label" style="display:flex;justify-content: start;align-items: center;gap:8px">
              Rating: 
        <div class="feedback-value">
          <c:forEach begin="1" end="${feedback.rating}">
            <i class="fa-solid fa-star" style="color: gold;"></i>
          </c:forEach>
        </div>
       </div>
      </div>

      <div class="feedback-item feedback-full">
        <div class="feedback-label">Feedback:</div>
        <div class="feedback-text">${feedback.content}</div>
      </div>
    </div>
  </div>
</div>
                      </div>

                              <div class="right">
  <div class="wg-box mb-20 gap10">
    <div class="body-title">Summary of Food</div>

    <div class="summary-item">
      <span class="body-text">Price:</span>
      <span class="body-title-2"><fmt:formatNumber value="${food.price}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ</span>
    </div>

    <div class="summary-item">
      <span class="body-text">Calo:</span>
      <span class="body-title-2">${food.calo}</span>
    </div>

    <div class="summary-item">
      <span class="body-text">Description:</span>
      <span class="body-title-2">${food.description}</span>
    </div>

    <div class="summary-item">
      <span class="body-text">Created At:</span>
      <span class="body-title-2 tf-color-1">
        <fmt:formatDate value="${feedback.createdAt}" pattern="dd/MM/yyyy HH:mm" />
      </span>
    </div>
  </div>

  <div class="wg-box gap10">
    <a class="tf-button style-1 w-full" href="${pageContext.request.contextPath}/seller/manage-feedback">
      Back Feedback List
    </a>
  </div>
</div>
      
                    </div>
                    <!-- /order-detail -->
                  </div>

                  <!-- Order Items -->
                </div>
                <!--end fix-->
                <!-- Toast Container -->
                <div
                  class="toast-container position-fixed bottom-0 end-0 p-3"
                  style="z-index: 11"
                >
                  <div
                    id="orderToast"
                    class="toast"
                    role="alert"
                    aria-live="assertive"
                    aria-atomic="true"
                  >
                    <div class="toast-header" id="toast-header">
                      <strong class="me-auto" id="toast-title"
                        >Thông báo</strong
                      >
                      <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="toast"
                        aria-label="Close"
                      ></button>
                    </div>
                    <div class="toast-body" id="toast-body"></div>
                  </div>

                  <!-- Confirmation Toast -->
                  <div
                    id="confirmToast"
                    class="toast"
                    role="alert"
                    aria-live="assertive"
                    aria-atomic="true"
                    data-bs-autohide="false"
                  >
                    <div class="toast-header bg-warning text-white">
                      <strong class="me-auto">Confirmation</strong>
                      <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="toast"
                        aria-label="Close"
                      ></button>
                    </div>
                    <div class="toast-body">
                      <p id="confirm-message">
                        Are you sure you want to update this order status?
                      </p>
                      <div class="mt-2 d-flex justify-content-end gap-2">
                        <button
                          type="button"
                          class="btn btn-sm btn-secondary"
                          data-bs-dismiss="toast"
                        >
                          No
                        </button>
                        <button
                          type="button"
                          class="btn btn-sm btn-primary"
                          id="confirm-yes-btn"
                        >
                          Yes, Update
                        </button>
                      </div>
                    </div>
                  </div>
                </div>

                <!-- /order-detail -->
              </div>
       <jsp:include page="../common/footer.jsp"></jsp:include>
              <!-- /main-content-wrap -->
            </div>
            <!-- /main-content-wrap -->
            <!-- bottom-page -->
          
            <!-- /bottom-page -->
          </div>
          <!-- /main-content -->
        </div>
        <!-- /section-content-right -->
      </div>
      <!-- /layout-wrap -->
    </div>
    <!-- /#wrapper -->

    <!-- Javascript -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/zoom.js"></script>
    <script src="${pageContext.request.contextPath}/js/switcher.js"></script>
    <script src="${pageContext.request.contextPath}/js/theme-settings.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <script>
      
      
  </body>

 </html>
