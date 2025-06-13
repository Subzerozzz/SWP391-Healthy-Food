<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<!--<![endif]-->


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
            /* Áp dụng cho hàng filter */
.filter-row{
    /* Flexbox để căn hàng ngang, cho phép wrap xuống dòng khi hẹp */
    display:flex;
    flex-wrap:wrap;
    gap:1rem;                 /* khoảng cách giữa các ô */
}

/* Mỗi phần tử con (select, input, button) chung 1 style */
.filter-row > *{
    flex:1 1 220px;           /*  grow   shrink   basis  */
    min-width:160px;          /* đừng nhỏ hơn 160 px */
}

/* Riêng nút Filter hơi nhỏ hơn ở desktop để trông cân đối */
@media (min-width: 768px){    /* ≥ md */
    .filter-row .btn{
        flex:0 0 150px;       /* nút cố định ~150px */
    }
}
.table-responsive {
    border-radius: 12px;
    overflow: hidden;
    box-shadow: 0 0 10px rgba(0,0,0,0.05);
}

.table {
    margin-bottom: 0;
    font-size: 15px;
}

.table th {
    background-color: #f8f9fa;
    font-weight: 600;
    color: #333;
    vertical-align: middle;
}

.table td {
    vertical-align: middle;
}

/* Badge cho status */
.badge {
    font-size: 0.85rem;
    padding: 6px 12px;
    border-radius: 20px;
    font-weight: 500;
    text-transform: capitalize;
}

.bg-warning {
    background-color: #ffc107 !important;
    color: #212529;
}

.bg-info {
    background-color: #17a2b8 !important;
    color: white;
}

.bg-success {
    background-color: #28a745 !important;
    color: white;
}

.bg-danger {
    background-color: #dc3545 !important;
    color: white;
}

/* View button */
.view-btn {
    display: inline-flex;
    align-items: center;
    gap: 6px;
    padding: 6px 12px;
    font-size: 14px;
    background-color: #f1f3f5;
    border-radius: 6px;
    color: #333;
    text-decoration: none;
    transition: all 0.3s ease;
}

.view-btn:hover {
    background-color: #dbeafe;
    color: #1d4ed8;
}

.view-icon {
    display: flex;
    align-items: center;
    justify-content: center;
}

.text-center {
    text-align: center;
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
                                                            <a href="${pageContext.request.contextPath}/admin/manage-order" class="btn btn-outline-primary mt-2">
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
<!--                                            <a href="${pageContext.request.contextPath}/seller/manage-order?action=view&id=${order.orderId}"
                                               class="view-btn">
                                                <span class="view-icon">
                                                    <iconify-icon icon="heroicons:eye" width="20" height="20"></iconify-icon>
                                                </span>
                                                <span class="view-text">View Details</span>
                                            </a>-->
                                            <div style="display: flex">
                                            <div class="item eye">
                                                <a href="${pageContext.request.contextPath}/seller/manage-order?action=view&id=${order.orderId}" >
                                                    <i class="icon-eye"></i>
                                                </a></div>
                                            <div class="item edit">
                                                <a href="type-of-request?action=accept&select=${foodD.type}&id=${foodD.id}" onclick="handleAccept(event)" title="Accept">
                                                    <i class="icon-edit-3"></i>
                                                </a>
                                            </div>

                                            <a href="type-of-request?action=reject&select=${foodD.type}&id=${foodD.id}" onclick="handleReject(event)" title="Reject">
                                                <div class="item trash">
                                                    <i class="icon-trash-2"></i>
                                                </div>    
                                            </a>
                                                </div>
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
                                    <a class="page-link" href="${pageContext.request.contextPath}/admin/manage-order?page=${currentPage - 1}&status=${status}&search=${search}">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:forEach begin="1" end="${totalPages}" var="i">
                                <li class="page-item ${i == currentPage ? 'active' : ''}">
                                    <a class="page-link" href="${pageContext.request.contextPath}/admin/manage-order?page=${i}&status=${status}&search=${search}">${i}</a>
                                </li>
                            </c:forEach>

                            <c:if test="${currentPage < totalPages}">
                                <li class="page-item">
                                    <a class="page-link" href="${pageContext.request.contextPath}/admin/manage-order?page=${currentPage + 1}&status=${status}&search=${search}">
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
    <script src="${pageContext.request.contextPath}/js/jquery.min_1.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/zoom.js"></script>
    <script src="${pageContext.request.contextPath}/js/switcher.js"></script>
    <script src="${pageContext.request.contextPath}/js/theme-settings.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>

</body>


<!-- Mirrored from themesflat.co/html/remos/oder-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
</html>