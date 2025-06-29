/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Predator
 */
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coupon {
    private int id;
    private String code;
    private String description;
    private String discount_type; // percentage or fixed
    private Double discount_value;
    private Double min_purchase;
    private Double max_discount;
    private Date start_date;
    private Date end_date;
    private Integer usage_limit;
    private Integer usage_count;
    private Integer per_customer_limit;
    private Integer is_active;
    private Date created_at;
    private Date updated_at;
}
