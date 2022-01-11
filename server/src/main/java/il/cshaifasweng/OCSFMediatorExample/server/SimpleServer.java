package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Patient;
import il.cshaifasweng.OCSFMediatorExample.requests.*;
import il.cshaifasweng.OCSFMediatorExample.response.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SimpleServer extends AbstractServer {
    protected DatabaseAccess dataBase;

    public SimpleServer(int port) {
        super(port);

        dataBase = DatabaseAccess.getInstance();

        if (dataBase.getAll(Clinic.class).isEmpty()) {
            dataBase.insertEntity(new Clinic("clinic1", LocalTime.of(10, 43), LocalTime.of(17, 43)));
            dataBase.insertEntity(new Clinic("clinic2", LocalTime.of(9, 17), LocalTime.of(16, 05)));
            dataBase.insertEntity(new Clinic("clinic3", LocalTime.of(11, 36), LocalTime.of(19, 34)));
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
                client.sendToClient(getFreeAppointmentsRequest((ReserveAppointmentRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - getALLCovidVaccineRequest");
            }
        }

        if (msg instanceof ReservePatientAppointmentRequest) {
            try {
                client.sendToClient(getPatientAppointmentsRequest((ReservePatientAppointmentRequest) msg);
            } catch (IOException e) {
                System.out.println("Error - ReservePatientAppointmentRequest");
            }
        }

        if (msg instanceof GetGreenPassRequest) {
            try {
                client.sendToClient(getGreenPassRequest((GetGreenPassRequest) msg);
            } catch (IOException e) {
                System.out.println("Error - GetGreenPassRequest");
            }
        }
    }

    protected Response updateActiveHoursRequest(UpdateActiveHoursRequest request) {
        UpdateActiveHoursResponse response;
        try {
            dataBase.setOpeningHours(dataBase.getClinic(request.clinicName), request.activeHours.openingHours);
            dataBase.setClosingHours(dataBase.getClinic(request.clinicName), request.activeHours.closingHours);
            response = new UpdateActiveHoursResponse(true);
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            allClinics = new GetAllClinicsResponse(clinics, false);
        }
        return allClinics;
    }

    protected Response getFreeAppointmentsRequest(ReserveFreeAppointmentsRequest request) {
        List<Appointment> appointments = new ArrayList<Appointment>();
        ReserveFreeAppointmentsResponse response;
        try {
            if ("CovidVaccine".equals(request.appointmentType)) {
                for (Clinic clinic : dataBase.getAll(Clinic.class)) {
                    appointments.addAll(dataBase.getFreeAppointments(clinic, CovidVaccine.class));
                }
            }
            if ("FluVaccine".equals(request.appointmentType)) {
                for (Clinic clinic : dataBase.getAll(Clinic.class)) {
                    appointments.addAll(dataBase.getFreeAppointments(clinic, FluVaccine.class));
                }
            }
            if ("CovidCheck".equals(request.appointmentType)) {
                for (Clinic clinic : dataBase.getAll(Clinic.class)) {
                    appointments.addAll(dataBase.getFreeAppointments(clinic, CovidCheck.class));
                }
            }
            response = new ReserveFreeAppointmentsResponse(true);
        } catch (Exception e) {
            response = new ReserveFreeAppointmentsResponse(false);
        }
        return response;
    }

    protected Response getPatientAppointmentsRequest(ReservePatientAppointmentRequest request) {
        List<Appointment> appointments = new ArrayList<Appointment>();
        ReservePatientAppointmentResponse allAppointments;
        try {
            Patiant patiant = dataBase.getPatiant(request.patient).getAppointments;
            allAppointments = new ReservePatientAppointmentResponse(true);
        } catch (Exception e) {
            allAppointments = new ReservePatientAppointmentResponse(true);
        }
        return allAppointments;
    }

    protected Response getGreenPassRequest(GetGreenPassRequest request) {
        Patient patient = new Patient();
        GetGreenPassResponse response;
        try {
            patient = dataBase.getPatiant(request.patient);
            response = new GetGreenPassResponse(true, patient.getCovidVaccine);
        } catch (Exception e) {
            response = new GetGreenPassResponse(true, patient.getCovidVaccine);
        }
        return response;
    }

}
