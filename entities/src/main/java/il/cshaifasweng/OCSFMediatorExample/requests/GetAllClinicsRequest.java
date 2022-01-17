package il.cshaifasweng.OCSFMediatorExample.requests;

public class GetAllClinicsRequest extends Request {

    public GetAllClinicsRequest() {
    }

    @Override
    public String getType() {
        return "GetAllClinicsRequest";
    }
}
