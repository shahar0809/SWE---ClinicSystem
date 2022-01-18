package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.util.List;

public class GetPatientAppointmentResponse extends Response {
    public List<Appointment> appointments;

    public GetPatientAppointmentResponse(List<Appointment> appointments, boolean isSuccessful, String error) {
        super(isSuccessful, error);
        this.appointments = appointments;
    }

    @Override
    public String getType() {
        return "GetPatientAppointmentResponse";
    }

    public GetPatientAppointmentResponse(List<Appointment> appointments, boolean isSuccessful) {
        super(isSuccessful);
        this.appointments = appointments;
    }
}
