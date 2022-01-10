package il.cshaifasweng.OCSFMediatorExample.response;

public class UpdateActiveHoursResponse extends Response {
    public UpdateActiveHoursResponse(boolean succeed) {
       super(succeed);
    }

    @Override
    public String getType() {
        return "UpdateActiveHoursResponse";
    }
}
