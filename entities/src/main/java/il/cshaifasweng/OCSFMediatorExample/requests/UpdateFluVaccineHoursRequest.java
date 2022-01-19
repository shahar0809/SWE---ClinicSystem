package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.utils.Hours;

public class UpdateFluVaccineHoursRequest extends Request {
    public Hours activeHours;
    public String clinicName;

    public UpdateFluVaccineHoursRequest(Hours hours, String activeClinic) {
        activeHours = hours;
        clinicName = activeClinic;
    }

    @Override
    public String getType() {
        return "UpdateFluVaccineHoursRequest";
    }
}