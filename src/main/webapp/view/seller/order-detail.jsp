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
    padding: 10px 20px;
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
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                    <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                        <li>
                                            <a href="index.html"><div class="text-tiny">Dashboard</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <a href="${pageContext.request.contextPath}/seller/manage-order"><div class="text-tiny">Order</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <a href="#"><div class="text-tiny">Order detail</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <div class="text-tiny">Order #${order.id}</div>
                                        </li>
                                    </ul>
                                </div>
                                <!-- order-detail -->
                                <!--start fix-->
                                
                                 <div class="dashboard-main-body">
            <div class="d-flex flex-wrap align-items-center justify-content-between gap-3 mb-24">
                <h6 class="fw-semibold mb-0">Order Details #${order.id}</h6>
                
            </div> 
                
                                  <div class="row g-24" >
                                 <!-- Order Information -->     
                <div class="col-lg-4 col-md-12" style="height: 300px">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0">Order Information</h6>
                        </div>
                        <div class="card-body">
                            <div class="mb-3">
                                <strong>Order ID:</strong> #${order.id}
                            </div>
                            <div class="mb-3">
                                <strong>Order Date:</strong> <fmt:formatDate value="${order.created_at}" pattern="dd/MM/yyyy HH:mm"/>
                            </div>
                            <div class="mb-3">
                                <strong>Payment Method:</strong> ${order.payment_method}
                            </div>
                            <div class="mb-3">
                                <strong>Status:</strong>
                                <span class="badge ${order.status == 'pending' ? 'bg-warning' : 
                                                    order.status == 'accepted' ? 'bg-info' : 
                                                    order.status == 'completed' ? 'bg-success' : 'bg-danger'}">
                                    ${order.status}
                                </span>
                            </div>
                            <div class="mb-3">
                                <strong>Total Amount:</strong> <fmt:formatNumber value="${order.total}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ
                            </div>
                            <c:if test="${not empty order.coupon_code}">
                                <div class="mb-3">
                                    <strong>Coupon Applied:</strong> ${order.coupon_code}
                                </div>
                                <div class="mb-3">
                                    <strong>Discount Amount:</strong> <fmt:formatNumber value="${order.discount_value}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ
                                </div>
