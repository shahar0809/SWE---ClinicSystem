package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class ProfessionDoctor extends Doctor {
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
