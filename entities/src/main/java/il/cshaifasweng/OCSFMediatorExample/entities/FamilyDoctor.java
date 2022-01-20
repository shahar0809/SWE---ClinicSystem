package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;

@Entity
public class FamilyDoctor extends ClinicMember {
    public FamilyDoctor(String username, String password, int employeeNum, String firstName, String lastName, String email, String role, Clinic clinic) {
        super(username, password, employeeNum, firstName, lastName, email, role, clinic);
    }

    public FamilyDoctor() {
        super();
    }
}
