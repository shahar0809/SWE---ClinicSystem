package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
public class ProfessionDoctor extends ClinicMember {
    public ProfessionDoctor(AppointmentType type, String username, String password, int employeeNum, String firstName, String lastName, String email, String role) {
        super(type, username, password, employeeNum, firstName, lastName, email, role);
        super.appointmentDuration = 20;
    }
    public ProfessionDoctor() {
        super();
        super.appointmentDuration = 20;
    }
    public void addReceptionHours(Clinic clinic, LocalDate day, LocalTime t1, LocalTime t2) {
        LocalDateTime curTime = LocalDateTime.of(day, t1);
        while (t2.isAfter(LocalTime.from(curTime))) {
            AppointmentType a = AppointmentType.CARDIO; // todo fix
            ProfessionDoctorAppointment newApp = new ProfessionDoctorAppointment(a, this, curTime, clinic);
            if (this.checkIfFree(newApp.treatmentDateTime))
                this.addAppointment(newApp);
            curTime = curTime.plusMinutes(this.appointmentDuration);
        }
    }
}
