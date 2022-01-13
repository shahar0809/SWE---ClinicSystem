package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("FamilyDoctorAppointment")
public class FamilyDoctorAppointment extends Appointment {
    public FamilyDoctorAppointment(Patient patient, LocalDateTime treatmentDateTime, FamilyDoctor doctor, Clinic clinic) {
        super(patient, treatmentDateTime, doctor, clinic);
    }


    public FamilyDoctorAppointment(LocalDateTime treatmentDateTime, FamilyDoctor familyDoctor, Clinic clinic) {
        super(treatmentDateTime, familyDoctor, clinic);
    }

    public FamilyDoctorAppointment() {
        super();
    }
}
