package il.cshaifasweng.OCSFMediatorExample.utils;
import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.io.Serializable;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;


//***https://www.baeldung.com/java-email
public class Mail implements Serializable {
    private String systemMail = "SWE.SystemMail5@gmail.com";
    private String Password =  "System3811";


    public void sendMessage (String receiver, String title, String body){
        sendFromGMail(systemMail, Password, receiver, title, body);
    }

    // the appointment has been scheduled successfully
    public void Confirmation(String userMail, Appointment appointment){
        String message = "Dear \n\n" + "This letter confirms your appointment with DR."+appointment.getClinicMember().getLastName()+
                "on "+appointment.getTreatmentDateTime().toLocalDate().toString()+"at "+appointment.getTreatmentDateTime().toLocalTime()+".";
        sendMessage(appointment.getPatient().getEmail(), "Your Appointment Is Confirmed", message);

    }

    // tomorrow you have an appointment
    public void ReminderTW(String userMail, Appointment appointment){
        String message = "Dear \n\n" + "We wanted to remind you that you have an appointment with Dr."+appointment.getClinicMember().getLastName()+
                " tomorrow at" + appointment.getTreatmentDateTime().toLocalTime().toString()+".";
        sendMessage(appointment.getPatient().getEmail(), "Reminder ~ Tomorrow's appointment", message);
    }

    // we would use it to send mails to patients that sheduled an appointment
    // but their appointment has been canceled as a result of changing the services hours
    public void forcedCancel(String userMail, Appointment appointment){
        String message = "Dear \n\n" + "Due to some unforeseen circumstances, we're forced to cancel your appointment which was scheduled on"+appointment.getTreatmentDateTime().toString()+".";
        sendMessage(appointment.getPatient().getEmail(), "Forced Canceling", message);
    }

    private static void sendFromGMail(String from, String pass, String to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}
