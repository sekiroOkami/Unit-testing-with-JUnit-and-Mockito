package chapter4.exercises;

public class PasswordValidator {
    private final static int MIN_LENGTH = 4;
    private final static int MAX_LENGTH = 8;
    private boolean isContainAtleastCapitalLetter = false;
    private long nbOfCaptipalLetter = 0;
    private boolean isContainSymbolic = false;
    private long nbOfSymbolicLetter = 0;


    public Level validatePassword(String password) {
        checkNbOfCapitalLetter(password);
        checkNbOfSymbolicLetter(password);
        if (password.length() < MIN_LENGTH | password.length() > MAX_LENGTH) {
            return Level.NOT_PASS;
        } else if (password.length() < MAX_LENGTH
                & isContainAtleastCapitalLetter == false
                & isContainSymbolic == false) {
            return Level.BAD;
        } else if (password.length() < MAX_LENGTH
                & isContainAtleastCapitalLetter == true
                & isContainSymbolic == false) {
            return Level.GOOD;
        } else {
            return Level.STRONG;
        }
    }

    private void checkNbOfSymbolicLetter(String password) {
        nbOfSymbolicLetter = password.codePoints()
                .filter(codePoint -> !Character.isLetterOrDigit(codePoint))
                .count();
        if (nbOfSymbolicLetter > 0) isContainSymbolic = true;
    }

    private void checkNbOfCapitalLetter(String password) {
        nbOfCaptipalLetter = password.codePoints()
                .filter(Character::isUpperCase)
                .count();
        if (nbOfCaptipalLetter > 0) isContainAtleastCapitalLetter = true;
    }
}
