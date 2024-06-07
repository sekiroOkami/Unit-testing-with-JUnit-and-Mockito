package chapter1.exercises;

public class StringReverse {

    public static String reverse(String testString) {
        StringBuilder reverseString = new StringBuilder();
        for (int i=testString.length()-1; i >= 0; i--) {
            reverseString.append(testString.charAt(i));
        }
        return reverseString.toString();
    }
}
