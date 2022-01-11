package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

public class DeleteAppointmentRequest implements Request {
    public Appointment appointment;
    public String username;

    public DeleteAppointmentRequest(Appointment appointment, String username) {
        this.appointment = appointment;
        this.username = username;
    }

    @Override
    public String getType() {
        return "DeleteAppointmentRequest";
    }
}
