package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;

import java.io.IOException;

public class BaseController {
    public void signOut(ActionEvent actionEvent) throws IOException {
        App.setActiveUser(null);
        App.setRoot("RegisterLogin");
    }
}
