<%-- Document : addFood.jsp Created on : May 28, 2025, 5:32:51 PM Author : Dell --%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <!DOCTYPE html>
        <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">


        <head>
          <meta charset="utf-8">
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
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
          <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>

          <!-- Font -->
          <link rel="stylesheet" href="${pageContext.request.contextPath}/font/fonts.css">
          <!-- Icon -->
          <link rel="stylesheet" href="${pageContext.request.contextPath}/icon/style.css">

          <!-- Favicon and Touch Icons  -->
          <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png">
          <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/images/favicon.png">

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
                  <jsp:include page="../../common/nutritionist/headerDashboard.jsp"></jsp:include>
                  <!-- /header-dashboard -->
                  <!-- main-content -->
                  <div class="main-content">
                    <!-- main-content-wrap -->
                    <div class="main-content-inner">
                      <!-- main-content-wrap -->
                      <div class="main-content-wrap">
                        <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                          <h3>Add Product</h3>
                        </div>
                        <!-- form-add-product -->
                        <form class="tf-section-2 form-add-product"
                          action="${pageContext.request.contextPath}/manage-food?action=add" method="POST"
                          enctype="multipart/form-data">
                          <div class="wg-box">
                            <!--Name-->
                            <fieldset class="name">
                              <div class="body-title mb-10">Product name <span class="tf-color-1">*</span></div>
                              <input class="mb-10" type="text" placeholder="Enter food name" name="name" tabindex="0"
                                value="${not empty formData ? fn:escapeXml(formData.name[0]) : ''}" aria-required="true"
                                required="">

                              <div class="text-tiny">Do not exceed 20 characters when entering the product name.</div>
                            </fieldset>

                            <!--Category-->
                            <div class="gap22 cols">
                              <fieldset class="category">
                                <div class="body-title mb-10">Category <span class="tf-color-1">*</span></div>
                                <div class="select">
                                  <select name="category" class="">
                                    <c:forEach items="${listCategory}" var="item">
                                      <option value="${item.getId()}" ${not empty formData and
                                        formData.category[0]==item.getId() ? "selected" : "" }>
                                        ${item.getMinBMI()} -
                                        ${item.getMaxBMI()}(${item.getName()})</option>
                                    </c:forEach>
                                  </select>
                                </div>
                              </fieldset>
                            </div>

                            <!--Price-->
                            <fieldset class="price">
                              <div class="body-title mb-10">Price <span class="tf-color-1">*</span></div>
                              <input class="mb-10" type="text" placeholder="Enter price" name="price" tabindex="0"
                                value="${not empty formData ? fn:escapeXml(formData.price[0]) : ''}"
                                aria-required="true" required="">
                            </fieldset>

                            <!--Status-->
                            <fieldset class="price" style="display: none">
                              <div class="body-title mb-10">Price <span class="tf-color-1">*</span></div>
                              <input class="mb-10" type="text" name="status" tabindex="0" value="init"
                                aria-required="true" required="">
                            </fieldset>

                            <!--Description-->
                            <fieldset class="description">
                              <div class="body-title mb-10">Description <span class="tf-color-1">*</span></div>
                              <textarea class="mb-10" name="description" placeholder="Description" tabindex="0"
                                aria-required="true"
                                required="">${not empty formData ? fn:escapeXml(formData.description[0]) : ''}</textarea>
                              <div class="text-tiny">Do not exceed 100 characters when entering the product name.</div>
                            </fieldset>
                          </div>
                          <!--Image và Make Request-->
                          <div class="wg-box">
                            <!--Image-->
                            <fieldset>
                              <div class="body-title mb-10">Upload images</div>
                              <div class="upload-image mb-16">
                                <div class="item">
                                  <img src="" alt="">
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
                            <!--Date-->
                            <!--
                                                                                                    <div class="cols gap22">
                                                                                                      <fieldset class="create_at">
                                                                                                        <div class="body-title mb-10">Food created</div>
                                                                                                        <div class="select">
                                                                                                          <input id="date-input" type="date" name="created_at" value="">
                                                                                                        </div>
                                                                                                      </fieldset>
                                                                                                    </div>-->
                            <!--Request-->
                            <div class="cols gap10">
                              <button class="tf-button w-full" type="submit">Make request</button>
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
                      <div class="body-text">Copyright © 2024 Remos. Design with</div>
                      <i class="icon-heart"></i>
                      <div class="body-text">by <a
                          href="https://themeforest.net/user/themesflat/portfolio">Themesflat</a>
                        All rights reserved.</div>
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

          <c:if test="${not empty errors}">
            <script>
              document.addEventListener("DOMContentLoaded", function () {
                <c:forEach var="entry" items="${errors}">
                  iziToast.error({
                    title: "Lỗi",
                  message: "${fn:escapeXml(entry.value)}",
                  position: 'topRight',
                  timeout: 5000
                                                                                });
                </c:forEach>
              });
            </script>
          </c:if>

          <script>
            //Xử lý ảnh
            document.addEventListener('DOMContentLoaded', function () {
              // Lấy tham chiếu đến input file và container hiển thị ảnh
              const fileInput = document.getElementById('myFile');
              const imageContainer = document.querySelector('.item img');

              // Thêm event listener cho sự kiện thay đổi file
              fileInput.addEventListener('change', function () {
                // Kiểm tra xem người dùng đã chọn file chưa
                if (fileInput.files && fileInput.files[0]) {
                  const reader = new FileReader();

                  // Khi FileReader đã load xong file
                  reader.onload = function (e) {
                    // Cập nhật src của thẻ img để hiển thị ảnh
                    imageContainer.src = e.target.result;
                  };

                  // Đọc file dưới dạng URL data
                  reader.readAsDataURL(fileInput.files[0]);
                }
              });
            });
          </script>

        </body>


        <!-- Mirrored from themesflat.co/html/remos/add-product.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:35 GMT -->

        </html>