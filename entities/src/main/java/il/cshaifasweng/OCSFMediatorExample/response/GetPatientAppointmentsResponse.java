package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;

import java.util.List;

public class GetPatientAppointmentsResponse extends Response {
    public List<Appointment> appointment;

    public GetPatientAppointmentsResponse(List<Appointment> appointment, boolean isSuccessful) {
        super(isSuccessful);
        this.appointment = appointment;
    }

    @Override
    public String getType() {
        return "GetPatientAppointmentsResponse";
    }
}
