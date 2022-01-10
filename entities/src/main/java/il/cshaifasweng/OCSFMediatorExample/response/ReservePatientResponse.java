package il.cshaifasweng.OCSFMediatorExample.response;

public class ReservePatientResponse extends Response {

    public ReservePatientResponse(boolean succeed) {
        super(succeed);
    }

    @Override
    public String getType() {
        return "ReservePatientResponse";
    }
}
