/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.config;

import com.su25.swp391.dal.implement.CategoryDAO;
import com.su25.swp391.entity.Category;
import java.util.List;

/**
 *
 * @author Hang
 */
public class CategoryFilterService {

    //hàm quyets định khaongr BMI dựa vào thời gian hiện tại
    public double[] getBMIRange(String filterType) {
        switch (filterType) {
            case "low":
                return new double[]{0, 18.4};
            case "normal":
                return new double[]{18.5, 24.9};
            case "overweight":
                return new double[]{25, 29.9};
            case "obese":
                return new double[]{30, 100}; // giả định max là 100
            default:
                return new double[]{0, 100}; // không lọc
        }
    }

    // Hàm lọc danh sách category dựa vào filterType
    public List<Category> filterByType(String filterType) {
        double[] range = getBMIRange(filterType);
        double minBMI = range[0];
        double maxBMI = range[1];

        CategoryDAO dao = new CategoryDAO();
        return dao.filterCategoryByBMI(minBMI, maxBMI);
    }
}
