package il.cshaifasweng.OCSFMediatorExample.response;

public class UpdateActiveHoursResponse extends Response {

    public UpdateActiveHoursResponse(boolean isSuccessful, String error) {
        super(isSuccessful, error);
    }

    public UpdateActiveHoursResponse(boolean isSuccessful) {
        super(isSuccessful);
    }

    @Override
    public String getType() {
        return "UpdateActiveHoursResponse";
    }
}
