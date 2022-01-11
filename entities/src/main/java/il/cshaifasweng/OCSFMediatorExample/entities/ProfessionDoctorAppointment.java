package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("ProfessionDoctorAppointment")
public class ProfessionDoctorAppointment extends Appointment {
    public ProfessionDoctorAppointment() {
        super();
    }

    public ProfessionDoctorAppointment(Patient patient, ProfessionDoctor professionDoctor, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(patient, treatmentDateTime, professionDoctor, clinic);
        this.professionDoctor = professionDoctor;
    }

    public ProfessionDoctor getProfessionDoctor() {
        return professionDoctor;
    }

    public ProfessionDoctorAppointment(LocalDateTime treatmentDateTime, ProfessionDoctor professionDoctor, Clinic clinic) {
        super(treatmentDateTime, professionDoctor, clinic);
        this.professionDoctor = professionDoctor;
    }

    public void setProfessionDoctor(ProfessionDoctor professionDoctor) {
        super.setMember(professionDoctor);
        this.professionDoctor = professionDoctor;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professionDoctor", nullable = false)
    private ProfessionDoctor professionDoctor;
}
