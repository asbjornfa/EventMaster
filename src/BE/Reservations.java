package BE;

public class Reservations {

    private int id;
    private String email;


    public Reservations(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public Reservations(String email) {
        this.email = email;
    }

    public Reservations(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
