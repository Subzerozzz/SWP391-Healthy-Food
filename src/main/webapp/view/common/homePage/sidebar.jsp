<%-- Document : sidebar.jsp Created on : May 25, 2025, 4:26:33 PM Author : Dell --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <div class="section-menu-left">
            <div class="box-logo">
                <a href="${pageContext.request.contextPath}/manage-account?action=list" id="site-logo-inner">
                    <img class="logo" src="${pageContext.request.contextPath}/images/healthylogo.png">          
                </a>
                <div class="button-show-hide">
                    <i class="icon-menu-left"></i>
                </div>
            </div>
            <div class="section-menu-left-wrap">
                <div class="center">
                    <div class="center-item">
                        <ul class="menu-list">
                            <li class="menu-item">
                                <a href="${pageContext.request.contextPath}/myaccount" class="">
                                    <div class="icon">
                                        <i class="icon-settings"></i>
                                    </div>
                                    <div class="text">My Account</div>
                                </a>
                            </li>

                            <li class="menu-item">
                                <a href="${pageContext.request.contextPath}/changepassword" class="">
                                    <div class="icon">
                                        <i class="icon-settings"></i>
                                    </div>
                                    <div class="text">Change password</div>
                                </a>
                            </li>

                            <li class="menu-item">
                                <a href="${pageContext.request.contextPath}/orderlist" class="">
                                    <div class="icon">
                                        <i class="icon-settings"></i>
                                    </div>
                                    <div class="text">Order List</div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <style>
            .box-logo{
                height: 100px;
                overflow: hidden
            }
            .logo{
                max-width:100%;
                height: 100%;
                display:block
            }
        </style>

    </body>

</html>