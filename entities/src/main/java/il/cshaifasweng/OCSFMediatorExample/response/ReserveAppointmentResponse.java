package il.cshaifasweng.OCSFMediatorExample.response;

public class ReserveAppointmentResponse extends Response {
    public ReserveAppointmentResponse(boolean succeed) {
        super(succeed);
    }

    @Override
    public String getType() {
        return "GetCovidVaccineResponse";
    }
}
