package BE;

public class Ticket_type {
    private int id;
    private String title;


    public Ticket_type(int id, String title) {
        this.id = id;
        this.title = title;

    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
