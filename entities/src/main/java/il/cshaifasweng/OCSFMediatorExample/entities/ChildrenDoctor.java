package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class ChildrenDoctor extends ClinicMember {
    public ChildrenDoctor(String username, String password, int employeeNum, String firstName, String lastName, String email, String role) {
        super(AppointmentType.CHILDREN, username, password, employeeNum, firstName, lastName, email, role);
        super.appointmentDuration = 15;
    }

    public ChildrenDoctor() {
        super();
        super.appointmentDuration = 15;
    }

    public void addReceptionHours(Clinic clinic, LocalDate day, LocalTime t1, LocalTime t2) {
        LocalDateTime curTime = LocalDateTime.of(day, t1);
        while (t2.isAfter(LocalTime.from(curTime))) {
            ChildrenDoctorAppointment newApp = new ChildrenDoctorAppointment(this, curTime, clinic);
            if (this.checkIfFree(newApp.treatmentDateTime))
                this.addAppointment(newApp);
            curTime = curTime.plusMinutes(this.appointmentDuration);
        }
    }
}
