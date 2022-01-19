package il.cshaifasweng.OCSFMediatorExample.requests;

public class GetCovidVaccineHoursRequest extends Request {
    public String clinicName;
    public GetCovidVaccineHoursRequest(String activeClinic) {
        clinicName = activeClinic;
    }

    @Override
    public String getType() {
        return "GetCovidVaccineHoursRequest";
    }
}