package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HospitalManager extends ClinicManager {
    @OneToMany(mappedBy = "hospitalManager")
    List<Clinic> clinics;

    public HospitalManager() {
        super();
        clinics = new ArrayList<>();
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void addClinic(Clinic clinic) {
        clinics.add(clinic);
    }
}
