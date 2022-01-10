package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;

import java.util.List;

public class GetFreeAppointmentsResponse<T> extends Response {
    private List<T> appointments;

    public GetFreeAppointmentsResponse(boolean isSuccessful, List<T> appointments) {
        super(isSuccessful);
        this.appointments = appointments;
    }

    public List<T> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<T> appointments) {
        this.appointments = appointments;
    }

    public GetFreeAppointmentsResponse(boolean isSuccessful) {
        super(isSuccessful);
    }

    @Override
    public String getType() {
        return "GetFluVaccineResponse";
    }
}
