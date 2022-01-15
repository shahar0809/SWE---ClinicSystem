package il.cshaifasweng.OCSFMediatorExample.entities;

import java.time.LocalDateTime;

public class ChildrenDoctorAppointment extends Appointment {
    public ChildrenDoctorAppointment() {
        this.type = AppointmentType.CHILDREN;
    }

    @Override
    public String getType() {
        return "Children";
    }

    public ChildrenDoctorAppointment(ClinicMember member, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(treatmentDateTime, member, clinic);
        this.type = AppointmentType.CHILDREN;
    }

    public ChildrenDoctorAppointment(Patient patient, ClinicMember member, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(patient, treatmentDateTime, member, clinic);
        this.type = AppointmentType.CHILDREN;
    }
}
