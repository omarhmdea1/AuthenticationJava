package authentication.server.services;

import authentication.server.User.User;
import authentication.server.repository.UsersRepository;

public class UserService {
    private static UserService instance;
    private static AuthService authService;
    private static UsersRepository usersRepository;

    private UserService() {
        authService = AuthService.getInstance();
        usersRepository = UsersRepository.getInstance();
    }

    public static UserService getInstance() {
        if(instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void updateUserDetails(int id, Fields field, String change) {
        User user = usersRepository.getUserById(id).orElseThrow(NullPointerException::new);

        switch(field) {
            case NAME:
                user.setName(change);
                break;
            case EMAIL:
                user.setEmail(change);
                break;
            case PASSWORD:
                user.setPassword(change);
                break;
        }
        //usersRepository.updateUserDetails(user);
    }

    public void delete(int id) {
        //usersRepository.deleteUser(id);
    }
}
