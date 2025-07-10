/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.utils;

import com.su25.swp391.entity.Food;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.apache.poi.ss.usermodel.*;

public class DataExcelUtils {

    public static List<Food> readFoods(InputStream is) throws Exception {
        List<Food> list = new ArrayList<>();
        try(Workbook wb = WorkbookFactory.create(is)) {
            Sheet sheet = wb.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if (row == null || isRowEmpty(row)) {
                    continue;
                }

                Food newFood = new Food();

                //get Name
                newFood.setName(getString(row, 0));
                //get description
                newFood.setDescription(getString(row, 1));
                //get price
                newFood.setPrice(getDouble(row, 2));
                //get image
                newFood.setImage_url(null);
                //get status
                newFood.setStatus(getString(row, 4));
                //get categoryid
                newFood.setCategory_id(getInteger(row, 5));
                //get create_at
                newFood.setCreated_at(new Timestamp(System.currentTimeMillis()));
                //get update_at
                newFood.setUpdated_at(new Timestamp(System.currentTimeMillis()));
                //get nutri_id
                newFood.setNutri_id(getInteger(row, 6));
                //get calo
                newFood.setCalo(getDouble(row, 7));

                list.add(newFood);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    private static String getString(Row row, Integer index) {
        Cell cell = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

        return cell == null ? null : cell.getStringCellValue().trim();
    }

    private static Double getDouble(Row row, Integer index) {
        Cell cell = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        }
        if (cell.getCellType() == CellType.STRING && !cell.getStringCellValue().isBlank()) {
            return Double.parseDouble(cell.getStringCellValue().trim());
        }
        return null;
    }

    private static Integer getInteger(Row row, Integer index) {
        Cell cell = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        if (cell.getCellType() == CellType.STRING) {
            return Integer.parseInt(cell.getStringCellValue().trim());
        }
        return null;
    }

    private static Timestamp getTimestamp(Row row, int index) {
        Cell cell = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell == null) {
            return null;
        }

        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            Date d = cell.getDateCellValue();
            return new Timestamp(d.getTime());
        }

        if (cell.getCellType() == CellType.STRING) {
            String txt = cell.getStringCellValue().trim();
            if (txt.isEmpty()) {
                return null;
            }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime ldt = LocalDateTime.parse(txt,formatter);
                return Timestamp.valueOf(ldt);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

    private static boolean isRowEmpty(Row row) {
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}
