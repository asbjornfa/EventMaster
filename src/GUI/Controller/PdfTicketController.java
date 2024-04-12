package GUI.Controller;

import DTO.TicketDTO;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PdfTicketController {
    public ImageView imageQR;
    public Label labelEventName;
    public Label labelLocation;
    public Label labelStartDate;
    public Label labelStartTime;
    public Label labelTicketType;

    private TicketDTO ticketDTO;


    public void upDateInformation(String eventName, String location, String startDate, String startTime, int ticketPrice){
        labelEventName.setText(eventName);
        labelLocation.setText(location);
        labelStartDate.setText(startDate);
        labelStartTime.setText(startTime);


    }

    /*public void GenerateQRCode() {
        String qrCodeText = UUID.randomUUID().toString(); // Laver en unik streng
        ByteArrayOutputStream out = QRCode
    }*/


}
