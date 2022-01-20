package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.requests.GetAllDoctorsRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.GetClinicRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.UpdateDoctorHoursRequest;
import il.cshaifasweng.OCSFMediatorExample.response.GetAllDoctorsResponse;
import il.cshaifasweng.OCSFMediatorExample.utils.Hours;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.LocalDate;

public class SetDoctorHoursController {
    private List<ClinicMember> doctorList;
    private ClinicMember doctorToSelect;
    private LocalDate day;
    @FXML
    private TextField DoctorDate;

    @FXML
    private TextField DoctorEndingHour1;

    @FXML
    private TextField DoctorStartingHour1;

    @FXML
    private Button FreeHours;

    @FXML
    private Button ReserveHours;

    @FXML
    private Button SelectDate;

    @FXML
    private TableColumn<Appointment, String> hourColumn;

    @FXML
    private TableColumn<Appointment, String> reservedColumn;

    @FXML
    private ListView<String> nameList;

    @FXML
    private TableView<Appointment> table;

    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);
        nameList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        GetAllDoctorsRequest requestAllDoctors = new GetAllDoctorsRequest();
        App.getClient().sendRequest(requestAllDoctors);
        nameList.getSelectionModel().selectedItemProperty().addListener((observableValue, newValue, oldValue) -> {
            DoctorDate.clear();
            DoctorStartingHour1.clear();
            DoctorEndingHour1.clear();
        });
        day = null;
        doctorToSelect = null;
    }

    @Subscribe
    public void updateListOfDoctors(GetAllDoctorsResponse response) {
        doctorList = response.doctors;
        for (ClinicMember doctor : doctorList) {
            nameList.getItems().add(doctor.getId() + ", " + doctor.getFirstName() + " " + doctor.getLastName());
        }
    }

    @FXML
    public void onMouseClick(MouseEvent mouseEvent) {
        String selectedName = nameList.getSelectionModel().getSelectedItem();
        if (selectedName == null) {
            return;
        }
        int selectedNum = Integer.parseInt(selectedName.substring(0, selectedName.indexOf(',')));
        for (ClinicMember doctor : doctorList) {
            if (selectedNum == doctor.getId()) {
                doctorToSelect = doctor;
                break;
            }
        }
    }

    @FXML
    void onDateSelect(ActionEvent event) {
        try {
            day = LocalDate.parse(DoctorDate.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "invalid date", ButtonType.OK);
            alert.setHeaderText("Error");
            alert.show();
            day = null;
            return;
        }
        List<Appointment> apps =  doctorToSelect.getAppointmentsOnDate(day);
        hourColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTreatmentDateTimeString()));
        reservedColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isAvailable()?"no":"yes"));
    }

    @FXML
    void onReserveHours(ActionEvent event) {
        if (day == null || doctorToSelect==null)
            return;
        LocalTime t1, t2;
        try {
            t1 = LocalTime.parse(DoctorStartingHour1.getText());
            t2 = LocalTime.parse(DoctorEndingHour1.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "invalid hour", ButtonType.OK);
            alert.setHeaderText("Error");
            alert.show();
            return;
        }
        UpdateDoctorHoursRequest requestToSend = new UpdateDoctorHoursRequest(null, doctorToSelect, day, t1, t2);
        App.getClient().sendRequest(requestToSend);
    }

    public void stop() throws Exception {
        EventBus.getDefault().unregister(this);
    }
}
