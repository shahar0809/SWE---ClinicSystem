

public class SaveAnswerResponse extends Response {
    public GetPatientAppointmentResponse(boolean isSuccessful, String error) {
        super(isSuccessful, error);
    }

    @Override
    public String getType() {
        return "SaveAnswerResponse";
    }

    public GetPatientAppointmentResponse(Lboolean isSuccessful) {
        super(isSuccessful);
    }