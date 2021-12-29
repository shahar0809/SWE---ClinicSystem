package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("ClinicManager")
public class ClinicManager extends ClinicEmployee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected int id;

    @OneToOne(mappedBy = "clinicManager")
    Clinic clinic;

    public ClinicManager() {
    }

    public ClinicManager(Clinic clinic) {
        this.clinic = clinic;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
