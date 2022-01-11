package il.cshaifasweng.OCSFMediatorExample.response;

public class GetGreenPassResponse extends Response {
    public boolean canGetGreenPass;

    public GetGreenPassResponse(boolean succeed, boolean canGetGreenPass)
    {
        super(succeed);
        this.canGetGreenPass = canGetGreenPass;
    }

    @Override
    public String getType() {
        return "GetGreenPassResponse";
    }
}
