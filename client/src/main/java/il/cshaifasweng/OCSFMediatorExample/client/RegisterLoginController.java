package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.AppointmentType;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.ClinicManager;

import il.cshaifasweng.OCSFMediatorExample.entities.Patient;
import il.cshaifasweng.OCSFMediatorExample.requests.GetAllClinicsRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.GetClinicRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.LoginRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.RegisterRequest;
import il.cshaifasweng.OCSFMediatorExample.response.GetAllClinicsResponse;
import il.cshaifasweng.OCSFMediatorExample.response.LoginResponse;
import il.cshaifasweng.OCSFMediatorExample.response.RegisterResponse;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class RegisterLoginController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField ageField;

    @FXML
    private ComboBox<String> comboBox;
    private ArrayList<String> clinicList;

    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);
        clinicList = new ArrayList<>();
        App.getClient().sendRequest(new GetAllClinicsRequest());
    }

    @FXML
    void login(ActionEvent event) {
        String username = usernameField.getText();
        if (username.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Username is required!", ButtonType.OK);
            alert.setHeaderText("Error");
            alert.show();
            return;
        }
        String password = passwordField.getText();
        if (password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Password is required!", ButtonType.OK);
            alert.setHeaderText("Error");
            alert.show();
            return;
        }
        App.getClient().sendRequest(new LoginRequest(username, password));
    }

    @FXML
    void register(ActionEvent event) {
        String username = usernameField.getText();
        if (username.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Username is required!", ButtonType.OK);
            alert.setHeaderText("Error");
            alert.show();
            return;
        }
        String password = passwordField.getText();
        if (password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Password is required!", ButtonType.OK);
            alert.setHeaderText("Error");
            alert.show();
            return;
        }
        String rawAge = ageField.getText();
        if (rawAge.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Age is required!", ButtonType.OK);
            alert.setHeaderText("Error");
            alert.show();
            return;
        }
        int age;
        try {
            age = Integer.parseInt(rawAge);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Age must be a number!", ButtonType.OK);
            alert.setHeaderText("Error");
            alert.show();
            return;
        }
        if (age <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Age must be positive!", ButtonType.OK);
            alert.setHeaderText("Error");
            alert.show();
            return;
        }
        String selectedClinic = comboBox.getValue();
        if (selectedClinic == null)
            return;

        App.getClient().sendRequest(new RegisterRequest(username, password, age, selectedClinic));
    }

    @Subscribe
    public void handleLoginResponse(LoginResponse response) throws IOException {
        if (response.getError() != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, response.getError(), ButtonType.OK);
            alert.setHeaderText("Error");
            alert.show();
            return;
        }
        App.setActiveUser(response.user);
        if (App.getActiveUser() instanceof ClinicManager) {
            App.setRoot("PrimaryManager");
        } else {
            App.setRoot("ReserveAppointment");
        }
    }

    @Subscribe
    public void handleRegisterResponse(RegisterResponse response) throws IOException {
        if (response.error != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, response.error, ButtonType.OK);
            alert.setHeaderText("Error");
            alert.show();
            return;
        }
        App.setActiveUser(response.user);
        App.setRoot("ReserveAppointment");
    }

    public void stop() throws Exception {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void updateListOfClinic(GetAllClinicsResponse response) {
        for (Clinic clinic : response.clinics) {
            clinicList.add(clinic.getName());
        }
        comboBox.setItems(FXCollections.observableArrayList(clinicList));
    }
}
