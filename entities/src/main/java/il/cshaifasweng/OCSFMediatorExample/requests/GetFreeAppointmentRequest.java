package il.cshaifasweng.OCSFMediatorExample.requests;

public class GetFreeAppointmentRequest<T> implements Request {
    Class<T> appointmentType;

    public GetFreeAppointmentRequest(Class<T> o) {
        appointmentType = o;
    }

    public Class<T> getAppointmentType() {
        return appointmentType;
    }

    @Override
    public String getType() {
        return "GetFreeAppointmentRequest";
    }
}
