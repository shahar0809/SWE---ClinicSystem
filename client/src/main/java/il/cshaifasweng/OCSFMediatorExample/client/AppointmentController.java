package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.requests.*;
import il.cshaifasweng.OCSFMediatorExample.response.DeleteAppointmentResponse;
import il.cshaifasweng.OCSFMediatorExample.response.GetFreeAppointmentsResponse;
import il.cshaifasweng.OCSFMediatorExample.response.GetGreenPassResponse;
import il.cshaifasweng.OCSFMediatorExample.response.ReserveAppointmentResponse;
import il.cshaifasweng.OCSFMediatorExample.utils.Messages;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.net.URL;
import java.util.ResourceBundle;

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
    private Button changingButton;
    @FXML
    private Label changingLabel;
    @FXML
    private ComboBox<AppointmentType> comboBox;

    private AppointmentType selectedType;
    private TableMode tableMode;

    @FXML
    private ObservableList<Appointment> appointments;

    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);
        appointments = FXCollections.observableArrayList();

        table.setItems(appointments);
        //table.getColumns().addAll(typeColumn, memberColumn, dateColumn, clinicColumn);

        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        memberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMember().toString()));
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTreatmentDateTimeString()));
        clinicColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClinic().toString()));

        // Initialize combo box with appointment types
        comboBox.setItems(FXCollections.observableArrayList(AppointmentType.values()));
    }

    @FXML
    void onAppointment(ActionEvent event) {
        changingLabel.setText("Your Appointment");
        changingButton.setText("Cancel");

        tableMode = TableMode.MY_APPOINTMENTS;
        GetPatientAppointmentRequest requestFreeAppointment = new GetPatientAppointmentRequest(App.getActiveUser());
        App.getClient().sendRequest(requestFreeAppointment);

        selectedType = null;
    }

    @FXML
    void onCovidVaccine(ActionEvent event) {
        changingLabel.setText("Covid Vaccine Available Appointment");
        changingButton.setText("Reserve");
        tableMode = TableMode.RESERVE_APPOINTMENTS;
        selectedType = AppointmentType.COVID_VACCINE;

        GetFreeAppointmentRequest<CovidVaccineAppointment> requestFreeAppointment = new GetFreeAppointmentRequest<>(CovidVaccineAppointment.class);
        App.getClient().sendRequest(requestFreeAppointment);
    }

    @FXML
    void onFluVaccine(ActionEvent event) {
        changingLabel.setText("Flu Vaccine Available Appointment");
        changingButton.setText("Reserve");
        selectedType = AppointmentType.FLU_VACCINE;
        tableMode = TableMode.RESERVE_APPOINTMENTS;
        GetFreeAppointmentRequest<FluVaccineAppointment> requestFreeAppointment = new GetFreeAppointmentRequest<>(FluVaccineAppointment.class);
        App.getClient().sendRequest(requestFreeAppointment);
    }

    @FXML
    void onCovidTest(ActionEvent event) {
        changingLabel.setText("Covid Test Available Appointment");
        changingButton.setText("Reserve");
        selectedType = AppointmentType.COVID_TEST;
        tableMode = TableMode.RESERVE_APPOINTMENTS;
        GetFreeAppointmentRequest<CovidTestAppointment> requestFreeAppointment = new GetFreeAppointmentRequest<>(CovidTestAppointment.class);
        App.getClient().sendRequest(requestFreeAppointment);
    }

    @FXML
    void onGetGreenPass(ActionEvent event) {
        // TODO: Open new activity of questionnaire
        changingLabel.setText("Answer The Following Question");
        changingButton.setText("Finish");
        selectedType =  null;
        tableMode = TableMode.GREEN_PASS;
        GetGreenPassRequest requestGreenPass = new GetGreenPassRequest(App.getActiveUser());
        App.getClient().sendRequest(requestGreenPass);
    }

    @FXML
    void onButton(ActionEvent event) {
        Appointment selectedAppointment = table.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null)
            return;
        if (tableMode == TableMode.MY_APPOINTMENTS) {
            onCancel(event);
        }
        if (tableMode == TableMode.RESERVE_APPOINTMENTS) {
            onReserve(event);
        }
    }

