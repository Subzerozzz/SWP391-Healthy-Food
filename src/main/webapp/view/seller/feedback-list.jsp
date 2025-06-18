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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <!--Fontawsoem-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/izitoast/dist/css/iziToast.min.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/izi-toast.css"/>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">

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
/* Card layout */
.card {
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.05);
  padding: 16px 24px;
  margin-bottom: 24px;
}

/* Header (filter form) */
.card-header {
  padding-bottom: 16px;
  border-bottom: 1px solid #e0e0e0;
  margin-bottom: 16px;
}

/* Filter row: rating + search + button */
.filter-row {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  align-items: center;
}

.filter-row select,
.filter-row input[type="text"],
.filter-row button {
  padding: 8px 12px;
  border-radius: 6px;
  border: 1px solid #ccc;
  font-size: 14px;
}

.filter-row select {
  width: 150px;
}

.filter-row input[type="text"] {
  flex: 1;
  min-width: 200px;
}

.filter-row button {
  background-color: #007bff;
  color: white;
  border: none;
  transition: background-color 0.2s ease;
}

.filter-row button:hover {
  background-color: #0056b3;
}

/* Table styles */
.table {
  width: 100%;
  border-collapse: collapse;
  font-size: 14px;
}

.table th, .table td {
  padding: 12px 16px;
  text-align: left;
  vertical-align: middle;
  border-bottom: 1px solid #dee2e6;
}

.table th {
  background-color: #f8f9fa;
  font-weight: bold;
  color: #333;
}

.table-hover tbody tr:hover {
  background-color: #f1f1f1;
}

/* Star rating icons */
.table td i.fa-star {
  margin-right: 2px;
  font-size: 14px;
}

/* Action buttons */
/* Căn giữa tiêu đề "Action" */
.table th:nth-child(5),
.table td:nth-child(5) {
  text-align: center;
}

/* Action buttons - hiện đại hơn */
.action-group {
  display: flex;
  gap: 8px;
  justify-content: center;
  align-items: center;
}

