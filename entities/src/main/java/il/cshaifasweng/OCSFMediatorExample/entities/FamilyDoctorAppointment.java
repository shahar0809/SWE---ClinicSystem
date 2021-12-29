package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("FamilyDoctorAppointment")
public class FamilyDoctorAppointment extends Appointment {
    public FamilyDoctorAppointment(FamilyDoctor doctor) {
        this.familyDoctor = doctor;
    }

    public FamilyDoctorAppointment(Patient patient, LocalDateTime treatmentDateTime, FamilyDoctor doctor) {
        super(patient, treatmentDateTime);
        this.familyDoctor = doctor;
    }

    public FamilyDoctor getFamilyDoctor() {
        return familyDoctor;
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
