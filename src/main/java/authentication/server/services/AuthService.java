package authentication.server.services;

import authentication.server.User.User;
import authentication.server.repository.UsersRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class AuthService {
    private final Map<String, Integer> tokenId;
    private static AuthService instance;
    private final UsersRepository usersRepository;


    private AuthService() {
        tokenId = new HashMap<>();
        usersRepository = UsersRepository.getInstance();
    }
    public static AuthService getInstance() {
        if(instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public void createNewUser(String name, String email, String password){
        usersRepository.emailIsFree(email);
        User newUser = new User(createId(), name, email, password);
        usersRepository.writeUserToRepo(newUser);
    }

    public String validateUserCredentials(String email, String password){
        int id = usersRepository.userIsValid(email, password);
        String token = createToken();
        tokenId.put(token, id);
        return token;
    }

    public int isValidToken(String token){
        if(tokenId.containsKey(token)) {
            return tokenId.get(token);
        }
        throw new Error("Invalid token");
    }

    private String createToken(){
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder;
        do {
            stringBuilder = new StringBuilder(6);
            for (int i = 0; i < 6; i++) {
                stringBuilder.append(chars.charAt(ThreadLocalRandom.current().nextInt(chars.length())));
            }
        }
        while (tokenId.get(stringBuilder) != null);
        return stringBuilder.toString();
    }

    private int createId(){
        int newId;
        do {
            newId = ThreadLocalRandom.current().nextInt(999);
        }
        while(!usersRepository.idIsFree(newId));
        return newId;
    }

    public boolean emailIsFree(String email){
        return usersRepository.emailIsFree(email);
    }
}
