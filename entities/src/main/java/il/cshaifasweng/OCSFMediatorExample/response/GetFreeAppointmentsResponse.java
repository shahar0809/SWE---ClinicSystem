package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.util.Collections;
import java.util.List;

public class GetFreeAppointmentsResponse<T extends Appointment> extends Response {
    private List<T> appointments;

    public GetFreeAppointmentsResponse(List<T> appointments, boolean isSuccessful) {
        super(isSuccessful);
        Collections.sort(appointments);
        this.appointments = appointments;
    }

    public GetFreeAppointmentsResponse(List<T> appointments, boolean isSuccessful, String error) {
        super(isSuccessful, error);
        Collections.sort(appointments);
        this.appointments = appointments;
    }

    public List<T> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<T> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String getType() {
        return "GetFluVaccineResponse";
    }
}
