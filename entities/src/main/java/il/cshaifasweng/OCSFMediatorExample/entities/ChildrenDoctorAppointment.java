package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class ChildrenDoctorAppointment extends Appointment {
    public ChildrenDoctorAppointment() {
        this.type = AppointmentType.CHILDREN;
    }

    @Override
    public String getType() {
        return "Children";
    }

    public ChildrenDoctorAppointment(ChildrenDoctor member, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(treatmentDateTime, member, clinic);
        this.type = AppointmentType.CHILDREN;
    }

    public ChildrenDoctorAppointment(Patient patient, ChildrenDoctor member, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(patient, treatmentDateTime, member, clinic);
        this.type = AppointmentType.CHILDREN;
    }
}
