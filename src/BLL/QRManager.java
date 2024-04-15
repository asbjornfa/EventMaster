package BLL;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class QRManager {
    //https://www.baeldung.com/java-generating-barcodes-qr-codes

    // unikke koder pr QR
    public static UUID generateUniqueUUID(String args) {
        return UUID.nameUUIDFromBytes(args.getBytes());
    }

    // bruges til 2d qr koder
    public static BufferedImage generate2DQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    // bruges til 1d qr koder
    public static BufferedImage generate1DCodeImage(String barcodeText) throws WriterException {
        Code128Writer barcodeWriter = new Code128Writer();
        BitMatrix bitMatrix;
        bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_128, 400, 100);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static File saveImage(BufferedImage billede, String fileName) {
        File output = new File(fileName);

        try {
            ImageIO.write(billede, "PNG", output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return output;
    }

    public static File getQrCodeFile(String uniqueText) throws Exception {
        BufferedImage twoD = generate2DQRCodeImage(uniqueText); // 4bc3e2cc-4a29-3ec2-a050-1967acbce4db
        BufferedImage oneD = generate1DCodeImage(uniqueText);   // 4bc3e2cc-4a29-3ec2-a050-1967acbce4db

        //BufferedImage kombineretBilleder = bla blala
        return saveImage(twoD, uniqueText + ".png");
    }

    public static File getOneQrCodeFile(BufferedImage file, String uniqueText) {
        return saveImage(file, uniqueText + ".png");
    }

        public static void main(String[] args) throws Exception {
        // kaldes
        UUID unique = generateUniqueUUID("FredagsBar-EventTicket-patrick@hotmail.dk");

        // gem i db + brug til generering af barkoder
        String uniqueText = unique.toString();

        //File qrCode = getQrCodeFile(uniqueText);

        File getOne = getOneQrCodeFile(generate1DCodeImage(uniqueText), uniqueText);

        System.out.println(uniqueText);
        System.out.println(getOne.getAbsolutePath());

    }
}
