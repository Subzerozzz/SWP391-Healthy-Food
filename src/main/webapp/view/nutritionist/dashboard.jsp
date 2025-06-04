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
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                    <h3>List Nutritionist Request</h3>
                                    <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                        <li>
                                            <a href="index.html"><div class="text-tiny">Dashboard</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <a href="#"><div class="text-tiny">Ecommerce</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <div class="text-tiny">Menu_Categories List</div>
                                        </li>
                                    </ul>
                                </div>
                                <!-- product-list -->
                                <div class="wg-box">
                                    <div class="title-box">
                                        <i class="icon-coffee"></i>
                                        <div class="body-text">Find request food_Draft by type of request</div>
                                    </div>
                                    <div  style="width: 300px ;">
                                        <form action="${pageContext.request.contextPath}/type-of-request" method="get">
                                            <input type="hidden" name="action" value="option">
                                            <h3>Choose an option:</h3>
                                            <select name="select" style="background-color: #F0E68C">
                                                <option value="-1" ${param.select == '-1' ? 'selected' : ''}>--All Type Of Request--</option>
                                            <c:forEach items="${type}" var="t">
                                                <option value="${t}" ${param.select == t ? 'selected' : ''}>--${t}--</option>
                                            </c:forEach>
                                        </select>
                                            <button type="submit" style="background-color: #C71585" >Submit Option</button>

                                    </form>  
                                </div>
                                   
                                    <!--Message about Alert-->
                                     <div id="mess">${mess}</div> 
<!--                     Start List Product-->

                      <div class="wg-table table-product-list">
                                        <ul class="table-title flex gap20 mb-14">
                                            <li>
                                                <div class="body-title">Product Images</div>
                                            </li>    
                                            <li>
                                                <div class="body-title">Product Name</div>
                                            </li>
                                            <li>
                                                <div class="body-title">Create DATE</div>
                                            </li>
                                            <li>
                                                <div class="body-title"></div>
                                            </li>
                                            <li>
                                                <div class="body-title">Type</div>
                                            </li>
                                            <li>
                                                <div class="body-title">View Detail</div>
                                            </li>
                                            <li>
                                                <div class="body-title">Acction</div>
                                            </li>
                                            <li>
                                                <div class="body-title"></div>
                                            </li>
                                        </ul>
                                        <ul class="flex flex-column">
                                               <c:forEach items="${listFoodDraft}" var="foodDraft">
                                            <li class="product-item gap14">
                                                <div class="image no-bg">
                                                    <img src="${foodDraft.image_url}" alt="">
                                                </div>
                                                <div class="flex items-center justify-between gap20 flex-grow">
                                                    <div class="name">
                                                        <a href="product-list.html" class="body-title-2">${foodDraft.name}</a>
                                                     </div>
                                                    <div class="body-text">${foodDraft.name}</div>
                                                    <div class="body-text">${foodDraft.created_at}</div>
                                                    <div class="body-text"></div>
                                                    <div class="body-text">${foodDraft.type}</div>
                                                    <div >
                                                        <form action="type-of-request" method="get">
                                                              <input type="hidden" name="action" value="view">
                                                              <input type="hidden" name="id" value="${foodDraft.id}">
                                                                  <button type="submit" class="btn-blue" style="background-color: #66CDAA">View Detail</button>
                                                       </form>
                                                    </div>   
                                                            <div style="display: flex;gap: 12px">
                                                        <form action="type-of-request" method="get">
                                                             <input type="hidden" name="select" value="${foodDraft.type}">
                                                            <input type="hidden" name="action" value="accept">
                                                            <input type="hidden" name="id" value="${foodDraft.id}">
                                                           <button type="submit" class="btn-blue" style="background-color: #00FFFF"
                                                                   onclick="return confirm('Are you want to Accept ${foodDraft.type} this food')" >
                                                               Accept
                                                           </button>
                                                       </form>
                                                        <form action="type-of-request" method="get">
                                                            <input type="hidden" name="select" value="${foodDraft.type}">
                                                            <input type="hidden" name="action" value="reject">
                                                             <input type="hidden" name="id" value="${foodDraft.id}">
                                                           <button type="submit" class="btn-blue" style="background-color: #FF4500"
                                                             onclick="return confirm('Are you want to Reject ${foodDraft.type} this food')"        >
                                                                Reject 
                                                           </button>
                                                       </form>     
                                                    </div>
                                                    <div>
                                                           
                                                    </div>
                                                  
                                                </div>
                                            </li>
                                            </c:forEach>
-->                                        </ul>
                                    </div>
<!--                    End list Product-->
<!--images/products/41.png-->
                 </div>
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
        const mess = document.getElementById("mess");
        if(mess !== null && mess.innerText.trim() !== ""){
            alert(mess.innerText);
        }
    </script>
</body>


<!-- Mirrored from themesflat.co/html/remos/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:12 GMT -->
</html>