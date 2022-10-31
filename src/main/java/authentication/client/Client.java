package authentication.client;

import authentication.server.controllers.AuthController;
import authentication.server.services.AuthService;

public class Client {
    public static void main(String[] args) {
        AuthController authController = AuthController.getInstance();
        authController.register("asd","asd@asd.com","Omar1999");

    }
}