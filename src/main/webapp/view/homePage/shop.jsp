<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

      <!DOCTYPE html>
      <html lang="zxx">

      <!-- Mirrored from templates.hibootstrap.com/hilo/default/shop-left-sidebar.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:18 GMT -->

      <head>
        <!-- Required Meta Tags -->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--=== Link of CSS Files ===-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/boxicons.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nice-select.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/meanmenu.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme-dark.css">

        <!--=== Title & Favicon ===-->
        <title>Hilo - Organic Food eCommerce Shop HTML Template</title>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
      </head>

      <body>
        <!-- Pre Loader -->
        <div class="preloader">
          <div class="d-table">
            <div class="d-table-cell">
              <img src="${pageContext.request.contextPath}/images/preloder-img.png" alt="Images">
              <h2>Hilo</h2>
            </div>
          </div>
        </div>
        <!-- End Pre Loader -->

        <!-- Start Navbar Area -->
        <div class="navbar-area">
          <!-- Menu For Mobile Device -->
          <div class="mobile-nav">
            <a href="index.html" class="logo">
              <img src="${pageContext.request.contextPath}/images/logos/logo-1.png" alt="Logo">
            </a>
          </div>

          <!-- Menu For Desktop Device -->
          <jsp:include page="../common/homePage/header.jsp"></jsp:include>

          <div class="side-nav-responsive">
            <div class="container">
              <div class="dot-menu">
                <div class="circle-inner">
                  <div class="circle circle-one"></div>
                  <div class="circle circle-two"></div>
                  <div class="circle circle-three"></div>
                </div>
              </div>

              <div class="container-3">
                <div class="side-nav-inner">
                  <div class="side-nav justify-content-center align-items-center">
                    <div class="side-nav-item">
                      <div class="language-on-list">
                        <select class="language-list-item">
                          <option>English</option>
                          <option>العربيّة</option>
                          <option>Deutsch</option>
                          <option>Português</option>
                          <option>简体中文</option>
                        </select>
                      </div>

                      <div class="side-nav-cart">
                        <a href="#"><i class='bx bx-cart'></i></a>
                        <span>1</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- End Navbar Area -->

        <!-- Inner Banner Area -->
        <div class="inner-banner-area">
          <div class="container">
            <div class="row align-items-center">
              <div class="col-lg-5 col-md-5">
                <div class="inner-content">
                  <h2>Shop Left Sidebar</h2>
                  <ul>
                    <li><a href="index.html">Home</a></li>

                  </ul>
                </div>
              </div>

              <div class="col-lg-7 col-md-7">
                <div class="inner-img">
                  <img src="${pageContext.request.contextPath}/images/inner-banner/inner-banner6.png" alt="Images">
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- Inner Banner Area End -->

        <!-- Product Area -->
        <div class="product-area pt-100 pb-70">
          <div class="container">
            <div class="row">
              <div class="col-lg-3">
                <div class="product-side-bar">
                  <!--Search Form-->
                  <div class="search-widget">
                    <form class="search-form" action="${pageContext.request.contextPath}/shop">
                      <input name="foodName" type="search" class="form-control" placeholder="Search by food name...">
                      <input type="hidden" name="action" value="search">
                      <button type="submit">
                        <i class="bx bx-search"></i>
                      </button>
                    </form>
                  </div>

                  <!--Price Range-->
                  <div class="product-side-bar-widget">
                    <h3 class="title">Prices Range</h3>
                    <form class="price-range-content">
                      <div class="price-range-bar" id="range-slider"></div>
                      <div class="price-range-filter">
                        <div class="price-range-filter-item d-flex align-items-center order-1 order-xl-2">
                          <h4>Range:</h4>
                          <input type="text" id="price-amount" readonly>
                        </div>

                        <div class="price-range-filter-item price-range-filter-button order-2 order-xl-1">
                          <button class="btn btn-red btn-icon">Filter</button>
                        </div>
                      </div>
                    </form>
                  </div>

                  <!--Food Categories-->
                  <div class="product-side-bar-widget">
                    <h3 class="title">Product Categories</h3>
                    <div class="product-side-categories">
                      <ul>
                          <li class="${category == 0 ? 'active': ''}">
                              <a href="${pageContext.request.contextPath}/shop">Tất cả sản phẩm</a>
                          </li>
                        <c:forEach items="${listFoodCategory}" var="item">
                            <li class="${category == item.getId()?'active' : ''}">
                                <a href="${pageContext.request.contextPath}/shop?action=foodByCategory&category=${item.getId()}">${item.getName()}</a>
                            </li>
                        </c:forEach>
                      </ul>
                    </div>
                  </div>

                  <!--Prices-->
                  <div class="product-side-bar-widget">
                    <h3 class="title">Prices</h3>
                    <div class="product-side-categories">
                      <ul>
                        <li class="active">
                          <a href="#">$0-$50</a>
                        </li>
                        <li>
                          <a href="#">$51-$100</a>
                        </li>
                        <li>
                          <a href="#">$101-$150</a>
                        </li>
                        <li>
                          <a href="#">$151-$200</a>
                        </li>
                        <li>
                          <a href="#">$200-$250</a>
                        </li>
                        <li>
                          <a href="#">$250-$300</a>
                        </li>
                        <li>
                          <a href="#">$350-$400</a>
                        </li>
                        <li>
                          <a href="#">$400-$450</a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-lg-9">
                <!--Category--> 
                <div class="product-topper">
                  <div class="row">
                    <div class="col-lg-8 col-md-8">
                      <div class="product-topper-title">
                        <h3>View All Products <span>( Showing 1-15 of 120 result )</span> </h3>
                      </div>
                    </div>
                    <div class="col-lg-4 col-md-4">
                      <div class="product-category">
                        <div class="form-group">
                          <select class="form-control">
                            <option>Categories</option>
                            <option>Beef Meat</option>
                            <option>Vegetable</option>
                            <option>Natural Fruits</option>
                            <option>Health & Beauty</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                
                <!--Food list-->
                <div class="row">
                  <c:forEach items="${listFood}" var="item">
                    <div class="col-lg-4 col-sm-6">
                      <div class="product-item">
                        <div class="product-img">
                          <a href="shop-details.html">
                            <img src="${item.getImage_url()}" alt="Product Images">
                          </a>
                          <ul class="product-item-action">
                            <li><a href="#"><i class='bx bx-repost'></i></a></li>
                            <li><a href="wishlist.html"><i class='bx bx-heart'></i></a></li>
                            <li><a href="cart.html"><i class='bx bx-cart'></i></a></li>
                          </ul>
                        </div>

                        <div class="content">
                          <h3><a href="shop-details.html">${item.getName()}</a></h3>
                          <div>
                            Calo: ${item.getCalo()}
                          </div>
                          <span><fmt:formatNumber value="${item.getPrice()}" type="number" groupingUsed="true" maxFractionDigits="0" /> VNĐ</span>
                        </div>
                      </div>
                    </div>
                  </c:forEach>

                </div>
                
                <!--Pagination--> 
                <div class="col-lg-12 col-md-12 text-center">
                    <c:if test="${not empty category}">
                        <!--pagination for all food--> 
                        <c:if test="${category == 0}">
                            <div class="pagination-area">

                                <a href="${pageContext.request.contextPath}/shop?page=1" class="prev page-numbers">
                                    <i class="bx bx-chevrons-left"></i>
                                </a>

                                <c:choose>
                                    <c:when test="${currentPage <= totalPage - 2}">
                                        <c:if test="${currentPage > 1}">
                                            <a href="${pageContext.request.contextPath}/shop?page=${currentPage - 1}" class="page-numbers">${currentPage - 1}</a>
                                        </c:if>
                                        <a href="${pageContext.request.contextPath}/shop?page=${currentPage}" class="page-numbers current">${currentPage}</a>

                                        <a href="${pageContext.request.contextPath}/shop?page=${currentPage + 1}" class="page-numbers">${currentPage + 1}</a>

                                        <c:if test="${currentPage < totalPage - 2}">
                                            <a href="#" class="page-numbers">...</a>
                                        </c:if>

                                        <a href="${pageContext.request.contextPath}/shop?page=${totalPage}" class="page-numbers">${totalPage}</a>
                                    </c:when>  
                                    <c:otherwise>
                                        <c:forEach begin="${totalPage-2 < 0 ? 1 : totalPage - 2}" end="${totalPage}" var="i">
                                                 <a href="${pageContext.request.contextPath}/shop?page=${i}"
                                                    class="page-numbers ${currentPage == i?'current' : ''}">${i}</a>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>

                                <a href="${pageContext.request.contextPath}/shop?page=${totalPage}" class="next page-numbers">
                                    <i class="bx bx-chevrons-right"></i>
                                </a>

                            </div>
                        </c:if>
                        <!--pagination for food category--> 
                        <c:if test="${category != 0 && not empty category}">
                            <div class="pagination-area">

                                <a href="${pageContext.request.contextPath}/shop?action=foodByCategory&category=${category}&page=1" class="prev page-numbers">
                                    <i class="bx bx-chevrons-left"></i>
                                </a>

                                <c:choose>
                                    <c:when test="${currentPage <= totalPage - 2}">
                                        <c:if test="${currentPage > 1}">
                                            <a href="${pageContext.request.contextPath}/shop?action=foodByCategory&category=${category}&page=${currentPage - 1}" class="page-numbers">${currentPage - 1}</a>
                                        </c:if>
                                        <a href="${pageContext.request.contextPath}/shop?action=foodByCategory&category=${category}&page=${currentPage}" class="page-numbers current">${currentPage}</a>

                                        <a href="${pageContext.request.contextPath}/shop?action=foodByCategory&category=${category}&page=${currentPage + 1}" class="page-numbers">${currentPage + 1}</a>

                                        <c:if test="${currentPage < totalPage - 2}">
                                            <a href="#" class="page-numbers">...</a>
                                        </c:if>


                                        <a href="${pageContext.request.contextPath}/shop?action=foodByCategory&category=${category}&page=${totalPage}" class="page-numbers">${totalPage}</a>
                                    </c:when>  
                                    <c:otherwise>
                                        <c:forEach begin="${totalPage-2 < 0 ? 1 : totalPage - 2}" end="${totalPage}" var="i">
                                                 <a href="${pageContext.request.contextPath}/shop?action=foodByCategory&category=${category}&page=${i}"
                                                    class="page-numbers ${currentPage == i?'current' : ''}">${i}</a>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>

                                <a href="${pageContext.request.contextPath}/shop?action=foodByCategory&category=${category}&page=${totalPage}" class="next page-numbers">
                                    <i class="bx bx-chevrons-right"></i>
                                </a>
                            </div>
                        </c:if>
                    </c:if>
                    <c:if test="${not empty isSearch}">
                        <div class="pagination-area">

                                <a href="${pageContext.request.contextPath}/shop?foodName=${foodName}&action=search&page=1" class="prev page-numbers">
                                    <i class="bx bx-chevrons-left"></i>
                                </a>

                                <c:choose>
                                    <c:when test="${currentPage <= totalPage - 2}">
                                        <c:if test="${currentPage > 1}">
                                            <a href="${pageContext.request.contextPath}/shop?foodName=${foodName}&action=search&page=${currentPage - 1}" class="page-numbers">${currentPage - 1}</a>
                                        </c:if>
                                        <a href="${pageContext.request.contextPath}/shop?foodName=${foodName}&action=search&page=${currentPage}" class="page-numbers current">${currentPage}</a>

                                        <a href="${pageContext.request.contextPath}/shop?foodName=${foodName}&action=search&page=${currentPage + 1}" class="page-numbers">${currentPage + 1}</a>

                                        <c:if test="${currentPage < totalPage - 2}">
                                            <a href="#" class="page-numbers">...</a>
                                        </c:if>

                                        <a href="${pageContext.request.contextPath}/shop?foodName=${foodName}&action=search&page=${totalPage}" class="page-numbers">${totalPage}</a>
                                    </c:when>  
                                    <c:otherwise>
                                        <c:forEach begin="${totalPage-2 < 0 ? 1 : totalPage - 2}" end="${totalPage}" var="i">
                                                 <a href="${pageContext.request.contextPath}/shop?foodName=${foodName}&action=search&page=${i}"
                                                    class="page-numbers ${currentPage == i?'current' : ''}">${i}</a>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>

                                <a href="${pageContext.request.contextPath}/shop?foodName=${foodName}&action=search&page=${totalPage}" class="next page-numbers">
                                    <i class="bx bx-chevrons-right"></i>
                                </a>

                            </div>
                    </c:if>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- Product Area End -->

        <!-- Footer Area -->
        <jsp:include page="../common/homePage/footer.jsp"></jsp:include>
        <!-- Footer Area End -->

        <!--=== Link of JS Files ===-->
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.nice-select.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/meanmenu.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.ajaxchimp.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/form-validator.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/contact-form-script.js"></script>
        <script src="${pageContext.request.contextPath}/js/mixitup.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/custom.js"></script>
        
        <style>
            .product-img {
                height: 250px; 
                overflow: hidden;
                display: flex;
                justify-content: center;
                align-items: center;
                border-radius: 15px !important;
              }
              
              .product-img a {
                  height: 100%;
                  width: auto;
              }

              .product-img img {
                height: 100%;
                width: 100%;
                object-fit: cover;
                padding:0px !important;
               }
        </style>
      </body>

      <!-- Mirrored from templates.hibootstrap.com/hilo/default/shop-left-sidebar.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 May 2025 14:15:18 GMT -->

      </html>