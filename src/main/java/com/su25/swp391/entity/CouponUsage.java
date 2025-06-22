/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.entity;

import java.sql.Timestamp;

/**
 *
 * @author Dell
 */
public class CouponUsage {
    private Integer id;
    private Integer coupon_id;
    private Integer account_id;
    private Integer order_id;
    private Timestamp used_at;
    private Double discount_amount;
}
