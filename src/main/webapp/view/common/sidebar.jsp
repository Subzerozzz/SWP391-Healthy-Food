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
      <a href="${pageContext.request.contextPath}/home" id="site-logo-inner">
        <img class="logo" src="${pageContext.request.contextPath}/images/healthylogo.png">
      </a>
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

                <c:if test="${sessionScope.account.role eq 'nutri' }">

                    <li class="menu-item">
                      <a href="${pageContext.request.contextPath}/manage-food?action=add" class="">
                        <div class="icon">
                          <i class="icon-settings"></i>
                        </div>
                        <div class="text">Add New Food</div>
                      </a>
                    </li>
                    <li class="menu-item">
                      <a href="${pageContext.request.contextPath}/manage-food?action=view" class="menu-item-button">
                        <div class="icon"><i class="icon-layers"></i></div>
                        <div class="text">View Food List</div>
                      </a>
                    </li>

                    <li class="menu-item">
                      <a href="${pageContext.request.contextPath}/manage-food?action=request" class="menu-item-button">
                        <div class="icon"><i class="icon-layers"></i></div>
                        <div class="text">View Request</div>
                      </a>
                    </li>

                    <li class="menu-item">
                      <a href="${pageContext.request.contextPath}/manage-blog" class="menu-item-button">
                        <div class="icon"><i class="icon-layers"></i></div>
                        <div class="text">View Blog</div>
                      </a>
                    </li>
                </c:if>
                    
                <c:if test="${sessionScope.account.role eq 'admin' }">
                    <li class="menu-item">
                        <a href="${pageContext.request.contextPath}/manage-account?action=add" class="">
                            <div class="icon">
                                <i class="icon-settings"></i>
                            </div>
                            <div class="text">Add New User</div>
                        </a>
                    </li>
                    <li class="menu-item">
                        <a href="${pageContext.request.contextPath}/manage-account?action=list" class="menu-item-button">
                            <div class="icon"><i class="icon-layers"></i></div>
                            <div class="text">All Account</div>
                        </a>
                    </li>
                </c:if>
                    
                <c:if test="${sessionScope.account.role eq 'customer' }">
                    <li class="menu-item">
                        <a href="${pageContext.request.contextPath}/orderlist" class="">
                            <div class="icon">
                                <i class="icon-settings"></i>
                            </div>
                            <div class="text">Order List</div>
                        </a>
                    </li>
                </c:if>
                    
                <c:if test="${sessionScope.account.role eq 'manager' }">
                  <li class="menu-item">
                    <a href="${pageContext.request.contextPath}/type-of-request" class="menu-item-button">
                      <div class="icon"><i class="icon-layers"></i></div>
                      <div class="text">View Request</div>
                    </a>
                  </li>
                </c:if>   
                    
            </ul>
            
            
          
        </div>
      </div>

    </div>
  </div>

  <style>
/*   .box-logo {
      overflow: hidden;
      margin-bottom: 50px
    }

    .logo {
      width: 180px;
      height: 100%;
      display: block
    }*/

    .section-menu-left-wrap {
      margin-top: 50px;
      
    }
    
    .layout-wrap .section-menu-left::before{
        background-color: #F0FDF4 !important
    }
    
  </style>

</body>

</html>