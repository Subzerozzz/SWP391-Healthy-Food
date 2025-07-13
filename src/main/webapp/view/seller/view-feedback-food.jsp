<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                  .product-detail-container {
                    max-width: 1000px;
                    margin: 40px auto;
                    padding: 20px;
                    background: #ffffff;
                    border-radius: 12px;
                    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
                    font-family: 'Segoe UI', sans-serif;
                  }

                  .product-detail {
                    display: flex;
                    flex-wrap: wrap;
                    gap: 30px;
                    align-items: flex-start;
                  }

                  .product-image img {
                    width: 320px;
                    height: auto;
                    object-fit: cover;
                    border-radius: 12px;
                    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                  }

                  .product-info {
                    flex: 1;
                    min-width: 300px;
                   }

                  .product-title {
                    font-size: 28px;
                    font-weight: bold;
                    margin-bottom: 10px;
                    color: #333;
                  }

                  .product-date {
                    font-size: 15px;
                    color: #000;
                    margin-bottom: 20px;
                    font-weight:500;
                  }

                  .price {
                    margin: 20px 0;
                  }

                  .price .current {
                    font-size: 20px;
                    font-weight: 50;
                    color: #000;
                  }

                  .description {
                    font-size: 16px;
                    line-height: 1.6;
                    color: #000;
                    margin-bottom: 12px;
                  }

                  .btn-modern {
                    background-color: #0d6efd;
                    color: white;
                    border: none;
                    padding: 10px 22px;
                    font-size: 14px;
                    border-radius: 8px;
                    cursor: pointer;
                    transition: background-color 0.3s ease;
                  }

                  .btn-modern:hover {
                    background-color: #084298;
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
                          <jsp:include page="../common/sidebar.jsp"></jsp:include>
                              <!-- /section-menu-left -->
                              <!-- section-content-right -->
                              <div class="section-content-right">
                                  <!-- header-dashboard -->
                              <jsp:include page="../common/headerDashboard.jsp"></jsp:include>
                                  <!-- /header-dashboard -->
                                  <!-- main-content -->
                                  <div class="main-content">
                                      <!-- main-content-wrap -->
                                      <div class="main-content-inner">
                                          <!-- main-content-wrap -->

   
                            <div class="product-detail-container">
                            <div class="product-detail">
                              <div class="product-image">
                                <img src="${food.image_url}" alt="${foodD.name}">
                              </div>
                              <div class="product-info">
                                  <h2 class="product-title" style="font-weight:500">Food Name: ${foodD.name}</h2>
                                <h3 class="product-date">Created at: <fmt:formatDate value="${foodD.created_at}" pattern="dd/MM/yyyy HH:mm" /></h3>

                                <div class="price">
                                  <span class="current">Price: <fmt:formatNumber value="${foodD.price}" type="currency" currencySymbol="" maxFractionDigits="0"/>VNĐ</span>
                                </div>

                                <p class="description">
                                  Description: ${foodD.description}
                                </p>
                                <p class="description">
                                  Number of Calories: ${foodD.calo}
                                </p>
                                <p class="description">
                                    Nutritionist: ${accDAO.findById(foodD.nutri_id).user_name}
                                </p>

                                <form action="${pageContext.request.contextPath}/seller/feedback" method="get">
                                  <button type="submit" class="btn-modern btn-backHome">
                                    Back
                                  </button>
                                </form>
                              </div>
                            </div>
                          </div>
                            </div>
                                
                          <jsp:include page="./../common/footer.jsp"></jsp:include>   
                            <!-- /main-content-wrap -->
                            </div>
                                          <!-- /main-content-wrap -->
                                          <!-- bottom-page -->
                      
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

     </body>                                                                                                                         
 </html>


