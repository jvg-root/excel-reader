package com.javago.reader;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.*;

public class DuplicateContactFinder {
	
	private final ContactsRepository contactsRepository;
	
	public DuplicateContactFinder(ContactsRepository contactsRepository) {
		this.contactsRepository = contactsRepository;
	}

    private static LevenshteinDistance levenshtein = new LevenshteinDistance();
    
    public static List<Coincidence> findDuplicates(List<Contact> contacts) {
        List <Coincidence> coincidences = new ArrayList<>();

        for (Contact c1 : contacts) {
            List<String> potentialMatches = new ArrayList<>();
            for (Contact c2 : contacts) {
                if (!c1.equals(c2)) {
                    double score = compute(c1.firstName(), c2.firstName()); 
                    score += compute(c1.lastName(), c2.lastName()); 
                    score += compute(c1.email(),  c2.email()); 
                    score = score/3;
                    if (score > 0.5 && score <= 0.66) {
                        potentialMatches.add(c2.toString() + " (Score: " + score + ")");
                    }else if(score > 0.66 && score <= 0.82) {
                    	
                    }
                }
            }
        }
        return coincidences;
    }
    
    private static double compute(String s1, String s2) {
        int maxLength = Math.max(s1.length(), s2.length());
        int distance = levenshtein.apply(s1, s2);
        return 1.0 - ((double) distance / maxLength); // 1.0 = identical, 0.0 = completely different
    }

    
    
}