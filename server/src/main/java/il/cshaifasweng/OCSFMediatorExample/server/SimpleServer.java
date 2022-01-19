package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.requests.*;
import il.cshaifasweng.OCSFMediatorExample.response.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import il.cshaifasweng.OCSFMediatorExample.utils.Constants;
import il.cshaifasweng.OCSFMediatorExample.utils.Hours;
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

        dataBase = DatabaseAccess.getInstance();
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
            dataBase.insertEntity(new Patient("p1", "pass1", 16, clinics.get(0)));
            dataBase.insertEntity(new Patient("p2", "pass1", 17, clinics.get(0)));
            dataBase.insertEntity(new Patient("p3", "pass1", 18, clinics.get(1)));
            dataBase.insertEntity(new Patient("p4", "pass1", 19, clinics.get(2)));
        }
        List<Patient> patientList = dataBase.getAll(Patient.class);

        if (dataBase.getAll(Nurse.class).isEmpty()) {
            dataBase.insertEntity(new Nurse("n1", "passnurse1", 0, "Nurse1", "LastNurse1", "n1@g.com", "Nurse"));
            dataBase.insertEntity(new Nurse("n2", "passnurse2", 1, "Nurse2", "LastNurse2", "n2@g.com", "Nurse"));
            dataBase.insertEntity(new Nurse("n3", "passnurse3", 2, "Nurse3", "LastNurse3", "n3@g.com", "Nurse"));
        }
        List<Nurse> nursesList = dataBase.getAll(Nurse.class);

        if (dataBase.getAll(ClinicManager.class).isEmpty()) {
            dataBase.insertEntity(new ClinicManager("cliinc1un", "clinicpass1", 2, "clinicfn1m", "clinic_last_manager1", "clinicemail@a.com", clinics.get(0)));
            dataBase.insertEntity(new ClinicManager("cliinc2un", "clinicpass1", 2, "clinicfn2m", "clinic_last_manager1", "clinicemail@a.com", clinics.get(1)));
            dataBase.insertEntity(new ClinicManager("cliinc3un", "clinicpass1", 2, "clinicfn3m", "clinic_last_manager1", "clinicemail@a.com", clinics.get(2)));
        }
        List<ClinicManager> clinicManagersList = dataBase.getAll(ClinicManager.class);

        if (dataBase.getAll(HospitalManager.class).isEmpty()) {
            dataBase.insertEntity(new HospitalManager("cliinc4un", "clinicpass1", 2, "clinicfn1m", "clinic_last_manager1", "clinicemail@a.com", clinics));
        }
        List<HospitalManager> hospitalManagersList = dataBase.getAll(HospitalManager.class);

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
        if (!(msg instanceof Request)) {
            System.out.println("Error - Received invalid request");
            return;
        }
        Request request = (Request) msg;

        if (request instanceof LoginRequest) {
            try {
                client.sendToClient(handleLoginRequest((LoginRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - LoginRequest");
            }
            return;
        }

        if (request instanceof RegisterRequest) {
            try {
                client.sendToClient(handleRegisterRequest((RegisterRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - RegisterRequest");
            }
            return;
        }

        if (request instanceof GetAllClinicsRequest) {
            try {
                client.sendToClient(getALLClinicRequest((GetAllClinicsRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - getALLClinicRequest");
            }
            return;
        }

        User user;
        try {
            user = dataBase.getUserByToken(request.getToken());
        } catch (Exception e) {
            System.out.println(request.getToken());
            e.printStackTrace();
            try {
                client.sendToClient(new TokenExpiredResponse(true));
            } catch (IOException e2) {
                System.out.println("Error - TokenExpiredResponse");
            }
            return;
        }
        // ALL REQUESTS ASIDE FROM LOGIN AND REGISTER MUST BE BELOW THIS LINE!!!


        if (msg instanceof GetClinicRequest) {
            try {
                client.sendToClient(getClinicRequest((GetClinicRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - getClinicRequest");
            }
            return;
        } else if (msg instanceof UpdateActiveHoursRequest) {
            try {
                client.sendToClient(updateActiveHoursRequest((UpdateActiveHoursRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - updateActiveHoursRequest");
            }
            return;
        }

        if (msg instanceof UpdateCovidVaccineHoursRequest) {
            try {
                client.sendToClient(updateCovidVaccineHoursRequest((UpdateCovidVaccineHoursRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - UpdateCovidVaccineHoursRequest");
            }
        } else if (msg instanceof GetCovidVaccineHoursRequest) {
            try {
                client.sendToClient(getCovidVaccineHoursRequest((GetCovidVaccineHoursRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - getClinicRequest");
            }
        } else if (msg instanceof GetFreeAppointmentRequest) {
            try {
                client.sendToClient(getFreeAppointmentsRequest((GetFreeAppointmentRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - getALLCovidVaccineRequest");
            }
        } else if (msg instanceof GetPatientAppointmentRequest) {
            try {
                client.sendToClient(getPatientAppointmentsRequest((GetPatientAppointmentRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - GetPatientAppointmentRequest");
            }
        } else if (msg instanceof ReserveAppointmentRequest) {
            try {
                client.sendToClient(addAppointmentsRequest((ReserveAppointmentRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - AddAppointmentRequest");
            }
        } else if (msg instanceof DeleteAppointmentRequest) {
            try {
                client.sendToClient(deleteAppointmentsRequest((DeleteAppointmentRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - DeleteAppointmentRequest");
            }
        } else if (msg instanceof ArriveAppointmentRequest) {
            try {
                client.sendToClient(arriveAppointmentsRequest((ArriveAppointmentRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - DeleteAppointmentRequest");
            }
        } else if (msg instanceof GetGreenPassRequest) {
            try {
                client.sendToClient(getGreenPassRequest((GetGreenPassRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - GetGreenPassRequest");
            }
        } else if (msg instanceof SaveAnswerRequest) {
            try {
                client.sendToClient(saveAnswerRequest((SaveAnswerRequest) msg));
            } catch (IOException e) {
                System.out.println("Error - SaveAnswerRequest");
            }
        } else if (msg instanceof GetQuestionRequest) {
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
        return new RegisterResponse(dataBase.createPatient(request.username, request.password, request.age, dataBase.getClinic(request.clinic)), true);
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
        dataBase.refreshUserToken(user);
        return new LoginResponse(user, true);
    }

    protected <T extends Appointment> Response getFreeAppointmentsRequest(GetFreeAppointmentRequest<T> request) {
        List<T> appointments = new ArrayList<>();
        GetFreeAppointmentsResponse<T> response;

        try {
            if (request.getEnumType() == AppointmentType.FAMILY_OR_CHILDREN) {
                appointments.addAll(dataBase.getFreeAppointments(request.getAppointmentType(), request.getPatient().getClinic(), request.getEnumType()));
                List<T> limited_appointments = new ArrayList<>();
                for (T appointment : appointments) {
                    if ((LocalDateTime.now().plusWeeks(Constants.FOUR_WEEKS)).compareTo(appointment.getTreatmentDateTime()) < 0) {
                        limited_appointments.add(appointment);
                    }
                }
                appointments = limited_appointments;
            } else {
                for (Clinic clinic : dataBase.getAll(Clinic.class)) {
                    appointments.addAll(dataBase.getFreeAppointments(request.getAppointmentType(), clinic, request.getEnumType()));
                }
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
            appointments = dataBase.getUserAppointment((Patient) request.getUser());
            allAppointments = new GetPatientAppointmentResponse(appointments, true);
        } catch (Exception e) {
            allAppointments = new GetPatientAppointmentResponse(appointments, false, e.getMessage());
        }

        return allAppointments;
    }

    protected Response addAppointmentsRequest(ReserveAppointmentRequest request) {
        ReserveAppointmentResponse response;
        try {
            if (request.getAppointment() instanceof CovidTestAppointment) {
                if (!dataBase.hasAnsweredCovidQuestionnaire(request.getUser())) {
                    return new ReserveAppointmentResponse(false, Messages.COVID_TEST_NO_QUESTIONNAIRE);
                }
            }

            ((Patient) request.getUser()).addAppointment(request.getAppointment());

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
            ((Patient) request.getUser()).deleteAppointment(request.getAppointment());
            dataBase.updateAppointment(request.getAppointment());

            response = new DeleteAppointmentResponse(true);
        } catch (Exception e) {
            response = new DeleteAppointmentResponse(false, e.getMessage());
        }
        return response;
    }

    protected Response arriveAppointmentsRequest(ArriveAppointmentRequest request) {
        ArriveAppointmentResponse response;
        try {
            request.getAppointment().setPatientArrived(true);
            dataBase.updateAppointment(request.getAppointment());
            response = new ArriveAppointmentResponse(true);
        } catch (Exception e) {
            response = new ArriveAppointmentResponse(false, e.getMessage());
        }
        return response;
    }

    protected Response updateCovidTestHoursRequest(UpdateCovidTestHoursRequest request) {
        UpdateCovidTestHoursResponse response;
        try {
            Clinic clinic = dataBase.getClinic(request.clinicName);
            LocalTime oldStartH = dataBase.getCovidTestStartHour(clinic);
            LocalTime oldEndH = dataBase.getCovidTestEndHour(clinic);
            LocalTime newStartH = request.activeHours.getOpeningHours(), newEndH = request.activeHours.getClosingHours();
            List<Appointment> Canceled = new ArrayList<Appointment>();

            if (newEndH.isBefore(newStartH)) {
                LocalTime temp = newStartH;
                newStartH = newEndH;
                newEndH = temp;
            }

            //**************OPENING/CLOSING CLINIC HOURS ******************
//        if(newStartH.isBefore(openingHour(...))){
//            newStartH = openingHour(...);
//        }
//        if(newEndH.isAfter(closingHour(..,))){
//            newEndH=closingHour(..,);
//        }

            //**************Cancel/order Appointment & send message/mail ******************
//        if(oldStartH.isBefore(newStartH)){
//            //Canceled.addAll(CanceledAppointments(oldStartH, newStartH, GetFreeAppointmentRequest(CovidTestAppointment.class, AppointmentType.COVID_TEST)));
//        }
//        if(newEndH.isBefore(oldEndH)){
//            //Canceled.addAll(CanceledAppointments(oldStartH, newStartH, GetFreeAppointmentRequest(CovidTestAppointment.class, AppointmentType.COVID_TEST)));
//        }

            // Update test hours
            dataBase.setCovidTestStartHour(clinic, newStartH);
            dataBase.setCovidTestEndHour(clinic, newEndH);
//        // go through the list and ask to get appointment if one of them didn't succeeded to get one
//        // send to the rest that there is no available
//        for(Appointment test : Canceled ){
//            //if(GetCovidTestAppointment(...)== "...")//there is no available Appointments
//            //{**send message**}
//        }
            response = new UpdateCovidTestHoursResponse(true);
        } catch (Exception e) {
            response = new UpdateCovidTestHoursResponse(false, e.getMessage());
        }

        return response;
    }

    protected Response updateCovidVaccineHoursRequest(UpdateCovidVaccineHoursRequest request) {
        UpdateCovidVaccineHoursResponse response;
        try {
            Clinic clinic = dataBase.getClinic(request.clinicName);
            LocalTime oldStartH = dataBase.getCovidVaccineStartHour(clinic);
            LocalTime oldEndH = dataBase.getCovidVaccineEndHour(clinic);
            LocalTime newStartH = request.activeHours.getOpeningHours(), newEndH = request.activeHours.getClosingHours();
            List<Appointment> Canceled = new ArrayList<Appointment>();

            if (newEndH.isBefore(newStartH)) {
                LocalTime temp = newStartH;
                newStartH = newEndH;
                newEndH = temp;
            }

            //**************OPENING/CLOSING CLINIC HOURS ******************
//        if(newStartH.isBefore(openingHour(...))){
//            newStartH = openingHour(...);
//        }
//        if(newEndH.isAfter(closingHour(..,))){
//            newEndH=closingHour(..,);
//        }

            //**************Cancel/order Appointment & send message/mail ******************
//        if(oldStartH.isBefore(newStartH)){
//            //Canceled.addAll(CanceledAppointments(oldStartH, newStartH, GetFreeAppointmentRequest(CovidVaccineAppointment.class, AppointmentType.COVID_VACCINE)));
//        }
//        if(newEndH.isBefore(oldEndH)){
//            //Canceled.addAll(CanceledAppointments(oldStartH, newStartH, GetFreeAppointmentRequest(CovidVaccineAppointment.class, AppointmentType.COVID_VACCINE)));
//        }
            // Update test hours
            dataBase.setCovidVaccineStartHour(clinic, newStartH);
            dataBase.setCovidVaccineEndHour(clinic, newEndH);
            response = new UpdateCovidVaccineHoursResponse(true);
        } catch (Exception e) {
            response = new UpdateCovidVaccineHoursResponse(false, e.getMessage());
        }
//        // go through the list and ask to get appointment if one of them didn't succeeded to get one
//        // send to the rest that there is no available
//        for(Appointment test : Canceled ){
//            //?????//if(GetCovidTestAppointment(...)== "...")//there is no available Appointments
//            //{**send message**}
//        }
        return response;
    }

    protected Response getCovidTestHoursRequest(GetCovidTestHoursRequest request) {
        GetCovidTestHoursResponse response;
        try {
            response = new GetCovidTestHoursResponse(new Hours(dataBase.getCovidTestStartHour(dataBase.getClinic(request.clinicName))
                    , dataBase.getCovidTestEndHour(dataBase.getClinic(request.clinicName))), true);
        } catch (Exception e) {
            response = new GetCovidTestHoursResponse(new Hours(dataBase.getCovidTestStartHour(dataBase.getClinic(request.clinicName))
                    , dataBase.getCovidTestEndHour(dataBase.getClinic(request.clinicName))), false, e.getMessage());
        }

        return response;
    }

    protected Response getCovidVaccineHoursRequest(GetCovidVaccineHoursRequest request) {
        GetCovidVaccineHoursResponse response;
        try {
            response = new GetCovidVaccineHoursResponse(new Hours(dataBase.getCovidVaccineStartHour(dataBase.getClinic(request.clinicName))
                    , dataBase.getCovidVaccineEndHour(dataBase.getClinic(request.clinicName))), true);
        } catch (Exception e) {
            response = new GetCovidVaccineHoursResponse(new Hours(dataBase.getCovidVaccineStartHour(dataBase.getClinic(request.clinicName))
                    , dataBase.getCovidVaccineEndHour(dataBase.getClinic(request.clinicName))), false, e.getMessage());
        }

        return response;
    }

    /*
    -> help function: which return a list of all the appointments that is between (from, to)
     */
    private List<Appointment> CanceledAppointments(LocalTime from, LocalTime to, List<Appointment> appointmentsList) {
        List<Appointment> Canceled = new ArrayList<Appointment>();
        LocalTime testH;
        for (Appointment test : appointmentsList) {
            testH = test.getTreatmentDateTime().toLocalTime();
            if (testH.isBefore(to) && testH.isAfter(from)) {
                Canceled.add(test);
                // DeleteAppointmentRequest(test, App.getActiveUser());
            }
        }
        return Canceled;
    }

    protected Response getGreenPassRequest(GetGreenPassRequest request) {
        Patient patient = null;
        GetGreenPassResponse response;
        try {
            patient = ((Patient) request.getUser());
            response = new GetGreenPassResponse(patient.gotCovidVaccine(), true);
        } catch (Exception e) {
            assert patient != null;
            response = new GetGreenPassResponse(patient.gotCovidVaccine(), false, e.getMessage());
        }
        return response;
    }

    protected Response saveAnswerRequest(SaveAnswerRequest request) {
        SaveAnswerResponse response;
        try {
            dataBase.insertEntity(request.answer);
            response = new SaveAnswerResponse(true);
        } catch (Exception e) {
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
