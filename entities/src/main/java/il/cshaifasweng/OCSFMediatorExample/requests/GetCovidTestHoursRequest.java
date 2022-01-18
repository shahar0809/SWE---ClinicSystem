package il.cshaifasweng.OCSFMediatorExample.requests;

public class GetCovidTestHoursRequest implements Request {
    public String clinicName;
    public GetCovidTestHoursRequest(String activeClinic) {
        clinicName = activeClinic;
    }

    @Override
    public String getType() {
        return "GetCovidTestHoursRequest";
    }
}
