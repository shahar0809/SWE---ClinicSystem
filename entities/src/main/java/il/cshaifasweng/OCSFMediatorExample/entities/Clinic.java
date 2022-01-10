package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "Clinics")
public class Clinic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected int id;

    @Column(name = "name")
    protected String name;

    @Column(name = "openingHours")
    protected LocalTime openingHours;
    @Column(name = "closingHours")
    protected LocalTime closingHours;

    @Column(name = "CovidTestsStartHour")
    protected LocalTime CTstartHour;
    @Column(name = "CovidTestsEndHour")
    protected LocalTime CTendHour;

    @Column(name = "CovidVaccinesStartHour")
    protected LocalTime CVstartHour;
    @Column(name = "CovidVaccinesEndHour")
    protected LocalTime CVendHour;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinicManager")
    ClinicManager clinicManager;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hospitalManager")
    HospitalManager hospitalManager;

    public Clinic() {
    }

    public Clinic(String name, LocalTime openingHours, LocalTime closingHours) {
        this.name = name;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
        // As a default value
        this.CTstartHour = openingHours;
        this.CTendHour = closingHours;
        this.CVstartHour = openingHours;
        this.CVendHour = closingHours;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(LocalTime openingHours) {
        this.openingHours = openingHours;
    }

    public LocalTime getClosingHours() {
        return closingHours;
    }

    public void setClosingHours(LocalTime closingHours) {
        this.closingHours = closingHours;
    }

    public ClinicManager getClinicManager() {
        return clinicManager;
    }

    public void setClinicManager(ClinicManager clinicManager) {
        this.clinicManager = clinicManager;
    }

    public HospitalManager getHospitalManager() {
        return hospitalManager;
    }

    public void setHospitalManager(HospitalManager hospitalManager) {
        this.hospitalManager = hospitalManager;
    }

    public LocalTime getCovidTestStartHour() {
        return CTstartHour;
    }

    public void setCovidTestStartHour(LocalTime startHour) {
        this.CTstartHour = startHour;
    }

    public LocalTime getCovidTestEndHour() {
        return CTendHour;
    }

    public void setCovidTestEndHour(LocalTime endHour) {
        this.CTendHour = endHour;
    }

    public LocalTime getCovidVaccineStartHour() {
        return CVstartHour;
    }

    public void setCovidVaccineStartHour(LocalTime startHour) {
        this.CVstartHour = startHour;
    }

    public LocalTime getCovidVaccineEndHour() {
        return CVendHour;
    }

    public void setCovidVaccineEndHour(LocalTime endHour) {
        this.CVendHour = endHour;
    }
}
