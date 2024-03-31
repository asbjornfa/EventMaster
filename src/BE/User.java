package BE;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String username;

    public User(int id, String name, String email, String password, String username){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User(int id, String name, String email, String username){
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public User(String name, String email, String password, String username) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User(String username, String storedPassword) {
        this.username = username;
        this.password = storedPassword;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
