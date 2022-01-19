package il.cshaifasweng.OCSFMediatorExample.utils;

import java.io.Serializable;
import java.time.LocalTime;

public class Hours implements Serializable {
    private LocalTime openingHours;
    private LocalTime closingHours;

    public Hours(LocalTime openingHours, LocalTime closingHours) {
        this.openingHours = openingHours;
        this.closingHours = closingHours;
    }

    public LocalTime getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(LocalTime openingHours) {
        this.openingHours = openingHours;
    }

    public LocalTime getClosingHours() {
        return closingHours;
    }

    public void setClosingHours(LocalTime closingHours) {
        this.closingHours = closingHours;
    }
}
