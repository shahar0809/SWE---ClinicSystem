package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("professionDoctor")
public class ProfessionDoctor extends ClinicMember {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "professionDoctor")
    private List<ProfessionDoctorAppointment> appointments;

    public ProfessionDoctor(String username, String password, int employeeNum, String firstName, String lastName, String email, String role) {
        super(username, password, employeeNum, firstName, lastName, email, role);
    }

    public ProfessionDoctor() {
        super();
    }

    public void addAppointment(ProfessionDoctorAppointment appointment) {
        appointments.add(appointment);
    }

    public List<ProfessionDoctorAppointment> getAppointments() {
        return appointments;
    }
}
