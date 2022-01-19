package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.AppointmentType;
import il.cshaifasweng.OCSFMediatorExample.requests.DeleteAppointmentRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.GetPatientAppointmentRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.ReserveAppointmentRequest;
import il.cshaifasweng.OCSFMediatorExample.response.DeleteAppointmentResponse;
import il.cshaifasweng.OCSFMediatorExample.response.GetFreeAppointmentsResponse;
import il.cshaifasweng.OCSFMediatorExample.response.GetPatientAppointmentResponse;
import il.cshaifasweng.OCSFMediatorExample.utils.Messages;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class PatientAppointmentsController extends BaseController {
    @FXML
    TableView<Appointment> table = new TableView<>();
    @FXML
    TableColumn<Appointment, String> typeColumn;
    @FXML
    TableColumn<Appointment, String> memberColumn;
    @FXML
    TableColumn<Appointment, String> dateColumn;
    @FXML
    TableColumn<Appointment, String> clinicColumn;

    @FXML
    private ObservableList<Appointment> appointments;

    public void initialize() {
        EventBus.getDefault().register(this);
        appointments = FXCollections.observableArrayList();

        table.setItems(appointments);

        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        memberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMember().toString()));
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTreatmentDateTimeString()));
        clinicColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClinic().toString()));

        onRefresh(null);
    }

    @FXML
    public void onCancel(ActionEvent actionEvent) {
        Appointment selectedAppointment = table.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null)
            return;
        DeleteAppointmentRequest request = new DeleteAppointmentRequest(selectedAppointment, App.getActiveUser());
        App.getClient().sendRequest(request);
    }

    @Subscribe
    public void cancelResponse(DeleteAppointmentResponse response) {
        if (response.isSuccessful()) {
            informUser(Messages.CANCEL_APPOINTMENT_SUCCESS);
            onRefresh(null);
        } else {
            alertUserError(response.getError());
        }
    }

    @FXML
    public void onRefresh(ActionEvent actionEvent) {
        App.getClient().sendRequest(new GetPatientAppointmentRequest(App.getActiveUser()));
    }

    @Subscribe
    public void onPatientAppointmentsResponse(GetPatientAppointmentResponse response) {
        if (response.isSuccessful()) {
            appointments.clear();
            appointments.addAll(response.appointments);
            table.setItems(appointments);
            table.refresh();
        } else {
            alertUserError(response.getError());
        }
    }
}
