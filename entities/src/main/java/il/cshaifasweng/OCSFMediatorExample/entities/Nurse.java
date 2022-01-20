package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Nurse extends ClinicMember {
    public Nurse() {
    }

    public Nurse(String username, String password, int employeeNum, String firstName, String lastName, String email, String role, Clinic clinic) {
        super(AppointmentType.NURSE, username, password, employeeNum, firstName, lastName, email, role, clinic);
        super.appointmentDuration = 10;
    }
}
