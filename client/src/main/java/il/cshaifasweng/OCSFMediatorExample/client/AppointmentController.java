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
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentController {
    @FXML
    TableView<Appointment> table = new TableView<>();

    TableColumn<Appointment, String> typeColumn //
            = new TableColumn<>("Type");

    TableColumn<Appointment, String> memberColumn//
            = new TableColumn<>("Member");

    TableColumn<Appointment, String> dateColumn//
            = new TableColumn<>("Date & Time");

    TableColumn<Appointment, String> clinicColumn = new TableColumn<>("Clinic");

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

    private AppointmentType selectedType;

    @FXML
    private ListView<Appointment> listView;
    private ObservableList<Appointment> appointments;

    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);
        appointments = FXCollections.observableArrayList();

        table.setItems(appointments);
        table.getColumns().addAll(typeColumn, memberColumn, dateColumn, clinicColumn);

        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        memberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMember().toString()));
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTreatmentDateTimeString()));
        clinicColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClinic().toString()));

//        UpdateTableService service = new UpdateTableService();
//        service.setPeriod(Duration.seconds(3));
//        service.start();
    }

    @FXML
    void onAppointment(ActionEvent event) {
        changingLabel.setText("Your Appointment");
        changingButton.setText("Cancel");

        GetPatientAppointmentRequest requestFreeAppointment = new GetPatientAppointmentRequest(App.getActiveUser());
        App.getClient().sendRequest(requestFreeAppointment);

        selectedType = null;
    }

    @FXML
    void onCovidVaccine(ActionEvent event) {
        changingLabel.setText("Covid Vaccine Available Appointment");
        changingButton.setText("Reserve");

        selectedType = AppointmentType.COVID_VACCINE;

        GetFreeAppointmentRequest<CovidVaccineAppointment> requestFreeAppointment = new GetFreeAppointmentRequest<>(CovidVaccineAppointment.class);
        App.getClient().sendRequest(requestFreeAppointment);
    }

    @FXML
    void onFluVaccine(ActionEvent event) {
        changingLabel.setText("Flu Vaccine Available Appointment");
        changingButton.setText("Reserve");
        selectedType = AppointmentType.FLU_VACCINE;

        GetFreeAppointmentRequest<FluVaccineAppointment> requestFreeAppointment = new GetFreeAppointmentRequest<>(FluVaccineAppointment.class);
        App.getClient().sendRequest(requestFreeAppointment);
    }

    @FXML
    void onCovidTest(ActionEvent event) {
        changingLabel.setText("Covid Test Available Appointment");
        changingButton.setText("Reserve");
        selectedType = AppointmentType.COVID_TEST;

        GetFreeAppointmentRequest<CovidTestAppointment> requestFreeAppointment = new GetFreeAppointmentRequest<>(CovidTestAppointment.class);
        App.getClient().sendRequest(requestFreeAppointment);
    }

    @FXML
    void onGetGreenPass(ActionEvent event) {
        // TODO: Open new activity of questionnaire
        changingLabel.setText("Answer The Following Question");
        changingButton.setText("Finish");
        selectedType =  null;

        GetGreenPassRequest requestGreenPass = new GetGreenPassRequest(App.getActiveUser());
        App.getClient().sendRequest(requestGreenPass);
    }

    @FXML
    void onButton(ActionEvent event) {
        Appointment selectedAppointment = listView.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null)
            return;
        if (changingButton.getText().equals("Cancel")) {
            onCancel(event);
        }
        if (changingButton.getText().equals("Reserve")) {
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
        Appointment selectedAppointment = listView.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null)
            return;
        ReserveAppointmentRequest request = new ReserveAppointmentRequest(selectedAppointment, App.getActiveUser());
        App.getClient().sendRequest(request);
    }

    @FXML
    public void onCancel(ActionEvent actionEvent) {
        Appointment selectedAppointment = listView.getSelectionModel().getSelectedItem();
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
    }

    @Subscribe
    public void cancelResponse(DeleteAppointmentResponse response) {
        if (response.isSuccessful()) {
            alertUser(Messages.CANCEL_APPOINTMENT_SUCCESS);
        } else {
            alertUser(response.getError());
        }
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




