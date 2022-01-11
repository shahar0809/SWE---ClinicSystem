package il.cshaifasweng.OCSFMediatorExample.response;

public class AddAppointmentResponse extends Response {
    public AddAppointmentResponse(boolean isSuccessful) {
        super(isSuccessful);
    }

    @Override
    public String getType() {
        return "AddAppointmentResponse";
    }
}
