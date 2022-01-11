package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("familyDoctor")
public class FamilyDoctor extends ClinicMember {
    public FamilyDoctor(String username, String password, int employeeNum, String firstName, String lastName, String email, String role) {
        super(username, password, employeeNum, firstName, lastName, email, role);
    }

    public FamilyDoctor() {
        super();
    }
}
