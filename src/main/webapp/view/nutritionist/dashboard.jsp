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
                                                                                                <div id="mess">${mess}</div> 


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
                                                                                                                                                                onclick="return confirm('Are you want to Accept ${foodD.type} this food')" >
                                                                                                                                                            Accept
                                                                                                                                                        </button>
                                                                                                                                                        </form>
                                                                                                                                                        <form action="type-of-request" method="get">
                                                                                                                                                            <input type="hidden" name="select" value="${foodD.type}">
                                                                                                                                                                <input type="hidden" name="action" value="reject">
                                                                                                                                                                    <input type="hidden" name="id" value="${foodD.id}">
                                                                                                                                                                        <button type="submit"  style="background-color: #e60004;color: white"
                                                                                                                                                                                onclick="return confirm('Are you want to Reject ${foodD.type} this food')"        >
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
                                                                                                                                                                    <div class="divider"></div>
                                                                                                                                                                    <div class="flex items-center justify-between flex-wrap gap10">
                                                                                                                                                                        <div class="text-tiny">Showing 10 entries</div>
                                                                                                                                                                        <ul class="wg-pagination">
                                                                                                                                                                            <li>
                                                                                                                                                                                <a href="#"><i class="icon-chevron-left"></i></a>
                                                                                                                                                                            </li>
                                                                                                                                                                            <li>
                                                                                                                                                                                <a href="#">1</a>
                                                                                                                                                                            </li>
                                                                                                                                                                            <li class="active">
                                                                                                                                                                                <a href="#">2</a>
                                                                                                                                                                            </li>
                                                                                                                                                                            <li>
                                                                                                                                                                                <a href="#">3</a>
                                                                                                                                                                            </li>
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

                                                                                                                                                                    <!--Alert Information about AccpetFood-->
                                                                                                                                                                    <script>
//                                                                                                                                                                                                        const mess = document.getElementById("mess");
//                                                                                                                                                                                                        if (mess !== null && mess.innerText.trim() !== "") {
//                                                                                                                                                                                                        alert(mess.innerText);
//                                                                                                                                                                                                        }
 
                                                                                                                                                                                                       if(${mess}!==null && mess.trim() !== ""){
                                                                                                                                                                                                           alert(${mess});
                                                                                                                                                                                                       }
                                                                                                                                                                                                        
                                                                                                                                                                    </script>
                                                                                                                                                                    </body>


                                                                                                                                                                    <!-- Mirrored from themesflat.co/html/remos/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:12 GMT -->
                                                                                                                                                                    </html>