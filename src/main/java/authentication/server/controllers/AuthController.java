package authentication.server.controllers;

import authentication.server.controllers.Utils.Validator;
import authentication.server.services.AuthService;

public class AuthController {
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
        Validator.isValidName(name);
        this.checkEmailAndPassword(email, password);
        authService.createNewUser(name, email, password);
    }

    public String logIn(String email, String password) {
        this.checkEmailAndPassword(email, password);
        return authService.validateUserCredentials(email,password);
    }

    public void checkEmailAndPassword(String email, String password) {
        Validator.isValidEmail(email);
        Validator.isValidPassword(password);
    }
}
