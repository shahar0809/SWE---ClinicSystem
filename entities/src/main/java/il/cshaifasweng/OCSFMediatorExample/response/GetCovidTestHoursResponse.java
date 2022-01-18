package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.utils.Hours;

public class GetCovidTestHoursResponse extends Response {
    public Hours testHours;

    public GetCovidTestHoursResponse(Hours testHours, boolean isSuccessful) {
        super(isSuccessful);
        this.testHours = testHours;
    }

    public GetCovidTestHoursResponse(Hours testHours, boolean isSuccessful, String error) {
        super(isSuccessful, error);
        this.testHours = testHours;
    }

    @Override
    public String getType() {
        return "GetCovidTestHoursResponse";
    }
}