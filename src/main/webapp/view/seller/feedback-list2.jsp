<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Management</title>
    <!-- Common Styles -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_1.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon_1.png">
    
    <style>
        body {
            background-color: #f4f7fa;
        }
        .main-content-wrap {
            padding: 25px;
        }
        .wg-box {
            background: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            margin-bottom: 25px;
        }
        .filter-row {
            display: flex;
            gap: 15px;
            align-items: center;
            flex-wrap: wrap;
        }
        .feedback-table {
            width: 100%;
            border-collapse: collapse;
        }
        .feedback-table th, .feedback-table td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #e0e0e0;
        }
        .feedback-table th {
            background-color: #f8f9fa;
            font-weight: 600;
        }
        .fa-star { color: #ffc107; }
        .status-badge {
            padding: 5px 12px;
            border-radius: 15px;
            font-size: 13px;
            font-weight: 500;
        }
        .status-badge.active { background-color: #e7f7ef; color: #0a6847; }
        .status-badge.inactive { background-color: #fde8e8; color: #d9534f; }
        .action-group { display: flex; gap: 10px; align-items: center; }
        .switch {
            position: relative;
            display: inline-block;
            width: 44px;
            height: 24px;
        }
        .switch input { display: none; }
        .slider {
            position: absolute;
            cursor: pointer;
            top: 0; left: 0; right: 0; bottom: 0;
            background-color: #ccc;
            transition: .4s;
            border-radius: 24px;
        }
        .slider:before {
            position: absolute;
            content: "";
            height: 18px; width: 18px;
            left: 3px; bottom: 3px;
            background-color: white;
            transition: .4s;
            border-radius: 50%;
        }
        input:checked + .slider { background-color: #28a745; }
        input:checked + .slider:before { transform: translateX(20px); }
    </style>
</head>
<body class="body">
    <div id="wrapper">
        <div id="page" class="">
            <div class="layout-wrap">
                <jsp:include page="./../common/sidebar.jsp" />
                <div class="section-content-right">
                    <jsp:include page="./../common/headerDashboard.jsp" />
                    <div class="main-content">
                        <div class="main-content-inner">
                            <div class="main-content-wrap">
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                    <h3>Feedback List</h3>
                                </div>

                                <!-- Filter Form -->
                                <div class="wg-box">
                                    <form action="<c:url value='/seller/feedback'/>" method="GET" class="form-search">
                                        <div class="filter-row">
                                            <select name="sort" class="form-control">
                                                <option value="" ${empty param.sort ? 'selected' : ''}>Sort by ID</option>
                                                <option value="ASC" ${param.sort == 'ASC' ? 'selected' : ''}>Ascending</option>
                                                <option value="DESC" ${param.sort == 'DESC' ? 'selected' : ''}>Descending</option>
                                            </select>
                                            <select name="selectFood" class="form-control">
                                                <option value="" ${empty param.selectFood ? 'selected' : ''}>All Foods</option>
                                                <c:forEach items="${lFood}" var="f">
                                                    <option value="${f}" ${param.selectFood == f ? 'selected' : ''}>${f}</option>
                                                </c:forEach>
                                            </select>
                                            <select name="rating" class="form-control">
                                                <option value="" ${empty param.rating ? 'selected' : ''}>All Ratings</option>
                                                <c:forEach var="i" begin="1" end="5">
                                                    <option value="${i}" ${param.rating == i ? 'selected' : ''}>${i} Star(s)</option>
                                                </c:forEach>
                                            </select>
                                            <input type="text" class="form-control flex-grow-1" name="search" placeholder="Search by customer name or email..." value="${search}">
                                            <button type="submit" class="btn btn-primary">Filter</button>
                                        </div>
                                    </form>
                                </div>

                                <!-- Feedback Table -->
                                <div class="wg-box">
                                    <table class="feedback-table">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Food</th>
                                                <th>Rating</th>
                                                <th style="width: 30%;">Content</th>
                                                <th>Customer</th>
                                                <th>Status</th>
                                                <th>Created At</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:if test="${empty feedbacks}">
                                                <tr>
                                                    <td colspan="8" class="text-center py-5">No feedback found.</td>
                                                </tr>
                                            </c:if>
                                            <c:forEach items="${feedbacks}" var="feedback">
                                                <c:set var="account" value="${AccountMap[feedback.user_id]}" />
                                                <c:set var="food" value="${FoodMap[feedback.order_item_id]}" />
                                                <tr>
                                                    <td>${feedback.id}</td>
                                                    <td>${food.name}</td>
                                                    <td>
                                                        <c:forEach begin="1" end="${feedback.rating}"><i class="fa-solid fa-star"></i></c:forEach>
                                                    </td>
                                                    <td style="white-space: normal;">${feedback.content}</td>
                                                    <td>${account.user_name}</td>
                                                    <td>
                                                        <span class="status-badge ${feedback.visible ? 'active' : 'inactive'}">
                                                            ${feedback.visible ? 'Active' : 'Inactive'}
                                                        </span>
                                                    </td>
                                                    <td><fmt:formatDate value="${feedback.createdAt}" pattern="dd/MM/yyyy HH:mm" /></td>
                                                    <td>
                                                        <div class="action-group">
                                                            <c:url var="viewUrl" value="/seller/feedback">
                                                                <c:param name="action" value="view"/>
                                                                <c:param name="feedbackId" value="${feedback.id}"/>
                                                            </c:url>
                                                            <a href="${viewUrl}" class="btn btn-sm btn-info" title="View Detail"><i class="fa-solid fa-eye"></i></a>
                                                            
                                                            <label class="switch">
                                                                <c:url var="updateUrl" value="/seller/feedback">
                                                                    <c:param name="action" value="update"/>
                                                                    <c:param name="feedbackId" value="${feedback.id}"/>
                                                                </c:url>
                                                                <input type="checkbox" onchange="handleVisibilityToggle(this, '${updateUrl}')" <c:if test="${feedback.visible}">checked</c:if>>
                                                                <span class="slider"></span>
                                                            </label>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>

                                <!-- Pagination -->
                                <div class="flex items-center justify-between flex-wrap gap10">
                                    <div class="text-tiny">Showing ${feedbacks.size()} of ${totalFeedback} entries</div>
                                    <ul class="wg-pagination">
                                        <c:if test="${currentPage > 1}">
                                            <li>
                                                <c:url value="/seller/feedback" var="prevUrl">
                                                    <c:param name="page" value="${currentPage - 1}"/><c:param name="rating" value="${param.rating}"/><c:param name="search" value="${param.search}"/><c:param name="selectFood" value="${param.selectFood}"/><c:param name="sort" value="${param.sort}"/>
                                                </c:url>
                                                <a href="${prevUrl}"><i class="icon-chevron-left"></i></a>
                                            </li>
                                        </c:if>
                                        <c:forEach begin="1" end="${totalPages}" var="i">
                                            <li class="${currentPage == i ? 'active' : ''}">
                                                <c:url value="/seller/feedback" var="pageUrl">
                                                    <c:param name="page" value="${i}"/><c:param name="rating" value="${param.rating}"/><c:param name="search" value="${param.search}"/><c:param name="selectFood" value="${param.selectFood}"/><c:param name="sort" value="${param.sort}"/>
                                                </c:url>
                                                <a href="${pageUrl}">${i}</a>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${currentPage < totalPages}">
                                            <li>
                                                <c:url value="/seller/feedback" var="nextUrl">
                                                    <c:param name="page" value="${currentPage + 1}"/><c:param name="rating" value="${param.rating}"/><c:param name="search" value="${param.search}"/><c:param name="selectFood" value="${param.selectFood}"/><c:param name="sort" value="${param.sort}"/>
                                                </c:url>
                                                <a href="${nextUrl}"><i class="icon-chevron-right"></i></a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <jsp:include page="./../common/footer.jsp" />
                </div>
            </div>
        </div>
    </div>

    <!-- JavaScript -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>

    <c:if test="${not empty sessionScope.isSuccess}">
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                iziToast.success({
                    title: "Success",
                    message: "Operation completed successfully!",
                    position: 'topRight',
                    timeout: 5000
                });
            });
        </script>
        <% session.removeAttribute("isSuccess"); %>
    </c:if>
    <c:if test="${not empty sessionScope.isError}">
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                iziToast.error({
                    title: "Error",
                    message: "An error occurred. Please try again.",
                    position: 'topRight',
                    timeout: 5000
                });
            });
        </script>
        <% session.removeAttribute("isError"); %>
    </c:if>

    <script>
        function handleVisibilityToggle(checkbox, url) {
            const isChecked = checkbox.checked;
            const actionText = isChecked ? "make it visible" : "hide it";
            
            Swal.fire({
                title: 'Are you sure?',
                text: `Do you want to ${actionText}?`,
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, change it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = url;
                } else {
                    // Revert the checkbox state if the user cancels
                    checkbox.checked = !isChecked;
                }
            });
        }
    </script>
</body>
</html>
