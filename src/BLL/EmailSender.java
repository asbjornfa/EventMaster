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

    private static String API_KEY = "config/config.emailapi";
    private Resend resend;

    public EmailSender() {
        String API = "";
        try {
            API = new String(Files.readAllBytes(Paths.get(API_KEY)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        resend = new Resend(API.trim());
    }


    public void Setup() throws ResendException {

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("EasvBar<EasvBar@leet.dk>")
                .to("asbjornfa@gmail.com")
                .subject("Easv-Bar")
                .html("<strong>Her er din Billet til eventet</strong>")
                .build();

        resend.emails().send(sendEmailRequest);
    }


    public boolean sendEmailWithAttachment(String recipient, String subject, String content, Attachment attachment) throws ResendException {
        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("EASV Event <EASV@leet.dk>")
                .to(recipient)
                .subject(subject)
                .addAttachment(attachment)
                .html(content)
                .build();
        try {
            resend.emails().send(sendEmailRequest);
            return true;
        } catch (ResendException e) {
            throw new ResendException("Error occurred trying to send email:\n" + e);
        }
    }

    public Attachment addImageAttachment(File file) throws IOException {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        String encodedContent = Base64.getEncoder().encodeToString(fileContent);
        return Attachment.builder()
                .fileName(file.getName())
                .content(encodedContent)
                .build();
    }
}