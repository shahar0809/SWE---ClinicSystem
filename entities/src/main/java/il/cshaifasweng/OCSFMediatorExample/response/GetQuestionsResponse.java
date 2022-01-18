package il.cshaifasweng.OCSFMediatorExample.response;


import il.cshaifasweng.OCSFMediatorExample.entities.Question;

import java.util.List;

public class GetQuestionsResponse extends Response {
    public List<Question> questions;

    public GetQuestionsResponse(List<Question> questions, boolean isSuccessful, String error) {
        super(isSuccessful, error);
        this.questions = questions;
    }

    public GetQuestionsResponse(List<Question> questions, boolean isSuccessful) {
        super(isSuccessful);
        this.questions = questions;
    }

    @Override
    public String getType() {
        return "GetQuestionsResponse";
    }
}