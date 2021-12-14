package il.cshaifasweng.OCSFMediatorExample.requests;

import java.util.List;

public class GetAllClinicsRequest implements Request{

    public GetAllClinicsRequest() { }
    @Override
    public String getType() {
        return "GetAllClinicsRequest";
    }
}
