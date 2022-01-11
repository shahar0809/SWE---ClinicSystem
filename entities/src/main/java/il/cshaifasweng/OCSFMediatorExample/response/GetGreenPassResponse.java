package il.cshaifasweng.OCSFMediatorExample.response;

public class GetGreenPassResponse extends Response {
    public boolean canGetGreenPass;

    public GetGreenPassResponse(boolean canGetGreenPass, boolean isSuccessful) {
        super(isSuccessful);
        this.canGetGreenPass = canGetGreenPass;
    }

    @Override
    public String getType() {
        return "GetGreenPassResponse";
    }
}
