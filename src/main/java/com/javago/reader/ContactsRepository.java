package com.javago.reader;

import java.util.List;

import com.javago.reader.model.Contact;

public interface ContactsRepository {
	List<Contact> getAll();
}
