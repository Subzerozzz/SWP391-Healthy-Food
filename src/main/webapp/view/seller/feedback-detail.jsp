<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<!--<![endif]-->


<!-- Mirrored from themesflat.co/html/remos/oder-detail.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
<head>
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

    <!--fontawsome-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <!-- Font -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font/fonts.css">

    <!-- Icon -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/icon/style.css">

    <!-- Favicon and Touch Icons  -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon_1.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/images/favicon_1.png">
        <style>
                               .box-logo{
                                   height: 100px;
                                   overflow: hidden
                               }
                               .logo{
                                   max-width:100%;
                                   height: 100%;
                                   display:block
                               }
                                /* Card styles */
/* Tổng thể khối chi tiết */
.wg-order-detail {
  display: flex;
  flex-wrap: wrap;
  gap: 32px;
  padding: 24px;
  background-color: #ffffff;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.06);
}

/* Trái: Nội dung chính */
.wg-order-detail .left {
  flex: 2;
  min-width: 320px;
}

/* Phải: Summary */
.wg-order-detail .right {
  flex: 1;
  min-width: 260px;
}

/* Box */
.wg-box {
  background: #f9fafb;
  border-radius: 12px;
  padding: 20px 24px;
  margin-bottom: 20px;
}

/* Tiêu đề lớn */
.body-title {
  font-size: 18px;
  font-weight: 600;
  color: #111827;
  margin-bottom: 10px;
}

/* Tiêu đề nhỏ */
.body-title-2 {
  font-size: 16px;
  font-weight: 500;
  color: #1f2937;
}

/* Văn bản mô tả */
.body-text {
  font-size: 14px;
  color: #6b7280;
}

/* Feedback content */
.wg-box .body-text {
  line-height: 1.6;
  white-space: pre-line;
}

/* Image */
.image img {
  width: 72px;
  height: 72px;
  border-radius: 8px;
  object-fit: cover;
}

/* Item feedback */
.product-item {
  display: flex;
  align-items: center;
  gap: 20px;
  padding: 12px 0;
  border-top: 1px solid #e5e7eb;
}

.product-item:first-child {
  border-top: none;
}

/* Tên, khách hàng, rating */
.product-item .name {
  flex: 1;
  font-size: 14px;
}

.product-item .name > div:first-child {
  color: #9ca3af;
  font-size: 13px;
  margin-bottom: 4px;
}

/* Rating */
.fa-star {
  font-size: 16px;
  margin-right: 2px;
}

/* Summary item */
.summary-item {
  display: flex;
  justify-content: space-between;
  margin-bottom: 12px;
  font-size: 14px;
}

/* Nút Back Feedback List */
.tf-button {
  display: block;
  padding: 10px 16px;
  text-align: center;
  background-color: #3b82f6;
  color: white;
  border-radius: 8px;
  text-decoration: none;
  font-weight: 500;
  transition: background 0.2s ease;
}

.tf-button:hover {
  background-color: #2563eb;
}
/* Căn giữa hàng item */
.product-item {
  display: flex;
  align-items: center;
  gap: 20px;
  padding: 16px 0;
}

