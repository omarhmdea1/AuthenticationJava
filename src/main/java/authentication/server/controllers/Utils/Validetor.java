package authentication.server.controllers.Utils;

import java.util.regex.Pattern;

public class Validetor {

    private static Pattern pattern;

    public static boolean isValidName(String name) {
        if(name == null) {
            return false;
        }
        String regex = "^[A-Za-z]\\w{2,29}$";
        pattern = Pattern.compile(regex);
        return pattern.matcher(name).matches();
    }

    public static boolean isValidEmail(String email) {
        if(email == null) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        if(password == null) {
            return false;
        }
        String regex = "^(?=.*[0-9])" +
                "(?=.*[a-z])(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$).{8,20}$";

        pattern = Pattern.compile(regex);
        return pattern.matcher(password).matches();
    }
}
