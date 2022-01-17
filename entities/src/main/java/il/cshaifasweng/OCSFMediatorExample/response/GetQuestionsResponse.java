


public class GetQuestionsResponse extends Response {
    public List<Qurstion> questions;

    public GetQuestionsResponse(List<Question> questions, boolean isSuccessful, String error) {
        super(isSuccessful, error);
        this.questions = questions;
    }

    public GetQuestionsResponse(List<Qurstion> questions, boolean isSuccessful) {
        super(isSuccessful);
        this.questions = questions;
    }

    @Override
    public String getType() {
        return "GetQuestionsResponse";
    }
}