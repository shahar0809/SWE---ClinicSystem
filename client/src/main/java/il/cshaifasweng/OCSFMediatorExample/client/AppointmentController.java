package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.requests.GetGreenPassRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.ReserveFreeAppointmentsRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.ReservePatientAppointmentRequest;
import il.cshaifasweng.OCSFMediatorExample.response.GetGreenPassResponse;
import il.cshaifasweng.OCSFMediatorExample.response.ReserveFreeAppointmentsResponse;
import il.cshaifasweng.OCSFMediatorExample.response.ReservePatientAppointmentResponse;
import il.cshaifasweng.OCSFMediatorExample.response.Response;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AppointmentController {

    @FXML
    private Button changingButton;

    @FXML
    private Label changingLabel;

    @FXML
    private ListView<Appointment> appointmentsList;

    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);
        appointmentsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    void onAppointment(ActionEvent event) {
        changingLabel.setText("Your Appointment");
        changingButton.setText("Cancel");
        ReservePatientAppointmentRequest requestFreeAppointment = new ReservePatientAppointmentRequest();
        App.getClient().sendRequest(requestFreeAppointment);
    }

    @FXML
    void onCovidVaccine(ActionEvent event) {
        changingLabel.setText("Covid Vaccine Available Appointment");
        changingButton.setText("Reserve");
        ReserveFreeAppointmentsRequest requestFreeAppointment = new ReserveFreeAppointmentsRequest("CovidVaccine");
        App.getClient().sendRequest(requestFreeAppointment);
    }

    @FXML
    void onFluVaccine(ActionEvent event) {
        changingLabel.setText("Flu Vaccine Available Appointment");
        changingButton.setText("Reserve");
        ReserveFreeAppointmentsRequest requestFreeAppointment = new ReserveFreeAppointmentsRequest("FluVaccine");
        App.getClient().sendRequest(requestFreeAppointment);
    }

    @FXML
    void onCovidTest(ActionEvent event) {
        changingLabel.setText("Covid Test Available Appointment");
        changingButton.setText("Reserve");
        ReserveFreeAppointmentsRequest requestFreeAppointment = new ReserveFreeAppointmentsRequest("CovidTest");
        App.getClient().sendRequest(requestFreeAppointment);
    }

    @FXML
    void onButton(ActionEvent event) {
        Appointment selectedCovidVaccine = appointmentsList.getSelectionModel().getSelectedItem();
        if (selectedCovidVaccine == null)
            return;
        ReserveAppointmentRequest requestReserveAppointment = new ReserveAppointmentRequest(selectedCovidVaccine);
        App.getClient().sendRequest(requestReserveAppointment);
    }

    @FXML
    void onGetGreenPass(ActionEvent event) {
        changingLabel.setText("Answer The Following Question");
        changingButton.setText("Finish");
        GetGreenPassRequest requestGreenPass = new GetGreenPassRequest();
        App.getClient().sendRequest(requestGreenPass);
    }

    @Subscribe
    public void Answer(Response response) {
        Alert alert;
        if (response instanceof ReserveAppointmentResponse) {
            if (changingButton.getText().equals("Cancel")) {
                alert = new Alert(Alert.AlertType.INFORMATION, "Appointment Deleted!", ButtonType.OK);
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION, "Appointment Reserve!", ButtonType.OK);
            }
            alert.setHeaderText("Success");
            alert.show();
        }
        if (response instanceof GetGreenPassResponse) {
            if (response.canGetGreenPass) {
                alert = new Alert(Alert.AlertType.INFORMATION, "Green Pass can be downland!", ButtonType.OK);
                alert.setHeaderText("You can downland your green pass");
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION, "Green Pass can't be downland!", ButtonType.OK);
                alert.setHeaderText("You didn't do covid vaccine - can't issue Green Pass");
            }
            alert.show();
        }

    }
}




