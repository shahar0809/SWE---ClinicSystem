package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.User;
import il.cshaifasweng.OCSFMediatorExample.requests.GetGreenPassRequest;
import il.cshaifasweng.OCSFMediatorExample.response.GetGreenPassResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.greenrobot.eventbus.Subscribe;

public class GreenPassController {
    @FXML
    void onGetGreenPass(ActionEvent event) {
        GetGreenPassRequest requestGreenPass = new GetGreenPassRequest();
        App.getClient().sendRequest(requestGreenPass);
    }

    @Subscribe
    public void getGreenPass(GetGreenPassResponse response) {
        Alert alert;
        if (response.canGetGreenPass) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Green Pass can be downland!", ButtonType.OK);
            alert.setHeaderText("You can downland your green pass");
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION, "Green Pass can't be downland!", ButtonType.OK);
            alert.setHeaderText("You didn't do covid vaccine - can't issue Green Pass");
        }
        alert.show();
    }
}
