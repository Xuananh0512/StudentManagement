package validators;

import java.util.regex.Pattern;

public class Validator {

    public static boolean isValidId(String id) {
        return Pattern.matches("^[A-Za-z0-9]{2,10}$", id);
    }

    public static boolean isValidName(String name) {
        return Pattern.matches("^[A-Za-z\\s]{3,50}$", name);
    }

    public static boolean isValidAddress(String address) {
        return address != null && address.trim().length() >= 3;
    }

    public static boolean isValidScore(double score) {
        return score >= 0 && score <= 10;
    }

    public static boolean isValidConduct(String conduct) {
        return Pattern.matches("^[A-Za-z\\s]{3,20}$", conduct);
    }
}
