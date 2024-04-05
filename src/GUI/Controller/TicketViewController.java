package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TicketViewController {

    public Button hideTicket;

    public void onClickHideTicket(ActionEvent actionEvent) {
        Stage stage = (Stage) hideTicket.getScene().getWindow();
        stage.close();
    }
}
