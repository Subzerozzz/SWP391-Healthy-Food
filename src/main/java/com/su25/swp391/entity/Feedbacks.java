/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.entity;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class Feedbacks {
    private int feedbackId;
    private int userId;
    private int orderItemId;
    private String content;
    private int rating;
    private boolean isVisible;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    // Thêm thuộc tính mở rộng để hiển thị thông tin sản phẩm trong đánh giá
    private String userName;
    private String productName;
    private String productImage;

    public Feedbacks() {
    }
      public Feedbacks(int feedbackId, int userId, int orderItemId, String content, int rating, boolean isVisible, Timestamp createdAt, Timestamp updatedAt) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.orderItemId = orderItemId;
        this.content = content;
        this.rating = rating;
        this.isVisible = isVisible;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Constructor để tạo mới feedback (không cần feedbackId, createdAt, updatedAt)
    public Feedbacks(int userId, int orderItemId, String content, int rating) {
        this.userId = userId;
        this.orderItemId = orderItemId;
        this.content = content;
        this.rating = rating;
        this.isVisible = true;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "Feedbacks{" + "feedbackId=" + feedbackId + ", userId=" + userId + ", orderItemId=" + orderItemId + ", content=" + content + ", rating=" + rating + ", isVisible=" + isVisible + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}
