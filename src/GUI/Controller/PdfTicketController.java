package GUI.Controller;

import BE.PurchasedTickets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PdfTicketController {
    public ImageView imageQR;
    public Label labelEventName;
    public Label labelLocation;
    public Label labelTicketType;
    public Label labelStartDate;
    public Label labelStartTime;
    public Label labelEmail;

    private PurchasedTickets purchasedTickets;


    public void upDateInformation(PurchasedTickets purchasedTickets){
        if (purchasedTickets != null) {
            labelEventName.setText(purchasedTickets.getEventTitle());
            labelLocation.setText(purchasedTickets.getEventLocation());
            labelTicketType.setText(purchasedTickets.getTicketTypeTitle());
            labelStartDate.setText(purchasedTickets.getStartDate());
            labelStartTime.setText(purchasedTickets.getStartTime());
            labelEmail.setText(purchasedTickets.getEmailString());

        }


    }

    public static void generatePdf(String filename, String content) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText(content);
                contentStream.endText();
            }
            document.save(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
