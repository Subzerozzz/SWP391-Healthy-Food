/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderComboProduct {
    private Integer orderComboProductId;
    private Integer orderComboId;
    private Integer productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer quantityInCombo;
    private Integer totalQuantity;
}
