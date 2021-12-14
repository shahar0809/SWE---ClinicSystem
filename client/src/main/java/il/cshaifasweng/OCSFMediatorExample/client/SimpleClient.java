package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.response.GetAllClinicsResponse;
import il.cshaifasweng.OCSFMediatorExample.response.GetClinicResponse;
import il.cshaifasweng.OCSFMediatorExample.response.UpdateActiveHoursResponse;
import javafx.application.Platform;

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
        if (msg instanceof GetAllClinicsResponse) {
            Platform.runLater(() -> App.getController().updateListOfClinic(((GetAllClinicsResponse) msg).clinics));
        }
        if (msg instanceof GetClinicResponse) {
            Platform.runLater(() -> App.getController().updateWorkingHours(((GetClinicResponse) msg).clinic));
        }
        if (msg instanceof UpdateActiveHoursResponse) {
            Platform.runLater(() -> App.getController().updateHours());
        }
    }

    public void sendRequest(Object msg) {
        try {
            client.sendToServer(msg);
        } catch (IOException e) {
            System.err.println("Error");
            e.printStackTrace();
        }
    }
}
