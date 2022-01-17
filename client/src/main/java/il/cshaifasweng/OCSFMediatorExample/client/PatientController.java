package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class PatientController extends BaseController {
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
    AppointmentController availableAppointmentsController;
    @FXML
    PatientAppointmentsController patientAppointmentsController;
    @FXML
    GreenPassController greenPassController;
    @FXML
    PatientIdentifyController identifyController;
}
