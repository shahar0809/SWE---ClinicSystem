package il.cshaifasweng.OCSFMediatorExample.response;

public class DeleteAppointmentResponse extends Response {
    public DeleteAppointmentResponse(boolean isSuccessful) {
        super(isSuccessful);
    }

    @Override
    public String getType() {
        return "DeleteAppointmentResponse";
    }
}
