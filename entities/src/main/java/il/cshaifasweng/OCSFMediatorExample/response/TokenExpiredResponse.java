package il.cshaifasweng.OCSFMediatorExample.response;

public class TokenExpiredResponse implements Response {
    @Override
    public String getType() {
        return "TokenExpiredResponse";
    }
}
