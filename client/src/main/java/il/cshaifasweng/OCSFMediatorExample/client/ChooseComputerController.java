package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ChooseComputerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void Lab(ActionEvent event) throws IOException {
        App.setRoot("RegisterLogin");
//        App.setRoot("PrimaryManager");
    }

    @FXML
    void NotLab(ActionEvent event) throws IOException {
//        App.setRoot("RegisterLogin");
        App.setRoot("PrimaryManager");
    }

    @FXML
    void initialize() {

    }

}
