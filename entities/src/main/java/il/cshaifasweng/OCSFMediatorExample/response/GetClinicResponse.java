package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;

public class GetClinicResponse implements Response {
    public Clinic clinic;

    public GetClinicResponse(Clinic clinic) {
        this.clinic = clinic;
    }

    @Override
    public String getType() {
        return "GetClinicResponse";
    }
}
