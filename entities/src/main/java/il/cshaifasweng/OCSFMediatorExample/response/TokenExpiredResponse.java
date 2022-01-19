package il.cshaifasweng.OCSFMediatorExample.response;

public class TokenExpiredResponse extends Response {
    public TokenExpiredResponse(boolean isSuccessful) {
        super(isSuccessful);
    }

    public TokenExpiredResponse(boolean isSuccessful, String error) {
        super(isSuccessful, error);
    }

    @Override
    public String getType() {
        return "TokenExpiredResponse";
    }
}
