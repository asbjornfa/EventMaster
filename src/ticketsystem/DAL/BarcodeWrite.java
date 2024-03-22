package ticketsystem.DAL;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class BarcodeWrite {

    public static void main(String[] args) {

        String data = "123456789"; // Den data vi skal have ind i vores barcode
        String dest = "src/barcode.png"; // stedet vi lagrer det

        try {
            BitMatrix bm = new MultiFormatWriter().encode(data, BarcodeFormat.CODE_39, 400, 200);
            Path path = FileSystems.getDefault().getPath(dest);
            MatrixToImageWriter.writeToPath(bm, "png", path);
            System.out.println("Barcode has been generated successfully.");
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}
