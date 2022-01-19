package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ClinicManager;

import il.cshaifasweng.OCSFMediatorExample.entities.ClinicMember;
import il.cshaifasweng.OCSFMediatorExample.entities.HospitalManager;
import il.cshaifasweng.OCSFMediatorExample.requests.LoginRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.RegisterRequest;
import il.cshaifasweng.OCSFMediatorExample.response.LoginResponse;
import il.cshaifasweng.OCSFMediatorExample.response.RegisterResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

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
    public void initialize() {
        EventBus.getDefault().register(this);
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
        App.getClient().sendRequest(new RegisterRequest(username, password, age));
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
        } else if (App.getActiveUser() instanceof ClinicMember) {
            App.setRoot("MemberScreen");
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

}
