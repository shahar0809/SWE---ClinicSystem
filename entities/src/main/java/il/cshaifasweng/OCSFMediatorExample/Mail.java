package il.cshaifasweng.OCSFMediatorExample;
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
        String message = "Dear \n\n" + "This letter confirms your appointment with DR. "+appointment.getMember().getLastName()+
                " on "+appointment.getTreatmentDateTime().toLocalDate().toString()+" at "+appointment.getTreatmentDateTime().toLocalTime()+".";
        sendMessage(appointment.getPatient().getEmail(), "Your Appointment Is Confirmed", message);

    }

    // tomorrow you have an appointment
    public void ReminderTW(String userMail, Appointment appointment){
        String message = "Dear \n\n" + "We wanted to remind you that you have an appointment with Dr."+appointment.getMember().getLastName() +
                " tomorrow " + appointment.getTreatmentDateTimeString()+".";
        sendMessage(appointment.getPatient().getEmail(), "Reminder ~ Tomorrow's appointment", message);
    }

    public void Cancel(String userMail, Appointment appointment){
        String message = "Dear \n\n" + ""
                +"This email serves as a notification that you have cancelled your appointment on "+appointment.getTreatmentDateTime().toLocalDate().toString()
                +" at "+appointment.getTreatmentDateTime().toLocalTime()+".\n"+
                "If you would like to reschedule, please enter the section of reserving appointments in our system and pick the wanted appointment.";
        sendMessage(appointment.getPatient().getEmail(), "Canceling Appointment", message);
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