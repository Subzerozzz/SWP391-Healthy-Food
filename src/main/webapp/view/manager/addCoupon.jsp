<%-- 
    Document   : addCoupon
    Created on : Jun 14, 2025, 4:34:20 PM
    Author     : Predator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<!--<![endif]-->


<!-- Mirrored from themesflat.co/html/remos/add-product.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:33 GMT -->
<head>
    <!-- Basic Page Needs -->
    <meta charset="utf-8">
    <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
    

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/icon/style.css">

    <!-- Favicon and Touch Icons  -->
    <link rel="shortcut icon" href="images/favicon.png">
    <link rel="apple-touch-icon-precomposied" href="images/favicon.png">
</head>
                            <style>
                                footer-wrap {
                                    background: #fff;
                                    padding: 16px;
                                    text-align: center;
                                    box-shadow: 0px 4px 24px 2px rgba(20, 25, 38, 0.05);
                                    font-family: Arial, sans-serif;
                                    font-size: 14px;
                                    color: #333;
                                }

                                .footer-wrap .bottom-page {
                                    display: inline-flex;
                                    align-items: center;
                                    gap: 6px;
                                    flex-wrap: wrap;
                                    justify-content: center;
                                    padding-left: 700px;
                                }

                                .footer-wrap .bottom-page .body-text {
                                    margin: 0;
                                }

                                .footer-wrap .bottom-page i.icon-heart {
                                    color: #ff6a00;
                                    font-style: normal; /* hoặc dùng font-awesome nếu có */
                                }

                                .footer-wrap .bottom-page a {
                                    color: #007bff;
                                    text-decoration: none;
                                }

                                .footer-wrap .bottom-page a:hover {
                                    text-decoration: underline;
                                }
                                .text-tiny {
                                    display: none;
                                    width: 100%;
                                    margin-top: 0.25rem;
                                    font-size: 0.875em;
                                    color: #dc3545; /* Red for error */
                                }

                                .is-invalid {
                                    border-color: #dc3545;
                                }

                                .is-invalid ~ .text-tiny {
                                    display: block;
                                }
                                .switch {
                                    position: relative;
                                    display: inline-block;
                                    width: 44px;
                                    height: 24px;
                                }

                                .switch input {
                                    opacity: 0;
                                    width: 0;
                                    height: 0;
                                }

                                .slider.round {
                                    position: absolute;
                                    cursor: pointer;
                                    top: 0;
                                    left: 0;
                                    right: 0;
                                    bottom: 0;
                                    background-color: #ccc;
                                    transition: 0.4s;
                                    border-radius: 24px;
                                }

                                .slider.round:before {
                                    position: absolute;
                                    content: "";
                                    height: 18px;
                                    width: 18px;
                                    left: 3px;
                                    bottom: 3px;
                                    background-color: white;
                                    transition: 0.4s;
                                    border-radius: 50%;
                                }

                                .switch input:checked + .slider.round {
                                    background-color: #28a745;
                                }

                                .switch input:checked + .slider.round:before {
                                    transform: translateX(20px);
                                }
                               
                    </style>
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
                <jsp:include page="../common/sidebar.jsp"></jsp:include>
                <!-- /section-menu-left -->
                <!-- section-content-right -->
                <div class="section-content-right">
                    <!-- header-dashboard -->
                    <jsp:include page="../common/headerDashboard.jsp"></jsp:include> 
                    <!-- /header-dashboard -->
                    <!-- main-content -->
                    <div class="main-content">
                        <!-- main-content-wrap -->
                        <div class="main-content-inner">
                            <!-- main-content-wrap -->
                            <div class="main-content-wrap">
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                    <h3>Add Coupon</h3>
                                </div>
                                <!-- form-add-coupon -->
                                <form  
                                    action="ManagerCoupon?action=add"
                                     method="POST">
                                    <div class="wg-box">
                                        <!-- Coupon Code -->
                                        <fieldset class="code">
                                            <div class="body-title mb-10">Coupon Code <span class="tf-color-1">*</span></div>
                                            <input class="mb-10" type="text" placeholder="Enter coupon code" name="code" tabindex="0" value="${code != null ? code : ''}" aria-required="true" maxlength="50"value="">
                                            <div class="text-tiny">Enter a unique coupon code (max 50 characters).</div>
                                        </fieldset>

                                        <!-- Description -->
                                        <fieldset class="description">
                                            <div class="body-title mb-10">Description <span class="tf-color-1">*</span></div>
                                            <textarea class="mb-10" placeholder="Enter coupon description" name="description" tabindex="0" aria-required="true" maxlength="500" rows="3"value="${description != null ? description : ''}"></textarea>
                                            <div class="text-tiny">Describe the coupon offer (max 500 characters).</div>
                                        </fieldset>
                                        <!-- Discount Type -->
                                        <fieldset class="discountType">
                                            <div class="body-title mb-10">Discount Type <span class="tf-color-1">*</span></div>
                                            <div class="select">
                                                <select name="discountype">
                                                    <option value="">Select discount type</option>
                                                    <option value="percentage" ${"percentage".equals(discountype) ? "selected" : ""}>Percentage (%)</option>
                                                    <option value="fixed" ${"fixed".equals(discountype) ? "selected" : ""}>Fixed Amount</option>
                                                </select>
                                                    <div class="text-tiny">Please choose discount type.</div>
                                            </div>
                                        </fieldset>
                                        <!-- Discount Value -->
                                        <fieldset class="discountValue">
                                            <div class="body-title mb-10">Discount Value <span class="tf-color-1">*</span></div>
                                            <input class="mb-10" type="number" step="0.01" min="0" placeholder="Enter discount value" name="discountvalue" tabindex="0" value="${discountvalue != null ? discountvalue : ''}" aria-required="true" >
                                            <div class="text-tiny">Enter the discount amount or percentage value.</div>
                                        </fieldset>

                                        <!-- Min Purchase -->
                                        <fieldset class="minPurchase">
                                            <div class="body-title mb-10">Minimum Purchase Amount</div>
                                            <input class="mb-10" type="number" step="0.01" min="0" placeholder="Enter minimum purchase amount" name="minpurchase" tabindex="0" value="${minpurchase != null ? minpurchase : ''}">
                                            <div class="text-tiny">Minimum order amount required to use this coupon (optional).</div>
                                        </fieldset>
                                        <!-- Date Range -->
                                         <!-- Max Discount -->
                                         <fieldset class="maxDiscount" style="display: none;">
                                             <div class="body-title mb-10">Maximum Discount Amount</div>
                                             <input class="mb-10" type="number" step="0.01" min="0"
                                                    placeholder="Enter maximum discount amount"
                                                    name="maxdiscount" tabindex="0"
                                                    value="${maxdiscount != null ? maxdiscount : ''}">
                                                 <div class="text-tiny">Maximum discount amount for percentage coupons (optional).</div>
                                         </fieldset>
                                                    <!-- Usage Limit -->
                                                    <fieldset class="usageLimit">
                                                        <div class="body-title mb-10">Usage Limit</div>
                                                        <input class="mb-10" type="number" min="0" step="1"
                                                               placeholder="Enter usage limit"
                                                               name="usageLimit"
                                                               value="${usageLimit != null ? usageLimit : ''}">
                                                            <div class="text-tiny">Maximum number of times this coupon can be used. Leave empty for unlimited.</div>
                                                    </fieldset>
                                                               <!-- Per Customer Limit -->
                                                               <fieldset class="perCustomerLimit">
                                                                   <div class="body-title mb-10">Per Customer Limit</div>
                                                                   <input class="mb-10" type="number" min="0" step="1"
                                                                          placeholder="Enter per customer limit"
                                                                          name="percuslimit"
                                                                          value="${percuslimit != null ? percuslimit : ''}">
                                                                       <div class="text-tiny">Maximum number of times a customer can use this coupon. Leave empty for unlimited.</div>
                                                               </fieldset>       
                                                    <!-- Status -->
                                                    <fieldset class="status">
                                                        <div class="body-title mb-10">Status</div>
                                                        <div style="display: flex; align-items: center; gap: 10px;">
                                                            <!-- Hidden input đảm bảo gửi isActive=0 nếu checkbox không được tick -->
                                                            <input type="hidden" name="isActive" value="0" />

                                                            <label class="switch">
                                                                <input type="checkbox" name="isActive" value="1"
                                                                       <c:if test="${empty coupon || coupon.isactive == 1}">checked</c:if> />
                                                                <span class="slider round"></span>
                                                            </label>
                                                            <span class="label">Active</span>
                                                        </div>
                                                    </fieldset>
                                          <div class="cols gap10">
                                            <fieldset class="startDate">
                                                <div class="body-title mb-10">Start Date <span class="tf-color-1">*</span></div>
                                                <input class="mb-10" type="date" name="date1" tabindex="0" aria-required="true" value="${date1!= null ? date1 : ''}">
                                                <div class="text-tiny">When the coupon becomes active.</div>
                                            </fieldset>

                                            <fieldset class="endDate">
                                                <div class="body-title mb-10">End Date <span class="tf-color-1">*</span></div>
                                                <input class="mb-10" type="date" name="date2" tabindex="0" aria-required="true" value="${date2!= null ? date2 : ''}">
                                                <div class="text-tiny">When the coupon expires.</div>
                                            </fieldset>
                                        </div>
                                         <div class="cols gap10">
                                             <button class="tf-button w-full" type="submit">Add Coupon</button>
                                         </div>
                                     </div>
                                 </form>
                                 <!-- /form-add-coupon -->
                             </div>
                             <!-- /main-content-wrap -->
                         </div>
                         <!-- /main-content-wrap -->
                     </div>
                     <!-- bottom-page -->
                     <div class="footer-wrap">
                            <jsp:include page="../common/footer.jsp"></jsp:include>
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
   <script>
