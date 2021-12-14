package il.cshaifasweng.OCSFMediatorExample.requests;

public class GetAllClinicsRequest implements Request {

    public GetAllClinicsRequest() {
    }

    @Override
    public String getType() {
        return "GetAllClinicsRequest";
    }
}
