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
                          
                          style for filter
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
            
/*             Responsive adjustments */
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


   .badge-modern {
    padding: 0.4rem 0.9rem;
    font-weight: 700;
    font-size: 0.85rem;
    border-radius: 2rem;
    text-transform: uppercase;
    position: relative;
    display: inline-block;
    overflow: hidden;
    box-shadow: 0 4px 10px rgba(0,0,0,0.2);
    transition: transform 0.2s ease-in-out;
}

.badge-modern::after {
    content: "";
    position: absolute;
    top: -50%;
    left: -50%;
    width: 200%;
    height: 200%;
    background: radial-gradient(circle, rgba(255,255,255,0.3) 0%, transparent 70%);
    transform: rotate(45deg);
    pointer-events: none;
}

.badge-modern:hover {
    transform: scale(1.05);
}

.badge-pending {
    background: linear-gradient(135deg, #ffea00, #ffd600); /* Vàng neon */
    color: #000;
}
.badge-accepted {
    background: linear-gradient(135deg, #00c6ff, #0072ff); /* Xanh biển sáng */
    color: #fff;
}
.badge-completed {
    background: linear-gradient(135deg, #00ff94, #00c853); /* Xanh lá tươi như bạc hà */
    color: #fff;
}
.badge-rejected {
    background: linear-gradient(135deg, #ff1744, #d50000); /* Đỏ neon */
    color: #fff;
}
.bg-info-glow {
    background: linear-gradient(135deg, #00c6ff, #0072ff); /* Xanh dương bóng loáng */
    color: #fff;
    box-shadow: 0 2px 8px rgba(0, 115, 255, 0.4); /* Đổ bóng nhẹ */
}
/* ======= TABLE ========== */
.table {
  width: 100%;
  border-collapse: separate;
  border-spacing: 0 8px; /* spacing giữa các dòng cho hiện đại */
  background-color: transparent;
  font-family: 'Segoe UI', sans-serif;
}

.table thead {
  background-color: transparent;
}

.table thead th {
  padding: 14px 18px;
  background-color: #f1f5f9;
  color: #334155;
  font-weight: 600;
  font-size: 14px;
  text-transform: uppercase;
  border: none;
  white-space: nowrap;
}

.table tbody tr {
  background-color: #ffffff;
  box-shadow: 0 4px 12px rgba(0,0,0,0.05);
  border-radius: 12px;
  transition: all 0.2s ease;
}

.table tbody tr:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(0,0,0,0.1);
}

.table tbody td {
   padding: 8px 10px;
  color: #000;
  font-size: 14px;
  border: none;
  vertical-align: middle;
  white-space: nowrap;
}

.table td:nth-child(2) {
  white-space: normal;
  line-height: 1.5;
  font-weight: 500;
}

.table td small {
  display: block;
  color: #000;
  font-size: 12px;
  margin-top: 2px;
}

/* ===== COLUMN WIDTH TUNING ===== */
.table th:nth-child(1),
.table td:nth-child(1) {
  width: 60px;
  text-align: center;
}
.table th:nth-child(2),
.table td:nth-child(2) {
  width: 220px;
}
.table th:nth-child(4),
.table td:nth-child(4),
.table th:nth-child(5),
.table td:nth-child(5),
.table th:nth-child(6),
.table td:nth-child(6) {
  text-align: center;
}
.table th:nth-child(7),
.table td:nth-child(7),
.table th:nth-child(8),
.table td:nth-child(8) {
  text-align: center;
  width: 100px;
}

/* ======= BADGE ========== */
.badge {
  font-size: 12px;
  padding: 6px 12px;
  border-radius: 999px;
  font-weight: 600;
  text-transform: uppercase;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-width: 100px;
}

/* Glowing style */
.bg-info-glow {
  background: linear-gradient(135deg, #00c6ff, #0072ff);
  color: #fff;
  box-shadow: 0 2px 8px rgba(0, 115, 255, 0.4);
}

/* Modern badge status */
.badge-modern {
  padding: 6px 14px;
  font-size: 12px;
  font-weight: 700;
  border-radius: 999px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-width: 100px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.06);
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

/* Badge colors */
.badge-pending {
  background: linear-gradient(135deg, #ffeb3b, #ffc107);
  color: #000;
}

.badge-accepted {
  background: linear-gradient(135deg, #00c6ff, #0072ff);
  color: #fff;
}

.badge-completed {
  background: linear-gradient(135deg, #00e676, #1de9b6);
  color: #fff;
}

.badge-rejected {
  background: linear-gradient(135deg, #ff1744, #d50000);
  color: #fff;
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
                                    <div class="">
                                        <div class="">
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
        <select  style="height: 40px;font-size: 15px;"
            class="form-select" name="status">
          <option value="">All Status</option>
          <option value="pending"   ${status == 'pending'   ? 'selected' : ''}>Pending</option>
          <option value="accepted"  ${status == 'accepted'  ? 'selected' : ''}>Accepted</option>
          <option value="completed" ${status == 'completed' ? 'selected' : ''}>Completed</option>
          <option value="cancelled" ${status == 'cancelled' ? 'selected' : ''}>Cancelled</option>
        </select>

        <!-- Select Payment Method -->
        <select    style="height: 40px;font-size: 15px; "
            class="form-select" name="paymentMethod">
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
                                    <th>Coupon Code</th>
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
                                        <c:set var="acc" value="${AccountMap[order.account_id]}"/>
                                        <tr>
                                         
                                            <td>${order.id}</td>
                                               <c:choose>
                                                   <c:when test="${not empty acc.user_name}">
                                                       <td>${acc.user_name}<br></td>
                                                     <td>  ${order.shipping_address} </td>
                                                   </c:when>
                                                   <c:otherwise>
                                                       <td>${order.full_name}</td>
                                                       <td>  ${order.shipping_address} </td>
                                                   </c:otherwise>
                                            </c:choose>
                                            
                                           
                                            <td><fmt:formatNumber value="${order.total}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ</td>
                                            <td>
                                                <span style="display: flex;align-items: center;justify-content: center;height: 25px;border-radius:15px"
                                                      class="badge bg-info-glow">
                                                    ${order.payment_method}
                                                </span>
                                            </td>
                                            <td>
<!--                                                <span class="badge ${order.status == 'pending' ? 'bg-warning' : 
                                                                order.status == 'accepted' ? 'bg-info' : 
                                                                order.status == 'completed' ? 'bg-success' : 'bg-danger'}">
                                                    ${order.status}
                                                </span>-->
                                                    <span style="display: flex;align-items: center;justify-content: center;height: 25px"
                                                          class="badge-modern ${
                                                          order.status == 'pending' ? 'badge-pending' :
                                                              order.status == 'accepted' ? 'badge-accepted' :
                                                              order.status == 'completed' ? 'badge-completed' :
                                                              'badge-rejected'
                                                          }">
                                                        ${order.status}
                                                    </span>
                                            </td>
                                           
                                             <td>${order.coupon_code}</td>
                                            <td>

                                                <div class="item eye">
                                                    <a href="${pageContext.request.contextPath}/seller/manage-order?action=view&id=${order.id}"  title="View Detial">
                                                        <i class="icon-eye"></i>
                                                    </a></div>

                                            </td>
                                        </tr>
                                    </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <!-- Pagination -->
                       <div class="pagination-wrapper">
                           <div class="text-tiny">Showing 10 entries</div>

                           <!--   Start Pagination-->
                           <ul class="pagination-wrapper">

                               <li >
                                   <a href="${pageContext.request.contextPath}/seller/manage-order?page=1&status=${status}&search=${search}"><i class="icon-chevron-left"></i></a>
                               </li>
                               <c:choose>
                                   <c:when test="${currentPage <= totalPages - 2}">
                                       <c:if test="${currentPage > 1}">
                                           <li class="">
                                               <a href="${pageContext.request.contextPath}/seller/manage-order?page=${currentPage - 1}&status=${status}&search=${search}">${currentPage - 1}</a>
                                           </li>
                                       </c:if>
                                       <li class="active">
                                           <a href="${pageContext.request.contextPath}/seller/manage-order?page=${currentPage}&status=${status}&search=${search}">${currentPage}</a>
                                       </li>

                                       <li class="">
                                           <a href="${pageContext.request.contextPath}/seller/manage-order?page=${currentPage + 1}&status=${status}&search=${search}">${currentPage + 1}</a>
                                       </li>

                                       <c:if test="${currentPage < totalPages - 2}">
                                           <li>
                                               <span>...</span>
                                           </li>
                                       </c:if>


                                       <li class="">
                                           <a href="${pageContext.request.contextPath}/seller/manage-order?page=${totalPages}&status=${status}&search=${search}">${totalPages}</a>
                                       </li>
                                   </c:when>

                                   <c:otherwise>
                                       <c:forEach begin="${totalPages-2 <= 0 ? 1 : totalPages - 2}" end="${totalPages}" var="i">
                                           <li class="${currentPage == i ? 'active' : ''}">
                                               <a href="${pageContext.request.contextPath}/seller/manage-order?page=${i}&status=${status}&search=${search}">${i}</a>
                                           </li>
                                       </c:forEach>
                                   </c:otherwise>
                               </c:choose>

                               <li>
                                   <a href="${pageContext.request.contextPath}/seller/manage-order?page=${totalPages}&status=${status}&search=${search}"><i class="icon-chevron-right"></i></a>
                               </li>
                           </ul>
                       </div>  
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
                    
                    </div>
               <jsp:include page="./../common/footer.jsp"></jsp:include>
                    <!-- /main-content -->
                </div>
                <!-- /section-content-right -->
            </div>
            <!-- /layout-wrap -->
        </div>
        <!-- /#page -->
    </div>
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
