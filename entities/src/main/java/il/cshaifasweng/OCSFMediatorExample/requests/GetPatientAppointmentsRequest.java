package il.cshaifasweng.OCSFMediatorExample.requests;

public class GetPatientAppointmentsRequest implements Request {
    public GetPatientAppointmentsRequest() {

    }

    @Override
    public String getType() {
        return "GetPatientAppointmentsRequest";
    }
}
