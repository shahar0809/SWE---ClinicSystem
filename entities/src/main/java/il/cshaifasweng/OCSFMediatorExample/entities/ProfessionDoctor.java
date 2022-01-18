package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProfessionDoctor extends ClinicMember {
    public ProfessionDoctor(String username, String password, int employeeNum, String firstName, String lastName, String email, String role) {
        super(username, password, employeeNum, firstName, lastName, email, role);
    }
    public ProfessionDoctor() {
        super();
    }
}
