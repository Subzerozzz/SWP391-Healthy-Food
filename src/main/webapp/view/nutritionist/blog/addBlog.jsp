<%-- 
    Document   : addBlog
    Created on : May 30, 2025, 3:32:10 PM
    Author     : Predator
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<!--<![endif]-->


<!-- Mirrored from themesflat.co/html/remos/add-product.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:33 GMT -->
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



    <!-- Font -->
    <link rel="stylesheet" href="font/fonts.css">

    <!-- Icon -->
    <link rel="stylesheet" href="icon/style.css">

    <!-- Favicon and Touch Icons  -->
    <link rel="shortcut icon" href="images/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="images/favicon.png">

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
                <jsp:include page="../../common/nutritionist/sidebar.jsp"></jsp:include>
                <!-- /section-menu-left -->
                <!-- section-content-right -->
                <div class="section-content-right">
                    <!-- header-dashboard -->
                    <jsp:include page="../../common/nutritionist/headerdashboard.jsp"></jsp:include> 
                    <!-- /header-dashboard -->
                    <!-- main-content -->
                    <div class="main-content">
                        <!-- main-content-wrap -->
                        <div class="main-content-inner">
                            <!-- main-content-wrap -->
                            <div class="main-content-wrap">
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                    <h3>Add Blog</h3>
                                    <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                        <li>
                                            <a href="index"><div class="text-tiny">Dashboard</div></a>
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
                                            <div class="text-tiny">Add Blog</div>
                                        </li>
                                    </ul>
                                </div>
                                <!-- form-add-product -->
                                <form class="tf-section-2 form-add-product" 
                                    action="manage-blog?action=add"
                                     method="POST"
                                     enctype="multipart/form-data"
                                     >
                                    <div class="wg-box">
                                        <fieldset class="title">
                                            <div class="body-title mb-10">Blog Title <span class="tf-color-1">*</span></div>
                                            <input class="mb-10" type="text" placeholder="Enter blog title" name="title" tabindex="0" value="" aria-required="true" required="">
                                            <div class="text-tiny">Do not exceed 20 characters when entering the product name.</div>
                                        </fieldset>
                                        <fieldset class="briefinfo">
                                            <div class="body-title mb-10">Brief_info <span class="tf-color-1">*</span></div>
                                            <input class="mb-10" type="text" placeholder="Enter briefinfo" name="briefinfo" tabindex="0" value="" aria-required="true" required="">
                                                <div class="text-tiny">Do not exceed 100 characters when entering the author.</div>
                                        </fieldset>


                                        <fieldset class="Content">
                                            <div class="body-title mb-10">Content <span class="tf-color-1">*</span></div>
                                            <textarea class="mb-10" name="content" placeholder="Description" tabindex="0" aria-required="true" required=""></textarea>
                                            <div class="text-tiny">Do not exceed 100 characters when entering the product name.</div>
                                        </fieldset>
                                    </div>
                                    <div class="wg-box">
                                        <!--Image-->
                                        <fieldset>
                                            <div class="body-title mb-10">Upload images</div>
                                            <div class="upload-image mb-16">
                                                <div class="item">
                                                    <img src="images/upload/upload-1.png" alt="">
                                                </div>
                                                <div class="item up-load">
                                                    <label class="uploadfile" for="myFile">
                                                        <span class="icon">
                                                            <i class="icon-upload-cloud"></i>
                                                        </span>
                                                        <span class="text-tiny">Drop your images here or select <span class="tf-color">click
                                                                to
                                                                browse</span></span>
                                                        <input type="file" id="myFile" name="filename">
                                                    </label>
                                                </div>
                                            </div>
                                        </fieldset>
                                         <div class="cols gap10">
                                             <button class="tf-button w-full" type="submit">Add Blog</button>
                                         </div>
                                     </div>
                                 </form>
                                 <!-- /form-add-product -->
                             </div>
                             <!-- /main-content-wrap -->
                         </div>
                         <!-- /main-content-wrap -->
                         <!-- bottom-page -->
                         <div class="bottom-page">
                             
                         </div>
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
     <script src="${pageContext.request.contextPath}/js/main.js"></script>
     <script>
          document.addEventListener('DOMContentLoaded', function () {
            const fileInput = document.getElementById('myFile');
            const imageContainer = document.querySelector('.item img');

            fileInput.addEventListener('change', function () {
              if (fileInput.files && fileInput.files[0]) {
                const reader = new FileReader();

                reader.onload = function (e) {
                  imageContainer.src = e.target.result;
                };

                reader.readAsDataURL(fileInput.files[0]);
              }
            });
          });
     </script>
 </body>
 <!-- Mirrored from themesflat.co/html/remos/add-product.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:35 GMT -->
 </html>
