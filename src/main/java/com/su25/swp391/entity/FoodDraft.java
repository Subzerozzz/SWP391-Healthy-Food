package com.su25.swp391.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.sql.Timestamp;

/**
 *
 * @author Dell
 */
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodDraft {
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
  private Double calo;



}
