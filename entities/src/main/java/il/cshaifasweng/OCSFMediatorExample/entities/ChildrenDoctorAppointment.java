package il.cshaifasweng.OCSFMediatorExample.entities;

import java.time.LocalDateTime;

public class ChildrenDoctorAppointment extends Appointment {
    public ChildrenDoctorAppointment() {
    }

    public ChildrenDoctorAppointment(ClinicMember member, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(treatmentDateTime, member, clinic);
    }

    public ChildrenDoctorAppointment(Patient patient, ClinicMember member, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(patient, treatmentDateTime, member, clinic);
    }
}
