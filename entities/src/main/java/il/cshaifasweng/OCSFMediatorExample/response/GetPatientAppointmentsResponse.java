package il.cshaifasweng.OCSFMediatorExample.response;

public class GetPatientAppointmentsResponse extends Response {

    public GetPatientAppointmentsResponse(boolean isSuccessful) {
        super(isSuccessful);
    }

    @Override
    public String getType() {
        return "GetPatientAppointmentsResponse";
    }
}
