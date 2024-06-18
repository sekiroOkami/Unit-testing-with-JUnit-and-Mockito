package chapter8.model;

public class UserToPersonConverter {
    public static Person convert(User user) {
        return new Person(user.name() + " " + user.surname());
    }
}
