package authentication.server.repository;

import authentication.server.User.User;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UsersRepository {

    private Map<Integer, User> userMap;
    private static UsersRepository instance;

    private UsersRepository() {
        userMap = new HashMap<>();
        this.loadMap();
    }

    public static UsersRepository getInstance() {
        if (instance == null) {
            instance = new UsersRepository();
        }
        return instance;
    }

    private void loadMap() {
        String userDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(userDirectory), "*.json")) {
            Map<String, String> user;
            for (Path p : stream) {
                user = ReadWriteToJson.readFromJson(p.toString());
                if (user != null && !user.isEmpty() && user.containsKey("id") && user.containsKey("name") && user.containsKey("email") && user.containsKey("password")) {
                    this.userMap.put(Integer.parseInt(user.get("id")), new User(Integer.parseInt(user.get("id")), user.get("name"), user.get("email"), user.get("password")));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeUserToRepo(User newUser) {
        String fileName = String.valueOf(newUser.getId()) + ".json";
        Map<String, String> user = new HashMap<>();
        user.put("id", String.valueOf(newUser.getId()));
        user.put("name", newUser.getName());
        user.put("email", newUser.getEmail());
        user.put("password", newUser.getPassword());
        ReadWriteToJson.writeToJson(fileName, user);
        this.userMap.put(Integer.parseInt(user.get("id")), new User(Integer.parseInt(user.get("id")), user.get("name"), user.get("email"), user.get("password")));
    }

    public Optional<User> readUserFromRepo(String userEmail) {
        if (userMap != null && !userMap.isEmpty()) {
            for (User user : userMap.values()) {
                if (user.getEmail().equals(userEmail)) {
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }


    public boolean userIsValid(String email, String password) {
        //todo
        return false;
    }

    public boolean emailIsFree(String email) {
        //todo
        return false;
    }
}
