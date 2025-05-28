<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@page contentType="text/html" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
    <!--[if (gte IE 9)|!(IE)]><!-->
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->


    <!-- Mirrored from themesflat.co/html/remos/product-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:35 GMT -->

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
      <link rel="stylesheet" href="${pageContext.request.contextPath}/font/fonts.css">

      <!-- Icon -->
      <link rel="stylesheet" href="${pageContext.request.contextPath}/icon/style_1.css">

      <!-- Favicon and Touch Icons  -->
      <link rel="shortcut icon"
        href="${pageContext.request.contextPath}/${pageContext.request.contextPath}/images/favicon_1.png">
      <link rel="apple-touch-icon-precomposed"
        href="${pageContext.request.contextPath}/${pageContext.request.contextPath}/images/favicon_1.png">

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
            <jsp:include page="../common/manager/sidebar.jsp"></jsp:include>
            <!-- /section-menu-left -->
            <!-- section-content-right -->
            <div class="section-content-right">
              <!-- header-dashboard -->
              <jsp:include page="../common/manager/headerDashboard.jsp"></jsp:include>
              <!-- /header-dashboard -->
              <!-- main-content -->
              <div class="main-content">
                <!-- main-content-wrap -->
                <div class="main-content-inner">
                  <!-- main-content-wrap -->
                  <div class="main-content-wrap">
                    <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                      <h3>Food List</h3>
                      
                    </div>
                    <!-- product-list -->
                    <div class="wg-box">
                      <div class="title-box">
                        <i class="icon-coffee"></i>
                        <div class="body-text">Tip search by Product ID: Each product is provided with a unique ID,
                          which you can rely on to find the exact product you need.</div>
                      </div>
                      <div class="flex items-center justify-between gap10 flex-wrap">
                        <div class="wg-filter flex-grow">
                          <div class="show">
                            <div class="text-tiny">Showing</div>
                            <div class="select">
                              <select class="">
                                <option>10</option>
                                <option>20</option>
                                <option>30</option>
                              </select>
                            </div>
                            <div class="text-tiny">entries</div>
                          </div>
                          <form class="form-search">
                            <fieldset class="name">
                              <input type="text" placeholder="Search here..." class="" name="name" tabindex="2" value=""
                                aria-required="true" required="">
                            </fieldset>
                            <div class="button-submit">
                              <button class="" type="submit"><i class="icon-search"></i></button>
                            </div>
                          </form>
                        </div>
                          <a class="tf-button style-1 w208" href="${pageContext.request.contextPath}/view/manager/addFood.jsp"><i class="icon-plus"></i>Add new</a>
                      </div>
                      <!--                      <div class="wg-table table-product-list">
                        

                      </div>-->
                      
                      <!--View food-->
                      <div class="table-container">
                        <table>
                          <thead>
                            <tr>
                              <th>ID</th>
                              <th>Product name</th>
                              <th>Category</th>
                              <th>Price</th>
                              <th>Status</th>
                              <th>Action</th>
                            </tr>
                          </thead>
                          <tbody>
                            <c:forEach items="${listFood}" var="item">
                              <tr>
                                <td>
                                    ${item.getId()}
                                    <img src="#" alt="product" class="product-image">
                                </td>
                                <td>
                                  <div class="product-info">
                                    
                                    <span class="product-name">${item.getName()}</span>
                                  </div>
                                </td>
                                <td>Lấy sau</td>
                                <td><span class="price">$${item.getPrice()}</span></td>
                                <td><span class="status">${item.getStatus()}</span></td>
                                <td>
                                  <div class="action-btns">
                                    <button class="btn btn-edit">Sửa</button>
                                    <button class="btn btn-delete">Xóa</button>
                                  </div>
                                </td>
                              </tr>
                            </c:forEach>

                          </tbody>
                        </table>
                      </div>
                      <!--End view food-->
                      
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
                <div class="bottom-page">
                  <div class="body-text">Copyright © 2024 Remos. Design with</div>
                  <i class="icon-heart"></i>
                  <div class="body-text">by <a href="https://themeforest.net/user/themesflat/portfolio">Themesflat</a>
                    All
                    rights reserved.</div>
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

      <style>
        * {
          box-sizing: border-box;
          margin: 0;
          padding: 0;
        }

        body {
          font-family: Arial, sans-serif;
          padding: 20px;
          background: #fff;
          color: #111;
        }

        .table-container {
          max-width: 1200px;
          margin: auto;
          border-radius: 8px;
          overflow-x: auto;
        }

        table {
          width: 100%;
          border-collapse: collapse;
          min-width: 700px;
        }

        thead {
          background-color: #f9fafb;
        }

        th,
        td {
          padding: 16px;
          text-align: left;
          font-size: 14px;
        }

        th {
          color: #6b7280;
          font-weight: 600;
          white-space: nowrap;
        }

        tbody tr {
          background-color: #f8fafc;
          transition: background 0.2s;
        }

        tbody tr:hover {
          background-color: #f1f5f9;
        }

        .product-info {
          display: flex;
          align-items: center;
          gap: 12px;
        }

        .product-image {
          width: 50px;
          height: 50px;
          flex-shrink: 0;
          border-radius: 8px;
          object-fit: cover;
        }

        .product-name {
          font-size: 15px;
          color: #374151;
          max-width: 200px;
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
        }

        .price {
          color: #059669;
          font-weight: 600;
        }

        .status {
          padding: 6px 12px;
          border-radius: 16px;
          background: #d1fae5;
          color: #065f46;
          font-size: 12px;
          font-weight: 500;
          display: inline-block;
        }

        .action-btns {
          display: flex;
          gap: 8px;
        }

        .btn {
          padding: 6px 12px;
          font-size: 12px;
          border: none;
          border-radius: 4px;
          cursor: pointer;
        }

        .btn-edit {
          background-color: #3b82f6;
          color: white;
        }

        .btn-delete {
          background-color: #ef4444;
          color: white;
        }

        /* Responsive */
        @media (max-width: 768px) {

          th,
          td {
            padding: 10px;
            font-size: 13px;
          }

          .product-desc {
            max-width: 120px;
          }

          .btn {
            font-size: 11px;
            padding: 4px 8px;
          }
        }
      </style>

    </body>


    <!-- Mirrored from themesflat.co/html/remos/product-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:40 GMT -->

    </html>