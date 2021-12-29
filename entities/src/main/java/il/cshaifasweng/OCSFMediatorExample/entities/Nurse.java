package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Nurses")
@DiscriminatorValue("Nurse")
public class Nurse extends ClinicMember {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nurse")
    private List<NurseAppointment> appointments;

    public Nurse() {
    }

    public Nurse(String username, String password, int employeeNum, String firstName, String lastName, String email, String role) {
        super(username, password, employeeNum, firstName, lastName, email, role);
    }

    public void addAppointment(NurseAppointment appointment) {
        appointments.add(appointment);
    }

    public List<NurseAppointment> getAppointments() {
        return appointments;
    }
}
