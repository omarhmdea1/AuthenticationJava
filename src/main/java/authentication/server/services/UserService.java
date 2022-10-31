package authentication.server.services;

public class UserService {

    private static UserService instance;
    private static AuthService authService;


    private UserService() {
        //authService = AuthService.getInstance();
    }

    public static UserService getInstance() {
        if(instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void updateEmail(String token, String eamil) {
        // todo
    }

    public void updateName(String token, String name) {
        // todo
    }

    public void updatePassword(String token, String password) {
        // todo
    }

    public void delete(String token) {
        // todo
    }

    public boolean isValidToken(String token) {
        return authService.isValidToken(token);
    }
}
