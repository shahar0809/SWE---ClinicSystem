package il.cshaifasweng.OCSFMediatorExample.response;

public class ReservePatientAppointmentResponse extends Response {

    public ReservePatientAppointmentResponse(boolean succeed) {
        super(succeed);
    }

    @Override
    public String getType() {
        return "ReservePatientResponse";
    }
}
