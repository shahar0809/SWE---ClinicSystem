package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Nurse")
public class Nurse extends ClinicMember {
    public Nurse() {
    }

    public Nurse(String username, String password, int employeeNum, String firstName, String lastName, String email, String role) {
        super(username, password, employeeNum, firstName, lastName, email, role);
    }
}
