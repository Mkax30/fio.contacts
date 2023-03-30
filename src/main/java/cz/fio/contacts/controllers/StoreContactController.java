package cz.fio.contacts.controllers;

import cz.fio.contacts.models.Contact;
import cz.fio.contacts.services.StoreContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8071")
@RestController
@RequestMapping("/api/v1")
public class StoreContactController {

    @Autowired
    private StoreContactService storeContactService;

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = new ArrayList<>(storeContactService.getAllContacts());
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> saveContact(@RequestBody Contact requestContact) {
        Contact contact = storeContactService.saveContact(requestContact);
        if (contact == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

}
