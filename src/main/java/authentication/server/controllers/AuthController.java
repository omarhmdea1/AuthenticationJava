package authentication.server.controllers;

import authentication.server.controllers.Utils.Validetor;
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
        this.checkEmailAndPassword(email, password);
        if(! Validetor.isValidName(name)) throw new IllegalArgumentException("Invalid name");
        authService.createNewUser(name, email, password);
    }

    public void logIn(String email, String password) {
        this.checkEmailAndPassword(email, password);
        Map<String, String> tokenMail = authService.validateUserCredentials(email,password);
        String token = tokenMail.get(0);
        //authService.logIn(email, password);
    }
    public void checkEmailAndPassword(String email, String password) {
        if(!Validetor.isValidEmail(email)) throw new IllegalArgumentException("Invalid email");
        if(!Validetor.isValidPassword(password)) throw new IllegalArgumentException("Invalid password");
    }


}
