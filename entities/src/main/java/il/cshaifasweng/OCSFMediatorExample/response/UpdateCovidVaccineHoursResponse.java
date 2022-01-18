package il.cshaifasweng.OCSFMediatorExample.response;

public class UpdateCovidVaccineHoursResponse implements Response {
    public boolean succeed;

    public UpdateCovidVaccineHoursResponse() {
        succeed = true;
    }

    @Override
    public String getType() {
        return "UpdateCovidVaccineHoursResponse";
    }
}