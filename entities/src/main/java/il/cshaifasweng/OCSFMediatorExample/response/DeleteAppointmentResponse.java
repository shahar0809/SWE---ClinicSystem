package il.cshaifasweng.OCSFMediatorExample.response;

public class DeleteAppointmentResponse extends Response {
    public DeleteAppointmentResponse(boolean isSuccessful, String error) {
        super(isSuccessful, error);
    }

    @Override
    public String getType() {
        return "DeleteAppointmentResponse";
    }
}
