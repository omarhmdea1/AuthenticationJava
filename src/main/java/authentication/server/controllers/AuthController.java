package authentication.server.controllers;

import authentication.server.services.AuthService;

import java.util.Map;
import java.util.regex.Pattern;

public class AuthController {

    private Pattern pattern;
    private static AuthController instance;
    private static AuthService authService;


    private AuthController() {
        //authService = AuthService.getInstance();
    }


    public static AuthController getInstance() {
        if(AuthController.instance == null) {
            instance = new AuthController();
        }
        return instance;
    }


    public void register(String name, String email, String password) {

        if(! isValidName(name)) throw new IllegalArgumentException("Invalid name");
        if(! isValidEmail(email)) throw new IllegalArgumentException("Invalid email");
        if(! isValidPassword(password)) throw new IllegalArgumentException("Invalid password");

        //authService.register(name, email, password);
    }


    public void logIn(String email, String password) {
        if(! isValidEmail(email)) throw new IllegalArgumentException("Invalid email");
        if(! isValidPassword(password)) throw new IllegalArgumentException("Invalid password");

        Map<String, String> tokenMail = AuthService.validateUserCredentials(email,password);
        String token = tokenMail.get(0);

        //authService.logIn(email, password);
    }


    private boolean isValidName(String name) {
        if(name == null) {
            return false;
        }

        String regex = "^[A-Za-z]\\w{2,15}$";

        this.pattern = Pattern.compile(regex);
        return this.pattern.matcher(name).matches();
    }


    private boolean isValidEmail(String email) {
        if(email == null){
            return false;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,15}$";

        this.pattern = Pattern.compile(emailRegex);
        return this.pattern.matcher(email).matches();
    }


    private boolean isValidPassword(String password) {

        if(password == null) return false;

        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        this.pattern = Pattern.compile(regex);
        return this.pattern.matcher(password).matches();
    }
}
