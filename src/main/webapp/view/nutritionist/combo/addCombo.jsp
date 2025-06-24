<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<!--<![endif]-->


<!-- Mirrored from themesflat.co/html/remos/add-new-user.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:55 GMT -->
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-select.min_1.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_1.css">



    <!-- Font -->
    <link rel="stylesheet" href="font/fonts.css">

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
                                    <h3>Add New User</h3>
                                    <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                        <li>
                                            <a href="index.html"><div class="text-tiny">Dashboard</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <div class="text-tiny">Add New Combo</div>
                                        </li>
                                    </ul>
                                </div>
                                <!-- add-new-user -->
                                <form class="form-add-new-user form-style-2">
                                    <div class="wg-box">
                                        <div class="left">
                                            <h5 class="mb-4">ComboFood</h5>
                                        </div>
                                        <div class="right flex-grow">
                                           
                                            <fieldset class="email mb-24">
                                                <div class="body-title mb-10">ComboId</div>
                                                <input class="flex-grow" type="text" placeholder="ID" name="comboId" tabindex="0" value="" aria-required="true" required="">
                                            </fieldset>
                                            <fieldset class="name mb-24">
                                                <div class="body-title mb-10">Name</div>
                                                <input class="flex-grow" type="text" placeholder="Comboname" name="name" tabindex="0" value="" aria-required="true" required="">
                                            </fieldset>
                                            <fieldset class="name mb-24">
                                                <div class="body-title mb-10">Description</div>
                                                <input class="flex-grow" type="text" placeholder="Description" name="description" tabindex="0" value="" aria-required="true" required="">
                                            </fieldset>
                                              <fieldset class="name mb-24">
                                                <div class="body-title mb-10">Description</div>
                                                <input class="flex-grow" type="text" placeholder="Description" name="description" tabindex="0" value="" aria-required="true" required="">
                                            </fieldset>
                                        </div>
                                    </div>
                                    <div class="wg-box">
                                       <div class="wg-table table-product-list">
                        <ul class="table-title flex gap20 mb-14">
                          <li>
                            <div class="body-title">Food Name</div>
                          </li>
                          <li>
                            <div class="body-title">Calo</div>
                          </li>
                          <li>
                            <div class="body-title">Category</div>
                          </li>
                          <li>
                            <div class="body-title">Price</div>
                          </li>
                          <li>
                            <div class="body-title">Status</div>
                          </li>
                          <li>
                            <div class="body-title">Action</div>
                          </li>
                        </ul>
                        <ul class="flex flex-column">
                          <c:forEach items="${listFood}" var="item">
                            <li class="product-item gap14">
                              <div class="image no-bg">
                                <img src=${item.getImage_url()} alt="">
                              </div>
                              <div class="flex items-center justify-between gap20 flex-grow">
                                <div class="name">
                                  <a href="product-list.html" class="body-title-2">${item.name}</a>
                                </div>
                                <div class="body-text">${item.getCalo()}</div>
                                <div class="body-text">
                                  <c:forEach items="${listCategory}" var="itemCategory">
                                    <c:if test="${itemCategory.getId() == item.getCategory_id()}">
                                      ${itemCategory.getName()}
                                    </c:if>
                                  </c:forEach>
                                </div>
                                <div class="body-text">
                                    <fmt:formatNumber value="${item.getPrice()}" type="number" groupingUsed="true" maxFractionDigits="0" /> VN?
                                </div>
                                <c:choose>
                                  <c:when test="${item.getStatus() == 'inactive'}">
                                    <div class="body-text food-inactive">${item.getStatus()}</div>
                                  </c:when>
                                  <c:otherwise>
                                    <div class="body-text food-active">${item.getStatus()}</div>
                                  </c:otherwise>
                                </c:choose>
                                <div class="list-icon-function">
                                  <div class="item edit">
                                    <a
                                      href="${pageContext.request.contextPath}/manage-food?action=update&id=${item.id}">
                                      <i class="icon-edit-3" style="color: green"></i>
                                    </a>
                                  </div>
                                  <div class="item delete" id="item-delete">
                                    <i class="icon-trash-2 item-trash" style="color: red;" data-id="${item.getId()}"
                                      data-name="${item.getName()}" onclick="showModalForm(event)"></i>
                                  </div>
                                </div>
                              </div>
                            </li>
                          </c:forEach>

                        </ul>
                      </div>
                                    </div>
                                    <div class="bot">
                                        <button class="tf-button w180" type="submit">Save</button>
                                    </div>

                                </form>
                                <!-- /add-new-user -->
                            </div>
                            <!-- /main-content-wrap -->
                        </div>
                        <!-- /main-content-wrap -->
                        <!-- bottom-page -->
                        <div class="bottom-page">
                            <div class="body-text">Copyright © 2024 Remos. Design with</div>
                            <i class="icon-heart"></i>
                            <div class="body-text">by <a href="https://themeforest.net/user/themesflat/portfolio">Themesflat</a> All rights reserved.</div>
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
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/zoom.js"></script>
    <script src="${pageContext.request.contextPath}/js/switcher.js"></script>
    <script src="${pageContext.request.contextPath}/js/theme-settings.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>

</body>


<!-- Mirrored from themesflat.co/html/remos/add-new-user.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:55 GMT -->
</html>