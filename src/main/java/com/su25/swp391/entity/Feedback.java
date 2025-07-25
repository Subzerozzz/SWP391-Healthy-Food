/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.entity;


import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Feedback {
    private Integer id;
    private Integer user_id;
    private Integer order_item_id;
    private String content;
    private Integer rating;
    private boolean isVisible;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
                              
}
