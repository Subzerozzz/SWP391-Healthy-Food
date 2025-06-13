<%-- 
    Document   : try
    Created on : Jun 12, 2025, 8:24:23 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <style>
            .fixed-width-btn {
                min-width: 120px;
                text-align: center;
            }
            .view-btn {
                display: inline-flex;
                align-items: center;
                gap: 8px;
                padding: 8px 16px;
                background: linear-gradient(135deg, #4e73df 0%, #3a54c4 100%);
                color: white;
                border-radius: 50px;
                font-size: 14px;
                font-weight: 500;
                text-decoration: none;
                transition: all 0.3s ease;
                box-shadow: 0 2px 10px rgba(78, 115, 223, 0.2);
            }
            
            .view-btn:hover {
                transform: translateY(-2px);
                box-shadow: 0 5px 15px rgba(78, 115, 223, 0.4);
                color: white;
            }
            
            .view-btn:active {
                transform: translateY(0);
                box-shadow: 0 2px 5px rgba(78, 115, 223, 0.3);
            }
            
            .view-icon {
                display: flex;
                align-items: center;
                justify-content: center;
                background-color: rgba(255, 255, 255, 0.2);
                border-radius: 50%;
                width: 24px;
                height: 24px;
                transition: all 0.3s ease;
            }
            
            .view-btn:hover .view-icon {
                background-color: rgba(255, 255, 255, 0.3);
                transform: rotate(15deg);
            }
            
            .view-text {
                transition: all 0.3s ease;
            }
            
            .view-btn:hover .view-text {
                transform: translateX(2px);
            }
            
            /* Responsive adjustments */
            @media (max-width: 768px) {
                .view-btn {
                    padding: 6px 12px;
                    font-size: 13px;
                }
                
                .view-icon {
                    width: 20px;
                    height: 20px;
                }
            }
        </style>
    </head>
    <body>
         <div class="dashboard-main-body">
            <div class="d-flex flex-wrap align-items-center justify-content-between gap-3 mb-24">
                <h6 class="fw-semibold mb-0">Order Management</h6>
                
            </div>

            <!-- Filter Section -->
            <div class="card mb-24">
                <div class="card-body p-24">
                    <form action="${pageContext.request.contextPath}/admin/manage-order" method="GET">
                        <div class="row g-3">
                            <div class="col-md-3">
                                <select class="form-select" name="status">
                                    <option value="">All Status</option>
                                    <option value="pending" ${status == 'pending' ? 'selected' : ''}>Pending</option>
                                    <option value="accepted" ${status == 'accepted' ? 'selected' : ''}>Accepted</option>
                                    <option value="completed" ${status == 'completed' ? 'selected' : ''}>Completed</option>
                                    <option value="cancelled" ${status == 'cancelled' ? 'selected' : ''}>Cancelled</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <select class="form-select" name="paymentMethod">
                                    <option value="">All Payment Methods</option>
                                    <option value="Cash on Delivery" ${paymentMethod == 'Cash on Delivery' ? 'selected' : ''}>Cash on Delivery</option>
                                    <option value="Digital Wallet" ${paymentMethod == 'Digital Wallet' ? 'selected' : ''}>Digital Wallet</option>
                                    <option value="Bank Transfer" ${paymentMethod == 'Bank Transfer' ? 'selected' : ''}>Bank Transfer</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input type="text" class="form-control" name="search" placeholder="Search by order ID, customer name, email..."
                                       value="${search}">
                            </div>
                            <div class="col-md-2">
                                <button type="submit" class="btn btn-primary w-100">Filter</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Orders Table -->
            <div class="card">
                <div class="card-body p-24">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Customer</th>
                                    <th>Address</th>
                                    <th>Total</th>
                                    <th>Payment Method</th>
                                    <th>Status</th>
                                    <th>Created Date</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                   <c:if test="${empty orders}">
                                    <tr>
                                        <td colspan="8" class="text-center">
                                            <div class="py-4">
                                                <i class="fas fa-search fs-1 text-muted mb-3"></i>
                                                <h5>No orders found</h5>
                                                <p class="text-muted">
                                                    <c:choose>
                                                        <c:when test="${not empty status || not empty search}">
                                                            No orders match your search criteria. Try adjusting your filters.
                                                            <br>
                                                            <a href="${pageContext.request.contextPath}/admin/manage-order" class="btn btn-outline-primary mt-2">
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
                                </c:if>
                                <c:if test="${empty orders}">
                                    <tr>
                                        <td colspan="8" class="text-center">
                                            <div class="py-4">
                                                <i class="fas fa-search fs-1 text-muted mb-3"></i>
                                                <h5>No orders found</h5>
                                                <p class="text-muted">
                                                    <c:choose>
                                                        <c:when test="${not empty status || not empty search}">
                                                            No orders match your search criteria. Try adjusting your filters.
                                                            <br>
                                                            <a href="${pageContext.request.contextPath}/admin/manage-order" class="btn btn-outline-primary mt-2">
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
                                </c:if>
                                <c:forEach var="order" items="${orders}">
                                    <tr>
                                        <td>#${order.orderId}</td>
                                        <td>
                                            ${order.username}<br>
                                            <small class="text-muted">${order.email}</small><br>
                                            <small class="text-muted">${order.phone}</small>
                                        </td>
                                        <td>${order.shippingAddress}</td>
                                        <td><fmt:formatNumber value="${order.total}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ</td>
                                        <td>
                                            <span class="badge bg-info">
                                                ${order.paymentMethod}
                                            </span>
                                        </td>
                                        <td>
                                            <span class="badge ${order.status == 'pending' ? 'bg-warning' : 
                                                                order.status == 'accepted' ? 'bg-info' : 
                                                                order.status == 'completed' ? 'bg-success' : 'bg-danger'}">
                                                ${order.status}
                                            </span>
                                        </td>
                                        <td><fmt:formatDate value="${order.createdAt}" pattern="dd/MM/yyyy HH:mm"/></td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/admin/manage-order?action=view&id=${order.orderId}"
                                               class="view-btn">
                                                <span class="view-icon">
                                                    <iconify-icon icon="heroicons:eye" width="20" height="20"></iconify-icon>
                                                </span>
                                                <span class="view-text">View Details</span>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
    </body>
</html>
