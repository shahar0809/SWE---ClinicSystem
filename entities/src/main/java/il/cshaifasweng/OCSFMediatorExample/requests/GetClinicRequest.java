package il.cshaifasweng.OCSFMediatorExample.requests;

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
