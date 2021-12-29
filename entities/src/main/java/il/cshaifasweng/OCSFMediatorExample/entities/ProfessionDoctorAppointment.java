package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("ProfessionDoctorAppointment")
public class ProfessionDoctorAppointment extends Appointment {
    public ProfessionDoctorAppointment() {
        super();
    }

    public ProfessionDoctorAppointment(Patient patient, ProfessionDoctor professionDoctor, LocalDateTime treatmentDateTime) {
        super(patient, treatmentDateTime);
        this.professionDoctor = professionDoctor;
    }

    public ProfessionDoctor getProfessionDoctor() {
        return professionDoctor;
    }

    public void setProfessionDoctor(ProfessionDoctor professionDoctor) {
        this.professionDoctor = professionDoctor;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professionDoctor", nullable = false)
    private ProfessionDoctor professionDoctor;
}