//    @Subscribe
//    public void Answer(Response response) {
//        Alert alert;
//        if (response instanceof ReserveAppointmentResponse) {
//            if (changingButton.getText().equals("Cancel")) {
//                alert = new Alert(Alert.AlertType.INFORMATION, "Appointment Deleted!", ButtonType.OK);
//            } else {
//                alert = new Alert(Alert.AlertType.INFORMATION, "Appointment Reserve!", ButtonType.OK);
//            }
//            alert.setHeaderText("Success");
//            alert.show();
//        }
//        if (response instanceof GetGreenPassResponse) {
//            if (((GetGreenPassResponse) response).canGetGreenPass) {
//                alert = new Alert(Alert.AlertType.INFORMATION, "Green Pass can be downland!", ButtonType.OK);
//                alert.setHeaderText("You can downland your green pass");
//            } else {
//                alert = new Alert(Alert.AlertType.INFORMATION, "Green Pass can't be downland!", ButtonType.OK);
//                alert.setHeaderText("You didn't do covid vaccine - can't issue Green Pass");
//            }
//            alert.show();
//        }
//    }

    @FXML
    public void onReserve(ActionEvent actionEvent) {
        Appointment selectedAppointment = table.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null)
            return;
        ReserveAppointmentRequest request = new ReserveAppointmentRequest(selectedAppointment, App.getActiveUser());
        App.getClient().sendRequest(request);
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
            alertUser(response.getError());
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
        Alert alert  = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.show();
    }

    public void onRefresh(ActionEvent actionEvent) {
        if (tableMode == TableMode.RESERVE_APPOINTMENTS) {
            switch (selectedType) {
                case COVID_TEST:
                    App.getClient().sendRequest(new GetFreeAppointmentRequest<>(CovidTestAppointment.class));
                    break;
                case COVID_VACCINE:
                    App.getClient().sendRequest(new GetFreeAppointmentRequest<>(CovidVaccineAppointment.class));
                    break;
                case NURSE:
                    App.getClient().sendRequest(new GetFreeAppointmentRequest<>(NurseAppointment.class));
                    break;
                case FLU_VACCINE:
                    App.getClient().sendRequest(new GetFreeAppointmentRequest<>(FluVaccineAppointment.class));
                    break;
                case FAMILY:
                    App.getClient().sendRequest(new GetFreeAppointmentRequest<>(FamilyDoctorAppointment.class));
                    break;
                case CHILDREN:
                    App.getClient().sendRequest(new GetFreeAppointmentRequest<>(ChildrenDoctorAppointment.class));
                    break;
                case CARDIO:
                case ORTHOPEDICS:
                case GYNECOLOGY:
                case OTOLARYNGOLOGY:
                case GASTROLOGY:
                    App.getClient().sendRequest(new GetFreeAppointmentRequest<>(ProfessionDoctorAppointment.class));
                    break;
            }
        } else if (tableMode == TableMode.MY_APPOINTMENTS) {
            App.getClient().sendRequest(new GetPatientAppointmentRequest(App.getActiveUser()));
        }
    }

    @FXML
    public void onAppointmentChoice(ActionEvent actionEvent) {
        AppointmentType selected = comboBox.getValue();

        if (selected == null)
            return;

        switch (selected) {
            case COVID_TEST:
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(CovidTestAppointment.class, selected));
                break;
            case COVID_VACCINE:
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(CovidVaccineAppointment.class, selected));
                break;
            case NURSE:
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(NurseAppointment.class, selected));
                break;
            case FLU_VACCINE:
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(FluVaccineAppointment.class, selected));
                break;
            case FAMILY:
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(FamilyDoctorAppointment.class, selected));
                break;
            case CHILDREN:
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(ChildrenDoctorAppointment.class, selected));
                break;
            case CARDIO:
            case ORTHOPEDICS:
            case GYNECOLOGY:
            case OTOLARYNGOLOGY:
            case GASTROLOGY:
                App.getClient().sendRequest(new GetFreeAppointmentRequest<>(ProfessionDoctorAppointment.class, selected));
                break;
        }
    }

    public void goBack(ActionEvent actionEvent) {
    }

    public class UpdateTableService extends ScheduledService<Void> {
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() throws Exception {
                    switch (selectedType) {
                        case COVID_TEST:
                            App.getClient().sendRequest(new GetFreeAppointmentRequest<>(CovidTestAppointment.class));
                            break;
                        case COVID_VACCINE:
                            App.getClient().sendRequest(new GetFreeAppointmentRequest<>(CovidVaccineAppointment.class));
                            break;
                        case NURSE:
                            App.getClient().sendRequest(new GetFreeAppointmentRequest<>(NurseAppointment.class));
                            break;
                        case FLU_VACCINE:
                            App.getClient().sendRequest(new GetFreeAppointmentRequest<>(FluVaccineAppointment.class));
                            break;
                        case FAMILY:
                            App.getClient().sendRequest(new GetFreeAppointmentRequest<>(FamilyDoctorAppointment.class));
                            break;
                        case CHILDREN:
                            App.getClient().sendRequest(new GetFreeAppointmentRequest<>(ChildrenDoctorAppointment.class));
                            break;
                        case CARDIO:
                        case ORTHOPEDICS:
                        case GYNECOLOGY:
                        case OTOLARYNGOLOGY:
                        case GASTROLOGY:
                            App.getClient().sendRequest(new GetFreeAppointmentRequest<>(ProfessionDoctorAppointment.class));
                            break;
                    }
                    return null;
                }
            };
        }
    }
}




