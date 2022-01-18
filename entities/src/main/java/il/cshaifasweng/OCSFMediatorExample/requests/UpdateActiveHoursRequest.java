package il.cshaifasweng.OCSFMediatorExample.requests;


import il.cshaifasweng.OCSFMediatorExample.utils.Hours;

public class UpdateActiveHoursRequest extends Request {
    public Hours activeHours;
    public String clinicName;

    public UpdateActiveHoursRequest(Hours hours, String activeClinic) {
        activeHours = hours;
        clinicName = activeClinic;
    }

    @Override
    public String getType() {
        return "UpdateActiveHoursRequest";
    }
}
