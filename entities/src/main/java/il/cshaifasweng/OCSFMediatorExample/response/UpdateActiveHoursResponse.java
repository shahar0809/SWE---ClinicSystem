package il.cshaifasweng.OCSFMediatorExample.response;

public class UpdateActiveHoursResponse implements Response {
    public boolean succeed;

    public UpdateActiveHoursResponse() {
        succeed = true;
    }

    @Override
    public String getType() {
        return "UpdateActiveHoursResponse";
    }
}
