package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.requests.ReservePatientAppointmentRequest;
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

public class PatientAppointmentController {
        @FXML
        private ListView<Appointment> appointmentList;

        final ObservableList<Appointment> covidVaccine = FXCollections.observableArrayList();

        @FXML
        public void initialize() {
            EventBus.getDefault().register(this);
            appointmentList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            ReservePatientAppointmentRequest requestPatientRequest = new ReservePatientAppointmentRequest();
            App.getClient().sendRequest(requestPatientRequest);
        }

        @FXML
        void onCancel(ActionEvent event) {
            Appointment selectedCovidVaccine = appointmentList.getSelectionModel().getSelectedItem();
            if (selectedCovidVaccine == null)
                return;
            ReserveAppointmentRequest requestReserveAppointment = new ReserveAppointmentRequest(selectedCovidVaccine);
            App.getClient().sendRequest(requestReserveAppointment);
        }

        @Subscribe
        public void DeleteAppointment(ReserveAppointmentResponse response) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment Deleted!", ButtonType.OK);
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

}
