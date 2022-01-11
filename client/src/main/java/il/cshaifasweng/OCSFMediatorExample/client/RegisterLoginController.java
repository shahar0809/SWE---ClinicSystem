package il.cshaifasweng.OCSFMediatorExample.client;

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
        App.getClient().sendRequest(new RegisterRequest(username, password));
    }

    @Subscribe
    public void handleLoginResponse(LoginResponse response) throws IOException {
        if (response.error != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, response.error, ButtonType.OK);
            alert.setHeaderText("Error");
            alert.show();
            return;
        }
        App.setActiveUser(response.user);
        App.setRoot("PrimaryManager");
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
        App.setRoot("PrimaryManager");
    }

}
