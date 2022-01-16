package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("hospitalManager")
public class HospitalManager extends ClinicManager {
    @OneToMany(mappedBy = "hospitalManager")
    List<Clinic> clinics = new ArrayList<>();

    public HospitalManager() {
        super();
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void addClinic(Clinic clinic) {
        clinics.add(clinic);
    }
}
