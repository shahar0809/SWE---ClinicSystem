package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class PatientController extends BaseController {
    @FXML
    public Pane patientAppointments;
    @FXML
    public Pane availableAppointments;
    @FXML
    public Pane greenPass;
    @FXML
    public Pane identify;

    @FXML
    TabPane tabPane;
    @FXML
    Tab patientAppointmentsTab;
    @FXML
    Tab availableAppointmentsTab;
    @FXML
    Tab greenPassTab;
    @FXML
    Tab identifyTab;

    @FXML
    void initialize() {
        Parent parent;

        try {
            parent = App.loadFXML("PatientAppointments");
            patientAppointments.getChildren().clear();
            patientAppointments.getChildren().add(parent);
        } catch (IOException e) {
            alertUserError(e.getMessage());
        }

        try {
            parent = App.loadFXML("ReserveAppointment");
            availableAppointments.getChildren().clear();
            availableAppointments.getChildren().add(parent);
        } catch (IOException e) {
            alertUserError(e.getMessage());
        }

        try {
            parent = App.loadFXML("GreenPass");
            greenPass.getChildren().clear();
            greenPass.getChildren().add(parent);
        } catch (IOException e) {
            alertUserError(e.getMessage());
        }

        try {
            parent = App.loadFXML("PatientIdentify");
            identify.getChildren().clear();
            identify.getChildren().add(parent);
        } catch (IOException e) {
            alertUserError(e.getMessage());
        }
    }
}
