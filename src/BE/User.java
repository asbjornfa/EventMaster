package BE;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String username;
    private String position;
    private int positionId;

    public User(int id, String name, String email, String password, String username, int positionId){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.positionId = positionId;
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

    public User(int id, String name, String email, String username, String position) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.position = position;
    }

    public User(String name, String email, String username, int positionId) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.positionId = positionId;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
}
