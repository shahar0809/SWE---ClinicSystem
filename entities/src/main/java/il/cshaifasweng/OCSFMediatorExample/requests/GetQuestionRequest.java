package il.cshaifasweng.OCSFMediatorExample.requests;

public class GetQuestionRequest implements Request {

    public GetQuestionRequest() {

    }

    @Override
    public String getType() {
        return "GetQuestionRequest";
    }
}
