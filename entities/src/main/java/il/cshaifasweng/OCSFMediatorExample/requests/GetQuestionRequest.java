package il.cshaifasweng.OCSFMediatorExample.requests;

public class GetQuestionRequest extends Request {

    public GetQuestionRequest() {

    }

    @Override
    public String getType() {
        return "GetQuestionRequest";
    }
}
