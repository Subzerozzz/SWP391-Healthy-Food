<%-- 
    Document   : category_add
    Created on : Jun 12, 2025, 12:04:52 AM
    Author     : Hang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->
    <!-- iziToast CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/izitoast/dist/css/iziToast.min.css">
        <!-- iziToast JS -->
        <script src="https://cdn.jsdelivr.net/npm/izitoast/dist/js/iziToast.min.js"></script>

        <!-- Mirrored from themesflat.co/html/remos/new-category.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
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
                                            <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font/fonts.css">
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
                                                                            <jsp:include page = "../../common/sidebar.jsp"></jsp:include>
                                                                                <!-- /section-menu-left -->
                                                                                <!-- section-content-right -->
                                                                                <div class="section-content-right">
                                                                                    <!-- header-dashboard -->
                                                                                <jsp:include page = "../../common/headerDashboard.jsp"></jsp:include>
                                                                                    <!-- /header-dashboard -->
                                                                                    <!-- main-content -->
                                                                                    <div class="main-content">
                                                                                        <!-- main-content-wrap -->
                                                                                        <div class="main-content-inner">
                                                                                            <!-- main-content-wrap -->
                                                                                            <div class="main-content-wrap">
                                                                                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                                                                                    <h3>Category infomation</h3>
                                                                                                    <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                                                                                        <li>
                                                                                                            <a href="index.html"><div class="text-tiny">Dashboard</div></a>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <i class="icon-chevron-right"></i>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <a href="#"><div class="text-tiny">Category</div></a>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <i class="icon-chevron-right"></i>
                                                                                                        </li>
                                                                                                        <li>
                                                                                                            <div class="text-tiny">New category</div>
                                                                                                        </li>
                                                                                                    </ul>
                                                                                                </div>
                                                                                                <!-- new-category -->
                                                                                                <div class="wg-box">
                                                                                                    <form class="form-new-category form-style-1" method="POST" action="${pageContext.request.contextPath}/manageCategory?action=add">

                                                                                                    <fieldset class="name">
                                                                                                        <div class="body-title"> Name: <span class="tf-color-1">*</span></div>
                                                                                                        <input class="flex-grow" type="text" placeholder="Category name" name="name" tabindex="0" value="${name != null ? name : ''}" aria-required="true" >
                                                                                                            <div class="invalid-feedback">Vui lòng nhập tên category (2-50 ký tự)</div>
                                                                                                    </fieldset>
                                                                                                    <fieldset>
                                                                                                        <div class="description body-title">Description: <span class="tf-color-1">*</span></div>
                                                                                                        <input class="flex-grow" type="text" placeholder="Description" name="description" tabindex="0" value="${description != null ? description : ''}" >
                                                                                                            <div class="invalid-feedback">Vui lòng nhập tên category (2-50 ký tự)</div>
                                                                                                    </fieldset>
                                                                                                    <fieldset class="minBMI">
                                                                                                        <div class="body-title">MinBMI: <span class="tf-color-1">*</span></div>
                                                                                                        <input class="flex-grow" type="text" placeholder="minBMI" name="minBMI" tabindex="0" value="${minBMI != null ? minBMI : ''}" >
                                                                                                            <div class="invalid-feedback">Vui lòng nhập tên category (2-50 ký tự)</div>
                                                                                                    </fieldset>
                                                                                                    <fieldset class="maxBMI">
                                                                                                        <div class="body-title">MaxBMI :<span class="tf-color-1">*</span></div>
                                                                                                        <input class="flex-grow" type="text" placeholder="maxBMI" name="maxBMI" tabindex="0" value="${maxBMI != null ? maxBMI : ''}" >
                                                                                                            <div class="invalid-feedback">Vui lòng nhập tên category (2-50 ký tự)</div>
                                                                                                    </fieldset>
                                                                                                    <div class="bot">
                                                                                                        <div></div>
                                                                                                        <button class="tf-button w208" type="submit">Save</button>
                                                                                                    </div>
                                                                                                </form>
                                                                                            </div>
                                                                                            <!-- /new-category -->
                                                                                        </div>
                                                                                        <!-- /main-content-wrap -->
                                                                                    </div>
                                                                                    <!-- /main-content-wrap -->
                                                                                    <!-- bottom-page -->
                                                                                    <jsp:include page="../../common/footer.jsp"></jsp:include>

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
                                                                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                                                                <c:if test="${not empty errors}">
                                                                    <c:forEach var="entry" items="${errors}">
                                                                        <script>
                                                                            iziToast.error({
                                                                                title: 'Lỗi',
                                                                                message: "${fn:escapeXml(entry.value)}",
                                                                                position: 'topRight',
                                                                                timeout: 5000
                                                                            });
                                                                        </script>
                                                                    </c:forEach>
                                                                </c:if>
                                                                <!--Validate ơ dao diện nang chạn việc save-->
                                                                <!-- Thêm CSS ngay trong file -->
                                                                <style>
                                                                    .invalid-feedback {
                                                                        display: none;
                                                                        width: 100%;
                                                                        margin-top: 0.25rem;
                                                                        font-size: 0.875em;
                                                                        color: #dc3545;
                                                                    }
                                                                    .is-invalid {
                                                                        border-color: #dc3545;
                                                                    }
                                                                    .is-invalid ~ .invalid-feedback {
                                                                        display: block;
                                                                    }
                                                                </style>
                                                                <script>
                                                                    document.addEventListener('DOMContentLoaded', function () {
                                                                        const form = document.querySelector('.form-new-category');

                                                                        // Hiển thị lỗi
                                                                        function showError(input, message) {
                                                                            input.classList.add('is-invalid');
                                                                            const feedback = input.nextElementSibling;
                                                                            if (feedback && feedback.classList.contains('invalid-feedback')) {
                                                                                feedback.textContent = message;
                                                                            }
                                                                        }

                                                                        // Ẩn lỗi
                                                                        function clearError(input) {
                                                                            input.classList.remove('is-invalid');
                                                                        }

                                                                        // Hàm kiểm tra số trong khoảng và bắt buộc
                                                                        function validateNumberInput(input, fieldName, minValue = 10, maxValue = 50) {
                                                                            const value = input.value.trim();

                                                                            if (value === "") {
                                                                                showError(input, `${fieldName} Vui lòng nhập`);
                                                                                return false;
                                                                            }

                                                                            if (isNaN(value)) {
                                                                                showError(input, `${fieldName} Vui lòng nhập`);
                                                                                return false;
                                                                            }

                                                                            const number = parseFloat(value);

                                                                            if (number < 0) {
                                                                                showError(input, `${fieldName} phải ≥ 0`);
                                                                                return false;
                                                                            }

                                                                            if (number < minValue || number > maxValue) {
                                                                                showError(input, `${fieldName} phải nằm trong khoảng 10-50`);
                                                                                                return false;
                                                                                            }

                                                                                            clearError(input);
                                                                                            return true;
                                                                                        }

                                                                                        // Khi submit form
                                                                                        form.addEventListener('submit', function (e) {
                                                                                            e.preventDefault();
                                                                                            let isValid = true;

                                                                                            // Validate name_category
                                                                                            const nameInput = form.querySelector('[name="name"]');
                                                                                            if (nameInput.value.trim().length < 3) {
                                                                                                showError(nameInput, 'Tên category phải có ít nhất 3 ký tự');
                                                                                                isValid = false;
                                                                                            } else {
                                                                                                clearError(nameInput);
                                                                                            }

                                                                                            // Validate description
                                                                                            const descInput = form.querySelector('[name="description"]');
                                                                                            if (descInput.value.trim().length < 5) {
                                                                                                showError(descInput, 'Mô tả phải có ít nhất 5 ký tự');
                                                                                                isValid = false;
                                                                                            } else {
                                                                                                clearError(descInput);
                                                                                            }

                                                                                            // Validate minBMI và maxBMI
                                                                                            const minBMIInput = form.querySelector('[name="minBMI"]');
                                                                                            const maxBMIInput = form.querySelector('[name="maxBMI"]');

                                                                                            const isMinValid = validateNumberInput(minBMIInput, 'MinBMI');
                                                                                            const isMaxValid = validateNumberInput(maxBMIInput, 'MaxBMI');

                                                                                            if (!isMinValid || !isMaxValid) {
                                                                                                isValid = false;
                                                                                            } else {
                                                                                                const minVal = parseFloat(minBMIInput.value.trim());
                                                                                                const maxVal = parseFloat(maxBMIInput.value.trim());

                                                                                                if (minVal >= maxVal) {
                                                                                                    showError(minBMIInput, 'MinBMI phải nhỏ hơn MaxBMI');
                                                                                                    showError(maxBMIInput, 'MaxBMI phải lớn hơn MinBMI');
                                                                                                    isValid = false;
                                                                                                }
                                                                                            }

                                                                                            // Nếu hợp lệ, submit form
                                                                                            if (isValid) {
                                                                                                form.submit();
                                                                                            } else {
                                                                                                const firstError = form.querySelector('.is-invalid');
                                                                                                if (firstError) {
                                                                                                    firstError.scrollIntoView({behavior: 'smooth', block: 'center'});
                                                                                                    firstError.focus();
                                                                                                }
                                                                                            }
                                                                                        });

                                                                                        // Real-time validation + blur
                                                                                        form.querySelectorAll('input').forEach(input => {
                                                                                            input.addEventListener('input', function () {
                                                                                                clearError(this);

                                                                                                if (this.name === 'minBMI' || this.name === 'maxBMI') {
                                                                                                    const fieldName = this.name === 'minBMI' ? 'MinBMI' : 'MaxBMI';
                                                                                                    validateNumberInput(this, fieldName);
                                                                                                }
                                                                                            });

                                                                                            input.addEventListener('blur', function () {
                                                                                                if (this.name === 'minBMI' || this.name === 'maxBMI') {
                                                                                                    const fieldName = this.name === 'minBMI' ? 'MinBMI' : 'MaxBMI';
                                                                                                    validateNumberInput(this, fieldName);
                                                                                                }
                                                                                            });
                                                                                        });
                                                                                    });
                                                                </script>


                                                            </body>


                                                            <!-- Mirrored from themesflat.co/html/remos/new-category.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:52 GMT -->
                                                            </html>