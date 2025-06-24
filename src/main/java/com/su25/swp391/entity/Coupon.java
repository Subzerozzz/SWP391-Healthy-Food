
package com.su25.swp391.entity;

import java.math.BigDecimal;
import java.sql.Date;
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
public class Coupon {
    private int id;
    private String code;
    private String description;
    private String discountType; // percentage or fixed
    private BigDecimal discountValue;
    private BigDecimal minPurchase;
    private BigDecimal maxDiscount;
    private Date startDate;
    private Date endDate;
    private Integer usageLimit;
    private Integer usageCount;
    private Integer perCustomerLimit;
    private Integer isactive;
    private Date createdAt;
    private Date updatedAt;
}
