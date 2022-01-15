package il.cshaifasweng.OCSFMediatorExample.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

// TODO: Decide if we need base class for doctor and nurse [instead of doctor in here]
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="appointmentType",
        discriminatorType = DiscriminatorType.STRING)
@Table(name = "Appointments")
public abstract class Appointment implements Serializable, Comparable<Appointment> {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    protected Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient")
    protected Patient patient;

    @Column(name = "appointment_time", nullable = false)
    protected LocalDateTime treatmentDateTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member", nullable = false)
    protected ClinicMember member;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinic", nullable = false)
    protected Clinic clinic;

    @Override
    public int compareTo(Appointment o) {
        return this.treatmentDateTime.compareTo(o.treatmentDateTime);
    }

    @Column(name = "isAvailable")
    protected boolean isAvailable;

    @Column(name = "patientArrived")
    protected boolean patientArrived;

    public Appointment() {
    }

    public Appointment(LocalDateTime treatmentDateTime, ClinicMember member, Clinic clinic) {
        this.treatmentDateTime = treatmentDateTime;
        this.member = member;
        this.clinic = clinic;
        this.isAvailable = true;
    }

    public Appointment(Patient patient, LocalDateTime treatmentDateTime, ClinicMember member, Clinic clinic) {
        this.patient = patient;
        this.treatmentDateTime = treatmentDateTime;
        this.member = member;
        this.clinic = clinic;
        this.isAvailable = false;
        this.patientArrived = false;
    }

    public ClinicMember getMember() {
        return member;
    }

    public void setMember(ClinicMember member) {
        this.member = member;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public Integer getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean hasPatientArrived() {
        return patientArrived;
    }

    public void setPatientArrived(boolean patientArrived) {
        this.patientArrived = patientArrived;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM hh:mm");
        return "Appointment{" +
                "patient=" + patient +
                ", treatmentDateTime=" + treatmentDateTime.format(format) +
                ", member=" + member +
                ", clinic=" + clinic +
                '}';
    }

    public LocalDateTime getTreatmentDateTime() {
        return treatmentDateTime;
    }

    public void setTreatmentDateTime(LocalDateTime treatmentDateTime) {
        this.treatmentDateTime = treatmentDateTime;
    }
}

