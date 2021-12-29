package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Doctors")
@DiscriminatorValue("Doctor")
public class Doctor extends ClinicMember {
    public Doctor(String username, String password, int employeeNum, String firstName, String lastName, String email, String role) {
        super(username, password, employeeNum, firstName, lastName, email, role);
    }

    public Doctor() {
        super();
    }
}
