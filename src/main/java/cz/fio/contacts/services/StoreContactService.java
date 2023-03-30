package cz.fio.contacts.services;

import cz.fio.contacts.models.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreContactService {

    List<Contact> getAllContacts();

    Contact saveContact(Contact contact);
}
