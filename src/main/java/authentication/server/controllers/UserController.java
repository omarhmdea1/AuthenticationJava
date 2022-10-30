package authentication.server.controllers;

import authentication.server.services.UserService;

import java.util.regex.Pattern;

public class UserController {

    private static UserService userService;
    private Pattern pattern;


    public void updateName(String token, String name) {

        if(! checkAuth(token)) {
            throw new IllegalArgumentException("Invalid token");
        }
        if(! isValidName(name)) {
            throw new IllegalArgumentException("Invalid name");
        }

        //userService.updateName(token, name);
    }


    public void updateEmail(String token, String email) {
        if(! checkAuth(token)) {
            throw new IllegalArgumentException("Invalid token");
        }
        if(! isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid name");
        }

        //userService.updateEmail(token, name);
    }


    public void updatePassword(String token, String password) {
        if(! checkAuth(token)) {
            throw new IllegalArgumentException("Invalid token");
        }
        if(! isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid name");
        }

        //userService.updatePassword(token, name);
    }


    public void delete(String token) {
        if(! checkAuth(token)) {
            throw new IllegalArgumentException("Invalid token");
        }

        //userService.delete(token);
    }


    private boolean checkAuth(String token) {
        return true; //userService.isValidToken(token);
    }


    private boolean isValidName(String name) {
        if(name == null) {
            return false;
        }

        String regex = "^[A-Za-z]\\w{5,29}$";

        this.pattern = Pattern.compile(regex);
        return this.pattern.matcher(name).matches();
    }


    private boolean isValidEmail(String email) {
        if(email == null) {
            return false;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        this.pattern = Pattern.compile(emailRegex);
        return this.pattern.matcher(email).matches();
    }


    private boolean isValidPassword(String password) {

        if(password == null) {
            return false;
        }

        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        this.pattern = Pattern.compile(regex);
        return this.pattern.matcher(password).matches();
    }
}
