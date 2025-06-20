<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

       <!--IzizToast-->
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
       <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>
      <!-- Font -->
      <link rel="stylesheet" href="${pageContext.request.contextPath}/font/fonts.css">

      <!-- Icon -->
      <link rel="stylesheet" href="${pageContext.request.contextPath}/icon/style.css">

      <!-- Favicon and Touch Icons  -->
      <link rel="shortcut icon"
        href="${pageContext.request.contextPath}/images/favicon_1.png">
      <link rel="apple-touch-icon-precomposed"
        href="${pageContext.request.contextPath}/images/favicon_1.png">

    </head>
        
        <style>
            .form-search{
                display: flex;
                gap: 20px 
            }
            
            .filter-btn{
                width: 200px;
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
                  <div class="main-content-wrap">
                    <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                      <h3>Food List</h3>
                    <c:if test="${empty listFood}">
                        <div>1</div>
                    </c:if>
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
                      <div class="flex items-center justify-between gap10 flex-wrap">
                        <div class="wg-filter flex-grow">     
                          <!--Filter channing-->
                          <form style="width: 500px" class="form-search" action="${pageContext.request.contextPath}/manage-food" method="GET">
                             
                              <input type="hidden" name="action" value="searchFilter">
                              <!-- FILTER BY CATEGORY -->
                              <div class="filter-dropdown">
                                <button class="filter-btn" type="button" id="filterToggle">
                                  <span class="filter-icon">
                                    <i class="fa-solid fa-filter"></i>
                                  </span>
                                  <span>Filter by category</span>
                                </button>

                                <div class="filter-select-wrap" id="filterSelectWrap">
                                  <select class="filter-select" name="categoryID" onchange="filterByCategory(this)">
                                    <option value="" disabled selected>-- Chọn danh mục --</option>
                                    <option value="${0}" ${categoryID == 0 ? 'selected' : ''}>Tất cả</option>
                                    <c:forEach items="${listCategory}" var="item">
                                      <option value="${item.getId()}" ${item.getId()==categoryID? 'selected' : '' }>
                                        ${item.getName()}
                                      </option>
                                    </c:forEach>
                                  </select>
                                </div>
                              </div>

                              <!-- SEARCH BY NAME -->
                              <fieldset style="flex:1" class="name">
                                  <input type="text" placeholder="Search by food name" name="name"
                                  value="${not empty foodName ? foodName : ''}" required="">
                              </fieldset>

                              <div class="button-submit">
                                  <button type="submit"><i class="icon-search"></i></button>
                              </div>
                            </form>

                        
                                
                        <!--Add new food--> 
                        </div>
                            <a class="tf-button style-1 w208"
                              href="${pageContext.request.contextPath}/manage-food?action=add"><i class="icon-plus"></i>Add
                              new
                            </a>
                        </div>
                      <div class="wg-table table-product-list">
                          <c:choose>
                              <c:when test="${listFood.size() == 0}">
                                  <tr>
                                    <td colspan="8" class="text-center">
                                        <div class="py-4">
                                            <h5>No Food Draft Request found</h5>
                                            <p class="text-muted">
                                                <c:choose>
                                                    <c:when test="${not empty param.name}">
                                                        No Food Draft Request match your search criteria. Try adjusting your filters.
                                                        <br>
                                                            <a href="${pageContext.request.contextPath}/manage-food" class="btn btn-outline-primary mt-2">
                                                                <i class="fas fa-times me-2"></i>Clear Filters
                                                            </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        There are no orders in the system yet.
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                    </td>
                                </tr>
                              </c:when>
                              <c:otherwise>
                                  <ul class="table-title flex gap20 mb-14">
                                    <li>
                                      <div class="body-title">Food Name</div>
                                    </li>
                                    <li>
                                      <div class="body-title">Calo</div>
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
                                                <img src="${item.getImage_url()}" alt="">
                                            </div>
                                            <div class="flex items-center justify-between gap20 flex-grow">
                                              <div class="name">
                                                <a href="product-list.html" class="body-title-2">${item.getName()}</a>
                                              </div>
                                              <div class="body-text">${item.getCalo()}</div>
                                              <div class="body-text">
                                                <c:forEach items="${listCategory}" var="itemCategory">
                                                  <c:if test="${itemCategory.getId() == item.getCategory_id()}">
                                                    ${itemCategory.getName()}
                                                  </c:if>
                                                </c:forEach>
                                              </div>
                                              <div class="body-text">
                                                  <fmt:formatNumber value="${item.getPrice()}" type="number" groupingUsed="true" maxFractionDigits="0" /> VNĐ
                                              </div>
                                              <c:choose>
                                                  <c:when test="${item.getStatus() == 'inactive'}">
                                                      <div class="body-text food-inactive" >${item.getStatus()}</div>
                                                  </c:when>
                                                  <c:otherwise>
                                                      <div class="body-text food-active" >${item.getStatus()}</div>
                                                  </c:otherwise>
                                              </c:choose>

                                              <div class="body-text">
                                                  <fmt:formatDate value="${item.getCreated_at()}" pattern="yyyy-MM-dd HH:mm:ss" />
                                              </div>
                                              <div class="body-text">
                                                  <fmt:formatDate value="${item.getUpdated_at()}" pattern="yyyy-MM-dd HH:mm:ss" />
                                              </div>
                                              <div class="list-icon-function">
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
                              </c:otherwise>
                          </c:choose>
                        
                      </div>
                      <div class="divider"></div>
                      <div class="flex items-center justify-between flex-wrap gap10">
                        <div class="text-tiny">Showing 10 entries</div>
                        <ul class="wg-pagination">
                          
                          <li>
                            <a href="${pageContext.request.contextPath}/manage-food?action=searchFilter&name=${foodName}&cateoryID=${categoryID}&page=1"><i class="icon-chevron-left"></i></a>
                          </li>
                            <c:choose>
                                <c:when test="${currentPage <= totalPage - 2}">
                                    <c:if test="${currentPage > 1}">
                                         <li class="">
                                            <a href="${pageContext.request.contextPath}/manage-food?action=searchFilter&name=${foodName}&cateoryID=${categoryID}&page=${currentPage - 1}">${currentPage - 1}</a>
                                        </li>
                                    </c:if>
                                    <li class="active">
                                        <a href="${pageContext.request.contextPath}/manage-food?action=searchFilter&name=${foodName}&cateoryID=${categoryID}&page=${currentPage}">${currentPage}</a>
                                    </li>
                                    
                                    <li class="">
                                        <a href="${pageContext.request.contextPath}/manage-food?action=searchFilter&name=${foodName}&cateoryID=${categoryID}&page=${currentPage + 1}">${currentPage + 1}</a>
                                    </li>
                                    
                                    <c:if test="${currentPage < totalPage - 2}">
                                        <li>
                                            <span>...</span>
                                        </li>
                                    </c:if>
                                    
                                    
                                    <li class="">
                                        <a href="${pageContext.request.contextPath}/manage-food?action=searchFilter&name=${foodName}&cateoryID=${categoryID}&page=${totalPage}">${totalPage}</a>
                                    </li>
                                </c:when>
                                
                                <c:otherwise>
                                    <c:forEach begin="${totalPage-2 <= 0 ? 1 : totalPage - 2}" end="${totalPage}" var="i">
                                        <li class="${currentPage == i ? 'active' : ''}">
                                            <a href="${pageContext.request.contextPath}/manage-food?action=searchFilter&name=${foodName}&cateoryID=${categoryID}&page=${i}">${i}</a>
                                        </li>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                            
                          <li>
                            <a href="${pageContext.request.contextPath}/manage-food?action=searchFilter&name=${foodName}&cateoryID=${categoryID}&page=${totalPage}"><i class="icon-chevron-right"></i></a>
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
      <!-- /#wrapper -->

      <!-- Javascript -->
      <script src="${pageContext.request.contextPath}/js/jquery.min_1.js"></script>
      <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/zoom.js"></script>
      <script src="${pageContext.request.contextPath}/js/switcher.js"></script>
      <script src="${pageContext.request.contextPath}/js/theme-settings.js"></script>
      <script src="${pageContext.request.contextPath}/js/main.js"></script>
      
      <!--Style-->
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

        /* filter  */
        .filter-dropdown {
          position: relative;
          display: flex;
        }

        .filter-btn {
          display: flex;
          align-items: center;
          gap: 6px;
          border: 1.5px solid #95989D;
          background: #fff;
          color: #95989D;
          border-radius: 22px;
          padding: 10px 20px;
          font-weight: 500;
          font-size: 15px;
          cursor: pointer;
          transition: background 0.2s, color 0.2s;
        }

        .filter-btn:hover {
          background: #e3f2fd;
        }

        .filter-icon {
          display: flex;
          align-items: center;
        }

        .filter-select-wrap {
          display: none;
          position: absolute;
          top: 110%;
          left: 0;
          background: #fff;
          border: 1px solid #e0e0e0;
          border-radius: 8px;
          box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
          padding: 8px 12px;
          z-index: 10;
        }

        .filter-select {
          min-width: 140px;
          padding: 6px 10px;
          border-radius: 4px;
          border: 1px solid #bdbdbd;
          font-size: 15px;
        }

        .filter-dropdown.active .filter-select-wrap {
          display: block;
        }
        
        /*food-inactive*/
        .food-inactive{
            background: #DC143C;
            color: white !important;
            text-align: center;
            border-radius: 10px
        }
        .food-active{
            background: #649F67;
            color: white !important;
            text-align: center;
            border-radius: 10px
        }
      </style>
      <!--Script-->
      <script>
        const showModalForm = (event) => {
          //Hiện form modal
          const modal = document.getElementById('customDeleteModal');
          modal.style.display = 'flex';
          //lấy thông tin
          const target = event.target;
          const foodId = target.dataset.id;
          console.log(foodId);
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
            //Ẩn modal
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
            //Ẩn modal
            hideFormModal();
            
          }
          else {
            alert("Form not found");
          }
        }
        
        // Filter 
        // Hiển thị select khi bấm nút Filter
        const filterDropdown = document.querySelector('.filter-dropdown');
        const filterToggle = document.getElementById('filterToggle');
        filterToggle.addEventListener('click', function (e) {
          filterDropdown.classList.toggle('active');
        });
        // Ẩn select khi click ra ngoài
        window.addEventListener('click', function (e) {
          if (!filterDropdown.contains(e.target)) {
            filterDropdown.classList.remove('active');
          }
        });
        
        const filterByCategory = (e) => {
             const form = e.closest("form"); // lấy thẻ form bao quanh
            form.submit(); // submit form (có cả name và categoryID)

 
          };
               
         
             
        // End Filter 
      </script>
        
        <!--Thông báo xóa--> 
        <c:if test="${isDelete == true}">
            <script>
              document.addEventListener("DOMContentLoaded", function () {
                iziToast.error({
                    title: "Thông báo",
                    message: "Yêu cầu xóa món ăn của bạn đã được gửi đi",
                    position: 'topRight',
                    timeout: 5000,
                    backgroundColor:"#d4edda"
                    });
              });
            </script>
            <!--Xóa đi biến isDelete sau khi đã thông báo--> 
            <%
                session.removeAttribute("isDelete");
            %>
          </c:if>
        
        <!--Thông báo add-->
        <c:if test="${isAdd == true}">
            <script>
              document.addEventListener("DOMContentLoaded", function () {
                iziToast.error({
                    title: "Thông báo",
                    message: "Yêu cầu tạo món ăn của bạn đã được gửi đi",
                    position: 'topRight',
                    timeout: 5000,
                    backgroundColor:"#d4edda"
                    });
              });
            </script>
            <!--Xóa đi biến isAdd sau khi đã thông báo--> 
            <%
                session.removeAttribute("isAdd");
            %>
          </c:if>
            
        <!--Thông báo update--> 

        <c:if test="${isUpdate == true}">
         <script>
           document.addEventListener("DOMContentLoaded", function () {
             iziToast.error({
                 title: "Thông báo",
                 message: "Yêu cầu chỉnh sửa món ăn của bạn đã được gửi đi",
                 position: 'topRight',
                 timeout: 5000,
                 backgroundColor:"#d4edda"
                 });
           });
         </script>
         <!--Xóa đi biến isAdd sau khi đã thông báo--> 
         <%
             session.removeAttribute("isUpdate");
         %>
       </c:if>


    </body>


    <!-- Mirrored from themesflat.co/html/remos/product-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:40 GMT -->

    </html>