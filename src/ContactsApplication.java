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


//        load (refresh) list of contacts
        loadContacts(contacts, info, infoFile);
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        String keepLooping = "y";
        do {
            System.out.println("1. View contacts.");
            System.out.println("2. Add a new contact.");
            System.out.println("3. Search a contact by name.");
            System.out.println("4. Delete an existing contact.");
            System.out.println("5. Exit.");
            System.out.println("Enter an option (1, 2, 3, 4 or 5):");
            int userNumber = input.nextInt();

            switch (userNumber) {
                case 1:
                    //        option ONE (view)
                    viewContacts(loadContacts(contacts, info, infoFile));
                    System.out.println("Would you like to continue? (y/n): ");
                    keepLooping = input.next();
                    break;

                case 2:
                    //        option TWO (add)
                    do {
                        System.out.println("Please enter a new contact name and phone number (John Doe | 2105551234): ");
                        String str = input.next();
                        String justTheName = str.substring(0, str.indexOf("|"));
//                       System.out.println(justTheName);  //
                        boolean found = searchContacts(loadContacts(contacts, info, infoFile), justTheName).contains(justTheName.trim());
//                       System.out.println(found);  //
                        if (found) {
                            System.out.println("There's already a contact named " + justTheName + ". Do you want to overwrite it? (y/n):");
                            String overwrite = input.next();
                            if (overwrite.equals("y")) {
                                removeContact(infoFile, info, justTheName, contacts);
                                addContact(infoFile, str, contacts, info);
                                loadContacts(contacts, info, infoFile);
                                System.out.println(str + " has been added"); //
                            }
                        } else {
                            System.out.println(str + " has been added"); //
                            addContact(infoFile, str, contacts, info);
                            loadContacts(contacts, info, infoFile);
                        }
                        System.out.println("Would you like to add another new contact? (y/n): ");
                        keepLooping = input.next();
                    } while (keepLooping.equals("y"));
                    keepLooping = "y";
                    break;
                case 3:
                    //        option THREE (search)
                    loadContacts(contacts, info, infoFile);

                    do {
                        System.out.println("Please enter a contact name to search for: ");
                        String searchStr = input.next();
                        String searchResult = searchContacts(loadContacts(contacts, info, infoFile), searchStr); //
//                       System.out.println(searchResult);  //
                        if (searchResult.equals("")) {
                            System.out.println(searchStr + " was not found.");
                        } else {
                            System.out.println(searchResult);
                        }
                        System.out.println("Would you like to search for another contact? (y/n): ");
                        keepLooping = input.next();
                    } while (keepLooping.equals("y"));
                    keepLooping = "y";
                    break;
                case 4:
                    //        Option FOUR (delete)
                    do {
                        System.out.println("Please enter a contact name to delete: ");
                        String searchStr = input.next();
                        String searchResult = searchContacts(loadContacts(contacts, info, infoFile), searchStr);  //
                        removeContact(infoFile, info, searchStr, contacts);
                        System.out.println(searchResult + "Has been removed.");  //
                        System.out.println("Would you like to delete another contact? (y/n): ");
                        keepLooping = input.next();
                    } while (keepLooping.equals("y"));
                    keepLooping = "y";
                    break;
                case 5:
                    System.out.println("Bye!");
                    keepLooping = "n";
                    break;
                default:
                    System.out.println("Please enter one of the options.");
                    keepLooping = "y";
            }
        } while (keepLooping.equalsIgnoreCase("y"));


//      This method is not currently used since we are updating the text file after making changes
//        But keeping the reference for possible refactoring
//        info = Contact.contactsToInfoStrings(contacts);


    } // end of main

    public static List<Contact> loadContacts(List<Contact> contacts, List<String> info, Path infoFile) {
        try {
            info = Files.readAllLines(infoFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Contact> contactsRefresh = contacts;
        contactsRefresh = Contact.infoStringsToContacts(info);
        return contactsRefresh;
    }


    public static void viewContacts(List<Contact> contacts) {
        String list = "Name            | Phone number\n";
        list += "------------------------------\n";
        for (Contact person : contacts) {
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


    public static String searchContacts(List<Contact> contacts, String searchStr) {
        String list = "";
        for (Contact person : contacts) {
            if (person.getInfo().contains(searchStr)) {
                list += person.getInfo() + "\n";
            }
        }
        if (list.equals("")) {
            return list;
        } else {
            return list;
        }
    }


}