/* Nút icon hiện đại */
.action-group .item {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  background-color: #f3f4f6; /* màu sáng */
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.2s ease-in-out;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

.action-group .item:hover {
  transform: scale(1.05);
  background-color: #e2e6ea;
}

/* Icon size & màu */
.action-group .item i {
  font-size: 16px;
}

/* Icon riêng biệt màu sắc */
.action-group .eye i {
  color: #0d6efd; /* xanh dương */
}

.action-group .edit i {
  color: #198754; /* xanh lá */
}

.action-group .trash i {
  color: #dc3545; /* đỏ */
}
/* Optional: Make sure card doesn't overflow footer */
body {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

main {
  flex-grow: 1;
}
       .text-center {
  text-align: center;
}

.py-4 {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 40px 0;
  min-height: 250px;
  color: #666;
}  
.py-4 a {
  color: #007bff;
  text-decoration: none;
  font-weight: 500;
  transition: color 0.2s ease;
  margin-left: 40%
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
                                            <a href="${pageContext.request.contextPath}/seller/manage-feedback"><div class="text-tiny">Feedbacks</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <a href="#"><div class="text-tiny">FeedBack List</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <div class="text-tiny">Feedback </div>
                                        </li>
                                    </ul>
                                </div>
                                <!-- order-detail -->
                                <!--start fix-->
                                
                                 <div class="dashboard-main-body">
                                     <div class="row g-24" >
                <!-- Feedback list -->
            <div class="card mt-24">
                <div class="card-header">
                    <form action="${pageContext.request.contextPath}/seller/manage-feedback" method="GET">
      <!-- Thêm class filter-row -->
      <div class="filter-row">
        <!-- Select Status -->
       <select name="rating">
    <option value="-1" ${param.rating == -1 ? 'selected' : ''}>ALl Rating </option>       
    <option value="1" ${param.rating == 1 ? 'selected' : ''}>1 </option> 
    <option value="2" ${param.rating == 2 ? 'selected' : ''}>2 </option>
    <option value="3" ${param.rating == 3 ? 'selected' : ''}>3 </option>
    <option value="4" ${param.rating == 4 ? 'selected' : ''}>4 </option>
    <option value="5" ${param.rating == 5 ? 'selected' : ''}>5 </option>
   </select>
    <!-- Bên cạnh có thể hiển thị preview -->

        <!-- Ô Search -->
        <input type="text" class="form-control"
               name="search"
               placeholder="Search by customer name,email..."
               value="${search}"/>

        <!-- Nút Filter -->
        <button type="submit" class="btn btn-primary">Filter</button>
      </div>
    </form>
                </div>
               
                 
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Customer</th>
                                    <th>Food</th>
                                    <th>Rating</th>
                                    <th>Action</th>
                                </tr>
                                
                            </thead>
                            <tbody>
                                  
                                <c:forEach var="feedback" items="${feedbacks}">
                                    <tr>
                                        <td>${feedback.id}</td>
                                         <td>${feedback.account.user_name}</td>
                                         <td>${feedback.food.name}</td>
                                       <td>
                                              <c:forEach begin="1" end="${feedback.rating}">
                                                  <i class="fa-solid fa-star" style="color: gold;"></i>
                                              </c:forEach>
                                          </td>
                                     <td>  
                                       <div  class="action-group">
                                           <div class="item eye">
                                               <a href="${pageContext.request.contextPath}/seller/manage-feedback?action=view&feedbackId=${feedback.id}" title="View Detail" >
                                                   <i class="icon-eye"></i>
                                               </a></div> 
                                           <div class="item trash">
                                               <a href="${pageContext.request.contextPath}/seller/manage-feedback?action=update&feedbackId=${feedback.id}" title="Hidden"
                                                  onclick="handleReject(event)">
                                                   <i class="fa-solid fa-xmark"></i>
                                               </a>         
                                           </div>    

                                       </div>
                                   </td>
                                    </tr>
                                </c:forEach>
                               
                            </tbody>
                        </table>
                    </div>
                    <div class="flex items-center justify-between flex-wrap gap10">
                        <div class="text-tiny">Showing 10 entries</div>
                        <ul class="wg-pagination">
                          
                          <li>
                            <a href="${pageContext.request.contextPath}/seller/manage-feedback?page=1"><i class="icon-chevron-left"></i></a>
                          </li>
                            <c:choose>
                                <c:when test="${currentPage <= totalPages - 2}">
                                    <c:if test="${currentPage > 1}">
                                         <li class="">
                                            <a href="${pageContext.request.contextPath}/seller/manage-feedback?page=${currentPage - 1}">${currentPage - 1}</a>
                                        </li>
                                    </c:if>
                                    <li class="active">
                                        <a href="${pageContext.request.contextPath}/seller/manage-feedback?page=${currentPage}">${currentPage}</a>
                                    </li>
                                    
                                    <li class="">
                                        <a href="${pageContext.request.contextPath}/seller/manage-feedback&page=${currentPage + 1}">${currentPage + 1}</a>
                                    </li>
                                    
                                    <c:if test="${currentPage < totalPages - 2}">
                                        <li>
                                            <span>...</span>
                                        </li>
                                    </c:if>
                                    
                                    
                                    <li class="">
                                        <a href="${pageContext.request.contextPath}/seller/manage-feedback?page=${totalPages}">${totalPages}</a>
                                    </li>
                                </c:when>
                                
                                <c:otherwise>
                                    <c:forEach begin="${totalPages-2 <= 0 ? 1 : totalPages - 2}" end="${totalPages}" var="i">
                                        <li class="${currentPage == i ? 'active' : ''}">
                                            <a href="${pageContext.request.contextPath}/seller/manage-feedback?page=${i}">${i}</a>
                                        </li>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                            
                          <li>
                            <a href="${pageContext.request.contextPath}/seller/manage-feedback?page=${totalPages}"><i class="icon-chevron-right"></i></a>
                          </li>
                        </ul>
                          
                    </div>
                     <c:if test="${empty feedbacks}">
                                    <tr>
                                        <td colspan="8" class="text-center">
                                            <div class="py-4">
                                                <i class="fas fa-search fs-1 text-muted mb-3"></i>
                                                <h5>No feedback found</h5>
                                                <p class="text-muted">
                                                    <c:choose>
                                                        <c:when test="${not empty status || not empty search}">
                                                            No feedback match your search criteria. Try adjusting your filters.
                                                            <br>
                                                            <a href="${pageContext.request.contextPath}/seller/manage-feedback" class="btn btn-outline-primary mt-2">
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
                        
                    </div>
                    <!-- /main-content -->
                    <!-- bottom-page -->
                        <jsp:include page="../common/dash-board-seller/bottom-page.jsp"></jsp:include>
                        <!-- /bottom-page -->
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
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
 
                                                                                                                                                                     
                                                                                                                                                                        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
                                                                                                                                                                        <script src="https://cdn.jsdelivr.net/npm/izitoast/dist/js/iziToast.min.js"></script>
                                                                                                                                                                        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/izitoast/dist/css/iziToast.min.css" />                                                                                                      <!--Alert Information about AccpetFood-->
        

  
      <c:if test="${sessionScope.isSuccess == true}">
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
     function handleReject(event) {
    event.preventDefault();

    const url = event.currentTarget.href;

    Swal.fire({
        title: 'Are you sure?\nThis action cannot be undone.',
        showCancelButton: true,
        confirmButtonText: 'Hidden',
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
            window.location.href = url ;
        }
    });
}
 </script>

</body>

</html>