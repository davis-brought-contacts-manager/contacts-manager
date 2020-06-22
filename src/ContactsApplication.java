import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        System.out.println(info);

        contacts = Contact.infoStringsToContacts(info);

//        option ONE
        viewContacts(contacts);





    }

    public static void viewContacts(List<Contact> contacts){
        String list = "Name       |      Phone number\n";
              list += "------------------------------\n";
        for(Contact person : contacts){
           list += person.getInfo() + "\n";
        }
        System.out.println(list);
    }

}
