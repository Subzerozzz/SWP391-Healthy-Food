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
      <!-- iziToast CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/izitoast/dist/css/iziToast.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/izi-toast.css" />
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
  grid-template-columns:  150px 500px  100px ; /* 3 cột */
  gap: 50px;
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

.bg-info-glow {
    background: linear-gradient(135deg, #00c6ff, #0072ff); /* Xanh dương bóng loáng */
    color: #fff;
    box-shadow: 0 2px 8px rgba(0, 115, 255, 0.4); /* Đổ bóng nhẹ */
}
/* ======= TABLE V2 (Modern) ========== */
.table {
  width: 100%;
  border-collapse: separate;
  border-spacing: 0 10px;
  font-family: 'Segoe UI', sans-serif;
  table-layout: fixed;
}

.table thead th {
  padding: 16px 20px;
  background-color: #f8f9fa;
  color: #495057;
  font-weight: 600;
  font-size: 13px;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  border: none;
  white-space: nowrap;
  text-align: left;
}

.table tbody tr {
  background-color: #ffffff;
  box-shadow: 0 2px 10px rgba(0,0,0,0.04);
  border-radius: 8px;
  transition: all 0.25s ease-in-out;
}

.table tbody tr:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 15px rgba(0,0,0,0.08);
}

.table tbody td {
  padding: 16px 20px;
  color: #212529;
  font-size: 14px;
  border: none;
  vertical-align: middle;
  white-space: normal;
  line-height: 1.6;
  word-wrap: break-word;
}

/* Column Alignment */
.table th, .table td {
    text-align: left;
    vertical-align: middle;
}
.table th:nth-child(2), .table td:nth-child(2),
.table th:nth-child(3), .table td:nth-child(3),
.table th:nth-child(4), .table td:nth-child(4) {
  text-align: center;
}

/* Column Widths */
.table th:nth-child(1) { width: 40%; } /* Shipper */
.table th:nth-child(2) { width: 25%; } /* Delivery order number */
.table th:nth-child(3) { width: 15%; } /* Status */
.table th:nth-child(4) { width: 20%; } /* Actions */

