package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.response.GetAllClinicsResponse;
import il.cshaifasweng.OCSFMediatorExample.response.GetClinicResponse;
import il.cshaifasweng.OCSFMediatorExample.response.UpdateActiveHoursResponse;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

import java.io.IOException;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		if (msg instanceof GetAllClinicsResponse) {
			App.getController().updateListOfClinic(((GetAllClinicsResponse) msg).clinics);
		}
		if (msg instanceof GetClinicResponse) {
			App.getController().updateWorkingHours(((GetClinicResponse) msg).clinic);
		}
		if (msg instanceof UpdateActiveHoursResponse) {
			App.getController().updateHours();
		}
	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

	public void sendRequest(Object msg) {
		try {
			client.sendToServer(msg);
		} catch (IOException e) {
			System.out.println("Error");
		}
	}
}
