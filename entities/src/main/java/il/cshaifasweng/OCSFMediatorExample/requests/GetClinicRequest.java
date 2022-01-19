package il.cshaifasweng.OCSFMediatorExample.requests;

public class GetClinicRequest extends Request {
    public String clinicName;

    public GetClinicRequest(String name) {
        clinicName = name;
    }

    @Override
    public String getType() {
        return "GetClinicRequest";
    }
}
