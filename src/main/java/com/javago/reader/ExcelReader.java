package com.javago.reader;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    public static List<Contact> readContacts(String filePath) {
        List<Contact> contacts = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; 
                
                Long contactId = ((Double) row.getCell(0).getNumericCellValue()).longValue();
                String firstName = row.getCell(1).getStringCellValue();
                String lastName = row.getCell(2).getStringCellValue();
                String email = row.getCell(3).getStringCellValue();

                contacts.add(new Contact(contactId, firstName, lastName, email));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
