<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
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
            <style>
                .main-content {
                    display: flex;
                    min-height: calc(100vh - 160px);
                }

                .content-wrapper {
                    flex: 1;
                    padding: 30px;
                    background-color: #f8f9fa;
                }

                .form-group {
                    margin-bottom: 25px;
                    background: white;
                    padding: 25px;
                    border-radius: 8px;
                    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                }

                .form-group label {
                    display: block;
                    margin-bottom: 10px;
                    font-weight: 600;
                    color: #333;
                }

                .rating {
                    display: flex;
                    flex-direction: row-reverse;       /* đảo thứ tự sao trong DOM để CSS hoạt động đúng */
                    justify-content: flex-end;         /* ⭐ đẩy toàn bộ dãy sao về phía bên trái */
                }

                .rating input {
                    display: none;
                }

                .rating label {
                    font-size: 2em;
                    color: #ddd;
                    cursor: pointer;
                    transition: color 0.2s;
                    margin-right: 5px;
                }

                .rating input:checked~label,
                .rating input:checked~label~label {
                    color: #ffcc00;
                }

                .rating label:hover,
                .rating label:hover~label {
                    color: #ffcc00;
                }

                .feedback-text {
                    width: 100%;
                    min-height: 150px;
                    padding: 15px;
                    font-size: 16px;
                    border: 1px solid #ddd;
                    border-radius: 5px;
                    resize: vertical;
                    transition: border 0.3s;
                }

                .feedback-text:focus {
                    border-color: #4a90e2;
                    outline: none;
                    box-shadow: 0 0 0 3px rgba(74, 144, 226, 0.1);
                }

                .btn-submit {
                    background-color: #4CAF50;
                    color: white;
                    padding: 12px 24px;
                    border: none;
                    border-radius: 4px;
                    cursor: pointer;
                    font-size: 16px;
                    transition: background-color 0.3s;
                }

                .btn-submit:hover {
                    background-color: #45a049;
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
                        <link rel="stylesheet" type="text/css"
                              href="${pageContext.request.contextPath}/css/animate.min_1.css">
                            <link rel="stylesheet" type="text/css"
                                  href="${pageContext.request.contextPath}/css/animation.css">
                                <link rel="stylesheet" type="text/css"
                                      href="${pageContext.request.contextPath}/css/bootstrap.css">
                                    <link rel="stylesheet" type="text/css"
                                          href="${pageContext.request.contextPath}/css/bootstrap-select.min.css">
                                        <link rel="stylesheet" type="text/css"
                                              href="${pageContext.request.contextPath}/css/style_1.css">



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
                                                                            <jsp:include page="/view/common/sidebar.jsp"></jsp:include>
                                                                                <!-- /section-menu-left -->
                                                                                <!-- section-content-right -->
                                                                                <div class="section-content-right">
                                                                                    <!-- header-dashboard -->
                                                                                <jsp:include page = "/view/common/headerDashboard.jsp"></jsp:include>                                                                                    <!-- /header-dashboard -->
                                                                                    <!-- main-content -->
                                                                                    <div class="main-content">
                                                                                        <!-- main-content-wrap -->
                                                                                        <div class="main-content-inner">
                                                                                            <!-- main-content-wrap -->
                                                                                            <div class="main-content-wrap">
                                                                                                <div class="content-wrapper">
                                                                                                    <h3>Tạo/Chỉnh sửa phản hồi</h3>

                                                                                                    <form action="createfeedback"
                                                                                                          method="POST">
                                                                                                        <!-- Kiểm tra xem có order_item_id không -->
                                                                                                        <input type="hidden" name="order_item_id" value="${param.order_item_id}" />

                                                                                                    <div class="form-group">
                                                                                                        <label>Đánh giá:</label>
                                                                                                        <div class="rating">
                                                                                                            <input type="radio" id="star1" name="rating"
                                                                                                                   value="5"><label for="star1"><i
                                                                                                                        class="fas fa-star"></i></label>
                                                                                                                <input type="radio" id="star2" name="rating"
                                                                                                                       value="4"><label for="star2"><i
                                                                                                                            class="fas fa-star"></i></label>
                                                                                                                    <input type="radio" id="star3" name="rating"
                                                                                                                           value="3"><label for="star3"><i
                                                                                                                                class="fas fa-star"></i></label>
                                                                                                                        <input type="radio" id="star4" name="rating"
                                                                                                                               value="2"><label for="star4"><i
                                                                                                                                    class="fas fa-star"></i></label>
                                                                                                                            <input type="radio" id="star5" name="rating"
                                                                                                                                   value="1"><label for="star5"><i
                                                                                                                                        class="fas fa-star"></i></label>
                                                                                                                                </div>
                                                                                                                                </div>

                                                                                                                                <div class="form-group">
                                                                                                                                    <label for="feedbackText">Nội dung phản hồi:</label>
                                                                                                                                    <textarea id="feedbackText" name="feedbackText"
                                                                                                                                              class="feedback-text"
                                                                                                                                              placeholder="Viết ý kiến của bạn ..."></textarea>
                                                                                                                                </div>

                                                                                                                                <button type="submit" class="btn-submit">Send Feedback</button>
                                                                                                                                <c:choose>
                                                                                                                                    <c:when test="${param.source == 'feedback' || empty param.order_id}">
                                                                                                                                        <a href="${pageContext.request.contextPath}/feedback" class="btn-submit">
                                                                                                                                            Cancel Feedback
                                                                                                                                        </a>
                                                                                                                                    </c:when>
                                                                                                                                    <c:otherwise>
                                                                                                                                        <a href="${pageContext.request.contextPath}/orderdetail?order_id=${param.order_id}" class="btn-submit">
                                                                                                                                            Cancel Order Detail
                                                                                                                                        </a>
                                                                                                                                    </c:otherwise>
                                                                                                                                </c:choose>                                                                                                                               </form>
                                                                                                                                </div>
                                                                                                                                </div>
                                                                                                                                <!-- /main-content-wrap -->
                                                                                                                                </div>
                                                                                                                                <!-- /main-content-wrap -->
                                                                                                                                <!-- bottom-page -->
                                                                                                                                <jsp:include page="/view/common/footer.jsp"></jsp:include>
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

                                                                                                                                </body>


                                                                                                                                <!-- Mirrored from themesflat.co/html/remos/oder-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->

                                                                                                                                </html>