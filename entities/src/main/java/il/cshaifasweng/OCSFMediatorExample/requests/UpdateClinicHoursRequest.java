package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.utils.Hours;

public class UpdateClinicHoursRequest extends Request {
    public Hours activeHours;
    public String clinicName;

    public UpdateClinicHoursRequest(Hours hours, String activeClinic) {
        activeHours = hours;
        clinicName = activeClinic;
    }

    @Override
    public String getType() {
        return "UpdateClinicHoursRequest";
    }
}