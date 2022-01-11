package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CovidTestAppointment")
public class CovidTestAppointment extends Appointment {
    public CovidTestAppointment() {
        super();
    }

    public CovidTestAppointment(Patient patient, LocalDateTime treatmentDateTime, ClinicMember member, Clinic clinic) {
        super(patient, treatmentDateTime, member, clinic);
    }

    public CovidTestAppointment(LocalDateTime treatmentDateTime, ClinicMember member, Clinic clinic) {
        super(treatmentDateTime, member, clinic);
    }
}
