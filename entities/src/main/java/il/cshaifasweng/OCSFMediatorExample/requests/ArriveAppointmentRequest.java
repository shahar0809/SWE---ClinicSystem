package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class ArriveAppointmentRequest extends Request {
    Appointment appointment;
    User user;

    public ArriveAppointmentRequest(Appointment appointment, User user) {
        this.appointment = appointment;
        this.user = user;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getType() {
        return "ArriveAppointmentRequest";
    }
}
