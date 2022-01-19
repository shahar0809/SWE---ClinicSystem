package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;

@Entity
public class ClinicManager extends ClinicEmployee {
    @OneToOne(mappedBy = "clinicManager")
    Clinic clinic;

    public ClinicManager() {
    }

    public ClinicManager(Clinic clinic) {
        this.clinic = clinic;
    }

    public ClinicManager(String username, String password, int employeeNum, String firstName, String lastName, String email,Clinic clinic) {
        super(username, password,employeeNum,firstName,lastName,email,"manager");
        this.clinic = clinic;
        clinic.setClinicManager(this);
    }

    public ClinicManager(String username, String password, int employeeNum, String firstName, String lastName, String email, String role) {
        super(username, password, employeeNum, firstName, lastName, email, role);
    }

    public ClinicManager(String username, String password, int employeeNum, String firstName, String lastName, String email) {
        super(username, password, employeeNum, firstName, lastName, email, null);
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
