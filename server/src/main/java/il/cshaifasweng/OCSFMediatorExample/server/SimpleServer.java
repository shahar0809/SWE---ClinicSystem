package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.requests.*;
import il.cshaifasweng.OCSFMediatorExample.response.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import il.cshaifasweng.OCSFMediatorExample.utils.Messages;
import il.cshaifasweng.OCSFMediatorExample.utils.SecureUtils;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
            dataBase.insertEntity(new Clinic("clinic2", LocalTime.of(9, 17), LocalTime.of(16, 19)));
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

        if (dataBase.getAll(Question.class).isEmpty()) {
            dataBase.insertEntity(new Question("Do you suffer from any of the following symptoms: fever, cough, shortness of breath, sore throat?"));
            dataBase.insertEntity(new Question("Were you in contact with a verified patient?"));
            dataBase.insertEntity(new Question("Are you or someone in your house waiting for a Covid test answer?"));
        }
        List<Question> questionList = dataBase.getAll(Question.class);

        if (dataBase.getAll(Appointment.class).isEmpty()) {
            dataBase.insertEntity(new NurseAppointment(nursesList.get(0), LocalDateTime.now(), clinics.get(0)));
            dataBase.insertEntity(new NurseAppointment(nursesList.get(1), LocalDateTime.now(), clinics.get(0)));
            dataBase.insertEntity(new NurseAppointment(patientList.get(3), nursesList.get(2), LocalDateTime.now(), clinics.get(0)));

            dataBase.insertEntity(new ProfessionDoctorAppointment(AppointmentType.GYNECOLOGY, professionDoctorList.get(1), LocalDateTime.now(), clinics.get(0)));
            dataBase.insertEntity(new ProfessionDoctorAppointment(AppointmentType.CARDIO, professionDoctorList.get(3), LocalDateTime.now(), clinics.get(0)));
            dataBase.insertEntity(new ProfessionDoctorAppointment(AppointmentType.GASTROLOGY, patientList.get(1), professionDoctorList.get(2), LocalDateTime.now(), clinics.get(0)));

            dataBase.insertEntity(new FamilyDoctorAppointment(familyDoctorsList.get(1), LocalDateTime.now(), clinics.get(0)));
            dataBase.insertEntity(new FamilyDoctorAppointment(familyDoctorsList.get(3), LocalDateTime.now(), clinics.get(0)));
            dataBase.insertEntity(new FamilyDoctorAppointment(patientList.get(2), familyDoctorsList.get(0), LocalDateTime.now(), clinics.get(0)));

            dataBase.insertEntity(new CovidTestAppointment(nursesList.get(0), LocalDateTime.now(), clinics.get(0)));
            dataBase.insertEntity(new CovidTestAppointment(patientList.get(0), nursesList.get(1), LocalDateTime.now(), clinics.get(0)));

            dataBase.insertEntity(new CovidVaccineAppointment(nursesList.get(0), LocalDateTime.now(), clinics.get(0)));
            dataBase.insertEntity(new CovidVaccineAppointment(nursesList.get(2), LocalDateTime.now(), clinics.get(1)));

            dataBase.insertEntity(new FluVaccineAppointment(nursesList.get(0), LocalDateTime.now(), clinics.get(0)));
            dataBase.insertEntity(new FluVaccineAppointment(nursesList.get(2), LocalDateTime.now(), clinics.get(1)));
            dataBase.insertEntity(new FluVaccineAppointment(patientList.get(3), nursesList.get(1), LocalDateTime.now(), clinics.get(0)));

            dataBase.insertEntity(new NurseAppointment(nursesList.get(0), LocalDateTime.now(), clinics.get(0)));
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
        else if (msg instanceof GetClinicRequest) {
            try {
                client.sendToClient(getClinicRequest((GetClinicRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - getClinicRequest");
            }
        }
        else if (msg instanceof UpdateActiveHoursRequest) {
            try {
                client.sendToClient(updateActiveHoursRequest((UpdateActiveHoursRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - updateActiveHoursRequest");
            }
        }
        else if (msg instanceof LoginRequest) {
            try {
                client.sendToClient(handleLoginRequest((LoginRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - LoginRequest");
            }
        }
        else if (msg instanceof RegisterRequest) {
            try {
                client.sendToClient(handleRegisterRequest((RegisterRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - RegisterRequest");
            }
        }
        else if (msg instanceof GetFreeAppointmentRequest) {
            try {
                client.sendToClient(getFreeAppointmentsRequest((GetFreeAppointmentRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - getALLCovidVaccineRequest");
            }
        }
        else if (msg instanceof GetPatientAppointmentRequest) {
            try {
                client.sendToClient(getPatientAppointmentsRequest((GetPatientAppointmentRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - GetPatientAppointmentRequest");
            }
        }
        else if (msg instanceof ReserveAppointmentRequest) {
            try {
                client.sendToClient(addAppointmentsRequest((ReserveAppointmentRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - AddAppointmentRequest");
            }
        }
        else if (msg instanceof DeleteAppointmentRequest) {
            try {
                client.sendToClient(deleteAppointmentsRequest((DeleteAppointmentRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - DeleteAppointmentRequest");
            }
        }
        else if (msg instanceof GetGreenPassRequest) {
            try {
                client.sendToClient(getGreenPassRequest((GetGreenPassRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - GetGreenPassRequest");
            }
        }
        else if (msg instanceof SaveAnswerRequest) {
            try {
                client.sendToClient(saveAnswerRequest((SaveAnswerRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - SaveAnswerRequest");
            }
        }
        else if (msg instanceof GetQuestionRequest) {
            try {
                client.sendToClient(getQuestionsRequest((GetQuestionRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - GetQuestionsRequest");
            }
        }
    }

    protected Response updateActiveHoursRequest(UpdateActiveHoursRequest request) {
        UpdateActiveHoursResponse response;
        try {
            dataBase.setOpeningHours(dataBase.getClinic(request.clinicName), request.activeHours.getOpeningHours());
            dataBase.setClosingHours(dataBase.getClinic(request.clinicName), request.activeHours.getClosingHours());
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
        List<Clinic> clinics = new ArrayList<>();
        GetAllClinicsResponse allClinics;
        try {
            clinics = dataBase.getAll(Clinic.class);
            allClinics = new GetAllClinicsResponse(clinics, true);
        } catch (Exception e) {
            allClinics = new GetAllClinicsResponse(clinics, false);
        }
        return allClinics;
    }

    protected Response handleRegisterRequest(RegisterRequest request) {
        try {
            dataBase.getUser(request.username);
            return new RegisterResponse(Messages.REGISTER_USERNAME_TAKEN, true);
        } catch (NoResultException ignored) {
        }
        return new RegisterResponse(dataBase.createPatient(request.username, request.password), true);
    }

    protected Response handleLoginRequest(LoginRequest request) {
        User user;
        try {
            user = dataBase.getUser(request.username);
        } catch (NoResultException e) {
            return new LoginResponse(Messages.LOGIN_USER_NOT_FOUND, true);
        }
        String securePassword = SecureUtils.getSecurePassword(request.password, user.getSALT());
        if (!Objects.equals(user.getHashPassword(), securePassword))
            return new LoginResponse(Messages.LOGIN_WRONG_AUTH, true);
        return new LoginResponse(user, true);
    }

    protected <T extends Appointment> Response getFreeAppointmentsRequest(GetFreeAppointmentRequest<T> request) {
        List<T> appointments = new ArrayList<>();
        GetFreeAppointmentsResponse<T> response;

        try {
            for (Clinic clinic : dataBase.getAll(Clinic.class)) {
                appointments.addAll(dataBase.getFreeAppointments(request.getAppointmentType(), clinic, request.getEnumType()));
            }

            response = new GetFreeAppointmentsResponse<>(appointments, true);
        } catch (Exception e) {
            response = new GetFreeAppointmentsResponse<>(appointments, false, e.getMessage());
        }

        return response;
    }

    protected Response getPatientAppointmentsRequest(GetPatientAppointmentRequest request) {
        List<Appointment> appointments = new ArrayList<>();
        GetPatientAppointmentResponse allAppointments;
        try {
            appointments = ((Patient) request.getUser()).getAppointments();
            allAppointments = new GetPatientAppointmentResponse(appointments, true);
        } catch (Exception e) {
            allAppointments = new GetPatientAppointmentResponse(appointments, false, e.getMessage());
        }

        return allAppointments;
    }

    protected Response addAppointmentsRequest(ReserveAppointmentRequest request) {
        ReserveAppointmentResponse response;
        try {
            if (request.getAppointment() instanceof CovidVaccineAppointment) {
                if (!dataBase.hasAnsweredCovidQuestionnaire(request.getUser())) {
                    return new ReserveAppointmentResponse(false, Messages.COVID_TEST_NO_QUESTIONNAIRE);
                }
            }

            ((Patient)request.getUser()).addAppointment(request.getAppointment());

            // Update availability
            request.getAppointment().setAvailable(false);
            dataBase.updateAppointment(request.getAppointment());

            response = new ReserveAppointmentResponse(true);
        } catch (Exception e) {
            response = new ReserveAppointmentResponse(false, e.getMessage());
        }
        return response;
    }

    protected Response deleteAppointmentsRequest(DeleteAppointmentRequest request) {
        DeleteAppointmentResponse response;
        try {
            request.getAppointment().setAvailable(true);
            request.getAppointment().setPatient(null);
            ((Patient)request.getUser()).deleteAppointment(request.getAppointment());
            dataBase.updateAppointment(request.getAppointment());

            response = new DeleteAppointmentResponse(true);
        } catch (Exception e) {
            response = new DeleteAppointmentResponse(false, e.getMessage());
        }
        return response;
    }

    protected Response getGreenPassRequest(GetGreenPassRequest request) {
        Patient patient = null;
        GetGreenPassResponse response;
        try {
            patient = ((Patient)request.getUser());
            response = new GetGreenPassResponse(patient.gotCovidVaccine(), true);
        } catch (Exception e) {
            assert patient != null;
            response = new GetGreenPassResponse(patient.gotCovidVaccine(), false, e.getMessage());
        }
        return response;
    }

    protected Response saveAnswerRequest(SaveAnswerRequest request) {
        Patient patient = null;
        SaveAnswerResponse response;
        try {
            patient = ((Patient) request.user);
            dataBase.insertEntity(request.answer);
            response = new SaveAnswerResponse(true);
        } catch (Exception e) {
            assert patient != null;
            response = new SaveAnswerResponse(false, e.getMessage());
        }
        return response;
    }

    protected Response getQuestionsRequest(GetQuestionRequest request) {
        List<Question> questions = new ArrayList<>();
        GetQuestionsResponse response;
        try {
            questions = dataBase.getAll(Question.class);
            response = new GetQuestionsResponse(questions, true);
        } catch (Exception e) {
            response = new GetQuestionsResponse(questions, false, e.getMessage());
        }
        return response;
    }
}
