package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "Clinics")
public class Clinic {
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinicManager")
    ClinicManager clinicManager;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hospitalManager")
    HospitalManager hospitalManager;

    public Clinic() {
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
}
