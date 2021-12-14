package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.DatabaseAccess;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.response.GetAllClinicsResponse;
import il.cshaifasweng.OCSFMediatorExample.response.GetClinicResponse;
import il.cshaifasweng.OCSFMediatorExample.response.Response;
import il.cshaifasweng.OCSFMediatorExample.response.UpdateActiveHoursResponse;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import il.cshaifasweng.OCSFMediatorExample.requests.GetAllClinicsRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.GetClinicRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.UpdateActiveHoursRequest;

public class SimpleServer extends AbstractServer {
	protected DatabaseAccess dataBase;

	public SimpleServer(int port) {
		super(port);
		dataBase = new DatabaseAccess();
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		if(msg instanceof GetAllClinicsRequest) {
			try {
				client.sendToClient(getALLClinicRequest((GetAllClinicsRequest) msg));
			} catch (IOException e) {
				System.out.println("Error - getALLClinicRequest");
			}
		}

		if(msg instanceof GetClinicRequest) {
			try {
				client.sendToClient(getClinicRequest((GetClinicRequest) msg));
			} catch (IOException e) {
				System.out.println("Error - getClinicRequest");
			}
		}

		if(msg instanceof UpdateActiveHoursRequest) {
			try {
				client.sendToClient(updateActiveHoursRequest((UpdateActiveHoursRequest) msg));
			} catch (IOException e) {
				System.out.println("Error - updateActiveHoursRequest");
			}
		}
	}

	protected Response updateActiveHoursRequest(UpdateActiveHoursRequest request) {
		dataBase.setOpeningHours(dataBase.getClinic(request.clinicName), request.activeHours.openingHours);
		dataBase.setClosingHours(dataBase.getClinic(request.clinicName), request.activeHours.closingHours);
		UpdateActiveHoursResponse response = new UpdateActiveHoursResponse();
		return response;
	}

	protected Response getClinicRequest(GetClinicRequest request) {
		GetClinicResponse response = new GetClinicResponse(dataBase.getClinic(request.clinicName));
		return response;
	}

	protected Response getALLClinicRequest(GetAllClinicsRequest request) {
		GetAllClinicsResponse allClinics = new GetAllClinicsResponse(dataBase.getAll(Clinic.class));
		return allClinics;
	}
}
