package il.cshaifasweng.OCSFMediatorExample.requests;

public class GetAllDoctorsRequest extends Request {

    public GetAllDoctorsRequest() {
    }

    @Override
    public String getType() {
        return "GetAllDoctorsRequest";
    }
}
