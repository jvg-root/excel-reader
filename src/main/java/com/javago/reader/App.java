package com.javago.reader;

import java.util.List;

import com.javago.reader.data.ExcelCoincidencesWriter;
import com.javago.reader.data.ExcelContactsReader;
import com.javago.reader.model.Coincidence;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		
		DuplicateContactFinder contactsFinder = new DuplicateContactFinder(new ExcelContactsReader());
        List<Coincidence> duplicates = contactsFinder.findDuplicates();
        ExcelCoincidencesWriter.writeToExcel("coincidences.xlsx", duplicates);

        
    }

}
