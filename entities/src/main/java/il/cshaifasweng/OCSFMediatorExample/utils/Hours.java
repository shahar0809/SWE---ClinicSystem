package il.cshaifasweng.OCSFMediatorExample.utils;

import java.io.Serializable;
import java.time.LocalTime;

public class Hours implements Serializable {
    public LocalTime openingHours;
    public LocalTime closingHours;

    public Hours(LocalTime opening, LocalTime closing) {
        openingHours = opening;
        closingHours = closing;
    }
}
