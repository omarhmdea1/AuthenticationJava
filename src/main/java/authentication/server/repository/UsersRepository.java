package authentication.server.repository;

import authentication.server.User.User;

import java.io.File;
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
        String PATH = new File("").getAbsolutePath() + "\\src\\main\\java\\authentication\\server\\repository\\usersData\\";
        //String userDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(PATH), "*.json")) {
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
        String PATH = new File("").getAbsolutePath() + "\\src\\main\\java\\authentication\\server\\repository\\usersData\\";
        String fileName = PATH + String.valueOf(newUser.getId()) + ".json";
        Map<String, String> user = new HashMap<>();
        user.put("id", String.valueOf(newUser.getId()));
        user.put("name", newUser.getName());
        user.put("email", newUser.getEmail());
        user.put("password", newUser.getPassword());
        ReadWriteToJson.writeToJson(fileName, user);
        this.userMap.put(Integer.parseInt(user.get("id")), new User(Integer.parseInt(user.get("id")), user.get("name"), user.get("email"), user.get("password")));
    }

    public Optional<User> readUserFromRepo(String userEmail) {
        if (this.userMap != null && !this.userMap.isEmpty()) {
            for (User user : this.userMap.values()) {
                if (user.getEmail().equals(userEmail)) {
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }

    public Optional<User> getUserById(int id) {
        if (this.userMap != null && !this.userMap.isEmpty()) {
            return Optional.of(this.userMap.get(id));
        }
        return Optional.empty();
    }

    public int userIsValid(String email, String password) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user.getId();
            }
        }
        return -1;
    }

    public boolean emailIsFree(String email) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public void deleteUser(int id) {
        String PATH = new File("").getAbsolutePath() + "\\src\\main\\java\\authentication\\server\\repository\\usersData\\";
        File file = new File(PATH + id + ".json");
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
            userMap.remove(id);
        } else {
            System.out.println("Can't delete file. File " + file.getName() + " doesn't exist.");
        }
    }

    public void updateUserDetails(User user) {
        if (this.userMap != null && !this.userMap.isEmpty()) {
            User editedUser = userMap.get(user.getId());
            editedUser.setName(user.getName());
            editedUser.setEmail(user.getEmail());
            editedUser.setPassword(user.getPassword());
            writeUserToRepo(editedUser);
        }
    }
}
