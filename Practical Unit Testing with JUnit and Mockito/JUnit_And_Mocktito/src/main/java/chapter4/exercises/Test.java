package chapter4.exercises;

public class Test {
    public static int nbOfSymbols(String string) {
        // Use codePoints() to process each code point in the string
        return (int) string.codePoints()
                .filter(codePoint -> !Character.isLetterOrDigit(codePoint))
                .count();
    }

    public static void main(String[] args) {
        String password = "P@ssw0rd!#";
        int count = nbOfSymbols(password);
        System.out.println("Number of symbols: " + count);
    }
}
