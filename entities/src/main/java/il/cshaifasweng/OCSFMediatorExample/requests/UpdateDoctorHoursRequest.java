package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.ClinicMember;
import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateDoctorHoursRequest extends Request {
    public Clinic clinic;
    public ClinicMember doctor;
    public LocalDate day;
    public LocalTime t1;
    public LocalTime t2;

    public UpdateDoctorHoursRequest(Clinic c, ClinicMember d, LocalDate da, LocalTime tt1, LocalTime tt2) {
        clinic = c;
        doctor = d;
        day = da;
        t1 = tt1;
        t2 = tt2;
    }

    @Override
    public String getType() {
        return "UpdateDoctorHoursRequest";
    }
}
