package BE;


public class Ticket_type {
    private int id;
    private String title;


    public Ticket_type(int id, String title) {
        this.id = id;
        this.title = title;

    }
    public Ticket_type (String title){
        this.title = title;
    }

    public Ticket_type(int id) {
        this.id = id;

    }

    public int getId() {
return id;    }


    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
