package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;

public class GetClinicResponse extends Response {
    public Clinic clinic;

    public GetClinicResponse(Clinic clinic, boolean succeed) {
        super(succeed);
        this.clinic = clinic;
    }

    @Override
    public String getType() {
        return "GetClinicResponse";
    }
}
