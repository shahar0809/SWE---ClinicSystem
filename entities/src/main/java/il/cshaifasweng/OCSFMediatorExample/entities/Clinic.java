package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    protected LocalDateTime openingHours;
    @Column(name = "closingHours")
    protected LocalDateTime closeingHours;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "clinic")
    ClinicManager clinicManager;

    @ManyToOne(cascade = CascadeType.ALL)
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

    public LocalDateTime getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(LocalDateTime openingHours) {
        this.openingHours = openingHours;
    }

    public LocalDateTime getCloseingHours() {
        return closeingHours;
    }

    public void setCloseingHours(LocalDateTime closeingHours) {
        this.closeingHours = closeingHours;
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
