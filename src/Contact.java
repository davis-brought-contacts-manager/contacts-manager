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


    public static List<Contact> infoStringsToContacts(List<String> info){
        List<Contact> contacts = new ArrayList<>();
        for(String line : info) {
            contacts.add(new Contact(line));
        }
        return contacts;
    }

}
