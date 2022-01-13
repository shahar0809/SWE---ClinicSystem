package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;

public class GetClinicResponse extends Response {
    public Clinic clinic;

    public GetClinicResponse(Clinic clinic, boolean isSuccessful, String error) {
        super(isSuccessful, error);
        this.clinic = clinic;
    }

    public GetClinicResponse( Clinic clinic, boolean isSuccessful) {
        super(isSuccessful);
        this.clinic = clinic;
    }

    @Override
    public String getType() {
        return "GetClinicResponse";
    }
}
