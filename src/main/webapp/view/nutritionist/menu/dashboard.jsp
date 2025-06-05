<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@page contentType="text/html" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
    <!--[if (gte IE 9)|!(IE)]><!-->
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
    <!--<![endif]-->


    <!-- Mirrored from themesflat.co/html/remos/product-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:35 GMT -->

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
      <link rel="stylesheet" href="${pageContext.request.contextPath}/font/fonts.css">

      <!-- Icon -->
      <link rel="stylesheet" href="${pageContext.request.contextPath}/icon/style.css">

      <!-- Favicon and Touch Icons  -->
      <link rel="shortcut icon"
        href="${pageContext.request.contextPath}/${pageContext.request.contextPath}/images/favicon_1.png">
      <link rel="apple-touch-icon-precomposed"
        href="${pageContext.request.contextPath}/${pageContext.request.contextPath}/images/favicon_1.png">

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
            <jsp:include page="../../common/nutritionist/sidebar.jsp"></jsp:include>
            <!-- /section-menu-left -->
            <!-- section-content-right -->
            <div class="section-content-right">
              <!-- header-dashboard -->
              <jsp:include page="../../common/nutritionist/headerDashboard.jsp"></jsp:include>
              <!-- /header-dashboard -->
              <!-- main-content -->
              <div class="main-content">
                <!-- main-content-wrap -->
                <div class="main-content-inner">
                  <!-- main-content-wrap -->
                  <div class="main-content-wrap">
                    <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                      <h3>Food List</h3>

                    </div>
                    <!-- Thêm modal xác nhận xóa -->
                    <div id="customDeleteModal" class="custom-modal-overlay" style="display:none;">
                      <div class="custom-modal">
                        <button onclick="hideFormModal1()" class="custom-modal-close"
                          id="closeModalBtn">&times;</button>
                        <div class="custom-modal-icon">
                          <svg width="48" height="48" viewBox="0 0 48 48" fill="none">
                            <circle cx="24" cy="24" r="24" fill="#FEE2E2" />
                            <g>
                              <rect x="18" y="20" width="12" height="12" rx="2" stroke="#EF4444" stroke-width="2"
                                fill="none" />
                              <rect x="21" y="16" width="6" height="2" rx="1" fill="#EF4444" />
                            </g>
                            <line x1="20" y1="24" x2="20" y2="28" stroke="#EF4444" stroke-width="2"
                              stroke-linecap="round" />
                            <line x1="24" y1="24" x2="24" y2="28" stroke="#EF4444" stroke-width="2"
                              stroke-linecap="round" />
                            <line x1="28" y1="24" x2="28" y2="28" stroke="#EF4444" stroke-width="2"
                              stroke-linecap="round" />
                          </svg>
                        </div>
                        <h3>Delete</h3>
                        <!-- <p>Bạn có chắc chắn muốn gửi yêu cầu xóa món ăn này?</p> -->
                        <p class="data-name-delete"></p>
                        <p>Bạn có chắc chắn muốn gửi yêu cầu xóa ?</p>
                        <div class="custom-modal-actions">
                          <button onclick="hideFormModal1()" class="custom-btn custom-btn-cancel"
                            id="cancelDeleteBtn">Cancel</button>
                          <button onclick="hideFormModal2()" class="custom-btn custom-btn-confirm"
                            id="confirmDeleteBtn">Confirm</button>
                        </div>
                      </div>
                    </div>
                    <!-- /Thêm modal xác nhận xóa -->
                    <!-- product-list -->
                    <div class="wg-box">
                      <div class="title-box">
                        <i class="icon-coffee"></i>
                        <div class="body-text">Tip search by Product ID: Each product is provided with a unique ID,
                          which
                          you can rely on to find the exact product you need.</div>
                      </div>
                      <div class="flex items-center justify-between gap10 flex-wrap">
                        <div class="wg-filter flex-grow">
                          <div class="show">
                            <div class="text-tiny">Showing</div>
                            <div class="select">
                              <select class="">
                                <option>10</option>
                                <option>20</option>
                                <option>30</option>
                              </select>
                            </div>
                            <div class="text-tiny">entries</div>
                          </div>
                          <form class="form-search">
                            <fieldset class="name">
                              <input type="text" placeholder="Search here..." class="" name="name" tabindex="2" value=""
                                aria-required="true" required="">
                            </fieldset>
                            <div class="button-submit">
                              <button class="" type="submit"><i class="icon-search"></i></button>
                            </div>
                          </form>
                        </div>
                        <a class="tf-button style-1 w208"
                          href="${pageContext.request.contextPath}/manage-food?action=add"><i class="icon-plus"></i>Add
                          new</a>
                      </div>
                      <div class="wg-table table-product-list">
                        <ul class="table-title flex gap20 mb-14">
                          <li>
                            <div class="body-title">Food</div>
                          </li>
                          <li>
                            <div class="body-title">Product ID</div>
                          </li>
                          <li>
                            <div class="body-title">Category</div>
                          </li>
                          <li>
                            <div class="body-title">Price</div>
                          </li>
                          <li>
                            <div class="body-title">Status</div>
                          </li>
                          <li>
                            <div class="body-title">Created date</div>
                          </li>
                          <li>
                            <div class="body-title">Updated date</div>
                          </li>
                          <li>
                            <div class="body-title">Action</div>
                          </li>
                        </ul>
                        <ul class="flex flex-column">
                          <c:forEach items="${listFood}" var="item">
                            <li class="product-item gap14">
                              <div class="image no-bg">
                                <img src=${item.getImage_url()} alt="">
                              </div>
                              <div class="flex items-center justify-between gap20 flex-grow">
                                <div class="name">
                                  <a href="product-list.html" class="body-title-2">${item.getName()}</a>
                                </div>
                                <div class="body-text">#0000${item.getId()}</div>
                                <div class="body-text">${item.getCategory_id()}</div>
                                <div class="body-text">${item.getPrice()}</div>
                                <div class="body-text">${item.getStatus()}</div>
                                <div class="body-text">${item.getCreated_at()}</div>
                                <div class="body-text">${item.getUpdated_at()}</div>
                                <div class="list-icon-function">
                                  <div class="item eye">
                                    <a
                                      href="${pageContext.request.contextPath}/manage-food?action=viewDetail&id=${item.id}">
                                      <i class="icon-eye" style="color: blue"></i>
                                    </a>

                                  </div>
                                  <div class="item edit">
                                    <a
                                      href="${pageContext.request.contextPath}/manage-food?action=update&id=${item.id}">
                                      <i class="icon-edit-3" style="color: green"></i>
                                    </a>
                                  </div>
                                  <div class="item delete" id="item-delete">
                                    <i class="icon-trash-2 item-trash" style="color: red;" data-id="${item.getId()}"
                                      data-name="${item.getName()}" onclick="showModalForm(event)"></i>
                                  </div>
                                </div>
                              </div>
                            </li>
                          </c:forEach>

                        </ul>
                      </div>
                      <div class="divider"></div>
                      <div class="flex items-center justify-between flex-wrap gap10">
                        <div class="text-tiny">Showing 10 entries</div>
                        <ul class="wg-pagination">
                          <li>
                            <a href="#"><i class="icon-chevron-left"></i></a>
                          </li>
                          <li>
                            <a href="#">1</a>
                          </li>
                          <li class="active">
                            <a href="#">2</a>
                          </li>
                          <li>
                            <a href="#">3</a>
                          </li>
                          <li>
                            <a href="#"><i class="icon-chevron-right"></i></a>
                          </li>
                        </ul>
                      </div>
                    </div>
                    <!-- /product-list -->
                  </div>
                  <!-- /main-content-wrap -->
                </div>
                <!-- /main-content-wrap -->
                <!-- bottom-page -->
                <div class="bottom-page">
                  <div class="body-text">Copyright © 2024 Remos. Design with</div>
                  <i class="icon-heart"></i>
                  <div class="body-text">by <a href="https://themeforest.net/user/themesflat/portfolio">Themesflat</a>
                    All
                    rights reserved.</div>
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

      <style>
        .item-delete {
          font-size: 20px;
        }

        .custom-modal-overlay {
          position: fixed;
          top: 0;
          left: 0;
          right: 0;
          bottom: 0;
          background: rgba(0, 0, 0, 0.2);
          display: flex;
          align-items: center;
          justify-content: center;
          z-index: 9999;
        }

        .custom-modal {
          background: #fff;
          border-radius: 16px;
          padding: 32px 24px 24px 24px;
          box-shadow: 0 2px 24px rgba(0, 0, 0, 0.08);
          min-width: 320px;
          max-width: 90vw;
          text-align: center;
          position: relative;
        }

        .custom-modal-icon {
          margin-bottom: 12px;
        }

        .custom-modal-close {
          position: absolute;
          top: 12px;
          right: 12px;
          background: none;
          border: none;
          font-size: 24px;
          cursor: pointer;
        }

        .custom-modal-actions {
          display: flex;
          justify-content: center;
          gap: 16px;
          margin-top: 24px;
        }

        .custom-btn {
          padding: 8px 24px;
          border-radius: 8px;
          border: none;
          font-size: 16px;
          cursor: pointer;
        }

        .custom-btn-cancel {
          background: #fff;
          color: #333;
          border: 1px solid #ddd;
        }

        .custom-btn-confirm {
          background: #EF4444;
          color: #fff;
        }
      </style>
      <script>
        const showModalForm = (event) => {
          //Hiện form modal
          const modal = document.getElementById('customDeleteModal');
          modal.style.display = 'flex';
          //lấy thông tin
          const target = event.target;
          const foodId = target.dataset.id;
          const foodName = target.dataset.name
          //Tạo form ảo gửi lên controller
          const form = document.createElement('form');
          form.id = "formDelete";
          form.method = 'POST';
          form.action = '${pageContext.request.contextPath}/manage-food?action=delete&id=' + foodId;
          //Tạo input ẩn chứa id
          const inputId = document.createElement('input');
          inputId.type = 'hidden';
          inputId.name = 'id';
          inputId.value = foodId;
          form.appendChild(inputId);
          //Thêm form vào body
          document.body.appendChild(form);
        }

        const hideFormModal = () => {
          const modal = document.getElementById('customDeleteModal');
          modal.style.display = 'none';
        }

        const hideFormModal1 = () => {
          const formDelete = document.getElementById("formDelete")
          if (formDelete) {
            //Xóa form
            document.body.removeChild(formDelete);
            //Ẩn form
            hideFormModal();
          }
          else {
            alert("Form not found");
          }
        }

        const hideFormModal2 = () => {
          const formDelete = document.getElementById("formDelete")
          if (formDelete) {
            //Gửi form
            formDelete.submit();
            //Xóa form
            document.body.removeChild(formDelete);
            //Ẩn form
            hideFormModal();
          }
          else {
            alert("Form not found");
          }
        }


      </script>



    </body>


    <!-- Mirrored from themesflat.co/html/remos/product-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:40 GMT -->

    </html>