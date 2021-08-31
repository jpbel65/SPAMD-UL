package ca.ulaval.glo4003.projet.base.ws.infrastructure.delivery;

import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.InvalidEmailAddressException;
import ca.ulaval.glo4003.projet.base.ws.domain.delivery.AddressBook;
import ca.ulaval.glo4003.projet.base.ws.application.delivery.DeliveryProcedure;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailDelivery implements DeliveryProcedure {

    private static final String senderEmail = "ulaval74Archi@gmail.com";
    private static final String senderPassword = "bhxsuwvnpazchdxq";

    @Override
    public void sendPermitId(AddressBook addressBook, String permitId) {
        System.out.println("Sending Email To " + addressBook.getAddress());
        Session session = createSession();

        MimeMessage message = new MimeMessage(session);

        try{
            prepareEmailMessage(message, addressBook.getAddress(), permitId);
            Transport.send(message);
            System.out.println("Done");
        }catch (MessagingException e){
            System.out.println("Fail");
            throw new InvalidEmailAddressException(addressBook.getAddress());
        }
    }

    private void prepareEmailMessage(MimeMessage message, String emailAddress, String parkingPermitId) throws MessagingException {
        message.setContent(parkingPermitId, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
        message.setSubject("Your Parking Permit");
    }

    private Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");//Outgoing server requires authentication
        props.put("mail.smtp.starttls.enable", "true");//TLS must be activated
        props.put("mail.smtp.host", "smtp.gmail.com"); //Outgoing server (SMTP) - change it to your SMTP server
        props.put("mail.smtp.port", "587");//Outgoing port

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
    }
}
