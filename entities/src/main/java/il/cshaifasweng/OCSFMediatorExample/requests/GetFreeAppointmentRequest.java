package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.AppointmentType;
import il.cshaifasweng.OCSFMediatorExample.entities.Patient;

public class GetFreeAppointmentRequest<T> extends Request {
    Class<T> appointmentType;
    AppointmentType enumType;
    Patient patient;

    public GetFreeAppointmentRequest(Class<T> appointmentType, AppointmentType enumType, Patient patient) {
        this.appointmentType = appointmentType;
        this.enumType = enumType;
        this.patient = patient;
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

    public Patient getPatient() {
        return patient;
    }

    @Override
    public String getType() {
        return "GetFreeAppointmentRequest";
    }
}
