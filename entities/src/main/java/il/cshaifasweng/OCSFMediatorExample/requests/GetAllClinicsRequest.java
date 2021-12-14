package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.server.Hours;

import java.util.List;

public class GetAllClinicsRequest implements Request{
    public List<String> clinicNameList;

    public GetAllClinicsRequest(List<String> nameList) {
        clinicNameList = nameList;
    }
    @Override
    public String getType() {
        return "GetAllClinicsRequest";
    }
}
