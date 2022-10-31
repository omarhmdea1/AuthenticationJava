package authentication.server.controllers;

import authentication.server.controllers.Utils.Validetor;
import authentication.server.services.UserService;

public class UserController {
    private static UserService userService;
    private static UserController instance;
    private UserController() {
        userService = UserService.getInstance();
    }

    public static UserController getInstance() {
        if(instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public void updateName(String token, String name) {
        if(! checkAuth(token)) {
            throw new IllegalArgumentException("Invalid token");
        }
        if(! Validetor.isValidName(name)) {
            throw new IllegalArgumentException("Invalid name");
        }
        userService.updateUserDetails(token, name);
    }

    public void updateEmail(String token, String email) {
        if(! checkAuth(token)) {
            throw new IllegalArgumentException("Invalid token");
        }
        if(!Validetor.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid name");
        }
        userService.updateUserDetails(token, email);
    }

    public void updatePassword(String token, String password) {
        if(! checkAuth(token)) {
            throw new IllegalArgumentException("Invalid token");
        }
        if(!Validetor.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid name");
        }
        userService.updateUserDetails(token, password);
    }

    public void delete(String token) {
        if(! checkAuth(token)) {
            throw new IllegalArgumentException("Invalid token");
        }
        userService.delete(token);
    }

    private boolean checkAuth(String token) {
        return userService.isValidToken(token);
    }

}
