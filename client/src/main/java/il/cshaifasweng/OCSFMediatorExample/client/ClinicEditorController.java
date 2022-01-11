package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.requests.GetAllClinicsRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.GetClinicRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.UpdateActiveHoursRequest;
import il.cshaifasweng.OCSFMediatorExample.response.GetAllClinicsResponse;
import il.cshaifasweng.OCSFMediatorExample.response.GetClinicResponse;
import il.cshaifasweng.OCSFMediatorExample.response.UpdateActiveHoursResponse;
import il.cshaifasweng.OCSFMediatorExample.utils.Hours;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.time.LocalTime;

public class ClinicEditorController {
    @FXML
    private ListView<String> clinicList;

    @FXML
    private TextField operatingHours;

    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);
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

    @Subscribe
    public void updateHours(UpdateActiveHoursResponse response) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Working Hours Updated!", ButtonType.OK);
        alert.setHeaderText("Success");
        alert.show();
    }

    @Subscribe
    public void updateWorkingHours(GetClinicResponse response) {
        String workingHours = response.clinic.getOpeningHours().toString() + " - " + response.clinic.getClosingHours().toString();
        operatingHours.setText(workingHours);
    }

    @Subscribe
    public void updateListOfClinic(GetAllClinicsResponse response) {
        for (Clinic clinic : response.clinics) {
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

