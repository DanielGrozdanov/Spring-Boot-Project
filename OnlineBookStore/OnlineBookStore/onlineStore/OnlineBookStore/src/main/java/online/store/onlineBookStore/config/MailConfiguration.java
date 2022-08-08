package online.store.onlineBookStore.config;

import online.store.onlineBookStore.utility.email.OrderFileDetails;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Component
public class MailConfiguration {

    public static void sendEmail(String receiver) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        String ownerAccount = "dani.grozdanov@gmail.com";
        String ownerPassword = "topywjodfpdgqrtn";

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ownerAccount, ownerPassword);
            }
        });
        Message message = mapEmail(receiver, ownerAccount, session);
        if (message == null) {
            message = mapEmail(receiver, ownerAccount, session);
        }
        assert message != null;
        Transport.send(message);

    }

    private static Message mapEmail(String receiver, String ownerAccount, Session session) {

        OrderFileDetails orderFileDetails = new OrderFileDetails();
        try {
            orderFileDetails.getFile();

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(ownerAccount));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            msg.setSubject("Online Book Store - Order Information");
            msg.setSentDate(new Date());
            msg.setText(orderFileDetails.getOrderContent());


            return msg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
