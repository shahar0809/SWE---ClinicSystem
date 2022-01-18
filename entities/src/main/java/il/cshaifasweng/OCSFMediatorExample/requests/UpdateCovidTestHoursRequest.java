package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.utils.Hours;


public class UpdateCovidTestHoursRequest extends Request {
    public Hours activeHours;
    public String clinicName;

    /*
    -> change the hours of the Corona tests, and if there is a patient who had already
        set a test appointment not between the new hours:
        1. check if there is an available appointment:
            yes -> set this appointment to the patient and send him message about the change
                    (the patient can cancel or set another appointment as he want)
            no -> send message that the appointment has been canceled and that there is no available
                    appointments at the moment.
     Note: the database can't be accessed from here, thus all the checks would be done in SimpleServer file.
     */
    public UpdateCovidTestHoursRequest(Hours hours, String activeClinic) {
        activeHours = hours;
        clinicName = activeClinic;
    }

    @Override
    public String getType() {
        return "UpdateCovidTestHoursRequest";
    }
}