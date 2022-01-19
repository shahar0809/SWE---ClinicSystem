package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import il.cshaifasweng.OCSFMediatorExample.requests.Request;
import javafx.application.Platform;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class SimpleClient extends AbstractClient {

    private static SimpleClient client = null;

    private SimpleClient(String host, int port) {
        super(host, port);
    }

    public static SimpleClient getClient() {
        if (client == null) {
            client = new SimpleClient("localhost", 3000);
        }
        return client;
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        Platform.runLater(() -> EventBus.getDefault().post(msg));
    }

    public void sendRequest(Request msg) {
        User activeUser = App.getActiveUser();
        if (activeUser != null)
            msg.setToken(activeUser.getToken());
        try {
            client.sendToServer(msg);
        } catch (IOException e) {
            System.err.println("Error");
            e.printStackTrace();
        }
    }
}
