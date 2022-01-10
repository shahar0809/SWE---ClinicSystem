package il.cshaifasweng.OCSFMediatorExample.response;

public class UpdateActiveHoursResponse extends Response {
    public boolean succeed;

    public UpdateActiveHoursResponse() {
        succeed = true;
    }

    @Override
    public String getType() {
        return "UpdateActiveHoursResponse";
    }
}
