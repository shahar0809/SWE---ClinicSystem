package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.ClinicMember;

import java.util.List;


public class GetAllDoctorsResponse extends Response {
    public List<ClinicMember> doctors;

    public GetAllDoctorsResponse(List<ClinicMember> doctors, boolean isSuccessful, String error) {
        super(isSuccessful, error);
        this.doctors = doctors;
    }

    public GetAllDoctorsResponse(List<ClinicMember> doctors, boolean isSuccessful) {
        super(isSuccessful);
        this.doctors = doctors;
    }

    @Override
    public String getType() {
        return "GetAllDoctorsResponse";
    }
}
