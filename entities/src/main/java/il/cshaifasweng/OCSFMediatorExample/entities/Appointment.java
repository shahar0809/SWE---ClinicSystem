package il.cshaifasweng.OCSFMediatorExample.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "Appointments")
public abstract class Appointment implements Serializable, Comparable<Appointment> {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    protected Integer id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "patient")
    protected Patient patient;

    @Column(name = "appointment_time", nullable = false)
    protected LocalDateTime treatmentDateTime;

    @Column(name = "arrival_time")
    protected LocalDateTime arrivalDateTime;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "member", nullable = false)
    protected ClinicMember member;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinic", nullable = false)
    protected Clinic clinic;

    @Column(name = "appointmentType")
    protected AppointmentType type;

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

    public abstract String getType();

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
        if (patientArrived)
            this.arrivalDateTime = LocalDateTime.now();
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

    public String getTreatmentDateTimeString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM hh:mm");
        return treatmentDateTime.format(format);
    }

    public LocalDateTime getTreatmentDateTime() {
        return treatmentDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setTreatmentDateTime(LocalDateTime treatmentDateTime) {
        this.treatmentDateTime = treatmentDateTime;
    }
}

