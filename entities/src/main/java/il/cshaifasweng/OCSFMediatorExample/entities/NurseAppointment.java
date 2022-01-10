package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@DiscriminatorValue("NurseAppointment")
public class NurseAppointment extends Appointment {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nurse", nullable = false)
    private Nurse nurse;

    public NurseAppointment() {
    }

    public NurseAppointment(Patient patient, Nurse nurse, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(patient, treatmentDateTime, nurse, clinic);
        this.nurse = nurse;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }
}
