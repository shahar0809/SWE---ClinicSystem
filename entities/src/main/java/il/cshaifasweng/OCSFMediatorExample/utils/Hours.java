package il.cshaifasweng.OCSFMediatorExample.utils;

import java.time.LocalTime;

public class Hours {
    public LocalTime openingHours;
    public LocalTime closingHours;

    public Hours(LocalTime opening, LocalTime closing){
        openingHours = opening;
        closingHours = closing;
    }
}
