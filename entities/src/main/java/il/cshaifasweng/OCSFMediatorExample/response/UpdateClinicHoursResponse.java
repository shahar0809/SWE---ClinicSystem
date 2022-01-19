package il.cshaifasweng.OCSFMediatorExample.response;

public class UpdateClinicHoursResponse extends Response {

    public UpdateClinicHoursResponse(boolean isSuccessful) {
        super(isSuccessful);
    }

    public UpdateClinicHoursResponse(boolean isSuccessful, String error) {
        super(isSuccessful, error);
    }

    @Override
    public String getType() {
        return "UpdateClinicHoursResponse";
    }
}
