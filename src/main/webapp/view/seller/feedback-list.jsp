<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
.feedback-table {
  width: 100%;
  border-collapse: separate;
  border-spacing: 0;
  font-family: 'Segoe UI', sans-serif;
  font-size: 14px;
  table-layout: fixed;
  background-color: #fff;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0,0,0,0.05);
}

/* Header */
.feedback-table thead th {
  background: linear-gradient(to right, #f0f4f8, #e0e7ef);
  color: #333;
  font-weight: 600;
  padding: 12px 10px;
  text-align: left;
  border-bottom: 2px solid #dee2e6;
}

/* Body cells */
.feedback-table tbody td {
  padding: 12px 10px;
  border-bottom: 1px solid #f0f0f0;
  color: #444;
  vertical-align: top;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

/* Content cột xuống dòng */
.feedback-table td:nth-child(4) {
  white-space: normal;
  word-break: break-word;
  max-height: 4.5em;
  line-height: 1.5em;
}

/* ===== Cố định chiều rộng hợp lý cho từng cột ===== */
.feedback-table th:nth-child(1), td:nth-child(1) { width: 50px; }     /* ID */
.feedback-table th:nth-child(2), td:nth-child(2) { width: 120px; }    /* Food */
.feedback-table th:nth-child(3), td:nth-child(3) { width: 130px; }    /* Rating */
.feedback-table th:nth-child(5), td:nth-child(5) { width: 150px; }    /* Customer */
.feedback-table th:nth-child(6), td:nth-child(6) { width: 100px; text-align: center; }
.feedback-table th:nth-child(7), td:nth-child(7) { width: 140px; }
.feedback-table th:nth-child(8), td:nth-child(8) { width: 80px; text-align: center; }

/* Action */
.action-group {
  display: flex;
  justify-content: center;
  gap: 10px;
}

.action-group .item {
  font-size: 16px;
  color: #666;
  transition: color 0.2s;
}

.action-group .item:hover {
  color: #007bff;
}

/* Rating */
.fa-star {
  color: #ffc107;
  font-size: 14px;
  margin-right: 2px;
}

/* Kiểu hiển thị badge status */
.status-badge {
  padding: 4px 10px;
  border-radius: 12px;
  font-size: 14px;
  display: inline-block;
  font-weight: normal;
  border: 1px solid transparent;
  min-width: 80px;       /* ✅ Cố định chiều rộng */
  text-align: center;     /* ✅ Căn giữa chữ */
}

/* Trạng thái Inactive */
.status-badge.inactive {
  background-color: #ffe5e5 !important;
  color: #cc0000 !important;
  border: 1px solid #cc0000 !important;
}

/* Trạng thái Active */
.status-badge.active {
  background-color: #e5f7e5 !important;
  color: #007700 !important;
  border: 1px solid #007700 !important;
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
/*css action*/
.action-group {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
}

/* Style chung cho nút */
.action-group .item {
  width: 25px;
  height: 25px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  border: 1px solid transparent;
  transition: all 0.25s ease;
  font-size: 15px;
}

/* View button - xanh */
.action-group .item.view {
  color: #4a90e2;
  border-color: #eaf3fc;
}

.action-group .item.view:hover {
  background-color: #e6f0ff;
  color: #0a58ca;
  border-color: #0a58ca;
}

/* Reject button - đỏ */
.action-group .item.reject {
  color: #e74c3c;
  border-color: #eaf3fc;
}

.action-group .item.reject:hover {
  background-color: #ffe6e9;
  color: #bb2d3b;
  border-color: #bb2d3b;
}
.pagination-wrapper {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  gap: 10px;
  padding: 10px 0;
  font-family: 'Segoe UI', sans-serif;
}

/* Dòng "Showing 10 entries" */
.pagination-wrapper .text-tiny {
  font-size: 14px;
  color: #888;
}

/* Phần ul chứa các trang */
.pagination-wrapper ul {
  display: flex;
  gap: 8px;
  list-style: none;
  margin: 0;
  padding: 0;
}

/* Mỗi item trong phân trang */
.pagination-wrapper ul li a,
.pagination-wrapper ul li span {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  width: 36px;
  height: 36px;
  font-size: 14px;
  border-radius: 50%;
  color: #555;
  background-color: #f1f1f1;
  border: none;
  text-decoration: none;
  transition: all 0.2s ease;
}

/* Hover hiệu ứng */
.pagination-wrapper ul li a:hover {
  background-color: #e0e7ff;
  color: #1d4ed8;
}

/* Nút hiện tại */
.pagination-wrapper ul li.active a {
  background-color: #001aff;
  color: white;
  font-weight: bold;
}

/* Dấu ... */
.pagination-wrapper ul li span {
  cursor: default;
  background-color: transparent;
  color: #aaa;
  font-weight: bold;
}

/* Mũi tên trái/phải */
.pagination-wrapper ul li a i {
  font-size: 14px;
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
    <!-- Select Status --><i class="fa-solid fa-layer-group fa-2x"></i>
    <!--select by Food-->
    <select name="selectFood">
        <option value="-1" ${param.selectFood == '-1' ? 'selected' : ''}>All Foods</option> 
        <c:forEach items="${lFood}" var="f">
            <option value="${f}" ${param.selectFood == f ? 'selected':''}>${f}</option> 
        </c:forEach>
    </select>
    
    
    <!--select by reating-->
    <select name="rating">
<option value="-1" ${param.rating == -1 ? 'selected' : ''}>ALL RATING </option>       
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
<button  style="width:100px"type="submit" class="btn btn-primary">Filter   <i class="fa-solid fa-filter"></i></button>
</div>
</form>
</div>
                <div class="card-body">
  <div class="table-responsive">
    <table class="table table-hover feedback-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Food</th>
          <th>Rating</th>
          <th>Content</th>
          <th>Customer Name</th>
          <th>Status</th>
          <th>Created At</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
          <c:if test="${empty feedbacks}">
                                    <tr>
                                        <td colspan="8" class="text-center">
                                            <div class="py-4" ">
                                                <i class="fas fa-search fs-1 text-muted mb-3"></i>
                                                <h5>No feedback found</h5>
                                                <p class="text-muted">
                                                    <c:choose>
                                                        <c:when test="${not empty status || not empty search}">
                                                            No Feedback match your search criteria. Try adjusting your filters.
                                                            <br>
                                                            <a href="${pageContext.request.contextPath}/seller/manage-feedback" class="btn btn-outline-primary mt-2"
                                                               style="margin-right:150px">
                                                                <i class="fas fa-times me-2"></i>Clear Filters
                                                            </a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            There are no feedback in the system yet.
                                                        </c:otherwise>
                                                    </c:choose>
                                                </p>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
        <c:forEach var="feedback" items="${feedbacks}">
          <c:set var="account" value="${AccountMap[feedback.user_id]}" />
          <c:set var="food" value="${FoodMap[feedback.order_item_id]}" />
          <tr>
            <td>${feedback.id}</td>
            <td><a href="${pageContext.request.contextPath}/seller/manage-feedback?action=food&food_id=${food.id}">
                ${food.name}
            </a></td>
            <td>
                <c:forEach begin="1" end="${feedback.rating}">
                    <i class="fa-solid fa-star"></i>
                </c:forEach>
            </td>
            <td class="content-cell">
              <c:choose>
                <c:when test="${fn:length(feedback.content) > 100}">
                  ${fn:substring(feedback.content, 0, 100)}...
                </c:when>
                <c:otherwise>
                  ${feedback.content}
                </c:otherwise>
              </c:choose>
            </td>
                <td><a href="${pageContext.request.contextPath}/seller/manage-feedback?action=account&account_id=${account.id}">
                        ${account.user_name}
                    </a></td>
            <td>
                <span class="status-badge ${feedback.visible ? 'active' : 'inactive'}">
                    ${feedback.visible ? 'Active' : 'Inactive'}
                </span>
            </td>
            <td>
              <fmt:formatDate value="${feedback.createdAt}" pattern="dd/MM/yyyy HH:mm" />
            </td>
                <td>
                  <div class="action-group">
                      <a class="item view" href="${pageContext.request.contextPath}/seller/manage-feedback?action=view&feedbackId=${feedback.id}" title="View Detail">
                          <i class="fa-solid fa-eye"></i>
                      </a>
                      <c:choose>
                          <c:when test="${feedback.visible}">
                        <a class="item reject" href="${pageContext.request.contextPath}/seller/manage-feedback?action=update&feedbackId=${feedback.id}" title="Hidden" onclick="handleReject(event)">
                          <i class="fa-solid fa-xmark"></i>
                        </a>
                          </c:when>
                      </c:choose>
                      
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
<a href="${pageContext.request.contextPath}/seller/manage-feedback?page=1&rating=${rating}&search=${search}"><i class="icon-chevron-left"></i></a>
</li>
<c:choose>
    <c:when test="${currentPage <= totalPages - 2}">
        <c:if test="${currentPage > 1}">
                <li class="">
                <a href="${pageContext.request.contextPath}/seller/manage-feedback?page=${currentPage - 1}&rating=${rating}&search=${search}">${currentPage - 1}</a>
            </li>
        </c:if>
        <li class="active">
            <a href="${pageContext.request.contextPath}/seller/manage-feedback?page=${currentPage}&rating=${rating}&search=${search}">${currentPage}</a>
        </li>

        <li class="">
            <a href="${pageContext.request.contextPath}/seller/manage-feedback?page=${currentPage + 1}&rating=${rating}&search=${search}">${currentPage + 1}</a>
        </li>

        <c:if test="${currentPage < totalPages - 2}">
            <li>
                <span>...</span>
            </li>
        </c:if>


        <li class="">
            <a href="${pageContext.request.contextPath}/seller/manage-feedback?page=${totalPages}&rating=${rating}&search=${search}">${totalPages}</a>
        </li>
    </c:when>

    <c:otherwise>
        <c:forEach begin="${totalPages-2 <= 0 ? 1 : totalPages - 2}" end="${totalPages}" var="i">
            <li class="${currentPage == i ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/seller/manage-feedback?page=${i}&rating=${rating}&search=${search}">${i}</a>
            </li>
        </c:forEach>
    </c:otherwise>
</c:choose>

<li>
<a href="${pageContext.request.contextPath}/seller/manage-feedback?page=${totalPages}&rating=${rating}&search=${search}"><i class="icon-chevron-right"></i></a>
</li>
</ul>

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

</div>
<!-- /main-content -->
<!-- bottom-page -->
<jsp:include page="./../common/footer.jsp"></jsp:include>
<!-- /bottom-page -->
</div>
<!-- /section-content-right -->
</div>
<!-- /layout-wrap -->
</div>
<!-- /#page -->
</div>
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