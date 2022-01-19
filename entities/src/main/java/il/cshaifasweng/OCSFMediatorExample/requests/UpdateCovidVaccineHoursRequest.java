package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.utils.Hours;

public class UpdateCovidVaccineHoursRequest extends Request {
    public Hours activeHours;
    public String clinicName;

    public UpdateCovidVaccineHoursRequest(Hours hours, String activeClinic) {
        activeHours = hours;
        clinicName = activeClinic;
    }

    @Override
    public String getType() {
        return "UpdateCovidVaccineHoursRequest";
    }
}