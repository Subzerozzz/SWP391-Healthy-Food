<%-- 
    Document   : headerDashboard
    Created on : May 26, 2025, 6:03:59 PM
    Author     : Dell
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
    </head>
    <body>
        <div class="header-dashboard">
                <div class="wrap">
                    <div class="header-left" style="visibility: hidden;">
                        <a href="index.html">
                            <img class="" id="logo_header_mobile" alt="" src="${pageContext.request.contextPath}/images/logo/logo.png" data-light="${pageContext.request.contextPath}/images/logo/logo.png" data-dark="${pageContext.request.contextPath}/images/logo/logo-dark.png" data-width="154px" data-height="52px" data-retina="${pageContext.request.contextPath}/images/logo/logo@2x.png">
                        </a>
                        <div class="button-show-hide">
                            <i class="icon-menu-left"></i>
                        </div>
                        <form class="form-search flex-grow">
                            <fieldset class="name">
                                <input type="text" placeholder="Search here..." class="show-search" name="name" tabindex="2" value="" aria-required="true" required="">
                            </fieldset>
                            <div class="button-submit">
                                <button class="" type="submit"><i class="icon-search"></i></button>
                            </div>
                            <div class="box-content-search" id="box-content-search">
                                <ul class="mb-24">
                                    <li class="mb-14">
                                        <div class="body-title">Top selling product</div>
                                    </li>
                                    <li class="mb-14">
                                        <div class="divider"></div>
                                    </li>
                                    <li>
                                        <ul>
                                            <li class="product-item gap14 mb-10">
                                                <div class="image no-bg">
                                                    <img src="${pageContext.request.contextPath}/images/products/17.png" alt="">
                                                </div>
                                                <div class="flex items-center justify-between gap20 flex-grow">
                                                    <div class="name">
                                                        <a href="product-list.html" class="body-text">Dog Food Rachael Ray Nutrish®</a>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="mb-10">
                                                <div class="divider"></div>
                                            </li>
                                            <li class="product-item gap14 mb-10">
                                                <div class="image no-bg">
                                                    <img src="${pageContext.request.contextPath}/images/products/18.png" alt="">
                                                </div>
                                                <div class="flex items-center justify-between gap20 flex-grow">
                                                    <div class="name">
                                                        <a href="product-list.html" class="body-text">Natural Dog Food Healthy Dog Food</a>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="mb-10">
                                                <div class="divider"></div>
                                            </li>
                                            <li class="product-item gap14">
                                                <div class="image no-bg">
                                                    <img src="${pageContext.request.contextPath}/images/products/19.png" alt="">
                                                </div>
                                                <div class="flex items-center justify-between gap20 flex-grow">
                                                    <div class="name">
                                                        <a href="product-list.html" class="body-text">Freshpet Healthy Dog Food and Cat</a>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                                <ul class="">
                                    <li class="mb-14">
                                        <div class="body-title">Order product</div>
                                    </li>
                                    <li class="mb-14">
                                        <div class="divider"></div>
                                    </li>
                                    <li>
                                        <ul>
                                            <li class="product-item gap14 mb-10">
                                                <div class="image no-bg">
                                                    <img src="${pageContext.request.contextPath}/images/products/20.png" alt="">
                                                </div>
                                                <div class="flex items-center justify-between gap20 flex-grow">
                                                    <div class="name">
                                                        <a href="product-list.html" class="body-text">Sojos Crunchy Natural Grain Free...</a>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="mb-10">
                                                <div class="divider"></div>
                                            </li>
                                            <li class="product-item gap14 mb-10">
                                                <div class="image no-bg">
                                                    <img src="${pageContext.request.contextPath}/images/products/21.png" alt="">
                                                </div>
                                                <div class="flex items-center justify-between gap20 flex-grow">
                                                    <div class="name">
                                                        <a href="product-list.html" class="body-text">Kristin Watson</a>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="mb-10">
                                                <div class="divider"></div>
                                            </li>
                                            <li class="product-item gap14 mb-10">
                                                <div class="image no-bg">
                                                    <img src="${pageContext.request.contextPath}/images/products/22.png" alt="">
                                                </div>
                                                <div class="flex items-center justify-between gap20 flex-grow">
                                                    <div class="name">
                                                        <a href="product-list.html" class="body-text">Mega Pumpkin Bone</a>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="mb-10">
                                                <div class="divider"></div>
                                            </li>
                                            <li class="product-item gap14">
                                                <div class="image no-bg">
                                                    <img src="${pageContext.request.contextPath}/images/products/23.png" alt="">
                                                </div>
                                                <div class="flex items-center justify-between gap20 flex-grow">
                                                    <div class="name">
                                                        <a href="product-list.html" class="body-text">Mega Pumpkin Bone</a>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </form>
                </div>
                    <div class="header-grid d-flex justify-content-end">
                        <div class="popup-wrap user type-header">
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton3" data-bs-toggle="dropdown" aria-expanded="false">
                                    <span class="header-user wg-user">
                                        <span class="image">
                                            <img src="${pageContext.request.contextPath}/images/user_icon2.png" alt="">
                                        </span>
                                        <span class="flex flex-column">
                                            <span class="body-title mb-2">${sessionScope.account.getUser_name()}</span>
                                            <c:if test="${sessionScope.account.role eq 'nutri' }">
                                                <span class="text-tiny">Nutritionist</span>
                                            </c:if>
                                            
                                            <c:if test="${sessionScope.account.role eq 'admin' }">
                                                <span class="text-tiny">Admin</span>
                                            </c:if>
                                                
                                            <c:if test="${sessionScope.account.role eq 'customer' }">
                                                <span class="text-tiny">Customer</span>
                                            </c:if>
                                                
                                            <c:if test="${sessionScope.account.role eq 'manager' }">
                                                <span class="text-tiny">Manager</span>
                                            </c:if>
                                        </span>
                                    </span>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end has-content" aria-labelledby="dropdownMenuButton3" >

                                    <li>
                                        <a href="${pageContext.request.contextPath}/logout" class="user-item">
                                            <div class="icon">
                                                <i class="icon-log-out"></i>
                                            </div>
                                            <div class="body-title-2">Log out</div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        
                                            
                                            <style>
                                                .header-dashboard{
                                                    background-color: #F0FDF4;
                                                }
                                            </style>

    </body>
</html>