package authentication.server.services;

import authentication.server.User.User;
import authentication.server.repository.UsersRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class AuthService {
    public void validateRegister(int id, String name, String email, String password){

    }
    public void createNewUser(int id, String name, String email, String password){
        if(!UsersRepository.emailIsFree(email)){
            throw  new Error("Email is occupied, please enter a different one");
        }
        User newUser = new User(id, name, email, password);
        UsersRepository.writeUserToRepo(newUser);
    }

    public static Map<String, String> validateUserCredentials(String email, String password){
        if(!UsersRepository.userIsValid(email, password){
            throw new Error("One or more details are incorrect");
        }
        Map<String, String> tokenEmail = new HashMap<>();
        tokenEmail.put(createToken(), email);
        return tokenEmail;
    }

    private String createToken(){
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder(6);
        for (int i = 0; i < 6; i++){
            stringBuilder.append(chars.charAt(ThreadLocalRandom.current().nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }
}
