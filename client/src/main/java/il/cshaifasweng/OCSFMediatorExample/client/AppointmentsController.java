package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.cellFactory.AppointmentCellFactory;
import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.CovidVaccineAppointment;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
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
        // ReserveFreeAppointmentRequest requestFreeAppointment = new ReserveFreeAppointmentRequest();
        // App.getClient().sendRequest(requestFreeAppointment);
    }

    @FXML
    void onReserve(ActionEvent event) {
        Appointment selectedAppointment = listView.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null)
            return;
        //ReserveAppointmentRequest requestReserveAppointment = new ReserveAppointmentRequest(selectedCovidVaccine);
        //App.getClient().sendRequest(requestReserveAppointment);
    }

    @Subscribe
    public void ReserveAppointment(ReserveAppointmentResponse response) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Covid Vaccine Appointment Saved!", ButtonType.OK);
        alert.setHeaderText("Success");
        alert.show();
    }



//    @Subscribe
//    public void updateListOfCovidVaccine(ReserveAppointmentResponse response) {
//        for (CovidVaccine vaccine : response.covidVaccines) {
//            covidVaccineList.getItems().add(vaccines.toString());
//        }
//    }
}
