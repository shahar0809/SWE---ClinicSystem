package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

// TODO: Decide if we need base class for doctor and nurse [instead of doctor in here]
@Entity
@Table(name = "Appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient")
    protected Patient patient;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinicMember", nullable = false)
    protected ClinicMember clinicMember;

    @Column(name = "appointment_time")
    protected LocalDateTime treatmentDateTime;

    @Column(name = "isAvailable")
    protected boolean isAvailable;

    public Appointment() {}

    public Appointment(Patient patient, ClinicMember doctor, LocalDateTime treatmentDateTime) {
        this.patient = patient;
        this.clinicMember = doctor;
        this.treatmentDateTime = treatmentDateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClinicMember getClinicMember() {
        return clinicMember;
    }

    public void setClinicMember(ClinicMember clinicMember) {
        this.clinicMember = clinicMember;
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
