package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.cellFactory.AppointmentCellFactory;
import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import il.cshaifasweng.OCSFMediatorExample.requests.*;
import il.cshaifasweng.OCSFMediatorExample.response.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AppointmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button changingButton;

    @FXML
    private Label changingLabel;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private ListView<Appointment> listView;
    private ObservableList<Appointment> appointmentsList;

    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);
        appointmentsList = FXCollections.observableArrayList();
        listView = new ListView<>(appointmentsList);
        listView.setCellFactory(new AppointmentCellFactory());

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    void onAppointment(ActionEvent event) {
        changingLabel.setText("Your Appointment");
        changingButton.setText("Cancel");

        ReservePatientAppointmentRequest requestFreeAppointment = new ReservePatientAppointmentRequest(App.getActiveUser().getUsername());
        App.getClient().sendRequest(requestFreeAppointment);
    }

    @FXML
    void onCovidVaccine(ActionEvent event) {
        changingLabel.setText("Covid Vaccine Available Appointment");
        changingButton.setText("Reserve");

        // Background thread to continuously update appointments
        new Thread(() -> {
            while (true) {
                ReserveFreeAppointmentsRequest requestFreeAppointment = new ReserveFreeAppointmentsRequest("CovidVaccine");
                App.getClient().sendRequest(requestFreeAppointment);
            }
        });
    }

    @FXML
    void onFluVaccine(ActionEvent event) {
        changingLabel.setText("Flu Vaccine Available Appointment");
        changingButton.setText("Reserve");

        new Thread(() -> {
            while (true) {
                ReserveFreeAppointmentsRequest requestFreeAppointment = new ReserveFreeAppointmentsRequest("FluVaccine");
                App.getClient().sendRequest(requestFreeAppointment);
            }
        });
    }

    @FXML
    void onCovidTest(ActionEvent event) {
        changingLabel.setText("Covid Test Available Appointment");
        changingButton.setText("Reserve");

        new Thread(() -> {
            while (true) {
                ReserveFreeAppointmentsRequest requestFreeAppointment = new ReserveFreeAppointmentsRequest("CovidTest");
                App.getClient().sendRequest(requestFreeAppointment);
            }
        });
    }

//    @FXML
//    void onGetGreenPass(ActionEvent event) {
//        changingLabel.setText("Answer The Following Question");
//        changingButton.setText("Finish");
//        GetGreenPassRequest requestGreenPass = new GetGreenPassRequest();
//        App.getClient().sendRequest(requestGreenPass);
//    }

    @FXML
    void onButton(ActionEvent event) {
        Appointment selectedAppointment = listView.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null)
            return;
        if (changingButton.getText().equals("Cancel")) {
            DeleteAppointmentRequest requestDeleteAppointment = new DeleteAppointmentRequest(selectedAppointment, App.getActiveUser().getUsername());
            App.getClient().sendRequest(requestDeleteAppointment);
        }
        if (changingButton.getText().equals("Covid Vaccine Available Appointment") || changingButton.getText().equals("Flu Vaccine Available Appointment") || changingButton.getText().equals("Covid Test Available Appointment")) {
            AddAppointmentRequest requestAddAppointment = new AddAppointmentRequest(selectedAppointment, App.getActiveUser().getUsername());
            App.getClient().sendRequest(requestAddAppointment);
        }
    }


    @Subscribe
    public void Answer(Response response) {
        Alert alert;
        if (response instanceof ReserveFreeAppointmentsResponse) {
            if (changingButton.getText().equals("Cancel")) {
                alert = new Alert(Alert.AlertType.INFORMATION, "Appointment Deleted!", ButtonType.OK);
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION, "Appointment Reserve!", ButtonType.OK);
            }
            alert.setHeaderText("Success");
            alert.show();
        }
        if (response instanceof GetGreenPassResponse) {
            if (((GetGreenPassResponse) response).canGetGreenPass) {
                alert = new Alert(Alert.AlertType.INFORMATION, "Green Pass can be downland!", ButtonType.OK);
                alert.setHeaderText("You can downland your green pass");
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION, "Green Pass can't be downland!", ButtonType.OK);
                alert.setHeaderText("You didn't do covid vaccine - can't issue Green Pass");
            }
            alert.show();
        }
    }

    @Subscribe
    public void updateAppointmentsList(ReserveFreeAppointmentsResponse response) {
        appointmentsList.addAll(response.appointments);
    }
}




