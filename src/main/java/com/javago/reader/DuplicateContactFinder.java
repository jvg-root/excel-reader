package com.javago.reader;

import org.apache.commons.text.similarity.LevenshteinDistance;

import com.javago.reader.model.Coincidence;
import com.javago.reader.model.Contact;
import com.javago.reader.model.Precision;

import java.util.*;

public class DuplicateContactFinder {
	
	private final ContactsRepository contactsRepository;
	
    private final LevenshteinDistance levenshtein = new LevenshteinDistance();
	
	public DuplicateContactFinder(ContactsRepository contactsRepository) {
		this.contactsRepository = contactsRepository;
	}
    
    public List<Coincidence> findDuplicates(){
    	List<Contact> contactsList = this.contactsRepository.getAll();
    	return findDuplicatesInList(contactsList);
    }
    
    private List<Coincidence> findDuplicatesInList(List<Contact> contacts) {
        List <Coincidence> coincidences = new ArrayList<>();

        for (Contact c1 : contacts) {
            for (Contact c2 : contacts) {
                if (!c1.equals(c2)) {
                    Precision precision = calculateCoincidencePrecision(c1, c2);
                    if(precision != Precision.VERY_LOW) {
                    	coincidences.add(new Coincidence(c1.contactId(), c2.contactId(), precision));
                    }
                }
            }
        }
        return coincidences;
    }
    
    Precision calculateCoincidencePrecision(Contact c1, Contact c2) {
    	double score = compute(c1.firstName(), c2.firstName()); 
        score += compute(c1.lastName(), c2.lastName()); 
        score += compute(c1.email(),  c2.email()); 
        score = score/3;
        return getPrecision(score);
    }
    
    private double compute(String s1, String s2) {
        int maxLength = Math.max(s1.length(), s2.length());
        int distance = levenshtein.apply(s1, s2);
        return 1.0 - ((double) distance / maxLength); // 1.0 = identical, 0.0 = completely different
    }
    
    private Precision getPrecision(double score) {
    	if(score <= 0.5)
    		return Precision.VERY_LOW;
    	else if (score <= 0.66) {
        	return Precision.LOW;
        }else if(score <= 0.82) {
        	return Precision.MIDDLE;
        }else{
        	return Precision.HIGH;
        }
    }

    
    
}