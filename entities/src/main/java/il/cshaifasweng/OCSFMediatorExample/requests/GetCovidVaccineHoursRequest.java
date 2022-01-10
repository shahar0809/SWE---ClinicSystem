package il.cshaifasweng.OCSFMediatorExample.requests;

public class GetCovidVaccineHoursRequest implements Request {
    public String clinicName;
    public GetCovidVaccineHoursRequest(String activeClinic) {
        clinicName = activeClinic;
    }

    @Override
    public String getType() {
        return "GetCovidVaccineHoursRequest";
    }
}