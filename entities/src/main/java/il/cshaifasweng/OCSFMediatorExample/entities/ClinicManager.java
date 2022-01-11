package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="userID")
@DiscriminatorValue("clinicManager")
public class ClinicManager extends ClinicEmployee {
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
