package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.util.List;

public class ReservePatientAppointmentResponse extends Response {
    public List<Appointment> appointments;

    public ReservePatientAppointmentResponse(List<Appointment> appointments, boolean isSuccessful) {
        super(isSuccessful);
        this.appointments = appointments;
    }

    @Override
    public String getType() {
        return "ReservePatientAppointmentResponse";
    }
}
