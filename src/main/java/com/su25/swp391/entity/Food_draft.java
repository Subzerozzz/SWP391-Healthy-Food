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
public class Food_draft {
  private Integer id;
  private String name;
  private String description;
  private Double price;
  private String image_url;
  private String status;
  private Integer category_id;
  private Timestamp created_at;
  private Timestamp updated_at;

}
