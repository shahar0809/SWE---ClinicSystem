package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Patients")
public class Patient extends User {
    @Column(name = "patientId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER, mappedBy = "patient")
    private List<Appointment> appointments;

    @Column(name = "age", nullable = false)
    private int age;

    private String email;
    private String lastName;

    public Patient() {
        appointments = new ArrayList<>();
    }

    public Patient(String username, String password, int age) {
        super(username, password);
        appointments = new ArrayList<>();
        this.age = age;
    }

    public Patient(String username, String password, int age, String email) {
        super(username, password);
        appointments = new ArrayList<>();
        this.age = age;
        this.email = email;
    }


    public List<Appointment> getAppointments() {
        List<Appointment> reserveAppointments = new ArrayList<>();
        for(Appointment appointment : appointments) {
            if(!appointment.hasPatientArrived() && LocalDateTime.now().compareTo(appointment.getTreatmentDateTime()) > 0) {
                reserveAppointments.add(appointment);
            }
        }
        return reserveAppointments;
    }

    public boolean gotCovidVaccine() {
        for(Appointment appointment : appointments) {
            if(appointment.hasPatientArrived() && appointment instanceof CovidVaccineAppointment) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        // TODO: Replace to name when supported
        return username;
    }

    public void addAppointment(Appointment appointment) {
        appointment.setPatient(this);
        appointments.add(appointment);
    }

    public int getAge() {
        return age;
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

    public void deleteAppointment(Appointment appointment) {
        for (Appointment oneAppointment : appointments) {
            if (appointment.getId().equals(oneAppointment.getId())) {
                appointments.remove(appointment);
            }
        }
    }
}
