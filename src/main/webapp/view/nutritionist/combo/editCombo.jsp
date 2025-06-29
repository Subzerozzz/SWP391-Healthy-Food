<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->


    <!-- Mirrored from themesflat.co/html/remos/add-new-user.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:55 GMT -->
    <head>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />
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
                                                                                        <div class="content-wrapper">
                                                                                            <section class="content">
                                                                                                <div class="dashboard-main-body">
                                                                                                    <div class="d-flex flex-wrap align-items-center justify-content-between gap-3 mb-24">
                                                                                                        <h6 class="fw-semibold mb-0">Update Combo</h6>
                                                                                                    </div>
                                                                                                    <div class="card">
                                                                                                        <div class="card-body p-24">
                                                                                                            <form id="comboForm" action="${pageContext.request.contextPath}/managerCombo?action=edit" method="post" enctype="multipart/form-data">
                                                                                                            <input type="hidden" name="comboId" value="${combo.comboId}">
                                                                                                                <input type="hidden" name="page" value="${param.page}">

                                                                                                                    <c:if test="${not empty sessionScope.errors}">
                                                                                                                        <div class="alert alert-danger">
                                                                                                                            <ul class="mb-0">
                                                                                                                                <c:forEach items="${sessionScope.errors}" var="error">
                                                                                                                                    <li>${error.value}</li>
                                                                                                                                    </c:forEach>
                                                                                                                            </ul>
                                                                                                                        </div>
                                                                                                                        <% session.removeAttribute("errors");%>
                                                                                                                    </c:if>

                                                                                                                    <c:if test="${not empty sessionScope.errors['duplicate_food']}">
                                                                                                                        <div class="alert alert-danger">
                                                                                                                            <ul class="mb-0">
                                                                                                                                <li>${sessionScope.errors['duplicate_food']}</li>
                                                                                                                            </ul>
                                                                                                                        </div>
                                                                                                                    </c:if>

                                                                                                                    <div class="row g-3">
                                                                                                                        <div class="col-md-6">
                                                                                                                            <label for="name" class="form-label">Combo Name <span class="text-danger">*</span></label>
                                                                                                                            <input type="text" class="form-control" id="name" name="name"
                                                                                                                                   placeholder="Enter combo name" required
                                                                                                                                   value="${combo.comboName}">
                                                                                                                        </div>
                                                                                                                        <div class="col-md-6">
                                                                                                                            <label for="status" class="form-label">Status <span class="text-danger">*</span></label>
                                                                                                                            <select class="form-select" id="status" name="status" required>
                                                                                                                                <option value="active" ${combo.status == 'active' ? 'selected' : ''}>Active</option>
                                                                                                                                <option value="inactive" ${combo.status == 'inactive' ? 'selected' : ''}>Inactive</option>
                                                                                                                            </select>
                                                                                                                        </div>
                                                                                                                        <div class="col-md-12">
                                                                                                                            <label for="description" class="form-label">Combo Description</label>
                                                                                                                            <textarea class="form-control" id="description" name="description"
                                                                                                                                      rows="3" placeholder="Enter detailed description of the combo">${combo.description}</textarea>
                                                                                                                        </div>

                                                                                                                    </div>

                                                                                                                    <!-- Food Selection Section -->
                                                                                                                    <div class="card border-info mt-4 mb-4">
                                                                                                                        <div class="card-header bg-info text-white">
                                                                                                                            <h6 class="mb-0"><i class="fas fa-boxes me-2"></i>Choose Foods for Combo</h6>
                                                                                                                        </div>
                                                                                                                        <div class="card-body">
                                                                                                                            <div class="food-selection-container">
                                                                                                                                <c:forEach items="${comboFood}" var="comboFood" varStatus="status">
                                                                                                                                    <div class="food-selection-row row mb-3">
                                                                                                                                        <div class="col-md-8">
                                                                                                                                            <select class="form-control food-select" required>
                                                                                                                                                <option value="">Choose a food</option>
                                                                                                                                                <c:forEach items="${allFoods}" var="food">
                                                                                                                                                    <option value="${food.id}" 
                                                                                                                                                            data-price="${food.price}"
                                                                                                                                                            ${food.id == comboFood.foodId ? 'selected' : ''}>
                                                                                                                                                        ${food.name} - ${food.price}đ
                                                                                                                                                    </option>
                                                                                                                                                </c:forEach>
                                                                                                                                            </select>
                                                                                                                                        </div>
                                                                                                                                        <div class="col-md-2">
                                                                                                                                            <input type="number" class="form-control food-quantity" 
                                                                                                                                                   value="${comboFood.quantityInCombo}" min="1" required>
                                                                                                                                        </div>
                                                                                                                                        <div class="col-md-2">
                                                                                                                                            <button type="button" class="btn btn-danger remove-food" 
                                                                                                                                                    ${status.index == 0 && comboFoods.size() == 1 ? 'disabled' : ''}>
                                                                                                                                                <i class="fas fa-trash"></i>
                                                                                                                                            </button>
                                                                                                                                        </div>
                                                                                                                                    </div>
                                                                                                                                </c:forEach>

                                                                                                                                <c:if test="${empty comboFoods}">
                                                                                                                                    <div class="food-selection-row row mb-3">
                                                                                                                                        <div class="col-md-8">
                                                                                                                                            <select class="form-control food-select" required>
                                                                                                                                                <option value="">Choose a food</option>
                                                                                                                                                <c:forEach items="${allFoods}" var="food">
                                                                                                                                                    <option value="${food.id}" data-price="${food.price}">
                                                                                                                                                        ${food.name} - <fmt:formatNumber value="${food.price}" type="currency" currencySymbol="" pattern="#,##0"/>đ
                                                                                                                                                    </option>
                                                                                                                                                </c:forEach>
                                                                                                                                            </select>
                                                                                                                                        </div>
                                                                                                                                        <div class="col-md-2">
                                                                                                                                            <input type="number" class="form-control food-quantity" value="1" min="1" required>
                                                                                                                                        </div>
                                                                                                                                        <div class="col-md-1 d-flex align-items-end">
                                                                                                                                            <button type="button" class="btn btn-danger remove-food" disabled title="Remove food">
                                                                                                                                                <i class="fas fa-trash"></i>
                                                                                                                                            </button>
                                                                                                                                        </div>
                                                                                                                                    </div>
                                                                                                                                </c:if>
                                                                                                                            </div>

                                                                                                                            <div class="row mt-3">
                                                                                                                                <div class="col-12">
                                                                                                                                    <button type="button" class="btn btn-success add-food">
                                                                                                                                        <i class="fas fa-plus"></i> Add Food
                                                                                                                                    </button>
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                        </div>
                                                                                                                    </div>

                                                                                                                    <!-- Hidden inputs for form submission -->
                                                                                                                    <input type="hidden" id="foodIdsInput" name="foodIds">
                                                                                                                        <input type="hidden" id="quantitiesInput" name="quantities">

                                                                                                                            <!-- Price Summary -->
                                                                                                                            <div class="row mt-4">
                                                                                                                                <div class="col-md-6 offset-md-6">
                                                                                                                                    <div class="table-responsive">
                                                                                                                                        <table class="table table-bordered">
                                                                                                                                            <tr>
                                                                                                                                                <th>Total Original Price:</th>
                                                                                                                                                <td>
                                                                                                                                                    <span id="original-price-display">${combo.originalPrice}</span>đ
                                                                                                                                                    <input type="hidden" id="original_price" name="originalPrice" 
                                                                                                                                                           value="${combo.originalPrice}">
                                                                                                                                                </td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                                <th>Discounted Price: <span class="text-danger">*</span></th>
                                                                                                                                                <td>
                                                                                                                                                    <input type="number" class="form-control" id="discount_price" 
                                                                                                                                                           name="discountPrice" value="${combo.discountPrice}" 
                                                                                                                                                           min="0" required>
                                                                                                                                                </td>
                                                                                                                                            </tr>
                                                                                                                                            <tr>
                                                                                                                                                <th>Savings:</th>
                                                                                                                                                <td><span id="savings-display">${combo.originalPrice - combo.discountPrice}</span>đ</td>
                                                                                                                                            </tr>
                                                                                                                                        </table>
                                                                                                                                    </div>
                                                                                                                                </div>
                                                                                                                            </div>

                                                                                                                            <!-- Submit Buttons -->
                                                                                                                            <div class="row mt-4">
                                                                                                                                <div class="col-12 text-end">
                                                                                                                                    <a href="${pageContext.request.contextPath}/admin/manage-combo" 
                                                                                                                                       class="btn btn-secondary me-2">
                                                                                                                                        <i class="fas fa-times"></i> Cancel
                                                                                                                                    </a>
                                                                                                                                    <button type="submit" class="btn btn-primary">
                                                                                                                                        <i class="fas fa-save"></i> Save Changes
                                                                                                                                    </button>
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                            </form>

                                                                                                                            <!-- Combo Food Manager -->
                                                                                                                            <script src="${pageContext.request.contextPath}/js/comboFoodManager.js"></script>
                                                                                                                            <script>
                                                                                                                                document.addEventListener('DOMContentLoaded', function () {
                                                                                                                                    // Validate form name
                                                                                                                                    var comboForm = document.getElementById('comboForm');
                                                                                                                                    if (comboForm) {
                                                                                                                                        comboForm.addEventListener('submit', function (e) {
                                                                                                                                            var nameInput = document.getElementById('name');
                                                                                                                                            var nameValue = nameInput.value;
                                                                                                                                            var regex = /^[a-zA-Z0-9\sÀ-ỹà-ỹ_.,-]+$/;
                                                                                                                                            if (!regex.test(nameValue)) {
                                                                                                                                                alert('Combo name cannot contain special characters!');
                                                                                                                                                nameInput.focus();
                                                                                                                                                e.preventDefault();
                                                                                                                                                return false;
                                                                                                                                            }

                                                                                                                                            //ĐỔ DỮ LIỆU vào input hidden trước khi submit
                                                                                                                                            const foodSelects = document.querySelectorAll(".food-select");
                                                                                                                                            const quantityInputs = document.querySelectorAll(".food-quantity");

                                                                                                                                            const foodIds = [];
                                                                                                                                            const quantities = [];

                                                                                                                                            foodSelects.forEach((select, index) => {
                                                                                                                                                const foodId = select.value;
                                                                                                                                                const quantity = quantityInputs[index].value;

                                                                                                                                                if (foodId && quantity) {
                                                                                                                                                    foodIds.push(foodId);
                                                                                                                                                    quantities.push(quantity);
                                                                                                                                                }
                                                                                                                                            });

                                                                                                                                            document.getElementById("foodIdsInput").value = foodIds.join(",");
                                                                                                                                            document.getElementById("quantitiesInput").value = quantities.join(",");
                                                                                                                                        });
                                                                                                                                    }

                                                                                                                                    // Handle add food
                                                                                                                                    const addFoodBtn = document.querySelector('.add-food');
                                                                                                                                    console.log("Add food Button:", addFoodBtn);

                                                                                                                                    if (addFoodBtn) {
                                                                                                                                        addFoodBtn.addEventListener('click', function () {
                                                                                                                                            console.log('Add food clicked');
                                                                                                                                            // Bạn cần bổ sung code clone dòng chọn món tại đây
                                                                                                                                        });
                                                                                                                                    } else {
                                                                                                                                        console.warn("Add food button not found!");
                                                                                                                                    }
                                                                                                                                });

                                                                                                                            </script>
                                                                                                                            </div>
                                                                                                                            </div>
                                                                                                                            </div>
                                                                                                                            </section>
                                                                                                                            </div>
                                                                                                                            <!-- /main-content-wrap -->
                                                                                                                            </div>
                                                                                                                            <!-- /main-content-wrap -->
                                                                                                                            <!-- bottom-page -->

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
                                                                                                                            <script src="${pageContext.request.contextPath}/js/comboFoodManager.js"></script>
                                                                                                                            <!-- jQuery -->
                                                                                                                            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

                                                                                                                            <!-- Gọi sau khi jQuery + Select2 sẵn -->

                                                                                                                            </body>
                                                                                                                            <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
                                                                                                                            <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
                                                                                                                            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

                                                                                                                            <!-- Mirrored from themesflat.co/html/remos/add-new-user.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:55 GMT -->
                                                                                                                            </html>