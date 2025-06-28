<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->
 <!--link izitoatMess-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
            <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>

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
                                                                                                <h3>Product List</h3>
                                                                                                <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                                                                                    <li>
                                                                                                        <a href="index.html"><div class="text-tiny">Dashboard</div></a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <i class="icon-chevron-right"></i>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <a href="#"><div class="text-tiny">Combo</div></a>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <i class="icon-chevron-right"></i>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="text-tiny">Combo List</div>
                                                                                                    </li>
                                                                                                </ul>
                                                                                            </div>
                                                                                            <!-- product-list -->
                                                                                            <div class="wg-box">
                                                                                                <div class="title-box">
                                                                                                    <i class="icon-coffee"></i>

                                                                                                </div>
                                                                                                <div class="flex items-center justify-between gap10 flex-wrap">
                                                                                                    <div class="wg-filter flex-grow" action = "${pageContext.request.contextPath}/managerCombo" method = "get">
                                                                                                    <form class="form-search">
                                                                                                        <fieldset class="name">
                                                                                                            <input type="text" placeholder="Search here..." class="" name="name" tabindex="2" value="" aria-required="true" required="">
                                                                                                        </fieldset>
                                                                                                        <div class="button-submit">
                                                                                                            <button class="" type="submit"><i class="icon-search"></i></button>
                                                                                                        </div>
                                                                                                    </form>
                                                                                                </div>
                                                                                                <a class="tf-button style-1 w208" href="${pageContext.request.contextPath}/managerCombo?action=add"><i class="icon-plus"></i>Add new</a>
                                                                                            </div>
                                                                                            <div class="wg-table table-product-list">
                                                                                                <ul class="table-title flex gap20 mb-14">
                                                                                                    <li style="width: 350px">
                                                                                                        <div class="body-title">Combo</div>
                                                                                                    </li>    
                                                                                                    <li style="width: 100px">
                                                                                                        <div class="body-title" style="width: 100px">Combo ID</div>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="body-title">Description</div>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="body-title">OriginalPrice</div>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="body-title">DiscountPrice</div>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="body-title">Status</div>
                                                                                                    </li>
                                                                                                    <li>
                                                                                                        <div class="body-title">Action</div>
                                                                                                    </li>
                                                                                                </ul>
                                                                                                <ul class="flex flex-column">
                                                                                                    <c:forEach items="${listCombo}" var = "cb">
                                                                                                        <li class="product-item gap14">
                                                                                                            <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                                <div class="name">
                                                                                                                    <a href="${pageContext.request.contextPath}/managerCombo?action=viewComboFoodDetail&id=${cb.comboId}" class="body-title-2">${cb.comboName == null ? 'null' : cb.comboName}</a>
                                                                                                                </div>
                                                                                                                <div class="body-text" style="width: 200px">${cb.comboId == null ? 'null': cb.comboId}</div>
                                                                                                                <div class="body-text">${cb.description== null ? 'null' : cb.description}</div>
                                                                                                                <div class="body-text">${cb.originalPrice == null ? 'null' : cb.originalPrice}</div>
                                                                                                                <div class="body-text">${cb.discountPrice == null ? 'null' : cb.discountPrice}</div>
                                                                                                                <div>
                                                                                                                    <c:choose>
                                                                                                                        <c:when test ="${cb.status == 'active'}">
                                                                                                                            <div class="status active highlight">Active</div>
                                                                                                                        </c:when>
                                                                                                                        <c:when test ="${cb.status == 'inactive'}">
                                                                                                                            <div class="status inactive highlight">Inactive</div>
                                                                                                                        </c:when>
                                                                                                                    </c:choose>
                                                                                                                </div>

                                                                                                                <div class="list-icon-function">
                                                                                                                    <div class="item eye">
                                                                                                                        <i class="icon-eye"></i>
                                                                                                                    </div>
                                                                                                                    <div class="item edit">
                                                                                                                        <i class="icon-edit-3"></i>
                                                                                                                    </div>
                                                                                                                    
                                                                                                                    <label class="switch"
                                                                                                                           title="${cb.status eq 'active' ? 'inactive' : 'active'}">
                                                                                                                        <input type="checkbox"
                                                                                                                               <c:if test='${cb.status eq "active"}'>checked</c:if>
                                                                                                                               onchange="location.href = '${pageContext.request.contextPath}/managerCombo?action=${cb.status eq 'active' ? 'inactive' : 'active'}&comboId=${cb.comboId}'" />
                                                                                                                        <span class="slider"></span>
                                                                                                                    </label>
                                                                                                                </div>
                                                                                                            </div>

                                                                                                        </li> 
                                                                                                    </c:forEach>
                                                                                                </ul>
                                                                                            </div>
                                                                                            <div class="divider"></div>
                                                                                            <div class="flex items-center justify-between flex-wrap gap10">
                                                                                                <div class="text-tiny">
                                                                                                    Showing ${startRecord} to ${endRecord} of ${totalCategory} entries
                                                                                                </div>
                                                                                                <div class="pagination">
                                                                                                    <ul class="wg-pagination">
                                                                                                        <!--                                                                                                            
                                                                                                        <c:if test="${currentPage > 1}">
                                                                                                            <li>
                                                                                                                <a href="?action=${param.action}&page=${currentPage - 1}&pageSize=${pageSize}">
                                                                                                                    <i class="icon-chevron-left"></i>
                                                                                                                </a>
                                                                                                            </li>
                                                                                                        </c:if>
                                                                                                        <!-- Hi?n th? các trang -->
                                                                                                        <c:choose>
                                                                                                            <c:when test="${totalPages <= 7}">
                                                                                                                <!-- Hi?n th? t?t c? trang n?u <= 7 trang -->
                                                                                                                <c:forEach var="i" begin="1" end="${totalPages}">
                                                                                                                    <li <c:if test="${i == currentPage}">class="active"</c:if>>
                                                                                                                        <a href="?action=${param.action}&page=${i}&pageSize=${pageSize}">${i}</a>
                                                                                                                    </li>
                                                                                                                </c:forEach>
                                                                                                            </c:when>
                                                                                                            <c:otherwise>
                                                                                                                <!-- Hi?n th? phân trang  -->
                                                                                                                <c:if test="${currentPage > 1}">
                                                                                                                    <li>
                                                                                                                        <a href="?action=${param.action}&page=1&pageSize=${pageSize}">1</a>
                                                                                                                    </li>
                                                                                                                </c:if>
                                                                                                                <c:if test="${currentPage > 3}">
                                                                                                                    <li><span>...</span></li>
                                                                                                                    </c:if>
                                                                                                                    <c:forEach var="i" begin="${currentPage > 2 ? currentPage - 1 : 1}"
                                                                                                                               end="${currentPage < totalPages - 1 ? currentPage + 1 : totalPages}">
                                                                                                                    <li <c:if test="${i == currentPage}">class="active"</c:if>>
                                                                                                                        <a href="?action=${param.action}&page=${i}&pageSize=${pageSize}">${i}</a>
                                                                                                                    </li>
                                                                                                                </c:forEach>
                                                                                                                <c:if test="${currentPage < totalPages - 2}">
                                                                                                                    <li><span>...</span></li>
                                                                                                                    </c:if>
                                                                                                                    <c:if test="${currentPage < totalPages}">
                                                                                                                    <li>
                                                                                                                        <a href="?action=${param.action}&page=${totalPages}&pageSize=${pageSize}">${totalPages}</a>
                                                                                                                    </li>
                                                                                                                </c:if>
                                                                                                            </c:otherwise>
                                                                                                        </c:choose>
                                                                                                        <!-- Nút Next -->
                                                                                                        <c:if test="${currentPage < totalPages}">
                                                                                                            <li>
                                                                                                                <a href="?action=${param.action}&page=${currentPage + 1}&pageSize=${pageSize}">
                                                                                                                    <i class="icon-chevron-right"></i>
                                                                                                                </a>
                                                                                                            </li>
                                                                                                        </c:if>

                                                                                                    </ul>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <!-- /product-list -->
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
                                                            <c:if test="${not empty sessionScope.toastMessage}">
                                                                <script>
                                                                iziToast.${sessionScope.toastType}({
                                                                title: '${sessionScope.toastType == "success" ? "Thành công" : "Thành Công"}',
                                                                message: '${sessionScope.toastMessage}',
                                                                position: 'topRight',
                                                                timeout: 3000,
                                                                color: '${sessionScope.toastType == "success" ? "green" : "red"}'
                                                                                 });
                                                                </script>
                                                                <c:remove var="toastMessage" scope="session"/>
                                                                <c:remove var="toastType" scope="session"/>
                                                            </c:if>
                                                            <style>
                                                                .status.inactive.highlight {
                                                                    font-size: 16px;     /* t?ng kích th??c */
                                                                    font-weight: bold;   /* ch? ??m */
                                                                    color: red;          /* màu ?? n?i b?t */

                                                                    padding: 4px 8px;
                                                                    border-radius: 10px;
                                                                    display: inline-block;
                                                                }
                                                                .status.active.highlight {
                                                                    font-size: 16px;     /* t?ng kích th??c */
                                                                    font-weight: bold;   /* ch? ??m */
                                                                    color: green;          /* màu ?? n?i b?t */
                                                                    /* n?n nh?t ?? d? nhìn */
                                                                    padding: 4px 8px;
                                                                    border-radius: 10px;
                                                                    display: inline-block;
                                                                }
                                                            </style>
                                                        </body>


                                                        <!-- Mirrored from themesflat.co/html/remos/product-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:40 GMT -->
                                                        </html>