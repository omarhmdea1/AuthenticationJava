package authentication.server.repository;

import authentication.server.User.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class UsersRepository {
    public static void writeUserToRepo(User newUser){
        String fileName = String.valueOf(newUser.getId()) + ".json";
        Map<String,String> userMap = new HashMap<>();
        userMap.put("id",String.valueOf(newUser.getId()));
        userMap.put("name", newUser.getName());
        userMap.put("email", newUser.getEmail());
        userMap.put("password", newUser.getPassword());

        RepoWriter.writeToJson(fileName, userMap);
    }
}
