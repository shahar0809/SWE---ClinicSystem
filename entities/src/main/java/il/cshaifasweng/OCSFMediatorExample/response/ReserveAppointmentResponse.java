package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.util.List;

public class ReserveAppointmentResponse extends Response {
    public ReserveAppointmentResponse(boolean isSuccessful, String error) {
        super(isSuccessful, error);
    }

    public ReserveAppointmentResponse(boolean isSuccessful) {
        super(isSuccessful);
    }

    @Override
    public String getType() {
        return "ReserveAppointmentResponse";
    }
}
