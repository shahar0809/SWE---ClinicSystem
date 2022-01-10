package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.utils.Hours;

public class GetCovidVaccineHoursResponse implements Response {
    public Hours testHours;

    public GetCovidVaccineHoursResponse(Hours testHours) {
        this.testHours = testHours;
    }

    @Override
    public String getType() {
        return "GetCovidVaccineHoursResponse";
    }
}