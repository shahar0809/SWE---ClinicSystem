package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Patient;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.requests.GetQuestionRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.SaveAnswerRequest;
import il.cshaifasweng.OCSFMediatorExample.response.GetQuestionsResponse;
import il.cshaifasweng.OCSFMediatorExample.response.SaveAnswerResponse;
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

public class QuestionnaireController extends BaseController {
    List<Question> questions = new ArrayList<>();
    boolean hasAnsweredQuestionnaire = false;
    int answersCounter = 0;

    @FXML
    private AnchorPane Covid;

    @FXML
    private Text q1;
    @FXML
    private Text q2;
    @FXML
    private Text q3;

    @FXML
    private ComboBox<PossibleAnswers> question1;
    @FXML
    private ComboBox<PossibleAnswers> question2;
    @FXML
    private ComboBox<PossibleAnswers> question3;

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);

        question1.getItems().addAll(FXCollections.observableArrayList(PossibleAnswers.values()));
        question2.getItems().addAll(FXCollections.observableArrayList(PossibleAnswers.values()));
        question3.getItems().addAll(FXCollections.observableArrayList(PossibleAnswers.values()));
    }

    @Override
    public void start() {
        App.getClient().sendRequest(new GetQuestionRequest());
    }

    @FXML
    void Continue(ActionEvent event) throws IOException {
        if (question1.getValue() == null || question2.getValue() == null || question3.getValue() == null) {
            informUser("Please answer all the questions!");
            return;
        }

        App.getClient().sendRequest(new SaveAnswerRequest((Patient) App.getActiveUser(), questions.get(0), question1.getValue().toString()));
        App.getClient().sendRequest(new SaveAnswerRequest((Patient) App.getActiveUser(), questions.get(1), question2.getValue().toString()));
        App.getClient().sendRequest(new SaveAnswerRequest((Patient) App.getActiveUser(), questions.get(2), question3.getValue().toString()));

        if (answersCounter == 3) {
            hasAnsweredQuestionnaire = true;
        }
        answersCounter = 0;
        App.setRoot("PatientHome");
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

    @Subscribe
    public void getAnswer(SaveAnswerResponse response) {
        if (response.isSuccessful()) {
            answersCounter++;
        }
    }
}
