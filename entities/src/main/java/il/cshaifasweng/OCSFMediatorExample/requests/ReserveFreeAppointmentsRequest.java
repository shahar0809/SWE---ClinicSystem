package il.cshaifasweng.OCSFMediatorExample.requests;

public class ReserveFreeAppointmentsRequest implements Request {
    public String appointmentType;

    public ReserveFreeAppointmentsRequest(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    @Override
    public String getType() {
        return "ReserveFreeAppointmentRequest";
    }
}
