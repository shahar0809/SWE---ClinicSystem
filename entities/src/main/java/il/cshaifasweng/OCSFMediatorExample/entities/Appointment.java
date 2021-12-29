package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

// TODO: Decide if we need base class for doctor and nurse [instead of doctor in here]
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Appointments")
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient")
    protected Patient patient;

    @Column(name = "appointment_time")
    protected LocalDateTime treatmentDateTime;

    @Column(name = "isAvailable")
    protected boolean isAvailable;

    public Appointment() {
    }

    public Appointment(Patient patient, LocalDateTime treatmentDateTime) {
        this.patient = patient;
        this.treatmentDateTime = treatmentDateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getTreatmentDateTime() {
        return treatmentDateTime;
    }

    public void setTreatmentDateTime(LocalDateTime treatmentDateTime) {
        this.treatmentDateTime = treatmentDateTime;
    }
}

/**
 * Enum for appointments type to fetch from database.
 */
enum AppointmentType {
    CovidTest("CovidTestAppointment"),
    FamilyDoctor("FamilyDoctorAppointment"),
    Nurse("NurseAppointmentAppointment"),
    ProfessionDoctor("ProfessionDoctorAppointment"),
    Vaccine("VaccineAppointment");

    private final String type;

    AppointmentType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
