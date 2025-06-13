<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->


    <!-- Mirrored from themesflat.co/html/remos/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:43:22 GMT -->
    <head>
        <!-- Basic Page Needs -->
        <meta charset="utf-8">
            <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
            <title>List Request</title>

            <meta name="author" content="themesflat.com">

                <!-- Mobile Specific Metas -->
                <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

                    <!-- Theme Style -->
                    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.css">
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
                                                    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleButton.css"/>
                                                    <!-- Favicon and Touch Icons  -->
                                                    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon_1.png">
                                                        <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/images/favicon_1.png">
                                                            <!-- iziToast CSS -->
                                                            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/izitoast/dist/css/iziToast.min.css">
                                                                <link rel="stylesheet" href="${pageContext.request.contextPath}/css/izi-toast.css"/>
                                                                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
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
                
  .icon-actions {
    display: flex;
    gap: 30px;
    align-items: center;
}

.icon-btn {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 10px;
    height: 10px;
    border-radius: 6px;
    font-size: 18px;
    text-decoration: none;
    transition: background 0.2s ease-in-out;
}

/* Màu sắc chủ đạo */
/*.view-btn {
    color: #3498db;
}*/



/* Hover thêm nền cùng tông */
.view-btn:hover {
    background-color: rgba(52, 152, 219, 0.15);
}

.accept-btn:hover {
    background-color: rgba(39, 174, 96, 0.15);
}

