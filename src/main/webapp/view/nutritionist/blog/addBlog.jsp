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
    <link rel="apple-touch-icon-precomposied" href="images/favicon.png">
    <script src="assets/ckeditor/ckeditor.js"></script>
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
                <jsp:include page="../../common/sidebar.jsp"></jsp:include>
                <!-- /section-menu-left -->
                <!-- section-content-right -->
                <div class="section-content-right">
                    <!-- header-dashboard -->
                   <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>  
                    <!-- /header-dashboard -->
                    <!-- main-content -->
                    <div class="main-content">
                        <!-- main-content-wrap -->
                        <div class="main-content-inner">
                            <!-- main-content-wrap -->
                            <div class="main-content-wrap">
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                <h3>Add Blog</h3>
                                <ul class="breadcrumbs flex items-center flex-wrap gap10">
                                    <li><a href="index.html"><div class="text-tiny">Dashboard</div></a></li>
                                    <li><i class="icon-chevron-right"></i></li>
                                    <li><a href="#"><div class="text-tiny">Ecommerce</div></a></li>
                                    <li><i class="icon-chevron-right"></i></li>
                                    <li><div class="text-tiny">Add Blog</div></li>
                                </ul>
                            </div>
                                <!-- form-add-product -->
                                <form  
                                    action="manage-blog?action=add"
                                     method="POST"
                                     enctype="multipart/form-data"
                                     >
                                    <div class="wg-box">
                                        <fieldset class="title">
                                            <div class="body-title mb-10">Blog Title <span class="tf-color-1">*</span></div>
                                            <input class="mb-10" type="text" placeholder="Enter blog title" name="title" tabindex="0" value="" aria-required="true" required maxlength="255">
                                                <div class="text-tiny">Do not exceed 255 characters when entering the blog title.</div>
                                        </fieldset>
                                        <fieldset class="briefinfo">
                                            <div class="body-title mb-10">Brief_info <span class="tf-color-1">*</span></div>
                                            <input class="mb-10" type="text" placeholder="Enter briefinfo" name="briefinfo" tabindex="0" value="" aria-required="true" required maxlength="255">
                                                <div class="text-tiny">Do not exceed 255 characters when entering the author.</div>
                                        </fieldset>

                                        <div class="col-md-12">
                                            <label class="form-label">Content <span class="text-danger">*</span></label>
                                            <textarea class="form-control" name="content" id="content" rows="10"></textarea>
                                        </div>
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
            CKEDITOR.replace('content', {
                        versionCheck: false
                    });
     </script>
     <script>
         function previewImage(event) {
    const input = event.target;
    const preview = document.getElementById("preview");
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function (e) {
            preview.src = e.target.result;
            preview.style.display = "block";
        };
        reader.readAsDataURL(input.files[0]);
    }
}

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
