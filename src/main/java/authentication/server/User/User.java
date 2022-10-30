package authentication.server.User;

public class User {
    private final int id;
    private final String name;
    private final String email;
    private final String password;

    public User(int id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
