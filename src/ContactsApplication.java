import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

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
        Scanner input = new Scanner(System.in);
       String keepLooping = "y";
       do{
           System.out.println("1. View contacts.");
           System.out.println("2. Add a new contact.");
           System.out.println("3. Search a contact by name.");
           System.out.println("4. Delete an existing contact.");
           System.out.println("5. Exit.");
           System.out.println("Enter an option (1, 2, 3, 4 or 5):");
           int userNumber = input.nextInt();
           String str = input.nextLine();

           switch(userNumber){
               case 1:
                   //        option ONE
                   viewContacts(loadContacts(contacts, info, infoFile));
                   System.out.println("Would you like to continue?");
                   keepLooping = input.next();
                   break;
               case 2:
                   //        option TWO (add)
                   System.out.println("Please enter a new contact");
                   addContact(infoFile, str, contacts, info);

           }

       }while(keepLooping.equalsIgnoreCase("y"));




//        option TWO (add)
//        addContact(infoFile,"New Add | 2105551234");

//        option THREE (search)
        String searchFor = "Andrew";
//        String searchFor = "Doe"; // find multiple entries
        searchContacts(contacts, searchFor);

//        info = Contact.contactsToInfoStrings(contacts);

//        Option FOUR:
        removeContact(infoFile, info,"Jack Blank", contacts);
        viewContacts(loadContacts(contacts, info, infoFile));

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

