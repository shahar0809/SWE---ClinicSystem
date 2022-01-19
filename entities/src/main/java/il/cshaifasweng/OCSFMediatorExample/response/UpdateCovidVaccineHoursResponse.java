package il.cshaifasweng.OCSFMediatorExample.response;

public class UpdateCovidVaccineHoursResponse extends Response {

    public UpdateCovidVaccineHoursResponse(boolean isSuccessful) {
        super(isSuccessful);
    }

    public UpdateCovidVaccineHoursResponse(boolean isSuccessful, String error) {
        super(isSuccessful, error);
    }

    @Override
    public String getType() {
        return "UpdateCovidVaccineHoursResponse";
    }
}