package il.cshaifasweng.OCSFMediatorExample.response;

public class ArriveAppointmentResponse extends Response {
    public ArriveAppointmentResponse(boolean isSuccessful, String error) {
        super(isSuccessful, error);
    }

    @Override
    public String getType() {
        return "ArriveAppointmentResponse";
    }

    public ArriveAppointmentResponse(boolean isSuccessful) {
        super(isSuccessful);
    }
}
