
package com.su25.swp391.entity;

import java.sql.Timestamp;
import java.util.List;
import java.math.BigDecimal;
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
public class Order {

    private Integer id;
    private Integer account_id;
    private String status; // enum: pending, accepted, cancelled, completed
    private Double total;
    private String shipping_address;
    private String payment_method;
    private Timestamp created_at;
    private Timestamp updated_at;
    private String coupon_code;
    private Double discount_amount;
    private String full_name;
    private String mobile;
    private String email;
    private Integer payment_status;
}
