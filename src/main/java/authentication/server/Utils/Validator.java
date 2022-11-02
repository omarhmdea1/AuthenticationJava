package authentication.server.Utils;

import java.util.regex.Pattern;

public class Validator {

    private static Pattern pattern;

    public static void isValidName(String name) {
        String regex = "^[A-Za-z]\\w{2,29}$";
        pattern = Pattern.compile(regex);
        if(name == null || !pattern.matcher(name).matches()) {
            throw new Error("Invalid name - please enter a name with at least 2 characters");
        }
    }

    public static void isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        pattern = Pattern.compile(emailRegex);
        if(email == null || !pattern.matcher(email).matches()) {
            throw new Error("Invalid email - please enter ____@__._");
        }
    }

    public static void isValidPassword(String password) {
        String regex = "^(?=.*[0-9])" +
                "(?=.*[a-z])(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$).{8,20}$";
        pattern = Pattern.compile(regex);
        if(password == null || !pattern.matcher(password).matches()) {
            throw new Error("Invalid password - " + getPasswordConstraints());
        }
    }

    public static String getPasswordConstraints(){
        return "\ncontains at least 8 characters and at most 20 characters.\n" +
                "At least one digit.\n" +
                "At least one upper case alphabet.\n" +
                "At least one lower case alphabet.\n" +
                "At least one special character which includes !@#$%&*()-+=^.\n" +
                "no white space.";
    }
}
