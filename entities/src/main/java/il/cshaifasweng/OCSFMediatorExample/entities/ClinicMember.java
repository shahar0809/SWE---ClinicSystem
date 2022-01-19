package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class ClinicMember extends ClinicEmployee {
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    protected List<Appointment> appointments = new ArrayList<>();

    protected AppointmentType type;
    protected int appointmentDuration;

    public ClinicMember(AppointmentType type, String username, String password, int employeeNum, String firstName, String lastName, String email,
                        String role) {
        super(username, password, employeeNum, firstName, lastName, email, role);
        this.type = type;
    }

    public ClinicMember() {
        super();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    protected void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
    protected void removeAppointment(Appointment appointment) { appointments.remove(appointment); }

    public abstract void addReceptionHours(Clinic clinic, LocalDate day, LocalTime t1, LocalTime t2);
    public void freeReceptionHours(LocalDate day, LocalTime t1, LocalTime t2) {
        for (Appointment a : this.appointments) {
            LocalTime aTime = LocalTime.from(a.getTreatmentDateTime());
            if (!aTime.isAfter(t2) && !aTime.plusMinutes(this.appointmentDuration).isBefore(t1))
                this.removeAppointment(a);
        }
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Appointment> getAppointmentsOnDate(LocalDate date) {
        ArrayList<Appointment> res = new ArrayList<>();
        for (Appointment a : appointments) {
            if (LocalDate.from(a.getTreatmentDateTime()).isEqual(date)) {
                res.add(a);
            }
        }
        return res;
    }

    public boolean checkIfFree(LocalDateTime timeToCheck) {
        for (Appointment a : this.appointments) {
            if (timeToCheck.isAfter(a.treatmentDateTime) && timeToCheck.isBefore(a.treatmentDateTime.plusMinutes(this.appointmentDuration))) {
                return false;
            }
        }
        return true;
    }
}
