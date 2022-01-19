package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.ClinicMember;
import il.cshaifasweng.OCSFMediatorExample.requests.GetMemberAppointmentsRequest;
import il.cshaifasweng.OCSFMediatorExample.response.GetMemberAppointmentsResponse;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MemberController {
    @FXML
    TableView<Appointment> table = new TableView<>();
    @FXML
    TableColumn<Appointment, String> typeColumn;
    @FXML
    TableColumn<Appointment, String> patientColumn;
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
        patientColumn.setCellValueFactory(cellData -> {
            String patient = "";
            if (cellData.getValue().getPatient() != null)
                patient = cellData.getValue().getPatient().toString();
            return new SimpleStringProperty(patient);
        });
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTreatmentDateTimeString()));
        clinicColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClinic().toString()));

        onRefresh(null);
    }

    @FXML
    public void onRefresh(ActionEvent actionEvent) {
        App.getClient().sendRequest(new GetMemberAppointmentsRequest((ClinicMember) App.getActiveUser()));
    }

    @Subscribe
    public void onPatientAppointmentsResponse(GetMemberAppointmentsResponse response) {
        if (response.isSuccessful()) {
            appointments.clear();
            appointments.addAll(response.getAppointments());
            table.setItems(appointments);
            table.refresh();
        } else {
            //al(response.getError());
        }
    }
}
