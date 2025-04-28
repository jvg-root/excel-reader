package com.javago.reader.data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.javago.reader.model.Coincidence;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelCoincidencesWriter {
    public static void writeToExcel(String filePath, List<Coincidence> duplicates) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(filePath)) {

            Sheet sheet = workbook.createSheet("Duplicates");

            // 🔹 Create Header Row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ContactID origen");
            header.createCell(1).setCellValue("ContactID coincidencia");
            header.createCell(2).setCellValue("Precisión");

            // 🔹 Populate Data
            int rowNum = 1;
            for (Coincidence coincidence: duplicates) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(coincidence.contactId());
                row.createCell(1).setCellValue(coincidence.contactIdCoincidence());
                row.createCell(2).setCellValue(coincidence.precision().getDescription());
            }

            workbook.write(fos);
            System.out.println("✅ Duplicate contacts saved to: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}