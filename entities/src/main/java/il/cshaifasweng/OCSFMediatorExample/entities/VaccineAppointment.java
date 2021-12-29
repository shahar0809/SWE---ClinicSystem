package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("VaccineAppointment")
public class VaccineAppointment extends Appointment {
    public VaccineAppointment() {
        super();
    }

    public VaccineAppointment(Patient patient, LocalDateTime treatmentDateTime) {
        super(patient, treatmentDateTime);
    }
}
