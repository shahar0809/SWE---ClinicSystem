package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class CovidTestAppointment extends Appointment {
    public CovidTestAppointment() {
        super();
        this.type = AppointmentType.COVID_TEST;
    }

    @Override
    public String getType() {
        return "Covid Test";
    }

    public CovidTestAppointment(Patient patient, ClinicMember member, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(patient, treatmentDateTime, member, clinic);
        this.type = AppointmentType.COVID_TEST;
    }

    public CovidTestAppointment(ClinicMember member, LocalDateTime treatmentDateTime, Clinic clinic) {
        super(treatmentDateTime, member, clinic);
        this.type = AppointmentType.COVID_TEST;
    }
}
