package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class BaseController {
    public void signOut(ActionEvent actionEvent) throws IOException {
        App.setActiveUser(null);
        App.setRoot("RegisterLogin");
    }

    public void start() {};

    public void informUser(String message) {
        Alert alert  = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.show();
    }

    public void alertUserError(String message) {
        Alert alert  = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.show();
    }

    public void confirmUser(String message) {
        Alert alert  = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        alert.show();
    }

    public void warnUser(String message) {
        Alert alert  = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.show();
    }
}
