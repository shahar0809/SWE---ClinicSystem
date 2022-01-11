package il.cshaifasweng.OCSFMediatorExample.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;

// TODO: Decide if we need base class for doctor and nurse [instead of doctor in here]
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="appointmentType",
        discriminatorType = DiscriminatorType.STRING)
@Table(name = "Appointments")
public abstract class Appointment implements Serializable {
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
    public String toString() {
        return "Appointment{" +
                "treatmentDateTime=" + treatmentDateTime +
                ", member=" + member +
                ", clinic=" + clinic +
                '}';
    }

    @Column(name = "isAvailable")
    protected boolean isAvailable;

    @Column(name = "cameToAppointment")
    protected boolean cameToAppointment;

    public Appointment() {
    }

    public Appointment(LocalDateTime treatmentDateTime, ClinicMember member, Clinic clinic) {
        this.treatmentDateTime = treatmentDateTime;
        this.member = member;
        this.clinic = clinic;
    }

    public Appointment(Patient patient, LocalDateTime treatmentDateTime, ClinicMember member, Clinic clinic) {
        this.patient = patient;
        this.treatmentDateTime = treatmentDateTime;
        this.member = member;
        this.clinic = clinic;
        this.isAvailable = true;
        this.cameToAppointment = false;
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

    public boolean cameToAppointment() {
        return cameToAppointment;
    }

    public void cameToAppointment(boolean isCame) {
        cameToAppointment = isCame;
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
    FluVaccine("FluVaccineAppointment"),
    CovidVaccine("CovidVaccineAppointment");

    private final String type;

    AppointmentType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