/* Actions icons styling */
.actions-container {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 10px;
}
.table .item a {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 32px;
    height: 32px;
    border-radius: 50%;
    background-color: #f1f5f9;
    transition: all 0.2s ease;
}
.table .item a:hover {
    background-color: #e2e8f0;
    transform: scale(1.1);
}
.table .item i {
    font-size: 16px;
    color: #1cc88a;
    transition: color 0.2s;
}
.table .item a:hover i {
    color: #13855c;
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

.badge-pending {
    background-color: #FFF8E1; /* vàng nhạt */
    color: #8D6E63;
}

.badge-accepted {
    background-color: #E3F2FD; /* xanh biển nhạt */
    color: #1565C0;
}

.badge-completed {
    background-color: #E8F5E9; /* xanh lá rất nhạt */
    color: #2E7D32;
}

.badge-rejected {
    background-color: #FFEBEE; /* đỏ nhạt */
    color: #C62828;
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
                                            <a href="#"><div class="text-tiny">Dashboard</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <a href="${pageContext.request.contextPath}/seller/manage-delivery"><div class="text-tiny">Deliveries List</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <a href="#"><div class="text-tiny">Shipper List</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <div class="text-tiny">Delivery Order ${de.order_id}</div>
                                        </li>
                                    </ul>
                                </div>
                                <!-- order-list -->
                                <div class="wg-box">
                                    <div class="">
                                        <div class="">
                                         <div class="dashboard-main-body">
            
                                             <!-- Filter Section -->
                                             <div class="card mb-24">
                                                 <div class="card-body p-24">
                                                     <form action="${pageContext.request.contextPath}/seller/manage-delivery" method="GET">
                                                         <input type="hidden" name="action" value="shipper" />
                                                         <input type="hidden" name="id" value="${id}" />

                                                         <div class="filter-row">
                                                             <!-- Sort by name -->
                                                             <select name="sort" class="form-select">
                                                                 <option value="" ${sort == '' ? 'selected' : ''}>Sort by Name</option>
                                                                 <option value="asc" ${sort == 'asc' ? 'selected' : ''}>A - Z</option>
                                                                 <option value="desc" ${sort == 'desc' ? 'selected' : ''}>Z - A</option>
                                                             </select>

                                                             <!-- Search by shipper name -->
                                                             <input type="text"
                                                                    class="form-control"
                                                                    name="search"
                                                                    placeholder="Search by shipper name,email,..."
                                                                    value="${search}" />

                                                             <!-- Filter button -->
                                                             <button type="submit" class="btn btn-primary">
                                                                 Filter <i class="fa-solid fa-filter"></i>
                                                             </button>

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
                                    <th>Shipper</th>
                                    <th>Delivery order number</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                   
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty shipperList}">
                                    <tr>
                                        <td colspan="8" class="text-center">
                                            <div class="py-4">
                                                <i class="fas fa-search fs-1 text-muted mb-3"></i>
                                                <h5>No Shipper found</h5>
                                                <p class="text-muted">
                                                    <c:choose>
                                                        <c:when test="${not empty status || not empty search}">
                                                            No shipper found. Try adjusting your filters.
                                                            <br>
                                                            <a href="${pageContext.request.contextPath}/seller/manage-delivery?action=shipper&id=${de.id}" class="btn btn-outline-primary mt-2">
                                                                <i class="fas fa-times me-2"></i>Clear Filters
                                                            </a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            There are no shipper in the system yet.
                                                        </c:otherwise>
                                                    </c:choose>
                                                </p>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                                    <c:forEach var="shipper" items="${shipperList}">
                                      
                                        <tr>
                                            <td>
                                                
                                                ${shipper.user_name}
                                            </td>
                                         

                                            <td>
                                                ${deliveryDAO.getNumberDeliveryOfShipperPending(shipper.id)}
                                            </td>
                                            <td>
                                                <span style="display: flex;align-items: center;justify-content: center;height: 25px; border:solid #6c757d "
                                                          class="badge-modern ${
                                                              deliveryDAO.getNumberDeliveryOfShipper(shipper.id) > 0 ? 'badge-pending' :
                                                              deliveryDAO.getNumberDeliveryOfShipper(shipper.id) > 0 ? 'badge-accepted' :
                                                           'badge-rejected'
                                                          }">
                                                   
                                                       ${deliveryDAO.getNumberDeliveryOfShipper(shipper.id) > 0 ? 'Đang giao' : 'Rảnh'}
                                                    </span>
                                            </td>
                                          
                                       <td>
                                            <div class="actions-container">
                                                <c:if test="${deliveringCountMap[shipper.id] == 0 && de.status == 'pending'}">
                                                    <div class="item edit">
                                                        <a href="${pageContext.request.contextPath}/seller/manage-delivery?action=add&id=${id}&shipper_id=${shipper.id}" 
                                                           onclick="handleAccept(event)" title="Choose Shipper">
                                                            <i class="fa-solid fa-motorcycle"></i>
                                                        </a>
                                                    </div>
                                                </c:if>
                                            </div>
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
                                   <a href="${pageContext.request.contextPath}/seller/manage-delivery?action=shipper&id=${de.id}&page=1&search=${search}&sort=${sort}"><i class="icon-chevron-left"></i></a>
                               </li>
                               <c:choose>
                                   <c:when test="${currentPage <= totalPages - 2}">
                                       <c:if test="${currentPage > 1}">
                                           <li class="">
                                               <a href="${pageContext.request.contextPath}/seller/manage-delivery?action=shipper&id=${de.id}&page=${currentPage - 1}&search=${search}&sort=${sort}">${currentPage - 1}</a>
                                           </li>
                                       </c:if>
                                       <li class="active">
                                           <a href="${pageContext.request.contextPath}/seller/manage-delivery?action=shipper&id=${de.id}&page=${currentPage}&search=${search}&sort=${sort}">${currentPage}</a>
                                       </li>

                                       <li class="">
                                           <a href="${pageContext.request.contextPath}/seller/manage-delivery?action=shipper&id=${de.id}&page=${currentPage + 1}&search=${search}&sort=${sort}">${currentPage + 1}</a>
                                       </li>

                                       <c:if test="${currentPage < totalPages - 2}">
                                           <li>
                                               <span>...</span>
                                           </li>
                                       </c:if>


                                       <li class="">
                                           <a href="${pageContext.request.contextPath}/seller/manage-delivery?action=shipper&id=${de.id}&page=${totalPages}&search=${search}&sort=${sort}">${totalPages}</a>
                                       </li>
                                   </c:when>

                                   <c:otherwise>
                                       <c:forEach begin="${totalPages-2 <= 0 ? 1 : totalPages - 2}" end="${totalPages}" var="i">
                                           <li class="${currentPage == i ? 'active' : ''}">
                                               <a href="${pageContext.request.contextPath}/seller/manage-delivery?action=shipper&id=${de.id}&page=${i}&search=${search}&sort=${sort}">${i}</a>
                                           </li>
                                       </c:forEach>
                                   </c:otherwise>
                               </c:choose>

                               <li>
                                   <a href="${pageContext.request.contextPath}/seller/manage-delivery?action=shipper&id=${de.id}&page=${totalPages}&search=${search}&sort=${sort}"><i class="icon-chevron-right"></i></a>
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
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
            const successMessage = "${sessionScope.successMessage}";
            const errorMessage = "${sessionScope.errorMessage}";

            document.addEventListener('DOMContentLoaded', function() {
                if (successMessage) {
                    showToast(successMessage, "success");
                }
                if (errorMessage) {
                    showToast(errorMessage, "error");
                }
            });
        </script>
        
        <%-- Remove session attributes after rendering the script to show them --%>
        <% if (session.getAttribute("successMessage") != null) {
                session.removeAttribute("successMessage");
           }
           if (session.getAttribute("errorMessage") != null) {
                session.removeAttribute("errorMessage");
           }
        %>
    <script>//
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
          window.location.href = url + '&action=add';
        }
      });
    }

  </script>
</body>


<!-- Mirrored from themesflat.co/html/remos/oder-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
</html>
