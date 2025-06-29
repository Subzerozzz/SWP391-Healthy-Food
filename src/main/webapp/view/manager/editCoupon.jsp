<%-- 
    Document   : editCoupon
    Created on : Jun 14, 2025, 4:34:20 PM
    Author     : Predator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                                    padding-left: 650px;
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
                <jsp:include page="../common/manager/sidebar.jsp"></jsp:include>
                <!-- /section-menu-left -->
                <!-- section-content-right -->
                <div class="section-content-right">
                    <!-- header-dashboard -->
                    <jsp:include page="../common/manager/headerDashboard.jsp"></jsp:include> 
                    <!-- /header-dashboard -->
                    <!-- main-content -->
                    <div class="main-content">
                        <!-- main-content-wrap -->
                        <div class="main-content-inner">
                            <!-- main-content-wrap -->
                            <div class="main-content-wrap">
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                    <h3>Edit Coupon</h3>
                                <fmt:formatDate value="${coupon.start_date}" pattern="yyyy-MM-dd" var="startDateFormatted" />
                                <fmt:formatDate value="${coupon.end_date}" pattern="yyyy-MM-dd" var="endDateFormatted" />
                                </div>
                                <!-- form-add-coupon -->
                                <form  
                                    action="ManagerCoupon?action=edit"
                                     method="POST">
                                    <input type="hidden" name="id" value="${coupon.id}">
                                    <div class="wg-box">
                                        <!-- Coupon Code -->
                                        <fieldset class="code">
                                            <div class="body-title mb-10">Coupon Code <span class="tf-color-1">*</span></div>
                                            <input class="mb-10" type="text" placeholder="Enter coupon code" name="code" tabindex="0" value="${coupon.code}" aria-required="true" required maxlength="50">
                                            <div class="text-tiny">Enter a unique coupon code (max 50 characters).</div>
                                        </fieldset>
                                        <!-- Description -->
                                        <fieldset class="description">
                                            <div class="body-title mb-10">Description <span class="tf-color-1">*</span></div>
                                             <input class="mb-10" type="text" placeholder="Enter description" name="description" tabindex="0" value="${coupon.description}" aria-required="true" required maxlength="500">
                                            <div class="text-tiny">Describe the coupon offer (max 500 characters).</div>
                                            
                                        </fieldset>
                                        <!-- Discount Type -->
                                        <fieldset class="discountType">
                                            <div class="body-title mb-10">Discount Type <span class="tf-color-1">*</span></div>
                                            <div class="select">
                                                <select name="discountype" required>
                                                    <option value="">Select discount type</option>
                                                    <option value="percentage" 
                                                            <c:if test="${coupon.discount_type == 'percentage'}">selected</c:if>>Percentage (%)</option>
                                                            <option value="fixed" 
                                                            <c:if test="${coupon.discount_type == 'fixed'}">selected</c:if>>Fixed Amount</option>
                                                </select>
                                            </div>
                                        </fieldset>
                                        <!-- Discount Value -->
                                        <fieldset class="discountValue">
                                            <div class="body-title mb-10">Discount Value <span class="tf-color-1">*</span></div>
                                            <input class="mb-10" type="number" step="0.01" min="0" placeholder="Enter discount value" name="discountvalue" tabindex="0" value="${coupon.discount_value}" aria-required="true" required>
                                            <div class="text-tiny">Enter the discount amount or percentage value.</div>
                                        </fieldset>

                                        <!-- Min Purchase -->
                                        <fieldset class="minPurchase">
                                            <div class="body-title mb-10">Minimum Purchase Amount</div>
                                            <input class="mb-10" type="number" step="0.01" min="0" placeholder="Enter minimum purchase amount" name="minpurchase" tabindex="0" value="${coupon.min_purchase}">
                                            <div class="text-tiny">Minimum order amount required to use this coupon (optional).</div>
                                        </fieldset>
                                            <fieldset class="maxDiscount">
                                                <div class="body-title mb-10">Maximum Discount Amount</div>
                                                <input class="mb-10" type="number" step="0.01" min="0" 
                                                       placeholder="Enter maximum discount amount" name="maxdiscount"
                                                       tabindex="0" value="${coupon.max_discount}">
                                                    <div class="text-tiny">Maximum discount amount for percentage coupons (optional).</div>
                                            </fieldset>
                                                 <!-- Usage Limit -->
                                                    <fieldset class="usageLimit">
                                                        <div class="body-title mb-10">Usage Limit</div>
                                                        <input class="mb-10" type="number" min="0" step="1" 
                                                               placeholder="Enter usage limit"
                                                               name="usageLimit"
                                                               value="${coupon.usage_limit}">
                                                            <div class="text-tiny">Maximum number of times this coupon can be used. Leave empty for unlimited.</div>
                                                    </fieldset>
                                                               <!-- Per Customer Limit -->
                                                               <fieldset class="perCustomerLimit">
                                                                   <div class="body-title mb-10">Per Customer Limit</div>
                                                                   <input class="mb-10" type="number" min="0" step="1"
                                                                          placeholder="Enter per customer limit"
                                                                          name="percuslimit"
                                                                          value="${coupon.per_customer_limit}">
                                                                       <div class="text-tiny">Maximum number of times a customer can use this coupon. Leave empty for unlimited.</div>
                                                               </fieldset>       
                                                    <!-- Status -->
                                                    <fieldset class="status">
                                                        <div class="body-title mb-10">Status</div>
                                                        <div style="display: flex; align-items: center; gap: 10px;">
                                                            <!-- Hidden input dùng để submit giá trị 0, sẽ được cập nhật bằng JS -->
                                                            <input type="hidden" id="isActiveHidden" name="isActive" 
                                                                   value="${coupon.is_active == 1 ? '1' : '0'}" />

                                                            <label class="switch">
                                                                <!-- Checkbox KHÔNG có name để tránh gửi trùng name -->
                                                                <input type="checkbox" id="isActiveCheckbox"
                                                                       <c:if test="${coupon.is_active == 1}">checked</c:if>
                                                                       onchange="document.getElementById('isActiveHidden').value = this.checked ? '1' : '0'" />
                                                                <span class="slider round"></span>
                                                            </label>
                                                            <span class="label">Active</span>
                                                        </div>
                                                    </fieldset>
                                                       <div class="cols gap10">
                                                           <fieldset class="startDate">
                                                               <div class="body-title mb-10">Start Date <span class="tf-color-1">*</span></div>
                                                               <input type="date" name="date1" id="startDate" value="${startDateFormatted}" required>
                                                                   <div class="text-tiny">When the coupon becomes active.</div>
                                                           </fieldset>

                                                           <fieldset class="endDate">
                                                               <div class="body-title mb-10">End Date <span class="tf-color-1">*</span></div>
                                                               <input type="date" name="date2" id="endDate" value="${endDateFormatted}" required>
                                                                   <div class="text-tiny">When the coupon expires.</div>
                                                           </fieldset>
                                                       </div>                                    
                                    </div>  
                                        <!-- Date Range -->
                                         <div class="cols gap10">
                                             <button class="tf-button w-full" type="submit">Edit Coupon</button>
                                         </div>
                                </div>
                                 </form>
                                 <!-- /form-edit-coupon -->
                             </div>
                             <!-- /main-content-wrap -->
                         </div>
                         <!-- /main-content-wrap -->
                     </div>
                          <!-- bottom-page -->
                        <
                         <!-- /bottom-page -->                                             
                     <!-- /main-content -->
                 </div>
                        <div class="footer-wrap">
                          <jsp:include page="../common/manager/footer.jsp"></jsp:include>
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
         // Validate form before submit
        document.querySelector('form').addEventListener('submit', function(e) {
        const startInput = document.querySelector('input[name="date1"]').value;
        const endInput = document.querySelector('input[name="date2"]').value;

        const startDate = new Date(startInput);
        const endDate = new Date(endInput);

         // Lấy ngày hôm nay, bỏ giờ để so sánh chính xác
        const today = new Date();
        today.setHours(0, 0, 0, 0);
        startDate.setHours(0, 0, 0, 0);
        endDate.setHours(0, 0, 0, 0);

        if (startDate < today) {
            e.preventDefault();
            alert('Start date must be today or in the future');
            return false;
         }

        if (endDate < startDate) {
            e.preventDefault();
            alert('End date must be the same or after start date');
            return false;
        }
             const discountType = document.querySelector('select[name="discountype"]').value;
             const discountValue = parseFloat(document.querySelector('input[name="discountvalue"]').value);
             
             if (discountType === 'percentage' && discountValue > 100) {
                 e.preventDefault();
                 alert('Percentage discount cannot exceed 100%');
                 return false;
             }
         });

         // Update discount value placeholder based on type
         document.querySelector('select[name="discountype"]').addEventListener('change', function() {
             const discountValueInput = document.querySelector('input[name="discountvalue"]');
             const maxDiscountField = document.querySelector('fieldset.maxdiscount');
             
             if (this.value === 'percentage') {
                 discountValueInput.placeholder = 'Enter percentage (0-100)';
                 discountValueInput.max = '100';
                 maxDiscountField.style.display = 'block';
             } else if (this.value === 'fixed') {
                 discountValueInput.placeholder = 'Enter fixed amount';
                 discountValueInput.removeAttribute('max');
                 maxDiscountField.style.display = 'none';
             }
         });

         // Set default start date to now
        document.addEventListener('DOMContentLoaded', function () {
        const startDateInput = document.getElementById('startDate');
        const endDateInput = document.getElementById('endDate');

        const pad = (num) => num.toString().padStart(2, '0');

        const formatDate = (d) => 
            d.getFullYear() + '-' + pad(d.getMonth() + 1) + '-' + pad(d.getDate());

        // Gán nếu chưa có giá trị từ server
        if (!startDateInput.value) {
            const today = new Date();
            startDateInput.value = formatDate(today);
        }

        if (!endDateInput.value) {
            const endDate = new Date();
            endDate.setDate(endDate.getDate() + 30); // 30 ngày sau
            endDateInput.value = formatDate(endDate);
        }
    });
     </script>
 </body>
 <!-- Mirrored from themesflat.co/html/remos/add-product.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:35 GMT -->
 </html>