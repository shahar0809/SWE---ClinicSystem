package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.requests.GetGreenPassRequest;
import il.cshaifasweng.OCSFMediatorExample.response.GetGreenPassResponse;
import il.cshaifasweng.OCSFMediatorExample.utils.Messages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
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
    private Label name;
    @FXML
    private VBox greenPassBoard;

    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void start() {
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
        } else if (response.isEligible()) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date.setText(LocalDate.now().format(format));
            name.setText(App.getActiveUser().getUsername());

            greenPassBoard.setVisible(true);
        } else {
            informUser(Messages.GREEN_PASS_NOT_VACCINATED);
        }
    }
}
