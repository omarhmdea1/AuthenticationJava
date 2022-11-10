package authentication.server.controllers;

import authentication.server.controllers.Utils.Validator;
import authentication.server.services.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthController {
    private static AuthController instance;
    private static AuthService authService;
    private static Logger logger = LogManager.getLogger(AuthController.class.getName());


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
        Validator.isValidName(name, logger);
        this.checkEmailAndPassword(email, password);
        authService.createNewUser(name, email, password);
    }

    public String logIn(String email, String password) {
        this.checkEmailAndPassword(email, password);
        return authService.validateUserCredentials(email,password);
    }

    public void checkEmailAndPassword(String email, String password) {
        Validator.isValidEmail(email, logger);
        Validator.isValidPassword(password, logger);
    }
}
