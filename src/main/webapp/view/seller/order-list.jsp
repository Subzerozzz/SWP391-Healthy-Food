<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<!<![endif]-->


<!-- Mirrored from themesflat.co/html/remos/oder-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage-request.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search-request.css"/>

    <!-- Font -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/fonts.css">

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
                          
                          /*style for filter*/
                           .fixed-width-btn {
                min-width: 120px;
                text-align: center;
            }
            .view-btn {
                display: inline-flex;
                align-items: center;
                gap: 8px;
                padding: 8px 16px;
                background: linear-gradient(135deg, #4e73df 0%, #3a54c4 100%);
                color: white;
                border-radius: 50px;
                font-size: 14px;
                font-weight: 500;
                text-decoration: none;
                transition: all 0.3s ease;
                box-shadow: 0 2px 10px rgba(78, 115, 223, 0.2);
            }
            
            .view-btn:hover {
                transform: translateY(-2px);
                box-shadow: 0 5px 15px rgba(78, 115, 223, 0.4);
                color: white;
            }
            
            .view-btn:active {
                transform: translateY(0);
                box-shadow: 0 2px 5px rgba(78, 115, 223, 0.3);
            }
            
            .view-icon {
                display: flex;
                align-items: center;
                justify-content: center;
                background-color: rgba(255, 255, 255, 0.2);
                border-radius: 50%;
                width: 24px;
                height: 24px;
                transition: all 0.3s ease;
            }
            
            .view-btn:hover .view-icon {
                background-color: rgba(255, 255, 255, 0.3);
                transform: rotate(15deg);
            }
            
            .view-text {
                transition: all 0.3s ease;
            }
            
            .view-btn:hover .view-text {
                transform: translateX(2px);
            }
            
            /* Responsive adjustments */
            @media (max-width: 768px) {
                .view-btn {
                    padding: 6px 12px;
                    font-size: 13px;
                }
                
                .view-icon {
                    width: 20px;
                    height: 20px;
                }
                
                
            }
.filter-row {
  display: grid;
  grid-template-columns: 130px 200px 400px 120px;
  gap: 25px;
  align-items: center;
  width: 100%;
}

/* Style chung cho input & select */
.filter-row select,
.filter-row input[type="text"] {
  height: 40px;
  padding: 8px 12px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 8px;
  width: 100%;
  box-sizing: border-box;
}

/* Nút filter */
.filter-row button {
  height: 40px;
  background-color: #007bff;
  color: white;
  border: none;
  padding: 0 20px;
  border-radius: 8px;
  font-weight: 500;
  white-space: nowrap;
  cursor: pointer;
  transition: background-color 0.3s;
}

.filter-row button:hover {
  background-color: #0056b3;
}

/* Table styling */
.table {
  width: 100%;
  border-collapse: collapse;
  background-color: #fff;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 0 8px rgba(0, 0, 0, 0.05);
}

.table thead {
  background-color: #f8f9fa;
  font-weight: bold;
}

.table thead th {
  padding: 16px 12px;
  text-align: left;
  color: #333;
  border-bottom: 1px solid #dee2e6;
  white-space: nowrap;
}

.table tbody td {
  padding: 14px 12px;
  border-bottom: 1px solid #f1f1f1;
  vertical-align: middle;
  color: #444;
  font-size: 14px;
  font-weight: 500;
}

/* Zebra rows */
.table tbody tr:nth-child(even) {
  background-color: #fafafa;
}

/* Hover row */
.table-hover tbody tr:hover {
  background-color: #f0f8ff;
}

/* Badge style for payment & status */
.badge {
  font-size: 13px;
  padding: 5px 10px;
  border-radius: 12px;
  font-weight: 500;
  text-transform: capitalize;
}

/* Status colors */
.bg-warning { background-color: #ffc107; color: #000; }
.bg-info    { background-color: #17a2b8; color: #fff; }
.bg-success { background-color: #28a745; color: #fff; }
.bg-danger  { background-color: #dc3545; color: #fff; }

/* Pagination styling */
.pagination {
  margin-top: 24px;
}

.page-item {
  margin: 0 4px;
}

.page-link {
  border: 1px solid #dee2e6;
  color: #007bff;
  padding: 8px 14px;
  border-radius: 8px;
  transition: background-color 0.3s;
}

.page-link:hover {
  background-color: #e9f5ff;
  text-decoration: none;
}

.page-item.active .page-link {
  background-color: #007bff;
  color: white;
  border-color: #007bff;
}
.table td, .table th {
  vertical-align: top; /* Cho nội dung nằm sát trên */
  text-align: left;
  white-space: nowrap; /* Không xuống dòng lung tung */
}

/* Nhưng riêng Customer nên cho xuống dòng mềm */
.table td:nth-child(2) {
  white-space: normal;
  line-height: 1.4;
}
/* Cột ID - nhỏ gọn */
.table th:nth-child(1),
.table td:nth-child(1) {
  width: 60px;
  white-space: nowrap;
  text-align: center;
}

.table th:nth-child(2),
.table td:nth-child(2) {
  width: 220px;
  white-space: normal;
  line-height: 1.4;
  font-size: 16px;
  font-weight: 500;
  vertical-align: middle; /* thêm dòng này để căn giữa theo chiều dọc */
}

/* Footer style (tuỳ chỉnh nếu cần) */
footer {
  background-color: #f8f9fa;
  padding: 16px;
  text-align: center;
  font-size: 14px;
  color: #666;
  border-top: 1px solid #ddd;
}
.table td:nth-child(2) * {
  margin: 0;
  padding: 0;
}
.table td, .table th {
  vertical-align: middle;
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
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                   
                                    <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                        <li>
                                            <a href="index.html"><div class="text-tiny">Dashboard</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <a href="#"><div class="text-tiny">Order</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <div class="text-tiny">Order List</div>
                                        </li>
                                    </ul>
                                </div>
                                <!-- order-list -->
                                <div class="wg-box">
                                    <div class="flex items-center justify-between gap10 flex-wrap">
                                        <div class="wg-filter flex-grow">
                                         <div class="dashboard-main-body">
            <div class="d-flex flex-wrap align-items-center justify-content-between gap-3 mb-24">
                <h6 class="fw-semibold mb-0">Order Management</h6>
                
            </div>

            <!-- Filter Section -->
           <div class="card mb-24">
  <div class="card-body p-24">
    <form action="${pageContext.request.contextPath}/seller/manage-order" method="GET">
      <!-- Thêm class filter-row -->
      <div class="filter-row">
        <!-- Select Status -->
        <select class="form-select" name="status">
          <option value="">All Status</option>
          <option value="pending"   ${status == 'pending'   ? 'selected' : ''}>Pending</option>
          <option value="accepted"  ${status == 'accepted'  ? 'selected' : ''}>Accepted</option>
          <option value="completed" ${status == 'completed' ? 'selected' : ''}>Completed</option>
          <option value="cancelled" ${status == 'cancelled' ? 'selected' : ''}>Cancelled</option>
        </select>

        <!-- Select Payment Method -->
        <select class="form-select" name="paymentMethod">
          <option value="">All Payment Methods</option>
          <option value="Cash on Delivery" ${paymentMethod == 'Cash on Delivery' ? 'selected' : ''}>Cash on Delivery</option>
          <option value="Digital Wallet"   ${paymentMethod == 'Digital Wallet'   ? 'selected' : ''}>Digital Wallet</option>
          <option value="Bank Transfer"    ${paymentMethod == 'Bank Transfer'    ? 'selected' : ''}>Bank Transfer</option>
        </select>

        <!-- Ô Search -->
        <input type="text" class="form-control"
               name="search"
               placeholder="Search by order ID, customer name, email..."
               value="${search}"/>

        <!-- Nút Filter -->
        <button type="submit" class="btn btn-primary">Filter</button>
      </div>
    </form>
  </div>
</div>

            <!-- Orders Table -->
            <div class="card">
                <div class="card-body p-24">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Customer</th>
                                    <th>Address</th>
                                    <th>Total</th>
                                    <th>Payment Method</th>
                                    <th>Status</th>
                                    <th>Created Date</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty orders}">
                                    <tr>
                                        <td colspan="8" class="text-center">
                                            <div class="py-4">
                                                <i class="fas fa-search fs-1 text-muted mb-3"></i>
                                                <h5>No orders found</h5>
                                                <p class="text-muted">
                                                    <c:choose>
                                                        <c:when test="${not empty status || not empty search}">
                                                            No orders match your search criteria. Try adjusting your filters.
                                                            <br>
                                                            <a href="${pageContext.request.contextPath}/seller/manage-order" class="btn btn-outline-primary mt-2">
                                                                <i class="fas fa-times me-2"></i>Clear Filters
                                                            </a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            There are no orders in the system yet.
                                                        </c:otherwise>
                                                    </c:choose>
                                                </p>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                                    <c:forEach var="order" items="${orders}">
                                        <tr>
                                            <td>#${order.orderId}</td>
                                            <td>
                                                ${order.username}<br>
                                                <small class="text-muted">${order.email}</small><br>
                                                <small class="text-muted">${order.mobie}</small>
                                            </td>
                                            <td>${order.shippingAddress}</td>
                                            <td><fmt:formatNumber value="${order.total}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ</td>
                                            <td>
                                                <span class="badge bg-info">
                                                    ${order.paymentMethod}
                                                </span>
                                            </td>
                                            <td>
                                                <span class="badge ${order.status == 'pending' ? 'bg-warning' : 
                                                                order.status == 'accepted' ? 'bg-info' : 
                                                                order.status == 'completed' ? 'bg-success' : 'bg-danger'}">
                                                    ${order.status}
                                                </span>
                                            </td>
                                            <td><fmt:formatDate value="${order.createdAt}" pattern="dd/MM/yyyy HH:mm"/></td>
                                            <td>

                                                <div class="item eye">
                                                    <a href="${pageContext.request.contextPath}/seller/manage-order?action=view&id=${order.orderId}"  title="View Detial">
                                                        <i class="icon-eye"></i>
                                                    </a></div>

                                            </td>
                                        </tr>
                                    </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <!-- Pagination -->
                    <nav class="mt-24">
                        <ul class="pagination justify-content-center">
                            <c:if test="${currentPage > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="${pageContext.request.contextPath}/seller/manage-order?page=${currentPage - 1}&status=${status}&search=${search}">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:forEach begin="1" end="${totalPages}" var="i">
                                <li class="page-item ${i == currentPage ? 'active' : ''}">
                                    <a class="page-link" href="${pageContext.request.contextPath}/seller/manage-order?page=${i}&status=${status}&search=${search}">${i}</a>
                                </li>
                            </c:forEach>

                            <c:if test="${currentPage < totalPages}">
                                <li class="page-item">
                                    <a class="page-link" href="${pageContext.request.contextPath}/seller/manage-order?page=${currentPage + 1}&status=${status}&search=${search}">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
                                    </div>
                                </div>
                                <!-- /order-list -->
                            </div>
                            <!-- /main-content-wrap -->
                        </div>
                        <!-- /main-content-wrap -->
                        <!-- bottom-page -->
                        <footer>
                            <jsp:include page="../common/dash-board-seller/bottom-page.jsp"></jsp:include>
                        </footer>
                        
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
 <!-- Toast Container -->
        <div class="toast-container position-fixed bottom-0 end-0 p-3" style="z-index: 11">
            <div id="orderToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
                <div class="toast-header" id="toast-header">
                    <strong class="me-auto" id="toast-title">Thông báo</strong>
                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
                <div class="toast-body" id="toast-body"></div>
            </div>
        </div>
    <!-- Javascript -->
    <script src="${pageContext.request.contextPath}/js/jquery.min_1.js"></script>
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
            }
            
            // Check for messages in session
            <c:if test="${not empty sessionScope.successMessage}">
                document.addEventListener('DOMContentLoaded', function() {
                    showToast("${sessionScope.successMessage}", "success");
                    // Remove message from session
                    <% session.removeAttribute("successMessage"); %>
                });
            </c:if>
            
            <c:if test="${not empty sessionScope.errorMessage}">
                document.addEventListener('DOMContentLoaded', function() {
                    showToast("${sessionScope.errorMessage}", "error");
                    // Remove message from session
                    <% session.removeAttribute("errorMessage"); %>
                });
            </c:if>
        </script>
   
</body>


<!-- Mirrored from themesflat.co/html/remos/oder-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
</html>
