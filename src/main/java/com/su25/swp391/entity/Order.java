/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.entity;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Order {

    private int orderId;
    private int userId;
    private String status; // enum: pending, accepted, cancelled, completed
    private BigDecimal total;
    private String shippingAddress;
    private String paymentMethod;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // User info (join với bảng account)
    private String username;
    private String email;
    private String mobie;

    // Danh sách các items trong order
    private List<OrderItem> orderItems;

    // Add these fields to your Order class
    private String couponCode;
    private BigDecimal discountAmount;
   

    // Constructors, getters, setters
    public Order() {
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobie() {
        return mobie;
    }

    public void setMobie(String mobie) {
        this.mobie = mobie;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
   @Override
public String toString() {
    return "Order{" +
            "orderId=" + orderId +
            ", userId=" + userId +
            ", status='" + status + '\'' +
            ", total=" + total +
            ", shippingAddress='" + shippingAddress + '\'' +
            ", paymentMethod='" + paymentMethod + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", couponCode='" + couponCode + '\'' +
            ", discountAmount=" + discountAmount +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", mobie='" + mobie + '\'' +
            '}';
}
}
