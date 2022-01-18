package il.cshaifasweng.OCSFMediatorExample.requests;


import il.cshaifasweng.OCSFMediatorExample.entities.Answer;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class SaveAnswerRequest implements Request {
    public Answer answer;

    public SaveAnswerRequest(User user, Question question, String answer) {
        this.answer = new Answer(this.user = user, this.question = question, this.answer = answer)
        this.user = user;
    }

    @Override
    public String getType() {
        return "SaveAnswerRequest";
    }
}


