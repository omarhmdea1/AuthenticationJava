package authentication.client;

import authentication.server.User.User;
import authentication.server.controllers.AuthController;
import authentication.server.controllers.UserController;
import authentication.server.repository.UsersRepository;
import authentication.server.services.AuthService;

import java.util.Optional;

public class Client {
    public static void main(String[] args) {
        AuthController authController = AuthController.getInstance();
        UserController userController = UserController.getInstance();

        authController.register("asd", "asd@asd.com", "Omar1999$");
        String token = authController.logIn("asd@asd.com", "Omar1999$");

        System.out.println(token);
        //userController.updateName(token, "Eden");

    }
}