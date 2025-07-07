<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->


    <!-- Mirrored from themesflat.co/html/remos/oder-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
                <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>
                <style>
                    .rating-stars {
                        display: inline-flex;
                        align-items: center;
                    }

                    .rating-stars .fa-star {
                        color: #ccc; /* màu mặc định là xám */
                        font-size: 20px;
                        margin-right: 2px;
                    }

                    .rating-stars .fas.fa-star {
                        color: #f5c518; /* màu vàng cho sao đầy */
                    }

                </style>
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
                                                            <link rel="apple-touch-icon-precomposed" href="images/favicon.png">

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
                                                                                <jsp:include page = "/view/common/sidebar.jsp"></jsp:include>
                                                                                    <!-- /section-menu-left -->
                                                                                    <!-- section-content-right -->
                                                                                    <div class="section-content-right">
                                                                                        <!-- header-dashboard -->
                                                                                    <jsp:include page = "/view/common/headerDashboard.jsp"></jsp:include>                                                                                <!-- main-content -->
                                                                                        <div class="main-content">
                                                                                            <!-- main-content-wrap -->
                                                                                            <div class="main-content-inner">
                                                                                                <!-- main-content-wrap -->
                                                                                                <div class="main-content-wrap">
                                                                                                    <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                                                                                        <h3>Feedback List</h3>
                                                                                                    </div>
                                                                                                    <!-- feedback-list -->
                                                                                                    <div class="wg-box">
                                                                                                        <div class="flex items-center justify-between gap10 flex-wrap">
                                                                                                            <div class="wg-filter flex-grow">
                                                                                                                <form class="form-search">
                                                                                                                    <fieldset class="name">
                                                                                                                        <select name="rating" required>
                                                                                                                            <option value="all" ${param.rating == 'all' ? 'selected' : ''}>-- All Rating --</option>
                                                                                                                        <option value="5"  ${param.rating == '5' ? 'selected' : ''}>5</option>
                                                                                                                        <option value="4"  ${param.rating == '4' ? 'selected' : ''}>4</option>
                                                                                                                        <option value="3"  ${param.rating == '3' ? 'selected' : ''}>3</option>
                                                                                                                        <option value="2"  ${param.rating == '2' ? 'selected' : ''}>2</option>
                                                                                                                        <option value="1"  ${param.rating == '1' ? 'selected' : ''}>1</option>
                                                                                                                    </select>                                                                                                         </fieldset>
                                                                                                                <div class="button-submit">
                                                                                                                    <button class="" type="submit"><i class="icon-search"></i></button>
                                                                                                                </div>
                                                                                                            </form>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                    <div class="wg-table table-all-category">
                                                                                                        <ul class="table-title flex gap20 mb-14">
                                                                                                            <li>
                                                                                                                <div class="body-title">Food</div>
                                                                                                            </li>
                                                                                                            <li>
                                                                                                                <div class="body-title">Rating</div>
                                                                                                            </li>
                                                                                                            <li>
                                                                                                                <div class="body-title">Content</div>
                                                                                                            </li>
                                                                                                            <li>
                                                                                                                <div class="body-title">Updated At</div>
                                                                                                            </li>
                                                                                                            <li>
                                                                                                                <div class="body-title">Action</div>
                                                                                                            </li>
                                                                                                        </ul>
                                                                                                        <ul class="flex flex-column">
                                                                                                            <c:forEach var="feedback" items="${feedbacklist}">
                                                                                                                <c:if test="${feedback.visible}">
                                                                                                                    <li class="product-item gap14">
                                                                                                                        <c:set var="food" value="${foodMap[feedback.order_item_id]}" />
                                                                                                                        <div class="image no-bg">
                                                                                                                            <img src="${food.image_url}" alt="">
                                                                                                                        </div>
                                                                                                                        <div class="flex items-center justify-between gap20 flex-grow">
                                                                                                                            <div class="name">
                                                                                                                                <a href="product-list.html" class="body-title-2">${food.name}</a>
                                                                                                                            </div>
                                                                                                                            <div class="rating-stars">
                                                                                                                                <c:forEach begin="1" end="5" var="i">
                                                                                                                                    <i class="<c:choose>
                                                                                                                                           <c:when test='${i <= feedback.rating}'>fas fa-star</c:when>
                                                                                                                                           <c:otherwise>far fa-star</c:otherwise>
                                                                                                                                       </c:choose>"></i>
                                                                                                                                </c:forEach>
                                                                                                                            </div>
                                                                                                                            <div class="body-text format-view">${feedback.content} </div>
                                                                                                                            <div class="body-text format-view">
                                                                                                                                <fmt:formatDate value="${feedback.createdAt}" pattern="dd-MM-yyyy HH:mm:ss" />
                                                                                                                            </div>

                                                                                                                            <div class="list-icon-function ">
                                                                                                                                <div class="item edit">
                                                                                                                                    <a href="createfeedback?order_item_id=${feedback.order_item_id}&source=feedback" title="Write Feedback">
                                                                                                                                        <i class="icon-edit-3"></i>
                                                                                                                                    </a>
                                                                                                                                </div>
                                                                                                                                <a href="${pageContext.request.contextPath}/remove?id=${feedback.id}" class="item eye">
                                                                                                                                    <i class="icon-trash-2"></i>
                                                                                                                                </a>
                                                                                                                            </div>
                                                                                                                        </div>
                                                                                                                    </li>
                                                                                                                </c:if>
                                                                                                            </c:forEach>

                                                                                                        </ul>
                                                                                                    </div>
                                                                                                    <div class="divider"></div>
                                                                                                    <div class="flex items-center justify-between flex-wrap gap10">
                                                                                                        <div class="divider"></div>
                                                                                                        <div class="pagination">

                                                                                                            <ul class="wg-pagination">

                                                                                                                <li>
                                                                                                                    <a href="${pageContext.request.contextPath}/feedback?page=1&rating=${param.rating}"><i class="icon-chevron-left"></i></a>
                                                                                                                </li>
                                                                                                                <c:choose>
                                                                                                                    <c:when test="${currentPage <= totalPages - 2}">
                                                                                                                        <c:if test="${currentPage > 1}">
                                                                                                                            <li class="">
                                                                                                                                <a href="${pageContext.request.contextPath}/feedback?page=${currentPage - 1}&rating=${param.rating}">${currentPage - 1}</a>
                                                                                                                            </li>
                                                                                                                        </c:if>
                                                                                                                        <li class="active">
                                                                                                                            <a href="${pageContext.request.contextPath}/feedback?page=${currentPage}&rating=${param.rating}">${currentPage}</a>
                                                                                                                        </li>

                                                                                                                        <li class="">
                                                                                                                            <a href="${pageContext.request.contextPath}/feedback?page=${currentPage + 1}&rating=${param.rating}">${currentPage + 1}</a>
                                                                                                                        </li>

                                                                                                                        <c:if test="${currentPage < totalPages - 2}">
                                                                                                                            <li>
                                                                                                                                <span>...</span>
                                                                                                                            </li>
                                                                                                                        </c:if>
                                                                                                                        <li class="">
                                                                                                                            <a href="${pageContext.request.contextPath}/feedback?page=${totalPages}&rating=${param.rating}">${totalPages}</a>
                                                                                                                        </li>
                                                                                                                    </c:when>

                                                                                                                    <c:otherwise>
                                                                                                                        <c:forEach begin="${totalPages-2 <= 0 ? 1 : totalPages - 2}" end="${totalPages}" var="i">
                                                                                                                            <li class="${currentPage == i ? 'active' : ''}">
                                                                                                                                <a href="${pageContext.request.contextPath}/feedback?page=${i}&rating=${param.rating}">${i}</a>
                                                                                                                            </li>
                                                                                                                        </c:forEach>
                                                                                                                    </c:otherwise>
                                                                                                                </c:choose>

                                                                                                                <li>
                                                                                                                    <a href="${pageContext.request.contextPath}/feedback?page=${totalPages}&rating=${param.rating}"><i class="icon-chevron-right"></i></a>
                                                                                                                </li>
                                                                                                            </ul>

                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                                <!-- /feedback-list -->
                                                                                            </div>
                                                                                            <!-- /main-content-wrap -->
                                                                                        </div>
                                                                                        <!-- /main-content-wrap -->
                                                                                        <!-- bottom-page -->
                                                                                        <jsp:include page = "/view/common/footer.jsp"></jsp:include>
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

                                                                    <c:if test="${sessionScope.toastType == 'success'}">
                                                                        <script>
                                                                            document.addEventListener("DOMContentLoaded", function () {
                                                                                iziToast.success({
                                                                                    title: "Thông báo",
                                                                                    message: '<c:out value="${sessionScope.toastMessage}" />',
                                                                                    position: 'topRight',
                                                                                    timeout: 5000,
                                                                                    backgroundColor: "#28a745"
                                                                                });
                                                                            });
                                                                        </script>
                                                                        <!--Xóa đi biến isDelete sau khi đã thông báo--> 
                                                                        <%
                                                                            session.removeAttribute("toastType");
                                                                            session.removeAttribute("toastMessage");
                                                                        %>
                                                                    </c:if>

                                                                </body>


                                                                <!-- Mirrored from themesflat.co/html/remos/oder-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
                                                                </html>