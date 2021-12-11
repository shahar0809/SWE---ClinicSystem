package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

// TODO: Decide if we need base class for doctor and nurse [instead of doctor in here]
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Integer id;

    @Column(name = "patient", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    protected Patient patient;

    @Column(name = "doctor", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    protected Doctor doctor;

    @Column(name = "appointment_time")
    protected LocalDateTime treatmentDateTime;

    public Appointment() {}

    public Appointment(Patient patient, Doctor doctor, LocalDateTime treatmentDateTime) {
        this.patient = patient;
        this.doctor = doctor;
        this.treatmentDateTime = treatmentDateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getTreatmentDateTime() {
        return treatmentDateTime;
    }

    public void setTreatmentDateTime(LocalDateTime treatmentDateTime) {
        this.treatmentDateTime = treatmentDateTime;
    }
}
