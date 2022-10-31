package authentication.server.services;

import authentication.server.User.User;

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


    public void updateUserDetails(String token, String field) {
        User user = getUserByToken(token);

        switch(field) {
            case "name":
                user.setName(field);
                break;
            case "email":
                user.setEmail(field);
                break;
            case "password":
                user.setPassword(field);
                break;
        }
        //UsersRepository.updateUserDetails(user);
    }


    public void delete(String token) {
        User user = getUserByToken(token);
        //UsersRepository.deletUser(user);
    }


    public boolean isValidToken(String token) {
        return authService.isValidToken(token);
    }


    private User getUserByToken(String token) {
        //todo
        return null;
    }

}
