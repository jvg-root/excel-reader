package com.javago.reader.data;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;

import com.javago.reader.ContactsRepository;
import com.javago.reader.model.Contact;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelContactsReader  implements ContactsRepository{
	@Override
	public List<Contact> getAll() {
		return readContacts("contacts.xlsx");
	}
	
	
    public static  List<Contact> readContacts(String filePath) {
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
                
                if(contactId == 0 && StringUtils.isBlank(firstName) && StringUtils.isBlank(lastName) && StringUtils.isBlank(email)) break;

                contacts.add(new Contact(contactId, firstName, lastName, email));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }

	
}
