package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.ClinicMember;
import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class GetProfessionDoctorAppointmentsRequest extends Request {
    public ClinicMember doctor;

    public GetProfessionDoctorAppointmentsRequest(ClinicMember doctor) {
        this.doctor = doctor;
    }

    public ClinicMember getDoctor() {
        return doctor;
    }

    @Override
    public String getType() {
        return "ReservePatientRequest";
    }
}