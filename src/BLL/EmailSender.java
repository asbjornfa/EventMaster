package BLL;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.Attachment;
import com.resend.services.emails.model.SendEmailRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;


public class EmailSender {

    // API_KEY to access the email API, stored in a config file
    private static String API_KEY = "config/config.emailapi";
    private Resend resend;

    // Constructor to initialize the EmailSender object
    public EmailSender() {
        String API = "";
        try {
            // Read API key from the configuration file
            API = new String(Files.readAllBytes(Paths.get(API_KEY)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Initialize the Resend object with the API key
        resend = new Resend(API.trim());
    }

    // Method to set up email sending parameters and send the email
    public void Setup() throws ResendException {
        // Create a request to send an email without attachment
        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("EasvBar<EasvBar@leet.dk>")
                .to("EnMail@gmail.com")
                .subject("Easv-Bar")
                .html("<strong>Her er din Billet til eventet</strong>")
                .build();
        // Send the email using the Resend object
        resend.emails().send(sendEmailRequest);
    }

    // Method to send email with attachment
    public boolean sendEmailWithAttachment(String recipient, String subject, String content, Attachment attachment) throws ResendException {
        // Create a request to send an email with attachment
        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("EASV Event <EASV@leet.dk>")
                .to(recipient)
                .subject(subject)
                .addAttachment(attachment)
                .html(content)
                .build();
        try {
            // Send the email with attachment using the Resend object
            resend.emails().send(sendEmailRequest);
            return true;
        } catch (ResendException e) {
            // If sending fails, throw a ResendException with error message
            throw new ResendException("Error occurred trying to send email:\n" + e);
        }
    }

    // Method to create an image attachment from a file
    public Attachment addImageAttachment(File file) throws IOException {
        // Read file content as byte array
        byte[] fileContent = Files.readAllBytes(file.toPath());
        // Encode file content to Base64
        String encodedContent = Base64.getEncoder().encodeToString(fileContent);
        // Create and return an Attachment object with file name and encoded content
        return Attachment.builder()
                .fileName(file.getName())
                .content(encodedContent)
                .build();
    }
}