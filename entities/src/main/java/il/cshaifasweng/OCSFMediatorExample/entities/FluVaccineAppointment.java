package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("FluVaccineAppointment")
public class FluVaccineAppointment extends Appointment {
    public FluVaccineAppointment() {
        super();
    }

    public FluVaccineAppointment(Patient patient, LocalDateTime treatmentDateTime, ClinicMember member, Clinic clinic) {
        super(patient, treatmentDateTime, member, clinic);
    }

    public FluVaccineAppointment(LocalDateTime treatmentDateTime, ClinicMember member, Clinic clinic) {
        super(treatmentDateTime, member, clinic);
    }
}
