package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("FamilyDoctorAppointment")
public class FamilyDoctorAppointment extends Appointment {
    public FamilyDoctorAppointment(Patient patient, LocalDateTime treatmentDateTime, FamilyDoctor doctor, Clinic clinic) {
        super(patient, treatmentDateTime, doctor, clinic);
        this.familyDoctor = doctor;
    }

    public FamilyDoctor getFamilyDoctor() {
        return familyDoctor;
    }

    public FamilyDoctorAppointment(LocalDateTime treatmentDateTime, FamilyDoctor familyDoctor, Clinic clinic) {
        super(treatmentDateTime, familyDoctor, clinic);
        this.familyDoctor = familyDoctor;
    }

    public void setFamilyDoctor(FamilyDoctor familyDoctor) {
        this.familyDoctor = familyDoctor;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "familyDoctor", nullable = false)
    private FamilyDoctor familyDoctor;

    public FamilyDoctorAppointment() {
        super();
    }
}
