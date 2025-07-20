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
.card {
    border-radius: 12px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
    padding: 20px;
    background-color: #fff;
    height: 100%;
}

.card-header {
    background-color: #f8f9fa;
    padding: 12px 20px;
    border-bottom: 1px solid #e0e0e0;
    border-top-left-radius: 12px;
    border-top-right-radius: 12px;
}

.card-body {
    padding: 20px;
    font-size: 15px;
}

.dashboard-main-body .row.g-24 {
    row-gap: 10px;
    column-gap: 10px;
}
/* Table styles */
.table th, .table td {
    vertical-align: middle;
    padding: 12px 16px;
    font-size: 14px;
}

.table-hover tbody tr:hover {
    background-color: #f1f3f5;
}

.table thead th {
    background-color: #f8f9fa;
    font-weight: 600;
}

/* Badge styles */
.badge {
    padding: 6px 12px;
    font-size: 13px;
    border-radius: 20px;
    text-transform: capitalize;
    font-weight: 500;
}

.bg-warning {
    background-color: #ffc107;
    color: #212529;
}

.bg-info {
    background-color: #17a2b8;
    color: white;
}

.bg-success {
    background-color: #28a745;
    color: white;
}

.bg-danger {
    background-color: #dc3545;
    color: white;
}

/* Form styling */
.form-label {
    font-weight: 600;
    margin-bottom: 6px;
    display: block;
}

.form-select, .form-control {
    padding: 10px;
    font-size: 14px;
    border-radius: 8px;
    border: 1px solid #ced4da;
    width: 100%;
}

textarea.form-control {
    resize: vertical;
}

/* Buttons */
.btn {
/*    padding: 10px 20px;*/
    font-size: 14px;
    border-radius: 8px;
    font-weight: 500;
}

.btn-primary {
    background-color: #007bff;
    border-color: #007bff;
    color: white;
}

.btn-secondary {
    background-color: #6c757d;
    border-color: #6c757d;
    color: white;
}

.btn:hover {
    opacity: 0.9;
}

/* Layout spacing */
.mb-3 {
    margin-bottom: 1rem !important;
}

.mt-24 {
    margin-top: 24px;
}

.mb-24 {
    margin-bottom: 24px;
}

.g-24 {
    gap: 24px;
}

/* Image inside table */
.product-image {
    width: 60px;
    height: 60px;
    object-fit: cover;
    border-radius: 8px;
}

/* Text alignment for amounts */
.text-end {
    text-align: right;
}

/* Responsive tweaks */
@media (max-width: 768px) {
    .d-flex {
        flex-direction: column;
        gap: 12px;
    }
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
                 <jsp:include page="./../common/sidebar.jsp"></jsp:include>
                <!-- /section-menu-left -->
                <!-- section-content-right -->
                <div class="section-content-right">
                    <!-- header-dashboard -->
                    <jsp:include page="./../common/headerDashboard.jsp"></jsp:include>   
                    <!-- /header-dashboard -->
                    <!-- main-content -->
                    <div class="main-content">
                        <!-- main-content-wrap -->
                        <div class="main-content-inner">
                            <!-- main-content-wrap -->
                            <div class="main-content-wrap">

                                 <div class="dashboard-main-body">
            <div class="d-flex flex-wrap align-items-center justify-content-between gap-3 mb-24">
              </div> 
                
                                  <div class="row g-24" >
                                      <div class="col-lg-12 col-md-12" style="display: flex;gap:70px">
                                 <!-- Order Information -->     
                <div  class="col-lg-6 col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0"  style="color: blue">OrderCombo Information</h6>
                        </div>
                       <div class="card-body">
                            <div class="mb-3">
                                <strong>Ordercombo ID:</strong> ${orderCombo.orderComboId}
                            </div>
                             <div class="mb-3">
                                <strong>Ordercombo Name:</strong> ${orderCombo.comboName}
                            </div>
                             <div class="mb-3">
                                <strong>Discount Price:</strong> ${orderCombo.discountPrice}
                            </div>
                             <div class="mb-3">
                                <strong>Quantity:</strong> ${orderCombo.quantity}
                            </div>
                                <c:choose>
                                    <c:when test="${orderCombo.payment_status == 1}">
                                        <div class="mb-3">
                                            <strong>Payment Status:</strong> Paid
                                        </div>   
                                    </c:when>
                                    <c:otherwise>
                                        <div class="mb-3">
                                            <strong>Payment Status:</strong> Unpaid
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                           
                           
                            <div class="mb-3">
                                <strong>Total Price:</strong> <fmt:formatNumber value="${orderCombo.totalPrice}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ
                            </div>
                          
                        </div>
                    </div>
                </div>
                            <!-- Customer Information -->
                            <div  class="col-lg-5 col-md-5" >
                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0"  style="color: blue">Customer Information</h6>
                        </div>
                        <c:choose>
                            <c:when test="${not empty acc}">
                                  <div class="card-body">
                            <div class="mb-3">
                                <strong>Name:</strong> ${acc.user_name}
                            </div>
                            <div class="mb-3">
                                <strong>Email:</strong> ${acc.email}
                            </div>
                            <div class="mb-3">
                                <strong>Mobile:</strong> ${acc.mobile}
                            </div>
                            <div class="mb-3">
                                <strong>Shipping Address:</strong>
                                ${acc.address}
                            </div>
                        </div>
                            
                              <div class="card-body">
                                  <h5 style="color: blue">Shipper Information</h5>  
                            <div class="mb-3">
                                <strong>Name:</strong> ${accShipper.user_name}
                            </div>
                            <div class="mb-3">
                                <strong>Email:</strong> ${accShipper.email}
                            </div>
                            <div class="mb-3">
                                <strong>Mobile:</strong> ${accShipper.mobile}
                            </div>
                            <div class="mb-3">
                                <strong>Shipping Address:</strong>${accShipper.address}
                            </div>
                  
                        </div>  
                            </c:when>
                        
                        </c:choose>
                      
                    </div>
                </div>
                            </div>
                     
                        </div>
                             <div style="display:flex;justify-content:end;gap:40px">
                                
                                <a href="${pageContext.request.contextPath}/seller/manage-delivery" 
                                   class="btn btn-secondary" style="margin-top: 10px; cursor:poiter;background-color: blue
                                   ;">Go Back</a>
            </div>
                              
           
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
                         <jsp:include page="./../common/footer.jsp"></jsp:include>
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