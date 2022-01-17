package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.requests.GetGreenPassRequest;
import il.cshaifasweng.OCSFMediatorExample.response.GetGreenPassResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GreenPassController extends BaseController {
    @FXML
    private ImageView barcode;
    @FXML
    private Label date;
    @FXML
    private AnchorPane greenPassBoard;

    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);
        greenPassBoard.setVisible(false);
    }

    public void getGreenPass(ActionEvent actionEvent) {
        GetGreenPassRequest request = new GetGreenPassRequest(App.getActiveUser());
        App.getClient().sendRequest(request);
    }

    @Subscribe
    public void greenPassResponse(GetGreenPassResponse response) {
        if (!response.isSuccessful()) {
            alertUserError(response.getError());
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            greenPassBoard.setVisible(true);
            date.setText(LocalDate.now().format(format));
        }
    }
}
