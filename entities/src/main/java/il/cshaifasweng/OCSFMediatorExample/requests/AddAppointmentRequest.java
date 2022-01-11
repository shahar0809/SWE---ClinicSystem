package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

public class AddAppointmentRequest implements Request{
    public Appointment appointment;
    public String username;

    public AddAppointmentRequest(Appointment appointment, String username) {
        this.appointment = appointment;
        this.username = username;
    }

    @Override
    public String getType() {
        return "AddAppointmentRequest";
    }
}
