package il.cshaifasweng.OCSFMediatorExample.response;

public class ReserveAppointmentResponse extends Response {
    public ReserveAppointmentResponse(boolean isSuccessful) {
        super(isSuccessful);
    }

    @Override
    public String getType() {
        return "GetCovidVaccineResponse";
    }
}
