package BE;


public class TicketType {
    private int id;
    private String title;


    public TicketType(int id, String title) {
        this.id = id;
        this.title = title;

    }
    public TicketType(String title){
        this.title = title;
    }

    public TicketType(int id) {
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
