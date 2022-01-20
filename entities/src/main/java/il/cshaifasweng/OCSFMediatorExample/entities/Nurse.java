package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Nurse extends ClinicMember {
    public Nurse() {
    }

    public Nurse(String username, String password, int employeeNum, String firstName, String lastName, String email, String role, Clinic clinic) {
        super(username, password, employeeNum, firstName, lastName, email, role, clinic);
    }
}
