package il.cshaifasweng.OCSFMediatorExample.response;


public class UpdateFluVaccineHoursResponse extends Response {

    public UpdateFluVaccineHoursResponse(boolean isSuccessful) {
        super(isSuccessful);
    }

    public UpdateFluVaccineHoursResponse(boolean isSuccessful, String error) {
        super(isSuccessful, error);
    }

    @Override
    public String getType() {
        return "UpdateFluVaccineHoursResponse";
    }
}