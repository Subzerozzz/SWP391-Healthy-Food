/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
    
    private int order_id;
    private int user_id;
    private String status;
    private BigDecimal total;
    private String payment_method;
    private Timestamp created_at;
    private Timestamp update_at;
    private String coupon_code;
    private BigDecimal discount_amount;
    private String full_name;
    private String mobile;
    private String address;
    private String email;
    private String shipping_address;
    private int payment_status;

}
