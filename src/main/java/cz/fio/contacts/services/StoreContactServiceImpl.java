package cz.fio.contacts.services;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import cz.fio.contacts.models.Contact;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StoreContactServiceImpl implements StoreContactService {

    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader("contacts.csv", Charset.forName("Cp1250")));
            List<String[]> csvBody = reader.readAll();

            for (String[] value : csvBody) {
                contacts.add(new Contact(value[0], value[1], value[2]));
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return contacts;
    }

    @Override
    public Contact saveContact(Contact contact) {

        try {
            CSVReader reader = new CSVReader(new FileReader("contacts.csv", Charset.forName("Cp1250")));
            List<String[]> csvBody = reader.readAll();

            // if list of contacts contains given email, then return null
            for (String[] value : csvBody) {
                if (Objects.equals(value[2], contact.getEmail())) {
                    return null;
                }
            }

            String[] newLine = {contact.getFirstName(), contact.getLastName(), contact.getEmail()};
            csvBody.add(newLine);

            CSVWriter writer = new CSVWriter(new FileWriter("contacts.csv", Charset.forName("Cp1250")));
            writer.writeAll(csvBody);

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return contact;
    }

}
