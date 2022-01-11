package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.Patient;

public class ReservePatientAppointmentRequest implements Request {
    public String username;

    public ReservePatientAppointmentRequest(String username) {
        this.username = username;
    }

    @Override
    public String getType() {
        return "ReservePatientRequest";
    }
}
