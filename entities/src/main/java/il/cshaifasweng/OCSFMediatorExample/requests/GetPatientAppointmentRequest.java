package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.Patient;
import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class GetPatientAppointmentRequest implements Request {
    public User user;

    public GetPatientAppointmentRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getType() {
        return "ReservePatientRequest";
    }
}
