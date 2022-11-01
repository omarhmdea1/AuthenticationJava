package authentication.server.controllers;

import authentication.server.controllers.Utils.Validetor;
import authentication.server.services.AuthService;
import java.util.regex.Pattern;

public class AuthController {
    private Pattern pattern;
    private static AuthController instance;
    private static AuthService authService;

    private AuthController() {
        authService = AuthService.getInstance();
    }

    public static AuthController getInstance() {
        if(AuthController.instance == null) {
            instance = new AuthController();
        }
        return instance;
    }

    public void register(String name, String email, String password) {
        if(! Validetor.isValidName(name)) throw new IllegalArgumentException("Invalid name");
        this.checkEmailAndPassword(email, password);
        authService.createNewUser(name, email, password);
    }

    public String logIn(String email, String password) {
        this.checkEmailAndPassword(email, password);
        return authService.validateUserCredentials(email,password);
    }
    public void checkEmailAndPassword(String email, String password) {
        if(!Validetor.isValidEmail(email)) throw new IllegalArgumentException("Invalid email");
        if(!Validetor.isValidPassword(password)) throw new IllegalArgumentException("Invalid password");
    }
}
