package authentication.server.services;

import authentication.server.User.User;
import authentication.server.repository.UsersRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class AuthService {
    private Map<String, String> tokenEmail;
    private static AuthService instance;
    private UsersRepository usersRepository;

    private AuthService() {
        tokenEmail = new HashMap<>();
        usersRepository = UsersRepository.getInstance();
    }
    public static AuthService getInstance() {
        if(instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public void createNewUser(String name, String email, String password){
        if(!UsersRepository.emailIsFree(email)){
            throw new Error("Email is occupied, please enter a different one");
        }
        User newUser = new User(createId(), name, email, password);
        UsersRepository.writeUserToRepo(newUser);
    }

    public Map<String, String> validateUserCredentials(String email, String password){
        if(!UsersRepository.userIsValid(email, password)){
            throw new Error("One or more details are incorrect");
        }
        tokenEmail.put(createToken(), email);
        return tokenEmail;
    }

    public boolean isValidToken(String token){
        return tokenEmail.containsKey(token);
    }

    private String createToken(){
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder(6);
        for (int i = 0; i < 6; i++){
            stringBuilder.append(chars.charAt(ThreadLocalRandom.current().nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }

    private int createId(){
        return 0;
    }
}
