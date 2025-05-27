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
                      <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                        <li>
                          <a href="index.html">
                            <div class="text-tiny">Dashboard</div>
                          </a>
                        </li>
                        <li>
                          <i class="icon-chevron-right"></i>
                        </li>
                        <li>
                          <a href="#">
                            <div class="text-tiny">Ecommerce</div>
                          </a>
                        </li>
                        <li>
                          <i class="icon-chevron-right"></i>
                        </li>
                        <li>
                          <div class="text-tiny">Product List</div>
                        </li>
                      </ul>
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
                        <a class="tf-button style-1 w208" href="add-product.html"><i class="icon-plus"></i>Add new</a>
                      </div>
                      <div class="wg-table table-product-list">
                        <!--                                        <ul class="table-title flex gap20 mb-14">
                                            <li>
                                                <div class="body-title">Product</div>
                                            </li>    
                                            <li>
                                                <div class="body-title">Product ID</div>
                                            </li>
                                            <li>
                                                <div class="body-title">Price</div>
                                            </li>
                                            <li>
                                                <div class="body-title">Stock</div>
                                            </li>
                                            <li>
                                                <div class="body-title">Action</div>
                                            </li>
                                        </ul>-->

                        <div class="product-management">
                          <div class="product-table">
                            <div class="table-responsive">
                              <table>
                                <thead>
                                  <tr>
                                    <th>ID</th>
                                    <th>Image</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Price</th>
                                    <th>Stock</th>
                                    <th>Actions</th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <tr>
                                    <td>${product.id}</td>
                                    <td>
                                      <img src="${product.image}" alt="${product.name}" class="product-img">
                                    </td>
                                    <td>${product.name}</td>
                                    <td>${product.description}</td>
                                    <td>$${product.price}</td>
                                    <td>${product.stock}</td>
                                    <td class="action-buttons">
                                      <button class="edit-btn" onclick="editProduct(${product.id})">
                                        <i class="fas fa-edit"></i>
                                      </button>
                                      <button class="delete-btn" onclick="deleteProduct(${product.id})">
                                        <i class="fas fa-trash-alt"></i>
                                      </button>
                                    </td>
                                  </tr>
                                </tbody>
                              </table>
                            </div>
                          </div>
                        </div>

                        <!-- <ul class="flex flex-column">
                                            <li class="product-item gap14">
                                                <div class="image no-bg">
                                                    <img src="${pageContext.request.contextPath}/images/products/41.png" alt="">
                                                </div>
                                                <div class="flex items-center justify-between gap20 flex-grow">
                                                    <div class="name">
                                                        <a href="product-list.html" class="body-title-2">Dog Food, Chicken & Chicken Liver Recipe...</a>
                                                    </div>
                                                    <div class="body-text">#7712309</div>
                                                    <div class="body-text">20</div>
                                                    <div>
                                                        <div class="block-not-available">Out of stock</div>
                                                    </div>
                                                    <div class="list-icon-function">
                                                        <div class="item eye">
                                                            <i class="icon-eye"></i>
                                                        </div>
                                                        <div class="item edit">
                                                            <i class="icon-edit-3"></i>
                                                        </div>
                                                        <div class="item trash">
                                                            <i class="icon-trash-2"></i>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul> -->
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
        .product-management {
          padding: 20px;
        }

        .product-table {
          width: 100%;
          background: #fff;
          border-radius: 8px;
          box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .table-responsive {
          overflow-x: auto;
        }

        table {
          width: 100%;
          border-collapse: collapse;
        }

        th,
        td {
          padding: 12px 15px;
          text-align: left;
          border: none;
        }

        th {
          background-color: #f8f9fa;
          color: #495057;
          font-weight: 600;
        }

        /* Adjust column widths */
        th:first-child,
        td:first-child {
          width: 80px;
          /* Narrower ID column */
        }

        th:nth-child(4),
        td:nth-child(4) {
          width: 30%;
          /* Wider description column */
        }

        /* Remove borders between cells */
        tbody tr {
          border-bottom: 1px solid #f2f2f2;
        }

        tbody tr:last-child {
          border-bottom: none;
        }

        /* Style for product image */
        .product-img {
          width: 60px;
          height: 60px;
          object-fit: cover;
          border-radius: 4px;
        }

        /* Style for action buttons */
        .action-buttons {
          display: flex;
          gap: 8px;
        }

        .edit-btn,
        .delete-btn {
          padding: 6px 12px;
          border: none;
          border-radius: 4px;
          cursor: pointer;
          transition: background-color 0.2s;
        }

        .edit-btn {
          background-color: #4CAF50;
          color: white;
        }

        .delete-btn {
          background-color: #f44336;
          color: white;
        }

        .edit-btn:hover {
          background-color: #45a049;
        }

        .delete-btn:hover {
          background-color: #da190b;
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {

          th,
          td {
            padding: 8px 10px;
          }

          .product-img {
            width: 40px;
            height: 40px;
          }

          .action-buttons {
            flex-direction: column;
            gap: 4px;
          }
        }
      </style>

    </body>


    <!-- Mirrored from themesflat.co/html/remos/product-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:40 GMT -->

    </html>