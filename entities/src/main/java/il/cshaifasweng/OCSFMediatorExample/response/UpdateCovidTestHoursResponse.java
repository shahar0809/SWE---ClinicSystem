package il.cshaifasweng.OCSFMediatorExample.response;


public class UpdateCovidTestHoursResponse implements Response {
    public boolean succeed;

    public UpdateCovidTestHoursResponse() {
        succeed = true;
    }

    @Override
    public String getType() {
        return "UpdateCovidTestHoursResponse";
    }
}
