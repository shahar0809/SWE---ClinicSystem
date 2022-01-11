package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("familyDoctor")
public class FamilyDoctor extends ClinicMember {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "familyDoctor")
    private List<FamilyDoctorAppointment> appointments;

    public FamilyDoctor(String username, String password, int employeeNum, String firstName, String lastName, String email, String role) {
        super(username, password, employeeNum, firstName, lastName, email, role);
    }

    public FamilyDoctor() {
        super();
    }

    public void addAppointment(FamilyDoctorAppointment appointment) {
        appointments.add(appointment);
    }

    public List<FamilyDoctorAppointment> getAppointments() {
        return appointments;
    }
}
