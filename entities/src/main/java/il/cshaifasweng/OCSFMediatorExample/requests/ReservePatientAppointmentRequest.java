package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.Patient;

public class ReservePatientAppointmentRequest implements Request {
    public Patient patient;
    public ReservePatientAppointmentRequest(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String getType() {
        return "ReservePatientRequest";
    }
}
