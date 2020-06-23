import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String info;

    public Contact(String info){
        this.info = info;
    }

    public String getInfo(){
        return info;
    }

    public void setInfo(String info){
        this.info = info;
    }

    // Takes in a list of Contact objects and returns a list of info strings
    // (will use this to write the file after making changes)
    public static List<String> contactsToInfoStrings(List<Contact> contacts) {
        List<String> info = new ArrayList<>();
        for (Contact object : contacts) {
            info.add(object.getInfo());
        }
        return info;
    }

    // Takes in a list of info strings and returns a list of Contact objects
    public static List<Contact> infoStringsToContacts(List<String> info){
        List<Contact> contacts = new ArrayList<>();
        for(String line : info) {
            contacts.add(new Contact(line));
        }
        return contacts;
    }

}
