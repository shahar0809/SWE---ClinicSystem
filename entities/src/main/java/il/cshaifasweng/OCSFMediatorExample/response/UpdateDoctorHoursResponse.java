package il.cshaifasweng.OCSFMediatorExample.response;

public class UpdateDoctorHoursResponse extends Response {
    public UpdateDoctorHoursResponse(boolean isSuccessful, String error) {
        super(isSuccessful, error);
    }

    public UpdateDoctorHoursResponse(boolean isSuccessful) {
        super(isSuccessful);
    }

    @Override
    public String getType() {
        return "UpdateDoctorHoursResponse";
    }
}
