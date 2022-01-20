package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.requests.GetProfessionDoctorClinicsRequest;
import il.cshaifasweng.OCSFMediatorExample.response.GetProfessionDoctorClinicsResponse;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class ChooseDoctorController extends BaseController {
    @FXML
    private ObservableList<ClinicMember> doctors;

    @FXML
    TableView<ClinicMember> table = new TableView<>();

    @FXML
    private TableColumn<ClinicMember, String> DoctorFirstColumn;

    @FXML
    private TableColumn<ClinicMember, String> clinicColumn;

    @FXML
    private TableColumn<ClinicMember, String> doctorLastColumn;


    public void initialize() {
        EventBus.getDefault().register(this);
        doctors = FXCollections.observableArrayList();
        table.setItems(doctors);

        DoctorFirstColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        doctorLastColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        clinicColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClinic().toString()));

        App.getClient().sendRequest(new GetProfessionDoctorClinicsRequest(AppointmentController.comboType, (Patient) App.getActiveUser()));
    }

    @FXML
    void Continue(ActionEvent event) throws IOException {
        ClinicMember selectedDoctor = table.getSelectionModel().getSelectedItem();
        if (selectedDoctor == null)
            App.setRoot("PatientHome");
        AppointmentController.doctor = selectedDoctor;

        App.setRoot("PatientHome");
    }

    @Subscribe
    public void GetProfessionDoctor(GetProfessionDoctorClinicsResponse response) {
        if (response.isSuccessful()) {
            doctors.clear();
            doctors.addAll(response.getDoctors());
            table.setItems(doctors);
            table.refresh();
        } else {
            alertUserError(response.getError());
        }
    }

    public void alertUserError(String message) {
        Alert alert  = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.show();
    }

}

