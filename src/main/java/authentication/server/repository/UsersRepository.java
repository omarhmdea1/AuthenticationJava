package authentication.server.repository;

import authentication.server.User.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UsersRepository {
    public static void writeUserToRepo(User newUser){
        if(newUser==null){
            throw new NullPointerException("User to write can't be null.");
        }
        String fileName = String.valueOf(newUser.getId()) + ".json";
        Map<String,String> userMap = new HashMap<>();
        userMap.put("id",String.valueOf(newUser.getId()));
        userMap.put("name", newUser.getName());
        userMap.put("email", newUser.getEmail());
        userMap.put("password", newUser.getPassword());
        ReadWriteToJson.writeToJson(fileName, userMap);
    }

    public static boolean userIsValid(String email, String password) {
        //todo
        return false;
    }

    public static boolean emailIsFree(String email) {
        //todo
        return false;
    }
}
