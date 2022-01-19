package il.cshaifasweng.OCSFMediatorExample.entities;

/**
 * Enum for appointments type to fetch from database.
 */
public enum AppointmentType {
    NURSE("Nurse/Lab"),
    COVID_TEST("Covid Test"),
    COVID_VACCINE("Covid Vaccine"),
    FLU_VACCINE("Flu Vaccine"),
    FAMILY_OR_CHILDREN("Family/Children"),
    FAMILY("Family"),
    CHILDREN("Children"),
    CARDIO("Cardiology"),
    ORTHOPEDICS("Orthopedics"),
    GYNECOLOGY("Gynecology"),
    OTOLARYNGOLOGY("Otolaryngology"),
    GASTROLOGY("Gastrology");

    private final String type;

    AppointmentType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