<!--                                <div class="mb-3">
                                    <strong>Original Amount:</strong> <fmt:formatNumber value="${order.discount_value}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ
                                </div>-->
                            </c:if>
                        </div>
                    </div>
                </div>
                            <!-- Customer Information -->
                            <div class="col-lg-3 col-md-12" style="height: 300px">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0">Customer Information</h6>
                        </div>
                        <c:choose>
                            <c:when test="${not empty account}">
                                  <div class="card-body">
                            <div class="mb-3">
                                <strong>Name:</strong> ${account.user_name}
                            </div>
                            <div class="mb-3">
                                <strong>Email:</strong> ${account.email}
                            </div>
                            <div class="mb-3">
                                <strong>Mobile:</strong> ${account.mobile}
                            </div>
                            <div class="mb-3">
                                <strong>Shipping Address:</strong><br>
                                ${order.shipping_address}
                            </div>
                        </div>
                            </c:when>
                            <c:otherwise>
                               <div class="card-body">
                            <div class="mb-3">
                                <strong>Name:</strong> ${order.full_name}
                            </div>
                            <div class="mb-3">
                                <strong>Email:</strong> ${order.email}
                            </div>
                            <div class="mb-3">
                                <strong>Mobile:</strong> ${order.mobile}
                            </div>
                            <div class="mb-3">
                                <strong>Shipping Address:</strong><br>
                                ${order.address}
                            </div>
                        </div>  
                            </c:otherwise>
                        </c:choose>
                      
                    </div>
                </div>
                             <!-- Update Status -->
                <div class="col-lg-4 col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0">Status Information</h6>
                        </div>
                        <div class="card-body">
                            <c:choose>
                                <%-- For completed or cancelled orders - Read only view --%>
                                <c:when test="${order.status == 'completed' || order.status == 'cancelled'}">
                                    <div class="mb-3">
                                        <label class="form-label">Current Status</label>
                                        <div class="d-flex align-items-center">
                                            <span class="badge ${order.status == 'completed' ? 'bg-success' : 'bg-danger'} fs-6">
                                                ${order.status}
                                            </span>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <small class="text-muted">
                                            <i class="fas fa-info-circle me-1"></i>
                                            This order has been ${order.status} and cannot be modified further.
                                        </small>
                                    </div>
                                    <div>
                                        <a href="${pageContext.request.contextPath}/seller/manage-order" 
                                           class="btn btn-secondary">
                                            Back to Order List
                                        </a>
                                    </div>
                                </c:when>
                                
                                <%-- For pending or accepted orders - Allow updates --%>
                                <c:otherwise>
                                    <form action="${pageContext.request.contextPath}/seller/manage-order" method="post">
                                        <input type="hidden" name="action" value="updateStatus">
                                        <input type="hidden" name="orderId" value="${order.id}">
                                        
                                        <div class="mb-3">
                                            <label class="form-label">New Status</label>
                                            <select class="form-select" name="newStatus" required>
                                                <option value="">-- Select Status --</option>
                                                <c:if test="${order.status == 'pending'}">
                                                    <option value="accepted">Accept Order</option>
                                                    <option value="cancelled">Reject Order</option>
                                                </c:if>
                                                <c:if test="${order.status == 'accepted'}">
                                                    <option value="completed">Complete Order</option>
                                                    <option value="cancelled">Cancel Order</option>
                                                </c:if>
                                            </select>
                                        </div>
                                        
                                        <div class="mb-3">
                                            <label class="form-label">Note</label>
                                            <textarea class="form-control" name="note" rows="3" 
                                                      placeholder="Enter note..."></textarea>
                                        </div>
                                        
                                        <div class="d-flex gap-2">
                                            <button type="submit" class="btn btn-primary">Update</button>
                                            <a href="${pageContext.request.contextPath}/seller/manage-order" 
                                               class="btn btn-secondary">Back</a>
                                        </div>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                        </div>
                             <!-- Order Items -->
            <div class="card mt-24">
                <div class="card-header">
                    <h6 class="card-title mb-0">Order Items</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Image</th>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Subtotal</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${OrderItems}">
                                    <c:set var="food" value="${OrderItemMap[item.food_id]}"/>
                                    <tr>
                                        <td> 
                                            <a 
                                                href="data:image/jpeg;base64,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" 
                                                target="_blank">
                                            <img 
                                                src="data:image/jpeg;base64,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" 
                                            alt="${food.name}" class="product-image">
                                              </a>
                                        </td>
                                        <td>${food.name}</td>
                                        <td><fmt:formatNumber value="${food.price}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ</td>
                                        <td>${item.quantity}</td>
                                        <td><fmt:formatNumber value="${food.price*item.quantity}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ</td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${not empty order.discount_value}">
                                    <tr>
                                        <td colspan="4" class="text-end"><strong>Discount Amount:</strong></td>
                                        <td><fmt:formatNumber value="${order.discount_value}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ</td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td colspan="4" class="text-end"><strong>Total:</strong></td>
                                    <td><fmt:formatNumber value="${order.total}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>     
                   <!-- Order History -->
            <div class="card mt-24">
                <div class="card-header">
                    <h6 class="card-title mb-0">Order History</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Updated By</th>
                                    <th>Date</th>
                                    <th>Previous Status</th>
                                    <th>New Status</th>
                                    <th>Note</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="approval" items="${approvals}">
                                    <c:set var="ItemAccount" value="${OrderApprovalMap[approval.approved_by]}"/>
                                    <tr>
                                        <td>${ItemAccount.user_name}</td>
                                        <td><fmt:formatDate value="${approval.approved_at}" pattern="dd/MM/yyyy HH:mm"/></td>
                                        <td>
                                            <span class="badge ${approval.statusBefore == 'pending' ? 'bg-warning' : 
                                                                approval.statusBefore == 'accepted' ? 'bg-info' : 
                                                                approval.statusBefore == 'completed' ? 'bg-success' : 'bg-danger'}">
                                                ${approval.statusBefore}
                                            </span>
                                        </td>
                                        <td>
                                            <span class="badge ${approval.statusAfter == 'pending' ? 'bg-warning' : 
                                                                approval.statusAfter == 'accepted' ? 'bg-info' : 
                                                                approval.statusAfter == 'completed' ? 'bg-success' : 'bg-danger'}">
                                                ${approval.statusAfter}
                                            </span>
                                        </td>
                                        <td>${approval.note}</td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty approvals}">
                                    <tr>
                                        <td colspan="5" class="text-center">No update history</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
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