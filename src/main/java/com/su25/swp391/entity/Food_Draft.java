/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.sql.Timestamp;
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Food_Draft {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String image_url;
    private Integer category_id;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Integer food_id;
    private String type;
    private Integer nutri_id;
    
    
    
  
}