/* Ảnh không bị lệch */
.product-item .image {
  flex-shrink: 0;
  width: 72px;
  height: 72px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.product-item .image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
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
                <jsp:include page="../common/dash-board-seller/section-menu-left.jsp"></jsp:include>
                <!-- /section-menu-left -->
                <!-- section-content-right -->
                <div class="section-content-right">
                    <!-- header-dashboard -->
                    <jsp:include page="../common/dash-board-seller/header-dashboard.jsp"></jsp:include>    
                    <!-- /header-dashboard -->
                    <!-- main-content -->
                    <div class="main-content">
                        <!-- main-content-wrap -->
                        <div class="main-content-inner">
                            <!-- main-content-wrap -->
                            <div class="main-content-wrap">
                           
                                <!-- order-detail -->
                                <div class="main-content-wrap">
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                  <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                       
                                        <li>
                                            <a href="${pageContext.request.contextPath}/seller/manage-feedback"><div class="text-tiny">Feedback</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <a href="#"><div class="text-tiny">Feedback detail</div></a>
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
                                    <div class="left flex-grow">
                                        <div class="wg-box mb-20">
                                            <div class="wg-table table-order-detail">
                                                <ul class="table-title flex items-center justify-between gap20 mb-24">
                                                    <li>
                                                        <div class="body-title">Item Detail</div>
                                                    </li>    
                                                </ul>
                                                <ul class="flex flex-column">
                                                    <li class="product-item gap14">
                                                        <div class="image">
                                                            <img src="https://foodphoto.vn/wp-content/uploads/2023/09/chup-hinh-thuc-pham-2-550x550.jpeg" alt="Imgage">
                                                        </div>
                                                        <div class="flex items-center justify-between gap40 flex-grow">
                                                            <div class="name">
                                                                <div class="text-tiny mb-1" style="color: black">Food name</div>
                                                                <div style="color: black">${feedback.food.name}</div>
                                                            </div>
                                                            <div class="name">
                                                                <div class="text-tiny mb-1" style="color: black">Customer</div>
                                                                <div class="body-title-2">${feedback.account.user_name}</div>
                                                            </div>
                                                            <div class="name">
                                                                <div class="text-tiny mb-1" style="color: black">Rating</div>
                                                                <div class="body-title-2"> <c:forEach begin="1" end="${feedback.rating}">
                                                                        <i class="fa-solid fa-star" style="color: gold;"></i>
                                                                    </c:forEach></div>
                                                            </div>
                                                            
                                                        </div>
                                                    </li>
                                                   </ul>
                                            </div>
                                                              <div class="wg-box mb-20 gap10">
                                            <div class="body-title">Content Feedback</div>
                                            <div class="body-text" style="color: black">${feedback.content}</div>
                                        </div>
                                        </div>

                                    </div>
                                    <div class="right">
                                        <div class="wg-box mb-20 gap10">
                                            <div class="body-title">Summary of Food </div>
                                            <div class="summary-item">
                                                <div class="body-text" style="color: black">Price:</div>
                                                <div class="body-title-2">${feedback.food.price}</div>
                                            </div>
                                            <div class="summary-item">
                                                <div class="body-text" style="color: black">Calo:</div>
                                                <div class="body-title-2">${feedback.food.calo}</div>
                                            </div>
                                            <div class="summary-item">
                                                <div class="body-text" style="color: black">Description: </div>
                                                <div class="body-title-2">${feedback.food.description}</div>
                                            </div>
                                          
                                             <div class="summary-item">
                                                <div class="body-text" style="color: black">Created At: </div>
                                                <div class="body-title-2 tf-color-1">
                                             <fmt:formatDate value="${feedback.createdAt}" pattern="dd/MM/yyyy HH:mm" />
                                                   </div>
                                            </div>
                                        </div>
                                          <div class="wg-box gap10">
                                            
                                            <a class="tf-button style-1 w-full" href="${pageContext.request.contextPath}/seller/manage-feedback" >Back Feedback List</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- /order-detail -->
                            </div>
                               
                             <!-- Order Items -->
            
            
             </div>
                                <!--end fix-->
                                 <!-- Toast Container -->
                                 <div class="toast-container position-fixed bottom-0 end-0 p-3" style="z-index: 11">
                                     <div id="orderToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
                                         <div class="toast-header" id="toast-header">
                                             <strong class="me-auto" id="toast-title">Thông báo</strong>
                                             <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                                         </div>
                                         <div class="toast-body" id="toast-body"></div>
                                     </div>

                                     <!-- Confirmation Toast -->
                                     <div id="confirmToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-bs-autohide="false">
                                         <div class="toast-header bg-warning text-white">
                                             <strong class="me-auto">Confirmation</strong>
                                             <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                                         </div>
                                         <div class="toast-body">
                                             <p id="confirm-message">Are you sure you want to update this order status?</p>
                                             <div class="mt-2 d-flex justify-content-end gap-2">
                                                 <button type="button" class="btn btn-sm btn-secondary" data-bs-dismiss="toast">No</button>
                                                 <button type="button" class="btn btn-sm btn-primary" id="confirm-yes-btn">Yes, Update</button>
                                             </div>
                                         </div>
                                     </div>
                                 </div>

                               
                                <!-- /order-detail -->
                            </div>
                            <!-- /main-content-wrap -->
                        </div>
                        <!-- /main-content-wrap -->
                        <!-- bottom-page -->
                        <jsp:include page="../common/dash-board-seller/bottom-page.jsp"></jsp:include>
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
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/zoom.js"></script>
    <script src="${pageContext.request.contextPath}/js/switcher.js"></script>
    <script src="${pageContext.request.contextPath}/js/theme-settings.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
 <script>
            // Function to show toast
            function showToast(message, type) {
                const toastEl = document.getElementById('orderToast');
                const toastTitle = document.getElementById('toast-title');
                const toastBody = document.getElementById('toast-body');
                const header = document.getElementById('toast-header');
                
                // Set content
                toastTitle.textContent = type === 'success' ? 'Success' : type === 'error' ? 'Error' : 'Notification';
                toastBody.textContent = message;
                
                // Set header color
                header.className = 'toast-header';
                if(type === 'success') {
                    header.classList.add('bg-success', 'text-white');
                } else if(type === 'error') {
                    header.classList.add('bg-danger', 'text-white');
                } else {
                    header.classList.add('bg-info', 'text-white');
                }
                
                // Show toast
                const toast = new bootstrap.Toast(toastEl);
                toast.show();
                
                return toast;
            }
            
            // Handle form submission with confirmation
            document.addEventListener('DOMContentLoaded', function() {
                const orderForm = document.querySelector('form[action*="manage-order"]');
                if (orderForm) {
                    orderForm.addEventListener('submit', function(e) {
                        e.preventDefault();
                        
                        const statusSelect = document.querySelector('select[name="newStatus"]');
                        if (!statusSelect.value) {
                            showToast("Please select a status", "error");
                            return;
                        }
                        
                        // Get status text for confirmation message - make sure to get the visible text
                        const selectedOption = statusSelect.options[statusSelect.selectedIndex];
                        
                        // Update confirmation message with the proper status text
                        document.getElementById('confirm-message').textContent = 
                            "Are you sure you want to change the order status to \"" + selectedOption.textContent + "\"?";
                        
                        // Show confirmation toast
                        const confirmToast = new bootstrap.Toast(document.getElementById('confirmToast'));
                        confirmToast.show();
                        
                        // Set up confirmation button
                        document.getElementById('confirm-yes-btn').onclick = function() {
                            confirmToast.hide();
                            showToast("Processing update...", "info");
                            orderForm.submit();
                        };
                    });
                }
                
                // Check for messages in session
                <c:if test="${not empty sessionScope.successMessage}">
                    showToast("${sessionScope.successMessage}", "success");
                    // Remove message from session
                    <% session.removeAttribute("successMessage"); %>
                </c:if>
                
                <c:if test="${not empty sessionScope.errorMessage}">
                    showToast("${sessionScope.errorMessage}", "error");
                    // Remove message from session
                    <% session.removeAttribute("errorMessage"); %>
                </c:if>
            });
        </script>
</body>


<!-- Mirrored from themesflat.co/html/remos/oder-detail.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
</html>