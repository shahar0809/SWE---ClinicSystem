package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;

public class ReserveFreeAppointmentResponse extends Response {

    public ReserveFreeAppointmentResponse(boolean succeed) {
        super(succeed);
    }

    @Override
    public String getType() {
        return "GetFluVaccineResponse";
    }
}
