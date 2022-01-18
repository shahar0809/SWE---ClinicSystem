package il.cshaifasweng.OCSFMediatorExample.response;


public class UpdateCovidTestHoursResponse extends Response {

    public UpdateCovidTestHoursResponse(boolean isSuccessful) {
        super(isSuccessful);
    }

    public UpdateCovidTestHoursResponse(boolean isSuccessful, String error) {
        super(isSuccessful, error);
    }

    @Override
    public String getType() {
        return "UpdateCovidTestHoursResponse";
    }
}
