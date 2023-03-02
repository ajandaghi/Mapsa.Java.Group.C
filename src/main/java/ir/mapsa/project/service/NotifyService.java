package ir.mapsa.project.service;


import ir.mapsa.project.entity.Customer;
import ir.mapsa.project.entity.Transaction;
import ir.mapsa.project.repository.CustomerRepository;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.List;
import java.util.Properties;

public class NotifyService implements NotifyBase  {

   private  CustomerRepository customerRepository;

    public NotifyService( ) {
        this.customerRepository = new CustomerRepository();
    }

    @Override
    public void notify(Transaction transaction, NotifyType type) throws Exception {
        List<Customer> customers=customerRepository.getAll();
        Customer customer1=customers.stream().filter(a->a.getCardNo().equals(transaction.getSenderCardNumber())).findFirst().get();
        Customer customer2=customers.stream().filter(a->a.getCardNo().equals(transaction.getRecieverCardNumber())).findFirst().get();
        if(type==NotifyType.SMS){
            sendSMS(transaction);
        }
        else if (type==NotifyType.EMAIL){
            sendEmail(transaction,customer1,TransationType.WITHDRAW);
            sendEmail(transaction,customer2,TransationType.DEPOSIT);

        }
    }

    private void sendEmail(Transaction transaction,Customer customer,TransationType type) throws Exception {


        // Recipient's email ID needs to be mentioned.
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        //prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        //prop.put("mail.smtp.socketFactory.class","org.eclipse.angus.mail.util.SocketFetcher");
        prop.put("mail.debug", "true");
        prop.put("mail.smtp.ssl.enable", "true");


        prop.put("mail.smtp.port", "465");
        // prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        Session session = Session.getInstance(prop ,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ali.jandaghi.6161@gmail.com", "ujlpnyamutkzfdpy");
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("info@mybank.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(customer.getEmail()));
        message.setSubject("new Transaction: "+transaction.getAmount()+" "+type);


        String msg =transaction.toString()+ "\n Current Balance is: "+customer.getBalance();

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);
        Transport.send(message);

    }

    private void sendSMS(Transaction transaction) {
    }
}
