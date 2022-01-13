package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("ProfessionDoctorAppointment")
public class ProfessionDoctorAppointment extends Appointment {
    public ProfessionDoctorAppointment() {
        super();
    }

    public ProfessionDoctorAppointment(Patient patient, ProfessionDoctor professionDoctor, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(patient, treatmentDateTime, professionDoctor, clinic);
    }

    public ProfessionDoctorAppointment(LocalDateTime treatmentDateTime, ProfessionDoctor professionDoctor, Clinic clinic) {
        super(treatmentDateTime, professionDoctor, clinic);
    }
}
