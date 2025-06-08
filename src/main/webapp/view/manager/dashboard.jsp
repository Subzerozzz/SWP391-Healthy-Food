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
                                                                            <jsp:include page="../common/menuDashBoard/section-menu-left.jsp"></jsp:include>
                                                                                <!-- /section-menu-left -->
                                                                                <!-- section-content-right -->
                                                                                <div class="section-content-right">
                                                                                    <!-- header-dashboard -->
                                                                                <jsp:include page="../common/menuDashBoard/header-dashboard.jsp"></jsp:include>    
                                                                                    <!-- /header-dashboard -->
                                                                                    <!-- main-content -->

                                                                                    <div class="main-content">
                                                                                        <!-- main-content-wrap -->
                                                                                        <div class="main-content-inner">
                                                                                            <!-- main-content-wrap -->
                                                                                            <div class="main-content-wrap">

                                                                                                <!-- product-list -->
                                                                                                <div class="wg-box" style="margin-bottom: 10px">
                                                                                                    <div class="body-text">Request statistics</div>
                                                                                                    <div  style="width: 500px ; height: 50px">
                                                                                                        <form action="${pageContext.request.contextPath}/type-of-request" method="get" style="display: flex">
                                                                                                        <input type="hidden" name="action" value="option">
                                                                                                            <select  name="select" style="background-color: #115ec2;color: white;">
                                                                                                                <option value="-1" ${param.select == '-1' ? 'selected' : ''}>--All Type Of Request--</option>
                                                                                                                <c:forEach items="${type}" var="t">
                                                                                                                    <option value="${t}" ${param.select == t ? 'selected' : ''}>--${t}--</option>
                                                                                                                    <i class="ti-arrow-down" style="color: white; font-size: 24px;"></i>
                                                                                                                </c:forEach>
                                                                                                            </select>
                                                                                                           <button type="submit" style="background-color: #b2b9c2" >Submit Option</button>

                                                                                                    </form>  
                                                                                                </div>

                                                                                                <!--Message about Alert-->
                                                                                                <div id="mess" ><input type="hidden" name="name" value="1"></div> 
                                                                                              
                                                                                            </div>
                                                                                            <!--start table-->
                                                                                            <div class="manage-request">
                                                                                                <table>
                                                                                                    <thead >
                                                                                                        <tr style="background-color: #e4edeb;">
                                                                                                            <th style="width: 100px">Image</th>
                                                                                                            <th>Product name </th>
                                                                                                            <th  style="width: 200px">Type</th>
                                                                                                            <th>View Detail</th>
                                                                                                            <th>Action</th>
                                                                                                        </tr>
                                                                                                    </thead>
                                                                                                    <tbody>
                                                                                                        <c:forEach items="${listFoodDraft}" var="foodD">
                                                                                                            <tr>
                                                                                                                <td "><img src="${foodD.image_url}" alt="Image of Food" class="product-img"></td>
                                                                                                                <td>${foodD.name}</td>
                                                                                                                <td>${foodD.type}</td>
                                                                                                                <td>
                                                                                                                    <form action="type-of-request" method="get">
                                                                                                                        <input type="hidden" name="action" value="view">
                                                                                                                            <input type="hidden" name="id" value="${foodD.id}">
                                                                                                                                <button type="submit"  style="background-color: #ede1df;">View Detail</button>
                                                                                                                                </form>  
                                                                                                                                </td>
                                                                                                                                <td>
                                                                                                                                    <div style="display: flex;justify-content: center;gap: 12px">
                                                                                                                                        <form action="type-of-request" method="get">
                                                                                                                                            <input type="hidden" name="select" value="${foodD.type}">
                                                                                                                                                <input type="hidden" name="action" value="accept">
                                                                                                                                                    <input type="hidden" name="id" value="${foodD.id}">
                                                                                                                                                        <button type="submit"  style="background-color: #02b80b;color: white"
                                                                                                                                                                onclick="handleAccept(event)" >
                                                                                                                                                            Accept
                                                                                                                                                         </button>
                                                                                                                                                        </form>
                                                                                                                                                        <form action="type-of-request" method="get">
                                                                                                                                                            <input type="hidden" name="select" value="${foodD.type}">
                                                                                                                                                                <input type="hidden" name="action" value="reject">
                                                                                                                                                                    <input type="hidden" name="id" value="${foodD.id}">
                                                                                                                                                                         <button type="submit"  style="background-color: #e60004;color: white"
                                                                                                                                                                                onclick="handleReject(event)"  >
                                                                                                                                                                           Reject 
                                                                                                                                                                        </button>
                                                                                                                                                                        </form>     
                                                                                                                                                                        </div>  
                                                                                                                                                                        </td>
                                                                                                                                                                        </tr>
                                                                                                                                                                    </c:forEach>
                                                                                                                                                                    </tbody>
                                                                                                                                                                    </table>



                                                                                                                                                                    </div>
                                                                                                                                                                    <!--end table-->
                                                                                                                                                                 
                                                                                                                                                                    </div>
                                                                                                                                                                 
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
                                                                                                                                                                    <!-- /product-list -->
                                                                                                                                                                    </div>
                                                                                                                                                                    <!-- /main-content-wrap -->
                                                                                                                                                                    </div>
                                                                                                                                                                    <!-- /main-content-wrap -->
                                                                                                                                                                    <!-- bottom-page -->
                                                                                                                                                                    <jsp:include page="../common/menuDashBoard/bottom-page.jsp"></jsp:include>
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
<!-- iziToast JS -->
<script src="https://cdn.jsdelivr.net/npm/izitoast/dist/js/iziToast.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdn.jsdelivr.net/npm/izitoast/dist/js/iziToast.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/izitoast/dist/css/iziToast.min.css" />                                                                                                      <!--Alert Information about AccpetFood-->
                                                                                                                                                                    <script>
                                                                                                                                                                                                      
 function handleAccept(event) {
    event.preventDefault(); // Ngăn form submit ngay

    Swal.fire({
        title: 'Are you want to Accept?',
        text: "This Item will be handle",
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
       background:'#fff3cd'
    }).then((result) => {
        if (result.isConfirmed) {
             // Lưu trạng thái
            localStorage.setItem('showSuccessToast', 'true');
            // Gửi form thủ công
            event.target.form.submit();
              
        }
    });
}
window.addEventListener('DOMContentLoaded', () => {
    if (localStorage.getItem('showSuccessToast') === 'true') {
        iziToast.success({
            title: 'Successful',
            message: 'Processing Successful!',
            position: 'topRight'
        });
        localStorage.removeItem('showSuccessToast'); // Xóa để không hiện lại
    }
});
function handleReject(event) {
    event.preventDefault(); // Ngăn form submit ngay

    Swal.fire({
        title: 'Are you want to Reject?',
        text: "This Item will be handle",
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
       background:'#fff3cd'
    }).then((result) => {
        if (result.isConfirmed) {
             // Lưu trạng thái
            localStorage.setItem('showSuccessToast', 'true');
            // Gửi form thủ công
            event.target.form.submit();
              
        }
    });
}
    
              
                                                                                                                                                                    </script>
                                                                                                                                                                    </body>


                                                                                                                                                                    <!-- Mirrored from themesflat.co/html/remos/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:12 GMT -->
                                                                                                                                                                    </html>