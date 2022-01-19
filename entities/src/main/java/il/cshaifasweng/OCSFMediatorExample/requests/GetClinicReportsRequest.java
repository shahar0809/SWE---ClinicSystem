package il.cshaifasweng.OCSFMediatorExample.requests;

public class GetClinicReportsRequest extends Request {
    public String clinicName;

    public GetClinicReportsRequest(String name) {
        clinicName = name;
    }

    @Override
    public String getType() {
        return "GetClinicReportsRequest";
    }
}
