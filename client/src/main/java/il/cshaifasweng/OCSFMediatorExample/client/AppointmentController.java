package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.requests.*;
import il.cshaifasweng.OCSFMediatorExample.response.DeleteAppointmentResponse;
import il.cshaifasweng.OCSFMediatorExample.response.GetFreeAppointmentsResponse;
import il.cshaifasweng.OCSFMediatorExample.response.GetGreenPassResponse;
import il.cshaifasweng.OCSFMediatorExample.response.ReserveAppointmentResponse;
import il.cshaifasweng.OCSFMediatorExample.utils.Constants;
import il.cshaifasweng.OCSFMediatorExample.utils.Messages;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;

import java.io.IOException;

public class AppointmentController {
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
    private ComboBox<AppointmentType> comboBox;

    @FXML
    private ObservableList<Appointment> appointments;

    @FXML
    private Button questionnaireButton;

    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);
        appointments = FXCollections.observableArrayList();

        table.setItems(appointments);

        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        memberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMember().toString()));
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTreatmentDateTimeString()));
        clinicColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClinic().toString()));

        // Initialize combo box with appointment types
        ArrayList<AppointmentType> types = new ArrayList<>(Arrays.asList(AppointmentType.values()));
        types.remove(AppointmentType.NURSE);
        types.remove(AppointmentType.FAMILY);
        types.remove(AppointmentType.CHILDREN);
        comboBox.setItems(FXCollections.observableArrayList(types));
        comboBox.setValue(AppointmentType.COVID_TEST);
        onAppointmentChoice(null);
    }

    @FXML
    public void onReserve(ActionEvent actionEvent) {
        Appointment selectedAppointment = table.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null)
            return;
        ReserveAppointmentRequest request = new ReserveAppointmentRequest(selectedAppointment, App.getActiveUser());
        App.getClient().sendRequest(request);
    }

    @Subscribe
    public <T extends Appointment> void freeAppointmentsResponse(GetFreeAppointmentsResponse<T> response) {
        if (response.isSuccessful()) {
            appointments.clear();
            appointments.addAll(response.getAppointments());
            table.setItems(appointments);
            table.refresh();
        } else {
            alertUser(response.getError());
        }
    }

    @Subscribe
    public void reserveResponse(ReserveAppointmentResponse response) {
        if (response.isSuccessful()) {
            alertUser(Messages.RESERVE_APPOINTMENT_SUCCESS);
        } else {
            if (response.getError().equals(Messages.COVID_TEST_NO_QUESTIONNAIRE)) {
                alertUser("You have to fill the questionnaire!");
            } else {
                alertUser(response.getError());
            }
        }
        onRefresh(null);
    }

    @Subscribe
    public void cancelResponse(DeleteAppointmentResponse response) {
        if (response.isSuccessful()) {
            alertUser(Messages.CANCEL_APPOINTMENT_SUCCESS);
        } else {
            alertUser(response.getError());
        }
        onRefresh(null);
    }

    @Subscribe
    public void greenPassResponse(GetGreenPassResponse response) {
        if (response.isSuccessful()) {
            alertUser(Messages.GREEN_PASS_SUCCESS);
        } else {
            alertUser(response.getError());
        }
    }

    public void alertUser(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.show();
    }

    @FXML
    public void onRefresh(ActionEvent actionEvent) {
        onAppointmentChoice(actionEvent);
    }

    @FXML
    public void onAppointmentChoice(ActionEvent actionEvent) {
        AppointmentType selected = comboBox.getValue();
        Patient patient = ((Patient) App.getActiveUser());

        if (selected == null)
            return;

        questionnaireButton.setVisible(false);
        switch (selected) {
            case COVID_TEST:
                questionnaireButton.setVisible(true);
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(CovidTestAppointment.class, selected, patient));
                break;
            case COVID_VACCINE:
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(CovidVaccineAppointment.class, selected, patient));
                break;
            case NURSE:
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(NurseAppointment.class, selected, patient));
                break;
            case FLU_VACCINE:
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(FluVaccineAppointment.class, selected, patient));
                break;
            case FAMILY_OR_CHILDREN:
                if (patient.getAge() >= Constants.AGE) {
                    App.getClient().sendRequest(new GetFreeAppointmentRequest<>(FamilyDoctorAppointment.class, AppointmentType.FAMILY, patient));
                } else {
                    App.getClient().sendRequest(new GetFreeAppointmentRequest<>(ChildrenDoctorAppointment.class, AppointmentType.CHILDREN, patient));
                }
                break;
            case CARDIO:
            case ORTHOPEDICS:
            case GYNECOLOGY:
            case OTOLARYNGOLOGY:
            case GASTROLOGY:
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(ProfessionDoctorAppointment.class, selected, patient));
                break;
        }
    }

    public void onQuestionnaire(ActionEvent actionEvent) throws IOException {
        App.setRoot("CovidQuestionnaire");
    }
}




