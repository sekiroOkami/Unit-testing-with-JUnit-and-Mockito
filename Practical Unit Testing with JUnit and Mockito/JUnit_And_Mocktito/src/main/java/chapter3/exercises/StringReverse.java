package chapter3.exercises;

import java.util.Objects;

public class StringReverse {

    public static String reverse(String testString) {
        Objects.requireNonNull(testString);
        StringBuilder reverseString = new StringBuilder();
        for (int i=testString.length()-1; i >= 0; i--) {
            reverseString.append(testString.charAt(i));
        }
        return reverseString.toString();
    }
}
