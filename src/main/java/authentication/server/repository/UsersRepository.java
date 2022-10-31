package authentication.server.repository;

import authentication.server.User.User;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class UsersRepository {

    private Map<Integer, User> userMap;
    private static UsersRepository instance;

    private UsersRepository(){
        userMap =new HashMap<>();
        //this.loadMap();
        //you can use readUserFromRepo to load Map
    }

    public static UsersRepository getInstance() {
        if(instance == null) {
            instance = new UsersRepository();
        }
        return instance;
    }
    //change to Non-static
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

    //change to Non-static
    public static Optional<User> readUserFromRepo(String userEmail){
        String userDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(userDirectory), "*.json")) {
            Map<String,String> userMap;
            for (Path p : stream) {
                System.out.println(p.toString());
                userMap = ReadWriteToJson.readFromJson(p.toString());
                if(userMap != null && !userMap.isEmpty() && userMap.containsKey("email")) {
                    if (userMap.get("email").equals(userEmail)) {
                        return Optional.of(new User(Integer.parseInt(userMap.get("id")), userMap.get("name"), userMap.get("email"), userMap.get("password")));
                    }
                }
            }
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //change to Non-static

    public static boolean userIsValid(String email, String password) {
        //todo
        return false;
    }
    //change to Non-static

    public static boolean emailIsFree(String email) {
        //todo
        return false;
    }
}
