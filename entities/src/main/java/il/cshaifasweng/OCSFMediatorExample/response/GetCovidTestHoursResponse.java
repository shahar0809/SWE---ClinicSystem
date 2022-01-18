package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.utils.Hours;

public class GetCovidTestHoursResponse implements Response {
    public Hours testHours;

    public GetCovidTestHoursResponse(Hours testHours) {
        this.testHours = testHours;
    }

    @Override
    public String getType() {
        return "GetCovidTestHoursResponse";
    }
}