package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HospitalManager extends ClinicManager {
    @OneToMany(mappedBy = "hospitalManager")
    List<Clinic> clinics = new ArrayList<>();

    public HospitalManager() {
        super();
    }

    public HospitalManager(String username, String password, int employeeNum, String firstName, String lastName, String email, List<Clinic> clinics) {
        super(username, password, employeeNum, firstName, lastName, email);
        this.clinics = clinics;
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void addClinic(Clinic clinic) {
        clinics.add(clinic);
    }
}
