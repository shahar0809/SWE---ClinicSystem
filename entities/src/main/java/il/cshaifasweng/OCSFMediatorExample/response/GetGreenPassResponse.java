package il.cshaifasweng.OCSFMediatorExample.response;

public class GetGreenPassResponse extends Response {
    boolean canGetGreenPass;

    public boolean isEligible() {
        return canGetGreenPass;
    }

    public GetGreenPassResponse(boolean canGetGreenPass, boolean isSuccessful, String error) {
        super(isSuccessful, error);
        this.canGetGreenPass = canGetGreenPass;
    }

    public GetGreenPassResponse(boolean canGetGreenPass, boolean isSuccessful) {
        super(isSuccessful);
        this.canGetGreenPass = canGetGreenPass;
    }

    @Override
    public String getType() {
        return "GetGreenPassResponse";
    }
}
