 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int foodId;
    private int quantity;
    private BigDecimal price;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    // Thông tin sản phẩm (join với bảng products)
    private String foodName;
    private String foodImage;

    public OrderItem() {
    }
    

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "orderItemId=" + orderItemId + ", orderId=" + orderId + ", foodId=" + foodId + ", quantity=" + quantity + ", price=" + price + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", foodName=" + foodName + ", foodImage=" + foodImage + '}';
    }

    
    
}