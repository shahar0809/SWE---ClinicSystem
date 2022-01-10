package il.cshaifasweng.OCSFMediatorExample.requests;

public class ReservePatientRequest implements Request {
    public ReservePatientRequest() {

    }

    @Override
    public String getType() {
        return "ReservePatientRequest";
    }
}
