package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CovidVaccineAppointment")
public class CovidVaccineAppointment extends Appointment {
    public CovidVaccineAppointment() {
        super();
    }

    public CovidVaccineAppointment(LocalDateTime treatmentDateTime, ClinicMember member, Clinic clinic) {
        super(treatmentDateTime, member, clinic);
    }

    public CovidVaccineAppointment(Patient patient, LocalDateTime treatmentDateTime, ClinicMember member, Clinic clinic) {
        super(patient, treatmentDateTime, member, clinic);
    }
}
