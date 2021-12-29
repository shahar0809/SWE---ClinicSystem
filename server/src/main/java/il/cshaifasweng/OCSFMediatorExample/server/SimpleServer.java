package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.requests.GetAllClinicsRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.GetClinicRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.UpdateActiveHoursRequest;
import il.cshaifasweng.OCSFMediatorExample.response.GetAllClinicsResponse;
import il.cshaifasweng.OCSFMediatorExample.response.GetClinicResponse;
import il.cshaifasweng.OCSFMediatorExample.response.Response;
import il.cshaifasweng.OCSFMediatorExample.response.UpdateActiveHoursResponse;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class SimpleServer extends AbstractServer {
    protected DatabaseAccess dataBase;

    public SimpleServer(int port) {
        super(port);
        dataBase = new DatabaseAccess();
        initDatabase();
    }

    private void initDatabase() {
        if (dataBase.getAll(Clinic.class).isEmpty()) {
            dataBase.insertEntity(new Clinic("clinic1", LocalTime.now(), LocalTime.now()));
            dataBase.insertEntity(new Clinic("clinic2", LocalTime.now(), LocalTime.now()));
            dataBase.insertEntity(new Clinic("clinic3", LocalTime.now(), LocalTime.now()));
        }

        if (dataBase.getAll(Patient.class).isEmpty()) {
            dataBase.insertEntity(new Patient("p1", "pass1"));
            dataBase.insertEntity(new Patient("p1", "pass1"));
            dataBase.insertEntity(new Patient("p1", "pass1"));
            dataBase.insertEntity(new Patient("p1", "pass1"));
        }
        List<Patient> patientList = dataBase.getAll(Patient.class);

        if (dataBase.getAll(Nurse.class).isEmpty()) {
            dataBase.insertEntity(new Nurse("n1", "passnurse1", 0, "Nurse1", "LastNurse1", "n1@g.com", "Nurse"));
            dataBase.insertEntity(new Nurse("n2", "passnurse2", 1, "Nurse2", "LastNurse2", "n2@g.com", "Nurse"));
            dataBase.insertEntity(new Nurse("n3", "passnurse3", 2, "Nurse3", "LastNurse3", "n3@g.com", "Nurse"));
        }
        List<Nurse> nursesList = dataBase.getAll(Nurse.class);

        if (dataBase.getAll(Doctor.class).isEmpty()) {
            dataBase.insertEntity(new FamilyDoctor("d1", "passdoctor1", 3, "Doctor1", "LastDoctor1", "d1@g.com", "Doctor"));
            dataBase.insertEntity(new ProfessionDoctor("d2", "passdoctor2", 4, "Doctor2", "LastDoctor2", "d2@g.com", "Doctor"));
            dataBase.insertEntity(new ProfessionDoctor("d3", "passdoctor1", 5, "Doctor3", "LastDoctor3", "d3@g.com", "Doctor"));
            dataBase.insertEntity(new ProfessionDoctor("d4", "passdoctor1", 6, "Doctor4", "LastDoctor4", "d4@g.com", "Doctor"));
        }
        List<Doctor> doctorsList = dataBase.getAll(Doctor.class);

        if (dataBase.getAll(Appointment.class).isEmpty()) {
            dataBase.insertEntity(new NurseAppointment(patientList.get(0), nursesList.get(0), LocalDateTime.now()));
            dataBase.insertEntity(new NurseAppointment(patientList.get(1), nursesList.get(1), LocalDateTime.now()));
            dataBase.insertEntity(new NurseAppointment(patientList.get(3), nursesList.get(2), LocalDateTime.now()));

            dataBase.insertEntity(new ProfessionDoctorAppointment(patientList.get(0), (ProfessionDoctor)doctorsList.get(1), LocalDateTime.now()));
            dataBase.insertEntity(new ProfessionDoctorAppointment(patientList.get(3), (ProfessionDoctor)doctorsList.get(3), LocalDateTime.now()));
            dataBase.insertEntity(new ProfessionDoctorAppointment(patientList.get(1), (ProfessionDoctor)doctorsList.get(2), LocalDateTime.now()));

            dataBase.insertEntity(new VaccineAppointment(patientList.get(3), LocalDateTime.now()));
            dataBase.insertEntity(new VaccineAppointment(patientList.get(0), LocalDateTime.now()));

            dataBase.insertEntity(new CovidTestAppointment(patientList.get(3), LocalDateTime.now()));
            dataBase.insertEntity(new CovidTestAppointment(patientList.get(0), LocalDateTime.now()));
        }
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        if (msg instanceof GetAllClinicsRequest) {
            try {
                client.sendToClient(getALLClinicRequest((GetAllClinicsRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - getALLClinicRequest");
            }
        }

        if (msg instanceof GetClinicRequest) {
            try {
                client.sendToClient(getClinicRequest((GetClinicRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - getClinicRequest");
            }
        }

        if (msg instanceof UpdateActiveHoursRequest) {
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
