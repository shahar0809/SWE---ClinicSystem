package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ClinicMember extends ClinicEmployee {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clinicMember")
    private List<Appointment> appointments;

    public ClinicMember() {
        appointments = new ArrayList<>();
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
