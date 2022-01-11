package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;

public class ReserveFreeAppointmentsResponse extends Response {

    public ReserveFreeAppointmentsResponse(boolean succeed) {
        super(succeed);
    }

    @Override
    public String getType() {
        return "GetFluVaccineResponse";
    }
}
