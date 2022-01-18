package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Patients")
public class Patient extends User {
    @Column(name = "patientId")

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private List<Appointment> appointments;

    private String email;
    private String lastName;

    public Patient() {
        appointments = new ArrayList<>();
    }

    public Patient(String username, String password) {
        super(username, password);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
