package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.requests.*;
import il.cshaifasweng.OCSFMediatorExample.response.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SimpleServer extends AbstractServer {
    protected DatabaseAccess dataBase;

    public SimpleServer(int port) {
        super(port);
        dataBase = new DatabaseAccess();
        initDatabase();
    }

    /**
     * Initialize database with fake data, if empty.
     */
    private void initDatabase() {
        if (dataBase.getAll(Clinic.class).isEmpty()) {
            dataBase.insertEntity(new Clinic("clinic1", LocalTime.now(), LocalTime.now()));
            dataBase.insertEntity(new Clinic("clinic2", LocalTime.now(), LocalTime.now()));
            dataBase.insertEntity(new Clinic("clinic3", LocalTime.now(), LocalTime.now()));
        }
        List<Clinic> clinics = dataBase.getAll(Clinic.class);

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

        if (dataBase.getAll(ProfessionDoctor.class).isEmpty()) {
            dataBase.insertEntity(new ProfessionDoctor("d1", "passdoctor1", 3, "Doctor1", "LastDoctor1", "d1@g.com", "Doctor"));
            dataBase.insertEntity(new ProfessionDoctor("d2", "passdoctor2", 4, "Doctor2", "LastDoctor2", "d2@g.com", "Doctor"));
            dataBase.insertEntity(new ProfessionDoctor("d3", "passdoctor1", 5, "Doctor3", "LastDoctor3", "d3@g.com", "Doctor"));
            dataBase.insertEntity(new ProfessionDoctor("d4", "passdoctor1", 6, "Doctor4", "LastDoctor4", "d4@g.com", "Doctor"));
        }
        List<ProfessionDoctor> professionDoctorList = dataBase.getAll(ProfessionDoctor.class);

        if (dataBase.getAll(FamilyDoctor.class).isEmpty()) {
            dataBase.insertEntity(new FamilyDoctor("d21", "passdoctor1", 3, "Doctor1", "LastDoctor1", "d1@g.com", "Doctor"));
            dataBase.insertEntity(new FamilyDoctor("d22", "passdoctor2", 4, "Doctor2", "LastDoctor2", "d2@g.com", "Doctor"));
            dataBase.insertEntity(new FamilyDoctor("d23", "passdoctor1", 5, "Doctor3", "LastDoctor3", "d3@g.com", "Doctor"));
            dataBase.insertEntity(new FamilyDoctor("d24", "passdoctor1", 6, "Doctor4", "LastDoctor4", "d4@g.com", "Doctor"));
        }
        List<FamilyDoctor> familyDoctorsList = dataBase.getAll(FamilyDoctor.class);

        if (dataBase.getAll(Appointment.class).isEmpty()) {
            dataBase.insertEntity(new NurseAppointment(patientList.get(0), nursesList.get(0), LocalDateTime.now(), clinics.get(0)));
            dataBase.insertEntity(new NurseAppointment(patientList.get(1), nursesList.get(1), LocalDateTime.now(), clinics.get(0)));
            dataBase.insertEntity(new NurseAppointment(patientList.get(3), nursesList.get(2), LocalDateTime.now(), clinics.get(0)));

            dataBase.insertEntity(new ProfessionDoctorAppointment(patientList.get(0), professionDoctorList.get(1), LocalDateTime.now(), clinics.get(0)));
            dataBase.insertEntity(new ProfessionDoctorAppointment(patientList.get(3), professionDoctorList.get(3), LocalDateTime.now(), clinics.get(0)));
            dataBase.insertEntity(new ProfessionDoctorAppointment(patientList.get(1), professionDoctorList.get(2), LocalDateTime.now(), clinics.get(0)));

            dataBase.insertEntity(new FamilyDoctorAppointment(patientList.get(0), LocalDateTime.now(), familyDoctorsList.get(1), clinics.get(0)));
            dataBase.insertEntity(new FamilyDoctorAppointment(patientList.get(2), LocalDateTime.now(), familyDoctorsList.get(3), clinics.get(0)));
            dataBase.insertEntity(new FamilyDoctorAppointment(patientList.get(1), LocalDateTime.now(), familyDoctorsList.get(0), clinics.get(0)));

            dataBase.insertEntity(new CovidVaccineAppointment(patientList.get(3), LocalDateTime.now(), nursesList.get(0), clinics.get(0)));
            dataBase.insertEntity(new CovidVaccineAppointment(patientList.get(0), LocalDateTime.now(), nursesList.get(1), clinics.get(0)));

            dataBase.insertEntity(new CovidTestAppointment(patientList.get(3), LocalDateTime.now(), nursesList.get(1), clinics.get(0)));
            dataBase.insertEntity(new CovidTestAppointment(patientList.get(0), LocalDateTime.now(), nursesList.get(0), clinics.get(0)));
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

        if (msg instanceof ReserveAppointmentRequest) {
            try {
                client.sendToClient(getFreeAppointmentsRequest((ReserveAppointmentRequest) msg, ((ReserveAppointmentRequest) msg).appointment));
            } catch (IOException e) {
                System.out.println("Error - getALLCovidVaccineRequest");
            }
        }
    }

    protected Response updateActiveHoursRequest(UpdateActiveHoursRequest request) {
        UpdateActiveHoursResponse response;
        try {
            dataBase.setOpeningHours(dataBase.getClinic(request.clinicName), request.activeHours.openingHours);
            dataBase.setClosingHours(dataBase.getClinic(request.clinicName), request.activeHours.closingHours);
            response = new UpdateActiveHoursResponse(true);
        }
        catch (Exception e) {
            response = new UpdateActiveHoursResponse(false);
        }
        return response;
    }

    protected Response getClinicRequest(GetClinicRequest request) {
        Clinic clinic = null;
        GetClinicResponse response;
        try {
            clinic = dataBase.getClinic(request.clinicName);
            response = new GetClinicResponse(clinic, true);
        }
        catch (Exception e) {
            response = new GetClinicResponse(clinic, false);
        }
        return response;
    }

    protected Response getALLClinicRequest(GetAllClinicsRequest request) {
        List<Clinic> clinics = new ArrayList<Clinic>();
        GetAllClinicsResponse allClinics;
        try {
            clinics = dataBase.getAll(Clinic.class);
            allClinics = new GetAllClinicsResponse(clinics, true);
        }
        catch (Exception e) {
            allClinics = new GetAllClinicsResponse(clinics, false);
        }
        return allClinics;
    }

    protected <T> Response getFreeAppointmentsRequest(GetFreeAppointmentRequest<T> request) {
        List<T> appointments = new ArrayList<>();
        GetFreeAppointmentsResponse<T> allAppointments;

        try {
            for (Clinic clinic : dataBase.getAll(Clinic.class)) {
                appointments.addAll(dataBase.getFreeAppointments(request.getAppointmentType(), clinic));
            }
            allAppointments = new GetFreeAppointmentsResponse<>(true, appointments);
        }
        catch (Exception e) {
            allAppointments = new GetFreeAppointmentsResponse<>(false, appointments);
        }
        return allAppointments;

    }
}
