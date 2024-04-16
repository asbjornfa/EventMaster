package BE;

public class Positions {

    private int id;
    private String position;


    public Positions(int id, String position) {
        this.id = id;
        this.position = position;
    }

    public Positions(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
