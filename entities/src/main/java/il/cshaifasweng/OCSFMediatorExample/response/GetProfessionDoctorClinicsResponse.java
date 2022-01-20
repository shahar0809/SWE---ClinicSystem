package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.ClinicMember;

import java.util.List;

public class GetProfessionDoctorClinicsResponse extends Response {
    public List<ClinicMember> doctors;

    public GetProfessionDoctorClinicsResponse(List<ClinicMember> doctors, boolean isSuccessful, String error) {
        super(isSuccessful, error);
        this.doctors = doctors;
    }

    public GetProfessionDoctorClinicsResponse(List<ClinicMember> doctors, boolean isSuccessful) {
        super(isSuccessful);
        this.doctors = doctors;
    }

    public List<ClinicMember> getDoctors() {
        return doctors;
    }

    @Override
    public String getType() {
        return "GetProfessionDoctorClinicsResponse";
    }
}

