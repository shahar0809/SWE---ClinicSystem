package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.AppointmentType;

public class GetFreeAppointmentRequest<T> extends Request {
    Class<T> appointmentType;
    AppointmentType enumType;

    public GetFreeAppointmentRequest(Class<T> appointmentType, AppointmentType enumType) {
        this.appointmentType = appointmentType;
        this.enumType = enumType;
    }

    public GetFreeAppointmentRequest(Class<T> o) {
        appointmentType = o;
    }

    public AppointmentType getEnumType() {
        return enumType;
    }

    public Class<T> getAppointmentType() {
        return appointmentType;
    }

    @Override
    public String getType() {
        return "GetFreeAppointmentRequest";
    }
}
