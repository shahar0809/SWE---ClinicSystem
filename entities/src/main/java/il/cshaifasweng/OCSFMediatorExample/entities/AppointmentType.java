package il.cshaifasweng.OCSFMediatorExample.entities;

/**
 * Enum for appointments type to fetch from database.
 */
public enum AppointmentType {
    CovidTest("CovidTestAppointment"),
    FamilyDoctor("FamilyDoctorAppointment"),
    Nurse("NurseAppointmentAppointment"),
    ProfessionDoctor("ProfessionDoctorAppointment"),
    FluVaccine("FluVaccineAppointment"),
    CovidVaccine("CovidVaccineAppointment");

    private final String type;

    AppointmentType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
