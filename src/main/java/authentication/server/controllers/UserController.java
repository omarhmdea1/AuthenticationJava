package authentication.server.controllers;

import authentication.server.Utils.Validator;
import authentication.server.services.AuthService;
import authentication.server.services.Fields;
import authentication.server.services.UserService;

public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private static UserController instance;
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
        Validator.isValidName(name);
        userService.updateUserDetails(id, Fields.NAME, name);
    }

    public void updateEmail(String token, String email) {
        int id = isValidToken(token);
        Validator.isValidEmail(email);
        authService.emailIsFree(email);
        userService.updateUserDetails(id, Fields.EMAIL, email);
    }

    public void updatePassword(String token, String password) {
        int id = isValidToken(token);
        Validator.isValidPassword(password);
        userService.updateUserDetails(id, Fields.PASSWORD, password);
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
