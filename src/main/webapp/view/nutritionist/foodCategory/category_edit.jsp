
<%-- 
    Document   : add_account
    Created on : May 29, 2025, 8:10:49 AM
    Author     : Hang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<script src="https://cdn.jsdelivr.net/npm/izitoast/dist/js/iziToast.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/izitoast/dist/css/iziToast.min.css">

    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
        <!--<![endif]-->
        <style>
            /* Container fieldset */
            fieldset.name {
                border: none;
                padding: 0;
                margin-bottom: 24px;
            }

            /* Label title */
            .body-title {
                font-weight: 600;
                font-size: 15px;
                margin-bottom: 6px;
            }

            /* Select box */
            select.form-control {
                width: 100%;
                padding: 10px 14px;
                border: 1px solid #ccc;
                border-radius: 6px;
                background-color: #fff;
                font-size: 14px;
                font-family: inherit;
                outline: none;
                transition: border-color 0.2s ease;
            }

            select.form-control:focus {
                border-color: #1e80ff;
                box-shadow: 0 0 0 2px rgba(30, 128, 255, 0.2);
                background-color: #fefefe;
            }
            .readonly-input[readonly] {
                background-color: #e9ecef;     /* Bootstrap-like xám */
                color: #495057;
                border: none;
                border-radius: 10px;
                padding: 12px 16px;
                font-size: 16px;
                width: 100%;
            }
        </style>

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
                                                                                    <div class="main-content">
                                                                                        <!-- main-content-wrap -->
                                                                                        <div class="main-content-inner">
                                                                                            <!-- main-content-wrap -->
                                                                                            <div class="main-content-wrap">
                                                                                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                                                                                    <h3>Edit User</h3>
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
                                                                                                            <div class="text-tiny">Edit Category</div>
                                                                                                        </li>
                                                                                                    </ul>
                                                                                                </div>
                                                                                                <!-- add-new-user -->
                                                                                                <form class="edit_category" action="${pageContext.request.contextPath}/manageCategory?action=edit" method="Post">
                                                                                                <div class="wg-box">
                                                                                                    <div class="left">
                                                                                                        <h5 class="mb-4">Category</h5>
                                                                                                        <div class="body-text">Fill in the information below to edit category</div>
                                                                                                    </div>

                                                                                                    <div class="right flex-grow">
                                                                                                        <fieldset class="name mb-24">
                                                                                                            <div class="body-title mb-10">Id</div>
                                                                                                            <input class="readonly-input" type="text" name="id" value="${param.id}" readonly>
                                                                                                        </fieldset>

                                                                                                        <!-- Name -->
                                                                                                        <fieldset class="name mb-24">
                                                                                                            <div class="body-title mb-10">Name</div>
                                                                                                            <input type="text" class="form-control" name="name" 
                                                                                                                   value="${empty formData.name ? cate.name : formData.name}"  />
                                                                                                            <c:if test="${not empty errors.name}">
                                                                                                                <div class="error" style="color:red;">${errors.name}</div>
                                                                                                            </c:if>
                                                                                                            <div class="invalid-feedback">Vui lòng nhập tên category (2-50 ký tự)</div>
                                                                                                        </fieldset>

                                                                                                        <!-- MinBMI -->
                                                                                                        <fieldset class="name mb-24">
                                                                                                            <div class="body-title mb-10">MinBMI</div>
                                                                                                            <input type="text" class="form-control" name="minBMI" 
                                                                                                                   value="${empty formData.minBMI ? cate.minBMI : formData.minBMI}"  />
                                                                                                            <c:if test="${not empty errors.minBMI}">
                                                                                                                <div class="error" style="color:red;">${errors.minBMI}</div>
                                                                                                            </c:if>
                                                                                                            <div class="invalid-feedback">Vui lòng nhập Số MinBMI</div>
                                                                                                        </fieldset>

                                                                                                        <!-- MaxBMI -->
                                                                                                        <fieldset class="name mb-24">
                                                                                                            <div class="body-title mb-10">MaxBMI</div>
                                                                                                            <input type="text" class="form-control" name="maxBMI" 
                                                                                                                   value="${empty formData.maxBMI ? cate.maxBMI : formData.maxBMI}"  />
                                                                                                            <c:if test="${not empty errors.maxBMI}">
                                                                                                                <div class="error" style="color:red;">${errors.maxBMI}</div>
                                                                                                            </c:if>
                                                                                                            <div class="invalid-feedback">Vui lòng nhập Số MaxBMI</div>
                                                                                                        </fieldset>
                                                                                                        <!-- BMI logic error -->
                                                                                                        <fieldset class="name mb-24">
                                                                                                            <c:if test="${not empty errors.BMI}">
                                                                                                                <div class="error" style="color:red;">${errors.BMI}</div>
                                                                                                            </c:if>
                                                                                                            <div class="invalid-feedback">Vui lòng nhập Số</div>
                                                                                                        </fieldset>
                                                                                                        <!-- Description -->
                                                                                                        <fieldset class="name mb-24">
                                                                                                            <div class="body-title mb-10">Description</div>
                                                                                                            <input type="text" class="form-control" name="description" 
                                                                                                                   value="${empty formData.description ? cate.description : formData.description}"  />
                                                                                                            <c:if test="${not empty errors.description}">
                                                                                                                <div class="error" style="color:red;">${errors.description}</div>
                                                                                                            </c:if>
                                                                                                            <div class="invalid-feedback">Vui lòng nhập tên description</div>
                                                                                                        </fieldset>
                                                                                                    </div>

                                                                                                </div>


                                                                                                <div class="bot">
                                                                                                    <button class="tf-button w180" type="submit">Save</button>
                                                                                                </div>

                                                                                        </div>

                                                                                        </form>
                                                                                        <!-- /add-new-user -->
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

                                                                    <script src="${pageContext.request.contextPath}/js/jquery.min_1.js"></script>
                                                                <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
                                                                <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
                                                                <script src="${pageContext.request.contextPath}/js/zoom.js"></script>
                                                                <script src="${pageContext.request.contextPath}/js/switcher.js"></script>
                                                                <script src="${pageContext.request.contextPath}/js/theme-settings.js"></script>
                                                                <script src="${pageContext.request.contextPath}/js/main.js"></script>
                                                                <!--Start hiem thi izitoat-->
                                                                <c:if test="${not empty errors.name}">
                                                                    <script>
                                                                        iziToast.error({
                                                                            title: 'Lỗi',
                                                                            message: '${errors.name}',
                                                                            position: 'topRight',
                                                                            timeout: 5000
                                                                        });
                                                                    </script>
                                                                </c:if>
                                                                <c:if test="${not empty errors.minBMI}">
                                                                    <script>
                                                                        iziToast.error({
                                                                            title: 'Lỗi',
                                                                            message: '${fn:escapeXml(errors.minBMI)}',
                                                                            position: 'topRight',
                                                                            timeout: 5000
                                                                        });
                                                                    </script>
                                                                </c:if>
                                                                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                                                                <c:if test="${not empty errors.maxBMI}">
                                                                    <script>
                                                                        iziToast.error({
                                                                            title: 'Lỗi',
                                                                            message: '${fn:escapeXml(errors.maxBMI)}',
                                                                            position: 'topRight',
                                                                            timeout: 5000
                                                                        });
                                                                    </script>
                                                                </c:if>

                                                                <c:if test="${not empty errors.description}">
                                                                    <script>
                                                                        iziToast.error({
                                                                            title: 'Lỗi',
                                                                            message: '${fn:escapeXml(errors.description)}',
                                                                            position: 'topRight',
                                                                            timeout: 5000
                                                                        });
                                                                    </script>
                                                                </c:if>

                                                                <c:if test="${not empty errors.BMI}">
                                                                    <script>
                                                                        iziToast.error({
                                                                            title: 'Lỗi',
                                                                            message: '${fn:escapeXml(errors.BMI)}',
                                                                            position: 'topRight',
                                                                            timeout: 5000
                                                                        });
                                                                    </script>
                                                                </c:if>
                                                                <!--End zitoat-->
                                                                <!--validate ơ bên dao diện-->
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
                                                                        const form = document.querySelector('.edit_category');

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
                                                                        function validateNumberInput(input, fieldName, minValue = 0, maxValue = 100) {
                                                                            const value = input.value.trim();

                                                                            if (value === "") {
                                                                                showError(input, ` vui long nhap `);
                                                                                return false;
                                                                            }

                                                                            if (isNaN(value)) {
                                                                                showError(input, ` Vui long nhap so`);
                                                                                return false;
                                                                            }

                                                                            const number = parseFloat(value);

                                                                            if (number < 0) {
                                                                                showError(input, `Số phải ≥ 0`);
                                                                                return false;
                                                                            }

                                                                            if (number < minValue || number > maxValue) {
                                                                                showError(input, ` Số phải nằm trong khoảng 0-100`);
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
                                                                <!--End validate ơ bên dao diện-->
                                                            </body>
                                                            </html>
