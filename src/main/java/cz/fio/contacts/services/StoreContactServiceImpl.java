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
        CSVReader reader;
        List<Contact> contacts = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("contacts.csv", Charset.forName("Cp1250"));

            reader = new CSVReader(fileReader);
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                contacts.add(new Contact(nextLine[0], nextLine[1], nextLine[2]));
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

            String[] newLine = new String[3];
            newLine[0] = contact.getFirstName();
            newLine[1] = contact.getLastName();
            newLine[2] = contact.getEmail();

            csvBody.add(newLine);

            CSVWriter writer = new CSVWriter(new FileWriter("contacts.csv", Charset.forName("Cp1250")));
            writer.writeAll(csvBody);

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return contact; // just return contact object for distinguish error state
    }

}
