package authentication.server.controllers;

import authentication.server.Utils.Validator;
import authentication.server.services.AuthService;
import authentication.server.services.Fields;
import authentication.server.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private static UserController instance;
    private static Logger logger = LogManager.getLogger(UserController.class.getName());

    private UserController() {
        userService = UserService.getInstance();
        authService = AuthService.getInstance();
    }

    public static UserController getInstance() {
        if(instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public void updateName(String token, String name) {
        int id = this.isValidToken(token);
        Validator.isValidName(name, logger);
        userService.updateUserDetails(id, Fields.NAME, name);
        logger.info("You updated the name Successfully");
    }

    public void updateEmail(String token, String email) {
        int id = isValidToken(token);
        Validator.isValidEmail(email, logger);
        authService.emailIsFree(email);
        userService.updateUserDetails(id, Fields.EMAIL, email);
    }

    public void updatePassword(String token, String password) {
        int id = isValidToken(token);
        Validator.isValidPassword(password, logger);
        userService.updateUserDetails(id, Fields.PASSWORD, password);
        logger.info("You updated the password Successfully");
    }

    public void delete(String token) {
        int id = isValidToken(token);
        userService.delete(id);
    }

    public int isValidToken(String token) {
        int id = authService.isValidToken(token);
        return id;
    }
}
