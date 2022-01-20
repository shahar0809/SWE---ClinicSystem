package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.AppointmentType;
import il.cshaifasweng.OCSFMediatorExample.entities.Patient;

public class GetProfessionDoctorClinicsRequest extends Request {
    AppointmentType enumType;
    Patient patient;

    public GetProfessionDoctorClinicsRequest(AppointmentType enumType, Patient patient) {
        this.enumType = enumType;
        this.patient = patient;
    }

    public AppointmentType getEnumType() {
        return enumType;
    }

    public Patient getPatient() {
        return patient;
    }

    @Override
    public String getType() {
        return "GetFreeAppointmentRequest";
    }
}