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

    @Override
    public String getType() {
        return "Flu Vaccine";
    }

    public FluVaccineAppointment(Patient patient, ClinicMember member, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(patient, treatmentDateTime, member, clinic);
        this.type = AppointmentType.FLU_VACCINE;
    }

    public FluVaccineAppointment(ClinicMember member, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(treatmentDateTime, member, clinic);
        this.type = AppointmentType.FLU_VACCINE;
    }
}
