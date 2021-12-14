package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.server.Hours;

public class GetClinicRequest implements Request {
    public String clinicName;

    public GetClinicRequest(String name) {
        clinicName = name;
    }
    @Override
    public String getType() {
        return "GetClinicRequest";
    }
}
