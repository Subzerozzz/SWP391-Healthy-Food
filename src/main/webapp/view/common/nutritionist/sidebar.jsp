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
            <a href="${pageContext.request.contextPath}/manage-food?action=view" id="site-logo-inner">
              <div style="font-size: 24px; font-family: Verdana; font-style: italic; font-weight: 700">GreenBite</div>
            </a>
            <div class="button-show-hide">
              <i class="icon-menu-left"></i>
            </div>
          </div>
          <div class="section-menu-left-wrap">
            <div class="center">
              <div class="center-item">
                <div class="center-heading">All page</div>
                <ul class="menu-list">
                  <li class="menu-item has-children active">
                    <a href="javascript:void(0);" class="menu-item-button">
                      <div class="icon"><i class="icon-shopping-cart"></i></div>
                      <div class="text">Product</div>
                    </a>
                    <ul class="sub-menu" style="display: block;">
                      <li class="sub-menu-item">
                          <a href="${pageContext.request.contextPath}/manage-food?action=add" class="">
                          <div class="text">Add New Product</div>
                        </a>
                      </li>
                      <li class="sub-menu-item">
                        <a href="${pageContext.request.contextPath}/manage-food?action=view" class="active"
                          id="viewLink">
                          <div class="text">Food List</div>
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="menu-item has-children">
                    <a href="javascript:void(0);" class="menu-item-button">
                      <div class="icon"><i class="icon-layers"></i></div>
                      <div class="text">Yêu cầu chờ duyệt</div>
                    </a>
                    <ul class="sub-menu">
                      <li class="sub-menu-item">
                          <a href="${pageContext.request.contextPath}/manage-food?action=request" class="">
                            <div class="text">Xem các yêu cầu</div>
                          </a>
                      </li>
                      <li class="sub-menu-item">
                        <a href="new-category.html" class="">
                          <div class="text">New category</div>
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="menu-item has-children">
                    <a href="javascript:void(0);" class="menu-item-button">
                      <div class="icon"><i class="icon-box"></i></div>
                      <div class="text">Attributes</div>
                    </a>
                    <ul class="sub-menu">
                      <li class="sub-menu-item">
                        <a href="attributes.html" class="">
                          <div class="text">Attributes</div>
                        </a>
                      </li>
                      <li class="sub-menu-item">
                        <a href="add-attributes.html" class="">
                          <div class="text">Add attributes</div>
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="menu-item has-children">
                    <a href="javascript:void(0);" class="menu-item-button">
                      <div class="icon"><i class="icon-file-plus"></i></div>
                      <div class="text">Order</div>
                    </a>
                    <ul class="sub-menu">
                      <li class="sub-menu-item">
                        <a href="oder-list.html" class="">
                          <div class="text">Order list</div>
                        </a>
                      </li>
                      <li class="sub-menu-item">
                        <a href="oder-detail.html" class="">
                          <div class="text">Order detail</div>
                        </a>
                      </li>
                      <li class="sub-menu-item">
                        <a href="oder-tracking.html" class="">
                          <div class="text">Order tracking</div>
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="menu-item has-children">
                    <a href="javascript:void(0);" class="menu-item-button">
                      <div class="icon"><i class="icon-user"></i></div>
                      <div class="text">User</div>
                    </a>
                    <ul class="sub-menu">
                      <li class="sub-menu-item">
                        <a href="all-user.html" class="">
                          <div class="text">All user</div>
                        </a>
                      </li>
                      <li class="sub-menu-item">
                        <a href="add-new-user.html" class="">
                          <div class="text">Add new user</div>
                        </a>
                      </li>
                      <li class="sub-menu-item">
                        <a href="login.html" class="">
                          <div class="text">Login</div>
                        </a>
                      </li>
                      <li class="sub-menu-item">
                        <a href="sign-up.html" class="">
                          <div class="text">Sign up</div>
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="menu-item has-children">
                    <a href="javascript:void(0);" class="menu-item-button">
                      <div class="icon"><i class="icon-user-plus"></i></div>
                      <div class="text">Roles</div>
                    </a>
                    <ul class="sub-menu">
                      <li class="sub-menu-item">
                        <a href="all-roles.html" class="">
                          <div class="text">All roles</div>
                        </a>
                      </li>
                      <li class="sub-menu-item">
                        <a href="create-role.html" class="">
                          <div class="text">Create role</div>
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="menu-item">
                    <a href="gallery.html" class="">
                      <div class="icon"><i class="icon-image"></i></div>
                      <div class="text">Gallery</div>
                    </a>
                  </li>
                  <li class="menu-item">
                    <a href="report.html" class="">
                      <div class="icon"><i class="icon-pie-chart"></i></div>
                      <div class="text">Report</div>
                    </a>
                  </li>
                </ul>
              </div>
              <div class="center-item">
                <div class="center-heading">Setting</div>
                <ul class="menu-list">
                  <li class="menu-item has-children">
                    <a href="javascript:void(0);" class="menu-item-button">
                      <div class="icon"><i class="icon-map-pin"></i></div>
                      <div class="text">Location</div>
                    </a>
                    <ul class="sub-menu">
                      <li class="sub-menu-item">
                        <a href="countries.html" class="">
                          <div class="text">Countries</div>
                        </a>
                      </li>
                      <li class="sub-menu-item">
                        <a href="states.html" class="">
                          <div class="text">States</div>
                        </a>
                      </li>
                      <li class="sub-menu-item">
                        <a href="cities.html" class="">
                          <div class="text">Cities</div>
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="menu-item">
                    <a href="setting.html" class="">
                      <div class="icon"><i class="icon-settings"></i></div>
                      <div class="text">Setting</div>
                    </a>
                  </li>
                  <li class="menu-item has-children">
                    <a href="javascript:void(0);" class="menu-item-button">
                      <div class="icon"><i class="icon-edit"></i></div>
                      <div class="text">Pages</div>
                    </a>
                    <ul class="sub-menu">
                      <li class="sub-menu-item">
                        <a href="list-page.html" class="">
                          <div class="text">List page</div>
                        </a>
                      </li>
                      <li class="sub-menu-item">
                        <a href="new-page.html" class="">
                          <div class="text">New page</div>
                        </a>
                      </li>
                      <li class="sub-menu-item">
                        <a href="edit-page.html" class="">
                          <div class="text">Edit page</div>
                        </a>
                      </li>
                    </ul>
                  </li>
                </ul>
              </div>
              
              
              
            </div>
            
          </div>
        </div>

      </body>

      </html>