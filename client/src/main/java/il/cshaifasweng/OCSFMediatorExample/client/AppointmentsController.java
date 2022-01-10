package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.cellFactory.AppointmentCellFactory;
import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.requests.GetFreeAppointmentRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.ReserveAppointmentRequest;
import il.cshaifasweng.OCSFMediatorExample.response.GetFreeAppointmentsResponse;
import il.cshaifasweng.OCSFMediatorExample.response.ReserveAppointmentResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AppointmentsController {
    @FXML
    private ListView<Appointment> listView;
    private ObservableList<Appointment> appointments;

    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);
        appointments = FXCollections.observableArrayList();
        listView = new ListView<>(appointments);
        listView.setCellFactory(new AppointmentCellFactory());

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Background thread to continuously update appointments
        new Thread(() -> {
            while (true) {
                GetFreeAppointmentRequest requestFreeAppointments = new GetFreeAppointmentRequest();
                App.getClient().sendRequest(requestFreeAppointments);
            }
        });
    }

    @Subscribe
    public void ReserveAppointment(ReserveAppointmentResponse response) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment reserved!", ButtonType.OK);
        alert.setHeaderText("Success");
        alert.show();
    }

    @Subscribe
    public void updateAppointmentsList(GetFreeAppointmentsResponse response) {
        appointments.addAll(response.getAppointments());
    }

    @FXML
    public void onReserve(ActionEvent actionEvent) {
        Appointment selectedAppointment = listView.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null)
            return;
        ReserveAppointmentRequest requestReserveAppointment = new ReserveAppointmentRequest(selectedAppointment);
        App.getClient().sendRequest(requestReserveAppointment);
    }
}
