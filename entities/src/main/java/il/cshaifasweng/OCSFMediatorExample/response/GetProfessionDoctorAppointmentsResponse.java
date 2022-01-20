package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.util.List;

public class GetProfessionDoctorAppointmentsResponse extends Response {
    public List<Appointment> appointments;

    public GetProfessionDoctorAppointmentsResponse(List<Appointment> appointments, boolean isSuccessful, String error) {
        super(isSuccessful, error);
        this.appointments = appointments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public String getType() {
        return "GetPatientAppointmentResponse";
    }

    public GetProfessionDoctorAppointmentsResponse(List<Appointment> appointments, boolean isSuccessful) {
        super(isSuccessful);
        this.appointments = appointments;
    }
}