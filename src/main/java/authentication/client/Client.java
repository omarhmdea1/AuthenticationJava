package authentication.client;

import authentication.server.controllers.AuthController;
import authentication.server.controllers.UserController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Client {
    public static Logger logger = LogManager.getLogger(Client.class.getName());

    public static void main(String[] args) {
        AuthController authController = AuthController.getInstance();
        UserController userController = UserController.getInstance();

        authController.register("omar", "omar@gmail.com", "Omar1999$");
        authController.register("daria", "daria@gmail.com", "Daria123$");

        String token = authController.logIn("omar@gmail.com", "Omar1999$");
        String token2 = authController.logIn("daria@gmail.com", "Daria123$");

        userController.updateName(token, "Eden");
        userController.updateEmail(token, "Eden@gmail.com");
        userController.updatePassword(token, "Eden123$");

        userController.delete(token2);


    }
}