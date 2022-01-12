package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.requests.*;
import il.cshaifasweng.OCSFMediatorExample.response.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import il.cshaifasweng.OCSFMediatorExample.utils.SecureUtils;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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
            dataBase.insertEntity(new Clinic("clinic1", LocalTime.of(10, 43), LocalTime.of(17, 43)));
            dataBase.insertEntity(new Clinic("clinic2", LocalTime.of(9, 17), LocalTime.of(16, 05)));
            dataBase.insertEntity(new Clinic("clinic3", LocalTime.of(11, 36), LocalTime.of(19, 34)));
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
            dataBase.insertEntity(new Appointment(patientList.get(0), LocalDateTime.now(), nursesList.get(0), clinics.get(0)));
            dataBase.insertEntity(new Appointment(patientList.get(1), LocalDateTime.now(), nursesList.get(1), clinics.get(0)));
            dataBase.insertEntity(new Appointment(patientList.get(3), LocalDateTime.now(), nursesList.get(2), clinics.get(0)));

            dataBase.insertEntity(new Appointment(patientList.get(0), LocalDateTime.now(), professionDoctorList.get(1), clinics.get(0)));
            dataBase.insertEntity(new Appointment(patientList.get(3), LocalDateTime.now(), professionDoctorList.get(3), clinics.get(0)));
            dataBase.insertEntity(new Appointment(patientList.get(1), LocalDateTime.now(), professionDoctorList.get(2), clinics.get(0)));

            dataBase.insertEntity(new Appointment(patientList.get(0), LocalDateTime.now(), familyDoctorsList.get(1), clinics.get(0)));
            dataBase.insertEntity(new Appointment(patientList.get(2), LocalDateTime.now(), familyDoctorsList.get(3), clinics.get(0)));
            dataBase.insertEntity(new Appointment(patientList.get(1), LocalDateTime.now(), familyDoctorsList.get(0), clinics.get(0)));

            dataBase.insertEntity(new Appointment(patientList.get(3), LocalDateTime.now(), nursesList.get(0), clinics.get(0)));
            dataBase.insertEntity(new Appointment(patientList.get(0), LocalDateTime.now(), nursesList.get(1), clinics.get(0)));

            dataBase.insertEntity(new Appointment(patientList.get(3), LocalDateTime.now(), nursesList.get(1), clinics.get(0)));
            dataBase.insertEntity(new Appointment(patientList.get(0), LocalDateTime.now(), nursesList.get(0), clinics.get(0)));
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
            return;
        }

        if (msg instanceof GetClinicRequest) {
            try {
                client.sendToClient(getClinicRequest((GetClinicRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - getClinicRequest");
            }
            return;
        }

        if (msg instanceof UpdateActiveHoursRequest) {
            try {
                client.sendToClient(updateActiveHoursRequest((UpdateActiveHoursRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - updateActiveHoursRequest");
            }
            return;
        }

        if (msg instanceof LoginRequest) {
            try {
                client.sendToClient(handleLoginRequest((LoginRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - LoginRequest");
            }
            return;
        }

        if (msg instanceof RegisterRequest) {
            try {
                client.sendToClient(handleRegisterRequest((RegisterRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - RegisterRequest");
            }
            return;
        }

        if (msg instanceof ReserveFreeAppointmentsRequest) {
            try {
                client.sendToClient(getFreeAppointmentsRequest((ReserveFreeAppointmentsRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - getALLCovidVaccineRequest");
            }
            return;
        }

        if (msg instanceof ReservePatientAppointmentRequest) {
            try {
                client.sendToClient(getPatientAppointmentsRequest((ReservePatientAppointmentRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - ReservePatientAppointmentRequest");
            }
            return;
        }

        if (msg instanceof AddAppointmentRequest) {
            try {
                client.sendToClient(addAppointmentsRequest((AddAppointmentRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - AddAppointmentRequest");
            }
            return;
        }

        if (msg instanceof DeleteAppointmentRequest) {
            try {
                client.sendToClient(deleteAppointmentsRequest((DeleteAppointmentRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - DeleteAppointmentRequest");
            }
            return;
        }

        //
//        if (msg instanceof GetGreenPassRequest) {
//            try {
//                client.sendToClient(getGreenPassRequest((GetGreenPassRequest) msg));
//            } catch (IOException e) {
//                System.out.println("Error - GetGreenPassRequest");
//            }
//        }
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

    protected Response handleLoginRequest(LoginRequest request) {
        User user;
        try {
            user = dataBase.getUser(request.username);
        } catch (NoResultException e) {
            return new LoginResponse("User not found!", true);
        }
        String securePassword = SecureUtils.getSecurePassword(request.password, user.getSALT());
        if (!Objects.equals(user.getHashPassword(), securePassword))
            return new LoginResponse("Incorrect password!", true);
        return new LoginResponse(user, true);
    }

    protected Response handleRegisterRequest(RegisterRequest request) {
        try {
            dataBase.getUser(request.username);
            return new RegisterResponse("Username is already taken!", true);
        } catch (NoResultException ignored) {
        }
        return new RegisterResponse(dataBase.createPatient(request.username, request.password), true);
    }

    protected Response getFreeAppointmentsRequest(ReserveFreeAppointmentsRequest request) {
        Comparator<Appointment> appointmentComparator = new Comparator<Appointment>() {
            @Override
            public int compare(Appointment appointment1, Appointment appointment2) {
                return appointment1.getTreatmentDateTime().compareTo(appointment2.getTreatmentDateTime());
            }
        };
        List<Appointment> appointments = new ArrayList<Appointment>();
        ReserveFreeAppointmentsResponse response;
        try {
            if ("CovidVaccine".equals(request.appointmentType)) {
                for (Clinic clinic : dataBase.getAll(Clinic.class)) {
                    appointments.addAll(dataBase.getFreeAppointments(clinic, AppointmentType.CovidVaccine));
                }
            }
            if ("FluVaccine".equals(request.appointmentType)) {
                for (Clinic clinic : dataBase.getAll(Clinic.class)) {
                    appointments.addAll(dataBase.getFreeAppointments(clinic, AppointmentType.FluVaccine));
                }
            }
            if ("CovidCheck".equals(request.appointmentType)) {
                for (Clinic clinic : dataBase.getAll(Clinic.class)) {
                    appointments.addAll(dataBase.getFreeAppointments(clinic, AppointmentType.CovidTest));
                }
            }
            appointments.sort(appointmentComparator);
            response = new ReserveFreeAppointmentsResponse(appointments, true);
        } catch (Exception e) {
            appointments.sort(appointmentComparator);
            response = new ReserveFreeAppointmentsResponse(appointments,false);
        }
        return response;
    }

    protected Response getPatientAppointmentsRequest(ReservePatientAppointmentRequest request) {
        List<Appointment> appointments = new ArrayList<Appointment>();
        ReservePatientAppointmentResponse allAppointments;
        try {
            appointments = dataBase.getPatient(request.username).getAppointments();
            allAppointments = new ReservePatientAppointmentResponse(appointments, true);
        } catch (Exception e) {
            allAppointments = new ReservePatientAppointmentResponse(appointments, true);
        }
        return allAppointments;
    }

    protected Response addAppointmentsRequest(AddAppointmentRequest request) {
        AddAppointmentResponse response;
        try {
            dataBase.getPatient(request.username).addAppointment(request.appointment);
            response = new AddAppointmentResponse(true);
        } catch (Exception e) {
            response = new AddAppointmentResponse(true);
        }
        return response;
    }

    protected Response deleteAppointmentsRequest(DeleteAppointmentRequest request) {
        DeleteAppointmentResponse response;
        try {
            request.appointment.setAvailable(true);
            dataBase.getPatient(request.username).deleteAppointment(request.appointment);
            response = new DeleteAppointmentResponse(true);
        } catch (Exception e) {
            response = new DeleteAppointmentResponse(true);
        }
        return response;
    }

//    protected Response getGreenPassRequest(GetGreenPassRequest request) {
//        Patient patient = new Patient();
//        GetGreenPassResponse response;
//        try {
//            patient = dataBase.getPatiant(request.patient);
//            response = new GetGreenPassResponse(patient.getCovidVaccine, true);
//        } catch (Exception e) {
//            response = new GetGreenPassResponse(patient.getCovidVaccine, true);
//        }
//        return response;
//    }

}
