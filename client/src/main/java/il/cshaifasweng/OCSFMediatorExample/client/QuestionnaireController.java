package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.requests.GetQuestionRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.SaveAnswerRequest;
import il.cshaifasweng.OCSFMediatorExample.response.GetQuestionsResponse;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

enum PossibleAnswers {
    YES("Yes"),
    NO("No");

    private final String type;

    PossibleAnswers(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}

public class QuestionnaireController {
    List<Question> questions = new ArrayList<>();
    boolean hasAnsweredQuestionnaire = false;

    @FXML
    private AnchorPane Covid;

    @FXML
    void Continue(ActionEvent event) throws IOException {
        if (question1.getValue() == null || question2.getValue() == null || question3.getValue() == null) {
            //alertUser()
            return;
        }
        hasAnsweredQuestionnaire = true;
        App.setRoot("ReserveAppointment");
    }

    @FXML
    void Question1(ActionEvent event) {
        PossibleAnswers selected = question1.getValue();

        if (selected == null)
            return;
        SaveAnswerRequest request = new SaveAnswerRequest(App.getActiveUser(), questions.get(0), selected.toString());
        App.getClient().sendRequest(request);
    }

    @FXML
    void Question2(ActionEvent event) {
        PossibleAnswers selected = question2.getValue();

        if (selected == null)
            return;
        else if(selected == "Yes"){
            SavaAnswerRequest request = new SavaAnswerRequest(App.getActiveUser() ,new Question(2, q2.text()) , "Yes");
            App.getClient().sendRequest(request);
        }
        else{
            SavaAnswerRequest request = new SavaAnswerRequest(App.getActiveUser() ,new Question(2, q2.text()) , "No");
            App.getClient().sendRequest(request);
        }
    }

    @FXML
    void Question3(ActionEvent event) {
        String selected = question1.getValue();

        if (selected == null)
            return;
        else if(selected == "Yes"){
            SavaAnswerRequest request = new SavaAnswerRequest(App.getActiveUser() ,new Question(3, q3.text()) , "Yes");
            App.getClient().sendRequest(request);
        }
        else{
            SavaAnswerRequest request = new SavaAnswerRequest(App.getActiveUser() ,new Question(3, q3.text()) , "No");
            App.getClient().sendRequest(request);
        }
    }

    @Subscribe
    public void getQuestions(GetQuestionsResponse response) {
        if (response.isSuccessful()) {
            questions.clear();
            questions.addAll(response.questions);

            q1.setText(questions.get(0).getQuestion());
            q2.setText(questions.get(1).getQuestion());
            q3.setText(questions.get(2).getQuestion());
        }
    }
}
