<%-- 
    Document   : editBlog
    Created on : Jun 3, 2025, 11:47:30 PM
    Author     : Predator
--%>

<%-- 
    Document   : editBlog
    Created on : June 3, 2025
    Author     : Predator (modified)
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<head>
    <meta charset="utf-8">
    <title>Edit Blog - Remos eCommerce Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    
    <!-- Styles -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min_1.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animation.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-select.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_1.css">
    <link rel="stylesheet" href="font/fonts.css">
    <link rel="stylesheet" href="icon/style.css">
    <link rel="shortcut icon" href="images/favicon.png">
</head>

<body class="body">
<div id="wrapper">
    <div id="page">
        <div class="layout-wrap">
            <div id="preload" class="preload-container">
                <div class="preloading"><span></span></div>
            </div>

            <jsp:include page="../../common/nutritionist/sidebar.jsp" />
            <div class="section-content-right">
                <jsp:include page="../../common/nutritionist/headerdashboard.jsp" />
                <div class="main-content">
                    <div class="main-content-inner">
                        <div class="main-content-wrap">
                            <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                <h3>Edit Blog</h3>
                                <ul class="breadcrumbs flex items-center flex-wrap gap10">
                                    <li><a href="index.html"><div class="text-tiny">Dashboard</div></a></li>
                                    <li><i class="icon-chevron-right"></i></li>
                                    <li><a href="#"><div class="text-tiny">Ecommerce</div></a></li>
                                    <li><i class="icon-chevron-right"></i></li>
                                    <li><div class="text-tiny">Edit Blog</div></li>
                                </ul>
                            </div>
                            <form class="tf-section-2 form-add-product" 
                                  action="manage-blog?action=edit"
                                     method="POST"
                                     enctype="multipart/form-data">
                                <input type="hidden" name="id" value="${blog.id}" />
                                <div class="wg-box">
                                    <fieldset class="title">
                                        <div class="body-title mb-10">Blog Title <span class="tf-color-1">*</span></div>
                                        <input class="mb-10" type="text" name="title" value="${blog.title}" required>
                                        <div class="text-tiny">Do not exceed 20 characters when entering the blog title.</div>
                                    </fieldset>
                                    <div class="gap22 cols">
                                        <fieldset class="author">
                                            <div class="body-title mb-10">Author <span class="tf-color-1">*</span></div>
                                            <input class="mb-10" type="text" name="author" value="${blog.author}" required>
                                            <div class="text-tiny">Do not exceed 20 characters when entering the author.</div>
                                        </fieldset>

                                        <fieldset class="briefinfo">
                                            <div class="body-title mb-10">Brief Info <span class="tf-color-1">*</span></div>
                                            <input class="mb-10" type="text" name="briefinfo" value="${blog.brief_info}" required>
                                            <div class="text-tiny">Do not exceed 100 characters for brief info.</div>
                                        </fieldset>
                                    </div>

                                    <fieldset class="Context">
                                        <div class="body-title mb-10">Description <span class="tf-color-1">*</span></div>
                                        <textarea class="mb-10" type="text" name="content" required>${blog.content}</textarea>
                                        <div class="text-tiny">Do not exceed 1000 characters for the description.</div>
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

                                    <fieldset class="name">
                                        <div class="body-title mb-10">Blog Date</div>
                                        <div class="select">
                                            <input type="date" name="date" value="${blog.birth_date}">
                                        </div>
                                    </fieldset>

                                    <div class="cols gap10">
                                        <button class="tf-button w-full" type="submit">Update Blog</button>
                                        <a href="blogList.jsp" class="tf-button style-2 w-full">Cancel</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="bottom-page"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->
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
</html>
