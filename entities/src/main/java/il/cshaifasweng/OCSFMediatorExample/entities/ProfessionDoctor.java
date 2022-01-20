package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class ProfessionDoctor extends ClinicMember {
    public ProfessionDoctor(String username, String password, int employeeNum, String firstName, String lastName, String email, String role, Clinic clinic) {
        super(username, password, employeeNum, firstName, lastName, email, role, clinic);
    }
    public ProfessionDoctor() {
        super();
    }

}
