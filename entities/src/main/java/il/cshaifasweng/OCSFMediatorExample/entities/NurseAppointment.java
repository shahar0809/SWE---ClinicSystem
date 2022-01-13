package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@DiscriminatorValue("NurseAppointment")
public class NurseAppointment extends Appointment {
    public NurseAppointment() {
    }

    public NurseAppointment(Patient patient, Nurse nurse, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(patient, treatmentDateTime, nurse, clinic);
    }
}
