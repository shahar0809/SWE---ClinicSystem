package il.cshaifasweng.OCSFMediatorExample.requests;


import il.cshaifasweng.OCSFMediatorExample.entities.Answer;
import il.cshaifasweng.OCSFMediatorExample.entities.Patient;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class SaveAnswerRequest extends Request {
    public Answer answer;

    public SaveAnswerRequest(Patient patient, Question question, String answer) {
        this.answer = new Answer(patient, question, answer);
    }

    @Override
    public String getType() {
        return "SaveAnswerRequest";
    }
}


