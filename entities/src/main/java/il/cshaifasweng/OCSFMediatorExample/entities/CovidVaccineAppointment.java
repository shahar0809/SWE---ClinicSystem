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

    @Override
    public String getType() {
        return "Covid Vaccine";
    }

    public CovidVaccineAppointment(ClinicMember member, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(treatmentDateTime, member, clinic);
        this.type = AppointmentType.COVID_VACCINE;
    }

    public CovidVaccineAppointment(Patient patient, ClinicMember member, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(patient, treatmentDateTime, member, clinic);
        this.type = AppointmentType.COVID_VACCINE;
    }
}