document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const discountTypeSelect = form.querySelector('[name="discountype"]');
    const maxDiscountField = form.querySelector('fieldset.maxDiscount');
    const discountValueInput = form.querySelector('[name="discountvalue"]');

    function showError(input, message) {
        input.classList.add('is-invalid');
        let feedback = input.closest('fieldset')?.querySelector('.text-tiny');
        if (feedback) {
            feedback.textContent = message;
            feedback.style.color = '#dc3545';
        }
    }

    function clearError(input) {
        input.classList.remove('is-invalid');
        let feedback = input.closest('fieldset')?.querySelector('.text-tiny');
        if (feedback) {
            feedback.textContent = '';
            feedback.style.color = '';
        }
    }

    function validateIntegerRequired(input, label, minValue = 0) {
        const value = input.value.trim();
        if (value === "") {
            showError(input, `${label} is required`);
            return false;
        }
        if (isNaN(value)) {
            showError(input, `${label} must be a number`);
            return false;
        }
        const number = parseFloat(value);
        if (number < minValue) {
            showError(input, `${label} must be >= ${minValue}`);
            return false;
        }
        if (!Number.isInteger(number)) {
            showError(input, `${label} must be an integer`);
            return false;
        }
        clearError(input);
        return true;
    }

    function validateNumberInput(input, label, minValue = 0, maxValue = Number.MAX_VALUE) {
        const value = input.value.trim();
        if (value === "") {
            showError(input, `${label} is required`);
            return false;
        }
        if (isNaN(value)) {
            showError(input, `${label} must be a number`);
            return false;
        }
        const number = parseFloat(value);
        if (number < minValue || number > maxValue) {
            showError(input, `${label} must be between ${minValue} and ${maxValue}`);
            return false;
        }
        clearError(input);
        return true;
    }

    form.addEventListener('submit', function (e) {
        e.preventDefault();
        let isValid = true;

        const codeInput = form.querySelector('[name="code"]');
        if (codeInput.value.trim().length < 3) {
            showError(codeInput, 'Coupon code must be at least 3 characters');
            isValid = false;
        } else {
            clearError(codeInput);
        }

        const descInput = form.querySelector('[name="description"]');
        if (descInput.value.trim().length < 5) {
            showError(descInput, 'Description must be at least 5 characters');
            isValid = false;
        } else {
            clearError(descInput);
        }

        if (discountTypeSelect.value === "") {
            showError(discountTypeSelect, 'Discount type is required');
            isValid = false;
        } else {
            clearError(discountTypeSelect);
        }

        // 👉 Xử lý giới hạn discount value phù hợp loại
        const discountMax = discountTypeSelect.value === 'percentage' ? 100 : Number.MAX_VALUE;
        if (discountTypeSelect.value === 'fixed') {
            discountValueInput.removeAttribute('max'); // tránh giới hạn nhầm
        }

        if (!validateNumberInput(discountValueInput, 'Discount value', 0, discountMax)) {
            isValid = false;
        }

        const minPurchaseInput = form.querySelector('[name="minpurchase"]');
        if (!validateNumberInput(minPurchaseInput, 'Min purchase', 0)) {
            isValid = false;
        }

        const maxDiscountInput = form.querySelector('[name="maxdiscount"]');
        if (discountTypeSelect.value === 'percentage') {
            if (!validateNumberInput(maxDiscountInput, 'Max discount', 0)) {
                isValid = false;
            }
        }

        const usageLimitInput = form.querySelector('[name="usageLimit"]');
        if (!validateIntegerRequired(usageLimitInput, 'Usage limit', 0)) {
            isValid = false;
        }

        const perCusLimitInput = form.querySelector('[name="percuslimit"]');
        if (!validateIntegerRequired(perCusLimitInput, 'Per customer limit', 0)) {
            isValid = false;
        }

        const startDateInput = form.querySelector('[name="date1"]');
        const endDateInput = form.querySelector('[name="date2"]');
        const startDate = new Date(startDateInput.value);
        const endDate = new Date(endDateInput.value);

        if (!startDateInput.value) {
            showError(startDateInput, 'Start date is required');
            isValid = false;
        } else {
            clearError(startDateInput);
        }

        if (!endDateInput.value) {
            showError(endDateInput, 'End date is required');
            isValid = false;
        } else {
            clearError(endDateInput);
        }

        if (startDateInput.value && endDateInput.value && startDate > endDate) {
            showError(startDateInput, 'Start date must be before or equal to end date');
            showError(endDateInput, 'End date must be after or equal to start date');
            isValid = false;
        }

        if (isValid) {
            form.submit();
        } else {
            const firstError = form.querySelector('.is-invalid');
            if (firstError) {
                firstError.scrollIntoView({ behavior: 'smooth', block: 'center' });
                firstError.focus();
            }
        }
    });

    // 👉 Thay đổi giao diện khi chọn loại giảm giá
    discountTypeSelect.addEventListener('change', function () {
        if (this.value === 'percentage') {
            discountValueInput.placeholder = 'Enter percentage (0–100)';
            discountValueInput.max = '100';
            maxDiscountField.style.display = 'block';
        } else {
            discountValueInput.placeholder = 'Enter fixed amount';
            discountValueInput.removeAttribute('max');
            maxDiscountField.style.display = 'none';
        }
    });

    // 👉 Validate realtime từng field
    form.querySelectorAll('input, textarea, select').forEach(input => {
        input.addEventListener('input', () => clearError(input));
        input.addEventListener('blur', function () {
            const name = this.name;

            if (name === 'description' && this.value.trim().length < 5) {
                showError(this, 'Description must be at least 5 characters');
            }

            if (name === 'discountype' && this.value === "") {
                showError(this, 'Discount type is required');
            }

            if (name === 'discountvalue') {
                const max = discountTypeSelect.value === 'percentage' ? 100 : Number.MAX_VALUE;
                validateNumberInput(this, 'Discount value', 0, max);
            }

            if (name === 'minpurchase') {
                validateNumberInput(this, 'Min purchase', 0);
            }

            if (name === 'maxdiscount' && discountTypeSelect.value === 'percentage') {
                validateNumberInput(this, 'Max discount', 0);
            }

            if (name === 'usageLimit') {
                validateIntegerRequired(this, 'Usage limit', 0);
            }

            if (name === 'percuslimit') {
                validateIntegerRequired(this, 'Per customer limit', 0);
            }
        });
    });

    // 👉 Gán ngày mặc định
    const now = new Date();
    const startDateInput = document.querySelector('input[name="date1"]');
    const endDateInput = document.querySelector('input[name="date2"]');
    const defaultEnd = new Date(now.getTime() + (30 * 24 * 60 * 60 * 1000));
    if (!startDateInput.value) startDateInput.valueAsDate = now;
    if (!endDateInput.value) endDateInput.valueAsDate = defaultEnd;

    // 👉 Trạng thái khởi đầu của giao diện khi load lại form
    if (discountTypeSelect.value === 'percentage') {
        maxDiscountField.style.display = 'block';
        discountValueInput.placeholder = 'Enter percentage (0–100)';
        discountValueInput.max = '100';
    } else {
        maxDiscountField.style.display = 'none';
        discountValueInput.placeholder = 'Enter fixed amount';
        discountValueInput.removeAttribute('max');
    }
});
</script>

 </body>
 <!-- Mirrored from themesflat.co/html/remos/add-product.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:35 GMT -->
 </html>