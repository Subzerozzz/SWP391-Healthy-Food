/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.config;

import com.su25.swp391.entity.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Dell
 */
public class GlobalConfig {

   public static List<Category> filterCategoryByBMI(List<Category> allCategories, double minBMI, double maxBMI) {
    List<Category> result = new ArrayList<>();
    for (Category c : allCategories) {
        // Nếu khoảng BMI của category giao nhau với khoảng lọc
        if (c.getMinBMI() <= maxBMI && c.getMaxBMI() >= minBMI) {
            result.add(c);
        }
    }
    return result;
}
} 
