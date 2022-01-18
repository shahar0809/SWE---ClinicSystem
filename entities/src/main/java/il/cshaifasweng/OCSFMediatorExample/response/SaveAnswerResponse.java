package il.cshaifasweng.OCSFMediatorExample.response;

public class SaveAnswerResponse extends Response {
    public SaveAnswerResponse(boolean isSuccessful, String error) {
        super(isSuccessful, error);
    }

    public SaveAnswerResponse(boolean isSuccessful) {
        super(isSuccessful);
    }

    @Override
    public String getType() {
        return "SaveAnswerResponse";
    }
}