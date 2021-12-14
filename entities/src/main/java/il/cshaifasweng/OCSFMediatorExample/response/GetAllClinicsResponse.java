package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;

import java.util.List;

public class GetAllClinicsResponse implements Response {
    public List<Clinic> clinics;

    public GetAllClinicsResponse(List<Clinic> clinics) {
        this.clinics = clinics;
    }

    @Override
    public String getType() {
        return "GetAllClinicsResponse";
    }
}
