package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class FamilyDoctor extends ClinicMember {
    public FamilyDoctor(String username, String password, int employeeNum, String firstName, String lastName, String email, String role, Clinic clinic) {
        super(AppointmentType.FAMILY, username, password, employeeNum, firstName, lastName, email, role, clinic);
        super.appointmentDuration = 15;
    }

    public FamilyDoctor() {
        super();
        super.appointmentDuration = 15;
    }
}
