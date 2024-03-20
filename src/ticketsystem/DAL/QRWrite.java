package ticketsystem.DAL;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Paths;

public class QRWrite {

    public static void main(String[] args) {

        String data= "https://www.youtube.com/watch?v=U3sAkAWfxLY";
        String dest= "src/qrcode.jpeg";

        try {
            BitMatrix bm = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 600, 600);
            MatrixToImageWriter.writeToPath(bm, "jpg", Paths.get(dest));
        } catch (WriterException | IOException e){
            e.printStackTrace();
        }
    }
}
