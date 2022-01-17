import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class PleaseProvideControllerClassName {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane Covid;

    @FXML
    void Continue(ActionEvent event) {

    }

    @FXML
    void Question1(ActionEvent event) {
        String selected = question1.getValue();

        if (selected == null)
            return;
        else if(selected == "Yes"){
            SavaAnswerRequest request = new SavaAnswerRequest(App.getActiveUser() ,new Question(1, q1.text()), "Yes");
            App.getClient().sendRequest(request);
        }
        else{
            SavaAnswerRequest request = new SavaAnswerRequest(App.getActiveUser() , new Question(1, q1.text()), "No");
            App.getClient().sendRequest(request);
        }
    }

    @FXML
    void Question2(ActionEvent event) {
        String selected = question1.getValue();

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

    @FXML
    void initialize() {
        assert Covid != null : "fx:id=\"Covid\" was not injected: check your FXML file 'CovidQuestionnaire.fxml'.";
        GetQuestionsRequest request = new GetQuestionsRequest();
        App.getClient().sendRequest(request);
    }

    @Subscribe
    public void getQuestions(ReserveQuestionsResponse response) {
        if (response.isSuccessful()) {
             q1.setText(response.questions.get(0).getQuestion());
             q2.setText(response.questions.get(1).getQuestion());
             q3.setText(response.questions.get(2).getQuestion());
        }
    }
}
