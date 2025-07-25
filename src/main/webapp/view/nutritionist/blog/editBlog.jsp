<%-- 
    Document   : editBlog
    Created on : Jun 3, 2025, 11:47:30 PM
    Author     : Predator
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
  <script src="assets/ckeditor/ckeditor.js"></script>
</head>

<body class="body">
  <div id="wrapper">
    <div id="page">
      <div class="layout-wrap">
        <div id="preload" class="preload-container">
          <div class="preloading"><span></span></div>
        </div>

        <jsp:include page="../../common/sidebar.jsp"></jsp:include>
        <div class="section-content-right">
          <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>
          <div class="main-content">
            <div class="main-content-inner">
              <div class="main-content-wrap">
                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                  <h3>Edit Blog</h3>
                  <ul class="breadcrumbs flex items-center flex-wrap gap10">
                    <li><a href="index.html">
                        <div class="text-tiny">Dashboard</div>
                      </a></li>
                    <li><i class="icon-chevron-right"></i></li>
                    <li><a href="#">
                        <div class="text-tiny">Ecommerce</div>
                      </a></li>
                    <li><i class="icon-chevron-right"></i></li>
                    <li>
                      <div class="text-tiny">Edit Blog</div>
                    </li>
                  </ul>
                </div>
                <form action="manage-blog?action=edit" method="POST" enctype="multipart/form-data">
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

                    <fieldset class="name">
                      <div class="body-title mb-10">Blog Date</div>
                      <div class="select">
                        <input type="date" name="date" value="${blog.created_Date}">
                      </div>
                    </fieldset>

                    <fieldset class="status">
                      <div class="body-title mb-10">Status <span class="tf-color-1">*</span></div>
                      <div class="select">
                        <select name="status" required>
                          <option value="Active" ${blog.status eq 'Active' ? 'selected' : ''}>Active</option>
                          <option value="Inactive" ${blog.status eq 'Inactive' ? 'selected' : ''}>Inactive</option>
                        </select>
                      </div>
                    </fieldset>

<!--                    <fieldset class="thumbnail">
                      <div class="body-title mb-10">Blog Thumbnail</div>
                      <div class="upload-image">
                        <div class="item">
                          <c:choose>
                            <c:when test="${not empty blog.thumbnailblogs}">
                              <img src="${pageContext.request.contextPath}/${blog.thumbnailblogs}" alt="Current Thumbnail" style="max-width: 200px; max-height: 150px;">
                            </c:when>
                            <c:otherwise>
                              <img src="${pageContext.request.contextPath}/images/upload/upload-1.png" alt="Upload Image" style="max-width: 200px; max-height: 150px;">
                            </c:otherwise>
                          </c:choose>
                        </div>
                        <div class="text-tiny">Upload a new image (optional). Current image will be kept if no new image is selected.</div>
                        <input type="file" name="filename" id="myFile" accept="image/*" style="margin-top: 10px;">
                      </div>
                    </fieldset>-->

                    <div class="cols gap10">
                      <button class="tf-button w-full" type="submit">Update Blog</button>
                      <a href="${pageContext.request.contextPath}/manage-blog" class="tf-button style-2 w-full">Cancel</a>
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
    CKEDITOR.replace('content', {
      versionCheck: false
    });
  </script>
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