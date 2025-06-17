<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
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
                                                                                        <jsp:include page="../common/sidebar.jsp"></jsp:include>
                                                                                            <!-- /section-menu-left -->
                                                                                            <!-- section-content-right -->
                                                                                            <div class="section-content-right">
                                                                                                <!-- header-dashboard -->
                                                                                            <jsp:include page="../common/headerDashboard.jsp"></jsp:include>
                                                                                                <!-- /header-dashboard -->

                                                                                                <!-- main-content -->
                                                                                                <div class="main-content">
                                                                                                    <!-- main-content-inner -->
                                                                                                    <div class="main-content-inner">
                                                                                                        <!-- main-content-wrap -->
                                                                                                        <div class="main-content-wrap">

                                                                                                            

                                                                                                            <!--Message about Alert-->
                                                                                                            <div id="mess" ><input type="hidden" name="name" value="1"></div> 

                                                                                                            <!--start filter-->
                                                                                                            <div class="filter">
                                                                                                                <div  style=" ; height: 40px;">
                                                                                                                    <form style="display: flex;" action="${pageContext.request.contextPath}/type-of-request" method="get" ">
                                                                                                                    <input type="hidden" name="action" value="option">
                                                                                                                        <select  name="select" class="selected">
                                                                                                                            <option style="display: flex;align-items: center" value="-1" ${param.select == '-1' ? 'selected' : ''}>--All Type Of Request--</option>
                                                                                                                            <c:forEach items="${type}" var="t">
                                                                                                                                <option value="${t}" ${param.select == t ? 'selected' : ''}>--${t}--</option>
                                                                                                                                <i class="ti-arrow-down" style="color: white; font-size: 15px;"></i>
                                                                                                                            </c:forEach>
                                                                                                                        </select>
                                                                                                                        <button style="margin-left: 20px;justify-content: center;width: 100px;height: 45px;
                                                                                                                                align-items: center;border-radius:60px" type="submit" class="filter-btn" >Submit</button>

                                                                                                                </form>  
                                                                                                            </div>    
                                                                                                            <div>
                                                                                                                <form class="form-search flex-grow" action="${pageContext.request.contextPath}/type-of-request" method="post">
                                                                                                                    <input type="hidden" name="action" value="search">
                                                                                                                        <fieldset class="name" >
                                                                                                                            <input style="height: 47px;width: 500px;margin-top: 5px;background-color: #fff;" type="text" 
                                                                                                                                   placeholder="Search here..." class="show-search" name="name" tabindex="2" value=""
                                                                                                                                   aria-required="true" required=""/>
                                                                                                                        </fieldset>
                                                                                                                        <div class="button-submit" ">
                                                                                                                            <button type="submit"><i class="icon-search"></i></button>
                                                                                                                        </div>
                                                                                                                </form>
                                                                                                            </div> 
                                                                                                        </div>
                                                                                                        <!--end filter-->
                                                                                                        <!--start table--><!-- product-list -->

                                                                                                      <div class="manage-request">
                                                                                                          <div class="table-pagination-container">
                                                                                                              <!-- Table -->
                                                                                                              <table class="table-wrapper">
                                                                                                                  <thead>
                                                                                                                      <tr style="background-color: #e4edeb;">
                                                                                                                          <th style="width: 100px">Image</th>
                                                                                                                          <th>Product name</th>
                                                                                                                          <th style="width: 200px">Type</th>
                                                                                                                          <th>View Detail</th>
                                                                                                                          <th>Action</th>
                                                                                                                      </tr>
                                                                                                                  </thead>
                                                                                                                  <tbody>
                                                                                                                      <c:if test="${empty listFoodDraft}">
                                                                                                                          <tr>
                                                                                                                              <td colspan="8" class="text-center">
                                                                                                                                  <div class="py-4">
                                                                                                                                      <i class="fas fa-search fs-1 text-muted mb-3"></i>
                                                                                                                                      <h5>No Food Draft Request found</h5>
                                                                                                                                      <p class="text-muted">
                                                                                                                                          <c:choose>
                                                                                                                                              <c:when test="${not empty param.name}">
                                                                                                                                                  No Food Draft Request match your search criteria. Try adjusting your filters.
                                                                                                                                                  <br>
                                                                                                                                                      <a href="${pageContext.request.contextPath}/type-of-request" class="btn btn-outline-primary mt-2">
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
                                                                                                                      <c:forEach items="${listFoodDraft}" var="foodD">
                                                                                                                          <tr>
                                                                                                                              <td><img src="${foodD.image_url}" alt="Image of Food" class="product-img"></td>
                                                                                                                              <td>${foodD.getName()}</td>
                                                                                                                              <td>${foodD.getType()}</td>
                                                                                                                              <td>
                                                                                                                                  <div class="item eye">
                                                                                                                                      <a href="type-of-request?action=view&select=${foodD.getType()}&id=${foodD.getId()}" title="View Detail">
                                                                                                                                          <i class="icon-eye"></i>
                                                                                                                                      </a>
                                                                                                                                  </div>
                                                                                                                              </td>
                                                                                                                              <td>
                                                                                                                                  <div class="item edit">
                                                                                                                                      <a href="type-of-request?action=accept&select=${foodD.getType()}&id=${foodD.getId()}" onclick="handleAccept(event)" title="Accept">
                                                                                                                                          <i class="fa-solid fa-check"></i>
                                                                                                                                      </a>
                                                                                                                                  </div>
                                                                                                                                  <a href="type-of-request?action=reject&select=${foodD.getType()}&id=${foodD.getId()}" onclick="handleReject(event)" title="Reject">
                                                                                                                                      <div class="item trash">
                                                                                                                                          <i class="fa-solid fa-xmark"></i>
                                                                                                                                      </div>
                                                                                                                                  </a>
                                                                                                                              </td>
                                                                                                                          </tr>
                                                                                                                      </c:forEach>
                                                                                                                  </tbody>
                                                                                                              </table>


                                                                                                              <div class="pagination-wrapper">
                                                                                                                  <div class="text-tiny">Showing 10 entries</div>

                                                                                                                  <!--   Start Pagination-->
                                                                                                    <ul class="pagination-wrapper">

                                                                                                        <li >
                                                                                                            <a href="${pageContext.request.contextPath}/type-of-request?page=1&action=option&select=${select}"><i class="icon-chevron-left"></i></a>
                                                                                                        </li>
                                                                                                        <c:choose>
                                                                                                            <c:when test="${currentPage <= totalPages - 2}">
                                                                                                                <c:if test="${currentPage > 1}">
                                                                                                                    <li class="">
                                                                                                                        <a href="${pageContext.request.contextPath}/type-of-request?page=${currentPage - 1}&action=option&select=${select}">${currentPage - 1}</a>
                                                                                                                    </li>
                                                                                                                </c:if>
                                                                                                                <li class="active">
                                                                                                                    <a href="${pageContext.request.contextPath}/type-of-request?page=${currentPage}&action=option&select=${select}">${currentPage}</a>
                                                                                                                </li>

                                                                                                                <li class="">
                                                                                                                    <a href="${pageContext.request.contextPath}/type-of-request?page=${currentPage + 1}&action=option&select=${select}">${currentPage + 1}</a>
                                                                                                                </li>

                                                                                                                <c:if test="${currentPage < totalPages - 2}">
                                                                                                                    <li>
                                                                                                                          <span>...</span>
                                                                                                                    </li>
                                                                                                                </c:if>


                                                                                                                <li class="">
                                                                                                                    <a href="${pageContext.request.contextPath}/type-of-request?page=${totalPages}&action=option&select=${select}">${totalPages}</a>
                                                                                                                </li>
                                                                                                            </c:when>

                                                                                                            <c:otherwise>
                                                                                                                <c:forEach begin="${totalPages-2 <= 0 ? 1 : totalPages - 2}" end="${totalPages}" var="i">
                                                                                                                    <li class="${currentPage == i ? 'active' : ''}">
                                                                                                                        <a href="${pageContext.request.contextPath}/type-of-request?page=${i}&action=option&select=${select}">${i}</a>
                                                                                                                    </li>
                                                                                                                </c:forEach>
                                                                                                            </c:otherwise>
                                                                                                        </c:choose>

                                                                                                        <li>
                                                                                                            <a href="${pageContext.request.contextPath}/type-of-request?page=${totalPages}&action=option&select=${select}"><i class="icon-chevron-right"></i></a>
                                                                                                        </li>
                                                                                                    </ul>
                                                                                                              </div>        
                                                                                                          </div>                                                                                              
                                                                                                          <!--End Pagination-->
                                                                                                      </div>
                                                                                                        <!--end table--> <!-- /product-list -->
                                                                                                    </div>  
                                                                                                    <!-- end  main-content-wrap -->

                                                                                                </div>
                                                                                                <!--end main content inner-->

                                                                                               
                                                                                                <!-- bottom-page -->
                                                                                <jsp:include page="../common/footer.jsp"></jsp:include>
                                                                                    <!-- /bottom-page -->
                                                                                            </div>
                                                                                            <!--end main content-->
                                                                                        </div>
                                                                                        <!--end section content right-->
                                                                                    </div>
                                                                                    <!-- /end layout wrapper -->
                                                                                </div>
                                                                                <!-- /end page -->
                                                                                
                                                                                </div>
                                                                                <!-- /end wrapper -->
                                                                              

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
                                                                           <!--start isSuccess-->
                                                                           <c:if test="${isSuccess == true}">
                                                                               <script>
                                                                                                                                   document.addEventListener("DOMContentLoaded", function () {
                                                                                                                                       iziToast.error({
                                                                                                                                           title: "Notification",
                                                                                                                                           message: "Processed successfully",
                                                                                                                                           position: 'topRight',
                                                                                                                                           timeout: 5000,
                                                                                                                                           backgroundColor: "#d4edda"
                                                                                                                                       });
                                                                                                                                   });
                                                                               </script>
                                                                               <!--Xóa đi biến isDelete sau khi đã thông báo--> 
                                                                               <%
                                                                                   session.removeAttribute("isSuccess");
                                                                               %>
                                                                           </c:if>
                                                                             <!--Message Error-->
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