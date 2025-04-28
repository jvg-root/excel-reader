package com.javago.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.javago.reader.model.Coincidence;
import com.javago.reader.model.Contact;
import com.javago.reader.model.Precision;

import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class DuplicateContactFinderTest {
    private ContactsRepository mockRepository;
    private DuplicateContactFinder duplicateFinder;

    @BeforeEach
    void setUp() {
        mockRepository = Mockito.mock(ContactsRepository.class);
        duplicateFinder = new DuplicateContactFinder(mockRepository);
    }

    @Test
    void testFindDuplicates() {
    	
        Contact c1 = new Contact(1L, "John", "Doe", "john@example.com");
        Contact c2 = new Contact(2L, "Jon", "Doe", "jon@example.com"); // 🔥 Slight variation
        Contact c3 = new Contact(3L, "Alice", "Smith", "alice@example.com");

        Mockito.when(mockRepository.getAll()).thenReturn(Arrays.asList(c1, c2, c3));

        List<Coincidence> result = duplicateFinder.findDuplicates();

        assertFalse(result.isEmpty()); 
        assertEquals(2, result.size()); 
        assertEquals(c1.contactId(), result.get(0).contactId()); 
        assertEquals(c2.contactId(), result.get(0).contactIdCoincidence());
    }

    @Test
    void testPrecisionCalculation() {
    	
        Contact c1 = new Contact(1L, "Alex", "Smith", "alex@example.com");
        Contact c2 = new Contact(2L, "Alec", "Smith", "alec@example.com");

        Precision precision = duplicateFinder.calculateCoincidencePrecision(c1, c2);
        assertNotEquals(Precision.VERY_LOW, precision);
    }

    @Test
    void testNoDuplicatesFound() {
        Mockito.when(mockRepository.getAll()).thenReturn(List.of(
            new Contact(1L, "Jane", "Doe", "jane@example.com"),
            new Contact(2L, "Alice", "Smith", "alice@example.com"),
            new Contact(3L, "Bob", "Brown", "bob@example.com")
        ));

        List<Coincidence> result = duplicateFinder.findDuplicates();
        assertTrue(result.isEmpty()); 
    }
}