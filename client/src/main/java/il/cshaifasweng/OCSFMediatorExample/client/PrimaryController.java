package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.requests.GetAllClinicsRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.GetClinicRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.UpdateActiveHoursRequest;
import il.cshaifasweng.OCSFMediatorExample.utils.Hours;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalTime;
import java.util.List;

public class PrimaryController {
    @FXML
    private ListView<String> clinicList;

    @FXML
    private TextField operatingHours;

    @FXML
    public void initialize() {
        clinicList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        GetAllClinicsRequest requestAllClinic = new GetAllClinicsRequest();
        App.getClient().sendRequest(requestAllClinic);
        clinicList.getSelectionModel().selectedItemProperty().addListener((observableValue, newValue, oldValue) -> operatingHours.clear());
    }
    
    @FXML
    void onUpdate(ActionEvent event) {
        String selectedClinic = clinicList.getSelectionModel().getSelectedItem();
        if (selectedClinic == null)
            return;
        String newWorkingHours = operatingHours.getText();
        if (newWorkingHours.isEmpty())
            return;
        Hours hours = new Hours(LocalTime.parse(newWorkingHours.substring(0, newWorkingHours.indexOf(" "))), LocalTime.parse(newWorkingHours.substring(newWorkingHours.indexOf(" ") + 3)));
        UpdateActiveHoursRequest requestUpdateActiveHours = new UpdateActiveHoursRequest(hours, selectedClinic);
        App.getClient().sendRequest(requestUpdateActiveHours);
    }

    void updateHours() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Working Hours Updated!", ButtonType.OK);
        alert.setHeaderText("Success");
        alert.show();
    }

    void updateWorkingHours(Clinic clinic) {
        String workingHours = clinic.getOpeningHours().toString() + " - " + clinic.getClosingHours().toString();
        operatingHours.setText(workingHours);
    }

    void updateListOfClinic(List<Clinic> clinics) {
        for (Clinic clinic : clinics) {
            clinicList.getItems().add(clinic.getName());
        }
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        String selectedClinic = clinicList.getSelectionModel().getSelectedItem();
        if (selectedClinic == null)
            return;
        GetClinicRequest requestClinic = new GetClinicRequest(selectedClinic);
        App.getClient().sendRequest(requestClinic);
    }
}

