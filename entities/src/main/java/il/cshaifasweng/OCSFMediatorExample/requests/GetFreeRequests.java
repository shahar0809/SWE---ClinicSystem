package il.cshaifasweng.OCSFMediatorExample.requests;

public class GetFreeRequests implements Request {

    @Override
    public String getType() {
        return "GetFreeRequests";
    }
}
