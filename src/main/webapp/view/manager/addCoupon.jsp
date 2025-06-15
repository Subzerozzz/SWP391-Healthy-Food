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
                    <jsp:include page="../common/manager/header.jsp"></jsp:include> 
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
                                            <input class="mb-10" type="text" placeholder="Enter coupon code" name="code" tabindex="0" value="" aria-required="true" required maxlength="50">
                                            <div class="text-tiny">Enter a unique coupon code (max 50 characters).</div>
                                        </fieldset>

                                        <!-- Description -->
                                        <fieldset class="description">
                                            <div class="body-title mb-10">Description <span class="tf-color-1">*</span></div>
                                            <textarea class="mb-10" placeholder="Enter coupon description" name="description" tabindex="0" aria-required="true" required maxlength="500" rows="3"></textarea>
                                            <div class="text-tiny">Describe the coupon offer (max 500 characters).</div>
                                        </fieldset>
                                        <!-- Discount Type -->
                                        <fieldset class="discountType">
                                            <div class="body-title mb-10">Discount Type <span class="tf-color-1">*</span></div>
                                            <div class="select">
                                                <select name="discountype" required>
                                                    <option value="">Select discount type</option>
                                                    <option value="percentage">Percentage (%)</option>
                                                    <option value="fixed">Fixed Amount</option>
                                                </select> 
                                            </div>
                                        </fieldset>
                                        <!-- Discount Value -->
                                        <fieldset class="discountValue">
                                            <div class="body-title mb-10">Discount Value <span class="tf-color-1">*</span></div>
                                            <input class="mb-10" type="number" step="0.01" min="0" placeholder="Enter discount value" name="discountvalue" tabindex="0" value="" aria-required="true" required>
                                            <div class="text-tiny">Enter the discount amount or percentage value.</div>
                                        </fieldset>

                                        <!-- Min Purchase -->
                                        <fieldset class="minPurchase">
                                            <div class="body-title mb-10">Minimum Purchase Amount</div>
                                            <input class="mb-10" type="number" step="0.01" min="0" placeholder="Enter minimum purchase amount" name="minpurchase" tabindex="0" value="">
                                            <div class="text-tiny">Minimum order amount required to use this coupon (optional).</div>
                                        </fieldset>
                                        <!-- Date Range -->
                                         <!-- Max Discount -->
                                        <fieldset class="maxDiscount">
                                            <div class="body-title mb-10">Maximum Discount Amount</div>
                                            <input class="mb-10" type="number" step="0.01" min="0" placeholder="Enter maximum discount amount" name="maxdiscount" tabindex="0" value="">
                                            <div class="text-tiny">Maximum discount amount for percentage coupons (optional).</div>
                                        </fieldset>

                                          <div class="cols gap10">
                                            <fieldset class="startDate">
                                                <div class="body-title mb-10">Start Date <span class="tf-color-1">*</span></div>
                                                <input class="mb-10" type="date" name="date1" tabindex="0" aria-required="true" required>
                                                <div class="text-tiny">When the coupon becomes active.</div>
                                            </fieldset>

                                            <fieldset class="endDate">
                                                <div class="body-title mb-10">End Date <span class="tf-color-1">*</span></div>
                                                <input class="mb-10" type="date" name="date2" tabindex="0" aria-required="true" required>
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
     
     <script>
         // Validate form before submit
         document.querySelector('form').addEventListener('submit', function(e) {
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
         document.addEventListener('DOMContentLoaded', function() {
             const now = new Date();
             const startDateInput = document.querySelector('input[name="date1"]');
             const endDateInput = document.querySelector('input[name="date2"]');
             
             // Set start date to current time
             startDateInput.value = now.toISOString().slice(0, 16);
             
             // Set end date to 30 days from now
             const endDate = new Date(now.getTime() + (30 * 24 * 60 * 60 * 1000));
             endDateInput.value = endDate.toISOString().slice(0, 16);
         });
     </script>
 </body>
 <!-- Mirrored from themesflat.co/html/remos/add-product.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:35 GMT -->
 </html>