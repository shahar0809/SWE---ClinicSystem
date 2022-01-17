package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.AppointmentType;
import javafx.util.StringConverter;

public class AppointmentConverter extends StringConverter<AppointmentType> {
    public AppointmentType fromString(String string) {
        return null;
    }

    public String toString(AppointmentType type) {
        return type.toString();
    }
}
