/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.config;

import com.su25.swp391.entity.Category;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Dell
 */
public class GlobalConfig {

    public static List<Category> filterCategoryByBMI(List<Category> categories, double minBMI, double maxBMI) {
        if (categories == null || categories.isEmpty()) return List.of();

        return categories.stream()
             .filter(c -> c.getMinBMI() >= minBMI && c.getMaxBMI() <= maxBMI) 
            .collect(Collectors.toList());
    }
} 
