import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactsApplication {

    public static void main(String[] args) {

//      define path
        Path infoFile = Paths.get("src", "contacts.txt");

//      variable declarations

        List<Contact> contacts;
        List<String> info = new ArrayList<>();
        try {
            info = Files.readAllLines(infoFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contacts = Contact.infoStringsToContacts(info);

       loadContacts(contacts, info, infoFile);

//        option ONE
        viewContacts(contacts);

//        option TWO (add)
//        addContact(infoFile,"New Add | 2105551234");

//        option THREE (search)
        String searchFor = "Andrew";
//        String searchFor = "Doe"; // find multiple entries
        searchContacts(contacts, searchFor);

//        info = Contact.contactsToInfoStrings(contacts);

//        Option FOUR:
        removeContact(infoFile, info,"Andrew Brought", contacts);
        viewContacts(contacts);

    }

    public static List<Contact> loadContacts(List<Contact> contacts, List<String> info, Path infoFile){
        try {
            info = Files.readAllLines(infoFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Contact> contactsRefresh = contacts;
        contactsRefresh = Contact.infoStringsToContacts(info);
        return contactsRefresh;
    }


    public static void viewContacts(List<Contact> contacts){
        String list = "Name            | Phone number\n";
              list += "------------------------------\n";
        for(Contact person : contacts){
           list += person.getInfo() + "\n";
        }
        System.out.println(list);

    }


    public static void addContact(Path a, String str, List<Contact> contacts, List<String> info) {
        List<String> newContact = Arrays.asList(str);
        try {
            Files.write(a, newContact, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadContacts(contacts, info, a);
    }

    public static void removeContact(Path a, List<String> info, String str, List<Contact> contacts) {

        List<String> newList = new ArrayList<>();
        for (String person : info) {
            if (person.contains(str)) {
                continue;
            }
            newList.add(person);
        }
        try {
            Files.write(a, newList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadContacts(contacts, info, a);
    }


        public static void searchContacts(List < Contact > contacts, String searchStr){
            String list = "";
            for (Contact person : contacts) {
                if (person.getInfo().contains(searchStr)) {
                    list += person.getInfo() + "\n";
                }
            }
            if (list.equals("")) {
                System.out.println(searchStr + " was not found");
            } else {
                System.out.println(list);
            }
        }


    }

