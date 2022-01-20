package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.requests.*;
import il.cshaifasweng.OCSFMediatorExample.response.*;
import il.cshaifasweng.OCSFMediatorExample.utils.Constants;
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
import java.util.List;

public class AppointmentController extends BaseController {
    static AppointmentType comboType = null;
    static ClinicMember doctor = null;

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
    private Button nextButton;

    public AppointmentController() {
    }

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
        //types.remove(AppointmentType.NURSE);
        types.remove(AppointmentType.FAMILY);
        types.remove(AppointmentType.CHILDREN);
        comboBox.setItems(FXCollections.observableArrayList(types));
        comboBox.setValue(comboType);
        if(!(comboType == AppointmentType.CARDIO || comboType ==  AppointmentType.ORTHOPEDICS || comboType == AppointmentType.GYNECOLOGY || comboType == AppointmentType.OTOLARYNGOLOGY || comboType == AppointmentType.GASTROLOGY))
            nextButton.setVisible(false);
        if(comboType != AppointmentType.COVID_TEST) {
            questionnaireButton.setVisible(false);
        }
    }

    @Override
    public void start() {
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
            alertUserError(response.getError());
        }
    }

    @Subscribe
    public void doctorFreeAppointmentsResponse(GetProfessionDoctorAppointmentsResponse response) {
        if (response.isSuccessful()) {
            appointments.clear();
            appointments.addAll(response.getAppointments());
            table.setItems(appointments);
            table.refresh();
            doctor = null;
        } else {
            alertUserError(response.getError());
        }
    }

    @Subscribe
    public void reserveResponse(ReserveAppointmentResponse response) {
        if (response.isSuccessful()) {
            informUser(Messages.RESERVE_APPOINTMENT_SUCCESS);
        } else {
            if (Messages.COVID_TEST_NO_QUESTIONNAIRE.equals(response.getError())) {
                informUser("You have to fill the questionnaire!");
            } else {
                alertUserError(response.getError());
            }
        }
        onRefresh(null);
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
        nextButton.setVisible(false);
        switch (selected) {
            case COVID_TEST:
                questionnaireButton.setVisible(true);
                comboType = AppointmentType.COVID_TEST;
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(CovidTestAppointment.class, selected, patient));
                break;
            case COVID_VACCINE:
                comboType = AppointmentType.COVID_VACCINE;
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(CovidVaccineAppointment.class, selected, patient));
                break;
            case NURSE:
                comboType = AppointmentType.NURSE;
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(NurseAppointment.class, selected, patient));
                break;
            case FLU_VACCINE:
                comboType = AppointmentType.FLU_VACCINE;
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(FluVaccineAppointment.class, selected, patient));
                break;
            case FAMILY_OR_CHILDREN:
                comboType = AppointmentType.FAMILY_OR_CHILDREN;
                if (patient.getAge() >= Constants.AGE) {
                    App.getClient().sendRequest(new GetFreeAppointmentRequest<>(FamilyDoctorAppointment.class, AppointmentType.FAMILY, patient));
                } else {
                    App.getClient().sendRequest(new GetFreeAppointmentRequest<>(ChildrenDoctorAppointment.class, AppointmentType.CHILDREN, patient));
                }
                break;
            case CARDIO:
                comboType = AppointmentType.CARDIO;
                professionDoctorCase();
                break;
            case ORTHOPEDICS:
                comboType = AppointmentType.ORTHOPEDICS;
                professionDoctorCase();
                break;
            case GYNECOLOGY:
                comboType = AppointmentType.GYNECOLOGY;
                professionDoctorCase();
                break;
            case OTOLARYNGOLOGY:
                comboType = AppointmentType.OTOLARYNGOLOGY;
                professionDoctorCase();
                break;
            case GASTROLOGY:
                comboType = AppointmentType.GASTROLOGY;
                professionDoctorCase();
                break;
        }
    }

    public void professionDoctorCase() {
        nextButton.setVisible(true);
        if (doctor == null) {
            appointments.clear();
        } else {
            App.getClient().sendRequest(new GetProfessionDoctorAppointmentsRequest(doctor));
        }
    }

    public void onQuestionnaire(ActionEvent actionEvent) throws IOException {
        App.setRoot("CovidQuestionnaire");
    }

    public void onChooseDoctor(ActionEvent actionEvent) throws IOException {
        App.setRoot("ChooseDoctor");
    }
}