.reject-btn:hover {
    background-color: rgba(231, 76, 60, 0.15);
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
<div class="d-flex flex-wrap align-items-center justify-content-between gap-3 mb-24">
                <h6 class="fw-semibold mb-0">Order Management</h6>
                
            </div>
                                                                  <!-- product-list -->

                                                                  <!--Message about Alert-->
                                                                  <div id="mess" ><input type="hidden" name="name" value="1"></div> 

<div class="card mb-24">
                <div class="card-body p-24">
                    <form action="${pageContext.request.contextPath}/seller/manage-order" method="post">
                                                    
                                                      <div class="row g-3" style="height: 50px">
                            <div class="col-md-3">
                                <select class="form-select" name="status">
                                    <option value="">All Status</option>
                                    <option value="pending" ${status == 'pending' ? 'selected' : ''}>Pending</option>
                                    <option value="accepted" ${status == 'accepted' ? 'selected' : ''}>Accepted</option>
                                    <option value="completed" ${status == 'completed' ? 'selected' : ''}>Completed</option>
                                    <option value="cancelled" ${status == 'cancelled' ? 'selected' : ''}>Cancelled</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <select class="form-select" name="paymentMethod">
                                    <option value="">All Payment Methods</option>
                                    <option value="Cash on Delivery" ${paymentMethod == 'Cash on Delivery' ? 'selected' : ''}>Cash on Delivery</option>
                                    <option value="Digital Wallet" ${paymentMethod == 'Digital Wallet' ? 'selected' : ''}>Digital Wallet</option>
                                    <option value="Bank Transfer" ${paymentMethod == 'Bank Transfer' ? 'selected' : ''}>Bank Transfer</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input style="height:30px" type="text" class="form-control" name="search" placeholder="Search by order ID, customer name, email..."
                                       value="${search}">
                            </div>
                            <div class="col-md-2">
                                <button type="submit" class="btn btn-primary w-100">Filter</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

                                                                  <table>
                                                                      <thead  >
                                                                          <tr>
                                                                              <th style="width: 60px">ID</th>
                                                                           <th style="width: 160px">Customer</th>
                                                                           <th>Address</th>
                                                                           <th>Total</th>
                                                                           <th>Payment Method</th>
                                                                           <th>Status</th>
                                                                           <th>Created Date</th>
                                                                           <th >Actions</th>
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
                                                                          <c:forEach items="${orders}" var="order">
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
                                                                                  
                                                                                  
                                                                                  
                                                                                  <td>  <div class="icon-actions">
    <a href="${pageContext.request.contextPath}/seller/manage-order?action=view&orderId=${order.orderId}" title="View Detail" class="icon-btn view-btn">
        <i class="icon-eye"></i>
    </a>

    <a href="type-of-request?action=accept&select=&id=" onclick="handleAccept(event)" title="Accept" class="icon-btn accept-btn">
        <i style=" color: #27ae60;" class="icon-edit-3"></i>
    </a>

    <a href="type-of-request?action=reject&select=&id=" onclick="handleReject(event)" title="Reject" class="icon-btn reject-btn">
        <i style="color: #e74c3c;" class="icon-trash-2"></i>
    </a>
</div>
                                                                                      </td>
                                                                                               </tr>
                                                                          </c:forEach>
                                                                      </tbody>
                                                                  </table>
                            
                              <div class="flex items-center justify-between flex-wrap gap10">
                                                                          <div class="text-tiny">Showing 10 entries</div>
                                                                          <ul class="wg-pagination">
                                                                              <li>
                                                                                  <a href="#"><i class="icon-chevron-left"></i></a>
                                                                              </li>
                                                                              <c:forEach var="i" begin="1" end="${totalPages}">
                                                                                  <c:choose>
                                                                                      <c:when test="${i == currentPage}">
                                                                                          <li style="color: white;background-color: #0000ff;border-radius: 50%;height: 40px;width: 40px;display: flex;justify-content: center;align-items: center">${i}</li>
                                                                                          </c:when>
                                                                                          <c:otherwise>
                                                                                          <li>
                                                                                              <a href="type-of-request?page=${i}&action=option&select=${select}">${i}</a>   
                                                                                          </li>

                                                                                      </c:otherwise>
                                                                                  </c:choose>
                                                                              </c:forEach> 
                                                                              <li>
                                                                                  <a href="#"><i class="icon-chevron-right"></i></a>
                                                                              </li>
                                                                          </ul>
                                                                      </div>
                                                                                  </div>

                                                                                  <!--com-->
                                                                                 



                                                                                                                                                                        </div>
                                                                                                                                                                        <!--end table-->

                                                                <!-- Pagination -->
<!--                    <nav class="mt-24">
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
                    </nav>                                                                                                          </div>-->
<!--
-->                                                                    
                                              </div>
                                              <!-- /product-list -->
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
<script src="${pageContext.request.contextPath}/js/apexcharts/apexcharts.js"></script>
<script src="${pageContext.request.contextPath}/js/apexcharts/line-chart-1.js"></script>
<script src="${pageContext.request.contextPath}/js/apexcharts/line-chart-2.js"></script>
<script src="${pageContext.request.contextPath}/js/apexcharts/line-chart-3.js"></script>
<script src="${pageContext.request.contextPath}/js/apexcharts/line-chart-4.js"></script>
<script src="${pageContext.request.contextPath}/js/apexcharts/line-chart-5.js"></script>
<script src="${pageContext.request.contextPath}/js/apexcharts/line-chart-6.js"></script>
<script src="${pageContext.request.contextPath}/js/switcher.js"></script>
<script src="${pageContext.request.contextPath}/js/theme-settings.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>

<!-- iziToast JS -->


<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdn.jsdelivr.net/npm/izitoast/dist/js/iziToast.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/izitoast/dist/css/iziToast.min.css" />                                                                                                      <!--Alert Information about AccpetFood-->
<c:if test="${isSuccess == true}">
    <script>
      document.addEventListener("DOMContentLoaded", function () {
        iziToast.error({
            title: "Notification",
            message: "Processed successfully",
            position: 'topRight',
            timeout: 5000,
            backgroundColor:"#d4edda"
            });
      });
    </script>
    <!--Xóa đi biến isDelete sau khi đã thông báo--> 
    <%
        session.removeAttribute("isSuccess");
    %>
</c:if>
<script>

    function handleAccept(event) {
    event.preventDefault(); // Ngăn hành vi mặc định

    const url = event.currentTarget.href; // Lấy URL từ <a>

    Swal.fire({
      title: 'Are you sure?\nThis action cannot be undone.',
    showCancelButton: true,
    confirmButtonText: 'Accept',
    cancelButtonText: 'Cancel',
    reverseButtons: true,
    background: '#ffffff',
    showCloseButton: true,
    customClass: {
        popup: 'custom-swal-popup',
        title: 'custom-swal-title',
        confirmButton: 'custom-swal-confirm',
        cancelButton: 'custom-swal-cancel'
    },
    buttonsStyling: false
    }).then((result) => {
        if (result.isConfirmed) {
            localStorage.setItem('showSuccessToast', 'true');
            window.location.href = url + '&actionStatus=accept';
        } else if (result.isDenied) {
            localStorage.setItem('showRejectToast', 'true');
            window.location.href = url + '&actionStatus=reject';
        }
    });
}

 function handleReject(event) {
    event.preventDefault();

    const url = event.currentTarget.href;

    Swal.fire({
        title: 'Are you sure?\nThis action cannot be undone.',
        showCancelButton: true,
        confirmButtonText: 'Reject',
        cancelButtonText: 'Cancel',
        reverseButtons: true,
        background: '#ffffff',
        showCloseButton: true,
        customClass: {
            popup: 'custom-swal-popup',
            title: 'custom-swal-title',
            confirmButton: 'custom-swal-confirm',
            cancelButton: 'custom-swal-cancel'
        },
        buttonsStyling: false
    }).then((result) => {
        if (result.isConfirmed) {
            localStorage.setItem('showRejectToast', 'true');
            window.location.href = url + '&actionStatus=reject';
        }
    });
}





                                                                                                                                                                        </script>
                                                                                                                                                                        </body>


                                                                                                                                                                        <!-- Mirrored from themesflat.co/html/remos/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:12 GMT -->
                                                                                                                                                                        </html>
