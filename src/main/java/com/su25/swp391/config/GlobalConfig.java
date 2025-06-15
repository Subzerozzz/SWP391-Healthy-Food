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

   public class BMIRange {
    public static final double[] LOW = {10.0, 18.4};
    public static final double[] NORMAL = {18.5, 24.9};
    public static final double[] OVERWEIGHT = {25.0, 29.9};
    public static final double[] OBESE = {30.0, 50.0};
    public static final double[] ALL = {0.0, 51.0}; // Mặc định lọc toàn bộ
}
} 
