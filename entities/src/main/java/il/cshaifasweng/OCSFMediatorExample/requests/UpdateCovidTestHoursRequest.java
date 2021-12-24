package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.utils.Hours;


public class UpdateCovidTestHoursRequest implements Request {
    public Hours activeHours;
    public String clinicName;

    public UpdateCovidTestHoursRequest(Hours hours, String activeClinic) {
        activeHours = hours;
        clinicName = activeClinic;
    }

    @Override
    public String getType() {
        return "UpdateCovidTestHoursRequest";
    }
}