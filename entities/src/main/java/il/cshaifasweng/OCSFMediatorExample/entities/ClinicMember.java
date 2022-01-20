package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class ClinicMember extends ClinicEmployee {
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ClinicMemberClinic")
    protected Clinic clinic;

    public ClinicMember(String username, String password, int employeeNum, String firstName, String lastName, String email, String role, Clinic clinic) {
        super(username, password, employeeNum, firstName, lastName, email, role);
        this.clinic = clinic;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public ClinicMember() {
        super();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
