package com.javago.reader;

import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
        String filePath = "contacts.xlsx"; // 📌 Archivo de entrada
        List<Contact> contacts = ExcelReader.readContacts(filePath);
        List<Coincidence> duplicates = DuplicateContactFinder.findDuplicates(contacts);

        
    }

}
