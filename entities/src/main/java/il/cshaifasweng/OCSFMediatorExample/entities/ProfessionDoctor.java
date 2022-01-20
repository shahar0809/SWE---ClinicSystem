package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
public class ProfessionDoctor extends ClinicMember {
    public ProfessionDoctor(AppointmentType type, String username, String password, int employeeNum, String firstName, String lastName, String email, String role, Clinic clinic) {
        super(type, username, password, employeeNum, firstName, lastName, email, role, clinic);
        super.appointmentDuration = 20;
    }
    public ProfessionDoctor() {
        super();
        super.appointmentDuration = 20;
    }

}
