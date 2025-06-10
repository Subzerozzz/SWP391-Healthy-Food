<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->


    <!-- Mirrored from themesflat.co/html/remos/product-detail-1.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:40 GMT -->
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
                                    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/swiper-bundle.min.css">
                                        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_1.css">
                                            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail_food.css"/>


                                            <!--css-->

                                            <!-- Font -->
                                            <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/fonts.css">

                                                <!-- Icon -->
                                                <link rel="stylesheet" href="${pageContext.request.contextPath}/icon/style.css">

                                                    <!-- Favicon and Touch Icons  -->
                                                    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon_1.png">
                                                        <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/images/favicon_1.png">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/izi-toast.css"/>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
                                                            <style>
   .btn-accept {
    background: linear-gradient(135deg, #00c853, #64dd17);
     color: #fff;
}

.btn-accept:hover {
    background: linear-gradient(135deg, #00b248, #558b2f);
    transform: translateY(-2px);
}

.btn-reject {
    background: linear-gradient(135deg, #d50000, #ff1744);
     color: #fff;
}

.btn-reject:hover {
    background: linear-gradient(135deg, #b71c1c, #d32f2f);
    transform: translateY(-2px);
}
.btn-backHome {
    background: linear-gradient(135deg, #00c6ff, #0072ff);
    margin-left: 280px;
    color: #fff;
}

.btn-backHome:hover {
    background: linear-gradient(135deg, #0072ff, #00c6ff);
    transform: translateY(-2px);
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
                                                                            <jsp:include page="/view/common/menuDashBoard/section-menu-left.jsp"></jsp:include>
                                                                                <!-- /section-menu-left -->
                                                                                <!-- section-content-right -->
                                                                                <div class="section-content-right">
                                                                                    <!-- header-dashboard -->
                                                                                <jsp:include page="/view/common/menuDashBoard/header-dashboard.jsp"></jsp:include>
                                                                                    <!-- /header-dashboard -->
                                                                                    <!-- main-content -->
                                                                                    <div class="main-content">
                                                                                        <!-- main-content-wrap -->
                                                                                        <div class="main-content-inner">
                                                                                            <!-- main-content-wrap -->
                                                                                        
                                                                                            <!-- /Product Detail -->
                                                                                            <div class="product-detail">
                                                                                                <div class="product-image">
                                                                                                    <img src="https://mediawinwin.vn/upload/images/sanpham/bao-gia-chup-mon-an-dich-vu-chup-anh-do-an-chuyen-nghiep-5.JPG" alt="Black Fresh Berry">
                                                                                                </div>
                                                                                                <div class="product-info">
                                                                                                    <h2>${foodD.name}</h2>
                                                                                                <h3>Created at: ${foodD.created_at}</h3>
                                                                                                <div class="price">
                                                                                                    <span class="current">Price: ${foodD.price} $</span>
                                                                                                    <!--                                            <span class="original">$330.00</span>-->
                                                                                                </div>
                                                                                                <p>Type: ${foodD.type}</p>
                                                                                                <p class="description">
                                                                                                   ${foodD.description}
                                                                                                </p>
                                                                                                <p class="description">
                                                                                                 Number of Calo: ${foodD.calo}
                                                                                                </p>
                                                                                                <p>Black Fresh Berry is a delicious and nutritious fruit, rich in antioxidants and flavor. Perfect for a healthy snack or to add freshness to your desserts and smoothies.</p>
                                                                                              
                                                                                                <div style="display: flex; gap: 40px">
                                                                                                    <form action="${pageContext.request.contextPath}/type-of-request" method="get">
                                                                                                        <input type="hidden" name="select" value="${foodD.type}">
                                                                                                            <input type="hidden" name="action" value="accept">
                                                                                                                <input type="hidden" name="id" value="${foodD.id}">
                                                                                                                    <button type="submit" class="btn-modern btn-accept"
                                                                                                                            onclick="handleAccept(event)">
                                                                                                                        Accept
                                                                                                                    </button>
                                                                                                                    </form>

                                                                                                                    <form action="${pageContext.request.contextPath}/type-of-request" method="get">
                                                                                                                        <input type="hidden" name="select" value="${foodD.type}">
                                                                                                                            <input type="hidden" name="action" value="reject">
                                                                                                                                <input type="hidden" name="id" value="${foodD.id}">
                                                                                                                                    <button type="submit" class="btn-modern btn-reject"
                                                                                                                                            onclick="handleReject(event)">
                                                                                                                                        Reject
                                                                                                                                    </button>
                                                                                                                                    </form>
                                                                                                                                            <form action="${pageContext.request.contextPath}/type-of-request" method="get">
                                                                                                                                                         <button type="submit" class="btn-modern btn-backHome" > Back
                                                                                                                                                         </button>
                                                                                                                                    </form>
                                                                                                                                    </div>
                                                                                                                                    </div>
                                                                                                                                    </div>
                                                                                                                                    </div>
                                                                                                                                    <!-- /main-content-wrap -->
                                                                                                                                    </div>
                                                                                                                                    <!-- /main-content-wrap -->
                                                                                                                                    <!-- bottom-page -->
                                                                                                                                    <jsp:include page="/view/common/menuDashBoard/bottom-page.jsp"></jsp:include>
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
                                                                                                                                    <script src="${pageContext.request.contextPath}/js/swiper-bundle.min.js"></script>
                                                                                                                                    <script src="${pageContext.request.contextPath}/js/carousel.js"></script>
                                                                                                                                    <script src="${pageContext.request.contextPath}/js/main.js"></script>
                                                                                                                                    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
                                                                                                                                    <script src="https://cdn.jsdelivr.net/npm/izitoast/dist/js/iziToast.min.js"></script>
                                                                                                                                     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/izitoast/dist/css/iziToast.min.css" />
                                                                                                                                    <script>
  function handleAccept(event) {
    event.preventDefault(); // Ngăn hành vi mặc định để xử lý trước khi gửi

    const form = event.currentTarget.closest("form"); // Lấy form chứa button

    Swal.fire({
        title: 'Are you sure?\nThis action cannot be undone.',
        showCancelButton: true,
        confirmButtonText: 'Accept',
        cancelButtonText: 'Cancel',
        reverseButtons: true,
        background: '#ffffff',
        showCloseButton: false, // Không hiển thị nút đóng
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
            form.submit(); // Gửi form sau khi người dùng xác nhận Accept
        } else if (result.isDismissed) {
            localStorage.setItem('showRejectToast', 'true');
        }
    });
}
 function handleReject(event) {
    event.preventDefault(); // Ngăn hành vi mặc định để xử lý trước khi gửi

    const form = event.currentTarget.closest("form"); // Lấy form chứa button

    Swal.fire({
        title: 'Are you sure?\nThis action cannot be undone.',
        showCancelButton: true,
        confirmButtonText: 'Accept',
        cancelButtonText: 'Cancel',
        reverseButtons: true,
        background: '#ffffff',
        showCloseButton: false, // Không hiển thị nút đóng
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
            form.submit(); // Gửi form sau khi người dùng xác nhận Accept
        } else if (result.isDismissed) {
            localStorage.setItem('showRejectToast', 'true');
        }
    });
}

   </script>
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
           
                                                            </body>


                                                                                                                                    <!-- Mirrored from themesflat.co/html/remos/product-detail-1.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:47 GMT -->
                                                                                                                                    </html>

                                                                                                                    
                                                                                                                                        