

package com.su25.swp391.entity;


import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {
    private Integer id;
    private Integer order_id;
    private Integer food_id;
    private Integer quantity;
    private Double price;
    private Timestamp created_at;
    private Timestamp updated_at;

}
