package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.util.List;

public class GetMemberAppointmentsResponse extends Response {
    List<Appointment> appointments;

    public GetMemberAppointmentsResponse(boolean isSuccessful, List<Appointment> appointments) {
        super(isSuccessful);
        this.appointments = appointments;
    }

    public GetMemberAppointmentsResponse(boolean isSuccessful, String error, List<Appointment> appointments) {
        super(isSuccessful, error);
        this.appointments = appointments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public String getType() {
        return "GetMemberAppointmentsResponse";
    }
}
