package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class ReserveAppointmentRequest implements Request {
    Appointment appointment;
    User user;


    public ReserveAppointmentRequest(Appointment appointment, User user) {
        this.appointment = appointment;
        this.user = user;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getType() {
        return "ReserveFreeAppointmentRequest";
    }
}
