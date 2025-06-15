package com.su25.swp391.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Category {
    private Integer categoryId;
    private String name;
    private String description;
    private Byte status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean isParent;
    private Integer parentId;
}