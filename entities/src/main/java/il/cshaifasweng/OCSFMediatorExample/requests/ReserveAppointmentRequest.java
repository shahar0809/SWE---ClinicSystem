package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

public class ReserveAppointmentRequest implements Request {
    public Appointment appointment;

    public ReserveAppointmentRequest(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public String getType() {
        return "GetCovidVaccineRequest";
    }
}