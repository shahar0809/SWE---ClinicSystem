package il.cshaifasweng.OCSFMediatorExample.requests;

public class ReserveFreeAppointmentRequest implements Request {

    public ReserveFreeAppointmentRequest() {

    }

    @Override
    public String getType() {
        return "ReserveFreeAppointmentRequest";
    }
}
