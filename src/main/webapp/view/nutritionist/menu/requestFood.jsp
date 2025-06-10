<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">



      <!--IzizToast-->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
      <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>
      <!-- Font -->
      <link rel="stylesheet" href="${pageContext.request.contextPath}/font/fonts.css">

      <!-- Icon -->
      <link rel="stylesheet" href="${pageContext.request.contextPath}/icon/style.css">

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
                      <h3>Các yêu cầu chờ duyệt</h3>

                    </div>
                    <!-- product-list -->
                    <div class="wg-box">
                      <div class="wg-table table-product-list">
                        <ul class="table-title flex gap20 mb-14">
                          <li>
                            <div class="body-title">Food Name</div>
                          </li>
                          <li>
                            <div class="body-title">Nutritionist</div>
                          </li>
                          <li>
                            <div class="body-title">User_name</div>
                          </li>
                          <li>
                            <div class="body-title">Price</div>
                          </li>
                          <li>
                            <div class="body-title">Calo</div>
                          </li>
                          <li>
                            <div class="body-title">Category</div>
                          </li>
                          <li>
                            <div class="body-title">Type</div>
                          </li>
                          <li>
                            <div class="body-title">Status</div>
                          </li>
                        </ul>
                        <ul class="flex flex-column">
                          <c:forEach items="${listFoodDraft}" var="item">
                            <li class="product-item gap14">
                              <div class="image no-bg">
                                <img src="${item.getImage_url()}" alt="">
                              </div>
                              <div class="flex items-center justify-between gap20 flex-grow">
                                <div class="name">
                                  <a href="product-list.html" class="body-title-2">${item.getName()}</a>
                                </div>
                                  <c:forEach items="${listNutri}" var="nutri">
                                    <c:if test="${nutri.getId() == item.getNutri_id()}">
                                        <div class="body-text">${nutri.getFull_name()}</div>
                                        <div class="body-text">${nutri.getUser_name()}</div>
                                    </c:if>
                                  </c:forEach>
                                <div class="body-text">
                                    <fmt:formatNumber value="${item.getPrice()}" type="number" groupingUsed="true" maxFractionDigits="0" /> VNĐ
                                </div>
                                <div class="body-text">${item.getCalo()}</div>
                                
                                <div class="body-text">
                                  <c:forEach items="${listCategory}" var="category">
                                    <c:if test="${item.getCategory_id() == category.getId()}">
                                      ${category.getName()}
                                    </c:if>
                                  </c:forEach>
                                </div>
                                <div>
                                  <c:choose>
                                    <c:when test="${item.getType()== 'CREATE'}">
                                      <div class="body-text typeCreate">${item.getType()}</div>
                                    </c:when>

                                    <c:when test="${item.getType()== 'UPDATE'}">
                                      <div class="body-text typeUpdate">${item.getType()}</div>
                                    </c:when>

                                    <c:otherwise>
                                      <div class="body-text typeDelete">${item.getType()}</div>
                                    </c:otherwise>
                                  </c:choose>

                                </div>
                                <div class="body-text">
                                  <c:forEach items="${listRequestNotDone}" var="request">
                                    <c:if test="${request.getFoodDraftId() == item.getId()}">
                                        <span class="statusNotDone">Chờ duyệt</span>
                                    </c:if>
                                  </c:forEach>
                                </div>


                              </div>
                            </li>
                          </c:forEach>
                          </li>
                        </ul>
                      </div>
                      <div class="divider"></div>
                      <div class="flex items-center justify-between flex-wrap gap10">
                        <div class="text-tiny">Showing 10 entries</div>
                        <ul class="wg-pagination">

                          <li>
                            <a href="${pageContext.request.contextPath}/manage-food?action=request&page=1"><i class="icon-chevron-left"></i></a>
                          </li>
                            <c:choose>
                                <c:when test="${currentPage < totalPage - 2}">
                                    <li class="active">
                                        <a href="${pageContext.request.contextPath}/manage-food?action=request&page=${currentPage}">${currentPage}</a>
                                    </li>
                                    
                                    <li class="">
                                        <a href="${pageContext.request.contextPath}/manage-food?action=request&page=${currentPage + 1}">${currentPage + 1}</a>
                                    </li>
                                    
                                    <li>
                                        <span>...</span>
                                    </li>
                                    
                                    <li class="">
                                        <a href="${pageContext.request.contextPath}/manage-food?action=request&page=${totalPage}">${totalPage}</a>
                                    </li>
                                </c:when>
                                
                                <c:otherwise>
                                    <c:forEach begin="${totalPage-2 < 0 ? 1 : totalPage - 2}" end="${totalPage}" var="i">
                                        <li class="${currentPage == i ? 'active' : ''}">
                                            <a href="${pageContext.request.contextPath}/manage-food?action=request&page=${i}">${i}</a>
                                        </li>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                            
                          <li>
                            <a href="${pageContext.request.contextPath}/manage-food?action=request&page=${totalPage}"><i class="icon-chevron-right"></i></a>
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
                <jsp:include page="../../common/nutritionist/footer.jsp"></jsp:include>
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
        .typeCreate {
          background: #649F67;
          color: white !important;
          text-align: center;
          border-radius: 10px;
          display: inline;
          padding: 5px 10px
        }

        .typeUpdate {
          background: #89CFF0;
          color: white !important;
          text-align: center;
          border-radius: 10px;
          display: inline;
          padding: 5px 10px
        }

        .typeDelete {
          background: #DC143C;
          color: white !important;
          text-align: center;
          border-radius: 10px;
          display: inline;
          padding: 5px 10px
        }
        
        .statusNotDone{
            background: #808080;
            padding: 5px 10px;
            border-radius: 10px
            
        }
      </style>
    </body>


    <!-- Mirrored from themesflat.co/html/remos/product-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:40 GMT -->

    </html>