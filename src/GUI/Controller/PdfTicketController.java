package GUI.Controller;

import BE.PurchasedTickets;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;
import java.io.*;

public class PdfTicketController {
    public ImageView imageQR;
    public Label labelEventName;
    public Label labelLocation;
    public Label labelTicketType;
    public Label labelStartDate;
    public Label labelStartTime;
    public Label labelEmail;
    public AnchorPane paneInner;
    public AnchorPane paneTicket;

    private PurchasedTickets purchasedTickets;

    public void setPaneTicket(AnchorPane paneTicket) {
        this.paneTicket = paneTicket;
    }


    public void upDateInformation(PurchasedTickets purchasedTickets) {
        if (purchasedTickets != null) {
            labelEventName.setText(purchasedTickets.getEventTitle());
            labelLocation.setText(purchasedTickets.getEventLocation());
            labelTicketType.setText(purchasedTickets.getTicketTypeTitle());
            labelStartDate.setText(purchasedTickets.getStartDate());
            labelStartTime.setText(purchasedTickets.getStartTime());
            labelEmail.setText(purchasedTickets.getEmailString());

        }


    }

    public void generateQR(PurchasedTickets purchasedTickets) {
        if (purchasedTickets != null) {
            String data = purchasedTickets.getQrCode();
        }


    }

    public void generatePdf(AnchorPane paneTicket) throws IOException {

        paneTicket.applyCss();
        paneTicket.layout();

        // Logning af dimensionerne af paneTicket
        System.out.println("Width of paneTicket: " + paneTicket.getWidth());
        System.out.println("Height of paneTicket: " + paneTicket.getHeight());
        // Opret en WritableImage af din FXML-scene
        WritableImage snapshot = new WritableImage((int) paneTicket.getWidth(), (int) paneTicket.getHeight());
        paneTicket.snapshot(new SnapshotParameters(), snapshot);

        // Konverter WritableImage til BufferedImage
        Image fxImage = snapshot;
        BufferedImage awtImage = SwingFXUtils.fromFXImage(fxImage, null);

        // Opret en ny PDF
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Indsæt billedet af FXML-scenen i PDF'en
        PDImageXObject pdImage = LosslessFactory.createFromImage(document, awtImage);
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.drawImage(pdImage, 0, 0);
        }

        // Gem PDF-filen
        document.save("C:\\Users\\asbjo\\IdeaProjects\\EventMaster\\Resources\\PDF\\output.pdf");
        System.out.println("PDF created");
        document.close();
    }

    public static void main(String[] args) throws IOException {





    }



}
