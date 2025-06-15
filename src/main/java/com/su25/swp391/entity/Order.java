/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 *
 * @author kieud
 */
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    private int orderId;
    private int userId;
    private String status; // enum: pending, accepted, cancelled, completed
    private BigDecimal total;
    private String shippingAddress;
    private String paymentMethod;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String quantity;
    private String couponCode;
    private BigDecimal discountAmount;
    private String type; // enum: wholesale, retail
    
}
