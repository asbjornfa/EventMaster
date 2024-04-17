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


    public static UUID generateUniqueUUID(String args) {
        // Generate a UUID based on the given string
        return UUID.nameUUIDFromBytes(args.getBytes());
    }

    public static BufferedImage generate2DQRCodeImage(String barcodeText) throws Exception {
        // Create a QR Code writer
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        // Encode the barcode text into a BitMatrix
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        // Convert the BitMatrix into a BufferedImage and return
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static BufferedImage generate1DCodeImage(String barcodeText) throws WriterException {
        // Create a Code128 writer
        Code128Writer barcodeWriter = new Code128Writer();
        // Encode the barcode text into a BitMatrix
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_128, 400, 100);

        // Convert the BitMatrix into a BufferedImage and return
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static File saveImage(BufferedImage image, String fileName) {
        // Define the directory path to save the image
        String directoryPath = "Resources/Qrcodes/";
        // Create a File object for the output image
        File output = new File(directoryPath + fileName);

        try {
            // Write the image to the output file as PNG format
            ImageIO.write(image, "PNG", output);
        } catch (IOException e) {
            // Throw a runtime exception if an error occurs while saving the image
            throw new RuntimeException(e);
        }

        return output;
    }

    public static File getQrCodeFile(String uniqueText) throws Exception {
        // Generate 2D QR Code and 1D Code images based on the unique text
        BufferedImage twoD = generate2DQRCodeImage(uniqueText);
        BufferedImage oneD = generate1DCodeImage(uniqueText);

        // Save the 2D QR Code image and return the File object
        return saveImage(twoD, uniqueText + ".png");
    }

    public static File getOneQrCodeFile(BufferedImage image, String uniqueText) {
        // Save the provided image and return the File object
        return saveImage(image, uniqueText + ".png");
    }

}