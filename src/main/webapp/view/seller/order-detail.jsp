<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
       <div class="d-flex flex-wrap align-items-center justify-content-between gap-3 mb-24">
                <h6 class="fw-semibold mb-0">Order Details #${order.orderId}</h6>
                
            </div>

            <div class="row g-24">
                <!-- Order Information -->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0">Order Information</h6>
                        </div>
                        <div class="card-body">
                            <div class="mb-3">
                                <strong>Order ID:</strong> #${order.orderId}
                            </div>
                            <div class="mb-3">
                                <strong>Order Date:</strong> <fmt:formatDate value="${order.createdAt}" pattern="dd/MM/yyyy HH:mm"/>
                            </div>
                            <div class="mb-3">
                                <strong>Payment Method:</strong> ${order.paymentMethod}
                            </div>
                            <div class="mb-3">
                                <strong>Status:</strong>
                                <span class="badge ${order.status == 'pending' ? 'bg-warning' : 
                                                    order.status == 'accepted' ? 'bg-info' : 
                                                    order.status == 'completed' ? 'bg-success' : 'bg-danger'}">
                                    ${order.status}
                                </span>
                            </div>
                            <div class="mb-3">
                                <strong>Total Amount:</strong> <fmt:formatNumber value="${order.total}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ
                            </div>
                            <c:if test="${not empty order.couponCode}">
                                <div class="mb-3">
                                    <strong>Coupon Applied:</strong> ${order.couponCode}
                                </div>
                                <div class="mb-3">
                                    <strong>Discount Amount:</strong> <fmt:formatNumber value="${order.discountAmount}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ
                                </div>
                                <div class="mb-3">
                                    <strong>Original Amount:</strong> <fmt:formatNumber value="${order.total.add(order.discountAmount)}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>

                <!-- Customer Information -->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0">Customer Information</h6>
                        </div>
                        <div class="card-body">
                            <div class="mb-3">
                                <strong>Name:</strong> ${order.username}
                            </div>
                            <div class="mb-3">
                                <strong>Email:</strong> ${order.email}
                            </div>
                            <div class="mb-3">
                                <strong>Mobie:</strong> ${order.mobie}
                            </div>
                            <div class="mb-3">
                                <strong>Shipping Address:</strong><br>
                                ${order.shippingAddress}
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Update Status -->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0">Status Information</h6>
                        </div>
                        <div class="card-body">
                            <c:choose>
                                <%-- For completed or cancelled orders - Read only view --%>
                                <c:when test="${order.status == 'completed' || order.status == 'cancelled'}">
                                    <div class="mb-3">
                                        <label class="form-label">Current Status</label>
                                        <div class="d-flex align-items-center">
                                            <span class="badge ${order.status == 'completed' ? 'bg-success' : 'bg-danger'} fs-6">
                                                ${order.status}
                                            </span>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <small class="text-muted">
                                            <i class="fas fa-info-circle me-1"></i>
                                            This order has been ${order.status} and cannot be modified further.
                                        </small>
                                    </div>
                                    <div>
                                        <a href="${pageContext.request.contextPath}/admin/manage-order" 
                                           class="btn btn-secondary">
                                            Back to Order List
                                        </a>
                                    </div>
                                </c:when>
                                
                                <%-- For pending or accepted orders - Allow updates --%>
                                <c:otherwise>
                                    <form action="${pageContext.request.contextPath}/admin/manage-order" method="post">
                                        <input type="hidden" name="action" value="updateStatus">
                                        <input type="hidden" name="orderId" value="${order.orderId}">
                                        
                                        <div class="mb-3">
                                            <label class="form-label">New Status</label>
                                            <select class="form-select" name="newStatus" required>
                                                <option value="">-- Select Status --</option>
                                                <c:if test="${order.status == 'pending'}">
                                                    <option value="accepted">Accept Order</option>
                                                    <option value="cancelled">Reject Order</option>
                                                </c:if>
                                                <c:if test="${order.status == 'accepted'}">
                                                    <option value="completed">Complete Order</option>
                                                    <option value="cancelled">Cancel Order</option>
                                                </c:if>
                                            </select>
                                        </div>
                                        
                                        <div class="mb-3">
                                            <label class="form-label">Note</label>
                                            <textarea class="form-control" name="note" rows="3" 
                                                      placeholder="Enter note..."></textarea>
                                        </div>
                                        
                                        <div class="d-flex gap-2">
                                            <button type="submit" class="btn btn-primary">Update</button>
                                            <a href="${pageContext.request.contextPath}/admin/manage-order" 
                                               class="btn btn-secondary">Back</a>
                                        </div>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Order Items -->
            <div class="card mt-24">
                <div class="card-header">
                    <h6 class="card-title mb-0">Order Items</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Image</th>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Subtotal</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${order.orderItems}">
                                    <tr>
                                        <td>
                                            <img src="${item.productImage}" alt="${item.foodName}" class="product-image">
                                        </td>
                                        <td>${item.foodName}</td>
                                        <td><fmt:formatNumber value="${item.price}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ</td>
                                        <td>${item.quantity}</td>
<!--                                        <td><fmt:formatNumber value="${item.subtotal}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ</td>-->
                                    </tr>
                                </c:forEach>
                                <c:if test="${not empty order.discountAmount}">
                                    <tr>
                                        <td colspan="4" class="text-end"><strong>Discount Amount:</strong></td>
                                        <td>-<fmt:formatNumber value="${order.discountAmount}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ</td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td colspan="4" class="text-end"><strong>Total:</strong></td>
                                    <td><fmt:formatNumber value="${order.total}" type="currency" currencySymbol="" maxFractionDigits="0"/> VNĐ</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!-- Order History -->
            <div class="card mt-24">
                <div class="card-header">
                    <h6 class="card-title mb-0">Order History</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Updated By</th>
                                    <th>Date</th>
                                    <th>Previous Status</th>
                                    <th>New Status</th>
                                    <th>Note</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="approval" items="${approvals}">
                                    <tr>
                                        <td>${approval.adminUsername}</td>
                                        <td><fmt:formatDate value="${approval.approvedAt}" pattern="dd/MM/yyyy HH:mm"/></td>
                                        <td>
                                            <span class="badge ${approval.statusBefore == 'pending' ? 'bg-warning' : 
                                                                approval.statusBefore == 'accepted' ? 'bg-info' : 
                                                                approval.statusBefore == 'completed' ? 'bg-success' : 'bg-danger'}">
                                                ${approval.statusBefore}
                                            </span>
                                        </td>
                                        <td>
                                            <span class="badge ${approval.statusAfter == 'pending' ? 'bg-warning' : 
                                                                approval.statusAfter == 'accepted' ? 'bg-info' : 
                                                                approval.statusAfter == 'completed' ? 'bg-success' : 'bg-danger'}">
                                                ${approval.statusAfter}
                                            </span>
                                        </td>
                                        <td>${approval.note}</td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty approvals}">
                                    <tr>
                                        <td colspan="5" class="text-center">No update history</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
