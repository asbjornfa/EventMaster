package GUI.Controller;

import BE.PurchasedTickets;
import com.google.zxing.qrcode.encoder.QRCode;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.image.ImageView;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

    public void generateQR(PurchasedTickets purchasedTickets) {
        if (purchasedTickets != null) {
            String data = purchasedTickets.getQrCode();
        }


    }

    public void generatePdf(String filename, String content, Node node) throws IOException {
        //int startX = 0;
        //int startY =
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText(content);
                contentStream.endText();

                WritableImage snapshot = node.snapshot(new SnapshotParameters(), null);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", out);
                out.close();
                InputStream in = new ByteArrayInputStream(out.toByteArray());
                PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, IOUtils.toByteArray(in), "snapshot");
                //contentStream.drawImage(pdImage, startX, startY, width, height);
            }
            document.save(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
