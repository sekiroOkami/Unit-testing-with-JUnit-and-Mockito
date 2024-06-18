package chapter7.model;

import java.util.ArrayList;
import java.util.Collection;

public class User {
    Collection<String> phones = new ArrayList<>();

    public void addPhone(String phoneNumber) {
        phones.add(phoneNumber);
    }

    public Collection<String> getPhones() {
        return phones;
    }
}
