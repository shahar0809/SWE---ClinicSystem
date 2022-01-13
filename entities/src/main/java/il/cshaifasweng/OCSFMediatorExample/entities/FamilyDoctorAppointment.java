package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("FamilyDoctorAppointment")
public class FamilyDoctorAppointment extends Appointment {
    public FamilyDoctorAppointment(Patient patient, FamilyDoctor doctor, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(patient, treatmentDateTime, doctor, clinic);
    }


    public FamilyDoctorAppointment(FamilyDoctor familyDoctor, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(treatmentDateTime, familyDoctor, clinic);
    }

    public FamilyDoctorAppointment() {
        super();
    }
}
